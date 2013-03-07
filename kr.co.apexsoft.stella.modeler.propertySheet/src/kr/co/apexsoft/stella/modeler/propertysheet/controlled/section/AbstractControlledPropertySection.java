package kr.co.apexsoft.stella.modeler.propertysheet.controlled.section;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.common.core.util.StringStatics;
import org.eclipse.gmf.runtime.diagram.ui.properties.sections.AbstractBasicTextPropertySection;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertyConstants;

public abstract class AbstractControlledPropertySection extends AbstractBasicTextPropertySection {

	public AbstractControlledPropertySection() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected abstract String getPropertyNameLabel();

	@Override
	protected abstract String getPropertyValueString();
	
	@Override
	protected void setPropertyValue(EObject object, Object value) {
		// TODO Auto-generated method stub
		// Controlled 탭의 내용은 Editable하지 않으므로 비어있는 메서드 처리
	}	

	@Override
	protected String getPropertyChangeCommandName() {
		// TODO Auto-generated method stub
		// Controlled 탭의 내용은 Editable하지 않으므로 비어있는 "" return 처리
		return "";
	}
	
	@Override
	protected boolean isReadOnly() {
		// Controlled 탭의 내용은 Editable하지 않으므로 readOnly 처리
		return true;
	}

	/**
	 * Instantiate a text widget
	 * 
	 * @param parent -
	 *            parent composite
	 * @return - a text widget to display and edit the property
	 */
	protected Text createTextWidget(Composite parent) {
		Text text = getWidgetFactory().createText(parent, StringStatics.BLANK);
		FormData data = new FormData();
		data.left = new FormAttachment(ITabbedPropertyConstants.HMARGIN, 60);
		data.right = new FormAttachment(100, -ITabbedPropertyConstants.HMARGIN);
		data.top = new FormAttachment(0, 0);
		text.setLayoutData(data);
		if (isReadOnly())
			text.setEditable(false);
		return text;
	}
}
