package kr.co.apexsoft.stella.modeler.explorer.core;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.papyrus.infra.core.resource.uml.UmlModel;
import org.eclipse.papyrus.infra.core.resource.uml.UmlUtils;
import org.eclipse.papyrus.infra.core.services.ServicesRegistry;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;

public class ApexStellaProjectMap {

	/**
	 * Key : Project Path
	 * Value : ApexProjectWrapper 
	 */
	private static Map<String, ApexProjectWrapper> _projectMap = new HashMap<String, ApexProjectWrapper>();

	public static Map<String, ApexProjectWrapper> getProjectMap() {	
		return _projectMap;
	}	

	/**
	 * apex added
	 * 
	 * 에디터를 열 때 ModelServices 설정 
	 * 
	 * @param servicesRegistry
	 */
	public static ApexProjectWrapper setUpModelServices(IFile diFile, ServicesRegistry servicesRegistry) {

		String diPath = diFile.getLocationURI().getPath();
		String projectPath = diFile.getParent().getLocationURI().getPath();
		ApexProjectWrapper projectWrapper = null;
		UmlModel umlModel = null;

		umlModel =  UmlUtils.getUmlModel(servicesRegistry);

		if ( ApexStellaProjectMap.getProjectMap().containsKey(projectPath) ) {
			projectWrapper = (ApexProjectWrapper) ApexStellaProjectMap.getProjectMap().get(projectPath);

			if ( !projectWrapper.getUmlModelMap().containsKey(diPath) ) {
				projectWrapper.put(diPath, umlModel);
				projectWrapper.setIsDisposed(diPath, new Boolean(false));
			}
			
		} else {
			projectWrapper = new ApexProjectWrapper(diFile.getProject());
			ApexStellaProjectMap.getProjectMap().put(projectPath, projectWrapper);
			projectWrapper.put(diPath, umlModel);
			projectWrapper.put(diPath, new Boolean(false));
		}

		return projectWrapper;
	}

	/**
	 * diFile이 close될 때 Services 처리
	 * 
	 * @param diFile
	 */
	public static void clearModelServices(IFile diFile) {

		String diPath = diFile.getLocationURI().getPath();
		String projectPath = diFile.getParent().getLocationURI().getPath();
		ApexProjectWrapper projectWrapper = null;
		projectWrapper = (ApexProjectWrapper) ApexStellaProjectMap.getProjectMap().get(projectPath);	

		if ( projectWrapper != null ) {
			if ( projectWrapper.getUmlModelMap().containsKey(diPath) ) {
				projectWrapper.removeUmlModel(diPath);	
			}
			if ( projectWrapper.getIsDisposedMap().containsKey(diPath) ) {
				projectWrapper.setIsDisposed(diPath, new Boolean(true));	
			}			
		}

	}
	
	/**
	 * diFile이 삭제될 때 Services 처리
	 * 
	 * @param diFile
	 */
	public static void removeModelServices(IFile diFile) {

		String diPath = diFile.getLocationURI().getPath();
		String projectPath = diFile.getParent().getLocationURI().getPath();
		ApexProjectWrapper projectWrapper = null;
		projectWrapper = (ApexProjectWrapper) ApexStellaProjectMap.getProjectMap().get(projectPath);

		if ( projectWrapper != null ) {
			if ( projectWrapper.getUmlModelMap().containsKey(diPath) ) {
				projectWrapper.removeUmlModel(diPath);
			}
			if ( projectWrapper.getIsDisposedMap().containsKey(diPath) ) {
				projectWrapper.removeIsDisposedMap(diPath);
			}			
		}

	}

	public static IEditorPart openEditor(IFile diFile) {
		IEditorPart result = null;
		try {
			result = IDE.openEditor(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage(), diFile, false);
		} catch (PartInitException e) {
			e.printStackTrace();
		}	
		return result;
	}
}
