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
package org.eclipse.papyrus.uml.diagram.profile.edit.policies;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.DeleteCommand;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.emf.commands.core.command.CompositeTransactionalCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateRelationshipRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.ReorientReferenceRelationshipRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.ReorientRelationshipRequest;
import org.eclipse.papyrus.commands.wrappers.EMFtoGMFCommandWrapper;
import org.eclipse.papyrus.uml.diagram.profile.edit.commands.AssociationBranchCreateCommand;
import org.eclipse.papyrus.uml.diagram.profile.edit.commands.AssociationBranchReorientCommand;
import org.eclipse.papyrus.uml.diagram.profile.edit.commands.AssociationCreateCommand;
import org.eclipse.papyrus.uml.diagram.profile.edit.commands.AssociationReorientCommand;
import org.eclipse.papyrus.uml.diagram.profile.edit.commands.CommentAnnotatedElementCreateCommand;
import org.eclipse.papyrus.uml.diagram.profile.edit.commands.CommentAnnotatedElementReorientCommand;
import org.eclipse.papyrus.uml.diagram.profile.edit.commands.ConstraintConstrainedElementCreateCommand;
import org.eclipse.papyrus.uml.diagram.profile.edit.commands.ConstraintConstrainedElementReorientCommand;
import org.eclipse.papyrus.uml.diagram.profile.edit.commands.DependencyBranchCreateCommand;
import org.eclipse.papyrus.uml.diagram.profile.edit.commands.DependencyBranchReorientCommand;
import org.eclipse.papyrus.uml.diagram.profile.edit.commands.DependencyCreateCommand;
import org.eclipse.papyrus.uml.diagram.profile.edit.commands.DependencyReorientCommand;
import org.eclipse.papyrus.uml.diagram.profile.edit.commands.ElementImportCreateCommand;
import org.eclipse.papyrus.uml.diagram.profile.edit.commands.ElementImportReorientCommand;
import org.eclipse.papyrus.uml.diagram.profile.edit.commands.GeneralizationCreateCommand;
import org.eclipse.papyrus.uml.diagram.profile.edit.commands.GeneralizationReorientCommand;
import org.eclipse.papyrus.uml.diagram.profile.edit.commands.PackageImportCreateCommand;
import org.eclipse.papyrus.uml.diagram.profile.edit.commands.PackageImportReorientCommand;
import org.eclipse.papyrus.uml.diagram.profile.edit.parts.AssociationBranchEditPart;
import org.eclipse.papyrus.uml.diagram.profile.edit.parts.AssociationEditPart;
import org.eclipse.papyrus.uml.diagram.profile.edit.parts.CommentAnnotatedElementEditPart;
import org.eclipse.papyrus.uml.diagram.profile.edit.parts.ConstraintConstrainedElementEditPart;
import org.eclipse.papyrus.uml.diagram.profile.edit.parts.DependencyBranchEditPart;
import org.eclipse.papyrus.uml.diagram.profile.edit.parts.DependencyEditPart;
import org.eclipse.papyrus.uml.diagram.profile.edit.parts.ElementImportEditPart;
import org.eclipse.papyrus.uml.diagram.profile.edit.parts.GeneralizationEditPart;
import org.eclipse.papyrus.uml.diagram.profile.edit.parts.PackageImportEditPart;
import org.eclipse.papyrus.uml.diagram.profile.providers.UMLElementTypes;

/**
 * @generated
 */
public class AssociationItemSemanticEditPolicy extends UMLBaseItemSemanticEditPolicy {

	/**
	 * @generated
	 */
	public AssociationItemSemanticEditPolicy() {
		super(UMLElementTypes.Association_4001);
	}





	/**
	 * @generated
	 */
	protected Command getDestroyElementCommand(DestroyElementRequest req) {
		CompositeTransactionalCommand cmd = new CompositeTransactionalCommand(getEditingDomain(), null);
		cmd.setTransactionNestingEnabled(true);
		List<EObject> todestroy = new ArrayList<EObject>();
		todestroy.add(req.getElementToDestroy());
		//cmd.add(new org.eclipse.gmf.runtime.emf.type.core.commands.DestroyElementCommand(req));
		cmd.add(new EMFtoGMFCommandWrapper(new DeleteCommand(getEditingDomain(), todestroy)));
		return getGEFWrapper(cmd.reduce());
		//return getGEFWrapper(new org.eclipse.gmf.runtime.emf.type.core.commands.DestroyElementCommand(req));
	}

	/**
	 * @generated
	 */
	protected Command getCreateRelationshipCommand(CreateRelationshipRequest req) {
		Command command = req.getTarget() == null ? getStartCreateRelationshipCommand(req) : getCompleteCreateRelationshipCommand(req);
		return command != null ? command : super.getCreateRelationshipCommand(req);
	}

	/**
	 * @generated
	 */
	protected Command getStartCreateRelationshipCommand(CreateRelationshipRequest req) {
		if(UMLElementTypes.Association_4001 == req.getElementType()) {
			return getGEFWrapper(new AssociationCreateCommand(req, req.getSource(), req.getTarget()));
		}
		if(UMLElementTypes.Association_4019 == req.getElementType()) {
			return getGEFWrapper(new AssociationBranchCreateCommand(req, req.getSource(), req.getTarget()));
		}
		if(UMLElementTypes.Generalization_4002 == req.getElementType()) {
			return getGEFWrapper(new GeneralizationCreateCommand(req, req.getSource(), req.getTarget()));
		}
		if(UMLElementTypes.Dependency_4008 == req.getElementType()) {
			return getGEFWrapper(new DependencyCreateCommand(req, req.getSource(), req.getTarget()));
		}
		if(UMLElementTypes.Dependency_4018 == req.getElementType()) {
			return getGEFWrapper(new DependencyBranchCreateCommand(req, req.getSource(), req.getTarget()));
		}
		if(UMLElementTypes.ElementImport_1064 == req.getElementType()) {
			return getGEFWrapper(new ElementImportCreateCommand(req, req.getSource(), req.getTarget()));
		}
		if(UMLElementTypes.PackageImport_1065 == req.getElementType()) {
			return getGEFWrapper(new PackageImportCreateCommand(req, req.getSource(), req.getTarget()));
		}
		if(UMLElementTypes.CommentAnnotatedElement_1022 == req.getElementType()) {
			return null;
		}
		if(UMLElementTypes.ConstraintConstrainedElement_4014 == req.getElementType()) {
			return null;
		}
		return null;
	}

	/**
	 * @generated
	 */
	protected Command getCompleteCreateRelationshipCommand(CreateRelationshipRequest req) {
		if(UMLElementTypes.Association_4001 == req.getElementType()) {
			return getGEFWrapper(new AssociationCreateCommand(req, req.getSource(), req.getTarget()));
		}
		if(UMLElementTypes.Association_4019 == req.getElementType()) {
			return getGEFWrapper(new AssociationBranchCreateCommand(req, req.getSource(), req.getTarget()));
		}
		if(UMLElementTypes.Generalization_4002 == req.getElementType()) {
			return getGEFWrapper(new GeneralizationCreateCommand(req, req.getSource(), req.getTarget()));
		}
		if(UMLElementTypes.Dependency_4008 == req.getElementType()) {
			return getGEFWrapper(new DependencyCreateCommand(req, req.getSource(), req.getTarget()));
		}
		if(UMLElementTypes.Dependency_4018 == req.getElementType()) {
			return getGEFWrapper(new DependencyBranchCreateCommand(req, req.getSource(), req.getTarget()));
		}
		if(UMLElementTypes.ElementImport_1064 == req.getElementType()) {
			return getGEFWrapper(new ElementImportCreateCommand(req, req.getSource(), req.getTarget()));
		}
		if(UMLElementTypes.PackageImport_1065 == req.getElementType()) {
			return null;
		}
		if(UMLElementTypes.CommentAnnotatedElement_1022 == req.getElementType()) {
			return getGEFWrapper(new CommentAnnotatedElementCreateCommand(req, req.getSource(), req.getTarget()));
		}
		if(UMLElementTypes.ConstraintConstrainedElement_4014 == req.getElementType()) {
			return getGEFWrapper(new ConstraintConstrainedElementCreateCommand(req, req.getSource(), req.getTarget()));
		}
		return null;
	}

	/**
	 * Returns command to reorient EClass based link. New link target or source
	 * should be the domain model element associated with this node.
	 * 
	 * @generated
	 */
	protected Command getReorientRelationshipCommand(ReorientRelationshipRequest req) {
		switch(getVisualID(req)) {
		case AssociationEditPart.VISUAL_ID:
			return getGEFWrapper(new AssociationReorientCommand(req));
		case AssociationBranchEditPart.VISUAL_ID:
			return getGEFWrapper(new AssociationBranchReorientCommand(req));
		case GeneralizationEditPart.VISUAL_ID:
			return getGEFWrapper(new GeneralizationReorientCommand(req));
		case DependencyEditPart.VISUAL_ID:
			return getGEFWrapper(new DependencyReorientCommand(req));
		case DependencyBranchEditPart.VISUAL_ID:
			return getGEFWrapper(new DependencyBranchReorientCommand(req));
		case ElementImportEditPart.VISUAL_ID:
			return getGEFWrapper(new ElementImportReorientCommand(req));
		case PackageImportEditPart.VISUAL_ID:
			return getGEFWrapper(new PackageImportReorientCommand(req));
		}
		return super.getReorientRelationshipCommand(req);
	}

	/**
	 * Returns command to reorient EReference based link. New link target or source
	 * should be the domain model element associated with this node.
	 * 
	 * @generated
	 */
	protected Command getReorientReferenceRelationshipCommand(ReorientReferenceRelationshipRequest req) {
		switch(getVisualID(req)) {
		case CommentAnnotatedElementEditPart.VISUAL_ID:
			return getGEFWrapper(new CommentAnnotatedElementReorientCommand(req));
		case ConstraintConstrainedElementEditPart.VISUAL_ID:
			return getGEFWrapper(new ConstraintConstrainedElementReorientCommand(req));
		}
		return super.getReorientReferenceRelationshipCommand(req);
	}

}
