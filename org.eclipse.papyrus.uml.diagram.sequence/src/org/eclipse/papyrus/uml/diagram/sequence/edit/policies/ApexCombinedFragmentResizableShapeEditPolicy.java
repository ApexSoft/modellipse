package org.eclipse.papyrus.uml.diagram.sequence.edit.policies;

import java.util.HashMap;
import java.util.Map;

import kr.co.apexsoft.modellipse.customization.diagram.sequence.util.ApexSequenceRequestConstants;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PrecisionRectangle;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.ResizableShapeEditPolicy;
import org.eclipse.gmf.runtime.draw2d.ui.mapmode.IMapMode;
import org.eclipse.gmf.runtime.draw2d.ui.mapmode.MapModeUtil;

public class ApexCombinedFragmentResizableShapeEditPolicy extends
		ResizableShapeEditPolicy {

	private int moveDirection;

	private int resizeDirections;


	public ApexCombinedFragmentResizableShapeEditPolicy(int moveDirection, int resizeDirections) {
		super();
		setMoveDirection(moveDirection);
		setResizeDirections(resizeDirections);
	}

	@Override
	protected void showChangeBoundsFeedback(ChangeBoundsRequest request) {

		Point moveDelta = request.getMoveDelta();
		Dimension sizeDelta = request.getSizeDelta();
		int direction = request.getResizeDirection();

		Point movePoint = moveDelta;

		IFigure feedback = getDragSourceFeedbackFigure();
        
        PrecisionRectangle rect = new PrecisionRectangle(getInitialFeedbackBounds().getCopy());
        getHostFigure().translateToAbsolute(rect);
        
        if ( sizeDelta.equals(0, 0) && moveDelta.x != 0 ) {
			request.setMoveDelta(new Point(0, moveDelta.y));
		}
        rect.translate(request.getMoveDelta());
        rect.resize(request.getSizeDelta());
        
        IFigure f = getHostFigure();
        Dimension min = f.getMinimumSize().getCopy();
        Dimension max = f.getMaximumSize().getCopy();
        IMapMode mmode = MapModeUtil.getMapMode(f);
        min.height = mmode.LPtoDP(min.height);
        min.width = mmode.LPtoDP(min.width);
        max.height = mmode.LPtoDP(max.height);
        max.width = mmode.LPtoDP(max.width);
        
        if (min.width>rect.width)
            rect.width = min.width;
        else if (max.width < rect.width)
            rect.width = max.width;
        
        if (min.height>rect.height)
            rect.height = min.height;
        else if (max.height < rect.height)
            rect.height = max.height;
        
        feedback.translateToRelative(rect);
        feedback.setBounds(rect);		
	}

	@Override
	protected Command getMoveCommand(ChangeBoundsRequest request) {
		ChangeBoundsRequest req = new ChangeBoundsRequest(REQ_MOVE_CHILDREN);
		req.setEditParts(getHost());
		req.setMoveDelta(request.getMoveDelta());
		req.setSizeDelta(request.getSizeDelta());
		req.setLocation(request.getLocation());
		req.setExtendedData(request.getExtendedData());
		req.setResizeDirection(request.getResizeDirection());
		return getHost().getParent().getCommand(req);
	}

	/**
	 * Returns the command contribution for the given resize request. By
	 * default, the request is re-dispatched to the host's parent as a
	 * {@link org.eclipse.gef.RequestConstants#REQ_RESIZE_CHILDREN}. The
	 * parent's edit policies determine how to perform the resize based on the
	 * layout manager in use.
	 * 
	 * @param request
	 *            the resize request
	 * @return the command contribution obtained from the parent
	 */
	@Override
	protected Command getResizeCommand(ChangeBoundsRequest request) {
		ChangeBoundsRequest req = new ChangeBoundsRequest(REQ_RESIZE_CHILDREN);
		req.setEditParts(getHost());

		req.setMoveDelta(request.getMoveDelta());
		req.setSizeDelta(request.getSizeDelta());
		req.setLocation(request.getLocation());

		/* apex improved */
		Map apexResizeInfo = new HashMap();
		apexResizeInfo.put(ApexSequenceRequestConstants.APEX_KEY_RESIZING_COMBINEDFRAGMENTEDITPART, getHost());

		req.setExtendedData(apexResizeInfo);
		/* apex improved */
		/* apex replaced
		req.setExtendedData(request.getExtendedData());
		*/
		req.setResizeDirection(request.getResizeDirection());
		return getHost().getParent().getCommand(req);
	}



	/**
	 * @return the moveDirection
	 */
	public int getMoveDirection() {
		return moveDirection;
	}


	/**
	 * @param moveDirection the moveDirection to set
	 */
	public void setMoveDirection(int moveDirection) {
		this.moveDirection = moveDirection;
	}


	/**
	 * @return the resizeDirections
	 */
	public int getResizeDirections() {
		return resizeDirections;
	}


	/**
	 * @param resizeDirections the resizeDirections to set
	 */
	public void setResizeDirections(int resizeDirections) {
		this.resizeDirections = resizeDirections;
	}

}