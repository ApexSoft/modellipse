package org.eclipse.papyrus.uml.diagram.sequence.apex.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Rectangle;

/**
 * Lifeline의 show/hide 구간 저장
 * @author Jiho
 *
 */
public class LifelineFigureHelper {

	static Map<IFigure, Collection<Rectangle>> hideRegions = new HashMap<IFigure, Collection<Rectangle>>();
	
	public static void showRegion(IFigure figure, Rectangle region) {
		showRegion(figure, region, true);
	}
	
	public static void showRegion(IFigure figure, Rectangle region, boolean isShow) {
		if (figure == null || region == null)
			return;
		
		Collection<Rectangle> regions = hideRegions.get(figure);
		if (regions == null) {
			hideRegions.put(figure, regions = new ArrayList<Rectangle>());
		}
		
		if (!isShow) {
			if (!regions.contains(region)) {
				regions.add(region);
			}
		} else {
			regions.remove(region);
		}
	}
	
	public static Collection<Rectangle> getHideRegions(IFigure figure) {
		return hideRegions.get(figure);
	}
	
	public static void showAllRegion(Rectangle region) {
		Iterator<IFigure> iter = hideRegions.keySet().iterator();
		while (iter.hasNext()) {
			Collection<Rectangle> regions = hideRegions.get(iter.next());
			if (regions.contains(region)) {
				regions.remove(region);
			}
		}
	}
	
	public static void removeAllRegion(IFigure figure) {
		if (hideRegions.containsKey(figure))
			hideRegions.remove(figure);
	}
}
