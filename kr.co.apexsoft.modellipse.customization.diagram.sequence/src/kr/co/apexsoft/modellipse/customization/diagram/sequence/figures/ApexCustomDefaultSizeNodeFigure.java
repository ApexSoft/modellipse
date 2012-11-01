package kr.co.apexsoft.modellipse.customization.diagram.sequence.figures;

import kr.co.apexsoft.modellipse.customization.diagram.sequence.draw2d.anchors.ApexHorizontalAnchor;

import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.gmf.runtime.gef.ui.figures.DefaultSizeNodeFigure;

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
