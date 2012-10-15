/*******************************************************************************
 * Copyright (c) 2008 Conselleria de Infraestructuras y Transporte,
 * Generalitat de la Comunitat Valenciana .
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors: Francisco Javier Cano MuÃ±oz (Prodevelop) - initial API implementation
 *
 ******************************************************************************/

package org.eclipse.papyrus.uml.diagram.common.commands;

import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gmf.runtime.emf.type.core.EditHelperContext;
import org.eclipse.gmf.runtime.emf.type.core.IClientContext;
import org.eclipse.gmf.runtime.emf.type.core.requests.AbstractEditCommandRequest;

// TODO: Auto-generated Javadoc
/**
 * Request to remove the value of a collection structural feature in a model element.
 * 
 * @author <a href="mailto:fjcano@prodevelop.es">Francisco Javier Cano Muñoz</a>
 */
public class RemoveValueRequest extends AbstractEditCommandRequest {

	/** The structural feature whose value will be removed. */
	private final EStructuralFeature feature;

	/** The owner of the structural feature. */
	private final EObject elementToEdit;

	/** The value of the structural feature to remove. */
	private final Object value;

	/**
	 * Constructs a new request to remove the value of a structural feature in a
	 * model element.
	 * 
	 * @param editingDomain
	 *        the editing domain in which I am requesting to make model
	 * @param elementToEdit
	 *        the owner of the structural feature
	 * @param feature
	 *        the structural feature whose value is to be removed
	 * @param value
	 *        the value to remove
	 */
	public RemoveValueRequest(TransactionalEditingDomain editingDomain, EObject elementToEdit, EStructuralFeature feature, Object value) {

		super(editingDomain);
		this.elementToEdit = elementToEdit;
		this.feature = feature;
		this.value = value;
	}

	/**
	 * Constructs a new request to remove the value of a structural feature in a
	 * model element. The editing domain will be derived from the <code>elementToEdit</code>.
	 * 
	 * @param elementToEdit
	 *        the owner of the structural feature
	 * @param feature
	 *        the structural feature whose value is to be removed
	 * @param value
	 *        the value to remove
	 */
	// @unused
	public RemoveValueRequest(EObject elementToEdit, EStructuralFeature feature, Object value) {

		this(TransactionUtil.getEditingDomain(elementToEdit), elementToEdit, feature, value);
	}

	/**
	 * Gets the structural feature.
	 * 
	 * @return the structural feature
	 */
	public EStructuralFeature getFeature() {
		return feature;
	}

	/**
	 * Gets the value.
	 * 
	 * @return the value
	 */
	public Object getValue() {
		return value;
	}

	/**
	 * Gets the owner of the structural feature.
	 * 
	 * @return the owner of the structural feature
	 */
	public EObject getElementToEdit() {
		return elementToEdit;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.gmf.runtime.emf.type.core.edithelper.IEditCommandRequest#
	 * getElementsToEdit()
	 */
	@Override
	public List getElementsToEdit() {
		if(elementToEdit != null) {
			return Collections.singletonList(elementToEdit);
		}

		return Collections.EMPTY_LIST;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.gmf.runtime.emf.type.core.edithelper.IEditCommandRequest#
	 * getEditHelperContext()
	 */
	public Object getEditHelperContext() {
		IClientContext context = getClientContext();

		if(context == null) {
			return elementToEdit;
		} else {
			return new EditHelperContext(elementToEdit, context);
		}
	}

}
