/*****************************************************************************
 * Copyright (c) 2009 CEA LIST.
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
package org.eclipse.papyrus.uml.diagram.clazz.custom.policies;

import org.eclipse.papyrus.uml.diagram.clazz.custom.helper.AssociationEndSourceLabelHelper;

/**
 * Mask Managed label edit policy for association ends (source role)
 */
public class DisplayAssociationEndSourceEditPolicy extends DisplayAssociationEndEditPolicy {

	public void addAdditionalListeners() {
		super.addAdditionalListeners();
		// adds a listener to the element itself, and to linked elements, like Type
		if(getView().eContainer() != null) {
			getDiagramEventBroker().addNotificationListener(getView().eContainer(), this);

		}
	}

	/**
	 * Instantiates a new display association end source edit policy.
	 */
	public DisplayAssociationEndSourceEditPolicy() {
		super();
		propertyLabelHelper = AssociationEndSourceLabelHelper.getInstance();
	}

	
}
