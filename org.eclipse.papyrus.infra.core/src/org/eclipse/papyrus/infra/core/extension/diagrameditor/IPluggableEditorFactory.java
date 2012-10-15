/*****************************************************************************
 * Copyright (c) 2008 CEA LIST.
 *
 *    
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Cedric Dumoulin  Cedric.dumoulin@lifl.fr - Initial API and implementation
 *
 *****************************************************************************/
package org.eclipse.papyrus.infra.core.extension.diagrameditor;

import org.eclipse.papyrus.infra.core.editorsfactory.IEditorFactory;
import org.eclipse.papyrus.infra.core.services.ServicesRegistry;

/**
 * This interface should be implemented by Editor Factories that can be declared
 * as Eclipse extension. It extends the {@link IEditorFactory} by adding methods
 * to initialize the factory with multieditor ServiceRegistry and associated
 * editor data.
 * 
 * @author C�dric Dumoulin
 * 
 */
public interface IPluggableEditorFactory extends IEditorFactory {

	/**
	 * Initialize the factory with useful Classes.
	 * 
	 * @param serviceRegistry
	 *        Service registry that will be provided to created editor.
	 * @param editorDescriptor
	 *        Descriptor containing data from the Eclipse Extension.
	 */
	public void init(ServicesRegistry serviceRegistry, EditorDescriptor editorDescriptor);
}
