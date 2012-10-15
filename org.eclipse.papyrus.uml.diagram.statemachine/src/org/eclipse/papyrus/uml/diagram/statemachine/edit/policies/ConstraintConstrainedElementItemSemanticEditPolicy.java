package org.eclipse.papyrus.uml.diagram.statemachine.edit.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.emf.type.core.commands.DestroyReferenceCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyReferenceRequest;
import org.eclipse.papyrus.uml.diagram.statemachine.providers.UMLElementTypes;

/**
 * @generated
 */
public class ConstraintConstrainedElementItemSemanticEditPolicy extends UMLBaseItemSemanticEditPolicy {

	/**
	 * @generated
	 */
	public ConstraintConstrainedElementItemSemanticEditPolicy() {
		super(UMLElementTypes.ConstraintConstrainedElement_670);
	}

	/**
	 * @generated
	 */
	protected Command getDestroyReferenceCommand(DestroyReferenceRequest req) {
		return getGEFWrapper(new DestroyReferenceCommand(req));
	}

}
