/*****************************************************************************
 * Copyright (c) 2012 CEA LIST.
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Camille Letavernier (CEA LIST) camille.letavernier@cea.fr - Initial API and implementation
 *****************************************************************************/
package org.eclipse.papyrus.infra.gmfdiag.properties.databinding.custom;

import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.View;

public class CustomStringStyleObservableList extends CustomStyleObservableList {

	public CustomStringStyleObservableList(View view, EditingDomain domain, String styleName) {
		super(view, styleName, domain, NotationPackage.eINSTANCE.getStringListValueStyle(), NotationPackage.eINSTANCE.getStringListValueStyle_StringListValue());
	}

}
