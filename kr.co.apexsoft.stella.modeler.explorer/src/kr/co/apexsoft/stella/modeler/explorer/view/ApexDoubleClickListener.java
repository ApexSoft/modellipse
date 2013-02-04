package kr.co.apexsoft.stella.modeler.explorer.view;

import java.util.Iterator;

import kr.co.apexsoft.stella.modeler.explorer.core.ApexStellaProjectMap;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.papyrus.editor.PapyrusMultiDiagramEditor;
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
						editor = ApexStellaProjectMap.openEditor(diFile);
					}

					if ( editor != null && editor instanceof PapyrusMultiDiagramEditor ) {
						ServicesRegistry servicesRegistry = ((PapyrusMultiDiagramEditor)editor).getServicesRegistry();
						ApexStellaProjectMap.setUpModelServices(diFile, servicesRegistry);
						Viewer aViewer = event.getViewer();
						aViewer.refresh();
					}					
				}
				// diagram 일 경우 더블클릭 시 해당 다이어그램 활성화
			}
		}
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