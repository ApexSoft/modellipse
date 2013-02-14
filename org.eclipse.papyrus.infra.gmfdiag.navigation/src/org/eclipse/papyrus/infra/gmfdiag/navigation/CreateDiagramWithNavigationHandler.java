/*****************************************************************************
 * Copyright (c) 2010 Atos Origin.
 *
 *    
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Mathieu Velten (Atos Origin) mathieu.velten@atosorigin.com - Initial API and implementation
 *
 *****************************************************************************/
package org.eclipse.papyrus.infra.gmfdiag.navigation;

import java.util.Collections;
import java.util.List;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.common.core.command.CompositeCommand;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.papyrus.commands.wrappers.GMFtoEMFCommandWrapper;
import org.eclipse.papyrus.infra.core.extension.commands.ICreationCommand;
import org.eclipse.papyrus.infra.core.extension.commands.ICreationCondition;
import org.eclipse.papyrus.infra.core.services.ServiceException;
import org.eclipse.papyrus.infra.core.utils.BusinessModelResolver;
import org.eclipse.papyrus.infra.core.utils.DiResourceSet;
import org.eclipse.papyrus.infra.core.utils.ServiceUtilsForActionHandlers;
import org.eclipse.papyrus.infra.widgets.toolbox.dialog.InformationDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;

/**
 * This command handler will try to create a diagram on the currently selected
 * element, using navigation if necessary. The action is always available and
 * the check is done in the run to avoid heavy navigation computation on each
 * selection change.
 * 
 * @author mvelten
 * 
 */
public abstract class CreateDiagramWithNavigationHandler extends AbstractHandler {

	private ICreationCondition creationCondition;

	private ICreationCommand creationCommand;

	public CreateDiagramWithNavigationHandler(ICreationCommand creationCommand, ICreationCondition creationCondition) {
		super();
		this.creationCommand = creationCommand;
		this.creationCondition = creationCondition;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public Object execute(ExecutionEvent event) throws ExecutionException {
		NavigableElement navElement = getNavigableElementWhereToCreateDiagram();

		if(navElement == null) {
			InformationDialog dialog = new InformationDialog(Display.getCurrent().getActiveShell(), "Impossible diagram creation", "It is not possible to create this diagram on the selected element.", null, null, SWT.OK, MessageDialog.WARNING, new String[]{ IDialogConstants.OK_LABEL });
			dialog.open();
		} else {
			createDiagram(navElement);
		}
		return null;
	}

	private NavigableElement getNavigableElementWhereToCreateDiagram() {
		EObject selectedElement = getSelectedElement();

		if(selectedElement != null) {
			// First check if the current element can host the requested diagram
			if(creationCondition.create(selectedElement)) {
				return new ExistingNavigableElement(selectedElement, null);
			} else {
				List<NavigableElement> navElements = NavigationHelper.getInstance().getAllNavigableElements(selectedElement);
				// this will sort elements by navigation depth
				Collections.sort(navElements);

				for(NavigableElement navElement : navElements) {
					// ignore existing elements because we want a hierarchy to
					// be created if it is not on the current element
					if(navElement instanceof CreatedNavigableElement && creationCondition.create(navElement.getElement())) {
						return navElement;
					}
				}
			}
		}
		return null;
	}

	/**
	 * apex updated
	 * 
	 * diResourceSet 을 deprecated method를 사용하지 않고 제대로 가져오도록 수정
	 * 
	 * @param navElement
	 */
	private void createDiagram(NavigableElement navElement) {
		/* apex improved start */
		DiResourceSet diResourceSet = null;
		try {
			diResourceSet = ServiceUtilsForActionHandlers.getInstance().getServiceRegistry().getService(DiResourceSet.class);
		} catch (ServiceException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		/* apex improved end */
		/* apex replaced
		DiResourceSet diResourceSet = EditorUtils.getDiResourceSet();
		*/

		if(navElement != null && diResourceSet != null) {
			try {
				CompositeCommand command = NavigationHelper.getLinkCreateAndOpenNavigableDiagramCommand(navElement, creationCommand, null, diResourceSet);
				diResourceSet.getTransactionalEditingDomain().getCommandStack().execute(new GMFtoEMFCommandWrapper(command));
			} catch (Exception e) {
			}
		}
	}

	private EObject getSelectedElement() {
		ISelection selection = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getSelectionService().getSelection();
		if(selection instanceof IStructuredSelection) {
			Object obj = ((IStructuredSelection)selection).getFirstElement();
			return resolveSemanticObject(obj);
		}
		return null;
	}

	/**
	 * Resolve semantic element
	 * 
	 * @param object
	 *        the object to resolve
	 * @return <code>null</code> or the semantic element associated to the
	 *         specified object
	 */
	protected EObject resolveSemanticObject(Object object) {
		if(object instanceof EObject) {
			return (EObject)object;
		}
		Object businessObject = BusinessModelResolver.getInstance().getBusinessModel(object);
		if(businessObject instanceof EObject) {
			return (EObject)businessObject;
		}
		return null;
	}

}
