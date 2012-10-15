/*****************************************************************************
 * Copyright (c) 2008, 2009 CEA LIST.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Remi Schnekenburger (CEA LIST) - Initial API and implementation
 *
 *****************************************************************************/
package org.eclipse.papyrus.infra.gmfdiag.common.editpolicies;

import java.util.Collection;
import java.util.Map;

/**
 * Interface for all edit policies that manage the label of a {@link GraphicalEditPart}. The label must be controlled by a system of mask,
 * i.e. the label shows or not some part of the string.
 */
public interface IMaskManagedLabelEditPolicy {

	/** key for this edit policy */
	public String MASK_MANAGED_LABEL_EDIT_POLICY = "MaskManagedLabelPolicy";

	/**
	 * Retrieve the mask name for the given mask value.
	 * 
	 * @param value
	 *        the value of the given mask
	 * @return the mask name
	 */
	// @unused
	public String getMaskLabel(int value);

	/**
	 * Returns the {@link Collection} of mask names
	 * 
	 * @return the {@link Collection} of mask names
	 */
	// @unused
	public Collection<String> getMaskLabels();

	/**
	 * Returns the {@link Collection} of mask values
	 * 
	 * @return the {@link Collection} of mask values
	 */
	// @unused
	public Collection<Integer> getMaskValues();

	/**
	 * Returns the {@link Map} of masks
	 * 
	 * @return the {@link Map} of masks
	 */
	public Map<Integer, String> getMasks();

	/**
	 * Retrieve the current display Value for the current host edit part.
	 * 
	 * @return the current display Value for the current host edit part.
	 */
	public int getCurrentDisplayValue();

	/**
	 * Retrieve the default display Value for the current host edit part.
	 * 
	 * @return the default display Value for the current host edit part.
	 */
	public int getDefaultDisplayValue();

	/**
	 * Refreshes the display of the edit part
	 */
	public void refreshDisplay();

	/**
	 * Sets the new Value for the element that manages the mask value
	 */
	public void updateDisplayValue(int newValue);

	/**
	 * Sets the default display value for the edit part
	 */
	public void setDefaultDisplayValue();

	/**
	 * returns the preference page ID
	 * 
	 * @return
	 */
	public String getPreferencePageID();
}
