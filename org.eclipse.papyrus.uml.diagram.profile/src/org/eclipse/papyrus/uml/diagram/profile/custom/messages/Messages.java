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


package org.eclipse.papyrus.uml.diagram.profile.custom.messages;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {

	private static final String BUNDLE_NAME = "org.eclipse.papyrus.uml.diagram.profile.custom.messages.messages"; //$NON-NLS-1$

	public static String CustomSemanticCreateCommand_MetaclassImport0;

	public static String CustomSemanticCreateCommand_FetchingMetaclasses;

	public static String ChooseSetMetaclassDialog_Metaclasses;

	public static String ChooseSetMetaclassDialog_ImportedMetaclasses;

	public static String ChooseSetMetaclassDialog_Metaclass;

	public static String ChooseSetMetaclassDialog_Information;

	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
