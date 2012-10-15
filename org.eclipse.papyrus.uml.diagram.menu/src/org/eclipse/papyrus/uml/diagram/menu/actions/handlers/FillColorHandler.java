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
package org.eclipse.papyrus.uml.diagram.menu.actions.handlers;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.gef.commands.Command;
import org.eclipse.papyrus.uml.diagram.menu.actions.AbstractColorAction;
import org.eclipse.papyrus.uml.diagram.menu.actions.FillColorAction;

/**
 * The handler for the {@link FillColorAction}
 * 
 * 
 * 
 */
public class FillColorHandler extends AbstractGraphicalCommandHandler {

	/** id of the parameter for the ZOrderAction */
	public static final String parameterID = "color_parameter"; //$NON-NLS-1$

	/**
	 * 
	 * Constructor.
	 * 
	 * @param parameter
	 *        parameter for the arrange action
	 */
	public FillColorHandler(String parameter) {
		super(parameterID, parameter);
	}

	/**
	 * 
	 * Constructor.
	 * 
	 * @param parameter
	 *        parameter for the arrange action
	 */
	public FillColorHandler() {
		//when we have no parameter, we fill in black!
		super(parameterID, AbstractColorAction.BLACK_.colorName);
	}

	/**
	 * 
	 * @see org.eclipse.papyrus.uml.diagram.common.handlers.GraphicalCommandHandler#getCommand()
	 * 
	 * @return
	 * @throws ExecutionException
	 */
	@Override
	protected Command getCommand() throws ExecutionException {
		AbstractColorAction action = new FillColorAction(parameter, getSelectedElements());
		return action.getCommand();
	}
}
