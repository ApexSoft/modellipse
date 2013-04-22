package kr.co.apexsoft.stella.modeler.explorer.actionprovider;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.papyrus.infra.onefile.utils.OneFileUtils;
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

public class ResourceActionProvider extends CommonActionProvider {

	private boolean fInViewPart = false;

	private ICommonViewerWorkbenchSite workbenchSite;

	private Action renameAction;

	public ResourceActionProvider() {
		
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
//		appendToGroup(menu, renameAction, ICommonMenuConstants.GROUP_EDIT);
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
				IStructuredSelection selec = helper.getOneStructuredSelection(getContext());
				return selec != null ? selec : super.getStructuredSelection();
			}

			@Override
			protected List getSelectedResources() {
				return helper.getOneSelectedResources(getContext());
			}

			@Override
			public boolean isEnabled() {
				return super.isEnabled() && getSelectedResources().size() > 0;
			}
		};

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

		public IStructuredSelection getStructuredSelection(ActionContext context) {
			return new StructuredSelection(getSelectedResources(context));
		}

		public List getOneSelectedResources(ActionContext context) {
			List selectedResources = getSelectedResources(context);
			if(selectedResources.size() > 0) {
				for(Iterator<?> i = selectedResources.iterator(); i.hasNext();) {
					Object o = i.next();
					if(o instanceof IFile) {
						IFile file = (IFile)o;
						if(!OneFileUtils.isDi(file)) {
							i.remove();
						}
					}
				}
				return selectedResources;
			}
			return Collections.EMPTY_LIST;
		}

		public IStructuredSelection getOneStructuredSelection(ActionContext context) {
			List selectedResources = getOneSelectedResources(context);
			if(selectedResources.size() > 0) {
				return new StructuredSelection(selectedResources);
			}
			return null;
		}

		protected List getSelectedResources(ActionContext context) {
			ISelection selec = context.getSelection();
			List<IResource> resources = new ArrayList<IResource>();
			if(selec instanceof IStructuredSelection) {
				IStructuredSelection struc = (IStructuredSelection)selec;
				for(Iterator<Object> i = struc.iterator(); i.hasNext();) {
					Object o = i.next();
					if(o instanceof IWorkspaceRoot == false && o instanceof IResource == true) {
						IResource res = (IResource)o;
						resources.add(res);
					}
				}
			}
			return resources;
		}

		public IFile getIFile(ActionContext context) {
			ISelection selec = context.getSelection();
			if(selec instanceof IStructuredSelection) {
				IStructuredSelection struc = (IStructuredSelection)selec;
				Object firstElement = struc.getFirstElement();
				if(firstElement instanceof IFile) {
					IFile file = (IFile)firstElement;
					return file;
				}
			}
			return null;
		}
	}
}
