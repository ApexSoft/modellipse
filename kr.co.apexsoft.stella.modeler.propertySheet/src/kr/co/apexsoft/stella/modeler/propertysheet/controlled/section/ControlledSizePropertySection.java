package kr.co.apexsoft.stella.modeler.propertysheet.controlled.section;

import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.URI;

public class ControlledSizePropertySection extends AbstractControlledPropertySection {
	
	private final String LABEL_SIZE = "Size";

	public ControlledSizePropertySection() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected String getPropertyNameLabel() {
		return LABEL_SIZE;
	}

	@Override
	protected String getPropertyValueString() {
		
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

}
