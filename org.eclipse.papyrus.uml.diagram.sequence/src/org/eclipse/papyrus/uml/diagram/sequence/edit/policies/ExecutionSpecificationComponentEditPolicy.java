/*****************************************************************************
 * Copyright (c) 2010 CEA
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
package org.eclipse.papyrus.uml.diagram.sequence.edit.policies;

import java.util.Iterator;
import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.requests.GroupRequest;
import org.eclipse.gef.requests.ReconnectRequest;
import org.eclipse.gmf.runtime.diagram.core.commands.DeleteCommand;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.ComponentEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.requests.EditCommandRequestWrapper;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.papyrus.uml.diagram.sequence.edit.parts.LifelineEditPart;
import org.eclipse.papyrus.uml.diagram.sequence.util.SequenceDeleteHelper;
import org.eclipse.papyrus.uml.diagram.sequence.util.SequenceRequestConstant;
import org.eclipse.papyrus.uml.diagram.sequence.util.SequenceUtil;

/**
 * This edit policy also deletes time/duration edit parts which are linked with the deleted edit part.
 */
public class ExecutionSpecificationComponentEditPolicy extends ComponentEditPolicy {

	/**
	 * apex updated
	 * 
	 * {@inheritDoc}
	 */
	@Override
	protected Command createDeleteViewCommand(GroupRequest deleteRequest) {
		CompoundCommand deleteViewsCommand = new CompoundCommand();
		
		/* apex added start */
		// Activation의 source connections들을 parent인 lifeline으로 이동하여 연결
		if (getHost() instanceof ShapeNodeEditPart) {
			LifelineEditPart lifelineEP = SequenceUtil.getParentLifelinePart(getHost());
			IFigure figure = lifelineEP.getNodeFigure();
			Rectangle newBounds = figure.getBounds().getCopy();
			figure.translateToAbsolute(newBounds);
			
			List connections = ((ShapeNodeEditPart)getHost()).getSourceConnections();
			for (Iterator iter = connections.iterator(); iter.hasNext(); ) {
				ConnectionNodeEditPart connection = (ConnectionNodeEditPart)iter.next();
				Point location = SequenceUtil.getAbsoluteEdgeExtremity(connection, true);
				
				ReconnectRequest reconnReq = new ReconnectRequest();
				reconnReq.setConnectionEditPart(connection);
				reconnReq.setLocation(location);
				reconnReq.setTargetEditPart(lifelineEP);
				reconnReq.setType(RequestConstants.REQ_RECONNECT_SOURCE);
				reconnReq.getExtendedData().put(SequenceRequestConstant.DO_NOT_MOVE_EDIT_PARTS, true);
				
				deleteViewsCommand.add(lifelineEP.getCommand(reconnReq));
			}
		}
		/* apex added end */

		Command deleteViewCommand = super.createDeleteViewCommand(deleteRequest);
		deleteViewsCommand.add(deleteViewCommand);
		if(getHost() instanceof ShapeNodeEditPart) {
			TransactionalEditingDomain editingDomain = ((ShapeNodeEditPart)getHost()).getEditingDomain();
			SequenceDeleteHelper.completeDeleteExecutionSpecificationViewCommand(deleteViewsCommand, editingDomain, getHost());
			addDeleteViewChildrenCommand(deleteViewsCommand, editingDomain, (ShapeNodeEditPart)getHost());			
		}
		return deleteViewsCommand;
	}

	// support nested  actions and behaviors execution, https://bugs.eclipse.org/bugs/show_bug.cgi?id=384616
	public static void addDeleteViewChildrenCommand(CompoundCommand deleteViewsCommand, TransactionalEditingDomain editingDomain, ShapeNodeEditPart part) {
		List<ShapeNodeEditPart> list = LifelineXYLayoutEditPolicy.getAffixedExecutionSpecificationEditParts(part);
		for(ShapeNodeEditPart p : list){
			deleteViewsCommand.add(	new ICommandProxy(new DeleteCommand(editingDomain, (View) p.getModel())));
			SequenceDeleteHelper.completeDeleteExecutionSpecificationViewCommand(deleteViewsCommand, editingDomain, p);
			addDeleteViewChildrenCommand(deleteViewsCommand,editingDomain, p);  //recursive delete children
		}
	}
	
	public static void addDestroyElementChildrenCommand(CompoundCommand deleteElementsCommand, TransactionalEditingDomain editingDomain, EditPart part) {
		List<ShapeNodeEditPart> list = LifelineXYLayoutEditPolicy.getAffixedExecutionSpecificationEditParts((ShapeNodeEditPart)part);
		for(ShapeNodeEditPart p : list){
			Request request = new EditCommandRequestWrapper(new DestroyElementRequest(p.resolveSemanticElement(), false));
			deleteElementsCommand.add(p.getCommand(request));
		}
	}
	
	/**
	 * apex updated
	 * 
	 * true일 경우 View 삭제하면 Model까지 삭제
	 */
	@Override
	protected boolean shouldDeleteSemantic() {
		/* apex improved start */
//		return true;
		/* apex improved end */
		/* apex replaced */
		return super.shouldDeleteSemantic();
		// */
	}
}
