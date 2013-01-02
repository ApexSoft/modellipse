package org.eclipse.papyrus.uml.diagram.sequence.apex.part.tweaks;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.TraverseEvent;
import org.eclipse.swt.events.TraverseListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.ui.forms.FormColors;

public abstract class TweakViewer extends StructuredViewer {

	private final List<TweakItem> fTweakItems;
	
	private final Composite fContainer;
	
	private Image fGradientBackground;
	private TweakItem fSelectedItem;
	
	private ILabelProvider fToolTipLabelProvider;
	
	public TweakViewer(Composite parent, int style) {
		fTweakItems = new ArrayList<TweakItem>();

		fContainer= new Composite(parent, SWT.NONE);
		GridData layoutData= new GridData(SWT.FILL, SWT.TOP, true, false);
		fContainer.setLayoutData(layoutData);
		fContainer.addTraverseListener(new TraverseListener() {
			public void keyTraversed(TraverseEvent e) {
				e.doit= true;
			}
		});
		fContainer.setBackgroundMode(SWT.INHERIT_DEFAULT);

		fContainer.addListener(SWT.Resize, new Listener() {
			public void handleEvent(Event event) {
				int height= fContainer.getClientArea().height;

				if (fGradientBackground == null || fGradientBackground.getBounds().height != height) {
					Image image= height == 0 ? null : createGradientImage(height, event.display);
					fContainer.setBackgroundImage(image);

					if (fGradientBackground != null)
						fGradientBackground.dispose();
					fGradientBackground= image;
				}
			}
		});

		hookControl(fContainer);

//		GridLayout gridLayout= new GridLayout(columns, false);
//		gridLayout.marginWidth= 0;
//		gridLayout.marginHeight= 0;
//		gridLayout.verticalSpacing= 0;
//		gridLayout.horizontalSpacing= 0;
//		fContainer.setLayout(gridLayout);
		FormLayout formLayout = new FormLayout();
		formLayout.marginBottom = 2;
		fContainer.setLayout(formLayout);
		
		fContainer.addListener(SWT.Resize, new Listener() {
			public void handleEvent(Event event) {
				refresh();
			}
		});
	}
	
	protected abstract void hookTweakItems(List<TweakItem> items);
	
	protected abstract void unhookTweakItems(List<TweakItem> items);
	
	public void setToolTipLabelProvider(ILabelProvider toolTipLabelProvider) {
		fToolTipLabelProvider = toolTipLabelProvider;
	}

	@Override
	protected void inputChanged(Object input, Object oldInput) {
		if (fContainer.isDisposed())
			return;

		disableRedraw();
		try {
			unhookTweakItems(fTweakItems);
			int lastIndex = buildItem(input);
			hookTweakItems(fTweakItems);
			
			while (lastIndex < fTweakItems.size()) {
				TweakItem item = fTweakItems.remove(fTweakItems.size() - 1);
				if (item.getData() != null) {
					unmapElement(item.getData());
				}
				item.dispose();
			}

			updateSize();
			fContainer.layout(true, true);
		} finally {
			enableRedraw();
		}
	}

	@Override
	protected Widget doFindInputItem(Object element) {
		if (element == null) {
			return null;
		}
		
		if (element == getInput() || element.equals(getInput())) {
			return doFindItem(element);
		}
		
		return null;
	}

	@Override
	protected Widget doFindItem(Object element) {
		if (element == null) {
			return null;
		}
		
		for (int i = 0, size = fTweakItems.size(); i < size; i++) {
			TweakItem item = fTweakItems.get(i);
			if (item.getData() == element || element.equals(item.getData()))
				return item;
		}
		
		return null;
	}

	@Override
	protected void doUpdateItem(Widget widget, Object element, boolean fullMap) {
		if (widget instanceof TweakItem) {
			final TweakItem item = (TweakItem)widget;
			
			if (fullMap) {
				associate(element, item);
			} else {
				Object data = item.getData();
				if (data != null) {
					unmapElement(data, item);
				}
				item.setData(element);
				mapElement(element, item);
			}
		}
	}

	@Override
	protected List getSelectionFromWidget() {
		if (fSelectedItem == null)
			return Collections.EMPTY_LIST;

		if (fSelectedItem.getData() == null)
			return Collections.EMPTY_LIST;

		ArrayList<Object> result= new ArrayList<Object>();
		result.add(fSelectedItem.getData());
		return result;
	}

	@Override
	protected void internalRefresh(Object element) {
		disableRedraw();
		try {
			TweakItem item= (TweakItem) doFindItem(element);
			if (item == null) {
				for (int i= 0, size= fTweakItems.size(); i < size; i++) {
					TweakItem item1= fTweakItems.get(i);
					item1.refresh();
				}
			} else {
				item.refresh();
			}
			if (updateSize())
				fContainer.layout(true, true);
		} finally {
			enableRedraw();
		}
	}
	
	@Override
	public void reveal(Object element) {
	}

	@Override
	protected void setSelectionToWidget(List l, boolean reveal) {
		TweakItem focusItem = null;
		
		for (int i = 0, size = fTweakItems.size(); i < size; i++) {
			TweakItem item = fTweakItems.get(i);
			if (item.hasFocus()) {
				focusItem = item;
			}
			item.setSelected(false);
		}
		
		if (l == null) {
			return;
		}
		
		for (Iterator<?> iterator = l.iterator(); iterator.hasNext();) {
			Object element = iterator.next();
			TweakItem item = (TweakItem) doFindItem(element);
			if (item != null) {
				fSelectedItem = item;
				item.setSelected(true);
				if (item == focusItem) {
					item.setFocus(true);
				}
			}
		}
	}

	@Override
	public Control getControl() {
		return fContainer;
	}
	
	private int buildItem(Object input) {
		IStructuredContentProvider contentProvider = (IStructuredContentProvider)getContentProvider();
		Object[] elements = contentProvider.getElements(input);
		if (elements == null) {
			return 0;
		}
		
		int index = 0;
		
		for (Object element : elements) {
			TweakItem item;
			if (index < fTweakItems.size()) {
				item = fTweakItems.get(index);
				if (item.getData() != null) {
					unmapElement(item.getData());
				}
			} else {
				item = createItem();
				fTweakItems.add(item);
			}
			
			index += 1;
			
			if (equals(element, item.getData())) {
				update(element, null);
			} else {
				item.setData(element);
				item.refresh();
			}
			
			mapElement(element, item);
			
			if (contentProvider instanceof ITreeContentProvider) {
				buildItem(element);
			}
		}
		
		return index;
	}
	
	private TweakItem createItem() {
		TweakItem result = new TweakItem(this, fContainer);
		result.setLabelProvider((ILabelProvider) getLabelProvider());
		result.setContentProvider((IStructuredContentProvider) getContentProvider());
		if (fToolTipLabelProvider != null) {
			result.setToolTipLabelProvider(fToolTipLabelProvider);
		} else {
			result.setToolTipLabelProvider((ILabelProvider) getLabelProvider());
		}
		return result;
	}
	
	private boolean updateSize() {
		boolean requiresLayout = true;
		return requiresLayout;
	}

	private void enableRedraw() {
		fContainer.setRedraw(true);
	}

	private void disableRedraw() {
		fContainer.setRedraw(false);
	}
	
	private int hOffset = 0;
	
	protected int getHorizontalOffset() {
		return hOffset;
	}
	protected void setHorizontalOffset(int offset) {
		hOffset = offset;
	}
	
	private Image createGradientImage(int height, Display display) {
		int width= 50;

		Image result= new Image(display, width, height);

		GC gc= new GC(result);

		Color colorC= createColor(SWT.COLOR_WIDGET_BACKGROUND, SWT.COLOR_LIST_BACKGROUND, 35, display);
		Color colorD= createColor(SWT.COLOR_WIDGET_BACKGROUND, SWT.COLOR_LIST_BACKGROUND, 45, display);
		Color colorE= createColor(SWT.COLOR_WIDGET_BACKGROUND, SWT.COLOR_LIST_BACKGROUND, 80, display);
		Color colorF= createColor(SWT.COLOR_WIDGET_BACKGROUND, SWT.COLOR_LIST_BACKGROUND, 70, display);
		Color colorG= createColor(SWT.COLOR_WIDGET_BACKGROUND, SWT.COLOR_WHITE, 45, display);
		Color colorH= createColor(SWT.COLOR_WIDGET_NORMAL_SHADOW, SWT.COLOR_LIST_BACKGROUND, 35, display);

		try {
			drawLine(width, 0, colorC, gc);
			drawLine(width, 1, colorC, gc);

			gc.setForeground(colorD);
			gc.setBackground(colorE);
			gc.fillGradientRectangle(0, 2, width, 2 + 8, true);

			gc.setBackground(colorE);
			gc.fillRectangle(0, 2 + 9, width, height - 4);

			drawLine(width, height - 3, colorF, gc);
			drawLine(width, height - 2, colorG, gc);
			drawLine(width, height - 1, colorH, gc);

		} finally {
			gc.dispose();

			colorC.dispose();
			colorD.dispose();
			colorE.dispose();
			colorF.dispose();
			colorG.dispose();
			colorH.dispose();
		}

		return result;
	}
	
	private void drawLine(int width, int position, Color color, GC gc) {
		gc.setForeground(color);
		gc.drawLine(0, position, width, position);
	}

	private Color createColor(int color1, int color2, int ratio, Display display) {
		RGB rgb1= display.getSystemColor(color1).getRGB();
		RGB rgb2= display.getSystemColor(color2).getRGB();

		RGB blend= FormColors.blend(rgb2, rgb1, ratio);

		return new Color(display, blend);
	}

	@Override
	protected void handleDispose(DisposeEvent event) {
		if (fGradientBackground != null) {
			fGradientBackground.dispose();
			fGradientBackground = null;
		}
		if (fToolTipLabelProvider != null) {
			fToolTipLabelProvider.dispose();
			fToolTipLabelProvider = null;
		}
		
		if (fTweakItems != null) {
			Iterator<TweakItem> iterator = fTweakItems.iterator();
			while (iterator.hasNext()) {
				TweakItem item = iterator.next();
				item.dispose();
			}
			fTweakItems.clear();
		}
		super.handleDispose(event);
	}
}
