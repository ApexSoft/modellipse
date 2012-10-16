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
 *  	Mathieu Velten (Atos Origin) mathieu.velten@atosorigin.com - remove linked messages too
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
import org.eclipse.papyrus.uml.diagram.common.helper.InteractionFragmentHelper;
import org.eclipse.uml2.uml.ExecutionSpecification;
import org.eclipse.uml2.uml.InteractionFragment;
import org.eclipse.uml2.uml.Lifeline;
import org.eclipse.uml2.uml.Message;
import org.eclipse.uml2.uml.MessageOccurrenceSpecification;
import org.eclipse.uml2.uml.MessageSort;
import org.eclipse.uml2.uml.OccurrenceSpecification;

/**
 * apex updated
 * 
 * Helper advice for all {@link ExecutionSpecification} elements.
 */
public class ExecutionSpecificationHelperAdvice extends AbstractEditHelperAdvice {

	/**
	 * <pre>
	 * Add a command to associated {@link OccurrenceSpecification} and {@link Message}.
	 * This command is only added if the start - finish referenced {@link OccurrenceSpecification} is not 
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

		ExecutionSpecification es = (ExecutionSpecification)request.getElementToDestroy();

		// Add start - finish referenced OccurrenceSpecification to the dependents list
		// if they are not used by another element.
		OccurrenceSpecification osStart = es.getStart();
		/* apex improved start */
		if((osStart != null) && (apexIsOnlyUsage(osStart, es))) {
			dependentsToDestroy.add(osStart);
		}
		/* apex improved end */
		/* apex replaced
		if((osStart != null) && (PapyrusEcoreUtils.isOnlyUsage(osStart, es))) {
			dependentsToDestroy.add(osStart);
		}
		*/

		OccurrenceSpecification osFinish = es.getFinish();
		/* apex improved start */
		if((osFinish != null) && (apexIsOnlyUsage(osFinish, es))) {
			dependentsToDestroy.add(osFinish);
		}
		/* apex improved end */
		/* apex replaced
		if((osFinish != null) && (PapyrusEcoreUtils.isOnlyUsage(osFinish, es))) {
			dependentsToDestroy.add(osFinish);
		}
		*/

		Set<Lifeline> coveredLifelines = new HashSet<Lifeline>(es.getCovereds());

		// find initiating MOS of a synch message
		InteractionFragment previousIft = InteractionFragmentHelper.findPreviousFragment(osStart, es.getOwner());
		while(previousIft != null) {
			// keep the first ift with the same lifelines, and check it
			if(coveredLifelines.equals(new HashSet<Lifeline>(previousIft.getCovereds()))) {
				if(previousIft instanceof MessageOccurrenceSpecification) {
					Message msg = ((MessageOccurrenceSpecification)previousIft).getMessage();
					if(msg != null && MessageSort.SYNCH_CALL_LITERAL.equals(msg.getMessageSort())) {
						dependentsToDestroy.add(previousIft);
					}
				}
				break;
			}
			previousIft = InteractionFragmentHelper.findPreviousFragment(previousIft, es.getOwner());
		}

		// find MOS between the start and finish
		
		/*
		InteractionFragment fragment = osStart;
		while(fragment != null && !fragment.equals(osFinish)) {
			// remove MOS if it have the same covered lifelines as the ES
			if(fragment instanceof MessageOccurrenceSpecification && coveredLifelines.equals(new HashSet<Lifeline>(fragment.getCovereds()))) {
				dependentsToDestroy.add(fragment);
			}

			fragment = InteractionFragmentHelper.findNextFragment(fragment, es.getOwner());
		}
		 */
		
		// return command to destroy dependents
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
		// Remove covered list
		if (usedObject instanceof InteractionFragment)
			crossReferences.removeAll(((InteractionFragment) usedObject).getCovereds());

		// If no referencer remains in the list, the known element is the only
		// usage.
		if(crossReferences.isEmpty()) {
			isUsed = true;
		}

		return isUsed;
	}
}
