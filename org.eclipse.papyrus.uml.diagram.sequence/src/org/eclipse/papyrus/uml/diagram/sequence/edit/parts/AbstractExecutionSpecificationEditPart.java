package org.eclipse.papyrus.uml.diagram.sequence.edit.parts;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Locator;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.RelativeLocator;
import org.eclipse.draw2d.TreeSearch;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.draw2d.geometry.PrecisionRectangle;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.ResizableShapeEditPolicy;
import org.eclipse.gmf.runtime.draw2d.ui.mapmode.IMapMode;
import org.eclipse.gmf.runtime.draw2d.ui.mapmode.MapModeUtil;
import org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.FillStyle;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.gmf.runtime.notation.datatype.GradientData;
import org.eclipse.papyrus.infra.emf.appearance.helper.ShadowFigureHelper;
import org.eclipse.papyrus.infra.gmfdiag.common.figure.node.IPapyrusNodeFigure;
import org.eclipse.papyrus.uml.diagram.common.figure.node.PapyrusNodeFigure;
import org.eclipse.papyrus.uml.diagram.sequence.figures.ApexCustomDefaultSizeNodeFigure;
import org.eclipse.swt.graphics.Color;
import org.eclipse.uml2.uml.ExecutionSpecification;

/**
 * apex updated
 */
public abstract class AbstractExecutionSpecificationEditPart extends
		ShapeNodeEditPart {
	private List executionSpecificationEndParts;

	public AbstractExecutionSpecificationEditPart(View view) {
		super(view);
	}

	/* apex added start */
	public View apexFindChildByModel(EObject model) {
		List list = getModelChildren();
		if(list != null && list.size() > 0) {
			for(Object o : list) {
				if(!(o instanceof View) )
					continue;
				
				View view = (View) o;
				if(view.getElement() == model){
					return view;
				}
			}
		}
		return null;
	}
	/* apex added end */
	
	@Override
	public List getChildren() {
		if (executionSpecificationEndParts == null) {
			initExecutionSpecificationEndEditPart();
		}
		return super.getChildren();
	}

	protected void initExecutionSpecificationEndEditPart() {
		executionSpecificationEndParts = new ArrayList();

		EObject element = this.resolveSemanticElement();
		if (!(element instanceof ExecutionSpecification))
			return;
		ExecutionSpecification execution = (ExecutionSpecification) element;
		final ExecutionSpecificationEndEditPart startPart = new ExecutionSpecificationEndEditPart(
				execution.getStart(), this, new RelativeLocator(getFigure(),
						PositionConstants.NORTH));
		executionSpecificationEndParts.add(startPart);

		final ExecutionSpecificationEndEditPart finishPart = new ExecutionSpecificationEndEditPart(
				execution.getFinish(), this, new RelativeLocator(getFigure(),
						PositionConstants.SOUTH));
		executionSpecificationEndParts.add(finishPart);

		Diagram diagram = ((View) this.getModel()).getDiagram();
		startPart.rebuildLinks(diagram);
		finishPart.rebuildLinks(diagram);
		
		addChild(startPart, -1);
		addChild(finishPart, -1);
	}

	static class FillParentLocator implements Locator {
		public void relocate(IFigure target) {
			target.setBounds(target.getParent().getBounds());
		}
	}
	
	/**
	 * apex updated
	 * 
	 * Overrides to disable the defaultAnchorArea. The edge is now more stuck with the middle of the
	 * figure.
	 * 
	 * @generated NOT
	 */
	protected NodeFigure createNodePlate() {
		/* apex improved start */
		ApexCustomDefaultSizeNodeFigure result = new ApexCustomDefaultSizeNodeFigure(16, 50) {
		/* apex improved end */
		/* apex replaced
		DefaultSizeNodeFigure result = new DefaultSizeNodeFigure(16, 60) {
		 */
			/**
			 * @see org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure#isDefaultAnchorArea(org.eclipse.draw2d.geometry.PrecisionPoint)
			 */
			@Override
			protected boolean isDefaultAnchorArea(PrecisionPoint p) {
				return false;
			}
		};
		result.setMinimumSize(new Dimension(getMapMode().DPtoLP(16), getMapMode().DPtoLP(20))); // min height 20
		return result;
	}
	
	@Override
	protected void createDefaultEditPolicies() {
		super.createDefaultEditPolicies();
		installEditPolicy(EditPolicy.PRIMARY_DRAG_ROLE, new ResizableShapeEditPolicy(){
			@Override
			protected void showChangeBoundsFeedback(ChangeBoundsRequest request) {
				IFigure feedback = getDragSourceFeedbackFigure();
			        
		        PrecisionRectangle rect = new PrecisionRectangle(getInitialFeedbackBounds().getCopy());
		        getHostFigure().translateToAbsolute(rect);
		        
		        IFigure f = getHostFigure();
		        Dimension min = f.getMinimumSize().getCopy();
		        Dimension max = f.getMaximumSize().getCopy();
		        IMapMode mmode = MapModeUtil.getMapMode(f);
		        min.height = mmode.LPtoDP(min.height);
		        min.width = mmode.LPtoDP(min.width);
		        max.height = mmode.LPtoDP(max.height);
		        max.width = mmode.LPtoDP(max.width);
		        
		        Rectangle originalBounds = rect.getCopy();
		        rect.translate(request.getMoveDelta());
		        rect.resize(request.getSizeDelta());
		        
		        if (min.width>rect.width)
		            rect.width = min.width;
		        else if (max.width < rect.width)
		            rect.width = max.width;
		        if (min.height>rect.height)
		            rect.height = min.height;
		        else if (max.height < rect.height)
		            rect.height = max.height;
		        
		        if(rect.height == min.height && request.getSizeDelta().height < 0 && request.getMoveDelta().y > 0 ){ //shrink at north
		        	Point loc = rect.getLocation();
		        	loc.y = originalBounds.getBottom().y - min.height;
		        	rect.setLocation(loc);
		        	
		        	request.getSizeDelta().height = min.height - originalBounds.height;
		        	request.getMoveDelta().y = loc.y - originalBounds.y;
		        }
		        feedback.translateToRelative(rect);
		        feedback.setBounds(rect);
			}
		});
	}	
	
	@Override
	protected void setLineWidth(int width) {
		if(getPrimaryShape() instanceof NodeFigure){
			((NodeFigure)getPrimaryShape()).setLineWidth(width);
		}
	}
	
	protected final void refreshShadow() {
		getPrimaryShape().setShadow(ShadowFigureHelper.getShadowFigureValue((View)getModel()));
	}
		
	/**
	 * Override to set the transparency to the correct figure
	 */
	@Override
	protected void setTransparency(int transp) {
		getPrimaryShape().setTransparency(transp);
	}
	
	/**
	 * sets the back ground color of this edit part
	 * 
	 * @param color
	 *        the new value of the back ground color
	 */
	@Override
	protected void setBackgroundColor(Color color) {
		getPrimaryShape().setBackgroundColor(color);
		getPrimaryShape().setIsUsingGradient(false);
		getPrimaryShape().setGradientData(-1, -1, 0);
	}

	/**
	 * Override to set the gradient data to the correct figure
	 */
	@Override
	protected void setGradient(GradientData gradient) {
		IPapyrusNodeFigure fig = getPrimaryShape();
		FillStyle style = (FillStyle)getPrimaryView().getStyle(NotationPackage.Literals.FILL_STYLE);
		if(gradient != null) {
			fig.setIsUsingGradient(true);;
			fig.setGradientData(style.getFillColor(), gradient.getGradientColor1(), gradient.getGradientStyle());
		} else {
			fig.setIsUsingGradient(false);
		}
	}
		
	public boolean supportsGradient() {
		return true;
	}
	
	@Override
	protected void handleNotificationEvent(Notification event) {
		super.handleNotificationEvent(event);
		
		Object feature = event.getFeature();
		if((getModel() != null) && (getModel() == event.getNotifier())) {
			if(NotationPackage.eINSTANCE.getLineStyle_LineWidth().equals(feature)) {
				refreshLineWidth();
			} else if(NotationPackage.eINSTANCE.getLineTypeStyle_LineType().equals(feature)) {
				refreshLineType();
			}
		}

		refreshShadow();
	}
	
	public class ExecutionSpecificationRectangleFigure extends PapyrusNodeFigure{ //RectangleFigure {

		/**
		 * apex updated
		 */
		public ExecutionSpecificationRectangleFigure() {
			/* apex improved start */
			this.setPreferredSize(new Dimension(getMapMode().DPtoLP(16), getMapMode().DPtoLP(50)));
			/* apex improved end */
			/* apex replaced
			this.setPreferredSize(new Dimension(getMapMode().DPtoLP(16), getMapMode().DPtoLP(60)));
			*/
			this.setMinimumSize(new Dimension(getMapMode().DPtoLP(16), getMapMode().DPtoLP(20)));
		}

		public IFigure findMouseEventTargetAt(int x, int y) {	
			// check children first instead of self
			IFigure f = findMouseEventTargetInDescendantsAt(x, y);
			if (f != null)
				return f;
			if (!containsPoint(x, y))
				return null;
			if (isMouseEventTarget())
				return this;
			return null;
		}

		public IFigure findFigureAt(int x, int y, TreeSearch search) {
			if (search.prune(this))
				return null;
			IFigure child = findDescendantAtExcluding(x, y, search);
			if (child != null)
				return child;
			if (!containsPoint(x, y))
				return null;
			if (search.accept(this))
				return this;
			return null;
		}
	}
	
	public abstract ExecutionSpecificationRectangleFigure getPrimaryShape() ;
}
