/*****************************************************************************
 * Copyright (c) 2011-2012 CEA LIST.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *		
 *		CEA LIST - Initial API and implementation
 *
 *****************************************************************************/
package org.eclipse.papyrus.uml.service.types.command;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;
import org.eclipse.gmf.runtime.emf.type.core.commands.EditElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.ReorientRelationshipRequest;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.papyrus.uml.service.types.utils.ConnectorUtils;
import org.eclipse.papyrus.uml.service.types.utils.NamedElementHelper;
import org.eclipse.papyrus.uml.service.types.utils.RequestParameterUtils;
import org.eclipse.uml2.uml.ConnectableElement;
import org.eclipse.uml2.uml.Connector;
import org.eclipse.uml2.uml.ConnectorEnd;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Port;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.StructuredClassifier;

/**
 * <pre>
 * Re-orient command for binary {@link Connector}.
 * </pre>
 */
public class ConnectorReorientCommand extends EditElementCommand {

	private final int reorientDirection;

	private final EObject oldEnd;

	private final EObject newEnd;

	private final View newEndView;

	private final View oppositeEndView;

	private final Edge reorientedEdgeView;

	private ConnectorUtils utils = new ConnectorUtils();

	/**
	 * Constructor.
	 */
	public ConnectorReorientCommand(ReorientRelationshipRequest request) {
		super(request.getLabel(), request.getRelationship(), request);
		reorientDirection = request.getDirection();
		oldEnd = request.getOldRelationshipEnd();
		newEnd = request.getNewRelationshipEnd();
		reorientedEdgeView = RequestParameterUtils.getReconnectedEdge(request);
		newEndView = RequestParameterUtils.getReconnectedEndView(request);
		oppositeEndView = (reorientDirection == ReorientRelationshipRequest.REORIENT_SOURCE) ? reorientedEdgeView.getTarget() : reorientedEdgeView.getSource();
	}

	/**
	 * Test if the command can be executed.
	 */
	public boolean canExecute() {
		if(!(getElementToEdit() instanceof Connector)) {
			return false;
		}

		if(getLink().getEnds().size() != 2) {
			return false;
		}

		return canReorient(newEndView, oppositeEndView);
	}

	private boolean canReorient(View newEndView, View oppositeEndView) {
		
		// Verify possible type of new source
		if((newEndView.getElement() == null) || !(newEndView.getElement() instanceof ConnectableElement)) {
			return false;
		}

		if((newEndView != null) && (oppositeEndView != null)) {
			// Cannot create a self connector on a view
			if(newEndView == oppositeEndView) {
				return false;
			}

			// Cannot create a connector from a view representing a Part to its own Port (or the opposite)
			if((newEndView.getChildren().contains(oppositeEndView)) || (oppositeEndView.getChildren().contains(newEndView))) {
				return false;
			}
			
			// Cannot connect two Port owned by the same view
			if ((newEndView.getElement() instanceof Port) && (oppositeEndView.getElement() instanceof Port)) {
				if (ViewUtil.getContainerView(newEndView) == ViewUtil.getContainerView(oppositeEndView)) {
					return false;
				}
			}
			
			// Cannot connect a Part to one of its (possibly indirect) containment, must connect to one of its Port.
			if (utils.getStructureContainers(newEndView).contains(oppositeEndView) 
				|| utils.getStructureContainers(oppositeEndView).contains(newEndView)) {
				return false;
			}
			
			// Ensure container can be deduced
			if(deduceContainer(newEndView, oppositeEndView) == null) {
				return false;
			}
		}

		return true;
	}

	protected CommandResult doExecuteWithResult(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
		if(!canExecute()) {
			throw new ExecutionException("Invalid arguments in reorient link command"); //$NON-NLS-1$
		}

		EObject oldOwner = getLink().eContainer();

		if(reorientDirection == ReorientRelationshipRequest.REORIENT_SOURCE) {
			StructuredClassifier newOwner = deduceContainer(reorientedEdgeView.getTarget(), newEndView);
			if (oldOwner != newOwner) {
				replaceOwner(getLink(), newOwner);
			}
			return reorientSource();
		}
		
		if(reorientDirection == ReorientRelationshipRequest.REORIENT_TARGET) {
			StructuredClassifier newOwner = deduceContainer(reorientedEdgeView.getSource(), newEndView);
			if (oldOwner != newOwner) {
				replaceOwner(getLink(), newOwner);
			}
			return reorientTarget();
		}
		
		throw new IllegalStateException();
	}

	protected CommandResult reorientSource() throws ExecutionException {
		// Nothing to do here on the connector itself, the connector end remains
		// the same but related to new Port or PartWithPort.
		ConnectorEnd reorientedEnd = getLink().getEnds().get(0);
		ConnectorEnd oppositeEnd = getLink().getEnds().get(1);
		return reorientEnd(reorientedEnd, oppositeEnd, (ConnectableElement)getNewSource(), getNewPartWithPort(), getNewOppositePartWithPort());
	}

	protected CommandResult reorientTarget() throws ExecutionException {
		// Nothing to do here on the connector itself, the connector end remains
		// the same but related to new Port or PartWithPort.		
		ConnectorEnd reorientedEnd = getLink().getEnds().get(1);
		ConnectorEnd oppositeEnd = getLink().getEnds().get(0);
		return reorientEnd(reorientedEnd, oppositeEnd, (ConnectableElement)getNewTarget(), getNewPartWithPort(), getNewOppositePartWithPort());
	}

	private CommandResult reorientEnd(ConnectorEnd end, ConnectorEnd oppositeEnd, ConnectableElement role, Property partWithPort, Property oppositePartWithPort) throws ExecutionException {
		end.setRole(role);
		end.setPartWithPort(partWithPort);
		oppositeEnd.setPartWithPort(oppositePartWithPort);
		return CommandResult.newOKCommandResult();
	}

	/**
	 * Get the link to re-orient.
	 * 
	 * @return the edited {@link Connector}
	 */
	protected Connector getLink() {
		return (Connector)getElementToEdit();
	}

	/**
	 * Get the old {@link Connector} source.
	 * 
	 * @return the previous {@link Connector} source.
	 */
	protected Element getOldSource() {
		return (Element)oldEnd;
	}

	/**
	 * Get the new {@link Connector} source.
	 * 
	 * @return the new {@link Connector} source.
	 */
	protected Element getNewSource() {
		return (Element)newEnd;
	}

	/**
	 * Get the old {@link Connector} target.
	 * 
	 * @return the previous {@link Connector} target.
	 */
	protected Element getOldTarget() {
		return (Element)oldEnd;
	}

	/**
	 * Get the new {@link Connector} target.
	 * 
	 * @return the new {@link Connector} target.
	 */
	protected Element getNewTarget() {
		return (Element)newEnd;
	}

	/**
	 * Get the new {@link Connector} end graphical parent.
	 * 
	 * @return the new {@link Connector} end graphical parent.
	 */
	protected Element getEndParent(View endView) {
		EObject parent = ViewUtil.getContainerView(endView).getElement();
		return (parent instanceof Element) ? (Element)parent : null;
	}

	private void replaceOwner(Connector connector, StructuredClassifier newOwner) {
		// Change owner and Connector name (possibly already exists in new container)
		if (newOwner.getOwnedConnector(connector.getName()) != null) {
			String replacementName = NamedElementHelper.getDefaultNameWithIncrementFromBase("connector", newOwner.eContents()); // //$NON-NLS-0$
			connector.setName(replacementName);
		}
		// Replace connector owner
		newOwner.getOwnedConnectors().add(connector);
	}
	
	/**
	 * Get the new {@link Connector} end part with port.
	 * 
	 * @return the new {@link Connector} end part with port.
	 */
	private Property getNewPartWithPort() {
		Property partWithPort = null;
		Element newEndParent = getEndParent(newEndView);
		if(newEnd instanceof Port) {
			// Only look for PartWithPort if the role is a Port.
			
			if((newEndParent != null) && (newEndParent instanceof Property) && !(newEndParent instanceof Port)) {
				// Only add PartWithPort for assembly (not for delegation)
				if(!EcoreUtil.isAncestor(ViewUtil.getContainerView(newEndView), oppositeEndView)) {
					partWithPort = (Property)newEndParent;
				}
			}
		}
		return partWithPort;
	}

	/**
	 * Get the new {@link Connector} opposite end part with port.
	 * 
	 * @return the new {@link Connector} opposite end part with port.
	 */
	private Property getNewOppositePartWithPort() {
		Property partWithPort = null;
		Element oppositeEndParent = getEndParent(oppositeEndView);
		if(oppositeEndView.getElement() instanceof Port) {
			// Only look for PartWithPort if the role is a Port.
				
			if((oppositeEndParent != null) && (oppositeEndParent instanceof Property) && !(oppositeEndParent instanceof Port)) {
				// Only add PartWithPort for assembly (not for delegation)
				if(!EcoreUtil.isAncestor(ViewUtil.getContainerView(oppositeEndView), newEndView)) {
					partWithPort = (Property)oppositeEndParent;
				}
			}
		}
		return partWithPort;
	}
			
	private StructuredClassifier deduceContainer(View source, View target) {
		return new ConnectorUtils().deduceContainer(source, target);
	}
}
