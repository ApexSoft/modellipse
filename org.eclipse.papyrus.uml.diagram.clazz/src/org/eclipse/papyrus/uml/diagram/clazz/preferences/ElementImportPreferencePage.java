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
 */
package org.eclipse.papyrus.uml.diagram.clazz.preferences;

import java.util.Map;
import java.util.TreeMap;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.papyrus.infra.gmfdiag.preferences.pages.AbstractPapyrusLinkPreferencePage;
import org.eclipse.papyrus.infra.gmfdiag.preferences.utils.PreferenceConstantHelper;
import org.eclipse.papyrus.uml.diagram.clazz.edit.parts.ModelEditPart;
import org.eclipse.papyrus.uml.diagram.clazz.part.UMLDiagramEditorPlugin;
import org.eclipse.papyrus.uml.diagram.common.util.StringComparator;

/**
 * @generated
 */
public class ElementImportPreferencePage extends AbstractPapyrusLinkPreferencePage {

	/**
	 * @generated
	 */
	public ElementImportPreferencePage() {
		super();
		setPreferenceKey(ModelEditPart.MODEL_ID + "_ElementImport");
	}

	/**
	 * @generated
	 */
	@Override
	protected String getBundleId() {
		return UMLDiagramEditorPlugin.ID;
	}

	/**
	 * @generated
	 */
	public static void initDefaults(IPreferenceStore store) {
		String key = ModelEditPart.MODEL_ID + "_ElementImport";
		Map<String, Boolean> map = getStaticLabelVisibilityPreferences();
		for(String role : map.keySet()) {
			String preferenceName = PreferenceConstantHelper.getLabelElementConstant(key, role, PreferenceConstantHelper.LABEL_VISIBILITY);
			store.setDefault(preferenceName, map.get(role));
		}
	}

	/**
	 * @generated
	 */
	private static TreeMap<String, String> getStaticLabelRole() {
		TreeMap<String, String> map = new TreeMap<String, String>(new StringComparator());
		map.put("Alias", "platform:/plugin/org.eclipse.papyrus.uml.diagram.common/icons/label_role/alias.png");//$NON-NLS-1$ //$NON-NLS-2$
		map.put("Stereotype", "platform:/plugin/org.eclipse.uml2.uml.edit/icons/full/obj16/Stereotype.gif");//$NON-NLS-1$ //$NON-NLS-2$
		return map;
	}

	/**
	 * @generated
	 */
	private static TreeMap<String, Boolean> getStaticLabelVisibilityPreferences() {
		TreeMap<String, Boolean> map = new TreeMap<String, Boolean>();
		map.put("Alias", Boolean.TRUE);
		map.put("Stereotype", Boolean.TRUE);
		return map;
	}

	/**
	 * @generated
	 */
	protected TreeMap<String, String> getLabelRole() {
		return getStaticLabelRole();
	}
}
