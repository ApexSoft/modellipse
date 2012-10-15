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

import org.eclipse.draw2d.PositionConstants;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ResizableEditPolicy;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.papyrus.uml.diagram.sequence.edit.parts.CombinedFragmentCombinedFragmentCompartmentEditPart;
import org.eclipse.papyrus.uml.diagram.sequence.edit.parts.InteractionOperandEditPart;
import org.eclipse.papyrus.uml.diagram.sequence.util.OperandBoundsComputeHelper;

/**
 * The customn DragDropEditPolicy for InteractionOperandEditPart.
 */
public class InteractionOperandDragDropEditPolicy extends ResizableEditPolicy {

	/**
	 * Disable drag and allow only south resize. {@inheritDoc}
	 */
	public InteractionOperandDragDropEditPolicy() {
		super();
		setDragAllowed(false);
	}

	/**
	 * apex updated
	 * 최상단 Op의 상변에서의 처리도 OperandBoundsComputeHelper에서 모두 처리하도록 변경
	 * 
	 * Handle resize InteractionOperand {@inheritDoc}
	 */
	@Override
	protected Command getResizeCommand(ChangeBoundsRequest request) {
		/* apex improved start */
		if (this.getHost() instanceof InteractionOperandEditPart
				&& this.getHost().getParent() instanceof CombinedFragmentCombinedFragmentCompartmentEditPart) {
			int heightDelta = request.getSizeDelta().height();
			int widthDelta = request.getSizeDelta().width();
			int direction = request.getResizeDirection();			
			
			InteractionOperandEditPart currentIOEP = (InteractionOperandEditPart)this.getHost();
			CombinedFragmentCombinedFragmentCompartmentEditPart compartEP = (CombinedFragmentCombinedFragmentCompartmentEditPart)this.getHost().getParent();
			
			return OperandBoundsComputeHelper.createIOEPResizeCommand(request, currentIOEP);
		}
		return null;
		/* apex improved end */
		/* apex replaced
		if ((request.getResizeDirection() & PositionConstants.EAST_WEST) != 0) {
			EditPart parent = getHost().getParent().getParent();
			return parent.getCommand(request);
		} else{
			if (this.getHost() instanceof InteractionOperandEditPart
					&& this.getHost().getParent() instanceof CombinedFragmentCombinedFragmentCompartmentEditPart) {
				InteractionOperandEditPart currentIOEP = (InteractionOperandEditPart) this
						.getHost();
				CombinedFragmentCombinedFragmentCompartmentEditPart compartEP = (CombinedFragmentCombinedFragmentCompartmentEditPart) this
						.getHost().getParent();
				// if first interaction operand and resize direction is NORTH
				if(this.getHost() == OperandBoundsComputeHelper.findFirstIOEP(compartEP)&&(request.getResizeDirection() & PositionConstants.NORTH) != 0){ 
					return getHost().getParent().getParent().getCommand(request);
				}else{
					int heightDelta = request.getSizeDelta().height();
					if ((request.getResizeDirection() & PositionConstants.NORTH) != 0) {
						return OperandBoundsComputeHelper.createIOEPResizeCommand(currentIOEP, heightDelta,
								compartEP,PositionConstants.NORTH);
					} else if ((request.getResizeDirection() & PositionConstants.SOUTH) != 0) {
						return OperandBoundsComputeHelper.createIOEPResizeCommand(currentIOEP, heightDelta,
								compartEP,PositionConstants.SOUTH);
					}
				}
			}
			return null;
		}
		*/
	}

}
