package kr.co.apexsoft.stella.modeler.explorer.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IProject;
import org.eclipse.emf.facet.infra.browser.uicore.internal.model.ITreeElement;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.papyrus.infra.core.Activator;
import org.eclipse.papyrus.infra.core.resource.uml.UmlModel;
import org.eclipse.papyrus.infra.core.services.ServicesRegistry;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.plugin.AbstractUIPlugin;

public class ApexProjectWrapper implements ITreeElement {

	private IProject _project;
	/**
	 * key : diPath = file.getLocationURI().getPath()
	 */
	private Map<String, UmlModel> _umlModelMap;
	private Map<String, Boolean> _isDisposedMap;
	private Map<String, ServicesRegistry> _servicesRegistryMap;	
	private Map<String, ApexDIWrapper> _diWrapperMap;
	private List<String> _deletedDIWrapperKey;

	public ApexProjectWrapper(IProject project) {
		_project = project;
		_umlModelMap = new HashMap<String, UmlModel>();
		_isDisposedMap = new HashMap<String, Boolean>();
		_servicesRegistryMap = new HashMap<String, ServicesRegistry>();
		_diWrapperMap = new HashMap<String, ApexDIWrapper>();
		_deletedDIWrapperKey = new ArrayList<String>();
	}
	
	public Map<String, UmlModel> getUmlModelMap() {
		return _umlModelMap;
	}

	public void put(String diPath, UmlModel s) {
		_umlModelMap.put(diPath, s);		
	}

	public void removeUmlModel(String diPath) {
		_umlModelMap.remove(diPath);
	}

	public UmlModel getUmlModel(String diPath) {
		return _umlModelMap.get(diPath);
	}

	public Map<String, Boolean> getIsDisposedMap() {
		return _isDisposedMap;
	}

	public void put(String diPath, Boolean isDisposed) {
		_isDisposedMap.put(diPath, isDisposed);
	}	

	public void setIsDisposed(String diPath, Boolean isDisposed) {
		_isDisposedMap.remove(diPath);
		_isDisposedMap.put(diPath, isDisposed);
	}

	public boolean getIsDisposed(String diPath) {
		boolean isDisposed;
		if (_isDisposedMap.get(diPath) == null ) {
			isDisposed = false;
		} else {
			isDisposed = _isDisposedMap.get(diPath).booleanValue(); 
		}
		return isDisposed;
	}
	
	public void removeIsDisposedMap(String diPath) {
		_isDisposedMap.remove(diPath);
	}
	
	public Map<String, ServicesRegistry> getServicesRegistryMap() {
		return _servicesRegistryMap;
	}

	public void put(String diPath, ServicesRegistry servicesRegistry) {
		_servicesRegistryMap.put(diPath, servicesRegistry);
	}	

	public void removeServicesRegistry(String diPath) {
		_servicesRegistryMap.remove(diPath);
	}

	public ServicesRegistry getServicesRegistry(String diPath) {
		return _servicesRegistryMap.get(diPath);
	}
	
	public void put(String diPath, ApexDIWrapper diWrapper) {
		_diWrapperMap.put(diPath, diWrapper);
	}
	
	public ApexDIWrapper getDIWrapper(String diPath) {
		return _diWrapperMap.get(diPath);
	}
	
	public Map<String, ApexDIWrapper> getDIWrapperMap() {
		return _diWrapperMap;
	}
	
	public void removeDIWrapper(String diPath) {
		_diWrapperMap.remove(diPath);
	}
	
	public void addDeletedDIWrapperKey(String diPath) {
		_deletedDIWrapperKey.add(diPath);
	}
	
	public void removeDeletedDIWrapperKey(String diPath) {
		_deletedDIWrapperKey.remove(diPath);
	}
	
	public List<String> getDeletedDIWrapperKeyList() {
		return _deletedDIWrapperKey;
	}

	public IProject getProject() {
		return _project;
	}

	@Override
	public String getText() {
		return _project.getName();
	}

	@Override
	public Image getImage() {
		ImageDescriptor imgDesc = AbstractUIPlugin.imageDescriptorFromPlugin(Activator.PLUGIN_ID, "icons/windowImage-16.png"); //$NON-NLS-1$
		return imgDesc.createImage();
	}

	@Override
	public Color getForeground() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Color getBackground() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Font getFont() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasChildren() {
		// TODO Auto-generated method stub
		return (_umlModelMap.size() - _isDisposedMap.size()) > 0;
	}

	@Override
	public List<?> getChildren() {
		// TODO Auto-generated method stub
		return null;
	}	

	@Override
	public ITreeElement getTreeParent() {
		// TODO Auto-generated method stub
		return null;
	}

}