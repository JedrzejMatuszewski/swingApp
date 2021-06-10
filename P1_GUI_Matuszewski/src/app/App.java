package app;

import javax.swing.SwingUtilities;


/**
 * Klasa uruchumieniowa
 * @author Jedrzej Matuszewski
 * @version 1.0
 */
public class App {

	/**
	 * Funkcja uruchomienowa main()
	 * @param args - argumenty wejsciowe
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new MainFrame();
			}
		});

	}

}
