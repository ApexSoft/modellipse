package kr.co.apexsoft.modellipse.customization.diagram.sequence.edit.policies;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import kr.co.apexsoft.modellipse.customization.diagram.sequence.commands.ApexMoveInteractionFragmentsCommand;
import kr.co.apexsoft.modellipse.customization.diagram.sequence.interfaces.IApexLifelineEditPart;
import kr.co.apexsoft.modellipse.customization.diagram.sequence.util.ApexOccurrenceSpecificationMoveHelper;
import kr.co.apexsoft.modellipse.customization.diagram.sequence.util.ApexSequenceDiagramConstants;
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
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.gef.editpolicies.SelectionHandlesEditPolicy;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.commands.SetBoundsCommand;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewRequest.ViewDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Control;
import org.eclipse.uml2.uml.ExecutionSpecification;
import org.eclipse.uml2.uml.InteractionFragment;
import org.eclipse.uml2.uml.Message;
import org.eclipse.uml2.uml.MessageEnd;
import org.eclipse.uml2.uml.MessageOccurrenceSpecification;
import org.eclipse.uml2.uml.OccurrenceSpecification;

/**
 * @author Jiho
 *
 * @deprecated
 */
public class ApexConnectionMoveEditPolicy extends SelectionHandlesEditPolicy {

	public final static String CONNECTION_MOVE_ROLE = "ApexConnectionMoveEditPolicy"; //$NON-NLS-1$ 

	private static final int MARGIN = ApexSequenceDiagramConstants.VERTICAL_MARGIN;

	private static final int PADDING = ApexSequenceDiagramConstants.EXECUTION_PADDING;

	private static final int NEAR_LINE_MARGIN = 2;

	private static final int NEAR_LINE_TOLERANCE = 10;

	private static boolean flexiblePrev = false;

	private PolylineConnection feedback;

	private PolylineConnection guideFeedback;

	@Override
	protected List<Object> createSelectionHandles() {
		List<Object> list = new ArrayList<Object>();
		return list;
	}

	/**
	 * Returns the Connection associated with this EditPolicy.
	 */
	protected Connection getConnection() {
		return (Connection) ((ConnectionEditPart) getHost()).getFigure();
	}

	@Override
	public Command getCommand(Request request) {
		if (request instanceof ChangeBoundsRequest) {
			if (REQ_MOVE.equals(request.getType())) {
				return getMoveConnectionCommand((ChangeBoundsRequest)request);
			}
		}
		return super.getCommand(request);
	}

	protected Command getMoveConnectionCommand(ChangeBoundsRequest request) {
		if(getHost() instanceof ConnectionNodeEditPart) {
			ConnectionNodeEditPart connectionPart = (ConnectionNodeEditPart)getHost();
			Command result = apexGetMoveConnectionCommand(request, connectionPart, request.isConstrainedMove());

			TransactionalEditingDomain editingDomain = connectionPart.getEditingDomain();
			EditPartViewer viewer = connectionPart.getViewer();
			Message message = (Message)connectionPart.resolveSemanticElement();
			MessageEnd sendEvent = message.getSendEvent();
			InteractionFragment container = null;
			if (sendEvent instanceof MessageOccurrenceSpecification) {
				container = ((MessageOccurrenceSpecification)sendEvent).getEnclosingOperand();
				if (container == null) {
					container = ((MessageOccurrenceSpecification)sendEvent).getEnclosingInteraction();
				}
			}
			if (container == null) {
				return null;
			}

			Point location = request.getLocation();
			Point moveDelta = new Point(0, request.getMoveDelta().y);
			Point oldLocation = ApexSequenceUtil.getAbsoluteEdgeExtremity(connectionPart, true);
			ApexMoveInteractionFragmentsCommand amifCommand = new ApexMoveInteractionFragmentsCommand(
					editingDomain, viewer, container, oldLocation, moveDelta, MARGIN);

			result = new ICommandProxy(amifCommand);

			return result;
		}

		return UnexecutableCommand.INSTANCE;
	}

	private Command getReorderingCommand(ChangeBoundsRequest request) {
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

		if (nearLocation != null) {
			Point moveDelta = new Point();
			TransactionalEditingDomain editingDomain = host.getEditingDomain();
			ViewDescriptor descriptor = (ViewDescriptor) host.getAdapter(ViewDescriptor.class);
			EditPartViewer viewer = host.getViewer();
		}

		return null;
	}

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
			ShapeNodeEditPart srcLifelinePart = ApexSequenceUtil.getParentLifelinePart(srcPart);
			EditPart tgtPart = connectionPart.getTarget();
			ShapeNodeEditPart tgtLifelinePart = ApexSequenceUtil.getParentLifelinePart(tgtPart);

			CompoundCommand compoudCmd = new CompoundCommand("Move Message");

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
						
						if (prevSourcePart instanceof IGraphicalEditPart ) {
							EObject eObj = ((IGraphicalEditPart) prevSourcePart).resolveSemanticElement();
							
							if (eObj instanceof ExecutionSpecification && prevSourcePart instanceof ShapeNodeEditPart) {
								int tMinY = ApexSequenceUtil.apexGetAbsolutePosition((IGraphicalEditPart)prevTargetPart, SWT.BOTTOM) + MARGIN;
								if (realMinY < tMinY) {
									realMinY = tMinY;
									realPrevPart = (IGraphicalEditPart)prevTargetPart;
								}
							}
						}
						
						if (prevSourcePart instanceof IGraphicalEditPart ) {
							EObject eObj = ((IGraphicalEditPart) prevSourcePart).resolveSemanticElement();
							
							if (eObj instanceof ExecutionSpecification && prevTargetPart instanceof ShapeNodeEditPart) {
								int ty = ApexSequenceUtil.apexGetAbsolutePosition((IGraphicalEditPart)prevTargetPart, SWT.BOTTOM) + MARGIN;
								if (realMinY < ty) {
									realMinY = ty;
									realPrevPart = (IGraphicalEditPart)prevTargetPart;
								}
							}
						}
						
					}
				}

				if (prevParts.size() == 0) {
					IFigure dotLine = ((IApexLifelineEditPart)srcLifelinePart).getPrimaryShape().getFigureLifelineDotLineFigure();
					Rectangle dotLineBounds = dotLine.getBounds().getCopy();
					dotLine.translateToAbsolute(dotLineBounds);
					minY = dotLineBounds.y() + MARGIN;
					realMinY = minY;
				}

				EObject eObj = realPrevPart.resolveSemanticElement();
				if (flexiblePrev && eObj instanceof ExecutionSpecification && realPrevPart instanceof ShapeNodeEditPart) {
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
					if ( tgtPart instanceof IGraphicalEditPart ) {
						EObject eTgtObj = ((IGraphicalEditPart) tgtPart).resolveSemanticElement();
						
						if (eTgtObj instanceof ExecutionSpecification && tgtPart instanceof ShapeNodeEditPart) {
							List sourceConnections = ((IGraphicalEditPart)tgtPart).getSourceConnections();
							if (sourceConnections == null || sourceConnections.size() == 0) {
								int minimumHeight = ((IGraphicalEditPart)tgtPart).getFigure().getMinimumSize().height();
								int bottom = ApexSequenceUtil.apexGetAbsolutePosition((IGraphicalEditPart)tgtPart, SWT.BOTTOM);
								maxY = bottom - minimumHeight;
							}
						}
						y = Math.min(maxY, Math.max(minY, y));
						moveDeltaY = y - oldLocation.y();
					}					

					// source : AbstractExecutionSpecificationEditPart
					if ( srcPart instanceof IGraphicalEditPart ) {
						EObject eSrcObj = ((IGraphicalEditPart) srcPart).resolveSemanticElement();
						
						if (eSrcObj instanceof ExecutionSpecification && srcPart instanceof ShapeNodeEditPart) {
							IGraphicalEditPart srcExecSpecEP = (IGraphicalEditPart)srcPart;
							Rectangle oldBounds = ApexSequenceUtil.apexGetAbsoluteRectangle(srcExecSpecEP);
							Rectangle newBounds = oldBounds.getCopy();
							if (newBounds.bottom() < y + PADDING) {
								newBounds.height = y + PADDING - newBounds.y;
							}

							compoudCmd.add( createChangeBoundsCommand(srcExecSpecEP, oldBounds, newBounds, true) );
							compoudCmd.add( ApexOccurrenceSpecificationMoveHelper.getMoveMessageOccurrenceSpecificationsCommand(
									(OccurrenceSpecification)send, y, newBounds, srcPart, srcLifelinePart, empty) );
						}
						else if (srcPart.equals(srcLifelinePart)) { // source : LifelineEditPart
							IFigure figure = ((IApexLifelineEditPart)srcLifelinePart).getPrimaryShape().getFigureLifelineDotLineFigure();
							Rectangle oldBounds = figure.getBounds().getCopy();
							figure.translateToAbsolute(oldBounds);
							Rectangle newBounds = oldBounds.getCopy();
							if (newBounds.bottom() < y + MARGIN) {
								newBounds.height = y + MARGIN - oldBounds.y;
							}

							compoudCmd.add( createChangeBoundsCommand(srcLifelinePart, oldBounds, newBounds, true) );
							compoudCmd.add( ApexOccurrenceSpecificationMoveHelper.getMoveMessageOccurrenceSpecificationsCommand(
									(OccurrenceSpecification)send, y, newBounds, srcPart, srcLifelinePart, empty) );
						}	
					}					

					// target : AbstractExecutionSpecificationEditPart
					if ( tgtPart instanceof IGraphicalEditPart ) {
						EObject eTgtObj = ((IGraphicalEditPart) tgtPart).resolveSemanticElement();
					
						if (eTgtObj instanceof ExecutionSpecification && tgtPart instanceof ShapeNodeEditPart) {
							IGraphicalEditPart tgtExecSpecEP = (IGraphicalEditPart)tgtPart;
							Rectangle oldBounds = ApexSequenceUtil.apexGetAbsoluteRectangle(tgtExecSpecEP);
							Rectangle newBounds = oldBounds.getCopy();
							newBounds.y = y;
							newBounds.height -= moveDeltaY; 

							compoudCmd.add( createChangeBoundsCommand(tgtExecSpecEP, oldBounds, newBounds, true) );
						}
					}					
				}
				else {
					if (moveDeltaY < 0) {
						y = Math.min(maxY, Math.max(minY, y));
						moveDeltaY = y - oldLocation.y();
					}

					// flexiblePrev인 경우, 상당 ExecutionSpecification의 크기 줄임
					EObject eRealPrevObj = realPrevPart.resolveSemanticElement();
					
					if (flexiblePrev && (eRealPrevObj instanceof ExecutionSpecification && realPrevPart instanceof ShapeNodeEditPart) && realMinY > y) {
						Rectangle oldBounds = ApexSequenceUtil.apexGetAbsoluteRectangle(realPrevPart);
						Rectangle newBounds = oldBounds.getCopy();
						newBounds.height += (y - realMinY); 

						compoudCmd.add( createChangeBoundsCommand(realPrevPart, oldBounds, newBounds, true) );
					}

					Command sendMessageMoveCmd = null;
					if ( srcPart instanceof IGraphicalEditPart ) {
						EObject eSrcPart = ((IGraphicalEditPart) srcPart).resolveSemanticElement();
						
						if (eSrcPart instanceof ExecutionSpecification && srcPart instanceof ShapeNodeEditPart) {
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
									Point location = ApexLifelineXYLayoutEditPolicy.findLocationOfMessageOccurrence((GraphicalEditPart) srcExecSpecEP, (MessageOccurrenceSpecification) sendEvent);
//									Point location = ApexSequenceUtil.apexGetAbsoluteRectangle(srcConnPart).getLocation();
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
								compoudCmd.add( createChangeBoundsCommand(srcExecSpecEP, oldBounds, newBounds, true) );
							}

							if (moveDeltaY > 0) {
								newBounds.height = oldBounds.height + moveDeltaY;
							}
							sendMessageMoveCmd = ApexOccurrenceSpecificationMoveHelper.getMoveMessageOccurrenceSpecificationsCommand(
									(OccurrenceSpecification)send, y, newBounds, srcPart, srcLifelinePart, empty);
						}
						else if (srcPart.equals(srcLifelinePart)) { // source : LifelineEditPart
							IFigure figure = ((IApexLifelineEditPart)srcLifelinePart).getPrimaryShape().getFigureLifelineDotLineFigure();
							Rectangle oldBounds = figure.getBounds().getCopy();
							figure.translateToAbsolute(oldBounds);
							Rectangle newBounds = oldBounds.getCopy();
							if (newBounds.bottom() < y + MARGIN) {
								newBounds.height = y + MARGIN - oldBounds.y;
							}

							compoudCmd.add( createChangeBoundsCommand(srcLifelinePart, oldBounds, newBounds, true) );
							compoudCmd.add( ApexOccurrenceSpecificationMoveHelper.getMoveMessageOccurrenceSpecificationsCommand(
									(OccurrenceSpecification)send, y, newBounds, srcPart, srcLifelinePart, empty) );
						}
					}
					

					List<IGraphicalEditPart> linkedParts = ApexSequenceUtil.apexGetLinkedEditPartList(connectionPart, false, true, false);
					for (IGraphicalEditPart linkedPart : linkedParts) {
						Rectangle oldBounds = ApexSequenceUtil.apexGetAbsoluteRectangle(linkedPart);
						Rectangle newBounds = oldBounds.getCopy();
						newBounds.y += moveDeltaY;

						compoudCmd.add( createChangeBoundsCommand(linkedPart, oldBounds, newBounds, false) );
					}

					if (moveDeltaY > 0) {
						linkedParts = ApexSequenceUtil.apexGetLinkedEditPartList(connectionPart, true, true, false);
						nextParts.removeAll(linkedParts);
						if (nextParts.size() > 0) {
							IGraphicalEditPart nextSiblingEditPart = nextParts.get(0);
							if (nextSiblingEditPart instanceof ConnectionNodeEditPart) {
								Command nextCmd = apexGetMoveConnectionCommand(request, (ConnectionNodeEditPart) nextSiblingEditPart, moveAlone);
								compoudCmd.add(nextCmd);
							}
							else {
								Command nextCmd = nextSiblingEditPart.getCommand(request);
								compoudCmd.add(nextCmd);
//								apexGetResizeOrMoveBelowItemsCommand(request, nextSiblingEditPart);
							}
						}
					}

					compoudCmd.add(sendMessageMoveCmd);
				}

				return compoudCmd.size() > 0 ? compoudCmd : null;
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
		command.add(ApexInteractionCompartmentXYLayoutEditPolicy.getCombinedFragmentResizeChildrenCommand(request, (GraphicalEditPart)gep));
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
		request.getExtendedData().put(ApexSequenceRequestConstants.DO_NOT_MOVE_EDIT_PARTS, true);
		if (oldBounds.y == newBounds.y)
			request.setResizeDirection(PositionConstants.SOUTH);
		else if (oldBounds.bottom() == newBounds.bottom())
			request.setResizeDirection(PositionConstants.NORTH);
		else
			request.setResizeDirection(PositionConstants.NORTH_SOUTH);
		return request;
	}

	/**
	 * don't show feedback if the drag is forbidden (message not horizontal).
	 */
	@Override
	public void showSourceFeedback(Request request) {
		if (request instanceof ChangeBoundsRequest) {
			if (isConstrainedMove((ChangeBoundsRequest)request)) {
				showConstrainedMoveFeedback((ChangeBoundsRequest)request, false);
			} else {
				showMoveConnectionFeedback((ChangeBoundsRequest) request);
			}
		}
		super.showSourceFeedback(request);
	}

	protected void showMoveConnectionFeedback(ChangeBoundsRequest request) {
		showReorderingFeedback(request);

		ConnectionNodeEditPart host = (ConnectionNodeEditPart)getHost();
		Connection connection = host.getConnectionFigure();

		Point moveDelta = request.getMoveDelta();
		PointList pl = connection.getPoints().getCopy();
		for (int i = 0; i < pl.size(); i++) {
			Point p = pl.getPoint(i);
			p.y += moveDelta.y;
			pl.setPoint(p, i);
		}

		PolylineConnection feedbackConnection = getDragSourceFeedbackFigure();
		feedbackConnection.setPoints(pl);
	}

	private void showConstrainedMoveFeedback(ChangeBoundsRequest request, boolean isFlexible) {
		ConnectionNodeEditPart host = (ConnectionNodeEditPart)getHost();
		Connection connection = host.getConnectionFigure();

		Point location = request.getLocation().getCopy();
		connection.translateToRelative(location);

		PointList pl = connection.getPoints().getCopy();
		Point oldLocation = pl.getFirstPoint().getCopy();
		PolylineConnection feedbackConnection = getDragSourceFeedbackFigure();

		int dy = location.y - oldLocation.y;

		int minY = ApexSequenceUtil.getMovableTop(host, isFlexible, MARGIN);
		int maxY = ApexSequenceUtil.getMovableBottom(host, MARGIN);
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

	private void showReorderingFeedback(ChangeBoundsRequest request) {
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
				viewPortXLocation = bounds.x;
				viewPortXExtent = bounds.x + bounds.width;
			}
			PointList pl = new PointList();
			pl.addPoint(viewPortXLocation, nearLocation + NEAR_LINE_MARGIN);
			pl.addPoint(viewPortXExtent, nearLocation + NEAR_LINE_MARGIN);

			guideFeedback.setPoints(pl);
			addFeedback(guideFeedback);
		}
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

	protected PolylineConnection createDragSourceFeedbackConnection() {
		PolylineConnection connection = new PolylineConnection();
		connection.setLineWidth(1);
		connection.setLineStyle(Graphics.LINE_DASHDOT);
		connection.setForegroundColor(((IGraphicalEditPart)getHost()).getFigure().getLocalForegroundColor());
		return connection;
	}

	/**
	 * Returns feedback figure
	 * 
	 * @return
	 */
	protected PolylineConnection getDragSourceFeedbackFigure() {
		if (feedback == null) {
			feedback = createDragSourceFeedbackConnection();
			addFeedback(feedback);
		}
		return feedback;
	}

	protected PolylineConnection createGuideLineFeedbackFigure() {
		if (guideFeedback == null) {
			guideFeedback = new PolylineConnection();
			guideFeedback.setLineWidth(1);
			guideFeedback.setLineStyle(Graphics.LINE_DASH);
			guideFeedback.setForegroundColor(ApexSequenceDiagramConstants.REORDERING_LINE_COLOR);
		}
		return guideFeedback;
	}

	private boolean isReordering(ChangeBoundsRequest request) {
//		return request.isCenteredResize();
		return !request.isSnapToEnabled();
	}

	private boolean isConstrainedMove(ChangeBoundsRequest request) {
		return request.isConstrainedMove();
	}
}