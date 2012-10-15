/*****************************************************************************
 * Copyright (c) 2010 CEA LIST.
 *
 *    
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Saadia DHOUIB (CEA LIST) saadia.dhouib@cea.fr - Initial API and implementation
 *
 *****************************************************************************/
package org.eclipse.papyrus.uml.diagram.communication.custom.factory;

import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.papyrus.uml.diagram.communication.custom.edit.parts.CustomInteractionEditPart;
import org.eclipse.papyrus.uml.diagram.communication.custom.edit.parts.CustomLifelineEditPartCN;
import org.eclipse.papyrus.uml.diagram.communication.custom.edit.parts.CustomMessageEditPart;
import org.eclipse.papyrus.uml.diagram.communication.custom.edit.parts.CustomMessageNameEditPart;
import org.eclipse.papyrus.uml.diagram.communication.edit.parts.InteractionEditPart;
import org.eclipse.papyrus.uml.diagram.communication.edit.parts.LifelineEditPartCN;
import org.eclipse.papyrus.uml.diagram.communication.edit.parts.MessageEditPart;
import org.eclipse.papyrus.uml.diagram.communication.edit.parts.MessageNameEditPart;
import org.eclipse.papyrus.uml.diagram.communication.edit.parts.UMLEditPartFactory;
import org.eclipse.papyrus.uml.diagram.communication.part.UMLVisualIDRegistry;

// TODO: Auto-generated Javadoc
/**
 * This class is used to create the custom edit parts.
 */
public class CustomUMLeditPartFactory extends UMLEditPartFactory {

	/**
	 * Creates a new CustomUMLeditPart object.
	 *
	 * @param context the context
	 * @param model the model
	 * @return the custom edit part
	 * @see org.eclipse.papyrus.uml.diagram.communication.edit.parts.UMLEditPartFactory#createEditPart(org.eclipse.gef.EditPart, java.lang.Object)
	 */
	@Override
	public EditPart createEditPart(EditPart context, Object model) {
		if(model instanceof View) {
			View view = (View)model;
			switch(UMLVisualIDRegistry.getVisualID(view)) {
			case LifelineEditPartCN.VISUAL_ID:
				return new CustomLifelineEditPartCN(view);
			case MessageNameEditPart.VISUAL_ID:
				return new CustomMessageNameEditPart(view);
			case MessageEditPart.VISUAL_ID:
				return new CustomMessageEditPart(view);
			case InteractionEditPart.VISUAL_ID:
				return new CustomInteractionEditPart(view);
			}
		}
		return super.createEditPart(context, model);
	}
}
