/**
 * 
 */
package org.eclipse.papyrus.editor.commands;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.window.Window;
import org.eclipse.papyrus.infra.core.sasheditor.contentprovider.IPageMngr;
import org.eclipse.papyrus.infra.core.sasheditor.editor.IEditorPage;
import org.eclipse.papyrus.infra.core.sasheditor.editor.IPage;
import org.eclipse.papyrus.infra.core.sasheditor.editor.ISashWindowsContainer;
import org.eclipse.papyrus.infra.core.utils.EditorUtils;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.part.EditorPart;

/**
 * A command to be used with the Eclipse Commands Framework.
 * This command is to be used with {@link SashWindowsContainer} implemented with the Di model.
 * This command allows to rename a nested editor.
 * 
 * This command use a Transaction.
 * TODO Move to gmf adapter.
 * 
 * @author cedric dumoulin
 * 
 */
public class RenameNestedEditorCommand extends AbstractHandler {

	/**
	 * Check if the Command is enabled.
	 */
	@Override
	public void setEnabled(Object evaluationContext) {
		//		System.out.println("call to CloseDiagramCommand.setEnable(" + evaluationContext + ")");
	}

	/**
	 * Execute the command. This method is called when the action is triggered.
	 * 
	 */
	public Object execute(ExecutionEvent event) throws ExecutionException {

//		try {
//			IEditorPart part = HandlerUtil.getActiveEditor(event);
//			IPageMngr pageMngr = (IPageMngr)part.getAdapter(IPageMngr.class);
//			ISashWindowsContainer container = (ISashWindowsContainer)part.getAdapter(ISashWindowsContainer.class);
//			IPage sashPage = container.getActiveSashWindowsPage();
//			
//			if(sashPage instanceof IEditorPage )
//			{
//			  IEditorPage editorPage = (IEditorPage)sashPage;
//			  execute(editorPage.getRawModel(), editorPage.getIEditorPart());
//			}
//			// Bug from sash Di to be corrected
//			if(pageIdentifier instanceof PageRef)
//			{
//				pageIdentifier = ((PageRef)pageIdentifier).getPageIdentifier();
//			}
//			execute(sashPage.getRawModel(), sashPage.);
//
//		} catch (NullPointerException e) {
//			// PageMngr can't be found
//			return null;
//		}



		return null;
	}

	/**
	 * Close selected page.
	 * 
	 * @param pageMngr
	 */
	public void execute(final Diagram diagram, final IEditorPart editorPart) {

		
		
		TransactionalEditingDomain editingDomain = EditorUtils.getTransactionalEditingDomain();
		if(editingDomain != null) {
			InputDialog dialog = new InputDialog(Display.getCurrent().getActiveShell(), "Rename an existing diagram", "New name:", diagram.getName(), null);
			if(dialog.open() == Window.OK) {
				final String name = dialog.getValue();
				if(name != null && name.length() > 0) {

					Command command = new RecordingCommand(editingDomain) {

						
						@Override
						protected void doExecute() {
							diagram.setName(name);
						}
					};

					editingDomain.getCommandStack().execute(command);
				}
			}
		}
	}

	}

