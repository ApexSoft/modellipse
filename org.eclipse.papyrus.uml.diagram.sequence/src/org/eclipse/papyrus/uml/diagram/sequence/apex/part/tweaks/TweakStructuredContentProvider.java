package org.eclipse.papyrus.uml.diagram.sequence.apex.part.tweaks;

import org.eclipse.gef.EditPart;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

public class TweakStructuredContentProvider implements
		IStructuredContentProvider {

	public void dispose() {
	}

	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
	}

	public Object[] getElements(Object inputElement) {
		if (inputElement instanceof EditPart) {
			return ((EditPart)inputElement).getChildren().toArray();
		}
		return null;
	}

}
