/*****************************************************************************
 * Copyright (c) 2009 CEA
 *
 *    
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Atos Origin - Initial API and implementation
 *
 *****************************************************************************/
package kr.co.apexsoft.modellipse.customization.diagram.sequence.edit.policies;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.commands.SetBoundsCommand;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.XYLayoutEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.l10n.DiagramUIMessages;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewAndElementRequest;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewRequest;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewRequest.ViewDescriptor;
import org.eclipse.gmf.runtime.emf.type.core.IHintedType;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.papyrus.uml.diagram.common.commands.PreserveAnchorsPositionCommand;
import org.eclipse.papyrus.uml.diagram.common.draw2d.LifelineDotLineFigure;
import org.eclipse.papyrus.uml.diagram.common.editpolicies.BorderItemResizableEditPolicy;
import org.eclipse.papyrus.uml.diagram.sequence.command.ApexPreserveAnchorsPositionCommand;
import org.eclipse.papyrus.uml.diagram.sequence.command.CustomZOrderCommand;
import org.eclipse.papyrus.uml.diagram.sequence.edit.parts.ActionExecutionSpecificationEditPart;
import org.eclipse.papyrus.uml.diagram.sequence.edit.parts.BehaviorExecutionSpecificationEditPart;
import org.eclipse.papyrus.uml.diagram.sequence.edit.parts.CombinedFragment2EditPart;
import org.eclipse.papyrus.uml.diagram.sequence.edit.parts.DestructionOccurrenceSpecificationEditPart;
import org.eclipse.papyrus.uml.diagram.sequence.edit.parts.LifelineEditPart;
import org.eclipse.papyrus.uml.diagram.sequence.edit.parts.StateInvariantEditPart;
import org.eclipse.papyrus.uml.diagram.sequence.edit.policies.InteractionCompartmentXYLayoutEditPolicy;
import org.eclipse.papyrus.uml.diagram.sequence.part.UMLVisualIDRegistry;
import org.eclipse.papyrus.uml.diagram.sequence.providers.UMLElementTypes;
import org.eclipse.papyrus.uml.diagram.sequence.util.OccurrenceSpecificationMoveHelper;
import org.eclipse.papyrus.uml.diagram.sequence.util.SequenceRequestConstant;
import org.eclipse.papyrus.uml.diagram.sequence.util.SequenceUtil;

/**
 * The custom LayoutEditPolicy for LifelineEditPart.
 */
public class ApexLifelineXYLayoutEditPolicy extends XYLayoutEditPolicy {

	/** Initialization width of Execution Specification. */
	public final static int EXECUTION_INIT_WIDTH = 16;

	/** Initialization width of CoRegion. */
	public final static int COREGION_INIT_WIDTH = 30;

	/** Initialization height of Execution Specification. */
	private final static int EXECUTION_INIT_HEIGHT = 50;

	/** Initialization height of a time bar figure. */
	private static final int TIME_BAR_HEIGHT = 1;

	/** The default spacing used between Execution Specification */
	private final static int SPACING_HEIGHT = 5;

	/**
	 * apex updated
	 * 
	 * @param lifelineEP
	 * @param request
	 * @param isMove
	 * @param updateEnclosingInteraction
	 * @param useFixedXPos
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Command getResizeOrMoveChildrenCommand(LifelineEditPart lifelineEP, ChangeBoundsRequest request, boolean isMove, boolean updateEnclosingInteraction, boolean useFixedXPos) {
		List<EditPart> editParts = request.getEditParts();

		if(editParts != null) {
			CompoundCommand compoundCmd = new CompoundCommand();
			compoundCmd.setLabel("Move or resize");
			compoundCmd.setDebugLabel("Debug: Move or resize of an ExecutionSpecification");

			for(EditPart ep : editParts) {

				if(ep instanceof ActionExecutionSpecificationEditPart || ep instanceof BehaviorExecutionSpecificationEditPart) {

					// an execution specification have been moved or resized
					ShapeNodeEditPart executionSpecificationEP = (ShapeNodeEditPart)ep;

					// Check if height is within the limits of the figure
					Dimension newSizeDelta = adaptSizeDeltaToMaxHeight(executionSpecificationEP.getFigure(), request.getSizeDelta());

					// Current bounds of the ExecutionSpecification
					Rectangle oldBounds = executionSpecificationEP.getFigure().getBounds().getCopy();

					Rectangle newBounds = oldBounds.getCopy();

					// According to the parameters, the new bounds would be the following
					newBounds.x += request.getMoveDelta().x;
					newBounds.y += request.getMoveDelta().y;
					newBounds.height += newSizeDelta.height;

					// Not to check list
					List<ShapeNodeEditPart> notToCheckExecutionSpecificationList = new BasicEList<ShapeNodeEditPart>();
					// Affixed ExecutionSpecification List
					notToCheckExecutionSpecificationList.addAll(getAffixedExecutionSpecificationEditParts(executionSpecificationEP));
					// Add also current ExecutionSpecification EditPart
					notToCheckExecutionSpecificationList.add(executionSpecificationEP);

					// find parent bar
					List<ShapeNodeEditPart> executionSpecificationList = lifelineEP.getChildShapeNodeEditPart();
					executionSpecificationList.remove(executionSpecificationEP);
					ShapeNodeEditPart parentBar = getParent(lifelineEP, newBounds, executionSpecificationList);
					
					// change bounds to relative
					newBounds = getExecutionSpecificationNewBounds(isMove, lifelineEP, oldBounds, newBounds, notToCheckExecutionSpecificationList, useFixedXPos);
					if(newBounds == null) {
						return UnexecutableCommand.INSTANCE;
					}

					if(parentBar != null){
						compoundCmd.add(resizeParentExecutionSpecification(lifelineEP, parentBar, newBounds.getCopy(), executionSpecificationList));
					}
					
					// Create and add the command to the compound command
					SetBoundsCommand setBoundsCmd = new SetBoundsCommand(executionSpecificationEP.getEditingDomain(), "Resize of a ExecutionSpecification", executionSpecificationEP, newBounds);
					compoundCmd.add(new ICommandProxy(setBoundsCmd));

					Rectangle realMoveDelta = getRealMoveDelta(getRelativeBounds(executionSpecificationEP.getFigure()), newBounds);

					if(isMove) {
						// Move also children
						compoundCmd.add(createMovingAffixedExecutionSpecificationCommand(executionSpecificationEP, realMoveDelta, newBounds));

						compoundCmd.add(createZOrderCommand(lifelineEP, executionSpecificationEP, newBounds, notToCheckExecutionSpecificationList));
					}
					/* apex added start */
					else {
						Object data = request.getExtendedData().get(SequenceRequestConstant.DO_NOT_MOVE_EDIT_PARTS);
						if (!Boolean.TRUE.equals(data)) {
							compoundCmd.add(apexCreateMovingAffixedExecutionSpecificationCommand(executionSpecificationEP, realMoveDelta, newBounds));
							compoundCmd.add(createZOrderCommand(lifelineEP, executionSpecificationEP, newBounds, notToCheckExecutionSpecificationList));
						}
					}
					/* apex added end */

					// Move also linked Time elements
					compoundCmd = OccurrenceSpecificationMoveHelper.completeMoveExecutionSpecificationCommand(compoundCmd, executionSpecificationEP, newBounds, request);

					IFigure parentFigure = executionSpecificationEP.getFigure().getParent();
					parentFigure.translateToAbsolute(newBounds);
					// translateToAbsolute only does half of the work, I don't know why
					newBounds.translate(parentFigure.getBounds().getLocation());

					if(updateEnclosingInteraction) {
						// update the enclosing interaction of a moved execution specification
						compoundCmd.add(SequenceUtil.createUpdateEnclosingInteractionCommand(executionSpecificationEP, request.getMoveDelta(), newSizeDelta));
					}

					// keep absolute position of anchors
					/* apex improved start */
					Object relative = request.getExtendedData().get(SequenceRequestConstant.PRESERVE_ANCHOR_RELATIVE_BOUNDS);
					ApexPreserveAnchorsPositionCommand command = new ApexPreserveAnchorsPositionCommand(executionSpecificationEP, new Dimension(realMoveDelta.width, realMoveDelta.height),
							PreserveAnchorsPositionCommand.PRESERVE_Y, executionSpecificationEP.getFigure(), request.getResizeDirection(), relative);
					compoundCmd.add(new ICommandProxy(command));
					/* apex improved end */
					/* apex replaced
					compoundCmd.add(new ICommandProxy(new LifelineEditPart.PreserveAnchorsPositionCommandEx(executionSpecificationEP, new Dimension(realMoveDelta.width, realMoveDelta.height), PreserveAnchorsPositionCommand.PRESERVE_Y, executionSpecificationEP.getFigure(), request.getResizeDirection())));
					*/
				}

				if(ep instanceof CombinedFragment2EditPart) {
					CombinedFragment2EditPart cf2EP = (CombinedFragment2EditPart)ep;
					IFigure cf2Figure = cf2EP.getFigure();
					Rectangle bounds = cf2Figure.getBounds().getCopy();
					cf2Figure.getParent().translateToAbsolute(bounds);

					Dimension sizeDelta = request.getSizeDelta();
					if(sizeDelta != null) {
						if(sizeDelta.width != 0) {
							return UnexecutableCommand.INSTANCE;
						}
						bounds.resize(sizeDelta);
					}
					Point moveDelta = request.getMoveDelta();
					if(moveDelta != null) {
						bounds.translate(moveDelta);
					}

					// Create and add the set bounds command to the compound command
					SetBoundsCommand setBoundsCmd = new SetBoundsCommand(cf2EP.getEditingDomain(), "Resize of a CoRegion", cf2EP, getNewBoundsForCoRegion(lifelineEP, bounds));
					compoundCmd.add(new ICommandProxy(setBoundsCmd));
				}
				
				/* apex added start */
				if(ep instanceof StateInvariantEditPart) {
					StateInvariantEditPart siEP = (StateInvariantEditPart)ep;
					IFigure siFigure = siEP.getFigure();
					Rectangle newBounds = siFigure.getBounds().getCopy();

					// Get the dotline figure
					LifelineDotLineFigure figureLifelineDotLineFigure = lifelineEP.getPrimaryShape().getFigureLifelineDotLineFigure();

					Point moveDelta = request.getMoveDelta();
					if(moveDelta != null) {
						newBounds.translate(moveDelta);
						newBounds.y -= 10;
					}
					
					// Create and add the set bounds command to the compound command
					SetBoundsCommand setBoundsCmd = new SetBoundsCommand(siEP.getEditingDomain(), "Apex Move of a StateInvariant", siEP, newBounds);
					compoundCmd.add(new ICommandProxy(setBoundsCmd));
					
					InteractionCompartmentXYLayoutEditPolicy.apexMoveBelowItems(request, siEP, compoundCmd);
				}
				
				if(ep instanceof DestructionOccurrenceSpecificationEditPart) {
					DestructionOccurrenceSpecificationEditPart doEP = (DestructionOccurrenceSpecificationEditPart)ep;
					IFigure siFigure = doEP.getFigure();
					Rectangle bounds = siFigure.getBounds().getCopy();

					Point moveDelta = request.getMoveDelta();
					if(moveDelta != null) {
						bounds.translate(moveDelta);
					}

					// Create and add the set bounds command to the compound command
					SetBoundsCommand setBoundsCmd = new SetBoundsCommand(doEP.getEditingDomain(), "Apex Move of a DestructionEvent", doEP, bounds);
					compoundCmd.add(new ICommandProxy(setBoundsCmd));
				}
				/* apex added end */
			}

			if(!compoundCmd.isEmpty()) {
				return compoundCmd;
			}
		}

		return null;
	}
}
