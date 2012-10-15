/*****************************************************************************
 * Copyright (c) 2011 CEA LIST.
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
package org.eclipse.papyrus.infra.hyperlink.util;

public final class HyperLinkConstants {

	/**
	 * 
	 * Constructor.
	 *
	 */
	private HyperLinkConstants(){
		//nothing to do
	}
	
	/**
	 * this is a key of eAnnnotation that contains hypertext link or referenced document
	 **/
	public static final String PAPYRUS_HYPERLINK_PREFIX = "PapyrusHyperLink_"; //$NON-NLS-1$
	
	public static final String HYPERLINK_DOCUMENT = PAPYRUS_HYPERLINK_PREFIX + "Document"; //$NON-NLS-1$

	public static final String HYPERLINK_WEB = PAPYRUS_HYPERLINK_PREFIX + "_web"; //$NON-NLS-1$
	
	public static final String HYPERLINK_TOOLTYPE_TEXT = "tooltip_text"; //$NON-NLS-1$

	public static final String HYPERLINK_IS_DEFAULT_NAVIGATION = "is_default_navigation"; //$NON-NLS-1$

	public static final String HYPERLINK_DOCUMENT_LOCALIZATION = "localization"; //$NON-NLS-1$

	public static final String HYPERLINK_WEB_LINK = "link"; //$NON-NLS-1$
}
