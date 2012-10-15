package org.eclipse.papyrus.uml.diagram.sequence.util;

import java.util.List;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.common.core.command.CompositeCommand;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.common.core.command.ICompositeCommand;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.commands.SetBoundsCommand;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.l10n.DiagramUIMessages;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewRequest;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewRequest.ViewDescriptor;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;
import org.eclipse.gmf.runtime.emf.core.util.EObjectAdapter;
import org.eclipse.gmf.runtime.emf.type.core.IHintedType;
import org.eclipse.gmf.runtime.notation.Bounds;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.Shape;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.papyrus.uml.diagram.sequence.edit.parts.CombinedFragmentCombinedFragmentCompartmentEditPart;
import org.eclipse.papyrus.uml.diagram.sequence.edit.parts.CombinedFragmentEditPart;
import org.eclipse.papyrus.uml.diagram.sequence.edit.parts.InteractionInteractionCompartmentEditPart;
import org.eclipse.papyrus.uml.diagram.sequence.edit.parts.InteractionOperandEditPart;
import org.eclipse.papyrus.uml.diagram.sequence.part.UMLVisualIDRegistry;
import org.eclipse.papyrus.uml.diagram.sequence.providers.UMLElementTypes;
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
	 * Create command for updating Interaction Operand EditpPart bounds after CombinedFragment to be resized. 
	 * @param compoundCmd
	 * @param request
	 * @param combinedFragmentEditPart
	 * @return Command
	 */
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

	/**
	 * Update Interaction Operand EditpPart bounds after CombinedFragment to be resized. 
	 * @param request
	 * @param compoundCmd
	 * @param combinedFragmentChildrenEditParts
	 * @param cf
	 * @param targetOperand
	 * @param sizeDelta
	 * @param direction
	 */
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
	 * Create interaction operand resize command
	 * @param currentIOEP
	 * @param heightDelta
	 * @param compartEP
	 * @param direction
	 * @return
	 */
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
	 * Command class for updating Interaction Operand EditpPart bounds after CombinedFragment to be resized. 
	 *
	 */
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

}
