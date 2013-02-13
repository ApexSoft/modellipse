/**
 * 
 */
package org.eclipse.papyrus.infra.core.ui;

import org.eclipse.papyrus.infra.core.services.ServicesRegistry;

/**
 * This interface is used to get serviceRegsitry from Model Explorer View.
 * The kr.co.apexsoft.stella.modeler.explorer.ApexStellaExplorerView is implementing this interface.
 * 
 * @author hanmomhanda
 *
 */
public interface IApexStellaExplorerViewService {

	/**
	 * 
	 * @return ServiceRegistry a serviceRegistry which the implementor of this interface contains.
	 */
	public ServicesRegistry getServicesRegistry();
}
