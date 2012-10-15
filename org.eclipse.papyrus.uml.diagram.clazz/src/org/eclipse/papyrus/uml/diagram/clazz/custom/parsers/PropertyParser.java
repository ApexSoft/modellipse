/*****************************************************************************
 * Copyright (c) 2008, 2009 CEA LIST.
 *
 *    
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Patrick Tessier (CEA LIST) Patrick.tessier@cea.fr - Initial API and implementation
 *  Yann TANGUY (CEA LIST) yann.tanguy@cea.fr
 *
 *****************************************************************************/
package org.eclipse.papyrus.uml.diagram.clazz.custom.parsers;

import java.util.List;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.common.ui.services.parser.IParser;
import org.eclipse.gmf.runtime.common.ui.services.parser.IParserEditStatus;
import org.eclipse.gmf.runtime.common.ui.services.parser.ParserEditStatus;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;
import org.eclipse.gmf.runtime.emf.core.util.EObjectAdapter;
import org.eclipse.jface.text.contentassist.IContentAssistProcessor;
import org.eclipse.jface.util.SafeRunnable;
import org.eclipse.papyrus.infra.core.utils.EditorUtils;
import org.eclipse.papyrus.uml.diagram.clazz.part.UMLDiagramEditorPlugin;
import org.eclipse.papyrus.uml.tools.utils.ICustomAppearence;
import org.eclipse.papyrus.uml.tools.utils.PropertyUtil;
import org.eclipse.uml2.uml.Property;

/**
 * This the parser in charge of editing and displaying properties in Papyrus. For the edition of
 * properties, a dialog box is opened
 * 
 * @author Patrick Tessier
 */
public class PropertyParser implements IParser {

	/**
	 * 
	 * {@inheritDoc}
	 */
	public IContentAssistProcessor getCompletionProcessor(IAdaptable element) {
		return null;
	}

	/**
	 * 
	 * {@inheritDoc}
	 */
	public String getEditString(final IAdaptable element, int flags) {
		if(element instanceof EObjectAdapter) {
			final Property property = ((Property)((EObjectAdapter)element).getRealObject());
			return PropertyUtil.getCustomLabel(property, ICustomAppearence.DISP_NAME);
		}
		return "";
	}

	/**
	 * 
	 * {@inheritDoc}
	 */
	public ICommand getParseCommand(IAdaptable element, String newString, int flags) {
		final Property property = ((Property)((EObjectAdapter)element).getRealObject());
		final String result = newString;

		AbstractTransactionalCommand tc = new AbstractTransactionalCommand(EditorUtils.getTransactionalEditingDomain(), "Edit Property", (List)null) {

			@Override
			protected CommandResult doExecuteWithResult(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
				SafeRunnable.run(new SafeRunnable() {

					public void run() {
						RecordingCommand rc = new RecordingCommand(EditorUtils.getTransactionalEditingDomain()) {

							protected void doExecute() {
								property.setName(result);
							}
						};
						EditorUtils.getTransactionalEditingDomain().getCommandStack().execute(rc);
					}
				});
				return CommandResult.newOKCommandResult();

			};
		};
		return tc;
	}

	/**
	 * 
	 * {@inheritDoc}
	 */
	public String getPrintString(IAdaptable element, int flags) {
		return "<default>";
	}

	/**
	 * 
	 * {@inheritDoc}
	 */
	public boolean isAffectingEvent(Object event, int flags) {
		return false;
	}

	/**
	 * 
	 * {@inheritDoc}
	 */
	public IParserEditStatus isValidEditString(IAdaptable element, String editString) {
		return new ParserEditStatus(UMLDiagramEditorPlugin.ID, IParserEditStatus.OK, "");
	}
}
