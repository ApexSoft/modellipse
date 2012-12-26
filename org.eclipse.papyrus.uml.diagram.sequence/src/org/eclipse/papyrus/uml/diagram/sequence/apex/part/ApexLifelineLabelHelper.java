package org.eclipse.papyrus.uml.diagram.sequence.apex.part;

import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.papyrus.uml.diagram.sequence.util.LifelineLabelHelper;

public class ApexLifelineLabelHelper extends LifelineLabelHelper {

	/**
	 * singelton instance
	 */
	private static ApexLifelineLabelHelper labelHelper;

	/**
	 * Returns the singleton instance of this class
	 * 
	 * @return the singleton instance.
	 */
	public static ApexLifelineLabelHelper getInstance() {
		if(labelHelper == null) {
			labelHelper = new ApexLifelineLabelHelper();
		}
		return labelHelper;
	}
	
	@Override
	public String labelToDisplay(GraphicalEditPart editPart) {
		return super.labelToDisplay(editPart);
	}

}
