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
 *  CEA LIST - Initial API and implementation
 *
 *****************************************************************************/
package org.eclipse.papyrus.uml.alf.syntax.expressions;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.papyrus.uml.alf.syntax.common.ElementReference;

public class LinkOperationExpression extends InvocationExpression {
	
	// Synthesized Properties
	public QualifiedName associationName ;
	public String operation ;
	
	// Derived Properties
	public boolean isClear ;
	public boolean isCreation ;
	
	// Constraints
	
	/*
	 * Each argument expression must be assignable to its corresponding expression.
	 */
	public void checkLinkOperationExpressionArgumentCompatibility() {
		
	}
	
	/*
	 * The qualified name of a link operation expression must resolve to a single association.
	 */
	public void checkLinkOperationExpressionAssociationReference() {
		
	}
	
	/*
	 * There is no feature for a link operation expression.
	 */
	public void checkLinkOperationExpressionFeatureDerivation() {
		
	}
	
	/*
	 * A link operation expression is for clearing an association if the operation is "clearAssoc".
	 */
	public void checkLinkOperationExpressionIsClearDerivation() {
		
	}
	
	/*
	 * A link operation expression is for link creation if its operation is "createLink".
	 */
	public void checkLinkOperationExpressionIsCreationDerivation() {
		
	}
	
	/*
	 * The referent for a link operation expression is the named association.
	 */
	public void checkLinkOperationExpressionReferentDerivation() {
		
	}
	
	// Helper Operations

	/*
	 * For a clear association operation, returns a single, typeless parameter. Otherwise, returns the ends of the
	 * 	named association.
	 */
	public List<ElementReference> parameterElements() {
		return new ArrayList<ElementReference>() ;
	}

}
