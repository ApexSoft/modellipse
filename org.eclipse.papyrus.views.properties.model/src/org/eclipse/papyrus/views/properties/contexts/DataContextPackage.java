/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.papyrus.views.properties.contexts;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Data Context Package</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.papyrus.views.properties.contexts.DataContextPackage#getElements <em>Elements</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.papyrus.views.properties.contexts.ContextsPackage#getDataContextPackage()
 * @model
 * @generated
 */
public interface DataContextPackage extends DataContextElement {
	/**
	 * Returns the value of the '<em><b>Elements</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.papyrus.views.properties.contexts.DataContextElement}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.papyrus.views.properties.contexts.DataContextElement#getPackage <em>Package</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Elements</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Elements</em>' containment reference list.
	 * @see org.eclipse.papyrus.views.properties.contexts.ContextsPackage#getDataContextPackage_Elements()
	 * @see org.eclipse.papyrus.views.properties.contexts.DataContextElement#getPackage
	 * @model opposite="package" containment="true"
	 * @generated
	 */
	EList<DataContextElement> getElements();

} // DataContextPackage
