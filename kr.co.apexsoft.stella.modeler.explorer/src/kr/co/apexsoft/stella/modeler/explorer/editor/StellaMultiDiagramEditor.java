package kr.co.apexsoft.stella.modeler.explorer.editor;

import java.util.Map;

import kr.co.apexsoft.stella.modeler.explorer.core.ApexStellaProjectMap;
import kr.co.apexsoft.stella.modeler.explorer.core.ApexProjectWrapper;

import org.eclipse.core.resources.IFile;
import org.eclipse.papyrus.editor.PapyrusMultiDiagramEditor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.PartInitException;

public class StellaMultiDiagramEditor extends PapyrusMultiDiagramEditor {

	IFile diFile;

	@Override
	public void init(IEditorSite site, IEditorInput input) throws PartInitException {
		super.init(site, input);
		diFile = ((IFileEditorInput)input).getFile();
	}

	@Override
	public void dispose() {
		ApexStellaProjectMap.clearModelServices(diFile);
		super.dispose();	
	}		
	
	/**
	 * Overrides getContributorId.
	 * 
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.ui.views.properties.tabbed.ITabbedPropertySheetPageContributor#getContributorId()
	 */
	@Override
	public String getContributorId() {
		// return Activator.PLUGIN_ID;
		// papyrus 내용 재사용. xml에서 TreeOutlinePage 검색으로 분석 가능
		// 이것이 없으면 Property 창에 해당 정보가 표시되지 않음
		// 기능적으로 override 하지 않아도 부모의 getContributorId()를 통해 "TreeOutlinePage"를 return 하나
		// 내용 파악 및 추후 별도 개발을 위해 override함
		return "TreeOutlinePage"; //$NON-NLS-1$

	}
}