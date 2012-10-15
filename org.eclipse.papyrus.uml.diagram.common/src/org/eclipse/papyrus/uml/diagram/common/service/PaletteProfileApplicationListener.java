/*****************************************************************************
 * Copyright (c) 2009 CEA LIST.
 *    
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Remi Schnekenburger (CEA LIST) remi.schnekenburger@cea.fr - Initial API and implementation
 *
 *****************************************************************************/

package org.eclipse.papyrus.uml.diagram.common.service;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.gmf.runtime.common.core.service.ProviderChangeEvent;
import org.eclipse.papyrus.infra.core.listenerservice.IPapyrusListener;
import org.eclipse.uml2.uml.ProfileApplication;

/**
 * Listener for the palette Service that updates palette contribution in case of
 * profile application/unapplication
 */
public class PaletteProfileApplicationListener implements IPapyrusListener {

	/**
	 * Creates a new PaletteProfileApplicationListener.
	 */
	public PaletteProfileApplicationListener() {
	}

	/**
	 * {@inheritDoc}
	 */
	public void notifyChanged(Notification notification) {
		// check notification is relevant for us
		// notifier shoud be instance of profileApplication. In this case,
		// reload the palette
		if(notification.getNotifier() instanceof ProfileApplication) {
			if(Notification.SET == notification.getEventType()) {
				PapyrusPaletteService.getInstance().providerChanged(new ProviderChangeEvent(PapyrusPaletteService.getInstance()));
			}
		}
	}
}
