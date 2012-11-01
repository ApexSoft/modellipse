package org.eclipse.papyrus.uml.diagram.sequence.edit.policies;

import java.util.ArrayList;
import java.util.List;

import kr.co.apexsoft.modellipse.customization.diagram.sequence.util.ApexSequenceUtil;

import org.eclipse.draw2d.PositionConstants;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Handle;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gmf.runtime.diagram.ui.editparts.INodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.ResizableShapeEditPolicy;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.papyrus.uml.diagram.sequence.edit.parts.AbstractExecutionSpecificationEditPart;
import org.eclipse.uml2.uml.ExecutionSpecification;
import org.eclipse.uml2.uml.InteractionFragment;

/**
 * @author Jiho
 *
 */
public class ApexExecutionSpecificationSelectionEditPolicy extends
		ResizableShapeEditPolicy {

	public ApexExecutionSpecificationSelectionEditPolicy() {
		setResizeDirections(PositionConstants.NORTH_SOUTH);
	}

	/**
	 * apex updated
	 */
	@Override
	protected List createSelectionHandles() {
		/* apex improved start */
		List<Handle> list = new ArrayList<Handle>();
		createMoveHandle(list);
		createResizeHandle(list, PositionConstants.NORTH);
		createResizeHandle(list, PositionConstants.SOUTH);this.getDragTracker();
		return list;
		/* apex improved end */
		/* apex replaced
		return super.createSelectionHandles();
		 */
	}

	/**
	 * apex updated
	 */
	@Override
	protected void showChangeBoundsFeedback(ChangeBoundsRequest request) {
		/* apex added start */
		if (getHost() instanceof AbstractExecutionSpecificationEditPart) {
			// north로 resize할 경우에도 south 방향으로 resize되도록 변경 해주는 기능
			request.getMoveDelta().y = 0;
		}
		/* apex added end */
		super.showChangeBoundsFeedback(request);
	}

	@Override
	protected Command getResizeCommand(ChangeBoundsRequest request) {
//		CompoundCommand compoundCmd = new CompoundCommand("");
//		if (getHost() instanceof AbstractExecutionSpecificationEditPart) {
//			AbstractExecutionSpecificationEditPart activationEP = (AbstractExecutionSpecificationEditPart)getHost();
//			InteractionOperandEditPart ioep = ApexSequenceUtil.apexGetEnclosingInteractionOperandEditpart(activationEP);
//			CombinedFragmentEditPart cfep = (CombinedFragmentEditPart)ioep.getParent().getParent();
//			compoundCmd.add(InteractionCompartmentXYLayoutEditPolicy.getCombinedFragmentResizeChildrenCommand(request, cfep, activationEP));
//		}
//		compoundCmd.add(super.getResizeCommand(request));
//		return compoundCmd;

		Command command = null;

		View view = (View)getHost().getModel();
		EObject element = view.getElement();
		if (element instanceof ExecutionSpecification) {
			InteractionFragment parent = ((ExecutionSpecification)element).getEnclosingOperand();
			if (parent != null) {
				EditPart interactionOperandEP = ApexSequenceUtil.getEditPart(parent, INodeEditPart.class, getHost().getViewer());
				EditPart combinedFragmentCompartmentEP = interactionOperandEP.getParent();
				EditPart combinedFragmentEP = combinedFragmentCompartmentEP.getParent();

				command = combinedFragmentEP.getCommand(request);
//				command = InteractionCompartmentXYLayoutEditPolicy.getCombinedFragmentResizeChildrenCommand(
//						request, (ShapeNodeEditPart)combinedFragmentEP, (ShapeNodeEditPart)getHost());
			}
		}
		return command == null ? super.getResizeCommand(request) :
				command.chain(super.getResizeCommand(request));
	}

}