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
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.co.apexsoft.modellipse.customization.diagram.sequence.commands.ApexCustomZOrderCommand;
import kr.co.apexsoft.modellipse.customization.diagram.sequence.commands.ApexPreserveAnchorsPositionCommand;
import kr.co.apexsoft.modellipse.customization.diagram.sequence.interfaces.IApexLifelineEditPart;
import kr.co.apexsoft.modellipse.customization.diagram.sequence.util.ApexSequenceRequestConstants;
import kr.co.apexsoft.modellipse.customization.diagram.sequence.util.ApexSequenceUtil;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.draw2d.AbstractPointListShape;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gef.requests.ReconnectRequest;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.commands.SetBoundsCommand;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IBorderItemEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.XYLayoutEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.figures.BorderedNodeFigure;
import org.eclipse.gmf.runtime.diagram.ui.l10n.DiagramUIMessages;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;
import org.eclipse.gmf.runtime.emf.core.util.EObjectAdapter;
import org.eclipse.gmf.runtime.notation.IdentityAnchor;
import org.eclipse.gmf.runtime.notation.NotationFactory;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.gmf.runtime.notation.impl.ConnectorImpl;
import org.eclipse.papyrus.uml.diagram.common.commands.PreserveAnchorsPositionCommand;
import org.eclipse.papyrus.uml.diagram.common.draw2d.LifelineDotLineFigure;
import org.eclipse.papyrus.uml.diagram.common.helper.InteractionFragmentHelper;
import org.eclipse.papyrus.uml.diagram.common.util.DiagramEditPartsUtil;
import org.eclipse.uml2.common.util.CacheAdapter;
import org.eclipse.uml2.uml.ActionExecutionSpecification;
import org.eclipse.uml2.uml.BehaviorExecutionSpecification;
import org.eclipse.uml2.uml.CombinedFragment;
import org.eclipse.uml2.uml.Continuation;
import org.eclipse.uml2.uml.DestructionOccurrenceSpecification;
import org.eclipse.uml2.uml.DurationConstraint;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.ExecutionOccurrenceSpecification;
import org.eclipse.uml2.uml.ExecutionSpecification;
import org.eclipse.uml2.uml.GeneralOrdering;
import org.eclipse.uml2.uml.Interaction;
import org.eclipse.uml2.uml.InteractionFragment;
import org.eclipse.uml2.uml.InteractionOperand;
import org.eclipse.uml2.uml.InteractionOperatorKind;
import org.eclipse.uml2.uml.InteractionUse;
import org.eclipse.uml2.uml.Lifeline;
import org.eclipse.uml2.uml.Message;
import org.eclipse.uml2.uml.MessageEnd;
import org.eclipse.uml2.uml.MessageOccurrenceSpecification;
import org.eclipse.uml2.uml.MessageSort;
import org.eclipse.uml2.uml.Observation;
import org.eclipse.uml2.uml.OccurrenceSpecification;
import org.eclipse.uml2.uml.StateInvariant;
import org.eclipse.uml2.uml.TimeConstraint;
import org.eclipse.uml2.uml.TimeObservation;

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
	public static Command getResizeOrMoveChildrenCommand(IApexLifelineEditPart lifelineEP, ChangeBoundsRequest request, boolean isMove, boolean updateEnclosingInteraction, boolean useFixedXPos) {
		List<EditPart> editParts = request.getEditParts();

		if(editParts != null) {
			CompoundCommand compoundCmd = new CompoundCommand();
			compoundCmd.setLabel("Move or resize");
			compoundCmd.setDebugLabel("Debug: Move or resize of an ExecutionSpecification");

			for(EditPart ep : editParts) {
				
				if ( ep instanceof IGraphicalEditPart ) {
					EObject eObj = ((IGraphicalEditPart) ep).resolveSemanticElement();
					
					if ( eObj instanceof ExecutionSpecification ) {
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
						ShapeNodeEditPart parentBar = getParent(newBounds, executionSpecificationList);
						
						// change bounds to relative
						newBounds = getExecutionSpecificationNewBounds(isMove, lifelineEP, oldBounds, newBounds, notToCheckExecutionSpecificationList, useFixedXPos);
						if(newBounds == null) {
							return UnexecutableCommand.INSTANCE;
						}

						if(parentBar != null){
							
							/* apex improved start */
							if ( lifelineEP instanceof IGraphicalEditPart ) {
								EObject eObject = ((IGraphicalEditPart) lifelineEP).resolveSemanticElement();
								
								if ( eObject instanceof Lifeline ) {
									compoundCmd.add(resizeParentExecutionSpecification(lifelineEP, parentBar, newBounds.getCopy(), executionSpecificationList));		
								}
							}							
							/* apex improved end */
							/* apex replaced
							compoundCmd.add(resizeParentExecutionSpecification(lifelineEP, parentBar, newBounds.getCopy(), executionSpecificationList));
							*/
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
							Object data = request.getExtendedData().get(ApexSequenceRequestConstants.DO_NOT_MOVE_EDIT_PARTS);
							if (!Boolean.TRUE.equals(data)) {
								compoundCmd.add(apexCreateMovingAffixedExecutionSpecificationCommand(executionSpecificationEP, realMoveDelta, newBounds));
								compoundCmd.add(createZOrderCommand(lifelineEP, executionSpecificationEP, newBounds, notToCheckExecutionSpecificationList));
							}
						}
						/* apex added end */

						// Move also linked Time elements
						compoundCmd = completeMoveExecutionSpecificationCommand(compoundCmd, executionSpecificationEP, newBounds, request);

						IFigure parentFigure = executionSpecificationEP.getFigure().getParent();
						parentFigure.translateToAbsolute(newBounds);
						// translateToAbsolute only does half of the work, I don't know why
						newBounds.translate(parentFigure.getBounds().getLocation());

						if(updateEnclosingInteraction) {
							// update the enclosing interaction of a moved execution specification
							compoundCmd.add(createUpdateEnclosingInteractionCommand(executionSpecificationEP, request.getMoveDelta(), newSizeDelta));
						}

						// keep absolute position of anchors
						/* apex improved start */
						Object relative = request.getExtendedData().get(ApexSequenceRequestConstants.PRESERVE_ANCHOR_RELATIVE_BOUNDS);
						ApexPreserveAnchorsPositionCommand command = new ApexPreserveAnchorsPositionCommand(executionSpecificationEP, new Dimension(realMoveDelta.width, realMoveDelta.height),
								PreserveAnchorsPositionCommand.PRESERVE_Y, executionSpecificationEP.getFigure(), request.getResizeDirection(), relative);
						compoundCmd.add(new ICommandProxy(command));
						/* apex improved end */
						/* apex replaced
						compoundCmd.add(new ICommandProxy(new LifelineEditPart.PreserveAnchorsPositionCommandEx(executionSpecificationEP, new Dimension(realMoveDelta.width, realMoveDelta.height), PreserveAnchorsPositionCommand.PRESERVE_Y, executionSpecificationEP.getFigure(), request.getResizeDirection())));
						*/
					}
					
					// 아래는 원래 CombinedFragment2EditPart 관련 내용이나 CombinedFragment로 처리
					if ( eObj instanceof CombinedFragment ) {
						ShapeNodeEditPart cf2EP = (ShapeNodeEditPart)ep;
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
					if ( eObj instanceof StateInvariant ) {
						ShapeNodeEditPart siEP = (ShapeNodeEditPart)ep;
						IFigure siFigure = siEP.getFigure();
						Rectangle newBounds = siFigure.getBounds().getCopy();

						Point moveDelta = request.getMoveDelta();
						if(moveDelta != null) {
							newBounds.translate(moveDelta);
							newBounds.y -= 10;
						}
						
						// Create and add the set bounds command to the compound command
						SetBoundsCommand setBoundsCmd = new SetBoundsCommand(siEP.getEditingDomain(), "Apex Move of a StateInvariant", siEP, newBounds);
						compoundCmd.add(new ICommandProxy(setBoundsCmd));
						
						ApexInteractionCompartmentXYLayoutEditPolicy.apexMoveBelowItems(request, siEP, compoundCmd);
					}
					
					if ( eObj instanceof DestructionOccurrenceSpecification ) {
						ShapeNodeEditPart doEP = (ShapeNodeEditPart)ep;
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

				
			}

			if(!compoundCmd.isEmpty()) {
				return compoundCmd;
			}
		}

		return null;
	}
	
	/**
	 * Used to modify the sizeDelta if the given value is higher/lower than the highest/lowest
	 * allowed values of the figure.
	 * 
	 * @param figure
	 *        the figure
	 * @param sizeDelta
	 *        the size delta
	 * 
	 * @return a corrected sizeDelta
	 */
	protected final static Dimension adaptSizeDeltaToMaxHeight(IFigure figure, Dimension sizeDelta) {
		Dimension newSizeDelta = new Dimension(sizeDelta);

		int figureHeight = figure.getBounds().height;
		int maximunFigureHeight = figure.getMaximumSize().height;
		int minimunFigureHeight = figure.getMinimumSize().height;

		int height = figureHeight + newSizeDelta.height;
		if(height > maximunFigureHeight) {
			newSizeDelta.height = maximunFigureHeight - figureHeight;
		} else if(height < minimunFigureHeight) {
			newSizeDelta.height = minimunFigureHeight - figureHeight;
		}

		return newSizeDelta;
	}
	
	/**
	 * Returns all the ExecutionSpecification EditParts that are affixed to the right side of the
	 * given ExecutionSpecification EditPart. Not only the ones directly affixed to the
	 * executionSpecificationEP are returned, but the ones that are indirectly affixed as well (this
	 * is done recursively)
	 * 
	 * @param executionSpecificationEP
	 *        the execution specification ep
	 * 
	 * @return the list of affixed ExecutionSpecification. If there is no affixed
	 *         ExecutionSpecification, then an empty list will be returned
	 */
	protected final static List<ShapeNodeEditPart> getAffixedExecutionSpecificationEditParts(ShapeNodeEditPart executionSpecificationEP) {
		List<ShapeNodeEditPart> notToCheckExecutionSpecificationList = new ArrayList<ShapeNodeEditPart>();
		return getAffixedExecutionSpecificationEditParts(executionSpecificationEP, notToCheckExecutionSpecificationList);

	}

	/**
	 * Operation used by the above operation. It's main goal is to obtain, recursively, all the
	 * affixed ExecutionSpecification. In order to do so, it is needed a ExecutionSpecification
	 * EditPart and the notToCheckList.
	 * 
	 * @param executionSpecificationEP
	 *        the execution specification ep
	 * @param notToCheckExecutionSpecificationList
	 *        the not to check ExecutionSpecification list
	 * 
	 * @return the list of affixed ExecutionSpecification. If there is no affixed
	 *         ExecutionSpecification, then an empty list will be returned
	 */
	protected final static List<ShapeNodeEditPart> getAffixedExecutionSpecificationEditParts(ShapeNodeEditPart executionSpecificationEP, List<ShapeNodeEditPart> notToCheckExecutionSpecificationList) {
		// Add itself to the notToCheck list
		List<ShapeNodeEditPart> newNotToCheckExecutionSpecificationList = new ArrayList<ShapeNodeEditPart>(notToCheckExecutionSpecificationList);
		newNotToCheckExecutionSpecificationList.add(executionSpecificationEP);

		// LifelineEditPart where the ExecutionSpecification EditPart is contained
		IApexLifelineEditPart lifelineEP = (IApexLifelineEditPart)executionSpecificationEP.getParent();

		// ExecutionSpecification EditParts list
		List<ShapeNodeEditPart> executionSpecificationList = lifelineEP.getChildShapeNodeEditPart();
		executionSpecificationList.remove(newNotToCheckExecutionSpecificationList);

		// List to store the Affixed ExecutionSpecification
		List<ShapeNodeEditPart> affixedExecutionSpecificationList = new ArrayList<ShapeNodeEditPart>();

		// Loop ExecutionSpecificationough the ExecutionSpecification list
		for(ShapeNodeEditPart childExecutionSpecificationEP : executionSpecificationList) {
			if(isAffixedToRight(executionSpecificationEP.getFigure().getBounds(), childExecutionSpecificationEP.getFigure().getBounds())) {
				affixedExecutionSpecificationList.add(childExecutionSpecificationEP);
				// Add also it's affixed ExecutionSpecification
				affixedExecutionSpecificationList.addAll(getAffixedExecutionSpecificationEditParts(childExecutionSpecificationEP, newNotToCheckExecutionSpecificationList));
			}
		}

		// To the ExecutionSpecification list
		return affixedExecutionSpecificationList;
	}
	
	/**
	 * Checks whether the right EditPart is affixed to the left EditPart. In order to do so, the
	 * operation checks if the right figure is really on the right and, if so, it just returns true
	 * if figures touch each other.
	 * 
	 * @param leftFigure
	 *        The left rectangle
	 * @param rightFigure
	 *        The right rectangle
	 * 
	 * @return true if the rectangles of both figures touch and the right figure is really on the
	 *         right. False otherwise
	 */
	protected final static boolean isAffixedToRight(Rectangle leftFigure, Rectangle rightFigure) {
		return leftFigure.touches(rightFigure) && leftFigure.x < rightFigure.x;
	}
	
	/**
	 * Get the (futur) parent of a ExecutionSpecification
	 * @param lifelinePart 
	 * 
	 * @param childBounds
	 *        the child bounds
	 * @param toCheckExecutionSpecificationList
	 *        List of EditPart to check
	 * @return The parent
	 */
	protected final static ShapeNodeEditPart getParent(Rectangle childBounds, List<ShapeNodeEditPart> toCheckExecutionSpecificationList) {
		ShapeNodeEditPart parent = null;
		// Loop through the ExecutionSpecification list and try to find the most to the right
		// ExecutionSpecification within the executionSpecificationEP Y-axis bounds
		Rectangle externalBounds = childBounds.getCopy();
		for(ShapeNodeEditPart externalExecutionSpecificationEP : toCheckExecutionSpecificationList) {
			Rectangle externalExecutionSpecificationBounds = externalExecutionSpecificationEP.getFigure().getBounds();
			externalBounds.x = externalExecutionSpecificationBounds.x;
			externalBounds.width = externalExecutionSpecificationBounds.width;
			
			/* apex improved start */
			if (externalExecutionSpecificationBounds.touches(externalBounds)					
				&& externalExecutionSpecificationBounds.y < externalBounds.y) {  // start 위치가 더 하단일 경우에만 우측으로 이동
					
				if(parent == null || externalExecutionSpecificationBounds.x > parent.getFigure().getBounds().x) {
					parent = externalExecutionSpecificationEP;
				}
			}
			/* apex improved start */
			/* apex replaced
			if(externalExecutionSpecificationBounds.touches(externalBounds) && externalExecutionSpecificationBounds.x < childBounds.x) {
				if(parent == null || externalExecutionSpecificationBounds.x > parent.getFigure().getBounds().x) {
					parent = externalExecutionSpecificationEP;
				}
			}
			*/
		}
		return parent;
	}
	
	/**
	 * apex updated
	 * 
	 * Useful operation to know where the figure of a ExecutionSpecification EditPart should be
	 * positioned within a Lifeline EditPart. The notToCheckList is needed to avoid checking those
	 * ExecutionSpecification EditParts. The returned bounds are relative to the Lifeline figure so
	 * they can be used, directly, within a SetBoundsCommand.
	 * 
	 * @param lifelineEP
	 *        the lifeline ep
	 * @param oldBounds
	 *        The old bounds of the ES
	 * @param newBounds
	 *        The new initial bounds
	 * @param notToCheckExecutionSpecificationList
	 *        The ExecutionSpecification EditPart's List that won't be checked
	 * 
	 * @return The new bounds of the executionSpecificationEP figure
	 */
	protected final static Rectangle getExecutionSpecificationNewBounds(boolean isMove, IApexLifelineEditPart lifelineEP, Rectangle oldBounds, Rectangle newBounds, List<ShapeNodeEditPart> notToCheckExecutionSpecificationList, boolean useFixedXPos) {
		// Lifeline's figure where the child is drawn
		Rectangle dotLineBounds = lifelineEP.getPrimaryShape().getFigureLifelineDotLineFigure().getBounds();

		// if ExecutionSpecification is resize outside of the lifeline bounds
		if(newBounds.y <= dotLineBounds.y || newBounds.x < dotLineBounds.x || newBounds.x > dotLineBounds.right()) {
			return null;
		}

		List<ShapeNodeEditPart> toCheckExecutionSpecificationList = lifelineEP.getChildShapeNodeEditPart();
		toCheckExecutionSpecificationList.removeAll(notToCheckExecutionSpecificationList);

		if(isMove) {
			ShapeNodeEditPart parent = getParent(newBounds, toCheckExecutionSpecificationList);

			if(useFixedXPos) {
				newBounds.x = oldBounds.x;
			} else if(parent == null) {
				// No mother, center position
				int width = newBounds.width > 0 ? newBounds.width : EXECUTION_INIT_WIDTH;
				newBounds.x = dotLineBounds.x + dotLineBounds.width / 2 - width / 2;
			} else {
				Rectangle parentBounds = parent.getFigure().getBounds();
				int width = parentBounds.width > 0 ? parentBounds.width : EXECUTION_INIT_WIDTH;
				newBounds.x = parentBounds.x + width / 2 + 1;
			}
		} else {
			ShapeNodeEditPart oldParent = getParent(oldBounds, toCheckExecutionSpecificationList);
			// forbid resize if the new bounds exceed Y-wise the bounds of a non-parent ES
			for(ShapeNodeEditPart esPart : toCheckExecutionSpecificationList) {
				Rectangle esBounds = esPart.getFigure().getBounds();
				int esYBottom = esBounds.y + esBounds.height;
				if(esPart != oldParent) {
					/* apex improved start */
					// 하단에 ExecutionSpecification이 있으면 더 이상 확장이 불가능
					/* apex improved end */
					/* apex replaced					 
					if(((oldBounds.y + oldBounds.height) <= esBounds.y && (newBounds.y + newBounds.height) >= esBounds.y) || (oldBounds.y >= esYBottom && newBounds.y <= esYBottom)) {
						return null;
					}
					// */
				}
			}
		}

		// Change to relative bounds of the LifelineEP
		newBounds.x -= dotLineBounds.x;
		newBounds.y -= dotLineBounds.y;

		return newBounds;
	}
	
	private static Command resizeParentExecutionSpecification(IApexLifelineEditPart lifelinePart, ShapeNodeEditPart part, Rectangle childBounds, List<ShapeNodeEditPart> list) {
		Rectangle bounds = getRelativeBounds(part.getFigure());
		
		childBounds.x = bounds.x;
		childBounds.width = bounds.width;
		if(bounds.contains(childBounds))
			return null; 
		bounds.union(childBounds);
		Command c = new ICommandProxy(new SetBoundsCommand(part.getEditingDomain(), "Resize of Parent Bar", part, bounds.getCopy()));
		
		list.remove(part);
		ShapeNodeEditPart parent = getParent(part.getFigure().getBounds(), list);
		if(parent == null)
			return c;
		
		return c.chain(resizeParentExecutionSpecification(lifelinePart, parent, bounds.getCopy(), list));
	}
	
	/**
	 * It returns the relative bounds of an Figure.
	 * 
	 * @param figure
	 *        The Figure
	 * 
	 * @return The relative bounds regarding it's parent figure
	 */
	protected final static Rectangle getRelativeBounds(IFigure figure) {
		Rectangle relBounds = figure.getBounds().getCopy();
		Rectangle parentRectangle = figure.getParent().getBounds();
		relBounds.x -= parentRectangle.x;
		relBounds.y -= parentRectangle.y;
		return relBounds;
	}
	
	/**
	 * Given an AbstractGraphialEditPart and the new relative bounds that the EditPart will have, it
	 * returns the real delta applied to the movement.
	 * 
	 * @param oldRelativeBounds
	 *        The old position of the mentioned EditPart
	 * @param newRelativeBounds
	 *        The new position of the mentioned EditPart
	 * 
	 * @return The real MoveDelta applied
	 */
	protected final static Rectangle getRealMoveDelta(Rectangle oldRelativeBounds, Rectangle newRelativeBounds) {
		Rectangle realMoveDelta = new Rectangle();
		realMoveDelta.x = newRelativeBounds.x - oldRelativeBounds.x;
		realMoveDelta.y = newRelativeBounds.y - oldRelativeBounds.y;
		realMoveDelta.height = newRelativeBounds.height - oldRelativeBounds.height;
		realMoveDelta.width = newRelativeBounds.width - oldRelativeBounds.width;
		return realMoveDelta;
	}
	
	/**
	 * If a ExecutionSpecification EditPart is going to be moved according to a moveDelta, this
	 * operation returns a compoundCommand that also moves the affixed ExecutionSpecification
	 * according to that delta.
	 * 
	 * @param executionSpecificationEP
	 *        The ExecutionSpecification EditPart that is going to be moved
	 * @param moveDelta
	 *        The moveDelta of the previous EditPart
	 * @param newBounds
	 *        the new bounds
	 * 
	 * @return the compound command
	 */
	protected final static CompoundCommand createMovingAffixedExecutionSpecificationCommand(ShapeNodeEditPart executionSpecificationEP, Rectangle moveDelta, Rectangle newBounds) {
		if(moveDelta.y != 0 || moveDelta.height != 0) {
			CompoundCommand compoundCmd = new CompoundCommand();
			for(ShapeNodeEditPart childExecutionSpecificationEP : getAffixedExecutionSpecificationEditParts(executionSpecificationEP)) {
				// Get Relative Bounds
				Rectangle childBounds = getRelativeBounds(childExecutionSpecificationEP.getFigure());
				// Apply delta
				childBounds.y += moveDelta.y;
				childBounds.x += moveDelta.x;

				// Create the child's SetBoundsCommand
				SetBoundsCommand childSetBoundsCmd = new SetBoundsCommand(executionSpecificationEP.getEditingDomain(), "Movement of affixed ExecutionSpecification", childExecutionSpecificationEP, childBounds);
				compoundCmd.add(new ICommandProxy(childSetBoundsCmd));


				IFigure parentFigure = childExecutionSpecificationEP.getFigure().getParent();
				parentFigure.translateToAbsolute(newBounds);
				// translateToAbsolute only does half of the work, I don't know why
				newBounds.translate(parentFigure.getBounds().getLocation());

				// change the enclosing interaction of the moved affixed child if necessary
				compoundCmd.add(createUpdateEnclosingInteractionCommand(childExecutionSpecificationEP, moveDelta.getLocation(), moveDelta.getSize()));

				// Move it's children as well
				if(!getAffixedExecutionSpecificationEditParts(childExecutionSpecificationEP).isEmpty()) {
					compoundCmd.add(createMovingAffixedExecutionSpecificationCommand(childExecutionSpecificationEP, moveDelta, childBounds));
				}
			}
			if(!compoundCmd.isEmpty()) {
				return compoundCmd;
			}
		}
		return null;
	}
	
	/**
	 * Command for change ZOrder of ExecutionSpecification ordered from parent to children.
	 * 
	 * @param lifelineEP
	 *        the lifeline ep
	 * @param executionSpecificationEP
	 *        the execution specification ep
	 * @param newBounds
	 *        the new bounds
	 * @param notToCheckExecutionSpecificationList
	 *        the not to check bes list
	 * 
	 * @return the command
	 */
	protected final static Command createZOrderCommand(IApexLifelineEditPart lifelineEP, ShapeNodeEditPart executionSpecificationEP, Rectangle newBounds, List<ShapeNodeEditPart> notToCheckExecutionSpecificationList) {
		List<ShapeNodeEditPart> toCheckExecutionSpecificationList = lifelineEP.getChildShapeNodeEditPart();
		toCheckExecutionSpecificationList.removeAll(notToCheckExecutionSpecificationList);
		CompoundCommand cmd = new CompoundCommand();
		for(ShapeNodeEditPart externalExecutionSpecificationEP : toCheckExecutionSpecificationList) {
			Rectangle externalExecutionSpecificationBounds = getRelativeBounds(externalExecutionSpecificationEP.getFigure());
			// Check if there is any contact
			if(externalExecutionSpecificationBounds.touches(newBounds)) {
				View containerView = ViewUtil.getContainerView(executionSpecificationEP.getPrimaryView());
				if(containerView != null) {
					int i = 0;
					int parentIndex = -1;
					int childIndex = -1;
					for(Object child : containerView.getChildren()) {
						if(child == externalExecutionSpecificationEP.getPrimaryView()) {
							parentIndex = i;
						} else if(child == executionSpecificationEP.getPrimaryView()) {
							childIndex = i;
						}
						if(parentIndex != -1 && childIndex != -1) {
							if(childIndex > parentIndex) {
								cmd.add(new ICommandProxy(new ApexCustomZOrderCommand(executionSpecificationEP.getEditingDomain(), executionSpecificationEP.getPrimaryView(), parentIndex)));
								cmd.add(new ICommandProxy(new ApexCustomZOrderCommand(externalExecutionSpecificationEP.getEditingDomain(), externalExecutionSpecificationEP.getPrimaryView(), childIndex)));
							} else {
								break;
							}
						}
						i++;
					}
				}
			}
		}

		if(!cmd.isEmpty()) {
			return cmd;
		}
		return null;
	}
	
	/**
	 * If a ExecutionSpecification EditPart is going to be moved according to a moveDelta, this
	 * operation returns a compoundCommand that also moves the affixed ExecutionSpecification
	 * according to that delta.
	 * 
	 * @param executionSpecificationEP
	 *        The ExecutionSpecification EditPart that is going to be moved
	 * @param moveDelta
	 *        The moveDelta of the previous EditPart
	 * @param newBounds
	 *        the new bounds
	 * 
	 * @return the compound command
	 */
	protected final static CompoundCommand apexCreateMovingAffixedExecutionSpecificationCommand(ShapeNodeEditPart executionSpecificationEP, Rectangle moveDelta, Rectangle newBounds) {
//		if(moveDelta.y != 0 || moveDelta.height != 0) {
			CompoundCommand compoundCmd = new CompoundCommand();
			
			List<ShapeNodeEditPart> newAffixedExecutionSpecificationEditParts = apexGetAffixedExecutionSpecificationEditParts(executionSpecificationEP, newBounds);
			for(ShapeNodeEditPart childExecutionSpecificationEP : newAffixedExecutionSpecificationEditParts) {
				// Get Relative Bounds
				Rectangle childBounds = getRelativeBounds(childExecutionSpecificationEP.getFigure());
				Rectangle childNewBounds = childBounds.getCopy();
				// Apply delta
				childNewBounds.x = newBounds.x + newBounds.width / 2 + 1;

				// Create the child's SetBoundsCommand
				SetBoundsCommand childSetBoundsCmd = new SetBoundsCommand(executionSpecificationEP.getEditingDomain(), "Movement of affixed ExecutionSpecification", childExecutionSpecificationEP, childNewBounds);
				compoundCmd.add(new ICommandProxy(childSetBoundsCmd));

				// change the enclosing interaction of the moved affixed child if necessary
				compoundCmd.add(createUpdateEnclosingInteractionCommand(childExecutionSpecificationEP, moveDelta.getLocation(), moveDelta.getSize()));

				// Move it's children as well
//				if(!apexGetAffixedExecutionSpecificationEditParts(childExecutionSpecificationEP, childNewBounds).isEmpty()) {
					Rectangle childMoveDelta = new Rectangle();
					childMoveDelta.x = childNewBounds.x - childBounds.x;
					compoundCmd.add(apexCreateMovingAffixedExecutionSpecificationCommand(childExecutionSpecificationEP, childMoveDelta, childNewBounds));
//				}
			}
			
			IApexLifelineEditPart lifelineEP = (IApexLifelineEditPart)executionSpecificationEP.getParent();
			Rectangle dotLineBounds = lifelineEP.getPrimaryShape().getFigureLifelineDotLineFigure().getBounds();
			int width = newBounds.width > 0 ? newBounds.width : EXECUTION_INIT_WIDTH;
			
			Rectangle oldBounds = getRelativeBounds(executionSpecificationEP.getFigure());
			
			List<ShapeNodeEditPart> oldAffixedExecutionSpecificationEditParts = apexGetAffixedExecutionSpecificationEditParts(executionSpecificationEP, oldBounds);
			oldAffixedExecutionSpecificationEditParts.removeAll(newAffixedExecutionSpecificationEditParts);
			for (ShapeNodeEditPart childExecutionSpecificationEP : oldAffixedExecutionSpecificationEditParts) {
				// Get Relative Bounds
				Rectangle oldChildBounds = getRelativeBounds(childExecutionSpecificationEP.getFigure());
				Rectangle newChildBounds = oldChildBounds.getCopy();
				
				if (moveDelta.x != 0) {
					newChildBounds.x = oldChildBounds.x + moveDelta.x ;
				}
				else {
					newChildBounds.x = dotLineBounds.x + dotLineBounds.width / 2 - width / 2;
					newChildBounds.x -= dotLineBounds.x;
				}
				
				// Create the child's SetBoundsCommand
				SetBoundsCommand childSetBoundsCmd = new SetBoundsCommand(executionSpecificationEP.getEditingDomain(), "Movement of affixed ExecutionSpecification", childExecutionSpecificationEP, newChildBounds);
				compoundCmd.add(new ICommandProxy(childSetBoundsCmd));
				
				Rectangle childMoveDelta = getRealMoveDelta(oldChildBounds, newChildBounds);
				
				compoundCmd.add(apexCreateMovingAffixedExecutionSpecificationCommand(childExecutionSpecificationEP, childMoveDelta, newChildBounds));
			}
			if(!compoundCmd.isEmpty()) {
				return compoundCmd;
			}
//		}
		return null;
	}
	
	/**
	 * Complete a command to move time/duration constraints/observation which are linked to the moved edit part
	 * 
	 * @param compoundCmd
	 *        existing command to complete
	 * @param executionSpecificationEP
	 *        the moved edit part representing an execution specification
	 * @param newBounds
	 *        the new part's bounds (relative to the figure's parent)
	 * @param request
	 *        the change bounds request which originated this move
	 * @return the updated parameter compoundCmd for convenience
	 */
	public static CompoundCommand completeMoveExecutionSpecificationCommand(CompoundCommand compoundCmd, ShapeNodeEditPart executionSpecificationEP, Rectangle newBounds, ChangeBoundsRequest request) {
		if(chainEffectIsDisabled(request)) {
			return compoundCmd;
		}
		// Move events delimiting the ExecutionSpecification
		EObject execSpec = executionSpecificationEP.resolveSemanticElement();
		if(execSpec instanceof ExecutionSpecification) {
			ShapeNodeEditPart lifelinePart = ApexSequenceUtil.getParentLifelinePart(executionSpecificationEP);
			// first, get absolute bounds
			newBounds = newBounds.getCopy();
			IFigure parentFig = executionSpecificationEP.getFigure().getParent();
			parentFig.translateToAbsolute(newBounds);
			newBounds.translate(parentFig.getBounds().getLocation());

			// move start and finish events
			OccurrenceSpecification start = ((ExecutionSpecification)execSpec).getStart();
			int startY = newBounds.getTop().y;
			OccurrenceSpecification finish = ((ExecutionSpecification)execSpec).getFinish();
			int finishY = newBounds.getBottom().y;
			List<EditPart> notToMoveEditParts = new ArrayList<EditPart>(1);
			notToMoveEditParts.add(executionSpecificationEP);
			Command cmd = getMoveOccurrenceSpecificationsCommand(start, finish, startY, finishY, lifelinePart, notToMoveEditParts);
			if(cmd != null) {
				compoundCmd.add(cmd);
			}

			if(request.getSizeDelta().height == 0) {
				// move time elements for events between start and finish
				InteractionFragment nextOccSpec = InteractionFragmentHelper.findNextFragment(start, start.eContainer());
				while(nextOccSpec != null && nextOccSpec != finish) {
					Point occSpecLocation = findLocationOfEvent(lifelinePart, nextOccSpec);
					if(nextOccSpec instanceof OccurrenceSpecification && occSpecLocation != null) {
						int occSpecY = occSpecLocation.y + request.getMoveDelta().y;
						cmd = getMoveTimeElementsCommand((OccurrenceSpecification)nextOccSpec, null, occSpecY, -1, lifelinePart, notToMoveEditParts);
						if(cmd != null) {
							compoundCmd.add(cmd);
						}
					}
					nextOccSpec = InteractionFragmentHelper.findNextFragment(nextOccSpec, start.eContainer());
				}
			}
		}
		return compoundCmd;
	}
	
	/**
	 * Check if moving chain effect has been disabled to avoid an infinite loop.
	 * 
	 * @param request
	 *        the request wich initiated the move
	 * @return true if no additional command must be performed.
	 */
	private static boolean chainEffectIsDisabled(Request request) {
		Object doNotToMoveEditParts = request.getExtendedData().get(ApexSequenceRequestConstants.DO_NOT_MOVE_EDIT_PARTS);
		return Boolean.TRUE.equals(doNotToMoveEditParts);
	}
	
	private static Rectangle getNewBoundsForCoRegion(IApexLifelineEditPart lifelineEP, Rectangle bounds) {
		Rectangle newBounds = bounds.getCopy();

		// Get the dotline figure
		LifelineDotLineFigure figureLifelineDotLineFigure = lifelineEP.getPrimaryShape().getFigureLifelineDotLineFigure();

		// Translate the absolute location to relative
		figureLifelineDotLineFigure.translateToRelative(newBounds);
		newBounds.translate(figureLifelineDotLineFigure.getBounds().getLocation().getCopy().negate());

		Rectangle dotLineFigureBounds = figureLifelineDotLineFigure.getBounds();

		newBounds.x = dotLineFigureBounds.width / 2 - COREGION_INIT_WIDTH / 2;
		newBounds.width = COREGION_INIT_WIDTH;

		return newBounds;
	}
	
	/**
	 * Returns all the ExecutionSpecification EditParts that are affixed to the right side of the
	 * given ExecutionSpecification EditPart. Not only the ones directly affixed to the
	 * executionSpecificationEP are returned, but the ones that are indirectly affixed as well (this
	 * is done recursively)
	 * 
	 * @param executionSpecificationEP
	 *        the execution specification ep
	 * @param newBounds
	 *        new bounds of executionSpecificationEP
	 * 
	 * @return the list of affixed ExecutionSpecification. If there is no affixed
	 *         ExecutionSpecification, then an empty list will be returned
	 */
	protected final static List<ShapeNodeEditPart> apexGetAffixedExecutionSpecificationEditParts(ShapeNodeEditPart executionSpecificationEP, Rectangle newBounds) {
		List<ShapeNodeEditPart> notToCheckExecutionSpecificationList = new ArrayList<ShapeNodeEditPart>();
		return apexGetAffixedExecutionSpecificationEditParts(executionSpecificationEP, newBounds, notToCheckExecutionSpecificationList);

	}
	
	/**
	 * Operation used by the above operation. It's main goal is to obtain, recursively, all the
	 * affixed ExecutionSpecification. In order to do so, it is needed a ExecutionSpecification
	 * EditPart and the notToCheckList.
	 * 
	 * @param executionSpecificationEP
	 *        the execution specification ep
	 * @param newBounds
	 *        new bounds of executionSpecificationEP
	 * @param notToCheckExecutionSpecificationList
	 *        the not to check ExecutionSpecification list
	 * 
	 * @return the list of affixed ExecutionSpecification. If there is no affixed
	 *         ExecutionSpecification, then an empty list will be returned
	 */
	protected final static List<ShapeNodeEditPart> apexGetAffixedExecutionSpecificationEditParts(ShapeNodeEditPart executionSpecificationEP, Rectangle newBounds, List<ShapeNodeEditPart> notToCheckExecutionSpecificationList) {
		if (newBounds == null) {
			return getAffixedExecutionSpecificationEditParts(executionSpecificationEP, notToCheckExecutionSpecificationList);
		}
		
		// Add itself to the notToCheck list
		List<ShapeNodeEditPart> newNotToCheckExecutionSpecificationList = new ArrayList<ShapeNodeEditPart>(notToCheckExecutionSpecificationList);
		newNotToCheckExecutionSpecificationList.add(executionSpecificationEP);

		// LifelineEditPart where the ExecutionSpecification EditPart is contained
		IApexLifelineEditPart lifelineEP = (IApexLifelineEditPart)executionSpecificationEP.getParent();

		// ExecutionSpecification EditParts list
		List<ShapeNodeEditPart> executionSpecificationList = lifelineEP.getChildShapeNodeEditPart();
		executionSpecificationList.removeAll(newNotToCheckExecutionSpecificationList);

		// List to store the Affixed ExecutionSpecification
		List<ShapeNodeEditPart> affixedExecutionSpecificationList = new ArrayList<ShapeNodeEditPart>();

		// Loop ExecutionSpecificationough the ExecutionSpecification list
		for(ShapeNodeEditPart childExecutionSpecificationEP : executionSpecificationList) {
			Rectangle childBounds = getRelativeBounds(childExecutionSpecificationEP.getFigure());
			if(newBounds.touches(childBounds) && newBounds.y < childBounds.y) {
				affixedExecutionSpecificationList.add(childExecutionSpecificationEP);
			}
		}

		// To the ExecutionSpecification list
		return affixedExecutionSpecificationList;
	}
	
	/**
	 * apex updated
	 * 
	 * Find the location on the lifeline of an interaction fragment
	 * 
	 * @param lifelineEditPart
	 *        the lifeline edit part
	 * @param fragment
	 *        the searched interaction fragment
	 * @return the absolute location or null if not found
	 */
	public static Point findLocationOfEvent(ShapeNodeEditPart lifelineEditPart, InteractionFragment fragment) {
		if(lifelineEditPart == null) {
			return null;
		}
		// Search for corresponding node edit part out of the lifeline.
		if(fragment instanceof CombinedFragment || fragment instanceof Continuation || fragment instanceof InteractionOperand || fragment instanceof InteractionUse || fragment instanceof Interaction) {
			List<View> views = DiagramEditPartsUtil.findViews(fragment, lifelineEditPart.getViewer());
			for(View view : views) {
				EditPart part = DiagramEditPartsUtil.getEditPartFromView(view, lifelineEditPart);
				
				/* apex improved start */
				if ( part instanceof IGraphicalEditPart ) {
					EObject eObj = ((IGraphicalEditPart) part).resolveSemanticElement();
					
					
					boolean isCombinedFragment = eObj instanceof CombinedFragment;
					boolean isContinuation = eObj instanceof Continuation;
					boolean isInteractionOperand = eObj instanceof InteractionOperand;
					boolean isInteractionUse = eObj instanceof InteractionUse;
					boolean isInteraction = eObj instanceof Interaction;
					if(isCombinedFragment || isContinuation || isInteractionOperand || isInteractionUse || isInteraction) {
						Rectangle bounds = ApexSequenceUtil.getAbsoluteBounds((IGraphicalEditPart)part);
						return bounds.getTop();
					}	
				}
				
				/* apex improved end */
				/* apex replaced
				boolean isCombinedFragment = part instanceof CombinedFragmentEditPart || part instanceof CombinedFragment2EditPart;
				boolean isContinuation = part instanceof ContinuationEditPart;
				boolean isInteractionOperand = part instanceof InteractionOperandEditPart;
				boolean isInteractionUse = part instanceof InteractionUseEditPart;
				boolean isInteraction = part instanceof InteractionEditPart;
				if(isCombinedFragment || isContinuation || isInteractionOperand || isInteractionUse || isInteraction) {
					Rectangle bounds = ApexSequenceUtil.getAbsoluteBounds((IGraphicalEditPart)part);
					return bounds.getTop();
				}
				*/
			}
		} else {
			// search on graphical children of the lifeline
			List<?> children = lifelineEditPart.getChildren();
			for(Object child : children) {
				
				/* apex improved start */
				if ( child instanceof IGraphicalEditPart ) {
					EObject eObj = ((IGraphicalEditPart) child).resolveSemanticElement();
					
					// check destruction event
					if(eObj instanceof DestructionOccurrenceSpecification) {
						EObject destructionOccurence = ((IGraphicalEditPart)child).resolveSemanticElement();
						EObject lifeline = lifelineEditPart.resolveSemanticElement();
						if(destructionOccurence instanceof DestructionOccurrenceSpecification && lifeline instanceof Lifeline && fragment instanceof DestructionOccurrenceSpecification) {
							DestructionOccurrenceSpecification destEvent = ((DestructionOccurrenceSpecification)fragment);
							if(destEvent != null && destEvent.equals(destructionOccurence)) {
								Rectangle bounds = ApexSequenceUtil.getAbsoluteBounds((IGraphicalEditPart)child);
								return bounds.getCenter();
							}
						}
					}
					// check in children executions
					if(eObj instanceof ActionExecutionSpecification || child instanceof BehaviorExecutionSpecification) {
						if(fragment instanceof ExecutionSpecification) {
							// check the execution
							EObject element = ((IGraphicalEditPart)child).resolveSemanticElement();
							if(element instanceof ExecutionSpecification) {
								if(fragment.equals(element)) {
									Rectangle bounds = ApexSequenceUtil.getAbsoluteBounds((IGraphicalEditPart)child);
									return bounds.getTop();
								}
							}
						} else if(fragment instanceof ExecutionOccurrenceSpecification) {
							// check start and finish events of the execution
							EObject element = ((IGraphicalEditPart)child).resolveSemanticElement();
							if(element instanceof ExecutionSpecification) {
								if(fragment.equals(((ExecutionSpecification)element).getStart())) {
									Rectangle bounds = ApexSequenceUtil.getAbsoluteBounds((IGraphicalEditPart)child);
									return bounds.getTop();
								} else if(fragment.equals(((ExecutionSpecification)element).getFinish())) {
									Rectangle bounds = ApexSequenceUtil.getAbsoluteBounds((IGraphicalEditPart)child);
									return bounds.getBottom();
								}
							}
						} else if(fragment instanceof MessageOccurrenceSpecification) {
							// check messages to and from the execution
							Point loc = findLocationOfMessageOccurrence((IGraphicalEditPart)child, (MessageOccurrenceSpecification)fragment);
							if(loc != null) {
								return loc;
							}
						}
					}
					// check in children StateInvariant
					if(eObj instanceof StateInvariant) {
						if(fragment instanceof StateInvariant) {
							// check the StateInvariant
							EObject element = ((IGraphicalEditPart)child).resolveSemanticElement();
							if(element instanceof StateInvariant) {
								if(fragment.equals(element)) {
									Rectangle bounds = ApexSequenceUtil.getAbsoluteBounds((IGraphicalEditPart)child);
									return bounds.getTop();
								}
							}
						} else if(fragment instanceof MessageOccurrenceSpecification) {
							// check messages to and from the execution
							Point loc = findLocationOfMessageOccurrence((GraphicalEditPart)child, (MessageOccurrenceSpecification)fragment);
							if(loc != null) {
								return loc;
							}
						}
					}					
				}			
				/* apex improved end */
				/* apex replaced
				// check destruction event
				if(child instanceof DestructionOccurrenceSpecificationEditPart) {
					EObject destructionOccurence = ((IGraphicalEditPart)child).resolveSemanticElement();
					EObject lifeline = lifelineEditPart.resolveSemanticElement();
					if(destructionOccurence instanceof DestructionOccurrenceSpecification && lifeline instanceof Lifeline && fragment instanceof DestructionOccurrenceSpecification) {
						DestructionOccurrenceSpecification destEvent = ((DestructionOccurrenceSpecification)fragment);
						if(destEvent != null && destEvent.equals(destructionOccurence)) {
							Rectangle bounds = ApexSequenceUtil.getAbsoluteBounds((IGraphicalEditPart)child);
							return bounds.getCenter();
						}
					}
				}
				// check in children executions
				if(child instanceof ActionExecutionSpecificationEditPart || child instanceof BehaviorExecutionSpecificationEditPart) {
					if(fragment instanceof ExecutionSpecification) {
						// check the execution
						EObject element = ((IGraphicalEditPart)child).resolveSemanticElement();
						if(element instanceof ExecutionSpecification) {
							if(fragment.equals(element)) {
								Rectangle bounds = ApexSequenceUtil.getAbsoluteBounds((IGraphicalEditPart)child);
								return bounds.getTop();
							}
						}
					} else if(fragment instanceof ExecutionOccurrenceSpecification) {
						// check start and finish events of the execution
						EObject element = ((IGraphicalEditPart)child).resolveSemanticElement();
						if(element instanceof ExecutionSpecification) {
							if(fragment.equals(((ExecutionSpecification)element).getStart())) {
								Rectangle bounds = ApexSequenceUtil.getAbsoluteBounds((IGraphicalEditPart)child);
								return bounds.getTop();
							} else if(fragment.equals(((ExecutionSpecification)element).getFinish())) {
								Rectangle bounds = ApexSequenceUtil.getAbsoluteBounds((IGraphicalEditPart)child);
								return bounds.getBottom();
							}
						}
					} else if(fragment instanceof MessageOccurrenceSpecification) {
						// check messages to and from the execution
						Point loc = findLocationOfMessageOccurrence((IGraphicalEditPart)child, (MessageOccurrenceSpecification)fragment);
						if(loc != null) {
							return loc;
						}
					}
				}
				// check in children StateInvariant
				if(child instanceof StateInvariantEditPart) {
					if(fragment instanceof StateInvariant) {
						// check the StateInvariant
						EObject element = ((IGraphicalEditPart)child).resolveSemanticElement();
						if(element instanceof StateInvariant) {
							if(fragment.equals(element)) {
								Rectangle bounds = ApexSequenceUtil.getAbsoluteBounds((IGraphicalEditPart)child);
								return bounds.getTop();
							}
						}
					} else if(fragment instanceof MessageOccurrenceSpecification) {
						// check messages to and from the execution
						Point loc = findLocationOfMessageOccurrence((GraphicalEditPart)child, (MessageOccurrenceSpecification)fragment);
						if(loc != null) {
							return loc;
						}
					}
				}				
				*/
			}
			if(fragment instanceof MessageOccurrenceSpecification) {
				// check messages to and from the lifeline
				Point loc = findLocationOfMessageOccurrence(lifelineEditPart, (MessageOccurrenceSpecification)fragment);
				if(loc != null) {
					return loc;
				}
			}
		}
		// If we found nothing, this may be a sync message receive
		if(fragment instanceof MessageOccurrenceSpecification) {
			boolean isSync = ((MessageOccurrenceSpecification)fragment).getMessage() != null && MessageSort.SYNCH_CALL_LITERAL.equals(((MessageOccurrenceSpecification)fragment).getMessage().getMessageSort());
			if(isSync) {
				// sync message should trigger an execution specification start. Find and return the corresponding start.
				EObject container = fragment.eContainer();
				EObject lifeline = lifelineEditPart.resolveSemanticElement();
				InteractionFragment nextFragment = InteractionFragmentHelper.findNextFragment(fragment, container);
				while(nextFragment != null && nextFragment.getCovereds().contains(lifeline)) {
					if(nextFragment.getCovereds().contains(lifeline)) {
						// Found next event of lifeline. Check if it really is a start.
						if(nextFragment instanceof ExecutionOccurrenceSpecification) {
							ExecutionSpecification exe = ((ExecutionOccurrenceSpecification)nextFragment).getExecution();
							if(exe != null && EcoreUtil.equals(exe.getStart(), nextFragment)) {
								// return location of the start.
								return findLocationOfEvent(lifelineEditPart, nextFragment);
							}
						}
						break;
					} else {
						nextFragment = InteractionFragmentHelper.findNextFragment(nextFragment, container);
					}
				}
			}
		}
		return null;
	}
	
	/**
	 * Find the location on a node of a message occurrence specification
	 * 
	 * @param nodeEditPart
	 *        the node edit part which to check incoming and outgoing messages
	 * @param event
	 *        the message occurrence specification
	 * @return the absolute location or null
	 */
	public static Point findLocationOfMessageOccurrence(IGraphicalEditPart nodeEditPart, MessageOccurrenceSpecification event) {
		// messages to the node
		List<?> targetConnections = nodeEditPart.getTargetConnections();
		for(Object conn : targetConnections) {
			if(conn instanceof ConnectionNodeEditPart) {
				EObject element = ((ConnectionNodeEditPart)conn).resolveSemanticElement();
				if(element instanceof Message && event.equals(((Message)element).getReceiveEvent())) {
					// finish event of the message
					IFigure figure = ((ConnectionNodeEditPart)conn).getFigure();
					if(figure instanceof AbstractPointListShape) {
						return ApexSequenceUtil.getAbsoluteEdgeExtremity((ConnectionNodeEditPart)conn, false);
					}
				}
			}
		}
		// messages from the node
		List<?> sourceConnections = nodeEditPart.getSourceConnections();
		for(Object conn : sourceConnections) {
			if(conn instanceof ConnectionNodeEditPart) {
				EObject element = ((ConnectionNodeEditPart)conn).resolveSemanticElement();
				if(element instanceof Message && event.equals(((Message)element).getSendEvent())) {
					// start event of the message
					IFigure figure = ((ConnectionNodeEditPart)conn).getFigure();
					if(figure instanceof AbstractPointListShape) {
						return ApexSequenceUtil.getAbsoluteEdgeExtremity((ConnectionNodeEditPart)conn, true);
					}
				}
			}
		}
		return null;
	}
	
	/**
	 * Create a command to update the enclosing interaction of a message end according to its new location.
	 * 
	 * @param movedMos
	 *        the moved Message Occurrence Specification
	 * @param newLocation
	 *        the new absolute location
	 * @param editPart
	 *        any adit part of the corresponding diagram
	 * @return the command or null if nothing changes
	 */
	//@SuppressWarnings("unchecked")
	public static Command createUpdateEnclosingInteractionCommand(MessageOccurrenceSpecification movedMos, Point newLocation, GraphicalEditPart editPart) {

		//		// calculate new bounds for the execution specification
		//		Rectangle absoluteNewBounds = executionSpecificationEP.getFigure().getBounds().getCopy();
		//
		//		executionSpecificationEP.getFigure().getParent().translateToAbsolute(absoluteNewBounds);
		//
		//		absoluteNewBounds.translate(moveDelta);
		//		absoluteNewBounds.resize(sizeDelta);
		//
		//		int xCenter = absoluteNewBounds.getCenter().x;
		//
		//		Rectangle top = new Rectangle(xCenter, absoluteNewBounds.y, 0, 0);
		//		Rectangle bottom = new Rectangle(xCenter, absoluteNewBounds.bottom(), 0, 0);
		//
		//		// associate es with its bounds, and start and finish event with the top and bottom of the bounds
		HashMap<InteractionFragment, Rectangle> iftToCheckForUpdate = new HashMap<InteractionFragment, Rectangle>();
		//
		//		ExecutionSpecification es = (ExecutionSpecification)executionSpecificationEP.resolveSemanticElement();

		iftToCheckForUpdate.put(movedMos, new Rectangle(newLocation, new Dimension()));

		//		iftToCheckForUpdate.put(es.getStart(), top);
		//
		//		iftToCheckForUpdate.put(es.getFinish(), bottom);
		//
		//		List<ConnectionEditPart> sourceConnectionEPs = executionSpecificationEP.getSourceConnections();
		//
		//		// find possible ifts associated with messages connected to the moved es
		//		for(ConnectionEditPart sourceConnectionEP : sourceConnectionEPs) {
		//			EObject elem = sourceConnectionEP.getNotationView().getElement();
		//
		//			// for connections, messages have ends that can be ift but don't have theirs own edit parts
		//			// => use anchors to determine position
		//			if(elem instanceof Message) {
		//				Message msg = (Message)elem;
		//				MessageEnd sendEvent = msg.getSendEvent();
		//				if(sendEvent instanceof InteractionFragment) {
		//					Connection msgFigure = sourceConnectionEP.getConnectionFigure();
		//
		//					Point sourcePoint = msgFigure.getSourceAnchor().getLocation(msgFigure.getTargetAnchor().getReferencePoint());
		//
		//					iftToCheckForUpdate.put((InteractionFragment)sendEvent, new Rectangle(sourcePoint.x + moveDelta.x, sourcePoint.y + moveDelta.y, 0, 0));
		//				}
		//			}
		//		}
		//
		//		List<ConnectionEditPart> targetConnectionEPs = executionSpecificationEP.getTargetConnections();
		//
		//		for(ConnectionEditPart targetConnectionEP : targetConnectionEPs) {
		//			EObject elem = targetConnectionEP.getNotationView().getElement();
		//
		//			if(elem instanceof Message) {
		//				Message msg = (Message)elem;
		//				MessageEnd receiveEvent = msg.getReceiveEvent();
		//				if(receiveEvent instanceof InteractionFragment) {
		//					Connection msgFigure = targetConnectionEP.getConnectionFigure();
		//
		//					Point targetPoint = msgFigure.getTargetAnchor().getLocation(msgFigure.getSourceAnchor().getReferencePoint());
		//
		//					iftToCheckForUpdate.put((InteractionFragment)receiveEvent, new Rectangle(targetPoint.x + moveDelta.x, targetPoint.y + moveDelta.y, 0, 0));
		//				}
		//			}
		//		}

		CompoundCommand cmd = new CompoundCommand();

		for(Map.Entry<InteractionFragment, Rectangle> entry : iftToCheckForUpdate.entrySet()) {
			InteractionFragment newEnclosingInteraction = ApexSequenceUtil.findInteractionFragmentContainerAt(entry.getValue(), editPart, true);
			if(newEnclosingInteraction != null) {
				cmd.add(new ICommandProxy(getSetEnclosingInteractionCommand(editPart.getEditingDomain(), entry.getKey(), newEnclosingInteraction)));
			}
		}

		if(!cmd.isEmpty()) {
			return cmd;
		} else {
			return null;
		}
	}

	/**
	 * Create a command to update the enclosing interaction of an execution specification according to its new bounds.
	 * 
	 * @param executionSpecificationEP
	 *        the edit part of the execution specification
	 * @param absoluteNewBounds
	 *        the new absolute bounds
	 * @return the command or null if nothing changes
	 */
	@SuppressWarnings("unchecked")
	public static Command createUpdateEnclosingInteractionCommand(ShapeNodeEditPart executionSpecificationEP, Point moveDelta, Dimension sizeDelta) {

		// calculate new bounds for the execution specification
		Rectangle absoluteNewBounds = executionSpecificationEP.getFigure().getBounds().getCopy();

		executionSpecificationEP.getFigure().getParent().translateToAbsolute(absoluteNewBounds);

		absoluteNewBounds.translate(moveDelta);
		absoluteNewBounds.resize(sizeDelta);

		int xCenter = absoluteNewBounds.getCenter().x;

		Rectangle top = new Rectangle(xCenter, absoluteNewBounds.y, 0, 0);
		Rectangle bottom = new Rectangle(xCenter, absoluteNewBounds.bottom(), 0, 0);

		// associate es with its bounds, and start and finish event with the top and bottom of the bounds
		HashMap<InteractionFragment, Rectangle> iftToCheckForUpdate = new HashMap<InteractionFragment, Rectangle>();

		ExecutionSpecification es = (ExecutionSpecification)executionSpecificationEP.resolveSemanticElement();

		iftToCheckForUpdate.put(es, absoluteNewBounds);

		iftToCheckForUpdate.put(es.getStart(), top);

		iftToCheckForUpdate.put(es.getFinish(), bottom);

		List<ConnectionEditPart> sourceConnectionEPs = executionSpecificationEP.getSourceConnections();

		// find possible ifts associated with messages connected to the moved es
		for(ConnectionEditPart sourceConnectionEP : sourceConnectionEPs) {
			EObject elem = sourceConnectionEP.getNotationView().getElement();

			// for connections, messages have ends that can be ift but don't have theirs own edit parts
			// => use anchors to determine position
			if(elem instanceof Message) {
				Message msg = (Message)elem;
				MessageEnd sendEvent = msg.getSendEvent();
				if(sendEvent instanceof InteractionFragment) {
					Connection msgFigure = sourceConnectionEP.getConnectionFigure();

					Point sourcePoint = msgFigure.getSourceAnchor().getLocation(msgFigure.getTargetAnchor().getReferencePoint());

					iftToCheckForUpdate.put((InteractionFragment)sendEvent, new Rectangle(sourcePoint.x + moveDelta.x, sourcePoint.y + moveDelta.y, 0, 0));
				}
			}
		}

		List<ConnectionEditPart> targetConnectionEPs = executionSpecificationEP.getTargetConnections();

		for(ConnectionEditPart targetConnectionEP : targetConnectionEPs) {
			EObject elem = targetConnectionEP.getNotationView().getElement();

			if(elem instanceof Message) {
				Message msg = (Message)elem;
				MessageEnd receiveEvent = msg.getReceiveEvent();
				if(receiveEvent instanceof InteractionFragment) {
					Connection msgFigure = targetConnectionEP.getConnectionFigure();

					Point targetPoint = msgFigure.getTargetAnchor().getLocation(msgFigure.getSourceAnchor().getReferencePoint());

					iftToCheckForUpdate.put((InteractionFragment)receiveEvent, new Rectangle(targetPoint.x + moveDelta.x, targetPoint.y + moveDelta.y, 0, 0));
				}
			}
		}

		CompoundCommand cmd = new CompoundCommand();

		for(Map.Entry<InteractionFragment, Rectangle> entry : iftToCheckForUpdate.entrySet()) {
			InteractionFragment newEnclosingInteraction = ApexSequenceUtil.findInteractionFragmentContainerAt(entry.getValue(), executionSpecificationEP, true);
			if(newEnclosingInteraction != null) {
				cmd.add(new ICommandProxy(getSetEnclosingInteractionCommand(executionSpecificationEP.getEditingDomain(), entry.getKey(), newEnclosingInteraction)));
			}
		}

		if(!cmd.isEmpty()) {
			return cmd;
		} else {
			return null;
		}
	}
	
	/**
	 * return a command to set the enclosing interaction or interaction operand of an interaction fragment.
	 * 
	 * @param ed
	 *        The transactional editing domain.
	 * @param ift
	 *        The interaction fragment.
	 * @param io
	 *        the new enclosing interaction.
	 * @return The command.
	 */
	public static ICommand getSetEnclosingInteractionCommand(final TransactionalEditingDomain ed, final InteractionFragment ift, final EObject interaction) {
		return new AbstractTransactionalCommand(ed, "Set enclosing interaction command", null) {

			@Override
			protected CommandResult doExecuteWithResult(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
				setEnclosingInteraction(ift, interaction, false);

				return CommandResult.newOKCommandResult();
			}
		};
	}
	
	/**
	 * Set the interaction or interaction operand which contains a fragment
	 * 
	 * @param ift
	 *        fragment to update container
	 * @param interaction
	 *        new containing interaction or interaction operand
	 * @param forceIfCoregion
	 *        force the set even if fragment belong to a coregion. Use true only when you are sure the fragment no longer belongs to a coregion's
	 *        operand.
	 */
	public static void setEnclosingInteraction(InteractionFragment ift, EObject interaction, boolean forceIfCoregion) {
		if(ift != null) {
			if(interaction instanceof Interaction) {
				if(!interaction.equals(ift.getEnclosingInteraction())) {
					// check case when mos looks outside but is in a coregion.
					if(!forceIfCoregion && ift instanceof MessageOccurrenceSpecification) {
						InteractionOperand operand = ift.getEnclosingOperand();
						if(operand != null) {
							Element cf = operand.getOwner();
							if(cf instanceof CombinedFragment && InteractionOperatorKind.PAR_LITERAL.equals(((CombinedFragment)cf).getInteractionOperator())) {
								// was in a coregion. Check whether other mos is still in the coregion
								Message mess = ((MessageOccurrenceSpecification)ift).getMessage();
								// find other mos
								MessageOccurrenceSpecification otherMos = null;
								if(ift.equals(mess.getSendEvent()) && mess.getReceiveEvent() instanceof MessageOccurrenceSpecification) {
									otherMos = (MessageOccurrenceSpecification)mess.getReceiveEvent();
								} else if(ift.equals(mess.getReceiveEvent()) && mess.getSendEvent() instanceof MessageOccurrenceSpecification) {
									otherMos = (MessageOccurrenceSpecification)mess.getSendEvent();
								}
								if(otherMos != null) {
									// check that it is in a coregion (specific code is in charge of taking it out in ReconnectMessageHelper)
									if(operand.equals(otherMos.getEnclosingOperand())) {
										return;
									}
								}
							}
						}
					}
					ift.setEnclosingOperand(null);
					ift.setEnclosingInteraction((Interaction)interaction);
				}
			} else if(interaction instanceof InteractionOperand) {
				if(!interaction.equals(ift.getEnclosingOperand())) {
					ift.setEnclosingInteraction(null);
					ift.setEnclosingOperand((InteractionOperand)interaction);
				}
			}
		}
	}
	
	/**
	 * Get the complete command to move or reconnect all edit parts attached to one or two occurrence specification(s).
	 * 
	 * @param movedOccurrenceSpecification1
	 *        first moved occurrence specification
	 * @param movedOccurrenceSpecification2
	 *        second moved occurrence specification (or null)
	 * @param yLocation1
	 *        y location where first occurrence specification is moved
	 * @param yLocation2
	 *        y location where second occurrence specification is moved (or -1)
	 * @param lifelinePart
	 *        lifeline edit part containing the moved element
	 * @param notToMoveEditParts
	 *        list of edit parts which must not be moved in the created command
	 * @return command to move all edit parts linked to the occurrence specifications or null
	 */
	public static Command getMoveOccurrenceSpecificationsCommand(OccurrenceSpecification movedOccurrenceSpecification1, OccurrenceSpecification movedOccurrenceSpecification2, int yLocation1, int yLocation2, ShapeNodeEditPart lifelinePart, List<EditPart> notToMoveEditParts) {
		// the global command which shall be completed and returned
		CompoundCommand globalCmd = new CompoundCommand();
		// move the corresponding execution specification if necessary
		Command command = getMoveExecutionSpecificationCommand(movedOccurrenceSpecification1, movedOccurrenceSpecification2, yLocation1, yLocation2, lifelinePart, notToMoveEditParts);
		if(command != null) {
			globalCmd.add(command);
		}
		// reconnect the corresponding message(s) if necessary
		if(movedOccurrenceSpecification1 instanceof MessageOccurrenceSpecification) {
			command = getReconnectMessageCommand(movedOccurrenceSpecification1, yLocation1, lifelinePart, notToMoveEditParts);
			if(command != null) {
				globalCmd.add(command);
			}
		}
		if(movedOccurrenceSpecification2 instanceof MessageOccurrenceSpecification) {
			command = getReconnectMessageCommand(movedOccurrenceSpecification2, yLocation2, lifelinePart, notToMoveEditParts);
			if(command != null) {
				globalCmd.add(command);
			}
		}
		// move the corresponding time/duration constraints/observations if necessary
		command = getMoveTimeElementsCommand(movedOccurrenceSpecification1, movedOccurrenceSpecification2, yLocation1, yLocation2, lifelinePart, notToMoveEditParts);
		if(command != null) {
			globalCmd.add(command);
		}
		// reconnect the corresponding general ordering(s) if necessary
		command = getReconnectGeneralOrderingCommand(movedOccurrenceSpecification1, yLocation1, lifelinePart, notToMoveEditParts);
		if(command != null) {
			globalCmd.add(command);
		}
		if(movedOccurrenceSpecification2 != null) {
			command = getReconnectGeneralOrderingCommand(movedOccurrenceSpecification2, yLocation2, lifelinePart, notToMoveEditParts);
			if(command != null) {
				globalCmd.add(command);
			}
		}
		// return null rather than an empty non executable command
		if(globalCmd.isEmpty()) {
			return null;
		}
		return globalCmd;
	}
	
	/**
	 * Get the command to move execution specification(s) attached to moved occurrence specification(s)
	 * 
	 * @param movedOccurrenceSpecification1
	 *        first moved occurrence specification
	 * @param movedOccurrenceSpecification2
	 *        second moved occurrence specification (or null)
	 * @param yLocation1
	 *        y location where first occurrence specification is moved
	 * @param yLocation2
	 *        y location where second occurrence specification is moved (or -1)
	 * @param lifelinePart
	 *        lifeline edit part containing the moved element
	 * @param notToMoveEditParts
	 *        list of edit parts which must not be moved in the created command
	 * @return command to move execution specification edit part linked to the occurrence specification or null
	 */
	private static Command getMoveExecutionSpecificationCommand(OccurrenceSpecification movedOccurrenceSpecification1, OccurrenceSpecification movedOccurrenceSpecification2, int yLocation1, int yLocation2, ShapeNodeEditPart lifelinePart, List<EditPart> notToMoveEditParts) {
		// the global command which shall be completed and returned
		CompoundCommand globalCmd = new CompoundCommand();
		// execution(s) linked to the event must be resized
		EditPart node1 = null;
		if(movedOccurrenceSpecification1 != null) {
			node1 = getLinkedEditPart(lifelinePart, movedOccurrenceSpecification1);
		}
		EditPart node2 = null;
		if(movedOccurrenceSpecification2 != null) {
			node2 = getLinkedEditPart(lifelinePart, movedOccurrenceSpecification2);
		}
		if(node1 instanceof GraphicalEditPart && !notToMoveEditParts.contains(node1)) {
			Command cmd = getMoveSingleExecutionSpecificationCommand((GraphicalEditPart)node1, movedOccurrenceSpecification1, movedOccurrenceSpecification2, yLocation1, yLocation2, lifelinePart);
			if(cmd != null) {
				globalCmd.add(cmd);
			}
		}
		if(node2 != node1 && node2 instanceof GraphicalEditPart && !notToMoveEditParts.contains(node2)) {
			Command cmd = getMoveSingleExecutionSpecificationCommand((GraphicalEditPart)node2, movedOccurrenceSpecification2, null, yLocation2, -1, lifelinePart);
			if(cmd != null) {
				globalCmd.add(cmd);
			}
		}
		// return null rather than an empty non executable command
		if(globalCmd.isEmpty()) {
			return null;
		}
		return globalCmd;
	}
	
	/**
	 * Get the edit part (message, execution, or destruction event) which starts or finishes with the event on the given lifeline part
	 * 
	 * @param lifelinePart
	 *        the lifeline edit part on which the event is located
	 * @param event
	 *        the event
	 * @return the edit part of which an end is defined by event on the lifelinePart edit part
	 */
	public static EditPart getLinkedEditPart(EditPart lifelinePart, OccurrenceSpecification event) {
		if(event instanceof MessageOccurrenceSpecification) {
			// get parts representing the message linked with the event
			Message message = ((MessageOccurrenceSpecification)event).getMessage();
			if(message == null) {
				return null;
			}
			Collection<Setting> settings = CacheAdapter.INSTANCE.getNonNavigableInverseReferences(message);
			for(Setting ref : settings) {
				if(NotationPackage.eINSTANCE.getView_Element().equals(ref.getEStructuralFeature())) {
					View view = (View)ref.getEObject();
					EditPart part = DiagramEditPartsUtil.getEditPartFromView(view, lifelinePart);
					// the message part must start or finish on the lifeline (with the event)
					if(part instanceof ConnectionEditPart) {
						EditPart lifelineChild = null;
						if(event.equals(message.getSendEvent())) {
							lifelineChild = ((ConnectionEditPart)part).getSource();
						} else if(event.equals(message.getReceiveEvent())) {
							lifelineChild = ((ConnectionEditPart)part).getTarget();
						}
						ShapeNodeEditPart parentLifeline = ApexSequenceUtil.getParentLifelinePart(lifelineChild);
						if(lifelinePart.equals(parentLifeline)) {
							return part;
						}
					}
				}
			}
		} else if(event instanceof ExecutionOccurrenceSpecification) {
			// get parts representing the execution linked with the event
			ExecutionSpecification execution = ((ExecutionOccurrenceSpecification)event).getExecution();
			if(execution == null) {
				return null;
			}
			Collection<Setting> settings = CacheAdapter.INSTANCE.getNonNavigableInverseReferences(execution);
			for(Setting ref : settings) {
				if(NotationPackage.eINSTANCE.getView_Element().equals(ref.getEStructuralFeature())) {
					View view = (View)ref.getEObject();
					EditPart part = DiagramEditPartsUtil.getEditPartFromView(view, lifelinePart);
					// the execution part must be on the lifeline
					EditPart lifelineChild = part;
					ShapeNodeEditPart parentLifeline = ApexSequenceUtil.getParentLifelinePart(lifelineChild);
					if(lifelinePart.equals(parentLifeline)) {
						return part;
					}
				}
			}
		} else {
			// get parts representing the destruction event linked with the event
			for(Object lifelineChild : lifelinePart.getChildren()) {
				
				if ( lifelineChild instanceof IGraphicalEditPart ) {
					EObject eObj = ((IGraphicalEditPart) lifelineChild).resolveSemanticElement();
					
					if(eObj instanceof DestructionOccurrenceSpecification && eObj.equals(event)) {
						return (EditPart)lifelineChild;
					}	
					
				}
				
			}
		}
		return null;
	}
	
	/**
	 * Get the command to move an execution specification attached to moved occurrence specification(s)
	 * 
	 * @param executionSpecificationPart
	 *        the execution specification edit part to move
	 * @param movedOccurrenceSpecification1
	 *        first moved occurrence specification
	 * @param movedOccurrenceSpecification2
	 *        second moved occurrence specification (or null)
	 * @param yLocation1
	 *        y location where first occurrence specification is moved
	 * @param yLocation2
	 *        y location where second occurrence specification is moved (or -1)
	 * @param lifelinePart
	 *        lifeline edit part containing the moved element
	 * @return command to move the execution specification edit part or null
	 */
	private static Command getMoveSingleExecutionSpecificationCommand(GraphicalEditPart executionSpecificationPart, OccurrenceSpecification movedOccurrenceSpecification1, OccurrenceSpecification movedOccurrenceSpecification2, int yLocation1, int yLocation2, ShapeNodeEditPart lifelinePart) {
		// execution linked to the event must be resized
		EObject execution = executionSpecificationPart.resolveSemanticElement();
		if(execution instanceof ExecutionSpecification) {
			// finish or start events of the execution have been moved
			// get positions where execution edit part is attached to events
			int position1 = PositionConstants.NONE;
			int position2 = PositionConstants.NONE;
			OccurrenceSpecification start = ((ExecutionSpecification)execution).getStart();
			OccurrenceSpecification finish = ((ExecutionSpecification)execution).getFinish();
			if(start != null && start.equals(movedOccurrenceSpecification1)) {
				position1 = PositionConstants.TOP;
			} else if(finish != null && finish.equals(movedOccurrenceSpecification1)) {
				position1 = PositionConstants.BOTTOM;
			}
			if(start != null && start.equals(movedOccurrenceSpecification2)) {
				position2 = PositionConstants.TOP;
			} else if(finish != null && finish.equals(movedOccurrenceSpecification2)) {
				position2 = PositionConstants.BOTTOM;
			}
			// move necessary bounds
			Rectangle newBounds = null;
			int heighDelta = 0;
			if(position1 != PositionConstants.NONE && position2 != PositionConstants.NONE) {
				// both bounds have changed
				Point referencePoint1 = getReferencePoint(lifelinePart, movedOccurrenceSpecification1, yLocation1);
				Point referencePoint2 = getReferencePoint(lifelinePart, movedOccurrenceSpecification2, yLocation2);
				makeRelativeToLifeline(referencePoint1, lifelinePart, true);
				makeRelativeToLifeline(referencePoint2, lifelinePart, true);
				// Get old bounds information by consulting old figure
				Rectangle esBounds = ApexSequenceUtil.getAbsoluteBounds(executionSpecificationPart);
				Point esLoc = esBounds.getLocation();
				makeRelativeToLifeline(esLoc, lifelinePart, true);
				esBounds.setLocation(esLoc);
				int oldX = esBounds.x;
				int oldY = esBounds.y;
				int oldWidth = esBounds.width;
				int oldHeight = esBounds.height;
				// Compute new bounds of the time element
				int top = oldY;
				int bottom = oldY + oldHeight;
				// bound is based on two events. Recompute it according to moved event(s).
				if(position1 == PositionConstants.TOP) {
					top = referencePoint1.y;
				} else if(position1 == PositionConstants.BOTTOM) {
					bottom = referencePoint1.y;
				}
				if(position2 == PositionConstants.TOP) {
					top = referencePoint2.y;
				} else if(position2 == PositionConstants.BOTTOM) {
					bottom = referencePoint2.y;
				}
				// top and bottom may have been inverted during the move.
				newBounds = new Rectangle(oldX, Math.min(top, bottom), oldWidth, Math.abs(bottom - top));
			} else if(position1 != PositionConstants.NONE) {
				Point referencePoint1 = getReferencePoint(lifelinePart, movedOccurrenceSpecification1, yLocation1);
				makeRelativeToLifeline(referencePoint1, lifelinePart, true);
				// Get old bounds information by consulting old figure
				Rectangle esBounds = ApexSequenceUtil.getAbsoluteBounds(executionSpecificationPart);
				Point esLoc = esBounds.getLocation();
				makeRelativeToLifeline(esLoc, lifelinePart, true);
				esBounds.setLocation(esLoc);
				int oldX = esBounds.x;
				int oldY = esBounds.y;
				int oldWidth = esBounds.width;
				int oldHeight = esBounds.height;
				// Compute new bounds of the time element
				int top = oldY;
				int bottom = oldY + oldHeight;
				// bound is based on two events. Recompute it according to moved event(s).
				if(position1 == PositionConstants.TOP) {
					top = referencePoint1.y;
				} else if(position1 == PositionConstants.BOTTOM) {
					bottom = referencePoint1.y;
				}
				// top and bottom may have been inverted during the move.
				newBounds = new Rectangle(oldX, Math.min(top, bottom), oldWidth, Math.abs(bottom - top));
			} else if(position2 != PositionConstants.NONE) {
				Point referencePoint2 = getReferencePoint(lifelinePart, movedOccurrenceSpecification2, yLocation2);
				makeRelativeToLifeline(referencePoint2, lifelinePart, true);
				// Get old bounds information by consulting old figure
				Rectangle esBounds = ApexSequenceUtil.getAbsoluteBounds(executionSpecificationPart);
				Point esLoc = esBounds.getLocation();
				makeRelativeToLifeline(esLoc, lifelinePart, true);
				esBounds.setLocation(esLoc);
				int oldX = esBounds.x;
				int oldY = esBounds.y;
				int oldWidth = esBounds.width;
				int oldHeight = esBounds.height;
				// Compute new bounds of the time element
				int top = oldY;
				int bottom = oldY + oldHeight;
				// bound is based on two events. Recompute it according to moved event(s).
				if(position2 == PositionConstants.TOP) {
					top = referencePoint2.y;
				} else if(position2 == PositionConstants.BOTTOM) {
					bottom = referencePoint2.y;
				}
				// top and bottom may have been inverted during the move.
				newBounds = new Rectangle(oldX, Math.min(top, bottom), oldWidth, Math.abs(bottom - top));
			}
			if(newBounds != null) {
				// adjust bounds for execution specification
				newBounds.height -= heighDelta;
				TransactionalEditingDomain editingDomain = executionSpecificationPart.getEditingDomain();
				// return the resize command
				ICommandProxy resize = new ICommandProxy(new SetBoundsCommand(editingDomain, DiagramUIMessages.SetLocationCommand_Label_Resize, new EObjectAdapter((View)executionSpecificationPart.getModel()), newBounds));
				return resize;
			}
		}
		return null;
	}
	
	/**
	 * Get the reference point to reconnect or resize edit parts at the given y location
	 * 
	 * @param lifelinePart
	 *        lifeline edit part containing the moved element
	 * @param movedOccurrenceSpecification
	 *        the moving occurrence specification which a reference point is searched for
	 * @param yLocation
	 *        y location
	 * @return reference point on the lifeline
	 */
	private static Point getReferencePoint(ShapeNodeEditPart lifelinePart, OccurrenceSpecification movedOccurrenceSpecification, int yLocation) {
		Point referencePoint = findLocationOfEvent(lifelinePart, movedOccurrenceSpecification);
		if(referencePoint == null) {
			referencePoint = lifelinePart.getFigure().getBounds().getCenter().getCopy();
		}
		referencePoint.y = yLocation;
		return referencePoint;
	}
	
	/**
	 * Make an absolute point relative to a lifeline figure.
	 * 
	 * @param absolutePoint
	 *        the absolute point to translate
	 * @param lifelinePart
	 *        the containing lifeline edit part
	 */
	private static void makeRelativeToLifeline(Point absolutePoint, ShapeNodeEditPart lifelinePart, boolean relativeToContentPane) {
		IFigure figure;
		if(relativeToContentPane) {
			figure = lifelinePart.getContentPane();
		} else {
			figure = lifelinePart.getFigure();
		}
		figure.translateToRelative(absolutePoint);
		absolutePoint.translate(figure.getBounds().getLocation().getNegated());
	}
	
	/**
	 * Get the command to move time/duration observations/constraints attached to a moved occurrence specification
	 * 
	 * @param movedOccurrenceSpecification1
	 *        first moved occurrence specification
	 * @param movedOccurrenceSpecification2
	 *        second moved occurrence specification (or null)
	 * @param yLocation1
	 *        y location where first occurrence specification is moved
	 * @param yLocation2
	 *        y location where second occurrence specification is moved (or -1)
	 * @param lifelinePart
	 *        lifeline edit part containing the moved element
	 * @param notToMoveEditParts
	 *        list of edit parts which must not be moved in the created command
	 * @return command to move time edit parts linked to the occurrence specification or null
	 */
	private static Command getMoveTimeElementsCommand(OccurrenceSpecification movedOccurrenceSpecification1, OccurrenceSpecification movedOccurrenceSpecification2, int yLocation1, int yLocation2, ShapeNodeEditPart lifelinePart, List<EditPart> notToMoveEditParts) {
		// the global command which shall be completed and returned
		CompoundCommand globalCmd = new CompoundCommand();
		IFigure lifelineFigure = lifelinePart.getFigure();
		// relocate each linked time element contained within the lifeline part
		for(Object lifelineChild : lifelinePart.getChildren()) {
			if(lifelineChild instanceof IBorderItemEditPart && !notToMoveEditParts.contains(lifelineChild)) {
				final IBorderItemEditPart timePart = (IBorderItemEditPart)lifelineChild;
				Command cmd = getMoveSingleTimeRelatedElementCommand(timePart, movedOccurrenceSpecification1, movedOccurrenceSpecification2, yLocation1, yLocation2, lifelinePart);
				if(cmd != null) {
					globalCmd.add(cmd);
				}
			}
		}
		
		// relocate each observation linked time element
		for(Object targetConnection : lifelinePart.getTargetConnections()){
			
			/* apex improved start */
			if ( targetConnection instanceof IGraphicalEditPart ) {
				EObject eObj = ((IGraphicalEditPart) targetConnection).resolveSemanticElement();				
				
				if(eObj instanceof Observation){
					Command cmd = getMoveSingleTimeRelatedElementCommand((ConnectionNodeEditPart)targetConnection, movedOccurrenceSpecification1, movedOccurrenceSpecification2, yLocation1, yLocation2, (IApexLifelineEditPart)lifelinePart);
					if(cmd != null) {
						globalCmd.add(cmd);
					}
				}	
			}
			/* apex improved end */
			/* apex replaced
			if(targetConnection instanceof ObservationLinkEditPart){
				Command cmd = getMoveSingleTimeRelatedElementCommand((ObservationLinkEditPart)targetConnection, movedOccurrenceSpecification1, movedOccurrenceSpecification2, yLocation1, yLocation2, lifelinePart);
				if(cmd != null) {
					globalCmd.add(cmd);
				}
			}	
			*/			
		}
		
		// refresh layout commands :
		// one before the commands for the undo and one after for classic execution
		if(!globalCmd.isEmpty() && lifelineFigure instanceof BorderedNodeFigure) {
			Command relayout = getReLayoutCmd((BorderedNodeFigure)lifelineFigure, false);
			Command relayoutUndo = getReLayoutCmd((BorderedNodeFigure)lifelineFigure, true);
			if(relayout != null && relayoutUndo != null) {
				CompoundCommand commandWithRelayout = new CompoundCommand();
				commandWithRelayout.add(relayoutUndo);
				commandWithRelayout.add(globalCmd);
				commandWithRelayout.add(relayout);
				return commandWithRelayout;
			}
		}
		// return null rather than an empty non executable command
		if(globalCmd.isEmpty()) {
			return null;
		}
		return globalCmd;
	}
	
	/**
	 * Get a command which refreshes the bordered layout of the node.
	 * 
	 * @param node
	 *        the node figure with bordered items (including time-related parts)
	 * @param onUndo
	 *        if true the relayout will be done on undo only, if false it will be done on classic execute only
	 * @return the refresh command
	 */
	private static Command getReLayoutCmd(BorderedNodeFigure node, boolean onUndo) {
		// relayout the border container figure so that time elements are refreshed
		final IFigure container = node.getBorderItemContainer();

		if(onUndo) {
			return new Command() {

				@Override
				public void undo() {
					container.getLayoutManager().layout(container);
				}
			};
		} else {
			return new Command() {

				@Override
				public void execute() {
					container.getLayoutManager().layout(container);
				}
			};
		}
	}
	
	private static Command getMoveSingleTimeRelatedElementCommand(
			final ConnectionNodeEditPart targetConnection,
			final OccurrenceSpecification movedOccurrenceSpecification1,
			final OccurrenceSpecification movedOccurrenceSpecification2,
			final int yLocation1, final int yLocation2,final IApexLifelineEditPart lifelinePart) {
		
		AbstractTransactionalCommand updateTargetAnchorCommand = new AbstractTransactionalCommand(((IGraphicalEditPart) targetConnection).getEditingDomain(),"update target anchor",null) {
			@Override
			protected CommandResult doExecuteWithResult(IProgressMonitor monitor,
					IAdaptable info) throws ExecutionException {
				
				// both bounds may have changed
				Point referencePoint1 = getReferencePoint((ShapeNodeEditPart)lifelinePart, movedOccurrenceSpecification1, yLocation1);
				Point referencePoint2 = getReferencePoint((ShapeNodeEditPart)lifelinePart, movedOccurrenceSpecification2, yLocation2);

				int position1 = PositionConstants.NONE;
				int position2 = PositionConstants.NONE;
				IBorderItemEditPart tolEP = (IBorderItemEditPart)targetConnection.getSource();
				if(tolEP == null){
					return CommandResult.newCancelledCommandResult();
				}
				
				if(movedOccurrenceSpecification1 != null) {
					position1 = positionWhereEventIsLinkedToPart(movedOccurrenceSpecification1, tolEP);
				}
				if(movedOccurrenceSpecification2 != null) {
					position2 = positionWhereEventIsLinkedToPart(movedOccurrenceSpecification2, tolEP);
				}
				ConnectionAnchor targetAnchor = null;
				if(position1 == PositionConstants.CENTER){
					targetAnchor = lifelinePart.getNodeFigure().getSourceConnectionAnchorAt(referencePoint1);
				}else if(position2 == PositionConstants.CENTER){
					targetAnchor = lifelinePart.getNodeFigure().getSourceConnectionAnchorAt(referencePoint2);
				}
				
				if(targetAnchor!= null){
					String newTargetTerminal = ((ShapeNodeEditPart)lifelinePart).mapConnectionAnchorToTerminal(targetAnchor);
					ConnectorImpl c = (ConnectorImpl)targetConnection.getModel();
					if (newTargetTerminal != null) {
						if (newTargetTerminal.length() == 0)
							c.setTargetAnchor(null);
						else {
							IdentityAnchor a = (IdentityAnchor) c.getTargetAnchor();
							if (a == null)
								a = NotationFactory.eINSTANCE.createIdentityAnchor();
							a.setId(newTargetTerminal);
							c.setTargetAnchor(a);
						}
					}

				}
				
				return CommandResult.newOKCommandResult(); 
			}
		};
		
		
		// return the resize command
		ICommandProxy resize = new ICommandProxy(updateTargetAnchorCommand);
		return resize;
	}

	/**
	 * Get a command to move the time related element's edit part
	 * 
	 * @param timePart
	 *        time related element's edit part to move
	 * @param movedOccurrenceSpecification1
	 *        first moved occurrence specification
	 * @param movedOccurrenceSpecification2
	 *        second moved occurrence specification (or null)
	 * @param yLocation1
	 *        y location where first occurrence specification is moved
	 * @param yLocation2
	 *        y location where second occurrence specification is moved (or -1)
	 * @param lifelinePart
	 *        lifeline edit part containing the moved element
	 * @return
	 */
	private static Command getMoveSingleTimeRelatedElementCommand(IBorderItemEditPart timePart, OccurrenceSpecification movedOccurrenceSpecification1, OccurrenceSpecification movedOccurrenceSpecification2, int yLocation1, int yLocation2, ShapeNodeEditPart lifelinePart) {
		IFigure lifelineFigure = lifelinePart.getFigure();
		// get positions where edit part is attached to events
		int position1 = PositionConstants.NONE;
		int position2 = PositionConstants.NONE;
		if(movedOccurrenceSpecification1 != null) {
			position1 = positionWhereEventIsLinkedToPart(movedOccurrenceSpecification1, timePart);
		}
		if(movedOccurrenceSpecification2 != null) {
			position2 = positionWhereEventIsLinkedToPart(movedOccurrenceSpecification2, timePart);
		}
		// move necessary bounds
		Rectangle newBounds = null;
		if(position1 != PositionConstants.NONE && position2 != PositionConstants.NONE) {
			// both bounds may have changed
			Point referencePoint1 = getReferencePoint(lifelinePart, movedOccurrenceSpecification1, yLocation1);
			Point referencePoint2 = getReferencePoint(lifelinePart, movedOccurrenceSpecification2, yLocation2);
			makeRelativeToLifeline(referencePoint1, lifelinePart, false);
			makeRelativeToLifeline(referencePoint2, lifelinePart, false);
			// Get old bounds information by consulting old figure
			int oldY = timePart.getFigure().getBounds().getLocation().y - lifelineFigure.getBounds().getLocation().y;
			int oldHeight = timePart.getFigure().getSize().height;
			// Compute new bounds of the time element
			if(position1 == PositionConstants.CENTER || position2 == PositionConstants.CENTER) {
				// should not happen, except if both events are merged at the same location
				newBounds = new Rectangle(referencePoint1.x, referencePoint1.y - oldHeight / 2, -1, oldHeight);
			} else {
				int top = oldY;
				int bottom = oldY + oldHeight;
				// bound is based on two events. Recompute it according to moved event(s).
				if(position1 == PositionConstants.TOP) {
					top = referencePoint1.y;
				} else if(position1 == PositionConstants.BOTTOM) {
					bottom = referencePoint1.y;
				}
				if(position2 == PositionConstants.TOP) {
					top = referencePoint2.y;
				} else if(position2 == PositionConstants.BOTTOM) {
					bottom = referencePoint2.y;
				}
				// top and bottom may have been inverted during the move.
				// restore x position, fix time duration always move to east
				int viewX = (Integer) ViewUtil.getPropertyValue((View) timePart.getModel(), NotationPackage.eINSTANCE
						.getLocation_X(), NotationPackage.eINSTANCE.getLocation_X().getEContainingClass());
						
				newBounds = new Rectangle(viewX, Math.min(top, bottom), -1, Math.abs(bottom - top));
//				newBounds = new Rectangle(referencePoint1.x, Math.min(top, bottom), -1, Math.abs(bottom - top));
			}
		} else if(position1 != PositionConstants.NONE) {
			Point referencePoint1 = getReferencePoint(lifelinePart, movedOccurrenceSpecification1, yLocation1);
			makeRelativeToLifeline(referencePoint1, lifelinePart, false);
			// Get old bounds information by consulting old figure
			int oldY = timePart.getFigure().getBounds().getLocation().y - lifelineFigure.getBounds().getLocation().y;
			int oldHeight = timePart.getFigure().getSize().height;
			// Compute new bounds of the time element
			if(position1 == PositionConstants.CENTER) {
				newBounds = new Rectangle(referencePoint1.x, referencePoint1.y - oldHeight / 2, -1, oldHeight);
			} else {
				int top = oldY;
				int bottom = oldY + oldHeight;
				// bound is based on two events. Recompute it according to moved event(s).
				if(position1 == PositionConstants.TOP) {
					top = referencePoint1.y;
				} else if(position1 == PositionConstants.BOTTOM) {
					bottom = referencePoint1.y;
				}
				// top and bottom may have been inverted during the move.
				newBounds = new Rectangle(referencePoint1.x, Math.min(top, bottom), -1, Math.abs(bottom - top));
			}
		} else if(position2 != PositionConstants.NONE) {
			Point referencePoint2 = getReferencePoint(lifelinePart, movedOccurrenceSpecification2, yLocation2);
			makeRelativeToLifeline(referencePoint2, lifelinePart, false);
			// Get old bounds information by consulting old figure
			int oldY = timePart.getFigure().getBounds().getLocation().y - lifelineFigure.getBounds().getLocation().y;
			int oldHeight = timePart.getFigure().getSize().height;
			// Compute new bounds of the time element
			if(position2 == PositionConstants.CENTER) {
				newBounds = new Rectangle(referencePoint2.x, referencePoint2.y - oldHeight / 2, -1, oldHeight);
			} else {
				int top = oldY;
				int bottom = oldY + oldHeight;
				// bound is based on two events. Recompute it according to moved event(s).
				if(position2 == PositionConstants.TOP) {
					top = referencePoint2.y;
				} else if(position2 == PositionConstants.BOTTOM) {
					bottom = referencePoint2.y;
				}
				// top and bottom may have been inverted during the move.
				newBounds = new Rectangle(referencePoint2.x, Math.min(top, bottom), -1, Math.abs(bottom - top));
			}
		}
		if(newBounds != null) {
			TransactionalEditingDomain editingDomain = timePart.getEditingDomain();
			// return the resize command
			ICommandProxy resize = new ICommandProxy(new SetBoundsCommand(editingDomain, DiagramUIMessages.SetLocationCommand_Label_Resize, new EObjectAdapter((View)timePart.getModel()), newBounds));
			return resize;
		}
		return null;
	}
	
	/**
	 * The position of the part where the event is linked
	 * 
	 * @param occSpec
	 *        the occurrence specification
	 * @param timeElementPart
	 *        the part representing time element (duration/time constraint/observation)
	 * @return one of {@link PositionConstants#TOP}, {@link PositionConstants#CENTER}, {@link PositionConstants#BOTTOM},
	 *         {@link PositionConstants#NONE}
	 */
	public static int positionWhereEventIsLinkedToPart(OccurrenceSpecification occSpec, IBorderItemEditPart timeElementPart) {
		EObject timeElement = timeElementPart.resolveSemanticElement();
		if(timeElement instanceof TimeObservation) {
			if(occSpec.equals(((TimeObservation)timeElement).getEvent())) {
				return PositionConstants.CENTER;
			} else {
				return PositionConstants.NONE;
			}
		} else if(timeElement instanceof TimeConstraint) {
			if(((TimeConstraint)timeElement).getConstrainedElements().contains(occSpec)) {
				return PositionConstants.CENTER;
			} else {
				return PositionConstants.NONE;
			}
		} else if(timeElement instanceof DurationConstraint) {
			if(((DurationConstraint)timeElement).getConstrainedElements().contains(occSpec)) {
				List<Element> events = ((DurationConstraint)timeElement).getConstrainedElements();
				ShapeNodeEditPart lifelinePart = ApexSequenceUtil.getParentLifelinePart(timeElementPart);
				if(lifelinePart != null && events.size() >= 2) {
					OccurrenceSpecification otherEvent = null;
					if(!occSpec.equals(events.get(0)) && events.get(0) instanceof OccurrenceSpecification) {
						otherEvent = (OccurrenceSpecification)events.get(0);
					} else if(!occSpec.equals(events.get(1)) && events.get(1) instanceof OccurrenceSpecification) {
						otherEvent = (OccurrenceSpecification)events.get(1);
					}
					if(otherEvent != null) {
						Point otherLoc = findLocationOfEvent(lifelinePart, otherEvent);
						Point thisLoc = findLocationOfEvent(lifelinePart, occSpec);
						if(otherLoc != null && thisLoc != null) {
							if(otherLoc.y > thisLoc.y) {
								return PositionConstants.TOP;
							} else {
								return PositionConstants.BOTTOM;
							}
						}
					}
				}
			} else {
				return PositionConstants.NONE;
			}
		}
		return PositionConstants.NONE;
	}
	
	/**
	 * Get the command to reconnect message attached to a moved occurrence specification
	 * 
	 * @param movedOccurrenceSpecification
	 *        moving occurrence specification
	 * @param yLocation
	 *        y location where occurrence specification is moved
	 * @param lifelinePart
	 *        lifeline edit part containing the moved element
	 * @param notToMoveEditParts
	 *        list of edit parts which must not be moved in the created command
	 * @return command to reconnect message edit part linked to the occurrence specification or null
	 */
	private static Command getReconnectMessageCommand(OccurrenceSpecification movedOccurrenceSpecification, int yLocation, ShapeNodeEditPart lifelinePart, List<EditPart> notToMoveEditParts) {
		// the global command which shall be completed and returned
		CompoundCommand command = new CompoundCommand();
		if(movedOccurrenceSpecification instanceof MessageOccurrenceSpecification) {
			Point referencePoint = getReferencePoint(lifelinePart, movedOccurrenceSpecification, yLocation);
			EditPart childToReconnectTo = findPartToReconnectTo(lifelinePart, referencePoint);
			// reconnect message from the event
			Message message = ((MessageOccurrenceSpecification)movedOccurrenceSpecification).getMessage();
			if(message != null && movedOccurrenceSpecification.equals(message.getSendEvent())) {
				Collection<Setting> settings = CacheAdapter.INSTANCE.getNonNavigableInverseReferences(message);
				for(Setting ref : settings) {
					if(NotationPackage.eINSTANCE.getView_Element().equals(ref.getEStructuralFeature())) {
						View view = (View)ref.getEObject();
						EditPart part = DiagramEditPartsUtil.getEditPartFromView(view, lifelinePart);
						// the message part must start or finish on the lifeline (with the event)
						if(part instanceof ConnectionEditPart && !notToMoveEditParts.contains(part)) {
							Request reconnectRequest = makeReconnectRequest((ConnectionEditPart)part, true, referencePoint, childToReconnectTo);
							Command reconnect = childToReconnectTo.getCommand(reconnectRequest);
							command.add(reconnect);
							// update enclosing interaction fragment
							Command updateIFrag = createUpdateEnclosingInteractionCommand((MessageOccurrenceSpecification)movedOccurrenceSpecification, referencePoint, lifelinePart);
							if(updateIFrag != null && updateIFrag.canExecute()) {
								command.add(updateIFrag);
							}
						}
					}
				}
			}
			// reconnect message to the event
			if(message != null && movedOccurrenceSpecification.equals(message.getReceiveEvent())) {
				Collection<Setting> settings = CacheAdapter.INSTANCE.getNonNavigableInverseReferences(message);
				for(Setting ref : settings) {
					if(NotationPackage.eINSTANCE.getView_Element().equals(ref.getEStructuralFeature())) {
						View view = (View)ref.getEObject();
						EditPart part = DiagramEditPartsUtil.getEditPartFromView(view, lifelinePart);
						// the message part must start or finish on the lifeline (with the event)
						if(part instanceof ConnectionEditPart && !notToMoveEditParts.contains(part)) {
							Request reconnectRequest = makeReconnectRequest((ConnectionEditPart)part, false, referencePoint, childToReconnectTo);
							Command reconnect = childToReconnectTo.getCommand(reconnectRequest);
							command.add(reconnect);
							// update enclosing interaction fragment
							Command updateIFrag = createUpdateEnclosingInteractionCommand((MessageOccurrenceSpecification)movedOccurrenceSpecification, referencePoint, lifelinePart);
							if(updateIFrag != null && updateIFrag.canExecute()) {
								command.add(updateIFrag);
							}
						}
					}
				}
			}
		}

		// return null rather than an empty non executable command
		if(command.isEmpty()) {
			return null;
		}
		return command;
	}
	
	/**
	 * Find the edit part a connection should be reconnected to at a given reference point on a lifeline
	 * 
	 * @param lifelinePart
	 *        lifeline part on which the reconnection must be performed
	 * @param referencePoint
	 *        the reference point
	 * @return lifeline or execution specification edit part to reconnect to (the most external in the lifeline)
	 */
	public static GraphicalEditPart findPartToReconnectTo(ShapeNodeEditPart lifelinePart, Point referencePoint) {
		Rectangle absoluteLifelineBounds = ApexSequenceUtil.getAbsoluteBounds(lifelinePart);
		// inspect children nodes of lifeline
		List<?> children = lifelinePart.getChildren();
		GraphicalEditPart adequateExecutionPart = null;
		int maxDeltaWithMiddle = 0;
		for(Object child : children) {
			
			/* apex improved start */
			if ( child instanceof IGraphicalEditPart ) {
				EObject eObj = ((IGraphicalEditPart) child).resolveSemanticElement();

				// children executions
				if(eObj instanceof ActionExecutionSpecification 
					|| eObj instanceof BehaviorExecutionSpecification 
					|| eObj instanceof CombinedFragment) {
					GraphicalEditPart childPart = (GraphicalEditPart)child;
					Rectangle absoluteBounds = ApexSequenceUtil.getAbsoluteBounds(childPart);
					// enlarge absolute bounds to contain also the right and bottom edges.
					absoluteBounds.expand(1, 1);
					if(absoluteBounds.contains(referencePoint)) {
						// this is an adequate execution part, take the most external one
						int deltaWithMiddle = Math.abs(absoluteBounds.getTop().x - absoluteLifelineBounds.getTop().x);
						if(deltaWithMiddle >= maxDeltaWithMiddle) {
							maxDeltaWithMiddle = deltaWithMiddle;
							adequateExecutionPart = childPart;
						}
					}	
				}
			}
			/* apex improved end */
			/* apex replaced
			// children executions
			if(child instanceof ActionExecutionSpecificationEditPart || child instanceof BehaviorExecutionSpecificationEditPart || child instanceof CombinedFragment2EditPart) {
				GraphicalEditPart childPart = (GraphicalEditPart)child;
				Rectangle absoluteBounds = ApexSequenceUtil.getAbsoluteBounds(childPart);
				// enlarge absolute bounds to contain also the right and bottom edges.
				absoluteBounds.expand(1, 1);
				if(absoluteBounds.contains(referencePoint)) {
					// this is an adequate execution part, take the most external one
					int deltaWithMiddle = Math.abs(absoluteBounds.getTop().x - absoluteLifelineBounds.getTop().x);
					if(deltaWithMiddle >= maxDeltaWithMiddle) {
						maxDeltaWithMiddle = deltaWithMiddle;
						adequateExecutionPart = childPart;
					}
				}
			}
		    */
		}
		if(adequateExecutionPart != null) {
			return adequateExecutionPart;
		}
		return lifelinePart;
	}
	
	/**
	 * Make the request to reconnect the connection
	 * 
	 * @param connection
	 *        connection part
	 * @param isSource
	 *        true if the source must be reconnect, false for target
	 * @param location
	 *        the location where to reconnect
	 * @param partToReconnectTo
	 *        the part which the connection must be reconnected to (or null if unknown or of no importance)
	 * @return the reconnection request
	 */
	@SuppressWarnings("unchecked")
	private static ReconnectRequest makeReconnectRequest(ConnectionEditPart connection, boolean isSource, Point location, EditPart partToReconnectTo) {
		// Obtain the target edit part
		EditPart targetEP = partToReconnectTo;
		String type;
		if(isSource) {
			type = RequestConstants.REQ_RECONNECT_SOURCE;
			if(targetEP == null) {
				targetEP = connection.getSource();
			}
		} else {
			type = RequestConstants.REQ_RECONNECT_TARGET;
			if(targetEP == null) {
				targetEP = connection.getTarget();
			}
		}

		// Create and set the properties of the request
		ReconnectRequest reconnReq = new ReconnectRequest();
		reconnReq.setConnectionEditPart(connection);
		reconnReq.setLocation(location);
		reconnReq.setTargetEditPart(targetEP);
		reconnReq.setType(type);
		// add a parameter to bypass the move impact to avoid infinite loop
		reconnReq.getExtendedData().put(ApexSequenceRequestConstants.DO_NOT_MOVE_EDIT_PARTS, true);

		// Return the request
		return reconnReq;
	}
	
	/**
	 * Get the command to reconnect general ordering attached to a moved occurrence specification
	 * 
	 * @param movedOccurrenceSpecification
	 *        moving occurrence specification
	 * @param yLocation
	 *        y location where occurrence specification is moved
	 * @param lifelinePart
	 *        lifeline edit part containing the moved element
	 * @param notToMoveEditParts
	 *        list of edit parts which must not be moved in the created command
	 * @return command to reconnect general ordering edit parts linked to the occurrence specification or null
	 */
	private static Command getReconnectGeneralOrderingCommand(OccurrenceSpecification movedOccurrenceSpecification, int yLocation, ShapeNodeEditPart lifelinePart, List<EditPart> notToMoveEditParts) {
		// the global command which shall be completed and returned
		CompoundCommand command = new CompoundCommand();
		Point referencePoint = getReferencePoint(lifelinePart, movedOccurrenceSpecification, yLocation);
		EditPart childToReconnectTo = findPartToReconnectTo(lifelinePart, referencePoint);
		// if referencePoint is on a moved part, it must be translated with the location delta of this part
		if(!notToMoveEditParts.isEmpty() && childToReconnectTo != lifelinePart) {
			Point oldLoc = findLocationOfEvent(lifelinePart, movedOccurrenceSpecification);
			referencePoint.y = oldLoc.y;
		}
		// reconnect general ordering from the event
		for(GeneralOrdering go : movedOccurrenceSpecification.getToAfters()) {
			Collection<Setting> settings = CacheAdapter.INSTANCE.getNonNavigableInverseReferences(go);
			for(Setting ref : settings) {
				if(NotationPackage.eINSTANCE.getView_Element().equals(ref.getEStructuralFeature())) {
					View view = (View)ref.getEObject();
					EditPart part = DiagramEditPartsUtil.getEditPartFromView(view, lifelinePart);
					// the general ordering part must start or finish on the lifeline (with the event)
					if(part instanceof ConnectionEditPart && !notToMoveEditParts.contains(part)) {
						Request reconnectRequest = makeReconnectRequest((ConnectionEditPart)part, true, referencePoint, childToReconnectTo);
						Command reconnect = childToReconnectTo.getCommand(reconnectRequest);
						if(reconnect.canExecute()) {
							command.add(reconnect);
						}
					}
				}
			}
		}
		// reconnect general ordering to the event
		for(GeneralOrdering go : movedOccurrenceSpecification.getToBefores()) {
			Collection<Setting> settings = CacheAdapter.INSTANCE.getNonNavigableInverseReferences(go);
			for(Setting ref : settings) {
				if(NotationPackage.eINSTANCE.getView_Element().equals(ref.getEStructuralFeature())) {
					View view = (View)ref.getEObject();
					EditPart part = DiagramEditPartsUtil.getEditPartFromView(view, lifelinePart);
					// the general ordering part must start or finish on the lifeline (with the event)
					if(part instanceof ConnectionEditPart && !notToMoveEditParts.contains(part)) {
						Request reconnectRequest = makeReconnectRequest((ConnectionEditPart)part, false, referencePoint, childToReconnectTo);
						Command reconnect = childToReconnectTo.getCommand(reconnectRequest);
						if(reconnect.canExecute()) {
							command.add(reconnect);
						}
					}
				}
			}
		}
		// return null rather than an empty non executable command
		if(command.isEmpty()) {
			return null;
		}
		return command;
	}
}
