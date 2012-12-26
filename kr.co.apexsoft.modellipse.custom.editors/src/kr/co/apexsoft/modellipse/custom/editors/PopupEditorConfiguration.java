package kr.co.apexsoft.modellipse.custom.editors;

import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.text.source.SourceViewerConfiguration;
import org.eclipse.papyrus.extensionpoints.editors.configuration.IPopupEditorConfiguration;
import org.eclipse.papyrus.extensionpoints.editors.ui.IPopupEditorHelper;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;

public abstract class PopupEditorConfiguration implements IPopupEditorConfiguration {
	
	private String language = "";
	
	protected enum EditorType {
		NONE, LIST, TREE, TABLE,
	}

	public PopupEditorConfiguration() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getStyle() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Point getPreferedSize() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getTextToEdit(Object editedObject) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object preEditAction(Object editedObject) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object postEditAction(Object editedObject, String newText) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Composite createExtendedDialogArea(Composite parent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getLanguage() {
		return language;
	}

	@Override
	public void setLanguage(String language) {
		this.language = "" + language;
	}

	@Override
	public IInputValidator getInputValidator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setInputValidator(IInputValidator validator) {
		// TODO Auto-generated method stub

	}

	@Override
	public Selection getTextSelection(String value, Object editedObject) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public abstract IPopupEditorHelper createPopupEditorHelper(Object editPart);
	
	public IPopupEditorHelper createPopupEditorHelper(Object editPart, EditorType editorType, IPopupEditorInputFactory factory) {
		if(editPart instanceof IGraphicalEditPart) {
			if (EditorType.NONE.equals(editorType)) {
				return new InitSelectedEditorHelper((IGraphicalEditPart) editPart, factory);
			}
			if (EditorType.LIST.equals(editorType)) {
				return new PopupListEditorHelper((IGraphicalEditPart) editPart, factory);
			}
		}
		return null;
	}

	@Override
	public SourceViewerConfiguration getSourceViewerConfiguration() {
		// TODO Auto-generated method stub
		return null;
	}

}
