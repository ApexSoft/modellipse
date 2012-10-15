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

import org.eclipse.papyrus.uml.alf.syntax.common.ElementReference;

public class ClassificationExpression extends UnaryExpression {

	// Synthesized Properties
	public QualifiedName typeName ; 
	
	// Derived Properties
	public boolean isDirect ;
	public ElementReference referent ;

	//Constraints
	/*
	 * A classification expression is direct if its operator is "hastype".
	 */
	public void checkClassificationExpressionIsDirectDerivation() {
		
	}
	
	/*
	 * A classification expression has a multiplicity lower bound that is the same as the lower bound of its
	 * 	operand expression.
	 */
	public void checkClassificationExpressionLowerDerivation() {
		
	}
	
	/*
	 * The operand expression of a classification expression must have a multiplicity upper bound of 1.
	 */
	public void checkClassificationExpressionOperand() {
		
	}
	
	/*
	 * The referent of a classification expression is the classifier to which the type name resolves.
	 */
	public void checkClassificationExpressionReferentDerivation() {
		
	}
	
	/*
	 * A classification expression has type Boolean.
	 */
	public void checkClassificationExpressionTypeDerivation() {
		
	}
	
	/*
	 * The type name in a classification expression must resolve to a classifier.
	 */
	public void checkClassificationExpressionTypeName() {
		
	}
	
	/*
	 * A classification expression has a multiplicity upper bound of 1.
	 */
	public void checkClassificationExpressionUpperDerivation() {
		
	}
	
}
