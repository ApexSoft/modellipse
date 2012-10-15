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

package org.eclipse.papyrus.uml.textedit.state.xtext;

import org.eclipse.papyrus.uml.textedit.state.xtext.UmlStateStandaloneSetupGenerated;

/**
 * Initialization support for running Xtext languages 
 * without equinox extension registry
 */
public class UmlStateStandaloneSetup extends UmlStateStandaloneSetupGenerated{

	public static void doSetup() {
		new UmlStateStandaloneSetup().createInjectorAndDoEMFRegistration();
	}
}

