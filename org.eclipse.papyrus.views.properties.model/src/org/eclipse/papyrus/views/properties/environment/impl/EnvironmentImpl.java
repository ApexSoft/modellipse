/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.papyrus.views.properties.environment.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.papyrus.infra.constraints.environment.impl.ConstraintEnvironmentImpl;
import org.eclipse.papyrus.views.properties.environment.CompositeWidgetType;
import org.eclipse.papyrus.views.properties.environment.Environment;
import org.eclipse.papyrus.views.properties.environment.EnvironmentPackage;
import org.eclipse.papyrus.views.properties.environment.LayoutType;
import org.eclipse.papyrus.views.properties.environment.MiscClass;
import org.eclipse.papyrus.views.properties.environment.ModelElementFactoryDescriptor;
import org.eclipse.papyrus.views.properties.environment.Namespace;
import org.eclipse.papyrus.views.properties.environment.PropertyEditorType;
import org.eclipse.papyrus.views.properties.environment.StandardWidgetType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Environment</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.papyrus.views.properties.environment.impl.EnvironmentImpl#getModelElementFactories <em>Model Element Factories</em>}</li>
 *   <li>{@link org.eclipse.papyrus.views.properties.environment.impl.EnvironmentImpl#getWidgetTypes <em>Widget Types</em>}</li>
 *   <li>{@link org.eclipse.papyrus.views.properties.environment.impl.EnvironmentImpl#getPropertyEditorTypes <em>Property Editor Types</em>}</li>
 *   <li>{@link org.eclipse.papyrus.views.properties.environment.impl.EnvironmentImpl#getCompositeWidgetTypes <em>Composite Widget Types</em>}</li>
 *   <li>{@link org.eclipse.papyrus.views.properties.environment.impl.EnvironmentImpl#getLayoutTypes <em>Layout Types</em>}</li>
 *   <li>{@link org.eclipse.papyrus.views.properties.environment.impl.EnvironmentImpl#getNamespaces <em>Namespaces</em>}</li>
 *   <li>{@link org.eclipse.papyrus.views.properties.environment.impl.EnvironmentImpl#getMiscClasses <em>Misc Classes</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class EnvironmentImpl extends ConstraintEnvironmentImpl implements Environment {
	/**
	 * The cached value of the '{@link #getModelElementFactories() <em>Model Element Factories</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getModelElementFactories()
	 * @generated
	 * @ordered
	 */
	protected EList<ModelElementFactoryDescriptor> modelElementFactories;

	/**
	 * The cached value of the '{@link #getWidgetTypes() <em>Widget Types</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWidgetTypes()
	 * @generated
	 * @ordered
	 */
	protected EList<StandardWidgetType> widgetTypes;

	/**
	 * The cached value of the '{@link #getPropertyEditorTypes() <em>Property Editor Types</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPropertyEditorTypes()
	 * @generated
	 * @ordered
	 */
	protected EList<PropertyEditorType> propertyEditorTypes;

	/**
	 * The cached value of the '{@link #getCompositeWidgetTypes() <em>Composite Widget Types</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCompositeWidgetTypes()
	 * @generated
	 * @ordered
	 */
	protected EList<CompositeWidgetType> compositeWidgetTypes;

	/**
	 * The cached value of the '{@link #getLayoutTypes() <em>Layout Types</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLayoutTypes()
	 * @generated
	 * @ordered
	 */
	protected EList<LayoutType> layoutTypes;

	/**
	 * The cached value of the '{@link #getNamespaces() <em>Namespaces</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNamespaces()
	 * @generated
	 * @ordered
	 */
	protected EList<Namespace> namespaces;

	/**
	 * The cached value of the '{@link #getMiscClasses() <em>Misc Classes</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMiscClasses()
	 * @generated
	 * @ordered
	 */
	protected EList<MiscClass> miscClasses;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EnvironmentImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return EnvironmentPackage.Literals.ENVIRONMENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ModelElementFactoryDescriptor> getModelElementFactories() {
		if (modelElementFactories == null) {
			modelElementFactories = new EObjectContainmentEList<ModelElementFactoryDescriptor>(ModelElementFactoryDescriptor.class, this, EnvironmentPackage.ENVIRONMENT__MODEL_ELEMENT_FACTORIES);
		}
		return modelElementFactories;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<StandardWidgetType> getWidgetTypes() {
		if (widgetTypes == null) {
			widgetTypes = new EObjectContainmentEList<StandardWidgetType>(StandardWidgetType.class, this, EnvironmentPackage.ENVIRONMENT__WIDGET_TYPES);
		}
		return widgetTypes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<PropertyEditorType> getPropertyEditorTypes() {
		if (propertyEditorTypes == null) {
			propertyEditorTypes = new EObjectContainmentEList<PropertyEditorType>(PropertyEditorType.class, this, EnvironmentPackage.ENVIRONMENT__PROPERTY_EDITOR_TYPES);
		}
		return propertyEditorTypes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<CompositeWidgetType> getCompositeWidgetTypes() {
		if (compositeWidgetTypes == null) {
			compositeWidgetTypes = new EObjectContainmentEList<CompositeWidgetType>(CompositeWidgetType.class, this, EnvironmentPackage.ENVIRONMENT__COMPOSITE_WIDGET_TYPES);
		}
		return compositeWidgetTypes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<LayoutType> getLayoutTypes() {
		if (layoutTypes == null) {
			layoutTypes = new EObjectContainmentEList<LayoutType>(LayoutType.class, this, EnvironmentPackage.ENVIRONMENT__LAYOUT_TYPES);
		}
		return layoutTypes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Namespace> getNamespaces() {
		if (namespaces == null) {
			namespaces = new EObjectContainmentEList<Namespace>(Namespace.class, this, EnvironmentPackage.ENVIRONMENT__NAMESPACES);
		}
		return namespaces;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<MiscClass> getMiscClasses() {
		if (miscClasses == null) {
			miscClasses = new EObjectContainmentEList<MiscClass>(MiscClass.class, this, EnvironmentPackage.ENVIRONMENT__MISC_CLASSES);
		}
		return miscClasses;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case EnvironmentPackage.ENVIRONMENT__MODEL_ELEMENT_FACTORIES:
				return ((InternalEList<?>)getModelElementFactories()).basicRemove(otherEnd, msgs);
			case EnvironmentPackage.ENVIRONMENT__WIDGET_TYPES:
				return ((InternalEList<?>)getWidgetTypes()).basicRemove(otherEnd, msgs);
			case EnvironmentPackage.ENVIRONMENT__PROPERTY_EDITOR_TYPES:
				return ((InternalEList<?>)getPropertyEditorTypes()).basicRemove(otherEnd, msgs);
			case EnvironmentPackage.ENVIRONMENT__COMPOSITE_WIDGET_TYPES:
				return ((InternalEList<?>)getCompositeWidgetTypes()).basicRemove(otherEnd, msgs);
			case EnvironmentPackage.ENVIRONMENT__LAYOUT_TYPES:
				return ((InternalEList<?>)getLayoutTypes()).basicRemove(otherEnd, msgs);
			case EnvironmentPackage.ENVIRONMENT__NAMESPACES:
				return ((InternalEList<?>)getNamespaces()).basicRemove(otherEnd, msgs);
			case EnvironmentPackage.ENVIRONMENT__MISC_CLASSES:
				return ((InternalEList<?>)getMiscClasses()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case EnvironmentPackage.ENVIRONMENT__MODEL_ELEMENT_FACTORIES:
				return getModelElementFactories();
			case EnvironmentPackage.ENVIRONMENT__WIDGET_TYPES:
				return getWidgetTypes();
			case EnvironmentPackage.ENVIRONMENT__PROPERTY_EDITOR_TYPES:
				return getPropertyEditorTypes();
			case EnvironmentPackage.ENVIRONMENT__COMPOSITE_WIDGET_TYPES:
				return getCompositeWidgetTypes();
			case EnvironmentPackage.ENVIRONMENT__LAYOUT_TYPES:
				return getLayoutTypes();
			case EnvironmentPackage.ENVIRONMENT__NAMESPACES:
				return getNamespaces();
			case EnvironmentPackage.ENVIRONMENT__MISC_CLASSES:
				return getMiscClasses();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case EnvironmentPackage.ENVIRONMENT__MODEL_ELEMENT_FACTORIES:
				getModelElementFactories().clear();
				getModelElementFactories().addAll((Collection<? extends ModelElementFactoryDescriptor>)newValue);
				return;
			case EnvironmentPackage.ENVIRONMENT__WIDGET_TYPES:
				getWidgetTypes().clear();
				getWidgetTypes().addAll((Collection<? extends StandardWidgetType>)newValue);
				return;
			case EnvironmentPackage.ENVIRONMENT__PROPERTY_EDITOR_TYPES:
				getPropertyEditorTypes().clear();
				getPropertyEditorTypes().addAll((Collection<? extends PropertyEditorType>)newValue);
				return;
			case EnvironmentPackage.ENVIRONMENT__COMPOSITE_WIDGET_TYPES:
				getCompositeWidgetTypes().clear();
				getCompositeWidgetTypes().addAll((Collection<? extends CompositeWidgetType>)newValue);
				return;
			case EnvironmentPackage.ENVIRONMENT__LAYOUT_TYPES:
				getLayoutTypes().clear();
				getLayoutTypes().addAll((Collection<? extends LayoutType>)newValue);
				return;
			case EnvironmentPackage.ENVIRONMENT__NAMESPACES:
				getNamespaces().clear();
				getNamespaces().addAll((Collection<? extends Namespace>)newValue);
				return;
			case EnvironmentPackage.ENVIRONMENT__MISC_CLASSES:
				getMiscClasses().clear();
				getMiscClasses().addAll((Collection<? extends MiscClass>)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case EnvironmentPackage.ENVIRONMENT__MODEL_ELEMENT_FACTORIES:
				getModelElementFactories().clear();
				return;
			case EnvironmentPackage.ENVIRONMENT__WIDGET_TYPES:
				getWidgetTypes().clear();
				return;
			case EnvironmentPackage.ENVIRONMENT__PROPERTY_EDITOR_TYPES:
				getPropertyEditorTypes().clear();
				return;
			case EnvironmentPackage.ENVIRONMENT__COMPOSITE_WIDGET_TYPES:
				getCompositeWidgetTypes().clear();
				return;
			case EnvironmentPackage.ENVIRONMENT__LAYOUT_TYPES:
				getLayoutTypes().clear();
				return;
			case EnvironmentPackage.ENVIRONMENT__NAMESPACES:
				getNamespaces().clear();
				return;
			case EnvironmentPackage.ENVIRONMENT__MISC_CLASSES:
				getMiscClasses().clear();
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case EnvironmentPackage.ENVIRONMENT__MODEL_ELEMENT_FACTORIES:
				return modelElementFactories != null && !modelElementFactories.isEmpty();
			case EnvironmentPackage.ENVIRONMENT__WIDGET_TYPES:
				return widgetTypes != null && !widgetTypes.isEmpty();
			case EnvironmentPackage.ENVIRONMENT__PROPERTY_EDITOR_TYPES:
				return propertyEditorTypes != null && !propertyEditorTypes.isEmpty();
			case EnvironmentPackage.ENVIRONMENT__COMPOSITE_WIDGET_TYPES:
				return compositeWidgetTypes != null && !compositeWidgetTypes.isEmpty();
			case EnvironmentPackage.ENVIRONMENT__LAYOUT_TYPES:
				return layoutTypes != null && !layoutTypes.isEmpty();
			case EnvironmentPackage.ENVIRONMENT__NAMESPACES:
				return namespaces != null && !namespaces.isEmpty();
			case EnvironmentPackage.ENVIRONMENT__MISC_CLASSES:
				return miscClasses != null && !miscClasses.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //EnvironmentImpl
