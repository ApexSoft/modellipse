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
 *   Arthur Daussy (Atos) - Initial API and implementation
 *   Arthur Daussy - 371712 : 372745: [ActivityDiagram] Major refactoring group framework
 *
 *****************************************************************************/
package org.eclipse.papyrus.uml.diagram.activity.activitygroup.editpolicy;

import org.eclipse.papyrus.uml.diagram.activity.activitygroup.IContainerNodeDescriptor;

/**
 * Creation edit policy for Activiyt partition
 * 
 * @author adaussy
 * 
 */
public class ActivityPartitionActivityPartitionContentCompartmentCreationEditPolicy extends GroupCreationEditPolicy {

	public ActivityPartitionActivityPartitionContentCompartmentCreationEditPolicy(IContainerNodeDescriptor groupDescriptor) {
		super(groupDescriptor);
	}
}
