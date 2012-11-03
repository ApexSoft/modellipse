package org.eclipse.papyrus.uml.diagram.sequence.apex.figures;


import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.gmf.runtime.gef.ui.figures.DefaultSizeNodeFigure;
import org.eclipse.papyrus.uml.diagram.sequence.apex.draw2d.anchors.ApexHorizontalAnchor;

/**
 * @author Jiho
 *
 */
public class ApexCustomDefaultSizeNodeFigure extends DefaultSizeNodeFigure {

	public ApexCustomDefaultSizeNodeFigure(Dimension defSize) {
		super(defSize);
	}

	public ApexCustomDefaultSizeNodeFigure(int width, int height) {
		super(width, height);
	}

	@Override
	protected ConnectionAnchor createDefaultAnchor() {
		return new ApexHorizontalAnchor(this);
	}

	@Override
	protected ConnectionAnchor createAnchor(PrecisionPoint p) {
		if (p==null)
			return createDefaultAnchor();
		return new ApexHorizontalAnchor(this, p);
	}

}
