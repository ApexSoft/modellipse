/*****************************************************************************
 * Copyright (c) 2009 CEA LIST.
 *
 *    
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Patrick Tessier (CEA LIST) Patrick.Tessier@cea.fr - Initial API and implementation
 *  Yann Tanguy (CEA LIST) yann.tanguy@cea.fr - Adaptation for Composite Diagram

 *  
 *****************************************************************************/
package org.eclipse.papyrus.uml.diagram.component.custom.edit.policies;

import org.eclipse.gmf.runtime.notation.BasicCompartment;
import org.eclipse.gmf.runtime.notation.DecorationNode;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.papyrus.uml.diagram.common.editpolicies.OrphanViewPolicy;

// TODO: Auto-generated Javadoc
/**
 * <pre>
 * This policy is used to suppress orphan node view in GMF view.
 * The policy to remove orphan connection is more complex. 
 * 
 * See RemoveOrphanViewPolicy in Class Diagram
 * </pre>
 */
public class RemoveOrphanViewPolicy extends OrphanViewPolicy {

	/**
	 * Checks if is orphaned.
	 *
	 * @param view the view
	 * @return true is the view is Orphaned
	 * @see org.eclipse.papyrus.uml.diagram.common.editpolicies.OrphanViewPolicy#isOrphaned(org.eclipse.gmf.runtime.notation.View)
	 */
	protected boolean isOrphaned(View view) {
		// Always treat Compartment and Decoration as not orphaned nodes 
		if((view instanceof BasicCompartment) || (view instanceof DecorationNode)) {
			return false;
		}
		return !view.isSetElement();
	}
}
