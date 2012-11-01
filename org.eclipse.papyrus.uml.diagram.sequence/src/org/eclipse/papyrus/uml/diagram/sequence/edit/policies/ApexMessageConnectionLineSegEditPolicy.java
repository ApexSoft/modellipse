package org.eclipse.papyrus.uml.diagram.sequence.edit.policies;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import kr.co.apexsoft.modellipse.customization.diagram.sequence.util.ApexOperandBoundsComputeHelper;
import kr.co.apexsoft.modellipse.customization.diagram.sequence.util.ApexSequenceRequestConstants;
import kr.co.apexsoft.modellipse.customization.diagram.sequence.util.ApexSequenceUtil;

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
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.ConnectionBendpointEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.util.SelectInDiagramHelper;
import org.eclipse.gmf.runtime.gef.ui.internal.editpolicies.LineMode;
import org.eclipse.papyrus.uml.diagram.sequence.command.ApexMoveInteractionFragmentsCommand;
import org.eclipse.papyrus.uml.diagram.sequence.draw2d.routers.MessageRouter.RouterKind;
import org.eclipse.papyrus.uml.diagram.sequence.edit.parts.AbstractExecutionSpecificationEditPart;
import org.eclipse.papyrus.uml.diagram.sequence.edit.parts.LifelineEditPart;
import org.eclipse.papyrus.uml.diagram.sequence.part.Messages;
import org.eclipse.papyrus.uml.diagram.sequence.util.ApexOccurrenceSpecificationMoveHelper;

import org.eclipse.papyrus.uml.diagram.sequence.util.SequenceRequestConstant;
import org.eclipse.papyrus.uml.diagram.sequence.util.SequenceUtil;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Control;
import org.eclipse.uml2.uml.InteractionFragment;
import org.eclipse.uml2.uml.Message;
import org.eclipse.uml2.uml.MessageEnd;
import org.eclipse.uml2.uml.MessageOccurrenceSpecification;
import org.eclipse.uml2.uml.OccurrenceSpecification;

/**
 * @author Jiho
 *
 */
@SuppressWarnings("restriction")
public class ApexMessageConnectionLineSegEditPolicy extends
		ConnectionBendpointEditPolicy {
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
			Point oldLocation = SequenceUtil.getAbsoluteEdgeExtremity(connectionPart, true);
			Point location = request.getLocation().getCopy();
			Point moveDelta = new Point(0, location.y() - oldLocation.y);

			ChangeBoundsRequest cbRequest = new ChangeBoundsRequest(REQ_MOVE);
			cbRequest.setMoveDelta(moveDelta);
			cbRequest.setLocation(location);
			cbRequest.setExtendedData(request.getExtendedData());

			Command result = apexGetMoveConnectionCommand(cbRequest, connectionPart, isConstrainedMove(cbRequest));
//			Command result = getXXXCommand(cbRequest);
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
		Point oldLocation = SequenceUtil.getAbsoluteEdgeExtremity(connectionPart, true);
		Rectangle extent = new Rectangle(oldLocation.x, oldLocation.y, 0, 0);
		List<IGraphicalEditPart> linkedEditParts = ApexSequenceUtil.apexGetLinkedEditPartList(connectionPart, false, true, false);
		for (IGraphicalEditPart linkedEditPart : linkedEditParts) {
			extent.union(SequenceUtil.getAbsoluteBounds(linkedEditPart));
		}

		Point realMoveDelta = request.getMoveDelta().getCopy();
		int minY = getMovableTop(connectionPart, false);
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

		Point oldLocation = SequenceUtil.getAbsoluteEdgeExtremity(connectionPart, true);
		Point referencePoint = new Point(oldLocation.x, nearLocation);
		InteractionFragment container = SequenceUtil.findInteractionFragmentContainerAt(referencePoint, connectionPart);
		if (container == null) {
			return null;
		}

		Rectangle extent = new Rectangle(oldLocation.x, oldLocation.y, 0, 0);
		List<IGraphicalEditPart> linkedEditParts = ApexSequenceUtil.apexGetLinkedEditPartList(connectionPart, false, true, false);
		for (IGraphicalEditPart linkedEditPart : linkedEditParts) {
			extent.union(SequenceUtil.getAbsoluteBounds(linkedEditPart));
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
		int minY = getMovableTop(connectionPart, true);
		int maxY = getMovableBottom(connectionPart);
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
	 * 
	 * @param request
	 * @param connectionPart
	 * @param moveAlone
	 * @return
	 */
	public static Command apexGetMoveConnectionCommand(ChangeBoundsRequest request, ConnectionNodeEditPart connectionPart, boolean moveAlone) {
		EObject message = connectionPart.resolveSemanticElement();
		if(message instanceof Message) {
			MessageEnd send = ((Message)message).getSendEvent();
			MessageEnd rcv = ((Message)message).getReceiveEvent();
			EditPart srcPart = connectionPart.getSource();
			LifelineEditPart srcLifelinePart = SequenceUtil.getParentLifelinePart(srcPart);
			EditPart tgtPart = connectionPart.getTarget();
			LifelineEditPart tgtLifelinePart = SequenceUtil.getParentLifelinePart(tgtPart);

			CompoundCommand compoundCmd = new CompoundCommand(Messages.MoveMessageCommand_Label);

			if(send instanceof OccurrenceSpecification && rcv instanceof OccurrenceSpecification && srcLifelinePart != null && tgtLifelinePart != null) {
				Point moveDelta = request.getMoveDelta().getCopy();
				int moveDeltaY = moveDelta.y();

				Point oldLocation = ApexSequenceUtil.apexGetAbsoluteRectangle(connectionPart).getLocation();
				if (oldLocation == null)
					return null;

				int y = oldLocation.y() + moveDeltaY;
				List<EditPart> empty = Collections.emptyList();

				int minY = Integer.MIN_VALUE, maxY = Integer.MAX_VALUE;
				int realMinY = Integer.MIN_VALUE;
				IGraphicalEditPart realPrevPart = null;	// ExecutionSpecificationEditPart 포함하여 가장 하위
				List<IGraphicalEditPart> prevParts = ApexSequenceUtil.apexGetPrevSiblingEditParts(connectionPart);
				List<IGraphicalEditPart> frontLinkedParts = ApexSequenceUtil.apexGetLinkedEditPartList(connectionPart, true, false, true);
				for (IGraphicalEditPart part : prevParts) {
					minY = Math.max(minY, ApexSequenceUtil.apexGetAbsolutePosition(part, SWT.BOTTOM) + MARGIN);
					if (realMinY < minY) {
						realMinY = minY;
					}

					if (part instanceof ConnectionNodeEditPart && !frontLinkedParts.contains(part)) {
						// activation중 가장 하위 검색. realMinY는 activation 포함 가장 하위 y값
						ConnectionNodeEditPart prevConnPart = (ConnectionNodeEditPart)part;
						EditPart prevSourcePart = prevConnPart.getSource();
						EditPart prevTargetPart = prevConnPart.getTarget();
						if (prevSourcePart instanceof AbstractExecutionSpecificationEditPart) {
							int tMinY = ApexSequenceUtil.apexGetAbsolutePosition((IGraphicalEditPart)prevTargetPart, SWT.BOTTOM) + MARGIN;
							if (realMinY < tMinY) {
								realMinY = tMinY;
								realPrevPart = (IGraphicalEditPart)prevTargetPart;
							}
						}
						if (prevTargetPart instanceof AbstractExecutionSpecificationEditPart) {
							int ty = ApexSequenceUtil.apexGetAbsolutePosition((IGraphicalEditPart)prevTargetPart, SWT.BOTTOM) + MARGIN;
							if (realMinY < ty) {
								realMinY = ty;
								realPrevPart = (IGraphicalEditPart)prevTargetPart;
							}
						}
					}
				}

				if (prevParts.size() == 0) {
					IFigure dotLine = srcLifelinePart.getPrimaryShape().getFigureLifelineDotLineFigure();
					Rectangle dotLineBounds = dotLine.getBounds().getCopy();
					dotLine.translateToAbsolute(dotLineBounds);
					minY = dotLineBounds.y() + MARGIN;
					realMinY = minY;
				}

				if (flexiblePrev && realPrevPart instanceof AbstractExecutionSpecificationEditPart) {
					Dimension minimumSize = realPrevPart.getFigure().getMinimumSize();
					int minimumBottom = ApexSequenceUtil.apexGetAbsolutePosition(realPrevPart, SWT.TOP) + minimumSize.height();
					minY = Math.max(minY, minimumBottom);
				}
				else {
					minY = realMinY;
				}

				List<IGraphicalEditPart> nextParts = ApexSequenceUtil.apexGetNextSiblingEditParts(connectionPart);
				for (IGraphicalEditPart part : nextParts) {
					int ty = ApexSequenceUtil.apexGetAbsolutePosition(part, SWT.TOP) - MARGIN;
					if (maxY > ty) {
						maxY = ty;
					}
				}

				if (moveAlone) {
					// Target인 Activation의 Minimumsize 이하로 줄어들 수 없음
					if (tgtPart instanceof AbstractExecutionSpecificationEditPart) {
						List sourceConnections = ((IGraphicalEditPart)tgtPart).getSourceConnections();
						if (sourceConnections == null || sourceConnections.size() == 0) {
							int minimumHeight = ((IGraphicalEditPart)tgtPart).getFigure().getMinimumSize().height();
							int bottom = ApexSequenceUtil.apexGetAbsolutePosition((IGraphicalEditPart)tgtPart, SWT.BOTTOM);
							maxY = bottom - minimumHeight;
						}
					}
					y = Math.min(maxY, Math.max(minY, y));
					moveDeltaY = y - oldLocation.y();

					// source : AbstractExecutionSpecificationEditPart
					if (srcPart instanceof AbstractExecutionSpecificationEditPart) {
						IGraphicalEditPart srcExecSpecEP = (IGraphicalEditPart)srcPart;
						Rectangle oldBounds = ApexSequenceUtil.apexGetAbsoluteRectangle(srcExecSpecEP);
						Rectangle newBounds = oldBounds.getCopy();
						if (newBounds.bottom() < y + PADDING) {
							newBounds.height = y + PADDING - newBounds.y;
						}

						compoundCmd.add( createChangeBoundsCommand(srcExecSpecEP, oldBounds, newBounds, true) );
						compoundCmd.add( ApexOccurrenceSpecificationMoveHelper.getMoveMessageOccurrenceSpecificationsCommand(
								(OccurrenceSpecification)send, y, newBounds, srcPart, srcLifelinePart, empty) );
					}
					else if (srcPart.equals(srcLifelinePart)) { // source : LifelineEditPart
						IFigure figure = srcLifelinePart.getPrimaryShape().getFigureLifelineDotLineFigure();
						Rectangle oldBounds = figure.getBounds().getCopy();
						figure.translateToAbsolute(oldBounds);
						Rectangle newBounds = oldBounds.getCopy();
						if (newBounds.bottom() < y + MARGIN) {
							newBounds.height = y + MARGIN - oldBounds.y;
						}

						compoundCmd.add( createChangeBoundsCommand(srcLifelinePart, oldBounds, newBounds, true) );
						compoundCmd.add( ApexOccurrenceSpecificationMoveHelper.getMoveMessageOccurrenceSpecificationsCommand(
								(OccurrenceSpecification)send, y, newBounds, srcPart, srcLifelinePart, empty) );
					}

					// target : AbstractExecutionSpecificationEditPart
					if (tgtPart instanceof AbstractExecutionSpecificationEditPart) {
						IGraphicalEditPart tgtExecSpecEP = (IGraphicalEditPart)tgtPart;
						Rectangle oldBounds = ApexSequenceUtil.apexGetAbsoluteRectangle(tgtExecSpecEP);
						Rectangle newBounds = oldBounds.getCopy();
						newBounds.y = y;
						newBounds.height -= moveDeltaY; 

						compoundCmd.add( createChangeBoundsCommand(tgtExecSpecEP, oldBounds, newBounds, true) );
					}
				}
				else {
					if (moveDeltaY < 0) {
						y = Math.min(maxY, Math.max(minY, y));
						moveDeltaY = y - oldLocation.y();
					}

					// flexiblePrev인 경우, 상당 ExecutionSpecification의 크기 줄임
					if (flexiblePrev && realPrevPart instanceof AbstractExecutionSpecificationEditPart && realMinY > y) {
						Rectangle oldBounds = ApexSequenceUtil.apexGetAbsoluteRectangle(realPrevPart);
						Rectangle newBounds = oldBounds.getCopy();
						newBounds.height += (y - realMinY); 

						compoundCmd.add( createChangeBoundsCommand(realPrevPart, oldBounds, newBounds, true) );
					}

					Command sendMessageMoveCmd = null;
					if (srcPart instanceof AbstractExecutionSpecificationEditPart) {
						IGraphicalEditPart srcExecSpecEP = (IGraphicalEditPart)srcPart;

						ConnectionNodeEditPart lastConnPart = null;
						int lastY = Integer.MIN_VALUE;
						List srcConnParts = srcExecSpecEP.getSourceConnections();
						Iterator iter = srcConnParts.iterator();
						while (iter.hasNext()) {
							ConnectionNodeEditPart srcConnPart = (ConnectionNodeEditPart)iter.next();
							EObject semanticElement = srcConnPart.resolveSemanticElement();
							if (semanticElement instanceof Message) {
								MessageEnd sendEvent = ((Message)semanticElement).getSendEvent();
								Point location = SequenceUtil.findLocationOfMessageOccurrence((GraphicalEditPart) srcExecSpecEP, (MessageOccurrenceSpecification) sendEvent);
//								Point location = ApexSequenceUtil.apexGetAbsoluteRectangle(srcConnPart).getLocation();
								if (lastY < location.y) {
									lastY = location.y;
									lastConnPart = srcConnPart;
								}
							}
						}

						Rectangle oldBounds = ApexSequenceUtil.apexGetAbsoluteRectangle(srcExecSpecEP);
						Rectangle newBounds = oldBounds.getCopy();

						if (connectionPart.equals(lastConnPart)) {
							newBounds.height = oldBounds.height + moveDeltaY;
							compoundCmd.add( createChangeBoundsCommand(srcExecSpecEP, oldBounds, newBounds, true) );
						}

						if (moveDeltaY > 0) {
							newBounds.height = oldBounds.height + moveDeltaY;
						}
						sendMessageMoveCmd = ApexOccurrenceSpecificationMoveHelper.getMoveMessageOccurrenceSpecificationsCommand(
								(OccurrenceSpecification)send, y, newBounds, srcPart, srcLifelinePart, empty);
					}
					else if (srcPart.equals(srcLifelinePart)) { // source : LifelineEditPart
						IFigure figure = srcLifelinePart.getPrimaryShape().getFigureLifelineDotLineFigure();
						Rectangle oldBounds = figure.getBounds().getCopy();
						figure.translateToAbsolute(oldBounds);
						Rectangle newBounds = oldBounds.getCopy();
						if (newBounds.bottom() < y + MARGIN) {
							newBounds.height = y + MARGIN - oldBounds.y;
						}

						compoundCmd.add( createChangeBoundsCommand(srcLifelinePart, oldBounds, newBounds, true) );
						compoundCmd.add( ApexOccurrenceSpecificationMoveHelper.getMoveMessageOccurrenceSpecificationsCommand(
								(OccurrenceSpecification)send, y, newBounds, srcPart, srcLifelinePart, empty) );
					}

					List<IGraphicalEditPart> linkedParts = ApexSequenceUtil.apexGetLinkedEditPartList(connectionPart, false, true, false);
					for (IGraphicalEditPart linkedPart : linkedParts) {
						Rectangle oldBounds = ApexSequenceUtil.apexGetAbsoluteRectangle(linkedPart);
						Rectangle newBounds = oldBounds.getCopy();
						newBounds.y += moveDeltaY;

						compoundCmd.add( createChangeBoundsCommand(linkedPart, oldBounds, newBounds, false) );
					}

					if (moveDeltaY > 0) {
						linkedParts = ApexSequenceUtil.apexGetLinkedEditPartList(connectionPart, true, true, false);

						// containing Operand Resize 처리 - omw
						ShapeNodeEditPart ioep = ApexSequenceUtil.apexGetEnclosingInteractionOperandEditpart(connectionPart);
						if ( ioep != null ) {

							ChangeBoundsRequest cbRequest = new ChangeBoundsRequest();
							cbRequest.setSizeDelta(new Dimension(0, moveDeltaY));
							cbRequest.setResizeDirection(PositionConstants.SOUTH);
							compoundCmd.add(ApexOperandBoundsComputeHelper.createIOEPResizeCommand(cbRequest, ioep));	
						}	

						nextParts.removeAll(linkedParts);
						if (nextParts.size() > 0) {
							IGraphicalEditPart nextSiblingEditPart = nextParts.get(0);
							if (nextSiblingEditPart instanceof ConnectionNodeEditPart) {
								Command nextCmd = apexGetMoveConnectionCommand(request, (ConnectionNodeEditPart) nextSiblingEditPart, moveAlone);
								compoundCmd.add(nextCmd);
							}
							else {
								Command nextCmd = nextSiblingEditPart.getCommand(request);
								compoundCmd.add(nextCmd);
//								apexGetResizeOrMoveBelowItemsCommand(request, nextSiblingEditPart);
							}
						}
					}

					compoundCmd.add(sendMessageMoveCmd);
				}

				return compoundCmd.size() > 0 ? compoundCmd : null;
			}
		}

		return null;
	}

	/**
	 * Message보다 하위의 item들을 delta만큼 이동
	 * @param request
	 * @param abstractGraphicalEditPart
	 * @return
	 */
	private static Command apexGetResizeOrMoveBelowItemsCommand(ChangeBoundsRequest request, IGraphicalEditPart gep) {
		CompoundCommand command = new CompoundCommand();
		gep.getCommand(request);
		command.add(InteractionCompartmentXYLayoutEditPolicy.getCombinedFragmentResizeChildrenCommand(request, (GraphicalEditPart)gep));
		return command;
	}

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
		Point oldLocation = SequenceUtil.getAbsoluteEdgeExtremity(host, true);
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

		int minY = getMovableTop(host, isFlexible);
		int maxY = getMovableBottom(host);
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

	protected int getMovableTop(ConnectionNodeEditPart connectionPart, boolean isFlexible) {
		int minTop = Integer.MIN_VALUE, maxTop = Integer.MIN_VALUE;

		List<IGraphicalEditPart> siblingParts = ApexSequenceUtil.apexGetPrevSiblingEditParts(connectionPart);
		List<IGraphicalEditPart> frontLinkedParts = ApexSequenceUtil.apexGetLinkedEditPartList(connectionPart, true, false, true);
		IGraphicalEditPart realPrevPart = null;

		for (IGraphicalEditPart siblingPart : siblingParts) {
			minTop = Math.max(minTop, ApexSequenceUtil.apexGetAbsolutePosition(siblingPart, SWT.BOTTOM) + MARGIN);
			maxTop = Math.max(maxTop, minTop);

			if (siblingPart instanceof ConnectionNodeEditPart && !frontLinkedParts.contains(connectionPart)) {
				// activation중 가장 하위 검색. realMinY는 activation 포함 가장 하위 y값
				ConnectionNodeEditPart prevConnPart = (ConnectionNodeEditPart)siblingPart;
				EditPart prevSourcePart = prevConnPart.getSource();
				EditPart prevTargetPart = prevConnPart.getTarget();
				if (prevSourcePart instanceof AbstractExecutionSpecificationEditPart) {
					int ty = ApexSequenceUtil.apexGetAbsolutePosition((IGraphicalEditPart)prevTargetPart, SWT.BOTTOM) + MARGIN;
					if (maxTop < ty) {
						maxTop = ty;
						realPrevPart = (IGraphicalEditPart)prevTargetPart;
					}
				}
				if (prevTargetPart instanceof AbstractExecutionSpecificationEditPart) {
					int ty = ApexSequenceUtil.apexGetAbsolutePosition((IGraphicalEditPart)prevTargetPart, SWT.BOTTOM) + MARGIN;
					if (maxTop < ty) {
						maxTop = ty;
						realPrevPart = (IGraphicalEditPart)prevTargetPart;
					}
				}
			}
		}

		if (siblingParts.size() == 0) {
			EditPart sourcePart = connectionPart.getSource();
			LifelineEditPart srcLifelinePart = SequenceUtil.getParentLifelinePart(sourcePart);
			IFigure dotLine = srcLifelinePart.getNodeFigure();
			Rectangle dotLineBounds = dotLine.getBounds().getCopy();
			dotLine.translateToAbsolute(dotLineBounds);
			minTop = dotLineBounds.y() + MARGIN;
			maxTop = minTop;
		}

		if (isFlexible && realPrevPart instanceof AbstractExecutionSpecificationEditPart) {
			Dimension minSize = realPrevPart.getFigure().getMinimumSize();
			int bottom = ApexSequenceUtil.apexGetAbsolutePosition(realPrevPart, SWT.TOP) + minSize.height();
			minTop = Math.max(minTop, bottom);
		}
		else {
			minTop = maxTop;
		}
		return minTop;
	}

	protected int getMovableBottom(ConnectionNodeEditPart connectionPart) {
		int bottom = Integer.MAX_VALUE;

		List<IGraphicalEditPart> siblingParts = ApexSequenceUtil.apexGetNextSiblingEditParts(connectionPart);
		List<IGraphicalEditPart> linkedParts = ApexSequenceUtil.apexGetLinkedEditPartList(connectionPart, true, true, false);

		Rectangle linkedBounds = ApexSequenceUtil.apexGetAbsoluteRectangle(connectionPart);
		for (IGraphicalEditPart linkedPart : linkedParts) {
			linkedBounds.union(ApexSequenceUtil.apexGetAbsoluteRectangle(linkedPart));
		}
		for (IGraphicalEditPart siblingPart : siblingParts) {
			if (!linkedParts.contains(siblingPart)) {
				bottom = Math.min(bottom, ApexSequenceUtil.apexGetAbsolutePosition(siblingPart, SWT.TOP) - MARGIN);
			}
		}

		return bottom - linkedBounds.height;
	}

}