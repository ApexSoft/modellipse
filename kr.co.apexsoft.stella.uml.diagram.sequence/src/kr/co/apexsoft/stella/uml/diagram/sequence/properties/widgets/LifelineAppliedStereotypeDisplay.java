package kr.co.apexsoft.stella.uml.diagram.sequence.properties.widgets;

import java.util.Collections;

import kr.co.apexsoft.stella.uml.diagram.sequence.properties.tree.objects.LifelineTreeObject;

import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.papyrus.infra.widgets.editors.AbstractEditor;
import org.eclipse.papyrus.uml.properties.modelelement.UMLNotationModelElement;
import org.eclipse.papyrus.uml.properties.profile.ui.compositeforview.AppearanceForAppliedStereotypeComposite;
import org.eclipse.papyrus.views.properties.modelelement.ModelElement;
import org.eclipse.papyrus.views.properties.widgets.AbstractPropertyEditor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.uml2.uml.Element;

public class LifelineAppliedStereotypeDisplay extends AbstractPropertyEditor {

	private AppearanceForAppliedStereotypeComposite composite;
	
	public LifelineAppliedStereotypeDisplay(Composite parent, int style) {
		composite = new AppearanceForAppliedStereotypeComposite(parent);
		composite.createContent(parent, AbstractEditor.factory);
	}
	
	@Override
	protected void doBinding() {
		ModelElement element = input.getModelElement(propertyPath);
		if (element instanceof UMLNotationModelElement) {
			View diagramElement = (View) ((UMLNotationModelElement) element).getEModelElement();
			EditPart editPart = ((UMLNotationModelElement) element).getEditPart();
			Element umlElement = (Element) diagramElement.getElement();
			
			composite.setSelection(new StructuredSelection(Collections.singletonList(editPart)));
			composite.setElement(umlElement);
			composite.setInput(new LifelineTreeObject(umlElement));
			composite.setDiagramElement(diagramElement);
			
			composite.refresh();
		}
	}

}
