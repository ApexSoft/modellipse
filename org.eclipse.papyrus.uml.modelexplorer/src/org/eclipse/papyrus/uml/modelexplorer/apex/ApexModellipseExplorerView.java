/*****************************************************************************
= * Copyright (c) 2010 CEA LIST.
 *
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Patrick Tessier (CEA LIST) Patrick.tessier@cea.fr - Initial API and implementation
 *
 *****************************************************************************/
package org.eclipse.papyrus.uml.modelexplorer.apex;

import static org.eclipse.papyrus.infra.core.Activator.log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

import org.eclipse.core.commands.operations.IUndoContext;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.emf.transaction.ResourceSetChangeEvent;
import org.eclipse.emf.transaction.ResourceSetListener;
import org.eclipse.emf.transaction.ResourceSetListenerImpl;
import org.eclipse.emf.transaction.Transaction;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ColumnViewerToolTipSupport;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.window.ToolTip;
import org.eclipse.papyrus.editor.PapyrusMultiDiagramEditor;
import org.eclipse.papyrus.infra.core.apex.ApexModellipseExplorerRoot;
import org.eclipse.papyrus.infra.core.apex.ApexProjectWrapper;
import org.eclipse.papyrus.infra.core.editor.IMultiDiagramEditor;
import org.eclipse.papyrus.infra.core.lifecycleevents.IEditorInputChangedListener;
import org.eclipse.papyrus.infra.core.lifecycleevents.ISaveAndDirtyService;
import org.eclipse.papyrus.infra.core.multidiagram.actionbarcontributor.ActionBarContributorRegistry;
import org.eclipse.papyrus.infra.core.resource.ModelMultiException;
import org.eclipse.papyrus.infra.core.resource.ModelSet;
import org.eclipse.papyrus.infra.core.resource.additional.AdditionalResourcesModel;
import org.eclipse.papyrus.infra.core.resource.uml.UmlModel;
import org.eclipse.papyrus.infra.core.resource.uml.UmlUtils;
import org.eclipse.papyrus.infra.core.sasheditor.contentprovider.IPageMngr;
import org.eclipse.papyrus.infra.core.sasheditor.contentprovider.ISashWindowsContentProvider;
import org.eclipse.papyrus.infra.core.sasheditor.di.contentprovider.DiSashModelMngr;
import org.eclipse.papyrus.infra.core.services.ExtensionServicesRegistry;
import org.eclipse.papyrus.infra.core.services.ServiceException;
import org.eclipse.papyrus.infra.core.services.ServicesRegistry;
import org.eclipse.papyrus.infra.core.ui.IRevealSemanticElement;
import org.eclipse.papyrus.infra.core.utils.EditorUtils;
import org.eclipse.papyrus.infra.core.utils.ServiceUtils;
import org.eclipse.papyrus.infra.emf.providers.SemanticFromModelExplorer;
import org.eclipse.papyrus.infra.onefile.model.IPapyrusFile;
import org.eclipse.papyrus.infra.onefile.model.PapyrusModelHelper;
import org.eclipse.papyrus.infra.onefile.utils.OneFileUtils;
import org.eclipse.papyrus.views.modelexplorer.Activator;
import org.eclipse.papyrus.views.modelexplorer.CustomCommonViewer;
import org.eclipse.papyrus.views.modelexplorer.DecoratingLabelProviderWTooltips;
import org.eclipse.papyrus.views.modelexplorer.ModelExplorerPageBookView;
import org.eclipse.papyrus.views.modelexplorer.listener.DoubleClickListener;
import org.eclipse.papyrus.views.modelexplorer.matching.IMatchingItem;
import org.eclipse.papyrus.views.modelexplorer.matching.LinkItemMatchingItem;
import org.eclipse.papyrus.views.modelexplorer.matching.ModelElementItemMatchingItem;
import org.eclipse.papyrus.views.modelexplorer.matching.ReferencableMatchingItem;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.ISaveablePart;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.WorkbenchException;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.internal.navigator.NavigatorContentService;
import org.eclipse.ui.internal.navigator.extensions.NavigatorContentDescriptor;
import org.eclipse.ui.navigator.CommonNavigator;
import org.eclipse.ui.navigator.CommonViewer;
import org.eclipse.ui.operations.RedoActionHandler;
import org.eclipse.ui.operations.UndoActionHandler;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.views.properties.IPropertySheetPage;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertySheetPageContributor;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

import com.google.common.base.Function;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

/**
 * Papyrus Model Explorer associated to one {@link IMultiDiagramEditor}.
 * This ModelExplorer is linked to one single {@link IMultiDiagramEditor}. It doesn't change its
 * source when the current Editor change. To allow to explore different Model, use a {@link ModelExplorerPageBookView}.
 * 
 */
public class ApexModellipseExplorerView extends CommonNavigator implements IRevealSemanticElement, IEditingDomainProvider {

	/** ID of the view, as given in the plugin.xml file */
	public static final String VIEW_ID = "org.eclipse.papyrus.uml.modelexplorer.modellipseexplorer"; //$NON-NLS-1$
	/**
	 * The associated EditorPart
	 * The View is associated to the ServicesRegistry rather than to an editor.
	 * */
	//	private IMultiDiagramEditor editorPart;

	/**
	 * The {@link ServicesRegistry} associated to the Editor. This view is associated to the
	 * ServicesRegistry rather than to the EditorPart.
	 */	
	private ServicesRegistry serviceRegistry;
	

	/** The save aservice associated to the editor. */
	private ISaveAndDirtyService saveAndDirtyService;

	/** {@link IUndoContext} used to tag command in the commandStack. */
	private IUndoContext undoContext;

	/** editing domain used to read/write the model */
	private TransactionalEditingDomain editingDomain;

	/** Flag to avoid reentrant call to refresh. */
	private AtomicBoolean isRefreshing = new AtomicBoolean(false);	

	/** Undo action handler */
	UndoActionHandler undoHandler;

	/** Redo action handler */
	RedoActionHandler redoHandler;

	/** The {@link IPropertySheetPage} this model explorer will use. */
	private IPropertySheetPage propertySheetPage = null;

	/**
	 * A listener on page (all editors) selection change. This listener is set
	 * in {@link ApexModellipseExplorerView#init(IViewSite)}. It should be dispose to remove
	 * hook to the Eclipse page.
	 */
//	private ISelectionListener pageSelectionListener = new ISelectionListener() {
//
//		public void selectionChanged(IWorkbenchPart part, ISelection selection) {
//			handleSelectionChangedFromDiagramEditor(part, selection);
//		}
//	};

	/**
	 * Listener on {@link ISaveAndDirtyService#addInputChangedListener(IEditorInputChangedListener)}
	 */
	protected IEditorInputChangedListener editorInputChangedListener = new IEditorInputChangedListener() {

		/**
		 * This method is called when the editor input is changed from the ISaveAndDirtyService.
		 * 
		 * @see org.eclipse.papyrus.infra.core.lifecycleevents.IEditorInputChangedListener#editorInputChanged(org.eclipse.ui.part.FileEditorInput)
		 * 
		 * @param fileEditorInput
		 */
		public void editorInputChanged(FileEditorInput fileEditorInput) {
			// Change the editor input.
			setPartName(fileEditorInput.getName());
		}

		/**
		 * The isDirty flag has changed, reflect its new value
		 * 
		 * @see org.eclipse.papyrus.infra.core.lifecycleevents.IEditorInputChangedListener#isDirtyChanged()
		 * 
		 */
		public void isDirtyChanged() {
			firePropertyChange(IEditorPart.PROP_DIRTY);
		}
	};



	public ApexModellipseExplorerView() {
		super();
		//serviceRegistry = ApexModellipseExplorerRoot.getServicesRegistryList().get(0);
//		PapyrusMultiDiagramEditor activeEditor = (PapyrusMultiDiagramEditor)PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
//		serviceRegistry = activeEditor.getServicesRegistry();
//		serviceRegistry = (ServicesRegistry)ApexModellipseExplorerRoot.getProjectMap()
//				              .get(ResourcesPlugin.getWorkspace().getRoot().getProject().getName()).getChildren().get(0);
//		Object o = super.getInitialInput();
//		setUpServicesRegistries(o);
//		if(serviceRegistry == null) {
//			throw new IllegalArgumentException("The part should have a ServiceRegistry.");
//		}
//		
//		/* apex added start */
//		addToGlobalRoot(serviceRegistry);
//		/* apex added end */
//		
//		setLinkingEnabled(true);
//
//		// Get required services from ServicesRegistry
//		try {
//			saveAndDirtyService = serviceRegistry.getService(ISaveAndDirtyService.class);
//			undoContext = serviceRegistry.getService(IUndoContext.class);
//		} catch (ServiceException e) {
//			e.printStackTrace();
//		}		  
	}
	
	/**
	 * 
	 * Constructor.
	 * 
	 * @param part
	 *        The part associated to this ModelExplorer
	 */
//	public ApexModellipseExplorerView(IMultiDiagramEditor part) {
//
//		if(part == null) {
//			throw new IllegalArgumentException("A part should be provided.");
//		}
//
//		// Try to get the ServicesRegistry
//		serviceRegistry = part.getServicesRegistry();
//		if(serviceRegistry == null) {
//			throw new IllegalArgumentException("The part should have a ServiceRegistry.");
//		}
//
//		setLinkingEnabled(true);
//
//		// Get required services from ServicesRegistry
//		try {
//			saveAndDirtyService = serviceRegistry.getService(ISaveAndDirtyService.class);
//			undoContext = serviceRegistry.getService(IUndoContext.class);
//		} catch (ServiceException e) {
//			e.printStackTrace();
//		}
//	}
	
	/**
	 * apex added
	 * 
	 * Constructor.
	 * 
	 * @param iWorkspaceRoot
	 *        The part associated to this ModelExplorer
	 */
//	public ApexModellipseExplorerView(IWorkspaceRoot iWorkspaceRoot) {
//		
//		if ( _servicesRegistryMap == null ) {
//			_servicesRegistryMap = new HashMap<String, ServicesRegistry>();
//		}
//
//		if(iWorkspaceRoot == null) {
//			throw new IllegalArgumentException("A workspaceRoot should be provided.");
//		}
//
//		_projects = ResourcesPlugin.getWorkspace().getRoot().getProjects();
//		for (IProject project : _projects) {
//			System.out
//					.println("ApexModellipseExplorerView.ApexModellipseExplorerView, line : "
//							+ Thread.currentThread().getStackTrace()[1]
//									.getLineNumber());
//			System.out.println("project.getParent() : " + project.getParent());
//			try {
//				System.out.println("project.members() : " + project.members());
//			} catch (CoreException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			// project에서 ServicesRegistry를 구하는 방법을 통해
////			_servicesRegistryMap.put(project.getName(), project.getServicesRegistry());
//		}
//		
//		// Try to get the ServicesRegistry
////		serviceRegistry = iWorkspaceRoot.getServicesRegistry();
////		if(serviceRegistry == null) {
////			throw new IllegalArgumentException("The part should have a ServiceRegistry.");
////		}
//
//		setLinkingEnabled(true);
//
//		// Get required services from ServicesRegistry
//		try {
//			saveAndDirtyService = serviceRegistry.getService(ISaveAndDirtyService.class);
//			undoContext = serviceRegistry.getService(IUndoContext.class);
//		} catch (ServiceException e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Handle a selection change in the editor.
	 * 
	 * @param part
	 * @param selection
	 */
	private void handleSelectionChangedFromDiagramEditor(IWorkbenchPart part, ISelection selection) {
		// Handle selection from diagram editor
		if(isLinkingEnabled()) {
			if(part instanceof IEditorPart) {
				if(selection instanceof IStructuredSelection) {
					Iterator<?> selectionIterator = ((IStructuredSelection)selection).iterator();
					ArrayList<Object> semanticElementList = new ArrayList<Object>();
					while(selectionIterator.hasNext()) {
						Object currentSelection = selectionIterator.next();
						if(currentSelection instanceof IAdaptable) {
							Object semanticElement = ((IAdaptable)currentSelection).getAdapter(EObject.class);
							if(semanticElement != null) {
								semanticElementList.add(semanticElement);
							}
						}

					}
					revealSemanticElement(semanticElementList);

				}

			}
		}
	}

	/**
	 * look for the path the list of element (comes from the content provider) to go the eObject
	 * 
	 * @param eobject
	 *        that we look for.
	 * @param objects
	 *        a list of elements where eobject can be wrapped.
	 * @return the list of modelElementItem ( from the root to the element that wrap the eobject)
	 */
	protected List<Object> searchPath(EObject eobject, List<Object> objects) {
		SemanticFromModelExplorer semanticGetter = new SemanticFromModelExplorer();
		List<Object> path = new ArrayList<Object>();
		ITreeContentProvider contentProvider = (ITreeContentProvider)getCommonViewer().getContentProvider();
		//		IPageMngr iPageMngr = EditorUtils.getIPageMngr();
		IPageMngr iPageMngr;
		try {
			iPageMngr = ServiceUtils.getInstance().getIPageMngr(serviceRegistry);
		} catch (ServiceException e) {
			// This shouldn't happen.
			return Collections.emptyList();
		}
		Object[] result = iPageMngr.allPages().toArray();
		List<Object> editors = Arrays.asList(result);


		for(Object o : objects) {
			// Search matches in this level
			//			if(!(o instanceof Diagram) && o instanceof IAdaptable) {
			if(!editors.contains(o) && o instanceof IAdaptable) {
				if(eobject.equals(((IAdaptable)o).getAdapter(EObject.class))) {
					path.add(o);
					return path;
				}
			}

			// Find childs only for feature container
			for(int i = 0; i < contentProvider.getChildren(o).length; i++) {
				Object treeItem = contentProvider.getChildren(o)[i];

				List<Object> tmppath = new ArrayList<Object>();
				Object element = semanticGetter.getSemanticElement(treeItem);
				if(element != null) {
					if(element instanceof EReference) {
						if(((EReference)element).isContainment() && (!((EReference)element).isDerived())) {
							List<Object> childs = new ArrayList<Object>();
							childs.add(treeItem);
							tmppath = searchPath(eobject, childs);
						}
					}

					else {
						if(element instanceof EObject) {
							List<Object> childs = new ArrayList<Object>();
							childs.add(treeItem);
							tmppath = searchPath(eobject, childs);
						}
					}
				}

				// if tmppath contains the wrapped eobject we have find the good path
				if(tmppath.size() > 0) {
					if(tmppath.get(tmppath.size() - 1) instanceof IAdaptable) {
						if(eobject.equals(((IAdaptable)(tmppath.get(tmppath.size() - 1))).getAdapter(EObject.class))) {
							path.add(o);
							path.addAll(tmppath);
							return path;
						}
					}
				}
			}
		}

		return new ArrayList<Object>();
	}


	/**
	 * {@inheritDoc}
	 */
	// FIXME Use of internal class (NavigatorContentService) - in the hope that the bug gets fixed soon.
	@Override
	protected CommonViewer createCommonViewerObject(Composite aParent) {
		CommonViewer viewer = new CustomCommonViewer(getViewSite().getId(), aParent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
		// enable tool-tips
		// workaround for bug 311827: the Common Viewer always uses NavigatorDecoratingLabelProvider
		// as a wrapper for the LabelProvider provided by the application. The NavigatorDecoratingLabelProvider
		// does not delegate tooltip related functions but defines them as empty.
		NavigatorContentService contentService = new NavigatorContentService(getViewSite().getId());
		@SuppressWarnings("unchecked")
		// get label provider from content service (which in turn evaluates extension points in
		// function of the input)
		Set<Object> descriptors = contentService.findDescriptorsByTriggerPoint(getInitialInput(), false);
		for(Object descriptor : descriptors) {
			if(descriptor instanceof NavigatorContentDescriptor) {
				try {
					ILabelProvider labelProvider = ((NavigatorContentDescriptor)descriptor).createLabelProvider();
					viewer.setLabelProvider(new DecoratingLabelProviderWTooltips(labelProvider)); // add for decorator and tooltip support
				} catch (CoreException e) {
					Activator.log.error(e);
				}
				break;
			}
		}
		ColumnViewerToolTipSupport.enableFor(viewer, ToolTip.NO_RECREATE);

		return viewer;
	}

	@Override
	public void createPartControl(Composite aParent) {
		super.createPartControl(aParent);
		getCommonViewer().setSorter(null);
		((CustomCommonViewer)getCommonViewer()).getDropAdapter().setFeedbackEnabled(true);
		getCommonViewer().addDoubleClickListener(new ApexDoubleClickListener());
		Tree tree = getCommonViewer().getTree();
		Activator.getDefault().getCustomizationManager().installCustomPainter(tree);
	}

	/**
	 * Return the control used to render this View
	 * 
	 * @see org.eclipse.papyrus.views.modelexplorer.core.ui.pagebookview.IPageBookNestableViewPart#getControl()
	 * 
	 * @return the main control of the navigator viewer
	 */
	public Control getControl() {
		return getCommonViewer().getControl();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void init(IViewSite site, IMemento aMemento) throws PartInitException {
		super.init(site, aMemento);

		// Hook undo/redo action
		//		hookGlobalHistoryHandler(site);

		//		page.addPartListener(partListener);
		activate();

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void init(IViewSite site) throws PartInitException {
		super.init(site);
//		IWorkbenchPage page = site.getPage();
		// an ISelectionListener to react to workbench selection changes.

//		page.addSelectionListener(pageSelectionListener);
	}

	/**
	 * Hook the global undo/redi actions.
	 */
	//	private void hookGlobalHistoryHandler(IViewSite site) {
	//		undoHandler = new UndoActionHandler(site, null);
	//		redoHandler = new RedoActionHandler(site, null);
	//
	//		IActionBars actionBars = site.getActionBars();
	//
	//		actionBars.setGlobalActionHandler(ActionFactory.UNDO.getId(), undoHandler);
	//		actionBars.setGlobalActionHandler(ActionFactory.REDO.getId(), redoHandler);
	//	}

	/**
	 * {@link ResourceSetListener} to listen and react to changes in the
	 * resource set.
	 */
	private final ResourceSetListener resourceSetListener = new ResourceSetListenerImpl() {

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void resourceSetChanged(ResourceSetChangeEvent event) {
			super.resourceSetChanged(event);
			handleResourceSetChanged(event);
		}
	};

	/** cache variable with last transaction which triggered a refresh */
	private Transaction lastTrans = null;

	/**
	 * Run in a UI thread to avoid non UI thread exception.
	 * 
	 * @param event
	 */
	private void handleResourceSetChanged(ResourceSetChangeEvent event) {
		// avoid refreshing N times for the same transaction (called for each object in resource)
		Transaction curTrans = event.getTransaction();
		if(lastTrans != null && lastTrans.equals(curTrans)) {
			return;
		}
		lastTrans = curTrans;
		PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable() {

			/**
			 * {@inheritDoc}
			 */
			public void run() {
				refresh();
			}
		});
	}

	/**
	 * refresh the view.
	 */
	public void refresh() {
		// Need to refresh, even if (temporarily) invisible
		// (Better alternative?: store refresh event and execute once visible again)
		if(getControl().isDisposed()) {
			return;
		}

		// avoid reentrant call
		// Refresh only of we are not already refreshing.
		if(isRefreshing.compareAndSet(false, true)) {
			if(!getCommonViewer().isBusy()) {
				getCommonViewer().refresh();
			}

			isRefreshing.set(false);
		}

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Object getInitialInput() {

		if(serviceRegistry != null) {
			return serviceRegistry;
		} else {
			return super.getInitialInput();
		}
		
//		return super.getInitialInput();
	}

	/**
	 * Activate specified Part.
	 */
	private void activate() {
		
		setUpTreeElements(getInitialInput());
		
		// 테스트용 고정 servicesRegistry
//		String diPath = "/C:/Apex/EclipseWorkspaces/Modellipse-0.9.1/runtime-Modellipse-0.9.1/aaa/model2.di"; 
//		ApexProjectWrapper apw = (ApexProjectWrapper)ApexModellipseExplorerRoot.getProjectMap().get(diPath);
//		serviceRegistry = (ServicesRegistry)apw.getServicesRegistry(diPath);
		
		PapyrusMultiDiagramEditor activeEditor = (PapyrusMultiDiagramEditor)PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
		serviceRegistry = activeEditor.getServicesRegistry();

		try {
			this.editingDomain = ServiceUtils.getInstance().getTransactionalEditingDomain(serviceRegistry);
			//			this.editingDomain = EditorUtils.getTransactionalEditingDomain(editorPart.getServicesRegistry());
			// Set Viewer input if it already exist
			if(getCommonViewer() != null) {
				getCommonViewer().setInput(serviceRegistry);
			}
			editingDomain.addResourceSetListener(resourceSetListener);
		} catch (ServiceException e) {
			// Can't get EditingDomain, skip
		}
		
		// Get required services from ServicesRegistry
		try {
			saveAndDirtyService = serviceRegistry.getService(ISaveAndDirtyService.class);
			undoContext = serviceRegistry.getService(IUndoContext.class);
		} catch (ServiceException e) {
			e.printStackTrace();
		}		  

		// Listen to isDirty flag
		saveAndDirtyService.addInputChangedListener(editorInputChangedListener);

		// Hook
		//			if(undoHandler != null){
		//				IUndoContext undoContext = getUndoContext(part);
		//				undoHandler.setContext(undoContext);
		//				undoHandler.update();
		//				redoHandler.setContext(undoContext);
		//				redoHandler.update();
		//			}
		if(this.getCommonViewer() != null) {
			refresh();
		}

	}
	
	private void setUpTreeElements(Object inputElement) {
		IProject[] projects = null;
		if(inputElement instanceof IWorkspaceRoot) {
			projects = ((IWorkspaceRoot) inputElement).getProjects();			
		}
	
		if ( projects != null ) {
			List<Object> result = new ArrayList<Object>();
			
			for ( IProject project : projects) {
				try {
					if(project instanceof IPapyrusFile) {
						IPapyrusFile file = (IPapyrusFile)project;
						for(IResource r : file.getAssociatedResources()) {
							result.add(PapyrusModelHelper.getPapyrusModelFactory().createISubResourceFile(file, (IFile)r));
						}
					} else {
						IResource[] members = null;
						if(project instanceof IContainer) {
							members = ((IContainer)project).members();
						}
						if(members != null) {
							for(IResource r : members) {
								if(r instanceof IContainer && !(r instanceof IProject)) {
									IContainer cont = (IContainer)r;
									result.add(cont);
								} else if(r instanceof IFile) {
									if(OneFileUtils.isDi(r)) {
										IPapyrusFile createIPapyrusFile = PapyrusModelHelper.getPapyrusModelFactory().createIPapyrusFile((IFile)r);
										result.add(createIPapyrusFile);
										
										
										
										try {
											IEditorPart papyrusEditor = IDE.openEditor(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage(), (IFile)r, true);
											setServicesRegistries(r, papyrusEditor, result);
										} catch (WorkbenchException e) {
										}
										
										
										
									} else {
										if(!OneFileUtils.diExists(r.getName(), r.getParent())) {
											result.add(r);
										}
									}
								} else {
									result.add(r);
								}
							}
						}
					}
			
				} catch (CoreException e) {
				} 
			}
		}
		int a = 0;		a = a++;
	}
	
	public void setServicesRegistries(IResource r, IEditorPart editorPart, List<Object> result) throws PartInitException {
		/* apex added start */
		ServicesRegistry servicesRegistry = null;
		try {
			servicesRegistry = new ExtensionServicesRegistry("org.eclipse.papyrus.infra.core");
			
			
			
//			// Start the ModelSet first, and load if from the specified File
//			List<Class<?>> servicesToStart = new ArrayList<Class<?>>(1);
//			servicesToStart.add(ModelSet.class);
//
//			servicesRegistry.startServicesByClassKeys(servicesToStart);
//			ModelSet resourceSet = servicesRegistry.getService(ModelSet.class);
//
//			resourceSet.loadModels((IFile)r);
//			
//			UmlModel umlModel = (UmlUtils.getUmlModel(servicesRegistry));
//			EList<EObject> contents = umlModel.getResource().getContents();				
//				
//			Iterator<EObject> iterator = contents.iterator();
//			while(iterator.hasNext()) {
//				EObject eObject = iterator.next();
//				result.add(eObject);
//			}
//			System.out
//					.println("PapyrusContentProvider.getElements, line "
//							+ Thread.currentThread()
//									.getStackTrace()[1]
//									.getLineNumber());
//			System.out.println("servicesRegistry : " + servicesRegistry);
//			System.out.println("umlModel : " + umlModel);
			
			
			
			// Create ServicesRegistry and register services
			//servicesRegistry = createServicesRegistry();
//			String diPath = r.getLocationURI().getPath();
//			ApexProjectWrapper aProjectWrapper = (ApexProjectWrapper)ApexModellipseExplorerRoot.getProjectMap().get(diPath);
//			servicesRegistry = aProjectWrapper.getServicesRegistry(diPath);

			// Add itself as a service
			servicesRegistry.add(IMultiDiagramEditor.class, 1, (IMultiDiagramEditor)editorPart);

			// Create lifeCycle event provider and the event that is used when the editor fire a save event.
			//		lifeCycleEventsProvider = new LifeCycleEventsProvider();
			//		lifeCycleEvent = new DoSaveEvent(servicesRegistry, this);
			//		servicesRegistry.add(ILifeCycleEventsProvider.class, 1, lifeCycleEventsProvider);

			// register services
//			servicesRegistry.add(ActionBarContributorRegistry.class, 1, getActionBarContributorRegistry());
			//		servicesRegistry.add(TransactionalEditingDomain.class, 1, transactionalEditingDomain);
			//		servicesRegistry.add(DiResourceSet.class, 1, resourceSet);

			// Create and initalize editor icons service
			//		PageIconsRegistry pageIconsRegistry = new PageIconsRegistry();
			//		PluggableEditorFactoryReader editorReader = new PluggableEditorFactoryReader(Activator.PLUGIN_ID);
			//		editorReader.populate(pageIconsRegistry);
			//		servicesRegistry.add(IPageIconsRegistry.class, 1, pageIconsRegistry);


			// Create PageModelRegistry requested by content provider.
			// Also populate it from extensions.
			//		PageModelFactoryRegistry pageModelRegistry = new PageModelFactoryRegistry();
			//		editorReader.populate(pageModelRegistry, servicesRegistry);

			// TODO : create appropriate Resource for the contentProvider, and pass it here.
			// This will allow to remove the old sash stuff.
			//		setContentProvider(createPageProvider(pageModelRegistry, resourceSet.getDiResource(), transactionalEditingDomain));
			//		servicesRegistry.add(ISashWindowsContentProvider.class, 1, getContentProvider());
			//		servicesRegistry.add(IPageMngr.class, 1, getIPageMngr());

			// register a basic label provider
			// adapter factory used by EMF objects
			AdapterFactory factory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);

			/** label provider for EMF objects */
			ILabelProvider labelProvider = new AdapterFactoryLabelProvider(factory) {

				/**
				 * This implements {@link ILabelProvider}.getText by forwarding it to an object that implements {@link IItemLabelProvider#getText
				 * IItemLabelProvider.getText}
				 */
				@Override
				public String getText(Object object) {
					// Get the adapter from the factory.
					//
					IItemLabelProvider itemLabelProvider = (IItemLabelProvider)adapterFactory.adapt(object, IItemLabelProvider.class);
					if(object instanceof EObject) {
						if(((EObject)object).eIsProxy()) {
							return "Proxy - " + object;
						}
					}
					return itemLabelProvider != null ? itemLabelProvider.getText(object) : object == null ? "" : object.toString();
				}
			};
			servicesRegistry.add(ILabelProvider.class, 1, labelProvider);

			// Start servicesRegistry
			try {
				// Start the ModelSet first, and load if from the specified File
				List<Class<?>> servicesToStart = new ArrayList<Class<?>>(1);
				servicesToStart.add(ModelSet.class);

				servicesRegistry.startServicesByClassKeys(servicesToStart);
				ModelSet resourceSet = servicesRegistry.getService(ModelSet.class);
				resourceSet.loadModels((IFile)r);

				UmlModel umlModel = (UmlUtils.getUmlModel(servicesRegistry));
				EList<EObject> contents = umlModel.getResource().getContents();				
					
				Iterator<EObject> iterator = contents.iterator();
				while(iterator.hasNext()) {
					EObject eObject = iterator.next();
					result.add(eObject);
				}
				
				// start remaining services
				servicesRegistry.startRegistry();
			} catch (ModelMultiException e) {
				try {
					// with the ModelMultiException it is still possible to open the editors that's why the service registry is still started 
					servicesRegistry.startRegistry();
					warnUser(e);
				} catch (ServiceException e1) {
					log.error(e);
					throw new PartInitException("could not initialize services", e); //$NON-NLS-1$
				}
			} catch (ServiceException e) {
				log.error(e);
				e.printStackTrace();
				throw new PartInitException("could not initialize services", e);
			}


			// Get required services
			ISashWindowsContentProvider contentProvider = null;
			try {
				editingDomain = servicesRegistry.getService(TransactionalEditingDomain.class);
//				DiSashModelMngr sashModelMngr = servicesRegistry.getService(DiSashModelMngr.class);
//				contentProvider = servicesRegistry.getService(ISashWindowsContentProvider.class);
				saveAndDirtyService = servicesRegistry.getService(ISaveAndDirtyService.class);
				undoContext = servicesRegistry.getService(IUndoContext.class);
			} catch (ServiceException e) {
				log.error("A required service is missing.", e);
				// if one of the services above fail to start, the editor can't run => stop
				throw new PartInitException("could not initialize services", e);
			}

			// Set the content provider providing editors.
//			setContentProvider(contentProvider);

			// Set editor name
			setPartName(((IFile)r).getName());

			// Listen on contentProvider changes
//			sashModelMngr.getSashModelContentChangedProvider().addListener(contentChangedListener);

			// Listen on input changed from the ISaveAndDirtyService
			saveAndDirtyService.addInputChangedListener(editorInputChangedListener);
			
			/* apex added end */
		} catch (ServiceException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}		
		addToGlobalRoot((IFile)r, servicesRegistry, saveAndDirtyService, editingDomain, undoContext);
	}
	
	/**
	 * apex added
	 * 
	 * @param servicesRegistry
	 */
	private void addToGlobalRoot(IFile diFile, 
			                     ServicesRegistry servicesRegistry,
			                     ISaveAndDirtyService saveAndDirtyService,
			                     TransactionalEditingDomain transactionalEditingDomain,
			                     IUndoContext undoContext) {
//			                     IPropertySheetPage propertySheetPage) {
		
			String diPath = diFile.getLocationURI().getPath();
			String projectPath = diFile.getParent().getLocationURI().getPath();
			ApexProjectWrapper projectWrapper = null;
			UmlModel umlModel = UmlUtils.getUmlModel(servicesRegistry);
			
			if ( ApexModellipseExplorerRoot.getProjectMap().containsKey(projectPath) ) {
				projectWrapper = (ApexProjectWrapper) ApexModellipseExplorerRoot.getProjectMap().get(projectPath);
			

				if ( !projectWrapper.getDiFileMap().containsKey(diPath) ) {
					projectWrapper.put(diPath, umlModel);
					projectWrapper.addChildren(umlModel);	
				}
				if ( !projectWrapper.getServicesRegistryMap().containsKey(diPath) ) {
					projectWrapper.put(diPath, servicesRegistry);
					projectWrapper.addServicesRegistryChildren(servicesRegistry);
				}
				if ( !projectWrapper.getSaveAndDirtyServiceMap().containsKey(diPath) ) {
					projectWrapper.put(diPath, saveAndDirtyService);
				}
				if ( !projectWrapper.getTransactionalEditingDomainMap().containsKey(diPath) ) {
					projectWrapper.put(diPath, transactionalEditingDomain);
				}
				if ( !projectWrapper.getUndoContextMap().containsKey(diPath) ) {
					projectWrapper.put(diPath, undoContext);
				}
				if ( !projectWrapper.getDiFileMap().containsKey(diPath) ) {
					projectWrapper.put(diPath, diFile);
				}
//				if ( !projectWrapper.getPropertySheetPageMap().containsKey(diPath) ) {
//					projectWrapper.put(diPath, propertySheetPage);
//				}
				
			} else {
				projectWrapper = new ApexProjectWrapper(diFile.getProject());
				ApexModellipseExplorerRoot.getProjectMap().put(projectPath, projectWrapper);
				projectWrapper.addChildren(umlModel);
				projectWrapper.addServicesRegistryChildren(servicesRegistry);
				projectWrapper.put(diPath, servicesRegistry);
				projectWrapper.put(diPath, saveAndDirtyService);
				projectWrapper.put(diPath, transactionalEditingDomain);
				projectWrapper.put(diPath, undoContext);
				projectWrapper.put(diPath, diFile);
				projectWrapper.put(diPath, umlModel);
//				projectWrapper.put(diPath, propertySheetPage);
			}
			
//			projectWrapper.addChildren(servicesRegistry);
//			ApexModellipseExplorerRoot.getServicesRegistryList().add(servicesRegistry);	
//		}
	}
	
	

	/**
	 * Deactivate the Model Explorer.
	 */
	private void deactivate() {
		// deactivate global handler
		if(Activator.log.isDebugEnabled()) {
			Activator.log.debug("deactivate ModelExplorerView"); //$NON-NLS-1$
		}

		// Stop listening on change events
//		getSite().getPage().removeSelectionListener(pageSelectionListener);
		// Stop Listening to isDirty flag
		saveAndDirtyService.removeInputChangedListener(editorInputChangedListener);

		// unhook
		//			IUndoContext undoContext = getUndoContext(editorPart);
		//			undoHandler.setContext(undoContext);
		//			undoHandler.update();
		//			redoHandler.setContext(undoContext);
		//			redoHandler.update();


		if(editingDomain != null) {
			editingDomain.removeResourceSetListener(resourceSetListener);
			editingDomain = null;
		}

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void dispose() {

		// Stop if we are already disposed
		if(isDisposed()) {
			return;
		}

		deactivate();

		saveAndDirtyService = null;
		undoContext = null;
		editingDomain = null;
		serviceRegistry = null;

		super.dispose();

		// Clean up properties to help GC

	}

	/**
	 * Return true if the component is already disposed.
	 * 
	 * @return
	 */
	public boolean isDisposed() {
		// use editorPart as flag
		return saveAndDirtyService == null;
	}

	/**
	 * Retrieves the {@link IPropertySheetPage} that his Model Explorer uses.
	 * 
	 * @return
	 */
	private IPropertySheetPage getPropertySheetPage() {
		final IMultiDiagramEditor multiDiagramEditor = EditorUtils.getMultiDiagramEditor();

		if(multiDiagramEditor != null) {
			if(propertySheetPage == null) {
				if(multiDiagramEditor instanceof ITabbedPropertySheetPageContributor) {
					ITabbedPropertySheetPageContributor contributor = (ITabbedPropertySheetPageContributor)multiDiagramEditor;
					this.propertySheetPage = new TabbedPropertySheetPage(contributor);
				}
			}
			return propertySheetPage;
		}
		return null;
	}

	/**
	 * in order to see element if the property view
	 */
	@Override
	@SuppressWarnings("rawtypes")
	public Object getAdapter(Class adapter) {
		if(IPropertySheetPage.class.equals(adapter)) {
			return getPropertySheetPage();
		}

		if(IUndoContext.class == adapter) {
			// Return the IUndoContext of associated model.
			return undoContext;
		}

		if(ISaveablePart.class.equals(adapter)) {
			return saveAndDirtyService;
		}

		if(ServicesRegistry.class == adapter) {
			return serviceRegistry;
		}

		return super.getAdapter(adapter);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @return the EditingDomain used by the properties view
	 */
	public EditingDomain getEditingDomain() {
		return editingDomain;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void selectReveal(ISelection selection) {
		if(getCommonViewer() != null) {
			getCommonViewer().setSelection(selection, true);
		}
	}

	public void revealSemanticElement(List<?> elementList) {
		reveal(elementList, getCommonViewer());
	}

	/**
	 * Expands the given CommonViewer to reveal the given elements
	 * @param elementList The elements to reveal
	 * @param commonViewer The CommonViewer they are to be revealed in
	 */
	public static void reveal(Iterable<?> elementList, CommonViewer commonViewer) {
		ArrayList<IMatchingItem> matchingItemsToSelect = new ArrayList<IMatchingItem>();
		// filter out non EMF objects
		Iterable<EObject> list = Iterables.transform(Iterables.filter(elementList, EObject.class), new Function<Object, EObject>() {

			public EObject apply(Object from) {
				return (EObject)from;
			}
		});

		for(EObject currentEObject : list) {
			matchingItemsToSelect.add(new ModelElementItemMatchingItem(currentEObject));

			// the content provider exist?
			if(commonViewer.getContentProvider() != null) {
				// retrieve the ancestors to reveal them
				// and allow the selection of the object
				ArrayList<EObject> parents = new ArrayList<EObject>();
				EObject tmp = currentEObject.eContainer();
				while(tmp != null) {
					parents.add(tmp);
					tmp = tmp.eContainer();
				}

				Iterable<EObject> reverseParents = Iterables.reverse(parents);

				// reveal the resource if necessary
				Resource r = null;
				if (!parents.isEmpty()) {
					 r = parents.get(parents.size() - 1).eResource();
				} else {
					r = currentEObject.eResource();
				}

				if (r != null) {
					ResourceSet rs = r.getResourceSet();
					if (rs instanceof ModelSet && AdditionalResourcesModel.isAdditionalResource((ModelSet)rs, r.getURI())) {
						commonViewer.expandToLevel(new ReferencableMatchingItem(rs), 1);
						commonViewer.expandToLevel(new ReferencableMatchingItem(r), 1);
					}
				}

				/*
				 * reveal the ancestors tree using expandToLevel on each of them
				 * in the good order. This is a lot faster than going through the whole tree
				 * using getChildren of the ContentProvider since our Viewer uses a Hashtable
				 * to keep track of the revealed elements.
				 * 
				 * However we need to use a dedicated MatchingItem to do the matching,
				 * and a specific comparer in our viewer so than the equals of MatchingItem is
				 * used in priority.
				 * 
				 * Please refer to MatchingItem for more infos.
				 */
				EObject previousParent = null;
				for(EObject parent : reverseParents) {
					if(parent.eContainingFeature() != null && previousParent != null) {
						commonViewer.expandToLevel(new LinkItemMatchingItem(previousParent, parent.eContainmentFeature()), 1);
					}
					commonViewer.expandToLevel(new ModelElementItemMatchingItem(parent), 1);
					previousParent = parent;
				}
				commonViewer.expandToLevel(new LinkItemMatchingItem(currentEObject.eContainer(), currentEObject.eContainmentFeature()), 1);
			}
		}

		selectReveal(new StructuredSelection(matchingItemsToSelect), commonViewer);
	}
	
	/**
	 * Selects the given ISelection in the given CommonViwer
	 * @param structuredSelection The ISelection to select
	 * @param commonViewer The ComonViewer to select it in
	 */
	public static void selectReveal(ISelection structuredSelection, Viewer commonViewer) {
		commonViewer.setSelection(structuredSelection, true);
	}

	/**
	 * Selects and, if possible, reveals the given ISelection in the given CommonViwer
	 * @param selection The ISelection to select
	 * @param viewer The ComonViewer to select it in
	 */
	public static void reveal(ISelection selection, CommonViewer viewer) {
		if(selection instanceof IStructuredSelection) {
			IStructuredSelection structured = (IStructuredSelection)selection;
			reveal(Lists.newArrayList(structured.iterator()), viewer);
		} else {
			viewer.setSelection(selection);
		}
	}
	
	protected void warnUser(ModelMultiException e) {
		MessageDialog.openError(getSite().getShell(), "Error", String.format("Your model is corrupted, invalid links have been found :\n"
			+ "%s"
			+ "It is recommended to fix it before editing it", e.getMessage()));
	}

}
