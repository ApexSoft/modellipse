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
 *  Tatiana Fesenko (CEA LIST) - Initial API and implementation
 *
 *****************************************************************************/package org.eclipse.papyrus.uml.diagram.wizards;

import java.util.Properties;

import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.internal.intro.impl.IntroPlugin;
import org.eclipse.ui.intro.IIntroSite;
import org.eclipse.ui.intro.config.IIntroAction;

/**
 * The Class NewProjectAction. It is used on the Welcome Page.
 */
public class NewProjectAction implements IIntroAction {

	/**
	 * Run.
	 *
	 * @param site the site
	 * @param params the params
	 * @see org.eclipse.ui.intro.config.IIntroAction#run(org.eclipse.ui.intro.IIntroSite, java.util.Properties)
	 */
	public void run(IIntroSite site, Properties params) {
		IntroPlugin.getDefault().closeIntro();
		
		NewPapyrusProjectWizard wizard = new NewPapyrusProjectWizard();
		wizard.init(site.getWorkbenchWindow().getWorkbench(), new StructuredSelection());
		WizardDialog dialog = new WizardDialog(site.getShell(), wizard);
		dialog.create();
		dialog.getShell().setSize(Math.max(500, dialog.getShell().getSize().x), 500);
		dialog.open();
	}

}
