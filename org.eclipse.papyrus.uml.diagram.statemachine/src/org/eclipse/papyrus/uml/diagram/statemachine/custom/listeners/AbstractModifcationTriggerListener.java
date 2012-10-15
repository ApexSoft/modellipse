/*****************************************************************************
 * Copyright (c) 2011 Atos.
 *
 *    
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Atos - Initial API and implementation
 *   Arthur Daussy Bug 366026 - [ActivityDiagram] Refactoring in order to try respect Generation Gap Pattern 
 *
 *****************************************************************************/
package org.eclipse.papyrus.uml.diagram.statemachine.custom.listeners;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.TriggerListener;
import org.eclipse.gmf.runtime.common.core.command.CompositeCommand;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.papyrus.commands.wrappers.GMFtoEMFCommandWrapper;
import org.eclipse.papyrus.editor.PapyrusMultiDiagramEditor;
import org.eclipse.papyrus.uml.diagram.common.util.DiagramEditPartsUtil;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;


public abstract class AbstractModifcationTriggerListener extends TriggerListener {

	@Override
	protected Command trigger(TransactionalEditingDomain domain, Notification notification) {
		if(notification != null) {
			Object feature = notification.getFeature();
			if(feature instanceof EStructuralFeature) {
				EStructuralFeature eStructuralFeature = (EStructuralFeature)feature;
				if(isCorrectStructuralfeature(eStructuralFeature)) {
					CompositeCommand cc = getModificationCommand(notification);
					if(cc != null) {
						return new GMFtoEMFCommandWrapper(cc);
					}
				}
			}
		}
		return null;
	}


	/**
	 * Return true if the {@link EStructuralFeature} correspond to one which this trigger will handle
	 * 
	 * @param eStructuralFeature
	 * @return
	 */
	protected abstract boolean isCorrectStructuralfeature(EStructuralFeature eStructuralFeature);


	protected abstract CompositeCommand getModificationCommand(Notification notif);

	/**
	 * Return the main edipart which correspond to the {@link EObject} passed in argument
	 * 
	 * @param eObject
	 * @param rootEditPart
	 *        {@link IGraphicalEditPart} root from which the search will start
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected IGraphicalEditPart getChildByEObject(final EObject eObject, IGraphicalEditPart rootEditPart, boolean isEdge) {
		return DiagramEditPartsUtil.getChildByEObject(eObject, rootEditPart, isEdge);
	}

	/**
	 * get the edit part registry
	 * 
	 * @return
	 */
	protected DiagramEditPart getDiagramEditPart() {
		IWorkbench wb = PlatformUI.getWorkbench();
		IWorkbenchPage page = wb.getActiveWorkbenchWindow().getActivePage();
		IEditorPart editor = page.getActiveEditor();
		if(editor instanceof PapyrusMultiDiagramEditor) {
			PapyrusMultiDiagramEditor papyrusEditor = (PapyrusMultiDiagramEditor)editor;
			return papyrusEditor.getDiagramEditPart();
		}
		return null;
	}


}
