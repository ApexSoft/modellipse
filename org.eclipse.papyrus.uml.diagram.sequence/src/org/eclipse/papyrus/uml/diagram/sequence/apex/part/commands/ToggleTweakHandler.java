package org.eclipse.papyrus.uml.diagram.sequence.apex.part.commands;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.Command;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.papyrus.uml.diagram.sequence.apex.part.ApexUMLDiagramEditor;
import org.eclipse.papyrus.uml.diagram.sequence.part.UMLDiagramEditorPlugin;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IPerspectiveDescriptor;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.handlers.HandlerUtil;

public class ToggleTweakHandler extends AbstractHandler {

	private IWorkbenchPage fPage;
	private IPreferenceStore fStore;
	
	public Object execute(ExecutionEvent event) throws ExecutionException {
		Command command = event.getCommand();
		boolean oldValue = HandlerUtil.toggleCommandState(command);
		
		try {
			IEditorPart part = HandlerUtil.getActiveEditor(event);
			fPage = part.getSite().getPage();
			fStore = UMLDiagramEditorPlugin.getInstance().getPreferenceStore();
			
			fStore.setValue(getPreferenceKey(), !oldValue);
		} catch (NullPointerException e) {
			return null;
		}
		return null;
	}
	
	public boolean isChecked() {
		if (fStore != null) {
			return fStore.getBoolean(getPreferenceKey());
		}
		return false;
	}
	
	private String getPreferenceKey() {
		IPerspectiveDescriptor perspective= fPage.getPerspective();
		String perspectiveID= perspective != null ? perspective.getId() : "<unknown>"; //$NON-NLS-1$
		return ApexUMLDiagramEditor.EDITOR_SHOW_TWEAK + "." + perspectiveID; //$NON-NLS-1$
	}
}
