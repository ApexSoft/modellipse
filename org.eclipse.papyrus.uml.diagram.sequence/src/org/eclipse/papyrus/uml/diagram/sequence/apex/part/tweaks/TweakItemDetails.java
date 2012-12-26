package org.eclipse.papyrus.uml.diagram.sequence.apex.part.tweaks;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

public class TweakItemDetails {
	
	private Composite fParent;
	private Composite fDetailsComposite;
	private Composite fImageComposite;
	private Composite fTextComposite;
	private Label fElementImage;
	private Label fElementText;
	
	public TweakItemDetails(Composite parent) {
		fParent = parent;
		
		fDetailsComposite = new Composite(parent, SWT.NONE);
		fDetailsComposite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		GridLayout layout = new GridLayout(2, false);
		layout.horizontalSpacing = 0;
		layout.verticalSpacing = 0;
		layout.marginWidth = 0;
		layout.marginHeight = 0;
		fDetailsComposite.setLayout(layout);
		
		fImageComposite = new Composite(fDetailsComposite, SWT.NONE);
		layout = new GridLayout();
		layout.horizontalSpacing = 0;
		layout.verticalSpacing = 0;
		layout.marginWidth = 0;
		layout.marginHeight = 0;
		fImageComposite.setLayout(layout);
		fImageComposite.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING));
		fElementImage = new Label(fImageComposite, SWT.NONE);
		fElementImage.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING));
		
		fTextComposite = new Composite(fDetailsComposite, SWT.NONE);
		layout = new GridLayout();
		layout.horizontalSpacing = 0;
		layout.verticalSpacing = 0;
		layout.marginWidth = 0;
		layout.marginHeight = 0;
		fTextComposite.setLayout(layout);
		fTextComposite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		fElementText = new Label(fTextComposite, SWT.CENTER);
		fElementText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
	}
	
	public void setText(String text) {
		fElementText.setText(text);
	}
	
	public void setImage(Image image) {
		fElementImage.setImage(image);
	}
	
	public void setToopTip(String toolTip) {
		fElementText.getParent().setToolTipText(toolTip);
		fElementText.setToolTipText(toolTip);
		fElementImage.setToolTipText(toolTip);
	}

}
