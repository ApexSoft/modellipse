/*****************************************************************************
 * Copyright (c) 2011 CEA LIST.
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Camille Letavernier (CEA LIST) camille.letavernier@cea.fr - Initial API and implementation
 *****************************************************************************/
package org.eclipse.papyrus.uml.properties.databinding;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.UnexecutableCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.papyrus.infra.emf.appearance.commands.AddMaskManagedLabelDisplayCommand;
import org.eclipse.papyrus.infra.emf.appearance.commands.SetNameLabelIconCommand;
import org.eclipse.papyrus.infra.emf.appearance.commands.SetQualifiedNameDepthCommand;
import org.eclipse.papyrus.infra.emf.appearance.commands.SetShadowFigureCommand;
import org.eclipse.papyrus.infra.emf.appearance.helper.NameLabelIconHelper;
import org.eclipse.papyrus.infra.emf.appearance.helper.QualifiedNameHelper;
import org.eclipse.papyrus.infra.emf.appearance.helper.ShadowFigureHelper;
import org.eclipse.papyrus.infra.emf.utils.EMFHelper;
import org.eclipse.papyrus.infra.gmfdiag.common.editpolicies.IMaskManagedLabelEditPolicy;
import org.eclipse.papyrus.infra.tools.databinding.AggregatedObservable;
import org.eclipse.papyrus.uml.properties.Activator;
import org.eclipse.papyrus.uml.tools.databinding.AbstractUMLAggregatedObservableValue;
import org.eclipse.papyrus.uml.tools.databinding.CommandBasedObservableValue;
import org.eclipse.papyrus.uml.tools.utils.UMLUtil;
import org.eclipse.uml2.uml.Element;

/**
 * An IObservableValue for custom Papyrus properties.
 * This enables to edit a few preferences-based values.
 * 
 * @author Camille Letavernier
 */
public class ElementCustomizationObservableValue extends AbstractUMLAggregatedObservableValue implements CommandBasedObservableValue, AggregatedObservable {

	private EditPart sourceElement;

	private Property property;

	private TransactionalEditingDomain transactionalDomain;

	private View notationElement;

	private Element semanticElement;

	/**
	 * 
	 * Constructor.
	 * 
	 * @param sourceElement
	 *        The selected EditPart
	 * @param property
	 *        The Property to edit
	 */
	public ElementCustomizationObservableValue(EditPart sourceElement, Property property) {
		super(EMFHelper.resolveEditingDomain(sourceElement));
		this.sourceElement = sourceElement;
		this.property = property;
		semanticElement = UMLUtil.resolveUMLElement(sourceElement);
		notationElement = (View)sourceElement.getModel();
		if(domain instanceof TransactionalEditingDomain) {
			transactionalDomain = (TransactionalEditingDomain)domain;
		}
	}

	//TODO : The value is not correctly refreshed when someone else edits it
	//Some listeners need to be added
	public Object getValueType() {
		switch(property) {
		case LABEL_CUSTOMIZATION:
		case QUALIFIED_NAME:
			return Integer.class;
		case ELEMENT_ICON:
		case SHADOW:
			return Boolean.class;
		}

		return Object.class;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Object doGetValue() {
		switch(property) {
		case LABEL_CUSTOMIZATION:
			return getEditPolicy().getCurrentDisplayValue();
		case ELEMENT_ICON:
			return NameLabelIconHelper.showLabelIcon(notationElement);
		case SHADOW:
			return ShadowFigureHelper.getShadowFigureValue(notationElement);
		case QUALIFIED_NAME:
			return QualifiedNameHelper.getQualifiedNameDepth(notationElement);
		}

		return null;
	}

	/**
	 * 
	 * @return the {@link IMaskManagedLabelEditPolicy#MASK_MANAGED_LABEL_EDIT_POLICY} edit policy
	 */
	protected IMaskManagedLabelEditPolicy getEditPolicy() {
		return (IMaskManagedLabelEditPolicy)sourceElement.getEditPolicy(IMaskManagedLabelEditPolicy.MASK_MANAGED_LABEL_EDIT_POLICY);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void doSetValue(Object value) {
		Command command = getCommand(value);
		domain.getCommandStack().execute(command);
	}

	/**
	 * {@inheritDoc}
	 */
	public Command getCommand(Object value) {
		switch(property) {
		case ELEMENT_ICON:
			if(value instanceof Boolean) {
				return new SetNameLabelIconCommand(transactionalDomain, notationElement, (Boolean)value);
			} else {
				Activator.log.warn(value + " is not a valid value for ElementIcon ; need a Boolean"); //$NON-NLS-1$
			}
			break;
		case SHADOW:
			if(value instanceof Boolean) {
				return new SetShadowFigureCommand(transactionalDomain, notationElement, (Boolean)value);
			} else {
				Activator.log.warn(value + " is not a valid value for Shadow ; need a Boolean"); //$NON-NLS-1$
			}
			break;
		case QUALIFIED_NAME:
			if(value instanceof Integer) {
				return new SetQualifiedNameDepthCommand(transactionalDomain, notationElement, (Integer)value);
			} else {
				Activator.log.warn(value + " is not a valid value for QualifiedNameDepth ; need an Integer"); //$NON-NLS-1$
			}
			break;
		case LABEL_CUSTOMIZATION:
			if(value instanceof Integer) {
				return new AddMaskManagedLabelDisplayCommand(transactionalDomain, notationElement, (Integer)value);
			} else {
				Activator.log.warn(value + " is not a valid value for LabelCustomization ; need an Integer"); //$NON-NLS-1$
			}
			break;
		}

		return UnexecutableCommand.INSTANCE;
	}

	/**
	 * The list of valid properties for {@link ElementCustomizationObservableValue}
	 * 
	 * @author Camille Letavernier
	 * 
	 */
	public enum Property {
		/**
		 * A UML Property or Operation label customization
		 */
		LABEL_CUSTOMIZATION,

		/**
		 * Whether and how the element icon should be displayed
		 */
		ELEMENT_ICON,

		/**
		 * Whether the shadow should be displayed or not
		 */
		SHADOW,

		/**
		 * Whether and how the qualified name should be displayed
		 */
		QUALIFIED_NAME
	}

}
