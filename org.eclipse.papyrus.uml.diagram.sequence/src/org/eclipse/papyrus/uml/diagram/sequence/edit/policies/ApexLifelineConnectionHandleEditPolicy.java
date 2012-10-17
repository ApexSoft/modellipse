package org.eclipse.papyrus.uml.diagram.sequence.edit.policies;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.ConnectionHandleEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.handles.ConnectionHandleLocator;
import org.eclipse.papyrus.uml.diagram.sequence.figures.LifelineDotLineCustomFigure;

/**
 * Lifeline의 ConnectionHandle 위치를 DashLineRectangle에 위치
 * @author Jiho
 *
 */
public class ApexLifelineConnectionHandleEditPolicy extends
		ConnectionHandleEditPolicy {

	@Override
	protected ConnectionHandleLocator getConnectionHandleLocator(
			Point referencePoint) {
		if (getHost() instanceof AbstractGraphicalEditPart) {
			IFigure figure = ((AbstractGraphicalEditPart)getHost()).getContentPane();
			Rectangle rect = figure.getBounds().getCopy();
			/*8
//			figure.getParent().translateToAbsolute(rect);
			System.out.println(rect + " " + referencePoint);
			System.out.println(rect.contains(referencePoint));
			*/
			if (rect.contains(referencePoint)) {
				if (figure instanceof LifelineDotLineCustomFigure) {
					figure = ((LifelineDotLineCustomFigure)figure).getDashLineRectangle();
				}
				return new ConnectionHandleLocator(figure, referencePoint);
			}
		}
		return super.getConnectionHandleLocator(referencePoint);
	}

}
