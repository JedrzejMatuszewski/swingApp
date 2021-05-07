package app;

import java.util.Random;

import javax.swing.table.AbstractTableModel;

public class CustomTableModel extends AbstractTableModel {

	private final int rowCount = 5;
	private final int colCount = 5;

	private String[][] data = new String[rowCount][colCount];
	private String[] colName = {"1","2","3","4","5"};
	
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

	public int getRowCount() {
		return rowCount;
	}

	public int getColumnCount() {
		return colCount;
	}

	public Object getValueAt(int row, int col) {
		return data[row][col];
	}

	public String getColumnName(int col) {
		return colName[col];
	}
	public String[] getColumnNames() { 
		return colName;
	}
	
	public void setValueAt(String value, int row, int col) throws Exception{
		if ((row > 4) || col > 4)
			throw new Exception("Wprowadzono błędny adres komórki! \n");
		
		data[row][col] = value;
		this.fireTableDataChanged();
	}

	
	public float getFloatValueAt(int row, int col) throws Exception {
		String value = this.getValueAt(row, col).toString();
		try {
			return Float.parseFloat(value);
		} catch (Exception ex) {
			throw new Exception("Wartość: " + value + " [" + (row + 1) + "," + (col + 1) + "] nie jest liczbą! \n");
		}

	}

	public void resetData() throws Exception {
		for (int w = 0; w < 5; w++) {
			for (int k = 0; k < 5; k++) {
				this.setValueAt("0.0", w, k);
			}
		}
	}

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

	public float sum() throws Exception {
		float result = 0;
		for (int w = 0; w < 5; w++) {
			for (int k = 0; k < 5; k++) {
				result += this.getFloatValueAt(w, k);
			}
		}
		return result;
	}

	public float avg() throws Exception {
		return this.sum() / 25;
	}

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
