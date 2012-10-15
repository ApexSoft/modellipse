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
 *  CEA LIST - Initial API and implementation
 *
 *****************************************************************************/
package org.eclipse.papyrus.uml.textedit.property.xtext.umlProperty.util;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.Switch;

import org.eclipse.papyrus.uml.textedit.property.xtext.umlProperty.*;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see org.eclipse.papyrus.uml.textedit.property.xtext.umlProperty.UmlPropertyPackage
 * @generated
 */
public class UmlPropertySwitch<T> extends Switch<T>
{
  /**
   * The cached model package
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected static UmlPropertyPackage modelPackage;

  /**
   * Creates an instance of the switch.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public UmlPropertySwitch()
  {
    if (modelPackage == null)
    {
      modelPackage = UmlPropertyPackage.eINSTANCE;
    }
  }

  /**
   * Checks whether this is a switch for the given package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @parameter ePackage the package in question.
   * @return whether this is a switch for the given package.
   * @generated
   */
  @Override
  protected boolean isSwitchFor(EPackage ePackage)
  {
    return ePackage == modelPackage;
  }

  /**
   * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the first non-null result returned by a <code>caseXXX</code> call.
   * @generated
   */
  @Override
  protected T doSwitch(int classifierID, EObject theEObject)
  {
    switch (classifierID)
    {
      case UmlPropertyPackage.PROPERTY_RULE:
      {
        PropertyRule propertyRule = (PropertyRule)theEObject;
        T result = casePropertyRule(propertyRule);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case UmlPropertyPackage.TYPE_RULE:
      {
        TypeRule typeRule = (TypeRule)theEObject;
        T result = caseTypeRule(typeRule);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case UmlPropertyPackage.QUALIFIED_NAME:
      {
        QualifiedName qualifiedName = (QualifiedName)theEObject;
        T result = caseQualifiedName(qualifiedName);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case UmlPropertyPackage.MULTIPLICITY_RULE:
      {
        MultiplicityRule multiplicityRule = (MultiplicityRule)theEObject;
        T result = caseMultiplicityRule(multiplicityRule);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case UmlPropertyPackage.BOUND_SPECIFICATION:
      {
        BoundSpecification boundSpecification = (BoundSpecification)theEObject;
        T result = caseBoundSpecification(boundSpecification);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case UmlPropertyPackage.MODIFIERS_RULE:
      {
        ModifiersRule modifiersRule = (ModifiersRule)theEObject;
        T result = caseModifiersRule(modifiersRule);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case UmlPropertyPackage.MODIFIER_SPECIFICATION:
      {
        ModifierSpecification modifierSpecification = (ModifierSpecification)theEObject;
        T result = caseModifierSpecification(modifierSpecification);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case UmlPropertyPackage.REDEFINES_RULE:
      {
        RedefinesRule redefinesRule = (RedefinesRule)theEObject;
        T result = caseRedefinesRule(redefinesRule);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case UmlPropertyPackage.SUBSETS_RULE:
      {
        SubsetsRule subsetsRule = (SubsetsRule)theEObject;
        T result = caseSubsetsRule(subsetsRule);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case UmlPropertyPackage.DEFAULT_VALUE_RULE:
      {
        DefaultValueRule defaultValueRule = (DefaultValueRule)theEObject;
        T result = caseDefaultValueRule(defaultValueRule);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      default: return defaultCase(theEObject);
    }
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Property Rule</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Property Rule</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casePropertyRule(PropertyRule object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Type Rule</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Type Rule</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseTypeRule(TypeRule object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Qualified Name</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Qualified Name</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseQualifiedName(QualifiedName object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Multiplicity Rule</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Multiplicity Rule</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseMultiplicityRule(MultiplicityRule object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Bound Specification</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Bound Specification</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseBoundSpecification(BoundSpecification object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Modifiers Rule</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Modifiers Rule</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseModifiersRule(ModifiersRule object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Modifier Specification</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Modifier Specification</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseModifierSpecification(ModifierSpecification object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Redefines Rule</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Redefines Rule</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseRedefinesRule(RedefinesRule object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Subsets Rule</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Subsets Rule</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseSubsetsRule(SubsetsRule object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Default Value Rule</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Default Value Rule</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseDefaultValueRule(DefaultValueRule object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch, but this is the last case anyway.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject)
   * @generated
   */
  @Override
  public T defaultCase(EObject object)
  {
    return null;
  }

} //UmlPropertySwitch
