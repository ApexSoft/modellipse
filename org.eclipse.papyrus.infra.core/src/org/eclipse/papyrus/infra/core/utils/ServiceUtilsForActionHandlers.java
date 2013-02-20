/*****************************************************************************
 * Copyright (c) 2010 LIFL & CEA LIST.
 *
 *    
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Cedric Dumoulin (LIFL) cedric.dumoulin@lifl.fr - Initial API and implementation
 *
 *****************************************************************************/

package org.eclipse.papyrus.infra.core.utils;

import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.papyrus.infra.core.editor.IMultiDiagramEditor;
import org.eclipse.papyrus.infra.core.lifecycleevents.ILifeCycleEventsProvider;
import org.eclipse.papyrus.infra.core.resource.ModelSet;
import org.eclipse.papyrus.infra.core.sasheditor.contentprovider.IPageMngr;
import org.eclipse.papyrus.infra.core.sasheditor.editor.ISashWindowsContainer;
import org.eclipse.papyrus.infra.core.services.ServiceException;
import org.eclipse.papyrus.infra.core.services.ServiceNotFoundException;
import org.eclipse.papyrus.infra.core.services.ServicesRegistry;
import org.eclipse.papyrus.infra.core.ui.IApexStellaExplorerViewService;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;

/**
 * Set of utility methods for accessing core Services. This class provide
 * methods to access the Papyrus well known services. This class is designed to
 * be used from ui Action Handlers or from any code relying on the Eclipse
 * Active Editor. <br>
 * All methods from this class rely on the Eclipse Active Editor, which should
 * be an instance of {@link IMultiDiagramEditor}. If this is not the case,
 * methods throw an exception {@link ServiceException}.
 * 
 * @author cedric dumoulin
 * 
 */
public class ServiceUtilsForActionHandlers {

	private final static ServiceUtilsForActionHandlers instance = new ServiceUtilsForActionHandlers();

	/**
	 * Get the singleton instance of the class.
	 * 
	 * @return
	 */
	public static final ServiceUtilsForActionHandlers getInstance() {
		return instance;
	}

	/**
	 * apex updated
	 * 
	 * servicesRegistry를 에디터 또는 모델익스플로러에서 모두 받아오도록 수정
	 * 
	 * Get the service registry from the specified parameter.
	 * 
	 * @param from
	 * @return
	 */
	public ServicesRegistry getServiceRegistry() throws ServiceException {
		
		/* apex improved start */
		IWorkbenchPart workbenchPart = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActivePart();
		ServicesRegistry serviceRegistry = null;
		
		
		if ( workbenchPart instanceof IApexStellaExplorerViewService ) {
			try {
				IApexStellaExplorerViewService aStellaExplorerViewService = (IApexStellaExplorerViewService)workbenchPart;
				serviceRegistry = aStellaExplorerViewService.getServicesRegistry();
				
				if ( serviceRegistry == null ) { // 탐색기에서의 serviceRegistry가 null 인 경우는 최초 로딩 시 또는 최초 로딩 후 아무 에디터도 열려있지 않은 경우
					IEditorPart editor = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
					
					if ( editor == null ) { // editor가 null 인 경우는 최초 로딩 시 또는 아무 에디터도 열려있지 않아 탐색기에서 선택해도 serviceRegistry를 구성할 수 없는 경우
						throw new ServiceNotFoundException("Can't get the current Eclipse Active Editor. No ServiceRegistry found.");
					} else { // editor가 null 이 아닌 경우는 탐색기에서 에디터를 여는 단계, 이 경우 에디터에서 serviceRegistry를 가져온다.
						serviceRegistry = getServicesRegistryFromEditor(editor);
					}
				}
			} catch (NullPointerException e) {
				// Can't get the Stella Explorer
				throw new ServiceNotFoundException("Can't get the current Stella Explorer. No ServiceRegistry found.");
			}
			
		} else if ( workbenchPart instanceof IEditorPart ) {
			serviceRegistry = getServicesRegistryFromEditor((IEditorPart)workbenchPart);					
		}			
		
		if ( serviceRegistry == null ) {
			throw new ServiceNotFoundException("Can't get the ServiceRegtistry from the current Stella Explorer.");
		}
		
		System.out.println("ServiceUtilsForActionHandlers.getServiceRegistry(), line : " + Thread.currentThread().getStackTrace()[1].getLineNumber());
		ModelSet modelSet = serviceRegistry.getService(ModelSet.class);
		if ( modelSet != null ) {
			System.out.println("serviceRegistry : " + serviceRegistry.getService(ModelSet.class).getFilenameWithoutExtension());	
		}
		
		return serviceRegistry;	
						
		/* apex improved end */		
		
		/* apex replaced
		IEditorPart editor;
		try {
			editor = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();			
			ServicesRegistry serviceRegistry = (ServicesRegistry)editor.getAdapter(ServicesRegistry.class);
			if(serviceRegistry != null) {
				return serviceRegistry;
			}
		} catch (NullPointerException e) {
			// Can't get the active editor
			throw new ServiceNotFoundException("Can't get the current Eclipse Active Editor. No ServiceRegistry found.");
		}
		


		// Not found
		throw new ServiceNotFoundException("Can't get the ServiceRegistry from current Eclipse Active Editor");
		//*/

	}
	
	private ServicesRegistry getServicesRegistryFromEditor(IEditorPart editor) throws ServiceException {
		try {
			editor = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();			
			ServicesRegistry serviceRegistry = (ServicesRegistry)editor.getAdapter(ServicesRegistry.class);
			if(serviceRegistry != null) {
				return serviceRegistry;
			}
		} catch (NullPointerException e) {
			// Can't get the active editor
			throw new ServiceNotFoundException("Can't get the current Eclipse Active Editor. No ServiceRegistry found.");
		}
		


		// Not found
		throw new ServiceNotFoundException("Can't get the ServiceRegistry from current Eclipse Active Editor");
		//*/
	}

	/**
	 * Gets the {@link TransactionalEditingDomain} registered in the {@link ServicesRegistry}.
	 * 
	 * @return
	 * @throws ServiceException
	 *         If an error occurs while getting the requested service.
	 */
	public TransactionalEditingDomain getTransactionalEditingDomain() throws ServiceException {
		return getServiceRegistry().getService(TransactionalEditingDomain.class);
	}

	/**
	 * Gets the {@link IPageMngr} registered in the {@link ServicesRegistry}.
	 * 
	 * @return
	 * @throws ServiceException
	 *         If an error occurs while getting the requested service.
	 */
	public IPageMngr getIPageMngr() throws ServiceException {
		return getServiceRegistry().getService(IPageMngr.class);
	}

	/**
	 * Gets the {@link IPageMngr} registered in the {@link ServicesRegistry}.
	 * 
	 * @return
	 * @throws ServiceException
	 *         If an error occurs while getting the requested service.
	 */
	public ModelSet getModelSet() throws ServiceException {
		return getServiceRegistry().getService(ModelSet.class);
	}

	/**
	 * Gets the {@link ILifeCycleEventsProvider} registered in the {@link ServicesRegistry}.
	 * 
	 * @param from
	 * @return
	 * @throws ServiceException
	 *         If an error occurs while getting the requested service.
	 */
	public ILifeCycleEventsProvider getILifeCycleEventsProvider() throws ServiceException {
		return getServiceRegistry().getService(ILifeCycleEventsProvider.class);
	}

	/**
	 * Gets the {@link ISashWindowsContainer} registered in the {@link ServicesRegistry}.
	 * 
	 * @param from
	 * @return
	 * @throws ServiceException
	 *         If an error occurs while getting the requested service.
	 */
	public ISashWindowsContainer getISashWindowsContainer() throws ServiceException {
		return getServiceRegistry().getService(ISashWindowsContainer.class);
	}

	/**
	 * Gets the {@link IEditorPart} of the currently nested active editor.
	 * 
	 * @param from
	 * @return
	 * @throws ServiceException
	 *         If an error occurs while getting the requested service.
	 */
	public IEditorPart getNestedActiveIEditorPart() throws ServiceException {
		return getISashWindowsContainer().getActiveEditor();
	}
}
