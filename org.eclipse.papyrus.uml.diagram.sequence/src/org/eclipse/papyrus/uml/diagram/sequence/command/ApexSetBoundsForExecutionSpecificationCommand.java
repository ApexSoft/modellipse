package org.eclipse.papyrus.uml.diagram.sequence.command;

import java.util.List;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.View;

/**
 * Target ExecutionSpecification의 위치에 따라 Source ExecutionSpecification의 Bounds 자동 조정
 * 
 * @deprecated Use {@link ApexCreateAndMoveInteractionFragmentsCommand}
 * @author Jiho 20120611
 */
public class ApexSetBoundsForExecutionSpecificationCommand extends
		AbstractTransactionalCommand {

	protected final static String COMMAND_LABEL = "Resize height of Source Execution Specification";
	
	private final static int EXECUTION_BOTTOM_MARGIN = 10;
	
	private CreateElementAndNodeCommand createElementAndNodeCommand;
	private IAdaptable adapter;
	private Rectangle bounds;
	private Rectangle oldBounds;
	
	public ApexSetBoundsForExecutionSpecificationCommand(
			TransactionalEditingDomain domain, CreateElementAndNodeCommand createElementAndNodeCommand, IAdaptable adapter) {
		super(domain, COMMAND_LABEL, null);
		Assert.isNotNull(adapter, "view cannot be null"); //$NON-NLS-1$
		this.createElementAndNodeCommand = createElementAndNodeCommand;
		this.adapter = adapter;
	}
	
	@Override
	protected CommandResult doExecuteWithResult(IProgressMonitor monitor,
			IAdaptable info) throws ExecutionException {
		if (adapter == null)
			return CommandResult.newErrorCommandResult("SetBoundsCommand: viewAdapter does not adapt to IView.class"); //$NON-NLS-1$
		
		View view  = (View)adapter.getAdapter(View.class);
		if (oldBounds == null)
			oldBounds = new Rectangle();
		oldBounds.x = (Integer)ViewUtil.getStructuralFeatureValue(view, NotationPackage.eINSTANCE.getLocation_X());
		oldBounds.y = (Integer)ViewUtil.getStructuralFeatureValue(view, NotationPackage.eINSTANCE.getLocation_Y());
		oldBounds.width = (Integer)ViewUtil.getStructuralFeatureValue(view, NotationPackage.eINSTANCE.getSize_Width());
		oldBounds.height = (Integer)ViewUtil.getStructuralFeatureValue(view, NotationPackage.eINSTANCE.getSize_Height());
		
		View createdView = createElementAndNodeCommand.getCreatedView();
		Point location = new Point();
		Dimension size = new Dimension();
		location.x = (Integer)ViewUtil.getStructuralFeatureValue(createdView, NotationPackage.eINSTANCE.getLocation_X());
		location.y = (Integer)ViewUtil.getStructuralFeatureValue(createdView, NotationPackage.eINSTANCE.getLocation_Y());
		size.width = (Integer)ViewUtil.getStructuralFeatureValue(createdView, NotationPackage.eINSTANCE.getSize_Width());
		size.height = (Integer)ViewUtil.getStructuralFeatureValue(createdView, NotationPackage.eINSTANCE.getSize_Height());
		int bottom = Math.max(oldBounds.getBottom().y, location.y + size.height + EXECUTION_BOTTOM_MARGIN);
		
		bounds = new Rectangle(oldBounds);
		bounds.height = bottom - oldBounds.y;
		
		ViewUtil.setStructuralFeatureValue(view, NotationPackage.eINSTANCE.getLocation_X(), Integer.valueOf(bounds.x));
		ViewUtil.setStructuralFeatureValue(view, NotationPackage.eINSTANCE.getLocation_Y(), Integer.valueOf(bounds.y));
		ViewUtil.setStructuralFeatureValue(view, NotationPackage.eINSTANCE.getSize_Width(), Integer.valueOf(bounds.width));
		ViewUtil.setStructuralFeatureValue(view, NotationPackage.eINSTANCE.getSize_Height(), Integer.valueOf(bounds.height));

		return CommandResult.newOKCommandResult();
	}

	@Override
    public List getAffectedFiles() {
        if (adapter != null) {
            View view = (View) adapter.getAdapter(View.class);
            if (view != null) {
                return getWorkspaceFiles(view);
            }
        }
        return super.getAffectedFiles();
    }
	
	/**
	 * Return size delta
	 */
	public Dimension getSizeDelta() {
		if (bounds != null && oldBounds != null) {
			return new Dimension(bounds.width - oldBounds.width, bounds.height - oldBounds.height);
		}
		return new Dimension(0, 0);
	}
}
