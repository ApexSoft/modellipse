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
 * 
 * 		Yann Tanguy (CEA LIST) yann.tanguy@cea.fr - Initial API and implementation
 *
 *****************************************************************************/
package org.eclipse.papyrus.infra.services.edit.service;

import org.eclipse.osgi.util.NLS;
import org.eclipse.papyrus.infra.core.services.ServiceException;
import org.eclipse.papyrus.infra.services.edit.Activator;
import org.eclipse.papyrus.infra.services.edit.internal.ElementEditServiceProvider;
import org.eclipse.papyrus.infra.services.edit.messages.Messages;

/**
 * <pre>
 * 
 * Utility class for a convenient access to edit services.
 * 
 * </pre>
 */
public class ElementEditServiceUtils {

	/**
	 * <pre>
	 * Try to retrieve an edit service for the object in parameter
	 * (EObject or EClass expected).
	 * 
	 * Current implementation directly use {@link IElementEditServiceProvider} instance
	 * rather than using Papyrus {@link ServiceUtils} which requires Papyrus to be
	 * the active editor.
	 * 
	 * @param objectToEdit
	 * @return the edit service or null
	 * </pre>
	 */
	public static IElementEditService getCommandProvider(Object objectToEdit) {

		try {
			return getEditServiceProvider().getEditService(objectToEdit);
		} catch (ServiceException e) {
			Activator.log.error(NLS.bind(Messages.ElementEditServiceUtils_UnableToFindElementType, objectToEdit), e);
		} catch (NullPointerException e) {
			Activator.log.error(NLS.bind(Messages.ElementEditServiceUtils_UnableToFindElementType, objectToEdit), e);
		}

		return null;

		//ServicesRegistry serviceRegistry = EditorUtils.getServiceRegistry();
		//		try {
		//			IElementEditServiceProvider serviceProvider = serviceRegistry.getService(IElementEditServiceProvider.class);
		//			IElementEditService service = serviceProvider.getEditService(objectToEdit);
		//			return serviceRegistry == null ? null : service;
		//		} catch (ServiceException e) {
		//			return null;
		//		}
	}

	/**
	 * <pre>
	 * Get the edit service provider (using {@link ElementTypeRegistry} instead of
	 * {@link ServiceUtils}). Note that {@link ServiceUtils} would return the same instance
	 * anyway.
	 * 
	 * @return the service provider
	 * </pre>
	 */
	public static IElementEditServiceProvider getEditServiceProvider() {

		try {
			return getEditServiceProviderFromElementTypeRegistry();
		} catch (ServiceException e) {
			Activator.log.error(Messages.ElementEditServiceUtils_UnableToFindServiceProvider, e);
		}

		return null;

		//		ServicesRegistry serviceRegistry = EditorUtils.getServiceRegistry();
		//		try {
		//			IElementEditServiceProvider serviceProvider = serviceRegistry.getService(IElementEditServiceProvider.class);
		//			return serviceRegistry == null ? null : serviceProvider;
		//		} catch (ServiceException e) {
		//			return null;
		//		}
	}

	/**
	 * Get the element edit service provider from the {@link ElementTypeRegistry}.
	 * 
	 * @return the element edit service
	 * @throws ServiceException
	 */
	protected static IElementEditServiceProvider getEditServiceProviderFromElementTypeRegistry() throws ServiceException {
		return ElementEditServiceProvider.getInstance();
	}

}
