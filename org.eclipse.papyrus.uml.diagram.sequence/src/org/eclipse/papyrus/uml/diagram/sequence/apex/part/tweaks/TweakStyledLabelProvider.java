package org.eclipse.papyrus.uml.diagram.sequence.apex.part.tweaks;

import org.eclipse.jface.viewers.DelegatingStyledCellLabelProvider.IStyledLabelProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.swt.graphics.Image;

public class TweakStyledLabelProvider extends LabelProvider implements
		IStyledLabelProvider {

	private ILabelProvider fDelagate;
	
	protected TweakStyledLabelProvider() {
		
	}
	
	public TweakStyledLabelProvider(ILabelProvider delegate) {
		fDelagate = delegate;
	}
	
	@Override
	public Image getImage(Object element) {
		return fDelagate.getImage(element);
	}

	@Override
	public String getText(Object element) {
		return fDelagate.getText(element);
	}

	public StyledString getStyledText(Object element) {
		StyledString styledText = convertToStyledString(getText(element));
		if (styledText != null) {
			return styledText;
		} else if (fDelagate != null) {
			if (fDelagate instanceof IStyledLabelProvider) {
				styledText = ((IStyledLabelProvider) fDelagate).getStyledText(element);
				if (styledText != null) {
					return styledText;
				}
			} else {
				styledText = convertToStyledString(fDelagate.getText(element));
				if (styledText != null) {
					return styledText;
				}
			}
		}
		return getDefaultStyledText();
	}
	
	protected StyledString convertToStyledString(Object text) {
		if (text instanceof StyledString) {
			return (StyledString) text;
		} else if (text instanceof String) {
			return new StyledString((String) text);
		}
		return null;
	}
	
	protected StyledString getDefaultStyledText() {
		return null;
	}

}
