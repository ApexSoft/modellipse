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
 * 
 * 		Yann Tanguy (CEA LIST) yann.tanguy@cea.fr - Initial API and implementation
 *
 *****************************************************************************/
package org.eclipse.papyrus.uml.service.types.helper.advice;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.emf.type.core.edithelper.AbstractEditHelperAdvice;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyDependentsRequest;
import org.eclipse.papyrus.infra.core.utils.PapyrusEcoreUtils;
import org.eclipse.uml2.uml.InteractionFragment;
import org.eclipse.uml2.uml.Message;
import org.eclipse.uml2.uml.MessageEnd;

/**
 * apex updated
 * 
 * Helper advice for all {@link Message} elements.
 */
public class MessageHelperAdvice extends AbstractEditHelperAdvice {

	/**
	 * <pre>
	 * Add a command to destroy {@link MessageEnd} referenced by the {@link Message} 
	 * to delete.
	 * This command is only added if the send - receive event referenced is not 
	 * referenced by another element.
	 * </pre>
	 * 
	 * @see org.eclipse.gmf.runtime.emf.type.core.edithelper.AbstractEditHelperAdvice#getBeforeDestroyDependentsCommand(org.eclipse.gmf.runtime.emf.type.core.requests.DestroyDependentsRequest)
	 * 
	 * @param request
	 *        the request
	 * @return the command to execute before the edit helper work is done
	 */
	@Override
	protected ICommand getBeforeDestroyDependentsCommand(DestroyDependentsRequest request) {

		List<EObject> dependentsToDestroy = new ArrayList<EObject>();

		Message message = (Message)request.getElementToDestroy();

		// Add send - receive referenced MessageEnd to the dependents list
		// if they are not used by another element.
		MessageEnd sendEvent = message.getSendEvent();
		/* apex improved start */
		if((sendEvent != null) && (apexIsOnlyUsage(sendEvent, message))) {
			dependentsToDestroy.add(sendEvent);
		}
		/* apex improved end */
		/* apex replaced
		if((sendEvent != null) && (PapyrusEcoreUtils.isOnlyUsage(sendEvent, message))) {
			dependentsToDestroy.add(sendEvent);
		}
		*/

		MessageEnd recvEvent = message.getReceiveEvent();
		/* apex improved start */
		if((recvEvent != null) && (apexIsOnlyUsage(recvEvent, message))) {
			dependentsToDestroy.add(recvEvent);
		}
		/* apex improved end */
		/* apex replaced
		if((recvEvent != null) && (PapyrusEcoreUtils.isOnlyUsage(recvEvent, message))) {
			dependentsToDestroy.add(recvEvent);
		}
		*/
		
		// return command to destroy dependents MessageEnd 
		if(!dependentsToDestroy.isEmpty()) {
			return request.getDestroyDependentsCommand(dependentsToDestroy);
		}

		return null;
	}
	
	/**
	 * Covered를 제외하여 onlyUsage를 계산
	 * @see PapyrusEcoreUtils#isOnlyUsage(EObject, EObject)
	 * 
	 * @param usedObject
	 * @param knownReferencer
	 * @return
	 */
	private boolean apexIsOnlyUsage(EObject usedObject, EObject knownReferencer) {
		boolean isUsed = false;
		EPackage mmPackage = usedObject.eClass().getEPackage();

		// Retrieve the list of elements referencing the usedObject.
		Set<EObject> crossReferences = new HashSet<EObject>();
		for(Setting setting : PapyrusEcoreUtils.getUsages(usedObject)) {
			EObject eObj = setting.getEObject();
			if(eObj.eClass().getEPackage().equals(mmPackage)) {
				crossReferences.add(eObj);
			}
		}

		// Remove the container of used object.
		crossReferences.remove(usedObject.eContainer());
		// Remove the knownReferencer from the list of references.
		crossReferences.remove(knownReferencer);
		/* apex added start */
		// Remove covered list
		if (usedObject instanceof InteractionFragment)
			crossReferences.removeAll(((InteractionFragment) usedObject).getCovereds());
		/* apex added end */

		// If no referencer remains in the list, the known element is the only
		// usage.
		if(crossReferences.isEmpty()) {
			isUsed = true;
		}

		return isUsed;
	}
}
