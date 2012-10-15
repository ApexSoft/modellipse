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
package org.eclipse.papyrus.customization.properties;

import java.util.List;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.facet.infra.browser.custom.MetamodelView;
import org.eclipse.emf.facet.infra.browser.custom.core.CustomizationsCatalog;
import org.eclipse.emf.facet.infra.browser.uicore.CustomizationManager;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.papyrus.infra.core.log.LogHelper;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/** The activator class controls the plug-in life cycle */
public class Activator extends AbstractUIPlugin {

	/**
	 * The plug-in ID
	 */
	public static final String PLUGIN_ID = "org.eclipse.papyrus.customization.properties"; //$NON-NLS-1$

	/**
	 * The Preview view ID
	 */
	public static final String PREVIEW_ID = "org.eclipse.papyrus.customization.properties.preview"; //$NON-NLS-1$

	/**
	 * The Customization perspective ID
	 */
	public static final String CUSTOMIZATION_PERSPECTIVE_ID = "org.eclipse.papyrus.customization.properties.perspective"; //$NON-NLS-1$

	// The shared instance
	private static Activator plugin;

	/**
	 * The plug-in logger
	 */
	public static LogHelper log;

	private CustomizationManager fCustomizationManager;

	@Override
	public void start(final BundleContext context) throws Exception {
		super.start(context);
		Activator.plugin = this;
		log = new LogHelper(plugin);
	}

	@Override
	public void stop(final BundleContext context) throws Exception {
		Activator.plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 * 
	 * @return the shared instance
	 */
	public static Activator getDefault() {
		return Activator.plugin;
	}

	/**
	 * 
	 * @return the customization manager in charge to adapt element in modisco
	 */
	public CustomizationManager getCustomizationManager() {
		if(this.fCustomizationManager == null) {
			this.fCustomizationManager = new CustomizationManager();
			init(this.fCustomizationManager);
		}
		return this.fCustomizationManager;
	}

	private void init(final CustomizationManager customizationManager) {
		customizationManager.setShowContainer(false);
		customizationManager.setShowDerivedLinks(true);
		customizationManager.setShowEmptyLinks(true);

		try {
			List<MetamodelView> registryDefaultCustomizations = CustomizationsCatalog.getInstance().getRegistryDefaultCustomizations();
			for(MetamodelView metamodelView : registryDefaultCustomizations) {
				customizationManager.registerCustomization(metamodelView);
			}

			customizationManager.loadCustomizations();
		} catch (Throwable e) {
			Activator.getDefault().getLog().log(new Status(IStatus.ERROR, Activator.PLUGIN_ID, "Error initializing customizations", e)); //$NON-NLS-1$
		}
	}

	/**
	 * @return The IPath representing the plugin's preferences folder location
	 */
	public IPath getPreferencesPath() {
		return getStateLocation();
	}

	/**
	 * Returns the image at the given path from this plugin
	 * 
	 * @param path
	 *        the path of the image to be displayed
	 * @return The Image at the given location, or null if it couldn't be found
	 */
	public Image getImage(String path) {
		return getImage(PLUGIN_ID, path);
	}

	/**
	 * Returns the image from the given image descriptor
	 * 
	 * @param pluginId
	 *        The plugin in which the image is located
	 * @param path
	 *        The path to the image from the plugin
	 * @return
	 *         The Image at the given location, or null if it couldn't be found
	 */
	public Image getImage(String pluginId, String path) {
		final ImageRegistry registry = getImageRegistry();
		String key = pluginId + "/" + path; //$NON-NLS-1$
		Image image = registry.get(key);
		if(image == null) {
			registry.put(key, AbstractUIPlugin.imageDescriptorFromPlugin(pluginId, path));
			image = registry.get(key);
		}
		return image;
	}
}
