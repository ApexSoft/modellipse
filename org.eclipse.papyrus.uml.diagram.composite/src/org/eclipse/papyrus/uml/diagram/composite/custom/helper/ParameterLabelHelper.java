/*****************************************************************************
 * Copyright (c) 2009 - 2010 CEA LIST.
 *    
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Remi Schnekenburger (CEA LIST) remi.schnekenburger@cea.fr - Initial API and implementation
 *  Yann Tanguy (CEA LIST) yann.tanguy@cea.fr - Customization for Parameter
 *
 *****************************************************************************/

package org.eclipse.papyrus.uml.diagram.composite.custom.helper;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.papyrus.infra.gmfdiag.common.editpolicies.IMaskManagedLabelEditPolicy;
import org.eclipse.papyrus.uml.diagram.common.helper.StereotypedElementLabelHelper;
import org.eclipse.papyrus.uml.tools.utils.ICustomAppearence;
import org.eclipse.papyrus.uml.tools.utils.ParameterUtil;
import org.eclipse.uml2.uml.Parameter;

/**
 * Helper for labels displaying {@link Parameter} in Composite Diagram
 */
public class ParameterLabelHelper extends StereotypedElementLabelHelper {

	/** Single instance */
	private static ParameterLabelHelper labelHelper;

	/** Single instance getter */
	public static ParameterLabelHelper getInstance() {
		if(labelHelper == null) {
			labelHelper = new ParameterLabelHelper();
		}
		return labelHelper;
	}

	/** Map for masks */
	protected final Map<Integer, String> masks = new HashMap<Integer, String>(7);

	/**
	 * Returns the mask name given the value of the mask
	 * 
	 * @return the mask name or <code>null</code> if no masks has been found
	 */
	public String getMaskLabel(int value) {
		return masks.get(value);
	}

	/**
	 * Returns the collection of mask names
	 * 
	 * @return the collection of mask names
	 */
	public Collection<String> getMaskLabels() {
		return masks.values();
	}

	/**
	 * Returns the map of masks used to display a {@link Parameter}
	 * 
	 * @return the {@link Map} of masks used to display a {@link Parameter}
	 */
	public Map<Integer, String> getMasks() {
		return masks;
	}

	/**
	 * Returns the collection of mask values
	 * 
	 * @return the collection of mask values
	 */
	public Set<Integer> getMaskValues() {
		return masks.keySet();
	}

	/** Disable constructor (private) */
	protected ParameterLabelHelper() {
		super();
		// initialize the map
		masks.clear();
		masks.put(ICustomAppearence.DISP_PARAMETER_DIRECTION, "Direction");
		masks.put(ICustomAppearence.DISP_PARAMETER_NAME, "Name");
		masks.put(ICustomAppearence.DISP_PARAMETER_TYPE, "Type");
	}

	/**
	 * {@inheritDoc}
	 */
	protected String elementLabel(GraphicalEditPart editPart) {
		int displayValue = ICustomAppearence.DEFAULT_UML_PARAMETER;

		IMaskManagedLabelEditPolicy policy = (IMaskManagedLabelEditPolicy)editPart.getEditPolicy(IMaskManagedLabelEditPolicy.MASK_MANAGED_LABEL_EDIT_POLICY);
		if(policy != null) {
			displayValue = policy.getCurrentDisplayValue();
		}
		Parameter elem = getUMLElement(editPart);
		if(elem != null) {
			return ParameterUtil.getCustomLabel(elem, displayValue);
		}
		return "";
	}

	/**
	 * {@inheritDoc}
	 */
	public Parameter getUMLElement(GraphicalEditPart editPart) {
		return (Parameter)((View)editPart.getModel()).getElement();
	}

}
