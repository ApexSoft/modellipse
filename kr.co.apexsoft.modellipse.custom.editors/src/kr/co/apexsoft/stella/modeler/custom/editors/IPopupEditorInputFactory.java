package kr.co.apexsoft.stella.modeler.custom.editors;

import java.util.Comparator;

import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.ILabelProvider;

public interface IPopupEditorInputFactory {
	
	ILabelProvider getLabelProvider();
	
	IContentProvider getContentprovider();
	
	Object getInput();

	void update(Object object);

	Comparator<?> getComparator();

}
