/*****************************************************************************
 * Copyright (c) 2011 Atos.
 *
 *    
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Atos - Initial API and implementation
 *   Arthur Daussy - arthur.daussy@atos.net - Bug 368932 - [ActivitiyDiagram] Prevent Compartment of Activity group to be selected
 *
 *****************************************************************************/
package org.eclipse.papyrus.uml.diagram.activity.edit.part.ActivityGroup;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.ScrollPane;
import org.eclipse.gmf.runtime.diagram.ui.figures.ResizableCompartmentFigure;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.papyrus.uml.diagram.activity.edit.parts.StructuredActivityNodeStructuredActivityNodeContentCompartmentEditPart;

/**
 * Set in order to have Generation Gap Pattern.
 * 
 * @author arthur daussy
 * 
 */
public class CustomStructuredActivityNodeStructuredActivityNodeContentCompartmentEditPart extends StructuredActivityNodeStructuredActivityNodeContentCompartmentEditPart implements IGroupCompartmentEditPart {

	public CustomStructuredActivityNodeStructuredActivityNodeContentCompartmentEditPart(View view) {
		super(view);
	}

	/**
	 * Remove polluting scroll bar
	 */
	@Override
	public IFigure createFigure() {
		ResizableCompartmentFigure result = (ResizableCompartmentFigure)super.createFigure();
		result.setTitleVisibility(false);
		// remove the top border
		result.setBorder(null);
		// Hide scrollBar
		result.getScrollPane().setEnabled(false);
		result.getScrollPane().setHorizontalScrollBar(null);
		result.getScrollPane().setVerticalScrollBar(null);
		result.getScrollPane().setScrollBarVisibility(ScrollPane.NEVER);
		return result;
	}

	/**
	 * Make this compartment unselectable
	 */
	@Override
	public boolean isSelectable() {
		return false;
	}
}
