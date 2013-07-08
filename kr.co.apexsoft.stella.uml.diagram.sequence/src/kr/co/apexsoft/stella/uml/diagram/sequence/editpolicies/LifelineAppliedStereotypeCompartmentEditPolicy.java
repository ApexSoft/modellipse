package kr.co.apexsoft.stella.uml.diagram.sequence.editpolicies;

import org.eclipse.papyrus.uml.diagram.common.editpolicies.AppliedStereotypeCompartmentEditPolicy;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Lifeline;

public class LifelineAppliedStereotypeCompartmentEditPolicy extends
		AppliedStereotypeCompartmentEditPolicy {

	@Override
	protected Element getUMLElement() {
		Lifeline lifeline = (Lifeline) super.getUMLElement();
		if (lifeline.getRepresents() != null && lifeline.getRepresents().getType() != null) {
			return lifeline.getRepresents().getType();
		}
		return super.getUMLElement();
	}

//	@Override
//	public String stereotypesToDisplay(String separator, String stereotypesToDisplay, String stereotypeWithQualifiedName) {
//
//		// Get the preference from PreferenceStore. there should be an assert
//		final IPreferenceStore store = Activator.getDefault().getPreferenceStore();
//		assert store != null : "The preference store was not found";
//		if(store == null) {
//			return "";
//		}
//		String sNameAppearance = store.getString(UMLVisualInformationPapyrusConstant.P_STEREOTYPE_NAME_APPEARANCE);
//
//		StringTokenizer strQualifiedName = new StringTokenizer(stereotypesToDisplay, ",");
//		String out = "";
//		while(strQualifiedName.hasMoreElements()) {
//			String currentStereotype = strQualifiedName.nextToken();
//
//			// check if current stereotype is applied
//			final Element umlElement = getUMLElement();
//			Stereotype stereotype = umlElement.getAppliedStereotype(currentStereotype);
//			
//			if (stereotype == null && umlElement instanceof Lifeline) {
//				ConnectableElement represents = ((Lifeline) umlElement).getRepresents();
//				if (represents != null && represents.getType() != null) {
//					Type type = represents.getType();
//					stereotype = type.getAppliedStereotype(currentStereotype);
//				}
//			}
//			
//			if(stereotype != null) {
//				String name = currentStereotype;
//				if((stereotypeWithQualifiedName.indexOf(currentStereotype)) == -1) {
//					// property value contains qualifiedName ==> extract name
//					// from it
//					StringTokenizer strToken = new StringTokenizer(currentStereotype, "::");
//
//					while(strToken.hasMoreTokens()) {
//						name = strToken.nextToken();
//					}
//				}
//				// AL Changes Feb. 07 - Beg
//				// Handling STEREOTYPE_NAME_APPEARANCE preference (from
//				// ProfileApplicationPreferencePage)
//				// Previously lowercase forced onto first letter (standard UML)
//				// stereotypesToDisplay = stereotypesToDisplay+name.substring(0,
//				// 1).toLowerCase()+name.substring(1,
//				// name.length())+","+separator;
//
//				// check that the name has not already been added to the
//				// displayed string
//				if(sNameAppearance.equals(UMLVisualInformationPapyrusConstant.P_STEREOTYPE_NAME_DISPLAY_USER_CONTROLLED)) {
//					if(out.indexOf(name) == -1) {
//						out = out + name + separator;
//					}
//				} else { // VisualInformationPapyrusConstants.P_STEREOTYPE_NAME_DISPLAY_UML_CONFORM))
//					// {
//					name = name.substring(0, 1).toLowerCase() + name.substring(1, name.length());
//					if(out.indexOf(name) == -1) {
//						out = out + name + separator;
//					}
//				}
//			}
//		}
//		if(out.endsWith(",")) {
//			return out.substring(0, out.length() - 1);
//		}
//		if(out.endsWith(separator)) {
//			return out.substring(0, out.length() - separator.length());
//		}
//		return out;
//	}
}
