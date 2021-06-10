package app;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
/**
 * Pasek narzedziowy aplikacji
 * @author Jedrzej Matuszewski
 * @version 1.0
 */
public class ToolBar extends JToolBar {

	/**
	 * Przycisk importu danych z pliku
	 */
	private JButton openFileButton;
	/**
	 * Przycisk zapisu danych do pliku
	 */
	private JButton saveFileButton;
	/**
	 * Przycisk zamykajacy aplikacje
	 */
	private JButton closeAppButton;
	
	
	/**
	 * Przycisk zerujacy dane w tabeli
	 */
	private JButton clearDataButton;
	/**
	 * Przycisk wprowadzajacy losowe dane do tabeli
	 */
	private JButton randomDataButton;
	
	/**
	 * Przycisk obliczajacy sume danych z tabeli
	 */
	private JButton mathSumButton;
	/**
	 * Przycisk obliczajacy srednia danych z tabeli
	 */
	private JButton mathAvgButton;
	/**
	 * Przycisk obliczajacy wartosc minimalna danych z tabeli
	 */
	private JButton mathMinButton;
	/**
	 * Przycisk obliczajacy wartosc maksymalna danych z tabeli
	 */
	private JButton mathMaxButton;
	
	/**
	 * Interfejs przekazujacy rodzaj wykonywanej operacji
	 */
	private ICommandListener listener;

	/**
	 * Konstruktor
	 */
	public ToolBar() {
		setBorder(BorderFactory.createEtchedBorder());
		setFloatable(false);

		openFileButton = new JButton(createIcon("icons8-open-file-under-cursor-25.png"));
		openFileButton.setToolTipText("Otwórz plik");
		saveFileButton = new JButton(createIcon("icons8-save-25.png"));
		saveFileButton.setToolTipText("Zapisz do pliku");
		closeAppButton = new JButton(createIcon("icons8-exit-25.png"));
		closeAppButton.setToolTipText("Zamknij aplikację");
		
		clearDataButton = new JButton(createIcon("icons8-delete-view-25.png"));
		clearDataButton.setToolTipText("Wyczyść tabelę");
		randomDataButton = new JButton(createIcon("icons8-dice-25.png"));
		randomDataButton.setToolTipText("Wprowadź losowe dane");
		
		mathSumButton = new JButton(createIcon("25sum.png"));
		mathSumButton.setToolTipText("Suma");
		mathAvgButton = new JButton(createIcon("25avg.png"));
		mathAvgButton.setToolTipText("Średnia");
		mathMinButton = new JButton(createIcon("25min.png"));
		mathMinButton.setToolTipText("Min");
		mathMaxButton = new JButton(createIcon("25max.png"));
		mathMaxButton.setToolTipText("Max");
		
		openFileButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(listener != null)
				{
					listener.eventOccured(Command.IMPORT_FROM_FILE);
				}
			}
		});
		
		saveFileButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(listener != null)
				{
					listener.eventOccured(Command.SAVE_TO_FILE);
				}
			}
		});
		
		clearDataButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(listener != null)
				{
					listener.eventOccured(Command.CLEAR);
				}
			}
		});
		
		randomDataButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(listener != null)
				{
					listener.eventOccured(Command.FILL_RANDOM);
				}
			}
		});
		
		mathSumButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(listener != null)
				{
					listener.eventOccured(Command.SUM);
				}
			}
		});
		
		mathAvgButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(listener != null)
				{
					listener.eventOccured(Command.AVG);
				}
			}
		});
		
		closeAppButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(listener != null)
				{
					listener.eventOccured(Command.CLOSE_APP);
				}
			}
		});
		
		mathMinButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(listener != null)
				{
					listener.eventOccured(Command.MIN);
				}
			}
		});
		
		mathMaxButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(listener != null)
				{
					listener.eventOccured(Command.MAX);
				}
			}
		});
		
		
		


		//setLayout(new FlowLayout(FlowLayout.LEFT));

		add(openFileButton);
		add(saveFileButton);
		add(closeAppButton);
		addSeparator();
		add(clearDataButton);
		add(randomDataButton);
		addSeparator();
		add(mathSumButton);
		add(mathAvgButton);
		add(mathMinButton);
		add(mathMaxButton);


	}
	/**
	 * Ustawianie interfejsu ICommandListener
	 * @param listener
	 */
	public void setListener(ICommandListener listener)
	{
		this.listener = listener;
	}
	

	/**
	 * Metoda tworzaca ikone
	 * @param file - nazwa ikony w katalogu icons
	 * @return icon - ikona
	 */
	public Icon createIcon(String file) {
		String path = System.getProperty("user.dir");
		path = path + "\\icons\\" + file;
		Icon icon = new ImageIcon(path);

		return icon;
	}

}
