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

import java.util.List;

public class SequenceExpressionList extends SequenceElements {

	// Synthesized Properties
	public List<Expression> element ;
	
	// Constraints
	/*
	 * The multiplicity lower bound of the elements of a sequence expression list is given by the sum of the
	 * 	lower bounds of each of the expressions in the list.
	 */
	public void checkSequenceExpressionListLowerDerivation() {
		
	}
	
	/*
	 * The multiplicity lower bound of the elements of a sequence expression list is given by the sum of the
	 * 	lower bounds of each of the expressions in the list. If any of the expressions in the list have an
	 * 	unbounded upper bound, then the sequence expression list also has an unbounded upper bound.
	 */
	public void checkSequenceExpressionListUpperDerivation() {
		
	}
	
}
