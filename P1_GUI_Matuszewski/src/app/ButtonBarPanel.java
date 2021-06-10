package app;

import com.l2fprod.common.swing.JButtonBar;
import com.l2fprod.common.swing.plaf.blue.BlueishButtonBarUI;
import com.l2fprod.common.swing.plaf.misc.IconPackagerButtonBarUI;
import com.l2fprod.common.util.ResourceManager;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.*;

import com.l2fprod.common.demo.ButtonBarMain;

/**
 * Gorny panel z przyciskami
 * @author Jedrzej Matuszewski
 * @version 1.0
 */
public class ButtonBarPanel extends JPanel {


	DataTable dataTable;
	Chart chart;
	
	private Component currentComponent;

	/**
	 * Konstruktor
	 * @param toolbar - obiekt klasy JButtonBar
	 */
	public ButtonBarPanel(JButtonBar toolbar) {
		setLayout(new BorderLayout());

		add("West", toolbar);

		ButtonGroup group = new ButtonGroup();
		
		dataTable = new DataTable();
		chart = new Chart();
		

		addButton("Tabela", "table.png",
				dataTable,toolbar, group);

		addButton("Wykres", "chart.png",
				chart, toolbar, group);

	}


	
	/**
	 * Metoda pomocnicza wykorzystywana w funkcji addButton
	 * @param component - obiekt klasy Component
	 */
	private void show(Component component) {
		if (currentComponent != null) {
			remove(currentComponent);
		}
		add("Center", currentComponent = component);
		revalidate();
		repaint();
	}

	/**
	 * Metoda dodajaca przycisk do panelu
	 * @param title - tytul przycisku
	 * @param iconUrl - adres ikony
	 * @param component - komponent docelowy
	 * @param bar - JbuttonBar
	 * @param group - ButtonGroup
	 */
	private void addButton(String title, String iconUrl, final Component component, JButtonBar bar, ButtonGroup group) {
		String iconSource = getSrc(iconUrl);
		Action action = new AbstractAction(title, new ImageIcon(iconSource)) {

			public void actionPerformed(ActionEvent e) {
				show(component);
			}
		};

		JToggleButton button = new JToggleButton(action);
		bar.add(button);

		group.add(button);

		if (group.getSelection() == null) {
			button.setSelected(true);
			show(component);
		}
	}
	
	/**
	 * Metoda pomocnicza, generuje adres globalny pliku ikony
	 * @param file -  nazwa ikony
	 * @return String - bezwzględny adres ikony
	 */
	private String getSrc(String file)
	{
		String path = System.getProperty("user.dir");
		path = path + "\\icons\\" + file;
		return path;
	}

}
