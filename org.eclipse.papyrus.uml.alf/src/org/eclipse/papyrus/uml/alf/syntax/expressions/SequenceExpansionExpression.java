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

import org.eclipse.papyrus.uml.alf.syntax.common.AssignedSource;

public abstract class SequenceExpansionExpression extends Expression {
	
	// Synthesized Properties
	public Expression argument ;
	public String operation ;
	public ExtentOrExpression primary ; 
	public String variable ;
	
	// Derived Properties
	public AssignedSource variableSource ;
	
	// Constraints
	
	/*
	 * The assignments before the argument expression of a sequence expansion expression include those after
	 * 	the primary expression plus one for the expansion variable.
	 */
	public void checkSequenceExpansionExpressionAssignmentsBeforeArgument() {
		
	}
	
	/*
	 * The assignments before the primary expression of a sequence expansion expression are the same as the
	 * 	assignments before the sequence expansion expression.
	 */
	public void checkSequenceExpansionExpressionAssignmentsBeforePrimary() {
		
	}
	
	/*
	 * The expansion variable may not be assigned within the argument expression.
	 */
	public void checkSequenceExpansionExpressionVariableAssignment() {
		
	}
	
	/*
	 * The expansion variable name may not conflict with any name already assigned after the primary
	 * 	expression.
	 */
	public void checkSequenceExpansionExpressionVariableName() {
		
	}
	
	/*
	 * The assigned source for the expansion variable of a sequence expansion expression is the expression
	 * 	itself.
	 */
	public void checkSequenceExpansionExpressionVariableSourceDerivation() {
		
	}
	
	
	//Helper Operations
	/*
	 * The assignments after a sequence expansion expression are the same as after its primary expression.
	 */
	public List<AssignedSource> updateAssignments() {
		return new ArrayList<AssignedSource>() ;
	}

}
