/*****************************************************************************
 * Copyright (c) 2010 CEA
 *
 *    
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Atos Origin - Initial API and implementation
 *
 *****************************************************************************/
package org.eclipse.papyrus.uml.diagram.sequence.edit.policies;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.FeedbackHelper;
import org.eclipse.gef.requests.CreateConnectionRequest;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gmf.runtime.common.core.command.CompositeCommand;
import org.eclipse.gmf.runtime.diagram.core.commands.SetConnectionAnchorsCommand;
import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.INodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateConnectionViewAndElementRequest;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateUnspecifiedTypeConnectionRequest;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.emf.type.core.IHintedType;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.papyrus.uml.diagram.sequence.command.PromptCreateElementAndNodeCommand;
import org.eclipse.papyrus.uml.diagram.sequence.providers.UMLElementTypes;
import org.eclipse.papyrus.uml.diagram.sequence.util.SequenceRequestConstant;
import org.eclipse.papyrus.uml.diagram.sequence.util.SequenceUtil;
import org.eclipse.uml2.uml.ExecutionSpecification;
import org.eclipse.uml2.uml.InteractionFragment;
import org.eclipse.uml2.uml.Lifeline;

/**
 * Edit Policy to create an element on a lifeline associated with the creation of a message.
 * For example it can be used to create a Destruction Event with a Message Delete
 * or the target Execution Specification with a Message Sync.
 * 
 * @author Mathieu Velten
 * 
 */
public class ElementCreationWithMessageEditPolicy extends LifelineChildGraphicalNodeEditPolicy {

	/**
	 * apex updated
	 */
	@Override
	protected Command getConnectionCompleteCommand(CreateConnectionRequest request) {
//		CompoundCommand compound = new CompoundCommand();

		Command command = super.getConnectionCompleteCommand(request);

		if(command != null && command.canExecute()) {
//			compound.add(command);

			if(request instanceof CreateConnectionViewAndElementRequest) {
				CreateConnectionViewAndElementRequest viewRequest = (CreateConnectionViewAndElementRequest)request;
				EditPart targetEP = getTargetEditPart(viewRequest);
				EObject target = ViewUtil.resolveSemanticElement((View)targetEP.getModel());
				EditPart sourceEP = viewRequest.getSourceEditPart();
				EObject source = ViewUtil.resolveSemanticElement((View)sourceEP.getModel());

				/* apex improved start */
				if (apexGetMessageHints().contains(viewRequest.getConnectionViewDescriptor().getSemanticHint())) {
				/* apex improved end */
				/* apex replaced
				if(getSyncMessageHint().equals(viewRequest.getConnectionViewDescriptor().getSemanticHint()) || getReplyMessageHint().equals(viewRequest.getConnectionViewDescriptor().getSemanticHint())) {
				 */
					if(target instanceof Lifeline ||
					// handle reflexive synch message by creating a new ES
					(target instanceof ExecutionSpecification && target.equals(source))) {
						InteractionFragment ift = SequenceUtil.findInteractionFragmentContainerAt(viewRequest.getLocation(), getHost());

						// retrieve the good execution specification type using the source of the message
						if(target instanceof ExecutionSpecification) {
							// retrieve its associated lifeline
							targetEP = targetEP.getParent();
							target = ViewUtil.resolveSemanticElement((View)targetEP.getModel());
						}
						
						/* apex improved start */
						return new ICommandProxy(new PromptCreateElementAndNodeCommand(
								command, getEditingDomain(),viewRequest.getConnectionViewDescriptor(),
								(ShapeNodeEditPart) targetEP, target, sourceEP, request, ift));
						/* apex improved end */
						/* apex replaced
						EditPart sourceEditPart = request.getSourceEditPart();
						if (sourceEditPart instanceof ActionExecutionSpecificationEditPart || sourceEditPart instanceof BehaviorExecutionSpecificationEditPart) {
							return new ICommandProxy(new PromptCreateElementAndNodeCommand(command, getEditingDomain(),viewRequest.getConnectionViewDescriptor(),(ShapeNodeEditPart) targetEP, target,sourceEP,request, ift));
						}
						 */
						
//						IHintedType elementType = null;
//						if(sourceEditPart instanceof ActionExecutionSpecificationEditPart) {
//							elementType = (IHintedType)UMLElementTypes.ActionExecutionSpecification_3006;
//						} else if(request.getSourceEditPart() instanceof BehaviorExecutionSpecificationEditPart) {
//							elementType = (IHintedType)UMLElementTypes.BehaviorExecutionSpecification_3003;
//						}
//
//
//						if(elementType != null) {
//							CreateElementAndNodeCommand createExecutionSpecificationCommand = new CreateElementAndNodeCommand(getEditingDomain(), (ShapeNodeEditPart)targetEP, target, elementType, request.getLocation());
//							createExecutionSpecificationCommand.putCreateElementRequestParameter(SequenceRequestConstant.INTERACTIONFRAGMENT_CONTAINER, ift);
//							compound.add(createExecutionSpecificationCommand);
//
//							// put the anchor at the top of the figure
//							ChangeEdgeTargetCommand changeTargetCommand = new ChangeEdgeTargetCommand(getEditingDomain(), createExecutionSpecificationCommand, viewRequest.getConnectionViewDescriptor(), "(0.5, 0.0)");
//							compound.add(new ICommandProxy(changeTargetCommand));
//						}
					}
				}
			}
		}

		return command;
	}

	private static String getSyncMessageHint() {
		IHintedType message = (IHintedType)UMLElementTypes.Message_4003;
		return message.getSemanticHint();
	}

	private static String getReplyMessageHint() {
		IHintedType message = (IHintedType)UMLElementTypes.Message_4005;
		return message.getSemanticHint();
	}

	private TransactionalEditingDomain getEditingDomain() {
		return ((IGraphicalEditPart)getHost()).getEditingDomain();
	}
	
	/**
	 * 모든 Message들의 SemanticHint List 반환
	 * @return
	 */
	private static List<String> apexGetMessageHints() {
		List<String> hints = new ArrayList<String>();
		
		hints.add(((IHintedType)UMLElementTypes.Message_4003).getSemanticHint());
		hints.add(((IHintedType)UMLElementTypes.Message_4004).getSemanticHint());
		hints.add(((IHintedType)UMLElementTypes.Message_4005).getSemanticHint());
//		hints.add(((IHintedType)UMLElementTypes.Message_4006).getSemanticHint());	// Create Message
		hints.add(((IHintedType)UMLElementTypes.Message_4007).getSemanticHint());
		hints.add(((IHintedType)UMLElementTypes.Message_4008).getSemanticHint());
		hints.add(((IHintedType)UMLElementTypes.Message_4009).getSemanticHint());
		
		return hints;
	}

	/**
	 * apex updated
	 */
	@Override
	protected void showCreationFeedback(CreateConnectionRequest request) {
		/* apex improved start */
		FeedbackHelper helper = getFeedbackHelper(request);
		Point p = new Point(request.getLocation());
		ConnectionAnchor anchor = getTargetConnectionAnchor(request);
		Point sourceLocation = null;
		if (request instanceof CreateUnspecifiedTypeConnectionRequest) {
			CreateUnspecifiedTypeConnectionRequest unspecifiedRequest = (CreateUnspecifiedTypeConnectionRequest)request;
			for (Object type : unspecifiedRequest.getElementTypes()) {
				if (type instanceof IElementType) {
					CreateRequest createRequest = unspecifiedRequest.getRequestForType((IElementType)type);
					Object srcLocation = createRequest.getExtendedData().get(SequenceRequestConstant.SOURCE_LOCATION_DATA);
					if (srcLocation instanceof Point) {
						sourceLocation = (Point)srcLocation;
						break;
					}
				}
			}
		}
		if (anchor == null && sourceLocation != null) {
			p.y = sourceLocation.y;
		}
		helper.update(anchor, p);
		/* apex improved end */
		/* apex replaced
		super.showCreationFeedback(request);
		 */

		/* apex added start */
		if (request instanceof CreateUnspecifiedTypeConnectionRequest) {
			CreateUnspecifiedTypeConnectionRequest unspecifiedRequest = (CreateUnspecifiedTypeConnectionRequest)request;
			for (Object type : unspecifiedRequest.getElementTypes()) {
				if (type instanceof IElementType) {
					CreateRequest createRequest = unspecifiedRequest.getRequestForType((IElementType)type);
					if (createRequest instanceof CreateConnectionRequest) {
						apexResetSourceAnchor((CreateConnectionRequest)createRequest);
					}
				}
			}
		}
		/* apex added end */
	}
	
	/**
	 * 화면 이동에 따른 Feedback의 source anchor 변경
	 * SetConnectionAnchorsCommand의 source terminal 변경
	 * @param request
	 */
	private void apexResetSourceAnchor(CreateConnectionRequest request) {
		EditPart sourceEditPart = request.getSourceEditPart();
		if (sourceEditPart instanceof INodeEditPart && !sourceEditPart.equals(request.getTargetEditPart())) {
			INodeEditPart sourceNodeEditPart = (INodeEditPart)sourceEditPart;
			Point p = (Point)request.getExtendedData().get(SequenceRequestConstant.SOURCE_LOCATION_DATA);
			ICommandProxy proxy = (ICommandProxy) request.getStartCommand();
			
			if (p != null && proxy != null) {
				CreateConnectionRequest createRequest = new CreateConnectionRequest();
				createRequest.setLocation(p);

				ConnectionAnchor anchor = sourceNodeEditPart.getSourceConnectionAnchor(createRequest);
				FeedbackHelper helper = getFeedbackHelper(createRequest);
				helper.setMovingStartAnchor(true);
				helper.update(anchor, p);
				helper.setMovingStartAnchor(false);
				
				CompositeCommand cc = (CompositeCommand)proxy.getICommand();
				SetConnectionAnchorsCommand scaCommand = null;
				Iterator iter = cc.iterator();
				while (iter.hasNext()) {
					Object next = iter.next();
					if (next instanceof SetConnectionAnchorsCommand) {
						scaCommand = (SetConnectionAnchorsCommand)next;
						break;
					}
				}
				if (scaCommand != null) {
					scaCommand.setNewSourceTerminal(sourceNodeEditPart.mapConnectionAnchorToTerminal(anchor));
				}
			}
		}
	}
}
