package org.eclipse.papyrus.uml.diagram.sequence.draw2d.anchors;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.gmf.runtime.gef.ui.figures.SlidableAnchor;

/**
 * @author Jiho
 *
 */
public class ApexHorizontalAnchor extends SlidableAnchor {

	public ApexHorizontalAnchor(IFigure f, PrecisionPoint p) {
		super(f, p);
	}

	public ApexHorizontalAnchor(IFigure f) {
		super(f);
	}

	@Override
	public boolean isDefaultAnchor() {
		return false;
	}

	@Override
	public Point getLocation(Point reference) {
		Point ownReference = getReferencePoint();

		Point location = getLocation(ownReference, reference);
		if (location == null) {
			location = getLocation(new PrecisionPoint(getBox().getCenter()), reference);
			if (location == null) {
				location = getBox().getCenter();
			}
		}
		
		return location;
	}

}
