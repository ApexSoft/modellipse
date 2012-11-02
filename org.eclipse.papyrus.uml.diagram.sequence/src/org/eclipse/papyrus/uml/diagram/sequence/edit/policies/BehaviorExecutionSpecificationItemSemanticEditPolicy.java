/*****************************************************************************
 * Copyright (c) 2009 Atos Origin.
 *
 *    
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Atos Origin - Initial API and implementation
 *
 *****************************************************************************/
package org.eclipse.papyrus.uml.diagram.sequence.edit.policies;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import kr.co.apexsoft.modellipse.customization.diagram.sequence.commands.ApexDestroyElementCommand;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.gef.requests.ReconnectRequest;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.diagram.core.commands.DeleteCommand;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateRelationshipRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyDependentsRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.IEditCommandRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.ReorientReferenceRelationshipRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.ReorientRelationshipRequest;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.papyrus.infra.services.edit.service.ElementEditServiceUtils;
import org.eclipse.papyrus.infra.services.edit.service.IElementEditService;
import org.eclipse.papyrus.uml.diagram.sequence.edit.commands.CommentAnnotatedElementCreateCommand;
import org.eclipse.papyrus.uml.diagram.sequence.edit.commands.CommentAnnotatedElementReorientCommand;
import org.eclipse.papyrus.uml.diagram.sequence.edit.commands.ConstraintConstrainedElementCreateCommand;
import org.eclipse.papyrus.uml.diagram.sequence.edit.commands.ConstraintConstrainedElementReorientCommand;
import org.eclipse.papyrus.uml.diagram.sequence.edit.commands.GeneralOrderingCreateCommand;
import org.eclipse.papyrus.uml.diagram.sequence.edit.commands.GeneralOrderingReorientCommand;
import org.eclipse.papyrus.uml.diagram.sequence.edit.commands.Message2CreateCommand;
import org.eclipse.papyrus.uml.diagram.sequence.edit.commands.Message2ReorientCommand;
import org.eclipse.papyrus.uml.diagram.sequence.edit.commands.Message3CreateCommand;
import org.eclipse.papyrus.uml.diagram.sequence.edit.commands.Message3ReorientCommand;
import org.eclipse.papyrus.uml.diagram.sequence.edit.commands.Message4CreateCommand;
import org.eclipse.papyrus.uml.diagram.sequence.edit.commands.Message4ReorientCommand;
import org.eclipse.papyrus.uml.diagram.sequence.edit.commands.Message5CreateCommand;
import org.eclipse.papyrus.uml.diagram.sequence.edit.commands.Message5ReorientCommand;
import org.eclipse.papyrus.uml.diagram.sequence.edit.commands.Message6CreateCommand;
import org.eclipse.papyrus.uml.diagram.sequence.edit.commands.Message6ReorientCommand;
import org.eclipse.papyrus.uml.diagram.sequence.edit.commands.Message7CreateCommand;
import org.eclipse.papyrus.uml.diagram.sequence.edit.commands.Message7ReorientCommand;
import org.eclipse.papyrus.uml.diagram.sequence.edit.commands.MessageCreateCommand;
import org.eclipse.papyrus.uml.diagram.sequence.edit.commands.MessageReorientCommand;
import org.eclipse.papyrus.uml.diagram.sequence.edit.parts.CommentAnnotatedElementEditPart;
import org.eclipse.papyrus.uml.diagram.sequence.edit.parts.ConstraintConstrainedElementEditPart;
import org.eclipse.papyrus.uml.diagram.sequence.edit.parts.GeneralOrderingEditPart;
import org.eclipse.papyrus.uml.diagram.sequence.edit.parts.LifelineEditPart;
import org.eclipse.papyrus.uml.diagram.sequence.edit.parts.Message2EditPart;
import org.eclipse.papyrus.uml.diagram.sequence.edit.parts.Message3EditPart;
import org.eclipse.papyrus.uml.diagram.sequence.edit.parts.Message4EditPart;
import org.eclipse.papyrus.uml.diagram.sequence.edit.parts.Message5EditPart;
import org.eclipse.papyrus.uml.diagram.sequence.edit.parts.Message6EditPart;
import org.eclipse.papyrus.uml.diagram.sequence.edit.parts.Message7EditPart;
import org.eclipse.papyrus.uml.diagram.sequence.edit.parts.MessageEditPart;
import org.eclipse.papyrus.uml.diagram.sequence.providers.UMLElementTypes;
import org.eclipse.papyrus.uml.diagram.sequence.util.SequenceDeleteHelper;
import org.eclipse.papyrus.uml.diagram.sequence.util.SequenceRequestConstant;
import org.eclipse.papyrus.uml.diagram.sequence.util.SequenceUtil;
import org.eclipse.uml2.uml.ExecutionSpecification;
import org.eclipse.uml2.uml.OccurrenceSpecification;

/**
 * apex updated
 * 
 * @generated
 */
public class BehaviorExecutionSpecificationItemSemanticEditPolicy extends UMLBaseItemSemanticEditPolicy {

	/**
	 * @generated
	 */
	public BehaviorExecutionSpecificationItemSemanticEditPolicy() {
		super(UMLElementTypes.BehaviorExecutionSpecification_3003);
	}

	/* apex added start */
	private static final String SOURCE_CONNECTIONS_RECONNECTED = "Source Connections Reconnected";
	/* apex added end */

	/**
	 * apex updated
	 * 
	 * @generated NOT
	 */
	protected Command getDestroyElementCommand(DestroyElementRequest req) {
		CompoundCommand deleteElementsCommand = new CompoundCommand();
		
		/* apex added start */
		Boolean isReconnedted = (Boolean)req.getParameter(SOURCE_CONNECTIONS_RECONNECTED);
		if (isReconnedted != Boolean.TRUE) {
			// Activation의 source connections들을 parent인 lifeline으로 이동하여 연결
			if (getHost() instanceof ShapeNodeEditPart) {
				LifelineEditPart lifelineEP = SequenceUtil.getParentLifelinePart(getHost());
				IFigure figure = lifelineEP.getNodeFigure();
				Rectangle newBounds = figure.getBounds().getCopy();
				figure.translateToAbsolute(newBounds);

				List connections = ((ShapeNodeEditPart)getHost()).getSourceConnections();
				for (Iterator iter = connections.iterator(); iter.hasNext(); ) {
					ConnectionNodeEditPart connection = (ConnectionNodeEditPart)iter.next();
					Point location = SequenceUtil.getAbsoluteEdgeExtremity(connection, true);

					ReconnectRequest reconnReq = new ReconnectRequest();
					reconnReq.setConnectionEditPart(connection);
					reconnReq.setLocation(location);
					reconnReq.setTargetEditPart(lifelineEP);
					reconnReq.setType(RequestConstants.REQ_RECONNECT_SOURCE);
					reconnReq.getExtendedData().put(SequenceRequestConstant.DO_NOT_MOVE_EDIT_PARTS, true);

					deleteElementsCommand.add(lifelineEP.getCommand(reconnReq));
				}
				
				if (deleteElementsCommand.size() > 0) {
					IEditCommandRequest request = new DestroyDependentsRequest(getEditingDomain(), req.getElementToDestroy(), false);
					request.addParameters(req.getParameters());
					request.setParameter(SOURCE_CONNECTIONS_RECONNECTED, true);
					deleteElementsCommand.add(new ICommandProxy(new ApexDestroyElementCommand(getEditingDomain(), getHost(), request)));
					return deleteElementsCommand;
				}
			}
		}
		/* apex added end */

		EObject selectedEObject = req.getElementToDestroy();
		IElementEditService provider = ElementEditServiceUtils.getCommandProvider(selectedEObject);
		if(provider != null) {
			// Retrieve delete command from the Element Edit service
			ICommand deleteCommand = provider.getEditCommand(req);

			if(deleteCommand != null) {
				deleteElementsCommand.add(new ICommandProxy(deleteCommand));
			}
		}
		
		if (getHost().getParent() instanceof LifelineEditPart) {
			if (selectedEObject instanceof ExecutionSpecification) {
				List<OccurrenceSpecification> oss = new ArrayList<OccurrenceSpecification>();
				oss.add(((ExecutionSpecification) selectedEObject).getStart());
				oss.add(((ExecutionSpecification) selectedEObject).getFinish());
				SequenceDeleteHelper
						.addDeleteRelatedTimeObservationLinkCommand(
								deleteElementsCommand, req.getEditingDomain(),
								(LifelineEditPart) getHost().getParent(), oss,true);
				
				ExecutionSpecificationComponentEditPolicy.addDestroyElementChildrenCommand(deleteElementsCommand, req.getEditingDomain(), getHost());
			}
		}
		
		if(deleteElementsCommand.size()>0){
			return deleteElementsCommand;
		}
		
		return UnexecutableCommand.INSTANCE;
	}

	/**
	 * This method has been overridden to also delete linked time/duration views
	 * 
	 * @generated NOT
	 */
	protected Command addDeleteViewCommand(Command mainCommand, DestroyRequest completedRequest) {
		CompoundCommand deleteViewsCommand = new CompoundCommand();
		
		/* apex added start */
		// Activation의 source connections들을 parent인 lifeline으로 이동하여 연결
		if (getHost() instanceof ShapeNodeEditPart) {
			LifelineEditPart lifelineEP = SequenceUtil.getParentLifelinePart(getHost());
			IFigure figure = lifelineEP.getNodeFigure();
			Rectangle newBounds = figure.getBounds().getCopy();
			figure.translateToAbsolute(newBounds);
			
			List connections = ((ShapeNodeEditPart)getHost()).getSourceConnections();
			for (Iterator iter = connections.iterator(); iter.hasNext(); ) {
				ConnectionNodeEditPart connection = (ConnectionNodeEditPart)iter.next();
				Point location = SequenceUtil.getAbsoluteEdgeExtremity(connection, true);
				
				ReconnectRequest reconnReq = new ReconnectRequest();
				reconnReq.setConnectionEditPart(connection);
				reconnReq.setLocation(location);
				reconnReq.setTargetEditPart(lifelineEP);
				reconnReq.setType(RequestConstants.REQ_RECONNECT_SOURCE);
				reconnReq.getExtendedData().put(SequenceRequestConstant.DO_NOT_MOVE_EDIT_PARTS, true);
				
				deleteViewsCommand.add(lifelineEP.getCommand(reconnReq));
			}
		}
		/* apex added end */

		Command deleteViewCommand = getGEFWrapper(new DeleteCommand(getEditingDomain(), (View)getHost().getModel()));
		deleteViewsCommand.add(deleteViewCommand);
		SequenceDeleteHelper.completeDeleteExecutionSpecificationViewCommand(deleteViewsCommand, getEditingDomain(), getHost());
		ExecutionSpecificationComponentEditPolicy.addDeleteViewChildrenCommand(deleteViewsCommand, getEditingDomain(), (ShapeNodeEditPart)getHost());
		if(mainCommand == null) {
			return deleteViewsCommand;
		} else {
			return mainCommand.chain(deleteViewsCommand);
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
	 * @generated NOT
	 *            (update at each lifeline modification) add general ordering
	 */
	protected Command getStartCreateRelationshipCommand(CreateRelationshipRequest req) {
		if(UMLElementTypes.Message_4003 == req.getElementType()) {
			return getGEFWrapper(new MessageCreateCommand(req, req.getSource(), req.getTarget()));
		}
		if(UMLElementTypes.Message_4004 == req.getElementType()) {
			return getGEFWrapper(new Message2CreateCommand(req, req.getSource(), req.getTarget()));
		}
		if(UMLElementTypes.Message_4005 == req.getElementType()) {
			return getGEFWrapper(new Message3CreateCommand(req, req.getSource(), req.getTarget()));
		}
		if(UMLElementTypes.Message_4006 == req.getElementType()) {
			return getGEFWrapper(new Message4CreateCommand(req, req.getSource(), req.getTarget()));
		}
		if(UMLElementTypes.Message_4007 == req.getElementType()) {
			return getGEFWrapper(new Message5CreateCommand(req, req.getSource(), req.getTarget()));
		}
		if(UMLElementTypes.Message_4008 == req.getElementType()) {
			return getGEFWrapper(new Message6CreateCommand(req, req.getSource(), req.getTarget()));
		}
		if(UMLElementTypes.Message_4009 == req.getElementType()) {
			return getGEFWrapper(new Message7CreateCommand(req, req.getSource(), req.getTarget()));
		}
		if(UMLElementTypes.CommentAnnotatedElement_4010 == req.getElementType()) {
			return null;
		}
		if(UMLElementTypes.ConstraintConstrainedElement_4011 == req.getElementType()) {
			return null;
		}
		//add general ordering
		if(UMLElementTypes.GeneralOrdering_4012 == req.getElementType()) {
			return getGEFWrapper(new GeneralOrderingCreateCommand(req, req.getSource(), req.getTarget()));
		}
		return null;
	}

	/**
	 * @generated NOT
	 *            (update at each lifeline modification) add general ordering
	 */
	protected Command getCompleteCreateRelationshipCommand(CreateRelationshipRequest req) {
		if(UMLElementTypes.Message_4003 == req.getElementType()) {
			return getGEFWrapper(new MessageCreateCommand(req, req.getSource(), req.getTarget()));
		}
		if(UMLElementTypes.Message_4004 == req.getElementType()) {
			return getGEFWrapper(new Message2CreateCommand(req, req.getSource(), req.getTarget()));
		}
		if(UMLElementTypes.Message_4005 == req.getElementType()) {
			return getGEFWrapper(new Message3CreateCommand(req, req.getSource(), req.getTarget()));
		}
		if(UMLElementTypes.Message_4006 == req.getElementType()) {
			return getGEFWrapper(new Message4CreateCommand(req, req.getSource(), req.getTarget()));
		}
		if(UMLElementTypes.Message_4007 == req.getElementType()) {
			return getGEFWrapper(new Message5CreateCommand(req, req.getSource(), req.getTarget()));
		}
		if(UMLElementTypes.Message_4008 == req.getElementType()) {
			return getGEFWrapper(new Message6CreateCommand(req, req.getSource(), req.getTarget()));
		}
		if(UMLElementTypes.Message_4009 == req.getElementType()) {
			return getGEFWrapper(new Message7CreateCommand(req, req.getSource(), req.getTarget()));
		}
		if(UMLElementTypes.CommentAnnotatedElement_4010 == req.getElementType()) {
			return getGEFWrapper(new CommentAnnotatedElementCreateCommand(req, req.getSource(), req.getTarget()));
		}
		if(UMLElementTypes.ConstraintConstrainedElement_4011 == req.getElementType()) {
			return getGEFWrapper(new ConstraintConstrainedElementCreateCommand(req, req.getSource(), req.getTarget()));
		}
		//add general ordering
		if(UMLElementTypes.GeneralOrdering_4012 == req.getElementType()) {
			return getGEFWrapper(new GeneralOrderingCreateCommand(req, req.getSource(), req.getTarget()));
		}
		return null;
	}

	/**
	 * Returns command to reorient EClass based link. New link target or source should be the domain
	 * model element associated with this node.
	 * 
	 * @generated NOT
	 *            (update at each lifeline modification) add general ordering
	 */
	protected Command getReorientRelationshipCommand(ReorientRelationshipRequest req) {
		switch(getVisualID(req)) {
		case MessageEditPart.VISUAL_ID:
			return getGEFWrapper(new MessageReorientCommand(req));
		case Message2EditPart.VISUAL_ID:
			return getGEFWrapper(new Message2ReorientCommand(req));
		case Message3EditPart.VISUAL_ID:
			return getGEFWrapper(new Message3ReorientCommand(req));
		case Message4EditPart.VISUAL_ID:
			return getGEFWrapper(new Message4ReorientCommand(req));
		case Message5EditPart.VISUAL_ID:
			return getGEFWrapper(new Message5ReorientCommand(req));
		case Message6EditPart.VISUAL_ID:
			return getGEFWrapper(new Message6ReorientCommand(req));
		case Message7EditPart.VISUAL_ID:
			return getGEFWrapper(new Message7ReorientCommand(req));
			//add general ordering
		case GeneralOrderingEditPart.VISUAL_ID:
			if(req.getNewRelationshipEnd() instanceof OccurrenceSpecification) {
				return getGEFWrapper(new GeneralOrderingReorientCommand(req));
			} else {
				return null;
			}
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
