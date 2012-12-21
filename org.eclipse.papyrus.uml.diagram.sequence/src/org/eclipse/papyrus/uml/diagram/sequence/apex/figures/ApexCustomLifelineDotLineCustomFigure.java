package org.eclipse.papyrus.uml.diagram.sequence.apex.figures;

import java.util.Collection;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure;
import org.eclipse.papyrus.uml.diagram.sequence.apex.util.LifelineFigureHelper;
import org.eclipse.papyrus.uml.diagram.sequence.figures.LifelineDotLineCustomFigure;

public class ApexCustomLifelineDotLineCustomFigure extends
		LifelineDotLineCustomFigure {
	
	/** The SOLI d_ size. */
	private final int SOLID_SIZE = 6;

	/** The DASHE d_ size. */
	private final int DASHED_SIZE = 10;
	
	private final int MARGIN = 2;
	
	private boolean inlineMode;
	
	@Override
	protected NodeFigure apexCreateDashLineRectangle() {
		return new ApexCustomNodeFigure();
	}

	@Override
	protected void outlineShape(Graphics graphics) {
		if (inlineMode)
			return;
		
		Rectangle r = getBounds();

		Point pStart = new Point();
		Point pEnd = new Point();
		Point pAux = new Point();

		pStart.x = r.x + r.width / 2;
		pStart.y = r.y + 1;

		pEnd.x = pStart.x;
		pEnd.y = pStart.y + r.height - 1;

		// Create the dash line
		pAux = pStart.getCopy();

		Collection<Rectangle> hideRegions = LifelineFigureHelper.getHideRegions(this);

		while(pAux.y <= pEnd.y) {
			// The drawing limit is pEnd.y
			int yEnd = pAux.y + SOLID_SIZE;
			if(yEnd > pEnd.y) {
				yEnd = pEnd.y;
			}
			
			int yStart = pAux.y;
			boolean isHide = false;
			if (hideRegions != null) {
				for (Rectangle region: hideRegions) {
					if (yStart < region.y && yEnd >= region.y) {
						yEnd = region.y - MARGIN;
						break;
					} else if (yStart >= region.y && yEnd <= region.bottom()) {
						isHide = true;
						break;
					} else if (yStart <= region.bottom() && yEnd > region.bottom()) {
						yStart = region.bottom() + MARGIN;
						break;
					}
				}
			}
			
			if (!isHide && yStart < yEnd)
				graphics.drawLine(new Point(pAux.x, yStart), new Point(pAux.x, yEnd));
			pAux.y = pAux.y + DASHED_SIZE;
		}

		// Update the size and the location of the rectangle representing the
		// dash line
		getDashLineRectangle().setSize(1, pEnd.y - pStart.y);
		getDashLineRectangle().setLocation(pStart);
	}
	
	@Override
	public void configure(boolean inlineMode, int innerConnectableElementsNumber) {
		super.configure(inlineMode, innerConnectableElementsNumber);
		this.inlineMode = inlineMode;
	}

}
