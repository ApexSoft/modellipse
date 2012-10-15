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
 *  Patrick Tessier (CEA LIST) Patrick.tessier@cea.fr - Initial API and implementation
 */
package org.eclipse.papyrus.uml.diagram.clazz.providers;

import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.gmf.runtime.emf.type.core.ElementTypeRegistry;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.papyrus.uml.diagram.clazz.edit.parts.AbstractionEditPart;
import org.eclipse.papyrus.uml.diagram.clazz.edit.parts.AssociationBranchEditPart;
import org.eclipse.papyrus.uml.diagram.clazz.edit.parts.AssociationClassDashedLinkEditPart;
import org.eclipse.papyrus.uml.diagram.clazz.edit.parts.AssociationClassEditPart;
import org.eclipse.papyrus.uml.diagram.clazz.edit.parts.AssociationClassLinkEditPart;
import org.eclipse.papyrus.uml.diagram.clazz.edit.parts.AssociationEditPart;
import org.eclipse.papyrus.uml.diagram.clazz.edit.parts.AssociationNodeEditPart;
import org.eclipse.papyrus.uml.diagram.clazz.edit.parts.ClassEditPart;
import org.eclipse.papyrus.uml.diagram.clazz.edit.parts.ClassEditPartCN;
import org.eclipse.papyrus.uml.diagram.clazz.edit.parts.ClassifierTemplateParameterEditPart;
import org.eclipse.papyrus.uml.diagram.clazz.edit.parts.CommentAnnotatedElementEditPart;
import org.eclipse.papyrus.uml.diagram.clazz.edit.parts.CommentEditPart;
import org.eclipse.papyrus.uml.diagram.clazz.edit.parts.CommentEditPartCN;
import org.eclipse.papyrus.uml.diagram.clazz.edit.parts.ComponentEditPart;
import org.eclipse.papyrus.uml.diagram.clazz.edit.parts.ComponentEditPartCN;
import org.eclipse.papyrus.uml.diagram.clazz.edit.parts.ConnectableElementTemplateParameterEditPart;
import org.eclipse.papyrus.uml.diagram.clazz.edit.parts.ConnectorDurationObservationEditPart;
import org.eclipse.papyrus.uml.diagram.clazz.edit.parts.ConnectorTimeObservationEditPart;
import org.eclipse.papyrus.uml.diagram.clazz.edit.parts.ConstraintConstrainedElementEditPart;
import org.eclipse.papyrus.uml.diagram.clazz.edit.parts.ConstraintEditPart;
import org.eclipse.papyrus.uml.diagram.clazz.edit.parts.ConstraintEditPartCN;
import org.eclipse.papyrus.uml.diagram.clazz.edit.parts.ContainmentCircleEditPart;
import org.eclipse.papyrus.uml.diagram.clazz.edit.parts.ContainmentLinkEditPart;
import org.eclipse.papyrus.uml.diagram.clazz.edit.parts.ContainmentSubLinkEditPart;
import org.eclipse.papyrus.uml.diagram.clazz.edit.parts.DataTypeEditPart;
import org.eclipse.papyrus.uml.diagram.clazz.edit.parts.DataTypeEditPartCN;
import org.eclipse.papyrus.uml.diagram.clazz.edit.parts.DefaultNamedElementEditPart;
import org.eclipse.papyrus.uml.diagram.clazz.edit.parts.DependencyBranchEditPart;
import org.eclipse.papyrus.uml.diagram.clazz.edit.parts.DependencyEditPart;
import org.eclipse.papyrus.uml.diagram.clazz.edit.parts.DependencyNodeEditPart;
import org.eclipse.papyrus.uml.diagram.clazz.edit.parts.DurationObservationEditPart;
import org.eclipse.papyrus.uml.diagram.clazz.edit.parts.ElementImportEditPart;
import org.eclipse.papyrus.uml.diagram.clazz.edit.parts.EnumerationEditPart;
import org.eclipse.papyrus.uml.diagram.clazz.edit.parts.EnumerationEditPartCN;
import org.eclipse.papyrus.uml.diagram.clazz.edit.parts.EnumerationLiteralEditPart;
import org.eclipse.papyrus.uml.diagram.clazz.edit.parts.GeneralizationEditPart;
import org.eclipse.papyrus.uml.diagram.clazz.edit.parts.GeneralizationSetEditPart;
import org.eclipse.papyrus.uml.diagram.clazz.edit.parts.InformationFlowEditPart;
import org.eclipse.papyrus.uml.diagram.clazz.edit.parts.InformationItemEditPart;
import org.eclipse.papyrus.uml.diagram.clazz.edit.parts.InformationItemEditPartCN;
import org.eclipse.papyrus.uml.diagram.clazz.edit.parts.InstanceSpecificationEditPart;
import org.eclipse.papyrus.uml.diagram.clazz.edit.parts.InstanceSpecificationEditPartCN;
import org.eclipse.papyrus.uml.diagram.clazz.edit.parts.InstanceSpecificationLinkEditPart;
import org.eclipse.papyrus.uml.diagram.clazz.edit.parts.InterfaceEditPart;
import org.eclipse.papyrus.uml.diagram.clazz.edit.parts.InterfaceEditPartCN;
import org.eclipse.papyrus.uml.diagram.clazz.edit.parts.InterfaceRealizationEditPart;
import org.eclipse.papyrus.uml.diagram.clazz.edit.parts.ModelEditPart;
import org.eclipse.papyrus.uml.diagram.clazz.edit.parts.ModelEditPartCN;
import org.eclipse.papyrus.uml.diagram.clazz.edit.parts.ModelEditPartTN;
import org.eclipse.papyrus.uml.diagram.clazz.edit.parts.NestedClassForClassEditPart;
import org.eclipse.papyrus.uml.diagram.clazz.edit.parts.NestedClassForComponentEditPart;
import org.eclipse.papyrus.uml.diagram.clazz.edit.parts.NestedClassForInterfaceEditPart;
import org.eclipse.papyrus.uml.diagram.clazz.edit.parts.NestedInterfaceForClassEditPart;
import org.eclipse.papyrus.uml.diagram.clazz.edit.parts.NestedInterfaceForComponentEditPart;
import org.eclipse.papyrus.uml.diagram.clazz.edit.parts.NestedInterfaceForInterfaceEditPart;
import org.eclipse.papyrus.uml.diagram.clazz.edit.parts.OperationForClassEditPart;
import org.eclipse.papyrus.uml.diagram.clazz.edit.parts.OperationForComponentEditPart;
import org.eclipse.papyrus.uml.diagram.clazz.edit.parts.OperationForDataTypeEditPart;
import org.eclipse.papyrus.uml.diagram.clazz.edit.parts.OperationForInterfaceEditpart;
import org.eclipse.papyrus.uml.diagram.clazz.edit.parts.OperationTemplateParameterEditPart;
import org.eclipse.papyrus.uml.diagram.clazz.edit.parts.PackageEditPart;
import org.eclipse.papyrus.uml.diagram.clazz.edit.parts.PackageEditPartCN;
import org.eclipse.papyrus.uml.diagram.clazz.edit.parts.PackageImportEditPart;
import org.eclipse.papyrus.uml.diagram.clazz.edit.parts.PackageMergeEditPart;
import org.eclipse.papyrus.uml.diagram.clazz.edit.parts.PrimitiveTypeEditPart;
import org.eclipse.papyrus.uml.diagram.clazz.edit.parts.PrimitiveTypeEditPartCN;
import org.eclipse.papyrus.uml.diagram.clazz.edit.parts.ProfileApplicationEditPart;
import org.eclipse.papyrus.uml.diagram.clazz.edit.parts.PropertyForClassEditPart;
import org.eclipse.papyrus.uml.diagram.clazz.edit.parts.PropertyForComponentEditPart;
import org.eclipse.papyrus.uml.diagram.clazz.edit.parts.PropertyForInterfaceEditPart;
import org.eclipse.papyrus.uml.diagram.clazz.edit.parts.PropertyForSignalEditPart;
import org.eclipse.papyrus.uml.diagram.clazz.edit.parts.PropertyforDataTypeEditPart;
import org.eclipse.papyrus.uml.diagram.clazz.edit.parts.RealizationEditPart;
import org.eclipse.papyrus.uml.diagram.clazz.edit.parts.ReceptionEditPart;
import org.eclipse.papyrus.uml.diagram.clazz.edit.parts.ReceptionInInterfaceEditPart;
import org.eclipse.papyrus.uml.diagram.clazz.edit.parts.RedefinableTemplateSignatureEditPart;
import org.eclipse.papyrus.uml.diagram.clazz.edit.parts.ShapeNamedElementEditPart;
import org.eclipse.papyrus.uml.diagram.clazz.edit.parts.ShortCutDiagramEditPart;
import org.eclipse.papyrus.uml.diagram.clazz.edit.parts.SignalEditPart;
import org.eclipse.papyrus.uml.diagram.clazz.edit.parts.SignalEditPartCN;
import org.eclipse.papyrus.uml.diagram.clazz.edit.parts.SlotEditPart;
import org.eclipse.papyrus.uml.diagram.clazz.edit.parts.SubstitutionEditPart;
import org.eclipse.papyrus.uml.diagram.clazz.edit.parts.TemplateBindingEditPart;
import org.eclipse.papyrus.uml.diagram.clazz.edit.parts.TemplateParameterEditPart;
import org.eclipse.papyrus.uml.diagram.clazz.edit.parts.TemplateSignatureEditPart;
import org.eclipse.papyrus.uml.diagram.clazz.edit.parts.TimeObservationEditPart;
import org.eclipse.papyrus.uml.diagram.clazz.edit.parts.UsageEditPart;
import org.eclipse.papyrus.uml.diagram.clazz.part.UMLDiagramEditorPlugin;
import org.eclipse.swt.graphics.Image;
import org.eclipse.uml2.uml.UMLPackage;

/**
 * @generated
 */
public class UMLElementTypes {

	/**
	 * @generated
	 */
	private UMLElementTypes() {
	}

	/**
	 * @generated
	 */
	private static Map<IElementType, ENamedElement> elements;

	/**
	 * @generated
	 */
	private static ImageRegistry imageRegistry;

	/**
	 * @generated
	 */
	private static Set<IElementType> KNOWN_ELEMENT_TYPES;

	/**
	 * @generated
	 */
	public static final IElementType Package_1000 = getElementType("org.eclipse.papyrus.uml.diagram.clazz.Package_1000"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType Dependency_2014 = getElementType("org.eclipse.papyrus.uml.diagram.clazz.Dependency_2014"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType AssociationClass_2013 = getElementType("org.eclipse.papyrus.uml.diagram.clazz.AssociationClass_2013"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType Association_2015 = getElementType("org.eclipse.papyrus.uml.diagram.clazz.Association_2015"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType InstanceSpecification_2001 = getElementType("org.eclipse.papyrus.uml.diagram.clazz.InstanceSpecification_2001"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType Component_2002 = getElementType("org.eclipse.papyrus.uml.diagram.clazz.Component_2002"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType Signal_2003 = getElementType("org.eclipse.papyrus.uml.diagram.clazz.Signal_2003"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType Interface_2004 = getElementType("org.eclipse.papyrus.uml.diagram.clazz.Interface_2004"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType Model_2005 = getElementType("org.eclipse.papyrus.uml.diagram.clazz.Model_2005"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType Enumeration_2006 = getElementType("org.eclipse.papyrus.uml.diagram.clazz.Enumeration_2006"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType Package_2007 = getElementType("org.eclipse.papyrus.uml.diagram.clazz.Package_2007"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType InformationItem_2099 = getElementType("org.eclipse.papyrus.uml.diagram.clazz.InformationItem_2099"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType Class_2008 = getElementType("org.eclipse.papyrus.uml.diagram.clazz.Class_2008"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType PrimitiveType_2009 = getElementType("org.eclipse.papyrus.uml.diagram.clazz.PrimitiveType_2009"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType DataType_2010 = getElementType("org.eclipse.papyrus.uml.diagram.clazz.DataType_2010"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType Constraint_2011 = getElementType("org.eclipse.papyrus.uml.diagram.clazz.Constraint_2011"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType Comment_2012 = getElementType("org.eclipse.papyrus.uml.diagram.clazz.Comment_2012"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType Diagram_2016 = getElementType("org.eclipse.papyrus.uml.diagram.clazz.Diagram_2016"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType DurationObservation_2095 = getElementType("org.eclipse.papyrus.uml.diagram.clazz.DurationObservation_2095"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType TimeObservation_2096 = getElementType("org.eclipse.papyrus.uml.diagram.clazz.TimeObservation_2096"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType NamedElement_2097 = getElementType("org.eclipse.papyrus.uml.diagram.clazz.NamedElement_2097"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType NamedElement_2098 = getElementType("org.eclipse.papyrus.uml.diagram.clazz.NamedElement_2098"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType Property_3012 = getElementType("org.eclipse.papyrus.uml.diagram.clazz.Property_3012"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType Property_3002 = getElementType("org.eclipse.papyrus.uml.diagram.clazz.Property_3002"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType Property_3005 = getElementType("org.eclipse.papyrus.uml.diagram.clazz.Property_3005"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType Property_3006 = getElementType("org.eclipse.papyrus.uml.diagram.clazz.Property_3006"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType Property_3018 = getElementType("org.eclipse.papyrus.uml.diagram.clazz.Property_3018"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType Class_3014 = getElementType("org.eclipse.papyrus.uml.diagram.clazz.Class_3014"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType Class_3004 = getElementType("org.eclipse.papyrus.uml.diagram.clazz.Class_3004"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType Class_3008 = getElementType("org.eclipse.papyrus.uml.diagram.clazz.Class_3008"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType Operation_3013 = getElementType("org.eclipse.papyrus.uml.diagram.clazz.Operation_3013"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType Operation_3003 = getElementType("org.eclipse.papyrus.uml.diagram.clazz.Operation_3003"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType Operation_3007 = getElementType("org.eclipse.papyrus.uml.diagram.clazz.Operation_3007"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType Operation_3019 = getElementType("org.eclipse.papyrus.uml.diagram.clazz.Operation_3019"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType ConnectableElementTemplateParameter_3034 = getElementType("org.eclipse.papyrus.uml.diagram.clazz.ConnectableElementTemplateParameter_3034"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType OperationTemplateParameter_3035 = getElementType("org.eclipse.papyrus.uml.diagram.clazz.OperationTemplateParameter_3035"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType ClassifierTemplateParameter_3031 = getElementType("org.eclipse.papyrus.uml.diagram.clazz.ClassifierTemplateParameter_3031"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType TemplateParameter_3016 = getElementType("org.eclipse.papyrus.uml.diagram.clazz.TemplateParameter_3016"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType EnumerationLiteral_3017 = getElementType("org.eclipse.papyrus.uml.diagram.clazz.EnumerationLiteral_3017"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType Reception_3011 = getElementType("org.eclipse.papyrus.uml.diagram.clazz.Reception_3011"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType Reception_3039 = getElementType("org.eclipse.papyrus.uml.diagram.clazz.Reception_3039"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType Slot_3030 = getElementType("org.eclipse.papyrus.uml.diagram.clazz.Slot_3030"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType RedefinableTemplateSignature_3015 = getElementType("org.eclipse.papyrus.uml.diagram.clazz.RedefinableTemplateSignature_3015"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType Node_3032 = getElementType("org.eclipse.papyrus.uml.diagram.clazz.ContainmentCircle_3032"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType TemplateSignature_3033 = getElementType("org.eclipse.papyrus.uml.diagram.clazz.TemplateSignature_3033"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType InstanceSpecification_3020 = getElementType("org.eclipse.papyrus.uml.diagram.clazz.InstanceSpecification_3020"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType Component_3021 = getElementType("org.eclipse.papyrus.uml.diagram.clazz.Component_3021"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType Signal_3022 = getElementType("org.eclipse.papyrus.uml.diagram.clazz.Signal_3022"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType Interface_3023 = getElementType("org.eclipse.papyrus.uml.diagram.clazz.Interface_3023"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType Model_3024 = getElementType("org.eclipse.papyrus.uml.diagram.clazz.Model_3024"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType Enumeration_3025 = getElementType("org.eclipse.papyrus.uml.diagram.clazz.Enumeration_3025"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType Package_3009 = getElementType("org.eclipse.papyrus.uml.diagram.clazz.Package_3009"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType InformationItem_3040 = getElementType("org.eclipse.papyrus.uml.diagram.clazz.InformationItem_3040"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType Class_3010 = getElementType("org.eclipse.papyrus.uml.diagram.clazz.Class_3010"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType PrimitiveType_3026 = getElementType("org.eclipse.papyrus.uml.diagram.clazz.PrimitiveType_3026"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType DataType_3027 = getElementType("org.eclipse.papyrus.uml.diagram.clazz.DataType_3027"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType Comment_3028 = getElementType("org.eclipse.papyrus.uml.diagram.clazz.Comment_3028"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType Constraint_3029 = getElementType("org.eclipse.papyrus.uml.diagram.clazz.Constraint_3029"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType Interface_3036 = getElementType("org.eclipse.papyrus.uml.diagram.clazz.Interface_3036"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType Interface_3037 = getElementType("org.eclipse.papyrus.uml.diagram.clazz.Interface_3037"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType Interface_3038 = getElementType("org.eclipse.papyrus.uml.diagram.clazz.Interface_3038"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType Link_4016 = getElementType("org.eclipse.papyrus.uml.diagram.clazz.Link_4016"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType AssociationClass_4017 = getElementType("org.eclipse.papyrus.uml.diagram.clazz.AssociationClass_4017"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType Association_4001 = getElementType("org.eclipse.papyrus.uml.diagram.clazz.Association_4001"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType Association_4019 = getElementType("org.eclipse.papyrus.uml.diagram.clazz.Association_4019"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType Generalization_4002 = getElementType("org.eclipse.papyrus.uml.diagram.clazz.Generalization_4002"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType InterfaceRealization_4003 = getElementType("org.eclipse.papyrus.uml.diagram.clazz.InterfaceRealization_4003"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType Substitution_4004 = getElementType("org.eclipse.papyrus.uml.diagram.clazz.Substitution_4004"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType Realization_4005 = getElementType("org.eclipse.papyrus.uml.diagram.clazz.Realization_4005"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType Abstraction_4006 = getElementType("org.eclipse.papyrus.uml.diagram.clazz.Abstraction_4006"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType Usage_4007 = getElementType("org.eclipse.papyrus.uml.diagram.clazz.Usage_4007"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType Dependency_4008 = getElementType("org.eclipse.papyrus.uml.diagram.clazz.Dependency_4008"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType Dependency_4018 = getElementType("org.eclipse.papyrus.uml.diagram.clazz.Dependency_4018"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType ElementImport_4009 = getElementType("org.eclipse.papyrus.uml.diagram.clazz.ElementImport_4009"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType PackageImport_4010 = getElementType("org.eclipse.papyrus.uml.diagram.clazz.PackageImport_4010"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType PackageMerge_4011 = getElementType("org.eclipse.papyrus.uml.diagram.clazz.PackageMerge_4011"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType ProfileApplication_4012 = getElementType("org.eclipse.papyrus.uml.diagram.clazz.ProfileApplication_4012"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType CommentAnnotatedElement_4013 = getElementType("org.eclipse.papyrus.uml.diagram.clazz.CommentAnnotatedElement_4013"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType ConstraintConstrainedElement_4014 = getElementType("org.eclipse.papyrus.uml.diagram.clazz.ConstraintConstrainedElement_4014"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType TemplateBinding_4015 = getElementType("org.eclipse.papyrus.uml.diagram.clazz.TemplateBinding_4015"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType GeneralizationSet_4020 = getElementType("org.eclipse.papyrus.uml.diagram.clazz.GeneralizationSet_4020"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType InstanceSpecification_4021 = getElementType("org.eclipse.papyrus.uml.diagram.clazz.InstanceSpecificationLink_4021"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType Link_4022 = getElementType("org.eclipse.papyrus.uml.diagram.clazz.AddedLink_4022"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType Link_4023 = getElementType("org.eclipse.papyrus.uml.diagram.clazz.ContainmentLink_4023"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType TimeObservationEvent_4024 = getElementType("org.eclipse.papyrus.uml.diagram.clazz.TimeObservationEvent_4024"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType DurationObservationEvent_4025 = getElementType("org.eclipse.papyrus.uml.diagram.clazz.DurationObservationEvent_4025"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType InformationFlow_4026 = getElementType("org.eclipse.papyrus.uml.diagram.clazz.InformationFlow_4026"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	private static ImageRegistry getImageRegistry() {
		if(imageRegistry == null) {
			imageRegistry = new ImageRegistry();
		}
		return imageRegistry;
	}

	/**
	 * @generated
	 */
	private static String getImageRegistryKey(ENamedElement element) {
		return element.getName();
	}

	/**
	 * @generated
	 */
	private static ImageDescriptor getProvidedImageDescriptor(ENamedElement element) {
		if(element instanceof EStructuralFeature) {
			EStructuralFeature feature = ((EStructuralFeature)element);
			EClass eContainingClass = feature.getEContainingClass();
			EClassifier eType = feature.getEType();
			if(eContainingClass != null && !eContainingClass.isAbstract()) {
				element = eContainingClass;
			} else if(eType instanceof EClass && !((EClass)eType).isAbstract()) {
				element = eType;
			}
		}
		if(element instanceof EClass) {
			EClass eClass = (EClass)element;
			if(!eClass.isAbstract()) {
				return UMLDiagramEditorPlugin.getInstance().getItemImageDescriptor(eClass.getEPackage().getEFactoryInstance().create(eClass));
			}
		}
		// TODO : support structural features
		return null;
	}

	/**
	 * @generated
	 */
	public static ImageDescriptor getImageDescriptor(ENamedElement element) {
		String key = getImageRegistryKey(element);
		ImageDescriptor imageDescriptor = getImageRegistry().getDescriptor(key);
		if(imageDescriptor == null) {
			imageDescriptor = getProvidedImageDescriptor(element);
			if(imageDescriptor == null) {
				imageDescriptor = ImageDescriptor.getMissingImageDescriptor();
			}
			getImageRegistry().put(key, imageDescriptor);
		}
		return imageDescriptor;
	}

	/**
	 * @generated
	 */
	public static Image getImage(ENamedElement element) {
		String key = getImageRegistryKey(element);
		Image image = getImageRegistry().get(key);
		if(image == null) {
			ImageDescriptor imageDescriptor = getProvidedImageDescriptor(element);
			if(imageDescriptor == null) {
				imageDescriptor = ImageDescriptor.getMissingImageDescriptor();
			}
			getImageRegistry().put(key, imageDescriptor);
			image = getImageRegistry().get(key);
		}
		return image;
	}

	/**
	 * @generated
	 */
	public static ImageDescriptor getImageDescriptor(IAdaptable hint) {
		ENamedElement element = getElement(hint);
		if(element == null) {
			return null;
		}
		return getImageDescriptor(element);
	}

	/**
	 * @generated
	 */
	public static Image getImage(IAdaptable hint) {
		ENamedElement element = getElement(hint);
		if(element == null) {
			return null;
		}
		return getImage(element);
	}

	/**
	 * Returns 'type' of the ecore object associated with the hint.
	 * 
	 * @generated
	 */
	public static ENamedElement getElement(IAdaptable hint) {
		Object type = hint.getAdapter(IElementType.class);
		if(elements == null) {
			elements = new IdentityHashMap<IElementType, ENamedElement>();
			elements.put(Package_1000, UMLPackage.eINSTANCE.getPackage());
			elements.put(Dependency_2014, UMLPackage.eINSTANCE.getDependency());
			elements.put(AssociationClass_2013, UMLPackage.eINSTANCE.getAssociationClass());
			elements.put(Association_2015, UMLPackage.eINSTANCE.getAssociation());
			elements.put(InstanceSpecification_2001, UMLPackage.eINSTANCE.getInstanceSpecification());
			elements.put(Component_2002, UMLPackage.eINSTANCE.getComponent());
			elements.put(Signal_2003, UMLPackage.eINSTANCE.getSignal());
			elements.put(Interface_2004, UMLPackage.eINSTANCE.getInterface());
			elements.put(Model_2005, UMLPackage.eINSTANCE.getModel());
			elements.put(Enumeration_2006, UMLPackage.eINSTANCE.getEnumeration());
			elements.put(Package_2007, UMLPackage.eINSTANCE.getPackage());
			elements.put(InformationItem_2099, UMLPackage.eINSTANCE.getInformationItem());
			elements.put(Class_2008, UMLPackage.eINSTANCE.getClass_());
			elements.put(PrimitiveType_2009, UMLPackage.eINSTANCE.getPrimitiveType());
			elements.put(DataType_2010, UMLPackage.eINSTANCE.getDataType());
			elements.put(Constraint_2011, UMLPackage.eINSTANCE.getConstraint());
			elements.put(Comment_2012, UMLPackage.eINSTANCE.getComment());
			elements.put(Diagram_2016, NotationPackage.eINSTANCE.getDiagram());
			elements.put(DurationObservation_2095, UMLPackage.eINSTANCE.getDurationObservation());
			elements.put(TimeObservation_2096, UMLPackage.eINSTANCE.getTimeObservation());
			elements.put(NamedElement_2097, UMLPackage.eINSTANCE.getNamedElement());
			elements.put(NamedElement_2098, UMLPackage.eINSTANCE.getNamedElement());
			elements.put(Property_3012, UMLPackage.eINSTANCE.getProperty());
			elements.put(Property_3002, UMLPackage.eINSTANCE.getProperty());
			elements.put(Property_3005, UMLPackage.eINSTANCE.getProperty());
			elements.put(Property_3006, UMLPackage.eINSTANCE.getProperty());
			elements.put(Property_3018, UMLPackage.eINSTANCE.getProperty());
			elements.put(Class_3014, UMLPackage.eINSTANCE.getClass_());
			elements.put(Class_3004, UMLPackage.eINSTANCE.getClass_());
			elements.put(Class_3008, UMLPackage.eINSTANCE.getClass_());
			elements.put(Operation_3013, UMLPackage.eINSTANCE.getOperation());
			elements.put(Operation_3003, UMLPackage.eINSTANCE.getOperation());
			elements.put(Operation_3007, UMLPackage.eINSTANCE.getOperation());
			elements.put(Operation_3019, UMLPackage.eINSTANCE.getOperation());
			elements.put(ConnectableElementTemplateParameter_3034, UMLPackage.eINSTANCE.getConnectableElementTemplateParameter());
			elements.put(OperationTemplateParameter_3035, UMLPackage.eINSTANCE.getOperationTemplateParameter());
			elements.put(ClassifierTemplateParameter_3031, UMLPackage.eINSTANCE.getClassifierTemplateParameter());
			elements.put(TemplateParameter_3016, UMLPackage.eINSTANCE.getTemplateParameter());
			elements.put(EnumerationLiteral_3017, UMLPackage.eINSTANCE.getEnumerationLiteral());
			elements.put(Reception_3011, UMLPackage.eINSTANCE.getReception());
			elements.put(Reception_3039, UMLPackage.eINSTANCE.getReception());
			elements.put(Slot_3030, UMLPackage.eINSTANCE.getSlot());
			elements.put(RedefinableTemplateSignature_3015, UMLPackage.eINSTANCE.getRedefinableTemplateSignature());
			elements.put(TemplateSignature_3033, UMLPackage.eINSTANCE.getTemplateSignature());
			elements.put(InstanceSpecification_3020, UMLPackage.eINSTANCE.getInstanceSpecification());
			elements.put(Component_3021, UMLPackage.eINSTANCE.getComponent());
			elements.put(Signal_3022, UMLPackage.eINSTANCE.getSignal());
			elements.put(Interface_3023, UMLPackage.eINSTANCE.getInterface());
			elements.put(Model_3024, UMLPackage.eINSTANCE.getModel());
			elements.put(Enumeration_3025, UMLPackage.eINSTANCE.getEnumeration());
			elements.put(Package_3009, UMLPackage.eINSTANCE.getPackage());
			elements.put(InformationItem_3040, UMLPackage.eINSTANCE.getInformationItem());
			elements.put(Class_3010, UMLPackage.eINSTANCE.getClass_());
			elements.put(PrimitiveType_3026, UMLPackage.eINSTANCE.getPrimitiveType());
			elements.put(DataType_3027, UMLPackage.eINSTANCE.getDataType());
			elements.put(Comment_3028, UMLPackage.eINSTANCE.getComment());
			elements.put(Constraint_3029, UMLPackage.eINSTANCE.getConstraint());
			elements.put(Interface_3036, UMLPackage.eINSTANCE.getInterface());
			elements.put(Interface_3037, UMLPackage.eINSTANCE.getInterface());
			elements.put(Interface_3038, UMLPackage.eINSTANCE.getInterface());
			elements.put(AssociationClass_4017, UMLPackage.eINSTANCE.getAssociationClass());
			elements.put(Association_4001, UMLPackage.eINSTANCE.getAssociation());
			elements.put(Association_4019, UMLPackage.eINSTANCE.getAssociation());
			elements.put(Generalization_4002, UMLPackage.eINSTANCE.getGeneralization());
			elements.put(InterfaceRealization_4003, UMLPackage.eINSTANCE.getInterfaceRealization());
			elements.put(Substitution_4004, UMLPackage.eINSTANCE.getSubstitution());
			elements.put(Realization_4005, UMLPackage.eINSTANCE.getRealization());
			elements.put(Abstraction_4006, UMLPackage.eINSTANCE.getAbstraction());
			elements.put(Usage_4007, UMLPackage.eINSTANCE.getUsage());
			elements.put(Dependency_4008, UMLPackage.eINSTANCE.getDependency());
			elements.put(Dependency_4018, UMLPackage.eINSTANCE.getDependency());
			elements.put(ElementImport_4009, UMLPackage.eINSTANCE.getElementImport());
			elements.put(PackageImport_4010, UMLPackage.eINSTANCE.getPackageImport());
			elements.put(PackageMerge_4011, UMLPackage.eINSTANCE.getPackageMerge());
			elements.put(ProfileApplication_4012, UMLPackage.eINSTANCE.getProfileApplication());
			elements.put(CommentAnnotatedElement_4013, UMLPackage.eINSTANCE.getComment_AnnotatedElement());
			elements.put(ConstraintConstrainedElement_4014, UMLPackage.eINSTANCE.getConstraint_ConstrainedElement());
			elements.put(TemplateBinding_4015, UMLPackage.eINSTANCE.getTemplateBinding());
			elements.put(GeneralizationSet_4020, UMLPackage.eINSTANCE.getGeneralizationSet());
			elements.put(InstanceSpecification_4021, UMLPackage.eINSTANCE.getInstanceSpecification());
			elements.put(TimeObservationEvent_4024, UMLPackage.eINSTANCE.getTimeObservation_Event());
			elements.put(DurationObservationEvent_4025, UMLPackage.eINSTANCE.getDurationObservation_Event());
			elements.put(InformationFlow_4026, UMLPackage.eINSTANCE.getInformationFlow());
		}
		return (ENamedElement)elements.get(type);
	}

	/**
	 * @generated
	 */
	private static IElementType getElementType(String id) {
		return ElementTypeRegistry.getInstance().getType(id);
	}

	/**
	 * @generated
	 */
	public static boolean isKnownElementType(IElementType elementType) {
		if(KNOWN_ELEMENT_TYPES == null) {
			KNOWN_ELEMENT_TYPES = new HashSet<IElementType>();
			KNOWN_ELEMENT_TYPES.add(Package_1000);
			KNOWN_ELEMENT_TYPES.add(Dependency_2014);
			KNOWN_ELEMENT_TYPES.add(AssociationClass_2013);
			KNOWN_ELEMENT_TYPES.add(Association_2015);
			KNOWN_ELEMENT_TYPES.add(InstanceSpecification_2001);
			KNOWN_ELEMENT_TYPES.add(Component_2002);
			KNOWN_ELEMENT_TYPES.add(Signal_2003);
			KNOWN_ELEMENT_TYPES.add(Interface_2004);
			KNOWN_ELEMENT_TYPES.add(Model_2005);
			KNOWN_ELEMENT_TYPES.add(Enumeration_2006);
			KNOWN_ELEMENT_TYPES.add(Package_2007);
			KNOWN_ELEMENT_TYPES.add(InformationItem_2099);
			KNOWN_ELEMENT_TYPES.add(Class_2008);
			KNOWN_ELEMENT_TYPES.add(PrimitiveType_2009);
			KNOWN_ELEMENT_TYPES.add(DataType_2010);
			KNOWN_ELEMENT_TYPES.add(Constraint_2011);
			KNOWN_ELEMENT_TYPES.add(Comment_2012);
			KNOWN_ELEMENT_TYPES.add(Diagram_2016);
			KNOWN_ELEMENT_TYPES.add(DurationObservation_2095);
			KNOWN_ELEMENT_TYPES.add(TimeObservation_2096);
			KNOWN_ELEMENT_TYPES.add(NamedElement_2097);
			KNOWN_ELEMENT_TYPES.add(NamedElement_2098);
			KNOWN_ELEMENT_TYPES.add(Property_3012);
			KNOWN_ELEMENT_TYPES.add(Property_3002);
			KNOWN_ELEMENT_TYPES.add(Property_3005);
			KNOWN_ELEMENT_TYPES.add(Property_3006);
			KNOWN_ELEMENT_TYPES.add(Property_3018);
			KNOWN_ELEMENT_TYPES.add(Class_3014);
			KNOWN_ELEMENT_TYPES.add(Class_3004);
			KNOWN_ELEMENT_TYPES.add(Class_3008);
			KNOWN_ELEMENT_TYPES.add(Operation_3013);
			KNOWN_ELEMENT_TYPES.add(Operation_3003);
			KNOWN_ELEMENT_TYPES.add(Operation_3007);
			KNOWN_ELEMENT_TYPES.add(Operation_3019);
			KNOWN_ELEMENT_TYPES.add(ConnectableElementTemplateParameter_3034);
			KNOWN_ELEMENT_TYPES.add(OperationTemplateParameter_3035);
			KNOWN_ELEMENT_TYPES.add(ClassifierTemplateParameter_3031);
			KNOWN_ELEMENT_TYPES.add(TemplateParameter_3016);
			KNOWN_ELEMENT_TYPES.add(EnumerationLiteral_3017);
			KNOWN_ELEMENT_TYPES.add(Reception_3011);
			KNOWN_ELEMENT_TYPES.add(Reception_3039);
			KNOWN_ELEMENT_TYPES.add(Slot_3030);
			KNOWN_ELEMENT_TYPES.add(RedefinableTemplateSignature_3015);
			KNOWN_ELEMENT_TYPES.add(Node_3032);
			KNOWN_ELEMENT_TYPES.add(TemplateSignature_3033);
			KNOWN_ELEMENT_TYPES.add(InstanceSpecification_3020);
			KNOWN_ELEMENT_TYPES.add(Component_3021);
			KNOWN_ELEMENT_TYPES.add(Signal_3022);
			KNOWN_ELEMENT_TYPES.add(Interface_3023);
			KNOWN_ELEMENT_TYPES.add(Model_3024);
			KNOWN_ELEMENT_TYPES.add(Enumeration_3025);
			KNOWN_ELEMENT_TYPES.add(Package_3009);
			KNOWN_ELEMENT_TYPES.add(InformationItem_3040);
			KNOWN_ELEMENT_TYPES.add(Class_3010);
			KNOWN_ELEMENT_TYPES.add(PrimitiveType_3026);
			KNOWN_ELEMENT_TYPES.add(DataType_3027);
			KNOWN_ELEMENT_TYPES.add(Comment_3028);
			KNOWN_ELEMENT_TYPES.add(Constraint_3029);
			KNOWN_ELEMENT_TYPES.add(Interface_3036);
			KNOWN_ELEMENT_TYPES.add(Interface_3037);
			KNOWN_ELEMENT_TYPES.add(Interface_3038);
			KNOWN_ELEMENT_TYPES.add(Link_4016);
			KNOWN_ELEMENT_TYPES.add(AssociationClass_4017);
			KNOWN_ELEMENT_TYPES.add(Association_4001);
			KNOWN_ELEMENT_TYPES.add(Association_4019);
			KNOWN_ELEMENT_TYPES.add(Generalization_4002);
			KNOWN_ELEMENT_TYPES.add(InterfaceRealization_4003);
			KNOWN_ELEMENT_TYPES.add(Substitution_4004);
			KNOWN_ELEMENT_TYPES.add(Realization_4005);
			KNOWN_ELEMENT_TYPES.add(Abstraction_4006);
			KNOWN_ELEMENT_TYPES.add(Usage_4007);
			KNOWN_ELEMENT_TYPES.add(Dependency_4008);
			KNOWN_ELEMENT_TYPES.add(Dependency_4018);
			KNOWN_ELEMENT_TYPES.add(ElementImport_4009);
			KNOWN_ELEMENT_TYPES.add(PackageImport_4010);
			KNOWN_ELEMENT_TYPES.add(PackageMerge_4011);
			KNOWN_ELEMENT_TYPES.add(ProfileApplication_4012);
			KNOWN_ELEMENT_TYPES.add(CommentAnnotatedElement_4013);
			KNOWN_ELEMENT_TYPES.add(ConstraintConstrainedElement_4014);
			KNOWN_ELEMENT_TYPES.add(TemplateBinding_4015);
			KNOWN_ELEMENT_TYPES.add(GeneralizationSet_4020);
			KNOWN_ELEMENT_TYPES.add(InstanceSpecification_4021);
			KNOWN_ELEMENT_TYPES.add(Link_4022);
			KNOWN_ELEMENT_TYPES.add(Link_4023);
			KNOWN_ELEMENT_TYPES.add(TimeObservationEvent_4024);
			KNOWN_ELEMENT_TYPES.add(DurationObservationEvent_4025);
			KNOWN_ELEMENT_TYPES.add(InformationFlow_4026);
		}
		return KNOWN_ELEMENT_TYPES.contains(elementType);
	}

	/**
	 * @generated
	 */
	public static IElementType getElementType(int visualID) {
		switch(visualID) {
		case ModelEditPart.VISUAL_ID:
			return Package_1000;
		case DependencyNodeEditPart.VISUAL_ID:
			return Dependency_2014;
		case AssociationClassEditPart.VISUAL_ID:
			return AssociationClass_2013;
		case AssociationNodeEditPart.VISUAL_ID:
			return Association_2015;
		case InstanceSpecificationEditPart.VISUAL_ID:
			return InstanceSpecification_2001;
		case ComponentEditPart.VISUAL_ID:
			return Component_2002;
		case SignalEditPart.VISUAL_ID:
			return Signal_2003;
		case InterfaceEditPart.VISUAL_ID:
			return Interface_2004;
		case ModelEditPartTN.VISUAL_ID:
			return Model_2005;
		case EnumerationEditPart.VISUAL_ID:
			return Enumeration_2006;
		case PackageEditPart.VISUAL_ID:
			return Package_2007;
		case InformationItemEditPart.VISUAL_ID:
			return InformationItem_2099;
		case ClassEditPart.VISUAL_ID:
			return Class_2008;
		case PrimitiveTypeEditPart.VISUAL_ID:
			return PrimitiveType_2009;
		case DataTypeEditPart.VISUAL_ID:
			return DataType_2010;
		case ConstraintEditPart.VISUAL_ID:
			return Constraint_2011;
		case CommentEditPart.VISUAL_ID:
			return Comment_2012;
		case ShortCutDiagramEditPart.VISUAL_ID:
			return Diagram_2016;
		case DurationObservationEditPart.VISUAL_ID:
			return DurationObservation_2095;
		case TimeObservationEditPart.VISUAL_ID:
			return TimeObservation_2096;
		case DefaultNamedElementEditPart.VISUAL_ID:
			return NamedElement_2097;
		case ShapeNamedElementEditPart.VISUAL_ID:
			return NamedElement_2098;
		case PropertyForClassEditPart.VISUAL_ID:
			return Property_3012;
		case PropertyForComponentEditPart.VISUAL_ID:
			return Property_3002;
		case PropertyForSignalEditPart.VISUAL_ID:
			return Property_3005;
		case PropertyForInterfaceEditPart.VISUAL_ID:
			return Property_3006;
		case PropertyforDataTypeEditPart.VISUAL_ID:
			return Property_3018;
		case NestedClassForClassEditPart.VISUAL_ID:
			return Class_3014;
		case NestedClassForComponentEditPart.VISUAL_ID:
			return Class_3004;
		case NestedClassForInterfaceEditPart.VISUAL_ID:
			return Class_3008;
		case OperationForClassEditPart.VISUAL_ID:
			return Operation_3013;
		case OperationForComponentEditPart.VISUAL_ID:
			return Operation_3003;
		case OperationForInterfaceEditpart.VISUAL_ID:
			return Operation_3007;
		case OperationForDataTypeEditPart.VISUAL_ID:
			return Operation_3019;
		case ConnectableElementTemplateParameterEditPart.VISUAL_ID:
			return ConnectableElementTemplateParameter_3034;
		case OperationTemplateParameterEditPart.VISUAL_ID:
			return OperationTemplateParameter_3035;
		case ClassifierTemplateParameterEditPart.VISUAL_ID:
			return ClassifierTemplateParameter_3031;
		case TemplateParameterEditPart.VISUAL_ID:
			return TemplateParameter_3016;
		case EnumerationLiteralEditPart.VISUAL_ID:
			return EnumerationLiteral_3017;
		case ReceptionEditPart.VISUAL_ID:
			return Reception_3011;
		case ReceptionInInterfaceEditPart.VISUAL_ID:
			return Reception_3039;
		case SlotEditPart.VISUAL_ID:
			return Slot_3030;
		case RedefinableTemplateSignatureEditPart.VISUAL_ID:
			return RedefinableTemplateSignature_3015;
		case ContainmentCircleEditPart.VISUAL_ID:
			return Node_3032;
		case TemplateSignatureEditPart.VISUAL_ID:
			return TemplateSignature_3033;
		case InstanceSpecificationEditPartCN.VISUAL_ID:
			return InstanceSpecification_3020;
		case ComponentEditPartCN.VISUAL_ID:
			return Component_3021;
		case SignalEditPartCN.VISUAL_ID:
			return Signal_3022;
		case InterfaceEditPartCN.VISUAL_ID:
			return Interface_3023;
		case ModelEditPartCN.VISUAL_ID:
			return Model_3024;
		case EnumerationEditPartCN.VISUAL_ID:
			return Enumeration_3025;
		case PackageEditPartCN.VISUAL_ID:
			return Package_3009;
		case InformationItemEditPartCN.VISUAL_ID:
			return InformationItem_3040;
		case ClassEditPartCN.VISUAL_ID:
			return Class_3010;
		case PrimitiveTypeEditPartCN.VISUAL_ID:
			return PrimitiveType_3026;
		case DataTypeEditPartCN.VISUAL_ID:
			return DataType_3027;
		case CommentEditPartCN.VISUAL_ID:
			return Comment_3028;
		case ConstraintEditPartCN.VISUAL_ID:
			return Constraint_3029;
		case NestedInterfaceForClassEditPart.VISUAL_ID:
			return Interface_3036;
		case NestedInterfaceForComponentEditPart.VISUAL_ID:
			return Interface_3037;
		case NestedInterfaceForInterfaceEditPart.VISUAL_ID:
			return Interface_3038;
		case AssociationClassDashedLinkEditPart.VISUAL_ID:
			return Link_4016;
		case AssociationClassLinkEditPart.VISUAL_ID:
			return AssociationClass_4017;
		case AssociationEditPart.VISUAL_ID:
			return Association_4001;
		case AssociationBranchEditPart.VISUAL_ID:
			return Association_4019;
		case GeneralizationEditPart.VISUAL_ID:
			return Generalization_4002;
		case InterfaceRealizationEditPart.VISUAL_ID:
			return InterfaceRealization_4003;
		case SubstitutionEditPart.VISUAL_ID:
			return Substitution_4004;
		case RealizationEditPart.VISUAL_ID:
			return Realization_4005;
		case AbstractionEditPart.VISUAL_ID:
			return Abstraction_4006;
		case UsageEditPart.VISUAL_ID:
			return Usage_4007;
		case DependencyEditPart.VISUAL_ID:
			return Dependency_4008;
		case DependencyBranchEditPart.VISUAL_ID:
			return Dependency_4018;
		case ElementImportEditPart.VISUAL_ID:
			return ElementImport_4009;
		case PackageImportEditPart.VISUAL_ID:
			return PackageImport_4010;
		case PackageMergeEditPart.VISUAL_ID:
			return PackageMerge_4011;
		case ProfileApplicationEditPart.VISUAL_ID:
			return ProfileApplication_4012;
		case CommentAnnotatedElementEditPart.VISUAL_ID:
			return CommentAnnotatedElement_4013;
		case ConstraintConstrainedElementEditPart.VISUAL_ID:
			return ConstraintConstrainedElement_4014;
		case TemplateBindingEditPart.VISUAL_ID:
			return TemplateBinding_4015;
		case GeneralizationSetEditPart.VISUAL_ID:
			return GeneralizationSet_4020;
		case InstanceSpecificationLinkEditPart.VISUAL_ID:
			return InstanceSpecification_4021;
		case ContainmentSubLinkEditPart.VISUAL_ID:
			return Link_4022;
		case ContainmentLinkEditPart.VISUAL_ID:
			return Link_4023;
		case ConnectorTimeObservationEditPart.VISUAL_ID:
			return TimeObservationEvent_4024;
		case ConnectorDurationObservationEditPart.VISUAL_ID:
			return DurationObservationEvent_4025;
		case InformationFlowEditPart.VISUAL_ID:
			return InformationFlow_4026;
		}
		return null;
	}
}
