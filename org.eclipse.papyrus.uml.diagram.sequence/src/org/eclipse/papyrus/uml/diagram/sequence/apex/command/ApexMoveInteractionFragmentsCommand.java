package org.eclipse.papyrus.uml.diagram.sequence.apex.command;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.commands.SetBoundsCommand;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;
import org.eclipse.papyrus.uml.diagram.sequence.apex.interfaces.IApexLifelineEditPart;
import org.eclipse.papyrus.uml.diagram.sequence.apex.util.ApexOccurrenceSpecificationMoveHelper;
import org.eclipse.papyrus.uml.diagram.sequence.apex.util.ApexSequenceRequestConstants;
import org.eclipse.papyrus.uml.diagram.sequence.apex.util.ApexSequenceUtil;
import org.eclipse.papyrus.uml.diagram.sequence.edit.parts.InteractionOperandEditPart;
import org.eclipse.papyrus.uml.diagram.sequence.edit.parts.LifelineEditPart;
import org.eclipse.papyrus.uml.diagram.sequence.edit.policies.InteractionCompartmentXYLayoutEditPolicy;
import org.eclipse.papyrus.uml.diagram.sequence.util.OperandBoundsComputeHelper;
import org.eclipse.papyrus.uml.diagram.sequence.util.SequenceRequestConstant;
import org.eclipse.papyrus.uml.diagram.sequence.util.SequenceUtil;
import org.eclipse.uml2.uml.CombinedFragment;
import org.eclipse.uml2.uml.ExecutionSpecification;
import org.eclipse.uml2.uml.Interaction;
import org.eclipse.uml2.uml.InteractionFragment;
import org.eclipse.uml2.uml.InteractionOperand;
import org.eclipse.uml2.uml.Lifeline;
import org.eclipse.uml2.uml.Message;
import org.eclipse.uml2.uml.MessageOccurrenceSpecification;

/**
 * @author Jiho
 *
 */
@SuppressWarnings("unchecked")
public class ApexMoveInteractionFragmentsCommand extends
		AbstractTransactionalCommand {

	protected final static String COMMAND_LABEL = "Move InteractionFragments";
	
	protected EditPartViewer viewer;
	
	/**
	 * container (Interaction, CombinedFragment)
	 */
	private InteractionFragment fragment;
	
	/**
	 * 이동 전 기준이 되는 범위
	 */
	private Rectangle extent;
	
	/**
	 * moveDelta, sizeDelta
	 */
	private Point moveDelta;
	
	/**
	 * 상하 여백
	 */
	private int margin;
	
	private boolean dontMoveOthers;

	/**
	 * 이동이 무시되는 fragment 리스트
	 */
	protected Collection<EObject> notToMoveEObject;

	private CompoundCommand command;
	
	public ApexMoveInteractionFragmentsCommand(
			TransactionalEditingDomain domain, EditPartViewer viewer, InteractionFragment fragment, Point location, Point moveDelta, int margin) {
		this(domain, viewer, fragment, new Rectangle(location.x, location.y, 0, 0), moveDelta, margin, false);
	}
		
	public ApexMoveInteractionFragmentsCommand(
			TransactionalEditingDomain domain, EditPartViewer viewer, InteractionFragment fragment, Point location, Point moveDelta, int margin, boolean dontMoveOthers) {
		this(domain, viewer, fragment, new Rectangle(location.x, location.y, 0, 0), moveDelta, margin, dontMoveOthers);
	}
	
	public ApexMoveInteractionFragmentsCommand(
			TransactionalEditingDomain domain, EditPartViewer viewer, InteractionFragment fragment, Rectangle extent, Point moveDelta, int margin) {
		this(domain, viewer, fragment, extent, moveDelta, margin, false);
	}
	
	/**
	 * 
	 * @param domain	editing domain
	 * @param viewer	EditPartViewer
	 * @param fragment	container (Interaction or InteractionOperand)
	 * @param extent	직접적인 이동 영역, Message의 경우 y만 해당되고, CF의 경우 y~bottom영역이 해당 됨
	 * @param moveDelta	move delta
	 * @param margin
	 * @param dontMoveOthers	true 이면 extent 범위만 이동하고, false 이면 extent 하위가 모두 이동
	 */
	public ApexMoveInteractionFragmentsCommand(
			TransactionalEditingDomain domain, EditPartViewer viewer, InteractionFragment fragment, Rectangle extent, Point moveDelta, int margin, boolean dontMoveOthers) {
		super(domain, COMMAND_LABEL, null);
		this.viewer = viewer;
		this.fragment = fragment;
		this.extent = extent;
		this.moveDelta = moveDelta;
		this.margin = margin;
		this.dontMoveOthers = dontMoveOthers;
		
		command = new CompoundCommand();
		notToMoveEObject = new HashSet<EObject>();
	}

	@Override
	protected CommandResult doExecuteWithResult(IProgressMonitor monitor,
			IAdaptable info) throws ExecutionException {
		
		Map<IGraphicalEditPart, Collection<MessageOccurrenceSpecification>> needToMoveMessages = new HashMap<IGraphicalEditPart, Collection<MessageOccurrenceSpecification>>();
		Map<IGraphicalEditPart, Integer> needToMoveBottoms = new HashMap<IGraphicalEditPart, Integer>();
		
		Collection<InteractionFragment> fragments = getInteractionFragments(fragment);
		Point realMoveDelta = getRealMoveDelta(getMoveDelta(), fragments);
		
		// 이동하려는 Message의 하위 Fragments 중 가장 상단 CombinedFragment를 찾고
		// 그 CF를 포함한 하위는 CF의 getCommand()를 통해 위치를 이동
		IGraphicalEditPart belowCombinedFragmentEditPart = null;
		int belowY = Integer.MAX_VALUE;
		for (InteractionFragment ift : fragments) {
			if (ift instanceof CombinedFragment) {
				IGraphicalEditPart editPart = getEditPart(ift);
				Rectangle bounds = ApexSequenceUtil.getAbsoluteBounds(editPart);
				
				if (dontMoveOthers && extent.bottom() < bounds.y) {
					continue;
				}
				
				if (extent.y <= bounds.y) {	// CF가 이동범위에 모두 포함된 경우, ChangeBoundsRequest 이용
					if (belowY > bounds.y) {
						belowY = bounds.y;
						belowCombinedFragmentEditPart = editPart;
					}
				} else if (/*extent.y > bounds.y &&*/ extent.y < bounds.bottom()) {
					// CF가 걸쳐있는 경우는 발생하지 않음
				}
			}
		}
		// CF내부의 Message 일괄 이동을 위해 ChangeBoundsRequest를 이용한 Command 생성
		if (belowCombinedFragmentEditPart != null) {
			ChangeBoundsRequest request = new ChangeBoundsRequest(RequestConstants.REQ_MOVE);
			request.setMoveDelta(realMoveDelta);
			command.add(belowCombinedFragmentEditPart.getCommand(request));
//			Rectangle newBounds = bounds.getCopy();
//			editPart.getFigure().translateToRelative(newBounds);
//			newBounds.translate(realMoveDelta);
//			SetBoundsCommand sbCommand = new SetBoundsCommand(getEditingDomain(), "Set Bounds", editPart, newBounds);
//			command.add(new ICommandProxy(sbCommand));
		}
		
		// Message~CombinedFragment 사이의 ExecutionSpecification들에 대한 위치 변경 Command 생성
		for (InteractionFragment ift : fragments) {
			if (notToMoveEObject.contains(ift))
				continue;
			
			if (ift instanceof ExecutionSpecification) {
				IGraphicalEditPart editPart = getEditPart(ift);
				Rectangle bounds = ApexSequenceUtil.getAbsoluteBounds(editPart);
				
				// extent 하단의 이동을 방지하였을 때 (dontMoveOthers == false)
				// 하단에 위치한 EditPart는 이동하지 않음
				if (dontMoveOthers && extent.bottom() < bounds.y) {
					continue;
				}
				
				if (belowY < bounds.y) {
					continue;
				}
				
				if (extent.y <= bounds.y) {	// Activation이 이동범위에 모두 포함된 경우, bounds를 이동
					if (!dontMoveOthers) {
						Rectangle newBounds = bounds.getCopy();
						Rectangle parentBounds = editPart.getFigure().getParent().getBounds().getCopy();
						editPart.getFigure().translateToRelative(newBounds);
						newBounds.translate(-parentBounds.x, -parentBounds.y);
						newBounds.translate(realMoveDelta);
						SetBoundsCommand sbCommand = new SetBoundsCommand(getEditingDomain(), "Set Bounds", editPart, newBounds);
						command.add(new ICommandProxy(sbCommand));
					} else {
						ChangeBoundsRequest request = new ChangeBoundsRequest(RequestConstants.REQ_MOVE);
						request.setMoveDelta(realMoveDelta);
						command.add(editPart.getCommand(request));
					}
				} else if (/*extent.y > bounds.y &&*/ extent.y < bounds.bottom()) {	// Activation의 하단만 이동범위에 걸쳐있을 경우, 하단 길이 변경
					ChangeBoundsRequest request = new ChangeBoundsRequest(RequestConstants.REQ_RESIZE);
					request.getExtendedData().put(ApexSequenceRequestConstants.APEX_PRESERVE_ANCHOR_RELATIVE_BOUNDS, extent);
					request.getExtendedData().put(SequenceRequestConstant.DO_NOT_MOVE_EDIT_PARTS, true);
					request.setResizeDirection(PositionConstants.NORTH);
					request.setSizeDelta(new Dimension(0, realMoveDelta.y));
					command.add(editPart.getCommand(request));
				}
			}
			// Lifeline을 source로 하는 Message들은 anchor를 변경하여 위치를 이동
			else if (ift instanceof MessageOccurrenceSpecification) {
				Message message = ((MessageOccurrenceSpecification)ift).getMessage();
				IGraphicalEditPart editPart = getEditPart(message);
				if (ift.equals(message.getSendEvent()) && editPart instanceof ConnectionNodeEditPart) {
					Point edge = ApexSequenceUtil.getAbsoluteEdgeExtremity((ConnectionNodeEditPart) editPart, true);
					if (edge == null) {
						continue;
					}
					
					if (dontMoveOthers && extent.bottom() < edge.y) {
						continue;
					}
					
					if (extent.y <= edge.y) {
						EditPart source = ((ConnectionNodeEditPart)editPart).getSource();
						
						if (source instanceof LifelineEditPart) {
							LifelineEditPart lifelineEditPart = (LifelineEditPart)source;
							
							Collection<MessageOccurrenceSpecification> occurrenceSpecifications = needToMoveMessages.get(lifelineEditPart);
							if (occurrenceSpecifications == null) {
								occurrenceSpecifications = new HashSet<MessageOccurrenceSpecification>();
							}
							occurrenceSpecifications.add((MessageOccurrenceSpecification) ift);
							needToMoveMessages.put(lifelineEditPart, occurrenceSpecifications);

							Integer bottom = needToMoveBottoms.get(lifelineEditPart);
							if (bottom == null || bottom < edge.y + realMoveDelta.y) {
								needToMoveBottoms.put(lifelineEditPart, edge.y + realMoveDelta.y);
							}
						}
					}
				}
			}
		}
		
		command.add(createPreserveAnchorCommands(needToMoveMessages, needToMoveBottoms, realMoveDelta));
		
		if (fragment instanceof InteractionOperand) {
			InteractionOperandEditPart ioEditPart = (InteractionOperandEditPart)getEditPart(fragment);
			ChangeBoundsRequest cbRequest = new ChangeBoundsRequest(RequestConstants.REQ_RESIZE);
			cbRequest.setResizeDirection(PositionConstants.SOUTH);
			cbRequest.setSizeDelta(new Dimension(0, realMoveDelta.y));
			command.add(OperandBoundsComputeHelper.createIOEPResizeCommand(cbRequest, ioEditPart));
		}
		
		if (command.isEmpty() || command.canExecute()) {
			command.execute();
			return CommandResult.newOKCommandResult();
		}
		System.out.println("cannot execute!!!");
		return CommandResult.newCancelledCommandResult();
	}
	
	/**
	 * Anchor 
	 * @param needToMoveMessages	source인 LifelineEditPart에 연결된 MOS들 중 preserve anchor가 필요한 MOS들
	 * @param needToMoveBottoms		source인 LifelineEditPart가 bottom으로 확장이 필요하다면 최종 bottom값
	 * @param realMoveDelta
	 * @return
	 */
	private Command createPreserveAnchorCommands(Map<IGraphicalEditPart, Collection<MessageOccurrenceSpecification>> needToMoveMessages,
			Map<IGraphicalEditPart, Integer> needToMoveBottoms, Point realMoveDelta) {
		CompoundCommand compCmd = new CompoundCommand();
		
		for (Entry<IGraphicalEditPart, Collection<MessageOccurrenceSpecification>> entry : needToMoveMessages.entrySet()) {
			IGraphicalEditPart editPart = entry.getKey();
			Integer bottom = needToMoveBottoms.get(editPart);
			Collection<MessageOccurrenceSpecification> occurrenceSpecifications = entry.getValue();
			
			if (bottom == null) {
				continue;
			}

			Rectangle oldBounds = ApexSequenceUtil.getAbsoluteBounds(editPart);
			
			EObject eObj = editPart.resolveSemanticElement();
			if ( eObj instanceof Lifeline ) {
				IFigure figure = ((IApexLifelineEditPart)editPart).getNodeFigure();
				oldBounds = figure.getBounds().getCopy();
				figure.translateToAbsolute(oldBounds);
			}
			/* apex replaced
			if (editPart instanceof LifelineEditPart) {
				LifelineEditPart lifelineEP = (LifelineEditPart)editPart;
				IFigure figure = lifelineEP.getNodeFigure();
				oldBounds = figure.getBounds().getCopy();
				figure.translateToAbsolute(oldBounds);
			}
			*/
			
			Rectangle newBounds = oldBounds.getCopy();
			if (newBounds.bottom() < bottom) {
				newBounds.height = bottom - newBounds.y;
			}
			
			if (!newBounds.equals(oldBounds)) {
				ChangeBoundsRequest request = new ChangeBoundsRequest(RequestConstants.REQ_RESIZE);
				request.getExtendedData().put(SequenceRequestConstant.DO_NOT_MOVE_EDIT_PARTS, true);
				request.setResizeDirection(PositionConstants.SOUTH);
				request.setSizeDelta(new Dimension(0, newBounds.bottom() - oldBounds.bottom()));
				compCmd.add(editPart.getCommand(request));
			}
			
			for (MessageOccurrenceSpecification occurrenceSpecification : occurrenceSpecifications) {
				Message message = occurrenceSpecification.getMessage();
				EditPart part = getEditPart(message);
				if (occurrenceSpecification.equals(message.getSendEvent())
						&& part instanceof ConnectionNodeEditPart) {
					ConnectionNodeEditPart messageEP = (ConnectionNodeEditPart)part;
					Point edge = ApexSequenceUtil.getAbsoluteEdgeExtremity(messageEP, true);
					
					List<EditPart> empty = Collections.emptyList();
					LifelineEditPart lifelineEP = SequenceUtil.getParentLifelinePart(editPart);
					if (lifelineEP != null) {
						compCmd.add(ApexOccurrenceSpecificationMoveHelper.getMoveMessageOccurrenceSpecificationsCommand(
								occurrenceSpecification, edge.y + realMoveDelta.y, newBounds, editPart, lifelineEP, empty));
					}
				}
			}
		}
		return !compCmd.isEmpty() ? compCmd.unwrap() : null;
	}
	
	public Rectangle getExtent() {
		return extent;
	}
	
	public Rectangle getNewExtent() {
		return extent.getCopy().translate(getMoveDelta());
	}
	
	public Point getMoveDelta() {
		return moveDelta;
	}
	
	public void setMoveDelta(Point moveDelta) {
		this.moveDelta = moveDelta;
	}
	
	public Point getRealMoveDelta(Point moveDelta, Collection<InteractionFragment> fragments) {
		Point newDelta = moveDelta.getCopy();
		
		for (InteractionFragment ift : fragments) {
			if (ift instanceof ExecutionSpecification || ift instanceof CombinedFragment) {
				IGraphicalEditPart editPart = getEditPart(ift);
				Rectangle bounds = ApexSequenceUtil.getAbsoluteBounds(editPart);
				
				// 위로 이동하는 경우
				if (!dontMoveOthers) {
					if (extent.y > bounds.bottom() &&
							getNewExtent().y < bounds.bottom() + margin) {
						newDelta.y = bounds.bottom() + margin - extent.y;
					}
				}
			}
		}
		return newDelta;
	}
	
	private Collection<InteractionFragment> getInteractionFragments(InteractionFragment fragment) {
		Set<InteractionFragment> allFragments = new HashSet<InteractionFragment>();
		if (fragment instanceof Interaction) {
			allFragments.addAll(((Interaction)fragment).getFragments());
		}
		else if (fragment instanceof InteractionOperand) {
			allFragments.addAll(((InteractionOperand)fragment).getFragments());
		}
		else {
			if (fragment.getEnclosingOperand() != null) {
				InteractionOperand operand = fragment.getEnclosingOperand();
				allFragments.addAll(operand.getFragments());
			}
			else if (fragment.getEnclosingInteraction() != null) {
				Interaction interaction = fragment.getEnclosingInteraction();
				allFragments.addAll(interaction.getFragments());
			}
		}
		return allFragments;
	}
	
	protected IGraphicalEditPart getEditPart(EObject eObject) {
		return (IGraphicalEditPart)ApexSequenceUtil.getEditPart(eObject, viewer);
	}
	
}