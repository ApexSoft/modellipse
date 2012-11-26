package org.eclipse.papyrus.uml.modelexplorer.apex;

import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.facet.infra.browser.uicore.internal.model.ITreeElement;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.papyrus.editor.PapyrusMultiDiagramEditor;
import org.eclipse.papyrus.infra.core.apex.ApexModellipseExplorerRoot;
import org.eclipse.papyrus.infra.core.apex.ApexProjectWrapper;
import org.eclipse.papyrus.infra.core.resource.NotFoundException;
import org.eclipse.papyrus.infra.core.resource.uml.UmlModel;
import org.eclipse.papyrus.infra.onefile.model.IPapyrusFile;
import org.eclipse.papyrus.infra.onefile.utils.OneFileUtils;
import org.eclipse.papyrus.views.modelexplorer.NavigatorUtils;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.WorkbenchException;
import org.eclipse.ui.ide.IDE;
import org.eclipse.uml2.uml.internal.impl.ModelImpl;

public class ApexDoubleClickListener implements IDoubleClickListener {

	public void doubleClick(DoubleClickEvent event) {
		ISelection selection = event.getSelection();
//		IPageMngr pageMngr = null;
//		//get the page Manager
//		try {
//			pageMngr = ServiceUtilsForActionHandlers.getInstance().getIPageMngr();
//		} catch (Exception e) {
//			Activator.log.error(Messages.DoubleClickListener_Error_NoLoadManagerToOpen, e);
//		}
//		if(pageMngr != null) {
			
			if(selection instanceof IStructuredSelection) {
				Iterator<?> iter = ((IStructuredSelection)selection).iterator();
				while(iter.hasNext()) {
					Object currentObject = iter.next();
					
					if ( currentObject instanceof IFile ) {
						// 에디터 열고
						IEditorPart editor = null;
						IFile diFile = (IFile)currentObject;
						if(OneFileUtils.isDi(diFile)) {
							editor = openEditor(diFile);
						}
						if ( editor != null && editor instanceof PapyrusMultiDiagramEditor ) {
							System.out.println("open by dblclick");
						}
						// 에디터에서 ServiceRegistry 가져와서
						// ApexProjectWrapper 구성
						// 트리 구성

						
						
						
						
						
					} else {
						EObject diag = NavigatorUtils.getElement(currentObject, EObject.class);
						
						if ( diag instanceof ModelImpl ) {
							
							try {
								ModelImpl model = (ModelImpl)diag;								
								Set<Entry<String, ITreeElement>> entrySet = ApexModellipseExplorerRoot.getProjectMap().entrySet();
								
								for ( Entry<String, ITreeElement> aProject : entrySet ) {
									ApexProjectWrapper project = (ApexProjectWrapper)aProject.getValue();									
									Set<Entry<String, UmlModel>> modelMapSet = project.getUmlModelMap().entrySet();
																	
									for ( Entry<String, UmlModel> umlModelEntry : modelMapSet ) {
										UmlModel umlModel = umlModelEntry.getValue();
										EObject modelRoot = umlModel.lookupRoot();
										
										if ( modelRoot.equals(model) ) {
											String diKey = umlModelEntry.getKey();
											IFile diFile = project.getDiFile(diKey);
											
											IEditorPart papyrusEditor = IDE.openEditor(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage(), diFile, true);
																					
										}
									}
								}
								
								
//								
//							} catch (Exception e) {
							} catch (WorkbenchException e) {
							} catch (NotFoundException e) {
							}
						}
//						if(pageMngr.allPages().contains(diag)) {
//							/**
//							 * Close the diagram if it was already open
//							 */
//							if(pageMngr.isOpen(diag)) {
//								pageMngr.closePage(diag);
//							}
//							pageMngr.openPage(diag);
//						}
					}
				}
			}

//		}
	}

	private IEditorPart openEditor(IFile diFile) {
		IEditorPart result = null;
		try {
			result = IDE.openEditor(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage(), diFile, true);
		} catch (PartInitException e) {
			e.printStackTrace();
		}	
		return result;
	}
	
//	public void doubleClick(final DoubleClickEvent event) {
//		SafeRunner.run(new NavigatorSafeRunnable() {
//			public void run() throws Exception {
//				handleDoubleClick(event);
//			}
//		});
//	}
//
//	protected void handleDoubleClick(DoubleClickEvent anEvent) {
//
//		IAction openHandler = getViewSite().getActionBars().getGlobalActionHandler(ICommonActionConstants.OPEN);
//		
//		if(openHandler == null) {
//			IStructuredSelection selection = (IStructuredSelection) anEvent
//					.getSelection();
//			Object element = selection.getFirstElement();
//	
//			TreeViewer viewer = getCommonViewer();
//			if (viewer.isExpandable(element)) {
//				viewer.setExpandedState(element, !viewer.getExpandedState(element));
//			}
//		}
//	}
}
