/*****************************************************************************
 * Copyright (c) 2011 CEA LIST.
 *
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Vincent Lorenzo (CEA LIST) vincent.lorenzo@cea.fr - Initial API and implementation
 *
 *****************************************************************************/
package org.eclipse.papyrus.infra.hyperlink.util;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.papyrus.infra.core.sasheditor.contentprovider.IPageMngr;
import org.eclipse.papyrus.infra.core.utils.EditorUtils;
import org.eclipse.papyrus.infra.hyperlink.Activator;


/**
 * The Class DiagramContentProvider.
 */
//TODO why a tree content provider
//TODO : extends the label provider of the ModelExplorer to get the customization
public class EditorListContentProvider implements ITreeContentProvider {

	/**
	 * {@inheritDoc}
	 */
	public Object[] getChildren(Object parentElement) {
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	public Object getParent(Object element) {
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean hasChildren(Object element) {
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	public Object[] getElements(Object inputElement) {
		try {
			IPageMngr iPageMngr = EditorUtils.getIPageMngr();
			Object[] result = iPageMngr.allPages().toArray();

			List<Object> res = new ArrayList<Object>();
			for(Object current : result) {
				if(current != null /* && current instanceof PapyrusTableInstance */) { 
//					 if the model is a little bit corrupted, we can have a null element in the list 
					 res.add(current);
				}
			}
			return res.toArray();

		} catch (Exception e) {
			Activator.log.error(e);
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	public void dispose() {
		//nothing to do
	}

	/**
	 * {@inheritDoc}
	 */
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		//nothing to do
	}
}
