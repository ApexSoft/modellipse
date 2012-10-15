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
 *  Vincent Lorenzo (CEA LIST) vincent.lorenzo@cea.fr - Initial API and implementation
 *
 *****************************************************************************/
package org.eclipse.papyrus.uml.diagram.profile.edit.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.eclipse.papyrus.uml.diagram.profile.edit.commands.DataTypePropertyCreateCommand;
import org.eclipse.papyrus.uml.diagram.profile.providers.UMLElementTypes;

/**
 * @generated
 */
public class DataTypeAttributeCompartmentItemSemanticEditPolicyCN extends UMLBaseItemSemanticEditPolicy {

	/**
	 * @generated
	 */
	public DataTypeAttributeCompartmentItemSemanticEditPolicyCN() {
		super(UMLElementTypes.DataType_3027);
	}


	/**
	 * @generated
	 */
	protected Command getCreateCommand(CreateElementRequest req) {
		if(UMLElementTypes.Property_3018 == req.getElementType()) {
			return getGEFWrapper(new DataTypePropertyCreateCommand(req));
		}
		return super.getCreateCommand(req);
	}

}
