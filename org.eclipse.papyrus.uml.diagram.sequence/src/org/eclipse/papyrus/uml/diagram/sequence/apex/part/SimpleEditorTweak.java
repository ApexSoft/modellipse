package org.eclipse.papyrus.uml.diagram.sequence.apex.part;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.notation.Bounds;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.LayoutConstraint;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.papyrus.uml.diagram.sequence.apex.part.tweaks.EditorTweak;
import org.eclipse.papyrus.uml.diagram.sequence.apex.part.tweaks.TweakViewer;
import org.eclipse.papyrus.uml.diagram.sequence.part.UMLDiagramEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.uml2.uml.Lifeline;
import org.eclipse.uml2.uml.NamedElement;

public class SimpleEditorTweak extends EditorTweak {

	private UMLDiagramEditor editor;
	
	public SimpleEditorTweak(UMLDiagramEditor editorPart) {
		super(editorPart);
		this.editor = editorPart;
	}

	@Override
	public Control createContent(Composite parent) {
		Composite composite = (Composite)super.createContent(parent);
		Composite subComposite = new Composite(composite, SWT.NONE);
		subComposite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		subComposite.setLayout(new FormLayout());
		
		for (Object item : getItems()) {
			if (item instanceof Node) {
				Rectangle r = null;
				LayoutConstraint constraint = ((Node) item).getLayoutConstraint();
				if (constraint instanceof Bounds) {
					Bounds b = (Bounds)constraint;
					r = new Rectangle(b.getX(), b.getY(), b.getWidth(), b.getHeight());
				}
				
				String name = "";
				EObject element = ((Node) item).getElement();
				if (element instanceof NamedElement) {
					name = ((NamedElement) element).getName();
				}
				
				Label label = new Label(subComposite, SWT.NONE);
				label.setText(name);
				label.setBackground(new Color(null, 255, 255, 255));
				FormData formData = new FormData();
				formData.top = new FormAttachment(0, 3);
				formData.bottom = new FormAttachment(100, -3);
				formData.left = new FormAttachment(0, r.x);
				formData.right = new FormAttachment(0, r.x + r.width);
				label.setLayoutData(formData);
			}
		}
		
		return composite;
	}

	protected Object[] getItems() {
		if (editor != null) {
			Map<Lifeline, View> views = new HashMap<Lifeline, View>();
			Diagram diagram = editor.getDiagram();
			for (Object child : diagram.getChildren()) {
				if (child instanceof View) {
					findLifelineViews((View)child, views);
				}
			}
			
			return views.values().toArray();
		}

		return new Object[0];
	}
	
	void findLifelineViews(View view, Map<Lifeline, View> views) {
		if (view.getElement() instanceof Lifeline) {
			Lifeline lifeline = (Lifeline)view.getElement();
			if (!views.containsKey(lifeline)) {
				views.put(lifeline, view);
			}
		}
		for (Object child : view.getChildren()) {
			if (child instanceof View) {
				findLifelineViews((View)child, views);
			}
		}
	}

	@Override
	protected TweakViewer createViewer(Composite parent) {
		return null;
	}

	@Override
	protected Object getCurrentInput() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected boolean open(Object element) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected boolean reveal(Object element) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void activateTweak() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void deactivateTweak() {
		// TODO Auto-generated method stub
		
	}
}
