/*****************************************************************************
 * Copyright (c) 2010 CEA LIST.
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
package kr.co.apexsoft.stella.modeler.explorer.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

import kr.co.apexsoft.stella.modeler.explorer.Activator;
import kr.co.apexsoft.stella.modeler.explorer.core.ApexDIWrapper;
import kr.co.apexsoft.stella.modeler.explorer.provider.ApexDecoratingLabelProviderWTooltips;

import org.eclipse.core.commands.operations.IUndoContext;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.emf.facet.infra.browser.uicore.internal.model.ITreeElement;
import org.eclipse.emf.facet.infra.browser.uicore.internal.model.LinkItem;
import org.eclipse.emf.facet.infra.browser.uicore.internal.model.ModelElementItem;
import org.eclipse.emf.transaction.ResourceSetChangeEvent;
import org.eclipse.emf.transaction.ResourceSetListener;
import org.eclipse.emf.transaction.ResourceSetListenerImpl;
import org.eclipse.emf.transaction.Transaction;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.MenuManager;
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
import org.eclipse.papyrus.infra.core.editor.IMultiDiagramEditor;
import org.eclipse.papyrus.infra.core.lifecycleevents.IEditorInputChangedListener;
import org.eclipse.papyrus.infra.core.lifecycleevents.ISaveAndDirtyService;
import org.eclipse.papyrus.infra.core.resource.ModelMultiException;
import org.eclipse.papyrus.infra.core.resource.ModelSet;
import org.eclipse.papyrus.infra.core.resource.additional.AdditionalResourcesModel;
import org.eclipse.papyrus.infra.core.sasheditor.contentprovider.IPageMngr;
import org.eclipse.papyrus.infra.core.services.ServiceException;
import org.eclipse.papyrus.infra.core.services.ServicesRegistry;
import org.eclipse.papyrus.infra.core.ui.IRevealSemanticElement;
import org.eclipse.papyrus.infra.core.utils.EditorUtils;
import org.eclipse.papyrus.infra.core.utils.ServiceUtils;
import org.eclipse.papyrus.infra.emf.providers.SemanticFromModelExplorer;
import org.eclipse.papyrus.views.modelexplorer.CustomCommonViewer;
import org.eclipse.papyrus.views.modelexplorer.matching.IMatchingItem;
import org.eclipse.papyrus.views.modelexplorer.matching.LinkItemMatchingItem;
import org.eclipse.papyrus.views.modelexplorer.matching.ModelElementItemMatchingItem;
import org.eclipse.papyrus.views.modelexplorer.matching.ReferencableMatchingItem;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.ISaveablePart;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.WorkbenchWindow;
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
 * ApexStellaExplorer 는 여러개의 IMultiDiagramEditor에 존재하는 여러 모델을 하나의 View에서 보여준다.
 * 
 */
public class ApexStellaExplorerView extends CommonNavigator 
                                        implements IRevealSemanticElement, 
                                                   IEditingDomainProvider,
                                                   ITabbedPropertySheetPageContributor {

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
	 * in {@link ApexStellaExplorerView#init(IViewSite)}. It should be dispose to remove
	 * hook to the Eclipse page.
	 */
	private ISelectionListener pageSelectionListener = new ISelectionListener() {

		public void selectionChanged(IWorkbenchPart part, ISelection selection) {
			handleSelectionChangedFromDiagramEditor(part, selection);
			handleSelectionChange(part, selection);
		}
	};	

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

	public ApexStellaExplorerView() {
		super();
		setLinkingEnabled(true);
	}	

	/**
	 * apex updated
	 * Editor 또는 Diagram이 바뀌는 경우 해당 Editor의 serviceRegistry가져와서
	 * editingDomain, saveAndDirtyService, undoContext setting 
	 * 
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
	 * Selection 이 바뀌었을 경우 처리
	 * ModelExplorer의 선택에 따라 해당 요소의 View를 Editor에서 reveal하도록 구현하려 했으나 실패
	 * 현재 Editor선택에 따라 ServiceRegistry를 구성하는 역할 수행
	 * 
	 * @param part
	 * @param selection
	 */
	private void handleSelectionChange(IWorkbenchPart part, ISelection selection) {
//		if(selection instanceof IStructuredSelection) {
//System.out.println("ApexStellaExplorerView.handleSelectionChange(), line "
//		+ Thread.currentThread().getStackTrace()[1].getLineNumber());
//
//			String selectedModelName = null;
//			IStructuredSelection structuredSelection = (IStructuredSelection)selection;
//			
//			Iterator its = structuredSelection.iterator();
//			for ( int i = 0 ; its.hasNext() ; i++ ) {
//				Object obj = its.next();
//				if ( obj instanceof ITreeElement ) {
//					ITreeElement itree = (ITreeElement)obj;
//					System.out.println(" structuredSelection["+i+"].getText() : " + itree.getText());
//					System.out.println(" structuredSelection["+i+"]           : " + itree);
//				}
//				
//			}
//			
//			Iterator it = structuredSelection.iterator();
//			while(it.hasNext()) {
//				Object obj = it.next();
//				
//				if ( obj instanceof EditPart ) {
//					return;
//				} else if ( obj instanceof IFile ) {
//					IFile diFile = (IFile)obj;
//
//					if(OneFileUtils.isDi(diFile)) {
//						selectedModelName = diFile.getName();
//					}
//					System.out.println("  obj instanceof IFile");
//					System.out.println("  obj : " + obj);
//				} else if ( obj instanceof ITreeElement ) {
//					ITreeElement modelItem = (ITreeElement)obj;				
//					ITreeElement modelImplElement = getDiWrapperFromChildren(modelItem);			
//
//					if ( modelImplElement instanceof ApexDIWrapper ) {
//						selectedModelName = ((ApexDIWrapper)modelImplElement).getFile().getName();	
//					} else {
//						System.out.println("getDIWrapper()가 DI가 아닌 것을 반환했다!!");
//						System.out.println(" selected ModelItem.getText() : " + modelItem.getText());
//						System.out.println(" selected ModelItem           : " + modelItem);
//						System.out.println(" returned ITreeElement from getDIWrapper() : " + modelImplElement);
//					}
//					System.out.println("  obj instanceof ITreeElement");
//					System.out.println("  obj : " + obj);
//				} else {
//					System.out.println(" obj is fucking mess");
//					System.out.println(" obj : " + obj);
//				}
//				
//				System.out.println("  selectedModelName : " + selectedModelName);
//			}
//
//			IWorkbenchPage workbenchPage = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();			
//			IEditorReference[] editorRefs = workbenchPage.getEditorReferences();
//
//			for ( IEditorReference editorRef : editorRefs ) {
//				IEditorPart editorPart = editorRef.getEditor(true);
//				String editorName = editorPart.getTitle();
//				
//				if ( editorName.equals(selectedModelName) ) {
//					System.out.println("  editorName : " + editorName);
//					workbenchPage.activate(editorPart);
//				}
//			}
//		}
		
		
		
		/* apex added start */
		if ( part instanceof PapyrusMultiDiagramEditor ) {
			PapyrusMultiDiagramEditor papyrusEditor = (PapyrusMultiDiagramEditor)part;
			serviceRegistry = papyrusEditor.getServicesRegistry();
			try {
				editingDomain = ServiceUtils.getInstance().getTransactionalEditingDomain(serviceRegistry);
				//			this.editingDomain = EditorUtils.getTransactionalEditingDomain(editorPart.getServicesRegistry());
				// Set Viewer input if it already exist
//				if(getCommonViewer() != null) {
//					getCommonViewer().setInput(serviceRegistry);
//				}							
				saveAndDirtyService = serviceRegistry.getService(ISaveAndDirtyService.class);
				undoContext = serviceRegistry.getService(IUndoContext.class);

				editingDomain.addResourceSetListener(resourceSetListener);
				saveAndDirtyService.addInputChangedListener(editorInputChangedListener);
			} catch (ServiceException e) {
				// Can't get EditingDomain, skip
				e.printStackTrace();
			}						
		}
		/* apex added end */
	}

//	private ITreeElement getDiWrapperFromChildren(ITreeElement modelItem) {
//
//		ITreeElement diWrapper = null;
//		ITreeElement parentItem = modelItem.getTreeParent();
//
//		if ( parentItem != null ) {			
//			System.out.println("    selectedItem.getText()   : " + modelItem.getText());
//			System.out.println("    selectedItem               : " + modelItem);
//			System.out.println("    parentItem.getText()     : " + parentItem.getText());
//			System.out.println("    parentItem                 : " + parentItem);
//			
//			
//			if (parentItem instanceof ModelElementItem
//				|| parentItem instanceof LinkItem) {
//				parentItem = getDiWrapperFromChildren((ITreeElement)parentItem);
//			}
//			if (parentItem instanceof ApexDIWrapper) {
//				diWrapper = (ApexDIWrapper)parentItem;			
//			} else {
//				System.out
//						.println("ApexStellaExplorerView.getDiFileFromChildren(), line "
//								+ Thread.currentThread().getStackTrace()[1]
//										.getLineNumber());
//				System.out.println("  di없다");
//				System.out.println("  parentItem : " + parentItem);
//			}
//		} else {
//			System.out.println("In getDIWrapper(),  parentItem이 null이다");
//			System.out.println("  modelItem.getText() : " + modelItem.getText());
//			System.out.println("  modelItem             : " + modelItem);
//		}
//		return diWrapper;
//	}

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
					viewer.setLabelProvider(new ApexDecoratingLabelProviderWTooltips(labelProvider)); // add for decorator and tooltip support
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

//		makeApexCustomContextMenu(aParent);
	}
	
	private void makeApexCustomContextMenu(Composite aParent) {
		
//		final List<IContributionItem> contextMenuItemList = new ArrayList<IContributionItem>();
//		IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();		
//		
//		if ( window instanceof WorkbenchWindow ) {
//			MenuManager menuMgr = ((WorkbenchWindow)window).getMenuManager();
//			
//			IContributionItem[] items = menuMgr.getItems();
//			System.out
//					.println("ApexStellaExplorerView.createPartControl(), line "
//							+ Thread.currentThread().getStackTrace()[1]
//									.getLineNumber());
//			System.out.println("  from Window Menu Manager");
//			
//			for ( IContributionItem item : items ) {
//				String menuId = item.getId();
//				System.out.println("    " + menuId);
//				
//				if ( item != null && item.getId() != null ) {
//					if ( menuId.equals("file") ) {
//						contextMenuItemList.add(item);
//					}
//				}
//			}
//		}
//			
//		MenuManager menuMgr = new MenuManager("#Popup") {
//			@Override
//			public IContributionItem[] getItems() {
//				
//				IContributionItem[] items = super.getItems();
//				System.out.println(" super.getItems().length : " + super.getItems().length);
//				List<IContributionItem> filteredItems = new ArrayList<IContributionItem> ();
//				filteredItems.addAll(contextMenuItemList);
//				System.out.println("filteredItems : " + filteredItems);
//				
//				for ( IContributionItem aItem : filteredItems) {
//					System.out.println("   item from filtered : " + aItem);
//					if ( aItem instanceof MenuManager ) {
//						MenuManager aMenuManager = (MenuManager)aItem;
//						IContributionItem[] aSubofItem = aMenuManager.getItems();
//						
//						for ( IContributionItem aofaItem : aSubofItem ) {
//							System.out.println("    sub of filtered : " + aofaItem);
//						}
//					}
//				}
//				
//				System.out
//				.println("ApexStellaExplorerView.createPartControl(), line "
//						+ Thread.currentThread().getStackTrace()[1]
//								.getLineNumber());
//				System.out.println("  contextFromMain : " + contextMenuItemList);
//				System.out.println("  from Overriding");
//				
//				for ( IContributionItem menuItem : items ) {
//					String menuId = menuItem.getId();
//					System.out.println("    menuItem : " + menuItem);
//					System.out.println("    menuItem.getId() : " + menuId);
//				
//					if ( menuItem!=null && menuItem.getId()!=null) {
//						if ( !menuId.equals("org.eclipse.papyrus.uml.diagram.ui.menu")
//								 && !menuId.equals("org.eclipse.papyrus.ui.menu")
//								 && !menuId.equals("org.eclipse.debug.ui.contextualLaunch.run.submenu")
//								 && !menuId.equals("org.eclipse.debug.ui.contextualLaunch.debug.submenu")
//								 && !menuId.equals("org.eclipse.ui.projectConfigure")
//								 && !menuId.equals("org.eclipse.modisco.infra.discovery.ui.menu")
//						) {
//								filteredItems.add(menuItem);
//								System.out.println("      added : " + menuItem);
//						} else {
//							System.out.println("      not added : " + menuItem);
//						}	
//					}
//					
//				}	
//				items = new IContributionItem[ filteredItems.size()];
//				return filteredItems.toArray( items );
//			}
//		};
//		Menu menu = menuMgr.createContextMenu(aParent);
//		getCommonViewer().getControl().setMenu(menu);
//		getSite().registerContextMenu(menuMgr, getCommonViewer());
		
		
		
		
		
		
//		MenuManager menuMgr = new MenuManager("#Popup") {
//			@Override
//			public IContributionItem[] getItems() {
//				
//				IContributionItem[] items = super.getItems();
//				List<IContributionItem> filteredItems = new ArrayList<IContributionItem> ();
//				System.out
//				.println("ApexStellaExplorerView.createPartControl(...).new MenuManager() {...}.getItems(), line "
//						+ Thread.currentThread()
//								.getStackTrace()[1]
//								.getLineNumber());
//				
//				for( IContributionItem item : items ) {
//			    	
//				    if( item != null
//				         && item.getId() != null
////				         && !item.getId().startsWith( "org.eclipse.debug.ui.contextualLaunch." )
////				        	 || item.getId().startsWith("kr.co.apexsoft.")
////							 || item.getId().startsWith("org.eclipse.ui.navigator.resources.")
////					     ) 
//					) {
//				    	System.out.println("    menuItem : " + item.getId());
//						filteredItems.add( item );						
//					}
//		        
//				}
//
//			  items = new IContributionItem[ filteredItems.size()];
//			  return filteredItems.toArray( items );
//			}
//			
//		};
//		Menu menu = menuMgr.createContextMenu(aParent);
//		getCommonViewer().getTree().setMenu(menu);
//		getSite().registerContextMenu(menuMgr, getCommonViewer());		
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
		IWorkbenchPage page = site.getPage();
		// an ISelectionListener to react to workbench selection changes.

		page.addSelectionListener(pageSelectionListener);
//		ISelectionProvider selectionProvider = site.getSelectionProvider();
//		selectionProvider.addSelectionChangedListener(selectionChangeListener);
		
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
		Object initialInput = null;
//		if(serviceRegistry != null) {
//			initialInput = serviceRegistry;
//		} else {
//			initialInput = super.getInitialInput();
//		}


		initialInput = super.getInitialInput();

		return initialInput;
	}

	/**
	 * Activate specified Part.
	 */
	private void activate() {

		if(this.getCommonViewer() != null) {
			refresh();
		}
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
		getSite().getPage().removeSelectionListener(pageSelectionListener);
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
		this.removeListenerObject(pageSelectionListener);
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
	 * apex updated
	 * 
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
			if(commonViewer.getContentProvider() != null
			   /* apex added start */
			   && !commonViewer.isBusy()) {
			   /* apex added end */
					
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
	
	/**
	 * {@inheritDoc}
	 */
	public String getContributorId() {
		// return Activator.PLUGIN_ID;
		// papyrus 내용 재사용. xml에서 TreeOutlinePage 검색으로 분석 가능
		// 실제는 MultiDiagramEditor의 getContributorId()에 명시되어 있는 것이 유효
		return "TreeOutlinePage"; //$NON-NLS-1$

	}
}