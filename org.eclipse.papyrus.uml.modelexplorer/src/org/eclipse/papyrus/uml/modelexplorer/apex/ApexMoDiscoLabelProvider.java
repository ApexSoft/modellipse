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
 *  Patrick Tessier (CEA LIST) Patrick.tessier@cea.fr - Initial API and implementation
 *  Emilien Perico (Atos Origin) emilien.perico@atosorigin.com - Initial API and implementation
 *	Amine EL KOUHEN (CEA LIST/LIFL) - Added the decorator Factory
 *****************************************************************************/
package org.eclipse.papyrus.uml.modelexplorer.apex;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.facet.infra.browser.uicore.CustomizableModelLabelProvider;
import org.eclipse.emf.facet.infra.browser.uicore.internal.model.ModelElementItem;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.osgi.util.NLS;
import org.eclipse.papyrus.infra.core.editorsfactory.IPageIconsRegistry;
import org.eclipse.papyrus.infra.core.editorsfactory.PageIconsRegistry;
import org.eclipse.papyrus.infra.core.services.ServiceException;
import org.eclipse.papyrus.infra.core.utils.EditorUtils;
import org.eclipse.papyrus.infra.emf.Activator;
import org.eclipse.papyrus.infra.onefile.model.IPapyrusFile;
import org.eclipse.papyrus.infra.onefile.model.ISubResourceFile;
import org.eclipse.papyrus.infra.onefile.model.PapyrusModelHelper;
import org.eclipse.papyrus.infra.onefile.utils.OneFileUtils;
import org.eclipse.papyrus.infra.services.decoration.DecorationService;
import org.eclipse.papyrus.infra.services.decoration.util.Decoration;
import org.eclipse.papyrus.infra.services.decoration.util.IPapyrusDecoration;
import org.eclipse.papyrus.views.modelexplorer.Messages;
import org.eclipse.papyrus.views.modelexplorer.core.ui.pagebookview.ModelExplorerDecorationAdapter;
import org.eclipse.swt.graphics.Image;
import org.eclipse.uml2.uml.internal.impl.ModelImpl;

/**
 * the label provider that inherits of modisco label provider.
 */
public class ApexMoDiscoLabelProvider extends CustomizableModelLabelProvider {
//public class ApexMoDiscoLabelProvider extends MoDiscoLabelProvider {

	/** icon registry. */
	private IPageIconsRegistry editorRegistry;

	/** Decoration Service *. */
	private DecorationService decorationService;

	/**
	 * Creates a new MoDiscoLabelProvider.
	 */
	public ApexMoDiscoLabelProvider() {
		super(Activator.getDefault().getCustomizationManager());
		try {
			decorationService = EditorUtils.getServiceRegistry().getService(DecorationService.class);
			/* OR : decorationService = ServiceUtilsForActionHandlers.getInstance().getServiceRegistry().getService(DecorationService.class); */
		} catch (ServiceException ex) {
			Activator.log.error(ex);
		}
	}

	/**
	 * Returns the message of the marker for the specified element.
	 * 
	 * @param element
	 *        the element for which the marker message should be found
	 * @return the message of the marker for the specified element
	 */
	public String getMarkerMessage(Object element) {
		EList<IPapyrusDecoration> decorations = decorationService.getDecorations(element, true);
		return Decoration.getMessageFromDecorations(decorations);
	}

	/**
	 * return the image of an element in the model browser
	 * evaluates error markers.
	 * 
	 * @param element
	 *        the element
	 * @return the image
	 */
	@Override
	public Image getImage(Object element) {

		// Get the Model Explorer Adapter
		ModelExplorerDecorationAdapter adapter = new ModelExplorerDecorationAdapter(null);


		//Set the decoration target
		/**
		 * Useless since EMF Facet integration with bug 358732
		 */
		if(element instanceof Diagram) {
			adapter.setDecoratorTarget(getEditorRegistry().getEditorIcon(element));
		} else {
			adapter.setDecoratorTarget(super.getImage(element));
		}

		//Set the adapter decoration with position as indicated by decoration (from decoration service)
		if(element != null) {
			if(element instanceof EObject || (element instanceof IAdaptable && ((IAdaptable)element).getAdapter(EObject.class) != null)) {
				EList<IPapyrusDecoration> decorations = decorationService.getDecorations(element, true);
				if(decorations != null) {
					adapter.setDecorations(decorations);
				}
			}
		}

		//return the target decorated
		return adapter.getDecoratedImage();

	}

	/**
	 * Get the EditorRegistry used to create editor instances. This default
	 * implementation return the singleton eINSTANCE. This method can be
	 * subclassed to return another registry.
	 * 
	 * @return the singleton eINSTANCE of editor registry
	 */
	protected IPageIconsRegistry getEditorRegistry() {
		if(editorRegistry == null) {
			editorRegistry = createEditorRegistry();
		}
		return editorRegistry;
	}

	/**
	 * Return the EditorRegistry for nested editor descriptors. Subclass should
	 * implements this method in order to return the registry associated to the
	 * extension point namespace.
	 * 
	 * @return the EditorRegistry for nested editor descriptors
	 */
	protected IPageIconsRegistry createEditorRegistry() {
		try {
			return EditorUtils.getServiceRegistry().getService(IPageIconsRegistry.class);
		} catch (ServiceException e) {
			// Not found, return an empty one which return null for each
			// request.
			return new PageIconsRegistry();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getText(Object element) {
		String text = null;
		if (element instanceof IFile) {
			IFile file = (IFile)element;
			if(OneFileUtils.isDi(file)) {
				String fileName = file.getName();
				return fileName.substring(0, fileName.lastIndexOf('.'));
			}			
		}
		if (element instanceof ISubResourceFile) {
			return ((ISubResourceFile) element).getText();
		}
		
		if (element instanceof IWorkspaceRoot) {
			return ((IWorkspaceRoot)element).getFullPath().toString();
		} else if (element instanceof IResource) {
			return ((IResource) element).getName();
		}
		
		if(element instanceof Diagram) {
			Diagram diagram = (Diagram)element;
			text = diagram.getName();
		} else if(element instanceof IAdaptable) {
			EObject obj = (EObject)((IAdaptable)element).getAdapter(EObject.class);
			if(obj instanceof InternalEObject && obj.eIsProxy()) {
				InternalEObject internal = (InternalEObject)obj;
				text = NLS.bind(Messages.MoDiscoLabelProvider_ProxyLabel, obj.getClass().getSimpleName(), internal.eProxyURI().trimFragment());
				// Messages.MoDiscoLabelProvider_0 +  + Messages.MoDiscoLabelProvider_1 + ;;
			} else if ( element instanceof ModelElementItem ) {
				ModelElementItem mItem = (ModelElementItem)element;
				EObject eObj = mItem.getEObject();
				
				if ( eObj instanceof ModelImpl ) {
					URI uri = eObj.eResource().getURI();
					String modelFileName = uri.lastSegment();
					return modelFileName.substring(0, modelFileName.lastIndexOf("."));
				} else {
					text = super.getText(element);
				}
			} else {
				text = super.getText(element);
			}
		} else {
			text = super.getText(element);
		}
		return text;
	}
}
