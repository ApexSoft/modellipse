package org.eclipse.papyrus.uml.diagram.sequence.apex.edit.policies;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.Border;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.gef.editpolicies.NonResizableEditPolicy;
import org.eclipse.gef.handles.AbstractHandle;

/**
 * @author Jiho
 *
 * InteractionInteractionCompartmentEditPart의 drag를 막아놓은 EditPolicy
 */
public class ApexNonMovableEditPolicy extends NonResizableEditPolicy {

	public ApexNonMovableEditPolicy() {
		super();
		setDragAllowed(false);
	}

	@Override
	protected List createSelectionHandles() {
		List list = new ArrayList();
		createMoveHandle(list);
		
		// remove all drag handles

		for (int i = 0, size = list.size(); i < size; i++) {
			if (list.get(i) instanceof AbstractHandle) {
				AbstractHandle handle = (AbstractHandle)list.get(i);
				Border border = handle.getBorder();
				if (border instanceof LineBorder) {
					((LineBorder)border).setWidth(2);
					handle.setBorder(border);
				}
			}
		}
		
		return list;
	}

}
