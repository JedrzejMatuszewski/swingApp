package app;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.util.Random;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

public class DataTable extends JPanel {
	JTable table;

	public DataTable() {
		String[] collumnNames = { "1", "2", "3", "4", "5" };
		String data[][] = { { "0", "0", "0", "0", "0", }, { "0", "0", "0", "0", "0", }, { "0", "0", "0", "0", "0", },
				{ "0", "0", "0", "0", "0", }, { "0", "0", "0", "0", "0", } };

		table = new JTable(data, collumnNames);
		table.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));

		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(450, 102));

		JTableHeader header = table.getTableHeader();
		header.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		header.setFont(new Font("SansSerif", Font.BOLD, 13));

		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
		table.getColumnModel().getColumn(0).setCellRenderer(rightRenderer);
		table.getColumnModel().getColumn(1).setCellRenderer(rightRenderer);
		table.getColumnModel().getColumn(2).setCellRenderer(rightRenderer);
		table.getColumnModel().getColumn(3).setCellRenderer(rightRenderer);
		table.getColumnModel().getColumn(4).setCellRenderer(rightRenderer);
		add(header, BorderLayout.NORTH);
		add(table, BorderLayout.CENTER);
	}

	public void setValueAt(Object value, int row, int col) throws Exception {
		String num_string = String.valueOf(value);
		
		if((row > 4) || col > 4) 
			throw new Exception("Wprowadzono błędny adres komórki! \n");
		
       this.table.setValueAt(num_string, row, col);
    }
	
	public String getValueForm(int row, int col) {
		return table.getValueAt(row, col).toString();
	}
	
	public float getFloatValueFrom(int row, int col) throws Exception
	{
		String value = table.getValueAt(row, col).toString();
		try {
			return Float.parseFloat(value); 
		}
		catch(Exception ex)
		{
			throw new Exception("Wartość: " + value
					+ " [" + (row+1) +"," + (col+1) 
					+ "] nie jest liczbą! \n");
		}
		
	}
	
	
	

	public void resetData() throws Exception
	{
		for (int w = 0; w < 5; w++) {
			for (int k = 0; k < 5; k++) {
				setValueAt(0, w, k);
			}
		}
	}

	public void generateRandomValues() throws Exception {
		Random rand = new Random();
		float num;
		num = rand.nextFloat();

		for (int w = 0; w < 5; w++) {
			for (int k = 0; k < 5; k++) {
				num = rand.nextInt(500);
				setValueAt(num, w, k);
			}
		}
	}
	
	public float sum() throws Exception
	{
		float result = 0;
		for (int w = 0; w < 5; w++) {
			for (int k = 0; k < 5; k++) {
				result += getFloatValueFrom(w,k); 
			}
		}
		return result;
	}
	
	public float avg() throws Exception {
		return sum()/25;
	}
	
	public float min() throws Exception {
		float result = 999999999;
		float num;
		for (int w = 0; w < 5; w++) {
			for (int k = 0; k < 5; k++) {
				num = getFloatValueFrom(w,k);
				if(num<result) {
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
				num = getFloatValueFrom(w,k);
				if(num>result) {
					result = num;
				}
			}
		}
		return result;
	}
}
