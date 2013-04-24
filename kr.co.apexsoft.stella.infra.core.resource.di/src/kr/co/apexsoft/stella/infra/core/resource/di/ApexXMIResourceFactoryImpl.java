package kr.co.apexsoft.stella.infra.core.resource.di;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;

public class ApexXMIResourceFactoryImpl extends XMIResourceFactoryImpl {

	@Override
	public Resource createResource(URI uri) {
		XMIResource resource = new XMIResourceImpl(uri);
		resource.setEncoding("UTF-8");
		
		resource.getDefaultSaveOptions().put(XMIResource.OPTION_ENCODING, "UTF-8");
		return resource;
	}

}
