/*****************************************************************************
 * Copyright (c) 2009 CEA LIST.
 *
 *    
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Remi Schnekenburger (CEA LIST) - Initial API and implementation
 *
 *****************************************************************************/
package org.eclipse.papyrus.uml.diagram.common.editpolicies;

import org.eclipse.uml2.uml.Substitution;


/**
 * Edit Policy for Applied Stereotype Label for {@link Substitution}.
 */
public class AppliedStereotypeSubstitutionLabelDisplayEditPolicy extends AppliedStereotypeLinkLabelDisplayEditPolicy {

	/**
	 * Creates the EditPolicy, with the correct tag.
	 */
	public AppliedStereotypeSubstitutionLabelDisplayEditPolicy() {
		super("substitution"); //$NON-NLS-1$
	}

}
