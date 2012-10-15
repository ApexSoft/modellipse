/*****************************************************************************
 * Copyright (c) 2010 CEA LIST.
 *    
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Camille Letavernier (CEA LIST) camille.letavernier@cea.fr - Initial API and implementation
 *****************************************************************************/
package org.eclipse.papyrus.infra.constraints.constraints;

/**
 * A Constraint always returning true.
 * 
 * @author Camille Letavernier
 */
public class TrueConstraint extends AbstractConstraint {

	@Override
	public boolean match(Object selection) {
		return true;
	}

	@Override
	protected boolean equivalent(Constraint constraint) {
		//return constraint != null && constraint instanceof TrueConstraint;
		return false; //TrueConstraint is always true ; it shouldn't override another "always true" constraint
	}

}
