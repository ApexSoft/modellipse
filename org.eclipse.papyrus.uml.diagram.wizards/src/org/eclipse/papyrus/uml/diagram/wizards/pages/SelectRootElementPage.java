/*******************************************************************************
 * Copyright (c) 2008 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.papyrus.uml.diagram.wizards.pages;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.provider.EcoreItemProviderAdapterFactory;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.Resource.Diagnostic;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.IWrapperItemProvider;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.papyrus.infra.core.resource.ModelSet;
import org.eclipse.papyrus.infra.core.resource.ModelUtils;
import org.eclipse.papyrus.infra.widgets.toolbox.notification.Type;
import org.eclipse.papyrus.infra.widgets.toolbox.notification.builders.NotificationBuilder;
import org.eclipse.papyrus.uml.diagram.wizards.Messages;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

/**
 * Wizard page that allows to select element from model.
 * 
 * @author <a href="mailto:jerome.benois@obeo.fr">Jerome Benois</a>
 */
public class SelectRootElementPage extends WizardPage {

	/** The selected model element. */
	protected EObject selectedModelElement;

	/** TreeViewer use to display the content of the domain model. */
	private TreeViewer modelViewer;

	/** The my domain model resource. */
	private final Resource myDomainModelResource;

	/** The Constant PAGE_ID. */
	public static final String PAGE_ID = "SelectRootPage"; //$NON-NLS-1$

	/**
	 * Constructor.
	 *
	 * @param file the file
	 */
	public SelectRootElementPage(IFile file) {
		super(PAGE_ID);
		setTitle(Messages.SelectRootElementPage_select_root_element_title);
		setDescription(Messages.SelectRootElementPage_select_root_element_desc);

		myDomainModelResource = getResourceForFile(file);

	}

	/**
	 * Gets the model element.
	 *
	 * @return the selected model element
	 */
	public EObject getModelElement() {
		return selectedModelElement;
	}

	/**
	 * Creates the control.
	 *
	 * @param parent the parent
	 * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
	 */

	public void createControl(Composite parent) {
		initializeDialogUnits(parent);

		Composite plate = new Composite(parent, SWT.NONE);
		plate.setLayoutData(new GridData(GridData.FILL_BOTH));
		GridLayout layout = new GridLayout();
		layout.marginWidth = 0;
		plate.setLayout(layout);
		setControl(plate);

		Label label = new Label(plate, SWT.NONE);
		// label.setText("Select the root model element");
		label.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING));

		modelViewer = new TreeViewer(plate, SWT.SINGLE | SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);

		GridData layoutData = new GridData(GridData.FILL_BOTH);
		layoutData.heightHint = 300;
		layoutData.widthHint = 300;
		modelViewer.getTree().setLayoutData(layoutData);
		AdapterFactory adapterFactory = createAdapterFactory();
		modelViewer.setContentProvider(new AdapterFactoryContentProvider(adapterFactory));
		modelViewer.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));

		modelViewer.setInput(myDomainModelResource);

		selectedModelElement = getModelRoot(myDomainModelResource);
		modelViewer.setSelection(selectedModelElement == null ? new StructuredSelection() :  new StructuredSelection(selectedModelElement));

		modelViewer.addSelectionChangedListener(new ISelectionChangedListener() {

			public void selectionChanged(SelectionChangedEvent event) {
				updateSelection((IStructuredSelection)event.getSelection());
			}
		});

		setPageComplete(validatePage());
	}

	/**
	 * Gets the model root.
	 *
	 * @param modelResource the model resource
	 * @return the model root
	 */
	private EObject getModelRoot(Resource modelResource) {
		if (modelResource == null) {
			//log
			return null;
		}
		return modelResource.getContents().get(0);
	}

	/**
	 * Gets the resource for file.
	 *
	 * @param file the file
	 * @return the resource for file
	 */
	private Resource getResourceForFile(IFile file) {
		if (file == null) {
			// log
			return null;
		}
		ModelSet modelSet = new ModelSet();
		Resource resource = null ;
		try {
			resource = modelSet.getResource(URI.createPlatformResourceURI(file.getFullPath().toString(), true), true);
		}
		catch (WrappedException e) {
			if (ModelUtils.isDegradedModeAllowed(e.getCause())){
				resource = modelSet.getResource(URI.createPlatformResourceURI(file.getFullPath().toString(), true), false);
				if (resource == null){
					throw e;
				}
			}
		}
		if (!resource.getErrors().isEmpty()){
			StringBuilder builder = new StringBuilder();
			for (Diagnostic d : resource.getErrors()){
				builder.append(String.format("<li>%s</li>", d.getMessage()));
			}
			error(resource, builder.toString());
		}
		return resource;
	}

	/**
	 * @param resource
	 * @param e
	 */
	private void error(Resource resource, String message) {
		NotificationBuilder.createWarningPopup(String.format("<form>Problems encountered in your input model, after the save you could lose data :%s</form>",message)).setHTML(true).setType(Type.WARNING).run();
	}

	/**
	 * Update selection.
	 * 
	 * @param selection
	 *        the selection
	 */
	protected void updateSelection(IStructuredSelection selection) {
		selectedModelElement = null;
		if(selection.size() == 1) {
			Object selectedElement = selection.getFirstElement();
			if(selectedElement instanceof IWrapperItemProvider) {
				selectedElement = ((IWrapperItemProvider)selectedElement).getValue();
			}
			if(selectedElement instanceof FeatureMap.Entry) {
				selectedElement = ((FeatureMap.Entry)selectedElement).getValue();
			}
			if(selectedElement instanceof EObject) {
				selectedModelElement = (EObject)selectedElement;
			}
		}
		setPageComplete(validatePage());
	}

	/**
	 * Validate page.
	 * 
	 * @return true, if successful
	 */
	protected boolean validatePage() {
		return selectedModelElement != null;
	}

	/**
	 * Creates the adapter factory.
	 * 
	 * @return the composed adapter factory
	 */
	protected ComposedAdapterFactory createAdapterFactory() {
		List<AdapterFactory> factories = new ArrayList<AdapterFactory>();
		fillItemProviderFactories(factories);
		return new ComposedAdapterFactory(factories);
	}

	/**
	 * Fill item provider factories.
	 * 
	 * @param factories
	 *        the factories
	 */
	protected void fillItemProviderFactories(List<AdapterFactory> factories) {
		// custom icons for model elements
		factories.add(new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE));
		factories.add(new EcoreItemProviderAdapterFactory());
		factories.add(new ReflectiveItemProviderAdapterFactory());
	}
}
