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
 *  CEA LIST - Initial API and implementation
 *
 *****************************************************************************/

package org.eclipse.papyrus.uml.textedit.connectionpointreference.xtext;

import org.eclipse.papyrus.uml.textedit.connectionpointreference.xtext.UMLConnectionPointReferenceStandaloneSetupGenerated;

/**
 * Initialization support for running Xtext languages 
 * without equinox extension registry
 */
public class UMLConnectionPointReferenceStandaloneSetup extends UMLConnectionPointReferenceStandaloneSetupGenerated{

	public static void doSetup() {
		new UMLConnectionPointReferenceStandaloneSetup().createInjectorAndDoEMFRegistration();
	}
}

