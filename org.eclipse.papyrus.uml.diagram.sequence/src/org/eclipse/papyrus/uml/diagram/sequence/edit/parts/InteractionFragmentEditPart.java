/*****************************************************************************
 * Copyright (c) 2009 Atos Origin.
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
package org.eclipse.papyrus.uml.diagram.sequence.edit.parts;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.notation.Bounds;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.Shape;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.papyrus.uml.diagram.sequence.apex.util.ApexSequenceUtil;
import org.eclipse.papyrus.uml.diagram.sequence.apex.util.LifelineFigureHelper;
import org.eclipse.papyrus.uml.diagram.sequence.figures.LifelineDotLineCustomFigure;
import org.eclipse.papyrus.uml.diagram.sequence.util.CommandHelper;
import org.eclipse.papyrus.uml.diagram.sequence.util.SequenceUtil;
import org.eclipse.uml2.uml.CombinedFragment;
import org.eclipse.uml2.uml.Interaction;
import org.eclipse.uml2.uml.InteractionFragment;
import org.eclipse.uml2.uml.Lifeline;
import org.eclipse.uml2.uml.PartDecomposition;
import org.eclipse.uml2.uml.UMLPackage;

public abstract class InteractionFragmentEditPart extends ShapeNodeEditPart {

	public InteractionFragmentEditPart(View view) {
		super(view);
	}

	@Override
	public void activate() {
		super.activate();
		/* apex added start */
		apexUpdateCoveredFigures();
		/* apex added end */
	}

	@Override
	public void deactivate() {
		/* apex added start */
		Rectangle region = getFigure().getBounds().getCopy();
		LifelineFigureHelper.showAllRegion(region);
		/* apex added end */
		super.deactivate();
	}
	
	/**
	 * View의 Bounds 프로퍼티를 구함
	 * 
	 * @param view
	 * @return
	 */
	private Rectangle apexGetViewBounds(View view) {
		if (view instanceof Node && ((Node)view).getLayoutConstraint() instanceof Bounds) {
			Bounds b = (Bounds)((Node)view).getLayoutConstraint();
			return new Rectangle(b.getX(), b.getY(), b.getWidth(), b.getHeight());
		}
		return new Rectangle(0, 0, 0, 0);
	}
	
	/**
	 * InteractionFragment가 생성될 때 covereds가 아닌 Lifeline들에 hide될 region을 등록
	 * InteractionFragment의 EditPart/Figure가 생성이 안되었을 수도 있으므로 View의 Bounds를 통해 영역을 구함
	 */
	private void apexUpdateCoveredFigures() {
		InteractionFragment ift = (InteractionFragment)resolveSemanticElement();
		Interaction interaction = ift.getEnclosingInteraction();
		EList<Lifeline> coveredLifelines = ift.getCovereds();
		
		View view = getNotationView();
		EObject container = view.eContainer();
		while (container != null && container instanceof View == false) {
			if (interaction == ((View)container).getElement()) {
				break;
			}
			container = container.eContainer();
		}
		
		if (container instanceof View == false) {
			return;
		}
		
		View parent = (View)container;
		for (Object child : parent.getChildren()) {
			if (child instanceof View) {
				EObject element = ((View)child).getElement();
				if (element instanceof Lifeline && !coveredLifelines.contains(element)) {
					Rectangle lifelineRect = apexGetViewBounds((View)child);
					Rectangle centralLineRect = new Rectangle(lifelineRect.x() +  lifelineRect.width() / 2, lifelineRect.y(), 
							1,  lifelineRect.height());

					apexUpdateCoveredFigure(view, (Lifeline)element, centralLineRect);
				}
			}
		}
	}
	
	/**
	 * InteractionFragment가 생성될 때 covereds가 아닌 Lifeline들에 hide될 region을 등록
	 * InteractionFragment의 EditPart/Figure가 생성이 안되었을 수도 있으므로 View의 Bounds를 통해 영역을 구함
	 * 
	 * @param view InteractionFragment의 View
	 * @param lifeline 현재 선택된 lifeline
	 * @param bounds lifeline의 central bounds
	 */
	private void apexUpdateCoveredFigure(View view, Lifeline lifeline, Rectangle bounds) {
		LifelineEditPart lifelineEP = (LifelineEditPart)SequenceUtil.getEditPart(this, lifeline, LifelineEditPart.class);
		if (lifelineEP == null) {
			return;
		}
		
		IFigure figure = lifelineEP.getPrimaryShape().getFigureLifelineDotLineFigure();
		
		Rectangle region = apexGetViewBounds(view);
		if (region.width > 0 && region.height > 0) {
			if (bounds.height <= 0) {
				bounds.height = region.height;
			}
			
			if (region.intersects(bounds)) {
				LifelineFigureHelper.hideRegion(figure, region);
			}
		}
	}
	
	/**
	 * Resize the InteractionFragmentFigure when the covered lifelines are selected in the
	 * properties view.
	 */
	public void resizeInteractionFragmentFigure() {
		Object obj = getModel();
		if(obj instanceof Shape) {
			// we get the element linked to this editpart
			EObject element = ((Shape)obj).getElement();

			if(element instanceof InteractionFragment) {
				// we get the list of the covered lifelinnes by the InteractionUse
				List<Lifeline> lifelineCoveredList = ((InteractionFragment)element).getCovereds();

				if(lifelineCoveredList != null && getParent() != null) {
					// we get the interactionCompartimentEditPart to have access to all lifelines
					// EditParts
					List<EditPart> childrenEditPart = getParent().getChildren();
					if(childrenEditPart != null) {
						// The max value guarantee that the first figure will set the minX value
						int minX = Integer.MAX_VALUE;
						int maxX = -Integer.MAX_VALUE;
						int maxR = -Integer.MAX_VALUE;

						for(EditPart childEditPart : childrenEditPart) {
							// we check all the EditParts to select only the lifelineEditParts
							if(childEditPart instanceof LifelineEditPart) {
								Object childModel = childEditPart.getModel();
								if(childModel instanceof Shape) {
									// we get the object Lifeline linked the selected
									// lifelineEditPart
									EObject childElement = ((Shape)childModel).getElement();
									if(childElement instanceof Lifeline) {
										Lifeline lifeline = (Lifeline)childElement;
										for(Lifeline lfn : lifelineCoveredList) {
											// we check if the lifeLine in the intreactionUse's
											// parent Interaction is a covered Lifeline
											if(lifeline.equals(lfn)) {
												LifelineEditPart liflelineEditPart = (LifelineEditPart)childEditPart;
												if(liflelineEditPart.getFigure().getBounds().x > maxX) {
													maxX = liflelineEditPart.getFigure().getBounds().x;
													// the maxR will represent the futur value of
													// the rectangle right value.
													maxR = liflelineEditPart.getFigure().getBounds().right();

												}
												if(liflelineEditPart.getFigure().getBounds().x < minX) {
													// the min value will represent the rectangle x
													// value.
													minX = liflelineEditPart.getFigure().getBounds().x;
												}
											}
										}
									}
								}
							}
						}

						if(minX != Integer.MAX_VALUE || maxR != -Integer.MAX_VALUE) {
							// after this loop we have the coordinate of two lifeline figure ,
							// even if we have more than two covered Lifelne we choose the
							// extremities.
							getNewSize(minX, maxR);
						}
					}
				}
			}
		}
	}

	/**
	 * resize the interactinUse figure
	 * 
	 * @param min
	 *        the min x position of a covered lifline
	 * @param max
	 *        the max x position of a coverd lifeline
	 * 
	 */
	private void getNewSize(int min, int max) {
		int h = getFigure().getBounds().height;
		int y = getFigure().getBounds().y;

		Dimension size = new Dimension(max - min, h);

		Point loc = new Point(min, y);

		((GraphicalEditPart)getParent()).setLayoutConstraint(this, getFigure(), new Rectangle(loc, size));
	}

	/**
	 * apex updated
	 * 
	 * CombinedFragmentEditPart.handleNotificationEvent()에서 호출되지 않아서 
	 * 호출하도록 CombinedFragmentEditPart 수정
	 * 
	 * Update covered lifelines of a Interaction fragment
	 * 
	 * @param newBounds
	 */
	public void updateCoveredLifelines(Bounds newBounds) {
		Rectangle newBound = new Rectangle(newBounds.getX(), newBounds.getY(), newBounds.getWidth(), newBounds.getHeight());
		InteractionFragment combinedFragment = (InteractionFragment)resolveSemanticElement();
		EList<Lifeline> coveredLifelines = combinedFragment.getCovereds();

		List<Lifeline> coveredLifelinesToAdd = new ArrayList<Lifeline>();
		List<Lifeline> coveredLifelinesToRemove = new ArrayList<Lifeline>();
		EditPart interactionCompartment = getInteractionCompartment();
		if(interactionCompartment != null) {
			/* apex improved start */			
			Rectangle origRect = this.getFigure().getBounds().getCopy();
			Rectangle newRect = newBound.getCopy();
			this.getFigure().translateToAbsolute(origRect);
			this.getFigure().translateToAbsolute(newRect);

			List<LifelineEditPart> lifelineEditPartsToCheck = new ArrayList<LifelineEditPart>();
			
			// Jiho start
			EObject container = resolveSemanticElement().eContainer();
			List<Lifeline> coveredLifelinesByParent = new ArrayList<Lifeline>();
			if (container instanceof Interaction) {
				coveredLifelinesByParent.addAll(((Interaction)container).getLifelines());
			} else if (container instanceof InteractionFragment) {
				coveredLifelinesByParent.addAll(((InteractionFragment)container).getCovereds());
			}
			
			if (origRect.width == 0 && origRect.height == 0) { // 새 CombinedFragment 생성하는 경우 Interaction 내 모든 Lifeline을 check
				Set<Entry<Object, EditPart>> allEditPartEntries = getViewer().getEditPartRegistry().entrySet();

				for(Entry<Object, EditPart> epEntry : allEditPartEntries) {
					EditPart ep = epEntry.getValue();
					if (ep instanceof LifelineEditPart) {
						LifelineEditPart lifelineEP = (LifelineEditPart)ep;
						Lifeline lifeline = (Lifeline)lifelineEP.resolveSemanticElement();
						if (coveredLifelinesByParent.contains(lifeline)) {
							lifelineEditPartsToCheck.add(lifelineEP);
							coveredLifelinesToAdd.add((Lifeline)lifelineEP.resolveSemanticElement());
						}
					}
				}
			} else { // 새로 생성이 아닌 CF 경계 변경인 경우
				List<LifelineEditPart> coveredLifelineEditParts = null;
				if (origRect.width > newRect.width) { // width 축소 시 원래 경계 내에 있던 lifeline을 check
					coveredLifelineEditParts = ApexSequenceUtil.apexGetPositionallyCoveredLifelineEditParts(origRect, this);	
				} else if (origRect.width < newRect.width) { // width 확대 시 새 경계내에 있는 lifeline을 check
					coveredLifelineEditParts = ApexSequenceUtil.apexGetPositionallyCoveredLifelineEditParts(newRect, this);
				} else {
					coveredLifelineEditParts = new ArrayList<LifelineEditPart>(0);
				}
				
				for (LifelineEditPart lifelineEP : coveredLifelineEditParts) {
					Lifeline lifeline = (Lifeline)lifelineEP.resolveSemanticElement();
					if (coveredLifelinesByParent.contains(lifeline)) {
						lifelineEditPartsToCheck.add(lifelineEP);
					}
				}
			}
			// Jiho end

			/*
			if (origRect.width == 0 && origRect.height == 0) { // 새 CombinedFragment 생성하는 경우 Interaction 내 모든 Lifeline을 check

				Set<Entry<Object, EditPart>> allEditPartEntries = getViewer().getEditPartRegistry().entrySet();

				for(Entry<Object, EditPart> epEntry : allEditPartEntries) {
					EditPart ep = epEntry.getValue();
					if( ep instanceof LifelineEditPart ) {
						LifelineEditPart lifelineEditPart = (LifelineEditPart)ep;
						lifelineEditPartsToCheck.add(lifelineEditPart);
						coveredLifelinesToAdd.add((Lifeline)lifelineEditPart.resolveSemanticElement());
					}
				}
			} else { // 새로 생성이 아닌 CF 경계 변경인 경우

				if (origRect.width > newRect.width ) { // width 축소 시 원래 경계 내에 있던 lifeline을 check
					lifelineEditPartsToCheck = ApexSequenceUtil.apexGetPositionallyCoveredLifelineEditParts(origRect, this);	
				} else if (origRect.width < newRect.width) { // width 확대 시 새 경계내에 있는 lifeline을 check
					lifelineEditPartsToCheck = ApexSequenceUtil.apexGetPositionallyCoveredLifelineEditParts(newRect, this);
				}
			}
			*/
			
			// 상하이동의 경우 covereds가 바뀔 일이 없으므로
			// 상하이동이 아닌 경우만 updateCoveredLifelines 호출
			for(Object lifelineEditPartToCheck : lifelineEditPartsToCheck) {
				if(lifelineEditPartToCheck instanceof LifelineEditPart) {
					LifelineEditPart lifelineEditPart = (LifelineEditPart)lifelineEditPartToCheck;
					updateCoveredLifelines(lifelineEditPart, newBound, coveredLifelinesToAdd, coveredLifelinesToRemove, coveredLifelines);
				}
			}	

			/* apex improved end */
			/* apex replaced
			this.getFigure().translateToAbsolute(newBound);
			for(Object child : interactionCompartment.getChildren()) {
				if(child instanceof LifelineEditPart) {
					LifelineEditPart lifelineEditPart = (LifelineEditPart)child;
					updateCoveredLifelines(lifelineEditPart, newBound, coveredLifelinesToAdd, coveredLifelinesToRemove, coveredLifelines);
				}
			}
			*/
		}
		if(!coveredLifelinesToAdd.isEmpty()) {
			CommandHelper.executeCommandWithoutHistory(getEditingDomain(), AddCommand.create(getEditingDomain(), combinedFragment, UMLPackage.eINSTANCE.getInteractionFragment_Covered(), coveredLifelinesToAdd), true);
		}
		if(!coveredLifelinesToRemove.isEmpty()) {
			/* apex added start */
			// Jiho - bounds에서 제외되어 삭제되는 lifeline이 이미 covered가 아닌 경우
			Rectangle origRect = getFigure().getBounds().getCopy();
			for (Lifeline lifeline : coveredLifelinesToRemove) {
				LifelineEditPart lifelineEP = (LifelineEditPart)SequenceUtil.getEditPart(this, lifeline, LifelineEditPart.class);
				if (!coveredLifelines.contains(lifeline) && lifelineEP != null) {
					LifelineDotLineCustomFigure customFigure = lifelineEP.getPrimaryShape().getFigureLifelineDotLineFigure();
					LifelineFigureHelper.showRegion(customFigure, origRect);
				}
			}
			/* apex added end */
			CommandHelper.executeCommandWithoutHistory(getEditingDomain(), RemoveCommand.create(getEditingDomain(), combinedFragment, UMLPackage.eINSTANCE.getInteractionFragment_Covered(), coveredLifelinesToRemove), true);
		}
		/* apex added start */
		if (coveredLifelinesToAdd.isEmpty() && coveredLifelinesToRemove.isEmpty() && !coveredLifelines.isEmpty()) {
			coveredLifelinesToRemove.addAll(coveredLifelines);
			coveredLifelinesToAdd.addAll(coveredLifelines);
			CommandHelper.executeCommandWithoutHistory(getEditingDomain(), RemoveCommand.create(getEditingDomain(), combinedFragment, UMLPackage.eINSTANCE.getInteractionFragment_Covered(), coveredLifelinesToRemove), true);
			CommandHelper.executeCommandWithoutHistory(getEditingDomain(), AddCommand.create(getEditingDomain(), combinedFragment, UMLPackage.eINSTANCE.getInteractionFragment_Covered(), coveredLifelinesToAdd), true);
		}
		/* apex added end */
	}

	/**
	 * apex updated
	 * 
	 * 0.9에서 추가된 메서드
	 * check lifelines in PartDecomposition, see bug https://bugs.eclipse.org/bugs/show_bug.cgi?id=364813
	 * 
	 * @param lifelineEditPart
	 * @param newCFBounds
	 * @param coveredLifelinesToAdd
	 * @param coveredLifelinesToRemove
	 * @param coveredLifelines
	 */
	// check lifelines in PartDecomposition, see bug https://bugs.eclipse.org/bugs/show_bug.cgi?id=364813
	private void updateCoveredLifelines(LifelineEditPart lifelineEditPart, Rectangle newCFBounds, List<Lifeline> coveredLifelinesToAdd, List<Lifeline> coveredLifelinesToRemove, EList<Lifeline> coveredLifelines) {
		Lifeline lifeline = (Lifeline)lifelineEditPart.resolveSemanticElement();

		LifelineDotLineCustomFigure dotLineFigure = lifelineEditPart.getPrimaryShape().getFigureLifelineDotLineFigure();
		Rectangle dotLineBounds = dotLineFigure.getBounds().getCopy();
		Rectangle centralLineBounds = new Rectangle(dotLineBounds.x() + dotLineBounds.width() / 2, dotLineBounds.y(), 1, dotLineBounds.height());
		dotLineFigure.translateToAbsolute(centralLineBounds);

		/* apex improved start */
		Rectangle origRect = this.getFigure().getBounds().getCopy();
		Rectangle newRect = newCFBounds.getCopy();
		this.getFigure().translateToAbsolute(origRect);
		this.getFigure().translateToAbsolute(newRect);

		Rectangle lifelineRect = lifelineEditPart.getFigure().getBounds().getCopy();
		lifelineEditPart.getFigure().translateToAbsolute(lifelineRect);

		// 새 경계와 lifeline 경계가 교차되고
		if (newRect.intersects(centralLineBounds)) {	// Jiho: lifelineRect -> centralLineBounds

			// 원래의 covered에 없던 lifeline이면
			if(!coveredLifelines.contains(lifeline)) {
				// 원래 CF에 포함되어 있으면서 coveredLifelines에 없는 것은
				// Property창에서 수동으로 covereds에서 제외한 것이므로
				// 원래 CF에 포함되지 않았던 경우에만 add
				if (!origRect.intersects(centralLineBounds)) {	// Jiho: lifelineRect -> centralLineBounds
					if (!coveredLifelinesToAdd.contains(lifeline)) {
						coveredLifelinesToAdd.add(lifeline);
					}
				}						
			}
		// 새 경계와 lifeline 경계가 교차되지 않고
		// 원래 covered에 있던 lifeline은 remove
		} else {
			coveredLifelinesToRemove.add(lifeline);			
		}
		/* apex improved end */

		/* apex replaced
		if(newBound.intersects(centralLineBounds)) {
			if(!coveredLifelines.contains(lifeline)) {
				coveredLifelinesToAdd.add(lifeline);
			}
		} else if(coveredLifelines.contains(lifeline)) {
			coveredLifelinesToRemove.add(lifeline);
		}
		*/

		PartDecomposition partDecomposition = lifeline.getDecomposedAs();
		if(partDecomposition != null) {
			List subLifelines = lifelineEditPart.getChildren();
			for(Object child : subLifelines) {
				if(child instanceof LifelineEditPart) {
					updateCoveredLifelines((LifelineEditPart)child, newCFBounds, coveredLifelinesToAdd, coveredLifelinesToRemove, coveredLifelines);
				}
			}
		}
	}

	/**
	 * Find parent editpart of lifeline
	 * @return EditPart
	 */
	public EditPart getInteractionCompartment() {
		EditPart editPart = getParent();
		while (editPart != null
				&& !(editPart instanceof InteractionInteractionCompartmentEditPart)) {
			editPart = editPart.getParent();
		}
		return editPart;
	}

}