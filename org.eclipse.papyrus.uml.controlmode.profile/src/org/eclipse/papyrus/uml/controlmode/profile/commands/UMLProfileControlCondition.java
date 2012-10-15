/*****************************************************************************
 * Copyright (c) 2010 Atos Origin.
 *
 *    
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Emilien Perico (Atos Origin) emilien.perico@atosorigin.com - Initial API and implementation
 *
 *****************************************************************************/
package org.eclipse.papyrus.uml.controlmode.profile.commands;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.papyrus.infra.services.controlmode.commands.IControlCondition;
import org.eclipse.uml2.uml.Package;


public class UMLProfileControlCondition implements IControlCondition {

	/**
	 * {@inheritDoc}
	 */
	public boolean enableControl(EObject selection) {
		// enable Control action on package only
		return selection instanceof Package;
	}

}
