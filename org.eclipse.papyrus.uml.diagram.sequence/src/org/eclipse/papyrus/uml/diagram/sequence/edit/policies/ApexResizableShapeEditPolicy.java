package org.eclipse.papyrus.uml.diagram.sequence.edit.policies;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.ResizableShapeEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.internal.requests.ChangeBoundsDeferredRequest;
import org.eclipse.gmf.runtime.diagram.ui.requests.RequestConstants;

/**
 * 이동 방향에 제한을 둘 수 있는 ResizableShapedEditPolicy
 * @author Jiho
 */
public class ApexResizableShapeEditPolicy extends ResizableShapeEditPolicy {

	private int moveDirection;

	private boolean autoSizeEnabled = true;

	private boolean moveDeferredEnabled = true;

	/**
	 * Constructor
	 * 
	 * @param moveDirection
	 * 				the direction of the move
	 */
	public ApexResizableShapeEditPolicy(int moveDirection) {
		super();
		setMoveDirection(moveDirection);
	}

	public ApexResizableShapeEditPolicy(int moveDirection, boolean autoSizeEnabled) {
		super();
		setMoveDirection(moveDirection);
		setAutoSizeEnabled(autoSizeEnabled);
	}

	public ApexResizableShapeEditPolicy(int moveDirection, boolean autoSizeEnabled, boolean moveDeferredEnabled) {
		super();
		setMoveDirection(moveDirection);
		setAutoSizeEnabled(autoSizeEnabled);
		setMoveDeferredEnabled(moveDeferredEnabled);
	}

	public ApexResizableShapeEditPolicy(int moveDirection, int resizeDirection, boolean autoSizeEnabled, boolean moveDeferredEnabled) {
		super();
		setMoveDirection(moveDirection);
		setAutoSizeEnabled(autoSizeEnabled);
		setMoveDeferredEnabled(moveDeferredEnabled);
	}

	@Override
	protected void showChangeBoundsFeedback(ChangeBoundsRequest request) {
		Point moveDelta = request.getMoveDelta();
//		if ((moveDirection & PositionConstants.EAST) == 0 && moveDelta.x < 0)
//			moveDelta.x = 0;
//		if ((moveDirection & PositionConstants.WEST) == 0 && moveDelta.x > 0)
//			moveDelta.x = 0;
//		if ((moveDirection & PositionConstants.SOUTH) == 0 && moveDelta.y < 0)
//			moveDelta.y = 0;
//		if ((moveDirection & PositionConstants.NORTH) == 0 && moveDelta.y > 0)
//			moveDelta.y = 0;
		request.setMoveDelta(moveDelta);
		super.showChangeBoundsFeedback(request);
	}

	@Override
	protected Command getMoveCommand(ChangeBoundsRequest request) {
		ChangeBoundsRequest req = new ChangeBoundsRequest(REQ_MOVE_CHILDREN);
		req.setEditParts(getHost());
		req.setMoveDelta(request.getMoveDelta());
		req.setSizeDelta(request.getSizeDelta());
		req.setLocation(request.getLocation());
		req.setExtendedData(request.getExtendedData());
		req.setResizeDirection(moveDirection);
		return getHost().getParent().getCommand(req);
	}

	@Override
	protected Command getResizeCommand(ChangeBoundsRequest request) {
		ChangeBoundsRequest req = new ChangeBoundsRequest(REQ_RESIZE_CHILDREN);
		req.setEditParts(getHost());

		req.setMoveDelta(request.getMoveDelta());
		req.setSizeDelta(request.getSizeDelta());
		req.setLocation(request.getLocation());
		req.setExtendedData(request.getExtendedData());
		req.setResizeDirection(request.getResizeDirection());
		return getHost().getParent().getCommand(req);
	}

	/**
	 * apex update
	 * 
	 * autoSize, moveDefer 통제 추가
	 */
	@Override
	public Command getCommand(Request request) {
		/* apex improved start */
		if (RequestConstants.REQ_AUTOSIZE.equals(request.getType()) && autoSizeEnabled) {

			return getAutoSizeCommand(request);
		}

		if (RequestConstants.REQ_MOVE_DEFERRED.equals(request.getType()) && moveDeferredEnabled) {

			return getMoveDeferredCommand((ChangeBoundsDeferredRequest) request);
		}

		return super.getCommand(request);
		/* apex improved start */

		/* apex replaced
		if (RequestConstants.REQ_AUTOSIZE.equals(request.getType()))
			return getAutoSizeCommand(request);
		if (RequestConstants.REQ_MOVE_DEFERRED.equals(request.getType()))
			return getMoveDeferredCommand((ChangeBoundsDeferredRequest) request);
		return super.getCommand(request);
		*/
	}

	/**
	 * Returns the direction the figure is being moved. Possible values are
	 * <ul>
	 * <li>{@link org.eclipse.draw2d.PositionConstants#EAST}
	 * <li>{@link org.eclipse.draw2d.PositionConstants#WEST}
	 * <li>{@link org.eclipse.draw2d.PositionConstants#NORTH}
	 * <li>{@link org.eclipse.draw2d.PositionConstants#SOUTH}
	 * <li>{@link org.eclipse.draw2d.PositionConstants#NORTH_EAST}
	 * <li>{@link org.eclipse.draw2d.PositionConstants#NORTH_WEST}
	 * <li>{@link org.eclipse.draw2d.PositionConstants#SOUTH_EAST}
	 * <li>{@link org.eclipse.draw2d.PositionConstants#SOUTH_WEST}
	 * </ul>
	 * 
	 * @return the move direction
	 */
	public int getMoveDirection() {
		return moveDirection;
	}

	/**
	 * Sets the direction the figure is being moved.
	 * @param moveDirection
	 */
	public void setMoveDirection(int moveDirection) {
		this.moveDirection = moveDirection;
	}

	public boolean isAutoSizeEnabled() {
		return autoSizeEnabled;
	}

	public void setAutoSizeEnabled(boolean autoSizeEnabled) {
		this.autoSizeEnabled = autoSizeEnabled;
	}

	public boolean isMoveDeferredEnabled() {
		return moveDeferredEnabled;
	}

	public void setMoveDeferredEnabled(boolean moveDeferredEnabled) {
		this.moveDeferredEnabled = moveDeferredEnabled;
	}

}