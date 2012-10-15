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
 *  Remi Schnekenburger (CEA LIST) remi.schnekenburger@cea.fr - Initial API and implementation
 *
 *****************************************************************************/
package org.eclipse.papyrus.infra.extendedtypes.types;

import org.eclipse.gmf.runtime.emf.type.core.AbstractElementTypeFactory;
import org.eclipse.gmf.runtime.emf.type.core.ISpecializationType;
import org.eclipse.gmf.runtime.emf.type.core.ISpecializationTypeDescriptor;
import org.eclipse.gmf.runtime.emf.type.core.SpecializationType;
import org.eclipse.gmf.runtime.emf.type.core.internal.impl.HintedTypeFactory;
import org.eclipse.papyrus.infra.extendedtypes.Activator;
import org.eclipse.papyrus.infra.extendedtypes.ExtendedElementTypeConfiguration;
import org.eclipse.papyrus.infra.extendedtypes.ExtendedSemanticTypeDescriptor;


/**
 * Factory for ExtendedHintedType elements
 */
public class ExtendedHintedTypeFactory extends AbstractElementTypeFactory {

	/**
	 * The hinted type kind. This string is specified in the XML 'kind'
	 * attribute of any element type that is a hinted type.
	 */
	public static final String HINTED_TYPE_KIND = "org.eclipse.gmf.runtime.emf.core.internal.util.IHintedType"; //$NON-NLS-1$

	/**
	 * The semantic hint parameter name.
	 */
	public static final String SEMANTIC_HINT_PARAM_NAME = "semanticHint"; //$NON-NLS-1$

	/** singleton instance of this factory */
	private static ExtendedHintedTypeFactory instance;

	/**
	 * Returns the singleton instance of this factory.
	 * 
	 * @return the singleton instance of this factory.
	 */
	public synchronized static ExtendedHintedTypeFactory getInstance() {
		if(instance == null) {
			instance = new ExtendedHintedTypeFactory();
		}
		return instance;
	}

	/**
	 * Private Constructor.
	 * 
	 */
	private ExtendedHintedTypeFactory() {

	}

	/**
	 * The extended hinted specialization type class.
	 */
	private static final class ExtendedHintedSpecializationType extends SpecializationType implements IExtendedHintedElementType {

		/** The semantic hint */
		private final String semanticHint;

		/** element type configuration */
		private final ExtendedElementTypeConfiguration configuration;

		/**
		 * Constructs a new hinted type.
		 * 
		 * @param descriptor
		 *        the specialization type descriptor
		 * @param semanticHint
		 *        the semantic hint
		 */
		public ExtendedHintedSpecializationType(ISpecializationTypeDescriptor descriptor, String semanticHint, ExtendedElementTypeConfiguration configuration) {
			super(descriptor);
			this.semanticHint = semanticHint;
			this.configuration = configuration;
		}

		/**
		 * Gets the semantic hint.
		 */
		public String getSemanticHint() {
			return semanticHint;
		}

		/**
		 * {@inheritDoc}
		 */
		public ExtendedElementTypeConfiguration getConfiguration() {
			return configuration;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("restriction")
	@Override
	public ISpecializationType createSpecializationType(ISpecializationTypeDescriptor descriptor) {

		String semanticHint = descriptor.getParamValue(SEMANTIC_HINT_PARAM_NAME);

		if(descriptor instanceof ExtendedSemanticTypeDescriptor) {
			ExtendedElementTypeConfiguration configuration = ((ExtendedSemanticTypeDescriptor)descriptor).getConfiguration();
			return new ExtendedHintedSpecializationType(descriptor, semanticHint, configuration);
		}

		// used default factory. Should never happen
		Activator.log.warn("The Extended type factory should never use HintedTypeFactory from GMF");
		return new HintedTypeFactory().createSpecializationType(descriptor);
	}


}
