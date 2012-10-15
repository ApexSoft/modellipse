package org.eclipse.papyrus.uml.tools.databinding;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.UnexecutableCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.papyrus.commands.Activator;
import org.eclipse.uml2.uml.ExtensionEnd;

/**
 * An IObservableValue for handling the UML ExtensionEnd#multiplicity
 * The multiplicity can only be either 1 or 0..1
 * 
 * @author Camille Letavernier
 * 
 */
public class ExtensionEndMultiplicityObservableValue extends MultiplicityObservableValue {

	public ExtensionEndMultiplicityObservableValue(ExtensionEnd source, EditingDomain domain) {
		super(source, domain);
	}

	@Override
	public Command getCommand(Object value) {
		if(ONE.equals(value) || OPTIONAL.equals(value)) {
			return super.getCommand(value);
		}

		Activator.log.warn(String.format("The multiplicity %s is not valid for an ExtensionEnd", value)); //$NON-NLS-1$
		return UnexecutableCommand.INSTANCE;
	}
}
