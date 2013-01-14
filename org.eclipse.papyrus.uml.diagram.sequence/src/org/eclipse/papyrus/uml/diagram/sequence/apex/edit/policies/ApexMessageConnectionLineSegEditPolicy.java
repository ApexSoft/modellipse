package org.eclipse.papyrus.uml.diagram.sequence.apex.edit.policies;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.FigureCanvas;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.gef.requests.BendpointRequest;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gef.ui.parts.ScrollingGraphicalViewer;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.commands.SetBoundsCommand;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.ConnectionBendpointEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.util.SelectInDiagramHelper;
import org.eclipse.gmf.runtime.gef.ui.internal.editpolicies.LineMode;
import org.eclipse.papyrus.uml.diagram.sequence.apex.command.ApexMoveInteractionFragmentsCommand;
import org.eclipse.papyrus.uml.diagram.sequence.apex.interfaces.IApexLifelineEditPart;
import org.eclipse.papyrus.uml.diagram.sequence.apex.util.ApexSequenceDiagramConstants;
import org.eclipse.papyrus.uml.diagram.sequence.apex.util.ApexSequenceRequestConstants;
import org.eclipse.papyrus.uml.diagram.sequence.apex.util.ApexSequenceUtil;
import org.eclipse.papyrus.uml.diagram.sequence.draw2d.routers.MessageRouter.RouterKind;
import org.eclipse.papyrus.uml.diagram.sequence.util.SequenceRequestConstant;
import org.eclipse.papyrus.uml.diagram.sequence.util.SequenceUtil;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Control;
import org.eclipse.uml2.uml.ExecutionSpecification;
import org.eclipse.uml2.uml.InteractionFragment;
import org.eclipse.uml2.uml.Message;
import org.eclipse.uml2.uml.MessageEnd;
import org.eclipse.uml2.uml.MessageOccurrenceSpecification;

/**
 * @author Jiho
 *
 */
@SuppressWarnings("restriction")
public class ApexMessageConnectionLineSegEditPolicy extends
		ConnectionBendpointEditPolicy {
	
	/* from MessageRouter */
	private static final int MAX_DELTA = 10;
	
	public ApexMessageConnectionLineSegEditPolicy() {
		super(LineMode.ORTHOGONAL_FREE);
	}

	@Override
	public Command getCommand(Request request) {
		if(isHorizontal()) {
			return super.getCommand(request);
		}
		return null;
	}

	/**
	 * Move the anchors along with the line and update bendpoints accordingly.
	 */
	@Override
	protected Command getBendpointsChangedCommand(BendpointRequest request) {
		if((getHost().getViewer() instanceof ScrollingGraphicalViewer) && (getHost().getViewer().getControl() instanceof FigureCanvas)) {
			SelectInDiagramHelper.exposeLocation((FigureCanvas)getHost().getViewer().getControl(), request.getLocation().getCopy());
		}

		if(getHost() instanceof ConnectionNodeEditPart) {
			ConnectionNodeEditPart connectionPart = (ConnectionNodeEditPart)getHost();
			Point oldLocation = ApexSequenceUtil.getAbsoluteEdgeExtremity(connectionPart, true);
			Point location = request.getLocation().getCopy();
			Point moveDelta = new Point(0, location.y() - oldLocation.y);

			ChangeBoundsRequest cbRequest = new ChangeBoundsRequest(REQ_MOVE);
			cbRequest.setMoveDelta(moveDelta);
			cbRequest.setLocation(location);
			cbRequest.setExtendedData(request.getExtendedData());

//			Command result = ApexMessageConnectionLineSegmentEditPolicy.apexGetMoveConnectionCommand(cbRequest, connectionPart, isConstrainedMove(cbRequest));
			Command result = getXXXCommand(cbRequest);
			return result;
		}
		return UnexecutableCommand.INSTANCE;
	}

	protected Command getXXXCommand(ChangeBoundsRequest request) {
		if (isReordering(request)) {
			return getReorderingCommand(request);
		}

		ConnectionNodeEditPart connectionPart = (ConnectionNodeEditPart)getHost();
		TransactionalEditingDomain editingDomain = connectionPart.getEditingDomain();
		EditPartViewer viewer = connectionPart.getViewer();

		InteractionFragment container = null;
		Message message = (Message)connectionPart.resolveSemanticElement();
		MessageEnd sendEvent = message.getSendEvent();
		if (sendEvent instanceof MessageOccurrenceSpecification) {
			container = ((MessageOccurrenceSpecification)sendEvent).getEnclosingOperand();
			if (container == null) {
				container = ((MessageOccurrenceSpecification)sendEvent).getEnclosingInteraction();
			}
		}
		if (container == null) {
			return null;
		}

		Point location = request.getLocation().getCopy();
		Point oldLocation = ApexSequenceUtil.getAbsoluteEdgeExtremity(connectionPart, true);
		Rectangle extent = new Rectangle(oldLocation.x, oldLocation.y, 0, 0);
		List<IGraphicalEditPart> linkedEditParts = ApexSequenceUtil.apexGetLinkedEditPartList(connectionPart, false, true, false);
		for (IGraphicalEditPart linkedEditPart : linkedEditParts) {
			extent.union(ApexSequenceUtil.getAbsoluteBounds(linkedEditPart));
		}

		Point realMoveDelta = request.getMoveDelta().getCopy();
		int minY = getMovableTopPosition(connectionPart, false);
		if (minY > location.y) {
			realMoveDelta.y += minY - location.y;
		}

		ApexMoveInteractionFragmentsCommand amifCommand = new ApexMoveInteractionFragmentsCommand(
				editingDomain, viewer, container, extent, realMoveDelta, MARGIN, false);

		Command result = new ICommandProxy(amifCommand);
		return result;
	}

	private Command getReorderingCommand(ChangeBoundsRequest request) {
		CompoundCommand cmpCommand = new CompoundCommand();

		ConnectionNodeEditPart connectionPart = (ConnectionNodeEditPart)getHost();
		TransactionalEditingDomain editingDomain = connectionPart.getEditingDomain();
		EditPartViewer viewer = connectionPart.getViewer();
		Point location = request.getLocation().getCopy();

		Integer[] reorderingLocations = ApexSequenceUtil.apexGetReorderingLocations(connectionPart, location);
		Integer nearLocation = null;

		for (Integer reorderingLocation : reorderingLocations) {
			if ((nearLocation != null && Math.abs(nearLocation + NEAR_LINE_MARGIN - location.y) > Math.abs(reorderingLocation + NEAR_LINE_MARGIN - location.y)) ||
					(Math.abs(reorderingLocation + NEAR_LINE_MARGIN - location.y) < NEAR_LINE_TOLERANCE)) {
				nearLocation = reorderingLocation;
			}
		}

		if (nearLocation == null) {
			return null;
		}

		Point oldLocation = ApexSequenceUtil.getAbsoluteEdgeExtremity(connectionPart, true);
		Point referencePoint = new Point(oldLocation.x, nearLocation);
		InteractionFragment container = SequenceUtil.findInteractionFragmentContainerAt(referencePoint, connectionPart, true);
		if (container == null) {
			return null;
		}

		Rectangle extent = new Rectangle(oldLocation.x, oldLocation.y, 0, 0);
		List<IGraphicalEditPart> linkedEditParts = ApexSequenceUtil.apexGetLinkedEditPartList(connectionPart, false, true, false);
		for (IGraphicalEditPart linkedEditPart : linkedEditParts) {
			extent.union(ApexSequenceUtil.getAbsoluteBounds(linkedEditPart));
		}

		Point realMoveDelta = request.getMoveDelta().getCopy();
		realMoveDelta.y += nearLocation - location.y;

		if (realMoveDelta.y < 0) { // B,A,C의 순서 -> A,B,C의 순서
			// C블록 하위로 이동
			Point newMoveDelta = new Point(0, extent.y - nearLocation);
			ApexMoveInteractionFragmentsCommand cmd = new ApexMoveInteractionFragmentsCommand(editingDomain, viewer, container, new Point(0, extent.bottom() + 1), newMoveDelta, MARGIN);
			cmpCommand.add(new ICommandProxy(cmd));

			// B블록->A블록 하위로 이동
			newMoveDelta = new Point(0, extent.bottom() - nearLocation);
			cmd = new ApexMoveInteractionFragmentsCommand(editingDomain, viewer, container, new Rectangle(0, nearLocation, 0, extent.y - nearLocation - 1), newMoveDelta, MARGIN, true);
			cmpCommand.add(new ICommandProxy(cmd));

			// A블록 포함한 하위 모두 상위로 이동
			newMoveDelta = new Point(0, extent.y - nearLocation).getNegated();
			cmd = new ApexMoveInteractionFragmentsCommand(editingDomain, viewer, container, new Point(0, extent.y), newMoveDelta, MARGIN);
			cmpCommand.add(new ICommandProxy(cmd));

			return cmpCommand;
		}
		else if (realMoveDelta.y > 0) { // A,B,C의 순서 -> B,A,C의 순서
			// C블록 하위로 이동
			Point newMoveDelta = new Point(0, extent.height);
			ApexMoveInteractionFragmentsCommand cmd = new ApexMoveInteractionFragmentsCommand(editingDomain, viewer, container, new Point(0, nearLocation + 1), newMoveDelta, MARGIN);
			cmpCommand.add(new ICommandProxy(cmd));

			// A블록->B블록 하위로 이동
			newMoveDelta = new Point(0, nearLocation - extent.y);
			cmd = new ApexMoveInteractionFragmentsCommand(editingDomain, viewer, container, extent, newMoveDelta, MARGIN, true);
			cmpCommand.add(new ICommandProxy(cmd));

			// B블록 포함한 하위 모두 상위로 이동 (A와 B사이의 공백 계산 불가)
			newMoveDelta = new Point(0, extent.height).getNegated();
			cmd = new ApexMoveInteractionFragmentsCommand(editingDomain, viewer, container, new Point(0, extent.bottom() + 1), newMoveDelta, MARGIN);
			cmpCommand.add(new ICommandProxy(cmd));

			return cmpCommand;
		}

		return null;
	}

	private Command getConstrainedMoveCommand(ChangeBoundsRequest request) {
		ConnectionNodeEditPart connectionPart = (ConnectionNodeEditPart)getHost();
		int minY = getMovableTopPosition(connectionPart, true);
		int maxY = getMovableBottomPosition(connectionPart);
		return null;
	}

	@Override
	protected List createManualHandles() {
		List list = new ArrayList();
		return list;
	}

	private static final int MARGIN = ApexSequenceDiagramConstants.VERTICAL_MARGIN;

	private static final int PADDING = ApexSequenceDiagramConstants.EXECUTION_PADDING;

	private static final int NEAR_LINE_MARGIN = ApexSequenceDiagramConstants.NEAR_LINE_MARGIN;

	private static final int NEAR_LINE_TOLERANCE = ApexSequenceDiagramConstants.NEAR_LINE_TOLERANCE;

	/**
	 * 이동하려는 상단에 ExecutionSpecification이 있는 경우 
	 */
	private static boolean flexiblePrev = false;

	private PolylineConnection feedback;

	private PolylineConnection guideFeedback;

	



	/**
	 * bounds를 변경하는 Command를 생성
	 * @param gep
	 * @param oldBounds
	 * @param newBounds
	 * @param isPreserveAnchorsPosition true이면 gep만 단독으로 변경하는 Command 생성, false이면 Request에 의해 변경하는 Command 그룹 생성
	 * @return
	 */
	private static Command createChangeBoundsCommand(IGraphicalEditPart gep, Rectangle oldBounds, Rectangle newBounds, boolean isPreserveAnchorsPosition) {
		Command command = null;

		if (oldBounds.x == newBounds.x && oldBounds.y == newBounds.y &&
				oldBounds.width == newBounds.width && oldBounds.height == newBounds.height) {
			return null;
		}

		if (!isPreserveAnchorsPosition) {
			TransactionalEditingDomain editingDomain = gep.getEditingDomain();
			Rectangle parentBounds = gep.getFigure().getParent().getBounds();
			// newBounds를 parent(Lifeline)을 기준으로 한 상대좌표로 변경
			gep.getFigure().translateToRelative(newBounds);
			newBounds.translate(-parentBounds.x, -parentBounds.y);
			// end
			command = new ICommandProxy( new SetBoundsCommand(editingDomain, "", gep, newBounds) );
		}
		else {
			ChangeBoundsRequest request = createChangeBoundsRequest(oldBounds, newBounds);
			command = gep.getCommand(request);
		}

		return command;
	}

	/**
	 * bounds를 변경하는 ChangeBoundsRequest 생성
	 * @param oldBounds
	 * @param newBounds
	 * @return
	 */
	private static ChangeBoundsRequest createChangeBoundsRequest(Rectangle oldBounds, Rectangle newBounds) {
		ChangeBoundsRequest request = new ChangeBoundsRequest(REQ_RESIZE);
		Point moveDelta = new Point(newBounds.x - oldBounds.x, newBounds.y - oldBounds.y);
		Dimension sizeDelta = new Dimension(newBounds.width - oldBounds.width, newBounds.height - oldBounds.height);
		request.setMoveDelta(moveDelta);
		request.setSizeDelta(sizeDelta);
		// execution 이동에 의해 editpart들이 겹치는 현상 방지
		request.getExtendedData().put(SequenceRequestConstant.DO_NOT_MOVE_EDIT_PARTS, true);
		if (oldBounds.y == newBounds.y)
			request.setResizeDirection(PositionConstants.SOUTH);
		else if (oldBounds.bottom() == newBounds.bottom())
			request.setResizeDirection(PositionConstants.NORTH);
		else
			request.setResizeDirection(PositionConstants.NORTH_SOUTH);
		return request;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void showMoveLineSegFeedback(BendpointRequest request) {
		if (isConstrainedMove(request)) {
			showConstrainedMoveLineSegFeedback(request, false);
			return;
		}
		showReorderingFeedback(request);

		ConnectionNodeEditPart host = (ConnectionNodeEditPart)getHost();
		Connection connection = host.getConnectionFigure();

		Point location = request.getLocation().getCopy();

		PointList pl = connection.getPoints().getCopy();
		Point oldLocation = ApexSequenceUtil.getAbsoluteEdgeExtremity(host, true);
		PolylineConnection feedbackConnection = getDragSourceFeedbackFigure();

		int dy = location.y - oldLocation.y;

		for (int i = 0; i < pl.size(); i++) {
			Point p = pl.getPoint(i);
			p.y += dy;
			pl.setPoint(p, i);
		}

		feedbackConnection.setForegroundColor(connection.getLocalForegroundColor());
		feedbackConnection.setPoints(pl);
	}

	public void eraseSourceFeedback(Request request) {
		if (feedback != null) {
			removeFeedback(feedback);
		}
		feedback = null;
		if (guideFeedback != null) {
			removeFeedback(guideFeedback);
		}
		guideFeedback = null;
		super.eraseSourceFeedback(request);
	}

	private void showConstrainedMoveLineSegFeedback(BendpointRequest request, boolean isFlexible) {
		ConnectionNodeEditPart host = (ConnectionNodeEditPart)getHost();
		Connection connection = host.getConnectionFigure();

		Point location = request.getLocation().getCopy();
		connection.translateToRelative(location);

		PointList pl = connection.getPoints().getCopy();
		Point oldLocation = pl.getFirstPoint().getCopy();
		PolylineConnection feedbackConnection = getDragSourceFeedbackFigure();

		int dy = location.y - oldLocation.y;

		int minY = getMovableTopPosition(host, isFlexible);
		int maxY = getMovableBottomPosition(host);
		if (minY > location.y) {
			dy = minY - oldLocation.y;
		} else if (maxY < location.y) {
			dy = maxY - oldLocation.y;
		}

		for (int i = 0; i < pl.size(); i++) {
			Point p = pl.getPoint(i);
			p.y += dy;
			pl.setPoint(p, i);
		}

		feedbackConnection.setForegroundColor(ApexSequenceDiagramConstants.CONSTRAINED_MOVE_LINE_COLOR);
		feedbackConnection.setPoints(pl);
	}

	private void showReorderingFeedback(BendpointRequest request) {
		ConnectionNodeEditPart host = (ConnectionNodeEditPart)getHost();
		Point location = request.getLocation().getCopy();
		Integer[] reorderingLocations = ApexSequenceUtil.apexGetReorderingLocations(host, location);
		Integer nearLocation = null;

		for (Integer reorderingLocation : reorderingLocations) {
			if ((nearLocation != null && Math.abs(nearLocation + NEAR_LINE_MARGIN - location.y) > Math.abs(reorderingLocation + NEAR_LINE_MARGIN - location.y)) ||
					(Math.abs(reorderingLocation + NEAR_LINE_MARGIN - location.y) < NEAR_LINE_TOLERANCE)) {
				nearLocation = reorderingLocation;
			}
		}

		if (!isReordering(request) || nearLocation == null) {
			if (guideFeedback != null) {
				removeFeedback(guideFeedback);
			}
			guideFeedback = null;
		} else {
			if (guideFeedback == null) {
				guideFeedback = createGuideLineFeedbackFigure();
			}

			Control control = getHost().getViewer().getControl();
			int viewPortXLocation = control.getBounds().x;
			int viewPortXExtent = control.getBounds().x + control.getBounds().width;
			if (control instanceof FigureCanvas) {
				FigureCanvas canvas = (FigureCanvas)control;
				Rectangle bounds = canvas.getViewport().getBounds().getCopy();
				canvas.getViewport().translateFromParent(bounds);
				viewPortXLocation = bounds.x;
				viewPortXExtent = bounds.x + bounds.width - 1;
				nearLocation += bounds.y;
			}
			PointList pl = new PointList();
			pl.addPoint(viewPortXLocation, nearLocation + NEAR_LINE_MARGIN);
			pl.addPoint(viewPortXExtent, nearLocation + NEAR_LINE_MARGIN);

			guideFeedback.setForegroundColor(ApexSequenceDiagramConstants.REORDERING_LINE_COLOR);
			guideFeedback.setPoints(pl);
			addFeedback(guideFeedback);
		}		
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected PolylineConnection createDragSourceFeedbackConnection() {
		PolylineConnection connection = new PolylineConnection();
		connection.setLineWidth(1);
		connection.setLineStyle(Graphics.LINE_DASHDOT);
		connection.setForegroundColor(getConnection().getLocalForegroundColor());
		return connection;
	}

	/**
	 * Returns feedback figure
	 * @return
	 */
	protected PolylineConnection getDragSourceFeedbackFigure() {
		if (feedback == null) {
			feedback = createDragSourceFeedbackConnection();
			addFeedback(feedback);
		}
		return feedback;
	}

	/**
	 * Return guide feedback figure
	 * @return
	 */
	protected PolylineConnection createGuideLineFeedbackFigure() {
		PolylineConnection connection = new PolylineConnection();
		connection.setLineWidth(1);
		connection.setLineStyle(Graphics.LINE_DASH);
		connection.setForegroundColor(getConnection().getLocalForegroundColor());
		return connection;
	}

	private boolean isHorizontal() {
		Connection connection = getConnection();
		RouterKind kind = RouterKind.getKind(connection, connection.getPoints());
		if(kind.equals(RouterKind.HORIZONTAL)) {
			return true;
		}
		return false;
	}

	/**
	 * @param request
	 * @return SHIFT 키가 눌렸는지 여부
	 */
	protected boolean isConstrainedMove(Request request) {
		return Boolean.TRUE.equals(request.getExtendedData()
				.get(ApexSequenceRequestConstants.APEX_MODIFIER_CONSTRAINED_MOVE));
	}

	/**
	 * @param request
	 * @return CTRL 키가 눌렸는지 여부
	 */
	protected boolean isReordering(Request request) {
		return Boolean.TRUE.equals(request.getExtendedData()
				.get(ApexSequenceRequestConstants.APEX_MODIFIER_REORDERING));
	}

	protected int getMovableTopPosition(ConnectionNodeEditPart connectionPart, boolean isFlexible) {
		int topMost = Integer.MIN_VALUE, movableTop = Integer.MIN_VALUE;

		List<IGraphicalEditPart> siblingParts = ApexSequenceUtil.apexGetPrevSiblingEditParts(connectionPart);
		List<IGraphicalEditPart> frontLinkedParts = ApexSequenceUtil.apexGetLinkedEditPartList(connectionPart, true, false, true);
		IGraphicalEditPart realPrevPart = null;

		for (IGraphicalEditPart siblingPart : siblingParts) {
			topMost = Math.max(topMost, ApexSequenceUtil.apexGetAbsolutePosition(siblingPart, SWT.BOTTOM) + MARGIN);
			movableTop = Math.max(movableTop, topMost);

			if (siblingPart instanceof ConnectionNodeEditPart && !frontLinkedParts.contains(connectionPart)) {
				// activation중 가장 하위 검색. realMinY는 activation 포함 가장 하위 y값
				ConnectionNodeEditPart prevConnPart = (ConnectionNodeEditPart)siblingPart;
				EditPart prevSourcePart = prevConnPart.getSource();
				EditPart prevTargetPart = prevConnPart.getTarget();
				
				if ( prevSourcePart instanceof IGraphicalEditPart ) {
					EObject ePrevSrcPartObj = ((IGraphicalEditPart) prevSourcePart).resolveSemanticElement(); 
				
					if (ePrevSrcPartObj instanceof ExecutionSpecification && prevSourcePart instanceof ShapeNodeEditPart) {
						int ty = ApexSequenceUtil.apexGetAbsolutePosition((IGraphicalEditPart)prevSourcePart, SWT.BOTTOM) + MARGIN;
						if (movableTop < ty) {
							movableTop = ty;
							realPrevPart = (IGraphicalEditPart)prevSourcePart;
						}
					}
				}
				
				if ( prevTargetPart instanceof IGraphicalEditPart ) {
					EObject ePrevTgtPartObj = ((IGraphicalEditPart) prevTargetPart).resolveSemanticElement();
					
					if (ePrevTgtPartObj instanceof ExecutionSpecification && prevTargetPart instanceof ShapeNodeEditPart) {
						int ty = ApexSequenceUtil.apexGetAbsolutePosition((IGraphicalEditPart)prevTargetPart, SWT.BOTTOM) + MARGIN;
						if (movableTop < ty) {
							movableTop = ty;
							realPrevPart = (IGraphicalEditPart)prevTargetPart;
						}
					}	
				}
				
			}
		}

		if (siblingParts.size() == 0 || realPrevPart == null) {
			EditPart sourcePart = connectionPart.getSource();
			ShapeNodeEditPart srcLifelinePart = SequenceUtil.getParentLifelinePart(sourcePart);
			IFigure dotLine = ((IApexLifelineEditPart)srcLifelinePart).getNodeFigure();
			Rectangle dotLineBounds = dotLine.getBounds().getCopy();
			dotLine.translateToAbsolute(dotLineBounds);
			topMost = dotLineBounds.y() + MARGIN;
			movableTop = topMost;
			return movableTop;
		}
		
		EObject eObj = realPrevPart.resolveSemanticElement();
		
		if (isFlexible && (eObj instanceof ExecutionSpecification && realPrevPart instanceof ShapeNodeEditPart)) {
			Dimension minSize = realPrevPart.getFigure().getMinimumSize();
			int bottom = ApexSequenceUtil.apexGetAbsolutePosition(realPrevPart, SWT.TOP) + minSize.height();
			topMost = Math.max(topMost, bottom);
		}
		else {
			topMost = movableTop;
		}
		return topMost;
	}

	protected int getMovableBottomPosition(ConnectionNodeEditPart connectionPart) {
		int movableBottom = Integer.MAX_VALUE;

		List<IGraphicalEditPart> siblingParts = ApexSequenceUtil.apexGetNextSiblingEditParts(connectionPart);
		List<IGraphicalEditPart> linkedParts = ApexSequenceUtil.apexGetLinkedEditPartList(connectionPart, true, true, false);

		Rectangle linkedBounds = ApexSequenceUtil.apexGetAbsoluteRectangle(connectionPart);
		for (IGraphicalEditPart linkedPart : linkedParts) {
			linkedBounds.union(ApexSequenceUtil.apexGetAbsoluteRectangle(linkedPart));
		}
		for (IGraphicalEditPart siblingPart : siblingParts) {
			if (!linkedParts.contains(siblingPart)) {
				movableBottom = Math.min(movableBottom, ApexSequenceUtil.apexGetAbsolutePosition(siblingPart, SWT.TOP) - MARGIN);
			}
		}

		return movableBottom - linkedBounds.height;
	}
}