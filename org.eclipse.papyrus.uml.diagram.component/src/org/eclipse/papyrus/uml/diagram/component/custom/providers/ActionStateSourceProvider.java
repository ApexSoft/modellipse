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
package org.eclipse.papyrus.uml.diagram.component.custom.providers;

import org.eclipse.papyrus.uml.diagram.common.providers.AbstractActionStateSourceProvider;
import org.eclipse.papyrus.uml.diagram.component.custom.actions.ShowHideRelatedContentsHandler;
import org.eclipse.ui.ISources;

// TODO: Auto-generated Javadoc
/**
 * This class provides the state of the actions. It's used to refresh
 * the status of these actions in the menu. (in toolbar and popup, it's not needed)
 * 
 * To get the status, we listen the selection service AND the part service!
 * The part service is used to know if the selection is in the Model Explorer or not!
 * When the selection is not in the model explorer, the handlers listening the variable need to be disabled
 */
public class ActionStateSourceProvider extends AbstractActionStateSourceProvider {

	/**
	 * The name of the variable to check.
	 */
	public static final String SHOW_HIDE_RELATED_CONTENTS = "showHideRelatedContents"; //$NON-NLS-1$

	/**
	 * 
	 * Constructor.
	 * 
	 */
	public ActionStateSourceProvider() {
		super();
		currentState.put(SHOW_HIDE_RELATED_CONTENTS, DISABLED);
	}


	/**
	 * Gets the provided source names.
	 *
	 * @return the provided source names
	 * @see org.eclipse.ui.ISourceProvider#getProvidedSourceNames()
	 */
	@Override
	public String[] getProvidedSourceNames() {
		return new String[]{ SHOW_HIDE_RELATED_CONTENTS };
	}


	/**
	 * Refresh the state of the Delete Action.
	 */
	protected void refreshShowHideRelatedContentsAction() {
		String oldState = currentState.get(SHOW_HIDE_RELATED_CONTENTS);
		String newState = (testShowHideRelatedContents() ? ENABLED : DISABLED);

		if(oldState != newState) {
			currentState.put(SHOW_HIDE_RELATED_CONTENTS, newState);
			fireSourceChanged(ISources.WORKBENCH, currentState);
		}
	}

	/**
	 * Tests if the action DeleteFromDiagram (now called Delete Selected Element can be executed.
	 *
	 * @return true, if successful
	 * <code>true</code> if the action DeleteFromDiagram (now called Delete Selected Element can be executed <code>false</code> if not
	 */
	protected boolean testShowHideRelatedContents() {
		ShowHideRelatedContentsHandler handler = new ShowHideRelatedContentsHandler();
		return isSelectionInDiagram() && handler.isEnabled();
	}

	/**
	 * Refresh actions.
	 *
	 * @see org.eclipse.papyrus.uml.diagram.common.providers.AbstractActionStateSourceProvider#refreshActions()
	 */
	@Override
	protected void refreshActions() {
		refreshShowHideRelatedContentsAction();
	}
}
