/*****************************************************************************
 * Copyright (c) 2010 CEA LIST.
 *
 *    
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Vincent Lorenzo (CEA LIST) vincent.lorenzo@cea.fr - Initial API and implementation
 *
 *****************************************************************************/
package org.eclipse.papyrus.uml.diagram.component.custom.ui;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.emf.type.core.IHintedType;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.eclipse.uml2.uml.NamedElement;

// TODO: Auto-generated Javadoc
/**
 * This class provides facilities to represent a New Element before its real creation.
 * We can store in this class :
 * <ul>
 * <li>the name of the new element</li>
 * <li>the parent of the new element</li>
 * <li>the {@link IHintedType} of the new element</li>
 * <li>the request to build the new element (optional)</li>
 * <li>the element itself when it has been created</li>
 * </ul>
 * 
 * 
 * 
 */
public class NewElementRepresentation {

	/** the name of the new element. */
	private String name;

	/** the parent of the new element. */
	private Object parent;

	/** the created element itself. */
	private EObject createdElement;

	/** the {@link IHintedType} of the new element. */
	private IHintedType hintedType;

	/** this field is used only when the element have an existing parent. */
	private CreateElementRequest request;

	/**
	 * 
	 * Constructor.
	 * 
	 * @param name
	 *        the name of the new element
	 * @param parent
	 *        the parent of the new element
	 * @param hintedType
	 *        the hinted type of the new element
	 * @param request
	 *        the request to build the new element (can be <code>null</code>)
	 */
	public NewElementRepresentation(String name, Object parent, IHintedType hintedType, CreateElementRequest request) {
		this.name = name;
		this.parent = parent;
		this.createdElement = null;
		this.hintedType = hintedType;
		this.request = request;
	}

	/**
	 * Getter for {@link #hintedType}.
	 *
	 * @return the hinted type
	 * {@link #hintedType}
	 */
	public IHintedType getHintedType() {
		return this.hintedType;
	}

	/**
	 * Getter for {@link #name}.
	 *
	 * @return the name
	 * {@link #name}
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Getter for {@link #parent}.
	 *
	 * @return the parent
	 * {@link #parent}
	 */
	public Object getParent() {
		return this.parent;
	}

	/**
	 * Returns the parent of this element.
	 *
	 * @return the e object parent
	 * the parent of this element or <code>null</code> if the parent is a {@link NewElementRepresentation} itself and if it has not been
	 * created
	 */
	public EObject getEObjectParent() {
		if(parent instanceof EObject) {
			return (EObject)this.parent;
		} else if(parent instanceof NewElementRepresentation) {
			return ((NewElementRepresentation)parent).getEObject();
		}
		return null;
	}

	/**
	 * Returns the created object.
	 *
	 * @return the e object
	 * the created Object or <code>null</code> if the object has not been created
	 */
	public EObject getEObject() {
		if(this.request != null) {
			return this.request.getNewElement();
		}
		return this.createdElement;
	}

	/**
	 * Setter for {@link #createdElement}.
	 *
	 * @param newElement the new creates the element
	 */
	public void setCreateElement(EObject newElement) {
		this.createdElement = newElement;
	}

	/**
	 * Setter for {@link #request}.
	 *
	 * @return the creates the element request
	 * {@link #request}
	 */
	public CreateElementRequest getCreateElementRequest() {
		return this.request;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Name : " + this.name + ", parent : " + this.parent;
	}

	/**
	 * Gets the qualified name.
	 *
	 * @return the qualified name
	 */
	public String getQualifiedName() {
		String qualifiedName = "";
		if(parent instanceof NewElementRepresentation) {
			qualifiedName = ((NewElementRepresentation)parent).getQualifiedName();
		} else if(parent instanceof NamedElement) {
			qualifiedName = ((NamedElement)parent).getQualifiedName() + "::" + this.name;
		}
		return qualifiedName;
	}
}
