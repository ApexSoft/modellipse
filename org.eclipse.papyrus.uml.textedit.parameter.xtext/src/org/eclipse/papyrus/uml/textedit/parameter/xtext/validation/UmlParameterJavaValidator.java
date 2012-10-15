/*****************************************************************************
 * Copyright (c) 2010 CEA LIST.
 *
 *    
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Vincent Lorenzo (CEA LIST) vincent.lorenzo@cea.fr - Initial API and implementation
 *
 *****************************************************************************/
package org.eclipse.papyrus.uml.textedit.parameter.xtext.validation;

import org.eclipse.papyrus.uml.textedit.common.xtext.validation.UmlCommonJavaValidator;

/**
 * 
 * The Validator for The Parameter
 * 
 * 
 */
public class UmlParameterJavaValidator extends UmlCommonJavaValidator {

	/**
	 * 
	 * Constructor.
	 * 
	 */
	public UmlParameterJavaValidator() {
		super();
	}

	/**
	 * 
	 * @see org.eclipse.papyrus.uml.textedit.common.xtext.validation.UmlCommonJavaValidator#validate()
	 * 
	 * @return
	 */
	@Override
	public boolean validate() {
		return valid_MultiplicityRule;
	}
}
