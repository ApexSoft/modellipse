/*****************************************************************************
 * Copyright (c) 2011 CEA LIST.
 *
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Vincent Lorenzo (CEA LIST) vincent.lorenzo@cea.fr - Initial API and implementation
 *
 *****************************************************************************/
package org.eclipse.papyrus.infra.hyperlink.ui;

import java.util.Collection;
import java.util.Iterator;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.papyrus.infra.core.editor.BackboneException;
import org.eclipse.papyrus.infra.core.editorsfactory.IPageIconsRegistry;
import org.eclipse.papyrus.infra.core.editorsfactory.IPageIconsRegistryExtended;
import org.eclipse.papyrus.infra.core.editorsfactory.PageIconsRegistry;
import org.eclipse.papyrus.infra.core.extension.NotFoundException;
import org.eclipse.papyrus.infra.core.extension.commands.CreationCommandDescriptor;
import org.eclipse.papyrus.infra.core.extension.commands.CreationCommandRegistry;
import org.eclipse.papyrus.infra.core.extension.commands.ICreationCommand;
import org.eclipse.papyrus.infra.core.extension.commands.ICreationCommandRegistry;
import org.eclipse.papyrus.infra.core.sasheditor.contentprovider.IPageMngr;
import org.eclipse.papyrus.infra.core.services.ServiceException;
import org.eclipse.papyrus.infra.core.services.ServicesRegistry;
import org.eclipse.papyrus.infra.core.utils.DiResourceSet;
import org.eclipse.papyrus.infra.core.utils.EditorUtils;
import org.eclipse.papyrus.infra.emf.providers.strategy.SemanticEMFContentProvider;
import org.eclipse.papyrus.infra.emf.utils.EMFHelper;
import org.eclipse.papyrus.infra.hyperlink.helper.AbstractHyperLinkEditorHelper;
import org.eclipse.papyrus.infra.hyperlink.object.HyperLinkEditor;
import org.eclipse.papyrus.infra.hyperlink.util.EditorListContentProvider;
import org.eclipse.papyrus.infra.hyperlink.util.HyperLinkEditorHelpersRegistrationUtil;
import org.eclipse.papyrus.infra.table.instance.papyrustableinstance.PapyrusTableInstance;
import org.eclipse.papyrus.views.modelexplorer.DecoratingLabelProviderWTooltips;
import org.eclipse.papyrus.views.modelexplorer.MoDiscoLabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
//TODO remove this dependency



//TODO : remove the table dependencies
public class EditorLookForEditorShell extends AbstractLookForEditorShell {

	/** The adapter factory. */
	protected AdapterFactory adapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);

	/** The selected diagram. */
	protected Object selectedEditor;

	/**
	 * Gets the selected editor.
	 * 
	 * @return the selectedEditor
	 */
	protected Object getSelectedEditor() {
		return selectedEditor;
	}

	/**
	 * Sets the selected editor
	 * 
	 * @param selectedEditor
	 *        the selectedEditor to set
	 */
	protected void setSelectedEditor(Object selectedEditor) {
		this.selectedEditor = selectedEditor;
	}

	/** The model. */
	protected EObject model;

	/** The diagram menu button. */
	protected Menu diagramMenuButton;

	/** The diagram list tree viewer. */
	protected TreeViewer diagramListTreeViewer;

	/** The tree viewer. */
	protected TreeViewer treeViewer;

	/**
	 * The listener interface for receiving diagramCreate events. The class that
	 * is interested in processing a diagramCreate event implements this
	 * interface, and the object created with that class is registered with a
	 * component using the component's <code>addDiagramCreateListener<code> method. When
	 * the diagramCreate event occurs, that object's appropriate
	 * method is invoked.
	 * 
	 * @see DiagramCreateEvent
	 */
	public class DiagramCreateListener extends SelectionAdapter {

		/** The command descriptor. */
		private final CreationCommandDescriptor commandDescriptor;

		/** The container. */
		private EObject container;

		/** The i creation command registry. */
		private final ICreationCommandRegistry iCreationCommandRegistry;

		/**
		 * {@inheritedDoc}.
		 * 
		 * @param e
		 *        the e
		 */
		@Override
		public void widgetSelected(SelectionEvent e) {
			try {
				EObject elt = (EObject)((IStructuredSelection)treeViewer.getSelection()).getFirstElement();

				setContainer(elt);
				ServicesRegistry servicesRegistry = EditorUtils.getServiceRegistry();
				DiResourceSet diResourceSet = servicesRegistry.getService(DiResourceSet.class);

				ICreationCommand creationCommand = iCreationCommandRegistry.getCommand(commandDescriptor.getCommandId());
				creationCommand.createDiagram(diResourceSet, container, null);

				// refresh several filtered tree
				getDiagramfilteredTree().getViewer().setInput(null);
				getDiagramfilteredTree().getViewer().setInput(""); //$NON-NLS-1$
				getModeFilteredTree().getViewer().setInput(null);
				getModeFilteredTree().getViewer().setInput(model.eResource());
			} catch (NotFoundException ex) {
				ex.printStackTrace();
			} catch (BackboneException ex) {
				ex.printStackTrace();
			} catch (ServiceException ex) {
				ex.printStackTrace();
			}
		}

		/**
		 * Instantiates a new diagram create listener.
		 * 
		 * @param commandDescriptor
		 *        the command descriptor
		 * @param backboneContext
		 *        the backbone context
		 * @param container
		 *        the container
		 * @param iCreationCommandRegistry
		 *        the i creation command registry
		 */
		public DiagramCreateListener(CreationCommandDescriptor commandDescriptor, EObject container, ICreationCommandRegistry iCreationCommandRegistry) {
			super();
			this.commandDescriptor = commandDescriptor;
			this.container = container;
			this.iCreationCommandRegistry = iCreationCommandRegistry;
		}

		/**
		 * Sets the container.
		 * 
		 * @param container
		 *        the new container
		 */
		public void setContainer(EObject container) {
			this.container = container;
		}
	}




	/**
	 * Instantiates a new editor look for diagram.
	 * 
	 * @param editorFactoryRegistry
	 *        the editor factory registry
	 * @param amodel
	 *        the amodel
	 */
	public EditorLookForEditorShell(IPageIconsRegistry editorFactoryRegistry, EObject amodel) {
		super();
		this.model = amodel;
		// create the shell
		createLookforShell();

		// intall tree with uml element
		treeViewer = getModeFilteredTree().getViewer();
		treeViewer.setUseHashlookup(true);
		//		treeViewer.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory) {
		//
		//			@Override
		//			public Image getImage(Object object) {
		//				//TODO
		////				if(object instanceof PapyrusTableInstance) {
		////					return editorRegistry.getEditorIcon((object));
		////				} else {
		////					return super.getImage(object);
		////				}
		//				Image im = super.getImage(object);
		//				return im;
		////				return null;
		//			}
		//		});
		ILabelProvider labelProvider = new LocalLabelProvider();
		treeViewer.setLabelProvider(labelProvider);
		//treeViewer.setContentProvider(new CustomAdapterFactoryContentProvider(adapterFactory));
		treeViewer.setContentProvider(new SemanticEMFContentProvider(new EObject[]{ amodel }));
		//		treeViewer.setContentProvider(new CustomizableModelContentProvider());
		//treeViewer.setInput(model.eResource());
		treeViewer.setInput(EditorUtils.getServiceRegistry());

		// install diagramlist
		diagramListTreeViewer = getDiagramfilteredTree().getViewer();
		diagramListTreeViewer.setUseHashlookup(true);

		// fill list of diagram
		//TODO
		//diagramListTreeViewer.setLabelProvider(new ObjectLabelProvider(null));

		//we can't reuse the same instance of the label provider see bug 385599: [Hyperlink] We can't select the diagram/table for referencing them
		labelProvider = new LocalLabelProvider();
		diagramListTreeViewer.setLabelProvider(labelProvider);


		diagramListTreeViewer.setContentProvider(new EditorListContentProvider());
		diagramListTreeViewer.setInput(" "); //$NON-NLS-1$

		// add listner on the new button to display menu for each diagram
		diagramMenuButton = new Menu(getNewDiagrambutton());
		getNewDiagrambutton().setMenu(diagramMenuButton);
		CreationCommandRegistry commandRegistry = CreationCommandRegistry.getInstance(org.eclipse.papyrus.infra.core.Activator.PLUGIN_ID);
		for(CreationCommandDescriptor desc : commandRegistry.getCommandDescriptors()) {
			MenuItem menuItem = new MenuItem(diagramMenuButton, SWT.PUSH);
			menuItem.addSelectionListener(new DiagramCreateListener(desc, null, commandRegistry));
			menuItem.setText(desc.getLabel());
		}
		getNewDiagrambutton().addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {

			@Override
			public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
				diagramMenuButton.setVisible(true);
			}
		});

		// add listener to remove diagram
		getRemoveDiagrambutton().addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {

			@Override
			public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
				IStructuredSelection iSelection = (IStructuredSelection)getModeFilteredTree().getViewer().getSelection();
				Iterator iterator = iSelection.iterator();
				while(iterator.hasNext()) {
					IPageMngr pageMngr = EditorUtils.getIPageMngr();
					pageMngr.removePage(iterator.next());
				}
				getDiagramfilteredTree().getViewer().setInput(null);
				getDiagramfilteredTree().getViewer().setInput(""); //$NON-NLS-1$
				getModeFilteredTree().getViewer().setInput(null);
				getModeFilteredTree().getViewer().setInput(model.eResource());
			}
		});

		// add listener to keep the selected diagram in the list for the model
		// view
		getModeFilteredTree().getViewer().addSelectionChangedListener(new ISelectionChangedListener() {

			public void selectionChanged(SelectionChangedEvent event) {
				Object selection = ((IStructuredSelection)getModeFilteredTree().getViewer().getSelection()).getFirstElement();
				refresh(selection);

			}
		});

		// add listener to keep in mind the selected diagram in the list for the
		// view of digram list
		getDiagramfilteredTree().getViewer().addSelectionChangedListener(new ISelectionChangedListener() {

			public void selectionChanged(SelectionChangedEvent event) {
				Object selection = ((IStructuredSelection)getDiagramfilteredTree().getViewer().getSelection()).getFirstElement();
				refresh(selection);
			}
		});

		// add listener for the button ok, keep the selected diagram
		getOKbutton().addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				getLookforShell().close();
			}

			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});

		// add listener for the button cancel, remove the value of the selected
		// diagram
		getCancelbutton().addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				setSelectedEditor(null);
				getLookforShell().close();
			}

			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});

	}

	/**
	 * Open the shell
	 */
	public void open() {
		Display display = Display.getCurrent();
		getLookforShell().pack();
		//		getLookforShell().setSize(700, 500);

		// code use to wait for an action from the user
		getLookforShell().pack();
		//		getLookforShell().setBounds(500, 500, 500, 300);
		getLookforShell().open();
		while(!getLookforShell().isDisposed()) {
			if(!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	protected void refresh(Object selectedElement) {
		selectedElement = EMFHelper.getEObject(selectedElement);
		Button but = getOKbutton();
		if(isAValidEditor(selectedElement)) {
			but = getOKbutton();
			but.setEnabled(true);
			selectedEditor = selectedElement;
		} else {
			but.setEnabled(false);
			selectedEditor = null;
		}
	}

	protected boolean isAValidEditor(final Object object) {
		Collection<AbstractHyperLinkEditorHelper> helpers = HyperLinkEditorHelpersRegistrationUtil.INSTANCE.getAllRegisteredHyperLinkEditorHelper();
		for(AbstractHyperLinkEditorHelper current : helpers) {
			HyperLinkEditor editor = current.getHyperLinkObjectFor(object);
			if(editor != null) {
				return true;
			}
		}
		return false;
	}

	//TODO delete this class to remove the PapyrusTable dependencies!
	private class LocalLabelProvider extends DecoratingLabelProviderWTooltips {

		public LocalLabelProvider() {
			super(new MoDiscoLabelProvider());
		}

		/**
		 * Return the EditorRegistry for nested editor descriptors. Subclass should
		 * implements this method in order to return the registry associated to the
		 * extension point namespace.
		 * 
		 * @return the EditorRegistry for nested editor descriptors
		 *         FIXME : use a deprecated method
		 */
		protected IPageIconsRegistry createEditorRegistry() {
			try {
				return EditorUtils.getServiceRegistry().getService(IPageIconsRegistry.class);
			} catch (ServiceException e) {
				// Not found, return an empty one which return null for each
				// request.
				return new PageIconsRegistry();
			}
		}

		/**
		 * the icon registry
		 */
		private IPageIconsRegistry editorRegistry;

		/**
		 * Get the EditorRegistry used to create editor instances. This default
		 * implementation return the singleton eINSTANCE. This method can be
		 * subclassed to return another registry.
		 * 
		 * @return the singleton eINSTANCE of editor registry
		 */
		protected IPageIconsRegistryExtended getEditorRegistry() {
			if(editorRegistry == null) {
				editorRegistry = createEditorRegistry();
			}
			if(!(editorRegistry instanceof IPageIconsRegistryExtended)) {
				throw new RuntimeException("The editor registry do not implement IPageIconsRegistryExtended");////$NON-NLS-1$
			}
			return (IPageIconsRegistryExtended)editorRegistry;
		}

		@Override
		public String getText(Object element) {
			if(element instanceof PapyrusTableInstance) {
				return ((PapyrusTableInstance)element).getName();
			}
			return super.getText(element);
		}


		@Override
		public Image getImage(Object element) {
			if(element instanceof PapyrusTableInstance) {
				return getEditorRegistry().getEditorIcon(element);
			}
			return super.getImage(element);
		}
	}


}
