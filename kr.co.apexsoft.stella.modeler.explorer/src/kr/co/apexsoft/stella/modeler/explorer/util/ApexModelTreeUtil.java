package kr.co.apexsoft.stella.modeler.explorer.util;

import kr.co.apexsoft.stella.modeler.explorer.core.ApexProjectWrapper;
import kr.co.apexsoft.stella.modeler.explorer.core.ApexStellaProjectMap;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.facet.infra.browser.uicore.internal.model.ModelElementItem;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.papyrus.editor.PapyrusMultiDiagramEditor;
import org.eclipse.papyrus.infra.core.services.ServicesRegistry;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.FileEditorInput;

public class ApexModelTreeUtil {
	
	public static IProject getProjectFromModelTreeSelection(ITreeSelection aTreeElement) {
		
		IProject project = null;
		
		TreePath[] treePath = aTreeElement.getPaths();				
		Object projectObject = null;
		
		for ( TreePath aTreePath : treePath ) {
			
			projectObject = aTreePath.getFirstSegment();
			if (projectObject instanceof IProject) {
				project = (IProject)projectObject;
			}
		}
		
		return project;
	}
	
	public static IFile getDiFileFromModelTreeSelection(ITreeSelection aTreeElement) {
		
		IFile diFile = null;
		
		TreePath[] treePath = aTreeElement.getPaths();				
		Object modelObject = null;
		
		for ( TreePath aTreePath : treePath ) {
			
			if ( !(aTreePath.getLastSegment() instanceof IProject) ) {
				modelObject = aTreePath.getSegment(1);
				
				if ( modelObject instanceof IFile ) {
					diFile = (IFile)modelObject;
				}
			}
		}
		
		return diFile;
	}

	public static PapyrusMultiDiagramEditor getEditorPartFromModelTreeSelection(ITreeSelection selection) {
		
		IFile editedFile = null;
		IEditorReference[] iEditorRefs = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getEditorReferences();
		PapyrusMultiDiagramEditor editorPart = null;
		
		IFile modelDiFile = getDiFileFromModelTreeSelection(selection);
		
		for ( IEditorReference aEditorRef : iEditorRefs ) {
			try {	
				IEditorInput editorInput = aEditorRef.getEditorInput();
				
				if ( editorInput instanceof FileEditorInput ) {
					FileEditorInput fileEditorInput = (FileEditorInput)editorInput;
					editedFile = fileEditorInput.getFile();
					
					if ( editedFile.equals(modelDiFile) ) {

						editorPart = (PapyrusMultiDiagramEditor)aEditorRef.getEditor(false);
					}
				}
				
				
			} catch (PartInitException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return editorPart;
	}
	
	public static ServicesRegistry getServicesRegistryFromModelTreeSelection(ITreeSelection treeSelection) {

		IProject project = getProjectFromModelTreeSelection(treeSelection);
		ApexProjectWrapper aProjectWrapper = ApexStellaProjectMap.getProjectMap().get(project.getLocationURI().getPath());
		
		IFile file = getDiFileFromModelTreeSelection(treeSelection);
		String diFilePath = file.getLocationURI().getPath();

		return aProjectWrapper.getServicesRegistry(diFilePath);
	}
	
	public static EObject getEObjectFromSelection(ISelection selection) {
		
		EObject eObj = null;
		
		if ( selection instanceof StructuredSelection ) {
			StructuredSelection aTreeSelection = (StructuredSelection)selection;
			Object treeObject = aTreeSelection.getFirstElement();
			
			if ( treeObject instanceof ModelElementItem ) {
				ModelElementItem aModelElementItem = (ModelElementItem)treeObject;
				eObj = aModelElementItem.getEObject();
//				System.out.println("  Current Model Selection : " + aModelElementItem.getEObject());	
			}
		} else {
//			System.out.println("  Current Selection : " + selection);
		}
		
		return eObj;
	}
	
	public static String getFileNameFromEmfResource(Resource resource, boolean includeExt) {
		
		URI uri = resource.getURI();
		String path = uri.path();
		String fileName = path.substring(path.lastIndexOf('/'));
		return includeExt ? fileName : fileName.substring(0, fileName.lastIndexOf('.'));
		
	}
	
}
