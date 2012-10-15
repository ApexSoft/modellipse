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
package org.eclipse.papyrus.infra.table.modelexplorer.handlers;

import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.command.UnexecutableCommand;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest;
import org.eclipse.papyrus.commands.DestroyElementPapyrusCommand;
import org.eclipse.papyrus.commands.wrappers.GMFtoEMFCommandWrapper;
import org.eclipse.papyrus.infra.core.sasheditor.contentprovider.IPageMngr;
import org.eclipse.papyrus.infra.table.instance.papyrustableinstance.PapyrusTableInstance;

/**
 * Handler for the delete Table action
 * 
 * 
 * 
 */
public class DeleteTableHandler extends AbstractTableModelExplorerHandler {


	/**
	 * 
	 * @see org.eclipse.papyrus.views.modelexplorer.handler.AbstractCommandHandler#getCommand()
	 * 
	 * @return
	 */
	@Override
	protected Command getCommand() {
		TransactionalEditingDomain editingDomain = getEditingDomain();
		final IPageMngr pageMngr = getPageManager();

		List<PapyrusTableInstance> tables = getSelectedTables();

		if(editingDomain != null && pageMngr != null && !tables.isEmpty()) {
			CompoundCommand command = new CompoundCommand();

			for(final PapyrusTableInstance table : tables) {
				Command sashRemoveComd = new RecordingCommand(editingDomain) {

					@Override
					protected void doExecute() {
						if(pageMngr.isOpen(table)) {
							pageMngr.closePage(table);
						}
					}
				};
				// the destroy element command is a good way to destroy the cross reference
				command.append(sashRemoveComd);
				command.append(new GMFtoEMFCommandWrapper(new DestroyElementPapyrusCommand(new DestroyElementRequest(table, false))));
			}
			return command.isEmpty() ? UnexecutableCommand.INSTANCE : command;
		}
		return UnexecutableCommand.INSTANCE;
	}
}
