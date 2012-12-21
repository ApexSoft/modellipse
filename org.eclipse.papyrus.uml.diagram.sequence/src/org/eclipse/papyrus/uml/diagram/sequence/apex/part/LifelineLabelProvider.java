package org.eclipse.papyrus.uml.diagram.sequence.apex.part;

import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.papyrus.infra.emf.appearance.helper.AppearanceHelper;
import org.eclipse.swt.graphics.Image;

public class LifelineLabelProvider extends LabelProvider {

	@Override
	public Image getImage(Object element) {
		if (element instanceof GraphicalEditPart) {
			GraphicalEditPart editPart = (GraphicalEditPart)element;
			boolean isShow = AppearanceHelper.showElementIcon((View)editPart.getModel());
			if (isShow) {
				return ApexLifelineLabelHelper.getInstance().getImage(editPart);
			}
		}
		return super.getImage(element);
	}

	@Override
	public String getText(Object element) {
		if (element instanceof GraphicalEditPart) {
			GraphicalEditPart editPart = (GraphicalEditPart)element;
			return ApexLifelineLabelHelper.getInstance().labelToDisplay(editPart);
		}
		// TODO Auto-generated method stub
		return super.getText(element);
	}

}
