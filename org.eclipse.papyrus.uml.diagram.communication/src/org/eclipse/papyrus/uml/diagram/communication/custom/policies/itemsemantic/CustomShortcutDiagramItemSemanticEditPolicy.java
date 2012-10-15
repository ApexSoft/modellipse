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
 *  Saadia DHOUIB (CEA LIST) saadia.dhouib@cea.fr - adapted from class diagram
 *
 *****************************************************************************/
package org.eclipse.papyrus.uml.diagram.communication.custom.policies.itemsemantic;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest;
import org.eclipse.papyrus.uml.diagram.communication.edit.policies.ShortCutDiagramItemSemanticEditPolicy;

/**
 * this is a specific item edit policy to disable the action remove from model
 * 
 */
public class CustomShortcutDiagramItemSemanticEditPolicy extends ShortCutDiagramItemSemanticEditPolicy {

	protected Command getDestroyElementCommand(DestroyElementRequest req) {
		return UnexecutableCommand.INSTANCE;
	}
}
