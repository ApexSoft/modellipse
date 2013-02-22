package kr.co.apexsoft.stella.modeler.explorer.tester;

import kr.co.apexsoft.stella.modeler.explorer.view.ApexStellaExplorerView;

import org.eclipse.ui.IWorkbenchPart;

public class PropertyTester extends org.eclipse.core.expressions.PropertyTester {
	
	/** property to test if the current activePart is the StellaExplorer */
	public static final String IS_STELLA_EXPLORER = "isStellaExplorer"; //$NON-NLS-1$

	@Override
	public boolean test(Object receiver, String property, Object[] args,
			Object expectedValue) {
		if(IS_STELLA_EXPLORER.equals(property) && receiver instanceof IWorkbenchPart) {
			boolean answer = isStellaExplorer((IWorkbenchPart)receiver);
			return new Boolean(answer).equals(expectedValue);
		}
		return false;
	}

	/**
	 * Tests if the current activePart is the Stella Explorer
	 * 
	 * @param receiver
	 * @return
	 */
	private boolean isStellaExplorer(IWorkbenchPart receiver) {
		return receiver instanceof ApexStellaExplorerView;
	}

}


