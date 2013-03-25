package kr.co.apexsoft.stella.modeler.explorer.command;

import java.util.List;
import java.util.Map;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.diagram.ui.requests.EditCommandRequestWrapper;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;

/**
 * Control된 패키지의 물리파일(di, uml, notation 파일)을 rename 하는 command
 * 
 * @author hanmomhanda
 *
 */
public class RenameControlledDiFileCommand extends AbstractTransactionalCommand {

	public RenameControlledDiFileCommand(TransactionalEditingDomain domain,
			String label, List affectedFiles) {
		super(domain, label, affectedFiles);
		// TODO Auto-generated constructor stub
	}

	public RenameControlledDiFileCommand(TransactionalEditingDomain domain,
			String label, Map options, List affectedFiles) {
		super(domain, label, options, affectedFiles);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected CommandResult doExecuteWithResult(IProgressMonitor monitor,
			IAdaptable info) throws ExecutionException {
		// TODO Auto-generated method stub
		
		return null;		
	}

}
