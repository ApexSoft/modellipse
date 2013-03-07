package kr.co.apexsoft.stella.modeler.propertysheet.controlled.section;

import org.eclipse.core.resources.ResourcesPlugin;


public class ControlledPathPropertySection extends AbstractControlledPropertySection {

	private final String LABEL_PATH = "Path";
	
	public ControlledPathPropertySection() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected String getPropertyNameLabel() {
		return LABEL_PATH;
	}

	@Override
	protected String getPropertyValueString() {
		
		String resourcePath = ResourcesPlugin.getWorkspace().getRoot().getLocation().toString();
		String platformRelativePath = getEObject().eResource().getURI().toPlatformString(true);		
		
		return resourcePath.concat(platformRelativePath);
	}
	
}
