package org.eclipse.papyrus.uml.diagram.sequence.apex.part;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import org.eclipse.draw2d.FigureCanvas;
import org.eclipse.draw2d.RangeModel;
import org.eclipse.draw2d.Viewport;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.core.listener.DiagramEventBroker;
import org.eclipse.gmf.runtime.diagram.core.listener.NotificationListener;
import org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramGraphicalViewer;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.papyrus.uml.diagram.common.util.DiagramEditPartsUtil;
import org.eclipse.papyrus.uml.diagram.sequence.apex.part.tweaks.EditorTweak;
import org.eclipse.papyrus.uml.diagram.sequence.apex.part.tweaks.TweakStyledLabelProvider;
import org.eclipse.papyrus.uml.diagram.sequence.apex.part.tweaks.TweakViewer;
import org.eclipse.papyrus.uml.diagram.sequence.part.UMLDiagramEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.uml2.uml.UMLPackage;

public class SequenceEditorTweak extends EditorTweak implements PropertyChangeListener, NotificationListener {

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
		
		Diagram diagram = getDiagramEditor().getDiagram();
		DiagramEventBroker broker = DiagramEventBroker.getInstance(getDiagramEditor().getEditingDomain());
		List<View> views = DiagramEditPartsUtil.findViews(diagram.getElement(), viewer);
		for (View view : views) {
			broker.addNotificationListener(view, this);
		}
	}
	
	@Override
	protected TweakViewer createViewer(Composite parent) {
		fViewer = new SequenceTweakViewer(parent, SWT.HORIZONTAL, getDiagramEditor().getEditingDomain());
		fViewer.setLabelProvider(createLabelProvider());
		fViewer.setContentProvider(createContentProvider());
		fViewer.setToolTipLabelProvider(createTooltipLabelProvider());
		return fViewer;
	}

	@Override
	protected Object getCurrentInput() {
		return getDiagramEditor().getDiagram();
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
		
		if (model != null) {
			if (fViewer != null && fViewer.getControl().isVisible()) {
				fViewer.refresh();
			}
		}
	}

	public void notifyChanged(Notification notification) {
		Object notifier = notification.getNotifier();
		if (notifier instanceof View) {
			EObject parentElement = ((View)notifier).getElement();
			if (Notification.ADD == notification.getEventType()) {
				if (notification.getNewValue() instanceof View) {
					View newView = (View)notification.getNewValue();
					if (!parentElement.equals(newView.getElement())) {
						DiagramEventBroker broker = DiagramEventBroker.getInstance(getDiagramEditor().getEditingDomain());
						broker.addNotificationListener(newView, this);
					}
				}
			} else if (Notification.REMOVE == notification.getEventType()) {
				if (notification.getOldValue() instanceof View) {
					View oldView = (View)notification.getOldValue();
					DiagramEventBroker broker = DiagramEventBroker.getInstance(getDiagramEditor().getEditingDomain());
					broker.removeNotificationListener(oldView, this);
				}
			}
		}
		Object feature = notification.getFeature();
		if (NotationPackage.eINSTANCE.getLocation_X().equals(feature) ||
				NotationPackage.eINSTANCE.getSize_Width().equals(feature)) {
			if (fViewer != null && fViewer.getControl().isVisible()) {
				fViewer.setInput(getCurrentInput());
			}
		}
	}

}
