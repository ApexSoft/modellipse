package kr.co.apexsoft.modellipse.customization.diagram.sequence.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.co.apexsoft.modellipse.customization.diagram.sequence.edit.policies.ApexInteractionCompartmentXYLayoutEditPolicy;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gmf.runtime.common.core.command.CompositeCommand;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.CompartmentEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.notation.Bounds;
import org.eclipse.uml2.uml.CombinedFragment;

public class ApexOperandBoundsComputeHelper {
	
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
	 * apex updated
	 * IO의 경계를 직접 변경하거나, IO내의 element의 move/resize에 의해 경계가 변경되는 경우 처리
	 * 
	 * @param request
	 * @param currentIOEP
	 * @return
	 */
	public static Command createIOEPResizeCommand(ChangeBoundsRequest request, ShapeNodeEditPart currentIOEP) {
		return createIOEPResizeCommand(request, currentIOEP, false);
	}
	
	/**
	 * apex updated
	 * IO의 경계를 직접 변경하거나, IO내의 element의 move/resize에 의해 경계가 변경되는 경우 처리
	 * Create interaction operand resize command
	 * 
	 * @param currentIOEP
	 * @param heightDelta
	 * @param compartEP
	 * @param direction
	 * @return
	 */
	public static Command createIOEPResizeCommand(ChangeBoundsRequest request, ShapeNodeEditPart currentIOEP, boolean isCalledByParentCFResize) {
		
		int widthDelta = request.getSizeDelta().width;
		int heightDelta = request.getSizeDelta().height;
		CompartmentEditPart compartEP = (CompartmentEditPart)currentIOEP.getParent();
		int direction = request.getResizeDirection();
		
		Bounds currentIOEPBounds = ApexSequenceUtil.getEditPartBounds(currentIOEP);
		
		ShapeNodeEditPart cfep = (ShapeNodeEditPart)compartEP.getParent();
		
		if (currentIOEPBounds == null) {
			return null;
		}
		
		if (heightDelta < 0) {
			if (currentIOEPBounds.getHeight() - Math.abs(heightDelta) < ApexOperandBoundsComputeHelper.DEFAULT_INTERACTION_OPERAND_HEIGHT) {
				return null;
			}
		}
		
		ShapeNodeEditPart targetIOEP = null;
		List<ShapeNodeEditPart> belowIOEPs = null;
		List<ShapeNodeEditPart> siblingIOEPsExceptTargetIOEP = null;
		
		if ((direction & PositionConstants.NORTH) != 0) {
			targetIOEP = ApexInteractionOperandUtil.findPreviousIOEP(compartEP, currentIOEP);
		} else if ((direction & PositionConstants.SOUTH) != 0) {
			targetIOEP = ApexInteractionOperandUtil.findLatterIOEP(compartEP, currentIOEP);
			belowIOEPs = ApexInteractionOperandUtil.apexFindBelowIOEPs(compartEP, currentIOEP);
		}
		if ((direction & PositionConstants.EAST_WEST) != 0) {
			List cfChildren = currentIOEP.getParent().getChildren();
			List<ShapeNodeEditPart> childrenIOEPs = new ArrayList<ShapeNodeEditPart>();
			for ( Object obj : cfChildren ) {
				if ( obj instanceof ShapeNodeEditPart
					 && !obj.equals(currentIOEP)
					 && !obj.equals(targetIOEP) ) {
					childrenIOEPs.add((ShapeNodeEditPart)obj);
				}
			}
			siblingIOEPsExceptTargetIOEP = childrenIOEPs;
		}
		
		CompositeCommand compositeCommand = new CompositeCommand("Resize Operand");
		
		ChangeBoundsRequest cbCFRequest = new ChangeBoundsRequest(ApexSequenceRequestConstants.APEX_REQUEST_MOVE_AND_RESIZE_COMBINEDFRAGMENT);
		
		Rectangle currentIOEPRect = ApexSequenceUtil.boundsToRectangle(currentIOEPBounds);
		
		IFigure cfFigure = cfep.getFigure();
		Rectangle cfRect = cfFigure.getBounds().getCopy();
		
		int cfX = cfRect.x;
		int cfY = cfRect.y;
		int cfWidth = cfRect.width;
		int cfHeight = cfRect.height;
		
		if ( (direction & PositionConstants.NORTH) != 0 ) { // 상단에서 확대/축소하는 경우
			
			// 최상단 Op의 경우
			if(currentIOEP == ApexInteractionOperandUtil.findFirstIOEP(compartEP)) {
				// 최상단 Op에서 위로 확장하는 경우
				// CF를 위로 이동 시키고 최상단 Op를 확장
				currentIOEPRect.setHeight(currentIOEPBounds.getHeight() + heightDelta);
				currentIOEPRect.setWidth(currentIOEPBounds.getWidth() + widthDelta);				
				currentIOEPRect.setY(0); // 최상단 Op 는 (0, 0)으로 고정
								
				// belowIOEPs의 width처리 및 Op의 상변 resize로 CF가 이동되어도 belowIOEP는 이동되지 않도록 처리
				belowIOEPs = ApexInteractionOperandUtil.apexFindBelowIOEPs(compartEP, currentIOEP);
				if ( belowIOEPs != null ) {
					for ( ShapeNodeEditPart aBelowIOEP : belowIOEPs ) {
						Bounds anIOEPBounds = ApexSequenceUtil.getEditPartBounds(aBelowIOEP);
						Rectangle anIOEPRect = ApexSequenceUtil.boundsToRectangle(anIOEPBounds);
						anIOEPRect.setWidth(anIOEPBounds.getWidth() + widthDelta);
						anIOEPRect.setY(anIOEPBounds.getY() + heightDelta);
						
						ICommand anIOEPCommand = ApexSequenceUtil.createUpdateEditPartBoundsCommand(aBelowIOEP, anIOEPRect);						

						compositeCommand.add(anIOEPCommand);
					}
				}
				
				ICommand currentIOEPCommand = ApexSequenceUtil.createUpdateEditPartBoundsCommand(currentIOEP, currentIOEPRect);
				
				compositeCommand.add(currentIOEPCommand);
				
				// CF 경계 처리
				if ( (direction & PositionConstants.WEST) != 0 ) { // 좌측에서 확대/축소하는 경우
					cfX -= widthDelta;
					cfY -= heightDelta;
					cfWidth += widthDelta;
					cfHeight += heightDelta;
					
					cbCFRequest.setMoveDelta(new Point(-widthDelta, -heightDelta));
					cbCFRequest.setSizeDelta(new Dimension(widthDelta, heightDelta));			
					
				} else if ( (direction & PositionConstants.EAST) != 0 ) {
					cfY -= heightDelta;
					cfWidth += widthDelta;
					cfHeight += heightDelta;
					
					cbCFRequest.setMoveDelta(new Point(0, -heightDelta));
					cbCFRequest.setSizeDelta(new Dimension(widthDelta, heightDelta));
				} else {
					cfY -= heightDelta;
					cfHeight += heightDelta;
					
					cbCFRequest.setMoveDelta(new Point(0, -heightDelta));
					cbCFRequest.setSizeDelta(new Dimension(0, heightDelta));
				}
				
			} else { // 최상단 Op가 아닌 경우				

				// Operand Resize에 의한 새 경계가 targetIOEP의 Lowest child 를 침범 시 X 표시 되도록 처리
				if ( ApexInteractionOperandUtil.apexIsInvadingTargetChildren(currentIOEP, compartEP, direction, heightDelta) ) {
					return null;
				}
				
				Bounds targetIOEPBounds = ApexSequenceUtil.getEditPartBounds(targetIOEP);
				if (targetIOEPBounds == null) {
					return null;
				}										

				Rectangle targetIOEPRect = null;	
				targetIOEPRect = ApexSequenceUtil.boundsToRectangle(targetIOEPBounds);
			
				// 축소되는 targetIOEP가 최소 높이 이하로 축소되지 않도록
				if (targetIOEPBounds.getHeight() - heightDelta < ApexOperandBoundsComputeHelper.DEFAULT_INTERACTION_OPERAND_HEIGHT) {
					return null;
				}
				
				// target을 축소하고, current를 확대, CF경계 불변				
				targetIOEPRect.setHeight(targetIOEPBounds.getHeight() - heightDelta);
				currentIOEPRect.setHeight(currentIOEPBounds.getHeight() + heightDelta);
				
				targetIOEPRect.setWidth(targetIOEPBounds.getWidth() + widthDelta);
				currentIOEPRect.setWidth(currentIOEPBounds.getWidth() + widthDelta);
				
				currentIOEPRect.setY(currentIOEPRect.y() - heightDelta);
				
				// target를 제외한 sibling width 변경
				List<ShapeNodeEditPart> siblingsExceptBelow = siblingIOEPsExceptTargetIOEP;
				if ( siblingsExceptBelow != null) {
				
					for (ShapeNodeEditPart anIoep : siblingsExceptBelow) {
						Bounds anIOEPBounds = ApexSequenceUtil.getEditPartBounds(anIoep);
						Rectangle anIOEPRect = ApexSequenceUtil.boundsToRectangle(anIOEPBounds);
						anIOEPRect.setWidth(anIOEPBounds.getWidth() + widthDelta);
						
						ICommand anIOEPCommand = ApexSequenceUtil.createUpdateEditPartBoundsCommand(anIoep, anIOEPRect);						

						compositeCommand.add(anIOEPCommand);
					}	
				}				
				
				ICommand currentIOEPCommand = ApexSequenceUtil.createUpdateEditPartBoundsCommand(currentIOEP, currentIOEPRect);
				ICommand targetIOEPCommand = ApexSequenceUtil.createUpdateEditPartBoundsCommand(targetIOEP, targetIOEPRect);
				
				compositeCommand.add(currentIOEPCommand);	
				compositeCommand.add(targetIOEPCommand);
				
				// CF 경계 처리를 위한 ChangeBoundsRequeset 처리
				if ( (direction & PositionConstants.WEST) != 0 ) { // 좌측에서 확대/축소하는 경우
					cfX -= widthDelta;
					cfWidth += widthDelta;
					
					cbCFRequest.setMoveDelta(new Point(-widthDelta, 0));
					cbCFRequest.setSizeDelta(new Dimension(widthDelta, 0));
				} else if ( (direction & PositionConstants.EAST) != 0 ) { // 우측에서 확대/축소하는 경우
					cfWidth += widthDelta;
					
					cbCFRequest.setSizeDelta(new Dimension(widthDelta, 0));
				}			
			}
		
		} else if ( (direction & PositionConstants.SOUTH) != 0 ) { // 하단에서 아래로 드래그하여 확대하는 경우
			
			// 최하단 Op의 경우
			if(currentIOEP == ApexInteractionOperandUtil.findLastIOEP(compartEP)) {

				// 최하단 Op에서 Resize시 CF Resize
				currentIOEPRect.setHeight(currentIOEPBounds.getHeight() + heightDelta);				
				currentIOEPRect.setWidth(currentIOEPBounds.getWidth() + widthDelta);
				
				// target를 제외한 sibling width 변경
				List<ShapeNodeEditPart> siblingsExceptBelow = siblingIOEPsExceptTargetIOEP;
				if ( siblingsExceptBelow != null) {
				
					for (ShapeNodeEditPart anIoep : siblingsExceptBelow) {
						Bounds anIOEPBounds = ApexSequenceUtil.getEditPartBounds(anIoep);
						Rectangle anIOEPRect = ApexSequenceUtil.boundsToRectangle(anIOEPBounds);
						anIOEPRect.setWidth(anIOEPBounds.getWidth() + widthDelta);
						
						ICommand anIOEPCommand = ApexSequenceUtil.createUpdateEditPartBoundsCommand(anIoep, anIOEPRect);						

						compositeCommand.add(anIOEPCommand);
					}	
				}
				
				ICommand currentIOEPCommand = ApexSequenceUtil.createUpdateEditPartBoundsCommand(currentIOEP, currentIOEPRect);
				compositeCommand.add(currentIOEPCommand);
				
				// CF 경계 처리
				if ( (direction & PositionConstants.WEST) != 0 ) { // 좌측에서 확대/축소하는 경우
					cfX -= widthDelta;
					cfWidth += widthDelta;
					cfHeight += heightDelta;
					
					cbCFRequest.setMoveDelta(new Point(-widthDelta, 0));
					cbCFRequest.setSizeDelta(new Dimension(widthDelta, heightDelta));
				} else if ( (direction & PositionConstants.EAST) != 0 ) {						
					cfWidth += widthDelta;
					cfHeight += heightDelta;
					
					cbCFRequest.setSizeDelta(new Dimension(widthDelta, heightDelta));
				} else {
					cfHeight += heightDelta;
					
					cbCFRequest.setSizeDelta(new Dimension(0, heightDelta));
				}
				
			} else { // 최하단 Op 가 아닌 경우
				// current 경계 확대
				currentIOEPRect.setHeight(currentIOEPBounds.getHeight() + heightDelta);
				currentIOEPRect.setWidth(currentIOEPBounds.getWidth() + widthDelta);
				ICommand currentIOEPCommand = ApexSequenceUtil.createUpdateEditPartBoundsCommand(currentIOEP, currentIOEPRect);
				compositeCommand.add(currentIOEPCommand);
				
				// belowIOEPs 위/아래로 이동 및 width 처리
				if ( belowIOEPs != null ) {
					for ( ShapeNodeEditPart belowIOEP : belowIOEPs ) {						
						Bounds belowIOEPBounds = ApexSequenceUtil.getEditPartBounds(belowIOEP);
						Rectangle belowIOEPRect = ApexSequenceUtil.boundsToRectangle(belowIOEPBounds);
						
						belowIOEPRect.setY(belowIOEPRect.y() + heightDelta);
						belowIOEPRect.setWidth(belowIOEPBounds.getWidth() + widthDelta);
						
						ICommand belowIOEPCommand = ApexSequenceUtil.createUpdateEditPartBoundsCommand(belowIOEP, belowIOEPRect);						

						compositeCommand.add(belowIOEPCommand);
					}	
				}				
				
				// below를 제외한 sibling width 변경
				List<ShapeNodeEditPart> siblingsExceptBelow = siblingIOEPsExceptTargetIOEP;
				if ( siblingsExceptBelow != null) {
					if ( belowIOEPs != null ) {
						siblingsExceptBelow.removeAll(belowIOEPs);	
					}
					for (ShapeNodeEditPart anIoep : siblingsExceptBelow) {
						Bounds anIOEPBounds = ApexSequenceUtil.getEditPartBounds(anIoep);
						Rectangle anIOEPRect = ApexSequenceUtil.boundsToRectangle(anIOEPBounds);
						anIOEPRect.setWidth(anIOEPBounds.getWidth() + widthDelta);
						
						ICommand anIOEPCommand = ApexSequenceUtil.createUpdateEditPartBoundsCommand(anIoep, anIOEPRect);	
						compositeCommand.add(anIOEPCommand);
					}	
				}
				
				// CF 경계 처리를 위한 ChangeBoundsRequeset 처리
				if ( (direction & PositionConstants.WEST) != 0 ) { // 좌측에서 확대/축소하는 경우					
					cfX -= widthDelta;
					cfWidth += widthDelta;
					cfHeight += heightDelta;
					
					cbCFRequest.setMoveDelta(new Point(-widthDelta, 0));
					cbCFRequest.setSizeDelta(new Dimension(widthDelta, heightDelta));
				} else if ( (direction & PositionConstants.EAST) != 0 ) { // 우측에서 확대/축소하는 경우
					cfWidth += widthDelta;
					cfHeight += heightDelta;
					
					cbCFRequest.setSizeDelta(new Dimension(widthDelta, heightDelta));
				} else { // 하단에서 
					cfWidth += widthDelta;
					cfHeight += heightDelta;
					
					cbCFRequest.setSizeDelta(new Dimension(widthDelta, heightDelta));
				}
			}
			
		} else if ( (direction & PositionConstants.NORTH_SOUTH) == 0 ) { // 좌우로만 이동하는 경우
			// current 경계 확대
			currentIOEPRect.setWidth(currentIOEPBounds.getWidth() + widthDelta);
			ICommand currentIOEPCommand = ApexSequenceUtil.createUpdateEditPartBoundsCommand(currentIOEP, currentIOEPRect);
			compositeCommand.add(currentIOEPCommand);
			
			// belowIOEPs 위/아래로 이동 및 width 처리
			if ( belowIOEPs != null ) {
				for ( ShapeNodeEditPart belowIOEP : belowIOEPs ) {						
					Bounds belowIOEPBounds = ApexSequenceUtil.getEditPartBounds(belowIOEP);
					Rectangle belowIOEPRect = ApexSequenceUtil.boundsToRectangle(belowIOEPBounds);
					
					belowIOEPRect.setY(belowIOEPRect.y() + heightDelta);
					belowIOEPRect.setWidth(belowIOEPBounds.getWidth() + widthDelta);
					
					ICommand belowIOEPCommand = ApexSequenceUtil.createUpdateEditPartBoundsCommand(belowIOEP, belowIOEPRect);
					compositeCommand.add(belowIOEPCommand);
				}	
			}			
			
			// below를 제외한 sibling width 변경
			List<ShapeNodeEditPart> siblingsExceptBelow = siblingIOEPsExceptTargetIOEP;
			if ( siblingsExceptBelow != null) {
				if ( belowIOEPs != null ) {
					siblingsExceptBelow.removeAll(belowIOEPs);	
				}				
				for (ShapeNodeEditPart anIoep : siblingsExceptBelow) {
					Bounds anIOEPBounds = ApexSequenceUtil.getEditPartBounds(anIoep);
					Rectangle anIOEPRect = ApexSequenceUtil.boundsToRectangle(anIOEPBounds);
					anIOEPRect.setWidth(anIOEPBounds.getWidth() + widthDelta);
					
					ICommand anIOEPCommand = ApexSequenceUtil.createUpdateEditPartBoundsCommand(anIoep, anIOEPRect);	
					compositeCommand.add(anIOEPCommand);
				}	
			}
			
			// CF 경계 처리
			if ( (direction & PositionConstants.WEST) != 0 ) { // 좌측에서 확대/축소하는 경우
				cfX -= widthDelta;				
				cbCFRequest.setMoveDelta(new Point(-widthDelta, 0));
			}
			cfWidth += widthDelta;		
			
			cbCFRequest.setSizeDelta(new Dimension(widthDelta, 0));			
		}
		
		if ( !isCalledByParentCFResize ) {
			Point moveDelta = cbCFRequest.getMoveDelta();
			Dimension sizeDelta = cbCFRequest.getSizeDelta();
			
			if ( !(moveDelta.equals(0, 0) && sizeDelta.equals(0, 0)) ) {
				EditPart ep = compartEP.getParent();
				
				if(ep instanceof ShapeNodeEditPart){
					EObject eObj = ((ShapeNodeEditPart) ep).resolveSemanticElement();
					
					if ( eObj instanceof CombinedFragment ) {
						ShapeNodeEditPart parentCfep = (ShapeNodeEditPart)ep;
						Map apexResizeInfo = new HashMap();
						apexResizeInfo.put(ApexSequenceRequestConstants.APEX_KEY_RESIZING_COMBINEDFRAGMENTEDITPART_BY_INTERACTIONOPERAND_RESIZE, currentIOEP);
						cbCFRequest.setExtendedData(apexResizeInfo);
						cbCFRequest.setResizeDirection(direction);
						
						Command compoundCmd = ApexInteractionCompartmentXYLayoutEditPolicy.getCombinedFragmentResizeChildrenCommand(cbCFRequest, parentCfep);
						ApexSequenceUtil.apexCompoundCommandToCompositeCommand(compoundCmd, compositeCommand);
					}
				}				
			}	
		}
		return new ICommandProxy(compositeCommand);
	}
}
