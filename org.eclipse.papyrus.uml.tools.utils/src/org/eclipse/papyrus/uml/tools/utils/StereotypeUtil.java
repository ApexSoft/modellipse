/*****************************************************************************
 * Copyright (c) 2008 CEA LIST.
 *
 *    
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Patrick Tessier (CEA LIST) Patrick.tessier@cea.fr - Initial API and implementation
 *
 *****************************************************************************/
package org.eclipse.papyrus.uml.tools.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.EnumerationLiteral;
import org.eclipse.uml2.uml.Image;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.util.UMLUtil;

/**
 * Utility class for Stereotypes.
 */
public class StereotypeUtil {

	protected static final String QUOTE = "\"";

	protected static final String SPACE_SEPARATOR = "#";

	protected static final String EQUAL_SEPARATOR = "=";

	protected static final String PROPERTY_VALUE_SEPARATOR = "|";

	protected static final String SETREOTYPE_WITH_VALUE_SEPARATOR = ";";

	protected static final String ST_LEFT = String.valueOf("\u00AB");

	protected static final String ST_RIGHT = String.valueOf("\u00BB");

	/**
	 * returns the list of all super stereotypes for the specified stereotype
	 * 
	 * @param stereotype
	 *        the stereotype for which super-stereotypes are looked for.
	 * @return the list of all stereotypes from which the specified stereotype inherits
	 */
	public static List<Stereotype> getAllSuperStereotypes(Stereotype stereotype) {
		List<Stereotype> generalStereotypes = new ArrayList<Stereotype>();
		for(Classifier generalClassifier : stereotype.getGenerals()) {
			if(generalClassifier instanceof Stereotype) {
				generalStereotypes.add((Stereotype)generalClassifier);
				generalStereotypes.addAll(getAllSuperStereotypes((Stereotype)generalClassifier));
			}
		}
		return generalStereotypes;
	}

	/**
	 * Parse the stereotype image and select those that have an "icon" kind (EAnnotation).
	 * 
	 * @param stereotype
	 *        to parse
	 * 
	 * @return a EList of {@link Image}
	 */
	public static EList<Image> getIcons(Stereotype stereotype) {

		EList<Image> icons = new BasicEList<Image>();

		Iterator<Image> it = stereotype.getIcons().iterator();
		while(it.hasNext()) {
			Image image = it.next();
			if("icon".equals(ImageUtil.getKind(image))) {
				icons.add(image);
			}
		}

		return icons;
	}

	/**
	 * Returns the list of names (not qualified) of properties to display.
	 * 
	 * @param stereotype
	 * @param stPropList
	 * 
	 * @return
	 */
	private static List<String> getStereoPropertiesToDisplay(org.eclipse.uml2.uml.Stereotype stereotype, List<String> stPropList) {
		ArrayList<String> result = new ArrayList<String>();

		Iterator<String> propIter = stPropList.iterator();
		while(propIter.hasNext()) {
			String currentProp = propIter.next();
			if(currentProp.substring(0, currentProp.lastIndexOf(".")).equals(stereotype.getQualifiedName())) {
				result.add(currentProp.substring(currentProp.lastIndexOf(".") + 1, currentProp.length()));
			}
		}
		return result;
	}

	/**
	 * return string that contains value of properties of applied stereotype
	 * 
	 * @param stereotypesPropertiesToDisplay
	 *        list of properties of stereotype to display grammar=
	 *        {<B>stereotypequalifiedName</B>'.'<B>propertyName</B>','}*
	 * 
	 * @return a string withe the following grammar grammar=
	 *         {'\u00AB'<B>StereotypeName</B>'\u00BB''#'
	 *         {<B>propertyName</B>'='<B>propertyValue</B>'|'}*';'}*
	 */
	public static String getPropertiesValues(String stereotypesPropertiesToDisplay, Element umlElement) {
		HashSet<org.eclipse.uml2.uml.Stereotype> stereoSet = new HashSet<org.eclipse.uml2.uml.Stereotype>();
		ArrayList<String> stPropList = new ArrayList<String>();

		String propValues = "";

		// fill our data structure in order to generate the string
		StringTokenizer propStringTokenizer = new StringTokenizer(stereotypesPropertiesToDisplay, ",");
		while(propStringTokenizer.hasMoreElements()) {
			// extract property to display
			String propertyQN = propStringTokenizer.nextToken();
			// stereotype
			String stereotypeQN = propertyQN.substring(0, propertyQN.indexOf("."));

			Stereotype stereotype = umlElement.getAppliedStereotype(stereotypeQN);
			if(stereotype != null) {
				stereoSet.add(stereotype);
			}
			stPropList.add(propertyQN);
		}

		// Display each stereotype
		Iterator<org.eclipse.uml2.uml.Stereotype> stereoIter = stereoSet.iterator();
		while(stereoIter.hasNext()) {
			org.eclipse.uml2.uml.Stereotype stereotype = stereoIter.next();
			// display the stereotype
			propValues = propValues + ST_LEFT + stereotype.getName() + ST_RIGHT + SPACE_SEPARATOR;
			// get the set of property to display
			Iterator<String> stPropIter = getStereoPropertiesToDisplay(stereotype, stPropList).iterator();

			// display each property
			while(stPropIter.hasNext()) {
				String stProp = stPropIter.next();
				// get the property
				org.eclipse.uml2.uml.Property currentProp = getPropertyByName(stereotype, stProp);

				if(currentProp == null) {
					return "No value";
				}
				propValues += displayPropertyValue(stereotype, currentProp, umlElement, PROPERTY_VALUE_SEPARATOR);
			}// display each property
			if(propValues.endsWith(PROPERTY_VALUE_SEPARATOR)) {
				propValues = propValues.substring(0, propValues.lastIndexOf(PROPERTY_VALUE_SEPARATOR));
			}
			propValues = propValues + SETREOTYPE_WITH_VALUE_SEPARATOR;
		}// end display each property

		return propValues;
	}

	/**
	 * Computes the display of a property value.
	 * 
	 * @param stereotype
	 *        the stereotype that contains the property to be displayed
	 * @param property
	 *        the property to be displayed
	 * @param umlElement
	 *        the element that is stereotyped by the specified
	 * @param separator
	 *        the separator between each property value, in case several properties are
	 *        displayed for the same property
	 * @return a string corresponding to the property value
	 */
	public static String displayPropertyValue(Stereotype stereotype, Property property, Element umlElement, String separator) {
		org.eclipse.uml2.uml.Type propType = property.getType();

		// property type is an enumeration
		if(propType instanceof org.eclipse.uml2.uml.Enumeration) {
			return getPropertyValueForEnumerationType(property, stereotype, umlElement, EQUAL_SEPARATOR, separator);
		}

		// property type is a metaclass
		else if((propType instanceof org.eclipse.uml2.uml.Class) && (propType.getAppliedStereotypes() != null) && (propType.getAppliedStereotypes().size() > 0) && ((org.eclipse.uml2.uml.Stereotype)propType.getAppliedStereotypes().get(0)).getName().equals("Metaclass")) {
			return getPropertyValueForMetaclassType(property, stereotype, umlElement, EQUAL_SEPARATOR, separator,false);
		}
		// property type is a stereotype
		else if(propType instanceof org.eclipse.uml2.uml.Stereotype) {
			return getPropertyValueForStereotypeType(property, stereotype, umlElement, EQUAL_SEPARATOR, separator,false);
		}
		// property is a composite class
		else if((propType instanceof org.eclipse.uml2.uml.Class) && !(propType instanceof org.eclipse.uml2.uml.Stereotype) && property.isComposite()) {
			return /* FIXME stProp + */property.getName() + EQUAL_SEPARATOR + property.getName() + separator;
		}
		// otherwise
		else {
			return getPropertyValue(property, stereotype, umlElement, EQUAL_SEPARATOR, separator,false);
		}
	}



	/**
	 * Computes the display of a property value.
	 * 
	 * @param stereotype
	 *        the stereotype that contains the property to be displayed
	 * @param property
	 *        the property to be displayed
	 * @param umlElement
	 *        the element that is stereotyped by the specified
	 * @param separator
	 *        the separator between each property value, in case several properties are
	 *        displayed for the same property
	 * @return a string corresponding to the property value
	 */
	public static String displayPropertyValueToEdit(Stereotype stereotype, Property property, Element umlElement, String separator) {
		org.eclipse.uml2.uml.Type propType = property.getType();

		// property type is an enumeration
		if(propType instanceof org.eclipse.uml2.uml.Enumeration) {
			return getPropertyValueForEnumerationType(property, stereotype, umlElement, EQUAL_SEPARATOR, separator);
		}

		// property type is a metaclass
		else if((propType instanceof org.eclipse.uml2.uml.Class) && (propType.getAppliedStereotypes() != null) && (propType.getAppliedStereotypes().size() > 0) && ((org.eclipse.uml2.uml.Stereotype)propType.getAppliedStereotypes().get(0)).getName().equals("Metaclass")) {
			return getPropertyValueForMetaclassType(property, stereotype, umlElement, EQUAL_SEPARATOR, separator,true);
		}
		// property type is a stereotype
		else if(propType instanceof org.eclipse.uml2.uml.Stereotype) {
			return getPropertyValueForStereotypeType(property, stereotype, umlElement, EQUAL_SEPARATOR, separator,true);
		}
		// property is a composite class
		else if((propType instanceof org.eclipse.uml2.uml.Class) && !(propType instanceof org.eclipse.uml2.uml.Stereotype) && property.isComposite()) {
			return /* FIXME stProp + */property.getName() + EQUAL_SEPARATOR + property.getName() + separator;
		}
		// otherwise
		else {
			return getPropertyValue(property, stereotype, umlElement, EQUAL_SEPARATOR, separator, true);
		}
	}

	/**
	 * Retrieves a property of the specified stereotype, given its name
	 * 
	 * @param stereotype
	 *        the stereotype owner of the property
	 * @param propertyName
	 *        the name of the property to find
	 */
	public static Property getPropertyByName(Stereotype stereotype, String propertyName) {
		Iterator<Property> iterPro = stereotype.getAllAttributes().iterator();
		// from a string look for the property
		while(iterPro.hasNext()) {
			org.eclipse.uml2.uml.Property tmpProperty = iterPro.next();
			String name = "";
			if(tmpProperty != null) {
				name = (tmpProperty.getName() != null) ? tmpProperty.getName() : "";
			}
			if(name.equals(propertyName)) {
				return tmpProperty;
			}
		}
		return null;
	}

	/**
	 * return string that contains value of properties of applied stereotype
	 * 
	 * @param stereotypesPropertiesToDisplay
	 *        list of properties of stereotype to display grammar=
	 *        {<B>stereotypequalifiedName</B>'.'<B>propertyName</B>','}*
	 * 
	 * @return a string with the following grammar grammar=
	 *         {(<B>propertyName</B>'='<B>propertyValue</B>',')*
	 *         <B>propertyName</B>'='<B>propertyValue</B>'}
	 */
	public static String getPropertiesValuesInBrace(String stereotypesPropertiesToDisplay, Element umlElement) {
		String propertyValues = "";

		HashSet<org.eclipse.uml2.uml.Stereotype> stereoSet = new HashSet<org.eclipse.uml2.uml.Stereotype>();
		ArrayList<String> stPropList = new ArrayList<String>();

		// fill our data structure in order to generate the string
		StringTokenizer propStringTokenizer = new StringTokenizer(stereotypesPropertiesToDisplay, ",");
		while(propStringTokenizer.hasMoreElements()) {
			// extract property to display
			String propertyQN = propStringTokenizer.nextToken();
			// stereotype
			String stereotypeQN = propertyQN.substring(0, propertyQN.indexOf("."));

			Stereotype stereotype = umlElement.getAppliedStereotype(stereotypeQN);
			if(stereotype != null) {
				stereoSet.add(stereotype);
			}

			stPropList.add(propertyQN);
		}

		// Display each stereotype
		Iterator<org.eclipse.uml2.uml.Stereotype> stereoIter = stereoSet.iterator();
		while(stereoIter.hasNext()) {
			Stereotype stereotype = stereoIter.next();
			if(stereotype != null) {
				propertyValues += displayPropertyValuesForStereotype(stereotype, stPropList, umlElement);
			}
		}
		return propertyValues;
	}

	public static String displayPropertyValuesForStereotype(Stereotype stereotype, List<String> stPropList, Element umlElement) {
		StringBuffer buffer = new StringBuffer();

		// add stereotype name. For "In Brace", display nothing
		buffer.append("");
		// get the set of property to display
		Iterator<String> stPropIter = getStereoPropertiesToDisplay(stereotype, stPropList).iterator();

		// display each property
		while(stPropIter.hasNext()) {
			String stProp = stPropIter.next();
			// get the property
			org.eclipse.uml2.uml.Property currentProp = null;
			Iterator<Property> iterPro = stereotype.getAllAttributes().iterator();
			// from a string look for the property
			while(iterPro.hasNext()) {
				org.eclipse.uml2.uml.Property tmpProperty = iterPro.next();
				if(stProp.equals(tmpProperty.getName())) {
					currentProp = tmpProperty;
				}
			}

			if(currentProp == null) {
				return "No value";
			}
			org.eclipse.uml2.uml.Type propType = currentProp.getType();

			// property type is an enumeration

			if(propType instanceof org.eclipse.uml2.uml.Enumeration) {
				buffer.append(getPropertyValueForEnumerationType(currentProp, stereotype, umlElement, EQUAL_SEPARATOR, ","));
			}

			// property type is a metaclass
			else if((propType instanceof org.eclipse.uml2.uml.Class) && (propType.getAppliedStereotypes() != null) && (propType.getAppliedStereotypes().size() > 0) && ((org.eclipse.uml2.uml.Stereotype)propType.getAppliedStereotypes().get(0)).getName().equals("Metaclass")) {
				buffer.append(getPropertyValueForMetaclassType(currentProp, stereotype, umlElement, EQUAL_SEPARATOR, ",",false));
			}

			// property type is a stereotype
			else if(propType instanceof org.eclipse.uml2.uml.Stereotype) {
				buffer.append(getPropertyValueForStereotypeType(currentProp, stereotype, umlElement, EQUAL_SEPARATOR, ",",false));
			}

			// property is a composite class
			else if((propType instanceof org.eclipse.uml2.uml.Class) && !(propType instanceof org.eclipse.uml2.uml.Stereotype) && currentProp.isComposite()) {
				buffer.append(stProp + EQUAL_SEPARATOR + currentProp.getName() + ",");
			}

			// otherwise
			else {
				buffer.append(getPropertyValue(currentProp, stereotype, umlElement, EQUAL_SEPARATOR, ",",false));
			}
		}// display each property

		String propValues = buffer.toString();
		if(propValues.endsWith(",")) {
			propValues = propValues.substring(0, propValues.lastIndexOf(","));
		}

		return propValues;
	}

	/**
	 * return the string that represents the value of property when its type is an Enumeration
	 * 
	 * @param property
	 *        the property to display
	 * @param stereotype
	 *        the stereotype that contains the property
	 * @param umlElement
	 *        the umlelement on which the stereotype is applied
	 * @param EQUAL_SEPARATOR
	 *        the separator between property and property value
	 * @param PROPERTY_VALUE_SEPARATOR
	 *        the separator to end the exprestion
	 * @return String withe the following grammar propertyname EQUAL_SEPERATOR propertyValue
	 *         PROPERTY_VALUE_SEPERATOR
	 */
	private static String getPropertyValueForEnumerationType(Property property, Stereotype stereotype, Element umlElement, final String EQUAL_SEPARATOR, final String PROPERTY_VALUE_SEPARATOR) {
		String out = "";
		if((property.getUpper() == 1) && (umlElement.getValue(stereotype, property.getName()) != null)) {
			if((property.getLower() != 0) || umlElement.getValue(stereotype, property.getName()) != null) {
				if(property.isSetDefault() || umlElement.getValue(stereotype, property.getName()) != null) {
					Object val = umlElement.getValue(stereotype, property.getName());
					if (val instanceof EnumerationLiteral)
						out = property.getName() + EQUAL_SEPARATOR + ((EnumerationLiteral)val).getLabel() + PROPERTY_VALUE_SEPARATOR;
					else
						out = property.getName() + EQUAL_SEPARATOR + val + PROPERTY_VALUE_SEPARATOR;
				} else {
					out = property.getName() + PROPERTY_VALUE_SEPARATOR;
				}
			} else {
				out = property.getName() + PROPERTY_VALUE_SEPARATOR;
			}
		}

		// multiplicity is greater than one
		else {
			out = property.getName() + EQUAL_SEPARATOR + umlElement.getValue(stereotype, property.getName()) + PROPERTY_VALUE_SEPARATOR;
		}
		return out;
	}

	/**
	 * return the string that represents the value of property when its type is a Metaclass
	 * 
	 * @param property
	 *        the property to display
	 * @param stereotype
	 *        the stereotype that contains the property
	 * @param umlElement
	 *        the umlelement on which the stereotype is applied
	 * @param EQUAL_SEPARATOR
	 *        the separator between property and property value
	 * @param PROPERTY_VALUE_SEPARATOR
	 *        the separator to end the exprestion
	 * @return String withe the following grammar propertyname EQUAL_SEPERATOR propertyValue
	 *         PROPERTY_VALUE_SEPERATOR
	 */
	private static String getPropertyValueForMetaclassType(Property property, Stereotype stereotype, Element umlElement, final String EQUAL_SEPARATOR, final String PROPERTY_VALUE_SEPARATOR, boolean withQualifiedName) {
		String out = "";

		if((property.getUpper() == 1) && (umlElement.getValue(stereotype, property.getName()) != null) && (umlElement.getValue(stereotype, property.getName()) instanceof NamedElement)) {
			if(withQualifiedName){
				out = property.getName() + EQUAL_SEPARATOR + ((NamedElement)(umlElement.getValue(stereotype, property.getName()))).getQualifiedName() + PROPERTY_VALUE_SEPARATOR;
			}
			else{
				out = property.getName() + EQUAL_SEPARATOR + ((NamedElement)(umlElement.getValue(stereotype, property.getName()))).getName() + PROPERTY_VALUE_SEPARATOR;
			}
		}

		// multiplicity greater than one
		else if(property.getUpper() != 1) {
			List values = (List)umlElement.getValue(stereotype, property.getName());
			ArrayList elementNames = new ArrayList();
			if(values != null) {
				for(int count = 0; count < values.size(); count++) {
					if(values.get(count) instanceof NamedElement) {
						if(withQualifiedName){
							elementNames.add(((NamedElement)values.get(count)).getQualifiedName());
						}
						else{
							elementNames.add(((NamedElement)values.get(count)).getName());
						}
					}
				}
			}
			out = property.getName() + EQUAL_SEPARATOR + elementNames + PROPERTY_VALUE_SEPARATOR;
		}

		// multiplicity = 1 and property value null
		else {
			out = property.getName() + EQUAL_SEPARATOR + umlElement.getValue(stereotype, property.getName()) + PROPERTY_VALUE_SEPARATOR;
		}
		return out;
	}

	/**
	 * return the string that represents the value of property when its type is a stereotype
	 * 
	 * @param property
	 *        the property to display
	 * @param stereotype
	 *        the stereotype that contains the property
	 * @param umlElement
	 *        the umlelement on which the stereotype is applied
	 * @param EQUAL_SEPARATOR
	 *        the separator between property and property value
	 * @param PROPERTY_VALUE_SEPARATOR
	 *        the separator to end the exprestion
	 * @return String withe the following grammar propertyname EQUAL_SEPERATOR propertyValue
	 *         PROPERTY_VALUE_SEPERATOR
	 */
	private static String getPropertyValueForStereotypeType(Property property, Stereotype stereotype, Element umlElement, final String EQUAL_SEPARATOR, final String PROPERTY_VALUE_SEPARATOR, boolean withQualifiedName) {
		String out = "";
		if((property.getUpper() == 1) && (umlElement.getValue(stereotype, property.getName()) != null)) {

			// retrieve the base element from the stereotype application
			Object value = umlElement.getValue(stereotype, property.getName());
			Element baseElement = UMLUtil.getBaseElement((EObject)value);

			// display the base element's qualified name
			if(withQualifiedName){
				out = property.getName() + EQUAL_SEPARATOR + ((NamedElement)baseElement).getQualifiedName() + PROPERTY_VALUE_SEPARATOR;
			}
			else{
				out = property.getName() + EQUAL_SEPARATOR + ((NamedElement)baseElement).getName() + PROPERTY_VALUE_SEPARATOR;
			}
		}

		// multiplicity greater than one
		else if(property.getUpper() != 1) {
			// retrieve the base element from the stereotype application
			List values = (List)umlElement.getValue(stereotype, property.getName());
			ArrayList baseElements = new ArrayList();
			if(values != null) {
				for(int k = 0; k < values.size(); k++) {
					if(withQualifiedName){
						baseElements.add(((NamedElement)UMLUtil.getBaseElement((EObject)values.get(k))).getQualifiedName());
					}
					else{
						baseElements.add(((NamedElement)UMLUtil.getBaseElement((EObject)values.get(k))).getName());
					}
				}
			}

			out = property.getName() + EQUAL_SEPARATOR + baseElements + PROPERTY_VALUE_SEPARATOR;
		}

		// multiplicity = 1 and property value null
		else {
			out = property.getName() + EQUAL_SEPARATOR + (umlElement.getValue(stereotype, property.getName())) + PROPERTY_VALUE_SEPARATOR;
		}
		return out;
	}

	/**
	 * return the string that represents the value of property
	 * 
	 * @param property
	 *        the property to display
	 * @param stereotype
	 *        the stereotype that contains the property
	 * @param umlElement
	 *        the umlelement on which the stereotype is applied
	 * @param EQUAL_SEPARATOR
	 *        the separator between property and property value
	 * @param PROPERTY_VALUE_SEPARATOR
	 *        the separator to end the exprestion
	 * @return String withe the following grammar propertyname EQUAL_SEPERATOR propertyValue
	 *         PROPERTY_VALUE_SEPERATOR
	 */
	private static String getPropertyValue(Property property, Stereotype stereotype, Element umlElement, final String EQUAL_SEPARATOR, final String PROPERTY_VALUE_SEPARATOR,boolean withDelimitator) {
		String out = "";
		if((property.getLower() != 0) || umlElement.getValue(stereotype, property.getName()) != null) {
			if(property.isSetDefault() || umlElement.getValue(stereotype, property.getName()) != null) {
				if(withDelimitator){
					String value= ""+umlElement.getValue(stereotype, property.getName());
					out = property.getName() + EQUAL_SEPARATOR + value + PROPERTY_VALUE_SEPARATOR;
					if(value.contains("[")){
					out= out.replace("[", "["+QUOTE);
					out= out.replace("]", QUOTE+"]");
					out= out.replace(", ", QUOTE+","+QUOTE);
					}
					else{
						out = property.getName() + EQUAL_SEPARATOR +QUOTE +value+QUOTE + PROPERTY_VALUE_SEPARATOR;
					}
				}
				else{
					out = property.getName() + EQUAL_SEPARATOR + umlElement.getValue(stereotype, property.getName()) + PROPERTY_VALUE_SEPARATOR;}
			} else {
				out = property.getName() + PROPERTY_VALUE_SEPARATOR;
			}
		} else {
			out = property.getName() + PROPERTY_VALUE_SEPARATOR;

		}
		return out;
	}

	/**
	 * Parse the stereotype image and select those that have an "shape" kind (EAnnotation).
	 * 
	 * @param stereotype
	 *        to parse
	 * 
	 * @return a EList of {@link Image}
	 */
	public static EList<Image> getShapes(Stereotype stereotype) {

		EList<Image> shapes = new BasicEList<Image>();

		Iterator<Image> it = stereotype.getIcons().iterator();
		while(it.hasNext()) {
			Image image = it.next();
			if("shape".equals(ImageUtil.getKind(image))) {
				shapes.add(image);
			}
		}

		return shapes;
	}
}
