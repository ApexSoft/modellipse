/*****************************************************************************
 * Copyright (c) 2009 Atos Origin.
 *
 *    
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Atos Origin - Initial API and implementation
 *
 *****************************************************************************/
package org.eclipse.papyrus.uml.diagram.activity.part;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.Tool;
import org.eclipse.gmf.runtime.diagram.ui.services.palette.PaletteFactory;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.papyrus.uml.diagram.activity.providers.UMLElementTypes;
import org.eclipse.papyrus.uml.diagram.common.service.AspectUnspecifiedTypeConnectionTool;
import org.eclipse.papyrus.uml.diagram.common.service.AspectUnspecifiedTypeCreationTool;

/**
 * @generated
 */
public class UMLPaletteFactory extends PaletteFactory.Adapter {

	/**
	 * @generated
	 */
	private final static String CREATEINITIALNODE1CREATIONTOOL = "createInitialnode1CreationTool"; //$NON-NLS-1$

	/**
	 * @generated
	 */
	private final static String CREATEACTIVITYFINAL2CREATIONTOOL = "createActivityfinal2CreationTool"; //$NON-NLS-1$

	/**
	 * @generated
	 */
	private final static String CREATEFLOWFINAL3CREATIONTOOL = "createFlowfinal3CreationTool"; //$NON-NLS-1$

	/**
	 * @generated
	 */
	private final static String CREATEDECISIONNODE4CREATIONTOOL = "createDecisionnode4CreationTool"; //$NON-NLS-1$

	/**
	 * @generated
	 */
	private final static String CREATEMERGENODE5CREATIONTOOL = "createMergenode5CreationTool"; //$NON-NLS-1$

	/**
	 * @generated
	 */
	private final static String CREATEJOINNODE6CREATIONTOOL = "createJoinnode6CreationTool"; //$NON-NLS-1$

	/**
	 * @generated
	 */
	private final static String CREATEFORKNODE7CREATIONTOOL = "createForknode7CreationTool"; //$NON-NLS-1$

	/**
	 * @generated
	 */
	private final static String CREATEACTIVITYPARAMETERNODE8CREATIONTOOL = "createActivityParameterNode8CreationTool"; //$NON-NLS-1$

	/**
	 * @generated
	 */
	private final static String CREATEDATASTORENODE9CREATIONTOOL = "createDataStoreNode9CreationTool"; //$NON-NLS-1$

	/**
	 * @generated
	 */
	private final static String CREATEOPAQUEACTION10CREATIONTOOL = "createOpaqueAction10CreationTool"; //$NON-NLS-1$

	/**
	 * @generated
	 */
	private final static String CREATECALLBEHAVIORACTION11CREATIONTOOL = "createCallBehaviorAction11CreationTool"; //$NON-NLS-1$

	/**
	 * @generated
	 */
	private final static String CREATECALLOPERATIONACTION12CREATIONTOOL = "createCallOperationAction12CreationTool"; //$NON-NLS-1$

	/**
	 * @generated
	 */
	private final static String CREATESENDOBJECTACTION13CREATIONTOOL = "createSendObjectAction13CreationTool"; //$NON-NLS-1$

	/**
	 * @generated
	 */
	private final static String CREATESENDSIGNALACTION14CREATIONTOOL = "createSendSignalAction14CreationTool"; //$NON-NLS-1$

	/**
	 * @generated
	 */
	private final static String CREATEACCEPTEVENTACTION15CREATIONTOOL = "createAcceptEventAction15CreationTool"; //$NON-NLS-1$

	/**
	 * @generated
	 */
	private final static String CREATEVALUESPECIFICATIONACTION16CREATIONTOOL = "createValueSpecificationAction16CreationTool"; //$NON-NLS-1$

	/**
	 * @generated
	 */
	private final static String CREATEACTIVITYPARTITION17CREATIONTOOL = "createActivityPartition17CreationTool"; //$NON-NLS-1$

	/**
	 * @generated
	 */
	private final static String CREATEINTERRUPTIBLEACTIVITYREGION18CREATIONTOOL = "createInterruptibleActivityRegion18CreationTool"; //$NON-NLS-1$

	/**
	 * @generated
	 */
	private final static String CREATESTRUCTUREDACTIVITYNODE19CREATIONTOOL = "createStructuredActivityNode19CreationTool"; //$NON-NLS-1$

	/**
	 * @generated
	 */
	private final static String CREATEREADSELFACTION20CREATIONTOOL = "createReadSelfAction20CreationTool"; //$NON-NLS-1$

	/**
	 * @generated
	 */
	private final static String CREATECONDITIONALNODE21CREATIONTOOL = "createConditionalNode21CreationTool"; //$NON-NLS-1$

	/**
	 * @generated
	 */
	private final static String CREATEEXPANSIONREGION22CREATIONTOOL = "createExpansionRegion22CreationTool"; //$NON-NLS-1$

	/**
	 * @generated
	 */
	private final static String CREATELOOPNODE23CREATIONTOOL = "createLoopNode23CreationTool"; //$NON-NLS-1$

	/**
	 * @generated
	 */
	private final static String CREATESEQUENCENODE24CREATIONTOOL = "createSequenceNode24CreationTool"; //$NON-NLS-1$

	/**
	 * @generated
	 */
	private final static String CREATEOUTPUTPIN25CREATIONTOOL = "createOutputPin25CreationTool"; //$NON-NLS-1$

	/**
	 * @generated
	 */
	private final static String CREATEINPUTPIN26CREATIONTOOL = "createInputPin26CreationTool"; //$NON-NLS-1$

	/**
	 * @generated
	 */
	private final static String CREATEACTIONINPUTPIN27CREATIONTOOL = "createActionInputPin27CreationTool"; //$NON-NLS-1$

	/**
	 * @generated
	 */
	private final static String CREATEVALUEPIN28CREATIONTOOL = "createValuePin28CreationTool"; //$NON-NLS-1$

	/**
	 * @generated
	 */
	private final static String CREATEINPUTEXPANSIONNODE29CREATIONTOOL = "createInputExpansionNode29CreationTool"; //$NON-NLS-1$

	/**
	 * @generated
	 */
	private final static String CREATEOUTPUTEXPANSIONNODE30CREATIONTOOL = "createOutputExpansionNode30CreationTool"; //$NON-NLS-1$

	/**
	 * @generated
	 */
	private final static String CREATELOCALPRECONDITIONCONSTRAINT31CREATIONTOOL = "createLocalPreconditionConstraint31CreationTool"; //$NON-NLS-1$

	/**
	 * @generated
	 */
	private final static String CREATELOCALPRECONDITIONINTERVALCONSTRAINT33CREATIONTOOL = "createLocalPreconditionIntervalConstraint33CreationTool"; //$NON-NLS-1$

	/**
	 * @generated
	 */
	private final static String CREATELOCALPRECONDITIONDURATIONCONSTRAINT34CREATIONTOOL = "createLocalPreconditionDurationConstraint34CreationTool"; //$NON-NLS-1$

	/**
	 * @generated
	 */
	private final static String CREATELOCALPRECONDITIONTIMECONSTRAINT35CREATIONTOOL = "createLocalPreconditionTimeConstraint35CreationTool"; //$NON-NLS-1$

	/**
	 * @generated
	 */
	private final static String CREATELOCALPOSTCONDITIONCONSTRAINT36CREATIONTOOL = "createLocalPostconditionConstraint36CreationTool"; //$NON-NLS-1$

	/**
	 * @generated
	 */
	private final static String CREATELOCALPOSTCONDITIONINTERVALCONSTRAINT38CREATIONTOOL = "createLocalPostconditionIntervalConstraint38CreationTool"; //$NON-NLS-1$

	/**
	 * @generated
	 */
	private final static String CREATELOCALPOSTCONDITIONDURATIONCONSTRAINT39CREATIONTOOL = "createLocalPostconditionDurationConstraint39CreationTool"; //$NON-NLS-1$

	/**
	 * @generated
	 */
	private final static String CREATELOCALPOSTCONDITIONTIMECONSTRAINT40CREATIONTOOL = "createLocalPostconditionTimeConstraint40CreationTool"; //$NON-NLS-1$

	/**
	 * @generated
	 */
	private final static String CREATECOMMENT41CREATIONTOOL = "createComment41CreationTool"; //$NON-NLS-1$

	/**
	 * @generated
	 */
	private final static String CREATEACTIVITY42CREATIONTOOL = "createActivity42CreationTool"; //$NON-NLS-1$

	/**
	 * @generated
	 */
	private final static String CREATECREATEOBJECTACTION43CREATIONTOOL = "createCreateObjectAction43CreationTool"; //$NON-NLS-1$

	/**
	 * @generated
	 */
	private final static String CREATEREADSTRUCTURALFEATUREACTION44CREATIONTOOL = "createReadStructuralFeatureAction44CreationTool"; //$NON-NLS-1$

	/**
	 * @generated
	 */
	private final static String CREATEADDSTRUCTURALFEATUREVALUEACTION45CREATIONTOOL = "createAddStructuralFeatureValueAction45CreationTool"; //$NON-NLS-1$

	/**
	 * @generated
	 */
	private final static String CREATEDESTROYOBJECTACTION46CREATIONTOOL = "createDestroyObjectAction46CreationTool"; //$NON-NLS-1$

	/**
	 * @generated
	 */
	private final static String CREATEREADVARIABLEACTION47CREATIONTOOL = "createReadVariableAction47CreationTool"; //$NON-NLS-1$

	/**
	 * @generated
	 */
	private final static String CREATEADDVARIABLEVALUEACTION48CREATIONTOOL = "createAddVariableValueAction48CreationTool"; //$NON-NLS-1$

	/**
	 * @generated
	 */
	private final static String CREATEBROADCASTSIGNALACTION49CREATIONTOOL = "createBroadcastSignalAction49CreationTool"; //$NON-NLS-1$

	/**
	 * @generated
	 */
	private final static String CREATECENTRALBUFFERNODE50CREATIONTOOL = "createCentralBufferNode50CreationTool"; //$NON-NLS-1$

	/**
	 * @generated
	 */
	private final static String CREATECONSTRAINT51CREATIONTOOL = "createConstraint51CreationTool"; //$NON-NLS-1$

	/**
	 * @generated
	 */
	private final static String CREATECONTROLFLOW1CREATIONTOOL = "createControlFlow1CreationTool"; //$NON-NLS-1$

	/**
	 * @generated
	 */
	private final static String CREATEOBJECTFLOW2CREATIONTOOL = "createObjectFlow2CreationTool"; //$NON-NLS-1$

	/**
	 * @generated
	 */
	private final static String CREATEEXCEPTIONHANDLER3CREATIONTOOL = "createExceptionHandler3CreationTool"; //$NON-NLS-1$

	/**
	 * @generated
	 */
	private final static String CREATELINK4CREATIONTOOL = "createLink4CreationTool"; //$NON-NLS-1$

	/**
	 * @generated
	 */
	public UMLPaletteFactory() {
	}

	/**
	 * @generated
	 */
	public Tool createTool(String toolId) {
		if(toolId.equals(CREATEINITIALNODE1CREATIONTOOL)) {
			return createInitialnode1CreationTool();
		}
		if(toolId.equals(CREATEACTIVITYFINAL2CREATIONTOOL)) {
			return createActivityfinal2CreationTool();
		}
		if(toolId.equals(CREATEFLOWFINAL3CREATIONTOOL)) {
			return createFlowfinal3CreationTool();
		}
		if(toolId.equals(CREATEDECISIONNODE4CREATIONTOOL)) {
			return createDecisionnode4CreationTool();
		}
		if(toolId.equals(CREATEMERGENODE5CREATIONTOOL)) {
			return createMergenode5CreationTool();
		}
		if(toolId.equals(CREATEJOINNODE6CREATIONTOOL)) {
			return createJoinnode6CreationTool();
		}
		if(toolId.equals(CREATEFORKNODE7CREATIONTOOL)) {
			return createForknode7CreationTool();
		}
		if(toolId.equals(CREATEACTIVITYPARAMETERNODE8CREATIONTOOL)) {
			return createActivityParameterNode8CreationTool();
		}
		if(toolId.equals(CREATEDATASTORENODE9CREATIONTOOL)) {
			return createDataStoreNode9CreationTool();
		}
		if(toolId.equals(CREATEOPAQUEACTION10CREATIONTOOL)) {
			return createOpaqueAction10CreationTool();
		}
		if(toolId.equals(CREATECALLBEHAVIORACTION11CREATIONTOOL)) {
			return createCallBehaviorAction11CreationTool();
		}
		if(toolId.equals(CREATECALLOPERATIONACTION12CREATIONTOOL)) {
			return createCallOperationAction12CreationTool();
		}
		if(toolId.equals(CREATESENDOBJECTACTION13CREATIONTOOL)) {
			return createSendObjectAction13CreationTool();
		}
		if(toolId.equals(CREATESENDSIGNALACTION14CREATIONTOOL)) {
			return createSendSignalAction14CreationTool();
		}
		if(toolId.equals(CREATEACCEPTEVENTACTION15CREATIONTOOL)) {
			return createAcceptEventAction15CreationTool();
		}
		if(toolId.equals(CREATEVALUESPECIFICATIONACTION16CREATIONTOOL)) {
			return createValueSpecificationAction16CreationTool();
		}
		if(toolId.equals(CREATEACTIVITYPARTITION17CREATIONTOOL)) {
			return createActivityPartition17CreationTool();
		}
		if(toolId.equals(CREATEINTERRUPTIBLEACTIVITYREGION18CREATIONTOOL)) {
			return createInterruptibleActivityRegion18CreationTool();
		}
		if(toolId.equals(CREATESTRUCTUREDACTIVITYNODE19CREATIONTOOL)) {
			return createStructuredActivityNode19CreationTool();
		}
		if(toolId.equals(CREATEREADSELFACTION20CREATIONTOOL)) {
			return createReadSelfAction20CreationTool();
		}
		if(toolId.equals(CREATECONDITIONALNODE21CREATIONTOOL)) {
			return createConditionalNode21CreationTool();
		}
		if(toolId.equals(CREATEEXPANSIONREGION22CREATIONTOOL)) {
			return createExpansionRegion22CreationTool();
		}
		if(toolId.equals(CREATELOOPNODE23CREATIONTOOL)) {
			return createLoopNode23CreationTool();
		}
		if(toolId.equals(CREATESEQUENCENODE24CREATIONTOOL)) {
			return createSequenceNode24CreationTool();
		}
		if(toolId.equals(CREATEOUTPUTPIN25CREATIONTOOL)) {
			return createOutputPin25CreationTool();
		}
		if(toolId.equals(CREATEINPUTPIN26CREATIONTOOL)) {
			return createInputPin26CreationTool();
		}
		if(toolId.equals(CREATEACTIONINPUTPIN27CREATIONTOOL)) {
			return createActionInputPin27CreationTool();
		}
		if(toolId.equals(CREATEVALUEPIN28CREATIONTOOL)) {
			return createValuePin28CreationTool();
		}
		if(toolId.equals(CREATEINPUTEXPANSIONNODE29CREATIONTOOL)) {
			return createInputExpansionNode29CreationTool();
		}
		if(toolId.equals(CREATEOUTPUTEXPANSIONNODE30CREATIONTOOL)) {
			return createOutputExpansionNode30CreationTool();
		}
		if(toolId.equals(CREATELOCALPRECONDITIONCONSTRAINT31CREATIONTOOL)) {
			return createLocalPreconditionConstraint31CreationTool();
		}
		if(toolId.equals(CREATELOCALPRECONDITIONINTERVALCONSTRAINT33CREATIONTOOL)) {
			return createLocalPreconditionIntervalConstraint33CreationTool();
		}
		if(toolId.equals(CREATELOCALPRECONDITIONDURATIONCONSTRAINT34CREATIONTOOL)) {
			return createLocalPreconditionDurationConstraint34CreationTool();
		}
		if(toolId.equals(CREATELOCALPRECONDITIONTIMECONSTRAINT35CREATIONTOOL)) {
			return createLocalPreconditionTimeConstraint35CreationTool();
		}
		if(toolId.equals(CREATELOCALPOSTCONDITIONCONSTRAINT36CREATIONTOOL)) {
			return createLocalPostconditionConstraint36CreationTool();
		}
		if(toolId.equals(CREATELOCALPOSTCONDITIONINTERVALCONSTRAINT38CREATIONTOOL)) {
			return createLocalPostconditionIntervalConstraint38CreationTool();
		}
		if(toolId.equals(CREATELOCALPOSTCONDITIONDURATIONCONSTRAINT39CREATIONTOOL)) {
			return createLocalPostconditionDurationConstraint39CreationTool();
		}
		if(toolId.equals(CREATELOCALPOSTCONDITIONTIMECONSTRAINT40CREATIONTOOL)) {
			return createLocalPostconditionTimeConstraint40CreationTool();
		}
		if(toolId.equals(CREATECOMMENT41CREATIONTOOL)) {
			return createComment41CreationTool();
		}
		if(toolId.equals(CREATEACTIVITY42CREATIONTOOL)) {
			return createActivity42CreationTool();
		}
		if(toolId.equals(CREATECREATEOBJECTACTION43CREATIONTOOL)) {
			return createCreateObjectAction43CreationTool();
		}
		if(toolId.equals(CREATEREADSTRUCTURALFEATUREACTION44CREATIONTOOL)) {
			return createReadStructuralFeatureAction44CreationTool();
		}
		if(toolId.equals(CREATEADDSTRUCTURALFEATUREVALUEACTION45CREATIONTOOL)) {
			return createAddStructuralFeatureValueAction45CreationTool();
		}
		if(toolId.equals(CREATEDESTROYOBJECTACTION46CREATIONTOOL)) {
			return createDestroyObjectAction46CreationTool();
		}
		if(toolId.equals(CREATEREADVARIABLEACTION47CREATIONTOOL)) {
			return createReadVariableAction47CreationTool();
		}
		if(toolId.equals(CREATEADDVARIABLEVALUEACTION48CREATIONTOOL)) {
			return createAddVariableValueAction48CreationTool();
		}
		if(toolId.equals(CREATEBROADCASTSIGNALACTION49CREATIONTOOL)) {
			return createBroadcastSignalAction49CreationTool();
		}
		if(toolId.equals(CREATECENTRALBUFFERNODE50CREATIONTOOL)) {
			return createCentralBufferNode50CreationTool();
		}
		if(toolId.equals(CREATECONSTRAINT51CREATIONTOOL)) {
			return createConstraint51CreationTool();
		}
		if(toolId.equals(CREATECONTROLFLOW1CREATIONTOOL)) {
			return createControlFlow1CreationTool();
		}
		if(toolId.equals(CREATEOBJECTFLOW2CREATIONTOOL)) {
			return createObjectFlow2CreationTool();
		}
		if(toolId.equals(CREATEEXCEPTIONHANDLER3CREATIONTOOL)) {
			return createExceptionHandler3CreationTool();
		}
		if(toolId.equals(CREATELINK4CREATIONTOOL)) {
			return createLink4CreationTool();
		}
		// default return: null
		return null;
	}

	public Object getTemplate(String templateId) {
		// default return: null
		return null;
	}

	/**
	 * @generated
	 */
	private Tool createInitialnode1CreationTool() {
		List<IElementType> types = new ArrayList<IElementType>(1);
		types.add(UMLElementTypes.InitialNode_3004);
		Tool tool = new AspectUnspecifiedTypeCreationTool(types);
		return tool;
	}

	/**
	 * @generated
	 */
	private Tool createActivityfinal2CreationTool() {
		List<IElementType> types = new ArrayList<IElementType>(1);
		types.add(UMLElementTypes.ActivityFinalNode_3005);
		Tool tool = new AspectUnspecifiedTypeCreationTool(types);
		return tool;
	}

	/**
	 * @generated
	 */
	private Tool createFlowfinal3CreationTool() {
		List<IElementType> types = new ArrayList<IElementType>(1);
		types.add(UMLElementTypes.FlowFinalNode_3006);
		Tool tool = new AspectUnspecifiedTypeCreationTool(types);
		return tool;
	}

	/**
	 * @generated
	 */
	private Tool createDecisionnode4CreationTool() {
		List<IElementType> types = new ArrayList<IElementType>(1);
		types.add(UMLElementTypes.DecisionNode_3038);
		Tool tool = new AspectUnspecifiedTypeCreationTool(types);
		return tool;
	}

	/**
	 * @generated
	 */
	private Tool createMergenode5CreationTool() {
		List<IElementType> types = new ArrayList<IElementType>(1);
		types.add(UMLElementTypes.MergeNode_3039);
		Tool tool = new AspectUnspecifiedTypeCreationTool(types);
		return tool;
	}

	/**
	 * @generated
	 */
	private Tool createJoinnode6CreationTool() {
		List<IElementType> types = new ArrayList<IElementType>(1);
		types.add(UMLElementTypes.JoinNode_3041);
		Tool tool = new AspectUnspecifiedTypeCreationTool(types);
		return tool;
	}

	/**
	 * @generated
	 */
	private Tool createForknode7CreationTool() {
		List<IElementType> types = new ArrayList<IElementType>(1);
		types.add(UMLElementTypes.ForkNode_3040);
		Tool tool = new AspectUnspecifiedTypeCreationTool(types);
		return tool;
	}

	/**
	 * @generated
	 */
	private Tool createActivityParameterNode8CreationTool() {
		List<IElementType> types = new ArrayList<IElementType>(1);
		types.add(UMLElementTypes.ActivityParameterNode_3059);
		Tool tool = new AspectUnspecifiedTypeCreationTool(types);
		return tool;
	}

	/**
	 * @generated
	 */
	private Tool createDataStoreNode9CreationTool() {
		List<IElementType> types = new ArrayList<IElementType>(1);
		types.add(UMLElementTypes.DataStoreNode_3078);
		Tool tool = new AspectUnspecifiedTypeCreationTool(types);
		return tool;
	}

	/**
	 * @generated
	 */
	private Tool createOpaqueAction10CreationTool() {
		List<IElementType> types = new ArrayList<IElementType>(1);
		types.add(UMLElementTypes.OpaqueAction_3007);
		Tool tool = new AspectUnspecifiedTypeCreationTool(types);
		return tool;
	}

	/**
	 * @generated
	 */
	private Tool createCallBehaviorAction11CreationTool() {
		List<IElementType> types = new ArrayList<IElementType>(1);
		types.add(UMLElementTypes.CallBehaviorAction_3008);
		Tool tool = new AspectUnspecifiedTypeCreationTool(types);
		return tool;
	}

	/**
	 * @generated
	 */
	private Tool createCallOperationAction12CreationTool() {
		List<IElementType> types = new ArrayList<IElementType>(1);
		types.add(UMLElementTypes.CallOperationAction_3010);
		Tool tool = new AspectUnspecifiedTypeCreationTool(types);
		return tool;
	}

	/**
	 * @generated
	 */
	private Tool createSendObjectAction13CreationTool() {
		List<IElementType> types = new ArrayList<IElementType>(1);
		types.add(UMLElementTypes.SendObjectAction_3042);
		Tool tool = new AspectUnspecifiedTypeCreationTool(types);
		return tool;
	}

	/**
	 * @generated
	 */
	private Tool createSendSignalAction14CreationTool() {
		List<IElementType> types = new ArrayList<IElementType>(1);
		types.add(UMLElementTypes.SendSignalAction_3052);
		Tool tool = new AspectUnspecifiedTypeCreationTool(types);
		return tool;
	}

	/**
	 * @generated
	 */
	private Tool createAcceptEventAction15CreationTool() {
		List<IElementType> types = new ArrayList<IElementType>(1);
		types.add(UMLElementTypes.AcceptEventAction_3063);
		Tool tool = new AspectUnspecifiedTypeCreationTool(types);
		return tool;
	}

	/**
	 * @generated
	 */
	private Tool createValueSpecificationAction16CreationTool() {
		List<IElementType> types = new ArrayList<IElementType>(1);
		types.add(UMLElementTypes.ValueSpecificationAction_3076);
		Tool tool = new AspectUnspecifiedTypeCreationTool(types);
		return tool;
	}

	/**
	 * @generated
	 */
	private Tool createActivityPartition17CreationTool() {
		List<IElementType> types = new ArrayList<IElementType>(1);
		types.add(UMLElementTypes.ActivityPartition_3067);
		Tool tool = new AspectUnspecifiedTypeCreationTool(types);
		return tool;
	}

	/**
	 * @generated
	 */
	private Tool createInterruptibleActivityRegion18CreationTool() {
		List<IElementType> types = new ArrayList<IElementType>(1);
		types.add(UMLElementTypes.InterruptibleActivityRegion_3068);
		Tool tool = new AspectUnspecifiedTypeCreationTool(types);
		return tool;
	}

	/**
	 * @generated
	 */
	private Tool createStructuredActivityNode19CreationTool() {
		List<IElementType> types = new ArrayList<IElementType>(1);
		types.add(UMLElementTypes.StructuredActivityNode_3065);
		Tool tool = new AspectUnspecifiedTypeCreationTool(types);
		return tool;
	}

	/**
	 * @generated
	 */
	private Tool createReadSelfAction20CreationTool() {
		List<IElementType> types = new ArrayList<IElementType>(1);
		types.add(UMLElementTypes.ReadSelfAction_3081);
		Tool tool = new AspectUnspecifiedTypeCreationTool(types);
		return tool;
	}

	/**
	 * @generated
	 */
	private Tool createConditionalNode21CreationTool() {
		List<IElementType> types = new ArrayList<IElementType>(1);
		types.add(UMLElementTypes.ConditionalNode_3069);
		Tool tool = new AspectUnspecifiedTypeCreationTool(types);
		return tool;
	}

	/**
	 * @generated
	 */
	private Tool createExpansionRegion22CreationTool() {
		List<IElementType> types = new ArrayList<IElementType>(1);
		types.add(UMLElementTypes.ExpansionRegion_3070);
		Tool tool = new AspectUnspecifiedTypeCreationTool(types);
		return tool;
	}

	/**
	 * @generated
	 */
	private Tool createLoopNode23CreationTool() {
		List<IElementType> types = new ArrayList<IElementType>(1);
		types.add(UMLElementTypes.LoopNode_3071);
		Tool tool = new AspectUnspecifiedTypeCreationTool(types);
		return tool;
	}

	/**
	 * @generated
	 */
	private Tool createSequenceNode24CreationTool() {
		List<IElementType> types = new ArrayList<IElementType>(1);
		types.add(UMLElementTypes.SequenceNode_3073);
		Tool tool = new AspectUnspecifiedTypeCreationTool(types);
		return tool;
	}

	/**
	 * @generated
	 */
	private Tool createOutputPin25CreationTool() {
		List<IElementType> types = new ArrayList<IElementType>(9);
		types.add(UMLElementTypes.OutputPin_3014);
		types.add(UMLElementTypes.OutputPin_3020);
		types.add(UMLElementTypes.OutputPin_3024);
		types.add(UMLElementTypes.OutputPin_3064);
		types.add(UMLElementTypes.OutputPin_3077);
		types.add(UMLElementTypes.OutputPin_3084);
		types.add(UMLElementTypes.OutputPin_3109);
		types.add(UMLElementTypes.OutputPin_3110);
		types.add(UMLElementTypes.OutputPin_3111);
		Tool tool = new AspectUnspecifiedTypeCreationTool(types);
		return tool;
	}

	/**
	 * @generated
	 */
	private Tool createInputPin26CreationTool() {
		List<IElementType> types = new ArrayList<IElementType>(5);
		types.add(UMLElementTypes.InputPin_3013);
		types.add(UMLElementTypes.InputPin_3019);
		types.add(UMLElementTypes.InputPin_3023);
		types.add(UMLElementTypes.InputPin_3055);
		types.add(UMLElementTypes.InputPin_3105);
		Tool tool = new AspectUnspecifiedTypeCreationTool(types);
		return tool;
	}

	/**
	 * @generated
	 */
	private Tool createActionInputPin27CreationTool() {
		List<IElementType> types = new ArrayList<IElementType>(4);
		types.add(UMLElementTypes.ActionInputPin_3016);
		types.add(UMLElementTypes.ActionInputPin_3018);
		types.add(UMLElementTypes.ActionInputPin_3021);
		types.add(UMLElementTypes.ActionInputPin_3053);
		Tool tool = new AspectUnspecifiedTypeCreationTool(types);
		return tool;
	}

	/**
	 * @generated
	 */
	private Tool createValuePin28CreationTool() {
		List<IElementType> types = new ArrayList<IElementType>(4);
		types.add(UMLElementTypes.ValuePin_3015);
		types.add(UMLElementTypes.ValuePin_3017);
		types.add(UMLElementTypes.ValuePin_3022);
		types.add(UMLElementTypes.ValuePin_3054);
		Tool tool = new AspectUnspecifiedTypeCreationTool(types);
		return tool;
	}

	/**
	 * @generated
	 */
	private Tool createInputExpansionNode29CreationTool() {
		List<IElementType> types = new ArrayList<IElementType>(1);
		types.add(UMLElementTypes.ExpansionNode_3074);
		Tool tool = new AspectUnspecifiedTypeCreationTool(types);
		return tool;
	}

	/**
	 * @generated
	 */
	private Tool createOutputExpansionNode30CreationTool() {
		List<IElementType> types = new ArrayList<IElementType>(1);
		types.add(UMLElementTypes.ExpansionNode_3075);
		Tool tool = new AspectUnspecifiedTypeCreationTool(types);
		return tool;
	}

	/**
	 * @generated
	 */
	private Tool createLocalPreconditionConstraint31CreationTool() {
		List<IElementType> types = new ArrayList<IElementType>(1);
		types.add(UMLElementTypes.Constraint_3011);
		Tool tool = new AspectUnspecifiedTypeCreationTool(types);
		return tool;
	}

	/**
	 * @generated
	 */
	private Tool createLocalPreconditionIntervalConstraint33CreationTool() {
		List<IElementType> types = new ArrayList<IElementType>(1);
		types.add(UMLElementTypes.IntervalConstraint_3032);
		Tool tool = new AspectUnspecifiedTypeCreationTool(types);
		return tool;
	}

	/**
	 * @generated
	 */
	private Tool createLocalPreconditionDurationConstraint34CreationTool() {
		List<IElementType> types = new ArrayList<IElementType>(1);
		types.add(UMLElementTypes.DurationConstraint_3034);
		Tool tool = new AspectUnspecifiedTypeCreationTool(types);
		return tool;
	}

	/**
	 * @generated
	 */
	private Tool createLocalPreconditionTimeConstraint35CreationTool() {
		List<IElementType> types = new ArrayList<IElementType>(1);
		types.add(UMLElementTypes.TimeConstraint_3036);
		Tool tool = new AspectUnspecifiedTypeCreationTool(types);
		return tool;
	}

	/**
	 * @generated
	 */
	private Tool createLocalPostconditionConstraint36CreationTool() {
		List<IElementType> types = new ArrayList<IElementType>(1);
		types.add(UMLElementTypes.Constraint_3012);
		Tool tool = new AspectUnspecifiedTypeCreationTool(types);
		return tool;
	}

	/**
	 * @generated
	 */
	private Tool createLocalPostconditionIntervalConstraint38CreationTool() {
		List<IElementType> types = new ArrayList<IElementType>(1);
		types.add(UMLElementTypes.IntervalConstraint_3033);
		Tool tool = new AspectUnspecifiedTypeCreationTool(types);
		return tool;
	}

	/**
	 * @generated
	 */
	private Tool createLocalPostconditionDurationConstraint39CreationTool() {
		List<IElementType> types = new ArrayList<IElementType>(1);
		types.add(UMLElementTypes.DurationConstraint_3035);
		Tool tool = new AspectUnspecifiedTypeCreationTool(types);
		return tool;
	}

	/**
	 * @generated
	 */
	private Tool createLocalPostconditionTimeConstraint40CreationTool() {
		List<IElementType> types = new ArrayList<IElementType>(1);
		types.add(UMLElementTypes.TimeConstraint_3037);
		Tool tool = new AspectUnspecifiedTypeCreationTool(types);
		return tool;
	}

	/**
	 * @generated
	 */
	private Tool createComment41CreationTool() {
		List<IElementType> types = new ArrayList<IElementType>(1);
		types.add(UMLElementTypes.Comment_3080);
		Tool tool = new AspectUnspecifiedTypeCreationTool(types);
		return tool;
	}

	/**
	 * @generated
	 */
	private Tool createActivity42CreationTool() {
		List<IElementType> types = new ArrayList<IElementType>(2);
		types.add(UMLElementTypes.Activity_2001);
		types.add(UMLElementTypes.Activity_3083);
		Tool tool = new AspectUnspecifiedTypeCreationTool(types);
		return tool;
	}

	/**
	 * @generated
	 */
	private Tool createCreateObjectAction43CreationTool() {
		List<IElementType> types = new ArrayList<IElementType>(1);
		types.add(UMLElementTypes.CreateObjectAction_3086);
		Tool tool = new AspectUnspecifiedTypeCreationTool(types);
		return tool;
	}

	/**
	 * @generated
	 */
	private Tool createReadStructuralFeatureAction44CreationTool() {
		List<IElementType> types = new ArrayList<IElementType>(1);
		types.add(UMLElementTypes.ReadStructuralFeatureAction_3088);
		Tool tool = new AspectUnspecifiedTypeCreationTool(types);
		return tool;
	}

	/**
	 * @generated
	 */
	private Tool createAddStructuralFeatureValueAction45CreationTool() {
		List<IElementType> types = new ArrayList<IElementType>(1);
		types.add(UMLElementTypes.AddStructuralFeatureValueAction_3091);
		Tool tool = new AspectUnspecifiedTypeCreationTool(types);
		return tool;
	}

	/**
	 * @generated
	 */
	private Tool createDestroyObjectAction46CreationTool() {
		List<IElementType> types = new ArrayList<IElementType>(1);
		types.add(UMLElementTypes.DestroyObjectAction_3095);
		Tool tool = new AspectUnspecifiedTypeCreationTool(types);
		return tool;
	}

	/**
	 * @generated
	 */
	private Tool createReadVariableAction47CreationTool() {
		List<IElementType> types = new ArrayList<IElementType>(1);
		types.add(UMLElementTypes.ReadVariableAction_3097);
		Tool tool = new AspectUnspecifiedTypeCreationTool(types);
		return tool;
	}

	/**
	 * @generated
	 */
	private Tool createAddVariableValueAction48CreationTool() {
		List<IElementType> types = new ArrayList<IElementType>(1);
		types.add(UMLElementTypes.AddVariableValueAction_3099);
		Tool tool = new AspectUnspecifiedTypeCreationTool(types);
		return tool;
	}

	/**
	 * @generated
	 */
	private Tool createBroadcastSignalAction49CreationTool() {
		List<IElementType> types = new ArrayList<IElementType>(1);
		types.add(UMLElementTypes.BroadcastSignalAction_3102);
		Tool tool = new AspectUnspecifiedTypeCreationTool(types);
		return tool;
	}

	/**
	 * @generated
	 */
	private Tool createCentralBufferNode50CreationTool() {
		List<IElementType> types = new ArrayList<IElementType>(1);
		types.add(UMLElementTypes.CentralBufferNode_3104);
		Tool tool = new AspectUnspecifiedTypeCreationTool(types);
		return tool;
	}

	/**
	 * @generated
	 */
	private Tool createConstraint51CreationTool() {
		List<IElementType> types = new ArrayList<IElementType>(1);
		types.add(UMLElementTypes.Constraint_3112);
		Tool tool = new AspectUnspecifiedTypeCreationTool(types);
		return tool;
	}

	/**
	 * @generated
	 */
	private Tool createControlFlow1CreationTool() {
		List<IElementType> types = new ArrayList<IElementType>(1);
		types.add(UMLElementTypes.ControlFlow_4004);
		Tool tool = new AspectUnspecifiedTypeConnectionTool(types);
		return tool;
	}

	/**
	 * @generated
	 */
	private Tool createObjectFlow2CreationTool() {
		List<IElementType> types = new ArrayList<IElementType>(1);
		types.add(UMLElementTypes.ObjectFlow_4003);
		Tool tool = new AspectUnspecifiedTypeConnectionTool(types);
		return tool;
	}

	/**
	 * @generated
	 */
	private Tool createExceptionHandler3CreationTool() {
		List<IElementType> types = new ArrayList<IElementType>(1);
		types.add(UMLElementTypes.ExceptionHandler_4005);
		Tool tool = new AspectUnspecifiedTypeConnectionTool(types);
		return tool;
	}

	/**
	 * @generated
	 */
	private Tool createLink4CreationTool() {
		List<IElementType> types = new ArrayList<IElementType>(2);
		types.add(UMLElementTypes.CommentAnnotatedElement_4006);
		types.add(UMLElementTypes.ConstraintConstrainedElement_4007);
		Tool tool = new AspectUnspecifiedTypeConnectionTool(types);
		return tool;
	}
}
