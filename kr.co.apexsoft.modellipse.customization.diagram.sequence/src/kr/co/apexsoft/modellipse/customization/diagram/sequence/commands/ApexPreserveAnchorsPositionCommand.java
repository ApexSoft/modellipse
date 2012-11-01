package kr.co.apexsoft.modellipse.customization.diagram.sequence.commands;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.draw2d.ui.figures.BaseSlidableAnchor;
import org.eclipse.gmf.runtime.notation.IdentityAnchor;
import org.eclipse.papyrus.uml.diagram.common.commands.PreserveAnchorsPositionCommand;

/**
 * @author Jiho
 *
 */
public class ApexPreserveAnchorsPositionCommand
extends PreserveAnchorsPositionCommand {

	private Rectangle relativeBounds;

	public ApexPreserveAnchorsPositionCommand(ShapeNodeEditPart shapeEP,
			Dimension sizeDelta, int preserveAxis, IFigure figure, int resizeDirection, Object relative) {
		super(shapeEP, sizeDelta, preserveAxis, figure, resizeDirection);
		setRelative(relative);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.papyrus.uml.diagram.common.commands.PreserveAnchorsPositionCommand#getNewIdStr(org.eclipse.gmf.runtime.notation.IdentityAnchor)
	 */
	protected String getNewIdStr(IdentityAnchor anchor) {
		if (relativeBounds == null) {
			return super.getNewIdStr(anchor);
		}
		
		Dimension sizeDelta = getSizeDelta();
		Rectangle figureBounds = getFigureBounds();

		PrecisionPoint pp = BaseSlidableAnchor.parseTerminalString(anchor.getId());

		int resizeDirection = getResizeDirection();

		if(getPreserveAxis() == PRESERVE_Y || getPreserveAxis() == PRESERVE_XY) {
			int anchorYPos = (int)Math.round(figureBounds.height * pp.preciseY());

			/* apex added start */
			if ((PositionConstants.SOUTH & resizeDirection) != 0 && relativeBounds.bottom() < figureBounds.y + anchorYPos) {
				resizeDirection &= ~PositionConstants.SOUTH;
				resizeDirection ^= PositionConstants.NORTH;
			}
			else if ((PositionConstants.NORTH & resizeDirection) != 0 && relativeBounds.y > figureBounds.y + anchorYPos) {
				resizeDirection &= ~PositionConstants.SOUTH;
				resizeDirection ^= PositionConstants.NORTH;
			}
			/* apex added end */

			pp.setPreciseY((double)anchorYPos / (figureBounds.height + sizeDelta.height));

			// If the resize direction is NORTH, the location of the figure
			// move, but the anchor stay visually at the same location
			if((PositionConstants.NORTH & resizeDirection) != 0) {
				pp.setPreciseY(pp.preciseY() + ((double)sizeDelta.height / (figureBounds.height + sizeDelta.height)));
			}

			if(pp.preciseY() > 1.0) {
				pp.setPreciseY(1.0);
			} else if(pp.preciseY() < 0.0) {
				pp.setPreciseY(0.0);
			}
		}

		if(getPreserveAxis() == PRESERVE_X || getPreserveAxis() == PRESERVE_XY) {
			int anchorXPos = (int)Math.round(figureBounds.width * pp.preciseX());

			/* apex added start */
			if ((PositionConstants.EAST & resizeDirection) != 0 && relativeBounds.right() < figureBounds.x + anchorXPos) {
				resizeDirection &= ~PositionConstants.EAST;
				resizeDirection ^= PositionConstants.WEST;
			}
			else if ((PositionConstants.WEST & resizeDirection) != 0 && relativeBounds.x > figureBounds.x + anchorXPos) {
				resizeDirection &= ~PositionConstants.WEST;
				resizeDirection ^= PositionConstants.EAST;
			}
			/* apex added end */

			pp.setPreciseX((double)anchorXPos / (figureBounds.width + sizeDelta.width));

			// If the resize direction is WEST, the location of the figure move,
			// but the anchor stay visually at the same location
			if((PositionConstants.WEST & resizeDirection) != 0) {
				pp.setPreciseX(pp.preciseX() + ((double)sizeDelta.width / (figureBounds.width + sizeDelta.width)));
			}

			if(pp.preciseX() > 1.0) {
				pp.setPreciseX(1.0);
			} else if(pp.preciseX() < 0.0) {
				pp.setPreciseX(0.0);
			}
		}

		String idStr = (new BaseSlidableAnchor(null, pp)).getTerminal();
		return idStr;
	}

	public void setRelative(Object relative) {
		if (relative instanceof Rectangle) {
			this.relativeBounds = ((Rectangle)relative).getCopy();
		}
		else if (relative instanceof Point)
			this.relativeBounds = new Rectangle((Point)relative, new Dimension());
	}
}