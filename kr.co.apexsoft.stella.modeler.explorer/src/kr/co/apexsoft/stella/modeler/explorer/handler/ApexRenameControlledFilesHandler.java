package kr.co.apexsoft.stella.modeler.explorer.handler;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import kr.co.apexsoft.stella.modeler.explorer.core.ApexStellaProjectMap;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.facet.infra.browser.uicore.internal.model.ModelElementItem;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.papyrus.infra.onefile.model.IPapyrusFile;
import org.eclipse.papyrus.infra.onefile.model.PapyrusModelHelper;
import org.eclipse.papyrus.infra.onefile.model.impl.PapyrusFile;
import org.eclipse.papyrus.infra.onefile.utils.OneFileUtils;
import org.eclipse.papyrus.infra.services.controlmode.util.ControlModeUtil;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;

@SuppressWarnings("restriction")
public class ApexRenameControlledFilesHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		
		ISelection selection = HandlerUtil.getCurrentSelection(event);
		
		
		IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindowChecked(event);
		InputDialog renameDialog = new InputDialog(window.getShell(), 
				                                   "Rename Controlled Files",
				                                   "New File Name", 
				                                   getOldFileName(selection), 
				                                   null);
		
		if ( renameDialog.open() == Window.OK ) {
			
			renameControlledFiles(selection, renameDialog.getValue());
//			MessageDialog.openConfirm(window.getShell(), "Done", "renamed");
		}
//		
//		IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindowChecked(event);
//		boolean isDeleteOk = MessageDialog.openQuestion(window.getShell(),
//				                                        "Dialog",
//				                                        "This deletion will remove resources from your file system and can not be recovered.\nAre you sure you want to delete?");
//		if ( isDeleteOk ) {
//			// 모델 간 공유된 자원있을 경우 깨질 수 있으므로 Valiation 후 삭제 가능 여부 알려줌
//			
//			
//			IWorkbenchPage activePage = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
//			ISelection aSelection = activePage.getSelection();
//			
//			if ( aSelection instanceof ITreeSelection ) {
//				ITreeSelection treeSelection = (ITreeSelection)aSelection;
//				Iterator it = treeSelection.iterator();
//
//				while ( it.hasNext() ) {
//					Object obj = it.next();						
//					deleteResources(activePage, obj);
//				}
//			}
//		}
		return null;
	}
	
	private String getOldFileName(ISelection selection) {
		String oldFileName = "";
		if ( selection instanceof TreeSelection ) {
			TreeSelection treeSelection = (TreeSelection)selection;
			Object obj = treeSelection.getFirstElement();			
			
			if ( obj instanceof ModelElementItem ) {
				EObject eObj = ((ModelElementItem)obj).getEObject();
				
				String platformRelativePath = eObj.eResource().getURI().toPlatformString(true);
				oldFileName = platformRelativePath.substring(platformRelativePath.lastIndexOf('/')+1, platformRelativePath.lastIndexOf('.'));
			}
		}
		return oldFileName;
	}
	
	private boolean isControlled(Object toTest) {
		boolean result = false;
		
		if ( toTest instanceof ModelElementItem ) {
			ModelElementItem aMitem = (ModelElementItem)toTest;
			EObject eObj = aMitem.getEObject();
			result = ControlModeUtil.canUncontrol(eObj);
		}
		
		return result;
	}
	
	private void renameControlledFiles(ISelection selection, String newName) {
		
		System.out.println("ApexRenameControlledFilesHandler.renameControlledFiles(), line : " + Thread.currentThread().getStackTrace()[1].getLineNumber());
		
		if ( selection instanceof TreeSelection ) {
			TreeSelection treeSelection = (TreeSelection)selection;
			Object obj = treeSelection.getFirstElement();
			
			
			if ( obj instanceof ModelElementItem ) {
				EObject eObj = ((ModelElementItem)obj).getEObject();
				
				String resourcePath = ResourcesPlugin.getWorkspace().getRoot().getLocation().toString();
				String platformRelativePath = eObj.eResource().getURI().toPlatformString(true);
				String umlFilePath = resourcePath.concat(platformRelativePath);
				System.out.println("  resourcePath : " + resourcePath);
				System.out.println("  platformRelativePath : " + platformRelativePath);
				System.out.println("  umlFilePath : " + umlFilePath);
				
				IFile controlledUMLFile = ResourcesPlugin.getWorkspace().getRoot().getFileForLocation(new Path(umlFilePath));
				
				IPapyrusFile papyrusFile = new PapyrusFile(controlledUMLFile);
				
				System.out.println("  papyrusFile : " + papyrusFile);
				
				for ( IResource res : papyrusFile.getAssociatedResources() ) {
					IPath oldPath = res.getFullPath();
					String ext = res.getFileExtension();
					
					System.out.println("  oldPath : " + oldPath);
					try {
						// 파일명 바꿔주고,
						IPath parentPath = res.getParent().getFullPath();
						IPath newPath = new Path(parentPath.toOSString()+"/"+newName+"."+ext);
						System.out.println("  newPath : " + newPath);
						res.move(newPath, false, new NullProgressMonitor());

						// 기존 파일명을 링크하고 있던 다른 모든 파일에서 해당 링크를 수정해줘야 함
					} catch (CoreException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}				
			}			
		}
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
			if (project.isAccessible()) {
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
			}
			String projectPath = project.getLocationURI().getPath();			
			ApexStellaProjectMap.getProjectMap().remove(projectPath);
			
			project.delete(true, true, null);
			project = null;
		} catch (CoreException e) {
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

		ApexStellaProjectMap.removeModelServices(file);
		
		// Editor close시 ServicesRegistry 등에 의한 해제됨(CoreMultiDiagramEditor.dispose())
		IEditorReference[] editorReferences = activePage.getEditorReferences();
		
		for ( IEditorReference editorReference : editorReferences ) {
			// 현재 선택된 editor 인지 확인 후 선택된 editor 닫기
			IEditorInput input = editorReference.getEditorInput();
			if (input instanceof IFileEditorInput) {
				IFile f = ((IFileEditorInput)input).getFile();
				if (file.equals(f)) {
					activePage.closeEditors(new IEditorReference[] {editorReference}, false);
				}
			}
		}
		
		deleteAssociatedFiles(file);
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
