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
package org.eclipse.papyrus.uml.alf.syntax.units;

import org.eclipse.papyrus.uml.alf.syntax.common.ElementReference;
import org.eclipse.papyrus.uml.alf.syntax.expressions.QualifiedName;

public class TypedElementDefinition extends Member {

	// Synthesized Properties
	public boolean isNonunique = false ;
	public boolean isOrdered = false ;
	public String lowerBound ;
	public QualifiedName typeName ;
	public String upperBound = "1" ;
	
	// Derived Properties
	public int lower ;
	public ElementReference type ;
	public int upper ;
	
	// Constraints
	
	/*
	 * If the lower bound string image of a typed element definition is not empty, then the integer lower bound
	 * 	is the integer value of the lower bound string. Otherwise the lower bound is equal to the upper bound,
	 * 	unless the upper bound is unbounded, in which case the lower bound is 0.
	 */
	public void checkTypedElementDefinitionLowerDerivation() {
		
	}
	
	/*
	 * The type of a typed element definition is the single classifier referent of the type name.
	 */
	public void checkTypedElementDefinitionTypeDerivation() {
		
	}
	
	/*
	 * The type name of a typed element definition must have a single classifier referent. This referent may not
	 * 	be a template.
	 */
	public void checkTypedElementDefinitionTypeName() {
		
	}
	
	/*
	 * The unlimited natural upper bound value is the unlimited natural value of the uper bound string (with
	 * 	"*" representing the unbounded value).
	 */
	public void checkTypedElementDefinitionUpperDerivation() {
		
	}
	
}
