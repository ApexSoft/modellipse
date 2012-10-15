/*****************************************************************************
 * Copyright (c) 2011 CEA LIST.
 *
 *    
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *	Amine EL KOUHEN (CEA LIST/LIFL) - Amine.El-Kouhen@lifl.fr 
 *****************************************************************************/
package org.eclipse.papyrus.uml.diagram.component.edit.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.eclipse.papyrus.uml.diagram.component.edit.commands.CommentCreateCommandPCN;
import org.eclipse.papyrus.uml.diagram.component.edit.commands.ComponentCreateCommandPCN;
import org.eclipse.papyrus.uml.diagram.component.edit.commands.ConstraintCreateCommandPCN;
import org.eclipse.papyrus.uml.diagram.component.edit.commands.InterfaceCreateCommandPCN;
import org.eclipse.papyrus.uml.diagram.component.providers.UMLElementTypes;

// TODO: Auto-generated Javadoc
/**
 * The Class PackagePackageableElementCompartmentItemSemanticEditPolicy.
 * 
 * @generated
 */
public class PackagePackageableElementCompartmentItemSemanticEditPolicy extends UMLBaseItemSemanticEditPolicy {

	/**
	 * Instantiates a new package packageable element compartment item semantic edit policy.
	 * 
	 * @generated
	 */
	public PackagePackageableElementCompartmentItemSemanticEditPolicy() {
		super(UMLElementTypes.Package_3200);
	}

	/**
	 * Gets the creates the command.
	 * 
	 * @param req
	 *        the req
	 * @return the creates the command
	 * @generated
	 */
	protected Command getCreateCommand(CreateElementRequest req) {
		if(UMLElementTypes.Interface_3072 == req.getElementType()) {
			return getGEFWrapper(new InterfaceCreateCommandPCN(req));
		}
		if(UMLElementTypes.Comment_3074 == req.getElementType()) {
			return getGEFWrapper(new CommentCreateCommandPCN(req));
		}
		if(UMLElementTypes.Constraint_3075 == req.getElementType()) {
			return getGEFWrapper(new ConstraintCreateCommandPCN(req));
		}
		if(UMLElementTypes.Component_3071 == req.getElementType()) {
			return getGEFWrapper(new ComponentCreateCommandPCN(req));
		}
		return super.getCreateCommand(req);
	}

}
