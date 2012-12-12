package kr.co.apexsoft.modellipse.explorer.handler;

import java.util.Iterator;

import kr.co.apexsoft.modellipse.explorer.core.ApexModellipseProjectMap;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.papyrus.infra.onefile.model.IPapyrusFile;
import org.eclipse.papyrus.infra.onefile.model.PapyrusModelHelper;
import org.eclipse.papyrus.infra.onefile.utils.OneFileUtils;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;

public class ApexResourceDeleteHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindowChecked(event);
		boolean isDeleteOk = MessageDialog.openQuestion(window.getShell(),
				                                        "Dialog",
				                                        "This deletion can not be recovered. Are you sure you want to delete?");
		if ( isDeleteOk ) {
			// 모델 간 공유된 자원있을 경우 깨질 수 있으므로 Valiation 후 삭제 가능 여부 알려줌
			
			// 메모리(ProjectWrapper or Model) 클리어 - 완료			
			// 자원 해제 - 완료
			// Editor 닫고 - 완료
			// 파일 삭제 - 완료
			IWorkbenchPage activePage = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
			ISelection aSelection = activePage.getSelection();
			
			if ( aSelection instanceof ITreeSelection ) {
				ITreeSelection treeSelection = (ITreeSelection)aSelection;
				
				if ( !treeSelection.isEmpty() ) {
					Iterator it = treeSelection.iterator();
					
					while ( it.hasNext() ) {
						Object obj = it.next();
						
						if ( obj instanceof IFile ) {
							IFile file = (IFile)obj;
							
							if(OneFileUtils.isDi((IFile)obj)) {
								// 서비스 및 메모리 클리어
								String diPath = file.getLocationURI().getPath();
								ApexModellipseProjectMap.removeModelServices(file);
//								ApexModellipseProjectMap.getProjectMap().remove(diPath);
								
								// Editor close시 자원 해제됨
								IEditorReference[] editorReferences = activePage.getEditorReferences();
								
								for ( IEditorReference editorReference : editorReferences ) {
									// 현재 선택된 editor 인지 확인 후 선택된 editor 닫기
									IEditorPart editorPart = editorReference.getEditor(false);
									String editorToolTip = editorPart.getTitleToolTip();
									String diTreePath = obj.toString();
									String simpleFilePath = diTreePath.substring(diTreePath.indexOf('/')+1);
									
									if ( editorToolTip.equals(simpleFilePath) ) {
										try {
											deleteAssociatedFiles(file);
											file.delete(true, null);											
										} catch (CoreException e) {
											e.printStackTrace();
										}
										activePage.closeEditor(editorPart, false);
										
									}
								}
							}
						}
					}
				}
			}
		}
		return null;
	}

	private void deleteAssociatedFiles(IFile file) throws CoreException {
		
		IPapyrusFile papyrusFile = PapyrusModelHelper.getPapyrusModelFactory().createIPapyrusFile(file);
		IResource[] associatedFiles = papyrusFile.getAssociatedResources();
		
		for ( IResource res : associatedFiles ) {
			res.delete(true, null);
		}		
	}
}
