/*****************************************************************************
 * Copyright (c) 2011 Atos.
 *
 *    
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Vincent Hemery (Atos) vincent.hemery@atos.net - Initial API and implementation
 *
 *****************************************************************************/
package org.eclipse.papyrus.views.modelexplorer.resourceloading;

import java.net.URL;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.edit.EMFEditPlugin;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.papyrus.infra.core.log.LogHelper;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends AbstractUIPlugin implements org.eclipse.ui.IStartup {

	/** The plug-in ID */
	public static final String PLUGIN_ID = "org.eclipse.papyrus.views.modelexplorer.resourceLoading"; //$NON-NLS-1$

	/** The plug-in shared instance */
	private static Activator plugin;

	/** The log service */
	public static LogHelper log;

	/** Default constructor */
	public Activator() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext
	 * )
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
		log = new LogHelper(plugin);
		EMFEditPlugin.getComposedAdapterFactoryDescriptorRegistry();

	}

	/**
	 * get the image descriptor from a string path
	 * 
	 * @param pathString
	 *        path of the image
	 * @return the image descriptor
	 */
	public static ImageDescriptor getImageDescriptor(String pathString) {

		IPath path = new Path(pathString);
		URL uri = FileLocator.find(Activator.plugin.getBundle(), path, null);
		if(uri == null) {
			return null;
		}
		return ImageDescriptor.createFromURL(uri);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext
	 * )
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 * 
	 * @return the shared instance
	 */
	public static Activator getDefault() {
		return plugin;
	}

	/**
	 * 
	 * @see org.eclipse.ui.IStartup#earlyStartup()
	 * 
	 */
	public void earlyStartup() {

	}

}
