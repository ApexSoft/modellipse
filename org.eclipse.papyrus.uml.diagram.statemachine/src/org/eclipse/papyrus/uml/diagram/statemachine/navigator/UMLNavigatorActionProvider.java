package org.eclipse.papyrus.uml.diagram.statemachine.navigator;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.common.ui.URIEditorInput;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.workspace.util.WorkspaceSynchronizer;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.papyrus.uml.diagram.statemachine.edit.parts.PackageEditPart;
import org.eclipse.papyrus.uml.diagram.statemachine.part.Messages;
import org.eclipse.papyrus.uml.diagram.statemachine.part.UMLDiagramEditor;
import org.eclipse.papyrus.uml.diagram.statemachine.part.UMLDiagramEditorPlugin;
import org.eclipse.papyrus.uml.diagram.statemachine.part.UMLVisualIDRegistry;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.navigator.CommonActionProvider;
import org.eclipse.ui.navigator.ICommonActionConstants;
import org.eclipse.ui.navigator.ICommonActionExtensionSite;
import org.eclipse.ui.navigator.ICommonViewerWorkbenchSite;
import org.eclipse.ui.part.FileEditorInput;

/**
 * @generated
 */
public class UMLNavigatorActionProvider extends CommonActionProvider {

	/**
	 * @generated
	 */
	private static class OpenDiagramAction extends Action {

		/**
		 * @generated
		 */
		private static IEditorInput getEditorInput(Diagram diagram) {
			Resource diagramResource = diagram.eResource();
			for(EObject nextEObject : diagramResource.getContents()) {
				if(nextEObject == diagram) {
					return new FileEditorInput(WorkspaceSynchronizer.getFile(diagramResource));
				}
				if(nextEObject instanceof Diagram) {
					break;
				}
			}
			URI uri = EcoreUtil.getURI(diagram);
			String editorName = uri.lastSegment() + '#' + diagram.eResource().getContents().indexOf(diagram);
			IEditorInput editorInput = new URIEditorInput(uri, editorName);
			return editorInput;
		}

		/**
		 * @generated
		 */
		private Diagram myDiagram;

		/**
		 * @generated
		 */
		private ICommonViewerWorkbenchSite myViewerSite;

		/**
		 * @generated
		 */
		public OpenDiagramAction(ICommonViewerWorkbenchSite viewerSite) {
			super(Messages.NavigatorActionProvider_OpenDiagramActionName);
			myViewerSite = viewerSite;
		}

		/**
		 * @generated
		 */
		public void run() {
			if(myDiagram == null || myDiagram.eResource() == null) {
				return;
			}

			IEditorInput editorInput = getEditorInput(myDiagram);
			IWorkbenchPage page = myViewerSite.getPage();
			try {
				page.openEditor(editorInput, UMLDiagramEditor.ID);
			} catch (PartInitException e) {
				UMLDiagramEditorPlugin.getInstance().logError("Exception while openning diagram", e); //$NON-NLS-1$
			}
		}

		/**
		 * @generated
		 */
		public final void selectionChanged(IStructuredSelection selection) {
			myDiagram = null;
			if(selection.size() == 1) {
				Object selectedElement = selection.getFirstElement();
				if(selectedElement instanceof UMLNavigatorItem) {
					selectedElement = ((UMLNavigatorItem)selectedElement).getView();
				} else if(selectedElement instanceof IAdaptable) {
					selectedElement = ((IAdaptable)selectedElement).getAdapter(View.class);
				}
				if(selectedElement instanceof Diagram) {
					Diagram diagram = (Diagram)selectedElement;
					if(PackageEditPart.MODEL_ID.equals(UMLVisualIDRegistry.getModelID(diagram))) {
						myDiagram = diagram;
					}
				}
			}
			setEnabled(myDiagram != null);
		}
	}

	/**
	 * @generated
	 */
	private boolean myContribute;

	/**
	 * @generated
	 */
	private OpenDiagramAction myOpenDiagramAction;

	/**
	 * @generated
	 */
	public void fillActionBars(IActionBars actionBars) {
		if(!myContribute) {
			return;
		}
		IStructuredSelection selection = (IStructuredSelection)getContext().getSelection();
		myOpenDiagramAction.selectionChanged(selection);
		if(myOpenDiagramAction.isEnabled()) {
			actionBars.setGlobalActionHandler(ICommonActionConstants.OPEN, myOpenDiagramAction);
		}
	}

	/**
	 * @generated
	 */
	public void fillContextMenu(IMenuManager menu) {
	}

	/**
	 * @generated
	 */
	public void init(ICommonActionExtensionSite aSite) {
		super.init(aSite);
		if(aSite.getViewSite() instanceof ICommonViewerWorkbenchSite) {
			myContribute = true;
			makeActions((ICommonViewerWorkbenchSite)aSite.getViewSite());
		} else {
			myContribute = false;
		}
	}

	/**
	 * @generated
	 */
	private void makeActions(ICommonViewerWorkbenchSite viewerSite) {
		myOpenDiagramAction = new OpenDiagramAction(viewerSite);
	}
}
