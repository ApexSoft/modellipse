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
 *  Saadia Dhouib saadia.dhouib@cea.fr  
 *
 *****************************************************************************/
package org.eclipse.papyrus.uml.diagram.communication.edit.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.eclipse.papyrus.uml.diagram.communication.edit.commands.CommentCreateCommandCN;
import org.eclipse.papyrus.uml.diagram.communication.edit.commands.ConstraintCreateCommandCN;
import org.eclipse.papyrus.uml.diagram.communication.edit.commands.DurationObservationCreateCommandCN;
import org.eclipse.papyrus.uml.diagram.communication.edit.commands.LifelineCreateCommandCN;
import org.eclipse.papyrus.uml.diagram.communication.edit.commands.TimeObservationCreateCommandCN;
import org.eclipse.papyrus.uml.diagram.communication.providers.UMLElementTypes;

/**
 * @generated
 */
public class InteractionCompartmentItemSemanticEditPolicy extends UMLBaseItemSemanticEditPolicy {

	/**
	 * @generated
	 */
	public InteractionCompartmentItemSemanticEditPolicy() {
		super(UMLElementTypes.Interaction_8002);
	}


	/**
	 * @generated
	 */
	protected Command getCreateCommand(CreateElementRequest req) {
		if(UMLElementTypes.Lifeline_8001 == req.getElementType()) {
			return getGEFWrapper(new LifelineCreateCommandCN(req));
		}
		if(UMLElementTypes.Comment_8005 == req.getElementType()) {
			return getGEFWrapper(new CommentCreateCommandCN(req));
		}
		if(UMLElementTypes.Constraint_8004 == req.getElementType()) {
			return getGEFWrapper(new ConstraintCreateCommandCN(req));
		}
		if(UMLElementTypes.TimeObservation_8006 == req.getElementType()) {
			return getGEFWrapper(new TimeObservationCreateCommandCN(req));
		}
		if(UMLElementTypes.DurationObservation_8007 == req.getElementType()) {
			return getGEFWrapper(new DurationObservationCreateCommandCN(req));
		}
		return super.getCreateCommand(req);
	}

}
