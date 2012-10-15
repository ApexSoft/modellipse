/*****************************************************************************
 * Copyright (c) 2011 CEA LIST.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *		
 *		CEA LIST - Initial API and implementation
 *
 *****************************************************************************/
package org.eclipse.papyrus.uml.diagram.pkg.provider;

import org.eclipse.gef.EditPart;
import org.eclipse.papyrus.uml.diagram.common.editpolicies.NavigationEditPolicy;

public class CustomEditPolicyProvider extends PackageDiagramEditPolicyProvider {

	public void createEditPolicies(EditPart editPart) {
		super.createEditPolicies(editPart);
		editPart.installEditPolicy(NavigationEditPolicy.NAVIGATION_POLICY, new NavigationEditPolicy());
	}

}
