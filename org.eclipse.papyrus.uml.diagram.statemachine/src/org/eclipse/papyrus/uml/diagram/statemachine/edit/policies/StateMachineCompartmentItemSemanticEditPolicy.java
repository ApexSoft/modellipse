package org.eclipse.papyrus.uml.diagram.statemachine.edit.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.eclipse.papyrus.uml.diagram.statemachine.edit.commands.PseudostateEntryPointCreateCommand;
import org.eclipse.papyrus.uml.diagram.statemachine.edit.commands.PseudostateExitPointCreateCommand;
import org.eclipse.papyrus.uml.diagram.statemachine.edit.commands.RegionCreateCommand;
import org.eclipse.papyrus.uml.diagram.statemachine.providers.UMLElementTypes;

/**
 * @generated
 */
public class StateMachineCompartmentItemSemanticEditPolicy extends UMLBaseItemSemanticEditPolicy {

	/**
	 * @generated
	 */
	public StateMachineCompartmentItemSemanticEditPolicy() {
		super(UMLElementTypes.StateMachine_2000);
	}

	/**
	 * @generated
	 */
	protected Command getCreateCommand(CreateElementRequest req) {
		if(UMLElementTypes.Region_3000 == req.getElementType()) {
			return getGEFWrapper(new RegionCreateCommand(req));
		}
		if(UMLElementTypes.Pseudostate_16000 == req.getElementType()) {
			return getGEFWrapper(new PseudostateEntryPointCreateCommand(req));
		}
		if(UMLElementTypes.Pseudostate_17000 == req.getElementType()) {
			return getGEFWrapper(new PseudostateExitPointCreateCommand(req));
		}
		return super.getCreateCommand(req);
	}
}
