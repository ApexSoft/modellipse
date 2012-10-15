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
 * 
 * 		Yann Tanguy (CEA LIST) yann.tanguy@cea.fr - Initial API and implementation
 *
 *****************************************************************************/
package org.eclipse.papyrus.uml.service.types.command;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.emf.type.core.commands.EditElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.ReorientRelationshipRequest;
import org.eclipse.uml2.uml.Dependency;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Package;

/**
 * <pre>
 * Re-orient command for {@link Dependency} elements.
 * </pre>
 */
public class DependencyReorientCommand extends EditElementCommand {

	protected final int reorientDirection;

	protected final EObject oldEnd;

	protected final EObject newEnd;

	/**
	 * <pre>
	 * Constructor.
	 * 
	 * @param request the re-orient relationship request.
	 * </pre>
	 */
	public DependencyReorientCommand(ReorientRelationshipRequest request) {
		super(request.getLabel(), request.getRelationship(), request);
		reorientDirection = request.getDirection();
		oldEnd = request.getOldRelationshipEnd();
		newEnd = request.getNewRelationshipEnd();
	}

	/**
	 * <pre>
	 * @see org.eclipse.gmf.runtime.emf.type.core.commands.EditElementCommand#canExecute()
	 * 
	 * @return true if the command is executable.
	 * </pre>
	 */
	public boolean canExecute() {
		if(false == getElementToEdit() instanceof Dependency) {
			return false;
		}
		if(reorientDirection == ReorientRelationshipRequest.REORIENT_SOURCE) {
			return canReorientSource();
		}
		if(reorientDirection == ReorientRelationshipRequest.REORIENT_TARGET) {
			return canReorientTarget();
		}
		return false;
	}

	protected boolean canReorientSource() {
		if(!(oldEnd instanceof NamedElement && newEnd instanceof NamedElement)) {
			return false;
		}
		if(!(getLink().eContainer() instanceof Package)) {
			return false;
		}
		return true;
	}

	protected boolean canReorientTarget() {
		if(!(oldEnd instanceof NamedElement && newEnd instanceof NamedElement)) {
			return false;
		}
		if(!(getLink().eContainer() instanceof Package)) {
			return false;
		}
		return true;
	}

	protected CommandResult doExecuteWithResult(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
		if(!canExecute()) {
			throw new ExecutionException("Invalid arguments in reorient link command"); //$NON-NLS-1$
		}
		if(reorientDirection == ReorientRelationshipRequest.REORIENT_SOURCE) {
			return reorientSource();
		}
		if(reorientDirection == ReorientRelationshipRequest.REORIENT_TARGET) {
			return reorientTarget();
		}
		throw new IllegalStateException();
	}

	protected CommandResult reorientSource() throws ExecutionException {
		getLink().getClients().remove(getOldSource());
		getLink().getClients().add(getNewSource());
		return CommandResult.newOKCommandResult(getLink());
	}

	protected CommandResult reorientTarget() throws ExecutionException {
		getLink().getSuppliers().remove(getOldTarget());
		getLink().getSuppliers().add(getNewTarget());
		return CommandResult.newOKCommandResult(getLink());
	}

	protected Dependency getLink() {
		return (Dependency)getElementToEdit();
	}

	protected NamedElement getOldSource() {
		return (NamedElement)oldEnd;
	}

	protected NamedElement getNewSource() {
		return (NamedElement)newEnd;
	}

	protected NamedElement getOldTarget() {
		return (NamedElement)oldEnd;
	}

	protected NamedElement getNewTarget() {
		return (NamedElement)newEnd;
	}
}
