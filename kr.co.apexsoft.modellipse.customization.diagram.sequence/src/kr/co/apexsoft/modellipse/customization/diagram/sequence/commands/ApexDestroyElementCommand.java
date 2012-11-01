package kr.co.apexsoft.modellipse.customization.diagram.sequence.commands;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.diagram.ui.requests.EditCommandRequestWrapper;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.IEditCommandRequest;

/**
 * editPart에 연결된 Source Connection들을 부모 EditPart에 연결한 뒤에 DestroyElementCommand가 생성/수행되도록 하는 Command
 * 
 * @author Jiho
 *
 */
public class ApexDestroyElementCommand extends AbstractTransactionalCommand {
	
	private static final String COMMAND_LABEL = "";
	private EditPart editPart;
	private IEditCommandRequest request;
	
	private Command command;

	public ApexDestroyElementCommand(TransactionalEditingDomain domain,
			EditPart editPart, IEditCommandRequest request) {
		super(domain, COMMAND_LABEL, null);
		this.editPart = editPart;
		this.request = request;
	}

	@Override
	protected CommandResult doExecuteWithResult(IProgressMonitor monitor,
			IAdaptable info) throws ExecutionException {
		command = editPart.getCommand(new EditCommandRequestWrapper(request));
		if (command != null && command.canExecute()) {
			command.execute();
		}
		return null;
	}

}
