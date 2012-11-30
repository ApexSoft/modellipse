package kr.co.apexsoft.modellipse.explorer.core;

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

public class ApexModellipseProjectMap {

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
	 * @param servicesRegistry
	 */
	public static ApexProjectWrapper setUpModelServices(IFile diFile, ServicesRegistry servicesRegistry) {

			String diPath = diFile.getLocationURI().getPath();
			String projectPath = diFile.getParent().getLocationURI().getPath();
			ApexProjectWrapper projectWrapper = null;

//			ISaveAndDirtyService saveAndDirtyService = null;
//			IUndoContext undoContext = null;
//			TransactionalEditingDomain transactionalEditingDomain = null;
			UmlModel umlModel = null;


			// Get required services from ServicesRegistry
//			try {
//				saveAndDirtyService = servicesRegistry.getService(ISaveAndDirtyService.class);
//				undoContext = servicesRegistry.getService(IUndoContext.class);
//				transactionalEditingDomain = ServiceUtils.getInstance().getTransactionalEditingDomain(servicesRegistry);
//			} catch (ServiceException e) {
//				e.printStackTrace();
//			}
			umlModel =  UmlUtils.getUmlModel(servicesRegistry);

			if ( ApexModellipseProjectMap.getProjectMap().containsKey(projectPath) ) {
				projectWrapper = (ApexProjectWrapper) ApexModellipseProjectMap.getProjectMap().get(projectPath);

//				if ( !projectWrapper.getServicesRegistryMap().containsKey(diPath) ) {
//					projectWrapper.put(diPath, servicesRegistry);
//					projectWrapper.addServicesRegistryChildren(servicesRegistry);
//				}
//				if ( !projectWrapper.getSaveAndDirtyServiceMap().containsKey(diPath) ) {
//					projectWrapper.put(diPath, saveAndDirtyService);
//				}
//				if ( !projectWrapper.getTransactionalEditingDomainMap().containsKey(diPath) ) {
//					projectWrapper.put(diPath, transactionalEditingDomain);
//				}
//				if ( !projectWrapper.getUndoContextMap().containsKey(diPath) ) {
//					projectWrapper.put(diPath, undoContext);
//				}
//				if ( !projectWrapper.getDiFileMap().containsKey(diPath) ) {
//					projectWrapper.put(diPath, diFile);					
//				}
				if ( !projectWrapper.getUmlModelMap().containsKey(diPath) ) {
					projectWrapper.put(diPath, umlModel);
					projectWrapper.setIsDisposed(diPath, new Boolean(false));
//					projectWrapper.addChildren(umlModel);	
				}
//				if ( !projectWrapper.getPropertySheetPageMap().containsKey(diPath) ) {
//					projectWrapper.put(diPath, propertySheetPage);
//				}				
			} else {
				projectWrapper = new ApexProjectWrapper(diFile.getProject());
				ApexModellipseProjectMap.getProjectMap().put(projectPath, projectWrapper);
//				projectWrapper.addChildren(umlModel);
//				projectWrapper.addServicesRegistryChildren(servicesRegistry);
//				projectWrapper.put(diPath, servicesRegistry);
//				projectWrapper.put(diPath, saveAndDirtyService);
//				projectWrapper.put(diPath, transactionalEditingDomain);
//				projectWrapper.put(diPath, undoContext);
//				projectWrapper.put(diPath, diFile);
				projectWrapper.put(diPath, umlModel);
//				projectWrapper.put(diPath, propertySheetPage);
				projectWrapper.put(diPath, new Boolean(false));
			}

//			projectWrapper.addChildren(servicesRegistry);
//			ApexModellipseExplorerRoot.getServicesRegistryList().add(servicesRegistry);	
//		}
		return projectWrapper;
	}

	public static void clearModelServices(IFile diFile) {

		String diPath = diFile.getLocationURI().getPath();
		String projectPath = diFile.getParent().getLocationURI().getPath();
		ApexProjectWrapper projectWrapper = null;
		projectWrapper = (ApexProjectWrapper) ApexModellipseProjectMap.getProjectMap().get(projectPath);

//		if ( servicesRegsitryMap.containsKey(diPath) ) {
//			servicesRegsitryMap.remove(diPath);
//			projectWrapper.addServicesRegistryChildren(servicesRegistry);
//		}
//		if ( !projectWrapper.getSaveAndDirtyServiceMap().containsKey(diPath) ) {
//			projectWrapper.put(diPath, saveAndDirtyService);
//		}
//		if ( !projectWrapper.getTransactionalEditingDomainMap().containsKey(diPath) ) {
//			projectWrapper.put(diPath, transactionalEditingDomain);
//		}
//		if ( !projectWrapper.getUndoContextMap().containsKey(diPath) ) {
//			projectWrapper.put(diPath, undoContext);
//		}
//		if ( !projectWrapper.getDiFileMap().containsKey(diPath) ) {
//			projectWrapper.put(diPath, diFile);					
//		}
		if ( projectWrapper.getUmlModelMap().containsKey(diPath) ) {
			projectWrapper.removeUmlModel(diPath);
//			projectWrapper.removeChildren(umlModel);	
		}
		if ( projectWrapper.getIsDisposedMap().containsKey(diPath) ) {
			projectWrapper.setIsDisposed(diPath, new Boolean(true));
//			projectWrapper.removeChildren(umlModel);	
		}
	}

	public static IEditorPart openEditor(IFile diFile) {
		IEditorPart result = null;
		try {
			result = IDE.openEditor(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage(), diFile, true);
		} catch (PartInitException e) {
			e.printStackTrace();
		}	
		return result;
	}
//	public static List<ServicesRegistry> getServicesRegistryList() {
//		return _serviceRegistryList;
//	}
}