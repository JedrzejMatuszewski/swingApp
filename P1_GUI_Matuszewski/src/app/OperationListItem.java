package app;

/**
 * Element listy z lewego panelu aplikacji
 * @author Jedrzej Matuszewski
 * @version 1.0
 */
public class OperationListItem {
	
	Command command;
	String operationName;
	
	/**
	 * Konstruktor
	 * @param c - komenda (operacja) do wykonania
	 * @param o - nazwa elementu listy
	 */
	public OperationListItem(Command c, String o) {
		this.command = c;
		this.operationName = o;
	}

	/**
	 * Przeciazona metoda toString() - zwraca nazwe elementu listy
	 */
	public String toString() {
		return this.operationName;
	}
}
