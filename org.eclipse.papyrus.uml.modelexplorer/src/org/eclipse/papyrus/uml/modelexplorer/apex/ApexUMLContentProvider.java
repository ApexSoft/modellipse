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

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.facet.infra.browser.uicore.CustomizableModelContentProvider;
import org.eclipse.emf.facet.infra.browser.uicore.internal.AppearanceConfiguration;
import org.eclipse.emf.facet.infra.browser.uicore.internal.model.ITreeElement;
import org.eclipse.emf.facet.infra.browser.uicore.internal.model.ItemsFactory;
import org.eclipse.emf.facet.infra.browser.uicore.internal.model.ModelElementItem;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.papyrus.editor.PapyrusMultiDiagramEditor;
import org.eclipse.papyrus.infra.core.apex.ApexModellipseExplorerRoot;
import org.eclipse.papyrus.infra.core.apex.ApexProjectWrapper;
import org.eclipse.papyrus.infra.core.resource.ModelSet;
import org.eclipse.papyrus.infra.core.resource.uml.UmlModel;
import org.eclipse.papyrus.infra.core.services.ServicesRegistry;
import org.eclipse.papyrus.infra.emf.Activator;
import org.eclipse.papyrus.infra.emf.providers.MoDiscoContentProvider;
import org.eclipse.papyrus.infra.onefile.utils.OneFileUtils;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.navigator.CommonViewer;
import org.eclipse.uml2.uml.internal.impl.ModelImpl;

/**
 * this is a specific content provider used to not display UML stereotype applications
 * 
 * @deprecated Use {@link org.eclipse.papyrus.uml.ApexUMLContentProvider.providers.UMLContentProvider} instead
 */
@Deprecated
public class ApexUMLContentProvider extends CustomizableModelContentProvider {
	
	private CommonViewer common;

	private final AppearanceConfiguration appearanceConfiguration;	
	
	public ApexUMLContentProvider() {
//		super();
		super(Activator.getDefault().getCustomizationManager());
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
			if ( inputElement instanceof IWorkspaceRoot ) {
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
	public boolean hasChildren(Object element) {
		boolean hasChildren = false;
		if ( element instanceof IProject ) {
			IProject project = (IProject)element;
			try {
				hasChildren = project.members().length > 0;
			} catch (CoreException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if ( element instanceof IFile ) {
			IFile diFile = (IFile)element;
			if(OneFileUtils.isDi(diFile)) {
				hasChildren = true;
			}
		} else {
			hasChildren = super.getChildren(element).length > 0;
		}
		return hasChildren;
	}
	
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
							result.add(file);
						}
					} 
				}
			} catch (CoreException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if ( parentElement instanceof IFile ) {
			IFile diFile = (IFile)parentElement;
			
			if(OneFileUtils.isDi(diFile)) {
				// diFile에서 ApexProjectWrapper 가져와서 super.getChildren(wrapper); 호출 후 아래 로직 태움
				String diFilePath = diFile.getLocationURI().getPath();
				String projectPath = diFile.getParent().getLocationURI().getPath();
				Map<String, ITreeElement> projectMap = ApexModellipseExplorerRoot.getProjectMap();
				ITreeElement aTreeElement = projectMap.get(projectPath);
				
				if ( aTreeElement == null ) {
					IEditorPart editor = ApexModellipseExplorerRoot.openEditor(diFile);
					
					if ( editor != null && editor instanceof PapyrusMultiDiagramEditor ) {
						ServicesRegistry servicesRegistry = ((PapyrusMultiDiagramEditor)editor).getServicesRegistry();
						ApexModellipseExplorerRoot.addToGlobalRoot(diFile, servicesRegistry);
						aTreeElement = projectMap.get(projectPath);
					}		
				}
				if ( aTreeElement instanceof ApexProjectWrapper ) {
					ApexProjectWrapper aProjectWrapper = (ApexProjectWrapper)aTreeElement;
					UmlModel umlModel = aProjectWrapper.getUmlModel(diFilePath);					
					EList<EObject> contents = umlModel.getResource().getContents();
					
					for ( EObject eObj : contents ) {
						if ( eObj instanceof ModelImpl ) {
							ModelElementItem modelItem = new ModelElementItem(eObj, null, this.appearanceConfiguration); 
							result.add(modelItem);	
//							System.out
//									.println("ApexUMLContentProvider.getChildren, line "
//											+ Thread.currentThread()
//													.getStackTrace()[1]
//													.getLineNumber());
//							System.out.println("added eObj : " + eObj);
//							System.out.println("added modelItem : " + modelItem);
						}
					}
				}	
			}			
		} else {
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
