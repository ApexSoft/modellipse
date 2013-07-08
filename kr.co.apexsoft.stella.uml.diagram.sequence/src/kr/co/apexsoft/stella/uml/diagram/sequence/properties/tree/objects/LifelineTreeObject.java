package kr.co.apexsoft.stella.uml.diagram.sequence.properties.tree.objects;

import java.util.Iterator;

import org.eclipse.papyrus.uml.profile.tree.objects.AppliedStereotypeTreeObject;
import org.eclipse.papyrus.uml.profile.tree.objects.StereotypedElementTreeObject;
import org.eclipse.uml2.uml.ConnectableElement;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Lifeline;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.Type;

public class LifelineTreeObject extends StereotypedElementTreeObject {

	public LifelineTreeObject(Element element) {
		super(element);
	}

	@Override
	protected void createChildren() {
		super.createChildren();
		
		Lifeline lifeline = (Lifeline) getElement();
		ConnectableElement represents = lifeline.getRepresents();
		if (represents != null && represents.getType() != null) {
			Type type = represents.getType();
			Iterator<Stereotype> stIt = type.getAppliedStereotypes().iterator();
			
			while (stIt.hasNext()) {
				final Stereotype currentSt = stIt.next();
				addChild(new AppliedStereotypeTreeObject(this, currentSt));
			}
		}
	}

}
