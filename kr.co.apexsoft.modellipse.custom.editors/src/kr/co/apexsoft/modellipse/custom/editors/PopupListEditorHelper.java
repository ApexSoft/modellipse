package kr.co.apexsoft.modellipse.custom.editors;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditDomain;
import org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramEditDomain;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.papyrus.extensionpoints.editors.ui.IPopupEditorHelper;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.ui.IEditorPart;

public class PopupListEditorHelper implements IPopupEditorHelper {

	private IGraphicalEditPart hostEditPart;
	
	private static boolean ignoreFocusLost;
	
	private IPopupEditorInputFactory factory;
	
	public PopupListEditorHelper(IGraphicalEditPart editPart, IPopupEditorInputFactory factory) {
		this.hostEditPart = editPart;
		this.factory = factory;
		ignoreFocusLost = false ;
	}
	
	
	private EObject semanticElement;
	
	private String semanticElementFragment;
	
	private IEditorPart diagramEditor;
	
	private Shell diagramShell;
	
	private Composite listEditorComposite;
	
	private TableViewer viewer;
	
	private Listener popupListener = new Listener() {
		@Override
		public void handleEvent(Event event) {
			switch(event.type) {
			case SWT.KeyDown:
				if (event.keyCode == SWT.ESC) {
					PopupListEditorHelper.this.closeEditor(false);
				}
				break;
			case SWT.MouseUp:
				PopupListEditorHelper.this.closeEditor(true);
				break;
			case SWT.FocusOut:
				PopupListEditorHelper.this.checkedClose();
				break;
			}
		}
	};
	
	@Override
	public void showEditor() {
		try {
			semanticElement = hostEditPart.resolveSemanticElement();
			if (semanticElement == null) {
				return;
			}
			
			Resource semanticResource = semanticElement.eResource();
			semanticElementFragment = semanticResource.getURIFragment(semanticElement);
			if (semanticElementFragment == null || "".equals(semanticElementFragment)) {
				return;
			}
			
			IDiagramEditDomain diagramEditDomain = hostEditPart.getDiagramEditDomain();
			diagramEditor = ((DiagramEditDomain)diagramEditDomain).getEditorPart();
			
			createListEditor();
		} catch(Exception e) {
			Activator.logError(e);
		}
	}
	
	/**
	 * Editor을 닫음
	 * @param isReconcile 수정사항에 대한 업데이트 여부
	 */
	public void closeEditor(boolean isReconcile) {
		if (isReconcile) {
			if (viewer.getSelection() instanceof IStructuredSelection) {
				IStructuredSelection selection = (IStructuredSelection)viewer.getSelection();
				ignoreFocusLost = true;
				factory.update(selection.getFirstElement());
				ignoreFocusLost = false;
			}
		}
//		if (ignoreFocusLost) {
//			return;
//		}
		
		if (listEditorComposite != null && !listEditorComposite.isDisposed()) {
			listEditorComposite.setVisible(false);
			listEditorComposite.dispose();
		}
	}
	
	private void createListEditor() {
		diagramShell = diagramEditor.getSite().getShell();
		
		listEditorComposite = new Shell(SWT.RESIZE);
		GridLayout layout = new GridLayout();
		layout.marginWidth = 0;
		layout.marginHeight = 0;
		listEditorComposite.setLayout(layout);
		
		Object input = factory.getInput();
		
		Table table = new Table(listEditorComposite, SWT.SINGLE | SWT.FULL_SELECTION);
		table.setLayoutData(new GridData(GridData.FILL_BOTH));

		viewer = new TableViewer(table);
		viewer.setLabelProvider(factory.getLabelProvider());
		viewer.setContentProvider(factory.getContentprovider());
		viewer.setInput(input);
		
		table.addListener(SWT.KeyDown, popupListener);
		table.addListener(SWT.MouseUp, popupListener);
		table.addListener(SWT.FocusOut, popupListener);
		
		setEditorBounds();
		
		listEditorComposite.setVisible(true);
		table.setFocus();
	}
	
	private void setEditorBounds() {
		IFigure figure = hostEditPart.getFigure();
		Rectangle bounds = figure.getBounds().getCopy();
		figure.translateToAbsolute(bounds);
		Point newCoord = diagramShell.getDisplay().map(hostEditPart.getViewer().getControl(), null, new Point(bounds.x, bounds.y));
		bounds.x = newCoord.x;
		bounds.y = newCoord.y;
		
		Control control = listEditorComposite;
		if (viewer != null) {
			control = viewer.getTable();
		}
		Dimension size = new Dimension(control.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		
		listEditorComposite.setBounds(bounds.x, bounds.y, size.width, size.height);
	}
	
	private void checkedClose() {
		closeEditor(false);
	}

}
