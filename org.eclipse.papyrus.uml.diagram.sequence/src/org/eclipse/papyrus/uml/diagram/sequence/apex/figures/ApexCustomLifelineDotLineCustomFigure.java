package org.eclipse.papyrus.uml.diagram.sequence.apex.figures;

import org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure;
import org.eclipse.papyrus.uml.diagram.sequence.figures.LifelineDotLineCustomFigure;

public class ApexCustomLifelineDotLineCustomFigure extends
		LifelineDotLineCustomFigure {

	@Override
	protected NodeFigure apexCreateDashLineRectangle() {
		return new ApexCustomNodeFigure();
	}

}
