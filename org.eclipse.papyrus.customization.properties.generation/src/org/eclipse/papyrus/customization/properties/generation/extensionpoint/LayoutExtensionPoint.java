/*****************************************************************************
 * Copyright (c) 2010 CEA LIST.
 *    
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Camille Letavernier (CEA LIST) camille.letavernier@cea.fr - Initial API and implementation
 *****************************************************************************/
package org.eclipse.papyrus.customization.properties.generation.extensionpoint;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.papyrus.customization.properties.generation.layout.ILayoutGenerator;
import org.eclipse.papyrus.customization.properties.generation.wizard.CreateContextWizard;
import org.eclipse.papyrus.infra.tools.util.ClassLoaderHelper;
import org.eclipse.papyrus.views.properties.Activator;

/**
 * Handles the extension point org.eclipse.papyrus.customization.properties.generation.layout
 * Registers the given layout Generator to the Property view generation wizard
 * 
 * @author Camille Letavernier
 */
public class LayoutExtensionPoint {

	private final String EXTENSION_ID = "org.eclipse.papyrus.customization.properties.generation.layout"; //$NON-NLS-1$

	/**
	 * Constructor.
	 */
	public LayoutExtensionPoint() {
		IConfigurationElement[] config = Platform.getExtensionRegistry().getConfigurationElementsFor(EXTENSION_ID);

		for(IConfigurationElement e : config) {
			String generatorClassName = e.getAttribute("generator"); //$NON-NLS-1$
			ILayoutGenerator generator = ClassLoaderHelper.newInstance(generatorClassName, ILayoutGenerator.class);
			if(generator == null) {
				Activator.log.warn("Cannot instantiate the layout generator : " + generatorClassName); //$NON-NLS-1$
				continue;
			}
			CreateContextWizard.addLayoutGenerator(generator);
		}
	}
}
