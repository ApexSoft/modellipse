/*****************************************************************************
 * Copyright (c) 2009 CEA
 *
 *    
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Atos Origin - Initial API and implementation
 *
 *****************************************************************************/
package org.eclipse.papyrus.uml.diagram.sequence.edit.parts;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.Cursors;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.PolylineDecoration;
import org.eclipse.draw2d.RotatableDecoration;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ITreeBranchEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateUnspecifiedTypeRequest;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.papyrus.uml.diagram.common.editpolicies.AppliedStereotypeLinkLabelDisplayEditPolicy;
import org.eclipse.papyrus.uml.diagram.common.figure.edge.UMLEdgeFigure;
import org.eclipse.papyrus.uml.diagram.sequence.edit.policies.CreationOnMessageEditPolicy;
import org.eclipse.papyrus.uml.diagram.sequence.edit.policies.LifelineChildGraphicalNodeEditPolicy;
import org.eclipse.papyrus.uml.diagram.sequence.edit.policies.Message4ItemSemanticEditPolicy;
import org.eclipse.papyrus.uml.diagram.sequence.edit.policies.MessageConnectionEditPolicy;
import org.eclipse.papyrus.uml.diagram.sequence.edit.policies.MessageConnectionLineSegEditPolicy;
import org.eclipse.papyrus.uml.diagram.sequence.util.SequenceUtil;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Display;
import org.eclipse.uml2.uml.Message;
import org.eclipse.uml2.uml.MessageSort;

/**
 * @generated
 */
public class Message4EditPart extends AbstractMessageEditPart

implements ITreeBranchEditPart {

	/**
	 * @generated
	 */
	public static final int VISUAL_ID = 4006;

	/**
	 * @generated
	 */
	public Message4EditPart(View view) {
		super(view);
	}

	/**
	 * Installs a specific message router on the edit part.
	 * 
	 * @generated NOT
	 */
	protected void installRouter() {
		getConnectionFigure().setConnectionRouter(LifelineChildGraphicalNodeEditPolicy.messageRouter);
		getConnectionFigure().setCursor(Cursors.ARROW);
		refreshBendpoints();
	}

	/**
	 * Ignore routing style since we are using a custom router and a custom ConnectionBendpointEditPolicy.
	 */
	protected void refreshRoutingStyles() {
	}

	/**
	 * @generated
	 */
	protected void createDefaultEditPolicies() {
		super.createDefaultEditPolicies();
		installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE, new Message4ItemSemanticEditPolicy());
		installEditPolicy(EditPolicyRoles.CREATION_ROLE, new CreationOnMessageEditPolicy());
		installEditPolicy(EditPolicy.CONNECTION_ROLE, new MessageConnectionEditPolicy());
		installEditPolicy(AppliedStereotypeLinkLabelDisplayEditPolicy.STEREOTYPE_LABEL_POLICY, new AppliedStereotypeLinkLabelDisplayEditPolicy());
		installEditPolicy(EditPolicy.CONNECTION_BENDPOINTS_ROLE, new MessageConnectionLineSegEditPolicy());
	}

	/**
	 * @generated
	 */
	protected boolean addFixedChild(EditPart childEditPart) {
		if(childEditPart instanceof MessageName4EditPart) {
			((MessageName4EditPart)childEditPart).setLabel(getPrimaryShape().getFigureMessageCreateLabelFigure());
			return true;
		}
		if(childEditPart instanceof MessageCreateAppliedStereotypeEditPart) {
			((MessageCreateAppliedStereotypeEditPart)childEditPart).setLabel(getPrimaryShape().getAppliedStereotypeLabel());
			return true;
		}
		return false;
	}

	/**
	 * @generated
	 */
	protected void addChildVisual(EditPart childEditPart, int index) {
		if(addFixedChild(childEditPart)) {
			return;
		}
		super.addChildVisual(childEditPart, -1);
	}

	/**
	 * @generated
	 */
	protected boolean removeFixedChild(EditPart childEditPart) {
		if(childEditPart instanceof MessageName4EditPart) {
			return true;
		}
		if(childEditPart instanceof MessageCreateAppliedStereotypeEditPart) {
			return true;
		}
		return false;
	}

	/**
	 * @generated
	 */
	protected void removeChildVisual(EditPart childEditPart) {
		if(removeFixedChild(childEditPart)) {
			return;
		}
		super.removeChildVisual(childEditPart);
	}

	/**
	 * Creates figure for this edit part.
	 * 
	 * Body of this method does not depend on settings in generation model so you may safely remove
	 * <i>generated</i> tag and modify it.
	 * 
	 * @generated
	 */

	protected Connection createConnectionFigure() {
		return new MessageCreate();
	}

	/**
	 * @generated
	 */
	public MessageCreate getPrimaryShape() {
		return (MessageCreate)getFigure();
	}

	/**
	 * @generated NOT inherits from UMLEdgeFigure to manage stereotype label
	 */
	public class MessageCreate extends UMLEdgeFigure {

		/**
		 * @generated
		 */
		private WrappingLabel fFigureMessageCreateLabelFigure;

		/**
		 * @generated
		 */
		private WrappingLabel fAppliedStereotypeLabel;

		/**
		 * @generated NOT call the super constructor
		 */
		public MessageCreate() {
			super();
			this.setLineStyle(Graphics.LINE_DASH);
			this.setForegroundColor(ColorConstants.black);

			createContents();
			setTargetDecoration(createTargetDecoration());
		}

		/**
		 * @generated NOT
		 */
		protected void createContents() {
			super.createContents();

			fFigureMessageCreateLabelFigure = new WrappingLabel();
			fFigureMessageCreateLabelFigure.setText("");

			fFigureMessageCreateLabelFigure.setFont(FFIGUREMESSAGECREATELABELFIGURE_FONT);

			this.add(fFigureMessageCreateLabelFigure);

		}

		/**
		 * @generated
		 */
		private RotatableDecoration createTargetDecoration() {
			PolylineDecoration df = new PolylineDecoration();
			df.setForegroundColor(ColorConstants.black);
			PointList pl = new PointList();
			pl.addPoint(getMapMode().DPtoLP(-2), getMapMode().DPtoLP(2));
			pl.addPoint(getMapMode().DPtoLP(0), getMapMode().DPtoLP(0));
			pl.addPoint(getMapMode().DPtoLP(-2), getMapMode().DPtoLP(-2));
			df.setTemplate(pl);
			df.setScale(getMapMode().DPtoLP(7), getMapMode().DPtoLP(3));
			return df;
		}

		/**
		 * @generated
		 */
		public WrappingLabel getFigureMessageCreateLabelFigure() {
			return fFigureMessageCreateLabelFigure;
		}

		/**
		 * @generated NOT get the stereotype label of super class
		 */
		public WrappingLabel getAppliedStereotypeLabel() {
			return super.getAppliedStereotypeLabel();
		}

	}

	/**
	 * @generated
	 */
	static final Font FFIGUREMESSAGECREATELABELFIGURE_FONT = new Font(Display.getCurrent(), "SANS", 9, SWT.NORMAL);

	/**
	 * Block message sort modification
	 */
	@Override
	protected void handleNotificationEvent(Notification notification) {
		SequenceUtil.handleMessageSortChange(getEditingDomain(), notification, (Message)resolveSemanticElement(), MessageSort.ASYNCH_CALL_LITERAL);
		super.handleNotificationEvent(notification);
	}

	/**
	 * @generated NOT Override for redirecting creation request to the lifeline
	 */
	@Override
	public void showSourceFeedback(Request request) {
		if(request instanceof CreateUnspecifiedTypeRequest) {
			getSource().showSourceFeedback(request);
			getTarget().showSourceFeedback(request);
		}
		super.showSourceFeedback(request);
	}

	/**
	 * @generated NOT Override for redirecting creation request to the lifeline
	 */
	@Override
	public void eraseSourceFeedback(Request request) {
		if(request instanceof CreateUnspecifiedTypeRequest) {
			getSource().eraseSourceFeedback(request);
			getTarget().eraseSourceFeedback(request);
		}
		super.eraseSourceFeedback(request);
	}

}
