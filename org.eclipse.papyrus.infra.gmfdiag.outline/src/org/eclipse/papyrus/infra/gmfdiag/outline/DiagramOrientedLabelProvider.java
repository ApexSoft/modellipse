/***********************************************************************
 * Copyright (c) 2008, 2009 Anyware Technologies, Obeo
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *    David Sciamma (Anyware Technologies) - initial API and implementation
 * 
 **********************************************************************/
package org.eclipse.papyrus.infra.gmfdiag.outline;

import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.papyrus.infra.core.editorsfactory.IPageIconsRegistry;
import org.eclipse.papyrus.infra.core.editorsfactory.PageIconsRegistry;
import org.eclipse.papyrus.infra.core.services.ServiceException;
import org.eclipse.papyrus.infra.core.utils.EditorUtils;
import org.eclipse.swt.graphics.Image;

/**
 * Compute the label of the elements contained by the current diagram.
 * 
 * @author <a href="mailto:david.sciamma@anyware-tech.com">David Sciamma</a>
 * @author <a href="mailto:jacques.lescot@anyware-tech.com">Jacques Lescot</a>
 * @author <a href="mailto:jerome.benois@obeo.fr">Jerome Benois</a>
 */
public class DiagramOrientedLabelProvider implements ILabelProvider {

	private AdapterFactoryLabelProvider myAdapterFactoryLabelProvider;

	/** Registry to store editor factories */
	private IPageIconsRegistry editorRegistry;

	public DiagramOrientedLabelProvider(AdapterFactoryLabelProvider adapterFactoryLabelProvider) {
		this.myAdapterFactoryLabelProvider = adapterFactoryLabelProvider;
	}

	/**
	 * {@inheritDoc}
	 */
	public Image getImage(Object element) {
		if(element instanceof Diagram) {
			return getEditorRegistry().getEditorIcon(element);
		}
		
		return myAdapterFactoryLabelProvider.getImage(element);
	}

	/**
	 * {@inheritDoc}
	 */
	public String getText(Object element) {
		if(element instanceof Diagram) {
			Diagram diagram = (Diagram)element;
			return myAdapterFactoryLabelProvider.getText(diagram.getElement());
		}

		return myAdapterFactoryLabelProvider.getText(element);
	}

	/**
	 * {@inheritDoc}
	 */
	public void addListener(ILabelProviderListener listener) {
		myAdapterFactoryLabelProvider.addListener(listener);
	}

	/**
	 * {@inheritDoc}
	 */
	public void dispose() {
		myAdapterFactoryLabelProvider.dispose();
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean isLabelProperty(Object element, String property) {
		return myAdapterFactoryLabelProvider.isLabelProperty(element, property);
	}

	/**
	 * {@inheritDoc}
	 */
	public void removeListener(ILabelProviderListener listener) {
		myAdapterFactoryLabelProvider.removeListener(listener);
	}

	/**
	 * Get the EditorRegistry used to create editor instances. This default implementation return
	 * the singleton eINSTANCE. This method can be subclassed to return another registry.
	 * 
	 * @return the singleton eINSTANCE of editor registry
	 * @throws ServiceException 
	 */
	protected IPageIconsRegistry getEditorRegistry() {
		if(editorRegistry == null) {
			editorRegistry = createEditorRegistry();
		}
		return editorRegistry;
	}

	/**
	 * Return the EditorRegistry for nested editor descriptors. Subclass should implements this
	 * method in order to return the registry associated to the extension point namespace.
	 * 
	 * @return the EditorRegistry for nested editor descriptors
	 * @throws ServiceException 
	 */
	protected IPageIconsRegistry createEditorRegistry() {
		try {
			return EditorUtils.getServiceRegistry().getService(IPageIconsRegistry.class);
		} catch (ServiceException e) {
			// Not found, return an empty one which return null for each request.
			return new PageIconsRegistry();
		}
	}

}
