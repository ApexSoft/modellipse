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
package org.eclipse.papyrus.uml.diagram.clazz.edit.parts;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.StackLayout;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.LayoutEditPolicy;
import org.eclipse.gef.editpolicies.NonResizableEditPolicy;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.draw2d.ui.figures.ConstrainedToolbarLayout;
import org.eclipse.gmf.runtime.draw2d.ui.figures.FigureUtilities;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.gef.ui.figures.DefaultSizeNodeFigure;
import org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferenceConverter;
import org.eclipse.papyrus.infra.gmfdiag.preferences.utils.GradientPreferenceConverter;
import org.eclipse.papyrus.infra.gmfdiag.preferences.utils.PreferenceConstantHelper;
import org.eclipse.papyrus.uml.diagram.clazz.custom.policies.ClazzDiagramChangeStereotypedShapeEditpolicy;
import org.eclipse.papyrus.uml.diagram.clazz.custom.policies.CustomGraphicalNodeEditPolicy;
import org.eclipse.papyrus.uml.diagram.clazz.custom.policies.itemsemantic.CustomConstraint2ItemSemanticEditPolicy;
import org.eclipse.papyrus.uml.diagram.clazz.edit.policies.ConstraintItemSemanticEditPolicyCN;
import org.eclipse.papyrus.uml.diagram.clazz.part.UMLDiagramEditorPlugin;
import org.eclipse.papyrus.uml.diagram.clazz.part.UMLVisualIDRegistry;
import org.eclipse.papyrus.uml.diagram.clazz.providers.UMLElementTypes;
import org.eclipse.papyrus.uml.diagram.common.editparts.AbstractConstraintEditPart;
import org.eclipse.papyrus.uml.diagram.common.editpolicies.AppliedStereotypeLabelDisplayEditPolicy;
import org.eclipse.papyrus.uml.diagram.common.editpolicies.AppliedStereotypeNodeLabelDisplayEditPolicy;
import org.eclipse.papyrus.uml.diagram.common.editpolicies.ChangeStereotypedShapeEditPolicy;
import org.eclipse.papyrus.uml.diagram.common.figure.node.ConstraintFigure;
import org.eclipse.papyrus.uml.diagram.common.helper.PreferenceInitializerForElementHelper;
import org.eclipse.swt.graphics.Color;

/**
 * @generated
 */
public class ConstraintEditPartCN extends AbstractConstraintEditPart {

	/**
	 * @generated
	 */
	public static final int VISUAL_ID = 3029;

	/**
	 * @generated
	 */
	protected IFigure contentPane;

	/**
	 * @generated
	 */
	protected IFigure primaryShape;

	/**
	 * @generated
	 */
	public ConstraintEditPartCN(View view) {
		super(view);
	}

	/**
	 * @generated
	 */
	protected void createDefaultEditPolicies() {
		super.createDefaultEditPolicies();
		installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE, new ConstraintItemSemanticEditPolicyCN());
		installEditPolicy(EditPolicy.LAYOUT_ROLE, createLayoutEditPolicy());
		installEditPolicy(EditPolicy.GRAPHICAL_NODE_ROLE, new CustomGraphicalNodeEditPolicy());
		installEditPolicy(AppliedStereotypeLabelDisplayEditPolicy.STEREOTYPE_LABEL_POLICY, new AppliedStereotypeNodeLabelDisplayEditPolicy());
		installEditPolicy(ChangeStereotypedShapeEditPolicy.CHANGE_SHAPE_POLICY, new ClazzDiagramChangeStereotypedShapeEditpolicy());
		installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE, new CustomConstraint2ItemSemanticEditPolicy());
		// XXX need an SCR to runtime to have another abstract superclass that would let children add reasonable editpolicies
		// removeEditPolicy(org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles.CONNECTION_HANDLES_ROLE);
	}

	/**
	 * Papyrus codeGen
	 * 
	 * @generated
	 **/
	protected void handleNotificationEvent(Notification event) {
		super.handleNotificationEvent(event);
	}

	/**
	 * @generated
	 */
	protected LayoutEditPolicy createLayoutEditPolicy() {
		org.eclipse.gmf.runtime.diagram.ui.editpolicies.LayoutEditPolicy lep = new org.eclipse.gmf.runtime.diagram.ui.editpolicies.LayoutEditPolicy() {

			protected EditPolicy createChildEditPolicy(EditPart child) {
				EditPolicy result = child.getEditPolicy(EditPolicy.PRIMARY_DRAG_ROLE);
				if(result == null) {
					result = new NonResizableEditPolicy();
				}
				return result;
			}

			protected Command getMoveChildrenCommand(Request request) {
				return null;
			}

			protected Command getCreateCommand(CreateRequest request) {
				return null;
			}
		};
		return lep;
	}

	/**
	 * @generated
	 */
	protected IFigure createNodeShape() {
		return primaryShape = new ConstraintFigure();
	}

	/**
	 * @generated
	 */
	public ConstraintFigure getPrimaryShape() {
		return (ConstraintFigure)primaryShape;
	}

	/**
	 * @generated
	 */
	protected boolean addFixedChild(EditPart childEditPart) {
		if(childEditPart instanceof ConstraintNameEditPartCN) {
			((ConstraintNameEditPartCN)childEditPart).setLabel(getPrimaryShape().getNameLabel());
			return true;
		}
		if(childEditPart instanceof ConstraintBodyEditPartCN) {
			((ConstraintBodyEditPartCN)childEditPart).setLabel(getPrimaryShape().getConstraintFigure());
			return true;
		}
		return false;
	}

	/**
	 * @generated
	 */
	protected boolean removeFixedChild(EditPart childEditPart) {
		if(childEditPart instanceof ConstraintNameEditPartCN) {
			return true;
		}
		if(childEditPart instanceof ConstraintBodyEditPartCN) {
			return true;
		}
		return false;
	}

	/**
	 * @generated
	 */
	protected void addChildVisual(EditPart childEditPart, int index) {
		if(addFixedChild(childEditPart)) {
			return;
		}
		super.addChildVisual(childEditPart, -1);
	}

	/**
	 * @generated
	 */
	protected void removeChildVisual(EditPart childEditPart) {
		if(removeFixedChild(childEditPart)) {
			return;
		}
		super.removeChildVisual(childEditPart);
	}

	/**
	 * @generated
	 */
	protected IFigure getContentPaneFor(IGraphicalEditPart editPart) {
		return getContentPane();
	}

	/**
	 * @generated
	 */
	protected NodeFigure createNodePlate() {
		String prefElementId = "Constraint";
		IPreferenceStore store = UMLDiagramEditorPlugin.getInstance().getPreferenceStore();
		String preferenceConstantWitdh = PreferenceInitializerForElementHelper.getpreferenceKey(getNotationView(), prefElementId, PreferenceConstantHelper.WIDTH);
		String preferenceConstantHeight = PreferenceInitializerForElementHelper.getpreferenceKey(getNotationView(), prefElementId, PreferenceConstantHelper.HEIGHT);
		DefaultSizeNodeFigure result = new DefaultSizeNodeFigure(store.getInt(preferenceConstantWitdh), store.getInt(preferenceConstantHeight));
		return result;
	}

	/**
	 * Creates figure for this edit part.
	 * 
	 * Body of this method does not depend on settings in generation model
	 * so you may safely remove <i>generated</i> tag and modify it.
	 * 
	 * @generated
	 */
	protected NodeFigure createNodeFigure() {
		NodeFigure figure = createNodePlate();
		figure.setLayoutManager(new StackLayout());
		IFigure shape = createNodeShape();
		figure.add(shape);
		contentPane = setupContentPane(shape);
		return figure;
	}

	/**
	 * Default implementation treats passed figure as content pane.
	 * Respects layout one may have set for generated figure.
	 * 
	 * @param nodeShape
	 *        instance of generated figure class
	 * @generated
	 */
	protected IFigure setupContentPane(IFigure nodeShape) {
		if(nodeShape.getLayoutManager() == null) {
			ConstrainedToolbarLayout layout = new ConstrainedToolbarLayout();
			layout.setSpacing(5);
			nodeShape.setLayoutManager(layout);
		}
		return nodeShape; // use nodeShape itself as contentPane
	}

	/**
	 * @generated
	 */
	public IFigure getContentPane() {
		if(contentPane != null) {
			return contentPane;
		}
		return super.getContentPane();
	}

	/**
	 * @generated
	 */
	protected void setForegroundColor(Color color) {
		if(primaryShape != null) {
			primaryShape.setForegroundColor(color);
		}
	}

	/**
	 * @generated NOT
	 */
	protected void setLineWidth(int width) {
		// Do not mask implementation, use method from parent.
		// See. https://bugs.eclipse.org/bugs/show_bug.cgi?id=352549
		super.setLineWidth(width);
	}

	/**
	 * @generated NOT
	 */
	protected void setLineType(int style) {
		// Do not mask implementation, use method from parent.
		// See. https://bugs.eclipse.org/bugs/show_bug.cgi?id=352549
		super.setLineType(style);
	}

	/**
	 * @generated
	 */
	public EditPart getPrimaryChildEditPart() {
		return getChildBySemanticHint(UMLVisualIDRegistry.getType(ConstraintNameEditPartCN.VISUAL_ID));
	}

	/**
	 * @generated
	 */
	public List<IElementType> getMARelTypesOnSource() {
		ArrayList<IElementType> types = new ArrayList<IElementType>(7);
		types.add(UMLElementTypes.Realization_4005);
		types.add(UMLElementTypes.Abstraction_4006);
		types.add(UMLElementTypes.Usage_4007);
		types.add(UMLElementTypes.Dependency_4008);
		types.add(UMLElementTypes.Dependency_4018);
		types.add(UMLElementTypes.ConstraintConstrainedElement_4014);
		types.add(UMLElementTypes.InformationFlow_4026);
		return types;
	}

	/**
	 * @generated
	 */
	public List<IElementType> getMARelTypesOnSourceAndTarget(IGraphicalEditPart targetEditPart) {
		LinkedList<IElementType> types = new LinkedList<IElementType>();
		if(targetEditPart instanceof DependencyNodeEditPart) {
			types.add(UMLElementTypes.Realization_4005);
		}
		if(targetEditPart instanceof AssociationClassEditPart) {
			types.add(UMLElementTypes.Realization_4005);
		}
		if(targetEditPart instanceof AssociationNodeEditPart) {
			types.add(UMLElementTypes.Realization_4005);
		}
		if(targetEditPart instanceof InstanceSpecificationEditPart) {
			types.add(UMLElementTypes.Realization_4005);
		}
		if(targetEditPart instanceof ComponentEditPart) {
			types.add(UMLElementTypes.Realization_4005);
		}
		if(targetEditPart instanceof SignalEditPart) {
			types.add(UMLElementTypes.Realization_4005);
		}
		if(targetEditPart instanceof InterfaceEditPart) {
			types.add(UMLElementTypes.Realization_4005);
		}
		if(targetEditPart instanceof ModelEditPartTN) {
			types.add(UMLElementTypes.Realization_4005);
		}
		if(targetEditPart instanceof EnumerationEditPart) {
			types.add(UMLElementTypes.Realization_4005);
		}
		if(targetEditPart instanceof PackageEditPart) {
			types.add(UMLElementTypes.Realization_4005);
		}
		if(targetEditPart instanceof InformationItemEditPart) {
			types.add(UMLElementTypes.Realization_4005);
		}
		if(targetEditPart instanceof ClassEditPart) {
			types.add(UMLElementTypes.Realization_4005);
		}
		if(targetEditPart instanceof PrimitiveTypeEditPart) {
			types.add(UMLElementTypes.Realization_4005);
		}
		if(targetEditPart instanceof DataTypeEditPart) {
			types.add(UMLElementTypes.Realization_4005);
		}
		if(targetEditPart instanceof ConstraintEditPart) {
			types.add(UMLElementTypes.Realization_4005);
		}
		if(targetEditPart instanceof DurationObservationEditPart) {
			types.add(UMLElementTypes.Realization_4005);
		}
		if(targetEditPart instanceof TimeObservationEditPart) {
			types.add(UMLElementTypes.Realization_4005);
		}
		if(targetEditPart instanceof DefaultNamedElementEditPart) {
			types.add(UMLElementTypes.Realization_4005);
		}
		if(targetEditPart instanceof ShapeNamedElementEditPart) {
			types.add(UMLElementTypes.Realization_4005);
		}
		if(targetEditPart instanceof RedefinableTemplateSignatureEditPart) {
			types.add(UMLElementTypes.Realization_4005);
		}
		if(targetEditPart instanceof InstanceSpecificationEditPartCN) {
			types.add(UMLElementTypes.Realization_4005);
		}
		if(targetEditPart instanceof ComponentEditPartCN) {
			types.add(UMLElementTypes.Realization_4005);
		}
		if(targetEditPart instanceof SignalEditPartCN) {
			types.add(UMLElementTypes.Realization_4005);
		}
		if(targetEditPart instanceof InterfaceEditPartCN) {
			types.add(UMLElementTypes.Realization_4005);
		}
		if(targetEditPart instanceof ModelEditPartCN) {
			types.add(UMLElementTypes.Realization_4005);
		}
		if(targetEditPart instanceof EnumerationEditPartCN) {
			types.add(UMLElementTypes.Realization_4005);
		}
		if(targetEditPart instanceof PackageEditPartCN) {
			types.add(UMLElementTypes.Realization_4005);
		}
		if(targetEditPart instanceof InformationItemEditPartCN) {
			types.add(UMLElementTypes.Realization_4005);
		}
		if(targetEditPart instanceof ClassEditPartCN) {
			types.add(UMLElementTypes.Realization_4005);
		}
		if(targetEditPart instanceof PrimitiveTypeEditPartCN) {
			types.add(UMLElementTypes.Realization_4005);
		}
		if(targetEditPart instanceof DataTypeEditPartCN) {
			types.add(UMLElementTypes.Realization_4005);
		}
		if(targetEditPart instanceof org.eclipse.papyrus.uml.diagram.clazz.edit.parts.ConstraintEditPartCN) {
			types.add(UMLElementTypes.Realization_4005);
		}
		if(targetEditPart instanceof DependencyNodeEditPart) {
			types.add(UMLElementTypes.Abstraction_4006);
		}
		if(targetEditPart instanceof AssociationClassEditPart) {
			types.add(UMLElementTypes.Abstraction_4006);
		}
		if(targetEditPart instanceof AssociationNodeEditPart) {
			types.add(UMLElementTypes.Abstraction_4006);
		}
		if(targetEditPart instanceof InstanceSpecificationEditPart) {
			types.add(UMLElementTypes.Abstraction_4006);
		}
		if(targetEditPart instanceof ComponentEditPart) {
			types.add(UMLElementTypes.Abstraction_4006);
		}
		if(targetEditPart instanceof SignalEditPart) {
			types.add(UMLElementTypes.Abstraction_4006);
		}
		if(targetEditPart instanceof InterfaceEditPart) {
			types.add(UMLElementTypes.Abstraction_4006);
		}
		if(targetEditPart instanceof ModelEditPartTN) {
			types.add(UMLElementTypes.Abstraction_4006);
		}
		if(targetEditPart instanceof EnumerationEditPart) {
			types.add(UMLElementTypes.Abstraction_4006);
		}
		if(targetEditPart instanceof PackageEditPart) {
			types.add(UMLElementTypes.Abstraction_4006);
		}
		if(targetEditPart instanceof InformationItemEditPart) {
			types.add(UMLElementTypes.Abstraction_4006);
		}
		if(targetEditPart instanceof ClassEditPart) {
			types.add(UMLElementTypes.Abstraction_4006);
		}
		if(targetEditPart instanceof PrimitiveTypeEditPart) {
			types.add(UMLElementTypes.Abstraction_4006);
		}
		if(targetEditPart instanceof DataTypeEditPart) {
			types.add(UMLElementTypes.Abstraction_4006);
		}
		if(targetEditPart instanceof ConstraintEditPart) {
			types.add(UMLElementTypes.Abstraction_4006);
		}
		if(targetEditPart instanceof DurationObservationEditPart) {
			types.add(UMLElementTypes.Abstraction_4006);
		}
		if(targetEditPart instanceof TimeObservationEditPart) {
			types.add(UMLElementTypes.Abstraction_4006);
		}
		if(targetEditPart instanceof DefaultNamedElementEditPart) {
			types.add(UMLElementTypes.Abstraction_4006);
		}
		if(targetEditPart instanceof ShapeNamedElementEditPart) {
			types.add(UMLElementTypes.Abstraction_4006);
		}
		if(targetEditPart instanceof RedefinableTemplateSignatureEditPart) {
			types.add(UMLElementTypes.Abstraction_4006);
		}
		if(targetEditPart instanceof InstanceSpecificationEditPartCN) {
			types.add(UMLElementTypes.Abstraction_4006);
		}
		if(targetEditPart instanceof ComponentEditPartCN) {
			types.add(UMLElementTypes.Abstraction_4006);
		}
		if(targetEditPart instanceof SignalEditPartCN) {
			types.add(UMLElementTypes.Abstraction_4006);
		}
		if(targetEditPart instanceof InterfaceEditPartCN) {
			types.add(UMLElementTypes.Abstraction_4006);
		}
		if(targetEditPart instanceof ModelEditPartCN) {
			types.add(UMLElementTypes.Abstraction_4006);
		}
		if(targetEditPart instanceof EnumerationEditPartCN) {
			types.add(UMLElementTypes.Abstraction_4006);
		}
		if(targetEditPart instanceof PackageEditPartCN) {
			types.add(UMLElementTypes.Abstraction_4006);
		}
		if(targetEditPart instanceof InformationItemEditPartCN) {
			types.add(UMLElementTypes.Abstraction_4006);
		}
		if(targetEditPart instanceof ClassEditPartCN) {
			types.add(UMLElementTypes.Abstraction_4006);
		}
		if(targetEditPart instanceof PrimitiveTypeEditPartCN) {
			types.add(UMLElementTypes.Abstraction_4006);
		}
		if(targetEditPart instanceof DataTypeEditPartCN) {
			types.add(UMLElementTypes.Abstraction_4006);
		}
		if(targetEditPart instanceof org.eclipse.papyrus.uml.diagram.clazz.edit.parts.ConstraintEditPartCN) {
			types.add(UMLElementTypes.Abstraction_4006);
		}
		if(targetEditPart instanceof DependencyNodeEditPart) {
			types.add(UMLElementTypes.Usage_4007);
		}
		if(targetEditPart instanceof AssociationClassEditPart) {
			types.add(UMLElementTypes.Usage_4007);
		}
		if(targetEditPart instanceof AssociationNodeEditPart) {
			types.add(UMLElementTypes.Usage_4007);
		}
		if(targetEditPart instanceof InstanceSpecificationEditPart) {
			types.add(UMLElementTypes.Usage_4007);
		}
		if(targetEditPart instanceof ComponentEditPart) {
			types.add(UMLElementTypes.Usage_4007);
		}
		if(targetEditPart instanceof SignalEditPart) {
			types.add(UMLElementTypes.Usage_4007);
		}
		if(targetEditPart instanceof InterfaceEditPart) {
			types.add(UMLElementTypes.Usage_4007);
		}
		if(targetEditPart instanceof ModelEditPartTN) {
			types.add(UMLElementTypes.Usage_4007);
		}
		if(targetEditPart instanceof EnumerationEditPart) {
			types.add(UMLElementTypes.Usage_4007);
		}
		if(targetEditPart instanceof PackageEditPart) {
			types.add(UMLElementTypes.Usage_4007);
		}
		if(targetEditPart instanceof InformationItemEditPart) {
			types.add(UMLElementTypes.Usage_4007);
		}
		if(targetEditPart instanceof ClassEditPart) {
			types.add(UMLElementTypes.Usage_4007);
		}
		if(targetEditPart instanceof PrimitiveTypeEditPart) {
			types.add(UMLElementTypes.Usage_4007);
		}
		if(targetEditPart instanceof DataTypeEditPart) {
			types.add(UMLElementTypes.Usage_4007);
		}
		if(targetEditPart instanceof ConstraintEditPart) {
			types.add(UMLElementTypes.Usage_4007);
		}
		if(targetEditPart instanceof DurationObservationEditPart) {
			types.add(UMLElementTypes.Usage_4007);
		}
		if(targetEditPart instanceof TimeObservationEditPart) {
			types.add(UMLElementTypes.Usage_4007);
		}
		if(targetEditPart instanceof DefaultNamedElementEditPart) {
			types.add(UMLElementTypes.Usage_4007);
		}
		if(targetEditPart instanceof ShapeNamedElementEditPart) {
			types.add(UMLElementTypes.Usage_4007);
		}
		if(targetEditPart instanceof RedefinableTemplateSignatureEditPart) {
			types.add(UMLElementTypes.Usage_4007);
		}
		if(targetEditPart instanceof InstanceSpecificationEditPartCN) {
			types.add(UMLElementTypes.Usage_4007);
		}
		if(targetEditPart instanceof ComponentEditPartCN) {
			types.add(UMLElementTypes.Usage_4007);
		}
		if(targetEditPart instanceof SignalEditPartCN) {
			types.add(UMLElementTypes.Usage_4007);
		}
		if(targetEditPart instanceof InterfaceEditPartCN) {
			types.add(UMLElementTypes.Usage_4007);
		}
		if(targetEditPart instanceof ModelEditPartCN) {
			types.add(UMLElementTypes.Usage_4007);
		}
		if(targetEditPart instanceof EnumerationEditPartCN) {
			types.add(UMLElementTypes.Usage_4007);
		}
		if(targetEditPart instanceof PackageEditPartCN) {
			types.add(UMLElementTypes.Usage_4007);
		}
		if(targetEditPart instanceof InformationItemEditPartCN) {
			types.add(UMLElementTypes.Usage_4007);
		}
		if(targetEditPart instanceof ClassEditPartCN) {
			types.add(UMLElementTypes.Usage_4007);
		}
		if(targetEditPart instanceof PrimitiveTypeEditPartCN) {
			types.add(UMLElementTypes.Usage_4007);
		}
		if(targetEditPart instanceof DataTypeEditPartCN) {
			types.add(UMLElementTypes.Usage_4007);
		}
		if(targetEditPart instanceof org.eclipse.papyrus.uml.diagram.clazz.edit.parts.ConstraintEditPartCN) {
			types.add(UMLElementTypes.Usage_4007);
		}
		if(targetEditPart instanceof DependencyNodeEditPart) {
			types.add(UMLElementTypes.Dependency_4008);
		}
		if(targetEditPart instanceof AssociationClassEditPart) {
			types.add(UMLElementTypes.Dependency_4008);
		}
		if(targetEditPart instanceof AssociationNodeEditPart) {
			types.add(UMLElementTypes.Dependency_4008);
		}
		if(targetEditPart instanceof InstanceSpecificationEditPart) {
			types.add(UMLElementTypes.Dependency_4008);
		}
		if(targetEditPart instanceof ComponentEditPart) {
			types.add(UMLElementTypes.Dependency_4008);
		}
		if(targetEditPart instanceof SignalEditPart) {
			types.add(UMLElementTypes.Dependency_4008);
		}
		if(targetEditPart instanceof InterfaceEditPart) {
			types.add(UMLElementTypes.Dependency_4008);
		}
		if(targetEditPart instanceof ModelEditPartTN) {
			types.add(UMLElementTypes.Dependency_4008);
		}
		if(targetEditPart instanceof EnumerationEditPart) {
			types.add(UMLElementTypes.Dependency_4008);
		}
		if(targetEditPart instanceof PackageEditPart) {
			types.add(UMLElementTypes.Dependency_4008);
		}
		if(targetEditPart instanceof InformationItemEditPart) {
			types.add(UMLElementTypes.Dependency_4008);
		}
		if(targetEditPart instanceof ClassEditPart) {
			types.add(UMLElementTypes.Dependency_4008);
		}
		if(targetEditPart instanceof PrimitiveTypeEditPart) {
			types.add(UMLElementTypes.Dependency_4008);
		}
		if(targetEditPart instanceof DataTypeEditPart) {
			types.add(UMLElementTypes.Dependency_4008);
		}
		if(targetEditPart instanceof ConstraintEditPart) {
			types.add(UMLElementTypes.Dependency_4008);
		}
		if(targetEditPart instanceof DurationObservationEditPart) {
			types.add(UMLElementTypes.Dependency_4008);
		}
		if(targetEditPart instanceof TimeObservationEditPart) {
			types.add(UMLElementTypes.Dependency_4008);
		}
		if(targetEditPart instanceof DefaultNamedElementEditPart) {
			types.add(UMLElementTypes.Dependency_4008);
		}
		if(targetEditPart instanceof ShapeNamedElementEditPart) {
			types.add(UMLElementTypes.Dependency_4008);
		}
		if(targetEditPart instanceof RedefinableTemplateSignatureEditPart) {
			types.add(UMLElementTypes.Dependency_4008);
		}
		if(targetEditPart instanceof InstanceSpecificationEditPartCN) {
			types.add(UMLElementTypes.Dependency_4008);
		}
		if(targetEditPart instanceof ComponentEditPartCN) {
			types.add(UMLElementTypes.Dependency_4008);
		}
		if(targetEditPart instanceof SignalEditPartCN) {
			types.add(UMLElementTypes.Dependency_4008);
		}
		if(targetEditPart instanceof InterfaceEditPartCN) {
			types.add(UMLElementTypes.Dependency_4008);
		}
		if(targetEditPart instanceof ModelEditPartCN) {
			types.add(UMLElementTypes.Dependency_4008);
		}
		if(targetEditPart instanceof EnumerationEditPartCN) {
			types.add(UMLElementTypes.Dependency_4008);
		}
		if(targetEditPart instanceof PackageEditPartCN) {
			types.add(UMLElementTypes.Dependency_4008);
		}
		if(targetEditPart instanceof InformationItemEditPartCN) {
			types.add(UMLElementTypes.Dependency_4008);
		}
		if(targetEditPart instanceof ClassEditPartCN) {
			types.add(UMLElementTypes.Dependency_4008);
		}
		if(targetEditPart instanceof PrimitiveTypeEditPartCN) {
			types.add(UMLElementTypes.Dependency_4008);
		}
		if(targetEditPart instanceof DataTypeEditPartCN) {
			types.add(UMLElementTypes.Dependency_4008);
		}
		if(targetEditPart instanceof org.eclipse.papyrus.uml.diagram.clazz.edit.parts.ConstraintEditPartCN) {
			types.add(UMLElementTypes.Dependency_4008);
		}
		if(targetEditPart instanceof DependencyNodeEditPart) {
			types.add(UMLElementTypes.Dependency_4018);
		}
		if(targetEditPart instanceof AssociationClassEditPart) {
			types.add(UMLElementTypes.Dependency_4018);
		}
		if(targetEditPart instanceof AssociationNodeEditPart) {
			types.add(UMLElementTypes.Dependency_4018);
		}
		if(targetEditPart instanceof InstanceSpecificationEditPart) {
			types.add(UMLElementTypes.Dependency_4018);
		}
		if(targetEditPart instanceof ComponentEditPart) {
			types.add(UMLElementTypes.Dependency_4018);
		}
		if(targetEditPart instanceof SignalEditPart) {
			types.add(UMLElementTypes.Dependency_4018);
		}
		if(targetEditPart instanceof InterfaceEditPart) {
			types.add(UMLElementTypes.Dependency_4018);
		}
		if(targetEditPart instanceof ModelEditPartTN) {
			types.add(UMLElementTypes.Dependency_4018);
		}
		if(targetEditPart instanceof EnumerationEditPart) {
			types.add(UMLElementTypes.Dependency_4018);
		}
		if(targetEditPart instanceof PackageEditPart) {
			types.add(UMLElementTypes.Dependency_4018);
		}
		if(targetEditPart instanceof InformationItemEditPart) {
			types.add(UMLElementTypes.Dependency_4018);
		}
		if(targetEditPart instanceof ClassEditPart) {
			types.add(UMLElementTypes.Dependency_4018);
		}
		if(targetEditPart instanceof PrimitiveTypeEditPart) {
			types.add(UMLElementTypes.Dependency_4018);
		}
		if(targetEditPart instanceof DataTypeEditPart) {
			types.add(UMLElementTypes.Dependency_4018);
		}
		if(targetEditPart instanceof ConstraintEditPart) {
			types.add(UMLElementTypes.Dependency_4018);
		}
		if(targetEditPart instanceof DurationObservationEditPart) {
			types.add(UMLElementTypes.Dependency_4018);
		}
		if(targetEditPart instanceof TimeObservationEditPart) {
			types.add(UMLElementTypes.Dependency_4018);
		}
		if(targetEditPart instanceof DefaultNamedElementEditPart) {
			types.add(UMLElementTypes.Dependency_4018);
		}
		if(targetEditPart instanceof ShapeNamedElementEditPart) {
			types.add(UMLElementTypes.Dependency_4018);
		}
		if(targetEditPart instanceof RedefinableTemplateSignatureEditPart) {
			types.add(UMLElementTypes.Dependency_4018);
		}
		if(targetEditPart instanceof InstanceSpecificationEditPartCN) {
			types.add(UMLElementTypes.Dependency_4018);
		}
		if(targetEditPart instanceof ComponentEditPartCN) {
			types.add(UMLElementTypes.Dependency_4018);
		}
		if(targetEditPart instanceof SignalEditPartCN) {
			types.add(UMLElementTypes.Dependency_4018);
		}
		if(targetEditPart instanceof InterfaceEditPartCN) {
			types.add(UMLElementTypes.Dependency_4018);
		}
		if(targetEditPart instanceof ModelEditPartCN) {
			types.add(UMLElementTypes.Dependency_4018);
		}
		if(targetEditPart instanceof EnumerationEditPartCN) {
			types.add(UMLElementTypes.Dependency_4018);
		}
		if(targetEditPart instanceof PackageEditPartCN) {
			types.add(UMLElementTypes.Dependency_4018);
		}
		if(targetEditPart instanceof InformationItemEditPartCN) {
			types.add(UMLElementTypes.Dependency_4018);
		}
		if(targetEditPart instanceof ClassEditPartCN) {
			types.add(UMLElementTypes.Dependency_4018);
		}
		if(targetEditPart instanceof PrimitiveTypeEditPartCN) {
			types.add(UMLElementTypes.Dependency_4018);
		}
		if(targetEditPart instanceof DataTypeEditPartCN) {
			types.add(UMLElementTypes.Dependency_4018);
		}
		if(targetEditPart instanceof org.eclipse.papyrus.uml.diagram.clazz.edit.parts.ConstraintEditPartCN) {
			types.add(UMLElementTypes.Dependency_4018);
		}
		if(targetEditPart instanceof DependencyNodeEditPart) {
			types.add(UMLElementTypes.ConstraintConstrainedElement_4014);
		}
		if(targetEditPart instanceof AssociationClassEditPart) {
			types.add(UMLElementTypes.ConstraintConstrainedElement_4014);
		}
		if(targetEditPart instanceof AssociationNodeEditPart) {
			types.add(UMLElementTypes.ConstraintConstrainedElement_4014);
		}
		if(targetEditPart instanceof InstanceSpecificationEditPart) {
			types.add(UMLElementTypes.ConstraintConstrainedElement_4014);
		}
		if(targetEditPart instanceof ComponentEditPart) {
			types.add(UMLElementTypes.ConstraintConstrainedElement_4014);
		}
		if(targetEditPart instanceof SignalEditPart) {
			types.add(UMLElementTypes.ConstraintConstrainedElement_4014);
		}
		if(targetEditPart instanceof InterfaceEditPart) {
			types.add(UMLElementTypes.ConstraintConstrainedElement_4014);
		}
		if(targetEditPart instanceof ModelEditPartTN) {
			types.add(UMLElementTypes.ConstraintConstrainedElement_4014);
		}
		if(targetEditPart instanceof EnumerationEditPart) {
			types.add(UMLElementTypes.ConstraintConstrainedElement_4014);
		}
		if(targetEditPart instanceof PackageEditPart) {
			types.add(UMLElementTypes.ConstraintConstrainedElement_4014);
		}
		if(targetEditPart instanceof InformationItemEditPart) {
			types.add(UMLElementTypes.ConstraintConstrainedElement_4014);
		}
		if(targetEditPart instanceof ClassEditPart) {
			types.add(UMLElementTypes.ConstraintConstrainedElement_4014);
		}
		if(targetEditPart instanceof PrimitiveTypeEditPart) {
			types.add(UMLElementTypes.ConstraintConstrainedElement_4014);
		}
		if(targetEditPart instanceof DataTypeEditPart) {
			types.add(UMLElementTypes.ConstraintConstrainedElement_4014);
		}
		if(targetEditPart instanceof ConstraintEditPart) {
			types.add(UMLElementTypes.ConstraintConstrainedElement_4014);
		}
		if(targetEditPart instanceof CommentEditPart) {
			types.add(UMLElementTypes.ConstraintConstrainedElement_4014);
		}
		if(targetEditPart instanceof DurationObservationEditPart) {
			types.add(UMLElementTypes.ConstraintConstrainedElement_4014);
		}
		if(targetEditPart instanceof TimeObservationEditPart) {
			types.add(UMLElementTypes.ConstraintConstrainedElement_4014);
		}
		if(targetEditPart instanceof DefaultNamedElementEditPart) {
			types.add(UMLElementTypes.ConstraintConstrainedElement_4014);
		}
		if(targetEditPart instanceof ShapeNamedElementEditPart) {
			types.add(UMLElementTypes.ConstraintConstrainedElement_4014);
		}
		if(targetEditPart instanceof RedefinableTemplateSignatureEditPart) {
			types.add(UMLElementTypes.ConstraintConstrainedElement_4014);
		}
		if(targetEditPart instanceof TemplateSignatureEditPart) {
			types.add(UMLElementTypes.ConstraintConstrainedElement_4014);
		}
		if(targetEditPart instanceof InstanceSpecificationEditPartCN) {
			types.add(UMLElementTypes.ConstraintConstrainedElement_4014);
		}
		if(targetEditPart instanceof ComponentEditPartCN) {
			types.add(UMLElementTypes.ConstraintConstrainedElement_4014);
		}
		if(targetEditPart instanceof SignalEditPartCN) {
			types.add(UMLElementTypes.ConstraintConstrainedElement_4014);
		}
		if(targetEditPart instanceof InterfaceEditPartCN) {
			types.add(UMLElementTypes.ConstraintConstrainedElement_4014);
		}
		if(targetEditPart instanceof ModelEditPartCN) {
			types.add(UMLElementTypes.ConstraintConstrainedElement_4014);
		}
		if(targetEditPart instanceof EnumerationEditPartCN) {
			types.add(UMLElementTypes.ConstraintConstrainedElement_4014);
		}
		if(targetEditPart instanceof PackageEditPartCN) {
			types.add(UMLElementTypes.ConstraintConstrainedElement_4014);
		}
		if(targetEditPart instanceof InformationItemEditPartCN) {
			types.add(UMLElementTypes.ConstraintConstrainedElement_4014);
		}
		if(targetEditPart instanceof ClassEditPartCN) {
			types.add(UMLElementTypes.ConstraintConstrainedElement_4014);
		}
		if(targetEditPart instanceof PrimitiveTypeEditPartCN) {
			types.add(UMLElementTypes.ConstraintConstrainedElement_4014);
		}
		if(targetEditPart instanceof DataTypeEditPartCN) {
			types.add(UMLElementTypes.ConstraintConstrainedElement_4014);
		}
		if(targetEditPart instanceof CommentEditPartCN) {
			types.add(UMLElementTypes.ConstraintConstrainedElement_4014);
		}
		if(targetEditPart instanceof org.eclipse.papyrus.uml.diagram.clazz.edit.parts.ConstraintEditPartCN) {
			types.add(UMLElementTypes.ConstraintConstrainedElement_4014);
		}
		if(targetEditPart instanceof DependencyNodeEditPart) {
			types.add(UMLElementTypes.InformationFlow_4026);
		}
		if(targetEditPart instanceof AssociationClassEditPart) {
			types.add(UMLElementTypes.InformationFlow_4026);
		}
		if(targetEditPart instanceof AssociationNodeEditPart) {
			types.add(UMLElementTypes.InformationFlow_4026);
		}
		if(targetEditPart instanceof InstanceSpecificationEditPart) {
			types.add(UMLElementTypes.InformationFlow_4026);
		}
		if(targetEditPart instanceof ComponentEditPart) {
			types.add(UMLElementTypes.InformationFlow_4026);
		}
		if(targetEditPart instanceof SignalEditPart) {
			types.add(UMLElementTypes.InformationFlow_4026);
		}
		if(targetEditPart instanceof InterfaceEditPart) {
			types.add(UMLElementTypes.InformationFlow_4026);
		}
		if(targetEditPart instanceof ModelEditPartTN) {
			types.add(UMLElementTypes.InformationFlow_4026);
		}
		if(targetEditPart instanceof EnumerationEditPart) {
			types.add(UMLElementTypes.InformationFlow_4026);
		}
		if(targetEditPart instanceof PackageEditPart) {
			types.add(UMLElementTypes.InformationFlow_4026);
		}
		if(targetEditPart instanceof InformationItemEditPart) {
			types.add(UMLElementTypes.InformationFlow_4026);
		}
		if(targetEditPart instanceof ClassEditPart) {
			types.add(UMLElementTypes.InformationFlow_4026);
		}
		if(targetEditPart instanceof PrimitiveTypeEditPart) {
			types.add(UMLElementTypes.InformationFlow_4026);
		}
		if(targetEditPart instanceof DataTypeEditPart) {
			types.add(UMLElementTypes.InformationFlow_4026);
		}
		if(targetEditPart instanceof ConstraintEditPart) {
			types.add(UMLElementTypes.InformationFlow_4026);
		}
		if(targetEditPart instanceof DurationObservationEditPart) {
			types.add(UMLElementTypes.InformationFlow_4026);
		}
		if(targetEditPart instanceof TimeObservationEditPart) {
			types.add(UMLElementTypes.InformationFlow_4026);
		}
		if(targetEditPart instanceof DefaultNamedElementEditPart) {
			types.add(UMLElementTypes.InformationFlow_4026);
		}
		if(targetEditPart instanceof ShapeNamedElementEditPart) {
			types.add(UMLElementTypes.InformationFlow_4026);
		}
		if(targetEditPart instanceof RedefinableTemplateSignatureEditPart) {
			types.add(UMLElementTypes.InformationFlow_4026);
		}
		if(targetEditPart instanceof InstanceSpecificationEditPartCN) {
			types.add(UMLElementTypes.InformationFlow_4026);
		}
		if(targetEditPart instanceof ComponentEditPartCN) {
			types.add(UMLElementTypes.InformationFlow_4026);
		}
		if(targetEditPart instanceof SignalEditPartCN) {
			types.add(UMLElementTypes.InformationFlow_4026);
		}
		if(targetEditPart instanceof InterfaceEditPartCN) {
			types.add(UMLElementTypes.InformationFlow_4026);
		}
		if(targetEditPart instanceof ModelEditPartCN) {
			types.add(UMLElementTypes.InformationFlow_4026);
		}
		if(targetEditPart instanceof EnumerationEditPartCN) {
			types.add(UMLElementTypes.InformationFlow_4026);
		}
		if(targetEditPart instanceof PackageEditPartCN) {
			types.add(UMLElementTypes.InformationFlow_4026);
		}
		if(targetEditPart instanceof InformationItemEditPartCN) {
			types.add(UMLElementTypes.InformationFlow_4026);
		}
		if(targetEditPart instanceof ClassEditPartCN) {
			types.add(UMLElementTypes.InformationFlow_4026);
		}
		if(targetEditPart instanceof PrimitiveTypeEditPartCN) {
			types.add(UMLElementTypes.InformationFlow_4026);
		}
		if(targetEditPart instanceof DataTypeEditPartCN) {
			types.add(UMLElementTypes.InformationFlow_4026);
		}
		if(targetEditPart instanceof org.eclipse.papyrus.uml.diagram.clazz.edit.parts.ConstraintEditPartCN) {
			types.add(UMLElementTypes.InformationFlow_4026);
		}
		return types;
	}

	/**
	 * @generated
	 */
	public List<IElementType> getMATypesForTarget(IElementType relationshipType) {
		LinkedList<IElementType> types = new LinkedList<IElementType>();
		if(relationshipType == UMLElementTypes.Realization_4005) {
			types.add(UMLElementTypes.Dependency_2014);
			types.add(UMLElementTypes.AssociationClass_2013);
			types.add(UMLElementTypes.Association_2015);
			types.add(UMLElementTypes.InstanceSpecification_2001);
			types.add(UMLElementTypes.Component_2002);
			types.add(UMLElementTypes.Signal_2003);
			types.add(UMLElementTypes.Interface_2004);
			types.add(UMLElementTypes.Model_2005);
			types.add(UMLElementTypes.Enumeration_2006);
			types.add(UMLElementTypes.Package_2007);
			types.add(UMLElementTypes.InformationItem_2099);
			types.add(UMLElementTypes.Class_2008);
			types.add(UMLElementTypes.PrimitiveType_2009);
			types.add(UMLElementTypes.DataType_2010);
			types.add(UMLElementTypes.Constraint_2011);
			types.add(UMLElementTypes.DurationObservation_2095);
			types.add(UMLElementTypes.TimeObservation_2096);
			types.add(UMLElementTypes.NamedElement_2097);
			types.add(UMLElementTypes.NamedElement_2098);
			types.add(UMLElementTypes.RedefinableTemplateSignature_3015);
			types.add(UMLElementTypes.InstanceSpecification_3020);
			types.add(UMLElementTypes.Component_3021);
			types.add(UMLElementTypes.Signal_3022);
			types.add(UMLElementTypes.Interface_3023);
			types.add(UMLElementTypes.Model_3024);
			types.add(UMLElementTypes.Enumeration_3025);
			types.add(UMLElementTypes.Package_3009);
			types.add(UMLElementTypes.InformationItem_3040);
			types.add(UMLElementTypes.Class_3010);
			types.add(UMLElementTypes.PrimitiveType_3026);
			types.add(UMLElementTypes.DataType_3027);
			types.add(UMLElementTypes.Constraint_3029);
		} else if(relationshipType == UMLElementTypes.Abstraction_4006) {
			types.add(UMLElementTypes.Dependency_2014);
			types.add(UMLElementTypes.AssociationClass_2013);
			types.add(UMLElementTypes.Association_2015);
			types.add(UMLElementTypes.InstanceSpecification_2001);
			types.add(UMLElementTypes.Component_2002);
			types.add(UMLElementTypes.Signal_2003);
			types.add(UMLElementTypes.Interface_2004);
			types.add(UMLElementTypes.Model_2005);
			types.add(UMLElementTypes.Enumeration_2006);
			types.add(UMLElementTypes.Package_2007);
			types.add(UMLElementTypes.InformationItem_2099);
			types.add(UMLElementTypes.Class_2008);
			types.add(UMLElementTypes.PrimitiveType_2009);
			types.add(UMLElementTypes.DataType_2010);
			types.add(UMLElementTypes.Constraint_2011);
			types.add(UMLElementTypes.DurationObservation_2095);
			types.add(UMLElementTypes.TimeObservation_2096);
			types.add(UMLElementTypes.NamedElement_2097);
			types.add(UMLElementTypes.NamedElement_2098);
			types.add(UMLElementTypes.RedefinableTemplateSignature_3015);
			types.add(UMLElementTypes.InstanceSpecification_3020);
			types.add(UMLElementTypes.Component_3021);
			types.add(UMLElementTypes.Signal_3022);
			types.add(UMLElementTypes.Interface_3023);
			types.add(UMLElementTypes.Model_3024);
			types.add(UMLElementTypes.Enumeration_3025);
			types.add(UMLElementTypes.Package_3009);
			types.add(UMLElementTypes.InformationItem_3040);
			types.add(UMLElementTypes.Class_3010);
			types.add(UMLElementTypes.PrimitiveType_3026);
			types.add(UMLElementTypes.DataType_3027);
			types.add(UMLElementTypes.Constraint_3029);
		} else if(relationshipType == UMLElementTypes.Usage_4007) {
			types.add(UMLElementTypes.Dependency_2014);
			types.add(UMLElementTypes.AssociationClass_2013);
			types.add(UMLElementTypes.Association_2015);
			types.add(UMLElementTypes.InstanceSpecification_2001);
			types.add(UMLElementTypes.Component_2002);
			types.add(UMLElementTypes.Signal_2003);
			types.add(UMLElementTypes.Interface_2004);
			types.add(UMLElementTypes.Model_2005);
			types.add(UMLElementTypes.Enumeration_2006);
			types.add(UMLElementTypes.Package_2007);
			types.add(UMLElementTypes.InformationItem_2099);
			types.add(UMLElementTypes.Class_2008);
			types.add(UMLElementTypes.PrimitiveType_2009);
			types.add(UMLElementTypes.DataType_2010);
			types.add(UMLElementTypes.Constraint_2011);
			types.add(UMLElementTypes.DurationObservation_2095);
			types.add(UMLElementTypes.TimeObservation_2096);
			types.add(UMLElementTypes.NamedElement_2097);
			types.add(UMLElementTypes.NamedElement_2098);
			types.add(UMLElementTypes.RedefinableTemplateSignature_3015);
			types.add(UMLElementTypes.InstanceSpecification_3020);
			types.add(UMLElementTypes.Component_3021);
			types.add(UMLElementTypes.Signal_3022);
			types.add(UMLElementTypes.Interface_3023);
			types.add(UMLElementTypes.Model_3024);
			types.add(UMLElementTypes.Enumeration_3025);
			types.add(UMLElementTypes.Package_3009);
			types.add(UMLElementTypes.InformationItem_3040);
			types.add(UMLElementTypes.Class_3010);
			types.add(UMLElementTypes.PrimitiveType_3026);
			types.add(UMLElementTypes.DataType_3027);
			types.add(UMLElementTypes.Constraint_3029);
		} else if(relationshipType == UMLElementTypes.Dependency_4008) {
			types.add(UMLElementTypes.Dependency_2014);
			types.add(UMLElementTypes.AssociationClass_2013);
			types.add(UMLElementTypes.Association_2015);
			types.add(UMLElementTypes.InstanceSpecification_2001);
			types.add(UMLElementTypes.Component_2002);
			types.add(UMLElementTypes.Signal_2003);
			types.add(UMLElementTypes.Interface_2004);
			types.add(UMLElementTypes.Model_2005);
			types.add(UMLElementTypes.Enumeration_2006);
			types.add(UMLElementTypes.Package_2007);
			types.add(UMLElementTypes.InformationItem_2099);
			types.add(UMLElementTypes.Class_2008);
			types.add(UMLElementTypes.PrimitiveType_2009);
			types.add(UMLElementTypes.DataType_2010);
			types.add(UMLElementTypes.Constraint_2011);
			types.add(UMLElementTypes.DurationObservation_2095);
			types.add(UMLElementTypes.TimeObservation_2096);
			types.add(UMLElementTypes.NamedElement_2097);
			types.add(UMLElementTypes.NamedElement_2098);
			types.add(UMLElementTypes.RedefinableTemplateSignature_3015);
			types.add(UMLElementTypes.InstanceSpecification_3020);
			types.add(UMLElementTypes.Component_3021);
			types.add(UMLElementTypes.Signal_3022);
			types.add(UMLElementTypes.Interface_3023);
			types.add(UMLElementTypes.Model_3024);
			types.add(UMLElementTypes.Enumeration_3025);
			types.add(UMLElementTypes.Package_3009);
			types.add(UMLElementTypes.InformationItem_3040);
			types.add(UMLElementTypes.Class_3010);
			types.add(UMLElementTypes.PrimitiveType_3026);
			types.add(UMLElementTypes.DataType_3027);
			types.add(UMLElementTypes.Constraint_3029);
		} else if(relationshipType == UMLElementTypes.Dependency_4018) {
			types.add(UMLElementTypes.Dependency_2014);
			types.add(UMLElementTypes.AssociationClass_2013);
			types.add(UMLElementTypes.Association_2015);
			types.add(UMLElementTypes.InstanceSpecification_2001);
			types.add(UMLElementTypes.Component_2002);
			types.add(UMLElementTypes.Signal_2003);
			types.add(UMLElementTypes.Interface_2004);
			types.add(UMLElementTypes.Model_2005);
			types.add(UMLElementTypes.Enumeration_2006);
			types.add(UMLElementTypes.Package_2007);
			types.add(UMLElementTypes.InformationItem_2099);
			types.add(UMLElementTypes.Class_2008);
			types.add(UMLElementTypes.PrimitiveType_2009);
			types.add(UMLElementTypes.DataType_2010);
			types.add(UMLElementTypes.Constraint_2011);
			types.add(UMLElementTypes.DurationObservation_2095);
			types.add(UMLElementTypes.TimeObservation_2096);
			types.add(UMLElementTypes.NamedElement_2097);
			types.add(UMLElementTypes.NamedElement_2098);
			types.add(UMLElementTypes.RedefinableTemplateSignature_3015);
			types.add(UMLElementTypes.InstanceSpecification_3020);
			types.add(UMLElementTypes.Component_3021);
			types.add(UMLElementTypes.Signal_3022);
			types.add(UMLElementTypes.Interface_3023);
			types.add(UMLElementTypes.Model_3024);
			types.add(UMLElementTypes.Enumeration_3025);
			types.add(UMLElementTypes.Package_3009);
			types.add(UMLElementTypes.InformationItem_3040);
			types.add(UMLElementTypes.Class_3010);
			types.add(UMLElementTypes.PrimitiveType_3026);
			types.add(UMLElementTypes.DataType_3027);
			types.add(UMLElementTypes.Constraint_3029);
		} else if(relationshipType == UMLElementTypes.ConstraintConstrainedElement_4014) {
			types.add(UMLElementTypes.Dependency_2014);
			types.add(UMLElementTypes.AssociationClass_2013);
			types.add(UMLElementTypes.Association_2015);
			types.add(UMLElementTypes.InstanceSpecification_2001);
			types.add(UMLElementTypes.Component_2002);
			types.add(UMLElementTypes.Signal_2003);
			types.add(UMLElementTypes.Interface_2004);
			types.add(UMLElementTypes.Model_2005);
			types.add(UMLElementTypes.Enumeration_2006);
			types.add(UMLElementTypes.Package_2007);
			types.add(UMLElementTypes.InformationItem_2099);
			types.add(UMLElementTypes.Class_2008);
			types.add(UMLElementTypes.PrimitiveType_2009);
			types.add(UMLElementTypes.DataType_2010);
			types.add(UMLElementTypes.Constraint_2011);
			types.add(UMLElementTypes.Comment_2012);
			types.add(UMLElementTypes.DurationObservation_2095);
			types.add(UMLElementTypes.TimeObservation_2096);
			types.add(UMLElementTypes.NamedElement_2097);
			types.add(UMLElementTypes.NamedElement_2098);
			types.add(UMLElementTypes.RedefinableTemplateSignature_3015);
			types.add(UMLElementTypes.TemplateSignature_3033);
			types.add(UMLElementTypes.InstanceSpecification_3020);
			types.add(UMLElementTypes.Component_3021);
			types.add(UMLElementTypes.Signal_3022);
			types.add(UMLElementTypes.Interface_3023);
			types.add(UMLElementTypes.Model_3024);
			types.add(UMLElementTypes.Enumeration_3025);
			types.add(UMLElementTypes.Package_3009);
			types.add(UMLElementTypes.InformationItem_3040);
			types.add(UMLElementTypes.Class_3010);
			types.add(UMLElementTypes.PrimitiveType_3026);
			types.add(UMLElementTypes.DataType_3027);
			types.add(UMLElementTypes.Comment_3028);
			types.add(UMLElementTypes.Constraint_3029);
		} else if(relationshipType == UMLElementTypes.InformationFlow_4026) {
			types.add(UMLElementTypes.Dependency_2014);
			types.add(UMLElementTypes.AssociationClass_2013);
			types.add(UMLElementTypes.Association_2015);
			types.add(UMLElementTypes.InstanceSpecification_2001);
			types.add(UMLElementTypes.Component_2002);
			types.add(UMLElementTypes.Signal_2003);
			types.add(UMLElementTypes.Interface_2004);
			types.add(UMLElementTypes.Model_2005);
			types.add(UMLElementTypes.Enumeration_2006);
			types.add(UMLElementTypes.Package_2007);
			types.add(UMLElementTypes.InformationItem_2099);
			types.add(UMLElementTypes.Class_2008);
			types.add(UMLElementTypes.PrimitiveType_2009);
			types.add(UMLElementTypes.DataType_2010);
			types.add(UMLElementTypes.Constraint_2011);
			types.add(UMLElementTypes.DurationObservation_2095);
			types.add(UMLElementTypes.TimeObservation_2096);
			types.add(UMLElementTypes.NamedElement_2097);
			types.add(UMLElementTypes.NamedElement_2098);
			types.add(UMLElementTypes.RedefinableTemplateSignature_3015);
			types.add(UMLElementTypes.InstanceSpecification_3020);
			types.add(UMLElementTypes.Component_3021);
			types.add(UMLElementTypes.Signal_3022);
			types.add(UMLElementTypes.Interface_3023);
			types.add(UMLElementTypes.Model_3024);
			types.add(UMLElementTypes.Enumeration_3025);
			types.add(UMLElementTypes.Package_3009);
			types.add(UMLElementTypes.InformationItem_3040);
			types.add(UMLElementTypes.Class_3010);
			types.add(UMLElementTypes.PrimitiveType_3026);
			types.add(UMLElementTypes.DataType_3027);
			types.add(UMLElementTypes.Constraint_3029);
		}
		return types;
	}

	/**
	 * @generated
	 */
	public List<IElementType> getMARelTypesOnTarget() {
		ArrayList<IElementType> types = new ArrayList<IElementType>(11);
		types.add(UMLElementTypes.Realization_4005);
		types.add(UMLElementTypes.Abstraction_4006);
		types.add(UMLElementTypes.Usage_4007);
		types.add(UMLElementTypes.Dependency_4008);
		types.add(UMLElementTypes.Dependency_4018);
		types.add(UMLElementTypes.ElementImport_4009);
		types.add(UMLElementTypes.CommentAnnotatedElement_4013);
		types.add(UMLElementTypes.ConstraintConstrainedElement_4014);
		types.add(UMLElementTypes.TimeObservationEvent_4024);
		types.add(UMLElementTypes.DurationObservationEvent_4025);
		types.add(UMLElementTypes.InformationFlow_4026);
		return types;
	}

	/**
	 * @generated
	 */
	public List<IElementType> getMATypesForSource(IElementType relationshipType) {
		LinkedList<IElementType> types = new LinkedList<IElementType>();
		if(relationshipType == UMLElementTypes.Realization_4005) {
			types.add(UMLElementTypes.Dependency_2014);
			types.add(UMLElementTypes.AssociationClass_2013);
			types.add(UMLElementTypes.Association_2015);
			types.add(UMLElementTypes.InstanceSpecification_2001);
			types.add(UMLElementTypes.Component_2002);
			types.add(UMLElementTypes.Signal_2003);
			types.add(UMLElementTypes.Interface_2004);
			types.add(UMLElementTypes.Model_2005);
			types.add(UMLElementTypes.Enumeration_2006);
			types.add(UMLElementTypes.Package_2007);
			types.add(UMLElementTypes.InformationItem_2099);
			types.add(UMLElementTypes.Class_2008);
			types.add(UMLElementTypes.PrimitiveType_2009);
			types.add(UMLElementTypes.DataType_2010);
			types.add(UMLElementTypes.Constraint_2011);
			types.add(UMLElementTypes.DurationObservation_2095);
			types.add(UMLElementTypes.TimeObservation_2096);
			types.add(UMLElementTypes.NamedElement_2097);
			types.add(UMLElementTypes.NamedElement_2098);
			types.add(UMLElementTypes.RedefinableTemplateSignature_3015);
			types.add(UMLElementTypes.InstanceSpecification_3020);
			types.add(UMLElementTypes.Component_3021);
			types.add(UMLElementTypes.Signal_3022);
			types.add(UMLElementTypes.Interface_3023);
			types.add(UMLElementTypes.Model_3024);
			types.add(UMLElementTypes.Enumeration_3025);
			types.add(UMLElementTypes.Package_3009);
			types.add(UMLElementTypes.InformationItem_3040);
			types.add(UMLElementTypes.Class_3010);
			types.add(UMLElementTypes.PrimitiveType_3026);
			types.add(UMLElementTypes.DataType_3027);
			types.add(UMLElementTypes.Constraint_3029);
		} else if(relationshipType == UMLElementTypes.Abstraction_4006) {
			types.add(UMLElementTypes.Dependency_2014);
			types.add(UMLElementTypes.AssociationClass_2013);
			types.add(UMLElementTypes.Association_2015);
			types.add(UMLElementTypes.InstanceSpecification_2001);
			types.add(UMLElementTypes.Component_2002);
			types.add(UMLElementTypes.Signal_2003);
			types.add(UMLElementTypes.Interface_2004);
			types.add(UMLElementTypes.Model_2005);
			types.add(UMLElementTypes.Enumeration_2006);
			types.add(UMLElementTypes.Package_2007);
			types.add(UMLElementTypes.InformationItem_2099);
			types.add(UMLElementTypes.Class_2008);
			types.add(UMLElementTypes.PrimitiveType_2009);
			types.add(UMLElementTypes.DataType_2010);
			types.add(UMLElementTypes.Constraint_2011);
			types.add(UMLElementTypes.DurationObservation_2095);
			types.add(UMLElementTypes.TimeObservation_2096);
			types.add(UMLElementTypes.NamedElement_2097);
			types.add(UMLElementTypes.NamedElement_2098);
			types.add(UMLElementTypes.RedefinableTemplateSignature_3015);
			types.add(UMLElementTypes.InstanceSpecification_3020);
			types.add(UMLElementTypes.Component_3021);
			types.add(UMLElementTypes.Signal_3022);
			types.add(UMLElementTypes.Interface_3023);
			types.add(UMLElementTypes.Model_3024);
			types.add(UMLElementTypes.Enumeration_3025);
			types.add(UMLElementTypes.Package_3009);
			types.add(UMLElementTypes.InformationItem_3040);
			types.add(UMLElementTypes.Class_3010);
			types.add(UMLElementTypes.PrimitiveType_3026);
			types.add(UMLElementTypes.DataType_3027);
			types.add(UMLElementTypes.Constraint_3029);
		} else if(relationshipType == UMLElementTypes.Usage_4007) {
			types.add(UMLElementTypes.Dependency_2014);
			types.add(UMLElementTypes.AssociationClass_2013);
			types.add(UMLElementTypes.Association_2015);
			types.add(UMLElementTypes.InstanceSpecification_2001);
			types.add(UMLElementTypes.Component_2002);
			types.add(UMLElementTypes.Signal_2003);
			types.add(UMLElementTypes.Interface_2004);
			types.add(UMLElementTypes.Model_2005);
			types.add(UMLElementTypes.Enumeration_2006);
			types.add(UMLElementTypes.Package_2007);
			types.add(UMLElementTypes.InformationItem_2099);
			types.add(UMLElementTypes.Class_2008);
			types.add(UMLElementTypes.PrimitiveType_2009);
			types.add(UMLElementTypes.DataType_2010);
			types.add(UMLElementTypes.Constraint_2011);
			types.add(UMLElementTypes.DurationObservation_2095);
			types.add(UMLElementTypes.TimeObservation_2096);
			types.add(UMLElementTypes.NamedElement_2097);
			types.add(UMLElementTypes.NamedElement_2098);
			types.add(UMLElementTypes.RedefinableTemplateSignature_3015);
			types.add(UMLElementTypes.InstanceSpecification_3020);
			types.add(UMLElementTypes.Component_3021);
			types.add(UMLElementTypes.Signal_3022);
			types.add(UMLElementTypes.Interface_3023);
			types.add(UMLElementTypes.Model_3024);
			types.add(UMLElementTypes.Enumeration_3025);
			types.add(UMLElementTypes.Package_3009);
			types.add(UMLElementTypes.InformationItem_3040);
			types.add(UMLElementTypes.Class_3010);
			types.add(UMLElementTypes.PrimitiveType_3026);
			types.add(UMLElementTypes.DataType_3027);
			types.add(UMLElementTypes.Constraint_3029);
		} else if(relationshipType == UMLElementTypes.Dependency_4008) {
			types.add(UMLElementTypes.Dependency_2014);
			types.add(UMLElementTypes.AssociationClass_2013);
			types.add(UMLElementTypes.Association_2015);
			types.add(UMLElementTypes.InstanceSpecification_2001);
			types.add(UMLElementTypes.Component_2002);
			types.add(UMLElementTypes.Signal_2003);
			types.add(UMLElementTypes.Interface_2004);
			types.add(UMLElementTypes.Model_2005);
			types.add(UMLElementTypes.Enumeration_2006);
			types.add(UMLElementTypes.Package_2007);
			types.add(UMLElementTypes.InformationItem_2099);
			types.add(UMLElementTypes.Class_2008);
			types.add(UMLElementTypes.PrimitiveType_2009);
			types.add(UMLElementTypes.DataType_2010);
			types.add(UMLElementTypes.Constraint_2011);
			types.add(UMLElementTypes.DurationObservation_2095);
			types.add(UMLElementTypes.TimeObservation_2096);
			types.add(UMLElementTypes.NamedElement_2097);
			types.add(UMLElementTypes.NamedElement_2098);
			types.add(UMLElementTypes.RedefinableTemplateSignature_3015);
			types.add(UMLElementTypes.InstanceSpecification_3020);
			types.add(UMLElementTypes.Component_3021);
			types.add(UMLElementTypes.Signal_3022);
			types.add(UMLElementTypes.Interface_3023);
			types.add(UMLElementTypes.Model_3024);
			types.add(UMLElementTypes.Enumeration_3025);
			types.add(UMLElementTypes.Package_3009);
			types.add(UMLElementTypes.InformationItem_3040);
			types.add(UMLElementTypes.Class_3010);
			types.add(UMLElementTypes.PrimitiveType_3026);
			types.add(UMLElementTypes.DataType_3027);
			types.add(UMLElementTypes.Constraint_3029);
		} else if(relationshipType == UMLElementTypes.Dependency_4018) {
			types.add(UMLElementTypes.Dependency_2014);
			types.add(UMLElementTypes.AssociationClass_2013);
			types.add(UMLElementTypes.Association_2015);
			types.add(UMLElementTypes.InstanceSpecification_2001);
			types.add(UMLElementTypes.Component_2002);
			types.add(UMLElementTypes.Signal_2003);
			types.add(UMLElementTypes.Interface_2004);
			types.add(UMLElementTypes.Model_2005);
			types.add(UMLElementTypes.Enumeration_2006);
			types.add(UMLElementTypes.Package_2007);
			types.add(UMLElementTypes.InformationItem_2099);
			types.add(UMLElementTypes.Class_2008);
			types.add(UMLElementTypes.PrimitiveType_2009);
			types.add(UMLElementTypes.DataType_2010);
			types.add(UMLElementTypes.Constraint_2011);
			types.add(UMLElementTypes.DurationObservation_2095);
			types.add(UMLElementTypes.TimeObservation_2096);
			types.add(UMLElementTypes.NamedElement_2097);
			types.add(UMLElementTypes.NamedElement_2098);
			types.add(UMLElementTypes.RedefinableTemplateSignature_3015);
			types.add(UMLElementTypes.InstanceSpecification_3020);
			types.add(UMLElementTypes.Component_3021);
			types.add(UMLElementTypes.Signal_3022);
			types.add(UMLElementTypes.Interface_3023);
			types.add(UMLElementTypes.Model_3024);
			types.add(UMLElementTypes.Enumeration_3025);
			types.add(UMLElementTypes.Package_3009);
			types.add(UMLElementTypes.InformationItem_3040);
			types.add(UMLElementTypes.Class_3010);
			types.add(UMLElementTypes.PrimitiveType_3026);
			types.add(UMLElementTypes.DataType_3027);
			types.add(UMLElementTypes.Constraint_3029);
		} else if(relationshipType == UMLElementTypes.ElementImport_4009) {
			types.add(UMLElementTypes.AssociationClass_2013);
			types.add(UMLElementTypes.Association_2015);
			types.add(UMLElementTypes.Component_2002);
			types.add(UMLElementTypes.Signal_2003);
			types.add(UMLElementTypes.Interface_2004);
			types.add(UMLElementTypes.Model_2005);
			types.add(UMLElementTypes.Enumeration_2006);
			types.add(UMLElementTypes.Package_2007);
			types.add(UMLElementTypes.InformationItem_2099);
			types.add(UMLElementTypes.Class_2008);
			types.add(UMLElementTypes.PrimitiveType_2009);
			types.add(UMLElementTypes.DataType_2010);
			types.add(UMLElementTypes.Component_3021);
			types.add(UMLElementTypes.Signal_3022);
			types.add(UMLElementTypes.Interface_3023);
			types.add(UMLElementTypes.Model_3024);
			types.add(UMLElementTypes.Enumeration_3025);
			types.add(UMLElementTypes.Package_3009);
			types.add(UMLElementTypes.InformationItem_3040);
			types.add(UMLElementTypes.Class_3010);
			types.add(UMLElementTypes.PrimitiveType_3026);
			types.add(UMLElementTypes.DataType_3027);
		} else if(relationshipType == UMLElementTypes.CommentAnnotatedElement_4013) {
			types.add(UMLElementTypes.Comment_2012);
			types.add(UMLElementTypes.Comment_3028);
		} else if(relationshipType == UMLElementTypes.ConstraintConstrainedElement_4014) {
			types.add(UMLElementTypes.Constraint_2011);
			types.add(UMLElementTypes.Constraint_3029);
		} else if(relationshipType == UMLElementTypes.TimeObservationEvent_4024) {
			types.add(UMLElementTypes.TimeObservation_2096);
		} else if(relationshipType == UMLElementTypes.DurationObservationEvent_4025) {
			types.add(UMLElementTypes.DurationObservation_2095);
		} else if(relationshipType == UMLElementTypes.InformationFlow_4026) {
			types.add(UMLElementTypes.Dependency_2014);
			types.add(UMLElementTypes.AssociationClass_2013);
			types.add(UMLElementTypes.Association_2015);
			types.add(UMLElementTypes.InstanceSpecification_2001);
			types.add(UMLElementTypes.Component_2002);
			types.add(UMLElementTypes.Signal_2003);
			types.add(UMLElementTypes.Interface_2004);
			types.add(UMLElementTypes.Model_2005);
			types.add(UMLElementTypes.Enumeration_2006);
			types.add(UMLElementTypes.Package_2007);
			types.add(UMLElementTypes.InformationItem_2099);
			types.add(UMLElementTypes.Class_2008);
			types.add(UMLElementTypes.PrimitiveType_2009);
			types.add(UMLElementTypes.DataType_2010);
			types.add(UMLElementTypes.Constraint_2011);
			types.add(UMLElementTypes.DurationObservation_2095);
			types.add(UMLElementTypes.TimeObservation_2096);
			types.add(UMLElementTypes.NamedElement_2097);
			types.add(UMLElementTypes.NamedElement_2098);
			types.add(UMLElementTypes.RedefinableTemplateSignature_3015);
			types.add(UMLElementTypes.InstanceSpecification_3020);
			types.add(UMLElementTypes.Component_3021);
			types.add(UMLElementTypes.Signal_3022);
			types.add(UMLElementTypes.Interface_3023);
			types.add(UMLElementTypes.Model_3024);
			types.add(UMLElementTypes.Enumeration_3025);
			types.add(UMLElementTypes.Package_3009);
			types.add(UMLElementTypes.InformationItem_3040);
			types.add(UMLElementTypes.Class_3010);
			types.add(UMLElementTypes.PrimitiveType_3026);
			types.add(UMLElementTypes.DataType_3027);
			types.add(UMLElementTypes.Constraint_3029);
		}
		return types;
	}

	/**
	 * @generated
	 */
	@Override
	public Object getPreferredValue(EStructuralFeature feature) {
		IPreferenceStore preferenceStore = (IPreferenceStore)getDiagramPreferencesHint().getPreferenceStore();
		Object result = null;
		if(feature == NotationPackage.eINSTANCE.getLineStyle_LineColor() || feature == NotationPackage.eINSTANCE.getFontStyle_FontColor() || feature == NotationPackage.eINSTANCE.getFillStyle_FillColor()) {
			String prefColor = null;
			if(feature == NotationPackage.eINSTANCE.getLineStyle_LineColor()) {
				prefColor = PreferenceConstantHelper.getElementConstant("Constraint", PreferenceConstantHelper.COLOR_LINE);
			} else if(feature == NotationPackage.eINSTANCE.getFontStyle_FontColor()) {
				prefColor = PreferenceConstantHelper.getElementConstant("Constraint", PreferenceConstantHelper.COLOR_FONT);
			} else if(feature == NotationPackage.eINSTANCE.getFillStyle_FillColor()) {
				prefColor = PreferenceConstantHelper.getElementConstant("Constraint", PreferenceConstantHelper.COLOR_FILL);
			}
			result = FigureUtilities.RGBToInteger(PreferenceConverter.getColor((IPreferenceStore)preferenceStore, prefColor));
		} else if(feature == NotationPackage.eINSTANCE.getFillStyle_Transparency() || feature == NotationPackage.eINSTANCE.getFillStyle_Gradient()) {
			String prefGradient = PreferenceConstantHelper.getElementConstant("Constraint", PreferenceConstantHelper.COLOR_GRADIENT);
			GradientPreferenceConverter gradientPreferenceConverter = new GradientPreferenceConverter(preferenceStore.getString(prefGradient));
			if(feature == NotationPackage.eINSTANCE.getFillStyle_Transparency()) {
				result = new Integer(gradientPreferenceConverter.getTransparency());
			} else if(feature == NotationPackage.eINSTANCE.getFillStyle_Gradient()) {
				result = gradientPreferenceConverter.getGradientData();
			}
		}
		if(result == null) {
			result = getStructuralFeatureValue(feature);
		}
		return result;
	}
}
