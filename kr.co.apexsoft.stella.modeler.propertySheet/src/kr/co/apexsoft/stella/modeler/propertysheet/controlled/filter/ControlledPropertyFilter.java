package kr.co.apexsoft.stella.modeler.propertysheet.controlled.filter;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.facet.infra.browser.uicore.internal.model.ModelElementItem;
import org.eclipse.jface.viewers.IFilter;
import org.eclipse.papyrus.infra.services.controlmode.util.ControlModeUtil;

public class ControlledPropertyFilter implements IFilter {

	@Override
	public boolean select(Object toTest) {
		boolean result = false;
		
		if ( toTest instanceof ModelElementItem ) {
			ModelElementItem aMitem = (ModelElementItem)toTest;
			EObject eObj = aMitem.getEObject();
			result = ControlModeUtil.canUncontrol(eObj);	
		}
		
		return result;
	}
}
