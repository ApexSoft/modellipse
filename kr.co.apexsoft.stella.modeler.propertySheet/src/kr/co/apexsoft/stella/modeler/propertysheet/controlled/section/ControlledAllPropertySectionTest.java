/******************************************************************************
 * Copyright (c) 2005, 2010 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    IBM Corporation - initial API and implementation
 ****************************************************************************/
package kr.co.apexsoft.stella.modeler.propertysheet.controlled.section;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.URI;
import org.eclipse.gmf.runtime.diagram.ui.properties.sections.AbstractModelerPropertySection;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertyConstants;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;


public class ControlledAllPropertySectionTest extends AbstractModelerPropertySection {

	private final String LABEL_PATH = "Path";
	
	private final String LABEL_TYPE = "Type";
	
	private final String LABEL_SIZE = "Size";
	
	private final String LABEL_TIME = "Last Modified";
	
	// label widget for the property name
	private CLabel pathLabelWidget;
	
	// label widget for the property value
	private CLabel pathValueLabelWidget;
	
	// label widget for the property name
	private CLabel typeLabelWidget;
	
	// label widget for the property value
	private CLabel typeValueLabelWidget;
	
	// label widget for the property name
	private CLabel sizeLabelWidget;
	
	// label widget for the property value
	private CLabel sizeValueLabelWidget;
	
	// label widget for the property name
	private CLabel timeLabelWidget;
	
	// label widget for the property value
	private CLabel timeValueLabelWidget;
	
	private Composite sectionComposite;
	
	private List<Object> eObjectList;
	
	public ControlledAllPropertySectionTest() {
		// TODO Auto-generated constructor stub
	}	
	
	@Override
	public void createControls(Composite parent,
			TabbedPropertySheetPage aTabbedPropertySheetPage) {
		doCreateControls(parent, aTabbedPropertySheetPage);
	}
	
	/**
	 * Creates the GUI <code>Control</code> for this text property section
	 * @param parent parent <code>Composite</code>
	 * @param aTabbedPropertySheetPage <code>TabbedPropertySheetPage</code>
	 * @see org.eclipse.gmf.runtime.common.ui.properties.ISection#createControls(org.eclipse.swt.widgets.Composite,
	 *      org.eclipse.gmf.runtime.common.ui.properties.TabbedPropertySheetPage)
	 */
	private void doCreateControls(Composite parent,
			TabbedPropertySheetPage aTabbedPropertySheetPage) {
		super.createControls(parent, aTabbedPropertySheetPage);

		sectionComposite = getWidgetFactory().createFlatFormComposite(parent);
		pathLabelWidget = createLabelWidget(sectionComposite, 0, getLABEL_PATH(), true);
		pathValueLabelWidget = createLabelWidget(sectionComposite, 0, "", false);
		typeLabelWidget = createLabelWidget(sectionComposite, 1, getLABEL_TYPE(), true);
		typeValueLabelWidget = createLabelWidget(sectionComposite, 1, "", false);
		sizeLabelWidget = createLabelWidget(sectionComposite, 2, getLABEL_SIZE(), true);
		sizeValueLabelWidget = createLabelWidget(sectionComposite, 2, "", false);
		timeLabelWidget = createLabelWidget(sectionComposite, 3, getLABEL_TIME(), true);
		timeValueLabelWidget = createLabelWidget(sectionComposite, 3, "", false);
	}
	
	/**
	 * Create a label for property name
	 * 
	 * @param parent -
	 *            parent composite
	 * @return - label to show property name
	 */
	private CLabel createLabelWidget(Composite parent, int order, String label, boolean isName) {
		CLabel cLabel = getWidgetFactory().createCLabel(parent, label);
		FormData data = new FormData();
		int rightRatio = 15;
		
		if (isName) {
			data.left = new FormAttachment(0, 0);
			data.right = new FormAttachment(rightRatio,-ITabbedPropertyConstants.HSPACE);
				
		} else {
			data.left = new FormAttachment(rightRatio, 0);
			data.right = new FormAttachment(100,-ITabbedPropertyConstants.HSPACE);
		}
		data.top = new FormAttachment(0, order*2);
		
		cLabel.setLayoutData(data);
		return cLabel;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.ui.views.properties.tabbed.ISection#refresh()
	 */
	@Override
	public void refresh() {
		executeAsReadAction(new Runnable() {

			public void run() {
				refreshUI();
			}
		});
	}

	/**
	 * Refresh UI body - referesh will surround this with read action block
	 */
	protected void refreshUI() {
		pathValueLabelWidget.setText(getPath());
		typeValueLabelWidget.setText(getType());
		sizeValueLabelWidget.setText(getSize());
		timeValueLabelWidget.setText(getTime());
	}
	
	private String getPath() {		
		String resourcePath = ResourcesPlugin.getWorkspace().getRoot().getLocation().toString();
		String platformRelativePath = getEObject().eResource().getURI().toPlatformString(true);		
		
		return resourcePath.concat(platformRelativePath);
	}
	
	private String getType() {		
		return getEObject().eResource().getURI().fileExtension() + " File";
	}
	
	private String getSize() {		
		long fileSize = 0L;
		URI uri = getEObject().eResource().getURI();
		String platformRelativePath = uri.toPlatformString(true);
		
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		IResource member = root.findMember(platformRelativePath);
		
		if ( member instanceof IFile ) {

			IFile iFile = (IFile)member;
			java.net.URI netUri = iFile.getLocationURI();
			
			try {
				IFileStore fileStore = org.eclipse.core.filesystem.EFS.getStore(netUri);
				fileSize = fileStore.fetchInfo().getLength();
			} catch (CoreException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return fileSize + " bytes";
	}
	
	private String getTime() {
		URI uri = getEObject().eResource().getURI();
		String platformRelativePath = uri.toPlatformString(true);
		
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		IResource file = root.findMember(platformRelativePath);		
		
		long timeStamp = file.getLocalTimeStamp();
		Date dateValue = new Date(timeStamp);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd', 'a hh:mm:ss, zzzz");
		String lastModified = dateFormat.format(dateValue);
		
		return lastModified;
	}

	private String getLABEL_PATH() {
		return LABEL_PATH;
	}

	private String getLABEL_TYPE() {
		return LABEL_TYPE;
	}

	private String getLABEL_SIZE() {
		return LABEL_SIZE;
	}

	private String getLABEL_TIME() {
		return LABEL_TIME;
	}


}
