/*****************************************************************************
 * Copyright (c) 2010 Atos Origin.
 *
 *    
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Atos Origin - Initial API and implementation
 *
 *****************************************************************************/
package org.eclipse.papyrus.uml.diagram.activity.parser.custom;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.gmf.runtime.emf.ui.services.parser.ISemanticParser;
import org.eclipse.papyrus.uml.diagram.activity.parsers.MessageFormatParser;
import org.eclipse.uml2.uml.CentralBufferNode;
import org.eclipse.uml2.uml.DataStoreNode;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.ObjectNode;
import org.eclipse.uml2.uml.State;
import org.eclipse.uml2.uml.UMLPackage;

/**
 * The Class ObjectNodeParser. This parser handle labels for Object Nodes
 */
public class ObjectNodeParser extends MessageFormatParser implements ISemanticParser {

	/** The String to display in front of a Central Buffer Node */
	private static final String CENTRAL_BUFFER = "<<centralBuffer>>".concat(System.getProperty("line.separator"));

	/** The String to display in front of a Data Store */
	private static final String DATASTORE_PREFIX = "<<datastore>>".concat(System.getProperty("line.separator"));

	/**
	 * The String format for displaying an ActivityParameterNodeParser with no
	 * type
	 */
	private static final String UNTYPED_PARAMETER_FORMAT = "%s";

	/**
	 * The String format for displaying an ActivityParameterNodeParser with its
	 * type
	 */
	private static final String TYPED_PARAMETER_FORMAT = "%s: %s";

	/**
	 * The String format for displaying an ActivityParameterNodeParser with in
	 * State property
	 */
	private static final String STATE_FORMAT = System.getProperty("line.separator").concat("[%s]");

	/** The String for separating states */
	private static final String STATE_SEPARATOR = ", ";

	public ObjectNodeParser() {
		super(new EAttribute[]{ UMLPackage.eINSTANCE.getNamedElement_Name() });
	}

	public ObjectNodeParser(EAttribute[] features) {
		super(features);
	}

	public ObjectNodeParser(EAttribute[] features, EAttribute[] editableFeatures) {
		super(features, editableFeatures);
	}

	/**
	 * Gets the e structural feature.
	 * 
	 * @param notification
	 * @return the structural feature
	 */
	protected EStructuralFeature getEStructuralFeature(Object notification) {
		EStructuralFeature featureImpl = null;
		if(notification instanceof Notification) {
			Object feature = ((Notification)notification).getFeature();
			if(feature instanceof EStructuralFeature) {
				featureImpl = (EStructuralFeature)feature;
			}
		}
		return featureImpl;
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean isAffectingEvent(Object event, int flags) {
		EStructuralFeature feature = getEStructuralFeature(event);
		return isValidFeature(feature);
	}

	/**
	 * {@inheritDoc}
	 */
	public String getPrintString(IAdaptable element, int flags) {
		StringBuffer result = new StringBuffer();
		Object adapter = element.getAdapter(EObject.class);
		if(adapter instanceof CentralBufferNode) {
			if(adapter instanceof DataStoreNode) {
				result.append(DATASTORE_PREFIX);
			} else {
				result.append(CENTRAL_BUFFER);
			}
		}
		if(adapter instanceof ObjectNode) {
			ObjectNode objectNode = (ObjectNode)adapter;
			String name = objectNode.getName();
			// manage type
			if(objectNode.getType() != null) {
				String type = objectNode.getType().getName();
				result.append(String.format(TYPED_PARAMETER_FORMAT, name, type));
			} else {
				result.append(String.format(UNTYPED_PARAMETER_FORMAT, name));
			}
			// manage states
			StringBuffer stateLabel = new StringBuffer();
			for(State state : objectNode.getInStates()) {
				String stateName = state.getName();
				if(stateName == null) {
					stateName = "";
				}
				if(!"".equals(stateName)) {
					if(stateLabel.length() > 0) {
						stateLabel.append(STATE_SEPARATOR);
					}
					stateLabel.append(stateName);
				}
			}
			if(stateLabel.length() > 0) {
				result.append(String.format(STATE_FORMAT, stateLabel.toString()));
			}
		}
		return result.toString();
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean areSemanticElementsAffected(EObject listener, Object notification) {
		EStructuralFeature feature = getEStructuralFeature(notification);
		return isValidFeature(feature);
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public List getSemanticElementsBeingParsed(EObject element) {
		List<Element> semanticElementsBeingParsed = new ArrayList<Element>();
		ObjectNode objectNode = null;
		if(element instanceof ObjectNode) {
			objectNode = (ObjectNode)element;
			semanticElementsBeingParsed.add(objectNode);
			if(objectNode.getType() != null) {
				semanticElementsBeingParsed.add(objectNode.getType());
			}
			if(objectNode.getInStates() != null && !objectNode.getInStates().isEmpty()) {
				semanticElementsBeingParsed.addAll(objectNode.getInStates());
			}
		}
		return semanticElementsBeingParsed;
	}

	/**
	 * Determines if the given feature has to be taken into account in this
	 * parser
	 * 
	 * @param feature
	 *        the feature to test
	 * @return true if is valid, false otherwise
	 */
	private boolean isValidFeature(EStructuralFeature feature) {
		return UMLPackage.eINSTANCE.getNamedElement_Name().equals(feature) || UMLPackage.eINSTANCE.getTypedElement_Type().equals(feature) || UMLPackage.eINSTANCE.getObjectNode_InState().equals(feature);
	}
}
