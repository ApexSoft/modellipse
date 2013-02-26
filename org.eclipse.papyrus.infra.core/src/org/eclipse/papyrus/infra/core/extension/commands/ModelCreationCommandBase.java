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
 *  Tatiana Fesenko (CEA LIST) - Initial API and implementation
 *
 *****************************************************************************/
package org.eclipse.papyrus.infra.core.extension.commands;

import java.util.Collections;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;
import org.eclipse.papyrus.commands.wrappers.GMFtoEMFCommandWrapper;
import org.eclipse.papyrus.infra.core.utils.DiResourceSet;

/**
 * The Class ModelCreationCommandBase.
 */
public abstract class ModelCreationCommandBase implements IModelCreationCommand {

	/**
	 * @see org.eclipse.papyrus.infra.core.extension.commands.IModelCreationCommand#createModel(org.eclipse.papyrus.infra.core.utils.DiResourceSet)
	 * 
	 * @param diResourceSet
	 */
	public void createModel(final DiResourceSet diResourceSet) {
		TransactionalEditingDomain transactionalEditingDomain = diResourceSet.getTransactionalEditingDomain();
		RecordingCommand command = new RecordingCommand(transactionalEditingDomain) {

			@Override
			protected void doExecute() {
				runAsTransaction(diResourceSet);
			}
		};
		transactionalEditingDomain.getCommandStack().execute(command);
	}

	/**
	 * apex updated
	 * 
	 * 초기 model root 이름을 CreateUMLModelCommand에서 "model"로 하드코딩된 것 대신
	 * di resource 이름과 같게 설정되도록 처리
	 * 
	 * Run as transaction.
	 * 
	 * @param diResourceSet
	 *        the di resource set
	 */
	protected void runAsTransaction(final DiResourceSet diResourceSet) {
		// Get the uml element to which the newly created diagram will be
		// attached.
		// Create the diagram
		final Resource modelResource = diResourceSet.getModelResource();
		TransactionalEditingDomain editingDomain = diResourceSet.getTransactionalEditingDomain();

		AbstractTransactionalCommand command = new AbstractTransactionalCommand(editingDomain, "Initialize model", Collections.EMPTY_LIST) {

			@Override
			protected CommandResult doExecuteWithResult(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {

				CommandResult commandResult = CommandResult.newErrorCommandResult("Error during diagram creation");
				EObject model = getRootElement(modelResource);
				attachModelToResource(model, modelResource);

				/* apex improved start */
				initializeModel(model, modelResource);
				/* apex improved end */
				/* apex replaced
				initializeModel(model);
				*/
				
				return CommandResult.newOKCommandResult();

			}
		};
		editingDomain.getCommandStack().execute(new GMFtoEMFCommandWrapper(command));
	}

	/**
	 * Initialize model.
	 * 
	 * @param owner
	 *        the owner
	 */
	protected void initializeModel(EObject owner) {

	}
	

	/**
	 * apex added
	 * 
	 * 모델의 root 이름을 di 파일명과 같게 처리
	 * 
	 * @see org.eclipse.papyrus.infra.core.extension.commands.ModelCreationCommandBase#initializeModel(org.eclipse.emf.ecore.EObject)
	 * 
	 * @param owner
	 */

	protected void initializeModel(EObject owner, Resource resource) {
		initializeModel(owner);
		((org.eclipse.uml2.uml.Package)owner).setName(getModelName(resource));
	}
	

	
	/**
	 * apex added
	 * 
	 * resource에서 di 파일명을 가져와서 return
	 * 
	 * Gets the model name.
	 * 
	 * @param resource  di 파일
	 * 
	 * @return the model name
	 */
	protected String getModelName(Resource resource) {
		URI uri = resource.getURI().trimFileExtension();
		String path = uri.path();
		String fileName = path.substring(path.lastIndexOf('/')+1);
		return fileName;
	}

	/**
	 * Get the root element associated with canvas.
	 * 
	 * @param modelResource
	 *        the model resource
	 * @return the root element
	 */
	protected EObject getRootElement(Resource modelResource) {
		EObject rootElement = null;
		if(modelResource != null && modelResource.getContents() != null && modelResource.getContents().size() > 0) {
			Object root = modelResource.getContents().get(0);
			if(root instanceof EObject) {
				rootElement = (EObject)root;
			}
		} else {
			rootElement = createRootElement();
		}
		return rootElement;
	}

	/**
	 * Store model element in the resource.
	 */
	protected void attachModelToResource(EObject root, Resource resource) {
		resource.getContents().add(root);
	}

	/**
	 * Create the root element of an EMF model
	 * 
	 * @return the root element
	 */
	protected abstract EObject createRootElement();

}
