/*****************************************************************************
 * Copyright (c) 2010 ATOS ORIGIN.
 *    
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Tristan Faure (ATOS ORIGIN INTEGRATION) tristan.faure@atosorigin.com - Initial API and implementation
 *****************************************************************************/
package org.eclipse.papyrus.infra.widgets.toolbox.notification.builders;

import org.eclipse.papyrus.infra.widgets.toolbox.notification.IBuilder;
import org.eclipse.papyrus.infra.widgets.toolbox.notification.ICompositeCreator;
import org.eclipse.papyrus.infra.widgets.toolbox.notification.INotification;
import org.eclipse.papyrus.infra.widgets.toolbox.notification.Type;
import org.eclipse.papyrus.infra.widgets.toolbox.notification.utils.PapyrusControlsFactory;
import org.eclipse.papyrus.infra.widgets.toolbox.notification.view.AbstractInsideComposite;
import org.eclipse.papyrus.infra.widgets.toolbox.notification.view.PapyrusNotificationView;
import org.eclipse.papyrus.infra.widgets.toolbox.notification.view.ViewNotification;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IViewReference;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.forms.widgets.FormToolkit;


/**
 * A Builder able to create {@link PapyrusNotificationView} instances
 * 
 * @author tristan faure
 * 
 */
public class ViewBuilder implements IBuilder {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.papyrus.infra.widgets.toolbox.notification.IBuilder#build(org.eclipse.papyrus.infra.widgets.toolbox.notification.PropertyWrapper,
	 * org.eclipse.ui.forms.widgets.FormToolkit)
	 */
	public INotification build(PropertyWrapper wrapper, FormToolkit toolkit) {
		try {
			IViewPart part = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().showView(PapyrusNotificationView.ID);
			if(part instanceof PapyrusNotificationView) {
				PapyrusNotificationView view = (PapyrusNotificationView)part;
				AbstractInsideComposite viewCompo = null;
				if(wrapper.getComposite() != null) {
					viewCompo = view.setComposite(wrapper.getComposite(), wrapper.getTitle(), wrapper.getActions(), wrapper.getType());
				} else {
					if(wrapper.getType() != null || wrapper.getImage() != null || wrapper.isHtml()) {
						final Image image = wrapper.getImage();
						final Type type = wrapper.getType();
						final boolean isHtml = wrapper.isHtml();
						final String message = wrapper.getMessage();
						viewCompo = view.setComposite(new ICompositeCreator() {

							public Composite createComposite(Composite parent, FormToolkit toolkit) {
								Composite created = PapyrusControlsFactory.createCompositeWithType(Display.getDefault().getActiveShell(), toolkit, parent, type, image, message, isHtml);
								return created;
							}
						}, wrapper.getTitle(), wrapper.getActions());
					} else {
						if(wrapper.getMessage() != null) {
							viewCompo = view.setMessage(wrapper.getMessage(), wrapper.getTitle(), wrapper.getActions());
						}
					}
				}
				ViewNotification notification = new ViewNotification(viewCompo);
				viewCompo.setINotification(notification);
				return notification ;
			}
		} catch (PartInitException e) {
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.papyrus.infra.widgets.toolbox.notification.IBuilder#accept(java.lang.String, java.lang.Object)
	 */
	public boolean accept(String parameterName, Object value) {
		boolean found = false;
		for(IViewReference ref : PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getViewReferences()) {
			if(PapyrusNotificationView.ID.equals(ref.getId())) {
				found = true;
			}
		}
		if(found) {
			if(NotificationBuilder.ASYNCHRONOUS.equals(parameterName)) {
				return value instanceof Boolean && (Boolean)value;
			}
			if(NotificationBuilder.TEMPORARY.equals(parameterName)) {
				return value instanceof Boolean && !(Boolean)value;
			}
			if(NotificationBuilder.DELAY.equals(parameterName)) {
				return false;
			}
			if(NotificationBuilder.MESSAGE.equals(parameterName)) {
				return true;
			}
			if(NotificationBuilder.COMPOSITE.equals(parameterName)) {
				return true;
			}
			if(NotificationBuilder.ACTION.equals(parameterName)) {
				return true;
			}
			if(NotificationBuilder.TYPE.equals(parameterName)) {
				return true;
			}
			if(NotificationBuilder.TITLE.equals(parameterName)) {
				return true;
			}
			if(NotificationBuilder.HTML.equals(parameterName)) {
				return true;
			}
		}
		return false;
	}
}
