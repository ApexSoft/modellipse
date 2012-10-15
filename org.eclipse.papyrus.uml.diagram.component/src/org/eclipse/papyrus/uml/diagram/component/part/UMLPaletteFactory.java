package org.eclipse.papyrus.uml.diagram.component.part;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.Tool;
import org.eclipse.gmf.runtime.diagram.ui.services.palette.PaletteFactory;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.papyrus.uml.diagram.common.service.AspectUnspecifiedTypeConnectionTool;
import org.eclipse.papyrus.uml.diagram.common.service.AspectUnspecifiedTypeCreationTool;
import org.eclipse.papyrus.uml.diagram.component.providers.UMLElementTypes;

/**
 * @generated
 */
public class UMLPaletteFactory extends PaletteFactory.Adapter {

	/**
	 * @generated
	 */
	private final static String COMPONENT_TOOL_PACKAGE = "component.tool.package"; //$NON-NLS-1$

	/**
	 * @generated
	 */
	private final static String COMPONENT_TOOL_COMPONENT = "component.tool.component"; //$NON-NLS-1$

	/**
	 * @generated
	 */
	private final static String COMPONENT_TOOL_INTERFACE = "component.tool.interface"; //$NON-NLS-1$

	/**
	 * @generated
	 */
	private final static String COMPONENT_TOOL_PORT = "component.tool.port"; //$NON-NLS-1$

	/**
	 * @generated
	 */
	private final static String COMPONENT_TOOL_COMMENT = "component.tool.comment"; //$NON-NLS-1$

	/**
	 * @generated
	 */
	private final static String COMPONENT_TOOL_CONSTRAINT = "component.tool.constraint"; //$NON-NLS-1$

	/**
	 * @generated
	 */
	private final static String COMPONENT_TOOL_LINK = "component.tool.link"; //$NON-NLS-1$

	/**
	 * @generated
	 */
	private final static String COMPONENT_TOOL_DEPENDENCY = "component.tool.dependency"; //$NON-NLS-1$

	/**
	 * @generated
	 */
	private final static String COMPONENT_TOOL_ABSTRACTION = "component.tool.abstraction"; //$NON-NLS-1$

	/**
	 * @generated
	 */
	private final static String COMPONENT_TOOL_MANIFESTATION = "component.tool.manifestation"; //$NON-NLS-1$

	/**
	 * @generated
	 */
	private final static String COMPONENT_TOOL_GENERALIZATION = "component.tool.generalization"; //$NON-NLS-1$

	/**
	 * @generated
	 */
	private final static String COMPONENT_TOOL_USAGE = "component.tool.usage"; //$NON-NLS-1$

	/**
	 * @generated
	 */
	private final static String COMPONENT_TOOL_SUBSTITUTION = "component.tool.substitution"; //$NON-NLS-1$

	/**
	 * @generated
	 */
	private final static String COMPONENT_TOOL_INTERFACEREALIZATION = "component.tool.interfaceRealization"; //$NON-NLS-1$

	/**
	 * @generated
	 */
	private final static String COMPONENT_TOOL_COMPONENTREALIZATION = "component.tool.componentRealization"; //$NON-NLS-1$

	/**
	 * @generated
	 */
	public UMLPaletteFactory() {

	}

	/**
	 * @generated
	 */
	public Tool createTool(String toolId) {
		if(toolId.equals(COMPONENT_TOOL_PACKAGE)) {
			return createPackageCreationTool();
		}
		if(toolId.equals(COMPONENT_TOOL_COMPONENT)) {
			return createComponentCreationTool();
		}
		if(toolId.equals(COMPONENT_TOOL_INTERFACE)) {
			return createInterfaceCreationTool();
		}
		if(toolId.equals(COMPONENT_TOOL_PORT)) {
			return createPortCreationTool();
		}
		if(toolId.equals(COMPONENT_TOOL_COMMENT)) {
			return createCommentCreationTool();
		}
		if(toolId.equals(COMPONENT_TOOL_CONSTRAINT)) {
			return createConstraintCreationTool();
		}
		if(toolId.equals(COMPONENT_TOOL_LINK)) {
			return createLinkCreationTool();
		}
		if(toolId.equals(COMPONENT_TOOL_DEPENDENCY)) {
			return createDependencyCreationTool();
		}
		if(toolId.equals(COMPONENT_TOOL_ABSTRACTION)) {
			return createAbstractionCreationTool();
		}
		if(toolId.equals(COMPONENT_TOOL_MANIFESTATION)) {
			return createManifestationCreationTool();
		}
		if(toolId.equals(COMPONENT_TOOL_GENERALIZATION)) {
			return createGeneralizationCreationTool();
		}
		if(toolId.equals(COMPONENT_TOOL_USAGE)) {
			return createUsageCreationTool();
		}
		if(toolId.equals(COMPONENT_TOOL_SUBSTITUTION)) {
			return createSubstitutionCreationTool();
		}
		if(toolId.equals(COMPONENT_TOOL_INTERFACEREALIZATION)) {
			return createInterfaceRealizationCreationTool();
		}
		if(toolId.equals(COMPONENT_TOOL_COMPONENTREALIZATION)) {
			return createComponentRealizationCreationTool();
		}

		// default return: null
		return null;
	}

	/**
	 * @generated
	 */
	public Object getTemplate(String templateId) {

		// default return: null
		return null;
	}

	/**
	 * @generated
	 */
	private Tool createPackageCreationTool() {
		List<IElementType> types = new ArrayList<IElementType>(1);
		types.add(UMLElementTypes.Package_3200);

		Tool tool = new AspectUnspecifiedTypeCreationTool(types);
		return tool;
	}

	/**
	 * @generated
	 */
	private Tool createComponentCreationTool() {
		List<IElementType> types = new ArrayList<IElementType>(3);
		types.add(UMLElementTypes.Component_2002);
		types.add(UMLElementTypes.Component_3070);
		types.add(UMLElementTypes.Component_3071);

		Tool tool = new AspectUnspecifiedTypeCreationTool(types);
		return tool;
	}

	/**
	 * @generated
	 */
	private Tool createInterfaceCreationTool() {
		List<IElementType> types = new ArrayList<IElementType>(2);
		types.add(UMLElementTypes.Interface_2003);
		types.add(UMLElementTypes.Interface_3072);

		Tool tool = new AspectUnspecifiedTypeCreationTool(types);
		return tool;
	}

	/**
	 * @generated
	 */
	private Tool createPortCreationTool() {
		List<IElementType> types = new ArrayList<IElementType>(1);
		types.add(UMLElementTypes.Port_3069);

		Tool tool = new AspectUnspecifiedTypeCreationTool(types);
		return tool;
	}

	/**
	 * @generated
	 */
	private Tool createCommentCreationTool() {
		List<IElementType> types = new ArrayList<IElementType>(2);
		types.add(UMLElementTypes.Comment_3201);
		types.add(UMLElementTypes.Comment_3074);

		Tool tool = new AspectUnspecifiedTypeCreationTool(types);
		return tool;
	}

	/**
	 * @generated
	 */
	private Tool createConstraintCreationTool() {
		List<IElementType> types = new ArrayList<IElementType>(2);
		types.add(UMLElementTypes.Constraint_3199);
		types.add(UMLElementTypes.Constraint_3075);

		Tool tool = new AspectUnspecifiedTypeCreationTool(types);
		return tool;
	}

	/**
	 * @generated
	 */
	private Tool createLinkCreationTool() {
		List<IElementType> types = new ArrayList<IElementType>(2);
		types.add(UMLElementTypes.CommentAnnotatedElement_4015);
		types.add(UMLElementTypes.ConstraintConstrainedElement_4009);

		Tool tool = new AspectUnspecifiedTypeConnectionTool(types);
		return tool;
	}

	/**
	 * @generated
	 */
	private Tool createDependencyCreationTool() {
		List<IElementType> types = new ArrayList<IElementType>(1);
		types.add(UMLElementTypes.Dependency_4010);

		Tool tool = new AspectUnspecifiedTypeConnectionTool(types);
		return tool;
	}

	/**
	 * @generated
	 */
	private Tool createAbstractionCreationTool() {
		List<IElementType> types = new ArrayList<IElementType>(1);
		types.add(UMLElementTypes.Abstraction_4013);

		Tool tool = new AspectUnspecifiedTypeConnectionTool(types);
		return tool;
	}

	/**
	 * @generated
	 */
	private Tool createManifestationCreationTool() {
		List<IElementType> types = new ArrayList<IElementType>(1);
		types.add(UMLElementTypes.Manifestation_4014);

		Tool tool = new AspectUnspecifiedTypeConnectionTool(types);
		return tool;
	}

	/**
	 * @generated
	 */
	private Tool createGeneralizationCreationTool() {
		List<IElementType> types = new ArrayList<IElementType>(1);
		types.add(UMLElementTypes.Generalization_4003);

		Tool tool = new AspectUnspecifiedTypeConnectionTool(types);
		return tool;
	}

	/**
	 * @generated
	 */
	private Tool createUsageCreationTool() {
		List<IElementType> types = new ArrayList<IElementType>(1);
		types.add(UMLElementTypes.Usage_4001);

		Tool tool = new AspectUnspecifiedTypeConnectionTool(types);
		return tool;
	}

	/**
	 * @generated
	 */
	private Tool createSubstitutionCreationTool() {
		List<IElementType> types = new ArrayList<IElementType>(1);
		types.add(UMLElementTypes.Substitution_4012);

		Tool tool = new AspectUnspecifiedTypeConnectionTool(types);
		return tool;
	}

	/**
	 * @generated
	 */
	private Tool createInterfaceRealizationCreationTool() {
		List<IElementType> types = new ArrayList<IElementType>(1);
		types.add(UMLElementTypes.InterfaceRealization_4006);

		Tool tool = new AspectUnspecifiedTypeConnectionTool(types);
		return tool;
	}

	/**
	 * @generated
	 */
	private Tool createComponentRealizationCreationTool() {
		List<IElementType> types = new ArrayList<IElementType>(1);
		types.add(UMLElementTypes.ComponentRealization_4007);

		Tool tool = new AspectUnspecifiedTypeConnectionTool(types);
		return tool;
	}

}
