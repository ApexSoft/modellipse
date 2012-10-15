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
 *  Patrick Tessier (CEA LIST) Patrick.tessier@cea.fr - Initial API and implementation
 *
 *****************************************************************************/
package org.eclipse.papyrus.infra.gmfdiag.preferences.ui.diagram;

import org.eclipse.jface.dialogs.DialogPage;
import org.eclipse.papyrus.infra.gmfdiag.preferences.ui.DecorationGroup;
import org.eclipse.papyrus.infra.gmfdiag.preferences.utils.PreferenceConstantHelper;
import org.eclipse.swt.widgets.Composite;

// TODO: Auto-generated Javadoc
/**
 * The Class DiagramDecorationGroupthat specialize Decoration group for the diagram level
 */
public class DiagramDecorationGroup extends DecorationGroup {

	/**
	 * Instantiates a new diagram decoration group.
	 * 
	 * @param parent
	 *        the parent
	 * @param key
	 *        the prefix preference key the kind of diagram
	 * @param dialogPage
	 *        the dialog page
	 */
	public DiagramDecorationGroup(Composite parent, String key, DialogPage dialogPage) {
		super(parent, key, dialogPage);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.papyrus.infra.gmfdiag.preferences.ui.AbstractGroup#getPreferenceConstant(int)
	 */
	protected String getPreferenceConstant(int preferenceType) {
		return PreferenceConstantHelper.getDiagramConstant(getKey(), preferenceType);
	}
}
