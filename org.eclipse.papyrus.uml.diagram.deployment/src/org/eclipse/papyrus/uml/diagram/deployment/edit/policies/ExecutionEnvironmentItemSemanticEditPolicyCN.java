/*
 * 
 */
package org.eclipse.papyrus.uml.diagram.deployment.edit.policies;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.DeleteCommand;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.common.core.command.ICompositeCommand;
import org.eclipse.gmf.runtime.emf.commands.core.command.CompositeTransactionalCommand;
import org.eclipse.gmf.runtime.emf.type.core.commands.DestroyElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.commands.DestroyReferenceCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateRelationshipRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyReferenceRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.ReorientReferenceRelationshipRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.ReorientRelationshipRequest;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.papyrus.commands.wrappers.EMFtoGMFCommandWrapper;
import org.eclipse.papyrus.infra.services.edit.service.ElementEditServiceUtils;
import org.eclipse.papyrus.infra.services.edit.service.IElementEditService;
import org.eclipse.papyrus.uml.diagram.deployment.edit.commands.CommentAnnotatedElementCreateCommand;
import org.eclipse.papyrus.uml.diagram.deployment.edit.commands.CommentAnnotatedElementReorientCommand;
import org.eclipse.papyrus.uml.diagram.deployment.edit.commands.ConstraintConstrainedElementCreateCommand;
import org.eclipse.papyrus.uml.diagram.deployment.edit.commands.ConstraintConstrainedElementReorientCommand;
import org.eclipse.papyrus.uml.diagram.deployment.edit.commands.DependencyCreateCommand;
import org.eclipse.papyrus.uml.diagram.deployment.edit.commands.DeploymentCreateCommand;
import org.eclipse.papyrus.uml.diagram.deployment.edit.commands.GeneralizationCreateCommand;
import org.eclipse.papyrus.uml.diagram.deployment.edit.commands.ManifestationCreateCommand;
import org.eclipse.papyrus.uml.diagram.deployment.edit.parts.ArtifactEditPartCN;
import org.eclipse.papyrus.uml.diagram.deployment.edit.parts.CommentAnnotatedElementEditPart;
import org.eclipse.papyrus.uml.diagram.deployment.edit.parts.ConstraintConstrainedElementEditPart;
import org.eclipse.papyrus.uml.diagram.deployment.edit.parts.DependencyEditPart;
import org.eclipse.papyrus.uml.diagram.deployment.edit.parts.DeploymentEditPart;
import org.eclipse.papyrus.uml.diagram.deployment.edit.parts.ExecutionEnvironmentCompositeCompartmentEditPartCN;
import org.eclipse.papyrus.uml.diagram.deployment.edit.parts.ExecutionEnvironmentEditPartCN;
import org.eclipse.papyrus.uml.diagram.deployment.edit.parts.GeneralizationEditPart;
import org.eclipse.papyrus.uml.diagram.deployment.edit.parts.ManifestationEditPart;
import org.eclipse.papyrus.uml.diagram.deployment.part.UMLVisualIDRegistry;
import org.eclipse.papyrus.uml.diagram.deployment.providers.UMLElementTypes;

/**
 * @generated
 */
public class ExecutionEnvironmentItemSemanticEditPolicyCN extends UMLBaseItemSemanticEditPolicy {

	/**
	 * @generated
	 */
	public ExecutionEnvironmentItemSemanticEditPolicyCN() {
		super(UMLElementTypes.ExecutionEnvironment_21);
	}

	/**
	 * @generated
	 */
	protected Command getDestroyElementCommand(DestroyElementRequest req) {
		View view = (View)getHost().getModel();
		CompositeTransactionalCommand cmd = new CompositeTransactionalCommand(getEditingDomain(), null);
		cmd.setTransactionNestingEnabled(true);

		EAnnotation annotation = view.getEAnnotation("Shortcut"); //$NON-NLS-1$
		if(annotation == null) {
			// there are indirectly referenced children, need extra commands:
			// false
			addDestroyChildNodesCommand(cmd);
			addDestroyShortcutsCommand(cmd, view);
			// delete host element
			List<EObject> todestroy = new ArrayList<EObject>();
			todestroy.add(req.getElementToDestroy());
			// cmd.add(new
			// org.eclipse.gmf.runtime.emf.type.core.commands.DestroyElementCommand(req));
			cmd.add(new EMFtoGMFCommandWrapper(new DeleteCommand(getEditingDomain(), todestroy)));
		} else {
			cmd.add(new org.eclipse.gmf.runtime.diagram.core.commands.DeleteCommand(getEditingDomain(), view));
		}
		return getGEFWrapper(cmd.reduce());
	}

	/**
	 * @generated
	 */
	protected void addDestroyChildNodesCommand(ICompositeCommand cmd) {
		View view = (View)getHost().getModel();
		for(Iterator<?> nit = view.getChildren().iterator(); nit.hasNext();) {
			Node node = (Node)nit.next();
			switch(UMLVisualIDRegistry.getVisualID(node)) {
			case ExecutionEnvironmentCompositeCompartmentEditPartCN.VISUAL_ID:
				for(Iterator<?> cit = node.getChildren().iterator(); cit.hasNext();) {
					Node cnode = (Node)cit.next();
					switch(UMLVisualIDRegistry.getVisualID(cnode)) {
					case ArtifactEditPartCN.VISUAL_ID:

						for(Iterator<?> it = cnode.getTargetEdges().iterator(); it.hasNext();) {
							Edge incomingLink = (Edge)it.next();
							switch(UMLVisualIDRegistry.getVisualID(incomingLink)) {
							case CommentAnnotatedElementEditPart.VISUAL_ID:
							case ConstraintConstrainedElementEditPart.VISUAL_ID:
								DestroyReferenceRequest destroyRefReq = new DestroyReferenceRequest(incomingLink.getSource().getElement(), null, incomingLink.getTarget().getElement(), false);
								cmd.add(new DestroyReferenceCommand(destroyRefReq));
								cmd.add(new org.eclipse.gmf.runtime.diagram.core.commands.DeleteCommand(getEditingDomain(), incomingLink));
								break;
							case DeploymentEditPart.VISUAL_ID:
							case ManifestationEditPart.VISUAL_ID:
							case GeneralizationEditPart.VISUAL_ID:
							case DependencyEditPart.VISUAL_ID:
								DestroyElementRequest destroyEltReq = new DestroyElementRequest(incomingLink.getElement(), false);
								cmd.add(new DestroyElementCommand(destroyEltReq));
								cmd.add(new org.eclipse.gmf.runtime.diagram.core.commands.DeleteCommand(getEditingDomain(), incomingLink));
								break;
							}
						}

						for(Iterator<?> it = cnode.getSourceEdges().iterator(); it.hasNext();) {
							Edge outgoingLink = (Edge)it.next();
							switch(UMLVisualIDRegistry.getVisualID(outgoingLink)) {
							case DeploymentEditPart.VISUAL_ID:
							case ManifestationEditPart.VISUAL_ID:
							case GeneralizationEditPart.VISUAL_ID:
							case DependencyEditPart.VISUAL_ID:
								DestroyElementRequest destroyEltReq = new DestroyElementRequest(outgoingLink.getElement(), false);
								cmd.add(new DestroyElementCommand(destroyEltReq));
								cmd.add(new org.eclipse.gmf.runtime.diagram.core.commands.DeleteCommand(getEditingDomain(), outgoingLink));
								break;
							}
						}
						cmd.add(new DestroyElementCommand(new DestroyElementRequest(getEditingDomain(), cnode.getElement(), false))); // directlyOwned:
																																		// true
						// don't need explicit deletion of cnode as parent's
						// view deletion would clean child views as well
						// cmd.add(new
						// org.eclipse.gmf.runtime.diagram.core.commands.DeleteCommand(getEditingDomain(),
						// cnode));
						break;
					case ExecutionEnvironmentEditPartCN.VISUAL_ID:

						for(Iterator<?> it = cnode.getTargetEdges().iterator(); it.hasNext();) {
							Edge incomingLink = (Edge)it.next();
							switch(UMLVisualIDRegistry.getVisualID(incomingLink)) {
							case CommentAnnotatedElementEditPart.VISUAL_ID:
							case ConstraintConstrainedElementEditPart.VISUAL_ID:
								DestroyReferenceRequest destroyRefReq = new DestroyReferenceRequest(incomingLink.getSource().getElement(), null, incomingLink.getTarget().getElement(), false);
								cmd.add(new DestroyReferenceCommand(destroyRefReq));
								cmd.add(new org.eclipse.gmf.runtime.diagram.core.commands.DeleteCommand(getEditingDomain(), incomingLink));
								break;
							case DeploymentEditPart.VISUAL_ID:
							case ManifestationEditPart.VISUAL_ID:
							case GeneralizationEditPart.VISUAL_ID:
							case DependencyEditPart.VISUAL_ID:
								DestroyElementRequest destroyEltReq = new DestroyElementRequest(incomingLink.getElement(), false);
								cmd.add(new DestroyElementCommand(destroyEltReq));
								cmd.add(new org.eclipse.gmf.runtime.diagram.core.commands.DeleteCommand(getEditingDomain(), incomingLink));
								break;
							}
						}

						for(Iterator<?> it = cnode.getSourceEdges().iterator(); it.hasNext();) {
							Edge outgoingLink = (Edge)it.next();
							switch(UMLVisualIDRegistry.getVisualID(outgoingLink)) {
							case DeploymentEditPart.VISUAL_ID:
							case ManifestationEditPart.VISUAL_ID:
							case GeneralizationEditPart.VISUAL_ID:
							case DependencyEditPart.VISUAL_ID:
								DestroyElementRequest destroyEltReq = new DestroyElementRequest(outgoingLink.getElement(), false);
								cmd.add(new DestroyElementCommand(destroyEltReq));
								cmd.add(new org.eclipse.gmf.runtime.diagram.core.commands.DeleteCommand(getEditingDomain(), outgoingLink));
								break;
							}
						}
						cmd.add(new DestroyElementCommand(new DestroyElementRequest(getEditingDomain(), cnode.getElement(), false))); // directlyOwned:
																																		// true
						// don't need explicit deletion of cnode as parent's
						// view deletion would clean child views as well
						// cmd.add(new
						// org.eclipse.gmf.runtime.diagram.core.commands.DeleteCommand(getEditingDomain(),
						// cnode));
						break;
					}
				}
				break;
			}
		}
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
		if(UMLElementTypes.CommentAnnotatedElement_4008 == req.getElementType()) {
			return null;
		}
		if(UMLElementTypes.ConstraintConstrainedElement_4009 == req.getElementType()) {
			return null;
		}
		if(UMLElementTypes.Deployment_4001 == req.getElementType()) {
			return getGEFWrapper(new DeploymentCreateCommand(req, req.getSource(), req.getTarget()));
		}
		if(UMLElementTypes.Manifestation_4002 == req.getElementType()) {
			return getGEFWrapper(new ManifestationCreateCommand(req, req.getSource(), req.getTarget()));
		}
		if(UMLElementTypes.Generalization_4003 == req.getElementType()) {
			return getGEFWrapper(new GeneralizationCreateCommand(req, req.getSource(), req.getTarget()));
		}
		if(UMLElementTypes.Dependency_4004 == req.getElementType()) {
			return getGEFWrapper(new DependencyCreateCommand(req, req.getSource(), req.getTarget()));
		}
		return null;
	}

	/**
	 * @generated
	 */
	protected Command getCompleteCreateRelationshipCommand(CreateRelationshipRequest req) {
		if(UMLElementTypes.CommentAnnotatedElement_4008 == req.getElementType()) {
			return getGEFWrapper(new CommentAnnotatedElementCreateCommand(req, req.getSource(), req.getTarget()));
		}
		if(UMLElementTypes.ConstraintConstrainedElement_4009 == req.getElementType()) {
			return getGEFWrapper(new ConstraintConstrainedElementCreateCommand(req, req.getSource(), req.getTarget()));
		}
		if(UMLElementTypes.Deployment_4001 == req.getElementType()) {
			return getGEFWrapper(new DeploymentCreateCommand(req, req.getSource(), req.getTarget()));
		}
		if(UMLElementTypes.Manifestation_4002 == req.getElementType()) {
			return getGEFWrapper(new ManifestationCreateCommand(req, req.getSource(), req.getTarget()));
		}
		if(UMLElementTypes.Generalization_4003 == req.getElementType()) {
			return getGEFWrapper(new GeneralizationCreateCommand(req, req.getSource(), req.getTarget()));
		}
		if(UMLElementTypes.Dependency_4004 == req.getElementType()) {
			return getGEFWrapper(new DependencyCreateCommand(req, req.getSource(), req.getTarget()));
		}
		return null;
	}

	/**
	 * Returns command to reorient EClass based link. New link target or source should be the domain model
	 * element associated with this node.
	 * 
	 * @generated
	 */
	protected Command getReorientRelationshipCommand(ReorientRelationshipRequest req) {
		switch(getVisualID(req)) {
		case DeploymentEditPart.VISUAL_ID:
		case ManifestationEditPart.VISUAL_ID:
		case GeneralizationEditPart.VISUAL_ID:
		case DependencyEditPart.VISUAL_ID:
			IElementEditService provider = ElementEditServiceUtils.getCommandProvider(req.getRelationship());
			if(provider == null) {
				return UnexecutableCommand.INSTANCE;
			}
			// Retrieve re-orient command from the Element Edit service
			ICommand reorientCommand = provider.getEditCommand(req);
			if(reorientCommand == null) {
				return UnexecutableCommand.INSTANCE;
			}
			return getGEFWrapper(reorientCommand.reduce());
		}
		return super.getReorientRelationshipCommand(req);
	}

	/**
	 * Returns command to reorient EReference based link. New link target or source should be the domain model
	 * element associated with this node.
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
