package org.eclipse.papyrus.uml.diagram.sequence.apex.part.tweaks;

import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

public interface ITweak {

	public Control createContent(Composite parent);

	public ISelectionProvider getSelectionProvider();

	public void activate();

	public boolean isActive();

	public void setInput(Object element);

	public void dispose();

}
