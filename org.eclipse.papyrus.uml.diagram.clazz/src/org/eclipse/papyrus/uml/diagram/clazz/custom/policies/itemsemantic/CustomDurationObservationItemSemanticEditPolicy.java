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
 *  Patrick Tessier (CEA LIST) patrick.tessier@cea.fr - Initial API and implementation
 *
 *****************************************************************************/
package org.eclipse.papyrus.uml.diagram.clazz.custom.policies.itemsemantic;

import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.emf.type.core.requests.ReorientRelationshipRequest;
import org.eclipse.papyrus.uml.diagram.clazz.custom.command.BranchDependenctReorientCommand;
import org.eclipse.papyrus.uml.diagram.clazz.edit.parts.DependencyBranchEditPart;
import org.eclipse.papyrus.uml.diagram.clazz.edit.policies.DurationObservationItemSemanticEditPolicy;


/**
 * this class has been specialized in order to manage reconnection of multidependency
 * 
 */
public class CustomDurationObservationItemSemanticEditPolicy extends DurationObservationItemSemanticEditPolicy {

	protected Command getReorientRelationshipCommand(ReorientRelationshipRequest req) {
		switch(getVisualID(req)) {
		case DependencyBranchEditPart.VISUAL_ID:
			return getGEFWrapper(new BranchDependenctReorientCommand(req));
		}
		return super.getReorientRelationshipCommand(req);
	}
}
