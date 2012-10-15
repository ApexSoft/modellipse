/*****************************************************************************
 * Copyright (c) 2008 CEA LIST.
 *
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Chokri Mraidha (CEA LIST) Chokri.Mraidha@cea.fr - Initial API and implementation
 *  Patrick Tessier (CEA LIST) Patrick.Tessier@cea.fr - modification
 *
 *****************************************************************************/
package org.eclipse.papyrus.uml.profile;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.papyrus.infra.core.log.LogHelper;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle.
 */
public class Activator extends AbstractUIPlugin {

	// The plug-in ID
	/**
	 * 
	 */
	public static final String PLUGIN_ID = "org.eclipse.papyrus.uml.profile";

	public static LogHelper log;

	// The shared instance
	/**
	 * 
	 */
	private static Activator plugin;

	/**
	 * The constructor.
	 */
	public Activator() {
		plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	/**
	 * 
	 * 
	 * @param context
	 * 
	 * @throws Exception
	 */
	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
		log = new LogHelper(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	/**
	 * 
	 * 
	 * @param context
	 * 
	 * @throws Exception
	 */
	@Override
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance.
	 * 
	 * @return the shared instance
	 */
	public static Activator getDefault() {
		return plugin;
	}

	/**
	 * Logs an error into the plugin's log
	 * 
	 * @param string
	 *        the message of the error
	 */
	public static void logWarning(String string) {
		getDefault().getLog().log(new Status(IStatus.WARNING, PLUGIN_ID, string));
	}

	/**
	 * Logs an error into the plugin's log
	 * 
	 * @param string
	 *        the message of the error
	 */
	public static void logError(String string) {
		getDefault().getLog().log(new Status(IStatus.ERROR, PLUGIN_ID, string));
	}

	/**
	 * Logs an exception into the plugin's log
	 * 
	 * @param exception
	 *        the exception to log
	 */
	public static void logException(Exception exception) {
		getDefault().getLog().log(new Status(IStatus.ERROR, PLUGIN_ID, exception.getLocalizedMessage(), exception));
	}

	/**
	 * Logs an exception into the plugin's log
	 * 
	 * @param exception
	 *        the exception to log
	 * @param message
	 *        the message for the error
	 */
	public static void logException(Exception exception, String message) {
		getDefault().getLog().log(new Status(IStatus.ERROR, PLUGIN_ID, message, exception));
	}

}
