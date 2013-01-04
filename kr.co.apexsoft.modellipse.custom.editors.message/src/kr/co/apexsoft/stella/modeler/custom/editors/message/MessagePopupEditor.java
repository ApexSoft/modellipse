package kr.co.apexsoft.stella.modeler.custom.editors.message;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import kr.co.apexsoft.stella.modeler.custom.editors.IPopupEditorInputFactory;
import kr.co.apexsoft.stella.modeler.custom.editors.InitSelectedEditorHelper;
import kr.co.apexsoft.stella.modeler.custom.editors.PopupEditorConfiguration;
import kr.co.apexsoft.stella.modeler.custom.editors.message.dialogs.CreateOrModifySignatureDialog;
import kr.co.apexsoft.stella.modeler.custom.editors.message.providers.MessagePopupEditorLabelProvider;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditDomain;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.papyrus.commands.wrappers.GMFtoEMFCommandWrapper;
import org.eclipse.papyrus.extensionpoints.editors.ui.IPopupEditorHelper;
import org.eclipse.ui.IEditorPart;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.InteractionFragment;
import org.eclipse.uml2.uml.Lifeline;
import org.eclipse.uml2.uml.Message;
import org.eclipse.uml2.uml.MessageSort;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Port;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Signal;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.UMLPackage;

public class MessagePopupEditor extends PopupEditorConfiguration {
	
	private static final String CREATE_LABEL = Messages.MessagePopupEditor_Create_Label;
	private static final String DESELECT_LABEL = Messages.MessagePopupEditor_Delselect_Label;

	public MessagePopupEditor() {
		// TODO Auto-generated constructor stub
	}
	
	private TransactionalEditingDomain editingDomain;
	
	private Message message;
	
	private ILabelProvider elementLabelProvider;
	
	private ILabelProvider typeLabelProvider;
	
	private Map<EClass, List<EObject>> mapTypesPossibleParents;
	
	private Collection<EObject> signatures;
	
	private IGraphicalEditPart graphicalEditPart;
	
//	private IObservableValue modelProperty;

	@Override
	public IPopupEditorHelper createPopupEditorHelper(Object editPart) {
		graphicalEditPart = null;
		if(!(editPart instanceof IGraphicalEditPart)) {
			return null;
		}
		graphicalEditPart = (IGraphicalEditPart)editPart;
		if(!(graphicalEditPart.getTopGraphicEditPart().resolveSemanticElement() instanceof Message)) {
			return null;
		}
		
		ConnectionEditPart connectionEditPart = null;
		if (!(graphicalEditPart.getParent() instanceof ConnectionEditPart)) {
			return null;
		}
		connectionEditPart = (ConnectionEditPart)graphicalEditPart.getParent();
		
		EditPart sourceEditPart = connectionEditPart.getSource();
		EditPart targetEditPart = connectionEditPart.getTarget();
		Element source = (Element)sourceEditPart.getAdapter(Element.class);
		Element target = (Element)targetEditPart.getAdapter(Element.class);
		
		editingDomain = graphicalEditPart.getEditingDomain();
		message = (Message)graphicalEditPart.getTopGraphicEditPart().resolveSemanticElement();
		
//		modelProperty = new PapyrusObservableValue(message, UMLPackage.eINSTANCE.getMessage_Signature(), editingDomain);

		signatures = getSignature(source, target, message.getMessageSort());
		
		EditorType editorType = EditorType.NONE;
		final List<Object> contents = new ArrayList<Object>();
		contents.add(CREATE_LABEL);
		contents.add(DESELECT_LABEL);
		if (signatures != null && signatures.size() > 0) {
			contents.addAll(signatures);
			editorType = EditorType.LIST;
		}
		
		IPopupEditorInputFactory factory = getPopupEditorInputFactory(contents);

		IPopupEditorHelper helper = super.createPopupEditorHelper(editPart, editorType, factory);
		if (helper instanceof InitSelectedEditorHelper) {
			((InitSelectedEditorHelper)helper).setSelection(CREATE_LABEL);
		}
		return helper;
	}
	
	protected IPopupEditorInputFactory getPopupEditorInputFactory(final Object contents) {
		return new IPopupEditorInputFactory() {
			
			@Override
			public ILabelProvider getLabelProvider() {
				return getElementLabelProvider();
			}
			
			@Override
			public Object getInput() {
				return contents;
			}
			
			@Override
			public IContentProvider getContentprovider() {
				return new ArrayContentProvider();
			}

			@Override
			public void update(Object object) {
				if (CREATE_LABEL.equals(object)) {
					handleCreateSignature(null);
				} else if (DESELECT_LABEL.equals(object)) {
					handleChangedSignature(null);
				} else if (object instanceof NamedElement) {
					handleChangedSignature(object);
				}
			}

			@Override
			public Comparator<?> getComparator() {
				return new Comparator<Object>() {
					@Override
					public int compare(Object arg0, Object arg1) {
						if (arg0.equals(arg1)) {
							return 0;
						} else if (CREATE_LABEL.equals(arg0)) {
							return -1;
						} else if (DESELECT_LABEL.equals(arg0)) {
							if (CREATE_LABEL.equals(arg1)) {
								return 1;
							}
							return -1;
						}
						
						if (arg0 instanceof String && arg1 instanceof String) {
							return ((String)arg0).compareTo((String)arg1);
						}
						return 0;
					}
				};
			}
		};
	}
	
	private void handleCreateSignature(Object object) {
		try {
			IEditorPart editor = ((DiagramEditDomain)graphicalEditPart.getDiagramEditDomain()).getEditorPart();
			CreateOrModifySignatureDialog dialog = new CreateOrModifySignatureDialog(
					editor.getSite().getShell(), Messages.MessagePopupEditor_CreateOrModifySignatureDialog_Title, getTypeLabelProvider(), getElementLabelProvider(),
					editingDomain, mapTypesPossibleParents, null);
			if (IDialogConstants.OK_ID == dialog.open()) {
				EObject selectedElement = dialog.getSelected();
				if (selectedElement instanceof NamedElement) {
					handleChangedSignature(selectedElement);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void handleChangedSignature(Object object) {
//		if (modelProperty != null) {
//			modelProperty.setValue(object);
//		}
		if (message != null && editingDomain != null) {
			if (object == null || object instanceof NamedElement) {
				Command command = new GMFtoEMFCommandWrapper(
						new UpdateSignatureCommand(editingDomain, message, (NamedElement)object));
				editingDomain.getCommandStack().execute(command);
			}
		}
	}
	
	private ILabelProvider getElementLabelProvider() {
		if (elementLabelProvider == null) {
			elementLabelProvider = new MessagePopupEditorLabelProvider();
		}
		return elementLabelProvider;
	}
	
	private ILabelProvider getTypeLabelProvider() {
		if (typeLabelProvider == null) {
			typeLabelProvider = createTypeLabelProvider();
		}
		return typeLabelProvider;
	}
	
	private ILabelProvider createTypeLabelProvider() {
		return new MessagePopupEditorLabelProvider() {

			@Override
			public String getText(Object object) {
				String text = super.getText(object);
				int index = text.indexOf(" "); //$NON-NLS-1$
				if(index != -1) {
					text = text.substring(0, index);
				}
				return text;
			}
		};
	}
	
	private Map<EClass, List<EObject>> getTypesPossibleParents(Element source, Element target, MessageSort messageSort) {
		
		// element where to look for parents
		Element parentsOwner = target;

		// default values
		// used for asynch message where messageSort = null
		boolean useOperations = true;
		boolean useSignals = true;

		// according to the type of the message
		// choose which types we should care of
		if(MessageSort.SYNCH_CALL_LITERAL.equals(messageSort)) {
			useSignals = false;
		} else if(MessageSort.CREATE_MESSAGE_LITERAL.equals(messageSort) || MessageSort.DELETE_MESSAGE_LITERAL.equals(messageSort)) {
			useOperations = false;
		} else if(MessageSort.REPLY_LITERAL.equals(messageSort)) {
			parentsOwner = source;
			useSignals = false;
		}

		LinkedHashMap<EClass, List<EObject>> mapTypesPossibleParents = new LinkedHashMap<EClass, List<EObject>>();
		
		List<EObject> possiblePackage = null;
		List<EObject> possibleClassifier = null;
		if(useSignals) {
			possiblePackage = new LinkedList<>();
			mapTypesPossibleParents.put(UMLPackage.eINSTANCE.getSignal(), possiblePackage);
		}
		if(useOperations) {
			possibleClassifier = new LinkedList<>();
			mapTypesPossibleParents.put(UMLPackage.eINSTANCE.getOperation(), possibleClassifier);
		}
		
		List<Lifeline> lifelines = new ArrayList<>();
		if(parentsOwner instanceof InteractionFragment) {
			lifelines.addAll( ((InteractionFragment)parentsOwner).getCovereds() );
		} else if(parentsOwner instanceof Lifeline) {
			lifelines.add( (Lifeline)parentsOwner );
		}
		
		for(Lifeline l : lifelines) {
			if(l.getRepresents() != null && l.getRepresents().getType() != null) {
				Type type = l.getRepresents().getType();
				if (possiblePackage != null && type.getPackage() != null) {
					Package package_ = type.getPackage();
					possiblePackage.add(package_);
					possiblePackage.addAll(package_.allOwningPackages());
				}
				if (possibleClassifier != null && type instanceof Classifier) {
					Classifier classifier = (Classifier)type;
					possibleClassifier.add(classifier);
					possibleClassifier.addAll(classifier.allParents());
				}
			}
		}
		
		return mapTypesPossibleParents;
	}
	
	private Collection<EObject> getSignature(Element source, Element target, MessageSort messageSort) {

		Set<EObject> existingElements = new HashSet<EObject>();
		mapTypesPossibleParents = getTypesPossibleParents(source, target, messageSort);
		
		for(EClass eClass : mapTypesPossibleParents.keySet()) {
			List<EObject> parents = mapTypesPossibleParents.get(eClass);
			for(EObject parent : parents) {
				if(parent instanceof Classifier) {
					existingElements.addAll(((Classifier)parent).getAllOperations());
					
					// add operations from port
					EList<Property> attrs = ((Classifier)parent).getAllAttributes();
					for(Property p : attrs)
						if(p instanceof Port && p.getType() instanceof Classifier){
							existingElements.addAll(((Classifier)p.getType()).getAllOperations());
						}
					
				} else if(parent instanceof Package) {
					EList<Element> ownedElements = ((Package)parent).allOwnedElements();
					for(Element e : ownedElements) {
						if(e instanceof Signal) {
							existingElements.add(e);
						}
					}
				}
			}
		}

		return existingElements;
	}
	
	public class UpdateSignatureCommand extends AbstractTransactionalCommand {

		private Message message;
		private NamedElement signature;
		
		public UpdateSignatureCommand(TransactionalEditingDomain domain, Message message, NamedElement signature) {
			super(domain, "update signature command", null); //$NON-NLS-1$
			this.message = message;
			this.signature = signature;
		}

		@Override
		protected CommandResult doExecuteWithResult(IProgressMonitor monitor,
				IAdaptable info) throws ExecutionException {
			message.setSignature(signature);
			return CommandResult.newOKCommandResult(message);
		}
		
	}
}
