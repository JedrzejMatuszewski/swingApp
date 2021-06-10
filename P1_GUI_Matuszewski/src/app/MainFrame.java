package app;

import java.awt.*;
import java.awt.TrayIcon.MessageType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.nio.file.*;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;



/**
 * Glowne okno aplikacji
 * @author Jedrzej Matuszewski
 * @version 1.0
 */
public class MainFrame extends JFrame {

	/**
	 * Pasek narzedziowy
	 */
	private ToolBar toolBar;
	/**
	 * Prawy panel aplikacji
	 */
	private RightPanel rightPanel;
	/**
	 * Lewy panel aplikacji
	 */
	private LeftPanel leftPanel;
	/**
	 * Dolny panel aplikacji
	 */
	private BottomPanel bottomPanel;

	/**
	 * Obiekt sluzacy do odczytu/zapisu pliku
	 */
	private JFileChooser fileChooser;

	/**
	 * Okno pomocy
	 */
	private HelpWindow helpWindow;
	/**
	 * Okno o autorze
	 */
	private AboutWindow aboutWindow;
	
	/**
	 * Panel typu JOptionPane
	 */
	private JOptionPane optionPane;
	
	/**
	 * Okno z porada dnia
	 */
	private Tips tips;
	
	/**
	 * Logger zapisujacy do konsoli
	 */
	private static final Logger consoleLogger = LogManager.getLogger("ConsoleLogger");
	/**
	 * Logger zapisujacy do pliku
	 */
	private static final Logger fileLogger = LogManager.getLogger("FileLogger");
	

	/**
	 * Konstruktor
	 */
	public MainFrame() {
		// General settings
		super("P1_GUI Matuszewski");
		setLayout(new BorderLayout());
		setMinimumSize(new Dimension(600, 520));
		setSize(695, 571);
		setVisible(true);
		arrangeWindow();

		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				makeOperation(Command.CLOSE_APP);
			}
		});
		

		toolBar = new ToolBar();
		rightPanel = new RightPanel();
		leftPanel = new LeftPanel();
		bottomPanel = new BottomPanel();
		fileChooser = new JFileChooser();

		helpWindow = new HelpWindow();
		helpWindow.setVisible(false);

		aboutWindow = new AboutWindow();
		aboutWindow.setVisible(false);
		
		optionPane = new JOptionPane();
		
		tips = new Tips();
		tips.setVisible(true);
		
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Pliki tekstowe .TXT", "txt", "text");
		fileChooser.setFileFilter(filter);
		
		fileLogger.info("Start aplikacji");
		consoleLogger.info("Start aplikacji");

		add(toolBar, BorderLayout.NORTH);
		add(rightPanel, BorderLayout.EAST);
		add(leftPanel, BorderLayout.WEST);
		add(bottomPanel, BorderLayout.SOUTH);

		toolBar.setListener(new ICommandListener() {
			public void eventOccured(Command c) {
				makeOperation(c);
			}
		});

		leftPanel.setListener(new ICommandListener() {
			public void eventOccured(Command c) {
				makeOperation(c);
			}
		});

		rightPanel.setListener(new ICommandListener() {
			public void eventOccured(Command c) {
				makeOperation(c);
			}
		});

		// Rejestracja menu
		setJMenuBar(CreateMenuBar());
	}

	/**
	 * Metoda tworzaca menu aplikacji
	 * @return JMenuBar - menu
	 */
	private JMenuBar CreateMenuBar() {
		JMenuBar menuBar = new JMenuBar();

		/* MENU PLIK ------------------------------------- */
		JMenu fileMenu = new JMenu("Plik");
		JMenuItem openDataItem = new JMenuItem("Otworz");
		JMenuItem saveDataItem = new JMenuItem("Zapisz");
		JMenuItem exitItem = new JMenuItem("Zamknij");

		fileMenu.add(openDataItem);
		fileMenu.add(saveDataItem);
		fileMenu.addSeparator();
		fileMenu.add(exitItem);

		// Mnemoniki i Acceleratory
		fileMenu.setMnemonic(KeyEvent.VK_P);
		openDataItem.setMnemonic(KeyEvent.VK_O);
		openDataItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));

		saveDataItem.setMnemonic(KeyEvent.VK_S);
		saveDataItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));

		exitItem.setMnemonic(KeyEvent.VK_Z);
		exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));

		exitItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				makeOperation(Command.CLOSE_APP);
			}
		});

		openDataItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				makeOperation(Command.IMPORT_FROM_FILE);
			}
		});

		saveDataItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				makeOperation(Command.SAVE_TO_FILE);
			}
		});

		/* MENU WIDOK ------------------------------------- */
		JMenu windowMenu = new JMenu("Widok");
		JCheckBoxMenuItem showToolBarItem = new JCheckBoxMenuItem("Pokaż pasek narzędzi");

		showToolBarItem.setSelected(true);
		windowMenu.add(showToolBarItem);
		
		windowMenu.setMnemonic(KeyEvent.VK_W);
		showToolBarItem.setMnemonic(KeyEvent.VK_N);
		
		showToolBarItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean showToolBar = showToolBarItem.getState();
				toolBar.setVisible(showToolBar);
			}
		});

		/* MENU Operations ------------------------------------- */
		JMenu calculationMenu = new JMenu("Obliczenia");
		JMenuItem sumItem = new JMenuItem("Suma");
		JMenuItem avgItem = new JMenuItem("Średnia");
		JMenuItem minItem = new JMenuItem("Minimum");
		JMenuItem maxItem = new JMenuItem("Maksimum");
		JMenuItem clearItem = new JMenuItem("Wyzeruj");
		JMenuItem randItem = new JMenuItem("Wypełnij");

		calculationMenu.add(sumItem);
		calculationMenu.add(avgItem);
		calculationMenu.add(minItem);
		calculationMenu.add(maxItem);
		calculationMenu.add(clearItem);
		calculationMenu.add(randItem);

		calculationMenu.setMnemonic(KeyEvent.VK_O);
		sumItem.setMnemonic(KeyEvent.VK_S);
		avgItem.setMnemonic(KeyEvent.VK_R);
		minItem.setMnemonic(KeyEvent.VK_M);
		maxItem.setMnemonic(KeyEvent.VK_K);
		clearItem.setMnemonic(KeyEvent.VK_W);
		randItem.setMnemonic(KeyEvent.VK_Y);

		sumItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				makeOperation(Command.SUM);
			}
		});

		avgItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				makeOperation(Command.AVG);
			}
		});

		minItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				makeOperation(Command.MIN);
			}
		});

		maxItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				makeOperation(Command.MAX);
			}
		});
		
		clearItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				makeOperation(Command.CLEAR);
			}
		});
		
		randItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				makeOperation(Command.FILL_RANDOM);
			}
		});

		/* MENU Help ------------------------------------- */
		JMenu helpMenu = new JMenu("Pomoc");
		JMenuItem helpItem = new JMenuItem("Strona pomocy");
		JMenuItem appInfoItem = new JMenuItem("Informacje o programie");

		helpMenu.add(helpItem);
		helpMenu.add(appInfoItem);

		helpMenu.setMnemonic(KeyEvent.VK_M);
		helpItem.setMnemonic(KeyEvent.VK_S);
		appInfoItem.setMnemonic(KeyEvent.VK_I);

		helpItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				helpWindow.setVisible(true);
			}
		});

		appInfoItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				aboutWindow.setVisible(true);
			}
		});

		/* ---------------------------------------------- */
		menuBar.add(fileMenu);
		menuBar.add(windowMenu);
		menuBar.add(calculationMenu);
		menuBar.add(helpMenu);

		return menuBar;
	}

	/**
	 * Metoda wybierajaca operacje do wykonania
	 * @param c - emum, typ operacji
	 */
	public void makeOperation(Command c) {
		bottomPanel.setInfo(c);
		try {
			operationPerformer(c);
			updateChart();
			bottomPanel.setStatus(true);
		} catch (Exception ex) {
			bottomPanel.setStatus(false);
			bottomPanel.sendMessage("Wystąpił błąd! ");
			bottomPanel.sendMessage(ex.getMessage() + "\n");
			
			consoleLogger.error(ex.getMessage());
			fileLogger.error(ex.getMessage());
			
			JOptionPane.showMessageDialog(this, ex.getMessage(), "Wystąpił błąd", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Metoda wykonujaca wskazana operacje
	 * @param c - enum, operacja do wykonanaia
	 */
	public void operationPerformer(Command c) throws Exception {
		float num;
		String message = "";
		if (c == Command.SUM) {
			num = this.leftPanel.buttonBarPanel.dataTable.tableModel.sum();
			message = "Suma: " + Float.toString(num);
			
		} else if (c == Command.AVG) {
			num = this.leftPanel.buttonBarPanel.dataTable.tableModel.avg();
			message = "Średnia: " + num;
			
		} else if (c == Command.MIN) {
			num = this.leftPanel.buttonBarPanel.dataTable.tableModel.min();
			message = "Minimum: " + num;
			
		} else if (c == Command.MAX) {
			num = this.leftPanel.buttonBarPanel.dataTable.tableModel.max();
			message = "Maximum: " + num;
			
		} else if (c == Command.ADD_VALUE) {
			int row = this.leftPanel.getRowNum() - 1;
			int col = this.leftPanel.getColNum() - 1;
			num = this.leftPanel.getTypedNum();
			this.leftPanel.buttonBarPanel.dataTable.tableModel.setValueAt(Float.toString(num), row, col);
			message = "Wprowadzono liczbę do tablicy";
		} else if (c == Command.CLEAR) {
			this.leftPanel.buttonBarPanel.dataTable.tableModel.resetData();
			message = "Tablica została zresetowana";
			
		} else if (c == Command.FILL_RANDOM) {
			this.leftPanel.buttonBarPanel.dataTable.tableModel.generateRandomValues();
			
			message = "Losowe liczby zostały umieszczone";
			
		}else if(c == Command.PRINT_DATE) {
			Date date = this.rightPanel.calendarCombo.getDate();
			String dt =new SimpleDateFormat("dd-MM-yyyy").format(date);
			message = "Wybrano date: " + dt;
		} else if (c == Command.SAVE_TO_FILE) {
			saveDataToFile();
			message = "Dane zostały zapisane do pliku";
			
		} else if (c == Command.IMPORT_FROM_FILE) {
			importDataFromFile();
			message = "Dane zostały zaimportowane";
			
		} else if (c == Command.CLOSE_APP) {
			closeApplication();
		}

		consoleLogger.info(message);
		fileLogger.info(message);
		this.bottomPanel.sendMessage(message + "\n");
	}

	/**
	 * Metoda zamykajaca aplikacje
	 */
	public void closeApplication() {
		int action = JOptionPane.showConfirmDialog(MainFrame.this, "Czy na pewno chcesz zamknąć aplikację?",
				"Potwierdź zamknięcie", JOptionPane.OK_CANCEL_OPTION);
		if (action == JOptionPane.OK_OPTION) {
			System.exit(0);
		}
	}

	/**
	 * Metoda zapisujaca dane do pliku
	 */
	public void saveDataToFile() throws Exception {
		String dataToSave = "";

		for (int w = 0; w < 5; w++) {
			for (int k = 0; k < 5; k++) {
				String num = leftPanel.buttonBarPanel.dataTable.tableModel.getValueAt(w, k).toString();
				dataToSave += num + " ";
			}
		}

		if (fileChooser.showSaveDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION) {
			Path path = Paths.get(fileChooser.getSelectedFile().getPath());
			byte[] strToBytes = dataToSave.getBytes();

			try {
				Files.write(path, strToBytes);
			} catch (IOException e) {
				throw new Exception("Wystąpił błąd podczas zapisu pliku! ");
			}
		} else {
			throw new Exception("Nie wybrano pliku! \n");
		}
	}

	/**
	 * Metoda pobierajaca dane z pliku
	 */
	public void importDataFromFile() throws Exception {

		if (fileChooser.showOpenDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION) {
			String numbersAsString[] = null;
			try {
				File myObj = fileChooser.getSelectedFile();
				Scanner myReader = new Scanner(myObj);
				while (myReader.hasNextLine()) {
					String line = myReader.nextLine();
					numbersAsString = line.split(" ");
				}
				myReader.close();

			} catch (Exception ex) {
				throw new Exception("Wystąpił błąd podczas otwarcia pliku! ");
			}
			int count = 0;
			for (int w = 0; w < 5; w++) {
				for (int k = 0; k < 5; k++) {
					String valueToSave = "";
					if (count >= numbersAsString.length) {
						valueToSave = "0.0";
					} else {
						valueToSave = numbersAsString[count];
					}
					leftPanel.buttonBarPanel.dataTable.tableModel.setValueAt(valueToSave, w, k);
					count++;
				}
			}

		} else {
			throw new Exception("Nie wybrano pliku! \n");
		}

	}
	
	/**
	 * Metoda aktualizujaca stan wykresu
	 */
	private void updateChart() throws Exception
	{
		float[] data = this.leftPanel.buttonBarPanel.dataTable.tableModel.getFloatArray();
		this.leftPanel.buttonBarPanel.chart.setData(data);
	}

	/**
	 * Metoda rozmieszczajaca okno na ekranie
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
