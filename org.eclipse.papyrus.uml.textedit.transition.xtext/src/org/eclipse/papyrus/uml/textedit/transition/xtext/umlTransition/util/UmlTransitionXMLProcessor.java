/**
 * <copyright>
 * </copyright>
 *

 */
package org.eclipse.papyrus.uml.textedit.transition.xtext.umlTransition.util;

import java.util.Map;

import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.resource.Resource;

import org.eclipse.emf.ecore.xmi.util.XMLProcessor;

import org.eclipse.papyrus.uml.textedit.transition.xtext.umlTransition.UmlTransitionPackage;

/**
 * This class contains helper methods to serialize and deserialize XML documents
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class UmlTransitionXMLProcessor extends XMLProcessor
{

  /**
   * Public constructor to instantiate the helper.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public UmlTransitionXMLProcessor()
  {
    super((EPackage.Registry.INSTANCE));
    UmlTransitionPackage.eINSTANCE.eClass();
  }
  
  /**
   * Register for "*" and "xml" file extensions the UmlTransitionResourceFactoryImpl factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected Map<String, Resource.Factory> getRegistrations()
  {
    if (registrations == null)
    {
      super.getRegistrations();
      registrations.put(XML_EXTENSION, new UmlTransitionResourceFactoryImpl());
      registrations.put(STAR_EXTENSION, new UmlTransitionResourceFactoryImpl());
    }
    return registrations;
  }

} //UmlTransitionXMLProcessor
