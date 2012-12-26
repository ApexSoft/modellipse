package kr.co.apexsoft.modellipse.custom.editors;

import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.papyrus.extensionpoints.editors.ui.IPopupEditorHelper;

/**
 * @author Jiho
 *
 */
public class InitSelectedEditorHelper implements IPopupEditorHelper {

	private IPopupEditorInputFactory factory;
	
	private Object selected;
	
	public InitSelectedEditorHelper(IGraphicalEditPart editPart, IPopupEditorInputFactory factory) {
		this.factory = factory;
	}
	
	public void setSelection(Object selected) {
		this.selected = selected;
	}
	
	@Override
	public void showEditor() {
		factory.update(selected);
	}

}
