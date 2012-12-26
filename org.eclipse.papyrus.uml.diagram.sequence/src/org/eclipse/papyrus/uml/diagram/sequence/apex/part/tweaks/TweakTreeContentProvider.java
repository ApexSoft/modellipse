package org.eclipse.papyrus.uml.diagram.sequence.apex.part.tweaks;

import org.eclipse.gef.EditPart;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

public class TweakTreeContentProvider extends TweakStructuredContentProvider implements ITreeContentProvider {

	public void dispose() {
	}

	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
	}

	public Object[] getElements(Object inputElement) {
		return getChildren(inputElement);
	}

	public Object[] getChildren(Object parentElement) {
		if (parentElement instanceof EditPart) {
			return ((EditPart)parentElement).getChildren().toArray();
		}
		return new Object[0];
	}

	public Object getParent(Object element) {
		if (element instanceof EditPart) {
			return ((EditPart)element).getParent();
		}
		return null;
	}

	public boolean hasChildren(Object element) {
		return getChildren(element).length > 0;
	}


}
