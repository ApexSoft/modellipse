package org.eclipse.papyrus.uml.diagram.sequence.edit.helpers;

import org.eclipse.core.runtime.Platform;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.draw2d.geometry.PrecisionRectangle;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.transaction.RunnableWithResult;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.requests.DropRequest;
import org.eclipse.gef.requests.ReconnectRequest;
import org.eclipse.gmf.runtime.common.core.util.Log;
import org.eclipse.gmf.runtime.diagram.ui.internal.DiagramUIPlugin;
import org.eclipse.gmf.runtime.diagram.ui.internal.DiagramUIStatusCodes;
import org.eclipse.gmf.runtime.gef.ui.figures.DefaultSizeNodeFigure;
import org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure;
import org.eclipse.gmf.runtime.gef.ui.figures.SlidableAnchor;
import org.eclipse.gmf.runtime.notation.Anchor;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.IdentityAnchor;
import org.eclipse.papyrus.uml.diagram.common.draw2d.anchors.FixedAnchor;


public class AnchorHelper {

	public static String getAnchorId(TransactionalEditingDomain domain, ConnectionEditPart connEditPart, final boolean isSource) {
        final org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionEditPart connection = 
            (org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionEditPart)connEditPart;
        
		String t = ""; //$NON-NLS-1$
		try {
			t = (String) domain.runExclusive(
				new RunnableWithResult.Impl() {

				public void run() {
					Anchor a = null;
					if(isSource)
						a = ((Edge)connection.getModel()).getSourceAnchor();
					else
						a = ((Edge)connection.getModel()).getTargetAnchor();
					if (a instanceof IdentityAnchor)
						setResult(((IdentityAnchor) a).getId());
                    else
                        setResult(""); //$NON-NLS-1$
				}
			});
		} catch (InterruptedException e) {
			Log.error(DiagramUIPlugin.getInstance(),
				DiagramUIStatusCodes.IGNORED_EXCEPTION_WARNING,
				"getTargetConnectionAnchor", e); //$NON-NLS-1$
		}
		return t;
	}

	
	public static Point getRequestLocation(Request request){
		if (request instanceof ReconnectRequest) {
			if (((DropRequest) request).getLocation() != null) {
				Point pt = ((DropRequest) request).getLocation().getCopy();
				return pt;
			}
		}
		else if (request instanceof DropRequest){
			return 	((DropRequest) request).getLocation();
		}
		return null;
	}
	
	public static class CombinedFragmentNodeFigure extends DefaultSizeNodeFigure{

		public CombinedFragmentNodeFigure(int width, int height) {
			super(width, height);
		}
		
		protected ConnectionAnchor createAnchor(PrecisionPoint p) {
			if (p==null)
				// If the old terminal for the connection anchor cannot be resolved (by SlidableAnchor) a null
				// PrecisionPoint will passed in - this is handled here
				return createDefaultAnchor();
			return new AnchorHelper.IntersectionPointAnchor(this, p);
		}
	}
	
	public static class IntersectionPointAnchor extends SlidableAnchor{

		public IntersectionPointAnchor(IFigure fig, PrecisionPoint p) {
			super(fig,p);
		}
		
		public IntersectionPointAnchor(IFigure fig) {
			super(fig);
		}
		
		protected Point getLocation(Point ownReference, Point foreignReference) {
			PointList intersections = getIntersectionPoints(ownReference, foreignReference);
			if (intersections!=null && intersections.size()!=0) {
				int size = intersections.size();
				double dist = foreignReference.getDistance(ownReference);
				for(int i = 0; i < size; i ++){
					Point loc = intersections.getPoint(i);
					if(isInOrder(foreignReference,ownReference,dist, loc)){
						return loc;
					}
				}
				return intersections.getFirstPoint();
			}
			return null;
		}	
		
		private boolean isInOrder(Point start, Point end, double dist, Point loc) {
			double total = loc.getDistance(start);
			double dist2 = loc.getDistance(end);
			if(total < dist || total < dist2)
				return false;
			
			if(Math.abs(total - dist - dist2) < 0.01)
				return true;
			
			return false;
		}	
	}

	public static class FixedAnchorEx extends FixedAnchor {

		private int location;

		public FixedAnchorEx(IFigure f, int location) {
			super(f, location);
			this.location = location;
		}

		public Point getLocation(Point reference) {
			if(location == TOP)
				return getBox().getTop();
			else if(location == BOTTOM)
				return getBox().getBottom();

			return super.getLocation(reference);
		}
	}
	
	public static class SideAnchor extends SlidableAnchor{

		private boolean isRight;

		public SideAnchor(NodeFigure nodeFigure, PrecisionPoint pt,
				boolean isRight) {
			super(nodeFigure, pt);
			this.isRight = isRight;
		}
		
		public boolean isRight() {
			return isRight;
		}
		
		public String getTerminal() {
			String side = isRight? "R": "L";
			return super.getTerminal() + "{" + side + "}";
		}
	}
	
	public static class InnerPointAnchor extends SlidableAnchor{
		private PrecisionPoint percent;
		private IFigure figure;

		public InnerPointAnchor(IFigure fig, PrecisionPoint percent) {
			super(fig,percent);
			this.figure = fig;			
			this.percent = percent;
		}
		
		public static InnerPointAnchor createAnchorAtLocation(IFigure fig, PrecisionPoint loc) {
			PrecisionPoint p = loc.getPreciseCopy();			
			Rectangle b = fig.getBounds().getCopy();
			fig.translateToAbsolute(b);
						
			Dimension d = p.getDifference(b.getTopLeft());
			PrecisionPoint per = new PrecisionPoint( d.preciseWidth()/ b.width , d.preciseHeight() / b.height);
			return new InnerPointAnchor(fig, per);
		}
		
		protected Point getLocation(Point ownReference, Point foreignReference) {
			PrecisionRectangle bounds = new PrecisionRectangle(figure.getBounds());
			bounds.setPreciseWidth((bounds.width * percent.preciseX()));
			bounds.setPreciseHeight((bounds.height * percent.preciseY()));
			figure.translateToAbsolute(bounds);
			return bounds.getBottomRight();
		}	
	}
}
