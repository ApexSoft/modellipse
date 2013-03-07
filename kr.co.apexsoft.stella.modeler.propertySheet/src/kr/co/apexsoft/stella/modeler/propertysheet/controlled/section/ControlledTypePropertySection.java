package kr.co.apexsoft.stella.modeler.propertysheet.controlled.section;


public class ControlledTypePropertySection extends AbstractControlledPropertySection {
	
	private final String LABEL_TYPE = "Type";

	public ControlledTypePropertySection() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected String getPropertyNameLabel() {		
		return LABEL_TYPE;
	}

	@Override
	protected String getPropertyValueString() {
		return getEObject().eResource().getURI().fileExtension() + " File";
	}
	
}
