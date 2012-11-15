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

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gef.requests.CreateConnectionRequest;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.ResizableShapeEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.XYLayoutEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateConnectionViewAndElementRequest;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateUnspecifiedTypeRequest;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewAndElementRequest;
import org.eclipse.papyrus.uml.diagram.sequence.edit.parts.CombinedFragmentEditPart;
import org.eclipse.papyrus.uml.diagram.sequence.providers.UMLElementTypes;

/**
 * The customn LayoutEditPolicy for InteractionOperandEditPart.
 */
public class InteractionOperandLayoutEditPolicy extends XYLayoutEditPolicy {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected EditPolicy createChildEditPolicy(EditPart child) {
		EditPolicy result = super.createChildEditPolicy(child);
		if(result == null) {
			return new ResizableShapeEditPolicy();
		}
		return result;
	}

	/**
	 * apex updated
	 * 
	 * 중첩된 child CombinedFragment 이동 시
	 * REQ_ORPHAN_CHILDREN에 대한 처리 로직 추가
	 * 
	 * Handle create InteractionOperand hover InteractionOperand {@inheritDoc}
	 */
	@Override
	public Command getCommand(Request request) {
		EditPart combinedFragmentCompartment = getHost().getParent();
		EditPart combinedFragment = combinedFragmentCompartment.getParent();
		EditPart interactionCompartment = combinedFragment.getParent();
		if(REQ_CREATE.equals(request.getType()) && request instanceof CreateUnspecifiedTypeRequest) {
			if(UMLElementTypes.InteractionOperand_3005.equals(((CreateUnspecifiedTypeRequest)request).getElementTypes().get(0))) {
				return combinedFragmentCompartment.getCommand(request);
			/* apex replaced
			} else if(UMLElementTypes.CombinedFragment_3004.equals(((CreateUnspecifiedTypeRequest)request).getElementTypes().get(0))) {
				return interactionCompartment.getCommand(request);
			// */
			} else if(UMLElementTypes.Lifeline_3001.equals(((CreateUnspecifiedTypeRequest)request).getElementTypes().get(0))) {
				return interactionCompartment.getCommand(request);
			}
		} else if(request instanceof CreateConnectionViewAndElementRequest) {
			CreateConnectionRequest createConnectionRequest = (CreateConnectionRequest)request;
			if(getHost().equals(createConnectionRequest.getSourceEditPart())) {
				createConnectionRequest.setSourceEditPart(combinedFragment);
			}
			if(getHost().equals(createConnectionRequest.getTargetEditPart())) {
				createConnectionRequest.setTargetEditPart(combinedFragment);
			}
			return combinedFragment.getCommand(request);
		} else if (request instanceof CreateViewAndElementRequest  ) {
			//FIXME If necessary
			return null;
		}else if (REQ_RESIZE_CHILDREN.equals(request.getType())){
			return interactionCompartment.getCommand(request);
		} 
		 /* apex added start */
		else if (REQ_ORPHAN_CHILDREN.equals(request.getType())) {
			request.setType(REQ_MOVE_CHILDREN);
			return getResizeChildrenCommand((ChangeBoundsRequest)request);
		}
		/* apex added end */
		return super.getCommand(request);
	}
	
	/**
	 * apex updated
	 * 
	 * Handle combined fragment resize
	 */
	@Override
	protected Command getResizeChildrenCommand(ChangeBoundsRequest request) {
		CompoundCommand compoundCmd = new CompoundCommand();
		compoundCmd.setLabel("Move or Resize");

		for(Object o : request.getEditParts()) {
			GraphicalEditPart child = (GraphicalEditPart)o;
			Object constraintFor = getConstraintFor(request, child);
			if(constraintFor != null) {
				if(child instanceof CombinedFragmentEditPart) {
					Command resizeChildrenCommand = InteractionCompartmentXYLayoutEditPolicy.getCombinedFragmentResizeChildrenCommand(request, (CombinedFragmentEditPart)child);
					if(resizeChildrenCommand != null && resizeChildrenCommand.canExecute()) {
						compoundCmd.add(resizeChildrenCommand);
					}
					/* apex added start */
					else compoundCmd.add(UnexecutableCommand.INSTANCE);
					/* apex added end */
				}

				Command changeConstraintCommand = createChangeConstraintCommand(request, child, translateToModelConstraint(constraintFor));
				compoundCmd.add(changeConstraintCommand);
			}
		}
		if(compoundCmd.isEmpty()) {
			return null;
		}
		return compoundCmd.unwrap();
	}

//	/**
//	 * Handle combined fragment resize
//	 */
//	@Override
//	protected Command getResizeChildrenCommand(ChangeBoundsRequest request) {
//		CompoundCommand compoundCmd = new CompoundCommand();
//		compoundCmd.setLabel("Move or Resize");
//
//		for(Object o : request.getEditParts()) {
//			GraphicalEditPart child = (GraphicalEditPart)o;
//			Object constraintFor = getConstraintFor(request, child);
//			if(constraintFor != null) {
//				if(child instanceof CombinedFragmentEditPart) {
//					Command resizeChildrenCommand = InteractionCompartmentXYLayoutEditPolicy.getCombinedFragmentResizeChildrenCommand(request, (CombinedFragmentEditPart)child);
//					if(resizeChildrenCommand != null && resizeChildrenCommand.canExecute()) {
//						compoundCmd.add(resizeChildrenCommand);
//					}
//				}
//
//				Command changeConstraintCommand = createChangeConstraintCommand(request, child, translateToModelConstraint(constraintFor));
//				compoundCmd.add(changeConstraintCommand);
//			}
//		}
//		if(compoundCmd.isEmpty()) {
//			return null;
//		}
//		return compoundCmd.unwrap();
//	}

}
