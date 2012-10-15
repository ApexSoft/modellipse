package org.eclipse.papyrus.uml.diagram.component.providers;

import org.eclipse.papyrus.uml.diagram.common.helper.NamedElementHelper;
import org.eclipse.papyrus.uml.diagram.component.expressions.UMLOCLFactory;
import org.eclipse.papyrus.uml.diagram.component.part.UMLDiagramEditorPlugin;
import org.eclipse.uml2.uml.Abstraction;
import org.eclipse.uml2.uml.AggregationKind;
import org.eclipse.uml2.uml.Comment;
import org.eclipse.uml2.uml.Component;
import org.eclipse.uml2.uml.ComponentRealization;
import org.eclipse.uml2.uml.Constraint;
import org.eclipse.uml2.uml.Dependency;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.InterfaceRealization;
import org.eclipse.uml2.uml.LiteralString;
import org.eclipse.uml2.uml.Manifestation;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Port;
import org.eclipse.uml2.uml.Substitution;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.Usage;
import org.eclipse.uml2.uml.ValueSpecification;

/**
 * @generated
 */
public class ElementInitializers {

	protected ElementInitializers() {
		// use #getInstance to access cached instance
	}

	/**
	 * @generated
	 */
	public void init_Component_2002(Component instance) {
		try {
			Object value_0 = UMLOCLFactory.getExpression(0, UMLPackage.eINSTANCE.getComponent(), null).evaluate(instance);
			instance.setName((String)value_0);
		} catch (RuntimeException e) {
			UMLDiagramEditorPlugin.getInstance().logError("Element initialization failed", e); //$NON-NLS-1$						
		}
	}

	/**
	 * @generated
	 */
	public void init_Interface_2003(Interface instance) {
		try {
			Object value_0 = UMLOCLFactory.getExpression(2, UMLPackage.eINSTANCE.getInterface(), null).evaluate(instance);
			instance.setName((String)value_0);
		} catch (RuntimeException e) {
			UMLDiagramEditorPlugin.getInstance().logError("Element initialization failed", e); //$NON-NLS-1$						
		}
	}

	/**
	 * @generated
	 */
	public void init_Package_3200(Package instance) {
		try {
			Object value_0 = UMLOCLFactory.getExpression(5, UMLPackage.eINSTANCE.getPackage(), null).evaluate(instance);
			instance.setName((String)value_0);
		} catch (RuntimeException e) {
			UMLDiagramEditorPlugin.getInstance().logError("Element initialization failed", e); //$NON-NLS-1$						
		}
	}

	/**
	 * @generated
	 */
	public void init_Comment_3201(Comment instance) {
		try {
			Object value_0 = body_Comment_3201(instance);
			instance.setBody((String)value_0);
		} catch (RuntimeException e) {
			UMLDiagramEditorPlugin.getInstance().logError("Element initialization failed", e); //$NON-NLS-1$						
		}
	}

	/**
	 * @generated
	 */
	public void init_Constraint_3199(Constraint instance) {
		try {
			Object value_0 = name_Constraint_3199(instance);
			instance.setName((String)value_0);
			Object value_1 = specification_Constraint_3199(instance);
			instance.setSpecification((ValueSpecification)value_1);
		} catch (RuntimeException e) {
			UMLDiagramEditorPlugin.getInstance().logError("Element initialization failed", e); //$NON-NLS-1$						
		}
	}

	/**
	 * @generated
	 */
	public void init_Port_3069(Port instance) {
		try {
			Object value_0 = UMLOCLFactory.getExpression(1, UMLPackage.eINSTANCE.getPort(), null).evaluate(instance);
			instance.setName((String)value_0);
			instance.setAggregation(AggregationKind.COMPOSITE_LITERAL);
		} catch (RuntimeException e) {
			UMLDiagramEditorPlugin.getInstance().logError("Element initialization failed", e); //$NON-NLS-1$						
		}
	}

	/**
	 * @generated
	 */
	public void init_Component_3070(Component instance) {
		try {
			Object value_0 = UMLOCLFactory.getExpression(0, UMLPackage.eINSTANCE.getComponent(), null).evaluate(instance);
			instance.setName((String)value_0);
		} catch (RuntimeException e) {
			UMLDiagramEditorPlugin.getInstance().logError("Element initialization failed", e); //$NON-NLS-1$						
		}
	}

	/**
	 * @generated
	 */
	public void init_Component_3071(Component instance) {
		try {
			Object value_0 = UMLOCLFactory.getExpression(0, UMLPackage.eINSTANCE.getComponent(), null).evaluate(instance);
			instance.setName((String)value_0);
		} catch (RuntimeException e) {
			UMLDiagramEditorPlugin.getInstance().logError("Element initialization failed", e); //$NON-NLS-1$						
		}
	}

	/**
	 * @generated
	 */
	public void init_Interface_3072(Interface instance) {
		try {
			Object value_0 = UMLOCLFactory.getExpression(2, UMLPackage.eINSTANCE.getInterface(), null).evaluate(instance);
			instance.setName((String)value_0);
		} catch (RuntimeException e) {
			UMLDiagramEditorPlugin.getInstance().logError("Element initialization failed", e); //$NON-NLS-1$						
		}
	}

	/**
	 * @generated
	 */
	public void init_Comment_3074(Comment instance) {
		try {
			Object value_0 = body_Comment_3074(instance);
			instance.setBody((String)value_0);
		} catch (RuntimeException e) {
			UMLDiagramEditorPlugin.getInstance().logError("Element initialization failed", e); //$NON-NLS-1$						
		}
	}

	/**
	 * @generated
	 */
	public void init_Constraint_3075(Constraint instance) {
		try {
			Object value_0 = name_Constraint_3075(instance);
			instance.setName((String)value_0);
			Object value_1 = specification_Constraint_3075(instance);
			instance.setSpecification((ValueSpecification)value_1);
		} catch (RuntimeException e) {
			UMLDiagramEditorPlugin.getInstance().logError("Element initialization failed", e); //$NON-NLS-1$						
		}
	}

	/**
	 * @generated
	 */
	public void init_Usage_4001(Usage instance) {
		try {
			Object value_0 = UMLOCLFactory.getExpression(3, UMLPackage.eINSTANCE.getUsage(), null).evaluate(instance);
			instance.setName((String)value_0);
		} catch (RuntimeException e) {
			UMLDiagramEditorPlugin.getInstance().logError("Element initialization failed", e); //$NON-NLS-1$						
		}
	}

	/**
	 * @generated
	 */
	public void init_InterfaceRealization_4006(InterfaceRealization instance) {
		try {
			Object value_0 = UMLOCLFactory.getExpression(4, UMLPackage.eINSTANCE.getInterfaceRealization(), null).evaluate(instance);
			instance.setName((String)value_0);
		} catch (RuntimeException e) {
			UMLDiagramEditorPlugin.getInstance().logError("Element initialization failed", e); //$NON-NLS-1$						
		}
	}

	/**
	 * @generated
	 */
	public void init_Substitution_4012(Substitution instance) {
		try {
			Object value_0 = name_Substitution_4012(instance);
			instance.setName((String)value_0);
		} catch (RuntimeException e) {
			UMLDiagramEditorPlugin.getInstance().logError("Element initialization failed", e); //$NON-NLS-1$						
		}
	}

	/**
	 * @generated
	 */
	public void init_Manifestation_4014(Manifestation instance) {
		try {
			Object value_0 = name_Manifestation_4014(instance);
			instance.setName((String)value_0);
		} catch (RuntimeException e) {
			UMLDiagramEditorPlugin.getInstance().logError("Element initialization failed", e); //$NON-NLS-1$						
		}
	}

	/**
	 * @generated
	 */
	public void init_ComponentRealization_4007(ComponentRealization instance) {
		try {
			Object value_0 = name_ComponentRealization_4007(instance);
			instance.setName((String)value_0);
		} catch (RuntimeException e) {
			UMLDiagramEditorPlugin.getInstance().logError("Element initialization failed", e); //$NON-NLS-1$						
		}
	}

	/**
	 * @generated
	 */
	public void init_Abstraction_4013(Abstraction instance) {
		try {
			Object value_0 = name_Abstraction_4013(instance);
			instance.setName((String)value_0);
		} catch (RuntimeException e) {
			UMLDiagramEditorPlugin.getInstance().logError("Element initialization failed", e); //$NON-NLS-1$						
		}
	}

	/**
	 * @generated
	 */
	public void init_Dependency_4010(Dependency instance) {
		try {
			Object value_0 = name_Dependency_4010(instance);
			instance.setName((String)value_0);
		} catch (RuntimeException e) {
			UMLDiagramEditorPlugin.getInstance().logError("Element initialization failed", e); //$NON-NLS-1$						
		}
	}

	/**
	 * @generated
	 */
	private String body_Comment_3201(Comment self) {
		// Comment body init
		return "";
	}

	/**
	 * @generated
	 */
	private String name_Constraint_3199(Constraint self) {
		return NamedElementHelper.EINSTANCE.getNewUMLElementName(self.getOwner(), self.eClass());
	}

	/**
	 * @generated
	 */
	private ValueSpecification specification_Constraint_3199(Constraint self) {
		// Constraint specification init
		LiteralString value = UMLFactory.eINSTANCE.createLiteralString();
		value.setValue("");
		return value;
	}

	/**
	 * @generated
	 */
	private String body_Comment_3074(Comment self) {
		// Comment body init
		return "";
	}

	/**
	 * @generated
	 */
	private String name_Constraint_3075(Constraint self) {
		return NamedElementHelper.EINSTANCE.getNewUMLElementName(self.getOwner(), self.eClass());
	}

	/**
	 * @generated
	 */
	private ValueSpecification specification_Constraint_3075(Constraint self) {
		// Constraint specification init
		LiteralString value = UMLFactory.eINSTANCE.createLiteralString();
		value.setValue("");
		return value;
	}

	/**
	 * @generated
	 */
	private String name_Substitution_4012(Substitution self) {
		return NamedElementHelper.EINSTANCE.getNewUMLElementName(self.getOwner(), self.eClass());
	}

	/**
	 * @generated
	 */
	private String name_Manifestation_4014(Manifestation self) {
		return NamedElementHelper.EINSTANCE.getNewUMLElementName(self.getOwner(), self.eClass());
	}

	/**
	 * @generated
	 */
	private String name_ComponentRealization_4007(ComponentRealization self) {
		return NamedElementHelper.EINSTANCE.getNewUMLElementName(self.getOwner(), self.eClass());
	}

	/**
	 * @generated
	 */
	private String name_Abstraction_4013(Abstraction self) {
		return NamedElementHelper.EINSTANCE.getNewUMLElementName(self.getOwner(), self.eClass());
	}

	/**
	 * @generated
	 */
	private String name_Dependency_4010(Dependency self) {
		return NamedElementHelper.EINSTANCE.getNewUMLElementName(self.getOwner(), self.eClass());
	}

	/**
	 * @generated
	 */
	public static ElementInitializers getInstance() {
		ElementInitializers cached = UMLDiagramEditorPlugin.getInstance().getElementInitializers();
		if(cached == null) {
			UMLDiagramEditorPlugin.getInstance().setElementInitializers(cached = new ElementInitializers());
		}
		return cached;
	}
}
