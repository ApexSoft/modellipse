/*****************************************************************************
 * Copyright (c) 2011 CEA LIST.
 *
 *    
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  CEA LIST - Initial API and implementation
 *
 *****************************************************************************/
package org.eclipse.papyrus.uml.alf.alf;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Super Invocation Statement</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.papyrus.uml.alf.alf.SuperInvocationStatement#get_super <em>super</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.papyrus.uml.alf.alf.AlfPackage#getSuperInvocationStatement()
 * @model
 * @generated
 */
public interface SuperInvocationStatement extends Statement
{
  /**
   * Returns the value of the '<em><b>super</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>super</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>super</em>' containment reference.
   * @see #set_super(SuperInvocationExpression)
   * @see org.eclipse.papyrus.uml.alf.alf.AlfPackage#getSuperInvocationStatement__super()
   * @model containment="true"
   * @generated
   */
  SuperInvocationExpression get_super();

  /**
   * Sets the value of the '{@link org.eclipse.papyrus.uml.alf.alf.SuperInvocationStatement#get_super <em>super</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>super</em>' containment reference.
   * @see #get_super()
   * @generated
   */
  void set_super(SuperInvocationExpression value);

} // SuperInvocationStatement
