package kr.co.apexsoft.stella.modeler.explorer.core;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IProject;
import org.eclipse.emf.facet.infra.browser.uicore.CustomizationManager;
import org.eclipse.emf.facet.infra.browser.uicore.internal.model.ITreeElement;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.papyrus.infra.core.Activator;
import org.eclipse.papyrus.infra.core.resource.uml.UmlModel;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.plugin.AbstractUIPlugin;

//public class ApexProjectWrapper implements ITreeElement {
public class ApexProjectWrapper implements ITreeElement {

	private IProject _project;
//	private List<UmlModel> _children;

//	private List<ServicesRegistry> _childrenServicesRegistry;
//	
//	private Map<String, ServicesRegistry> _servicesRegistryMap;
//	private Map<String, ISaveAndDirtyService> _saveAndDirtyServiceMap;
//	private Map<String, TransactionalEditingDomain> _transactionalEditingDomainMap;
//	private Map<String, IUndoContext> _undoContextMap;
//	private Map<String, IPropertySheetPage> _propertySheetPageMap;
//	private Map<String, IFile> _diFileMap;
	/**
	 * key : diPath
	 */
	private Map<String, UmlModel> _umlModelMap;
	private Map<String, Boolean> _isDisposedMap;

	public ApexProjectWrapper(IProject project) {
		_project = project;
//		_children = new ArrayList<UmlModel>();
//		_childrenServicesRegistry = new ArrayList<ServicesRegistry>();
//		_servicesRegistryMap = new HashMap<String, ServicesRegistry>();
//		_saveAndDirtyServiceMap = new HashMap<String, ISaveAndDirtyService>();
//		_transactionalEditingDomainMap = new HashMap<String, TransactionalEditingDomain>();
//		_undoContextMap = new HashMap<String, IUndoContext>();
//		_propertySheetPageMap = new HashMap<String, IPropertySheetPage>();
//		_diFileMap = new HashMap<String, IFile>(); 
		_umlModelMap = new HashMap<String, UmlModel>();
		_isDisposedMap = new HashMap<String, Boolean>();
	}

//	public void addChildren(UmlModel umlModel) {
//		_children.add(umlModel);
//	}
//	
//	public void removeChildren(UmlModel umlModel) {
//		_children.remove(umlModel);
//	}
//	
//	public List<UmlModel> getChildren() {
//		return _children;
//	}

//	public void addServicesRegistryChildren(ServicesRegistry s) {
//		_childrenServicesRegistry.add(s);
//	}
//	
//	public List<ServicesRegistry> getServicesRegistryChildren() {
//		return _childrenServicesRegistry;
//	}
//	
//	public void put(String diPath, ServicesRegistry s) {
//		_servicesRegistryMap.put(diPath, s);		
//	}
//	
//	public ServicesRegistry getServicesRegistry(String diPath) {
//		return _servicesRegistryMap.get(diPath);
//	}
//	
//	public Map<String, ServicesRegistry> getServicesRegistryMap() {
//		return _servicesRegistryMap;
//	}
//	
//	public void put(String diPath, TransactionalEditingDomain t) {
//		_transactionalEditingDomainMap.put(diPath, t);		
//	}
//	
//	public TransactionalEditingDomain getTransactionalEditingDomain(String diPath) {
//		return _transactionalEditingDomainMap.get(diPath);
//	}
//	
//	public Map<String, TransactionalEditingDomain> getTransactionalEditingDomainMap() {
//		return _transactionalEditingDomainMap;
//	}	
//	
//	public void put(String diPath, ISaveAndDirtyService s) {
//		_saveAndDirtyServiceMap.put(diPath, s);		
//	}
//	
//	public ISaveAndDirtyService getSaveAndDirtyService(String diPath) {
//		return _saveAndDirtyServiceMap.get(diPath);
//	}
//	
//	public Map<String, ISaveAndDirtyService> getSaveAndDirtyServiceMap() {
//		return _saveAndDirtyServiceMap;
//	}	
//	
//	public void put(String diPath, IUndoContext s) {
//		_undoContextMap.put(diPath, s);		
//	}
//	
//	public IUndoContext getUndoContext(String diPath) {
//		return _undoContextMap.get(diPath);
//	}
//	
//	public Map<String, IUndoContext> getUndoContextMap() {
//		return _undoContextMap;
//	}
//	
//	public void put(String diPath, IPropertySheetPage s) {
//		_propertySheetPageMap.put(diPath, s);		
//	}
//	
//	public IPropertySheetPage getPropertySheetPage(String diPath) {
//		return _propertySheetPageMap.get(diPath);
//	}
//	
//	public Map<String, IPropertySheetPage> getPropertySheetPageMap() {
//		return _propertySheetPageMap;
//	}
//	
//	public void put(String diPath, IFile s) {
//		_diFileMap.put(diPath, s);		
//	}
//	
//	public IFile getDiFile(String diPath) {
//		return _diFileMap.get(diPath);
//	}
//	
//	public Map<String, IFile> getDiFileMap() {
//		return _diFileMap;
//	}

	public void put(String diPath, UmlModel s) {
		_umlModelMap.put(diPath, s);		
	}

	public void removeUmlModel(String diPath) {
		_umlModelMap.remove(diPath);
	}

	public UmlModel getUmlModel(String diPath) {
		return _umlModelMap.get(diPath);
	}

	public Map<String, UmlModel> getUmlModelMap() {
		return _umlModelMap;
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

	public IProject getProject() {
		return _project;
	}

	public ITreeElement getTreeParent() {
		// TODO Auto-generated method stub
		return null;
	}

//	public boolean hasChildren() {
//		return _children.size() > 0;
//	}

	public String getText() {
		return _project.getName();
	}

	public Image getImage() {
		ImageDescriptor imgDesc = AbstractUIPlugin.imageDescriptorFromPlugin(Activator.PLUGIN_ID, "icons/Modellipse-windowImage-16.png"); //$NON-NLS-1$
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
}