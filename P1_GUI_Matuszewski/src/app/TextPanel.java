package app;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.*;

/**
 * Pole tekstowe umieszczone w BottomPanelu
 * @author Jedrzej Matuszewski
 * @version 1.0
 */
public class TextPanel extends JPanel {
	private JTextArea textArea;
	
	/**
	 * Konstruktor
	 */
	public TextPanel()
	{
		textArea = new JTextArea();
		textArea.setBorder(BorderFactory.createEmptyBorder(5, 5, 0, 0));
		textArea.setEditable(false);
		textArea.setBackground(new Color(255,255,255));
		
		setLayout(new BorderLayout());
		
		add(new JScrollPane(textArea), BorderLayout.CENTER);
		
	}
	
	/**
	 * Metoda wprowadzajaca tekst do pola tekstowego
	 * @param text - tekst do wprowadzenia
	 */
	public void appendText(String text)
	{
		textArea.append(text);
	}
}