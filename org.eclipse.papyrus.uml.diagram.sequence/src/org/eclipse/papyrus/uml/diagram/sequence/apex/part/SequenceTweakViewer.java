package org.eclipse.papyrus.uml.diagram.sequence.apex.part;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.draw2d.Viewport;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.diagram.core.listener.DiagramEventBroker;
import org.eclipse.gmf.runtime.diagram.core.listener.NotificationListener;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.figures.ResizableCompartmentFigure;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramGraphicalViewer;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.papyrus.uml.diagram.sequence.apex.part.tweaks.TweakItem;
import org.eclipse.papyrus.uml.diagram.sequence.apex.part.tweaks.TweakViewer;
import org.eclipse.papyrus.uml.diagram.sequence.edit.parts.InteractionInteractionCompartmentEditPart;
import org.eclipse.swt.widgets.Composite;

public class SequenceTweakViewer extends TweakViewer {

	private final NotificationListenerImpl fNotificationListener = new NotificationListenerImpl();
	private TransactionalEditingDomain fEditingDomain;
	private IDiagramGraphicalViewer fViewer;
	private Viewport fViewport;
	
	public SequenceTweakViewer(Composite parent, int style) {
		super(parent, style);
	}
	
	public SequenceTweakViewer(Composite parent, int style, DiagramEditor editor) {
		super(parent, style);
		fEditingDomain = editor.getEditingDomain();
		fViewer = editor.getDiagramGraphicalViewer();
	}
	
	@Override
	protected void hookTweakItems(List<TweakItem> items) {
		for (TweakItem item : items) {
			if (item.getData() instanceof EObject) {
				EObject view = (EObject)item.getData();
				DiagramEventBroker broker = DiagramEventBroker.getInstance(fEditingDomain);
				broker.addNotificationListener(view, fNotificationListener);
			}
			if (item.getData() instanceof IGraphicalEditPart) {
				IGraphicalEditPart editPart = (IGraphicalEditPart)item.getData();
//				EObject element = editPart.resolveSemanticElement();
				EObject view = editPart.getNotationView();
				DiagramEventBroker broker = DiagramEventBroker.getInstance(editPart.getEditingDomain());
//				broker.addNotificationListener(element, fNotificationListener);
				broker.addNotificationListener(view, fNotificationListener);
			}
		}
	}

	@Override
	protected void unhookTweakItems(List<TweakItem> items) {
		for (TweakItem item : items) {
			if (item.getData() instanceof EObject) {
				EObject view = (EObject)item.getData();
				DiagramEventBroker broker = DiagramEventBroker.getInstance(fEditingDomain);
				broker.removeNotificationListener(view, fNotificationListener);
			}
			if (item.getData() instanceof IGraphicalEditPart) {
				IGraphicalEditPart editPart = (IGraphicalEditPart)item.getData();
//				EObject element = editPart.resolveSemanticElement();
				EObject view = editPart.getNotationView();
				DiagramEventBroker broker = DiagramEventBroker.getInstance(editPart.getEditingDomain());
//				broker.removeNotificationListener(element, fNotificationListener);
				broker.removeNotificationListener(view, fNotificationListener);
			}
		}
	}

	@Override
	public int getHorizontalOffset() {
		if (fViewport == null && fViewer != null) {
			Map registry = fViewer.getEditPartRegistry();
			for (Iterator iterator = registry.keySet().iterator(); iterator.hasNext(); ) {
				Object key = iterator.next();
				Object value = registry.get(key);
				if (value instanceof InteractionInteractionCompartmentEditPart) {
					InteractionInteractionCompartmentEditPart editPart = (InteractionInteractionCompartmentEditPart)value;
					ResizableCompartmentFigure compartmentFigure = editPart.getCompartmentFigure();
					fViewport = compartmentFigure.getScrollPane().getViewport();
					break;
				}
			}
		}
		if (fViewport != null) {
			Rectangle bounds = fViewport.getBounds().getCopy();
			fViewport.translateToAbsolute(bounds);
			return bounds.x;
		}
		return super.getHorizontalOffset();
	}
	
	private class NotificationListenerImpl implements NotificationListener {
		public void notifyChanged(Notification notification) {
			Object feature = notification.getFeature();
			if (NotationPackage.eINSTANCE.getLocation_X().equals(feature)) {
				refresh();
			} else if (NotationPackage.eINSTANCE.getSize_Width().equals(feature)) {
				refresh();
			}
		}
	}
}
