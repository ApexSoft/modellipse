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
 *  Tatiana Fesenko (CEA LIST) - Initial API and implementation
 *
 *****************************************************************************/
package org.eclipse.papyrus.uml.diagram.wizards.category;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.papyrus.infra.core.editor.BackboneException;
import org.eclipse.papyrus.infra.core.extension.commands.IModelCreationCommand;

/**
 * The Class DiagramCategoryDescriptor.
 */
public class DiagramCategoryDescriptor {

	/** The my id. */
	private String myId;

	/** The my label. */
	private String myLabel;

	/** The my description. */
	private String myDescription;

	/** The my file extension. */
	private String myFileExtension;

	/** The my icon. */
	private ImageDescriptor myIcon;
	
	/** The instance. */
	private IModelCreationCommand instance;
	
	/** The my creation command class. */
	protected Class<? extends IModelCreationCommand> myCreationCommandClass;

	/**
	 * Instantiates a new diagram category descriptor.
	 *
	 * @param id the id
	 * @param label the label
	 * @param creationCommandClass the creation command class
	 */
	public DiagramCategoryDescriptor(String id, String label, Class<? extends IModelCreationCommand> creationCommandClass) {
		myId = id;
		myLabel = label;
		myCreationCommandClass = creationCommandClass;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public String getId() {
		return myId;
	}

	/**
	 * Gets the label.
	 *
	 * @return the label
	 */
	public String getLabel() {
		return myLabel;
	}
	
	/**
	 * constructor.
	 *
	 * @return the creation command
	 * @throws BackboneException the backbone exception
	 */
	public IModelCreationCommand getCommand() throws BackboneException {
		if(instance == null)
			instance = createCommand();

		return instance;
	}

	/**
	 * Creates the command.
	 *
	 * @return the i model creation command
	 * @throws BackboneException the backbone exception
	 */
	private IModelCreationCommand createCommand() throws BackboneException {
		try {
			IModelCreationCommand command = myCreationCommandClass.newInstance();
			return command;
		} catch (SecurityException e) {
			// Lets propagate. This is an implementation problem that should be solved by
			// programmer.
			throw new RuntimeException(e);
		} catch (InstantiationException e) {
			// Lets propagate. This is an implementation problem that should be solved by
			// programmer.
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			// Lets propagate. This is an implementation problem that should be solved by
			// programmer.
			throw new RuntimeException(e);
		}
	}

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return myDescription;
	}

	/**
	 * Gets the icon.
	 *
	 * @return the icon
	 */
	public ImageDescriptor getIcon() {
		return myIcon;
	}

	/**
	 * Gets the file extension.
	 *
	 * @return the file extension
	 */
	public String getExtensionPrefix() {
		return myFileExtension;
	}

	/**
	 * Sets the description.
	 *
	 * @param description the new description
	 */
	public void setDescription(String description) {
		myDescription = description;
	}

	/**
	 * Sets the icon.
	 *
	 * @param icon the new icon
	 */
	public void setIcon(ImageDescriptor icon) {
		myIcon = icon;
	}
	
	/**
	 * Sets the file extension.
	 *
	 * @param fileExtension the new file extension
	 */
	public void setExtensionPrefix(String fileExtension) {
		myFileExtension = fileExtension;
	}


}