package app;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.*;
import javax.swing.border.*;

public class BottomPanel extends JPanel{
	
	private TextPanel textPanel;
	private JLabel infoLabel;
	private JLabel statusLabel;
	private JTextField infoText;
	private JTextField statusText;
	
	public BottomPanel() {
		textPanel = new TextPanel();
		textPanel.setPreferredSize(new Dimension(573,150));
		TitledBorder innerBorder = BorderFactory.createTitledBorder("Uzyskany rezultat");
		innerBorder.setTitleJustification(TitledBorder.CENTER);
		
		Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		textPanel.setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
		
		infoLabel = new JLabel("Info:");
		statusLabel = new JLabel("Status:");
		infoText = new JTextField();
		statusText = new JTextField();
		
		Dimension dim = new Dimension(240, 20);
		Border border = BorderFactory.createLineBorder(new Color(170,170,170));
		
		infoText.setPreferredSize(dim);
		infoText.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
		infoText.setEditable(false);
		infoText.setBackground(new Color(255,255,255));
		infoText.setText("Start aplikacji");
		statusText.setPreferredSize(dim);
		statusText.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
		statusText.setEditable(false);
		statusText.setBackground(new Color(255,255,255));
		statusText.setText("OK");
		setUpLayout();		
		
	}
	
	public void sendMessage(String message)
	{
		this.textPanel.appendText(message);
	}
	
	public void setInfo(Command c) {
		//SUM,AVG,MIN,MAX,ADD_VALUE,CLEAR,FILL_RANDOM,SAVE_TO_FILE,OPEN_FORM_FILE
		String result ="";
		if(c == Command.SUM)
		{
			result = "Suma elementów tabeli";
		}
		else if(c == Command.AVG)
		{
			result = "Średnia elementów tabeli";
		}
		else if(c == Command.MIN)
		{
			result = "Najmniejszy element tabeli";
		}
		else if(c == Command.MAX)
		{
			result = "Największy element tabeli";
		}
		else if(c == Command.ADD_VALUE)
		{
			result = "Wprowadzenie liczby do tabeli";
		}
		else if(c == Command.CLEAR)
		{
			result = "Zerowanie tabeli";
		}
		else if(c == Command.FILL_RANDOM)
		{
			result = "Wprowadzenie losowych danych";
		}
		else if(c == Command.SAVE_TO_FILE)
		{
			result = "Eksport danych do pliku";
		}
		else if(c == Command.IMPORT_FROM_FILE)
		{
			result = "Import danych z pliku";
		}
		infoText.setText(result);
	}
	
	
	
	public void setStatus(boolean status) {
		if(status) {
			statusText.setText("OK");
			statusText.setForeground(Color.BLACK);
		}
		else
		{
			statusText.setText("Error");
			statusText.setForeground(Color.RED);
		}
	}
	
	
	
	private void setUpLayout() {
		setLayout(new GridBagLayout());

		GridBagConstraints gc = new GridBagConstraints();
		gc.fill = GridBagConstraints.NONE;

		/* FIRST ROW------------------------------------------ */
		gc.weightx = 1;
		gc.weighty = 1;
		gc.gridy = 0;

		/* Text Panel */
		gc.gridx = 0;
		gc.anchor = GridBagConstraints.LINE_START;
							//top,left,bottom,right
		gc.insets = new Insets(0, 5, 10, 0);
		gc.gridwidth = 4;
		add(textPanel, gc);
		
		/* Second ROW------------------------------------------ */
		
		JPanel second = new JPanel();
		second.setLayout(new GridBagLayout());
		
		
		gc.weightx = 1;
		gc.weighty = 0.1;
		gc.gridy = 0;


		/* Info Label */
		gc.gridx = 0;
		gc.anchor = GridBagConstraints.LINE_END;
							//top,left,bottom,right
		gc.insets = new Insets(0, 10, 0, 5);
		gc.gridwidth = 1;
		gc.weightx = 0.1;
		second.add(infoLabel, gc);
		
		/* Info Text */
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.LINE_START;
							//top,left,bottom,right
		gc.insets = new Insets(0, 0, 0, 0);
		gc.weightx = 1;
		second.add(infoText, gc);
		
		
		/* Status Label */
		gc.gridx = 2;
		gc.anchor = GridBagConstraints.LINE_END;
							//top,left,bottom,right
		gc.insets = new Insets(0, 0, 0, 5);
		gc.weightx = 0.1;
		second.add(statusLabel, gc);
		
		/* Status Text */
		gc.gridx = 3;
		gc.anchor = GridBagConstraints.LINE_START;
							//top,left,bottom,right
		gc.insets = new Insets(0, 0, 0, 0);
		gc.weightx = 1;
		second.add(statusText, gc);
		
		/* Putting seccond row into grid ------------------------------------------ */
		gc.weightx = 1;
		gc.weighty = 1;
		gc.gridy = 1;
		gc.gridx = 0;
		gc.anchor = GridBagConstraints.LINE_START;
							//top,left,bottom,right
		gc.insets = new Insets(0, 0, 0, 0);
		gc.gridwidth = 4;

		second.setBackground(new Color(214,214,214));
		//second.setBorder(BorderFactory.createEmptyBorder(5,0,5,0));
		Dimension dim = new Dimension(584,30);
		second.setPreferredSize(dim);
		
		add(second, gc);
		
		
		
		
		
	}
}


