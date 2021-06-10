package app;

import java.util.Random;

import javax.swing.table.AbstractTableModel;

/**
 * Model danych tabeli
 * @author Jedrzej Matuszewski
 * @version 1.0
 */
public class CustomTableModel extends AbstractTableModel {

	/**
	 * Ilosc wierszy
	 */
	private final int rowCount = 5;
	/**
	 * Ilosc kolumn
	 */
	private final int colCount = 5;

	private String[][] data = new String[rowCount][colCount];
	private String[] colName = {"1","2","3","4","5"};
	
	/**
	 * Konstruktor
	 */
	public CustomTableModel(){
		super();
		try {
			this.resetData();
		}
		catch(Exception ex) {
			System.out.println("Err");
		}
		this.fireTableDataChanged();
	}

	/**
	 * Pobieranie ilosci wierszy
	 */
	public int getRowCount() {
		return rowCount;
	}

	/**
	 * Pobieranie ilości kolumn
	 */
	public int getColumnCount() {
		return colCount;
	}

	/**
	 * Pobieranie wartosci spod wskazanego adresu
	 */
	public Object getValueAt(int row, int col) {
		return data[row][col];
	}

	/**
	 * Pobieranie nazwy kolumny
	 */
	public String getColumnName(int col) {
		return colName[col];
	}
	
	/**
	 * Pobieranie nazw kolumn
	 * @return - nazwy kolumn
	 */
	public String[] getColumnNames() {
		return colName;
	}
	
	/**
	 * Wprowadzanie wartosci do tabeli
	 * @param value - wartosc
	 * @param row - adres wiersza
	 * @param col - adres kolumny
	 * @throws Exception  wyjatek jezeli podamy nieprawidlowy adres wiersza 
	 * lub kolumny
	 */
	public void setValueAt(String value, int row, int col) throws Exception{
		if ((row > 4) || col > 4)
			throw new Exception("Wprowadzono błędny adres komórki!");
		
		data[row][col] = value;
		this.fireTableDataChanged();
	}

	
	/**
	 * Pobieranie wartosci typu Float z tabeli
	 * @param row - adres wiersza
	 * @param col - adres kolumny
	 * @return - wartosc typu Float
	 * @throws Exception wyjatek jezeli pobierana wartosc nie jest liczba
	 */
	public float getFloatValueAt(int row, int col) throws Exception {
		String value = this.getValueAt(row, col).toString();
		try {
			return Float.parseFloat(value);
		} catch (Exception ex) {
			throw new Exception("Wartość: " + value + " [" + (row + 1) + "," + (col + 1) + "] nie jest liczbą!");
		}

	}

	/**
	 * Zerowanie modelu
	 */
	public void resetData() throws Exception {
		for (int w = 0; w < 5; w++) {
			for (int k = 0; k < 5; k++) {
				this.setValueAt("0.0", w, k);
			}
		}
	}

	/**
	 * Wypelnienie modelu losowymi danymi
	 */
	public void generateRandomValues() throws Exception {
		Random rand = new Random();
		float num;
		num = rand.nextFloat();

		for (int w = 0; w < 5; w++) {
			for (int k = 0; k < 5; k++) {
				num = rand.nextInt(100);
				String s_num = String.valueOf(num);
				this.setValueAt(s_num, w, k);
			}
		}
		this.fireTableDataChanged();
	}

	/**
	 * Suma danych z modelu
	 * @return float - suma
	 */
	public float sum() throws Exception {
		float result = 0;
		for (int w = 0; w < 5; w++) {
			for (int k = 0; k < 5; k++) {
				result += this.getFloatValueAt(w, k);
			}
		}
		return result;
	}

	/**
	 * Srednia danych z modelu
	 * @return float - srednia
	 */
	public float avg() throws Exception {
		return this.sum() / 25;
	}

	/**
	 * Wartosc minmalna z danych w modelu
	 */
	public float min() throws Exception {
		float result = 999999999;
		float num;
		for (int w = 0; w < 5; w++) {
			for (int k = 0; k < 5; k++) {
				num = this.getFloatValueAt(w, k);
				if (num < result) {
					result = num;
				}
			}
		}
		return result;
	}

	/**
	 * Wartosc maksymalna danych z modelu
	 */
	public float max() throws Exception {
		float result = -999999999;
		float num;
		for (int w = 0; w < 5; w++) {
			for (int k = 0; k < 5; k++) {
				num = this.getFloatValueAt(w, k);
				if (num > result) {
					result = num;
				}
			}
		}
		return result;
	}
	
	/**
	 * Pobieranie danych z modelu w typie float
	 * @throws Exception wyjatek jezeli dane nie sa typu liczbowego
	 */
	public float[] getFloatArray() throws Exception
	{
		float data[] = new float[25];
		float num;
		int i = 0;
		
		for (int w = 0; w < 5; w++) {
			for (int k = 0; k < 5; k++) {
				num = this.getFloatValueAt(w, k);
				data[i] = num;
				i++;
			}
		}
		
		return data;
		
	}
}
