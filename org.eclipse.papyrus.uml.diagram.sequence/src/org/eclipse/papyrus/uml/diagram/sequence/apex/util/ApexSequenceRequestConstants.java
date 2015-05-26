package org.eclipse.papyrus.uml.diagram.sequence.apex.util;

public interface ApexSequenceRequestConstants {

	/**
	 *  Move or Resize of an element
	 */
	public static final String APEX_COMMAND_LABLE_MOVE_OR_RESIZE = "Apex Move or Resize"; //$NON-NLS-1$
	
	/**
	 * Move or Resize a lifeline
	 */
	public static final String APEX_COMMAND_LABLE_LIFELINE_MOVE_OR_RESIZE = "Set Lifeline Move or Resize"; //$NON-NLS-1$
	
	/**
	 * Relocation of an executionSpecification due to the movement of the parent lifeline
	 */
	public static final String APEX_RELOCATION_OF_EXECUTIONSPECIFICATION_BY_LIFELINE_MOVE = "Re-location of a ExecutionSpecification due to a Lifeline movement"; //$NON-NLS-1$	

	/**
	 * 
	 */
	public static final String APEX_KEY_IS_LEFTESTLIFELINE_OF_PARENT_COMBINEDFRAGMENT = "Apex Is LeftestLifeline Of ParentCF"; //$NON-NLS-1$
	
	/**
	 * 
	 */
	public static final String APEX_KEY_MOVING_LIFELINEEDITPART = "Apex Move By This LifelineEditPart"; //$NON-NLS-1$
	
	/**
	 * 
	 */
	public static final String APEX_KEY_RESIZING_COMBINEDFRAGMENTEDITPART = "Apex Resize By This CombinedFragmentEditPart"; //$NON-NLS-1$
	
	
	/**
	 * 
	 */
	public static final String APEX_KEY_RESIZING_COMBINEDFRAGMENTEDITPART_BY_INTERACTIONOPERAND_RESIZE = "Apex Resize CombinedFragment By This InteractionOperandEditPart Resize"; //$NON-NLS-1$
	
	/**
	 * 
	 */
	public static final String APEX_KEY_RESIZING_COMBINEDFRAGMENTEDITPART_BY_INTERACTIONOPERAND_CREATE = "Apex Resize CombinedFragment By This InteractionOperandEditPart Creation"; //$NON-NLS-1$
	
	/**
	 * 
	 */
	public static final String APEX_COMMAND_LABEL_RESIZE_MOVE_COMBINEDFRAGMENT =  "Resize or Move CombinedFragmentEditPart"; //$NON-NLS-1$
	
	/**
	 * 
	 */
	public static final String APEX_COMMAND_LABEL_RESIZE_THE_CF_BOUNDS = "Resize the actual bounds of CombinedFragment"; //$NON-NLS-1$
	
	/**
	 * 
	 */
	public static final String APEX_COMMAND_LABEL_MOVE_THE_CF_BOUNDS = "Move the actual bounds of CombinedFragment"; //$NON-NLS-1$
	
	/**
	 * 
	 */
	public static final String APEX_COMMAND_LABEL_CHILD_MOVE_ADJUSTMENT = "Apex_Child_Move_Adjustment"; //$NON-NLS-1$
	
	/**
	 * 
	 */
	public static final String APEX_COMMAND_LABEL_RESIZE_THE_INTERACTIONOPERAND = "Apex_IO_Resize"; //$NON-NLS-1$
	
	/**
	 * 
	 */
	public static final String APEX_COMMAND_LABEL_RELOCATION_BY_ABOVE_ELEMENT = "Re-location by above EP move/resize"; //$NON-NLS-1$
	
	/**
	 * 
	 */
	public static final String APEX_REQUEST_MOVE_AND_RESIZE_COMBINEDFRAGMENT = "Move and Resize CF"; //$NON-NLS-1$
	
	/**
	 * 
	 */
	public static final String APEX_COMMAND_LABEL_RESIZE_INTERACTION_COMPARTMENTS = "Resize of Interaction Compartment Elements"; //$NON-NLS-1$
	
	/**
	 * 
	 */
	public static final String APEX_KEY_IS_RESIZE_BY_IO_APPEND = "Apex_Is_ResizeCF_By_IOAppend"; //$NON-NLS-1$
	
	/**
	 * 
	 */
	public static final String APEX_KEY_IS_RESIZE_BY_MESSAGE_MOVE = "Resize IO by message Move or activation Resize"; //$NON-NLS-1$

	/**
	 * connectable element create command
	 */
	public static final String APEX_CONNECTABLE_ELEMENT_CREATE_COMMAND = "Connectable Element Create Command"; //$NON-NLS-1$

	/**
	 * update connectable element's type reference
	 */
	public static final String APEX_CONNECTABLE_ELEMENT_TYPE = "Connectable Element Type"; //$NON-NLS-1$

	/**
	 * activation's new bounds to be preserved anchor
	 */
	public static final String APEX_PRESERVE_ANCHOR_RELATIVE_BOUNDS = "Preserve Anchor Relative bounds"; //$NON-NLS-1$

	/**
	 * 'SHIFT' key is pressed
	 */
	public static final String APEX_MODIFIER_CONSTRAINED_MOVE = "modifier for constrained move"; //$NON-NLS-1$
	
	/**
	 * 'CTRL' key is pressed
	 */
	public static final String APEX_MODIFIER_REORDERING = "modifier for sequence of messages"; //$NON-NLS-1$

	/**
	 * due to moved message
	 */
	public static final String APEX_DUE_TO_MOVED_MESSAGE = "due to moved message"; //$NON-NLS-1$
}
