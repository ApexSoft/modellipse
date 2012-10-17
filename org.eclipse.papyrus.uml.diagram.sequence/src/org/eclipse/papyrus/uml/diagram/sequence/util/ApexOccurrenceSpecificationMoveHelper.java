package org.eclipse.papyrus.uml.diagram.sequence.util;

import java.util.Collection;
import java.util.List;

import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gmf.runtime.common.core.util.StringStatics;
import org.eclipse.gmf.runtime.diagram.core.commands.SetConnectionAnchorsCommand;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.INodeEditPart;
import org.eclipse.gmf.runtime.draw2d.ui.figures.BaseSlidableAnchor;
import org.eclipse.gmf.runtime.emf.core.util.EObjectAdapter;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.papyrus.uml.diagram.common.util.DiagramEditPartsUtil;
import org.eclipse.papyrus.uml.diagram.sequence.draw2d.anchors.ApexHorizontalAnchor;
import org.eclipse.papyrus.uml.diagram.sequence.edit.parts.LifelineEditPart;
import org.eclipse.uml2.common.util.CacheAdapter;
import org.eclipse.uml2.uml.InteractionFragment;
import org.eclipse.uml2.uml.Message;
import org.eclipse.uml2.uml.MessageOccurrenceSpecification;
import org.eclipse.uml2.uml.OccurrenceSpecification;

/**
 * @author Jiho
 *
 */
public class ApexOccurrenceSpecificationMoveHelper {
	
	public static Command getMoveInteractionFragmentCommand(InteractionFragment movedInteractionFragment, int yLocation) {
		CompoundCommand command = new CompoundCommand();
		
		return command;
	}

	/**
	 * Get the complete command to move message occurrence specification.
	 * 
	 * @param movedOccurrenceSpecification
	 *        moved occurrence specification
	 * @param yLocation
	 *        y location where occurrence specification is moved
	 * @param newBounds
	 *        source or target edit part's bounds
	 * @param lifelinePart
	 *        lifeline edit part containing the moved element
	 * @param notToMoveEditParts
	 *        list of edit parts which must not be moved in the created command
	 * @return command to move all edit parts linked to the occurrence specifications or null
	 */

	public static Command getMoveMessageOccurrenceSpecificationsCommand(OccurrenceSpecification movedOccurrenceSpecification, int yLocation, Rectangle newBounds, EditPart childToReconnectTo, LifelineEditPart lifelinePart, List<EditPart> notToMoveEditParts) {
		// the global command which shall be completed and returned
		CompoundCommand command = new CompoundCommand();
		
		if (newBounds == null) {
			return OccurrenceSpecificationMoveHelper.getMoveOccurrenceSpecificationsCommand(movedOccurrenceSpecification, null, yLocation, -1, lifelinePart, notToMoveEditParts);
		}
		
		if(movedOccurrenceSpecification instanceof MessageOccurrenceSpecification) {
			Point referencePoint = getReferencePoint(lifelinePart, movedOccurrenceSpecification, yLocation);

			if (childToReconnectTo instanceof IGraphicalEditPart) {
				// reconnect message from the event
				Message message = ((MessageOccurrenceSpecification)movedOccurrenceSpecification).getMessage();
				if(message != null && movedOccurrenceSpecification.equals(message.getSendEvent())) {
					Collection<Setting> settings = CacheAdapter.getInstance().getNonNavigableInverseReferences(message);
					for(Setting ref : settings) {
						if(NotationPackage.eINSTANCE.getView_Element().equals(ref.getEStructuralFeature())) {
							View view = (View)ref.getEObject();
							EditPart part = DiagramEditPartsUtil.getEditPartFromView(view, lifelinePart);
							// the message part must start or finish on the lifeline (with the event)
							if(part instanceof ConnectionNodeEditPart && !notToMoveEditParts.contains(part)) {
								SetConnectionAnchorsCommand scaCmd = new SetConnectionAnchorsCommand(((IGraphicalEditPart)childToReconnectTo).getEditingDomain(), StringStatics.BLANK);
								PrecisionPoint pt = BaseSlidableAnchor.getAnchorRelativeLocation(referencePoint, newBounds);
								IFigure figure = ((IGraphicalEditPart)childToReconnectTo).getFigure();
								if (childToReconnectTo instanceof LifelineEditPart) {
									figure = ((LifelineEditPart)childToReconnectTo).getNodeFigure();
								}
								
								ConnectionAnchor sourceAnchor = new ApexHorizontalAnchor(figure, pt);
								scaCmd.setEdgeAdaptor(new EObjectAdapter(view));
								scaCmd.setNewSourceTerminal(((INodeEditPart)part).mapConnectionAnchorToTerminal(sourceAnchor));
								command.add(new ICommandProxy(scaCmd));
								// update enclosing interaction fragment
								Command updateIFrag = SequenceUtil.createUpdateEnclosingInteractionCommand((MessageOccurrenceSpecification)movedOccurrenceSpecification, referencePoint, lifelinePart);
								if(updateIFrag != null && updateIFrag.canExecute()) {
									command.add(updateIFrag);
								}
							}
						}
					}
				}
				// reconnect message to the event
				if(message != null && movedOccurrenceSpecification.equals(message.getReceiveEvent())) {
					Collection<Setting> settings = CacheAdapter.getInstance().getNonNavigableInverseReferences(message);
					for(Setting ref : settings) {
						if(NotationPackage.eINSTANCE.getView_Element().equals(ref.getEStructuralFeature())) {
							View view = (View)ref.getEObject();
							EditPart part = DiagramEditPartsUtil.getEditPartFromView(view, lifelinePart);
							// the message part must start or finish on the lifeline (with the event)
							if(part instanceof ConnectionNodeEditPart && !notToMoveEditParts.contains(part)) {
								SetConnectionAnchorsCommand scaCmd = new SetConnectionAnchorsCommand(((IGraphicalEditPart)childToReconnectTo).getEditingDomain(), StringStatics.BLANK);
								PrecisionPoint pt = BaseSlidableAnchor.getAnchorRelativeLocation(referencePoint, newBounds);
								IFigure figure = ((IGraphicalEditPart)childToReconnectTo).getFigure();
								if (childToReconnectTo instanceof LifelineEditPart) {
									figure = ((LifelineEditPart)childToReconnectTo).getNodeFigure();
								}
								ConnectionAnchor targetAnchor = new ApexHorizontalAnchor(figure, pt);
								scaCmd.setEdgeAdaptor(new EObjectAdapter(view));
								scaCmd.setNewSourceTerminal(((INodeEditPart)part).mapConnectionAnchorToTerminal(targetAnchor));
								command.add(new ICommandProxy(scaCmd));
								// update enclosing interaction fragment
								Command updateIFrag = SequenceUtil.createUpdateEnclosingInteractionCommand((MessageOccurrenceSpecification)movedOccurrenceSpecification, referencePoint, lifelinePart);
								if(updateIFrag != null && updateIFrag.canExecute()) {
									command.add(updateIFrag);
								}
							}
						}
					}
				}
			}
		}

		// return null rather than an empty non executable command
		if(command.isEmpty()) {
			return null;
		}
		return command;
	}

	/**
	 * Get the reference point to reconnect or resize edit parts at the given y location
	 * 
	 * @param lifelinePart
	 *        lifeline edit part containing the moved element
	 * @param movedOccurrenceSpecification
	 *        the moving occurrence specification which a reference point is searched for
	 * @param yLocation
	 *        y location
	 * @return reference point on the lifeline
	 */
	private static Point getReferencePoint(LifelineEditPart lifelinePart, OccurrenceSpecification movedOccurrenceSpecification, int yLocation) {
		Point referencePoint = SequenceUtil.findLocationOfEvent(lifelinePart, movedOccurrenceSpecification);
		if(referencePoint == null) {
			referencePoint = lifelinePart.getFigure().getBounds().getCenter().getCopy();
		}
		referencePoint.y = yLocation;
		return referencePoint;
	}

}
