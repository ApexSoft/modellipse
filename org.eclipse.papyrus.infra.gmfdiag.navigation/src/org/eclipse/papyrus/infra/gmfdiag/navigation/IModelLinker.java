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
 *  Mathieu Velten (Atos Origin) mathieu.velten@atosorigin.com - Initial API and implementation
 *
 *****************************************************************************/
package org.eclipse.papyrus.infra.gmfdiag.navigation;

import org.eclipse.emf.ecore.EObject;

/**
 * This interface is used to link an element to the original model.
 * 
 * @author mvelten
 * 
 */
public interface IModelLinker {

	public void linkToModel(EObject toLink);
}
