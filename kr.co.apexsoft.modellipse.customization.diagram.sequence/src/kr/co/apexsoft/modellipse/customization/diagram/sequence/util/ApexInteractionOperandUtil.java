package kr.co.apexsoft.modellipse.customization.diagram.sequence.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.CompartmentEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.swt.SWT;
import org.eclipse.uml2.uml.ExecutionSpecification;
import org.eclipse.uml2.uml.InteractionOperand;

public class ApexInteractionOperandUtil {
	
	/**
	 * Default height of Interaction Operand
	 */
	public static final int DEFAULT_INTERACTION_OPERAND_HEIGHT = 61;
	/**
	 * Default width of Interaction Operand
	 */
	public static final int DEFAULT_INTERACTION_OPERAND_WIDTH = 210;
	/**
	 * Border width of CombinedFragmentFigure
	 */
	public static final int COMBINED_FRAGMENT_FIGURE_BORDER = 1;
	
	/**
	 * Find first Interaction Operand EditpPart of CombinedFragmentCombinedFragmentCompartmentEditPart
	 * @param compartEP
	 * @return ShapeNodeEditPart
	 */
	public static ShapeNodeEditPart findFirstIOEP(
			CompartmentEditPart compartEP) {
		ShapeNodeEditPart firstIOEP = null;
		List children = compartEP.getChildren();
		for (int i = 0; i < children.size(); i++) {
			
			/* apex improved start */
			Object obj = children.get(i);
			if ( obj instanceof IGraphicalEditPart ) {
				EObject eObj = ((IGraphicalEditPart) obj).resolveSemanticElement();
				
				if ( eObj instanceof InteractionOperand ) {
					firstIOEP = (ShapeNodeEditPart) obj;
					break;
				}
			}
			/* apex improved end */
			/* apex replaced
			if (children.get(i) instanceof ShapeNodeEditPart) {
				firstIOEP = (ShapeNodeEditPart) children.get(i);
				break;
			}
			*/
		}
		return firstIOEP;
	}
	
	/**
	 * Find Last Interaction Operand EditpPart of CombinedFragmentCombinedFragmentCompartmentEditPart
	 * @param compartEP
	 * @return ShapeNodeEditPart
	 */
	public static ShapeNodeEditPart findLastIOEP(
			CompartmentEditPart compartEP) {
		ShapeNodeEditPart lastIOEP = null;
		List children = compartEP.getChildren();
		for (int i = children.size() - 1; i >= 0; i--) {
			
			/* apex improved start */
			Object obj = children.get(i);
			if ( obj instanceof IGraphicalEditPart ) {
				EObject eObj = ((IGraphicalEditPart) obj).resolveSemanticElement();
				
				if ( eObj instanceof InteractionOperand ) {
					lastIOEP = (ShapeNodeEditPart) obj;
					break;
				}
			}
			/* apex improved end */
			/* apex replaced
			if (children.get(i) instanceof InteractionOperandEditPart) {
				lastIOEP = (InteractionOperandEditPart) children.get(i);
				break;
			}
			*/
		}
		return lastIOEP;
	}
	
	/**
	 * apex updated
	 * currentIOEP from InteractionOperandEditPart to ShapeNodeEditPart
	 * 
	 * Find Previous Interaction Operand EditpPart of current given InteractionOperandEditPart
	 * @param compartEP
	 * @param currentIOEP
	 * @return ShapeNodeEditPart
	 */
	public static ShapeNodeEditPart findPreviousIOEP(
			CompartmentEditPart compartEP,
			ShapeNodeEditPart currentIOEP) {
		ShapeNodeEditPart previousIOEP = null;
		List children = compartEP.getChildren();
		for (int i = 0; i < children.size() - 1; i++) {
			
			/* apex improved start */
			Object obj = children.get(i);
			if ( obj instanceof IGraphicalEditPart ) {
				EObject eObj = ((IGraphicalEditPart) obj).resolveSemanticElement();
				
				if ( eObj instanceof InteractionOperand ) {
					if ( obj == currentIOEP ) {
						break;
					} else {
						previousIOEP = (ShapeNodeEditPart) obj;	
					}
				}
			}
			/* apex improved end */
			/* apex replaced
			if (children.get(i) instanceof InteractionOperandEditPart) {
				if (children.get(i) == currentIOEP) {
					break;
				} else {
					previousIOEP = (InteractionOperandEditPart) children.get(i);
				}
			}
			*/
		}
		return previousIOEP;
	}
	
	/**
	 * Find Latter Interaction Operand EditpPart of current given InteractionOperandEditPart
	 * @param compartEP
	 * @param currentIOEP
	 * @return ShapeNodeEditPart
	 */
	public static ShapeNodeEditPart findLatterIOEP(
			CompartmentEditPart compartEP,
			ShapeNodeEditPart currentIOEP) {
		ShapeNodeEditPart latterIOEP = null;
		List children = compartEP.getChildren();
		for (int i = children.size() - 1; i > 0; i--) {
			
			/* apex improved start */
			Object obj = children.get(i);
			if ( obj instanceof IGraphicalEditPart ) {
				EObject eObj = ((IGraphicalEditPart) obj).resolveSemanticElement();
				
				if ( eObj instanceof InteractionOperand ) {
					if ( obj == currentIOEP ) {
						break;
					} else {
						latterIOEP = (ShapeNodeEditPart) obj;	
					}
				}
			}
			/* apex improved end */
			/* apex replaced
			if (children.get(i) instanceof InteractionOperandEditPart) {
				if (children.get(i) == currentIOEP) {
					break;
				} else {
					latterIOEP = (InteractionOperandEditPart) children.get(i);
				}
			}
			*/
		}
		return latterIOEP;
	}	
	

	/**
	 * InteractionOperand��children 怨�
	 * InteractionOperand��cover�섎뒗 Lifeline��Activation 以�InteractionOperand���꾩튂�곸쑝濡��ы븿��Activation �④퍡 由ы꽩 
	 * 
	 * @param ioep
	 * @return
	 */
	public static List apexGetInteractionOperandChildrenEditParts(ShapeNodeEditPart ioep) {
		List ioChildren = ApexInteractionOperandUtil.apexGetIOEPContainedEditParts(ioep);
		ioChildren.addAll(ioep.getChildren());
				
		return ioChildren;
	}

	/**
	 * IOEP媛�cover�섎뒗 Lifeline��Activation 諛�Connection 以�IOEP��寃쎄퀎媛��ы븿�섎뒗 EditPartList 諛섑솚
	 * 
	 * @param ioep
	 * @return
	 */
	public static List<EditPart> apexGetIOEPContainedEditParts(ShapeNodeEditPart ioep) {
		return ApexInteractionOperandUtil.apexGetIOEPContainedEditParts(ioep, false);
	}

	/**
	 * coveredOnly媛�false��寃쎌슦 IOEP��寃쎄퀎媛�援먯감�섎뒗 Lifeline��Activation 諛�Connection 以�IOEP��寃쎄퀎媛��ы븿�섎뒗 EditPartList 諛섑솚
	 * 
	 * coveredOnly媛�true��寃쎌슦 IOEP媛�cover�섎뒗 Lifeline��Activation 諛�Connection 以�IOEP��寃쎄퀎媛��ы븿�섎뒗 EditPartList 諛섑솚 
	 * 
	 * @param ioep
	 * @param coveredOnly
	 * @return
	 */
	public static List<EditPart> apexGetIOEPContainedEditParts(ShapeNodeEditPart ioep, boolean coveredOnly) {
		
		List<EditPart> containedEditParts = new ArrayList<EditPart>();
		
		List<ShapeNodeEditPart> coveredLifelineEditParts = ApexSequenceUtil.apexGetCoveredLifelineEditParts(ioep, coveredOnly);
		
		Rectangle ioepRect = ApexSequenceUtil.apexGetAbsoluteRectangle(ioep);
		
		// LifelineEditPart��children editpart 以�IOEP���ы븿�섏뼱���섎뒗 寃�泥섎━
		for ( ShapeNodeEditPart lep : coveredLifelineEditParts ) {
			List<ShapeNodeEditPart> lepChildren = lep.getChildren();
			
			for ( ShapeNodeEditPart ep : lepChildren ) {
				EObject eObj = ep.resolveSemanticElement();
				
				if ( eObj instanceof ExecutionSpecification ) {
//					AbstractExecutionSpecificationEditPart aesep = (AbstractExecutionSpecificationEditPart)ep;
					Rectangle activationRect = ApexSequenceUtil.apexGetAbsoluteRectangle(ep);
					
					if ( ioepRect.contains(activationRect) ) {
						containedEditParts.add(ep);
					}	
				}				
			}
			
			List<EditPart> sourceConnections = lep.getSourceConnections();
			
			for ( EditPart ep : sourceConnections ) {
				if ( ep instanceof ConnectionEditPart ) {
					ConnectionEditPart cep = (ConnectionEditPart)ep;
					Rectangle cepRect = ApexSequenceUtil.apexGetAbsoluteRectangle(cep);
					if ( ioepRect.contains(cepRect) ) {
						containedEditParts.add(cep);
						// 遺�냽�섎뒗 MessageSyncAppliedStereotypeEditPart瑜�異붿텧�섏뿬 
						// containedEditParts��add
					}	
				}
			}
		}
		
		return containedEditParts;
	}
	
	/**
	 * currentIOEP 아래의 sibling IOEP List를 위치순으로 정렬하여 반환
	 * 
	 * @param compartEP
	 * @param currentIOEP
	 * @return List<ShapeNodeEditPart>
	 */
	public static List<ShapeNodeEditPart> apexFindBelowIOEPs(
			CompartmentEditPart compartEP,
			ShapeNodeEditPart currentIOEP) {
		List<ShapeNodeEditPart> belowIOEPs = new ArrayList<ShapeNodeEditPart>();
		
		int currentAbsBottom = ApexSequenceUtil.apexGetAbsolutePosition(currentIOEP, SWT.BOTTOM);
		
		List ioeps = compartEP.getChildren();
		
		for ( int i = 0 ; i < ioeps.size() ; i++ ) {
			ShapeNodeEditPart ioep = (ShapeNodeEditPart)ioeps.get(i);
			int ioepAbsTop = ApexSequenceUtil.apexGetAbsolutePosition(ioep, SWT.TOP);
			
			if ( ioepAbsTop >= currentAbsBottom ) {
				belowIOEPs.add(ioep);
			}
		}
		
		Collections.sort(belowIOEPs, new Comparator<IGraphicalEditPart>() {
			public int compare(IGraphicalEditPart o1, IGraphicalEditPart o2) {
				Rectangle r1 = ApexSequenceUtil.apexGetAbsoluteRectangle(o1);
				Rectangle r2 = ApexSequenceUtil.apexGetAbsoluteRectangle(o2);
				
				if (r1.y - r2.y == 0)
					return r1.x - r2.x;
				return r1.y - r2.y;
			}
		});
				
		return belowIOEPs;
	}
	
	/**
	 * 상방향으로 확대하는 경우 위에 있는 targetOp의 lowest child를 침범하지 않고
	 * 하뱡향으로 확대하는 경우 아래에 있는 targetOp의 lowest child가 targetOp의 bottom 아래로 밀려내려가지 않도록 처리
	 * 
	 * 상방향으로 축소하는 경우 currentOp의 lowest child를 침범하지 않고
	 * 하방향으로 축소하는 경우 currentOp의 lowest child가 currentOp의 bottom 아래로 밀려내려가지 않도록 처리
	 * 
	 * @param currentIOEP
	 * @param compartEP
	 * @param direction
	 * @param heightDelta
	 * @return
	 */
	public static boolean apexIsInvadingTargetChildren(ShapeNodeEditPart currentIOEP,
			                                           CompartmentEditPart compartEP,
			                                           int direction,
			                                           int heightDelta) {
		boolean isInvadingTargetChildren = false;
		ShapeNodeEditPart targetIOEP = null;
		
		if ((direction & PositionConstants.NORTH) != 0) {
			targetIOEP = findPreviousIOEP(compartEP, currentIOEP);
		} else if ((direction & PositionConstants.SOUTH) != 0) {
			targetIOEP = findLatterIOEP(compartEP, currentIOEP);
		}
		
		if ( targetIOEP != null ) {
			List<IGraphicalEditPart> targetIOEPChildrenEditParts = ApexInteractionOperandUtil.apexGetInteractionOperandChildrenEditParts(targetIOEP);
			IGraphicalEditPart lowestTargetChildEP = ApexSequenceUtil.apexGetLowestEditPartFromList(targetIOEPChildrenEditParts);
			
			List currentIOEPChildrenEditParts = ApexInteractionOperandUtil.apexGetInteractionOperandChildrenEditParts(currentIOEP);
			IGraphicalEditPart lowestCurrentChildEP = ApexSequenceUtil.apexGetLowestEditPartFromList(currentIOEPChildrenEditParts);
			
			if ((direction & PositionConstants.NORTH) != 0) { // 상단에서
				
				if ( heightDelta > 0 ) { // 확대하는 경우
					// currentIOEP의 상단이 targetIOEP의 lowest child보다 위로 가면 X
					int topNewCurrentIOEP = ApexSequenceUtil.apexGetAbsolutePosition(currentIOEP, SWT.TOP) - Math.abs(heightDelta);
					if ( lowestTargetChildEP != null ) {			
						int bottomTargetIOEPLowestChild = ApexSequenceUtil.apexGetAbsolutePosition(lowestTargetChildEP, SWT.BOTTOM);
						
						if ( topNewCurrentIOEP < bottomTargetIOEPLowestChild + COMBINED_FRAGMENT_FIGURE_BORDER) {
							isInvadingTargetChildren = true;
						}
					}
				} else { // 축소하는 경우
					// currentIOEP의 new lowest child가  currentIOEP의 하단보다 아래로 내려가면 X
					if ( lowestCurrentChildEP != null ) {
						int bottomNewLowestCurrentChildEP = ApexSequenceUtil.apexGetAbsolutePosition(lowestCurrentChildEP, SWT.BOTTOM) + Math.abs(heightDelta);				
						int bottomCurrentIOEP = ApexSequenceUtil.apexGetAbsolutePosition(currentIOEP, SWT.BOTTOM);
						
						if ( (bottomNewLowestCurrentChildEP + COMBINED_FRAGMENT_FIGURE_BORDER) >= bottomCurrentIOEP ) {
							isInvadingTargetChildren = true;
						}	
					}				
				}					
			} else if ((direction & PositionConstants.SOUTH) != 0) { // 하단에서
				
				if ( heightDelta > 0 ) { // 확대하는 경우
					// targetIOEP의 new lowest child가  targetIOEP의 하단보다 아래로 내려가면 X 
					if ( lowestTargetChildEP != null ) {
						int bottomNewLowestTargetChildEP = ApexSequenceUtil.apexGetAbsolutePosition(lowestTargetChildEP, SWT.BOTTOM) + Math.abs(heightDelta);
						int bottomTargetIOEP = ApexSequenceUtil.apexGetAbsolutePosition(targetIOEP, SWT.BOTTOM);
						
						if ( (bottomNewLowestTargetChildEP + COMBINED_FRAGMENT_FIGURE_BORDER) >= bottomTargetIOEP ) {
							isInvadingTargetChildren = true;
						}
					}
				} else { // 축소하는 경우
					// currentIOEP의 하단이 currentIOEP의 lowest child보다 위로 가면 X
					if ( lowestCurrentChildEP != null ) {
						int bottomNewCurrentIOEP = ApexSequenceUtil.apexGetAbsolutePosition(currentIOEP, SWT.BOTTOM) - Math.abs(heightDelta);
						int bottomCurrentIOEPLowestChild = ApexSequenceUtil.apexGetAbsolutePosition(lowestCurrentChildEP, SWT.BOTTOM);
						
						if ( bottomNewCurrentIOEP < bottomCurrentIOEPLowestChild + COMBINED_FRAGMENT_FIGURE_BORDER) {
							isInvadingTargetChildren = true;
						}	
					}				
				}
			}
		} else { // 하나뿐인 Op의 경우
			List currentIOEPChildrenEditParts = ApexInteractionOperandUtil.apexGetInteractionOperandChildrenEditParts(currentIOEP);
			IGraphicalEditPart lowestCurrentChildEP = ApexSequenceUtil.apexGetLowestEditPartFromList(currentIOEPChildrenEditParts);
			if ( (direction & PositionConstants.NORTH) != 0 && heightDelta < 0 ) { // 상단에서 축소하는 경우
				// currentIOEP의 new lowest child가  currentIOEP의 하단보다 아래로 내려가면 X
				if ( lowestCurrentChildEP != null ) {
					int bottomNewLowestCurrentChildEP = ApexSequenceUtil.apexGetAbsolutePosition(lowestCurrentChildEP, SWT.BOTTOM) + Math.abs(heightDelta);				
					int bottomCurrentIOEP = ApexSequenceUtil.apexGetAbsolutePosition(currentIOEP, SWT.BOTTOM);
					
					if ( (bottomNewLowestCurrentChildEP + COMBINED_FRAGMENT_FIGURE_BORDER) >= bottomCurrentIOEP ) {
						isInvadingTargetChildren = true;
					}	
				}
			ApexSequenceUtil.apexGetCoveredLifelineEditParts(currentIOEP, true);
				
			}
		}

		return isInvadingTargetChildren;
	}
	
}
