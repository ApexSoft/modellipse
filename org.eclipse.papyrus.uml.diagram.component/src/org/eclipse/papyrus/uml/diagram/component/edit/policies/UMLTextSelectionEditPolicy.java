/*****************************************************************************
 * Copyright (c) 2011 CEA LIST.
 *
 *    
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *	Amine EL KOUHEN (CEA LIST/LIFL) - Amine.El-Kouhen@lifl.fr 
 *****************************************************************************/
package org.eclipse.papyrus.uml.diagram.component.edit.policies;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.FigureListener;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.editpolicies.SelectionEditPolicy;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel;

// TODO: Auto-generated Javadoc
/**
 * The Class UMLTextSelectionEditPolicy.
 * 
 * @generated
 */
public class UMLTextSelectionEditPolicy extends SelectionEditPolicy {

	/** The selection feedback figure. @generated */
	private IFigure selectionFeedbackFigure;

	/** The focus feedback figure. @generated */
	private IFigure focusFeedbackFigure;

	/** The host position listener. @generated */
	private FigureListener hostPositionListener;

	/**
	 * Show primary selection.
	 * 
	 * @generated
	 */
	protected void showPrimarySelection() {
		if(getHostFigure() instanceof WrappingLabel) {
			((WrappingLabel)getHostFigure()).setSelected(true);
			((WrappingLabel)getHostFigure()).setFocus(true);
		} else {
			showSelection();
			showFocus();
		}
	}

	/**
	 * Show selection.
	 * 
	 * @generated
	 */
	protected void showSelection() {
		if(getHostFigure() instanceof WrappingLabel) {
			((WrappingLabel)getHostFigure()).setSelected(true);
			((WrappingLabel)getHostFigure()).setFocus(false);
		} else {
			hideSelection();
			addFeedback(selectionFeedbackFigure = createSelectionFeedbackFigure());
			getHostFigure().addFigureListener(getHostPositionListener());
			refreshSelectionFeedback();
			hideFocus();
		}
	}

	/**
	 * Hide selection.
	 * 
	 * @generated
	 */
	protected void hideSelection() {
		if(getHostFigure() instanceof WrappingLabel) {
			((WrappingLabel)getHostFigure()).setSelected(false);
			((WrappingLabel)getHostFigure()).setFocus(false);
		} else {
			if(selectionFeedbackFigure != null) {
				removeFeedback(selectionFeedbackFigure);
				getHostFigure().removeFigureListener(getHostPositionListener());
				selectionFeedbackFigure = null;
			}
			hideFocus();
		}
	}

	/**
	 * Show focus.
	 * 
	 * @generated
	 */
	protected void showFocus() {
		if(getHostFigure() instanceof WrappingLabel) {
			((WrappingLabel)getHostFigure()).setFocus(true);
		} else {
			hideFocus();
			addFeedback(focusFeedbackFigure = createFocusFeedbackFigure());
			refreshFocusFeedback();
		}
	}

	/**
	 * Hide focus.
	 * 
	 * @generated
	 */
	protected void hideFocus() {
		if(getHostFigure() instanceof WrappingLabel) {
			((WrappingLabel)getHostFigure()).setFocus(false);
		} else {
			if(focusFeedbackFigure != null) {
				removeFeedback(focusFeedbackFigure);
				focusFeedbackFigure = null;
			}
		}
	}

	/**
	 * Gets the feedback bounds.
	 * 
	 * @return the feedback bounds
	 * @generated
	 */
	protected Rectangle getFeedbackBounds() {
		Rectangle bounds;
		if(getHostFigure() instanceof Label) {
			bounds = ((Label)getHostFigure()).getTextBounds();
			bounds.intersect(getHostFigure().getBounds());
		} else {
			bounds = getHostFigure().getBounds().getCopy();
		}
		getHostFigure().getParent().translateToAbsolute(bounds);
		getFeedbackLayer().translateToRelative(bounds);
		return bounds;
	}

	/**
	 * Creates the selection feedback figure.
	 * 
	 * @return the i figure
	 * @generated
	 */
	protected IFigure createSelectionFeedbackFigure() {
		if(getHostFigure() instanceof Label) {
			Label feedbackFigure = new Label();
			feedbackFigure.setOpaque(true);
			feedbackFigure.setBackgroundColor(ColorConstants.menuBackgroundSelected);
			feedbackFigure.setForegroundColor(ColorConstants.menuForegroundSelected);
			return feedbackFigure;
		} else {
			RectangleFigure feedbackFigure = new RectangleFigure();
			feedbackFigure.setFill(false);
			return feedbackFigure;
		}
	}

	/**
	 * Creates the focus feedback figure.
	 * 
	 * @return the i figure
	 * @generated
	 */
	protected IFigure createFocusFeedbackFigure() {
		return new Figure() {

			protected void paintFigure(Graphics graphics) {
				graphics.drawFocus(getBounds().getResized(-1, -1));
			}
		};
	}

	/**
	 * Update label.
	 * 
	 * @param target
	 *        the target
	 * @generated
	 */
	protected void updateLabel(Label target) {
		Label source = (Label)getHostFigure();
		target.setText(source.getText());
		target.setTextAlignment(source.getTextAlignment());
		target.setFont(source.getFont());
	}

	/**
	 * Refresh selection feedback.
	 * 
	 * @generated
	 */
	protected void refreshSelectionFeedback() {
		if(selectionFeedbackFigure != null) {
			if(selectionFeedbackFigure instanceof Label) {
				updateLabel((Label)selectionFeedbackFigure);
				selectionFeedbackFigure.setBounds(getFeedbackBounds());
			} else {
				selectionFeedbackFigure.setBounds(getFeedbackBounds().expand(5, 5));
			}
		}
	}

	/**
	 * Refresh focus feedback.
	 * 
	 * @generated
	 */
	protected void refreshFocusFeedback() {
		if(focusFeedbackFigure != null) {
			focusFeedbackFigure.setBounds(getFeedbackBounds());
		}
	}

	/**
	 * Refresh feedback.
	 * 
	 * @generated
	 */
	public void refreshFeedback() {
		refreshSelectionFeedback();
		refreshFocusFeedback();
	}

	/**
	 * Gets the host position listener.
	 * 
	 * @return the host position listener
	 * @generated
	 */
	private FigureListener getHostPositionListener() {
		if(hostPositionListener == null) {
			hostPositionListener = new FigureListener() {

				public void figureMoved(IFigure source) {
					refreshFeedback();
				}
			};
		}
		return hostPositionListener;
	}
}
