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
package org.eclipse.papyrus.infra.core.sasheditor.internal;

import org.eclipse.papyrus.infra.core.sasheditor.contentprovider.IAbstractPanelModel;
import org.eclipse.papyrus.infra.core.sasheditor.contentprovider.ISashPanelModel;
import org.eclipse.papyrus.infra.core.sasheditor.contentprovider.ISashWindowsContentProvider;
import org.eclipse.papyrus.infra.core.sasheditor.contentprovider.ITabFolderModel;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;

/**
 * Part used as root. This is the one with no parent and no model. This class is intended for local
 * use only.
 * 
 * @author dumoulin
 * 
 * @param T
 *        Common ancestor for the model provided for the sash windows by the application.
 *        This is the type used externally by the application. Sash implementation don't use this type,
 *        it just carry it to ask for the appropriate wrapper. Concrete implementation can specify
 *        a type.
 */
public class RootPart extends AbstractPart implements IPanelParent {

	/** The SWT container used as parent of all */
	private Composite container;

	/**
	 * The first SWT controled part;
	 */
	private AbstractPanelPart child;

	/** Raw model associated to this part. We store it because the PartModel do not provide it */
//	private Object rawModel;

	/**
	 * Constructor.
	 */
	public RootPart(SashWindowsContainer sashWindowsContainer) {
		super(sashWindowsContainer);
	}

	/**
	 * Create the SWT controls.
	 * This Root as no control. Children are created by the {@link #synchronize2(PartLists)} method.
	 */
	public void createPartControl(Composite parent) {
		this.container = parent;
	}

	/**
	 * Dispose this part and its children.
	 */
	public void disposeThisAndChildren() {
		if(child != null)
			child.disposeThisAndChildren();
		
		// Detach properties to help GC
		child = null;
	}

	/**
	 * Get control associated to this part.
	 */
	public Composite getControl() {
		return container;
	}


	/**
	 * Create the part for the specified child model.
	 * The controls are also builds.
	 * 
	 * TODO: delegate to sashContainer.
	 * 
	 * @param rootPart
	 * @param partModel
	 * @return
	 */
	private AbstractPanelPart createChildPart(Object rawModel) {

		// Create the child PartModel. Delegate creation to the root PartModel.
		IAbstractPanelModel model = getPartModel().createChildSashModel(rawModel);

		AbstractPanelPart createdPart;
		if(model instanceof ITabFolderModel) {
			createdPart = new TabFolderPart(this, (ITabFolderModel)model, rawModel);
		} else if(model instanceof ISashPanelModel) {
			createdPart = new SashPanelPart(this, (ISashPanelModel)model, rawModel);
		} else {
			// error
			throw new IllegalArgumentException("Can't create child part for model of type '"
					+ model.getClass().getName()
					+ "'");
			// TODO: Return an error Part showing the exception instead of throwing it ?
		}

		// Create controls
		createdPart.createPartControl(getControl());
		return createdPart;

	}

	/**
	 * Get the PartModel associated to this Part.
	 * 
	 * @return
	 */
	private ISashWindowsContentProvider getPartModel() {
		// The associated model for a root is the ContentProvider.
		return getSashWindowContainer().getContentProvider();
	}

	/**
	 * Synchronize the part and its children.
	 * 
	 * @param partMap
	 */
	public void synchronize2(PartLists partMap) {

		// Synchronize locally the child
		synchronizeChild(partMap);
		// Synchronize recursively subchilds.
		if(child != null)
			child.synchronize2(partMap);

	}

	/**
	 * Synchronize locally the child
	 * 
	 * @param partMap
	 */
	private void synchronizeChild(PartLists partMap) {

		// Get the new raw model
		Object rawModel = getContentProvider().getRootModel();

		// Check if old child exist
		// If exist, check if the current part is associated to the checked model
		// 
		if(child != null) {
			// If the tile is already for the model, there is nothing to do.
			if(child.isPartFor(rawModel)) {
				child.unchanged();
				return;
			}
			// The current tile is not for the model: mark it as orphan
			child.orphan();
		}

		// The child tile need to be updated. Do it.
		// First check if already exist in the map
		AbstractPanelPart newPart = partMap.findPartFor(rawModel);
		if(newPart != null) {
			// Reparent the tile
			newPart.reparent(this, getControl());
		} else {
			// create the tile and its control
			newPart = createChildPart(rawModel);
		}

		// Now, put the tile on the right side
		setChild(newPart);
	}

	/**
	 * Set the child. If a child already exist at the specified index, it is lost.
	 * 
	 * @param newTile
	 */
	private void setChild(AbstractPanelPart newTile) {
		child = newTile;
	}

	/**
	 * Fill partMap with the children.
	 * 
	 * @param partMap
	 */
	public void fillPartMap(PartLists partMap) {
		if(child != null)
			child.fillPartMap(partMap);

	}

	/**
	 * Find The AbstractPart under the specified position.
	 */
	public AbstractPart findPart(Point toFind) {
		if(child != null)
			try {
				return child.findPart(toFind);
			} catch (NotFoundException e) {
				System.err.println(e.getMessage());
				return null;
			}
		else
			return null;
	}

	//	/**
	//	 * Locates the part that intersects the given point and that have the expected type
	//	 * 
	//	 * @param toFind
	//	 * @return
	//	 */
	//	public AbstractPart findPartAt(Point toFind, Class<?> tileType) {
	//		return child.findPartAt(toFind, tileType);
	//	}

	/**
	 * 
	 */
	public AbstractPart findPart(Object control) {
		if(child != null)
			return child.findPart(control);
		else
			return null;
	}

	//	/**
	//	 * @see org.eclipse.papyrus.infra.core.sasheditor.sash.ITilePart#getDropTarget(java.lang.Object, org.eclipse.papyrus.infra.core.sasheditor.sash.TabFolderPart, org.eclipse.swt.graphics.Point)
	//	 */
	//	public IDropTarget getDropTarget(Object draggedObject, TabFolderPart sourcePart, Point position) {
	//		return child.getDropTarget(draggedObject, sourcePart, position);
	//	}

	/**
	 * Do nothing. This node can't be orphaned
	 * 
	 */
	public void orphan() {
		// Do nothing. This node can't be orphaned
	}

	/**
	 * Always return false. This Part can't be orphaned.
	 * 
	 * @see org.eclipse.papyrus.infra.core.sasheditor.internal.AbstractPart#isOrphaned()
	 * 
	 * @return
	 */
	public boolean isOrphaned() {
		return false;
	}

	/**
	 * Get the Garbage state.
	 * 
	 * @see org.eclipse.papyrus.infra.core.sasheditor.internal.AbstractPart#getGarbageState()
	 * 
	 * @return
	 */
	public GarbageState getGarbageState() {
		return GarbageState.UNVISITED;
	}

	/**
	 * Accept the provided visitor.
	 * Call the corresponding accept method in the visitor.
	 * 
	 * @param visitor
	 * @return
	 */
	public boolean visit(IPartVisitor visitor) {
		return visitor.accept(this);
	}

	/**
	 * Visit the children of this Tile.
	 * 
	 * @param visitor
	 */
	public boolean visitChildren(IPartVisitor visitor) {
		if(child != null)
			return child.visit(visitor);

		// Return the default value
		return true;
	}

	/**
	 * Show tile status.
	 */
	protected void showStatus() {
		org.eclipse.papyrus.infra.core.sasheditor.Activator.log.debug("rootPart (1)"
				+ ", disposed=" + container.isDisposed()
				+ ", visible=" + container.isVisible()
				+ ", " + this);
	}


}
