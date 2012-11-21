package org.eclipse.papyrus.infra.core.apex;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.facet.infra.browser.uicore.internal.model.ITreeElement;

public class ApexModellipseExplorerRoot {
	
	/**
	 * Key : .di 파일의 Path
	 * Value : ApexProjectWrapper 
	 */
	private static Map<String, ITreeElement> _projectMap = new HashMap<String, ITreeElement>();
	
//	private static List<ServicesRegistry> _serviceRegistryList = new ArrayList<ServicesRegistry>();
	
	public static Map<String, ITreeElement> getProjectMap() {
		return _projectMap;
	}	
	
//	public static List<ServicesRegistry> getServicesRegistryList() {
//		return _serviceRegistryList;
//	}
}
