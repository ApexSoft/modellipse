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
package org.eclipse.papyrus.uml.diagram.activity.helper;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.AbstractPointListShape;
import org.eclipse.draw2d.Polygon;
import org.eclipse.draw2d.PolylineShape;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gmf.runtime.draw2d.ui.mapmode.IMapMode;
import org.eclipse.gmf.runtime.notation.Bounds;
import org.eclipse.gmf.runtime.notation.Shape;

/**
 * The ActivityFigureDrawer helps drawing representations for activity diagram
 * out of a Polyline figure
 */
public class ActivityFigureDrawer {

	/**
	 * The template for drawing a triangle figure
	 */
	private static final List<Point> TRIANGLE_FIGURE = new ArrayList<Point>(3);
	static {
		TRIANGLE_FIGURE.add(new Point(0, 5));
		TRIANGLE_FIGURE.add(new Point(5, 5));
		TRIANGLE_FIGURE.add(new Point(3, 0));
	}

	/**
	 * The template size point for translating a pin arrow figure
	 */
	private static final Point TRIANGLE_FIGURE_SIZE = new Point(5, 5);

	/**
	 * The template for drawing a rake figure
	 */
	private static final List<Point> RAKE_FIGURE = new ArrayList<Point>(8);
	static {
		RAKE_FIGURE.add(new Point(0, 10));
		RAKE_FIGURE.add(new Point(0, 4));
		RAKE_FIGURE.add(new Point(5, 4));
		RAKE_FIGURE.add(new Point(5, 0));
		RAKE_FIGURE.add(new Point(5, 10));
		RAKE_FIGURE.add(new Point(5, 4));
		RAKE_FIGURE.add(new Point(10, 4));
		RAKE_FIGURE.add(new Point(10, 10));
	}

	/**
	 * The template point for translating a rake figure
	 */
	private static final Point RAKE_FIGURE_TRANSLATION = new Point(-16, -12);

	/**
	 * The template for drawing an pin arrow figure turned right
	 */
	private static final List<Point> RIGHT_PIN_ARROW_FIGURE = new ArrayList<Point>(8);
	static {
		RIGHT_PIN_ARROW_FIGURE.add(new Point(4, 2));
		RIGHT_PIN_ARROW_FIGURE.add(new Point(7, 4));
		RIGHT_PIN_ARROW_FIGURE.add(new Point(1, 4));
		RIGHT_PIN_ARROW_FIGURE.add(new Point(7, 4));
		RIGHT_PIN_ARROW_FIGURE.add(new Point(4, 6));
	}

	/**
	 * The template for drawing a pin arrow figure turned left
	 */
	private static final List<Point> LEFT_PIN_ARROW_FIGURE = new ArrayList<Point>(8);
	static {
		LEFT_PIN_ARROW_FIGURE.add(new Point(4, 2));
		LEFT_PIN_ARROW_FIGURE.add(new Point(1, 4));
		LEFT_PIN_ARROW_FIGURE.add(new Point(7, 4));
		LEFT_PIN_ARROW_FIGURE.add(new Point(1, 4));
		LEFT_PIN_ARROW_FIGURE.add(new Point(4, 6));
	}

	/**
	 * The template for drawing a pin arrow figure turned up
	 */
	private static final List<Point> UP_PIN_ARROW_FIGURE = new ArrayList<Point>(8);
	static {
		UP_PIN_ARROW_FIGURE.add(new Point(2, 4));
		UP_PIN_ARROW_FIGURE.add(new Point(4, 1));
		UP_PIN_ARROW_FIGURE.add(new Point(4, 7));
		UP_PIN_ARROW_FIGURE.add(new Point(4, 1));
		UP_PIN_ARROW_FIGURE.add(new Point(6, 4));
	}

	/**
	 * The template for drawing a pin arrow figure turned down
	 */
	private static final List<Point> DOWN_PIN_ARROW_FIGURE = new ArrayList<Point>(8);
	static {
		DOWN_PIN_ARROW_FIGURE.add(new Point(2, 4));
		DOWN_PIN_ARROW_FIGURE.add(new Point(4, 7));
		DOWN_PIN_ARROW_FIGURE.add(new Point(4, 1));
		DOWN_PIN_ARROW_FIGURE.add(new Point(4, 7));
		DOWN_PIN_ARROW_FIGURE.add(new Point(6, 4));
	}

	/**
	 * The template size point for translating a pin arrow figure
	 */
	private static final Point PIN_ARROW_FIGURE_SIZE = new Point(8, 8);

	/**
	 * Get the direction opposite to the parameter one
	 * 
	 * @param direction
	 *        {@link PositionConstants#NORTH}, {@link PositionConstants#EAST}, {@link PositionConstants#SOUTH} or {@link PositionConstants#WEST}
	 * @return the direction at the opposite or {@link PositionConstants#NONE}
	 */
	public static int getOppositeDirection(int direction) {
		switch(direction) {
		case PositionConstants.NORTH:
			return PositionConstants.SOUTH;
		case PositionConstants.EAST:
			return PositionConstants.WEST;
		case PositionConstants.SOUTH:
			return PositionConstants.NORTH;
		case PositionConstants.WEST:
			return PositionConstants.EAST;
		default:
			return PositionConstants.NONE;
		}
	}

	/**
	 * Draw the triangle or refresh it
	 * 
	 * @param triangle
	 *        figure which contains the triangle
	 * @param iMapMode
	 *        the map mode to translate points
	 * @param parentDimension
	 *        the dimension of the parent
	 */
	public static void redrawTriangle(Polygon triangle, IMapMode iMapMode, Dimension parentDimension) {
		triangle.removeAllPoints();
		double xScale = parentDimension.preciseWidth() / TRIANGLE_FIGURE_SIZE.x;
		double yScale = parentDimension.preciseHeight() / TRIANGLE_FIGURE_SIZE.y;
		for(Point refPoint : TRIANGLE_FIGURE) {
			Point translatedPoint = new Point(refPoint).scale(xScale, yScale);
			iMapMode.DPtoLP(translatedPoint);
			triangle.addPoint(translatedPoint);
		}
	}

	/**
	 * Draw the rake or refresh it
	 * 
	 * @param rake
	 *        figure which contains the rake
	 * @param iMapMode
	 *        the map mode to translate points
	 * @param parentDimension
	 *        the dimension of the parent
	 */
	public static void redrawRake(AbstractPointListShape rake, IMapMode iMapMode, Dimension parentDimension) {
		rake.removeAllPoints();
		Point translationPoint = new Point();
		if(!(rake instanceof PolylineShape)) {
			// do not translate for PolylineShape since bounds will be adapted
			new Point(RAKE_FIGURE_TRANSLATION);
			translationPoint.translate(parentDimension);
		}
		for(Point refPoint : RAKE_FIGURE) {
			Point translatedPoint = new Point(refPoint).translate(translationPoint);
			iMapMode.DPtoLP(translatedPoint);
			rake.addPoint(translatedPoint);
		}
		if(rake instanceof PolylineShape) {
			// adapt bounds for PolylineShape
			Point loc = rake.getParent().getBounds().getLocation().getCopy().translate(parentDimension).translate(RAKE_FIGURE_TRANSLATION);
			Rectangle b = new Rectangle(loc, new Dimension(-RAKE_FIGURE_TRANSLATION.x, -RAKE_FIGURE_TRANSLATION.y));
			((PolylineShape)rake).setBounds(b);
		}
	}

	/**
	 * Draw the pin arrow or refresh it
	 * 
	 * @param arrow
	 *        figure which contains the arrow
	 * @param iMapMode
	 *        the map mode to translate points
	 * @param parentDimension
	 *        the dimension of the parent
	 * @param direction
	 *        the direction to which the arrow is directed : {@link PositionConstants#SOUTH}, {@link PositionConstants#NORTH},
	 *        {@link PositionConstants#EAST}, {@link PositionConstants#WEST}
	 */
	public static void redrawPinArrow(AbstractPointListShape arrow, IMapMode iMapMode, Dimension parentDimension, int direction) {
		arrow.removeAllPoints();
		List<Point> template;
		switch(direction) {
		case PositionConstants.NORTH:
			template = UP_PIN_ARROW_FIGURE;
			break;
		case PositionConstants.SOUTH:
			template = DOWN_PIN_ARROW_FIGURE;
			break;
		case PositionConstants.WEST:
			template = LEFT_PIN_ARROW_FIGURE;
			break;
		case PositionConstants.EAST:
		default:
			template = RIGHT_PIN_ARROW_FIGURE;
			break;
		}
		double xScale = parentDimension.preciseWidth() / PIN_ARROW_FIGURE_SIZE.x;
		double yScale = parentDimension.preciseHeight() / PIN_ARROW_FIGURE_SIZE.y;
		for(Point refPoint : template) {
			Point translatedPoint = new Point(refPoint).scale(xScale, yScale);
			iMapMode.DPtoLP(translatedPoint);
			arrow.addPoint(translatedPoint);
		}
		if(arrow instanceof PolylineShape) {
			// adapt bounds for PolylineShape
			((PolylineShape)arrow).setBounds(arrow.getParent().getBounds());
		}
	}

	/**
	 * Undraw the polyline figure
	 * 
	 * @param figure
	 *        figure to undraw
	 */
	public static void undrawFigure(AbstractPointListShape figure) {
		figure.removeAllPoints();
	}

	/**
	 * Get the size of the edit part node
	 * 
	 * @param editPart
	 *        edit part
	 * @param dimensionChangeEvent
	 *        the change dimension event or null
	 * @return the node size
	 */
	public static Dimension getNodeSize(AbstractGraphicalEditPart editPart, Notification dimensionChangeEvent) {
		Bounds bounds = null;
		// if a bounds change is notified, size may have changed, otherwise, get
		// size from model
		// shape
		if(dimensionChangeEvent != null && dimensionChangeEvent.getNotifier() instanceof Bounds) {
			bounds = (Bounds)dimensionChangeEvent.getNotifier();
		} else if(editPart.getModel() instanceof Shape) {
			Shape shape = (Shape)editPart.getModel();
			if(shape.getLayoutConstraint() instanceof Bounds) {
				bounds = (Bounds)shape.getLayoutConstraint();
			}
		}
		Dimension size = null;
		if(bounds != null) {
			size = new Dimension(bounds.getWidth(), bounds.getHeight());
		}
		if(size == null || size.isEmpty()) {
			// recover the exact size from the figure
			Dimension preferredSize = editPart.getFigure().getPreferredSize(size.width, size.height);
			if(size.width <= 0) {
				size.width = preferredSize.width;
			}
			if(size.height <= 0) {
				size.height = preferredSize.height;
			}
		}
		return size;
	}
}
