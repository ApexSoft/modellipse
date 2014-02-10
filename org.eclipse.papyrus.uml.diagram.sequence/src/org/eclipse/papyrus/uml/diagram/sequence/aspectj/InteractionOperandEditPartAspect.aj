package org.eclipse.papyrus.uml.diagram.sequence.aspectj;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.TopGraphicEditPart;
import org.eclipse.gmf.runtime.draw2d.ui.figures.OneLineBorder;
import org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure;
import org.eclipse.papyrus.uml.diagram.sequence.apex.edit.policies.ApexInteractionOperandDragEditPolicy;
import org.eclipse.papyrus.uml.diagram.sequence.edit.parts.CombinedFragmentEditPart;
import org.eclipse.papyrus.uml.diagram.sequence.edit.parts.InteractionOperandEditPart;
import org.eclipse.papyrus.uml.diagram.sequence.edit.parts.InteractionOperandEditPart.CustomInteractionOperandFigure;

privileged aspect InteractionOperandEditPartAspect {

	pointcut calledCreateDefaultEditPolicies() :
		execution(protected void InteractionOperandEditPart.createDefaultEditPolicies());
	
	after(InteractionOperandEditPart editpart) returning() :
		calledCreateDefaultEditPolicies() && target(editpart) {
		editpart.installEditPolicy(EditPolicy.PRIMARY_DRAG_ROLE, new ApexInteractionOperandDragEditPolicy());
	}
	
	pointcut calledCreateMainFigure() :
		execution(protected NodeFigure InteractionOperandEditPart.createMainFigure());
	
	after(InteractionOperandEditPart editpart) returning(NodeFigure figure) :
		calledCreateMainFigure() && target(editpart) {
		figure.setBorder(new MarginBorder(0, 2, 2, 2));
	}
	
	pointcut calledIsSelectable() :
		execution(public boolean InteractionOperandEditPart.isSelectable());
	
	boolean around(InteractionOperandEditPart editpart) : calledIsSelectable() && this(editpart) {
		boolean retVal = proceed(editpart);
		if (retVal) {
			EditPart focusEditPart = editpart.getViewer().getFocusEditPart();
			if (focusEditPart instanceof IGraphicalEditPart) {
				TopGraphicEditPart focusTopEP = ((IGraphicalEditPart)focusEditPart).getTopGraphicEditPart();
				TopGraphicEditPart myTopEP = editpart.getTopGraphicEditPart();

				if (myTopEP == focusTopEP) {
					return true;
				}
				
				EditPart myParentEP = myTopEP.getParent();
				while (myParentEP != null && myParentEP instanceof CombinedFragmentEditPart == false) {
					myParentEP = myParentEP.getParent();
				}
				
				EditPart focusParentEP = focusTopEP;
				while (focusParentEP != null && focusParentEP instanceof CombinedFragmentEditPart == false) {
					focusParentEP = focusParentEP.getParent();
				}
				
				if (myParentEP != null && myParentEP.equals(focusParentEP)) {
					return true;
				}
			}
		}
		return retVal;
	}
	
	pointcut constructor() : execution(public CustomInteractionOperandFigure.new(..));
	
	Object around(InteractionOperandEditPart editpart, CustomInteractionOperandFigure figure) :
		constructor() && args(editpart) && this(figure) {
		proceed(editpart, figure);
		figure.setShadow(false);
		
		if (!editpart.firstOperand) {
			OneLineBorder border = new OneLineBorder(ColorConstants.lightGray, figure.getLineWidth(), PositionConstants.TOP);
			border.setStyle(Graphics.LINE_DASH);			
			figure.setBorder(border);
		} else {
			figure.setBorder(null);
		}
		return figure;
	}
}
