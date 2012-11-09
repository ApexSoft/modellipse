package kr.co.apexsoft.modellipse.custom.editors.message.dialogs;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.FormDialog;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.UMLFactory;

public class CreateOrModifySignatureDialog extends FormDialog {

	private EObject selectedElement = null;
	
	private EClass selectedType = null;

	private String selectedName = null;

	private EObject selectedParent = null;


	private String title;
	
	private Map<EClass, List<EObject>> mapTypesPossibleParents;
	
	private ILabelProvider typeLabelProvider;
	
	private ILabelProvider elementLabelProvider;
	
	private TransactionalEditingDomain editingDomain;
	
	
	private Button creationRadio;
	
	private Combo creationTypeCombo;
	
	private ComboViewer typeComboViewer;
	
	private Text creationNameText;
	
	private Combo creationParentCombo;
	
	private ComboViewer parentComboViewer;
	
	private Button nothingRadio;
	
	public CreateOrModifySignatureDialog(Shell shell, String title, ILabelProvider typeLabelProvider, ILabelProvider elementLabelProvider,
			TransactionalEditingDomain editingDomain, Map<EClass, List<EObject>> mapTypes, EObject selectedParent) {
		super(shell);
		this.title = title;
		this.typeLabelProvider = typeLabelProvider;
		this.elementLabelProvider = elementLabelProvider;
		this.editingDomain = editingDomain;
		this.mapTypesPossibleParents = mapTypes;

		initSelected(selectedParent);
	}
	
	private void initSelected(EObject selectedParent) {
		if (selectedParent != null) {
			for (EClass type : mapTypesPossibleParents.keySet()) {
				List<EObject> parents = mapTypesPossibleParents.get(type);
				if (parents.contains(selectedParent)) {
					setParent(selectedParent);
					setType(type);
					break;
				}
			}
		}
	}
	
	/**
	 * Add the created element to its selected parent
	 */
	protected void addElementInParent(EObject selectedParent, EObject createdElement) {
		// Let the command find the relation on its own.
		Command addCmd = AddCommand.create(editingDomain, selectedParent, null, Collections.singleton(createdElement));
		if(addCmd.canExecute()) {
			editingDomain.getCommandStack().execute(addCmd);
		}
	}

	@Override
	protected void okPressed() {
		// create element if needed
		if(creationRadio.getSelection()) {
			selectedElement = UMLFactory.eINSTANCE.create(selectedType);
			if(selectedElement instanceof NamedElement) {
				((NamedElement)selectedElement).setName(selectedName);
			}
			addElementInParent(selectedParent, selectedElement);
		} else if(nothingRadio.getSelection()) {
			selectedElement = null;
		}
		super.okPressed();
	}

	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		super.createButtonsForButtonBar(parent);
		refreshOkButton();
	}

	@Override
	protected void createFormContent(IManagedForm mform) {
		ScrolledForm scrolledForm = mform.getForm();
		scrolledForm.setText(title);

		FormToolkit toolkit = mform.getToolkit();
		Composite parent = scrolledForm.getBody();
		parent.setLayout(new GridLayout());

		createCreationSection(scrolledForm.getBody(), toolkit);
		createNothingSection(scrolledForm.getBody(), toolkit);

		refreshSectionsEnable(creationRadio);
		hookListener();
		
		setName(null);
		
		scrolledForm.reflow(true);
	}

	/**
	 * Create the section to ask the user to select or create an element.
	 * 
	 * @param pParent
	 *        the section's parent widget
	 * @param pToolkit
	 *        the form toolkit
	 */
	private void createCreationSection(Composite pParent, FormToolkit pToolkit) {
		// create the section
		Section lSection = pToolkit.createSection(pParent, Section.EXPANDED | Section.TITLE_BAR);
		lSection.setLayoutData(new GridData(GridData.FILL_HORIZONTAL | GridData.GRAB_HORIZONTAL));

		ScrolledForm lInsideScrolledForm = pToolkit.createScrolledForm(lSection);
		lInsideScrolledForm.setExpandHorizontal(true);
		lInsideScrolledForm.setExpandVertical(true);
		Composite lBody = lInsideScrolledForm.getBody();

		lBody.setLayout(new GridLayout(2, false));

		// content of the section
		creationRadio = pToolkit.createButton(lBody, "Create a new element", SWT.RADIO);
		creationRadio.setSelection(false);
		creationRadio.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));

		Set<EClass> possibleTypes = mapTypesPossibleParents.keySet();
		EClass defaultType = selectedType;
		if (defaultType == null && possibleTypes.size() > 0) {
			defaultType = (EClass)possibleTypes.toArray()[0];
		}

		// only create the type selection buttons if more than one type
		// is possible
		if(possibleTypes.size() > 1) {
			pToolkit.createLabel(lBody, "Type:", SWT.NONE);
			creationTypeCombo = new Combo(lBody, SWT.DROP_DOWN | SWT.READ_ONLY);
			typeComboViewer = new ComboViewer(creationTypeCombo);
			pToolkit.adapt(creationTypeCombo);
			creationTypeCombo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
			typeComboViewer.setLabelProvider(typeLabelProvider);
			typeComboViewer.add(possibleTypes.toArray());
			typeComboViewer.setSelection(new StructuredSelection(defaultType));
		}

		pToolkit.createLabel(lBody, "Name:", SWT.NONE);
		creationNameText = pToolkit.createText(lBody, "", SWT.BORDER); //$NON-NLS-1$
		creationNameText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));

		pToolkit.createLabel(lBody, "Owner of the created element:", SWT.NONE);
		creationParentCombo = new Combo(lBody, SWT.DROP_DOWN | SWT.READ_ONLY);
		parentComboViewer = new ComboViewer(creationParentCombo);
		pToolkit.adapt(creationParentCombo);
		creationParentCombo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		parentComboViewer.setLabelProvider(elementLabelProvider);
		
		setType(defaultType);

		lInsideScrolledForm.reflow(true);
		lSection.setClient(lInsideScrolledForm);
	}
	
	private void createNothingSection(Composite pParent, FormToolkit pToolkit) {
		// create the section
		Section lSection = pToolkit.createSection(pParent, Section.EXPANDED | Section.TITLE_BAR);
		lSection.setLayoutData(new GridData(GridData.FILL_HORIZONTAL | GridData.GRAB_HORIZONTAL));

		ScrolledForm lInsideScrolledForm = pToolkit.createScrolledForm(lSection);
		lInsideScrolledForm.setExpandHorizontal(true);
		lInsideScrolledForm.setExpandVertical(true);
		Composite lBody = lInsideScrolledForm.getBody();

		lBody.setLayout(new GridLayout());

		// content of the section
		nothingRadio = pToolkit.createButton(lBody, "No element", SWT.RADIO);
		nothingRadio.setSelection(true);
		nothingRadio.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));

		lInsideScrolledForm.reflow(true);
		lSection.setClient(lInsideScrolledForm);
	}
	
	public EObject getSelected() {
		return selectedElement;
	}
	
	private void setName(String name) {
		selectedName = name;
		if (name != null) {
			creationNameText.setText(name);
		} else {
			creationNameText.setText("");
		}
		refreshOkButton();
	}
	
	private void setType(EClass type) {
		selectedType = type;
		List<EObject> possibleParents = mapTypesPossibleParents.get(type);
		if(possibleParents != null && possibleParents.size() > 0) {
			setParents(possibleParents, 0);
		} else {
			setParents(null, -1);
		}
		refreshOkButton();
	}
	
	private void setParent(EObject parent) {
		selectedParent = parent;
		refreshOkButton();
	}
	
	private void setParents(List<EObject> parents, int index) {
		parentComboViewer.refresh();
		if (parents == null || parents.size() == 0) {
			creationParentCombo.setEnabled(false);
		} else {
			parentComboViewer.add(parents.toArray());
			if (index < 0 || index >= parents.size()) {
				index = 0;
			}
			parentComboViewer.setSelection(new StructuredSelection(parents.get(index)));
			setParent(parents.get(index));
		}
		refreshOkButton();
	}
	
	private void refreshSectionsEnable(Object radioObject) {
		boolean nothingSelected = false;
		boolean creationSelected = false;
		if(creationRadio.equals(radioObject)) {
			creationSelected = true;
		} else {
			nothingSelected = true;
		}

		// Creation widgets
		creationRadio.setSelection(creationSelected);
		if(creationTypeCombo != null) {
			creationTypeCombo.setEnabled(creationSelected);
		}
		creationNameText.setEnabled(creationSelected);
		creationParentCombo.setEnabled(creationSelected);

		// Nothing widgets
		nothingRadio.setSelection(nothingSelected);
	}
	
	private void refreshOkButton() {
		Button okButton = getButton(IDialogConstants.OK_ID);
		if(okButton != null && !okButton.isDisposed()) {
			if(nothingRadio.getSelection()) {
				okButton.setEnabled(true);
			} else {
				okButton.setEnabled(selectedType != null && selectedParent != null && selectedName != null && !"".equals(selectedName)); //$NON-NLS-1$
			}
		}
	}
	
	private void hookListener() {
		
		SelectionListener radioListener = new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				refreshSectionsEnable(e.getSource());
				refreshOkButton();
			}
		};
		if (creationRadio != null)
			creationRadio.addSelectionListener(radioListener);
		if (nothingRadio != null)
			nothingRadio.addSelectionListener(radioListener);
		
		if (creationTypeCombo != null && typeComboViewer != null) {
			ModifyListener typeModifyListener = new ModifyListener() {
				@Override
				public void modifyText(ModifyEvent e) {
					ISelection sel = typeComboViewer.getSelection();
					if(sel instanceof IStructuredSelection) {
						Object type = ((IStructuredSelection)sel).getFirstElement();
						if(type instanceof EClass) {
							setType((EClass)type);
						} else {
							setType(null);
						}
						// reset name
						setName(null);
					}
				}
			};
			
			creationTypeCombo.addModifyListener(typeModifyListener);
		}
		
		if (creationParentCombo != null && parentComboViewer != null) {
			ModifyListener parentModifyListener = new ModifyListener() {
				@Override
				public void modifyText(ModifyEvent e) {
					ISelection sel = parentComboViewer.getSelection();
					if (sel instanceof IStructuredSelection) {
						Object parent = ((IStructuredSelection)sel).getFirstElement();
						if (parent instanceof NamedElement) {
							setParent((NamedElement)parent);
						} else {
							setParent(null);
						}
					}
				}
			};
			
			creationParentCombo.addModifyListener(parentModifyListener);
		}
		
		if (creationNameText != null) {
			ModifyListener nameModifyListener = new ModifyListener() {
				@Override
				public void modifyText(ModifyEvent e) {
					selectedName = creationNameText.getText();
					refreshOkButton();
				}
			};
			
			creationNameText.addModifyListener(nameModifyListener);
		}
	}
}
