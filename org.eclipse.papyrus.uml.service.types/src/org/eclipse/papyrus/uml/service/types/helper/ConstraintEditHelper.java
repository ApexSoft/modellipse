/*****************************************************************************
 * Copyright (c) 2010-2011 CEA LIST.
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
package org.eclipse.papyrus.uml.service.types.helper;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.common.core.command.CompositeCommand;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.emf.type.core.commands.ConfigureElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.ConfigureRequest;
import org.eclipse.uml2.uml.Constraint;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.ValueSpecification;

/**
 * <pre>
 * 
 * Edit helper class for {@link Constraint}
 * 
 * Expected behavior:
 * - Initialize specification with a LiteralString
 * - Set new ValueSpecification as specification of the parent constraint
 * 
 * </pre>
 */
public class ConstraintEditHelper extends ElementEditHelper {

	{
		getDefaultContainmentFeatures().put(UMLPackage.eINSTANCE.getValueSpecification(), UMLPackage.eINSTANCE.getConstraint_Specification());
	}
	
	@Override
	protected ICommand getConfigureCommand(final ConfigureRequest req) {
		ICommand configureCommand =  new ConfigureElementCommand(req) {

			protected CommandResult doExecuteWithResult(IProgressMonitor progressMonitor, IAdaptable info) throws ExecutionException {

				Constraint element = (Constraint)req.getElementToConfigure();

				// Create constraint specification
				ValueSpecification spec = UMLFactory.eINSTANCE.createLiteralString();
				spec.setName("constraintSpec"); //$NON-NLS-1$

				element.setSpecification(spec);
				
				return CommandResult.newOKCommandResult(element);
			}
		};
		
		return CompositeCommand.compose(configureCommand, super.getConfigureCommand(req));
	}
}
