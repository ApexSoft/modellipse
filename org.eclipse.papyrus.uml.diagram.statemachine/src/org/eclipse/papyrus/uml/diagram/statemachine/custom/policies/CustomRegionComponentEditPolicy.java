package org.eclipse.papyrus.uml.diagram.statemachine.custom.policies;

import java.util.List;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.GroupRequest;
import org.eclipse.gmf.runtime.common.core.command.CompositeCommand;
import org.eclipse.gmf.runtime.common.core.util.StringStatics;
import org.eclipse.gmf.runtime.diagram.core.commands.SetPropertyCommand;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.ComponentEditPolicy;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.papyrus.uml.diagram.common.commands.SemanticAdapter;
import org.eclipse.papyrus.uml.diagram.statemachine.custom.commands.CustomRegionDeleteCommand;

public class CustomRegionComponentEditPolicy extends ComponentEditPolicy {

	@Override
	protected Command createDeleteViewCommand(GroupRequest deleteRequest) {
		CompositeCommand cc = new CompositeCommand(StringStatics.BLANK);

		TransactionalEditingDomain editingDomain = getEditingDomain();
		if(editingDomain == null) {
			return null;
		}
		List toDel = deleteRequest.getEditParts();
		if(toDel == null || toDel.isEmpty()) {
			cc.compose(new CustomRegionDeleteCommand(editingDomain, (View)getHost().getModel()));

			View compartmentView = (View)((View)getHost().getModel()).eContainer();
			// get and adaptable for it, to pass on to commands
			IAdaptable adaptableForCompartmentView = (IAdaptable)new SemanticAdapter(null, compartmentView);
			if(compartmentView.getChildren().size() == 1) {
				SetPropertyCommand showCompartment = new SetPropertyCommand(editingDomain, adaptableForCompartmentView, "notation.View.visible", "Visibility", false);
				cc.compose(showCompartment);
			}
		} else {
			for(int i = 0; i < toDel.size(); i++) {
				IGraphicalEditPart gep = (IGraphicalEditPart)toDel.get(i);
				cc.compose(new CustomRegionDeleteCommand(editingDomain, (View)gep.getModel()));
				View compartmentView = (View)((View)getHost().getModel()).eContainer();
				// get and adaptable for it, to pass on to commands
				IAdaptable adaptableForCompartmentView = (IAdaptable)new SemanticAdapter(null, compartmentView);
				if(compartmentView.getChildren().size() == toDel.size()) {
					SetPropertyCommand showCompartment = new SetPropertyCommand(editingDomain, adaptableForCompartmentView, "notation.View.visible", "Visibility", false);
					cc.compose(showCompartment);
				}
			}
		}
		return new ICommandProxy(cc.reduce());
	}

	private TransactionalEditingDomain getEditingDomain() {
		if(getHost() instanceof IGraphicalEditPart) {
			return ((IGraphicalEditPart)getHost()).getEditingDomain();
		} else if(getHost() instanceof IEditingDomainProvider) {
			Object domain = ((IEditingDomainProvider)getHost()).getEditingDomain();
			if(domain instanceof TransactionalEditingDomain) {
				return (TransactionalEditingDomain)domain;
			}
		}
		return null;
	}

}
