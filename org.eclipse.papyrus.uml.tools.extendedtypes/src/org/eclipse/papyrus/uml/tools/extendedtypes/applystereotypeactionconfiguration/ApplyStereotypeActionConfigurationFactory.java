/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.papyrus.uml.tools.extendedtypes.applystereotypeactionconfiguration;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.papyrus.uml.tools.extendedtypes.applystereotypeactionconfiguration.ApplyStereotypeActionConfigurationPackage
 * @generated
 */
public interface ApplyStereotypeActionConfigurationFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ApplyStereotypeActionConfigurationFactory eINSTANCE = org.eclipse.papyrus.uml.tools.extendedtypes.applystereotypeactionconfiguration.impl.ApplyStereotypeActionConfigurationFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Apply Stereotype Action Configuration</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Apply Stereotype Action Configuration</em>'.
	 * @generated
	 */
	ApplyStereotypeActionConfiguration createApplyStereotypeActionConfiguration();

	/**
	 * Returns a new object of class '<em>Stereotype To Apply</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Stereotype To Apply</em>'.
	 * @generated
	 */
	StereotypeToApply createStereotypeToApply();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	ApplyStereotypeActionConfigurationPackage getApplyStereotypeActionConfigurationPackage();

} //ApplyStereotypeActionConfigurationFactory
