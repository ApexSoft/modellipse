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
import org.eclipse.papyrus.infra.core.apex.ApexModellipseProjectMap;
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
					result.add(project);
				}
			}
		} catch (Exception e) {
			Activator.log.error(e);
		}
		return result.toArray();
		/* apex improved end */	
	}	
	
	/**
	 * apex updated
	 */
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
				String diFilePath = diFile.getLocationURI().getPath();
				String projectPath = diFile.getParent().getLocationURI().getPath();

				Map<String, ApexProjectWrapper> projectMap = ApexModellipseProjectMap.getProjectMap();
				ApexProjectWrapper aProjectWrapper = projectMap.get(projectPath);
				
				// 최초 뷰 구성 시
				// 프로젝트 래퍼가 없고,
				// diPath에 대한 umlModel 도 없다.
				// ->에디터 열고 ServiceReg를 가져와 세팅(최초 뷰 구성 시)
				
				// refresh() 시
				// 프로젝트 래퍼가 있고,
				// diPath에 대한 umlModel 은 없다.
				// 아무일도 안 함
				
				// 더블클릭 시
				// 더블클릭 리스너에서 ServiceReg를 세팅
				// 프로젝트 래퍼가 있고,
				// diPath에 대한 umlModel도 있다.
				
				if ( aProjectWrapper == null ) { // 최초 뷰 구성 시
					IEditorPart editor = ApexModellipseProjectMap.openEditor(diFile);
					
					if ( editor != null && editor instanceof PapyrusMultiDiagramEditor ) {
						ServicesRegistry servicesRegistry = ((PapyrusMultiDiagramEditor)editor).getServicesRegistry();
						aProjectWrapper = ApexModellipseProjectMap.setUpModelServices(diFile, servicesRegistry);
						
						UmlModel umlModel = aProjectWrapper.getUmlModel(diFilePath);					
						EList<EObject> contents = umlModel.getResource().getContents();
						
						makeModelElementItemList(umlModel, result);
					}		
				} else {
					UmlModel umlModel = aProjectWrapper.getUmlModel(diFilePath);
					
					if ( umlModel != null ) { // doubleClick() 인 경우
						makeModelElementItemList(umlModel, result);
					}
				}
			}			
		} else {
			Object[] arrayObject = super.getChildren(parentElement);

			if(arrayObject != null) {

				for(int i = 0; i < arrayObject.length; i++) {
				
					if ( arrayObject[i] instanceof UmlModel ) {
						UmlModel umlModel = (UmlModel) arrayObject[i];
						makeModelElementItemList(umlModel, result);
					} else {
						result.add(arrayObject[i]);
					}
				}
			}
		}
		return result.toArray();
	}	
	
	@Override
	public boolean hasChildren(Object element) {
		
		boolean hasChildren = false;
		
		if ( element instanceof IProject ) {
			IProject project = (IProject)element;
		
			try {
				hasChildren = project.members().length > 0;
			} catch (CoreException e) {
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
	
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {

		super.inputChanged(viewer, oldInput, newInput);
		
		if(viewer instanceof CommonViewer) {
			common = (CommonViewer)viewer;
		}
	}
	
	private void makeModelElementItemList(UmlModel umlModel, List<Object> result) {
		EList<EObject> contents = umlModel.getResource().getContents();

		for ( EObject eObj : contents ) {
	
			if ( eObj instanceof ModelImpl ) {
				ModelElementItem modelItem = new ModelElementItem(eObj, null, this.appearanceConfiguration); 
				result.add(modelItem);	
			}
		}
	}
}
