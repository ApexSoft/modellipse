/*******************************************************************************
 * Copyright (c) 2010 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.gmf.glue.edit.part;

import org.eclipse.jface.text.contentassist.ContentAssistEvent;
import org.eclipse.jface.text.contentassist.ContentAssistant;
import org.eclipse.jface.text.contentassist.ICompletionListener;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.VerifyKeyListener;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.VerifyEvent;

/**
 * @author koehnlein
 */
public class PopupXtextEditorKeyListener extends KeyAdapter implements VerifyKeyListener {

	private final PopupXtextEditorHelper popupXtextEditorHelper;

	private ContentAssistant contentAssistant;

	private boolean isIgnoreNextESC;

	/**
	 * This element comes from the XText/GMF integration example, and was not originally documented.
	 * @param popupXtextEditorHelper 
	 * @param contentAssistant 
	 */
	public PopupXtextEditorKeyListener(PopupXtextEditorHelper popupXtextEditorHelper, ContentAssistant contentAssistant) {
		this.popupXtextEditorHelper = popupXtextEditorHelper;
		this.contentAssistant = contentAssistant;
		isIgnoreNextESC = false;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.keyCode;
		if ((e.stateMask & SWT.CTRL) != 0 && ((keyCode == SWT.KEYPAD_CR) || (keyCode == SWT.CR))) {
			this.popupXtextEditorHelper.closeEditor(true);
		}
		if (keyCode == SWT.ESC) {
			if (isIgnoreNextESC) {
				isIgnoreNextESC = false;
			} else {
				PopupXtextEditorHelper.ignoreFocusLost = true ;
				this.popupXtextEditorHelper.closeEditor(false);
			}
		}
		if ((e.stateMask & SWT.CTRL) != 0 && (keyCode == ' ')) {
			this.contentAssistant.setRepeatedInvocationMode(true) ;
			this.contentAssistant.showPossibleCompletions() ;
			
			this.isIgnoreNextESC = true ;
			contentAssistant.addCompletionListener(new ICompletionListener() {
				
				public void selectionChanged(ICompletionProposal proposal, boolean smartToggle) {		
				}
				
				public void assistSessionStarted(ContentAssistEvent event) {
				}
				
				public void assistSessionEnded(ContentAssistEvent event) {
					try {
						popupXtextEditorHelper.getSourceViewerHandle().getViewer().getTextWidget().setFocus() ;
					}
					catch (Exception e) {
						//ignore
					}
				}
			}) ;
		}
	}

	public void verifyKey(VerifyEvent e) {
		if (e.keyCode == SWT.ESC && isContentAssistActive()) {
			isIgnoreNextESC = true;
		}
		if ((e.stateMask & SWT.CTRL) != 0 && ((e.keyCode == SWT.KEYPAD_CR) || (e.keyCode == SWT.CR))) {
			e.doit = false;
		}
	}

	/**
	 * @return true if the proposal popup has focus
	 *
	 */
	public boolean isContentAssistActive() {
		return contentAssistant != null && contentAssistant.hasProposalPopupFocus();
	}
	
}