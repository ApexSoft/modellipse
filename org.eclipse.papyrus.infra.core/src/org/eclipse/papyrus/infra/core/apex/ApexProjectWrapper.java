package org.eclipse.papyrus.infra.core.apex;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.emf.facet.infra.browser.uicore.internal.model.ITreeElement;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.papyrus.infra.core.Activator;
import org.eclipse.papyrus.infra.core.resource.uml.UmlModel;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.plugin.AbstractUIPlugin;

public class ApexProjectWrapper implements ITreeElement {
	
	private IProject _project;
	private List<UmlModel> _children;
	
	public ApexProjectWrapper(IProject project) {
		_project = project;
		_children = new ArrayList<UmlModel>();
	}
	
	public void addChildren(UmlModel umlModel) {
		_children.add(umlModel);
	}
	
	public List<UmlModel> getChildren() {
		return _children;
	}
	
	public IProject getProject() {
		return _project;
	}

	public ITreeElement getTreeParent() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean hasChildren() {
		return _children.size() > 0;
	}
	
	public String getText() {
		return _project.getName();
	}

	public Image getImage() {
		ImageDescriptor imgDesc = AbstractUIPlugin.imageDescriptorFromPlugin(Activator.PLUGIN_ID, "icons/Modellipse-windowImage-16.png");
		return imgDesc.createImage();
	}

	public Color getForeground() {
		// TODO Auto-generated method stub
		return null;
	}

	public Color getBackground() {
		// TODO Auto-generated method stub
		return null;
	}

	public Font getFont() {
		// TODO Auto-generated method stub
		return null;
	}
}
