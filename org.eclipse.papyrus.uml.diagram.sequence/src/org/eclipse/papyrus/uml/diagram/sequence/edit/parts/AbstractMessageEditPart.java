package org.eclipse.papyrus.uml.diagram.sequence.edit.parts;

import java.util.ArrayList;
import java.util.List;

import kr.co.apexsoft.modellipse.customization.diagram.sequence.tools.ApexSelectConnectionEditPartTracker;

import org.eclipse.draw2d.ConnectionLocator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.requests.ReconnectRequest;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateUnspecifiedTypeConnectionRequest;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.papyrus.infra.gmfdiag.common.editpolicies.IMaskManagedLabelEditPolicy;
import org.eclipse.papyrus.uml.diagram.common.figure.edge.UMLEdgeFigure;
import org.eclipse.papyrus.uml.diagram.sequence.edit.policies.MessageLabelEditPolicy;
import org.eclipse.papyrus.uml.diagram.sequence.providers.UMLElementTypes;
import org.eclipse.uml2.uml.Message;

public abstract class AbstractMessageEditPart extends ConnectionNodeEditPart {
	
	/* apex added start */
	// Jiho: MessageSync~MessageFound�� ��� �������̽�
	public interface MessageFigure {
	}
	/* apex added end */
	
	private List messageEventParts;
	
	public AbstractMessageEditPart(View view) {
		super(view);
	}
	
	public View findChildByModel(EObject model) {
		List list = getModelChildren();
		if(list != null && list.size() > 0) {
			for(Object o : list) {
				if(!(o instanceof View) )
					continue;
				
				View view = (View) o;
				if(view.getElement() == model){
					return view;
				}
			}
		}
		return null;
	}

	@Override
	public List getChildren() {
		if (messageEventParts == null) {
			initMessageEventPart();
		}
		return super.getChildren();
	}
	
	protected void initMessageEventPart() {
		messageEventParts = new ArrayList();

		EObject element = this.resolveSemanticElement();
		if (!(element instanceof Message))
			return;
		Message message = (Message) element;
		UMLEdgeFigure edgeFigure = (UMLEdgeFigure) this.getFigure();
		final MessageEndEditPart sendEventPart = new MessageEndEditPart(
				message.getSendEvent(), this, new ConnectionLocator(edgeFigure,
						ConnectionLocator.SOURCE));
		messageEventParts.add(sendEventPart);

		final MessageEndEditPart receiveEventPart = new MessageEndEditPart(
				message.getReceiveEvent(), this, new ConnectionLocator(
						edgeFigure, ConnectionLocator.TARGET));
		messageEventParts.add(receiveEventPart);

		Diagram diagram = ((View) this.getModel()).getDiagram();
		sendEventPart.rebuildLinks(diagram);
		receiveEventPart.rebuildLinks(diagram);
		
		addChild(sendEventPart, -1);
		addChild(receiveEventPart, -1);
	}
	
	@Override
	protected void createDefaultEditPolicies() {
		super.createDefaultEditPolicies();
		installEditPolicy(IMaskManagedLabelEditPolicy.MASK_MANAGED_LABEL_EDIT_POLICY, new MessageLabelEditPolicy());
	}
	
	@Override
	public EditPart getTargetEditPart(Request request) {
		if(request instanceof CreateUnspecifiedTypeConnectionRequest){
			List types = ((CreateUnspecifiedTypeConnectionRequest) request).getElementTypes();
			if(types.contains(UMLElementTypes.Message_4009) || types.contains(UMLElementTypes.Message_4008)){
				return null;
			}
		}else if(request instanceof ReconnectRequest){
			ConnectionEditPart con = ((ReconnectRequest)request).getConnectionEditPart();
			if(con instanceof Message7EditPart || con instanceof Message6EditPart){
				return null;
			}
		}
		return super.getTargetEditPart(request);
	}
	
	/**
	 * apex updated
	 */
	@Override
	public DragTracker getDragTracker(Request req) {
		/* apex improved start */
		return new ApexSelectConnectionEditPartTracker(this);
		/* apex improved end */
		/* apex replaced
		return super.getDragTracker(req);
		//*/
	}
	
}
