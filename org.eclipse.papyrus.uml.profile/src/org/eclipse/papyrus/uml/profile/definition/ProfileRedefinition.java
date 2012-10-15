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
 *  Chokri Mraidha (CEA LIST) Chokri.Mraidha@cea.fr - Initial API and implementation
 *  Patrick Tessier (CEA LIST) Patrick.Tessier@cea.fr - modification
 *
 *****************************************************************************/
package org.eclipse.papyrus.uml.profile.definition;

import java.util.ArrayList;
import java.util.Iterator;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.papyrus.uml.profile.Message;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.DataType;
import org.eclipse.uml2.uml.Enumeration;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Profile;
import org.eclipse.uml2.uml.Type;


/**
 * <p>
 * this class is used to manage the redefinition of profiles:
 * </p>
 * The normal definition in UML is like this:
 * <ul>
 * <li>Stereotype-->Eclass</li>
 * <li>Enumeration-->EEnumeration :local copy
 * <li>Datatype-->EClass</li>
 * <li>Property--> EReference or EAttribute with ref on local copy</li>
 * <li>PrimitiveType-> Edatatype : local copy</li>
 * </ul>
 * In papyrus:
 * <ul>
 * <li>Stereotype-->Eclass
 * <li>Enumeration-->EEnumeration:local copy
 * <li>Datatype-->EDatatype: local copy
 * <li>Property--> EReference or EAttribute with ref on direct definition
 * <li>PrimitiveType-> Edatatype : local copy
 * </ul>
 */
public class ProfileRedefinition {

	/**
	 * this method is used to redefine profile even if it contains subpackages
	 * or subprofiles
	 * 
	 * @param thepackage
	 *        the given profile that we want to define
	 * @param definitionAnnotation
	 *        the definition annotation that is used to create the version
	 *        annotation
	 */
	public static void redefineProfile(Package thepackage, PapyrusDefinitionAnnotation definitionAnnotation) {
		// he wants to define
		if(thepackage instanceof Profile) {
			Profile profile = (Profile)thepackage;
			// get profile definition
			EPackage profileDefinition = profile.getDefinition();
			// collect all EClassifier of the definition
			ArrayList<EClassifier> tempList = new ArrayList<EClassifier>();
			for(int i = 0; i < profileDefinition.getEClassifiers().size(); i++) {
				tempList.add((EClassifier)profileDefinition.getEClassifiers().get(i));
			}

			// for all EClass
			Iterator<EClassifier> eClassIter = tempList.iterator();
			while(eClassIter.hasNext()) {
				EClassifier eclassifier = eClassIter.next();
				if(eclassifier instanceof EClass && !isDataTypeDefinition(eclassifier)) {
					// redefine Eclass
					redefineEclass((EClass)eclassifier);
				}
			}

			// add profile definition annotation
			if(definitionAnnotation != null) {
				profile.getDefinition().getEAnnotations().add(definitionAnnotation.convertToEAnnotation());
			}
		}
		Iterator<Package> it = thepackage.getNestedPackages().iterator();
		while(it.hasNext()) {
			Package p = it.next();
			ProfileRedefinition.redefineProfile(p, definitionAnnotation);
		}
	}

	/**
	 * redefine only real definition or do nothing
	 * 
	 * @param eclass
	 *        the given eclass that we want to redefine
	 */
	public static void redefineEclass(EClass eclass) {
		if(isADirectDefinition(eclass)) {
			// 1. redefine inheritances
			EList<EClass> eSuperTypes = eclass.getESuperTypes();

			/* copy in order to avoid concurrent access */
			ArrayList<EClass> superTypesList = new ArrayList<EClass>();
			for(int j = 0; j < eSuperTypes.size(); j++) {
				superTypesList.add((EClass)eSuperTypes.get(j));
			}
			// for each super types :we test if this is a direct definition
			// if not we remove the local copy and replace by direct definition
			Iterator<EClass> superIter = superTypesList.iterator();
			while(superIter.hasNext()) {
				EClass currentSuperClass = superIter.next();
				if(!isADirectDefinition(currentSuperClass)) {
					EClass directSuperClass = (EClass)lookForDirectDefinitionFrom(currentSuperClass);
					eclass.getESuperTypes().remove(currentSuperClass);
					eclass.getESuperTypes().add(directSuperClass);
				}
			}
			// 2.redefine eReferences
			// temp copy of the list
			Iterator<EReference> iterReference = eclass.getEReferences().iterator();
			ArrayList<EReference> referenceList = new ArrayList<EReference>();
			while(iterReference.hasNext()) {
				referenceList.add(iterReference.next());
			}
			// for each reference of the EClass
			Iterator<EReference> refIterator = referenceList.iterator();
			while(refIterator.hasNext()) {
				redefineEReference(refIterator.next(), eclass.getEPackage());
			}
			// 3. redefine EAttributes
			Iterator<EAttribute> eattIterator = eclass.getEAllAttributes().iterator();
			// for each reference of the EClass
			while(eattIterator.hasNext()) {
				redefineEAttribute(eattIterator.next(), eclass.getEPackage());
			}
		}
	}

	/**
	 * this class is used to redefine EReference with direct definition
	 * 
	 * @param eReference
	 *        the given EReference that we want to redefine
	 */
	public static void redefineEReference(EReference eReference, EPackage profileDefinition) {
		EReference oldEOpposite = eReference.getEOpposite();
		EClassifier oldType = eReference.getEType();

		// 2.1 the type is an EClass
		if(oldType instanceof EClass) {
			// redefine type
			eReference.setContainment(false);
			if(!isDataTypeDefinition(oldType)) {
				eReference.setEType(lookForDirectDefinitionFrom((EClass)oldType));
				// redefine the Opposite
				if(oldEOpposite != null)
					eReference.setEOpposite(lookForEquivalentEreference((EClass)eReference.getEType(), oldEOpposite));
			}
		}
		if(isDataTypeDefinition(oldType)) {
			EAttribute eatt = createEAttribute(eReference.getEContainingClass(), eReference);
			eatt.setEType(createDataType(profileDefinition, getUMLClassifierFromDefinition(oldType)));
			eatt.setDefaultValue(eReference.getDefaultValue());
			eatt.setDefaultValueLiteral(eReference.getDefaultValueLiteral());
			eReference.getEContainingClass().getEStructuralFeatures().remove(eReference);
		}
	}

	/**
	 * this class is used to redefine EAttribute with direct definition
	 * 
	 * @param eAttribute
	 *        the given EAttribute that we want to redefine
	 * @param profileDefinition
	 *        the epackage that contains the Eclass , container of this
	 *        Eattribute
	 */
	public static void redefineEAttribute(EAttribute eAttribute, EPackage profileDefinition) {
		EClassifier oldEType = eAttribute.getEType();
		if(isDataTypeDefinition(oldEType)) {
			eAttribute.setEType(createDataType(profileDefinition, getUMLClassifierFromDefinition(oldEType)));
		}
	}

	/**
	 * return id this Eclass is the real Definition
	 * 
	 * @param eclass
	 *        the eclass that we want to test
	 * @return true if this is the real definition or not is this is a local
	 *         copy
	 */
	public static boolean isADirectDefinition(EClass eclass) {
		if(eclass.getEAnnotations().size() > 0) {
			EAnnotation eAnnotation = eclass.getEAnnotations().get(0);
			if(eAnnotation.getReferences().size() > 0) {
				if(!(eAnnotation.getReferences().get(0) instanceof org.eclipse.uml2.uml.Stereotype)) {
					String errMsg = "Problem because of the definition of " + eclass.getName() + " in "
							+ eclass.getEPackage().getNsURI();
					Message.error(errMsg);
				}
				org.eclipse.uml2.uml.Stereotype theStereotype = (org.eclipse.uml2.uml.Stereotype)eAnnotation
						.getReferences().get(0);
				if(theStereotype.getDefinition().equals(eclass)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * look for the real definition of the stereotype where the EClass may be
	 * the definition
	 * 
	 * @param eclass
	 *        that maybe the real definition (maybe a local copy of
	 *        definition)
	 * @return the real definition or the itself
	 */
	public static EClassifier lookForDirectDefinitionFrom(EClassifier eClassifier) {
		if(eClassifier.getEAnnotations().size() > 0) {
			EAnnotation eAnnotation = eClassifier.getEAnnotations().get(0);
			if(eAnnotation.getReferences().size() > 0) {
				org.eclipse.uml2.uml.Stereotype theStereotype = (org.eclipse.uml2.uml.Stereotype)eAnnotation
						.getReferences().get(0);
				return theStereotype.getDefinition();
			}
		}
		return eClassifier;
	}

	/**
	 * this method is used to look for an EReference equivalent to a given
	 * EReference same name and type
	 * 
	 * @param eclass
	 *        the given EClass where we look for the equivalent EReference
	 * @param eReference
	 *        the given EReference
	 * @return the return EReference or null
	 */
	private static EReference lookForEquivalentEreference(EClass eclass, EReference eReference) {
		Iterator<EReference> refIterator = eclass.getEReferences().iterator();
		while(refIterator.hasNext()) {
			EReference currentEReference = refIterator.next();
			if(currentEReference.getName().equals(eReference.getName())) {
				if(currentEReference.getEType().getName().endsWith(eReference.getEType().getName())) {
					return currentEReference;
				}
			}
		}
		return null;
	}

	/**
	 * this method is used to suppress all local copy of EClass in each Profile.
	 * 
	 * @param thePackage
	 *        that we want to clean
	 */
	public static void cleanProfile(Package thePackage) {
		if(thePackage instanceof Profile) {
			Profile profile = (Profile)thePackage;
			// get profile definition
			EPackage profileDefinition = profile.getDefinition();

			// collect all EClassifier of the definition
			ArrayList<EClassifier> tempList = new ArrayList<EClassifier>();
			for(int i = 0; i < profileDefinition.getEClassifiers().size(); i++) {
				tempList.add((EClassifier)profileDefinition.getEClassifiers().get(i));
			}

			// for all EClass
			Iterator<EClassifier> eClassIter = tempList.iterator();
			while(eClassIter.hasNext()) {
				EClassifier eclassifier = eClassIter.next();

				if(eclassifier instanceof EClass) {
					if(isDataTypeDefinition(eclassifier)) {
						profileDefinition.getEClassifiers().remove(eclassifier);
					}

					// this is a direct Definition?
					else if(!isADirectDefinition((EClass)eclassifier)) {
						// no, so it is removed
						profileDefinition.getEClassifiers().remove(eclassifier);
					}

				}
			}
		}
		Iterator<Package> it = thePackage.getNestedPackages().iterator();
		while(it.hasNext()) {
			Package p = it.next();
			ProfileRedefinition.cleanProfile(p);
		}
	}

	/**
	 * Create a Edataptype from a datatype if it does not exist
	 * 
	 * @param profileDefinition
	 *        the Epackage where we want to create the Edatatype
	 * @param currentUmlType
	 *        from this, the Edatatype will be created
	 * @return the created datatype
	 */
	public static EDataType createDataType(EPackage profileDefinition, Type currentUmlType) {
		/* if eDataType already exists */
		Iterator<EClassifier> iter = profileDefinition.getEClassifiers().iterator();
		while(iter.hasNext()) {
			EClassifier current = iter.next();
			if(current instanceof EDataType) {
				if(current.getName().equals(currentUmlType.getName())) {
					// instance class name specified for primitiveTypes.
					current.setInstanceClassName("java.lang.String");
					return (EDataType)current;
				}
			}
		}
		// no, it has to be created
		EDataType dataTypeDef = EcoreFactory.eINSTANCE.createEDataType();
		dataTypeDef.setName(currentUmlType.getName());
		dataTypeDef.setInstanceClassName("java.lang.String");
		dataTypeDef.setSerializable(true);

		EAnnotation umlAnnotation = EcoreFactory.eINSTANCE.createEAnnotation();
		umlAnnotation.setSource("http://www.eclipse.org/uml2/2.0.0/UML");
		umlAnnotation.getReferences().add(currentUmlType);

		dataTypeDef.getEAnnotations().add(umlAnnotation);

		profileDefinition.getEClassifiers().add(dataTypeDef);
		return dataTypeDef;
	}

	/**
	 * this method is used to created an EAttribute from an Ereference
	 * 
	 * @param container
	 *        the Eclass that will contain the eattribute
	 * @param eReference
	 *        from this, the eattribute will be created
	 * @return the created Eattribute
	 */
	public static EAttribute createEAttribute(EClass container, EReference eReference) {

		EAttribute eAttribute = EcoreFactory.eINSTANCE.createEAttribute();
		eAttribute.setEType(eReference.getEType());
		eAttribute.setName(eReference.getName());
		eAttribute.setChangeable(eReference.isChangeable());
		eAttribute.setLowerBound(eReference.getLowerBound());
		eAttribute.setUpperBound(eReference.getUpperBound());
		eAttribute.setOrdered(eReference.isOrdered());
		eAttribute.setDerived(eReference.isDerived());
		eAttribute.setTransient(eReference.isTransient());
		eAttribute.setUnique(eReference.isUnique());
		eAttribute.setUnsettable(eReference.isUnsettable());
		eAttribute.setVolatile(eReference.isVolatile());
		container.getEStructuralFeatures().add(eAttribute);
		return eAttribute;
	}

	/**
	 * test if the Eclass is a definition of a Datatype
	 * 
	 * @param eclass
	 *        that we want to test
	 * @return true if this is the defintion of a Datatype, false other
	 */
	public static boolean isDataTypeDefinition(EClassifier eclass) {
		if(eclass.getEAnnotations().size() > 0) {
			EAnnotation eAnnotation = eclass.getEAnnotations().get(0);
			if(eAnnotation.getReferences().size() > 0) {
				org.eclipse.uml2.uml.Classifier theClassifier = (org.eclipse.uml2.uml.Classifier)eAnnotation
						.getReferences().get(0);
				if(theClassifier instanceof DataType && !(theClassifier instanceof Enumeration)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * this method is used to obtain the classifier from its definition
	 * 
	 * @param eclass
	 *        that is a definition
	 * @return the classifier that produce this definition
	 */
	public static Classifier getUMLClassifierFromDefinition(EClassifier eclass) {
		if(eclass.getEAnnotations().size() > 0) {
			EAnnotation eAnnotation = eclass.getEAnnotations().get(0);
			if(eAnnotation.getReferences().size() > 0) {
				org.eclipse.uml2.uml.Classifier theClassifier = (org.eclipse.uml2.uml.Classifier)eAnnotation
						.getReferences().get(0);
				return theClassifier;
			}
		}
		return null;
	}
}
