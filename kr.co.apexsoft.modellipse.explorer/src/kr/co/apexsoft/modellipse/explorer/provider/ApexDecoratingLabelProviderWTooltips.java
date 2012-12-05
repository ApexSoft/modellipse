package kr.co.apexsoft.modellipse.explorer.provider;

import java.util.Arrays;

import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.graphics.Point;
import org.eclipse.ui.internal.navigator.NavigatorDecoratingLabelProvider;

public class ApexDecoratingLabelProviderWTooltips extends
		NavigatorDecoratingLabelProvider {
	
	private ApexUMLLabelProvider apexLabelProvider;

	public ApexDecoratingLabelProviderWTooltips(ILabelProvider commonLabelProvider) {
		super(commonLabelProvider);
		if(commonLabelProvider instanceof ApexUMLLabelProvider) {
			apexLabelProvider = (ApexUMLLabelProvider)commonLabelProvider;
		}
	}

	@Override
	public String getToolTipText(Object element) {
		return apexLabelProvider.getMarkerMessage(element);
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
	}
}
