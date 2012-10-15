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
 *   Soyatec - Initial API and implementation
 *
 *****************************************************************************/
package org.eclipse.papyrus.uml.diagram.sequence.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.papyrus.uml.diagram.sequence.edit.parts.InteractionFragmentEditPart;
import org.eclipse.papyrus.uml.diagram.sequence.edit.parts.LifelineEditPart;
import org.eclipse.uml2.uml.InteractionFragment;
import org.eclipse.uml2.uml.Lifeline;
import org.eclipse.uml2.uml.UMLPackage;

/**
 * This class updates the property CoveredBy of Lifeline when a Lifeline gets created, moved/resize and also the resize of moving of each CombinedFragment  
 * 
 * @author yyang
 *
 */
public class LifelineCoveredByUpdater {
	protected GraphicalEditPart context;
	protected Map<LifelineEditPart, Rectangle> lifelines = new HashMap<LifelineEditPart, Rectangle>();
	protected HashMap<InteractionFragmentEditPart, Rectangle> interactionFragments = new HashMap<InteractionFragmentEditPart, Rectangle>();
	
	protected List<InteractionFragment> coveredByLifelinesToAdd = new ArrayList<InteractionFragment>();
	protected List<InteractionFragment> coveredByLifelinesToRemove = new ArrayList<InteractionFragment>();
	
	protected TransactionalEditingDomain editingDomain;

	public LifelineCoveredByUpdater() {
	}

	protected void init() {
		editingDomain = this.context.getEditingDomain();
		GraphicalEditPart parent = context;
		while (true) {
			EditPart editPart = parent.getParent();
			if (editPart instanceof GraphicalEditPart) {
				parent = (GraphicalEditPart) editPart;
			} else {
				break;
			}
		}
		childrenCollect(parent);
	}

	private void childrenCollect(GraphicalEditPart editPart) {
		if (editPart instanceof LifelineEditPart) {
			IFigure figure = editPart.getFigure();
			Rectangle childBounds = figure.getBounds().getCopy();
			figure.translateToAbsolute(childBounds);
			Rectangle centralLineBounds = new Rectangle(
					childBounds.x() +  childBounds.width() / 2,
					childBounds.y(), 1,  childBounds.height());
			
			lifelines.put((LifelineEditPart)editPart, centralLineBounds);
		}
		if (editPart instanceof InteractionFragmentEditPart) {
			IFigure figure = editPart.getFigure();
			Rectangle childBounds = figure.getBounds().getCopy();
			figure.translateToAbsolute(childBounds);
			interactionFragments.put((InteractionFragmentEditPart)editPart, childBounds);
		}
		for (Object child : editPart.getChildren()) {
			if (child instanceof GraphicalEditPart) {
				childrenCollect((GraphicalEditPart)child);
			}
		}
	}

	public void update(GraphicalEditPart context) {
		this.context = context;
		this.init();
		
		for (Map.Entry<LifelineEditPart, Rectangle> entry : lifelines.entrySet()) {
			LifelineEditPart editPart = entry.getKey();
			Rectangle childBounds = entry.getValue();
			updateLifeline(editPart, childBounds);
		}
	}
		
	public void updateLifeline(LifelineEditPart lifelineEditpart, Rectangle rect) {
		Lifeline lifeline = (Lifeline) lifelineEditpart.resolveSemanticElement();
		EList<InteractionFragment> coveredByLifelines = lifeline
				.getCoveredBys();

		coveredByLifelinesToAdd.clear();
		coveredByLifelinesToRemove.clear();
		
		for (Map.Entry<InteractionFragmentEditPart, Rectangle> entry : interactionFragments.entrySet()) {
			InteractionFragmentEditPart editPart = entry.getKey();
			Rectangle childBounds = entry.getValue();
			InteractionFragment interactionFragment = (InteractionFragment) editPart
					.resolveSemanticElement();
			if (rect.intersects(childBounds)) {
				if (!coveredByLifelines.contains(interactionFragment)) {
					coveredByLifelinesToAdd.add(interactionFragment);
				}
			} else if (coveredByLifelines.contains(interactionFragment)) {
				coveredByLifelinesToRemove.add(interactionFragment);
			}
		}

		if (!coveredByLifelinesToAdd.isEmpty()) {
			CommandHelper.executeCommandWithoutHistory(editingDomain,
					AddCommand.create(editingDomain, lifeline,
							UMLPackage.eINSTANCE.getLifeline_CoveredBy(),
							coveredByLifelinesToAdd), true);
		}
		if (!coveredByLifelinesToRemove.isEmpty()) {
			CommandHelper.executeCommandWithoutHistory(editingDomain,
					RemoveCommand.create(editingDomain, lifeline,
							UMLPackage.eINSTANCE.getLifeline_CoveredBy(),
							coveredByLifelinesToRemove), true);
		}
	}
}
