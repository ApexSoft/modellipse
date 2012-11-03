/**
 * 
 */
package org.eclipse.papyrus.uml.diagram.sequence.apex.interfaces;

import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure;

/**
 * @author hanmomhanda
 *
 */
public interface IApexLifelineEditPart {
	
	public List<ShapeNodeEditPart> getChildShapeNodeEditPart();

	public IApexLifelineFigure getPrimaryShape();
	
	public IFigure getFigure();
	
	public TransactionalEditingDomain getEditingDomain();
	
	public NodeFigure getNodeFigure();
	
	
}
