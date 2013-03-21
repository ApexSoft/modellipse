package kr.co.apexsoft.stella.modeler.explorer.actionprovider;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.facet.infra.browser.uicore.internal.model.ModelElementItem;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.papyrus.infra.onefile.model.IPapyrusFile;
import org.eclipse.papyrus.infra.onefile.model.impl.PapyrusFile;
import org.eclipse.papyrus.infra.services.controlmode.util.ControlModeUtil;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionContext;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.actions.RenameResourceAction;
import org.eclipse.ui.navigator.CommonActionProvider;
import org.eclipse.ui.navigator.ICommonActionExtensionSite;
import org.eclipse.ui.navigator.ICommonMenuConstants;
import org.eclipse.ui.navigator.ICommonViewerWorkbenchSite;

public class RenameControlledFileActionProvider extends CommonActionProvider {

	private boolean fInViewPart = false;

	private ICommonViewerWorkbenchSite workbenchSite;

	private Action renameAction;	

	public RenameControlledFileActionProvider() {
		
	}

	@Override
	public void fillActionBars(IActionBars actionBars) {
		if(fInViewPart) {
			if (renameAction.isEnabled()) {
				actionBars.setGlobalActionHandler(ActionFactory.RENAME.getId(), renameAction);
			}
		}
		super.fillActionBars(actionBars);
	}

	@Override
	public void fillContextMenu(IMenuManager menu) {
		super.fillContextMenu(menu);
		appendToGroup(menu, renameAction, ICommonMenuConstants.GROUP_ADDITIONS);
	}

	private void appendToGroup(IMenuManager menu, IAction action, String id) {
		if(action != null && action.isEnabled()) {
			menu.appendToGroup(id, action);
		}
	}

	@Override
	public void init(ICommonActionExtensionSite site) {
		if(site.getViewSite() instanceof ICommonViewerWorkbenchSite) {
			workbenchSite = (ICommonViewerWorkbenchSite)site.getViewSite();
		}
		if(workbenchSite != null) {
			if(workbenchSite.getPart() != null && workbenchSite.getPart() instanceof IViewPart) {
				fInViewPart = true;
			}
			makeActions();
		}
	}

	private void makeActions() {
		final IWorkbenchPartSite provider = workbenchSite.getSite();
		final ActionHelper helper = new ActionHelper();

		renameAction = new RenameResourceAction(provider) {

			@Override
			public IStructuredSelection getStructuredSelection() {
				return new StructuredSelection(helper.getSelectedElements(getContext()));
			}

			@Override
			protected List getSelectedResources() {
				// helper 에 해당 controlled 패키지에서 di 파일셋을 가져오도록 구현
				return helper.getRelatedResources(getContext());
			}

			@Override
			public boolean isEnabled() {
				boolean enabled = false;
				List<Object> element = helper.getSelectedElements(getContext());
				if ( super.isEnabled()
					 && element.size() == 1 ) {
					Object obj = element.get(0);
					
					if ( isControlled(obj) ) {
						enabled = true;						
					}
				}
				
				return enabled;
			}
		};

	}
	
	public boolean isControlled(Object toTest) {
		boolean result = false;
		
		if ( toTest instanceof ModelElementItem ) {
			ModelElementItem aMitem = (ModelElementItem)toTest;
			EObject eObj = aMitem.getEObject();
			result = ControlModeUtil.canUncontrol(eObj);	
		}
		
		return result;
	}

	protected void makeAction(Action action, String id, String imgTool, String imgToolDisabled) {
		if(action != null) {
			ISharedImages images = PlatformUI.getWorkbench().getSharedImages();
			if(id != null) {
				action.setId(id);
				action.setActionDefinitionId(id);
			}
			if(imgTool != null) {
				action.setImageDescriptor(images.getImageDescriptor(imgTool));
			}
			if(imgToolDisabled != null) {
				action.setDisabledImageDescriptor(images.getImageDescriptor(imgToolDisabled));
			}
		}
	}

	public static class ActionHelper {

//		public IStructuredSelection getStructuredSelection(ActionContext context) {
//			return new StructuredSelection(getSelectedResources(context));
//		}
		
		/**
		 * 선택된 패키지가 Control되어 생성된 3가지 papyrus file(di, uml, notation) list 반환 
		 * 
		 * @param context
		 * @return
		 */
		public List getRelatedResources(ActionContext context) {
			
			List result = new ArrayList();
			List<Object> elements = getSelectedElements(context);
			
			if ( elements.size() == 1 ) {
				Object obj = elements.get(0);
				
				EObject eObj = ((ModelElementItem)obj).getEObject();
				
				String resourcePath = ResourcesPlugin.getWorkspace().getRoot().getLocation().toString();
				String platformRelativePath = eObj.eResource().getURI().toPlatformString(true);
				String umlFilePath = resourcePath.concat(platformRelativePath);
				
				IFile controlledUMLFile = ResourcesPlugin.getWorkspace().getRoot().getFileForLocation(new Path(umlFilePath));
				
				IPapyrusFile papyrusFile = new PapyrusFile(controlledUMLFile);
				
				for ( IResource res : papyrusFile.getAssociatedResources() ) {
					result.add(res);
				}
			}
			
			return result;
		}
//
//		protected List getSelectedResources(ActionContext context) {
//			ISelection selec = context.getSelection();
//			List<IResource> resources = new ArrayList<IResource>();
//			if(selec instanceof IStructuredSelection) {
//				IStructuredSelection struc = (IStructuredSelection)selec;
//				for(Iterator<Object> i = struc.iterator(); i.hasNext();) {
//					Object o = i.next();
//					if(o instanceof IWorkspaceRoot == false && o instanceof IResource == true) {
//						IResource res = (IResource)o;
//						resources.add(res);
//					}
//				}
//			}
//			return resources;
//		}		
		
		public List<Object> getSelectedElements(ActionContext context) {
			ISelection selec = context.getSelection();
			List<Object> elements = new ArrayList<Object>();
			if(selec instanceof IStructuredSelection) {
				IStructuredSelection struc = (IStructuredSelection)selec;
				for(Iterator<Object> i = struc.iterator(); i.hasNext();) {
					Object o = i.next();
					elements.add(o);
				}
			}
			return elements;
		}		
		
	}

}
