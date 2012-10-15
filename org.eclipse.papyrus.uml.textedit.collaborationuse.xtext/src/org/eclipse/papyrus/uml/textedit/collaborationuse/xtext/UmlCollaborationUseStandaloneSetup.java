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
 *  Vincent Lorenzo (CEA LIST) vincent.lorenzo@cea.fr - Initial API and implementation
 *
 *****************************************************************************/

package org.eclipse.papyrus.uml.textedit.collaborationuse.xtext;

import org.eclipse.papyrus.uml.textedit.collaborationuse.xtext.UmlCollaborationUseStandaloneSetupGenerated;

/**
 * Initialization support for running Xtext languages
 * without equinox extension registry
 */
public class UmlCollaborationUseStandaloneSetup extends UmlCollaborationUseStandaloneSetupGenerated {

	public static void doSetup() {
		new UmlCollaborationUseStandaloneSetup().createInjectorAndDoEMFRegistration();
	}
}
