package app;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class LeftPanel extends JPanel {

	JLabel inNumLabel;
	JLabel inRowLabel;
	JLabel inColLabel;
	JTextField inNumber;
	JSpinner rowNum;
	JSpinner colNum;

	DataTable dataTable;

	JLabel operationLabel;
	JList<OperationListItem> operationList;
	JButton calculateButton;
	TextPanel textPanel;
	
	private ICommandListener listener;	

	public LeftPanel() {

		inNumLabel = new JLabel("Podaj liczbę: ");
		inRowLabel = new JLabel("Nr. wiersza: ");
		inColLabel = new JLabel("Nr. kolumny: ");
		operationLabel = new JLabel("Wybierz operację:  ");

		inNumber = new JTextField();
		rowNum = new JSpinner();
		colNum = new JSpinner();
		dataTable = new DataTable();
		
		inNumber.setText("0");
		inNumber.setHorizontalAlignment(JTextField.RIGHT);
		calculateButton = new JButton("Oblicz");
		
		operationList = new JList<OperationListItem>();
		DefaultListModel<OperationListItem> operationListModel = new DefaultListModel<OperationListItem>();
		operationListModel.addElement(new OperationListItem(Command.SUM, "Suma"));
		operationListModel.addElement(new OperationListItem(Command.AVG, "Średnia"));
		operationListModel.addElement(new OperationListItem(Command.MIN, "Min"));
		operationListModel.addElement(new OperationListItem(Command.MAX, "Max"));
		operationList.setModel(operationListModel);
		operationList.setBorder(BorderFactory.createEtchedBorder());
		operationList.setSelectedIndex(0);
		

		Dimension dim = new Dimension(50, 20);

		inNumber.setPreferredSize(dim);
		rowNum.setPreferredSize(dim);
		colNum.setPreferredSize(dim);
		rowNum.setValue(1);
		colNum.setValue(1);
		

		SetUpLayout();
		
		
		calculateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Command command = operationList.getSelectedValue().command;
				if(listener != null)
				{
					listener.eventOccured(command);
				}
			}
		});
	}
	
	public int getRowNum()
	{
		return (int)rowNum.getValue();
	}
	
	public int getColNum()
	{
		return (int)colNum.getValue();
	}
	
	public float getTypedNum() throws Exception
	{
		try {
			return Float.parseFloat(inNumber.getText());
		}
		catch(Exception ex)
		{
			throw new Exception("Wprowadzona wartość nie jest liczbą! \n");
		}
	}
	
	public void setListener(ICommandListener listener)
	{
		this.listener = listener;
	}

	private void SetUpLayout() {
		setLayout(new GridBagLayout());
		setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

		Dimension dim = getPreferredSize();
		dim.width = 480;
		setPreferredSize(dim);
		setSize(dim);

		GridBagConstraints gc = new GridBagConstraints();
		gc.fill = GridBagConstraints.NONE;

		/* FIRST ROW------------------------------------------ */
		gc.weightx = 1;
		gc.weighty = 0.2;
		gc.gridy = 0;

		/* Num Label */
		gc.gridx = 0;
		gc.anchor = GridBagConstraints.LINE_END;
		//top,left,bottom,right
		gc.insets = new Insets(0, 0, 0, 5);
		add(inNumLabel, gc);

		/* Num input */
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(inNumber, gc);

		/* Row Label */
		gc.gridx = 2;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(inRowLabel, gc);

		/* Row input */
		gc.gridx = 3;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(rowNum, gc);

		/* Col Label */
		gc.gridx = 4;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(inColLabel, gc);

		/* Col input */
		gc.gridx = 5;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 5);
		add(colNum, gc);

		/* SECOND ROW------------------------------------------ */		
		/* JTable */
		gc.weightx = 1;
		gc.weighty = 1;
		gc.gridy = 1;
		gc.gridx = 0;
		gc.gridwidth = 6;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(12, 4, 0, 6);
		
		add(dataTable, gc);

		/* THIRD ROW ------------------------------------------ */
		gc.weightx = 1;
		gc.weighty = 0.1;
		gc.gridy = 2;
		gc.gridx = 0;
		gc.gridwidth = 1;

		
		/* Col Operation Label */
		gc.gridx = 0;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);

		add(operationLabel, gc);
		
		/* Col Operation List */
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);

		add(operationList, gc);
		
		/* Col Calculate Button */
		gc.gridx = 2;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(5, 0, 0, 0);

		add(calculateButton, gc);
		
		
	}
}
