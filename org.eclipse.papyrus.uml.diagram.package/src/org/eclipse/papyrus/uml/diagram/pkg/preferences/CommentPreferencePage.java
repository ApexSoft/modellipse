/*****************************************************************************
 * Copyright (c) 2011 CEA LIST.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *		
 *		CEA LIST - Initial API and implementation
 *
 *****************************************************************************/
package org.eclipse.papyrus.uml.diagram.pkg.preferences;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.papyrus.uml.diagram.pkg.provider.ElementTypes;

public class CommentPreferencePage extends PackageDiagramNodePreferencePage {

	/** Constant key to access preferences */
	protected static String prefKey = ElementTypes.DIAGRAM_ID + "_Comment"; //$NON-NLS-1$


	/** Default constructor */
	public CommentPreferencePage() {
		super();
		setPreferenceKey(ElementTypes.DIAGRAM_ID + "_Comment"); //$NON-NLS-1$
	}

	/**
	 * Initialize defaults using a specified {@link IPreferenceStore}
	 * 
	 * @param store
	 *        the preference store.
	 */
	public static void initDefaults(IPreferenceStore store) {
		// Start of user code custom default initializations
		// End of user code

	}


}
