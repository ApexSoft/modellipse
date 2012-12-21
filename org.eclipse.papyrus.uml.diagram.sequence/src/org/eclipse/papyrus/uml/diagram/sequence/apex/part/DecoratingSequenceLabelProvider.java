package org.eclipse.papyrus.uml.diagram.sequence.apex.part;

import org.eclipse.jface.viewers.DecoratingStyledCellLabelProvider;
import org.eclipse.jface.viewers.DecorationContext;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.ui.PlatformUI;

public class DecoratingSequenceLabelProvider extends DecoratingStyledCellLabelProvider implements ILabelProvider {

	public DecoratingSequenceLabelProvider(IStyledLabelProvider labelProvider) {
		super(labelProvider, PlatformUI.getWorkbench().getDecoratorManager().getLabelDecorator(), DecorationContext.DEFAULT_CONTEXT);
	}

	public String getText(Object element) {
		return getStyledText(element).toString();
	}

}
