package app;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.*;

public class TextPanel extends JPanel {
	private JTextArea textArea;
	
	public TextPanel()
	{
		textArea = new JTextArea();
		textArea.setBorder(BorderFactory.createEmptyBorder(5, 5, 0, 0));
		textArea.setEditable(false);
		textArea.setBackground(new Color(255,255,255));
		
		setLayout(new BorderLayout());
		
		add(new JScrollPane(textArea), BorderLayout.CENTER);
		
	}
	
	public void appendText(String text)
	{
		textArea.append(text);
	}
}