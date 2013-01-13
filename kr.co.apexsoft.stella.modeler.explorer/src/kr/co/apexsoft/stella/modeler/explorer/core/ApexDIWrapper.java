package kr.co.apexsoft.stella.modeler.explorer.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.facet.infra.browser.uicore.internal.AppearanceConfiguration;
import org.eclipse.emf.facet.infra.browser.uicore.internal.model.ITreeElement;
import org.eclipse.emf.facet.infra.browser.uicore.internal.model.ItemsFactory;
import org.eclipse.emf.facet.infra.browser.uicore.internal.model.ModelElementItem;
import org.eclipse.papyrus.editor.PapyrusMultiDiagramEditor;
import org.eclipse.papyrus.infra.core.resource.uml.UmlModel;
import org.eclipse.papyrus.infra.core.services.ServicesRegistry;
import org.eclipse.papyrus.infra.onefile.utils.OneFileUtils;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IEditorPart;
import org.eclipse.uml2.uml.internal.impl.ModelImpl;
import org.eclipse.uml2.uml.internal.impl.PackageImpl;

public class ApexDIWrapper implements ITreeElement {
	
	private IFile _file;
	private ApexProjectWrapper aProjectWrapper;
	private ItemsFactory itemsFactory;
	private final AppearanceConfiguration appearanceConfiguration;
	
	public ApexDIWrapper(IFile file, ItemsFactory itemsFactory, AppearanceConfiguration appearanceConfigration) {
		this._file = file;
		
		IContainer container = _file.getParent();
		String projectPath = _file.getParent().getLocationURI().getPath();
		
		if ( container instanceof IProject ) {

			Map<String, ApexProjectWrapper> projectMap = ApexStellaProjectMap.getProjectMap();
			aProjectWrapper = projectMap.get(projectPath);
		}
		
		this.itemsFactory = itemsFactory;
		this.appearanceConfiguration = appearanceConfigration;
	}
	
	public IFile getFile() {
		return _file;
	}

	@Override
	public String getText() {
		return _file.getName();
	}

	@Override
	public Image getImage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ITreeElement getTreeParent() {
		
		return aProjectWrapper;
	}

	@Override
	public boolean hasChildren() {
		return aProjectWrapper.getChildren().size() > 0;
	}

	@Override
	public List<Object> getChildren() {
		List<Object> result = new ArrayList<Object>();
		
		if(OneFileUtils.isDi(_file)) {
			String diFilePath = _file.getLocationURI().getPath();

			// 프로젝트 내 di를 최초로 펼친 경우 - (1)
			// 프로젝트 래퍼가 없고,
			// diPath에 대한 umlModel 도 없다.
			// ->에디터 열고 ServiceReg를 가져와 세팅(최초 뷰 구성 시)

			// 프로젝트 내 di를 한 번 펼친 후 다른 di를 펼친 경우 - (2)
			// 프로젝트 래퍼는 있고,
			// diPath에 대한 umlModel은 없다
			// ->에디터 열고 ServiceReg를 가져와 세팅(최초 뷰 구성 시)

			// 더블클릭에 의한 refresh() 시 - (3)
			// 더블클릭 리스너가 직접 Editor를 열고 ServiceReg를 세팅
			// 프로젝트 래퍼가 있고,
			// diPath에 대한 umlModel 도 있다.
			// 여기선 가져와서 트리 만들어주기만 하믄 됨		
			// 끌때도 여기를 타서 계속 modelElementItem 생성하는 오류 있음

			// 에디터 닫아서 ApexMEView에서 refresh() 호출된 경우 - (4)
			// 프로젝트 래퍼는 있고
			// diPath에 대한 umlModel은 없다 (2)와 겹침
			// 에디터가 닫은 모델은 IsDisposed 가 true 인 것으로 구분
			// 그냥 지나친다

			if ( aProjectWrapper == null ) { // (1)
				createEditorAndSetUpTree(_file, result);
			} else if (aProjectWrapper.getIsDisposed(diFilePath)) { // (4)

			} else if ( aProjectWrapper.getUmlModel(diFilePath) == null ) { // (2)
				createEditorAndSetUpTree(_file, result);						
			} else { // (3)
				UmlModel umlModel = aProjectWrapper.getUmlModel(diFilePath);

				if ( umlModel != null ) {
					makeModelElementItemList(this, umlModel, result);
				}
			}
		}	
		
		return result;
	}

	@Override
	public Color getForeground() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Color getBackground() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Font getFont() {
		// TODO Auto-generated method stub
		return null;
	}
	
	private void createEditorAndSetUpTree(IFile diFile, List<Object> result) {
		ApexProjectWrapper aProjectWrapper = null;
		String diFilePath = diFile.getLocationURI().getPath();
		IEditorPart editor = ApexStellaProjectMap.openEditor(diFile);

		if ( editor != null && editor instanceof PapyrusMultiDiagramEditor ) {
			ServicesRegistry servicesRegistry = ((PapyrusMultiDiagramEditor)editor).getServicesRegistry();
			
			// 아래에서 적합한 ProjectWrapper를 받아오므로 파라미터로 aProjectWrapper를 받아올 필요는 없어 보임)
			aProjectWrapper = ApexStellaProjectMap.setUpModelServices(diFile, servicesRegistry);

			UmlModel umlModel = aProjectWrapper.getUmlModel(diFilePath);
			makeModelElementItemList(this, umlModel, result);			
			
			// refresh() 하지 않으면 MultiDiagramEditor보다 ExplorerView가 먼저 실행된 경우
			// Browser Customization이 작동하지 않음
			//viewer.refresh();
		}	
	}
	
	private void makeModelElementItemList(ApexDIWrapper diWrapper, UmlModel umlModel, List<Object> result) {
		EList<EObject> contents = umlModel.getResource().getContents();
		
		for ( EObject eObj : contents ) {

			if ( eObj instanceof ModelImpl ) {
//				ModelElementItem modelItem = new ModelElementItem(eObj, null, this.appearanceConfiguration); 
//				result.add(modelItem);	
				ModelElementItem modelItem = itemsFactory.createModelElementItem(eObj, null, appearanceConfiguration);
				modelItem.setTreeParent(diWrapper);
//				System.out
//						.println("ApexDIWrapper.makeModelElementItemList(), line "
//								+ Thread.currentThread().getStackTrace()[1]
//										.getLineNumber());
//				System.out.println("added modelItem : " + modelItem);
//				System.out.println("added modelItem.getText() : " + modelItem.getText());
//				System.out.println("modelItem.getTreeParent() : " + modelItem.getTreeParent());				
				
				result.add((ITreeElement)modelItem);
			} else if ( eObj instanceof PackageImpl ) { // Control를 통해 분리된 Package의 경우 모델처럼 Tree생성해줘야 함
				ModelElementItem modelItem = itemsFactory.createModelElementItem(eObj, null, appearanceConfiguration);
				modelItem.setTreeParent(diWrapper);				
				result.add((ITreeElement)modelItem);
			}
		}
	}
}
