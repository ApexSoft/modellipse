/******************************************************************************
 * Copyright (c) 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    IBM Corporation - initial API and implementation 
 ****************************************************************************/

package org.eclipse.papyrus.infra.gmfdiag.preferences.utils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.widgets.Display;

/**
 * Helper for fonts.
 * 
 * For now, it contains a method to get the font names.
 * 
 * @author wdiu, Wayne Diu
 * @canBeSeenBy %level1
 */
public class FontHelper {

	private static String[] FONT_NAMES;

	/**
	 * array of font sizes
	 */
	protected static final String[] FONT_SIZES = { "8", //$NON-NLS-1$
	"9", //$NON-NLS-1$
	"10", //$NON-NLS-1$
	"11", //$NON-NLS-1$
	"12", //$NON-NLS-1$
	"14", //$NON-NLS-1$
	"16", //$NON-NLS-1$
	"18", //$NON-NLS-1$
	"20", //$NON-NLS-1$
	"22", //$NON-NLS-1$
	"24", //$NON-NLS-1$
	"26", //$NON-NLS-1$
	"28", //$NON-NLS-1$
	"36", //$NON-NLS-1$
	"48", //$NON-NLS-1$
	"72" }; //$NON-NLS-1$

	/**
	 * Return the font names for the default display.
	 * 
	 * @return String array of font names as String objects for the default display.
	 */
	static public String[] getFontNames() {
		if(FONT_NAMES != null)
			return FONT_NAMES;

		// add the names into a set to get a set of unique names
		Set<String> stringItems = new HashSet<String>();
		FontData[] fontDatas = Display.getDefault().getFontList(null, true);
		for(int i = 0; i < fontDatas.length; i++) {
			if(fontDatas[i].getName() != null) {
				stringItems.add(fontDatas[i].getName());
			}
		}

		// add strings into the array
		String strings[] = new String[stringItems.size()];
		int i = 0;
		for(Iterator<String> it = stringItems.iterator(); it.hasNext();) {
			strings[i++] = it.next();
		}

		// sort the array
		Arrays.sort(strings);

		return FONT_NAMES = strings;
	}

	/**
	 * @return - array of font sizes
	 */
	public static final String[] getFontSizes() {
		return FONT_SIZES;
	}

}
