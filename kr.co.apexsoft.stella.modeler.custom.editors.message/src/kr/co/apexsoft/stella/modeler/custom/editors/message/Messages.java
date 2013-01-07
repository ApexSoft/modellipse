package kr.co.apexsoft.stella.modeler.custom.editors.message;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "kr.co.apexsoft.stella.modeler.custom.editors.message.messages"; //$NON-NLS-1$
	public static String MessagePopupEditor_Create_Label;
	public static String MessagePopupEditor_CreateOrModifySignatureDialog_Title;
	public static String MessagePopupEditor_Delselect_Label;
	public static String CreateOrModifySignatureDialog_CreationRadio_Text;
	public static String CreateOrModifySignatureDialog_NameLabel_Text;
	public static String CreateOrModifySignatureDialog_NoElementRadio_Text;
	public static String CreateOrModifySignatureDialog_OwnerLabel_Text;
	public static String CreateOrModifySignatureDialog_TypeLabel_Text;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
