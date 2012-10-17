package org.eclipse.papyrus.uml.diagram.sequence.tools;

import java.util.Map;

import org.eclipse.core.runtime.Platform;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gmf.runtime.gef.ui.internal.tools.SelectConnectionEditPartTracker;
import org.eclipse.papyrus.uml.diagram.sequence.util.ApexSequenceRequestConstants;
import org.eclipse.swt.SWT;

/**
 * @author Jiho
 *
 */
@SuppressWarnings({ "restriction", "unchecked" })
public class ApexSelectConnectionEditPartTracker extends
		SelectConnectionEditPartTracker {
	
	/**
	 * Key modifier for constrained move. It's SHIFT on all platforms.
	 */
	static final int MODIFIER_CONSTRAINED_MOVE = SWT.SHIFT;
	
	/**
	 * Key modifier for sequence of messages. It's ALT on Mac, and MOD1
	 * on all other platforms.
	 */
	static final int MODIFIER_REORDERING;
	
	static {
		if (Platform.OS_MACOSX.equals(Platform.getOS())) {
			MODIFIER_REORDERING = SWT.ALT;
		} else {
			MODIFIER_REORDERING = SWT.MOD1;
		}
	}
	
	private Request sourceRequest;
	
	public ApexSelectConnectionEditPartTracker(ConnectionEditPart owner) {
		super(owner);
	}

	@Override
	protected Request createSourceRequest() {
		if (sourceRequest == null)
			sourceRequest = super.createSourceRequest();
		return sourceRequest;
	}

	@Override
	protected void updateSourceRequest() {
		super.updateSourceRequest();
		
		Input currentInput = getCurrentInput();
		Map extendedData = sourceRequest.getExtendedData();
		extendedData.put(ApexSequenceRequestConstants.APEX_MODIFIER_CONSTRAINED_MOVE, currentInput.isModKeyDown(MODIFIER_CONSTRAINED_MOVE));
		extendedData.put(ApexSequenceRequestConstants.APEX_MODIFIER_REORDERING, currentInput.isModKeyDown(MODIFIER_REORDERING));
	}

}
