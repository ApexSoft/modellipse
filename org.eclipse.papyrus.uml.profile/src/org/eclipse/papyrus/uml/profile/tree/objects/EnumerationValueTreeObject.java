/*****************************************************************************
 * Copyright (c) 2008 CEA LIST.
 *
 *    
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Chokri Mraidha (CEA LIST) Chokri.Mraidha@cea.fr - Initial API and implementation
 *  Patrick Tessier (CEA LIST) Patrick.Tessier@cea.fr - modification
 *
 *****************************************************************************/
package org.eclipse.papyrus.uml.profile.tree.objects;

import org.eclipse.papyrus.uml.profile.ui.dialogs.InputDialogEnumeration;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.uml2.uml.Enumeration;
import org.eclipse.uml2.uml.EnumerationLiteral;
import org.eclipse.uml2.uml.Property;

// TODO: Auto-generated Javadoc
/**
 * The Class EnumerationValueTreeObject.
 */
public class EnumerationValueTreeObject extends ValueTreeObject {

	/**
	 * The Constructor.
	 * 
	 * @param value
	 *        the value
	 * @param parent
	 *        the parent
	 */
	public EnumerationValueTreeObject(AppliedStereotypePropertyTreeObject parent, Object value) {
		super(parent, value);
		this.value = value;
	}

	/**
	 * Edits the me.
	 */
	@Override
	public void editMe() {
		AppliedStereotypePropertyTreeObject pTO = (AppliedStereotypePropertyTreeObject)getParent();
		Property property = pTO.getProperty();
		Enumeration enumeration = (Enumeration)property.getType();

		// Creates a dialog box to enter value
		InputDialogEnumeration valueDialog = new InputDialogEnumeration(new Shell(), property, getValue());
		int val = valueDialog.open();

		int literalIdx = -1;
		if((val == InputDialogEnumeration.OK) && (valueDialog.getSelectionIndex() != -1)) {
			literalIdx = valueDialog.getSelectionIndex();
			// Treat dialogValue
			EnumerationLiteral selectedLiteral = enumeration.getOwnedLiterals().get(literalIdx);
			pTO.updateValue(pTO.appendMV(selectedLiteral));
		}

		// Close dialog box and refresh table
		valueDialog.close();
	}
}
