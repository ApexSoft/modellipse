package org.eclipse.papyrus.uml.modelexplorer.apex;

import java.util.Iterator;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.papyrus.editor.PapyrusMultiDiagramEditor;
import org.eclipse.papyrus.infra.core.apex.ApexModellipseProjectMap;
import org.eclipse.papyrus.infra.core.services.ServicesRegistry;
import org.eclipse.papyrus.infra.onefile.utils.OneFileUtils;
import org.eclipse.ui.IEditorPart;

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
						editor = ApexModellipseProjectMap.openEditor(diFile);
					}
					
					if ( editor != null && editor instanceof PapyrusMultiDiagramEditor ) {
						ServicesRegistry servicesRegistry = ((PapyrusMultiDiagramEditor)editor).getServicesRegistry();
						ApexModellipseProjectMap.setUpModelServices(diFile, servicesRegistry);
						Viewer aViewer = event.getViewer();
						aViewer.refresh();
					}					
				} 
//				else {
//					EObject diag = NavigatorUtils.getElement(currentObject, EObject.class);
//					
//					if ( diag instanceof ModelImpl ) {
//						
//						try {
//							ModelImpl model = (ModelImpl)diag;								
//							Set<Entry<String, ITreeElement>> entrySet = ApexModellipseExplorerRoot.getProjectMap().entrySet();
//							
//							for ( Entry<String, ITreeElement> aProject : entrySet ) {
//								ApexProjectWrapper project = (ApexProjectWrapper)aProject.getValue();									
//								Set<Entry<String, UmlModel>> modelMapSet = project.getUmlModelMap().entrySet();
//																
//								for ( Entry<String, UmlModel> umlModelEntry : modelMapSet ) {
//									UmlModel umlModel = umlModelEntry.getValue();
//									EObject modelRoot = umlModel.lookupRoot();
//									
//									if ( modelRoot.equals(model) ) {
//										String diKey = umlModelEntry.getKey();
//										IFile diFile = project.getDiFile(diKey);
//										
//										IEditorPart papyrusEditor = IDE.openEditor(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage(), diFile, true);
//																				
//									}
//								}
//							}
//							
//							
////								
////							} catch (Exception e) {
//						} catch (WorkbenchException e) {
//						} catch (NotFoundException e) {
//						}
//					}
//					if(pageMngr.allPages().contains(diag)) {
//						/**
//						 * Close the diagram if it was already open
//						 */
//						if(pageMngr.isOpen(diag)) {
//							pageMngr.closePage(diag);
//						}
//						pageMngr.openPage(diag);
//					}
				}
			}
//		}
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
