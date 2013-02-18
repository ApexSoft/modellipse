package org.eclipse.papyrus.uml.diagram.sequence.apex.edit.policies;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.Border;
import org.eclipse.draw2d.Cursors;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.SharedCursors;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ResizableEditPolicy;
import org.eclipse.gef.handles.AbstractHandle;
import org.eclipse.gef.handles.MoveHandle;
import org.eclipse.gef.handles.MoveHandleLocator;
import org.eclipse.gef.handles.RelativeHandleLocator;
import org.eclipse.gef.handles.ResizeHandle;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.papyrus.uml.diagram.sequence.edit.parts.CombinedFragmentCombinedFragmentCompartmentEditPart;
import org.eclipse.papyrus.uml.diagram.sequence.edit.parts.InteractionOperandEditPart;
import org.eclipse.papyrus.uml.diagram.sequence.util.OperandBoundsComputeHelper;

public class ApexInteractionOperandDragEditPolicy extends ResizableEditPolicy {

	/**
	 * Disable drag and allow only north resize. {@inheritDoc}
	 */
	public ApexInteractionOperandDragEditPolicy() {
		super();
		setDragAllowed(false);
		setResizeDirections(PositionConstants.NORTH_SOUTH);
	}

	/**
	 * apex updated
	 */
	@Override
	protected List createSelectionHandles() {
		/* apex improved start */
		List handles = new ArrayList();
		
		boolean isFirstOperand = false;
		boolean isLastOperand = false;
		
		if (getHost() instanceof InteractionOperandEditPart) {
			InteractionOperandEditPart ioEP = (InteractionOperandEditPart)getHost();
			List children = ioEP.getParent().getChildren();
			if (children.size() > 0) {
				if (ioEP.equals(children.get(0))) {
					isFirstOperand = true;
				}
				if (ioEP.equals(children.get(children.size() - 1))) {
					isLastOperand = true;
				}
			}
			
			createMoveHandle(handles, ioEP);
			if (!isFirstOperand) {
				createResizeHandle(handles, PositionConstants.NORTH, ioEP);
			}
//			if (!isLastOperand) {
				createResizeHandle(handles, PositionConstants.SOUTH, ioEP);
//			}
		}
		
		for (int i = 0, size = handles.size(); i < size; i++) {
			if (handles.get(i) instanceof AbstractHandle) {
				AbstractHandle handle = (AbstractHandle)handles.get(i);
				Border border = handle.getBorder();
				if (border instanceof LineBorder) {
					((LineBorder)border).setWidth(2);
				}
			}
		}
		
		return handles;
		/* apex improved end */
		/* apex removed
		return super.createSelectionHandles();
		*/
	}
	
	/**
	 * Creates a 'move' handle
	 * @param handles
	 * @param host
	 */
	private void createMoveHandle(List handles, InteractionOperandEditPart host) {
		MoveHandleLocator locator = new MoveHandleLocator(host.getPrimaryShape());
		MoveHandle moveHandle = new MoveHandle(host, locator);
		
		if (isDragAllowed()) {
			moveHandle.setDragTracker(getDragTracker());
			moveHandle.setCursor(Cursors.SIZEALL);
		} else {
			moveHandle.setDragTracker(getSelectTracker());
			moveHandle.setCursor(SharedCursors.ARROW);
		}
		handles.add(moveHandle);
	}
	
	/**
	 * Creates a 'resize' handle
	 * @param handles
	 * @param host
	 */
	private void createResizeHandle(List handles, int direction, InteractionOperandEditPart host) {
		if ((getResizeDirections() & direction) == direction) {
			RelativeHandleLocator locator = new RelativeHandleLocator(host.getPrimaryShape(), direction);
			ResizeHandle resizeHandle = new ResizeHandle(host, locator, Cursors
					.getDirectionalCursor(direction, getHostFigure().isMirrored()));
			resizeHandle.setDragTracker(getResizeTracker(direction));
			handles.add(resizeHandle);
		} else {
			createDragHandle(handles, direction, host);
		}
	}
	
	/**
	 * Creates a 'drag' handle
	 * @param handles
	 * @param direction
	 * @param host
	 */
	private void createDragHandle(List handles, int direction, InteractionOperandEditPart host) {
		RelativeHandleLocator locator = new RelativeHandleLocator(host.getPrimaryShape(), direction);
		ResizeHandle handle = new ResizeHandle(host, locator, null);
		
		if (isDragAllowed()) {
			handle.setDragTracker(getDragTracker());
			handle.setCursor(Cursors.SIZEALL);
		} else {
			handle.setDragTracker(getSelectTracker());
			handle.setCursor(SharedCursors.ARROW);
		}
		handles.add(handle);
	}
	
	/**
	 * apex updated
	 * 최상단 Op의 상변에서의 처리도 OperandBoundsComputeHelper에서 모두 처리하도록 변경
	 * 
	 * Handle resize InteractionOperand {@inheritDoc}
	 */
	@Override
	protected Command getResizeCommand(ChangeBoundsRequest request) {
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
	}

}
