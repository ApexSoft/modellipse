package kr.co.apexsoft.stella.uml.diagram.sequence.providers;

import kr.co.apexsoft.stella.uml.diagram.sequence.editpolicies.LifelineAppliedStereotypeCompartmentEditPolicy;

import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.common.core.service.IOperation;
import org.eclipse.gmf.runtime.common.core.service.IProviderChangeListener;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.services.editpolicy.CreateEditPoliciesOperation;
import org.eclipse.gmf.runtime.diagram.ui.services.editpolicy.IEditPolicyProvider;
import org.eclipse.papyrus.uml.diagram.common.editpolicies.AppliedStereotypeLabelDisplayEditPolicy;
import org.eclipse.papyrus.uml.diagram.sequence.edit.parts.LifelineEditPart;
import org.eclipse.papyrus.uml.diagram.sequence.edit.parts.PackageEditPart;

public class CustomEditPolicyProvider implements IEditPolicyProvider {

	/**
	 * {@inheritDoc}
	 */
	public void addProviderChangeListener(IProviderChangeListener listener) {

	}

	/**
	 * {@inheritDoc}
	 */
	public boolean provides(IOperation operation) {
		CreateEditPoliciesOperation epOperation = (CreateEditPoliciesOperation)operation;
		if(!(epOperation.getEditPart() instanceof GraphicalEditPart)) {
			return false;
		}
		GraphicalEditPart gep = (GraphicalEditPart)epOperation.getEditPart();
		String diagramType = gep.getNotationView().getDiagram().getType();
		if(PackageEditPart.MODEL_ID.equals(diagramType)) {
			return true;
		}


		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	public void removeProviderChangeListener(IProviderChangeListener listener) {

	}

	/**
	 * {@inheritDoc}
	 */
	public void createEditPolicies(EditPart editPart) {
		installPopupbarPolicy(editPart);
	}

	private void installPopupbarPolicy(final EditPart editPart) {
		if(editPart instanceof LifelineEditPart){
			editPart.installEditPolicy(AppliedStereotypeLabelDisplayEditPolicy.STEREOTYPE_LABEL_POLICY, new LifelineAppliedStereotypeCompartmentEditPolicy());
		}
	}

}
