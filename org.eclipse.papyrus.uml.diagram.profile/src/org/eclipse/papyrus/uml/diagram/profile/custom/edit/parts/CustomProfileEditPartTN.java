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
 *  Vincent Lorenzo (CEA LIST) vincent.lorenzo@cea.fr 
 *****************************************************************************/
package org.eclipse.papyrus.uml.diagram.profile.custom.edit.parts;

import org.eclipse.gmf.runtime.gef.ui.figures.DefaultSizeNodeFigure;
import org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.papyrus.uml.diagram.common.figure.node.PackageNodePlateFigure;
import org.eclipse.papyrus.uml.diagram.profile.edit.parts.ProfileEditPartTN;

/**
 * this a specific editpart used to overload the method createNodePlate
 */
public class CustomProfileEditPartTN extends ProfileEditPartTN {

	public CustomProfileEditPartTN(View view) {
		super(view);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected NodeFigure createNodePlate() {

		DefaultSizeNodeFigure result = new PackageNodePlateFigure(200, 100);
		return result;
	}
}
