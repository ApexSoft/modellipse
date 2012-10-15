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
 *	Amine EL KOUHEN (CEA LIST/LIFL) - Amine.El-Kouhen@lifl.fr 
 *****************************************************************************/
package org.eclipse.papyrus.uml.diagram.component.edit.policies;

import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.DuplicateElementsRequest;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.papyrus.uml.diagram.common.commands.DuplicateNamedElementCommand;
import org.eclipse.papyrus.uml.diagram.component.edit.commands.CommentCreateCommand;
import org.eclipse.papyrus.uml.diagram.component.edit.commands.ComponentCreateCommand;
import org.eclipse.papyrus.uml.diagram.component.edit.commands.ConstraintCreateCommand;
import org.eclipse.papyrus.uml.diagram.component.edit.commands.InterfaceCreateCommand;
import org.eclipse.papyrus.uml.diagram.component.edit.commands.PackageCreateCommand;
import org.eclipse.papyrus.uml.diagram.component.providers.UMLElementTypes;

// TODO: Auto-generated Javadoc
/**
 * The Class ComponentDiagramItemSemanticEditPolicy.
 * 
 * @generated
 */
public class ComponentDiagramItemSemanticEditPolicy extends UMLBaseItemSemanticEditPolicy {

	/**
	 * Instantiates a new component diagram item semantic edit policy.
	 * 
	 * @generated
	 */
	public ComponentDiagramItemSemanticEditPolicy() {
		super(UMLElementTypes.Package_1000);
	}

	/**
	 * Gets the creates the command.
	 * 
	 * @param req
	 *        the req
	 * @return the creates the command
	 * @generated
	 */
	protected Command getCreateCommand(CreateElementRequest req) {
		if(UMLElementTypes.Component_2002 == req.getElementType()) {
			return getGEFWrapper(new ComponentCreateCommand(req));
		}
		if(UMLElementTypes.Interface_2003 == req.getElementType()) {
			return getGEFWrapper(new InterfaceCreateCommand(req));
		}
		if(UMLElementTypes.Package_3200 == req.getElementType()) {
			return getGEFWrapper(new PackageCreateCommand(req));
		}
		if(UMLElementTypes.Comment_3201 == req.getElementType()) {
			return getGEFWrapper(new CommentCreateCommand(req));
		}
		if(UMLElementTypes.Constraint_3199 == req.getElementType()) {
			return getGEFWrapper(new ConstraintCreateCommand(req));
		}
		return super.getCreateCommand(req);
	}

	/**
	 * Gets the duplicate command.
	 * 
	 * @param req
	 *        the req
	 * @return the duplicate command
	 * @generated
	 */
	protected Command getDuplicateCommand(DuplicateElementsRequest req) {
		TransactionalEditingDomain editingDomain = ((IGraphicalEditPart)getHost()).getEditingDomain();
		Diagram currentDiagram = null;
		if(getHost() instanceof IGraphicalEditPart) {
			currentDiagram = ((IGraphicalEditPart)getHost()).getNotationView().getDiagram();
		}
		return getGEFWrapper(new DuplicateAnythingCommand(editingDomain, req, currentDiagram));
	}

	/**
	 * The Class DuplicateAnythingCommand.
	 * 
	 * @generated
	 */
	private static class DuplicateAnythingCommand extends DuplicateNamedElementCommand {

		/** The diagram. @generated */
		private Diagram diagram;

		/**
		 * Instantiates a new duplicate anything command.
		 * 
		 * @param editingDomain
		 *        the editing domain
		 * @param req
		 *        the req
		 * @param currentDiagram
		 *        the current diagram
		 * @generated
		 */
		public DuplicateAnythingCommand(TransactionalEditingDomain editingDomain, DuplicateElementsRequest req, Diagram currentDiagram) {
			super(editingDomain, req.getLabel(), req.getElementsToBeDuplicated(), req.getAllDuplicatedElementsMap(), currentDiagram);
			this.diagram = currentDiagram;
		}
	}

}
