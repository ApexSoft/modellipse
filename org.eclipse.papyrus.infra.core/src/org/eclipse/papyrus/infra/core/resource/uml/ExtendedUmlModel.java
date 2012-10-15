/**
 * 
 */
package org.eclipse.papyrus.infra.core.resource.uml;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.papyrus.infra.core.resource.ModelUtils;

/**
 * A UML model whose resource is either a uml file or a profile(?).
 * 
 * @author cedric dumoulin
 * 
 */
public class ExtendedUmlModel extends UmlModel {

	/**
	 * Load the first resource with a supported extension TODO: (question from
	 * Cedric) fix what is a supported extension ?
	 * 
	 * @see org.eclipse.papyrus.infra.core.resource.AbstractBaseModel#loadModel(org.eclipse.core.runtime.IPath)
	 * 
	 * @param fullPath
	 */
	@Override
	public void loadModel(IFile file) {

		// Extract file name, without extension
		IPath fullPath = file.getFullPath().removeFileExtension();

		// Try to load resources that are not DI or Notation
		// TODO : set the requested extension rather than taking anyone.
		if(resource == null) {
			IContainer folder = file.getParent();
			try {
				IResource[] files = folder.members();
				for(IResource r : files) {
					String extension = r.getFullPath().getFileExtension();
					if(r.getFullPath().removeFileExtension().lastSegment().equals(fullPath.lastSegment()) && !"di".equalsIgnoreCase(extension) && !"notation".equalsIgnoreCase(extension)) {
						if(Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().get(extension) != null) {
							resourceURI = getPlatformURI(r.getFullPath());
							try {
								resource = getResourceSet().getResource(resourceURI, true);
							} catch (WrappedException e) {
								if (ModelUtils.isDegradedModeAllowed(e.getCause())){
									resource = getResourceSet().getResource(resourceURI, false);
									if (resource == null){
										throw e;
									}
								}
							}
							break;
						}
					}
				}
			} catch (CoreException e) {
				// never happens.
			}
		}
	}
}
