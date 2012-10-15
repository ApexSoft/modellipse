/*****************************************************************************
 * Copyright (c) 2010 CEA
 *
 *    
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Atos Origin - Initial API and implementation
 *
 *****************************************************************************/
package org.eclipse.papyrus.uml.diagram.sequence.edit.policies;

import org.eclipse.papyrus.uml.diagram.common.editpolicies.OrphanViewPolicy;
import org.eclipse.papyrus.uml.diagram.sequence.edit.parts.LifelineNameEditPart;

/**
 * this policy is used to suppress orphan node view in GMF view the policy to remove orphan
 * connection is more complex. It is dependent of the diagram. see remove OrphanConnectionView
 * policy
 * 
 */
public class RemoveOrphanViewPolicy extends OrphanViewPolicy {

	public int[] notOrphanNode = { LifelineNameEditPart.VISUAL_ID };

	public RemoveOrphanViewPolicy() {
		super();
		init(notOrphanNode);
	}

}
