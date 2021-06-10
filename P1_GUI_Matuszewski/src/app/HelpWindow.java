package app;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;

import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

/**
 * Okno pomocy
 * @author Jedrzej Matuszewski
 * @version 1.0
 */
public class HelpWindow extends JDialog {
	
	private JEditorPane editorPane;
	private String dataLocation;

	public HelpWindow() {
		editorPane = new JEditorPane();
		editorPane.setEditable(false);
		editorPane.setBackground(new Color(255, 255, 255));

		setTitle("Pomoc - P1_GUI_Matuszewski");
		setLayout(new BorderLayout());
		setSize(new Dimension(800, 600));
		setMinimumSize(new Dimension(500, 500));
		arrangeWindow();

		dataLocation = System.getProperty("user.dir");
		dataLocation = dataLocation + "\\html\\help.html";

		try {
			editorPane.setContentType("text/html");
			editorPane.setPage(new File(dataLocation).toURI().toURL());
		} catch (Exception ex) {
			editorPane.setText("Error: " + ex);
		}

		editorPane.addHyperlinkListener(new HyperlinkListener() {
			public void hyperlinkUpdate(HyperlinkEvent event) {
				if (event.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
					try {
						editorPane.setPage(event.getURL());
					} catch (IOException e) {
						editorPane.setText("Exception: " + e);
					}
				}
			}
		});

		add(new JScrollPane(editorPane), BorderLayout.CENTER);

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