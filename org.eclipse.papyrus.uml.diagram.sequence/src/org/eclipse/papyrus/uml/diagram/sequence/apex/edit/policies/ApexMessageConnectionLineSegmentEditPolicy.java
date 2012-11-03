package org.eclipse.papyrus.uml.diagram.sequence.apex.edit.policies;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.commands.SetBoundsCommand;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.papyrus.uml.diagram.sequence.apex.interfaces.IApexLifelineEditPart;
import org.eclipse.papyrus.uml.diagram.sequence.apex.util.ApexSequenceDiagramConstants;
import org.eclipse.papyrus.uml.diagram.sequence.apex.util.ApexSequenceUtil;
import org.eclipse.papyrus.uml.diagram.sequence.edit.parts.InteractionOperandEditPart;
import org.eclipse.papyrus.uml.diagram.sequence.edit.parts.LifelineEditPart;
import org.eclipse.papyrus.uml.diagram.sequence.edit.policies.InteractionCompartmentXYLayoutEditPolicy;
import org.eclipse.papyrus.uml.diagram.sequence.util.OccurrenceSpecificationMoveHelper;
import org.eclipse.papyrus.uml.diagram.sequence.util.OperandBoundsComputeHelper;
import org.eclipse.papyrus.uml.diagram.sequence.util.SequenceRequestConstant;
import org.eclipse.papyrus.uml.diagram.sequence.util.SequenceUtil;
import org.eclipse.swt.SWT;
import org.eclipse.uml2.uml.ExecutionSpecification;
import org.eclipse.uml2.uml.Message;
import org.eclipse.uml2.uml.MessageEnd;
import org.eclipse.uml2.uml.MessageOccurrenceSpecification;
import org.eclipse.uml2.uml.OccurrenceSpecification;

public class ApexMessageConnectionLineSegmentEditPolicy {

	/**
	 * 이동하려는 상단에 ExecutionSpecification이 있는 경우 
	 */
	private static boolean flexiblePrev = false;
	
	private static final int MARGIN = ApexSequenceDiagramConstants.VERTICAL_MARGIN;
	
	private static final int PADDING = ApexSequenceDiagramConstants.EXECUTION_PADDING;

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

			CompoundCommand compoundCmd = new CompoundCommand("Move Message");

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
						
						if (prevTargetPart instanceof IGraphicalEditPart ) {
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

							compoundCmd.add( apexCreateChangeBoundsCommand(srcExecSpecEP, oldBounds, newBounds, true) );
							compoundCmd.add( OccurrenceSpecificationMoveHelper.getMoveMessageOccurrenceSpecificationsCommand(
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

							compoundCmd.add( apexCreateChangeBoundsCommand(srcLifelinePart, oldBounds, newBounds, true) );
							compoundCmd.add( OccurrenceSpecificationMoveHelper.getMoveMessageOccurrenceSpecificationsCommand(
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

							compoundCmd.add( apexCreateChangeBoundsCommand(tgtExecSpecEP, oldBounds, newBounds, true) );
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

						compoundCmd.add( apexCreateChangeBoundsCommand(realPrevPart, oldBounds, newBounds, true) );
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
									Point location = SequenceUtil.findLocationOfMessageOccurrence((GraphicalEditPart) srcExecSpecEP, (MessageOccurrenceSpecification) sendEvent);
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
								compoundCmd.add( apexCreateChangeBoundsCommand(srcExecSpecEP, oldBounds, newBounds, true) );
							}

							if (moveDeltaY > 0) {
								newBounds.height = oldBounds.height + moveDeltaY;
							}
							sendMessageMoveCmd = OccurrenceSpecificationMoveHelper.getMoveMessageOccurrenceSpecificationsCommand(
									(OccurrenceSpecification)send, y, newBounds, srcPart, srcLifelinePart, empty);
						}
					}
					else if (srcPart.equals(srcLifelinePart)) { // source : LifelineEditPart
						IFigure figure = ((IApexLifelineEditPart)srcLifelinePart).getPrimaryShape().getFigureLifelineDotLineFigure();
						Rectangle oldBounds = figure.getBounds().getCopy();
						figure.translateToAbsolute(oldBounds);
						Rectangle newBounds = oldBounds.getCopy();
						if (newBounds.bottom() < y + MARGIN) {
							newBounds.height = y + MARGIN - oldBounds.y;
						}

						compoundCmd.add( apexCreateChangeBoundsCommand(srcLifelinePart, oldBounds, newBounds, true) );
						compoundCmd.add( OccurrenceSpecificationMoveHelper.getMoveMessageOccurrenceSpecificationsCommand(
								(OccurrenceSpecification)send, y, newBounds, srcPart, srcLifelinePart, empty) );
					}

					List<IGraphicalEditPart> linkedParts = ApexSequenceUtil.apexGetLinkedEditPartList(connectionPart, false, true, false);
					for (IGraphicalEditPart linkedPart : linkedParts) {
						Rectangle oldBounds = ApexSequenceUtil.apexGetAbsoluteRectangle(linkedPart);
						Rectangle newBounds = oldBounds.getCopy();
						newBounds.y += moveDeltaY;

						compoundCmd.add( apexCreateChangeBoundsCommand(linkedPart, oldBounds, newBounds, false) );
					}

					if (moveDeltaY > 0) {
						linkedParts = ApexSequenceUtil.apexGetLinkedEditPartList(connectionPart, true, true, false);

						// containing Operand Resize 처리 - omw
						ShapeNodeEditPart ioep = ApexSequenceUtil.apexGetEnclosingInteractionOperandEditpart(connectionPart);
						if ( ioep != null ) {

							ChangeBoundsRequest cbRequest = new ChangeBoundsRequest();
							cbRequest.setSizeDelta(new Dimension(0, moveDeltaY));
							cbRequest.setResizeDirection(PositionConstants.SOUTH);
							compoundCmd.add(OperandBoundsComputeHelper.createIOEPResizeCommand(cbRequest, (InteractionOperandEditPart)ioep));	
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
	 * @param oldRect
	 * @param newRect
	 * @param isPreserveAnchorsPosition true이면 gep만 단독으로 변경하는 Command 생성, false이면 Request에 의해 변경하는 Command 그룹 생성
	 * @return
	 */
	private static Command apexCreateChangeBoundsCommand(IGraphicalEditPart gep, Rectangle oldRect, Rectangle newRect, boolean isPreserveAnchorsPosition) {
		Command command = null;

		/* apex improved start */
		if (oldRect.getLocation().equals(newRect.getLocation())
				&& oldRect.height == newRect.height) {
				return null;
		}
		/* apex improved start */
		/* apex replaced
		if (oldRect.x == newRect.x && oldRect.y == newRect.y 
			&& oldRect.width == newRect.width && oldRect.height == newRect.height) {
			return null;
		}
		*/		

		if (!isPreserveAnchorsPosition) {
			TransactionalEditingDomain editingDomain = gep.getEditingDomain();
			Rectangle parentRect = gep.getFigure().getParent().getBounds().getCopy();
			// newBounds를 parent(Lifeline)을 기준으로 한 상대좌표로 변경
			gep.getFigure().translateToRelative(newRect);
			newRect.translate(-parentRect.x, -parentRect.y);
			// end
			command = new ICommandProxy( new SetBoundsCommand(editingDomain, "Apex in ApexMessageLineSegmentEditPolicy", gep, newRect) );
		} else {
			ChangeBoundsRequest request = apexCreateChangeBoundsRequest(oldRect, newRect);
			command = gep.getCommand(request);
		}

		return command;
	}

	/**
	 * bounds를 변경하는 ChangeBoundsRequest 생성
	 * @param oldRect
	 * @param newRect
	 * @return
	 */
	private static ChangeBoundsRequest apexCreateChangeBoundsRequest(Rectangle oldRect, Rectangle newRect) {
		ChangeBoundsRequest request = new ChangeBoundsRequest(RequestConstants.REQ_RESIZE);
		request.setMoveDelta(new Point(newRect.x - oldRect.x, newRect.y - oldRect.y));
		request.setSizeDelta(new Dimension(newRect.width - oldRect.width, newRect.height - oldRect.height));
		// execution 이동에 의해 editpart들이 겹치는 현상 방지
		request.getExtendedData().put(SequenceRequestConstant.DO_NOT_MOVE_EDIT_PARTS, true);
		if (oldRect.y == newRect.y)
			request.setResizeDirection(PositionConstants.SOUTH);
		else if (oldRect.bottom() == newRect.bottom())
			request.setResizeDirection(PositionConstants.NORTH);
		else
			request.setResizeDirection(PositionConstants.NORTH_SOUTH);
		return request;
	}

}
