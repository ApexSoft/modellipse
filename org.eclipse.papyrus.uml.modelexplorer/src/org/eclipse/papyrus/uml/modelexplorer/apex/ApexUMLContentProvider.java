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
 *
 *****************************************************************************/
package org.eclipse.papyrus.uml.modelexplorer.apex;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.facet.infra.browser.uicore.internal.AppearanceConfiguration;
import org.eclipse.emf.facet.infra.browser.uicore.internal.model.ITreeElement;
import org.eclipse.emf.facet.infra.browser.uicore.internal.model.ItemsFactory;
import org.eclipse.emf.facet.infra.browser.uicore.internal.model.ModelElementItem;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.papyrus.infra.core.apex.ApexModellipseExplorerRoot;
import org.eclipse.papyrus.infra.core.resource.ModelSet;
import org.eclipse.papyrus.infra.core.resource.ModelUtils;
import org.eclipse.papyrus.infra.core.resource.uml.UmlModel;
import org.eclipse.papyrus.infra.core.resource.uml.UmlUtils;
import org.eclipse.papyrus.infra.core.sasheditor.di.contentprovider.DiSashModelMngr;
import org.eclipse.papyrus.infra.core.services.ServicesRegistry;
import org.eclipse.papyrus.infra.emf.Activator;
import org.eclipse.papyrus.infra.emf.apex.providers.ApexMoDiscoContentProvider;
import org.eclipse.papyrus.infra.onefile.model.IPapyrusFile;
import org.eclipse.papyrus.infra.onefile.model.PapyrusModelHelper;
import org.eclipse.papyrus.infra.onefile.utils.OneFileUtils;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.WorkbenchException;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.navigator.CommonViewer;
import org.eclipse.uml2.uml.internal.impl.ModelImpl;

/**
 * this is a specific content provider used to not display UML stereotype applications
 * 
 * @deprecated Use {@link org.eclipse.papyrus.uml.ApexUMLContentProvider.providers.UMLContentProvider} instead
 */
@Deprecated
public class ApexUMLContentProvider extends ApexMoDiscoContentProvider {
	
	private CommonViewer common;

	private final AppearanceConfiguration appearanceConfiguration;	
	
	public ApexUMLContentProvider() {
		super();
		appearanceConfiguration = new AppearanceConfiguration(new ItemsFactory());
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
				UmlModel umlModel = UmlUtils.getUmlModel(servicesRegistry);
//System.out.println("ApexUMLContentProvider.getRootElements, line "
//		+ Thread.currentThread().getStackTrace()[1].getLineNumber());
//System.out.println("servicesRegistry in ApexUMLContentProvider : \n    " + servicesRegistry);
//System.out.println("umlModel in ApexUMLContentProvider : \n    " + umlModel);

				modelSet = ModelUtils.getModelSetChecked(servicesRegistry);
				pageMngr = servicesRegistry.getService(DiSashModelMngr.class).getIPageMngr();

				return result.toArray(new Object[result.size()]);
//				return result.toArray(new EObject[result.size()]);
//				return getRootElements(modelSet);	
			} else if ( inputElement instanceof IWorkspaceRoot ) {
				IWorkspaceRoot root = (IWorkspaceRoot)inputElement;
				IProject[] projects = root.getProjects();
				
				for ( IProject project : projects ) {
//					System.out
//							.println("ApexUMLContentProvider.getRootElements, line "
//									+ Thread.currentThread().getStackTrace()[1]
//											.getLineNumber());
//					System.out.println("  project.getName() : " + project.getName());
					
					result.add(project);
					try {
						IResource[] resources = project.members();
						
						for ( IResource resource : resources ) {
//							System.out.println("    resource.getName() : " + resource.getName());
						}
					} catch (CoreException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			}
		} catch (Exception e) {
			Activator.log.error(e);
		}
		return result.toArray();
//		return new Object[0];
//		return new EObject[0];
		/* apex improved end */	
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
	 * 
	 * 호출 안됨
	 */
//	@Override
//	protected EObject[] getRootElements(ModelSet modelSet) {
//		UmlModel umlModel = (UmlUtils.getUmlModel(modelSet));
//System.out.println("ApexUMLContentProvider.getRootElements, line "
//		+ Thread.currentThread().getStackTrace()[1].getLineNumber());
//System.out.println("umlModel in ApexUMLContentProvider : \n    " + umlModel);
//		if(umlModel == null)
//			return null;
//
//		EList<EObject> contents = umlModel.getResource().getContents();
//		ArrayList<EObject> result = new ArrayList<EObject>();
//		Iterator<EObject> iterator = contents.iterator();
//
//		while(iterator.hasNext()) {
//			EObject eObject = iterator.next();
//			//functionality that comes from UML2 plugins
//			if(UMLUtil.getStereotype(eObject) == null) {
//				result.add(eObject);
//			}
//		}
//		
//		return result.toArray(new EObject[result.size()]);		
//	}	
	
	/**
	 * apex added
	 * Get the roots elements from the {@link ModelSet} provided as input.
	 * 호출 안됨
	 * @return
	 */
////	protected EObject[] getRootElements(ServicesRegistry servicesRegistry) {
//	protected Object[] getRootElements(ServicesRegistry servicesRegistry) {
//		UmlModel umlModel = (UmlUtils.getUmlModel(servicesRegistry));
//System.out.println("ApexMoDiscoContentProvider.getRootElements, line "
//		+ Thread.currentThread().getStackTrace()[1].getLineNumber());
//System.out.println("umlModel in ApexMoDisco : " + umlModel);
//		if(umlModel == null) {
//			return null;
//		}
//
//		EList<EObject> contents = umlModel.getResource().getContents();				
//		ArrayList<EObject> result = new ArrayList<EObject>();		
//		Iterator<EObject> iterator = contents.iterator();
//		while(iterator.hasNext()) {
//			EObject eObject = iterator.next();
//			result.add(eObject);
//		}
//		return result.toArray(new Object[result.size()]);
////		return result.toArray(new EObject[result.size()]);
//	}
	
	@Override
	public Object[] getChildren(final Object parentElement) {
		ArrayList<Object> result = new ArrayList<Object>();
		
		if ( parentElement instanceof IProject ) {
			IProject project = (IProject)parentElement;
			IResource[] members;
			try {
				members = project.members();
				
				for ( IResource r : members ) {
					
					if ( r instanceof IFile ) {
						IFile file = (IFile)r;
						String fileNameWithExt = file.getName();
						
						if(OneFileUtils.isDi(file)) {
//							IPapyrusFile createIPapyrusFile = PapyrusModelHelper.getPapyrusModelFactory().createIPapyrusFile(file);
							result.add(file);
							
							
							
//							try {
//								IEditorPart papyrusEditor = IDE.openEditor(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage(), (IFile)r, true);
////								setServicesRegistries(r, papyrusEditor, result);
//							} catch (WorkbenchException e) {
//							}							
							
							
						}
					} 
				}
			} catch (CoreException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			super.getChildren(parentElement);
			Object[] arrayObject = super.getChildren(parentElement);
			
			if(arrayObject != null) {
				for(int i = 0; i < arrayObject.length; i++) {
					if ( arrayObject[i] instanceof UmlModel ) {
						UmlModel umlModel = (UmlModel) arrayObject[i];
						EList<EObject> contents = umlModel.getResource().getContents();
						
						for ( EObject eObj : contents ) {
							if ( eObj instanceof ModelImpl ) {
								ModelElementItem modelItem = new ModelElementItem(eObj, null, this.appearanceConfiguration); 
								result.add(modelItem);	
//								System.out
//										.println("ApexUMLContentProvider.getChildren, line "
//												+ Thread.currentThread()
//														.getStackTrace()[1]
//														.getLineNumber());
//								System.out.println("added eObj : " + eObj);
//								System.out.println("added modelItem : " + modelItem);
							}
						}
					} else {
						result.add(arrayObject[i]);
					}
				}
			}
			
		}
		return result.toArray();
	}	

	/**
	 * apex updated
	 */
	@Override
	public Object[] getElements(final Object inputElement) {
		Object[] rootElements = getRootElements(inputElement);
		if (rootElements == null) {
			return null;
		}
		List<Object> result = new ArrayList<Object>();
		for (Object element : rootElements) {
			if (element instanceof EObject) {
				EObject eObject = (EObject) element;
				result.add(new ModelElementItem(eObject, null, this.appearanceConfiguration));
			} else {
				result.add(element);
			}
		}
		return result.toArray();
	}
	
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		super.inputChanged(viewer, oldInput, newInput);
		if(viewer instanceof CommonViewer) {
			common = (CommonViewer)viewer;
		}
	}
	
}
