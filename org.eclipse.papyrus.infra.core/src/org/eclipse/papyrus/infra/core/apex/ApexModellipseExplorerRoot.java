package org.eclipse.papyrus.infra.core.apex;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.commands.operations.IUndoContext;
import org.eclipse.core.resources.IFile;
import org.eclipse.emf.facet.infra.browser.uicore.internal.model.ITreeElement;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.papyrus.infra.core.lifecycleevents.ISaveAndDirtyService;
import org.eclipse.papyrus.infra.core.resource.uml.UmlModel;
import org.eclipse.papyrus.infra.core.resource.uml.UmlUtils;
import org.eclipse.papyrus.infra.core.services.ServiceException;
import org.eclipse.papyrus.infra.core.services.ServicesRegistry;
import org.eclipse.papyrus.infra.core.utils.ServiceUtils;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.operations.RedoActionHandler;
import org.eclipse.ui.operations.UndoActionHandler;

public class ApexModellipseExplorerRoot {
	
	/**
	 * Key : .di 파일의 Path
	 * Value : ApexProjectWrapper 
	 */
	private static Map<String, ITreeElement> _projectMap = new HashMap<String, ITreeElement>();
	
//	private static List<ServicesRegistry> _serviceRegistryList = new ArrayList<ServicesRegistry>();
	
	public static Map<String, ITreeElement> getProjectMap() {
		return _projectMap;
	}	
	
	/**
	 * apex added
	 * 
	 * @param servicesRegistry
	 */
	public static void addToGlobalRoot(IFile diFile, ServicesRegistry servicesRegistry) {
		
			String diPath = diFile.getLocationURI().getPath();
			String projectPath = diFile.getParent().getLocationURI().getPath();
			ApexProjectWrapper projectWrapper = null;
			
			ISaveAndDirtyService saveAndDirtyService = null;
			IUndoContext undoContext = null;
			TransactionalEditingDomain transactionalEditingDomain = null;
			UmlModel umlModel = null;
			
			
			// Get required services from ServicesRegistry
			try {
				saveAndDirtyService = servicesRegistry.getService(ISaveAndDirtyService.class);
				undoContext = servicesRegistry.getService(IUndoContext.class);
				transactionalEditingDomain = ServiceUtils.getInstance().getTransactionalEditingDomain(servicesRegistry);
			} catch (ServiceException e) {
				e.printStackTrace();
			}
			umlModel =  UmlUtils.getUmlModel(servicesRegistry);
			
			if ( ApexModellipseExplorerRoot.getProjectMap().containsKey(projectPath) ) {
				projectWrapper = (ApexProjectWrapper) ApexModellipseExplorerRoot.getProjectMap().get(projectPath);
				
				if ( !projectWrapper.getServicesRegistryMap().containsKey(diPath) ) {
					projectWrapper.put(diPath, servicesRegistry);
					projectWrapper.addServicesRegistryChildren(servicesRegistry);
				}
				if ( !projectWrapper.getSaveAndDirtyServiceMap().containsKey(diPath) ) {
					projectWrapper.put(diPath, saveAndDirtyService);
				}
				if ( !projectWrapper.getTransactionalEditingDomainMap().containsKey(diPath) ) {
					projectWrapper.put(diPath, transactionalEditingDomain);
				}
				if ( !projectWrapper.getUndoContextMap().containsKey(diPath) ) {
					projectWrapper.put(diPath, undoContext);
				}
				if ( !projectWrapper.getDiFileMap().containsKey(diPath) ) {
					projectWrapper.put(diPath, diFile);					
				}
				if ( !projectWrapper.getUmlModelMap().containsKey(diPath) ) {
					projectWrapper.put(diPath, umlModel);
					projectWrapper.addChildren(umlModel);	
				}
//				if ( !projectWrapper.getPropertySheetPageMap().containsKey(diPath) ) {
//					projectWrapper.put(diPath, propertySheetPage);
//				}
				
			} else {
				projectWrapper = new ApexProjectWrapper(diFile.getProject());
				ApexModellipseExplorerRoot.getProjectMap().put(projectPath, projectWrapper);
				projectWrapper.addChildren(umlModel);
				projectWrapper.addServicesRegistryChildren(servicesRegistry);
				projectWrapper.put(diPath, servicesRegistry);
				projectWrapper.put(diPath, saveAndDirtyService);
				projectWrapper.put(diPath, transactionalEditingDomain);
				projectWrapper.put(diPath, undoContext);
				projectWrapper.put(diPath, diFile);
				projectWrapper.put(diPath, umlModel);
//				projectWrapper.put(diPath, propertySheetPage);
			}
			
//			projectWrapper.addChildren(servicesRegistry);
//			ApexModellipseExplorerRoot.getServicesRegistryList().add(servicesRegistry);	
//		}
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
