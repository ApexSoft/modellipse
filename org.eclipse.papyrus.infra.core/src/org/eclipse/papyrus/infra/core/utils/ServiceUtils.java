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

import org.eclipse.papyrus.infra.core.services.ServiceException;
import org.eclipse.papyrus.infra.core.services.ServicesRegistry;

/**
 * @author cedric dumoulin
 * 
 */
public class ServiceUtils extends AbstractServiceUtils<ServicesRegistry> {

	private final static ServiceUtils instance = new ServiceUtils();

	/**
	 * Get the singleton instance of the class.
	 * 
	 * @return
	 */
	public static final ServiceUtils getInstance() {
		return instance;
	}

	/**
	 * @see org.eclipse.papyrus.infra.core.utils.AbstractServiceUtils#getServiceRegistry(java.lang.Object)
	 * 
	 * @param from
	 * @return
	 * @throws ServiceException
	 */
	@Override
	public ServicesRegistry getServiceRegistry(ServicesRegistry from) throws ServiceException {
		return from;
	}

}
