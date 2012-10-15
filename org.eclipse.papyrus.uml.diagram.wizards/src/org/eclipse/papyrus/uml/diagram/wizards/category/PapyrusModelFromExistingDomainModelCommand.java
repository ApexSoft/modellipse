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
package org.eclipse.papyrus.uml.diagram.wizards.category;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IPath;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.papyrus.infra.core.resource.IModel;
import org.eclipse.papyrus.infra.core.resource.ModelSet;
import org.eclipse.papyrus.infra.core.resource.ModelUtils;
import org.eclipse.papyrus.infra.core.resource.notation.NotationModel;
import org.eclipse.papyrus.infra.core.resource.sasheditor.DiModel;
import org.eclipse.papyrus.infra.core.resource.uml.UmlModel;
import org.eclipse.papyrus.uml.diagram.wizards.CreateModelWizard.DiResourceSetExt;

/**
 * The command to initialize Papyrus diagram for a given domain model.
 */
public class PapyrusModelFromExistingDomainModelCommand extends RecordingCommand {

	/** The my di resource set. */
	private final ModelSet myDiResourceSet;

	/** The my file name without extension. */
	private final IPath myFileNameWithoutExtension;

	/** The my root. */
	private final EObject myRoot;

	/**
	 * Instantiates a new papyrus model from existing domain model command.
	 *
	 * @param diResourceSet the di resource set
	 * @param newFile the new file
	 * @param root the root
	 */
	public PapyrusModelFromExistingDomainModelCommand(ModelSet diResourceSet, IFile newFile, EObject root) {
		super(diResourceSet.getTransactionalEditingDomain());
		myDiResourceSet = diResourceSet;
		myFileNameWithoutExtension = newFile.getFullPath().removeFileExtension();
		// Bug 339504 - [Wizard] NPE when init diagram from an existing model
		((DiResourceSetExt)diResourceSet).setFilenameWithoutExtension(myFileNameWithoutExtension);
		myRoot = root;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.transaction.RecordingCommand#doExecute()
	 */
	@Override
	protected void doExecute() {
		IModel model = myDiResourceSet.getModel(DiModel.MODEL_ID);
		model.createModel(myFileNameWithoutExtension);
		model = myDiResourceSet.getModel(NotationModel.MODEL_ID);
		model.createModel(myFileNameWithoutExtension);
		// START OF WORKAROUND for #315083 
		IModel umlModel = new UmlModel() {

			public void createModel(IPath fullPath) {
				try {
					resourceURI = myRoot.eResource().getURI();
					// as resource already exists, use rs.getResource() not rs.createResource() here
					try {
						resource = getResourceSet().getResource(resourceURI, true);
					}
					catch (WrappedException e){
						if (ModelUtils.isDegradedModeAllowed(e.getCause())){
							// in this case Papyrus can work in degraded mode
							resource = getResourceSet().getResource(resourceURI, false);
							if (resource == null){
								throw e ;
							}
						}
						else {
							throw e;
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			};
		};
		myDiResourceSet.registerModel(umlModel);
		umlModel.createModel(null);

		//					// call snippets to allow them to do their stuff
		//					snippets.performStart(this);
		// END OF WORKAROUND for #315083 
	}

}