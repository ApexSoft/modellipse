package org.eclipse.papyrus.uml.diagram.sequence.apex.part;

import org.eclipse.gef.ui.parts.ScrollingGraphicalViewer;
import org.eclipse.gef.ui.rulers.RulerComposite;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.papyrus.infra.core.services.ServiceException;
import org.eclipse.papyrus.infra.core.services.ServicesRegistry;
import org.eclipse.papyrus.uml.diagram.sequence.apex.part.tweaks.ITweak;
import org.eclipse.papyrus.uml.diagram.sequence.part.UMLDiagramEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IPerspectiveDescriptor;

public class ApexUMLDiagramEditor extends UMLDiagramEditor {

	public static final String EDITOR_SHOW_TWEAK = "tweak";
	
	public ApexUMLDiagramEditor(ServicesRegistry servicesRegistry,
			Diagram diagram) throws ServiceException {
		super(servicesRegistry, diagram);
	}

	private boolean fIsTweakVisible;

	private Composite fTweakComposite;
	private ITweak fTweak;
	
	private IPropertyChangeListener fPreferenceStoreChangeListener = new PreferenceStoreChangeListener();
	
	@Override
	public void createPartControl(Composite parent) {
		super.createPartControl(parent);
		
		fTweak = createTweak();
		fTweak.init();
		fIsTweakVisible = isTweakShown();
		if (fIsTweakVisible) {
			showTweak();
		}
	}

	@Override
	protected void createGraphicalViewer(Composite parent) {
        setRulerComposite(new RulerComposite(parent, SWT.NONE));

		Composite rulerComposite = getRulerComposite();
		GridLayout layout = new GridLayout(1, false);
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		layout.horizontalSpacing = 0;
		layout.verticalSpacing = 1;
		rulerComposite.setLayout(layout);
		rulerComposite.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
		
		fTweakComposite = new Composite(rulerComposite, SWT.NONE);
		GridData layoutData= new GridData(SWT.FILL, SWT.TOP, true, false);
		fTweakComposite.setLayoutData(layoutData);
		layout = new GridLayout(1, false);
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		layout.horizontalSpacing = 0;
		layout.verticalSpacing = 0;
		layoutData.exclude= true;
		fTweakComposite.setLayout(layout);
		
        ScrollingGraphicalViewer sGViewer = createScrollingGraphicalViewer();
        sGViewer.createControl(getRulerComposite());
        setGraphicalViewer(sGViewer);
        hookGraphicalViewer();
        configureGraphicalViewer();
        initializeGraphicalViewer();
        getRulerComposite().setGraphicalViewer(
            (ScrollingGraphicalViewer) getGraphicalViewer());
		
		Control control = sGViewer.getControl();
		control.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
	}

	@Override
	protected void initializeGraphicalViewer() {
		super.initializeGraphicalViewer();
		Object store = getPreferencesHint().getPreferenceStore();
		if (store instanceof IPreferenceStore) {
			((IPreferenceStore)store).addPropertyChangeListener(fPreferenceStoreChangeListener);
		}
	}

	public ITweak createTweak() {
		return new SequenceEditorTweak(this);
	}

	protected boolean isTweakShown() {
		Object store= getPreferencesHint().getPreferenceStore();
		String key= getTweakPreferenceKey();
		if (store instanceof IPreferenceStore && key != null) {
			return ((IPreferenceStore)store).getBoolean(key);
		}
		return false;
	}
	
	String getTweakPreferenceKey() {
		IPerspectiveDescriptor perspective = getSite().getPage().getPerspective();
		if (perspective == null) {
			return null;
		}
		return ApexUMLDiagramEditor.EDITOR_SHOW_TWEAK + "." + perspective.getId(); //$NON-NLS-1$;
	}

	private void showTweak() {
		if (fTweak == null)
			return;

		if (fTweakComposite.getChildren().length == 0) {
			fTweak.createContent(fTweakComposite);
		}

		((GridData) fTweakComposite.getLayoutData()).exclude= false;
		fTweakComposite.setVisible(true);
		
		fTweak.setInput(getDiagram());

		fTweakComposite.getParent().layout(true, true);
	}
	
	private void hideTweak() {
		if (fTweak == null) {
			return;
		}
		((GridData) fTweakComposite.getLayoutData()).exclude= true;
		fTweakComposite.setVisible(false);
		fTweakComposite.getParent().layout(true, true);
	}
	
	protected void handlePreferenceStoreChanged(PropertyChangeEvent event) {
		final String property = event.getProperty();
		
		try {
			boolean newBooleanValue = false;
			Object newValue = event.getNewValue();
			if (newValue != null) {
				newBooleanValue = Boolean.valueOf(newValue.toString()).booleanValue();
			}

			if (property.equals(getTweakPreferenceKey())) {
				if (newBooleanValue != fIsTweakVisible) {
					fIsTweakVisible = newBooleanValue;
					if (fIsTweakVisible) {
						showTweak();
					} else {
						hideTweak();
					}
				}
			}
		} finally {
			
		}
	}


	private class PreferenceStoreChangeListener implements IPropertyChangeListener {
		public void propertyChange(PropertyChangeEvent event) {
			handlePreferenceStoreChanged(event);
		}
	}
	
}
