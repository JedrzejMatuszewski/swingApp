package app;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class RightPanel extends JPanel {

	JButton putNumberButton;
	JButton clearTableButton;
	JButton putRandomNumbersButton;
	JButton saveToFileButton;
	ICommandListener listener;

	public RightPanel() {
		putNumberButton = new JButton("Dodaj");
		clearTableButton = new JButton("Wyzeruj");
		putRandomNumbersButton = new JButton("Wypełnij");
		saveToFileButton = new JButton("Zapisz");
		
		Dimension buttonSize = new Dimension(100,25);
		putNumberButton.setPreferredSize(buttonSize);
		clearTableButton.setPreferredSize(buttonSize);
		putRandomNumbersButton.setPreferredSize(buttonSize);
		saveToFileButton.setPreferredSize(buttonSize);
		
		CreateWiewOfComponent();
		
		putRandomNumbersButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(listener != null)
				{
					listener.eventOccured(Command.FILL_RANDOM);
				}
				
			}
		});
		
		clearTableButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(listener != null)
				{
					listener.eventOccured(Command.CLEAR);
				}
				
			}
		});
		
		saveToFileButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(listener != null)
				{
					listener.eventOccured(Command.SAVE_TO_FILE);
				}
				
			}
		});
		
		
		putNumberButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(listener != null)
				{
					listener.eventOccured(Command.ADD_VALUE);
				}
				
			}
		});

		
	}
	
	public void setListener(ICommandListener rightPanelListener)
	{
		this.listener = rightPanelListener;
	}
	

	private void CreateWiewOfComponent() {
		setLayout(new GridBagLayout());
		setBorder(BorderFactory.createEmptyBorder(1,5,5,12));
		
		GridBagConstraints gc = new GridBagConstraints();
		gc.fill = GridBagConstraints.NONE;

		gc.gridy = 0;
		gc.weightx = 1;
		gc.weighty = 0.1;

		gc.gridx = 0;
		gc.anchor = GridBagConstraints.LINE_START;

		
		add(putNumberButton, gc);
		
		gc.gridy++;
		add(clearTableButton, gc);
		gc.gridy++;
		add(putRandomNumbersButton, gc);
		gc.gridy++;
		add(saveToFileButton, gc);
		

		gc.gridy++;
		gc.weighty = 2;
		add(new JSeparator(), gc);
	}
}
