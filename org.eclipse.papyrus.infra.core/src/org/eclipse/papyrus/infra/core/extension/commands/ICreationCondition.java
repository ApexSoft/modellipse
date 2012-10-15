/*****************************************************************************
 * Copyright (c) 2009 ATOS ORIGIN.
 *
 *    
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 *  Tristan Faure (ATOS ORIGIN) tristan.faure@atosorigin.com - Initial API and implementation
 *
 *****************************************************************************/
package org.eclipse.papyrus.infra.core.extension.commands;

import org.eclipse.emf.ecore.EObject;

public interface ICreationCondition {

	/**
	 * This method returns true if the diagram creation is allowed
	 * 
	 * @param selectedElement
	 *        the element where the diagram is provided
	 * @return true if the diagram can be created
	 */
	boolean create(EObject selectedElement);

	/**
	 * set the command ID in order to take account the environment in order to
	 * create a diagram
	 * 
	 * @param commandID
	 */
	public void setCommand(String commandID);

}
