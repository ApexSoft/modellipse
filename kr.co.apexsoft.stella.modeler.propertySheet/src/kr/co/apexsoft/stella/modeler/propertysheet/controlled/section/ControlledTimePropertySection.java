package kr.co.apexsoft.stella.modeler.propertysheet.controlled.section;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.emf.common.util.URI;

public class ControlledTimePropertySection extends AbstractControlledPropertySection {
	
	private final String LABEL_LAST_MODIFIED = "Last Modified";

	public ControlledTimePropertySection() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected String getPropertyNameLabel() {
		return LABEL_LAST_MODIFIED;
	}

	@Override
	protected String getPropertyValueString() {
		
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

}
