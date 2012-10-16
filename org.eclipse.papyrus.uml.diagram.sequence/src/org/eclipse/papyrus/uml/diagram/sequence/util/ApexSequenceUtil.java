/*****************************************************************************
 * Copyright (c) 2012 ApexSoft
 *
 *    
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   ApexSoft - Initial API and implementation
 *
 *****************************************************************************/
package org.eclipse.papyrus.uml.diagram.sequence.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gmf.runtime.common.core.command.CompositeCommand;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.common.core.command.UnexecutableCommand;
import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.INodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.papyrus.uml.diagram.common.util.DiagramEditPartsUtil;
import org.eclipse.papyrus.uml.diagram.sequence.edit.parts.AbstractExecutionSpecificationEditPart;
import org.eclipse.papyrus.uml.diagram.sequence.edit.parts.CombinedFragment2EditPart;
import org.eclipse.papyrus.uml.diagram.sequence.edit.parts.CombinedFragmentCombinedFragmentCompartmentEditPart;
import org.eclipse.papyrus.uml.diagram.sequence.edit.parts.CombinedFragmentEditPart;
import org.eclipse.papyrus.uml.diagram.sequence.edit.parts.ContinuationEditPart;
import org.eclipse.papyrus.uml.diagram.sequence.edit.parts.InteractionEditPart;
import org.eclipse.papyrus.uml.diagram.sequence.edit.parts.InteractionInteractionCompartmentEditPart;
import org.eclipse.papyrus.uml.diagram.sequence.edit.parts.InteractionOperandEditPart;
import org.eclipse.papyrus.uml.diagram.sequence.edit.parts.InteractionUseEditPart;
import org.eclipse.papyrus.uml.diagram.sequence.edit.parts.LifelineEditPart;
import org.eclipse.swt.SWT;
import org.eclipse.uml2.common.util.CacheAdapter;
import org.eclipse.uml2.uml.CombinedFragment;
import org.eclipse.uml2.uml.ExecutionOccurrenceSpecification;
import org.eclipse.uml2.uml.ExecutionSpecification;
import org.eclipse.uml2.uml.Interaction;
import org.eclipse.uml2.uml.InteractionFragment;
import org.eclipse.uml2.uml.InteractionOperand;
import org.eclipse.uml2.uml.Lifeline;
import org.eclipse.uml2.uml.Message;
import org.eclipse.uml2.uml.MessageEnd;
import org.eclipse.uml2.uml.MessageOccurrenceSpecification;
import org.eclipse.uml2.uml.OccurrenceSpecification;

@SuppressWarnings({"unchecked", "rawtypes"})
public class ApexSequenceUtil {

	/**
	 * 二쇱뼱吏�EditPartEntry Set�먯꽌 �대떦 AbstractGraphicalEditPart 蹂대떎
	 * y 醫뚰몴媛��꾨옒���덈뒗 EditPartList 諛섑솚 
	 * 
	 * �ъ슜 ����
	 * 
	 * @param agep   湲곗����섎뒗 AbstractGraphicalEditPart
	 * @return aep蹂대떎 �꾨옒���꾩튂��EditPart��List
	 */
	public static List apexGetBelowEditPartList(IGraphicalEditPart agep) {
					
		Set<Entry<Object, EditPart>> wholeEditPartEntries = agep.getViewer().getEditPartRegistry().entrySet();
		
		Map<IGraphicalEditPart, Integer> belowEditPartMap = new HashMap<IGraphicalEditPart, Integer>();

		int yBottomOfAgep = apexGetAbsolutePosition(agep, SWT.BOTTOM);
		
		for (Entry<Object, EditPart> aEPEntry : wholeEditPartEntries ) {
			
			EditPart editPart = aEPEntry.getValue();
			
			if (editPart.equals(agep))
				continue;
			if ( editPart instanceof IGraphicalEditPart ) {				
				IGraphicalEditPart agep1 = (IGraphicalEditPart)editPart;				
				int yTopThisEP = apexGetAbsolutePosition(agep1, SWT.TOP);
				if ( yTopThisEP >= yBottomOfAgep
						&& !belowEditPartMap.containsKey(agep1)) {
					belowEditPartMap.put(agep1, yTopThisEP);
					
				}	
			}	
		}
		
		Collection<Entry<IGraphicalEditPart, Integer>> entrySet = belowEditPartMap.entrySet();
		List<Entry<IGraphicalEditPart, Integer>> entryList = new ArrayList<Entry<IGraphicalEditPart, Integer>>(entrySet);
		Collections.sort(entryList, new Comparator<Entry<IGraphicalEditPart, Integer>>() {
			public int compare(Entry<IGraphicalEditPart, Integer> o1,
					Entry<IGraphicalEditPart, Integer> o2) {
				return o1.getValue().compareTo(o2.getValue());
			}
		});
		
		List<IGraphicalEditPart> belowEditPartList = new ArrayList<IGraphicalEditPart>(entryList.size());
		for (Entry<IGraphicalEditPart, Integer> entry : entryList) {
			belowEditPartList.add(entry.getKey());
		}

		return belowEditPartList;
	}
	
	/** 
	 * �대떦 AbstractGraphicalEditPart 蹂대떎 y醫뚰몴媛��꾨옒���덉뼱
	 * �섑뼢 �대룞 ���④퍡 ��쭅�ъ쨾����EditPartList 諛섑솚
	 * 
	 * agep媛�Interaction��child��寃쎌슦 Interaction��children留��꾩튂鍮꾧탳�섏뿬 List��異붽�(以묒꺽��EP��異붽� �덊븿)
	 * agep媛�InteractionOperand��child��寃쎌슦 �대떦 Op���ы븿��Sibling留��꾩튂鍮꾧탳�섏뿬 List��異붽� 
	 * 
	 * @param agep   湲곗����섎뒗 AbstractGraphicalEditPart
	 * @return aep蹂대떎 �꾨옒���꾩튂��EditPart��List
	 */
	public static List apexGetMovableEditPartList(IGraphicalEditPart agep) {
					
		Set<Entry<Object, EditPart>> wholeEditPartEntries = agep.getViewer().getEditPartRegistry().entrySet();
		
		Map<IGraphicalEditPart, Integer> belowEditPartMap = new HashMap<IGraphicalEditPart, Integer>();

		int yBottomOfAgep = apexGetAbsolutePosition(agep, SWT.BOTTOM);
		
		for (Entry<Object, EditPart> aEPEntry : wholeEditPartEntries ) {
			
			EditPart editPart = aEPEntry.getValue();
			if (editPart.equals(agep))
				continue;
			if ( editPart instanceof IGraphicalEditPart ) {
				
				IGraphicalEditPart agep1 = (IGraphicalEditPart)editPart;

				// agep媛�以묒꺽��寃쎌슦 �대떦 OP�댁쓽 �붿냼留��꾩튂鍮꾧탳�섏뿬 異붽�
				if ( agep.getParent() instanceof InteractionOperandEditPart ) {
					InteractionOperandEditPart ioep = (InteractionOperandEditPart)agep.getParent();
					List<EditPart> siblings = apexGetInteractionOperandChildrenEditParts(ioep);
					
					for ( EditPart ep : siblings ) {
						if (ep.equals(agep))
							continue;
						if ( ep instanceof IGraphicalEditPart ) {
							
							IGraphicalEditPart agep2 = (IGraphicalEditPart)ep;
							
							int yTopThisEP = apexGetAbsolutePosition(agep2, SWT.TOP);

							if ( yTopThisEP >= yBottomOfAgep
									&& !belowEditPartMap.containsKey(agep2)) {
								belowEditPartMap.put(agep2, yTopThisEP);
							}	
							
						}
					}
				// agep 媛�以묒꺽�섏� �딆� 寃쎌슦 interactionCompartment �댁쓽 �붿냼留��꾩튂鍮꾧탳�섏뿬 異붽�
				} else if ( agep.getParent() instanceof InteractionInteractionCompartmentEditPart ) {
					if ( agep1.getParent() instanceof InteractionInteractionCompartmentEditPart ) {
						int yTopThisEP = apexGetAbsolutePosition(agep1, SWT.TOP);

						if ( yTopThisEP >= yBottomOfAgep
								&& !belowEditPartMap.containsKey(agep1)) {
							belowEditPartMap.put(agep1, yTopThisEP);
						}	
					}
				}
			}	
		}
		
		Collection<Entry<IGraphicalEditPart, Integer>> entrySet = belowEditPartMap.entrySet();
		List<Entry<IGraphicalEditPart, Integer>> entryList = new ArrayList<Entry<IGraphicalEditPart, Integer>>(entrySet);
		Collections.sort(entryList, new Comparator<Entry<IGraphicalEditPart, Integer>>() {
			public int compare(Entry<IGraphicalEditPart, Integer> o1,
					Entry<IGraphicalEditPart, Integer> o2) {
				return o1.getValue().compareTo(o2.getValue());
			}
		});
		
		List<IGraphicalEditPart> belowEditPartList = new ArrayList<IGraphicalEditPart>(entryList.size());
		for (Entry<IGraphicalEditPart, Integer> entry : entryList) {
			belowEditPartList.add(entry.getKey());
		}
		return belowEditPartList;
	}

	
	
	/**
	 * 二쇱뼱吏�EditPartList瑜�寃�깋�섏뿬
	 * y醫뚰몴 湲곗� 二쇱뼱吏�AbstractGraphicalEditPart��諛붾줈 �꾨옒���꾩튂��AbstractGraphicalEditPart 諛섑솚
	 * 
	 * @param agep    湲곗����섎뒗 AbstractGraphicalEditPart
	 * @param belowEditPartList    寃�깋��EditPart��List
	 * @return    y醫뚰몴 湲곗� agep��諛붾줈 �꾨옒���꾩튂��AbstractGraphicalEditPart
	 */
	public static IGraphicalEditPart apexGetBeneathEditPart(IGraphicalEditPart agep, List belowEditPartList) {

		int gap = Integer.MAX_VALUE;
		IGraphicalEditPart beneathEditPart = null;

		int yCF = apexGetAbsolutePosition(agep, SWT.BOTTOM);
		
		Iterator it = belowEditPartList.iterator();
		
		while( it.hasNext()) {
			
			IGraphicalEditPart sep = (IGraphicalEditPart)it.next();
			
			int yEP = apexGetAbsolutePosition(sep, SWT.TOP);
			
			int thisGap = yEP - yCF;
			
			if ( thisGap < gap ) {
				gap = thisGap;
				beneathEditPart = sep;
			}
		}
		return beneathEditPart;
	}
	
	/**
	 * MovableEditParts 以묒뿉��AbstractGraphicalEditPart��諛붾줈 �꾨옒���꾩튂��AbstractGraphicalEditPart 諛섑솚
	 * 
	 * @param agep    湲곗����섎뒗 AbstractGraphicalEditPart
	 * @param belowEditPartList    寃�깋��EditPart��List
	 * @return    y醫뚰몴 湲곗� agep��諛붾줈 �꾨옒���꾩튂��AbstractGraphicalEditPart
	 */
	public static IGraphicalEditPart apexGetBeneathEditPart(IGraphicalEditPart agep) {

		List belowEditPartList = apexGetMovableEditPartList(agep);
		//List belowEditPartList = apexGetNextSiblingEditParts(agep);
		
		int gap = Integer.MAX_VALUE;
		IGraphicalEditPart beneathEditPart = null;

		int yCF = apexGetAbsolutePosition(agep, SWT.BOTTOM);
		
		Iterator it = belowEditPartList.iterator();
		
		while( it.hasNext()) {
			
			IGraphicalEditPart sep = (IGraphicalEditPart)it.next();
			
			int yEP = apexGetAbsolutePosition(sep, SWT.TOP);
			
			int thisGap = yEP - yCF;
			
			if ( thisGap < gap ) {
				gap = thisGap;
				beneathEditPart = sep;
			}
		}
		return beneathEditPart;
	}

	/**
     * 二쇱뼱吏�EditPart��紐⑤뱺 children��Indent�섏뿬 異쒕젰�섎뒗 Util
	 * 肄붾뱶 �댁뿉���몄텧 ���꾨떖 ��depth媛믪쓣 0�쇰줈 �섏뿬 蹂�硫붿꽌���몄텧
	 * 
	 * @param ep
	 */
	public static void apexShowChildrenEditPart(EditPart ep) {
		apexShowChildrenEditPart(ep, 0);
	}
	
	/**
	 * 二쇱뼱吏�EditPart��紐⑤뱺 children��Indent�섏뿬 異쒕젰�섎뒗 Util
	 * 肄붾뱶 �댁뿉���몄텧 ���꾨떖 ��depth媛믪쓣 湲곗��쇰줈 Indent 泥섎━
	 * (depth>=0���대뼡 媛믩룄 臾대갑�섎굹 �몄텧 ��depth=0 沅뚯옣) 
	 * 
	 * @param ep     異쒕젰��children��媛�쭊 EditPart
	 * @param depth  Indent �섏�
	 */
	public static void apexShowChildrenEditPart(EditPart ep, int depth) {
		List childrenList = ep.getChildren();
		Iterator it = childrenList.iterator();
		
		while ( it.hasNext() ) {
			StringBuffer sb = new StringBuffer();
			for ( int i = 0 ; i < depth ; i++ ) {
				sb.append(" ");	
			}
			
			EditPart o = (EditPart)it.next();
			
			System.out.println(sb.toString() + o/*.getClass().getSimpleName()*/);
			// interactionOperand��寃쎌슦 �ы븿�섎뒗 fragment瑜�異쒕젰
			if ( o instanceof InteractionOperandEditPart ) {
				InteractionOperand io = (InteractionOperand)((InteractionOperandEditPart)o).resolveSemanticElement();
				System.out.println(sb.toString() + "  " + "lifelines : " + io.getCovereds());
				System.out.println(sb.toString() + "  " + "fragments : " + io.getFragments());	
			}
			
			// children �덉쑝硫�depth 利앷� ���ш��몄텧
			if ( o.getChildren().size() > 0 ) {
				apexShowChildrenEditPart(o, depth+2);	
			}

		}	
	}
	
	/**
	 * �대떦 EditPart��紐⑤뱺 child EditPart瑜�leveling �놁씠 諛섑솚
	 * 鍮�List瑜��앹꽦�섏뿬 蹂�硫붿꽌��apexGetChildEditPartList(EditPart, List) �몄텧
	 * 
	 * @param ep
	 * @return
	 */
	public static List apexGetChildEditPartList(EditPart ep) {		
		return apexGetChildEditPartList(ep, new ArrayList());
	}
	
	/**
	 * �대떦 EditPart��紐⑤뱺 child EditPart瑜�leveling �놁씠 諛섑솚
	 * 
	 * @param ep
	 * @param childEPList
	 * @return
	 */
	public static List apexGetChildEditPartList(EditPart ep, List childEPList) {
		
		List<EditPart> childrenList = ep.getChildren();
		Iterator it = childrenList.iterator();
		
		while ( it.hasNext() ) {
			EditPart o = (EditPart)it.next();

			childEPList.add(o);

			if ( o.getChildren().size() > 0 ) {
				apexGetChildEditPartList(o, childEPList);	
			}
		}
		return childEPList;
	}
	
	/**
	 * ArrayList瑜��뚮씪誘명꽣濡�異붽��섏뿬 apexGetParentCombinedFragmentEditPartList(CombinedFragmentEditPart, List) �몄텧
	 * 
	 * @param cfep
	 * @return
	 */
	public static List<CombinedFragmentEditPart> apexGetParentCombinedFragmentEditPartList(CombinedFragmentEditPart cfep) {
		return apexGetParentCombinedFragmentEditPartList(cfep, new ArrayList<CombinedFragmentEditPart>());
	}

	/**
	 * 
	 * @param cfep
	 * @param parentCombinedFragmentEditParts
	 * @return
	 */
	public static List<CombinedFragmentEditPart> apexGetParentCombinedFragmentEditPartList(CombinedFragmentEditPart cfep, List<CombinedFragmentEditPart> parentCombinedFragmentEditParts) {
				
		EditPart opParent = cfep.getParent();

		if ( opParent instanceof InteractionOperandEditPart ) {
			
			CombinedFragmentEditPart parentCombinedFragmentEditPart = (CombinedFragmentEditPart)opParent.getParent().getParent();
			
			parentCombinedFragmentEditParts.add(parentCombinedFragmentEditPart);
			
			apexGetParentCombinedFragmentEditPartList(parentCombinedFragmentEditPart, parentCombinedFragmentEditParts);			
		}
		return parentCombinedFragmentEditParts;
	}
	
	/**
	 * 二쇱뼱吏�EditPartEntry Set�먯꽌 �대떦 AbstractGraphicalEditPart 蹂대떎
	 * y 醫뚰몴媛��꾩뿉 �덈뒗 EditPartList 諛섑솚 
	 * 
	 * @param agep   湲곗����섎뒗 AbstractGraphicalEditPart
	 * @return aep蹂대떎 �꾩뿉 �꾩튂��EditPart��List
	 */
	public static List apexGetHigherEditPartList(IGraphicalEditPart agep) {
		
//apexTestCoordinateSystem(agep);
		
		Set<Entry<Object, EditPart>> wholeEditPartEntries = agep.getViewer().getEditPartRegistry().entrySet();

		Map<IGraphicalEditPart, Integer> higherEditPartMap = new HashMap<IGraphicalEditPart, Integer>();
		
		int yTopOfAgep = apexGetAbsolutePosition(agep, SWT.TOP);
		
		for (Entry<Object, EditPart> aEPEntry : wholeEditPartEntries ) {
			
			EditPart editPart = aEPEntry.getValue();
			
			if (editPart.equals(agep))
				continue;
			if (!(editPart instanceof INodeEditPart))
				continue;
			if ( editPart instanceof IGraphicalEditPart ) {
				
				IGraphicalEditPart agep1 = (IGraphicalEditPart)editPart;
				
				int yBottomThisEP = apexGetAbsolutePosition(agep1, SWT.BOTTOM);
/*8
System.out.println("ApexSequenceUtil.apexGetHigherEditPartList, line : "
		+ Thread.currentThread().getStackTrace()[1].getLineNumber());
System.out.println("agep,     yTopOfAgep : " + agep.getClass() + ", " + yTopOfAgep);
if ( agep1 instanceof CombinedFragmentEditPart) {
System.out.println("agep.bounds     : " + agep.getFigure().getBounds().getCopy());	
System.out.println("agep.absBounds  : " + apexGetAbsoluteRectangle(agep));
}
System.out.println("agep1, yBottomThisEP : " + agep1.getClass() + ", " + yBottomThisEP);
if ( agep1 instanceof CombinedFragmentEditPart) {
System.out.println("agep1.bounds    : " + agep1.getFigure().getBounds().getCopy());	
System.out.println("agep1.absBounds : " + apexGetAbsoluteRectangle(agep1));
}
//*/
				if ( yBottomThisEP < yTopOfAgep
						&& !higherEditPartMap.containsKey(agep1)) {					
					higherEditPartMap.put(agep1, yBottomThisEP);
				}
			}	
		}

		Collection<Entry<IGraphicalEditPart, Integer>> entrySet = higherEditPartMap.entrySet();
		List<Entry<IGraphicalEditPart, Integer>> entryList = new ArrayList<Entry<IGraphicalEditPart, Integer>>(entrySet);
		Collections.sort(entryList, new Comparator<Entry<IGraphicalEditPart, Integer>>() {
			public int compare(Entry<IGraphicalEditPart, Integer> o1,
					Entry<IGraphicalEditPart, Integer> o2) {
				return o2.getValue().compareTo(o1.getValue());
			}
		});
		
		List<IGraphicalEditPart> higherEditPartList = new ArrayList<IGraphicalEditPart>(entryList.size());
		for (Entry<IGraphicalEditPart, Integer> entry : entryList) {
			higherEditPartList.add(entry.getKey());
		}

		return higherEditPartList;
	}

	/**
	 * 二쇱뼱吏�EditPartList瑜�寃�깋�섏뿬
	 * y醫뚰몴 湲곗� 二쇱뼱吏�AbstractGraphicalEditPart��諛붾줈 �꾩뿉 �꾩튂��AbstractGraphicalEditPart 諛섑솚
	 * 
	 * @param agep    湲곗����섎뒗 AbstractGraphicalEditPart
	 * @param higherEditPartList    寃�깋��EditPart��List
	 * @return    y醫뚰몴 湲곗� agep��諛붾줈 �꾩뿉 �꾩튂��AbstractGraphicalEditPart
	 */
	public static IGraphicalEditPart apexGetAboveEditPart(IGraphicalEditPart agep, List higherEditPartList) {

		int gap = Integer.MAX_VALUE;
		IGraphicalEditPart aboveEditPart = null;
		
		int yCF = apexGetAbsolutePosition(agep, SWT.TOP);
		
		Iterator it = higherEditPartList.iterator();
		
		while( it.hasNext()) {
			IGraphicalEditPart sep = (IGraphicalEditPart)it.next();
			int yEP = apexGetAbsolutePosition(sep, SWT.BOTTOM);
			int thisGap = yCF - yEP;
			if ( thisGap < gap ) {
				gap = thisGap;
				aboveEditPart = sep;
			}
		}
		return aboveEditPart;
	}
	
	/**
	 * 二쇱뼱吏�EditPart List�먯꽌 bottom 湲곗� 媛�옣 �꾨옒���덈뒗 EditPart 諛섑솚
	 * @param editPartList
	 * @return
	 */
	public static IGraphicalEditPart apexGetLowestEditPartFromList(List<IGraphicalEditPart> editPartList) {
		
		int bottom = Integer.MIN_VALUE;
		IGraphicalEditPart lowestEditPart = null;
		
		for (IGraphicalEditPart ep : editPartList) {	
			
			int epBottom = apexGetAbsolutePosition(ep, SWT.BOTTOM);
			
			if ( epBottom > bottom) {
				lowestEditPart = ep;
				bottom = epBottom;
			}
		}
		
		return lowestEditPart;
	}
	
	/**
	 * 二쇱뼱吏�EditPart List�먯꽌 top 湲곗� 媛�옣 �꾩뿉 �덈뒗 EditPart 諛섑솚
	 * @param editPartList
	 * @return
	 */
	public static IGraphicalEditPart apexGetHighestEditPartFromList(List<IGraphicalEditPart> editPartList) {
		
		int top = Integer.MAX_VALUE;
		IGraphicalEditPart highestEditPart = null;
		
		for (IGraphicalEditPart ep : editPartList) {	
			
			int epTop = apexGetAbsolutePosition(ep, SWT.TOP);
			
			if ( epTop < top) {
				highestEditPart = ep;
				top = epTop;
			}
		}
		
		return highestEditPart;
	}
	
	/**
	 * Message��留곹겕��ExecutionSpec怨�Message�ㅼ쓣 由ъ뒪�몃줈 諛섑솚
	 * 
	 * @param agep
	 * @param findConnection	Whether to find Message or not 
	 * @param findExecSpec		Whether to find ExecutionSpec or not 
	 * @param findFront
	 * @return
	 */
	public static List<IGraphicalEditPart> apexGetLinkedEditPartList(IGraphicalEditPart agep, boolean findConnection, boolean findExecSpec, boolean findFront) {
		return apexGetLinkedEditPartList(agep, findConnection, findExecSpec, findFront, new ArrayList());
	}
	
	/**
	 * Message��留곹겕��ExecutionSpec怨�Message�ㅼ쓣 由ъ뒪�몃줈 諛섑솚
	 * 
	 * @param agep Message or ExecutionSpecification
	 * @param findConnection
	 * @param findExecSpec
	 * @param findFront
	 * @param list
	 * @return
	 */
	public static List<IGraphicalEditPart> apexGetLinkedEditPartList(IGraphicalEditPart agep, boolean findConnection, boolean findExecSpec, boolean findFront, List list) {
		if (agep instanceof ConnectionNodeEditPart) {
			ConnectionNodeEditPart cep = (ConnectionNodeEditPart)agep;
			if (findConnection)
				list.add(cep);
			
			IGraphicalEditPart nextEditPart = null;
			if (!findFront)
				nextEditPart = (IGraphicalEditPart)cep.getTarget();
			else
				nextEditPart = (IGraphicalEditPart)cep.getSource();
			apexGetLinkedEditPartList(nextEditPart, findConnection, findExecSpec, findFront, list);
		}
		else if (agep instanceof AbstractExecutionSpecificationEditPart) {
			if (findExecSpec)
				list.add(agep);
			
			List connections = null;
			if (!findFront)
				connections = agep.getSourceConnections();
			else
				connections = agep.getTargetConnections();
			
			if (connections != null) {
				Iterator iter = connections.iterator();
				while (iter.hasNext()) {
					ConnectionNodeEditPart srcConnection = (ConnectionNodeEditPart)iter.next();
					apexGetLinkedEditPartList((ConnectionNodeEditPart)srcConnection, findConnection, findExecSpec, findFront, list);
				}
			}
		}
		
		return list;
	}

	/**
	 * Message��留곹겕��EditPart��以�媛�옣 �섏쐞���덈뒗 EditPart瑜�諛섑솚 (bottom媛믪씠 媛�옣 �섏쐞)
	 * 
	 * @param agep
	 * @return
	 */
	public static IGraphicalEditPart apexGetBottomEditPartInLinked(IGraphicalEditPart agep) {
		List editPartList = apexGetLinkedEditPartList(agep, true, true, false, new ArrayList());
		Iterator iter = editPartList.iterator();
		int bottom = Integer.MIN_VALUE;
		IGraphicalEditPart bottomEditPart = null;
		while (iter.hasNext()) {
			Object next = iter.next();
			if (next instanceof IGraphicalEditPart) {
				int b = apexGetAbsolutePosition((IGraphicalEditPart)next, SWT.BOTTOM);
				if (b > bottom) {
					bottom = b;
					bottomEditPart = (IGraphicalEditPart)next;
				}
			}
		}
		return bottomEditPart;
	}
	
	public static Rectangle apexGetAbsoluteRectangle(IGraphicalEditPart gep) {
		Rectangle bounds = null;
		if ( gep != null ) {
			if (gep instanceof ConnectionNodeEditPart) {
				Connection conn = ((ConnectionNodeEditPart)gep).getConnectionFigure();
				PointList pl = conn.getPoints().getCopy();
				conn.translateToAbsolute(pl);
				bounds = pl.getBounds();
//				Point p2 = conn.getTargetAnchor().getReferencePoint();
//				Point p1 = conn.getSourceAnchor().getLocation(p2);
//				bounds = new Rectangle(p1.x(), p1.y, p2.x() - p1.x(), p2.y() - p1.y());
			} else {
				IFigure figure = gep.getFigure();
				bounds = figure.getBounds().getCopy();
				figure.translateToAbsolute(bounds);
			}
		}		
		
		return bounds;
	}	
	
	public static void apexTestCoordinateSystem(IGraphicalEditPart agep) {
		IFigure thisFigure = agep.getFigure();
		Rectangle origRect = thisFigure.getBounds().getCopy();
		Rectangle copyRect1 = thisFigure.getBounds().getCopy();
		Rectangle copyRect2 = thisFigure.getBounds().getCopy();
		Rectangle copyRect3 = thisFigure.getBounds().getCopy();
		Rectangle copyRect4 = thisFigure.getBounds().getCopy();
		
		System.out.println("----------------------");
		System.out.println("agep.getNotationView() :                        : " + agep.resolveSemanticElement());
		System.out.println("agep.getParent() :                              : " + agep.getParent());
		System.out.println("agep.getParent().getFigure()                    : " + ((AbstractGraphicalEditPart)agep.getParent()).getFigure());
		System.out.println("");
		System.out.println("thisFigure                                      : " + thisFigure);
		System.out.println("thisFigure.getParent()                          : " + thisFigure.getParent());
		System.out.println("");
		System.out.println("thisFigure.getInset() :                         : " + thisFigure.getInsets());
		System.out.println("thisFigure.getBounds().getCopy()                : " + origRect);	
		
		thisFigure.translateFromParent(copyRect1);
//		System.out.println("thisFigure.translateFromParent(copyRect1)       : " + copyRect1);		
		
		thisFigure.translateToParent(copyRect3);
//		System.out.println("thisFigure.translateToParent(copyRect3)         : " + copyRect3);
		
		thisFigure.translateToAbsolute(copyRect2);
		System.out.println("thisFigure.translateToAbsolute(copyRect2)       : " + copyRect2);
		
		thisFigure.translateToRelative(copyRect4);
//		System.out.println("thisFigure.translateToRelative(copyRect4)       : " + copyRect4);		
		
//		System.out.println("thisFigure.getParent().getClass()               : " + thisFigure.getParent().getClass());
//		System.out.println("thisFigure.getParent().getInsets()              : " + thisFigure.getParent().getInsets());
//		System.out.println("thisFigure.getParent().getBounds()              : " + thisFigure.getParent().getBounds());
		Rectangle parentR = thisFigure.getParent().getBounds().getCopy();
		thisFigure.getParent().translateToAbsolute(parentR);
		
		System.out.println("thisFigure.getParent().translateToAbs(parentF)  : " + parentR);
		
		Rectangle copyRect11 = thisFigure.getBounds().getCopy();
		Rectangle copyRect21 = thisFigure.getBounds().getCopy();
		Rectangle copyRect31 = thisFigure.getBounds().getCopy();
		Rectangle copyRect41 = thisFigure.getBounds().getCopy();
		
		thisFigure.getParent().translateFromParent(copyRect11);
//		System.out.println("parentFigure.translateFromParent(copyRect11)    : " + copyRect11);
		
		thisFigure.getParent().translateToParent(copyRect31);
//		System.out.println("parentFigure.translateToParent(copyRect31)      : " + copyRect31);
				
		thisFigure.getParent().translateToAbsolute(copyRect21);
		System.out.println("parentFigure.translateToAbsolute(copyRect21)    : " + copyRect21);

		
		thisFigure.getParent().translateToRelative(copyRect41);
//		System.out.println("parentFigure.translateToRelative(copyRect41)    : " + copyRect41);
		
//		apexShowChildrenEditPart(agep);
		List children = apexGetChildEditPartList(agep);
		Iterator it = children.iterator();
		
		while ( it.hasNext() ) {
			AbstractGraphicalEditPart agep1 = (AbstractGraphicalEditPart)it.next();
			if ( agep1 instanceof CombinedFragmentEditPart ) {
				CombinedFragmentEditPart cfep = (CombinedFragmentEditPart)agep1;
				
				IFigure childFigure = cfep.getFigure();
				Rectangle origRect2 = childFigure.getBounds().getCopy();
				Rectangle copyRect12 = childFigure.getBounds().getCopy();
				Rectangle copyRect22 = childFigure.getBounds().getCopy();
				Rectangle copyRect32 = childFigure.getBounds().getCopy();
				Rectangle copyRect42 = childFigure.getBounds().getCopy();
				
//				System.out.println("cfep.getNotationView() :                        : " + cfep.resolveSemanticElement());
//				System.out.println("cfep.getParent() :                              : " + cfep.getParent());
//				System.out.println("cfep.getParent().getFigure()                    : " + ((InteractionOperandEditPart)cfep.getParent()).getFigure());
//				System.out.println("");
//				System.out.println("childFigure                                      : " + childFigure);
//				System.out.println("childFigure.getParent()                          : " + childFigure.getParent());
				
				System.out.println("");
				System.out.println("childFigure.getBounds().getCopy()           : " + origRect2);	
				
				childFigure.translateFromParent(copyRect12);
//				System.out.println("childFigure.translateFromParent(copyRect12) : " + copyRect12);
				
				childFigure.translateToParent(copyRect32);
//				System.out.println("childFigure.translateToParent(copyRect32)   : " + copyRect32);
				
				childFigure.translateToAbsolute(copyRect22);
				System.out.println("childFigure.translateToAbsolute(copyRect22) : " + copyRect22);
				
				childFigure.translateToRelative(copyRect42);
//				System.out.println("childFigure.translateToRelative(copyRect42) : " + copyRect42);
				
//				System.out.println("childFigure.getParent().getClass()          : " + childFigure.getParent().getClass());
//				System.out.println("childFigure.getParent().getInsets()         : " + childFigure.getParent().getInsets());
				System.out.println("childFigure.getParent().getBounds()         : " + childFigure.getParent().getBounds());
				
				Rectangle copyRect13 = childFigure.getBounds().getCopy();
				Rectangle copyRect23 = childFigure.getBounds().getCopy();
				Rectangle copyRect33 = childFigure.getBounds().getCopy();
				Rectangle copyRect43 = childFigure.getBounds().getCopy();
				
				childFigure.getParent().translateFromParent(copyRect13);
//				System.out.println("parentFigure.translateFromParent(copyRect13): " + copyRect13);
				
				childFigure.getParent().translateToParent(copyRect33);
//				System.out.println("parentFigure.translateToParent(copyRect33)  : " + copyRect33);
				
				childFigure.getParent().translateToAbsolute(copyRect23);
				System.out.println("parentFigure.translateToAbsolute(copyRect23): " + copyRect23);
				
				childFigure.getParent().translateToRelative(copyRect43);
//				System.out.println("parentFigure.translateToRelative(copyRect43): " + copyRect43);
			}
		}
		
			
		
		
		
/*		
		org.eclipse.draw2d.geometry.Rectangle parentRect = new Rectangle(10, 20, 300, 100);
		IFigure parentFigure = new Figure();
		parentFigure.setBounds(parentRect);
		Rectangle origRect = parentFigure.getBounds().getCopy();
		Rectangle copyRect1 = parentFigure.getBounds().getCopy();
		Rectangle copyRect2 = parentFigure.getBounds().getCopy();
		Rectangle copyRect3 = parentFigure.getBounds().getCopy();
		Rectangle copyRect4 = parentFigure.getBounds().getCopy();
		
		System.out.println("----------------------");
		System.out.println("parentFigure.getBounds().getCopy()           : " + origRect);	
		
		parentFigure.translateFromParent(copyRect1);
		System.out.println("parentFigure.translateFromParent(copyRect1)  : " + copyRect1);
		
		parentFigure.translateToAbsolute(copyRect2);
		System.out.println("parentFigure.translateToAbsolute(copyRect2)  : " + copyRect2);
		
		parentFigure.translateToParent(copyRect3);
		System.out.println("parentFigure.translateToParent(copyRect3)    : " + copyRect3);
		
		parentFigure.translateToRelative(copyRect4);
		System.out.println("parentFigure.translateToRelative(copyRect4)  : " + copyRect4);
		
		org.eclipse.draw2d.geometry.Rectangle childRect = new Rectangle(40, 80, 100, 30);
		IFigure childFigure = new Figure();
		childFigure.setBounds(childRect);
		childFigure.setParent(parentFigure);
		Rectangle origRect1 = childFigure.getBounds().getCopy();
		Rectangle copyRect11 = childFigure.getBounds().getCopy();
		Rectangle copyRect22 = childFigure.getBounds().getCopy();
		Rectangle copyRect33 = childFigure.getBounds().getCopy();
		Rectangle copyRect44 = childFigure.getBounds().getCopy();
		
		System.out.println("childFigure.getBounds().getCopy()            : " + origRect1);	
		
		childFigure.getParent().translateFromParent(copyRect11);		
		System.out.println("parentFigure.translateFromParent(copyRect11) : " + copyRect11);
		
		childFigure.getParent().translateToAbsolute(copyRect22);
		System.out.println("parentFigure.translateToAbsolute(copyRect22) : " + copyRect22);
		
		childFigure.getParent().translateToParent(copyRect33);
		System.out.println("parentFigure.translateToParent(copyRect33)   : " + copyRect33);
		
		childFigure.getParent().translateToRelative(copyRect44);
		System.out.println("parentFigure.translateToRelative(copyRect44) : " + copyRect44);
*/
	}
	
	/**
	 * EditPart��Bottom媛믪쓣 諛섑솚. Message��寃쎌슦 Bendpoint��以�媛�옣 �섏쐞��媛믪쓣 諛섑솚
	 * 
	 * @param gep
	 * @param type	one of TOP, BOTTOM
	 * @return
	 */
	public static int apexGetAbsolutePosition(IGraphicalEditPart gep, int type) {
		Rectangle bounds = apexGetAbsoluteRectangle(gep);
		switch (type) {
			case SWT.TOP:
				return bounds.getTop().y();
			case SWT.BOTTOM:
				return bounds.getBottom().y();
			case SWT.LEFT:
				return bounds.x;
			case SWT.RIGHT:
				return bounds.right();				
		}
		return -1;
	}
	
	/**
	 * SequenceUtil���덈뜕 硫붿꽌�쒖�留��꾨Т���몄텧�섏� �딆븘
	 * ApexSequenceUtil 濡�媛�졇��꽌 媛쒖“ �ъ슜
	 * Property��covered �ㅼ젙怨�愿�퀎�놁씠
	 * �대떦 Rectangle��intersect�섎뒗 紐⑤뱺 Lifeline 諛섑솚
	 * �덈�醫뚰몴濡�鍮꾧탳
	 * 
	 * @param selectionRect �덈�醫뚰몴�붾맂 �좏깮�곸뿭
	 * @param hostEditPart
	 * @return
	 */
	public static List<LifelineEditPart> apexGetPositionallyCoveredLifelineEditParts(Rectangle selectionRect, AbstractGraphicalEditPart hostEditPart) {
		
		//hostEditPart.getFigure().translateToAbsolute(selectionRect);
		
		List positionallyCoveredLifelineEditParts = new ArrayList();

		// retrieve all the edit parts in the registry
		Set<Entry<Object, EditPart>> allEditPartEntries = hostEditPart.getViewer().getEditPartRegistry().entrySet();
		for(Entry<Object, EditPart> epEntry : allEditPartEntries) {
			EditPart ep = epEntry.getValue();

			if(ep instanceof LifelineEditPart) {
				Rectangle lifelineRect = ApexSequenceUtil.apexGetAbsoluteRectangle((LifelineEditPart)ep);

				if(selectionRect.intersects(lifelineRect)) {
					positionallyCoveredLifelineEditParts.add((LifelineEditPart)ep);
				}
			}

		}
		return positionallyCoveredLifelineEditParts;
	}
	
	/**
	 * lifelineRect���꾩튂�곸쑝濡�援먯감�섎뒗 CFEditPart List 諛섑솚
	 * 
	 * @param lifelineRect lifelineEditPart���덈�醫뚰몴寃쎄퀎
	 * @param hostEditPart �ы븿�щ� 湲곗����섎뒗 lifelineEditPart
	 * @return
	 */
	public static List<CombinedFragmentEditPart> apexGetPositionallyLifelineCoveringCFEditParts(Rectangle lifelineRect, AbstractGraphicalEditPart hostEditPart) {
		
//		System.out
//				.println("ApexSequenceUtil.apexGetPositionallyLifelineCoveringCFEditParts(), line : "
//						+ Thread.currentThread().getStackTrace()[1]
//								.getLineNumber());
//		System.out.println("lifelineRect in apexGetPosition.. : " + lifelineRect);
		
		
		List<CombinedFragmentEditPart> positionallyLifelineCoveringCFEditParts = new ArrayList<CombinedFragmentEditPart>();

		// retrieve all the edit parts in the registry
		Set<Entry<Object, EditPart>> allEditPartEntries = hostEditPart.getViewer().getEditPartRegistry().entrySet();
		for(Entry<Object, EditPart> epEntry : allEditPartEntries) {
			EditPart ep = epEntry.getValue();

			if(ep instanceof CombinedFragmentEditPart) {
				Rectangle cfRect = ApexSequenceUtil.apexGetAbsoluteRectangle((CombinedFragmentEditPart)ep);

				if(lifelineRect.right() >= cfRect.x && lifelineRect.x <= cfRect.right()) {
					positionallyLifelineCoveringCFEditParts.add((CombinedFragmentEditPart)ep);
				}
			}
		}
		return positionallyLifelineCoveringCFEditParts;
	}

	/**
	 * �숈씪��遺�え InteractionFragment瑜�媛뽯뒗 Sibling EditPart�ㅼ쓣 援ы븿
	 * @param gep current edit part
	 * @return
	 */
	public static List<IGraphicalEditPart> apexGetSiblingEditParts(IGraphicalEditPart gep) {
		InteractionFragment fragment = null;
		if (gep instanceof ConnectionNodeEditPart) {
			ConnectionNodeEditPart connection = (ConnectionNodeEditPart)gep;
			Message message = (Message)ViewUtil.resolveSemanticElement(connection.getNotationView());
			MessageEnd send = message.getSendEvent();
			if (send instanceof OccurrenceSpecification) {
				fragment = (OccurrenceSpecification)send;
			}
//			Point edge = SequenceUtil.getAbsoluteEdgeExtremity(connection, true);
//			fragment = SequenceUtil.findInteractionFragmentContainerAt(edge, gep);

		}
		else {
			EObject element = ViewUtil.resolveSemanticElement((View)gep.getModel());
			if (element instanceof InteractionFragment) {
				fragment = (InteractionFragment)element;
			}
//			Rectangle bounds = SequenceUtil.getAbsoluteBounds(gep);
//			fragment = SequenceUtil.findInteractionFragmentContainerAt(bounds, gep);
		}
		
		InteractionFragment parent = null;
		if (fragment != null) {
			parent = fragment.getEnclosingOperand();
			if (parent == null) {
				parent = fragment.getEnclosingInteraction();
			}
		}
		
		return apexGetSiblingEditParts(parent, gep.getViewer());
	}

	/**
	 * �숈씪��遺�え InteractionFragment瑜�媛뽯뒗 Sibling EditPart��以��꾨옒��EditPart��援ы븿
	 * @param gep
	 * @return
	 */
	public static List<IGraphicalEditPart> apexGetNextSiblingEditParts(IGraphicalEditPart gep) {
		List<IGraphicalEditPart> removeList = new ArrayList<IGraphicalEditPart>();
		List<IGraphicalEditPart> result = apexGetSiblingEditParts(gep);
		int y = ApexSequenceUtil.apexGetAbsolutePosition(gep, SWT.BOTTOM);
		for (IGraphicalEditPart part : result) {
			int top = ApexSequenceUtil.apexGetAbsolutePosition(part, SWT.TOP);
			if (y > top) {
				removeList.add(part);
			}
		}
		
		result.removeAll(removeList);
		
		Collections.sort(result, new Comparator<IGraphicalEditPart>() {
			public int compare(IGraphicalEditPart o1, IGraphicalEditPart o2) {
				Rectangle r1 = apexGetAbsoluteRectangle(o1);
				Rectangle r2 = apexGetAbsoluteRectangle(o2);
				
				if (r1.y - r2.y == 0)
					return r1.x - r2.x;
				return r1.y - r2.y;
			}
		});
		
		return result;
	}
	
	/**
	 * �숈씪��遺�え InteractionFragment瑜�媛뽯뒗 Sibling EditPart��以��꾩쓽 EditPart��援ы븿
	 * @param gep
	 * @return
	 */
	public static List<IGraphicalEditPart> apexGetPrevSiblingEditParts(IGraphicalEditPart gep) {
		List<IGraphicalEditPart> removeList = new ArrayList<IGraphicalEditPart>();
		List<IGraphicalEditPart> result = apexGetSiblingEditParts(gep);
		int y = ApexSequenceUtil.apexGetAbsolutePosition(gep, SWT.TOP);
		for (IGraphicalEditPart part : result) {
			int bottom = ApexSequenceUtil.apexGetAbsolutePosition(part, SWT.BOTTOM);
			if (y < bottom) {
				removeList.add(part);
			}
		}
		
		result.removeAll(removeList);
		
		Collections.sort(result, new Comparator<IGraphicalEditPart>() {
			public int compare(IGraphicalEditPart o1, IGraphicalEditPart o2) {
				Rectangle r1 = apexGetAbsoluteRectangle(o1);
				Rectangle r2 = apexGetAbsoluteRectangle(o2);
				
				if (r1.bottom() - r2.bottom() == 0)
					return r1.right() - r2.right();
				return r1.bottom() - r2.bottom();
			}
		});
		
		return result;
	}
	
	/**
	 * interactionFragment���섏쐞 fragment�ㅼ쓽 EditPart��援ы븿
	 * @param interactionFragment
	 * @param viewer
	 * @return
	 */
	public static List<IGraphicalEditPart> apexGetSiblingEditParts(InteractionFragment parent, EditPartViewer viewer) {
		List<InteractionFragment> fragmentList = new ArrayList<InteractionFragment>();
		
		if (parent instanceof Interaction) {
			fragmentList.addAll( ((Interaction) parent).getFragments() );
		}
		else if (parent instanceof InteractionOperand) {
			fragmentList.addAll( ((InteractionOperand) parent).getFragments() );
		}
		else if (parent instanceof CombinedFragment) {
			fragmentList.addAll( ((CombinedFragment) parent).getOperands() );
		}
		
		List<IGraphicalEditPart> result = new ArrayList<IGraphicalEditPart>(fragmentList.size());
		for (InteractionFragment itf : fragmentList) {
			EObject parseElement = itf;
			if (itf instanceof MessageOccurrenceSpecification) {
				parseElement = ((MessageOccurrenceSpecification)itf).getMessage();
			}
			else if (itf instanceof ExecutionOccurrenceSpecification) {
				parseElement = ((ExecutionOccurrenceSpecification)itf).getExecution();
			}
			
			List<View> views = DiagramEditPartsUtil.findViews(parseElement, viewer);
			for (View view : views) {
				EditPart part = (EditPart)viewer.getEditPartRegistry().get(view);
				boolean isCombinedFragment = part instanceof CombinedFragmentEditPart || part instanceof CombinedFragment2EditPart;
				boolean isContinuation = part instanceof ContinuationEditPart;
				boolean isInteractionOperand = part instanceof InteractionOperandEditPart;
				boolean isInteractionUse = part instanceof InteractionUseEditPart;
				boolean isInteraction = part instanceof InteractionEditPart;
				boolean isMessage = part instanceof ConnectionNodeEditPart;
				boolean isActivation = part instanceof AbstractExecutionSpecificationEditPart;
				boolean isSameEditPart = parent.equals(view.getElement());
				if(isCombinedFragment || isContinuation || isInteractionOperand || isInteractionUse || isInteraction || isMessage || isActivation) {
					if (!result.contains(part) && !isSameEditPart) {
						result.add((IGraphicalEditPart) part);
					}
				}
			}
		}
		
		return result;
	}
	
	/**
	 * y媛믪쓣 �ы븿�섎뒗 EditPart 由ъ뒪�몃� 援ы븿
	 * @param yLocation
	 * @param interactionFragment
	 * @param viewer
	 * @return
	 */
	public static List<IGraphicalEditPart> apexGetEditPartsContainerAt(int yLocation, InteractionFragment interactionFragment, EditPartViewer viewer) {
		List<IGraphicalEditPart> result = new ArrayList<IGraphicalEditPart>();
		
		List<IGraphicalEditPart> siblingEditParts = apexGetSiblingEditParts(interactionFragment, viewer);
		for (IGraphicalEditPart editPart : siblingEditParts) {
			if (yLocation > apexGetAbsolutePosition(editPart, SWT.TOP) &&
					yLocation < apexGetAbsolutePosition(editPart, SWT.BOTTOM)) {
				result.add(editPart);
			}
		}
		return result;
	}
	
	public static String apexGetSimpleClassName(Object object) {
		if ( object != null ) {
			String fullName = object.getClass().toString();
			return fullName.substring(fullName.lastIndexOf('.')+1);	
		} else {
			return null;
		}		
	}
	
	/**
	 * compoundCommand瑜�遺꾪빐�댁꽌 compositeCommand��add�댁＜��硫붿꽌��
	 * 
	 * @param inputCommand
	 * @param compositeCommand
	 */
	public static void apexCompoundCommandToCompositeCommand(Command inputCommand, CompositeCommand compositeCommand) {
		if ( inputCommand instanceof CompoundCommand ) {
			CompoundCommand compoundCommand = (CompoundCommand)inputCommand;
			List cmdList = compoundCommand.getCommands();
			Iterator itCmd = cmdList.iterator();
			while ( itCmd.hasNext() ) {
				Command aCommand = (Command)itCmd.next();
				
				if ( aCommand instanceof CompoundCommand ) {
					apexCompoundCommandToCompositeCommand(aCommand, compositeCommand);					
				} else {
					if ( aCommand != null && !aCommand.canExecute()) {
						compositeCommand.add(UnexecutableCommand.INSTANCE);
					} else if ( aCommand != null ) {
						if ( aCommand instanceof ICommandProxy ) {
							ICommandProxy iCommandProxy = (ICommandProxy)aCommand;
							ICommand iCommand = iCommandProxy.getICommand();
							compositeCommand.add(iCommand);
						}									
					}	
				}
				
			}
		} else if ( inputCommand instanceof org.eclipse.gef.commands.UnexecutableCommand) {
			compositeCommand.add(UnexecutableCommand.INSTANCE);
		} else if ( inputCommand instanceof ICommandProxy ) {
			ICommandProxy iCommandProxy = (ICommandProxy)inputCommand;
			ICommand iCommand = iCommandProxy.getICommand();
			compositeCommand.add(iCommand);
		}
	}
	
	/**
	 * compoundCommand瑜�遺꾪빐�섏뿬 �ㅻⅨ compoundCommand��add�섎뒗 硫붿꽌��
	 * null �대굹 Unexecutable��愿�퀎�놁씠 �덈뒗��줈 add��
	 * 
	 * @param inputCommand
	 * @param resultCompoundCommand
	 */
	public static void apexCompoundCommandToCompoundCommand(Command inputCommand, CompoundCommand resultCompoundCommand) {
		if ( inputCommand instanceof CompoundCommand ) {
			CompoundCommand inputCompoundCommand = (CompoundCommand)inputCommand;			
			List cmdList = inputCompoundCommand.getCommands();
			Iterator itCmd = cmdList.iterator();
			while ( itCmd.hasNext() ) {
				Command aCommand = (Command)itCmd.next();
				
				if ( aCommand instanceof CompoundCommand ) {
					apexCompoundCommandToCompoundCommand(aCommand, resultCompoundCommand);
				} else {
					resultCompoundCommand.add(aCommand);	
				}
			}	
		} else {
			resultCompoundCommand.add(inputCommand);
		}
		
	}
		
	/**
	 * IOEP媛�cover�섎뒗 Lifeline��Activation 諛�Connection 以�IOEP��寃쎄퀎媛��ы븿�섎뒗 EditPartList 諛섑솚
	 * 
	 * @param ioep
	 * @return
	 */
	public static List<EditPart> apexGetIOEPContainedEditParts(InteractionOperandEditPart ioep) {
		return apexGetIOEPContainedEditParts(ioep, false);
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
	public static List<EditPart> apexGetIOEPContainedEditParts(InteractionOperandEditPart ioep, boolean coveredOnly) {
		
		List<EditPart> containedEditParts = new ArrayList<EditPart>();
		
		List<LifelineEditPart> coveredLifelineEditParts = apexGetCoveredLifelineEditParts(ioep, coveredOnly);
		
		Rectangle ioepRect = apexGetAbsoluteRectangle(ioep);
		
		// LifelineEditPart��children editpart 以�IOEP���ы븿�섏뼱���섎뒗 寃�泥섎━
		for ( LifelineEditPart lep : coveredLifelineEditParts ) {
			List<EditPart> lepChildren = lep.getChildren();
			
			for ( EditPart ep : lepChildren ) {
				if ( ep instanceof AbstractExecutionSpecificationEditPart ) {
					AbstractExecutionSpecificationEditPart aesep = (AbstractExecutionSpecificationEditPart)ep;
					Rectangle activationRect = apexGetAbsoluteRectangle(aesep);
					
					if ( ioepRect.contains(activationRect) ) {
						containedEditParts.add(aesep);
					}	
				}				
			}
			
			List<EditPart> sourceConnections = lep.getSourceConnections();
			
			for ( EditPart ep : sourceConnections ) {
				if ( ep instanceof ConnectionEditPart ) {
					ConnectionEditPart cep = (ConnectionEditPart)ep;
					Rectangle cepRect = apexGetAbsoluteRectangle(cep);
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
	 * CombinedFragmentEditPart ��InteractionOperandEditPart��coveredLifelineEditParts 諛섑솚
	 * 
	 * @param snep
	 * @return
	 */
	public static List<LifelineEditPart> apexGetCoveredLifelineEditParts(ShapeNodeEditPart snep, boolean coveredOnly) {
	
		List<LifelineEditPart> coveredLifelineEditParts = new ArrayList<LifelineEditPart>();
		
		List<Lifeline> coveredLifelines = null;
				
		if ( snep instanceof CombinedFragmentEditPart ) {
			CombinedFragment cf = (CombinedFragment)snep.resolveSemanticElement();
			if ( coveredOnly ) {
				coveredLifelines = cf.getCovereds();	
			} else {
				coveredLifelines = new ArrayList<Lifeline>();				
				List<LifelineEditPart> positionallyCoveredLifelineEditparts = apexGetPositionallyCoveredLifelineEditParts(apexGetAbsoluteRectangle(snep), snep);
			
				for ( LifelineEditPart lep : positionallyCoveredLifelineEditparts ) {
					coveredLifelines.add((Lifeline)lep.resolveSemanticElement());
				}
			}
			
		} else if ( snep instanceof InteractionOperandEditPart ) {
			InteractionOperand io = (InteractionOperand)snep.resolveSemanticElement();
			if ( coveredOnly ) {
				coveredLifelines = io.getCovereds();	
			} else {
				coveredLifelines = new ArrayList<Lifeline>();				
				List<LifelineEditPart> positionallyCoveredLifelineEditparts = apexGetPositionallyCoveredLifelineEditParts(apexGetAbsoluteRectangle(snep), snep);
			
				for ( LifelineEditPart lep : positionallyCoveredLifelineEditparts ) {
					coveredLifelines.add((Lifeline)lep.resolveSemanticElement());
				}
			}
				
		} else {
			return null;
		}
		
		for ( Lifeline ll : coveredLifelines ) {
			coveredLifelineEditParts.add((LifelineEditPart)apexGetEditPart(ll, snep.getViewer()));
		}
		/*
		RootEditPart rootEP = snep.getRoot();
		EditPart contents = rootEP.getContents();
		System.out
				.println("ApexSequenceUtil.apexGetCoveredLifelineEditParts(), line : "
						+ Thread.currentThread().getStackTrace()[1]
								.getLineNumber());
		System.out.println("rootEP.getChildren() : " + rootEP.getChildren());
		
		if ( contents instanceof PackageEditPart ) {
			PackageEditPart pep = (PackageEditPart)contents;
			List interactionEditParts = pep.getChildren();
			
			if ( interactionEditParts.size() > 0 ) {
			
				if ( interactionEditParts.get(0) instanceof InteractionEditPart ) {				
					InteractionEditPart iep = (InteractionEditPart)interactionEditParts.get(0);
					List<EditPart> iepChildren = iep.getChildren();
					
					for ( EditPart ep : iepChildren ) {					
						if ( ep instanceof InteractionInteractionCompartmentEditPart ) {
							InteractionInteractionCompartmentEditPart iicep = (InteractionInteractionCompartmentEditPart)ep;
							List<EditPart> iicepChildren = iicep.getChildren();
						
							for ( EditPart ep1 : iicepChildren ) {
								if ( ep1 instanceof LifelineEditPart ) {
									LifelineEditPart llep = (LifelineEditPart)ep1;									
									Lifeline lifeline = (Lifeline)llep.resolveSemanticElement();
									
									
									if ( coveredLifelines.contains(lifeline) ) {
										coveredLifelineEditParts.add(llep);										
									}
								}
							}
						}
					}
				}
			}
		}
		*/
		return coveredLifelineEditParts;
	}
	
	/**
	 * 
	 * 
	 * @param lifelineEditPart
	 * @return
	 */
	public static List<CombinedFragmentEditPart> apexGetCoveringCombinedFragmentEditParts(LifelineEditPart lifelineEditPart) {
	
		List<CombinedFragmentEditPart> coveringCombinedFragmentEditParts = new ArrayList<CombinedFragmentEditPart>();
		
//		List<CombinedFragment> coveringCombinedFragments = new ArrayList<CombinedFragment>();
		
		Lifeline lifeline = (Lifeline)lifelineEditPart.resolveSemanticElement();
		
		List<InteractionFragment> coveringInteractionFragments = lifeline.getCoveredBys();
		
		//System.out.println("lifelineEP     : " + lifelineEditPart);
		for ( InteractionFragment aInteractionFragment : coveringInteractionFragments ) {
			if ( aInteractionFragment instanceof CombinedFragment ) {
//				coveringCombinedFragments.add((CombinedFragment)aInteractionFragment);
				CombinedFragmentEditPart cfEP = (CombinedFragmentEditPart)getEditPart(aInteractionFragment, lifelineEditPart);
				coveringCombinedFragmentEditParts.add(cfEP);
				
				//System.out.println("  coveringCFEP : " + cfEP);
			}
		}
		return coveringCombinedFragmentEditParts;
	}
	
	/**
	 * InteractionOperand��children 怨�
	 * InteractionOperand��cover�섎뒗 Lifeline��Activation 以�InteractionOperand���꾩튂�곸쑝濡��ы븿��Activation �④퍡 由ы꽩 
	 * 
	 * @param ioep
	 * @return
	 */
	public static List apexGetInteractionOperandChildrenEditParts(InteractionOperandEditPart ioep) {
		
		List ioChildren = apexGetIOEPContainedEditParts(ioep);
		ioChildren.addAll(ioep.getChildren());
				
		return ioChildren;
	}
	
	/**
	 * CFEP��紐⑤뱺 理쒖쥌 childrenEditParts 諛섑솚
	 * CombinedFragmentCombinedFragmentCompartmentEditPart 怨�InteractionOperand���쒖쇅��
	 * 以묒꺽��CF��IOEP �댁쓽 紐⑤뱺 Activation 諛섑솚
	 * 
	 * @param cfep
	 * @return
	 */
	public static List apexGetCombinedFragmentChildrenEditParts(CombinedFragmentEditPart cfep) {
		
		List cfChildren = new ArrayList();
		
		List<CombinedFragmentCombinedFragmentCompartmentEditPart> childCompartments = cfep.getChildren();
		
		for ( CombinedFragmentCombinedFragmentCompartmentEditPart compartEP : childCompartments ) {
			List<InteractionOperandEditPart> ioeps = compartEP.getChildren();
			
			for ( InteractionOperandEditPart ioep : ioeps ) {
				cfChildren.addAll(apexGetInteractionOperandChildrenEditParts(ioep));
			}			
		}		
		
		return cfChildren;
	}
	
	public static EditPart apexGetEditPart(EObject eObject, EditPartViewer viewer) {
		Collection<Setting> settings = CacheAdapter.getInstance().getNonNavigableInverseReferences(eObject);
		for (Setting ref : settings) {
			if(NotationPackage.eINSTANCE.getView_Element().equals(ref.getEStructuralFeature())) {
				View view = (View)ref.getEObject();
				EditPart part = (EditPart)viewer.getEditPartRegistry().get(view);
				return part;
			}
		}
		return null;
	}
	
	public static InteractionOperandEditPart apexGetEnclosingInteractionOperandEditpart(IGraphicalEditPart graphicalEditPart) {
		InteractionOperandEditPart ioep = null;
		
		Rectangle absRect = ApexSequenceUtil.apexGetAbsoluteRectangle(graphicalEditPart);
		
		InteractionFragment iftContainer = SequenceUtil.findInteractionFragmentContainerAt(absRect.getLocation(), graphicalEditPart);
		
		EditPart editPart = ApexSequenceUtil.getEditPart(iftContainer, graphicalEditPart);
		if ( editPart instanceof InteractionOperandEditPart ) {
			ioep = (InteractionOperandEditPart)editPart;
		}
		return ioep;
	}
	
	/**
	 * eObject���대떦�섎뒗 EditPart 諛섑솚
	 * 
	 * @param eObject
	 * @param editPart EditPartViewer瑜��산린�꾪븳 any EditPart
	 * @return
	 */
	/* viewer瑜��ъ슜�섎뒗 getEditPart濡��듭씪(2012.09.26)
	public static EditPart getEditPart(EObject eObject, EditPart editPart) {
		Collection<Setting> settings = CacheAdapter.getInstance().getNonNavigableInverseReferences(eObject);
		for (Setting ref : settings) {
			if(NotationPackage.eINSTANCE.getView_Element().equals(ref.getEStructuralFeature())) {
				View view = (View)ref.getEObject();
				EditPartViewer epViewer = editPart.getViewer();
//				EditPartFactory epFactory = epViewer.getEditPartFactory();
//				EditPartService epService = (EditPartService)epFactory;
//				IGraphicalEditPart igep = epService.createGraphicEditPart(view);
				EObject eObj = view.getElement();
				EditPart part = (EditPart)epViewer.getEditPartRegistry().get(view);
				
				// Interaction��寃쎌슦 �꾨옒 泥섎━瑜��댁＜吏��딆쑝硫�PackageEditPart媛�諛섑솚��
				if ( eObj instanceof Interaction ) {
//					RootEditPart rootEP = igep.getRoot();
					RootEditPart rootEP = editPart.getRoot();		
					PackageEditPart packageEP = (PackageEditPart)rootEP.getContents();
					List<InteractionEditPart> interactionEditParts = packageEP.getChildren();
					part = interactionEditParts.get(0);
				}
				return part;
			}
		}
		return null;
	}
	*/
	
	/**
	 * eObject���대떦�섎뒗 EditPart 諛섑솚
	 * 
	 * @param eObject
	 * @param editPart EditPartViewer瑜��산린�꾪븳 any EditPart
	 * @return
	 */
	public static EditPart getEditPart(EObject eObject, EditPart editPart) {
		return getEditPart(eObject, null, editPart.getViewer());	
	}
	
	public static EditPart getEditPart(EObject eObject, EditPartViewer viewer) {
		return getEditPart(eObject, null, viewer);
	}
	
	public static EditPart getEditPart(EObject eObject, Class targetClass, EditPartViewer viewer) {
		if (eObject == null || viewer == null)
			return null;
		
		Set<EditPart> parts = new HashSet<EditPart>();
		
		Map<?, ?> map = viewer.getEditPartRegistry();
		for (Entry<?, ?> entry : map.entrySet()) {
			Object key = entry.getKey();
			if (!(key instanceof View)) {
				continue;
			}
			View view = (View) key;
			EObject tempElement = view.getElement();
			if (eObject.equals(tempElement)) {
				Object value = entry.getValue();
				if (targetClass == null || targetClass.isInstance(value)) {
					parts.add((EditPart) value);
				}
			}
		}
		
		if (parts.size() == 1) {
			return parts.iterator().next();
		}
		
		Set<EditPart> removeParts = new HashSet<EditPart>();
		for (EditPart part : parts) {
			EditPart parent = part.getParent();
			while (parent != null) {
				if (parts.contains(parent)) {
					removeParts.add(part);
					break;
				}
				parent = parent.getParent();
			}
		}
		
		parts.removeAll(removeParts);
		return parts.size() == 0 ? null : parts.iterator().next();
		
//		Collection<Setting> settings = CacheAdapter.getInstance().getNonNavigableInverseReferences(eObject);
//		List<EditPart> parts = new ArrayList<EditPart>();
//		for (Setting ref : settings) {
//			if(NotationPackage.eINSTANCE.getView_Element().equals(ref.getEStructuralFeature())) {
//				View view = (View)ref.getEObject();
//				EditPart part = (EditPart)viewer.getEditPartRegistry().get(view);
//				parts.add(part);
//			}
//		}
//
//		if (parts.size() == 1) {
//			return parts.get(0);
//		}
//
//		List<EditPart> removeParts = new ArrayList<EditPart>();
//		for (EditPart part : parts) {
//			EditPart parent = part.getParent();
//			while (parent != null) {
//				if (parts.contains(parent)) {
//					removeParts.add(parent);
//				}
//			}
//		}
//		
//		parts.removeAll(removeParts);
//		return parts.size() == 0 ? null : parts.get(0);
	}
	
	public static List<LifelineEditPart> apexGetSortedLifelineEditParts(InteractionInteractionCompartmentEditPart iice) {
		List<LifelineEditPart> sortedLifelineEPs = new ArrayList<LifelineEditPart>();
		
		for (Object ep : iice.getChildren()) {
			if ( ep instanceof LifelineEditPart ) {
				sortedLifelineEPs.add((LifelineEditPart)ep);
			}
		}
		
		Collections.sort(sortedLifelineEPs, new Comparator<IGraphicalEditPart>() {
			public int compare(IGraphicalEditPart o1, IGraphicalEditPart o2) {
				Rectangle r1 = apexGetAbsoluteRectangle(o1);
				Rectangle r2 = apexGetAbsoluteRectangle(o2);
				
				if (r1.x - r2.x == 0)
					return r1.right() - r2.right();
				return r1.x - r2.x;
			}
		});
		
		return sortedLifelineEPs;
	}
	
	public static List<LifelineEditPart> apexGetNextLifelineEditParts(LifelineEditPart lep) {
		
		int leftOfLep = apexGetAbsolutePosition(lep, SWT.LEFT);				
		
		List<LifelineEditPart> sortedLifelineEPs = apexGetSortedLifelineEditParts((InteractionInteractionCompartmentEditPart)lep.getParent());
		List<LifelineEditPart> removeList = new ArrayList<LifelineEditPart>();
		
		for ( LifelineEditPart aLep : sortedLifelineEPs) {
			int leftOfLifeline = apexGetAbsolutePosition(aLep, SWT.LEFT);
			
			if ( leftOfLifeline <= leftOfLep ) {
				removeList.add(aLep);
			}
		}
		
		sortedLifelineEPs.removeAll(removeList);		
				
		return sortedLifelineEPs;
	}
	
	public static List<CombinedFragmentEditPart> apexGetAllCombinedFragmentEditParts(EditPart editPart) {
		List<CombinedFragmentEditPart> allCFs = new ArrayList<CombinedFragmentEditPart>();		

		Set<Entry<Object, EditPart>> allEditPartEntries = editPart.getViewer().getEditPartRegistry().entrySet();	
				
		for(Entry<Object, EditPart> epEntry : allEditPartEntries) {
			EditPart ep = epEntry.getValue();
			if( ep instanceof CombinedFragmentEditPart ) {
				CombinedFragmentEditPart combinedFragmentEditPart = (CombinedFragmentEditPart)ep;
				allCFs.add(combinedFragmentEditPart);
			}
		}
		
		return allCFs;
	}	
	
	public static List apexGetSortedGraphicalEditPartList(List list, 
			                                              final int position) {
		Collections.sort(list, new Comparator<IGraphicalEditPart>() {
			public int compare(IGraphicalEditPart o1, IGraphicalEditPart o2) {
				Rectangle r1 = apexGetAbsoluteRectangle(o1);
				Rectangle r2 = apexGetAbsoluteRectangle(o2);
				
				int result = 0;
				
				switch (position) {
				    case SWT.LEFT:
				    	if (r1.x - r2.x == 0)
							result = r1.right() - r2.right();
						result = r1.x - r2.x;
						break;
				    case SWT.RIGHT:
				    	if (r1.right() - r2.right() == 0)
				    		result = r1.x - r2.x;
				    	result = r1.right() - r2.right();
				    	break;
				    case SWT.TOP:
				    	if (r1.y - r2.y == 0)
				    		result = r1.bottom() - r2.bottom();
				    	result = r1.y - r2.y;
				    	break;
				    case SWT.BOTTOM:
				    	if (r1.bottom() - r2.bottom() == 0)
				    		result = r1.y - r2.y;
				    	result = r1.bottom() - r2.bottom();
				    	break;
				}
				return result;
			}
		});
		
		return list;
	}
	
	public static List<CombinedFragmentEditPart> apexGetDeepestCombinedFragmentEditParts(List<CombinedFragmentEditPart> combinedFragmentEditParts) {
		
		
		
		return null;
	}
	
	public static Integer[] apexGetReorderingLocations(IGraphicalEditPart editPart, Point location) {
		List<Integer> locations = new ArrayList<Integer>();
		
		Set<LifelineEditPart> mustCoveredEditParts = new HashSet<LifelineEditPart>();
		Set<IGraphicalEditPart> ignoreEditParts = new HashSet<IGraphicalEditPart>();
		if (editPart instanceof ConnectionNodeEditPart) {
			ConnectionNodeEditPart connectionNodeEditPart = (ConnectionNodeEditPart)editPart;
			
			List<IGraphicalEditPart> linkedEditParts = apexGetLinkedEditPartList(connectionNodeEditPart, true, true, false);
			ignoreEditParts.addAll(linkedEditParts);
			
			for (IGraphicalEditPart linkedEditPart : linkedEditParts) {
				if (linkedEditPart instanceof ConnectionNodeEditPart) {
					EditPart sourceEditPart = ((ConnectionNodeEditPart) linkedEditPart).getSource();
					EditPart targetEditPart = ((ConnectionNodeEditPart) linkedEditPart).getTarget();
					LifelineEditPart sourceLifelineEditPart = SequenceUtil.getParentLifelinePart(sourceEditPart);
					LifelineEditPart targetLifelineEditPart = SequenceUtil.getParentLifelinePart(targetEditPart);
					mustCoveredEditParts.add(sourceLifelineEditPart);
					mustCoveredEditParts.add(targetLifelineEditPart);
				} else {
					LifelineEditPart parentLifelineEditPart = SequenceUtil.getParentLifelinePart(linkedEditPart);
					mustCoveredEditParts.add(parentLifelineEditPart);
				}
			}
		} else if (editPart instanceof CombinedFragmentEditPart) {
			CombinedFragmentEditPart combinedFragmentEditPart = (CombinedFragmentEditPart)editPart;
			mustCoveredEditParts.addAll(apexGetCoveredLifelineEditParts(combinedFragmentEditPart, true));
			ignoreEditParts.add(editPart);
		}
		
		List<IGraphicalEditPart> preSiblingEditParts = ApexSequenceUtil.apexGetPrevSiblingEditParts(editPart);
		if (preSiblingEditParts.size() > 0) {
			ignoreEditParts.add(preSiblingEditParts.get(preSiblingEditParts.size() - 1));
		}
		
		List<InteractionFragment> fragments = new ArrayList<InteractionFragment>();
		InteractionFragment interactionFragment = SequenceUtil.findInteractionFragmentContainerAt(location, editPart);
		if (interactionFragment instanceof Interaction) {
			fragments.addAll(((Interaction) interactionFragment).getFragments());
		} else if (interactionFragment instanceof InteractionOperand) {
			EditPart interactionOperandEditPart = getEditPart(interactionFragment, editPart);
			if (interactionOperandEditPart instanceof ShapeNodeEditPart) {
				List<LifelineEditPart> coveredLifelineEditParts = apexGetCoveredLifelineEditParts((ShapeNodeEditPart) interactionOperandEditPart, true);
				if (coveredLifelineEditParts.containsAll(mustCoveredEditParts)) {
					fragments.addAll(((InteractionOperand) interactionFragment).getFragments());
				}
			}
		}
		
		for (InteractionFragment fragment : fragments) {
			if (fragment instanceof MessageOccurrenceSpecification) {
				Message message = ((MessageOccurrenceSpecification)fragment).getMessage();
				ConnectionNodeEditPart connectionNodeEditPart = (ConnectionNodeEditPart)getEditPart(message, editPart);
				if (!ignoreEditParts.contains(connectionNodeEditPart)) {
					Point point = SequenceUtil.getAbsoluteEdgeExtremity(connectionNodeEditPart, false);
					if (point != null && !locations.contains(point.y)) {
						locations.add(point.y);
					}
				}
			} else if (fragment instanceof ExecutionSpecification
					|| fragment instanceof CombinedFragment) {
				IGraphicalEditPart graphicalEditPart = (IGraphicalEditPart)getEditPart(fragment, editPart);
				if (!ignoreEditParts.contains(graphicalEditPart)) {
					Rectangle bounds = SequenceUtil.getAbsoluteBounds(graphicalEditPart);
					if (bounds != null && !locations.contains(bounds.bottom())) {
						locations.add(bounds.bottom());
					}
				}
			}
		}
		
		Collection allEditParts = editPart.getViewer().getEditPartRegistry().values();
		for (Iterator iter = allEditParts.iterator(); iter.hasNext(); ) {
			Object subEditPart = iter.next();
			if (subEditPart instanceof LifelineEditPart) {
				LifelineEditPart lifelineEditPart = (LifelineEditPart)subEditPart;
				IFigure figure = lifelineEditPart.getPrimaryShape().getFigureLifelineDotLineFigure();
				Rectangle bounds = figure.getBounds().getCopy();
				figure.translateToAbsolute(bounds);
				locations.add(bounds.y);
				break;
			}
		}
		
		return locations.toArray(new Integer[locations.size()]);
	}
	
	/**
	 * 이동 가능한 가장 상위의 y값
	 * 
	 * @param connectionPart
	 * @param isFlexible
	 * @return
	 */
	public static int getMovableTop(IGraphicalEditPart editPart, boolean isFlexible, int margin) {
		int minTop = Integer.MIN_VALUE, maxTop = Integer.MIN_VALUE;
		
		List<IGraphicalEditPart> siblingParts = apexGetPrevSiblingEditParts(editPart);
		List<IGraphicalEditPart> frontLinkedParts = apexGetLinkedEditPartList(editPart, true, false, true);

		IGraphicalEditPart realPrevPart = null;
		
		for (IGraphicalEditPart siblingPart : siblingParts) {
			minTop = Math.max(minTop, apexGetAbsolutePosition(siblingPart, SWT.BOTTOM) + margin);
			maxTop = Math.max(maxTop, minTop);
			
			if (siblingPart instanceof ConnectionNodeEditPart && !frontLinkedParts.contains(siblingPart)) {
				// activation중 가장 하위 검색. realMinY는 activation 포함 가장 하위 y값
				ConnectionNodeEditPart prevConnPart = (ConnectionNodeEditPart)siblingPart;
				EditPart prevSourcePart = prevConnPart.getSource();
				EditPart prevTargetPart = prevConnPart.getTarget();
				if (prevSourcePart instanceof AbstractExecutionSpecificationEditPart) {
					int ty = ApexSequenceUtil.apexGetAbsolutePosition((IGraphicalEditPart)prevTargetPart, SWT.BOTTOM) + margin;
					if (maxTop < ty) {
						maxTop = ty;
						realPrevPart = (IGraphicalEditPart)prevTargetPart;
					}
				}
				if (prevTargetPart instanceof AbstractExecutionSpecificationEditPart) {
					int ty = ApexSequenceUtil.apexGetAbsolutePosition((IGraphicalEditPart)prevTargetPart, SWT.BOTTOM) + margin;
					if (maxTop < ty) {
						maxTop = ty;
						realPrevPart = (IGraphicalEditPart)prevTargetPart;
					}
				}
			}
		}
		
		if (siblingParts.size() == 0) {
			EditPart sourcePart = null;
			if (editPart instanceof ConnectionNodeEditPart) {
				sourcePart = ((ConnectionNodeEditPart)editPart).getSource();
			} else {
				EObject eObject = editPart.resolveSemanticElement();
				if (eObject instanceof InteractionFragment) {
					Interaction interaction = ((InteractionFragment)eObject).getEnclosingInteraction();
					if (interaction != null && interaction.getCovereds().size() > 0) {
						sourcePart = getEditPart(interaction.getCovereds().get(0), editPart);
					}
				}
			}
			LifelineEditPart srcLifelinePart = SequenceUtil.getParentLifelinePart(sourcePart);
			IFigure dotLine = srcLifelinePart.getPrimaryShape().getFigureLifelineDotLineFigure();
			Rectangle dotLineBounds = dotLine.getBounds().getCopy();
			dotLine.translateToAbsolute(dotLineBounds);
			minTop = dotLineBounds.y() + margin;
			maxTop = minTop;
		}
		
		if (isFlexible && realPrevPart instanceof AbstractExecutionSpecificationEditPart) {
			Dimension minSize = realPrevPart.getFigure().getMinimumSize();
			int bottom = ApexSequenceUtil.apexGetAbsolutePosition(realPrevPart, SWT.TOP) + minSize.height();
			minTop = Math.max(minTop, bottom);
		}
		else {
			minTop = maxTop;
		}
		return minTop;
	}
	
	/**
	 * 이동 가능한 가장 하위의 y값
	 * @param connectionPart
	 * @param margin
	 * @return
	 */
	public static int getMovableBottom(ConnectionNodeEditPart connectionPart, int margin) {
		int bottom = Integer.MAX_VALUE;
		
		List<IGraphicalEditPart> siblingParts = ApexSequenceUtil.apexGetNextSiblingEditParts(connectionPart);
		List<IGraphicalEditPart> linkedParts = ApexSequenceUtil.apexGetLinkedEditPartList(connectionPart, true, true, false);
		
		Rectangle linkedBounds = ApexSequenceUtil.apexGetAbsoluteRectangle(connectionPart);
		for (IGraphicalEditPart linkedPart : linkedParts) {
			linkedBounds.union(ApexSequenceUtil.apexGetAbsoluteRectangle(linkedPart));
		}
		for (IGraphicalEditPart siblingPart : siblingParts) {
			if (!linkedParts.contains(siblingPart)) {
				bottom = Math.min(bottom, ApexSequenceUtil.apexGetAbsolutePosition(siblingPart, SWT.TOP) - margin);
			}
		}
		
		return bottom - linkedBounds.height;
	}
}
