/*****************************************************************************
 * Copyright (c) 2012 CEA LIST.
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Camille Letavernier (CEA LIST) camille.letavernier@cea.fr - Initial API and implementation
 *****************************************************************************/
package org.eclipse.papyrus.infra.emf.appearance.style;

import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.papyrus.infra.emf.appearance.commands.SetNameLabelIconCommand;
import org.eclipse.papyrus.infra.emf.appearance.commands.SetQualifiedNameDepthCommand;
import org.eclipse.papyrus.infra.emf.appearance.commands.SetShadowFigureCommand;
import org.eclipse.papyrus.infra.emf.appearance.helper.VisualInformationPapyrusConstants;

/**
 * Basic implementation of AppearanceStyleProvider, based on EMF EAnnotations
 * 
 * @author Camille Letavernier
 */
//Implementation note: the CSS rely on these EAnnotations to determine 
//whether the CSS appearance should be enabled or not. If the EAnnotations
//are removed, the implementation of CSSApperanceProvider and ResetStyleHandler
//should be changed accordingly (e.g. if EAnnotations are replaced by GMF
//NamedStyles, see
//Bug 321305 - https://bugs.eclipse.org/bugs/show_bug.cgi?id=321305 )
public class AnnotationStyleProvider implements AppearanceStyleProvider {

	public boolean showElementIcon(EModelElement modelElement) {
		EAnnotation displayNameLabelIcon = modelElement.getEAnnotation(VisualInformationPapyrusConstants.DISPLAY_NAMELABELICON);
		if(displayNameLabelIcon != null) {
			EMap<String, String> entries = displayNameLabelIcon.getDetails();
			if(entries != null) {
				String gradientvalueString = entries.get(VisualInformationPapyrusConstants.DISPLAY_NAMELABELICON_VALUE);
				if(gradientvalueString != null) {
					return new Boolean(gradientvalueString);
				}
			}
		}
		return false;
	}

	//TODO: Improve this method. The annotation should be able to store either a String (FULL, NONE) or an Integer.
	//The method should parse this string to the corresponding integer when called.
	//Check whether this is compatible with the property view edition (Which already manipulates and parses "FULL" and "NONE")
	public int getQualifiedNameDepth(EModelElement modelElement) {
		EAnnotation qualifiedNameAnnotation = modelElement.getEAnnotation(VisualInformationPapyrusConstants.QUALIFIED_NAME);
		if(qualifiedNameAnnotation != null) {
			EMap<String, String> entries = qualifiedNameAnnotation.getDetails();

			if(entries != null) {
				String depthString = entries.get(VisualInformationPapyrusConstants.QUALIFIED_NAME_DEPTH);
				if(depthString != null) {
					return new Integer(depthString);
				}
			}
		}

		return 1000;
	}

	public boolean showShadow(EModelElement modelElement) {
		EAnnotation shadowAnnotation = modelElement.getEAnnotation(VisualInformationPapyrusConstants.SHADOWFIGURE);
		if(shadowAnnotation != null) {
			EMap<String, String> entries = shadowAnnotation.getDetails();

			if(entries != null) {
				String gradientvalueString = entries.get(VisualInformationPapyrusConstants.SHADOWFIGURE_VALUE);
				if(gradientvalueString != null) {
					return new Boolean(gradientvalueString);
				}
			}
		}

		return false;
	}

	/**
	 * Gets the command to set the gradient to true are false.
	 * 
	 * @param domain
	 *        the domain
	 * @param view
	 *        the view
	 * @param nameLabelIconValue
	 *        true to display the icon of the element in labelName
	 * 
	 * @return the command to set the elementIcon to true are false.
	 */
	public static RecordingCommand getSetElementIconCommand(TransactionalEditingDomain domain, EModelElement view, boolean nameLabelIconValue) {
		return new SetNameLabelIconCommand(domain, view, nameLabelIconValue);
	}

	/**
	 * Gets the sets the qualifed name depth command.
	 * 
	 * @param domain
	 *        the domain
	 * @param view
	 *        the view
	 * @param depth
	 *        the depth
	 * 
	 * @return the set qualified name depth command
	 */
	public static RecordingCommand getSetQualifiedNameDepthCommand(TransactionalEditingDomain domain, EModelElement view, int depth) {
		return new SetQualifiedNameDepthCommand(domain, view, depth);
	}

	/**
	 * Gets the command to set the shadow figure to true are false.
	 * 
	 * @param domain
	 *        the domain
	 * @param view
	 *        the view
	 * @param shadowFigureValue
	 *        true to display the shadow on the figure
	 * 
	 * @return the command to set the shadow to true or false.
	 */
	public static RecordingCommand getSetShadowCommand(TransactionalEditingDomain domain, EModelElement view, boolean shadowFigureValue) {
		return new SetShadowFigureCommand(domain, view, shadowFigureValue);
	}

}
