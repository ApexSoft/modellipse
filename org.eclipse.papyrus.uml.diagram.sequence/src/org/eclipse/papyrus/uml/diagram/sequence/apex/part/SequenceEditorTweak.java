package org.eclipse.papyrus.uml.diagram.sequence.apex.part;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.eclipse.draw2d.FigureCanvas;
import org.eclipse.draw2d.RangeModel;
import org.eclipse.draw2d.Viewport;
import org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramGraphicalViewer;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.papyrus.uml.diagram.sequence.apex.part.tweaks.EditorTweak;
import org.eclipse.papyrus.uml.diagram.sequence.apex.part.tweaks.TweakStyledLabelProvider;
import org.eclipse.papyrus.uml.diagram.sequence.apex.part.tweaks.TweakViewer;
import org.eclipse.papyrus.uml.diagram.sequence.part.UMLDiagramEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

public class SequenceEditorTweak extends EditorTweak implements PropertyChangeListener {

	private TweakViewer fViewer;
	
	public SequenceEditorTweak(UMLDiagramEditor editorPart) {
		super(editorPart);
	}

	public void init() {
		IDiagramGraphicalViewer viewer = getDiagramEditor().getDiagramGraphicalViewer();
		Control control = viewer.getControl();
		if (control instanceof FigureCanvas) {
			FigureCanvas canvas = (FigureCanvas)control;
			canvas.getViewport().getHorizontalRangeModel().addPropertyChangeListener(this);
		}
	}
	
	@Override
	public Control createContent(Composite parent) {
		return super.createContent(parent);
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

	public void propertyChange(PropertyChangeEvent evt) {
		RangeModel model = null;
		if (evt.getSource() instanceof RangeModel) {
			model = (RangeModel)evt.getSource();
		} else if (evt.getSource() instanceof Viewport) {
			if (Viewport.PROPERTY_VIEW_LOCATION.equals(evt.getPropertyName())) {
				Viewport viewport = (Viewport)evt.getSource();
				model = viewport.getHorizontalRangeModel();
			}
		}
		
		if (model != null && fViewer != null) {
			fViewer.refresh();
		}
	}


}
