package org.eclipse.papyrus.uml.diagram.sequence.apex.part.tweaks;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.notation.Bounds;
import org.eclipse.gmf.runtime.notation.LayoutConstraint;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.papyrus.uml.diagram.sequence.edit.parts.LifelineEditPart;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Item;

public class TweakItem extends Item {
	
	private ILabelProvider fLabelProvider;
	private IStructuredContentProvider fContentProvider;
	private ILabelProvider fToolTipLabelProvider;

	private TweakViewer fParent;
	private Composite fContainer;
	
	private TweakItemDetails fDetailsBlock;
	
	public TweakItem(TweakViewer viewer, Composite parent) {
		super(parent, SWT.NONE);
		fParent = viewer;
		
		fContainer = new Composite(parent, SWT.BORDER);
		fContainer.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		GridLayout layout = new GridLayout(1, false);
		layout.marginBottom= 1;
		layout.marginHeight= 0;
		layout.marginWidth= 0;
		layout.horizontalSpacing= 0;
		fContainer.setLayout(layout);
		
		fDetailsBlock = new TweakItemDetails(fContainer);
	}
	
	/**
	 * Returns this items viewer
	 * 
	 * @return the viewer showing this item
	 */
	public TweakViewer getViewer() {
		return fParent;
	}
	
	@Override
	public void dispose() {
		fContainer.dispose();
		super.dispose();
	}

	@Override
	public void setImage(Image image) {
		super.setImage(image);
		fDetailsBlock.setImage(image);
	}

	@Override
	public void setText(String string) {
		super.setText(string);
		fDetailsBlock.setText(string);
	}
	
	/**
	 * Sets the label provider of this item.
	 *
	 * @param labelProvider the label provider to use
	 */
	public void setLabelProvider(ILabelProvider labelProvider) {
		fLabelProvider = labelProvider;
	}
	
	/**
	 * Sets the content provider of this item.
	 *
	 * @param contentProvider the content provider to use
	 */
	public void setContentProvider(IStructuredContentProvider contentProvider) {
		fContentProvider = contentProvider;
	}
	
	/**
	 * Sets the the label provider for the tool tips of this item.
	 *
	 * @param toolTipLabelProvider the label provider for the tool tips
	 */
	public void setToolTipLabelProvider(ILabelProvider toolTipLabelProvider) {
		fToolTipLabelProvider = toolTipLabelProvider;
	}

	void refresh() {
		String text = fLabelProvider.getText(getData());
		Image image = fLabelProvider.getImage(getData());
		String toolTip = fToolTipLabelProvider.getText(getData());
		
		fDetailsBlock.setText(text);
		fDetailsBlock.setImage(image);
		fDetailsBlock.setToopTip(toolTip);
		
		if (getData() instanceof Node) {
			int l = 0, r = 0;
			
			Node node = (Node)getData();
			LayoutConstraint constraint = node.getLayoutConstraint();
			if (constraint instanceof Bounds) {
				Bounds b = (Bounds)constraint;
				l = b.getX();
				r = b.getWidth() == SWT.DEFAULT ? l + LifelineEditPart.DEFAULT_FIGURE_WIDTH : l + ((Bounds)constraint).getWidth();
			}
			
			Object layoutData = fContainer.getLayoutData();
			if (layoutData instanceof FormData == false) {
				layoutData = new FormData();
			}
			
			int hOffset = fParent.getHorizontalOffset();
			((FormData)layoutData).left = new FormAttachment(0, l + hOffset);
			((FormData)layoutData).right = new FormAttachment(0, r + hOffset);
			fContainer.setLayoutData(layoutData);
		} else if (getData() instanceof IGraphicalEditPart) {
			IGraphicalEditPart editPart = (IGraphicalEditPart) getData();
			IFigure figure = editPart.getFigure();
			Rectangle bounds = figure.getBounds().getCopy();
			
			if (true/*changedBounds*/) {
				View view = editPart.getNotationView();
				if (view instanceof Node) {
					LayoutConstraint constraint = ((Node)view).getLayoutConstraint();
					if (constraint instanceof Bounds) {
						bounds.x = ((Bounds)constraint).getX();
						bounds.width = ((Bounds)constraint).getWidth();
					}
				}
			}
			
			figure.translateToAbsolute(bounds);

			Object layoutData = fContainer.getLayoutData();
			if (layoutData instanceof FormData == false) {
				layoutData = new FormData();
			}
			
			int hOffset = 0;
			((FormData)layoutData).left = new FormAttachment(0, bounds.x + hOffset);
			((FormData)layoutData).right = new FormAttachment(0, bounds.right() + hOffset);
			fContainer.setLayoutData(layoutData);
		}
	}
	
	boolean hasFocus() {
		return false;
	}
	
	void setFocus(boolean state) {
		
	}
	
	void setSelected(boolean selected) {
		
	}
}
