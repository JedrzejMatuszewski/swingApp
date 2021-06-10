package app;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.SimpleDateFormat;

import javax.swing.*;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

import org.freixas.jcalendar.DateEvent;
import org.freixas.jcalendar.DateListener;
import org.freixas.jcalendar.JCalendarCombo;

/**
 * Prawy panel aplikacji
 * @author Jedrzej Matuszewski
 * @version 1.0
 */
public class RightPanel extends JPanel {

	/**
	 * Przycisk dodajacy dane do tabeli
	 */
	JButton putNumberButton;
	
	/**
	 * Przycisk czyszczacy tabele
	 */
	JButton clearTableButton;
	/**
	 * Przycisk umieszczajacy losowe dane w tabeli
	 */
	JButton putRandomNumbersButton;
	/**
	 * Przycisk zapisujacy dane do pliku
	 */
	JButton saveToFileButton;
	/**
	 * Interfejs przekazujacy rodzaj operacji do wykonania
	 */
	ICommandListener listener;
	
	/**
	 * Opis operacji
	 */
	JLabel operationLabel;
	/**
	 * Lista mozliwywch operacji
	 */
	JList<OperationListItem> operationList;
	/**
	 * Przycisk obliczajacy
	 */
	JButton calculateButton;

	/**
	 * Opis kalendarza
	 */
	JLabel calendarLabel;
	/**
	 * Kalendarz
	 */
	JCalendarCombo calendarCombo;
	
	/**
	 * Konstruktor
	 */
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
		
		operationLabel = new JLabel("Wybierz:  ");
		calculateButton = new JButton("Oblicz");
		operationLabel.setPreferredSize(buttonSize);
		calculateButton.setPreferredSize(buttonSize);
		
		operationList = new JList();
		
		OperationListItem[] listData = {
				new OperationListItem(Command.SUM, "Suma"),
				new OperationListItem(Command.AVG, "Średnia"),
				new OperationListItem(Command.MIN, "Min"),
				new OperationListItem(Command.MAX, "Max")
				};
		
		CustomListModel operationListModel = new CustomListModel(listData);

		operationList.setModel(operationListModel);
		operationList.setBorder(BorderFactory.createEtchedBorder());
		operationList.setSelectedIndex(0);
		operationList.setPreferredSize(new Dimension(100,75));
		
		
		calendarCombo = new JCalendarCombo();
		calendarCombo.setPreferredSize(buttonSize);
		calendarCombo.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
		calendarLabel = new JLabel("Wybierz datę: ");
		
		
		createWiewOfComponent();
		
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

		calculateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Command command = operationList.getSelectedValue().command;
				if(listener != null)
				{
					listener.eventOccured(command);
				}
			}
		});
		

		/*
		calendarCombo.addDateListener(new DateListener() {
			
			public void dateChanged(DateEvent arg0) {
				if((listener != null))
				{
					listener.eventOccured(Command.PRINT_DATE);
					System.out.println("listener \n");
				}
			}
		});
		*/
		
		calendarCombo.addFocusListener(new FocusListener() {
			public void focusLost(FocusEvent e) {}

			public void focusGained(FocusEvent e) {
				if((listener != null))
				{
					listener.eventOccured(Command.PRINT_DATE);
				}
			}
		});

		
	}
	
	/**
	 * Metoda ustawiajaca interfejs ICommandListener
	 * @param rightPanelListener - listener
	 */
	public void setListener(ICommandListener rightPanelListener)
	{
		this.listener = rightPanelListener;
	}
	

	/**
	 * Metoda generujaca wyglad komponentu
	 */
	private void createWiewOfComponent() {
		setLayout(new GridBagLayout());
		setBorder(BorderFactory.createEmptyBorder(1,5,5,12));
		
		GridBagConstraints gc = new GridBagConstraints();
		gc.fill = GridBagConstraints.NONE;

		gc.gridy = 0;
		gc.weightx = 1;
		gc.weighty = 0.1;

		gc.gridx = 0;
		gc.anchor = GridBagConstraints.LINE_START;

		
		//add(putNumberButton, gc);
		
		//gc.gridy++;
		add(clearTableButton, gc);
		gc.gridy++;
		add(putRandomNumbersButton, gc);
		gc.gridy++;
		add(saveToFileButton, gc);
		
		//gc.gridy++;
		//gc.weighty = 0.1;
		//add(new JSeparator(), gc);
		
		gc.weighty = 0.1;
		gc.gridy++;
		add(operationLabel , gc);
		gc.gridy++;
		add(operationList , gc);
		gc.gridy++;
		add(calculateButton , gc);
		
		//gc.gridy++;
		//gc.weighty = 0.1;
		//add(new JSeparator(), gc);
		
		gc.gridy++;
		add(calendarLabel , gc);
		gc.gridy++;
		add(calendarCombo , gc);
		

		
	}
}
