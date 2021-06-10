package app;

/**
 * Interfejs sluzacy do przekazywania polecen
 * @author Jedrzej Matuszewski
 * @version 1.0
 */
public interface ICommandListener {
	public void eventOccured(Command c);
}
