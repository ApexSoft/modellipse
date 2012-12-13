package kr.co.apexsoft.modellipse.explorer.handler;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import kr.co.apexsoft.modellipse.explorer.core.ApexModellipseProjectMap;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
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
				                                        "This deletion will remove resources from your file system and can not be recovered.\nAre you sure you want to delete?");
		if ( isDeleteOk ) {
			// 모델 간 공유된 자원있을 경우 깨질 수 있으므로 Valiation 후 삭제 가능 여부 알려줌
			
			
			IWorkbenchPage activePage = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
			ISelection aSelection = activePage.getSelection();
			
			if ( aSelection instanceof ITreeSelection ) {
				ITreeSelection treeSelection = (ITreeSelection)aSelection;
				
				if ( !treeSelection.isEmpty() ) {
					Iterator it = treeSelection.iterator();
					
					while ( it.hasNext() ) {
						Object obj = it.next();						
						deleteResources(activePage, obj);
					}
				}
			}
		}
		return null;
	}
	
	/**
	 * 선택에 따라 삭제 처리
	 * 
	 * @param activePage
	 * @param obj
	 */
	private void deleteResources(IWorkbenchPage activePage, Object obj) {
		
		try {
			if ( obj instanceof IProject ) { // 프로젝트를 삭제하는 경우
				IProject project = (IProject)obj;
				deleteProject(activePage, project);
				
			} else if ( obj instanceof IFile ) { // Model을 삭제하는 경우
				IFile file = (IFile)obj;
				
				if(OneFileUtils.isDi(file)) {
					deleteDi(activePage, file);
				}
			}	
		} catch (CoreException e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * 해당 프로젝트 하위의 리소스 및 프로젝트래퍼, 물리적 파일 삭제
	 * 
	 * @param activePage
	 * @param project
	 * @throws CoreException
	 */
	private void deleteProject(IWorkbenchPage activePage, IProject project) throws CoreException {
		
		try {
			IResource[] resources = project.members();
			List<IFile> diFiles = new ArrayList<IFile>();
			
			for ( IResource resource : resources ) {
				
				if ( resource instanceof IFile ) {
					IFile file = (IFile)resource;
					
					if (OneFileUtils.isDi(file)) {
						diFiles.add(file);
					}
				}
			}
			
			for (IFile diFile : diFiles) {
				deleteDi(activePage, diFile);
			}

			String projectPath = project.getLocationURI().getPath();			
			ApexModellipseProjectMap.getProjectMap().remove(projectPath);
			
			project.delete(true, true, null);
			project = null;
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 해당 di 와 관련된 모델, 서비스, 물리적 파일 들 삭제 및 에디터 종료
	 * 
	 * @param activePage
	 * @param file
	 * @throws CoreException
	 */
	private void deleteDi(IWorkbenchPage activePage, IFile file) throws CoreException {

		ApexModellipseProjectMap.removeModelServices(file);
		
		// Editor close시 ServicesRegistry 등에 의한 해제됨(CoreMultiDiagramEditor.dispose())
		IEditorReference[] editorReferences = activePage.getEditorReferences();
		
		for ( IEditorReference editorReference : editorReferences ) {
			// 현재 선택된 editor 인지 확인 후 선택된 editor 닫기
			IEditorPart editorPart = editorReference.getEditor(false);
			String editorToolTip = editorPart.getTitleToolTip();
			String diTreePath = file.toString();
			String simpleFilePath = diTreePath.substring(diTreePath.indexOf('/')+1);
			
			if ( editorToolTip.equals(simpleFilePath) ) {
				deleteAssociatedFiles(file);
				file.delete(true, null);
				file = null;
				activePage.closeEditor(editorPart, false);						
			}
		}
	}

	/**
	 * 해당 di와 파일명이 같은 .notation, .uml 파일 삭제
	 * 
	 * @param file
	 * @throws CoreException
	 */
	private void deleteAssociatedFiles(IFile file) throws CoreException {
		
		IPapyrusFile papyrusFile = PapyrusModelHelper.getPapyrusModelFactory().createIPapyrusFile(file);
		IResource[] associatedFiles = papyrusFile.getAssociatedResources();
		
		for ( IResource res : associatedFiles ) {
			res.delete(true, null);
		}		
	}
	
}
