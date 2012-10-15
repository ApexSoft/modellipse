/*****************************************************************************
 * Copyright (c) 2011 CEA LIST.
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
package org.eclipse.papyrus.uml.diagram.menu.providers;

import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.emf.core.util.PackageUtil;
import org.eclipse.gmf.runtime.notation.Routing;
import org.eclipse.jface.commands.ToggleState;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.papyrus.uml.diagram.menu.actions.LineStyleAction;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

/**
 * abstract class for the Line Style action, which are represented with toggle buttons
 * 
 * 
 * 
 */
public abstract class AbstractLineStyleToggleState extends ToggleState implements ISelectionListener {

	/** the parameter for this toggle */
	private String parameter;

	/** the selection service */
	private ISelectionService serv = null;

	/**
	 * 
	 * Constructor.
	 * 
	 * @param parameter
	 *        the parameter for this toggle
	 */
	public AbstractLineStyleToggleState(String parameter) {
		this.parameter = parameter;
		setValue(false);
		addSelectionListener();
		if(serv != null) {
			updateStatus(serv.getSelection());
		}
	}

	/***
	 * Add a listener on the selection, in order to refresh the status of the action when the selection changes
	 */
	protected void addSelectionListener() {
		//when we are in the Menu, we need to refresh
		if(serv == null) {//should be always!=null after the first call to getCommand()
			IWorkbench workbench = PlatformUI.getWorkbench();

			if(workbench != null) {
				IWorkbenchWindow workbenchWindow = workbench.getActiveWorkbenchWindow();
				if(workbenchWindow != null) {
					this.serv = workbenchWindow.getSelectionService();
					if(this.serv != null) {
						serv.addSelectionListener(this);
					}
				}
			}
		}
	}

	/**
	 * 
	 * @see org.eclipse.ui.ISelectionListener#selectionChanged(org.eclipse.ui.IWorkbenchPart, org.eclipse.jface.viewers.ISelection)
	 * 
	 * @param part
	 * @param selection
	 */
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		updateStatus(selection);
	}

	/**
	 * 
	 * @param selection
	 *        update the status of the lineStyleAction
	 */
	protected void updateStatus(ISelection selection) {
		boolean newValue = false;
		if(selection instanceof IStructuredSelection) {
			Object firstSelectedElement = ((IStructuredSelection)selection).getFirstElement();
			if(firstSelectedElement instanceof IGraphicalEditPart) {
				ENamedElement element = PackageUtil.getElement(LineStyleAction.PROPERTY_ID);
				Object value = null;
				if(element instanceof EStructuralFeature) {
					value = ((IGraphicalEditPart)firstSelectedElement).getStructuralFeatureValue((EStructuralFeature)element);

					if(this.parameter.equals(LineStyleAction.OBLIQUE)) {
						if(Routing.MANUAL_LITERAL.equals(value)) {
							newValue = true;
						}
					} else if(this.parameter.equals(LineStyleAction.RECTILINEAR)) {
						if(Routing.RECTILINEAR_LITERAL.equals(value)) {
							newValue = true;
						}
					} else if(this.parameter.equals(LineStyleAction.TREE)) {
						if(Routing.TREE_LITERAL.equals(value)) {
							newValue = true;
						}
					}
				}
			}
		}
		//		((DiagramGraphicalViewer)getDiagramGraphicalViewer()).getWorkspaceViewerPreferenceStore().setValue(id, !isChecked());
		setValue(newValue);
	}

	/**
	 * 
	 * @see org.eclipse.core.commands.State#dispose()
	 * 
	 */
	@Override
	public void dispose() {
		if(serv != null) {
			serv.removeSelectionListener(this);
		}
		super.dispose();
	}
}
