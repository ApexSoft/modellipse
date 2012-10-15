package org.eclipse.papyrus.uml.diagram.usecase.preferences;

import org.eclipse.jface.preference.RadioGroupFieldEditor;
import org.eclipse.papyrus.uml.diagram.usecase.edit.parts.UseCaseDiagramEditPart;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;

public class SubjectPreferencePage extends ClassifierPreferencePage {

	
	public static final String USE_CASE_SUBJECT_SEMANTIC_HINT = "Subject_SemanticHint";
	
	@Override
	protected void createPageContents(Composite parent) {
		// TODO Auto-generated method stub
		super.createPageContents(parent);
	
		addAbstractGroup( new RadioGroup(parent, USE_CASE_SUBJECT_SEMANTIC_HINT, this));

	}
	
}
