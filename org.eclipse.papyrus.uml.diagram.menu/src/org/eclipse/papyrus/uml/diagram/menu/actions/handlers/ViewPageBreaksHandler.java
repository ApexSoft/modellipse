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

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.gmf.runtime.diagram.ui.internal.properties.WorkspaceViewerProperties;
import org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramGraphicalViewer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;

/**
 * 
 * Handler for the View Page Breaks Action
 * 
 * 
 */
@SuppressWarnings("restriction")
public class ViewPageBreaksHandler extends AbstractViewHandler {


	/**
	 * 
	 * Constructor.
	 * 
	 */
	public ViewPageBreaksHandler() {
		super(WorkspaceViewerProperties.VIEWPAGEBREAKS);
	}

	/**
	 * 
	 * @see org.eclipse.papyrus.uml.diagram.menu.actions.handlers.AbstractViewHandler#execute(org.eclipse.core.commands.ExecutionEvent)
	 * 
	 * @param event
	 * @return
	 * @throws ExecutionException
	 */
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		super.execute(event);
		IDiagramGraphicalViewer viewer = getDiagramGraphicalViewer();
		ISelection tmp = viewer.getSelection();

		//trick to force easily the change of status of Recalculate Break Pages when the selection has not changed!
		viewer.setSelection(new StructuredSelection());
		viewer.setSelection(tmp);
		return null;
	}
}
