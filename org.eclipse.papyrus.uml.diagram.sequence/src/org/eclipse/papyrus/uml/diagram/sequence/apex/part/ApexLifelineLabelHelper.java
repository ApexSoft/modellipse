package org.eclipse.papyrus.uml.diagram.sequence.apex.part;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.ui.provider.ExtendedImageRegistry;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.papyrus.infra.emf.appearance.helper.VisualInformationPapyrusConstants;
import org.eclipse.papyrus.uml.appearance.helper.AppliedStereotypeHelper;
import org.eclipse.papyrus.uml.appearance.helper.UMLVisualInformationPapyrusConstant;
import org.eclipse.papyrus.uml.diagram.common.Activator;
import org.eclipse.papyrus.uml.diagram.sequence.part.UMLDiagramEditorPlugin;
import org.eclipse.papyrus.uml.diagram.sequence.preferences.LifelinePreferencePage;
import org.eclipse.papyrus.uml.diagram.sequence.util.LifelineLabelHelper;
import org.eclipse.papyrus.uml.tools.utils.StereotypeUtil;
import org.eclipse.swt.graphics.Image;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Lifeline;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Stereotype;

public class ApexLifelineLabelHelper extends LifelineLabelHelper {

	/**
	 * singelton instance
	 */
	private static ApexLifelineLabelHelper labelHelper;

	/**
	 * Returns the singleton instance of this class
	 * 
	 * @return the singleton instance.
	 */
	public static ApexLifelineLabelHelper getInstance() {
		if(labelHelper == null) {
			labelHelper = new ApexLifelineLabelHelper();
		}
		return labelHelper;
	}
	
	@Override
	public String labelToDisplay(GraphicalEditPart editPart) {
		return super.labelToDisplay(editPart);
	}
	
	/**
	 * Parses the string containing the complete definition of properties to be
	 * displayed, and generates a map.
	 * 
	 * @param view
	 *        the view that displays the lifeline
	 * @param stereotypesToDisplay
	 *        the list of stereotypes to display
	 * @param stereotypesPropertiesToDisplay
	 *        the properties of stereotypes to display
	 * @return a map. The keys are the name of displayed stereotypes, the
	 *         corresponding data is a collection of its properties to be
	 *         displayed
	 */
	protected Map<String, List<String>> parseStereotypeProperties(View view, String stereotypesToDisplay, String stereotypesPropertiesToDisplay) {
		Map<String, List<String>> propertiesMap = new HashMap<String, List<String>>();

		StringTokenizer stringTokenizer = new StringTokenizer(stereotypesPropertiesToDisplay, UMLVisualInformationPapyrusConstant.STEREOTYPE_PROPERTIES_LIST_SEPARATOR);
		while(stringTokenizer.hasMoreTokens()) {
			String propertyName = stringTokenizer.nextToken();
			// retrieve the name of the stereotype for this property
			String stereotypeName = propertyName.substring(0, propertyName.lastIndexOf(".")); // stereotypequalifiedName.propertyname
			if(!propertiesMap.containsKey(stereotypeName)) {
				List<String> propertiesForStereotype = new ArrayList<String>();
				propertiesMap.put(stereotypeName, propertiesForStereotype);
			}
			propertiesMap.get(stereotypeName).add(propertyName.substring(propertyName.lastIndexOf(".") + 1, propertyName.length()));
		}
		return propertiesMap;
	}

	/**
	 * @param view
	 *        the view that displays the lifeline
	 * @return
	 */
	public Lifeline getUMLElement(View view) {
		return (Lifeline)view.getElement();
	}

	/**
	 * Returns the image to be displayed for the applied stereotypes.
	 * 
	 * @return the image that represents the first applied stereotype or <code>null</code> if no image has to be displayed
	 */
	public Collection<Image> stereotypeIconsToDisplay(View view) {
		String stereotypespresentationKind = AppliedStereotypeHelper.getAppliedStereotypePresentationKind(view);
		if(stereotypespresentationKind == null) {
			return null;
		}
		if(stereotypespresentationKind.equals(UMLVisualInformationPapyrusConstant.ICON_STEREOTYPE_PRESENTATION) || stereotypespresentationKind.equals(UMLVisualInformationPapyrusConstant.TEXT_ICON_STEREOTYPE_PRESENTATION)) {

			// retrieve the first stereotype in the list of displayed stereotype
			String stereotypesToDisplay = AppliedStereotypeHelper.getStereotypesToDisplay(view);
			Collection<Stereotype> stereotypes = new ArrayList<Stereotype>();
			StringTokenizer tokenizer = new StringTokenizer(stereotypesToDisplay, ",");
			while(tokenizer.hasMoreTokens()) {
				String firstStereotypeName = tokenizer.nextToken();
				stereotypes.add(getUMLElement(view).getAppliedStereotype(firstStereotypeName));
			}
			return Activator.getIconElements(getUMLElement(view), stereotypes, false);
		}
		return new ArrayList<Image>();
	}

	/**
	 * Returns a String that displays stereotypes (using their simple name or
	 * their qualified name) and their properties
	 * 
	 * @param view
	 *        the view that displays the lifeline
	 * @param separator
	 *        the separator used to split the string representing the
	 *        stereotypes.
	 * @param stereotypesToDisplay
	 *        the list of stereotypes displayed
	 * @param stereotypeWithQualifiedName
	 *        the list of stereotypes displayed using their qualified names
	 * @param stereotypesPropertiesToDisplay
	 *        the list of properties to display
	 * @return a string that displays stereotypes (using their simple name or
	 *         their qualified name) and their properties
	 */
	public String stereotypesAndPropertiesToDisplay(View view, String separator, String stereotypesToDisplay, String stereotypeWithQualifiedName, String stereotypesPropertiesToDisplay) {
		// Get the preference from PreferenceStore. there should be an assert
		final IPreferenceStore store = Activator.getDefault().getPreferenceStore();
		assert store != null : "The preference store was not found";
		if(store == null) {
			return "";
		}
		// retrieve if the name of the stereotype has to put to lower case or
		// not
		String sNameAppearance = store.getString(UMLVisualInformationPapyrusConstant.P_STEREOTYPE_NAME_APPEARANCE);

		// changes the string of properties into a map, where the entries of the
		// map are the
		// stereotype qualified name, and the properties to display are the data
		Map<String, List<String>> propertiesToDisplay = parseStereotypeProperties(view, stereotypesToDisplay, stereotypesPropertiesToDisplay);

		StringTokenizer strQualifiedName = new StringTokenizer(stereotypesToDisplay, ",");
		String out = "";
		while(strQualifiedName.hasMoreElements()) {
			String currentStereotype = strQualifiedName.nextToken();

			// check if current stereotype is applied
			final Element umlElement = getUMLElement(view);
			Stereotype stereotype = umlElement.getAppliedStereotype(currentStereotype);
			if(stereotype != null) {
				String name = currentStereotype;
				if((stereotypeWithQualifiedName.indexOf(currentStereotype)) == -1) {
					// property value contains qualifiedName ==> extract name
					// from it
					StringTokenizer strToken = new StringTokenizer(currentStereotype, "::");

					while(strToken.hasMoreTokens()) {
						name = strToken.nextToken();
					}
				}

				// check that the name has not already been added to the
				// displayed string
				if(sNameAppearance.equals(UMLVisualInformationPapyrusConstant.P_STEREOTYPE_NAME_DISPLAY_USER_CONTROLLED)) {
					if(out.indexOf(name) == -1) {
						out = out + Activator.ST_LEFT + name + Activator.ST_RIGHT + separator;
					}
				} else { // VisualInformationPapyrusConstants.P_STEREOTYPE_NAME_DISPLAY_UML_CONFORM))
					// {
					name = name.substring(0, 1).toLowerCase() + name.substring(1, name.length());
					if(out.indexOf(name) == -1) {
						out = out + Activator.ST_LEFT + name + Activator.ST_RIGHT + separator;
					}
				}

				// now should add all properties associated to this stereotype
				List<String> properties = propertiesToDisplay.get(stereotype.getQualifiedName());
				if(properties != null) {
					// retrieve property
					for(String propertyName : properties) {
						out = out + StereotypeUtil.displayPropertyValue(stereotype, StereotypeUtil.getPropertyByName(stereotype, propertyName), getUMLElement(view), " ");
					}
				}
			}
		}
		if(out.endsWith(",")) {
			return out.substring(0, out.length() - 1);
		}
		if(out.endsWith(separator)) {
			return out.substring(0, out.length() - separator.length());
		}
		return out;
	}

	/**
	 * get the list of stereotype to display from the eannotation
	 * 
	 * @return the list of stereotypes to display
	 */
	public String stereotypesToDisplay(View view) {
		// try to display stereotype properties
		String stereotypesPropertiesToDisplay = AppliedStereotypeHelper.getAppliedStereotypesPropertiesToDisplay(view);
		String stereotypesToDisplay = AppliedStereotypeHelper.getStereotypesToDisplay(view);
		String stereotypespresentationKind = AppliedStereotypeHelper.getAppliedStereotypePresentationKind(view);

		// check the presentation kind. if only icon => do not display
		// stereotype, only values
		if(UMLVisualInformationPapyrusConstant.ICON_STEREOTYPE_PRESENTATION.equals(stereotypespresentationKind)) {
			return StereotypeUtil.getPropertiesValuesInBrace(stereotypesPropertiesToDisplay, getUMLElement(view));
		}

		String stereotypesToDisplayWithQN = AppliedStereotypeHelper.getStereotypesQNToDisplay(view);
		String display = "";
		if(UMLVisualInformationPapyrusConstant.STEREOTYPE_TEXT_VERTICAL_PRESENTATION.equals(stereotypespresentationKind)) {
			display += stereotypesAndPropertiesToDisplay(view, "\n", stereotypesToDisplay, stereotypesToDisplayWithQN, stereotypesPropertiesToDisplay);
		} else {
			final String st = stereotypesToDisplay(view, ", ", stereotypesToDisplay, stereotypesToDisplayWithQN);
			if(st != null && !st.equals("")) {
				display += Activator.ST_LEFT + st + Activator.ST_RIGHT + " ";
			}
			final String propSt = StereotypeUtil.getPropertiesValuesInBrace(stereotypesPropertiesToDisplay, getUMLElement(view));
			if(propSt != null && !propSt.equals("")) {
				if(st != null && !st.equals("")) {
					// display += "\n";
				}
				display += "{" + propSt + "} ";
			}
		}
		return display;
	}

	/**
	 * Computes the string that displays the stereotypes for the current element
	 * 
	 * @param view
	 *        the view that displays the lifeline
	 * @param separator
	 *        the separator used to split the string representing the
	 *        stereotypes.
	 * @param stereotypesToDisplay
	 *        the list of stereotypes displayed
	 * @param stereotypeWithQualifiedName
	 *        the list of stereotypes displayed using their qualified names
	 * @return the string that represent the stereotypes
	 */
	public String stereotypesToDisplay(View view, String separator, String stereotypesToDisplay, String stereotypeWithQualifiedName) {

		// Get the preference from PreferenceStore. there should be an assert
		final IPreferenceStore store = Activator.getDefault().getPreferenceStore();
		assert store != null : "The preference store was not found";
		if(store == null) {
			return "";
		}
		String sNameAppearance = store.getString(UMLVisualInformationPapyrusConstant.P_STEREOTYPE_NAME_APPEARANCE);

		StringTokenizer strQualifiedName = new StringTokenizer(stereotypesToDisplay, ",");
		String out = "";
		while(strQualifiedName.hasMoreElements()) {
			String currentStereotype = strQualifiedName.nextToken();

			// check if current stereotype is applied
			final Element umlElement = getUMLElement(view);
			Stereotype stereotype = umlElement.getAppliedStereotype(currentStereotype);
			if(stereotype != null) {
				String name = currentStereotype;
				if((stereotypeWithQualifiedName.indexOf(currentStereotype)) == -1) {
					// property value contains qualifiedName ==> extract name
					// from it
					StringTokenizer strToken = new StringTokenizer(currentStereotype, "::");

					while(strToken.hasMoreTokens()) {
						name = strToken.nextToken();
					}
				}

				// check that the name has not already been added to the
				// displayed string
				if(sNameAppearance.equals(UMLVisualInformationPapyrusConstant.P_STEREOTYPE_NAME_DISPLAY_USER_CONTROLLED)) {
					if(out.indexOf(name) == -1) {
						out = out + name + separator;
					}
				} else { // VisualInformationPapyrusConstants.P_STEREOTYPE_NAME_DISPLAY_UML_CONFORM))
					// {
					name = name.substring(0, 1).toLowerCase() + name.substring(1, name.length());
					if(out.indexOf(name) == -1) {
						out = out + name + separator;
					}
				}
			}
		}
		if(out.endsWith(",")) {
			return out.substring(0, out.length() - 1);
		}
		if(out.endsWith(separator)) {
			return out.substring(0, out.length() - separator.length());
		}
		return out;
	}

	/**
	 * Computes the label to be displayed for the property
	 */
	public String labelToDisplay(View view) {
		StringBuffer buffer = new StringBuffer();

		// computes the label for the stereotype (horizontal presentation)
		buffer.append(stereotypesToDisplay(view));

		// computes the string label to be displayed
		buffer.append(elementLabel(view));

		// buffer.append(PropertyUtil.getCustomLabel(getUMLElement(), 0));
		return buffer.toString();
	}

	/**
	 * Computes the label corresponding to the semantic element
	 * 
	 * @param editPart
	 *        the graphical part managing the semantic element
	 * @return the string corresponding to the display of the semantic element
	 */
	public String elementLabel(View view) {
		int displayValue = getCurrentDisplayValue(view);
		return getCustomLabel(getUMLElement(view), displayValue);
	}
	
	private String getCustomLabel(Lifeline lifeline, int displayValue) {
		StringBuilder sb = new StringBuilder();		
		appendName(lifeline, displayValue, sb);
		
		// handle represent type label
		appendType(lifeline, displayValue, sb);
		return sb.toString();
	}
	
	private int getCurrentDisplayValue(View view) {
		EAnnotation customeDisplayAnnotation = view.getEAnnotation(VisualInformationPapyrusConstants.CUSTOM_APPEARENCE_ANNOTATION);
		int displayValue = LifelinePreferencePage.DEFAULT_LABEL_DISPLAY;
		if(customeDisplayAnnotation != null && customeDisplayAnnotation.getDetails().get(VisualInformationPapyrusConstants.CUSTOM_APPEARANCE_MASK_VALUE) != null) {
			displayValue = Integer.parseInt(customeDisplayAnnotation.getDetails().get(VisualInformationPapyrusConstants.CUSTOM_APPEARANCE_MASK_VALUE));
		} else {
			// no specific information => look in preferences
			IPreferenceStore store = UMLDiagramEditorPlugin.getInstance().getPreferenceStore();
			int displayValueTemp = store.getInt(LifelinePreferencePage.LABEL_DISPLAY_PREFERENCE);
			if(displayValueTemp != 0) {
				displayValue = displayValueTemp;
			}
		}
		return displayValue;
	}

	/**
	 * Returns the image for the element
	 * 
	 * @param view
	 *        the view that displays the lifeline
	 * @return the image
	 */
	public Image getImage(View view) {
		Element element = getUMLElement(view);
		String key = "";
		if(element instanceof NamedElement) {
			key = ((NamedElement)element).getName() + "::" + ((NamedElement)element).getVisibility();
		} else if(element != null) {
			key = element.getClass().getName();
		}
		ImageRegistry imageRegistry = Activator.getDefault().getImageRegistry();
		Image image = imageRegistry.get(key);
		ImageDescriptor descriptor = null;
		if(image == null) {
			AdapterFactory factory = Activator.getDefault().getItemProvidersAdapterFactory();
			IItemLabelProvider labelProvider = (IItemLabelProvider)factory.adapt(getUMLElement(view), IItemLabelProvider.class);
			if(labelProvider != null) {
				descriptor = ExtendedImageRegistry.getInstance().getImageDescriptor(labelProvider.getImage(getUMLElement(view)));
			}
			if(descriptor == null) {
				descriptor = ImageDescriptor.getMissingImageDescriptor();
			}
			imageRegistry.put(key, descriptor);
			image = imageRegistry.get(key);
		}
		return image;
	}



}
