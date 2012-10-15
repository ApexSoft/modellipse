/*****************************************************************************
 * Copyright (c) 2009 Atos Origin.
 *
 *    
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Emilien Perico (Atos Origin) emilien.perico@atosorigin.com - Initial API and implementation
 *
 *****************************************************************************/
package org.eclipse.papyrus.uml.diagram.usecase.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.papyrus.uml.diagram.usecase.part.UMLDiagramEditorPlugin;

/**
 * @generated
 */
public class DiagramPreferenceInitializer extends AbstractPreferenceInitializer {

	/**
	 * @generated
	 */
	public void initializeDefaultPreferences() {
		IPreferenceStore store = getPreferenceStore();
		UseCasePreferencePage.initDefaults(store);
		ShortCutDiagramPreferencePage.initDefaults(store);
		ClassifierPreferencePage.initDefaults(store);
		CommentPreferencePage.initDefaults(store);
		ConstraintPreferencePage.initDefaults(store);
		NamedElementPreferencePage.initDefaults(store);
		ActorPreferencePage.initDefaults(store);
		ExtensionPointPreferencePage.initDefaults(store);
		PackagePreferencePage.initDefaults(store);
		ComponentPreferencePage.initDefaults(store);
		DefaultNamedElementPreferencePage.initDefaults(store);
		ExtendPreferencePage.initDefaults(store);
		PackageMergePreferencePage.initDefaults(store);
		AbstractionPreferencePage.initDefaults(store);
		AssociationPreferencePage.initDefaults(store);
		GeneralizationPreferencePage.initDefaults(store);
		RealizationPreferencePage.initDefaults(store);
		DependencyPreferencePage.initDefaults(store);
		UsagePreferencePage.initDefaults(store);
		PackageImportPreferencePage.initDefaults(store);
		IncludePreferencePage.initDefaults(store);
	}

	/**
	 * @generated
	 */
	protected IPreferenceStore getPreferenceStore() {
		return UMLDiagramEditorPlugin.getInstance().getPreferenceStore();
	}
}
