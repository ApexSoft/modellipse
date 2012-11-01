package kr.co.apexsoft.modellipse.customization.diagram.sequence.commands;

import java.util.Collection;

import kr.co.apexsoft.modellipse.customization.diagram.sequence.util.ApexSequenceDiagramConstants;
import kr.co.apexsoft.modellipse.customization.diagram.sequence.util.ApexSequenceUtil;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewRequest.ViewDescriptor;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.View;

import org.eclipse.papyrus.uml.diagram.sequence.util.SequenceUtil;
import org.eclipse.uml2.uml.ExecutionSpecification;
import org.eclipse.uml2.uml.InteractionFragment;
import org.eclipse.uml2.uml.Message;

/**
 * @author Jiho
 * 
 */
public class ApexCreateAndMoveInteractionFragmentsCommand extends
		ApexMoveInteractionFragmentsCommand {

	private static final int MARGIN = ApexSequenceDiagramConstants.VERTICAL_MARGIN;
	
	private static final int PADDING = ApexSequenceDiagramConstants.EXECUTION_PADDING;
	
	private ViewDescriptor descriptor;
	
	private CreateElementAndNodeCommand createElementAndNodeCommand;
	
	public ApexCreateAndMoveInteractionFragmentsCommand(TransactionalEditingDomain domain, CreateElementAndNodeCommand command,
			ViewDescriptor descriptor, EditPartViewer viewer, InteractionFragment fragment, Point location) {
		super(domain, viewer, fragment, location, null, MARGIN);
	
		this.descriptor = descriptor;
		this.createElementAndNodeCommand = command;
	}

	@Override
	protected CommandResult doExecuteWithResult(IProgressMonitor monitor,
			IAdaptable info) throws ExecutionException {
		if (descriptor != null) {
			View view = (View)descriptor.getAdapter(View.class);
			if (view != null) {
				EObject eObject = view.getElement();
				if (eObject instanceof Message) {
					Message message = (Message)eObject;
					notToMoveEObject.add(message.getSendEvent());
					notToMoveEObject.add(message.getReceiveEvent());
				}
			}
		}

		if (createElementAndNodeCommand != null) {
			View view = createElementAndNodeCommand.getCreatedView();
			if (view != null) {
				EObject eObject = view.getElement();
				if (eObject instanceof ExecutionSpecification) {
					ExecutionSpecification execution = (ExecutionSpecification)eObject;
					notToMoveEObject.add(execution);
					notToMoveEObject.add(execution.getStart());
					notToMoveEObject.add(execution.getFinish());
				}
			}
		}
		
		return super.doExecuteWithResult(monitor, info);
	}

	@Override
	public Point getMoveDelta() {
		Point delta = super.getMoveDelta();
		if (delta == null && createElementAndNodeCommand != null) {
			View createdView = createElementAndNodeCommand.getCreatedView();
			if (createdView != null) {
				int height = (Integer)ViewUtil.getStructuralFeatureValue(createdView, NotationPackage.eINSTANCE.getSize_Height());
				delta = new Point(0, height);
				setMoveDelta(delta);
			}
		}
		return delta;
	}
	
	@Override
	public Point getRealMoveDelta(Point moveDelta, Collection<InteractionFragment> fragments) {
		Point newDelta = moveDelta.getCopy();
		for (InteractionFragment ift : fragments) {
			if (ift instanceof ExecutionSpecification) {
				ExecutionSpecification execution = (ExecutionSpecification)ift;
				IGraphicalEditPart editPart = getEditPart(execution);
				Rectangle bounds = ApexSequenceUtil.getAbsoluteBounds(editPart);
				
				if (bounds.y < getExtent().y && bounds.bottom() >= getExtent().y) {
					if (bounds.bottom() < getExtent().y + PADDING) {
						newDelta.y = Math.max(newDelta.y, moveDelta.y + getExtent().y + PADDING - bounds.bottom());
					}
				}
			}
		}
		return newDelta;
	}
}
