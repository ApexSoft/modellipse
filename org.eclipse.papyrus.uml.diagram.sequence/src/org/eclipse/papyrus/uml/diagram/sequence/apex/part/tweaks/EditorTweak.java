package org.eclipse.papyrus.uml.diagram.sequence.apex.part.tweaks;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.IOpenListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.OpenEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.papyrus.uml.diagram.common.util.DiagramEditPartsUtil;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.ui.IPartListener;
import org.eclipse.ui.IWorkbenchPart;

public abstract class EditorTweak implements ITweak {
	
	private static final String ACTIVE_TAB_BG_END= "org.eclipse.ui.workbench.ACTIVE_TAB_BG_END"; //$NON-NLS-1$
	
	private DiagramEditor fEditorPart;

	private Composite fComposite;
	
	private TweakViewer fTweakViewer;
	
	private Listener fDisplayFocusListener;
	
	private IPropertyChangeListener fPropertyChangeListener;
	
	private IPartListener fPartListener;
	
	private boolean fHasFocus, fIsActive;
	
	private ISelection fOldSelection;
	
	/**
	 * @param editorPart
	 */
	public EditorTweak(DiagramEditor editorPart) {
		setDiagramEditor(editorPart);
	}
	
	protected abstract TweakViewer createViewer(Composite parent);

	protected abstract Object getCurrentInput();
	
	protected abstract boolean open(Object element);
	
	protected abstract boolean reveal(Object element);
	
	protected abstract void activateTweak();

	protected abstract void deactivateTweak();
	
	/* (non-Javadoc)
	 * @see org.eclipse.papyrus.uml.diagram.sequence.apex.part.tweaks.ITweak#createContent(org.eclipse.swt.widgets.Composite)
	 */
	public Control createContent(Composite parent) {
		fComposite = new Composite(parent, SWT.NONE);
		fComposite.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false));
		GridLayout layout = new GridLayout(1, false);
		layout.horizontalSpacing = 0;
		layout.verticalSpacing = 0;
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		fComposite.setLayout(layout);
		fComposite.setBackground(Display.getDefault().getSystemColor(SWT.COLOR_WHITE));

		fDisplayFocusListener= new Listener() {
			public void handleEvent(Event event) {
				if (isTweakEvent(event)) {
					if (fHasFocus)
						return;

					fIsActive= true;

					focusGained();
				} else {
					if (!fIsActive)
						return;

					if (!fHasFocus)
						return;

					focusLost();
				}
			}
		};
		Display.getCurrent().addFilter(SWT.FocusIn, fDisplayFocusListener);
		
		fTweakViewer = createViewer(fComposite);
		if (fTweakViewer != null) {
			fTweakViewer.getControl().setBackground(Display.getDefault().getSystemColor(SWT.COLOR_WHITE));

			fTweakViewer.addDoubleClickListener(new IDoubleClickListener() {
				public void doubleClick(DoubleClickEvent event) {
					// TODO Auto-generated method stub

				}
			});

			fTweakViewer.addOpenListener(new IOpenListener() {
				public void open(OpenEvent event) {
					doRevealOrOpen(event.getSelection());
				}
			});

			fPropertyChangeListener= new IPropertyChangeListener() {
				public void propertyChange(PropertyChangeEvent event) {
					if (ACTIVE_TAB_BG_END.equals(event.getProperty())) {
						if (fComposite.isFocusControl()) {
							fComposite.setBackground(JFaceResources.getColorRegistry().get(ACTIVE_TAB_BG_END));
						}
					}
				}
			};
			JFaceResources.getColorRegistry().addListener(fPropertyChangeListener);
		}
		
		return fComposite;
	}
	
	public ISelectionProvider getSelectionProvider() {
		return fTweakViewer;
	}

	public void activate() {
		if (fTweakViewer.getSelection().isEmpty()) {
			fTweakViewer.setSelection(new StructuredSelection(fTweakViewer.getInput()));
		}
	}

	public boolean isActive() {
		return fIsActive;
	}
	
	public void setInput(Object element) {
		if (element == null) {
			return;
		}
		
		if (fTweakViewer == null) {
			return;
		}
		
		Object input = fTweakViewer.getInput();
		if (input == element || element.equals(input)) {
//			fTweakViewer.refresh();
//			return;
		}
		
		fTweakViewer.setInput(element);
	}
	
	public void dispose() {
		if (fDisplayFocusListener != null) {
			Display.getCurrent().removeListener(SWT.FocusIn, fDisplayFocusListener);
		}
		
		if (fPropertyChangeListener != null) {
			JFaceResources.getColorRegistry().removeListener(fPropertyChangeListener);
		}
		
		if (fPartListener != null) {
			fEditorPart.getSite().getPage().removePartListener(fPartListener);
		}
		
		setDiagramEditor(null);
	}

	protected DiagramEditor getDiagramEditor() {
		return fEditorPart;
	}
	
	protected void setDiagramEditor(DiagramEditor editorPart) {
		fEditorPart = editorPart;
		
		fPartListener = new IPartListener() {
			public void partOpened(IWorkbenchPart part) {
			}
			
			public void partDeactivated(IWorkbenchPart part) {
				if (part == fEditorPart && fHasFocus) {
					focusLost();
				}
			}
			
			public void partClosed(IWorkbenchPart part) {
			}
			
			public void partBroughtToTop(IWorkbenchPart part) {
			}
			
			public void partActivated(IWorkbenchPart part) {
				if (part == fEditorPart && fHasFocus) {
					focusGained();
				}
			}
		};
		fEditorPart.getSite().getPage().addPartListener(fPartListener);
	}
	
	private void doRevealOrOpen(ISelection selection) {
		if (doReveal(selection)) {
			fEditorPart.getDiagramGraphicalViewer().getControl().setFocus();
		} else if (doOpen(selection)) {
			fIsActive= false;
			focusLost();
			fTweakViewer.setInput(getCurrentInput());
		}
	}

	private boolean doOpen(ISelection selection) {
		if (!(selection instanceof StructuredSelection))
			return false;

		StructuredSelection structuredSelection= (StructuredSelection) selection;
		if (structuredSelection.isEmpty())
			return false;

		return open(structuredSelection.getFirstElement());
	}

	private boolean doReveal(ISelection selection) {
		if (!(selection instanceof StructuredSelection))
			return false;

		StructuredSelection structuredSelection= (StructuredSelection) selection;
		if (structuredSelection.isEmpty())
			return false;

		if (fOldSelection != null) {
			getDiagramEditor().getDiagramGraphicalViewer().setSelection(fOldSelection);

			boolean result= reveal(structuredSelection.getFirstElement());

			fOldSelection= getDiagramEditor().getDiagramGraphicalViewer().getSelection();
			getDiagramEditor().getDiagramGraphicalViewer().setSelection(new StructuredSelection(this));
			return result;
		} else {
			return reveal(structuredSelection.getFirstElement());
		}
	}
	
	/**
	 * Focus has been transfered into the tweak.
	 */
	private void focusGained() {
		if (fHasFocus)
			focusLost();

		fComposite.setBackground(JFaceResources.getColorRegistry().get(ACTIVE_TAB_BG_END));
		fHasFocus= true;

		activateTweak();

//		getDiagramEditor().getEditorSite().getActionBars().updateActionBars();
		
//		fOldSelection = getDiagramEditor().getDiagramGraphicalViewer().getSelection();
		
//		getDiagramEditor().getDiagramGraphicalViewer().setSelection(new StructuredSelection(this));
	}

	/**
	 * Focus has been revoked from the tweak.
	 */
	private void focusLost() {
		if (!fHasFocus)
			return;

		fComposite.setBackground(null);
		fHasFocus= false;

		deactivateTweak();

//		getDiagramEditor().getEditorSite().getActionBars().updateActionBars();
		
//		getDiagramEditor().getDiagramGraphicalViewer().setSelection(fOldSelection);
		
		fOldSelection = null;
	}

	private boolean isTweakEvent(Event event) {
		if (fTweakViewer == null)
			return false;

		Widget item= event.widget;
		if (!(item instanceof Control))
			return false;

		return isChild((Control) item, fTweakViewer.getControl());
	}

	private boolean isChild(Control child, Control parent) {
		if (child == null)
			return false;

		if (child == parent)
			return true;

		return isChild(child.getParent(), parent);
	}

}
