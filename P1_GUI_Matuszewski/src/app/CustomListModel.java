package app;

import java.util.Vector;

import javax.swing.AbstractListModel;

/**
 * Model danych wykorzystywanych w liscie
 * @author Jedrzej Matuszewski
 * @version 1.0
 */
public class CustomListModel  extends AbstractListModel{
	Vector<OperationListItem> v = new Vector<OperationListItem>();
	
	/**
	 * Konstuktor bezparametrowy
	 */
	public CustomListModel() {}
	
	/**
	 * Konstruktor
	 * @param items - tablica elementow modelu
	 */
	public CustomListModel(OperationListItem[] items) {
		for(int i = 0; i < items.length; i++) {
			v.addElement(items[i]);
		}
	}

	
	/**
	 * Metoda pobierajaca ilosc elementow w modelu
	 */
	public int getSize() { return v.size(); }
	
	/**
	 * Pobieranie elementu o indeksie
	 * @param index - numer indeksu
	 */
	public Object getElementAt(int index) { return v.elementAt(index); }
	
	/**
	 * Dodawanie elementu do modelu
	 * @param item - nowy element
	 */
	public void add(OperationListItem item) {
		add(getSize(), item);
	}
	
	/**
	 * Dodawanie elementu do modelu pod wskazany indeks
	 * @param index - numer indeksu
	 * @param item - nowy element
	 */
	public void add(int index, OperationListItem item) {
		v.insertElementAt(item, index);
		fireIntervalAdded(this,index,index);
	}
	
	/**
	 * Usuwanie elementu z modelu
	 * @param index - numer indeksu elementu
	 */
	public void remove(int index) {
		v.removeElementAt(index);
		fireIntervalRemoved(this, index, index);
	}

}
