/*****************************************************************************
 * Copyright (c) 2009-2011 CEA LIST.
 *
 *    
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Yann Tanguy (CEA LIST) yann.tanguy@cea.fr - Initial API and implementation
 *
 *****************************************************************************/
package org.eclipse.papyrus.uml.diagram.composite.edit.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.eclipse.papyrus.uml.diagram.composite.edit.commands.CollaborationRoleCreateCommandCN;
import org.eclipse.papyrus.uml.diagram.composite.edit.commands.CollaborationUseCreateCommandCN;
import org.eclipse.papyrus.uml.diagram.composite.edit.commands.CommentCreateCommandCN;
import org.eclipse.papyrus.uml.diagram.composite.edit.commands.ConstraintCreateCommandCN;
import org.eclipse.papyrus.uml.diagram.composite.edit.commands.DurationConstraintCreateCommandCN;
import org.eclipse.papyrus.uml.diagram.composite.edit.commands.InteractionConstraintCreateCommandCN;
import org.eclipse.papyrus.uml.diagram.composite.edit.commands.IntervalConstraintCreateCommandCN;
import org.eclipse.papyrus.uml.diagram.composite.edit.commands.PropertyPartCreateCommandCN;
import org.eclipse.papyrus.uml.diagram.composite.edit.commands.TimeConstraintCreateCommandCN;
import org.eclipse.papyrus.uml.diagram.composite.providers.UMLElementTypes;

/**
 * @generated
 */
public class CollaborationCompositeCompartmentItemSemanticEditPolicyCN extends UMLBaseItemSemanticEditPolicy {

	/**
	 * @generated
	 */
	public CollaborationCompositeCompartmentItemSemanticEditPolicyCN() {
		super(UMLElementTypes.Collaboration_3086);
	}

	/**
	 * @generated
	 */
	protected Command getCreateCommand(CreateElementRequest req) {
		if(UMLElementTypes.Property_3070 == req.getElementType()) {
			return getGEFWrapper(new PropertyPartCreateCommandCN(req));
		}
		if(UMLElementTypes.CollaborationUse_3071 == req.getElementType()) {
			return getGEFWrapper(new CollaborationUseCreateCommandCN(req));
		}
		if(UMLElementTypes.Comment_3097 == req.getElementType()) {
			return getGEFWrapper(new CommentCreateCommandCN(req));
		}
		if(UMLElementTypes.ConnectableElement_3115 == req.getElementType()) {
			return getGEFWrapper(new CollaborationRoleCreateCommandCN(req));
		}
		if(UMLElementTypes.DurationConstraint_3116 == req.getElementType()) {
			return getGEFWrapper(new DurationConstraintCreateCommandCN(req));
		}
		if(UMLElementTypes.TimeConstraint_3117 == req.getElementType()) {
			return getGEFWrapper(new TimeConstraintCreateCommandCN(req));
		}
		if(UMLElementTypes.IntervalConstraint_3118 == req.getElementType()) {
			return getGEFWrapper(new IntervalConstraintCreateCommandCN(req));
		}
		if(UMLElementTypes.InteractionConstraint_3119 == req.getElementType()) {
			return getGEFWrapper(new InteractionConstraintCreateCommandCN(req));
		}
		if(UMLElementTypes.Constraint_3120 == req.getElementType()) {
			return getGEFWrapper(new ConstraintCreateCommandCN(req));
		}
		return super.getCreateCommand(req);
	}

}
