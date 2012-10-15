package org.eclipse.papyrus.uml.diagram.sequence.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.common.core.command.CompositeCommand;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.common.core.command.ICompositeCommand;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.commands.SetBoundsCommand;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.l10n.DiagramUIMessages;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewRequest;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewRequest.ViewDescriptor;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;
import org.eclipse.gmf.runtime.emf.type.core.IHintedType;
import org.eclipse.gmf.runtime.notation.Bounds;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.Shape;
import org.eclipse.papyrus.uml.diagram.sequence.edit.parts.CombinedFragmentCombinedFragmentCompartmentEditPart;
import org.eclipse.papyrus.uml.diagram.sequence.edit.parts.CombinedFragmentEditPart;
import org.eclipse.papyrus.uml.diagram.sequence.edit.parts.InteractionInteractionCompartmentEditPart;
import org.eclipse.papyrus.uml.diagram.sequence.edit.parts.InteractionOperandEditPart;
import org.eclipse.papyrus.uml.diagram.sequence.edit.policies.InteractionCompartmentXYLayoutEditPolicy;
import org.eclipse.papyrus.uml.diagram.sequence.part.UMLVisualIDRegistry;
import org.eclipse.papyrus.uml.diagram.sequence.providers.UMLElementTypes;
import org.eclipse.swt.SWT;
import org.eclipse.uml2.uml.CombinedFragment;
import org.eclipse.uml2.uml.InteractionOperand;

public class OperandBoundsComputeHelper {

	/**
	 * Default height of Interaction Operand
	 */
	public static final int DEFAULT_INTERACTION_OPERAND_HEIGHT = 61;
	/**
	 * Default width of Interaction Operand
	 */
	public static final int DEFAULT_INTERACTION_OPERAND_WIDTH = 210;
	/**
	 * Border width of CombinedFragmentFigure
	 */
	public static final int COMBINED_FRAGMENT_FIGURE_BORDER = 1;
	
	/**
	 * Find first Interaction Operand EditpPart of CombinedFragmentCombinedFragmentCompartmentEditPart
	 * @param compartEP
	 * @return InteractionOperandEditPart
	 */
	public static InteractionOperandEditPart findFirstIOEP(
			CombinedFragmentCombinedFragmentCompartmentEditPart compartEP) {
		InteractionOperandEditPart firstIOEP = null;
		List children = compartEP.getChildren();
		for (int i = 0; i < children.size(); i++) {
			if (children.get(i) instanceof InteractionOperandEditPart) {
				firstIOEP = (InteractionOperandEditPart) children.get(i);
				break;
			}
		}
		return firstIOEP;
	}
	
	/**
	 * Find Last Interaction Operand EditpPart of CombinedFragmentCombinedFragmentCompartmentEditPart
	 * @param compartEP
	 * @return InteractionOperandEditPart
	 */
	public static InteractionOperandEditPart findLastIOEP(
			CombinedFragmentCombinedFragmentCompartmentEditPart compartEP) {
		InteractionOperandEditPart lastIOEP = null;
		List children = compartEP.getChildren();
		for (int i = children.size() - 1; i >= 0; i--) {
			if (children.get(i) instanceof InteractionOperandEditPart) {
				lastIOEP = (InteractionOperandEditPart) children.get(i);
				break;
			}
		}
		return lastIOEP;
	}
	
	/**
	 * Find Previous Interaction Operand EditpPart of current given InteractionOperandEditPart
	 * @param compartEP
	 * @param currentIOEP
	 * @return InteractionOperandEditPart
	 */
	public static InteractionOperandEditPart findPreviousIOEP(
			CombinedFragmentCombinedFragmentCompartmentEditPart compartEP,
			InteractionOperandEditPart currentIOEP) {
		InteractionOperandEditPart previousIOEP = null;
		List children = compartEP.getChildren();
		for (int i = 0; i < children.size() - 1; i++) {
			if (children.get(i) instanceof InteractionOperandEditPart) {
				if (children.get(i) == currentIOEP) {
					break;
				} else {
					previousIOEP = (InteractionOperandEditPart) children.get(i);
				}
			}
		}
		return previousIOEP;
	}
	
	/**
	 * Find Latter Interaction Operand EditpPart of current given InteractionOperandEditPart
	 * @param compartEP
	 * @param currentIOEP
	 * @return InteractionOperandEditPart
	 */
	public static InteractionOperandEditPart findLatterIOEP(
			CombinedFragmentCombinedFragmentCompartmentEditPart compartEP,
			InteractionOperandEditPart currentIOEP) {
		InteractionOperandEditPart latterIOEP = null;
		List children = compartEP.getChildren();
		for (int i = children.size() - 1; i > 0; i--) {
			if (children.get(i) instanceof InteractionOperandEditPart) {
				if (children.get(i) == currentIOEP) {
					break;
				} else {
					latterIOEP = (InteractionOperandEditPart) children.get(i);
				}
			}
		}
		return latterIOEP;
	}
	
	/**
	 * currentIOEP 아래의 sibling IOEP List를 위치순으로 정렬하여 반환
	 * 
	 * @param compartEP
	 * @param currentIOEP
	 * @return List<InteractionOperandEditPart>
	 */
	public static List<InteractionOperandEditPart> apexFindBelowIOEPs(
			CombinedFragmentCombinedFragmentCompartmentEditPart compartEP,
			InteractionOperandEditPart currentIOEP) {
		List<InteractionOperandEditPart> belowIOEPs = new ArrayList<InteractionOperandEditPart>();
		
		int currentAbsBottom = ApexSequenceUtil.apexGetAbsolutePosition(currentIOEP, SWT.BOTTOM);
		
		List ioeps = compartEP.getChildren();
		
		for ( int i = 0 ; i < ioeps.size() ; i++ ) {
			InteractionOperandEditPart ioep = (InteractionOperandEditPart)ioeps.get(i);
			int ioepAbsTop = ApexSequenceUtil.apexGetAbsolutePosition(ioep, SWT.TOP);
			
			if ( ioepAbsTop >= currentAbsBottom ) {
				belowIOEPs.add(ioep);
			}
		}
		
		Collections.sort(belowIOEPs, new Comparator<IGraphicalEditPart>() {
			public int compare(IGraphicalEditPart o1, IGraphicalEditPart o2) {
				Rectangle r1 = ApexSequenceUtil.apexGetAbsoluteRectangle(o1);
				Rectangle r2 = ApexSequenceUtil.apexGetAbsoluteRectangle(o2);
				
				if (r1.y - r2.y == 0)
					return r1.x - r2.x;
				return r1.y - r2.y;
			}
		});
				
		return belowIOEPs;
	}

	/**
	 * Update EditPart bounds using new rect
	 * @param parent
	 * @param rect
	 */
	public static void updateEditPartBounds(GraphicalEditPart editpart,
			final Rectangle rect) {
		if (editpart.getModel() instanceof Node) {
			Node node = (Node) editpart.getModel();
			if (node.getLayoutConstraint() instanceof Bounds) {
				Bounds bounds = (Bounds) node.getLayoutConstraint();
				updateBounds(bounds, rect, editpart.getEditingDomain());
			}
		}
	}
	
	/**
	 * Update EditPart bounds using new rect
	 * @param parent
	 * @param rect
	 */
	public static ICommand createUpdateEditPartBoundsCommand(
			final GraphicalEditPart editpart, final Rectangle rect) {
		ICommand cmd = new AbstractTransactionalCommand(
				editpart.getEditingDomain(),
				"Update Operand Bounds", null) {
			@Override
			protected CommandResult doExecuteWithResult(
					IProgressMonitor monitor, IAdaptable info)
					throws ExecutionException {
				if (editpart.getModel() instanceof Node) {
					Node node = (Node) editpart.getModel();
					if (node.getLayoutConstraint() instanceof Bounds) {
						Bounds bounds = (Bounds) node.getLayoutConstraint();
						fillBounds(bounds, rect);
					}
				}
				return CommandResult.newOKCommandResult();
			}
		};
		return cmd;
	}
	
	/**
	 * Create command for updating Interaction Operand EditpPart bounds after the CombinedFragment to be created. 
	 * @param editPart
	 * @param request
	 * @return ICommand
	 */
	public static ICommand createUpdateCFAndIOBoundsForCFCreationCommand(final EditPart editPart, final CreateViewRequest request) {
		if (!(editPart instanceof GraphicalEditPart)) {
			return null;
		}
		GraphicalEditPart graphicalEditPart= (GraphicalEditPart)editPart;
		ICommand cmd = new AbstractTransactionalCommand(graphicalEditPart.getEditingDomain(),"Create update operand bounds command",null) {
			@Override
			protected CommandResult doExecuteWithResult(IProgressMonitor monitor,
					IAdaptable info) throws ExecutionException {
				OperandBoundsComputeHelper.updateCFAndIOBoundsForCFCreation(
						(GraphicalEditPart) editPart, request);
				return CommandResult.newOKCommandResult();
			}
		};
		return cmd;
	}
	
	/**
	 * Update Interaction Operand EditpPart bounds after the CombinedFragment to be created. 
	 * @param parent
	 * @param request
	 * @param selectionRect
	 */
	private static void updateCFAndIOBoundsForCFCreation(GraphicalEditPart parent, CreateViewRequest request) {
		Object subEditPart = null;
		if (parent instanceof InteractionInteractionCompartmentEditPart) {
			InteractionInteractionCompartmentEditPart interactionInteractionCompartmentEditPart = (InteractionInteractionCompartmentEditPart) parent;
			subEditPart = interactionInteractionCompartmentEditPart
					.getChildren().get(
							interactionInteractionCompartmentEditPart
									.getChildren().size() - 1);
			
		}else if (parent instanceof InteractionOperandEditPart) {
			InteractionOperandEditPart interactionOperandEditPart = (InteractionOperandEditPart) parent;
			subEditPart = interactionOperandEditPart
					.getChildren().get(
							interactionOperandEditPart
									.getChildren().size() - 1);
		}
		if (subEditPart != null
				&& subEditPart instanceof CombinedFragmentEditPart) {
			CombinedFragmentEditPart combinedFragmentEditPart = (CombinedFragmentEditPart) subEditPart;
			// set bounds for CombinedFragmentEditPart
			Rectangle cfEPAbsoluteRect = null;
			int labelContainerHeight = computeCombinedFragementHeaderHeight(combinedFragmentEditPart);
			Shape cfEPShape = (Shape) combinedFragmentEditPart.getModel();
			if (cfEPShape.getLayoutConstraint() instanceof Bounds) {
				if(request.getSize() == null){
					Dimension cfEPDefaultSize = new Dimension();
					cfEPDefaultSize.setWidth(DEFAULT_INTERACTION_OPERAND_WIDTH+COMBINED_FRAGMENT_FIGURE_BORDER*2);
					cfEPDefaultSize.setHeight(DEFAULT_INTERACTION_OPERAND_HEIGHT+labelContainerHeight+COMBINED_FRAGMENT_FIGURE_BORDER*2);
					cfEPAbsoluteRect = new Rectangle(request.getLocation(),cfEPDefaultSize);
				}else{
					cfEPAbsoluteRect = new Rectangle(request.getLocation(),request.getSize());
				}
				Rectangle cfEPRelativeRect = cfEPAbsoluteRect.getCopy();
				combinedFragmentEditPart.getFigure().translateToRelative(cfEPRelativeRect);
				Bounds cfEPBounds = (Bounds) cfEPShape.getLayoutConstraint();
				fillBounds(cfEPBounds,cfEPRelativeRect);
			}
			
			// set bounds for new added Operand
			InteractionOperandEditPart lastOperand = OperandBoundsComputeHelper
					.findLastIOEP((CombinedFragmentCombinedFragmentCompartmentEditPart) combinedFragmentEditPart.getChildBySemanticHint(UMLVisualIDRegistry
							.getType(CombinedFragmentCombinedFragmentCompartmentEditPart.VISUAL_ID)));
			Shape shape = (Shape) lastOperand.getModel();
			if (shape.getLayoutConstraint() instanceof Bounds) {
				Bounds bounds = (Bounds) shape.getLayoutConstraint();
				Rectangle rect = new Rectangle(bounds.getX(), bounds.getY(),
						cfEPAbsoluteRect.width()-COMBINED_FRAGMENT_FIGURE_BORDER*2, cfEPAbsoluteRect.height()
								- labelContainerHeight-COMBINED_FRAGMENT_FIGURE_BORDER*2);
				fillBounds(bounds,rect);
			}
		}
	}

	/**
	 * apex updated - 사용 안 함 - createIOEPResizeCommand()에서 모두 처리
	 * 
	 * Create command for updating Interaction Operand EditpPart bounds after CombinedFragment to be resized. 
	 * @param compoundCmd
	 * @param request
	 * @param combinedFragmentEditPart
	 * @return Command
	 */
	/* apex replaced
	public static Command createUpdateIOBoundsForCFResizeCommand(
			CompoundCommand compoundCmd, final ChangeBoundsRequest request,
			CombinedFragmentEditPart combinedFragmentEditPart) {
		if (combinedFragmentEditPart.getChildren().size() > 0
				&& combinedFragmentEditPart.getChildren().get(0) instanceof CombinedFragmentCombinedFragmentCompartmentEditPart) {
			CombinedFragmentCombinedFragmentCompartmentEditPart compartment = (CombinedFragmentCombinedFragmentCompartmentEditPart) combinedFragmentEditPart
					.getChildren().get(0);
			CombinedFragment cf = (CombinedFragment) ((CombinedFragmentEditPart) combinedFragmentEditPart)
					.resolveSemanticElement();
			InteractionOperandEditPart targetIOEP = null;
			if ((request.getResizeDirection() & PositionConstants.NORTH) != 0) {
				targetIOEP = findFirstIOEP(compartment);
			} else if ((request.getResizeDirection() & PositionConstants.SOUTH) != 0) {
				targetIOEP = findLastIOEP(compartment);
			}
			updateIOBoundsForCFResize(
					request,
					compoundCmd,
					compartment.getChildren(),
					cf,
					targetIOEP != null ? (InteractionOperand) targetIOEP
							.resolveSemanticElement() : null,
					request.getSizeDelta(), request.getResizeDirection());
		}
		return compoundCmd;
	}
	*/

	/**
	 * apex updated - 사용안함 - createIOEPResizeCommand()에서 모두 처리
	 * 
	 * Update Interaction Operand EditpPart bounds after CombinedFragment to be resized. 
	 * @param request
	 * @param compoundCmd
	 * @param combinedFragmentChildrenEditParts
	 * @param cf
	 * @param targetOperand
	 * @param sizeDelta
	 * @param direction
	 */
	/* apex replaced
	private static void updateIOBoundsForCFResize(final ChangeBoundsRequest request,
			CompoundCommand compoundCmd,
			List<EditPart> combinedFragmentChildrenEditParts,
			CombinedFragment cf, InteractionOperand targetOperand,
			final Dimension sizeDelta, int direction) {
		InteractionOperandEditPart targetOperandEditPart = findTargetOperandEditPart(
				cf, targetOperand, combinedFragmentChildrenEditParts);
		for (EditPart ep : combinedFragmentChildrenEditParts) {
			if (ep instanceof InteractionOperandEditPart) {
				InteractionOperandEditPart ioEP = (InteractionOperandEditPart) ep;
				Object ioEPModel = ioEP.getModel();
				if (ioEPModel instanceof Shape) {
					Shape ioEPShape = (Shape) ioEPModel;
					if (ioEPShape.getLayoutConstraint() instanceof Bounds) {
						final Bounds ioEPOriginalBounds = (Bounds) ioEPShape
								.getLayoutConstraint();
						ICommand cmd = new UpdateIOBoundsForCFResizeCommand(
								ioEP.getEditingDomain(),
								"Update operand bounds interaction command",
								ioEPOriginalBounds, request, sizeDelta,
								ioEP == targetOperandEditPart, direction);
						compoundCmd.add(new ICommandProxy(cmd));
					}
				}
			}
		}

	}
	*/
	
	/**
	 * Update bounds using new rect
	 * @param bounds
	 * @param rect
	 * @param transactionalEditingDomain
	 */
	public static void updateBounds(final Bounds bounds, final Rectangle rect,
			TransactionalEditingDomain transactionalEditingDomain) {
		if (bounds != null) {
			final Rectangle originalBounds = new Rectangle();
			originalBounds.setX(bounds.getX());
			originalBounds.setY(bounds.getY());
			originalBounds.setWidth(bounds.getWidth());
			originalBounds.setHeight(bounds.getHeight());
			AbstractCommand cmd = new AbstractCommand() {
				public boolean canExecute() {
					return true;
				}

				public void execute() {
					exec(rect);
				}

				public void redo() {
					execute();
				}
				
				public void undo(){
					exec(originalBounds);
				}
				
				public void exec(Rectangle rect){
					fillBounds(bounds,rect);
				}

				public boolean canUndo() {
					return true;
				}
			};
			CommandHelper.executeCommandWithoutHistory(
					transactionalEditingDomain, cmd,true);
		}
	}

	/**
	 * Get InteractionOperandEditPart from its model object InteractionOperand.
	 * @param cf
	 * @param targetOperand
	 * @param combinedFragmentChildrenEditParts
	 * @return InteractionOperandEditPart
	 */
	public static InteractionOperandEditPart findTargetOperandEditPart(CombinedFragment cf,
			InteractionOperand targetOperand,
			List<EditPart> combinedFragmentChildrenEditParts) {
		if(targetOperand == null)
			return null;
		for(EditPart ep : combinedFragmentChildrenEditParts) {
			if(ep instanceof InteractionOperandEditPart) {
				InteractionOperandEditPart ioEP = (InteractionOperandEditPart)ep;
				InteractionOperand io = (InteractionOperand)ioEP.resolveSemanticElement();
				if(cf.getOperands().contains(io)) {
					if(targetOperand.equals(io)) {
						return ioEP;
					}
				}
			}
		}
		return null;
	}
	
	/**
	 * Get graphical edit part bounds
	 * @param editPart
	 * @return
	 */
	public static Bounds getEditPartBounds(GraphicalEditPart editPart){
		if (editPart.getModel() instanceof Node) {
			Node node = (Node) editPart.getModel();
			if (node.getLayoutConstraint() instanceof Bounds) {
				Bounds bounds = (Bounds) node.getLayoutConstraint();
				return bounds;
			}
		}
		return null;
	}
	
	/**
	 * Fill data from Bounds to Rectangle
	 * @param source
	 * @return Rectangle
	 */
	public static Rectangle fillRectangle(Bounds source) {
		if (source == null) {
			return null;
		}
		Rectangle target = new Rectangle();
		target.setX(source.getX());
		target.setY(source.getY());
		target.setWidth(source.getWidth());
		target.setHeight(source.getHeight());
		return target;
	}
	
	/**
	 * Fill data from Rectangle to Bounds
	 * @param bounds
	 * @param source
	 * @return Bounds
	 */
	public static Bounds fillBounds(Bounds bounds,Rectangle source) {
		if (bounds == null || source == null) {
			return null;
		}
		bounds.setX(source.x());
		bounds.setY(source.y());
		bounds.setWidth(source.width());
		bounds.setHeight(source.height());
		return bounds;
	}
	
	/**
	 * Get interaction operand bounds
	 * @param operandEP
	 * @return Bounds
	 */
	public static Bounds getInteractionOperandEPBounds(GraphicalEditPart operandEP){
		Object lastChildModel = operandEP.getModel();
		if (lastChildModel instanceof Shape) {
			Shape lastOperandShape = (Shape) lastChildModel;
			if (lastOperandShape.getLayoutConstraint() instanceof Bounds) {
				return (Bounds)lastOperandShape.getLayoutConstraint();
			}
		}
		return null;
	}
	
	/**
	 * apex updated
	 * IO의 경계를 직접 변경하거나, IO내의 element의 move/resize에 의해 경계가 변경되는 경우 처리
	 * 
	 * @param request
	 * @param currentIOEP
	 * @return
	 */
	public static Command createIOEPResizeCommand(ChangeBoundsRequest request, InteractionOperandEditPart currentIOEP) {
		return createIOEPResizeCommand(request, currentIOEP, false);
	}
	
	/**
	 * apex updated
	 * IO의 경계를 직접 변경하거나, IO내의 element의 move/resize에 의해 경계가 변경되는 경우 처리
	 * Create interaction operand resize command
	 * 
	 * @param currentIOEP
	 * @param heightDelta
	 * @param compartEP
	 * @param direction
	 * @return
	 */
	public static Command createIOEPResizeCommand(ChangeBoundsRequest request, InteractionOperandEditPart currentIOEP, boolean isCalledByParentCFResize) {
		
		int widthDelta = request.getSizeDelta().width;
		int heightDelta = request.getSizeDelta().height;
		CombinedFragmentCombinedFragmentCompartmentEditPart compartEP = (CombinedFragmentCombinedFragmentCompartmentEditPart)currentIOEP.getParent();
		int direction = request.getResizeDirection();
		
		Bounds currentIOEPBounds = OperandBoundsComputeHelper.getEditPartBounds(currentIOEP);
		
		CombinedFragmentEditPart cfep = (CombinedFragmentEditPart)compartEP.getParent();
		
		if (currentIOEPBounds == null) {
			return null;
		}
		
		if (heightDelta < 0) {
			if (currentIOEPBounds.getHeight() - Math.abs(heightDelta) < OperandBoundsComputeHelper.DEFAULT_INTERACTION_OPERAND_HEIGHT) {
				return null;
			}
		}
		
		InteractionOperandEditPart targetIOEP = null;
		List<InteractionOperandEditPart> belowIOEPs = null;
		List<InteractionOperandEditPart> siblingIOEPsExceptTargetIOEP = null;
		
		if ((direction & PositionConstants.NORTH) != 0) {
			targetIOEP = OperandBoundsComputeHelper.findPreviousIOEP(compartEP, currentIOEP);
		} else if ((direction & PositionConstants.SOUTH) != 0) {
			targetIOEP = OperandBoundsComputeHelper.findLatterIOEP(compartEP, currentIOEP);
			belowIOEPs = apexFindBelowIOEPs(compartEP, currentIOEP);
		}
		if ((direction & PositionConstants.EAST_WEST) != 0) {
			List cfChildren = currentIOEP.getParent().getChildren();
			List<InteractionOperandEditPart> childrenIOEPs = new ArrayList<InteractionOperandEditPart>();
			for ( Object obj : cfChildren ) {
				if ( obj instanceof InteractionOperandEditPart
					 && !obj.equals(currentIOEP)
					 && !obj.equals(targetIOEP) ) {
					childrenIOEPs.add((InteractionOperandEditPart)obj);
				}
			}
			siblingIOEPsExceptTargetIOEP = childrenIOEPs;
		}
		
		CompositeCommand compositeCommand = new CompositeCommand("Resize Operand");
		
		ChangeBoundsRequest cbCFRequest = new ChangeBoundsRequest(ApexSequenceRequestConstants.APEX_REQUEST_MOVE_AND_RESIZE_COMBINEDFRAGMENT);
		
		Rectangle currentIOEPRect = OperandBoundsComputeHelper.fillRectangle(currentIOEPBounds);
		
		IFigure cfFigure = cfep.getFigure();
		Rectangle cfRect = cfFigure.getBounds().getCopy();
		
		int cfX = cfRect.x;
		int cfY = cfRect.y;
		int cfWidth = cfRect.width;
		int cfHeight = cfRect.height;
		
		if ( (direction & PositionConstants.NORTH) != 0 ) { // 상단에서 확대/축소하는 경우
			
			// 최상단 Op의 경우
			if(currentIOEP == OperandBoundsComputeHelper.findFirstIOEP(compartEP)) {
				// 최상단 Op에서 위로 확장하는 경우
				// CF를 위로 이동 시키고 최상단 Op를 확장
				currentIOEPRect.setHeight(currentIOEPBounds.getHeight() + heightDelta);
				currentIOEPRect.setWidth(currentIOEPBounds.getWidth() + widthDelta);				
				currentIOEPRect.setY(0); // 최상단 Op 는 (0, 0)으로 고정
								
				// belowIOEPs의 width처리 및 Op의 상변 resize로 CF가 이동되어도 belowIOEP는 이동되지 않도록 처리
				belowIOEPs = apexFindBelowIOEPs(compartEP, currentIOEP);
				if ( belowIOEPs != null ) {
					for ( InteractionOperandEditPart aBelowIOEP : belowIOEPs ) {
						Bounds anIOEPBounds = OperandBoundsComputeHelper.getEditPartBounds(aBelowIOEP);
						Rectangle anIOEPRect = OperandBoundsComputeHelper.fillRectangle(anIOEPBounds);
						anIOEPRect.setWidth(anIOEPBounds.getWidth() + widthDelta);
						anIOEPRect.setY(anIOEPBounds.getY() + heightDelta);
						
						ICommand anIOEPCommand = OperandBoundsComputeHelper.createUpdateEditPartBoundsCommand(aBelowIOEP, anIOEPRect);						

						compositeCommand.add(anIOEPCommand);
					}
				}
				
				ICommand currentIOEPCommand = OperandBoundsComputeHelper.createUpdateEditPartBoundsCommand(currentIOEP, currentIOEPRect);
				
				compositeCommand.add(currentIOEPCommand);
				
				// CF 경계 처리
				if ( (direction & PositionConstants.WEST) != 0 ) { // 좌측에서 확대/축소하는 경우
					cfX -= widthDelta;
					cfY -= heightDelta;
					cfWidth += widthDelta;
					cfHeight += heightDelta;
					
					cbCFRequest.setMoveDelta(new Point(-widthDelta, -heightDelta));
					cbCFRequest.setSizeDelta(new Dimension(widthDelta, heightDelta));			
					
				} else if ( (direction & PositionConstants.EAST) != 0 ) {
					cfY -= heightDelta;
					cfWidth += widthDelta;
					cfHeight += heightDelta;
					
					cbCFRequest.setMoveDelta(new Point(0, -heightDelta));
					cbCFRequest.setSizeDelta(new Dimension(widthDelta, heightDelta));
				} else {
					cfY -= heightDelta;
					cfHeight += heightDelta;
					
					cbCFRequest.setMoveDelta(new Point(0, -heightDelta));
					cbCFRequest.setSizeDelta(new Dimension(0, heightDelta));
				}
				
			} else { // 최상단 Op가 아닌 경우				

				// Operand Resize에 의한 새 경계가 targetIOEP의 Lowest child 를 침범 시 X 표시 되도록 처리
				if ( apexIsInvadingTargetChildren(currentIOEP, compartEP, direction, heightDelta) ) {
					return null;
				}
				
				Bounds targetIOEPBounds = OperandBoundsComputeHelper.getEditPartBounds(targetIOEP);
				if (targetIOEPBounds == null) {
					return null;
				}										

				Rectangle targetIOEPRect = null;	
				targetIOEPRect = OperandBoundsComputeHelper.fillRectangle(targetIOEPBounds);
			
				// 축소되는 targetIOEP가 최소 높이 이하로 축소되지 않도록
				if (targetIOEPBounds.getHeight() - heightDelta < OperandBoundsComputeHelper.DEFAULT_INTERACTION_OPERAND_HEIGHT) {
					return null;
				}
				
				// target을 축소하고, current를 확대, CF경계 불변				
				targetIOEPRect.setHeight(targetIOEPBounds.getHeight() - heightDelta);
				currentIOEPRect.setHeight(currentIOEPBounds.getHeight() + heightDelta);
				
				targetIOEPRect.setWidth(targetIOEPBounds.getWidth() + widthDelta);
				currentIOEPRect.setWidth(currentIOEPBounds.getWidth() + widthDelta);
				
				currentIOEPRect.setY(currentIOEPRect.y() - heightDelta);
				
				// target를 제외한 sibling width 변경
				List<InteractionOperandEditPart> siblingsExceptBelow = siblingIOEPsExceptTargetIOEP;
				if ( siblingsExceptBelow != null) {
				
					for (InteractionOperandEditPart anIoep : siblingsExceptBelow) {
						Bounds anIOEPBounds = OperandBoundsComputeHelper.getEditPartBounds(anIoep);
						Rectangle anIOEPRect = OperandBoundsComputeHelper.fillRectangle(anIOEPBounds);
						anIOEPRect.setWidth(anIOEPBounds.getWidth() + widthDelta);
						
						ICommand anIOEPCommand = OperandBoundsComputeHelper.createUpdateEditPartBoundsCommand(anIoep, anIOEPRect);						

						compositeCommand.add(anIOEPCommand);
					}	
				}				
				
				ICommand currentIOEPCommand = OperandBoundsComputeHelper.createUpdateEditPartBoundsCommand(currentIOEP, currentIOEPRect);
				ICommand targetIOEPCommand = OperandBoundsComputeHelper.createUpdateEditPartBoundsCommand(targetIOEP, targetIOEPRect);
				
				compositeCommand.add(currentIOEPCommand);	
				compositeCommand.add(targetIOEPCommand);
				
				// CF 경계 처리를 위한 ChangeBoundsRequeset 처리
				if ( (direction & PositionConstants.WEST) != 0 ) { // 좌측에서 확대/축소하는 경우
					cfX -= widthDelta;
					cfWidth += widthDelta;
					
					cbCFRequest.setMoveDelta(new Point(-widthDelta, 0));
					cbCFRequest.setSizeDelta(new Dimension(widthDelta, 0));
				} else if ( (direction & PositionConstants.EAST) != 0 ) { // 우측에서 확대/축소하는 경우
					cfWidth += widthDelta;
					
					cbCFRequest.setSizeDelta(new Dimension(widthDelta, 0));
				}			
			}
		
		} else if ( (direction & PositionConstants.SOUTH) != 0 ) { // 하단에서 아래로 드래그하여 확대하는 경우
			
			// 최하단 Op의 경우
			if(currentIOEP == OperandBoundsComputeHelper.findLastIOEP(compartEP)) {

				// 최하단 Op에서 Resize시 CF Resize
				currentIOEPRect.setHeight(currentIOEPBounds.getHeight() + heightDelta);				
				currentIOEPRect.setWidth(currentIOEPBounds.getWidth() + widthDelta);
				
				// target를 제외한 sibling width 변경
				List<InteractionOperandEditPart> siblingsExceptBelow = siblingIOEPsExceptTargetIOEP;
				if ( siblingsExceptBelow != null) {
				
					for (InteractionOperandEditPart anIoep : siblingsExceptBelow) {
						Bounds anIOEPBounds = OperandBoundsComputeHelper.getEditPartBounds(anIoep);
						Rectangle anIOEPRect = OperandBoundsComputeHelper.fillRectangle(anIOEPBounds);
						anIOEPRect.setWidth(anIOEPBounds.getWidth() + widthDelta);
						
						ICommand anIOEPCommand = OperandBoundsComputeHelper.createUpdateEditPartBoundsCommand(anIoep, anIOEPRect);						

						compositeCommand.add(anIOEPCommand);
					}	
				}
				
				ICommand currentIOEPCommand = OperandBoundsComputeHelper.createUpdateEditPartBoundsCommand(currentIOEP, currentIOEPRect);
				compositeCommand.add(currentIOEPCommand);
				
				// CF 경계 처리
				if ( (direction & PositionConstants.WEST) != 0 ) { // 좌측에서 확대/축소하는 경우
					cfX -= widthDelta;
					cfWidth += widthDelta;
					cfHeight += heightDelta;
					
					cbCFRequest.setMoveDelta(new Point(-widthDelta, 0));
					cbCFRequest.setSizeDelta(new Dimension(widthDelta, heightDelta));
				} else if ( (direction & PositionConstants.EAST) != 0 ) {						
					cfWidth += widthDelta;
					cfHeight += heightDelta;
					
					cbCFRequest.setSizeDelta(new Dimension(widthDelta, heightDelta));
				} else {
					cfHeight += heightDelta;
					
					cbCFRequest.setSizeDelta(new Dimension(0, heightDelta));
				}
				
			} else { // 최하단 Op 가 아닌 경우
				// current 경계 확대
				currentIOEPRect.setHeight(currentIOEPBounds.getHeight() + heightDelta);
				currentIOEPRect.setWidth(currentIOEPBounds.getWidth() + widthDelta);
				ICommand currentIOEPCommand = OperandBoundsComputeHelper.createUpdateEditPartBoundsCommand(currentIOEP, currentIOEPRect);
				compositeCommand.add(currentIOEPCommand);
				
				// belowIOEPs 위/아래로 이동 및 width 처리
				if ( belowIOEPs != null ) {
					for ( InteractionOperandEditPart belowIOEP : belowIOEPs ) {						
						Bounds belowIOEPBounds = OperandBoundsComputeHelper.getEditPartBounds(belowIOEP);
						Rectangle belowIOEPRect = OperandBoundsComputeHelper.fillRectangle(belowIOEPBounds);
						
						belowIOEPRect.setY(belowIOEPRect.y() + heightDelta);
						belowIOEPRect.setWidth(belowIOEPBounds.getWidth() + widthDelta);
						
						ICommand belowIOEPCommand = OperandBoundsComputeHelper.createUpdateEditPartBoundsCommand(belowIOEP, belowIOEPRect);						

						compositeCommand.add(belowIOEPCommand);
					}	
				}				
				
				// below를 제외한 sibling width 변경
				List<InteractionOperandEditPart> siblingsExceptBelow = siblingIOEPsExceptTargetIOEP;
				if ( siblingsExceptBelow != null) {
					if ( belowIOEPs != null ) {
						siblingsExceptBelow.removeAll(belowIOEPs);	
					}
					for (InteractionOperandEditPart anIoep : siblingsExceptBelow) {
						Bounds anIOEPBounds = OperandBoundsComputeHelper.getEditPartBounds(anIoep);
						Rectangle anIOEPRect = OperandBoundsComputeHelper.fillRectangle(anIOEPBounds);
						anIOEPRect.setWidth(anIOEPBounds.getWidth() + widthDelta);
						
						ICommand anIOEPCommand = OperandBoundsComputeHelper.createUpdateEditPartBoundsCommand(anIoep, anIOEPRect);	
						compositeCommand.add(anIOEPCommand);
					}	
				}
				
				// CF 경계 처리를 위한 ChangeBoundsRequeset 처리
				if ( (direction & PositionConstants.WEST) != 0 ) { // 좌측에서 확대/축소하는 경우					
					cfX -= widthDelta;
					cfWidth += widthDelta;
					cfHeight += heightDelta;
					
					cbCFRequest.setMoveDelta(new Point(-widthDelta, 0));
					cbCFRequest.setSizeDelta(new Dimension(widthDelta, heightDelta));
				} else if ( (direction & PositionConstants.EAST) != 0 ) { // 우측에서 확대/축소하는 경우
					cfWidth += widthDelta;
					cfHeight += heightDelta;
					
					cbCFRequest.setSizeDelta(new Dimension(widthDelta, heightDelta));
				} else { // 하단에서 
					cfWidth += widthDelta;
					cfHeight += heightDelta;
					
					cbCFRequest.setSizeDelta(new Dimension(widthDelta, heightDelta));
				}
			}
			
		} else if ( (direction & PositionConstants.NORTH_SOUTH) == 0 ) { // 좌우로만 이동하는 경우
			// current 경계 확대
			currentIOEPRect.setWidth(currentIOEPBounds.getWidth() + widthDelta);
			ICommand currentIOEPCommand = OperandBoundsComputeHelper.createUpdateEditPartBoundsCommand(currentIOEP, currentIOEPRect);
			compositeCommand.add(currentIOEPCommand);
			
			// belowIOEPs 위/아래로 이동 및 width 처리
			if ( belowIOEPs != null ) {
				for ( InteractionOperandEditPart belowIOEP : belowIOEPs ) {						
					Bounds belowIOEPBounds = OperandBoundsComputeHelper.getEditPartBounds(belowIOEP);
					Rectangle belowIOEPRect = OperandBoundsComputeHelper.fillRectangle(belowIOEPBounds);
					
					belowIOEPRect.setY(belowIOEPRect.y() + heightDelta);
					belowIOEPRect.setWidth(belowIOEPBounds.getWidth() + widthDelta);
					
					ICommand belowIOEPCommand = OperandBoundsComputeHelper.createUpdateEditPartBoundsCommand(belowIOEP, belowIOEPRect);
					compositeCommand.add(belowIOEPCommand);
				}	
			}			
			
			// below를 제외한 sibling width 변경
			List<InteractionOperandEditPart> siblingsExceptBelow = siblingIOEPsExceptTargetIOEP;
			if ( siblingsExceptBelow != null) {
				if ( belowIOEPs != null ) {
					siblingsExceptBelow.removeAll(belowIOEPs);	
				}				
				for (InteractionOperandEditPart anIoep : siblingsExceptBelow) {
					Bounds anIOEPBounds = OperandBoundsComputeHelper.getEditPartBounds(anIoep);
					Rectangle anIOEPRect = OperandBoundsComputeHelper.fillRectangle(anIOEPBounds);
					anIOEPRect.setWidth(anIOEPBounds.getWidth() + widthDelta);
					
					ICommand anIOEPCommand = OperandBoundsComputeHelper.createUpdateEditPartBoundsCommand(anIoep, anIOEPRect);	
					compositeCommand.add(anIOEPCommand);
				}	
			}
			
			// CF 경계 처리
			if ( (direction & PositionConstants.WEST) != 0 ) { // 좌측에서 확대/축소하는 경우
				cfX -= widthDelta;				
				cbCFRequest.setMoveDelta(new Point(-widthDelta, 0));
			}
			cfWidth += widthDelta;		
			
			cbCFRequest.setSizeDelta(new Dimension(widthDelta, 0));			
		}
		
		if ( !isCalledByParentCFResize ) {
			Point moveDelta = cbCFRequest.getMoveDelta();
			Dimension sizeDelta = cbCFRequest.getSizeDelta();
			
			if ( !(moveDelta.equals(0, 0) && sizeDelta.equals(0, 0)) ) {
				if(compartEP.getParent() instanceof CombinedFragmentEditPart){
					CombinedFragmentEditPart parentCfep = (CombinedFragmentEditPart) compartEP.getParent();
					Map apexResizeInfo = new HashMap();
					apexResizeInfo.put(ApexSequenceRequestConstants.APEX_KEY_RESIZING_COMBINEDFRAGMENTEDITPART_BY_INTERACTIONOPERAND_RESIZE, currentIOEP);
					cbCFRequest.setExtendedData(apexResizeInfo);
					cbCFRequest.setResizeDirection(direction);
					
					Command compoundCmd = InteractionCompartmentXYLayoutEditPolicy.getCombinedFragmentResizeChildrenCommand(cbCFRequest, parentCfep);
					ApexSequenceUtil.apexCompoundCommandToCompositeCommand(compoundCmd, compositeCommand);			
				}	
			}	
		}
		return new ICommandProxy(compositeCommand);
	}
	
	/**
	 * apex updated - 사용 안 함
	 * createIOEPResizeCommand(ChangeBoundsRequest request, InteractionOperandEditPart currentIOEP)로 대체
	 * 
	 * Create interaction operand resize command
	 * @param currentIOEP
	 * @param heightDelta
	 * @param compartEP
	 * @param direction
	 * @return
	 */
	/* apex replaced
	public static Command createIOEPResizeCommand(
			InteractionOperandEditPart currentIOEP, int heightDelta,
			CombinedFragmentCombinedFragmentCompartmentEditPart compartEP,
			int direction) {
		Bounds currentIOEPBounds = OperandBoundsComputeHelper
				.getEditPartBounds(currentIOEP);
		if (currentIOEPBounds == null) {
			return null;
		}
		InteractionOperandEditPart targetIOEP = null;
		if ((direction & PositionConstants.NORTH) != 0) {
			targetIOEP = OperandBoundsComputeHelper.findPreviousIOEP(compartEP,
					currentIOEP);
		} else if ((direction & PositionConstants.SOUTH) != 0) {
			targetIOEP = OperandBoundsComputeHelper.findLatterIOEP(compartEP,
					currentIOEP);
		}
		
		CompositeCommand compositeCommand = new CompositeCommand(
				"Resize Operand");
		// if last operand
		if(targetIOEP == null){
			if (heightDelta < 0) {
				if (currentIOEPBounds.getHeight() - Math.abs(heightDelta) < OperandBoundsComputeHelper.DEFAULT_INTERACTION_OPERAND_HEIGHT) {
					return null;
				}
			}
			Rectangle currentIOEPRect = OperandBoundsComputeHelper
					.fillRectangle(currentIOEPBounds);
			currentIOEPRect.setHeight(currentIOEPBounds.getHeight() + heightDelta);
			ICommand currentIOEPCommand = OperandBoundsComputeHelper
					.createUpdateEditPartBoundsCommand(currentIOEP, currentIOEPRect);
			compositeCommand.add(currentIOEPCommand);
			// auto update CombinedFragmentEditPart bounds after resize the last operand
			if(compartEP.getParent() instanceof CombinedFragmentEditPart){
				CombinedFragmentEditPart parent = (CombinedFragmentEditPart) compartEP.getParent();
				if (parent.getModel() instanceof Node) {
					Node node = (Node) parent.getModel();
					if (node.getLayoutConstraint() instanceof Bounds) {
						Bounds containerBounds = (Bounds) node
								.getLayoutConstraint();
						Dimension preferredSize = parent.getFigure().getPreferredSize();
						int width = containerBounds.getWidth()!=-1? containerBounds.getWidth() :  preferredSize.width();
						int height = containerBounds.getHeight()!=-1? containerBounds.getHeight() :  preferredSize.height();
						height = height + heightDelta;
						Rectangle containerRect = new Rectangle(containerBounds.getX(),containerBounds.getY(),width,height);
						compositeCommand.add(OperandBoundsComputeHelper.createUpdateEditPartBoundsCommand(parent, containerRect));
					}
				}
			}
		}else{
			Bounds targetIOEPBounds = OperandBoundsComputeHelper
					.getEditPartBounds(targetIOEP);
			if (targetIOEPBounds == null) {
				return null;
			}
			if (heightDelta > 0) {
				if (targetIOEPBounds.getHeight() - heightDelta < OperandBoundsComputeHelper.DEFAULT_INTERACTION_OPERAND_HEIGHT) {
					return null;
				}
			} else {
				if (currentIOEPBounds.getHeight() - Math.abs(heightDelta) < OperandBoundsComputeHelper.DEFAULT_INTERACTION_OPERAND_HEIGHT) {
					return null;
				}
			}
			Rectangle targetIOEPRect = OperandBoundsComputeHelper
					.fillRectangle(targetIOEPBounds);
			Rectangle currentIOEPRect = OperandBoundsComputeHelper
					.fillRectangle(currentIOEPBounds);
			targetIOEPRect.setHeight(targetIOEPBounds.getHeight() - heightDelta);
			currentIOEPRect.setHeight(currentIOEPBounds.getHeight() + heightDelta);
			if ((direction & PositionConstants.NORTH) != 0) {
				currentIOEPRect.setY(currentIOEPRect.y() - heightDelta);
			}else if ((direction & PositionConstants.SOUTH) != 0) {
				targetIOEPRect.setY(targetIOEPRect.y() + heightDelta);
			}

			ICommand previousIOEPCommand = OperandBoundsComputeHelper
					.createUpdateEditPartBoundsCommand(targetIOEP, targetIOEPRect);
			ICommand currentIOEPCommand = OperandBoundsComputeHelper
					.createUpdateEditPartBoundsCommand(currentIOEP, currentIOEPRect);

			compositeCommand.add(previousIOEPCommand);
			compositeCommand.add(currentIOEPCommand);
		}

		return new ICommandProxy(compositeCommand);
	}
	*/
	
	/**
	 * Compute CombinedFragement's header height
	 * @param combinedFragmentEditPart 
	 * @return int
	 */
	public static int computeCombinedFragementHeaderHeight(
			CombinedFragmentEditPart combinedFragmentEditPart) {
		int headerHeight = 0;
		IFigure labelContainer = combinedFragmentEditPart.getPrimaryShape().getHeaderLabel().getParent();
		if(labelContainer!=null){
			headerHeight = labelContainer.getPreferredSize().height();
		}
		return headerHeight;
	}
	
	/**
	 * Check if operation resizing on CombinedFragment is allowed
	 * @param request
	 * @param child
	 * @return boolean
	 */
	public static boolean checkRedistrictOnCFResize(ChangeBoundsRequest request,
			EditPart child) {
		if(child instanceof CombinedFragmentEditPart){
			if ((request.getResizeDirection() & PositionConstants.NORTH_SOUTH) != 0) {
				CombinedFragmentEditPart combinedFragmentEditPart = (CombinedFragmentEditPart)child;
				if (combinedFragmentEditPart.getChildren().size() > 0
						&& combinedFragmentEditPart.getChildren()
								.get(0) instanceof CombinedFragmentCombinedFragmentCompartmentEditPart) {
					CombinedFragmentCombinedFragmentCompartmentEditPart compartment = (CombinedFragmentCombinedFragmentCompartmentEditPart) combinedFragmentEditPart
							.getChildren().get(0);
					List<EditPart> combinedFragmentChildrenEditParts = compartment
							.getChildren();
					CombinedFragment cf = (CombinedFragment) ((CombinedFragmentEditPart) combinedFragmentEditPart)
							.resolveSemanticElement();
					InteractionOperand targetOperand = null;
					if ((request.getResizeDirection() & PositionConstants.NORTH) != 0) {
						targetOperand = cf.getOperands().get(0);
					} else if ((request.getResizeDirection() & PositionConstants.SOUTH) != 0) {
						targetOperand = cf.getOperands().get(
								cf.getOperands().size() - 1);
					}
					InteractionOperandEditPart targetOperandEditPart = OperandBoundsComputeHelper.findTargetOperandEditPart(
							cf, targetOperand, combinedFragmentChildrenEditParts);
					if(targetOperandEditPart!=null){
						int height = targetOperandEditPart.getFigure().getBounds().height();
						int heightDelta = request.getSizeDelta().height();
						if(heightDelta<0){
							if(height-Math.abs(heightDelta) < OperandBoundsComputeHelper.DEFAULT_INTERACTION_OPERAND_HEIGHT){
								return false;
							}
						}
					}
				}
			}
		}
		return true;
	}
	
	/**
	 * Add command for updating adjacent interaction operand bounds after deleting a interaction operand
	 * @param editPart
	 * @param cmd
	 */
	public static void addUpdateBoundsCommandForOperandDelelete(EditPart editPart,ICompositeCommand cmd) {
		if(editPart instanceof InteractionOperandEditPart){
			if(editPart.getParent() instanceof CombinedFragmentCombinedFragmentCompartmentEditPart){
				CombinedFragmentCombinedFragmentCompartmentEditPart compartment = (CombinedFragmentCombinedFragmentCompartmentEditPart)editPart.getParent();
					if(compartment.getParent() instanceof CombinedFragmentEditPart){
						List<EditPart> combinedFragmentChildrenEditParts = compartment
								.getChildren();
						InteractionOperandEditPart previousIOEP = null;
						InteractionOperandEditPart latterIOEP = null;
						boolean isFirstOperand = false;
						
						InteractionOperandEditPart currentioEP = (InteractionOperandEditPart)editPart;
						final Rectangle currentioEPBounds = currentioEP.getFigure().getBounds();
						
						for(int i = 0;i<combinedFragmentChildrenEditParts.size();i++) {
							EditPart ep = combinedFragmentChildrenEditParts.get(i);
							if(ep instanceof InteractionOperandEditPart) {
								InteractionOperandEditPart ioEP = (InteractionOperandEditPart)ep;
								if(currentioEP == ioEP){
									if(previousIOEP!=null){
										Object previousIOEPModel = previousIOEP.getModel();
										if (previousIOEPModel instanceof Shape) {
											Shape previousIOEPShape = (Shape) previousIOEPModel;
											if (previousIOEPShape.getLayoutConstraint() instanceof Bounds) {
												final Bounds previousIOEPOriginalBounds = (Bounds) previousIOEPShape.getLayoutConstraint();
												ICommand command = new AbstractTransactionalCommand(previousIOEP.getEditingDomain(),"Merge operand bounds command",null){
													@Override
													protected CommandResult doExecuteWithResult(
															IProgressMonitor monitor,
															IAdaptable info)
															throws ExecutionException {
														previousIOEPOriginalBounds.setHeight(previousIOEPOriginalBounds.getHeight() + currentioEPBounds.height());
														return CommandResult.newOKCommandResult();
													}
												};
												cmd.add(command);
											}
										}
										break;
									}else{
										isFirstOperand = true; 
										continue;
									}
								}else{
									previousIOEP = ioEP;
								}
								if(isFirstOperand){
									latterIOEP = ioEP;
									break;
								}
							}
						}
						if(isFirstOperand && latterIOEP!=null){
							Object latterIOEPModel = latterIOEP.getModel();
							if (latterIOEPModel instanceof Shape) {
								Shape latterIOEPShape = (Shape) latterIOEPModel;
								if (latterIOEPShape.getLayoutConstraint() instanceof Bounds) {
									final Bounds latterIOEPOriginalBounds = (Bounds) latterIOEPShape.getLayoutConstraint();
									ICommand command = new AbstractTransactionalCommand(previousIOEP.getEditingDomain(),"Merge operand bounds command",null){
										@Override
										protected CommandResult doExecuteWithResult(
												IProgressMonitor monitor,
												IAdaptable info)
												throws ExecutionException {
											latterIOEPOriginalBounds.setY(0);
											latterIOEPOriginalBounds.setHeight(latterIOEPOriginalBounds.getHeight() + currentioEPBounds.height());
											return CommandResult.newOKCommandResult();
										}
									};
									cmd.add(command);
								}
							}
						}
					}
			}
		}
		
	}
	
	/**
	 * apex updated
	 * 
	 * extendedData에 IOEP를 넣어줘야 CF에서 lastOperand를 resize하지 않음
	 * 
	 * Add update InteractionOperand bounds command after IO is created 
	 * @param compartment
	 * @param request
	 * @param command
	 */
	public static void addUpdateBoundsForIOCreationCommand(
			CombinedFragmentCombinedFragmentCompartmentEditPart compartment, ViewDescriptor viewDescriptor,
			CompositeCommand command) {
		List children = compartment.getChildren();
		if (children != null && children.size() > 0) {
			InteractionOperandEditPart lastOperandEP = OperandBoundsComputeHelper.findLastIOEP(compartment);
			// update bounds
			if (lastOperandEP != null) {
				Bounds lastOperandBounds = OperandBoundsComputeHelper.getInteractionOperandEPBounds(lastOperandEP);
				if (lastOperandBounds!= null) {
					Rectangle rect = new Rectangle(
							lastOperandBounds.getX(),
							lastOperandBounds.getY()+lastOperandBounds.getHeight(), lastOperandBounds.getWidth(), OperandBoundsComputeHelper.DEFAULT_INTERACTION_OPERAND_HEIGHT);
					// auto update CombinedFragmentEditPart bounds after added new operand
					if(compartment.getParent() instanceof CombinedFragmentEditPart){
						CombinedFragmentEditPart parent = (CombinedFragmentEditPart) compartment
								.getParent();
						/* apex improved start */
						Map apexResizeInfo = new HashMap();
						apexResizeInfo.put(ApexSequenceRequestConstants.APEX_KEY_RESIZING_COMBINEDFRAGMENTEDITPART_BY_INTERACTIONOPERAND_CREATE, lastOperandEP);
						ChangeBoundsRequest cbRequest = new ChangeBoundsRequest(RequestConstants.REQ_RESIZE);
						cbRequest.setExtendedData(apexResizeInfo);
						cbRequest.setSizeDelta(new Dimension(0, OperandBoundsComputeHelper.DEFAULT_INTERACTION_OPERAND_HEIGHT));
						cbRequest.setResizeDirection(PositionConstants.SOUTH);						
						ApexSequenceUtil.apexCompoundCommandToCompositeCommand(InteractionCompartmentXYLayoutEditPolicy.getCombinedFragmentResizeChildrenCommand(cbRequest, parent),
								                                               command);
						/* apex improved end */
						/* apex replaced
						if (parent.getModel() instanceof Node) {
							Node node = (Node) parent.getModel();
							if (node.getLayoutConstraint() instanceof Bounds) {
								Bounds containerBounds = (Bounds) node.getLayoutConstraint();
								Dimension preferredSize = parent.getFigure().getPreferredSize();
								int width = containerBounds.getWidth()!=-1? containerBounds.getWidth() :  preferredSize.width();
								int height = containerBounds.getHeight()!=-1? containerBounds.getHeight() :  preferredSize.height();
								height = height + OperandBoundsComputeHelper.DEFAULT_INTERACTION_OPERAND_HEIGHT;
								View shapeView = (View) parent.getModel();
								ICommand setParentBoundsCmd = new SetBoundsCommand(compartment.getEditingDomain(),
							            DiagramUIMessages.SetLocationCommand_Label_Resize,
							            new EObjectAdapter(shapeView), new Rectangle(containerBounds.getX(),containerBounds.getY(),width,height));
								command.add(setParentBoundsCmd);
							}
						}
						*/
					}
					command.add(new SetBoundsCommand(compartment.getEditingDomain(), DiagramUIMessages.SetLocationCommand_Label_Resize, viewDescriptor, rect));
				}
			}
		}else{ // first add operand
			if(compartment.getParent() instanceof CombinedFragmentEditPart){
				CombinedFragmentEditPart parent = (CombinedFragmentEditPart) compartment
						.getParent();
				if (parent.getModel() instanceof Node) {
					Node node = (Node) parent.getModel();
					if (node.getLayoutConstraint() instanceof Bounds) {
						Bounds containerBounds = (Bounds) node.getLayoutConstraint();
						Dimension preferredSize = parent.getFigure().getPreferredSize();
						int width = containerBounds.getWidth()!=-1? containerBounds.getWidth() :  preferredSize.width();
						int height = containerBounds.getHeight()!=-1? containerBounds.getHeight() :  preferredSize.height();
						height = height - computeCombinedFragementHeaderHeight(parent);
						command.add(new SetBoundsCommand(compartment.getEditingDomain(), DiagramUIMessages.SetLocationCommand_Label_Resize, viewDescriptor, new Rectangle(0,0,width-COMBINED_FRAGMENT_FIGURE_BORDER*2,height-COMBINED_FRAGMENT_FIGURE_BORDER*2)));
					}
				}
			}
		}
	}
	
	/**
	 * Check if it is a combined fragment.
	 * 
	 * @param hint
	 *        the semantic hint
	 * @return
	 */
	public static boolean isDerivedCombinedFragment(String hint) {
		if(((IHintedType)UMLElementTypes.CombinedFragment_3004).getSemanticHint().equals(hint)) {
			return true;
		}
		if(((IHintedType)UMLElementTypes.ConsiderIgnoreFragment_3007).getSemanticHint().equals(hint)) {
			return true;
		}
		return false;
	}
	
	/** 
	 * apex updated - 사용안함 - createIOEPResizeCommand()에서 모두 처리
	 * 
	 * Command class for updating Interaction Operand EditpPart bounds after CombinedFragment to be resized. 
	 *
	 */
	/* apex replaced
	private static final class UpdateIOBoundsForCFResizeCommand extends
			AbstractTransactionalCommand {
		private final Bounds ioEPOriginalBounds;
		private final ChangeBoundsRequest request;
		private final Dimension sizeDelta;
		private boolean updateHeight = false;
		private int direction;

		private UpdateIOBoundsForCFResizeCommand(TransactionalEditingDomain domain,
				String label, Bounds ioEPOriginalBounds,
				ChangeBoundsRequest request, Dimension sizeDelta,
				boolean updateHeight, int direction) {
			super(domain, label, null);
			this.ioEPOriginalBounds = ioEPOriginalBounds;
			this.request = request;
			this.sizeDelta = sizeDelta;
			this.updateHeight = updateHeight;
			this.direction = direction;
		}

		@Override
		protected CommandResult doExecuteWithResult(IProgressMonitor monitor,
				IAdaptable info) throws ExecutionException {
			if (updateHeight) {
				ioEPOriginalBounds.setHeight(ioEPOriginalBounds.getHeight()
						+ sizeDelta.height());
			} else if ((direction & PositionConstants.NORTH) != 0) {
				ioEPOriginalBounds.setY(ioEPOriginalBounds.getY()
						+ sizeDelta.height());
			}
			if ((request.getResizeDirection() & PositionConstants.EAST_WEST) != 0) {
				ioEPOriginalBounds.setWidth(ioEPOriginalBounds.getWidth()
						+ sizeDelta.width());
			}

			return CommandResult.newOKCommandResult();
		}

	}
	*/

	/**
	 * 상방향으로 확대하는 경우 위에 있는 targetOp의 lowest child를 침범하지 않고
	 * 하뱡향으로 확대하는 경우 아래에 있는 targetOp의 lowest child가 targetOp의 bottom 아래로 밀려내려가지 않도록 처리
	 * 
	 * 상방향으로 축소하는 경우 currentOp의 lowest child를 침범하지 않고
	 * 하방향으로 축소하는 경우 currentOp의 lowest child가 currentOp의 bottom 아래로 밀려내려가지 않도록 처리
	 * 
	 * @param currentIOEP
	 * @param compartEP
	 * @param direction
	 * @param heightDelta
	 * @return
	 */
	public static boolean apexIsInvadingTargetChildren(InteractionOperandEditPart currentIOEP,
			                                           CombinedFragmentCombinedFragmentCompartmentEditPart compartEP,
			                                           int direction,
			                                           int heightDelta) {
		boolean isInvadingTargetChildren = false;
		InteractionOperandEditPart targetIOEP = null;
		
		if ((direction & PositionConstants.NORTH) != 0) {
			targetIOEP = OperandBoundsComputeHelper.findPreviousIOEP(compartEP,
					currentIOEP);
		} else if ((direction & PositionConstants.SOUTH) != 0) {
			targetIOEP = OperandBoundsComputeHelper.findLatterIOEP(compartEP,
					currentIOEP);
		}
		
		if ( targetIOEP != null ) {
			List targetIOEPChildrenEditParts = ApexSequenceUtil.apexGetInteractionOperandChildrenEditParts(targetIOEP);
			IGraphicalEditPart lowestTargetChildEP = ApexSequenceUtil.apexGetLowestEditPartFromList(targetIOEPChildrenEditParts);
			
			List currentIOEPChildrenEditParts = ApexSequenceUtil.apexGetInteractionOperandChildrenEditParts(currentIOEP);
			IGraphicalEditPart lowestCurrentChildEP = ApexSequenceUtil.apexGetLowestEditPartFromList(currentIOEPChildrenEditParts);
			
			if ((direction & PositionConstants.NORTH) != 0) { // 상단에서
				
				if ( heightDelta > 0 ) { // 확대하는 경우
					// currentIOEP의 상단이 targetIOEP의 lowest child보다 위로 가면 X
					int topNewCurrentIOEP = ApexSequenceUtil.apexGetAbsolutePosition(currentIOEP, SWT.TOP) - Math.abs(heightDelta);
					if ( lowestTargetChildEP != null ) {			
						int bottomTargetIOEPLowestChild = ApexSequenceUtil.apexGetAbsolutePosition(lowestTargetChildEP, SWT.BOTTOM);
						
						if ( topNewCurrentIOEP < bottomTargetIOEPLowestChild + OperandBoundsComputeHelper.COMBINED_FRAGMENT_FIGURE_BORDER) {
							isInvadingTargetChildren = true;
						}
					}
				} else { // 축소하는 경우
					// currentIOEP의 new lowest child가  currentIOEP의 하단보다 아래로 내려가면 X
					if ( lowestCurrentChildEP != null ) {
						int bottomNewLowestCurrentChildEP = ApexSequenceUtil.apexGetAbsolutePosition(lowestCurrentChildEP, SWT.BOTTOM) + Math.abs(heightDelta);				
						int bottomCurrentIOEP = ApexSequenceUtil.apexGetAbsolutePosition(currentIOEP, SWT.BOTTOM);
						
						if ( (bottomNewLowestCurrentChildEP + OperandBoundsComputeHelper.COMBINED_FRAGMENT_FIGURE_BORDER) >= bottomCurrentIOEP ) {
							isInvadingTargetChildren = true;
						}	
					}				
				}					
			} else if ((direction & PositionConstants.SOUTH) != 0) { // 하단에서
				
				if ( heightDelta > 0 ) { // 확대하는 경우
					// targetIOEP의 new lowest child가  targetIOEP의 하단보다 아래로 내려가면 X 
					if ( lowestTargetChildEP != null ) {
						int bottomNewLowestTargetChildEP = ApexSequenceUtil.apexGetAbsolutePosition(lowestTargetChildEP, SWT.BOTTOM) + Math.abs(heightDelta);
						int bottomTargetIOEP = ApexSequenceUtil.apexGetAbsolutePosition(targetIOEP, SWT.BOTTOM);
						
						if ( (bottomNewLowestTargetChildEP + OperandBoundsComputeHelper.COMBINED_FRAGMENT_FIGURE_BORDER) >= bottomTargetIOEP ) {
							isInvadingTargetChildren = true;
						}
					}
				} else { // 축소하는 경우
					// currentIOEP의 하단이 currentIOEP의 lowest child보다 위로 가면 X
					if ( lowestCurrentChildEP != null ) {
						int bottomNewCurrentIOEP = ApexSequenceUtil.apexGetAbsolutePosition(currentIOEP, SWT.BOTTOM) - Math.abs(heightDelta);
						int bottomCurrentIOEPLowestChild = ApexSequenceUtil.apexGetAbsolutePosition(lowestCurrentChildEP, SWT.BOTTOM);
						
						if ( bottomNewCurrentIOEP < bottomCurrentIOEPLowestChild + OperandBoundsComputeHelper.COMBINED_FRAGMENT_FIGURE_BORDER) {
							isInvadingTargetChildren = true;
						}	
					}				
				}
			}
		} else { // 하나뿐인 Op의 경우
			List currentIOEPChildrenEditParts = ApexSequenceUtil.apexGetInteractionOperandChildrenEditParts(currentIOEP);
			IGraphicalEditPart lowestCurrentChildEP = ApexSequenceUtil.apexGetLowestEditPartFromList(currentIOEPChildrenEditParts);
			if ( (direction & PositionConstants.NORTH) != 0 && heightDelta < 0 ) { // 상단에서 축소하는 경우
				// currentIOEP의 new lowest child가  currentIOEP의 하단보다 아래로 내려가면 X
				if ( lowestCurrentChildEP != null ) {
					int bottomNewLowestCurrentChildEP = ApexSequenceUtil.apexGetAbsolutePosition(lowestCurrentChildEP, SWT.BOTTOM) + Math.abs(heightDelta);				
					int bottomCurrentIOEP = ApexSequenceUtil.apexGetAbsolutePosition(currentIOEP, SWT.BOTTOM);
					
					if ( (bottomNewLowestCurrentChildEP + OperandBoundsComputeHelper.COMBINED_FRAGMENT_FIGURE_BORDER) >= bottomCurrentIOEP ) {
						isInvadingTargetChildren = true;
					}	
				}
			ApexSequenceUtil.apexGetCoveredLifelineEditParts(currentIOEP, true);
				
			}
		}

		return isInvadingTargetChildren;
	}
}
