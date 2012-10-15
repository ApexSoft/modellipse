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
package org.eclipse.papyrus.infra.core.utils;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;
import org.eclipse.papyrus.infra.core.sasheditor.contentprovider.IPageMngr;

/**
 * The Class OpenDiagramCommand.
 * 
 * @deprecated This method use GMF stuff. It should not be in the core package.
 */
public class OpenDiagramCommand extends AbstractTransactionalCommand {

	/** The diagram to open. */
	private EObject diagramToOpen = null;

	private ICommand previousCreateDiagramCommand = null;

	/**
	 * Instantiates a new open diagram command.
	 * 
	 * @param domain
	 *        the domain
	 * @param diagram
	 *        the diagram
	 */
	public OpenDiagramCommand(TransactionalEditingDomain editingDomain, EObject diagram) {
		super(editingDomain, "Open diagram", null);
		diagramToOpen = diagram;
	}

	public OpenDiagramCommand(TransactionalEditingDomain editingDomain, ICommand previousCreateDiagramCommand) {
		super(editingDomain, "Open diagram", null);
		this.previousCreateDiagramCommand = previousCreateDiagramCommand;
	}

	/**
	 * {@inheritedDoc}
	 */
	@Override
	protected CommandResult doExecuteWithResult(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
		try {
			if(diagramToOpen == null && previousCreateDiagramCommand != null) {
				Object possibleDiagramToOpen = previousCreateDiagramCommand.getCommandResult().getReturnValue();
				if(possibleDiagramToOpen instanceof EObject) {
					diagramToOpen = (EObject)possibleDiagramToOpen;
				}
			}


			
			if(diagramToOpen != null) {
				IPageMngr pageMngr;
				
				// bug 358799
				// The command is also called during the initialiation phase. In this case, the
				// EditorsUtils.getServiceRegistry() method return the wrong ServiceREgistry.
				// Disable this call, and use the more costly way to get the IPageMngr
				// TODO : provide a better way of getting the IPageMngr.
				/*final ServicesRegistry serviceRegistry = EditorUtils.getServiceRegistry();
				if(serviceRegistry != null) {
					pageMngr =serviceRegistry.getService(IPageMngr.class);
				} else */
				if(getEditingDomain().getResourceSet() instanceof DiResourceSet){
					DiResourceSet diResourceSet = (DiResourceSet)getEditingDomain().getResourceSet();
					pageMngr = EditorUtils.getIPageMngr(diResourceSet.getDiResource());
				} else {
					throw new IllegalStateException("Enable to get the page manager");////$NON-NLS-1$
				}
				

				if(pageMngr.isOpen(diagramToOpen)) {
					pageMngr.closePage(diagramToOpen);
				}
				pageMngr.openPage(diagramToOpen);
			}

			return CommandResult.newOKCommandResult();
		} catch (Exception e) {
			throw new ExecutionException("Can't open diagram", e);
		}
	}
}
