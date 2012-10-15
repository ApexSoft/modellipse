package org.eclipse.papyrus.uml.diagram.component.providers;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.common.core.service.AbstractProvider;
import org.eclipse.gmf.runtime.common.core.service.IOperation;
import org.eclipse.gmf.runtime.common.ui.services.parser.GetParserOperation;
import org.eclipse.gmf.runtime.common.ui.services.parser.IParser;
import org.eclipse.gmf.runtime.common.ui.services.parser.IParserProvider;
import org.eclipse.gmf.runtime.common.ui.services.parser.ParserService;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.emf.ui.services.parser.ParserHintAdapter;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.papyrus.uml.diagram.common.parser.CommentParser;
import org.eclipse.papyrus.uml.diagram.common.parser.ConstraintParser;
import org.eclipse.papyrus.uml.diagram.common.parser.stereotype.AppliedStereotypeParser;
import org.eclipse.papyrus.uml.diagram.component.edit.parts.AbstractionAppliedStereotypeEditPart;
import org.eclipse.papyrus.uml.diagram.component.edit.parts.AbstractionNameEditPart;
import org.eclipse.papyrus.uml.diagram.component.edit.parts.CommentBodyEditPart;
import org.eclipse.papyrus.uml.diagram.component.edit.parts.CommentBodyEditPartPCN;
import org.eclipse.papyrus.uml.diagram.component.edit.parts.ComponentNameEditPart;
import org.eclipse.papyrus.uml.diagram.component.edit.parts.ComponentNameEditPartCN;
import org.eclipse.papyrus.uml.diagram.component.edit.parts.ComponentNameEditPartPCN;
import org.eclipse.papyrus.uml.diagram.component.edit.parts.ComponentRealizationAppliedStereotypeEditPart;
import org.eclipse.papyrus.uml.diagram.component.edit.parts.ComponentRealizationNameEditPart;
import org.eclipse.papyrus.uml.diagram.component.edit.parts.ConstraintNameEditPart;
import org.eclipse.papyrus.uml.diagram.component.edit.parts.ConstraintNameEditPartPCN;
import org.eclipse.papyrus.uml.diagram.component.edit.parts.ConstraintSpecificationEditPart;
import org.eclipse.papyrus.uml.diagram.component.edit.parts.ConstraintSpecificationEditPartPCN;
import org.eclipse.papyrus.uml.diagram.component.edit.parts.DependencyAppliedStereotypeEditPart;
import org.eclipse.papyrus.uml.diagram.component.edit.parts.DependencyNameEditPart;
import org.eclipse.papyrus.uml.diagram.component.edit.parts.GeneralizationAppliedStereotypeEditPart;
import org.eclipse.papyrus.uml.diagram.component.edit.parts.InterfaceNameEditPart;
import org.eclipse.papyrus.uml.diagram.component.edit.parts.InterfaceNameEditPartPCN;
import org.eclipse.papyrus.uml.diagram.component.edit.parts.ManifestationAppliedStereotypeEditPart;
import org.eclipse.papyrus.uml.diagram.component.edit.parts.ManifestationNameEditPart;
import org.eclipse.papyrus.uml.diagram.component.edit.parts.PackageNameEditPart;
import org.eclipse.papyrus.uml.diagram.component.edit.parts.PortAppliedStereotypeEditPart;
import org.eclipse.papyrus.uml.diagram.component.edit.parts.PortNameEditPart;
import org.eclipse.papyrus.uml.diagram.component.edit.parts.SubstitutionAppliedStereotypeEditPart;
import org.eclipse.papyrus.uml.diagram.component.edit.parts.SubstitutionNameEditPart;
import org.eclipse.papyrus.uml.diagram.component.parsers.MessageFormatParser;
import org.eclipse.papyrus.uml.diagram.component.part.UMLVisualIDRegistry;
import org.eclipse.uml2.uml.UMLPackage;

/**
 * @generated
 */
public class UMLParserProvider extends AbstractProvider implements IParserProvider {

	/**
	 * @generated
	 */
	private IParser componentName_5004Parser;

	/**
	 * @generated
	 */
	private IParser getComponentName_5004Parser() {
		if(componentName_5004Parser == null) {
			EAttribute[] features = new EAttribute[]{ UMLPackage.eINSTANCE.getNamedElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features);
			componentName_5004Parser = parser;
		}
		return componentName_5004Parser;
	}

	/**
	 * @generated
	 */
	private IParser interfaceName_5005Parser;

	/**
	 * @generated
	 */
	private IParser getInterfaceName_5005Parser() {
		if(interfaceName_5005Parser == null) {
			EAttribute[] features = new EAttribute[]{ UMLPackage.eINSTANCE.getNamedElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features);
			interfaceName_5005Parser = parser;
		}
		return interfaceName_5005Parser;
	}

	/**
	 * @generated
	 */
	private IParser packageName_5254Parser;

	/**
	 * @generated
	 */
	private IParser getPackageName_5254Parser() {
		if(packageName_5254Parser == null) {
			EAttribute[] features = new EAttribute[]{ UMLPackage.eINSTANCE.getNamedElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features);
			packageName_5254Parser = parser;
		}
		return packageName_5254Parser;
	}

	/**
	 * @generated
	 */
	private CommentParser commentBody_5255Parser;

	/**
	 * @generated
	 */
	private IParser getCommentBody_5255Parser() {
		if(commentBody_5255Parser == null) {
			commentBody_5255Parser = new CommentParser();
		}
		return commentBody_5255Parser;
	}

	/**
	 * @generated
	 */
	private IParser constraintName_5252Parser;

	/**
	 * @generated
	 */
	private IParser getConstraintName_5252Parser() {
		if(constraintName_5252Parser == null) {
			EAttribute[] features = new EAttribute[]{ UMLPackage.eINSTANCE.getNamedElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features);
			constraintName_5252Parser = parser;
		}
		return constraintName_5252Parser;
	}

	/**
	 * @generated
	 */
	private ConstraintParser constraintSpecification_5253Parser;

	/**
	 * @generated
	 */
	private IParser getConstraintSpecification_5253Parser() {
		if(constraintSpecification_5253Parser == null) {
			constraintSpecification_5253Parser = new ConstraintParser();
		}
		return constraintSpecification_5253Parser;
	}

	/**
	 * @generated
	 */
	private IParser portName_5006Parser;

	/**
	 * @generated
	 */
	private IParser getPortName_5006Parser() {
		if(portName_5006Parser == null) {
			EAttribute[] features = new EAttribute[]{ UMLPackage.eINSTANCE.getNamedElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features);
			portName_5006Parser = parser;
		}
		return portName_5006Parser;
	}

	/**
	 * @generated
	 */
	private AppliedStereotypeParser portName_5007Parser;

	/**
	 * @generated
	 */
	private IParser getPortName_5007Parser() {
		if(portName_5007Parser == null) {
			portName_5007Parser = new AppliedStereotypeParser();
		}
		return portName_5007Parser;
	}

	/**
	 * @generated
	 */
	private IParser componentName_5256Parser;

	/**
	 * @generated
	 */
	private IParser getComponentName_5256Parser() {
		if(componentName_5256Parser == null) {
			EAttribute[] features = new EAttribute[]{ UMLPackage.eINSTANCE.getNamedElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features);
			parser.setViewPattern("{0}"); //$NON-NLS-1$
			parser.setEditorPattern("{0}"); //$NON-NLS-1$
			parser.setEditPattern("{0}"); //$NON-NLS-1$
			componentName_5256Parser = parser;
		}
		return componentName_5256Parser;
	}

	/**
	 * @generated
	 */
	private IParser componentName_5257Parser;

	/**
	 * @generated
	 */
	private IParser getComponentName_5257Parser() {
		if(componentName_5257Parser == null) {
			EAttribute[] features = new EAttribute[]{ UMLPackage.eINSTANCE.getNamedElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features);
			parser.setViewPattern("{0}"); //$NON-NLS-1$
			parser.setEditorPattern("{0}"); //$NON-NLS-1$
			parser.setEditPattern("{0}"); //$NON-NLS-1$
			componentName_5257Parser = parser;
		}
		return componentName_5257Parser;
	}

	/**
	 * @generated
	 */
	private IParser interfaceName_0Parser;

	/**
	 * @generated
	 */
	private IParser getInterfaceName_0Parser() {
		if(interfaceName_0Parser == null) {
			EAttribute[] features = new EAttribute[]{ UMLPackage.eINSTANCE.getNamedElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features);
			interfaceName_0Parser = parser;
		}
		return interfaceName_0Parser;
	}

	/**
	 * @generated
	 */
	private CommentParser commentBody_5258Parser;

	/**
	 * @generated
	 */
	private IParser getCommentBody_5258Parser() {
		if(commentBody_5258Parser == null) {
			commentBody_5258Parser = new CommentParser();
		}
		return commentBody_5258Parser;
	}

	/**
	 * @generated
	 */
	private IParser constraintName_5259Parser;

	/**
	 * @generated
	 */
	private IParser getConstraintName_5259Parser() {
		if(constraintName_5259Parser == null) {
			EAttribute[] features = new EAttribute[]{ UMLPackage.eINSTANCE.getNamedElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features);
			constraintName_5259Parser = parser;
		}
		return constraintName_5259Parser;
	}

	/**
	 * @generated
	 */
	private ConstraintParser constraintSpecification_5260Parser;

	/**
	 * @generated
	 */
	private IParser getConstraintSpecification_5260Parser() {
		if(constraintSpecification_5260Parser == null) {
			constraintSpecification_5260Parser = new ConstraintParser();
		}
		return constraintSpecification_5260Parser;
	}

	/**
	 * @generated
	 */
	private AppliedStereotypeParser generalizationIsSubstitutable_2Parser;

	/**
	 * @generated
	 */
	private IParser getGeneralizationIsSubstitutable_2Parser() {
		if(generalizationIsSubstitutable_2Parser == null) {
			generalizationIsSubstitutable_2Parser = new AppliedStereotypeParser();
		}
		return generalizationIsSubstitutable_2Parser;
	}

	/**
	 * @generated
	 */
	private IParser substitutionName_6006Parser;

	/**
	 * @generated
	 */
	private IParser getSubstitutionName_6006Parser() {
		if(substitutionName_6006Parser == null) {
			EAttribute[] features = new EAttribute[]{ UMLPackage.eINSTANCE.getNamedElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features);
			substitutionName_6006Parser = parser;
		}
		return substitutionName_6006Parser;
	}

	/**
	 * @generated
	 */
	private AppliedStereotypeParser substitutionName_6020Parser;

	/**
	 * @generated
	 */
	private IParser getSubstitutionName_6020Parser() {
		if(substitutionName_6020Parser == null) {
			substitutionName_6020Parser = new AppliedStereotypeParser();
		}
		return substitutionName_6020Parser;
	}

	/**
	 * @generated
	 */
	private IParser manifestationName_6008Parser;

	/**
	 * @generated
	 */
	private IParser getManifestationName_6008Parser() {
		if(manifestationName_6008Parser == null) {
			EAttribute[] features = new EAttribute[]{ UMLPackage.eINSTANCE.getNamedElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features);
			manifestationName_6008Parser = parser;
		}
		return manifestationName_6008Parser;
	}

	/**
	 * @generated
	 */
	private AppliedStereotypeParser manifestationName_6022Parser;

	/**
	 * @generated
	 */
	private IParser getManifestationName_6022Parser() {
		if(manifestationName_6022Parser == null) {
			manifestationName_6022Parser = new AppliedStereotypeParser();
		}
		return manifestationName_6022Parser;
	}

	/**
	 * @generated
	 */
	private IParser componentRealizationName_3Parser;

	/**
	 * @generated
	 */
	private IParser getComponentRealizationName_3Parser() {
		if(componentRealizationName_3Parser == null) {
			EAttribute[] features = new EAttribute[]{ UMLPackage.eINSTANCE.getNamedElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features);
			componentRealizationName_3Parser = parser;
		}
		return componentRealizationName_3Parser;
	}

	/**
	 * @generated
	 */
	private AppliedStereotypeParser componentRealizationName_4Parser;

	/**
	 * @generated
	 */
	private IParser getComponentRealizationName_4Parser() {
		if(componentRealizationName_4Parser == null) {
			componentRealizationName_4Parser = new AppliedStereotypeParser();
		}
		return componentRealizationName_4Parser;
	}

	/**
	 * @generated
	 */
	private IParser abstractionName_6007Parser;

	/**
	 * @generated
	 */
	private IParser getAbstractionName_6007Parser() {
		if(abstractionName_6007Parser == null) {
			EAttribute[] features = new EAttribute[]{ UMLPackage.eINSTANCE.getNamedElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features);
			abstractionName_6007Parser = parser;
		}
		return abstractionName_6007Parser;
	}

	/**
	 * @generated
	 */
	private AppliedStereotypeParser abstractionName_6021Parser;

	/**
	 * @generated
	 */
	private IParser getAbstractionName_6021Parser() {
		if(abstractionName_6021Parser == null) {
			abstractionName_6021Parser = new AppliedStereotypeParser();
		}
		return abstractionName_6021Parser;
	}

	/**
	 * @generated
	 */
	private IParser dependencyName_6009Parser;

	/**
	 * @generated
	 */
	private IParser getDependencyName_6009Parser() {
		if(dependencyName_6009Parser == null) {
			EAttribute[] features = new EAttribute[]{ UMLPackage.eINSTANCE.getNamedElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features);
			dependencyName_6009Parser = parser;
		}
		return dependencyName_6009Parser;
	}

	/**
	 * @generated
	 */
	private AppliedStereotypeParser dependencyName_6023Parser;

	/**
	 * @generated
	 */
	private IParser getDependencyName_6023Parser() {
		if(dependencyName_6023Parser == null) {
			dependencyName_6023Parser = new AppliedStereotypeParser();
		}
		return dependencyName_6023Parser;
	}

	/**
	 * @generated
	 */
	protected IParser getParser(int visualID) {
		switch(visualID) {
		case ComponentNameEditPart.VISUAL_ID:
			return getComponentName_5004Parser();
		case InterfaceNameEditPart.VISUAL_ID:
			return getInterfaceName_5005Parser();
		case PackageNameEditPart.VISUAL_ID:
			return getPackageName_5254Parser();
		case CommentBodyEditPart.VISUAL_ID:
			return getCommentBody_5255Parser();
		case ConstraintNameEditPart.VISUAL_ID:
			return getConstraintName_5252Parser();
		case ConstraintSpecificationEditPart.VISUAL_ID:
			return getConstraintSpecification_5253Parser();
		case PortNameEditPart.VISUAL_ID:
			return getPortName_5006Parser();
		case PortAppliedStereotypeEditPart.VISUAL_ID:
			return getPortName_5007Parser();
		case ComponentNameEditPartCN.VISUAL_ID:
			return getComponentName_5256Parser();
		case ComponentNameEditPartPCN.VISUAL_ID:
			return getComponentName_5257Parser();
		case InterfaceNameEditPartPCN.VISUAL_ID:
			return getInterfaceName_0Parser();
		case CommentBodyEditPartPCN.VISUAL_ID:
			return getCommentBody_5258Parser();
		case ConstraintNameEditPartPCN.VISUAL_ID:
			return getConstraintName_5259Parser();
		case ConstraintSpecificationEditPartPCN.VISUAL_ID:
			return getConstraintSpecification_5260Parser();
		case GeneralizationAppliedStereotypeEditPart.VISUAL_ID:
			return getGeneralizationIsSubstitutable_2Parser();
		case SubstitutionNameEditPart.VISUAL_ID:
			return getSubstitutionName_6006Parser();
		case SubstitutionAppliedStereotypeEditPart.VISUAL_ID:
			return getSubstitutionName_6020Parser();
		case ManifestationNameEditPart.VISUAL_ID:
			return getManifestationName_6008Parser();
		case ManifestationAppliedStereotypeEditPart.VISUAL_ID:
			return getManifestationName_6022Parser();
		case ComponentRealizationNameEditPart.VISUAL_ID:
			return getComponentRealizationName_3Parser();
		case ComponentRealizationAppliedStereotypeEditPart.VISUAL_ID:
			return getComponentRealizationName_4Parser();
		case AbstractionNameEditPart.VISUAL_ID:
			return getAbstractionName_6007Parser();
		case AbstractionAppliedStereotypeEditPart.VISUAL_ID:
			return getAbstractionName_6021Parser();
		case DependencyNameEditPart.VISUAL_ID:
			return getDependencyName_6009Parser();
		case DependencyAppliedStereotypeEditPart.VISUAL_ID:
			return getDependencyName_6023Parser();
		}
		return null;
	}

	/**
	 * Utility method that consults ParserService
	 * 
	 * @generated
	 */
	public static IParser getParser(IElementType type, EObject object, String parserHint) {
		return ParserService.getInstance().getParser(new HintAdapter(type, object, parserHint));
	}

	/**
	 * @generated
	 */
	public IParser getParser(IAdaptable hint) {
		String vid = (String)hint.getAdapter(String.class);
		if(vid != null) {
			return getParser(UMLVisualIDRegistry.getVisualID(vid));
		}
		View view = (View)hint.getAdapter(View.class);
		if(view != null) {
			return getParser(UMLVisualIDRegistry.getVisualID(view));
		}
		return null;
	}

	/**
	 * @generated
	 */
	public boolean provides(IOperation operation) {
		if(operation instanceof GetParserOperation) {
			IAdaptable hint = ((GetParserOperation)operation).getHint();
			if(UMLElementTypes.getElement(hint) == null) {
				return false;
			}
			return getParser(hint) != null;
		}
		return false;
	}

	/**
	 * @generated
	 */
	private static class HintAdapter extends ParserHintAdapter {

		/**
		 * @generated
		 */
		private final IElementType elementType;

		/**
		 * @generated
		 */
		public HintAdapter(IElementType type, EObject object, String parserHint) {
			super(object, parserHint);
			assert type != null;
			elementType = type;
		}

		/**
		 * @generated
		 */
		public Object getAdapter(Class adapter) {
			if(IElementType.class.equals(adapter)) {
				return elementType;
			}
			return super.getAdapter(adapter);
		}
	}

}
