package kr.co.apexsoft.modellipse.custom.editors.message;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import kr.co.apexsoft.modellipse.custom.editors.IPopupEditorInputFactory;
import kr.co.apexsoft.modellipse.custom.editors.PopupEditorConfiguration;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.databinding.observable.value.IObservableValue;
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
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.papyrus.commands.wrappers.GMFtoEMFCommandWrapper;
import org.eclipse.papyrus.extensionpoints.editors.ui.IPopupEditorHelper;
import org.eclipse.papyrus.uml.tools.databinding.PapyrusObservableValue;
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
	
	private static final String CREATE_LABEL = "Create";
	private static final String SELECT_LABEL = "Select";

	public MessagePopupEditor() {
		// TODO Auto-generated constructor stub
	}
	
	private TransactionalEditingDomain editingDomain;
	
	private Message message;
	
	private IObservableValue modelProperty;

	@Override
	public IPopupEditorHelper createPopupEditorHelper(Object editPart) {
		IGraphicalEditPart graphicalEditPart = null;
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
		
		modelProperty = new PapyrusObservableValue(message, UMLPackage.eINSTANCE.getMessage_Signature(), editingDomain);

		final List<Object> contents = new ArrayList<Object>();
		List<EObject> signatures = getSignature(source, target, message.getMessageSort());
		contents.add(CREATE_LABEL);
		contents.add(SELECT_LABEL);
		if (signatures != null && signatures.size() > 0) {
			contents.addAll(signatures);
		}
		
		IPopupEditorInputFactory factory = getPopupEditorInputFactory(contents);
		
		return super.createPopupEditorHelper(editPart, EditorType.LIST, factory);
	}
	
	protected IPopupEditorInputFactory getPopupEditorInputFactory(final Object contents) {
		return new IPopupEditorInputFactory() {
			
			@Override
			public ILabelProvider getLabelProvider() {
				return new MessagePopupEditorLabelProvider();
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
					System.out.println(object);
				} else if (SELECT_LABEL.equals(object)) {
					System.out.println(object);
				} else if (object instanceof NamedElement) {
					NamedElement element = (NamedElement)object;
					handleChangedSignature(element);
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
						} else if (SELECT_LABEL.equals(arg0)) {
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
	
	private void handleChangedSignature(NamedElement element) {
		if (modelProperty != null) {
			modelProperty.setValue(element);
		}
//		if (message != null && editingDomain != null) {
//			Command command = new GMFtoEMFCommandWrapper(new UpdateSignatureCommand(editingDomain, message, element));
//			editingDomain.getCommandStack().execute(command);
//		}
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
	
	private List<EObject> getSignature(Element source, Element target, MessageSort messageSort) {

		List<EObject> existingElements = new ArrayList<>();
		Map<EClass, List<EObject>> mapTypesPossibleParents = getTypesPossibleParents(source, target, messageSort);
		
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
			super(domain, "update signature command", null);
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
