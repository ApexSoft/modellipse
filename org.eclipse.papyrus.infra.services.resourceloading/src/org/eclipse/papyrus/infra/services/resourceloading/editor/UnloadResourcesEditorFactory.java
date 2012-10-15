package org.eclipse.papyrus.infra.services.resourceloading.editor;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.papyrus.infra.core.extension.diagrameditor.EditorDescriptor;
import org.eclipse.papyrus.infra.core.extension.diagrameditor.IPluggableEditorFactory;
import org.eclipse.papyrus.infra.core.sasheditor.contentprovider.IPageModel;
import org.eclipse.papyrus.infra.core.services.ServicesRegistry;


public class UnloadResourcesEditorFactory implements IPluggableEditorFactory {

	private EditorDescriptor editorDescriptor;

	private ServicesRegistry serviceRegistry;

	public IPageModel createIPageModel(Object pageIdentifier) {
		URI uri = null;
		if(pageIdentifier instanceof InternalEObject) {
			InternalEObject internal = (InternalEObject)pageIdentifier;
			uri = internal.eProxyURI();
		}
		return new UnloadResourcesEditorModel(uri);
	}

	public boolean isPageModelFactoryFor(Object pageIdentifier) {
		boolean result = false;
		if(pageIdentifier instanceof EObject) {
			EObject eObjectPageIdentifier = (EObject)pageIdentifier;
			result = eObjectPageIdentifier.eIsProxy();
		}
		return result;
	}

	public void init(ServicesRegistry serviceRegistry, EditorDescriptor editorDescriptor) {
		this.editorDescriptor = editorDescriptor;
		this.serviceRegistry = serviceRegistry;
	}

}
