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
 *  Ansgar Radermacher (CEA LIST) ansgar.radermacher@cea.fr - Initial API and implementation
 *  
 * History:
 *  Renamed from MoDiscoLabelProviderWTooltips - fix for bug 371905
 *
 *****************************************************************************/
package org.eclipse.papyrus.views.modelexplorer;

import java.util.Arrays;

import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.graphics.Point;
import org.eclipse.ui.internal.navigator.NavigatorDecoratingLabelProvider;

/**
 * the label provider that inherits of modisco label provider
 * 
 */
public class DecoratingLabelProviderWTooltips extends NavigatorDecoratingLabelProvider {

	private MoDiscoLabelProvider moDiscoLP;

	public DecoratingLabelProviderWTooltips(ILabelProvider labelProvider) {
		super(labelProvider);
		if(labelProvider instanceof MoDiscoLabelProvider) {
			moDiscoLP = (MoDiscoLabelProvider)labelProvider;
		}
	}

	@Override
	public String getToolTipText(Object element) {
		return moDiscoLP.getMarkerMessage(element);
	}

	@Override
	public Point getToolTipShift(Object object) {
		return new Point(5, 5);
	}

	@Override
	public int getToolTipDisplayDelayTime(Object object) {
		return 1000;
	}

	@Override
	public int getToolTipTimeDisplayed(Object object) {
		return 10000;
	}
	
	/**
	 * apex updated
	 */
	@Override
	public void update(ViewerCell cell) {
		/* apex improved start */
		Object element = cell.getElement();

		StyledString styledString = getStyledText(element);
		String newText= styledString.toString();
		
		StyleRange[] oldStyleRanges= cell.getStyleRanges();
		StyleRange[] newStyleRanges= isOwnerDrawEnabled() ? styledString.getStyleRanges() : null;
		
		if (!Arrays.equals(oldStyleRanges, newStyleRanges)) {
			cell.setStyleRanges(newStyleRanges);
			if (cell.getText().equals(newText)) {
				// make sure there will be a refresh from a change
				cell.setText(""); //$NON-NLS-1$
			}
		}
		
		cell.setText(newText);
		cell.setImage(getImage(element));
		cell.setFont(getFont(element));
		cell.setForeground(getForeground(element));
		cell.setBackground(getBackground(element));
		/* apex improved end */
		/* apex replaced
		super.update(cell);
		*/
	}
}
