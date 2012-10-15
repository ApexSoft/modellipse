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

public class ArithmeticExpression extends BinaryExpression {
	
	// derived properties
	protected boolean isConcatenation ;

	// constraints
	
	/*
	 * An arithmetic expression is a string concatenation expression if its type is String. 
	 */
	protected void checkArithmeticExpressionIsConcatenationDerivation () {
		
	}
	
	/*
	 *  An arithmetic expression has a multiplicity lower bound of 0 if the lower bound if either operand
	 *  expression is 0 and 1 otherwise.
	 */
	protected void checkArithmeticExpressionLowerDerivation () {
		
	}
	
	/* 
	 * The operands of an arithmetic expression must both have type Integer, unless the operator is +, in which
	 * case they may also both have type String.
	 */
	protected void checkArithmeticExpressionOperandTypes () {
	
	}
	
	/*
	 * The type of an arithmetic expression is the same as the type of its operands.
	 */
	protected void checkArithmeticExpressionTypeDerivation () {
	
	}
	
	/*
	 * An arithmetic expression has a multiplicity upper bound of 1.
	 */
	protected void checkArithmeticExpressionUpperDerivation () {
	
	}
	
}
