package app;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * Okno zawierajace informacje o autorze
 * @author Jedrzej Matuszewski
 * @version 1.0
 */
public class AboutWindow extends JDialog {

	/**
	 * Opis zdjecia
	 */
	private JLabel imgLabel;
	/**
	 * Tresc okna
	 */
	private JLabel textLabel;
	/**
	 * Przycisk zamykajacy okno 
	 */
	private JButton closeButton;

	/**
	 * Domyślny konstruktor
	 */
	public AboutWindow() {
		setTitle("Info - P1_GUI_Matuszewski");
		setSize(new Dimension(230, 340));
		setMinimumSize(new Dimension(230, 340));
		arrangeWindow();


		textLabel = new JLabel("<html><center><h3>P1 GUI</h3> Autor:<br/>"
				+ "Jędrzej Matuszewski <br/>"
				+ "WEII PK <br />"
				+ "Inf. 4 sem U-16864 <br/> "
				+ "<a href='mailto:jedrzej@matuszewski.net'>"
				+ "jedrzej@matuszewski.net</a>"
				+ "</center></html>");
		
		imgLabel = new JLabel(createIcon("weii.png"));
		
		closeButton = new JButton("Zamknij");
		
		closeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		
		
		
		
		
		

		setLayout(new GridBagLayout());

		GridBagConstraints gc = new GridBagConstraints();
		gc.fill = GridBagConstraints.NONE;

		gc.gridy = 0;
		gc.weightx = 1;
		gc.weighty = 0.1;

		gc.gridx = 0;
		gc.anchor = GridBagConstraints.CENTER;

		add(imgLabel, gc);
		
		gc.gridy = 1;
		gc.weighty = 1;
		add(textLabel, gc);
		
		gc.gridy = 2;
		gc.weighty = 0.1;
		add(closeButton, gc);
		
		gc.gridy = 3;
		gc.weighty = 0.2;
		add(new JSeparator(), gc);




	}
	
	/**
	 * Metoda tworzaca ikone
	 * @param file - adres do pliku w folderze icons
	 * @return ImageIcon - ikona
	 */
	public ImageIcon createIcon(String file) {
		String path = System.getProperty("user.dir");
		path = path + "\\icons\\" + file;
		return new ImageIcon(path);
	}
	
	/**
	 * Metoda rozmieszczajaca okno
	 */
	private void arrangeWindow() {
		// pobranie rozmiarow aplikacji
		Dimension dialogSize = getSize();
		// pobranie rozdzielczosci pulpitu
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		if (dialogSize.height > screenSize.height)
			dialogSize.height = screenSize.height;
		if (dialogSize.width > screenSize.width)
			dialogSize.height = screenSize.width;

		// rozmieszczenie aplikacji na srodku ekranu
		setLocation((screenSize.width - dialogSize.width) / 2, (screenSize.height - dialogSize.height) / 2);
	}

}
