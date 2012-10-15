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
package org.eclipse.papyrus.infra.widgets.editors;

import java.io.File;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.window.Window;
import org.eclipse.papyrus.infra.widgets.Activator;
import org.eclipse.papyrus.infra.widgets.messages.Messages;
import org.eclipse.papyrus.infra.widgets.providers.WorkspaceContentProvider;
import org.eclipse.papyrus.infra.widgets.util.FileUtil;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.ui.model.WorkbenchLabelProvider;

/**
 * A Widget for editing Strings with File paths
 * The file paths may be absolute (FileSystem paths) or relative to the workspace (Workspace paths)
 * 
 * @author Camille Letavernier
 */
public class StringFileSelector extends StringEditor {

	private Button browse;

	private Button browseWorkspace;

	private List<String> filterNames;

	private List<String> filterExtensions;

	private boolean allowWorkspace = true, allowFileSystem = true;

	private boolean readOnly = false;

	public StringFileSelector(Composite parent, int style) {
		super(parent, style);
		((GridLayout)getLayout()).numColumns = 5;

		browse = factory.createButton(this, Messages.StringFileSelector_Browse, SWT.PUSH);
		browse.setLayoutData(new GridData());
		browseWorkspace = factory.createButton(this, Messages.StringFileSelector_BrowseWorkspace, SWT.PUSH);
		browseWorkspace.setLayoutData(new GridData());

		filterNames = new LinkedList<String>();
		filterExtensions = new LinkedList<String>();

		browse.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				File file = FileUtil.getFile(text.getText());

				FileDialog dialog = new FileDialog(getShell());
				if(labelText != null) {
					dialog.setText(labelText);
				}
				dialog.setFileName(file.getAbsolutePath());
				dialog.setFilterExtensions(filterExtensions.toArray(new String[filterExtensions.size()]));
				dialog.setFilterNames(filterNames.toArray(new String[filterNames.size()]));
				String result = dialog.open();
				if(result == null) { //Cancel
					return;
				}
				setResult(result);
			}

			public void widgetDefaultSelected(SelectionEvent e) {
				//Nothing
			}

		});

		browseWorkspace.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				IFile currentFile = FileUtil.getIFile(text.getText());

				TreeSelectorDialog dialog = new TreeSelectorDialog(getShell());
				if(labelText != null) {
					dialog.setTitle(labelText);
				}
				dialog.setContentProvider(new WorkspaceContentProvider());
				dialog.setLabelProvider(WorkbenchLabelProvider.getDecoratingWorkbenchLabelProvider());


				if(currentFile != null && currentFile.exists()) {
					dialog.setInitialSelections(new IFile[]{ currentFile });
				}

				int code = dialog.open();
				if(code == Window.OK) {
					Object[] result = dialog.getResult();
					if(result.length > 0) {
						Object file = result[0];
						if(file instanceof IFile) {
							setResult((IFile)file);
						}
					}
				}
			}

			public void widgetDefaultSelected(SelectionEvent e) {
				//Nothing
			}

		});
	}

	protected void setResult(IFile file) {
		text.setText(file.getFullPath().toString());
		notifyChange();
	}

	protected void setResult(File file) {
		text.setText(file.getAbsolutePath());
		notifyChange();
	}

	protected void setResult(String path) {
		text.setText(path);
		notifyChange();
	}

	public void setFilters(String[] filterExtensions, String[] filterNames) {
		if(filterExtensions.length != filterNames.length) {
			//This is a simple warning. Only valid filters will be retained.
			Activator.log.warn("FilterExtensions and FilterNames do not match");
		}

		setFilterNames(getFilterLabels(filterNames, filterExtensions));
		setFilterExtensions(filterExtensions);
	}

	protected String[] getFilterLabels(String[] filterNames, String[] filterExtensions) {
		int size = Math.min(filterNames.length, filterExtensions.length);
		String[] filters = new String[size];
		for(int i = 0; i < size; i++) {
			filters[i] = filterNames[i] + " (" + filterExtensions[i] + ")";
		}
		return filters;
	}

	public void setFilterExtensions(String[] filterExtensions) {
		this.filterExtensions = Arrays.asList(filterExtensions);
	}

	public void setFilterNames(String[] filterNames) {
		this.filterNames = Arrays.asList(filterNames);
	}

	public void addFilteredExtension(String filteredExtension, String filterName) {
		if(filteredExtension != null) {
			if(filterName == null) {
				filterName = filteredExtension;
			}

			filterExtensions.add(filteredExtension);
			filterNames.add(filterName);
		}
	}

	@Override
	public Object getEditableType() {
		return String.class;
	}

	@Override
	public void setReadOnly(boolean readOnly) {
		super.setReadOnly(readOnly);
		this.readOnly = readOnly;
		updateButtons();
	}

	public void setAllowWorkspace(boolean allowWorkspace) {
		this.allowWorkspace = allowWorkspace;
		updateButtons();
	}

	public void setAllowFileSystem(boolean allowFileSystem) {

		this.allowFileSystem = allowFileSystem;
		updateButtons();
	}

	private void updateButtons() {
		boolean enableWorkspace = !readOnly && allowWorkspace;
		boolean enableFileSystem = !readOnly && allowFileSystem;
		//((GridData)browseWorkspace.getLayoutData()).exclude = !allowWorkspace;
		//((GridData)browse.getLayoutData()).exclude = !allowFileSystem;
		browseWorkspace.setEnabled(enableWorkspace);
		browse.setEnabled(enableFileSystem);
	}

}
