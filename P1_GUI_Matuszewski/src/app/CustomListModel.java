package app;

import java.util.Vector;

import javax.swing.AbstractListModel;

public class CustomListModel  extends AbstractListModel{
	Vector<OperationListItem> v = new Vector<OperationListItem>();
	
	public CustomListModel() {}
	
	public CustomListModel(OperationListItem[] items) {
		for(int i = 0; i < items.length; i++) {
			v.addElement(items[i]);
		}
	}

	
	public int getSize() { return v.size(); }
	public Object getElementAt(int index) { return v.elementAt(index); }
	
	public void add(OperationListItem item) {
		add(getSize(), item);
	}
	
	public void add(int index, OperationListItem item) {
		v.insertElementAt(item, index);
		fireIntervalAdded(this,index,index);
	}
	
	public void remove(int index) {
		v.removeElementAt(index);
		fireIntervalRemoved(this, index, index);
	}

}
