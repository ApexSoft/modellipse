/*******************************************************************************
 * Copyright (c) 2010 Mia-Software.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Nicolas Bros (Mia-Software) - initial API and implementation
 *******************************************************************************/
package org.eclipse.papyrus.infra.emf.apex.providers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.facet.infra.browser.uicore.CustomizableModelContentProvider;
import org.eclipse.emf.facet.infra.browser.uicore.internal.AppearanceConfiguration;
import org.eclipse.emf.facet.infra.browser.uicore.internal.model.ITreeElement;
import org.eclipse.emf.facet.infra.browser.uicore.internal.model.ItemsFactory;
import org.eclipse.emf.facet.infra.browser.uicore.internal.model.ModelElementItem;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.papyrus.infra.core.apex.ApexModellipseExplorerRoot;
import org.eclipse.papyrus.infra.core.resource.ModelSet;
import org.eclipse.papyrus.infra.core.resource.ModelUtils;
import org.eclipse.papyrus.infra.core.resource.uml.UmlModel;
import org.eclipse.papyrus.infra.core.resource.uml.UmlUtils;
import org.eclipse.papyrus.infra.core.sasheditor.contentprovider.IPageMngr;
import org.eclipse.papyrus.infra.core.sasheditor.di.contentprovider.DiSashModelMngr;
import org.eclipse.papyrus.infra.core.services.ServicesRegistry;
import org.eclipse.papyrus.infra.emf.Activator;

/**
 * the content provider that inherits of modisco properties
 * 
 * @deprecated Use SemanticUMLContentProvider instead
 */
@Deprecated
public class ApexMoDiscoContentProvider extends CustomizableModelContentProvider {

	/* apex added start */
	private List<ServicesRegistry> _servicesRegistry;
	/* apex added end */
	/** The ModelSet containing all the models. This is the initial input. */
	protected ModelSet modelSet;

	/** The list of open pages (diagrams) */
	protected IPageMngr pageMngr;

	/**
	 * Creates a new MoDiscoContentProvider.
	 */
	public ApexMoDiscoContentProvider() {
		super(Activator.getDefault().getCustomizationManager());		
	}

	@Override
	public boolean hasChildren(Object element) {
		return getChildren(element).length > 0;
	}

	@Override
	public Object[] getChildren(final Object parentElement) {
		ArrayList<Object> result = new ArrayList<Object>();

		Object[] arrayObject = super.getChildren(parentElement);
//		System.out.println("MoDiscoContentProvider.getChildren, line : "
//				+ Thread.currentThread().getStackTrace()[1].getLineNumber());
//		System.out.println("  parentElement : " + ((ITreeElement)parentElement).getText());
		if(arrayObject != null) {
			for(int i = 0; i < arrayObject.length; i++) {
				result.add(arrayObject[i]);
//				if ( arrayObject[i] instanceof ModelElementItem ) {
//					ModelElementItem modelElement = (ModelElementItem)arrayObject[i];
//					System.out.println("    modelElement : " + modelElement.getName());
//					System.out.println("    modelElement.getTreeParent() : " + modelElement.getTreeParent().getText());
//					System.out.println("    modelElement.getEObject().eContainer() : " + modelElement.getEObject().eContainer());	 
//				}
			}
		}
		/**
		 * Refactoring with bug 358732
		 */
		//
		//		if (parentElement instanceof IAdaptable) {
		//			EObject eObject = (EObject)((IAdaptable)parentElement).getAdapter(EObject.class);
		//			if(eObject !=null) {
		//				List<Diagram> diagramList = findAllExistingDiagrams(eObject);
		//					Iterator<Diagram> iterator = diagramList.iterator();
		//					while (iterator.hasNext()) {
		//						result.add(iterator.next());
		//					}
		//			}
		//
		//		}

		return result.toArray();
	}

	/**
	 * @param owner
	 *        the owner of the diagrams
	 * @return the list of diagrams contained by the given owner
	 */
	private List<Diagram> findAllExistingDiagrams(EObject owner) {
		ArrayList<Diagram> diagrams = new ArrayList<Diagram>();

		// Walk on page (Diagram) references
		for(Object page : pageMngr.allPages()) {
			if(!(page instanceof Diagram)) {
				continue;
			}
			// We have a GMF Diagram
			Diagram diagram = (Diagram)page;
			if(owner.equals(diagram.getElement())) {
				diagrams.add(diagram);
			}

		}

		return diagrams;
	}

	/**
	 * apex updated
	 * 
	 * Return the initial values from the input.
	 * Input should be of type {@link UmlModel}.
	 * 
	 * @see org.eclipse.gmt.modisco.infra.browser.uicore.CustomizableModelContentProvider#getRootElements(java.lang.Object)
	 * 
	 * @param inputElement
	 * @return
	 */
	@Override
//	public EObject[] getRootElements(Object inputElement) {
	public Object[] getRootElements(Object inputElement) {
		/* apex improved start */
		List<Object> result = new ArrayList<Object>();	
		try {
			if(inputElement instanceof ServicesRegistry) {				
//				_servicesRegistry = ApexModellipseExplorerRoot.getServicesRegistryList();
//				
//				for ( ServicesRegistry servicesRegistry : _servicesRegistry ) {
//					// getRootElements()는 언제나 length 가 1인 배열 반환
//					result.add(getRootElements(servicesRegistry)[0]);
//				}
//
				Map<String, ITreeElement> projectMap = ApexModellipseExplorerRoot.getProjectMap(); 
				Set<Entry<String, ITreeElement>> entrySet = projectMap.entrySet();
				
				for ( Entry<String, ITreeElement> aProject : entrySet ) {
					// getRootElements()는 언제나 length 가 1인 배열 반환
					result.add(aProject.getValue());
				}
			
				
				ServicesRegistry servicesRegistry = (ServicesRegistry)inputElement;

				modelSet = ModelUtils.getModelSetChecked(servicesRegistry);
				pageMngr = servicesRegistry.getService(DiSashModelMngr.class).getIPageMngr();

				return result.toArray(new Object[result.size()]);
//				return result.toArray(new EObject[result.size()]);
//				return getRootElements(modelSet);	
			}
		} catch (Exception e) {
			Activator.log.error(e);
		}
		return new Object[0];
//		return new EObject[0];
		/* apex improved end */
		
//		try {
//			if(!(inputElement instanceof ServicesRegistry)) {
//				return null;
//			}
//
//			ServicesRegistry servicesRegistry = (ServicesRegistry)inputElement;
//
//			modelSet = ModelUtils.getModelSetChecked(servicesRegistry);
//			pageMngr = servicesRegistry.getService(DiSashModelMngr.class).getIPageMngr();
//
//			return getRootElements(modelSet);
//		} catch (Exception e) {
//			Activator.log.error(e);
//		}
//
//		return new EObject[0];
	}

	/**
	 * Get the roots elements from the {@link ModelSet} provided as input.
	 * 
	 * @return
	 */
//	protected EObject[] getRootElements(ModelSet modelSet) {
	protected Object[] getRootElements(ModelSet modelSet) {
		UmlModel umlModel = (UmlUtils.getUmlModel(modelSet));

		if(umlModel == null) {
			return null;
		}

		EList<EObject> contents = umlModel.getResource().getContents();				
		ArrayList<EObject> result = new ArrayList<EObject>();		
		Iterator<EObject> iterator = contents.iterator();
		while(iterator.hasNext()) {
			EObject eObject = iterator.next();
			result.add(eObject);
		}
		return result.toArray(new Object[result.size()]);
//		return result.toArray(new EObject[result.size()]);
	}
	
	/**
	 * apex added
	 * Get the roots elements from the {@link ModelSet} provided as input.
	 * 
	 * @return
	 */
//	protected EObject[] getRootElements(ServicesRegistry servicesRegistry) {
	protected Object[] getRootElements(ServicesRegistry servicesRegistry) {
		UmlModel umlModel = (UmlUtils.getUmlModel(servicesRegistry));
System.out.println("ApexMoDiscoContentProvider.getRootElements, line "
		+ Thread.currentThread().getStackTrace()[1].getLineNumber());
System.out.println("servicesRegistry in ApexMoDisco : " + servicesRegistry);
System.out.println("umlModel in ApexMoDisco : " + umlModel);
		if(umlModel == null) {
			return null;
		}

		EList<EObject> contents = umlModel.getResource().getContents();				
		ArrayList<EObject> result = new ArrayList<EObject>();		
		Iterator<EObject> iterator = contents.iterator();
		while(iterator.hasNext()) {
			EObject eObject = iterator.next();
			result.add(eObject);
		}
		return result.toArray(new Object[result.size()]);
//		return result.toArray(new EObject[result.size()]);
	}
	
}
