/*****************************************************************************
 * Copyright (c) 2011 CEA LIST.
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Camille Letavernier (CEA LIST) camille.letavernier@cea.fr - Initial API and implementation
 *****************************************************************************/
package org.eclipse.papyrus.uml.properties.creation;

import java.util.List;

import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.papyrus.infra.widgets.creation.StringEditionFactory;
import org.eclipse.papyrus.uml.properties.expression.ExpressionList.Expression;
import org.eclipse.papyrus.uml.properties.messages.Messages;
import org.eclipse.swt.widgets.Control;

/**
 * A Factory for setting languages in a UML Expression editor.
 * The value is the name of the language
 * 
 * @author Camille Letavernier
 */
public class ExpressionLanguageFactory extends StringEditionFactory {

	private UniqueElementValidator validator;

	private List<?> expressionList;

	/**
	 * 
	 * Constructor.
	 * 
	 * @param currentExpressionList
	 */
	public ExpressionLanguageFactory(List<?> currentExpressionList) {
		super(Messages.ExpressionLanguageFactory_EditLanguage, Messages.ExpressionLanguageFactory_SetNewLanguage);

		validator = new UniqueElementValidator();
		this.expressionList = currentExpressionList;

		setValidator(validator);
	}

	private class UniqueElementValidator implements IInputValidator {

		private String currentValue;

		public void setCurrentValue(String currentValue) {
			this.currentValue = currentValue;
		}

		public String isValid(String newText) {
			if(newText.equals(currentValue)) {
				return null;
			}

			for(Object object : expressionList) {
				if(object instanceof Expression) {
					Expression expression = (Expression)object;
					if(newText.equals(expression.getLanguage())) {
						return Messages.ExpressionLanguageFactory_LanguageDuplicateError;
					}
				}
			}

			return null;
		}
	}

	@Override
	public Object edit(Control widget, Object currentValue) {
		if(currentValue instanceof Expression) {
			String valueToEdit = ((Expression)currentValue).getLanguage();
			validator.setCurrentValue(valueToEdit);
			String newValue = (String)super.edit(widget, valueToEdit);
			((Expression)currentValue).setLanguage(newValue);
		}

		return currentValue;
	}

	@Override
	public Object createObject(Control widget) {
		String languageName = (String)super.createObject(widget);
		if(languageName == null) {
			return null;
		}
		Expression expression = new Expression();
		expression.setLanguage(languageName);
		return expression;
	}
}
