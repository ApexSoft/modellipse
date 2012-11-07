package kr.co.apexsoft.modellipse.custom.editors;

import java.util.Collection;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditDomain;
import org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramEditDomain;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.papyrus.extensionpoints.editors.ui.IPopupEditorHelper;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.dialogs.FilteredList;

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
	
	private FilteredList filteredList;
	
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
			if (filteredList.getSelection().length > 0) {
				factory.update(filteredList.getSelection()[0]);
			}
		}
		
		listEditorComposite.setVisible(false);
		listEditorComposite.dispose();
	}
	
	
    private boolean fIgnoreCase = true;

    private boolean fMatchEmptyString = true;

    private boolean fAllowDuplicates = true;
    
	private void createListEditor() {
		diagramShell = diagramEditor.getSite().getShell();
		
		listEditorComposite = new Shell(SWT.RESIZE);
		GridLayout layout = new GridLayout();
		layout.marginWidth = 0;
		layout.marginHeight = 0;
		listEditorComposite.setLayout(layout);
		
		setEditorBounds();
		
		filteredList = new FilteredList(listEditorComposite, SWT.SINGLE | SWT.FULL_SELECTION | SWT.BORDER,
				factory.getLabelProvider(), fIgnoreCase, fMatchEmptyString, fAllowDuplicates);
		GridData gridData = new GridData(GridData.FILL_BOTH);
		filteredList.setLayoutData(gridData);
		
		filteredList.setComparator(factory.getComparator());
		
		Control[] children = filteredList.getChildren();
		for (Control control : children) {
			control.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					int keyCode = e.keyCode;
					if (keyCode == SWT.ESC) {
						PopupListEditorHelper.this.closeEditor(false);
					}
				}
			});

			control.addFocusListener(new FocusAdapter() {
				@Override
				public void focusLost(FocusEvent e) {
					PopupListEditorHelper.this.checkedClose();
				}
			});

			control.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseDoubleClick(MouseEvent e) {
					PopupListEditorHelper.this.closeEditor(true);
				}
			});
		}
		
		Object input = factory.getInput();
		Object[] elements = null;
		if (input instanceof Collection<?>) {
			elements = ((Collection<?>)input).toArray();
		} else if (input instanceof Object[]) {
			elements = (Object[])input;
		} else {
			elements = new Object[] { input };
		}
		filteredList.setElements(elements);
		
		TableViewer viewer = new TableViewer(listEditorComposite, SWT.SINGLE | SWT.FULL_SELECTION | SWT.BORDER);
		viewer.setLabelProvider(factory.getLabelProvider());
		viewer.setContentProvider(factory.getContentprovider());
		viewer.setInput(input);
		viewer.getTable().setLinesVisible(true);
		
		viewer.getControl().setLayoutData(new GridData(GridData.FILL_BOTH));
		
		listEditorComposite.setVisible(true);
		filteredList.setFocus();
	}
	
	private void setEditorBounds() {
		IFigure figure = hostEditPart.getFigure();
		Rectangle bounds = figure.getBounds().getCopy();
		figure.translateToAbsolute(bounds);
		Point newCoord = diagramShell.getDisplay().map(hostEditPart.getViewer().getControl(), null, new Point(bounds.x, bounds.y));
		bounds.x = newCoord.x;
		bounds.y = newCoord.y;
		
		listEditorComposite.setBounds(bounds.x, bounds.y, 200, 200);
	}
	
	private void checkedClose() {
		closeEditor(false);
	}

}
