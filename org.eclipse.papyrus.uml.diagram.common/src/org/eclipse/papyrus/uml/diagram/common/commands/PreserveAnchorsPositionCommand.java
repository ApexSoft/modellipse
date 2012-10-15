/*******************************************************************************
 * Copyright (c) 2009 Conselleria de Infraestructuras y Transporte, Generalitat 
 * de la Comunitat Valenciana . All rights reserved. This program
 * and the accompanying materials are made available under the terms of the
 * Eclipse Public License v1.0 which accompanies this distribution, and is
 * available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Gabriel Merin Cubero (Prodevelop) – Sequence Diagram Implementation
 *
 ******************************************************************************/
package org.eclipse.papyrus.uml.diagram.common.commands;

import java.util.List;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.Request;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.draw2d.ui.figures.BaseSlidableAnchor;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.IdentityAnchor;
import org.eclipse.gmf.runtime.notation.View;

/**
 * This class modifies the anchors of the edges connected to the passed element
 * so that they can preserve their position after the resize of the figure. If
 * any of the anchors does not fit in the new size, it will be positioned at the
 * nearest bound.
 * 
 * This class allows to preserve the position in the Y axis, in the X axis or in
 * both axis.
 * 
 * @author gmerin
 * 
 */
@SuppressWarnings("unchecked")
public class PreserveAnchorsPositionCommand extends AbstractTransactionalCommand {

	// The Shape being resized
	private ShapeNodeEditPart shapeEP;

	// The size delta aplied to the shape
	private Dimension sizeDelta;

	private int preserveAxis;

	//
	private IFigure figure;

	// The resize direction
	private int resizeDirection;

	// Command's label
	protected final static String COMMAND_LABEL = "Modify Anchors to Preserve Position";

	// Command's error message
	protected final static String COMMAND_ERROR_MESSAGE = "One of the anchors is left outside of the new figure's size";

	// Constants to describe which axis position should be preserved
	public final static int PRESERVE_Y = 0;

	public final static int PRESERVE_X = 1;

	public final static int PRESERVE_XY = 2;

	/**
	 * Constructor. It needs the shape being resized, it's re-size delta and the
	 * axis where the position should be preserved. The different preserveAxis
	 * values are the following:
	 * <ul>
	 * <li>ModifyAnchorsToPreservePosition.PRESERVE_Y</li>
	 * <li>ModifyAnchorsToPreservePosition.PRESERVE_X</li>
	 * <li>ModifyAnchorsToPreservePosition.PRESERVE_XY</li>
	 * </ul>
	 * 
	 * @param shapeEP
	 *        the ShapeNodeEditPart that is being resized
	 * @param sizeDelta
	 *        the re-size delta
	 * @param preserveAxis
	 *        the axis where the position should be preserved. If the given
	 *        value is not valid, then PRESERVE_Y will be taken as default
	 */
	public PreserveAnchorsPositionCommand(ShapeNodeEditPart shapeEP, Dimension sizeDelta, int preserveAxis) {
		super(shapeEP.getEditingDomain(), COMMAND_LABEL, null);
		setShapeEP(shapeEP);
		setSizeDelta(sizeDelta);
		setPreserveAxis(preserveAxis);
	}

	/**
	 * Constructor. It needs the shape being resized, it's re-size delta and the
	 * axis where the position should be preserved. The different preserveAxis
	 * values are the following:
	 * <ul>
	 * <li>ModifyAnchorsToPreservePosition.PRESERVE_Y</li>
	 * <li>ModifyAnchorsToPreservePosition.PRESERVE_X</li>
	 * <li>ModifyAnchorsToPreservePosition.PRESERVE_XY</li>
	 * </ul>
	 * 
	 * 
	 * @param shapeEP
	 *        the ShapeNodeEditPart that is being resized
	 * @param sizeDelta
	 *        the re-size delta
	 * @param preserveAxis
	 *        the axis where the position should be preserved. If the given
	 *        value is not valid, then PRESERVE_Y will be taken as default
	 * @param figure
	 *        the figure where the anchors are (when it is not the
	 *        getShapeEP().getFigure()).
	 * @param resizeDirection
	 *        the resize direction. Possible values are
	 *        <ul>
	 *        <li>{@link org.eclipse.draw2d.PositionConstants#EAST}
	 *        <li>{@link org.eclipse.draw2d.PositionConstants#WEST}
	 *        <li>{@link org.eclipse.draw2d.PositionConstants#NORTH}
	 *        <li>{@link org.eclipse.draw2d.PositionConstants#SOUTH}
	 *        <li>{@link org.eclipse.draw2d.PositionConstants#NORTH_EAST}
	 *        <li>{@link org.eclipse.draw2d.PositionConstants#NORTH_WEST}
	 *        <li>{@link org.eclipse.draw2d.PositionConstants#SOUTH_EAST}
	 *        <li>{@link org.eclipse.draw2d.PositionConstants#SOUTH_WEST}
	 *        </ul>
	 */
	public PreserveAnchorsPositionCommand(ShapeNodeEditPart shapeEP, Dimension sizeDelta, int preserveAxis, IFigure figure, int resizeDirection) {
		this(shapeEP, sizeDelta, preserveAxis);
		this.figure = figure;
		this.resizeDirection = resizeDirection;
	}

	/**
	 * Set the new value of the preserveAxis property
	 * 
	 * @param preserveAxis
	 *        the new preserveAxis value
	 */
	public void setPreserveAxis(int preserveAxis) {
		if(preserveAxis != PRESERVE_Y && preserveAxis != PRESERVE_X && preserveAxis != PRESERVE_XY) {
			this.preserveAxis = PRESERVE_Y;
		} else {
			this.preserveAxis = preserveAxis;
		}
	}

	/**
	 * Return the current value of the preserveAxis property
	 * 
	 * @return preserveAxis current value
	 */
	public int getPreserveAxis() {
		return preserveAxis;
	}

	/**
	 * Set the new value of the ShapeNodeEditPart property
	 * 
	 * @param shapeEP
	 */
	public void setShapeEP(ShapeNodeEditPart shapeEP) {
		this.shapeEP = shapeEP;
	}

	/**
	 * Return the current value of the ShapeNodeEditPart property
	 * 
	 * @return shapeEP
	 */
	public ShapeNodeEditPart getShapeEP() {
		return shapeEP;
	}

	/**
	 * Return the bounds of the ShapeNodeEditPart's figure
	 * 
	 * @return The bounds
	 */
	public Rectangle getFigureBounds() {
		if(figure != null) {
			return figure.getBounds();
		}
		return getShapeEP().getFigure().getBounds();
	}

	/**
	 * Return's the view associated with the ShapeNodeEditPart
	 * 
	 * @return The View
	 */
	public View getView() {
		return (View)getShapeEP().getModel();
	}

	/**
	 * Sets the new size delta property
	 * 
	 * @param sizeDelta
	 *        the new sizeDelta value
	 */
	protected void setSizeDelta(Dimension sizeDelta) {
		this.sizeDelta = sizeDelta;
	}

	/**
	 * Returns the current size delta property
	 * 
	 * @return The size delta
	 */
	public Dimension getSizeDelta() {
		return sizeDelta;
	}

	/**
	 * Execution of the command
	 */
	@Override
	protected CommandResult doExecuteWithResult(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {

		View view = getView();

		List<Edge> sourceList = ViewUtil.getSourceConnections(view);
		List<Edge> targetList = ViewUtil.getTargetConnections(view);

		for(Edge edge : sourceList) {
			IdentityAnchor anchor = (IdentityAnchor)edge.getSourceAnchor();
			if(anchor != null) {
				anchor.setId(getNewIdStr(anchor));
			}
		}

		for(Edge edge : targetList) {
			IdentityAnchor anchor = (IdentityAnchor)edge.getTargetAnchor();
			if(anchor != null) {
				anchor.setId(getNewIdStr(anchor));
			}
		}

		return CommandResult.newOKCommandResult();
	}

	/**
	 * Returns the new anchor's position to preserve it's position after
	 * 
	 * @param anchor
	 * @return the new IdStr
	 */
	protected String getNewIdStr(IdentityAnchor anchor) {
		Dimension sizeDelta = getSizeDelta();
		Rectangle figureBounds = getFigureBounds();

		PrecisionPoint pp = BaseSlidableAnchor.parseTerminalString(anchor.getId());

		if(getPreserveAxis() == PRESERVE_Y || getPreserveAxis() == PRESERVE_XY) {
			int anchorYPos = (int)Math.round(figureBounds.height * pp.preciseY);

			pp.preciseY = (double)anchorYPos / (figureBounds.height + sizeDelta.height);

			// If the resize direction is NORTH, the location of the figure
			// move, but the anchor stay visually at the same location
			if(PositionConstants.NORTH == resizeDirection || PositionConstants.NORTH_EAST == resizeDirection || PositionConstants.NORTH_WEST == resizeDirection) {
				pp.preciseY = pp.preciseY + ((double)sizeDelta.height / (figureBounds.height + sizeDelta.height));
			}

			if(pp.preciseY > 1.0) {
				pp.preciseY = 1.0;
			} else if(pp.preciseY < 0.0) {
				pp.preciseY = 0.0;
			}
		}

		if(getPreserveAxis() == PRESERVE_X || getPreserveAxis() == PRESERVE_XY) {
			int anchorXPos = (int)Math.round(figureBounds.width * pp.preciseX);

			pp.preciseX = (double)anchorXPos / (figureBounds.width + sizeDelta.width);

			// If the resize direction is WEST, the location of the figure move,
			// but the anchor stay visually at the same location
			if(PositionConstants.WEST == resizeDirection || PositionConstants.NORTH_WEST == resizeDirection || PositionConstants.SOUTH_WEST == resizeDirection) {
				pp.preciseX = pp.preciseX + ((double)sizeDelta.width / (figureBounds.width + sizeDelta.width));
			}

			if(pp.preciseX > 1.0) {
				pp.preciseX = 1.0;
			} else if(pp.preciseX < 0.0) {
				pp.preciseX = 0.0;
			}
		}

		String idStr = (new BaseSlidableAnchor(null, pp)).getTerminal();
		return idStr;
	}

	/**
	 * This operation checks if, after resizing the ShapeNodeEditPart, all links
	 * anchors will fit inside the figure in case their positions are preserved
	 * 
	 * @param shapeEP
	 *        That shape being resized
	 * @param sizeDelta
	 *        The SizeDelta for the resize
	 * @param preserveAxis
	 *        The axisxxx
	 * @return The new SizeDelta to preserve anchors' positions
	 */
	public static Dimension getSizeDeltaToFitAnchors(ShapeNodeEditPart shapeEP, Dimension sizeDelta, int preserveAxis) {

		Dimension newSizeDelta = new Dimension(sizeDelta);
		View view = (View)shapeEP.getModel();
		Rectangle figureBounds = shapeEP.getFigure().getBounds();

		List<Edge> sourceList = ViewUtil.getSourceConnections(view);
		List<Edge> targetList = ViewUtil.getTargetConnections(view);

		for(Edge edge : sourceList) {
			IdentityAnchor anchor = (IdentityAnchor)edge.getSourceAnchor();
			modifySizeDeltaToFitAnchor(anchor, newSizeDelta, preserveAxis, figureBounds);
		}
		for(Edge edge : targetList) {
			IdentityAnchor anchor = (IdentityAnchor)edge.getTargetAnchor();
			modifySizeDeltaToFitAnchor(anchor, newSizeDelta, preserveAxis, figureBounds);
		}

		return newSizeDelta;
	}

	/**
	 * Used inside the getSizeDeltaToFitAnchors operation. It's goal is to
	 * modify a SizeDelta in order to keep fitting an anchor within the
	 * figureBounds
	 * 
	 * @param anchor
	 *        The anchor whose position will be kept
	 * @param sizeDelta
	 * @param preserveAxis
	 * @param figureBounds
	 */
	protected static void modifySizeDeltaToFitAnchor(IdentityAnchor anchor, Dimension sizeDelta, int preserveAxis, Rectangle figureBounds) {

		if(anchor == null) {
			return;
		}

		PrecisionPoint pp = BaseSlidableAnchor.parseTerminalString(anchor.getId());

		int margin = 6;

		if(preserveAxis == PRESERVE_Y || preserveAxis == PRESERVE_XY) {
			int anchorYPos = (int)Math.round(figureBounds.height * pp.preciseY);

			int newHeight = figureBounds.height + sizeDelta.height;

			if(anchorYPos + margin > newHeight) {
				sizeDelta.height = (anchorYPos - figureBounds.height) + margin;
			}
		}

		if(preserveAxis == PRESERVE_X || preserveAxis == PRESERVE_XY) {
			int anchorXPos = (int)Math.round(figureBounds.width * pp.preciseX);

			int newWidth = figureBounds.width + sizeDelta.width;

			if(anchorXPos + margin > newWidth) {
				sizeDelta.width = (anchorXPos - figureBounds.width) + margin;
			}
		}
	}

	/**
	 * Creations of a new request in order to have a correct visualization of
	 * the feedback in order to preserve links's anchors.
	 * 
	 * @param request
	 * @param editPart
	 * @return a replication of the request but with a SizeDelta modification
	 */
	// @unused
	public static Request getNewSourceFeedbackRequest(Request request, ShapeNodeEditPart editPart) {
		if(request instanceof ChangeBoundsRequest) {
			ChangeBoundsRequest currRequest = (ChangeBoundsRequest)request;
			Dimension oldDelta = currRequest.getSizeDelta();
			Dimension newDelta = getSizeDeltaToFitAnchors(editPart, oldDelta, PreserveAnchorsPositionCommand.PRESERVE_Y);
			// Information for creating a new ChangeBoundsRequest has been taken
			// from org.eclipse.gef.editpolicies.ResizableEditPolicy
			ChangeBoundsRequest newRequest = new ChangeBoundsRequest();
			newRequest.setMoveDelta(currRequest.getMoveDelta());
			newRequest.setSizeDelta(newDelta);
			newRequest.setLocation(currRequest.getLocation());
			newRequest.setExtendedData(currRequest.getExtendedData());
			newRequest.setResizeDirection(currRequest.getResizeDirection());
			newRequest.setType(currRequest.getType());
			return newRequest;
		} else {
			return request;
		}
	}

}
