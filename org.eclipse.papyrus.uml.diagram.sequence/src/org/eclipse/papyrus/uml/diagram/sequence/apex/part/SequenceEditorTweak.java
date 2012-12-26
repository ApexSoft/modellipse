package org.eclipse.papyrus.uml.diagram.sequence.apex.part;

import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.papyrus.uml.diagram.sequence.apex.part.tweaks.EditorTweak;
import org.eclipse.papyrus.uml.diagram.sequence.apex.part.tweaks.TweakStyledLabelProvider;
import org.eclipse.papyrus.uml.diagram.sequence.apex.part.tweaks.TweakViewer;
import org.eclipse.papyrus.uml.diagram.sequence.part.UMLDiagramEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

public class SequenceEditorTweak extends EditorTweak {

	private TweakViewer fViewer;
	
	public SequenceEditorTweak(UMLDiagramEditor editorPart) {
		super(editorPart);
	}

	@Override
	protected TweakViewer createViewer(Composite parent) {
		fViewer = new SequenceTweakViewer(parent, SWT.HORIZONTAL);
		fViewer.setLabelProvider(createLabelProvider());
		fViewer.setContentProvider(createContentProvider());
		fViewer.setToolTipLabelProvider(createTooltipLabelProvider());
		return fViewer;
	}

	@Override
	protected Object getCurrentInput() {
		return getDiagramEditor().getDiagramEditPart();
	}

	@Override
	public void setInput(Object element) {
		if (element == null) {
			element = getCurrentInput();
		}
		
		super.setInput(element);
	}
	
	@Override
	protected boolean open(Object element) {
		return false;
	}

	@Override
	protected boolean reveal(Object element) {
		return false;
	}

	@Override
	protected void activateTweak() {
		
	}

	@Override
	protected void deactivateTweak() {
		
	}
	
	private ILabelProvider createLabelProvider() {
//		AdapterFactoryLabelProvider adapterFactoryLabelProvider = new AdapterFactoryLabelProvider(UMLDiagramEditorPlugin.getInstance().getItemProvidersAdapterFactory());
//		SequenceTweakStyledLabelProvider labelProvider = new SequenceTweakStyledLabelProvider(adapterFactoryLabelProvider);
		LifelineLabelProvider lifelineLabelProvider = new LifelineLabelProvider();
		TweakStyledLabelProvider labelProvider = new TweakStyledLabelProvider(lifelineLabelProvider);
		return new DecoratingSequenceLabelProvider(labelProvider);
	}
	
	private IContentProvider createContentProvider() {
		return new SequenceTweakContentProvider(getDiagramEditor().getDiagramGraphicalViewer());
	}

	private ILabelProvider createTooltipLabelProvider() {
		return null;
	}
}
