/*****************************************************************************
 * Copyright (c) 2012 CEA LIST.
 *
 *    
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Patrick Tessier (CEA LIST) Patrick.tessier@cea.fr - Initial API and implementation
 *
 *****************************************************************************/
package org.eclipse.papyrus.uml.diagram.clazz.custom.policies;

import java.util.Collection;
import java.util.Map;

import org.eclipse.gef.editpolicies.AbstractEditPolicy;
import org.eclipse.papyrus.infra.gmfdiag.common.editpolicies.IMaskManagedLabelEditPolicy;


public class NoSemanticParserEditPolicy extends AbstractEditPolicy implements IMaskManagedLabelEditPolicy {

	public String getMaskLabel(int value) {
		// TODO Auto-generated method stub
		return null;
	}

	public Collection<String> getMaskLabels() {
		// TODO Auto-generated method stub
		return null;
	}

	public Collection<Integer> getMaskValues() {
		// TODO Auto-generated method stub
		return null;
	}

	public Map<Integer, String> getMasks() {
		// TODO Auto-generated method stub
		return null;
	}

	public int getCurrentDisplayValue() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getDefaultDisplayValue() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void refreshDisplay() {
		// TODO Auto-generated method stub
	}

	public void updateDisplayValue(int newValue) {
		// TODO Auto-generated method stub
	}

	public void setDefaultDisplayValue() {
		// TODO Auto-generated method stub
	}

	public String getPreferencePageID() {
		// TODO Auto-generated method stub
		return null;
	}
}
