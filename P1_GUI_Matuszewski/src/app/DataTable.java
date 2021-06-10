package app;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.Random;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

/**
 * Komponent - tabela
 * @author Jedrzej Matuszewski
 * @version 1.0
 */
public class DataTable extends JPanel {
	/**
	 * Obiekt klasy JTable
	 */
	JTable table;
	
	/**
	 * Model danych
	 */
	CustomTableModel tableModel;

	public DataTable() {
		tableModel = new CustomTableModel();
		table = new JTable(tableModel);
		table.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));

		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(470, 230));
		Border outer = BorderFactory.createEmptyBorder(0, 5, 0, 0);
		Border inner = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		this.setBorder(BorderFactory.createCompoundBorder(outer, inner));

		JTableHeader header = table.getTableHeader();
		header.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		header.setFont(new Font("SansSerif", Font.PLAIN, 18));
		header.setPreferredSize(new Dimension(470, 47));

		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
		table.getColumnModel().getColumn(0).setCellRenderer(rightRenderer);
		table.getColumnModel().getColumn(1).setCellRenderer(rightRenderer);
		table.getColumnModel().getColumn(2).setCellRenderer(rightRenderer);
		table.getColumnModel().getColumn(3).setCellRenderer(rightRenderer);
		table.getColumnModel().getColumn(4).setCellRenderer(rightRenderer);
		
		table.setRowHeight(35);
		table.setFont(new Font("SansSerif", Font.PLAIN,16));
		table.setIntercellSpacing(new Dimension(10,0));
		
		add(header, BorderLayout.NORTH);
		add(table, BorderLayout.CENTER);
	}
}
