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

public class FeatureInvocationExpression extends InvocationExpression {

	// Synthesized Properties
	public FeatureReference target ;

	// Constraints
	
	/*
	 * An alternative constructor invocation may only occur in an expression statement as the first statement in
	 * 	the definition for the method of a constructor operation.
	 */
	public void checkFeatureInvocationExpressionAlternativeConstructor() {
		
	}
	
	/*
	 * If a feature invocation expression has an explicit target, then that is its feature. Otherwise, it is an
	 * 	alternative constructor call with its feature determined implicitly.
	 */
	public void checkFeatureInvocationExpressionFeatureDerivation() {
		
	}
	
	/*
	 * If there is no target feature expression, then the implicit feature with the same name as the target type
	 * 	must be a constructor.
	 */
	public void checkFeatureInvocationExpressionImplicitAlternativeConstructor() {
		
	}
	
	/*
	 * If a feature invocation expression is an implicit object destruction, it has no referent. Otherwise, its
	 * 	referent is the referent of its feature.
	 */
	public void checkFeatureInvocationExpressionReferentDerivation() {
		
	}
	
	/*
	 * If a feature invocation expression is not an implicit destructor call, then it must be possible to determine
	 * 	a single valid referent for it according to the overloading resolution rules.
	 */
	public void checkFeatureInvocationExpressionReferentExists() {
		
	}
	
}
