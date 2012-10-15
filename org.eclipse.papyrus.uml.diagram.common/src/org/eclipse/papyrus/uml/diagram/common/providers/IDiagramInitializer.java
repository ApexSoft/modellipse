/*******************************************************************************
 * Copyright (c) 2008 Conselleria de Infraestructuras y Transporte,
 * Generalitat de la Comunitat Valenciana .
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors: Francisco Javier Cano MuÃ±oz (Prodevelop) - initial API implementation
 *
 ******************************************************************************/
package org.eclipse.papyrus.uml.diagram.common.providers;

import org.eclipse.gmf.runtime.notation.Diagram;

// TODO: Auto-generated Javadoc
/**
 * The Interface IDiagramInitializer.
 * 
 * @author <a href="mailto:fjcano@prodevelop.es">Francisco Javier Cano Muñoz</a>
 */
public interface IDiagramInitializer {

	/**
	 * Inits the <Diagram>.
	 * 
	 * @param diagram
	 *        the diagram
	 */
	void init(Diagram diagram);
}
