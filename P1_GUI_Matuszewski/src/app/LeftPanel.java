package app;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import org.freixas.jcalendar.JCalendarCombo;

import com.l2fprod.common.swing.JButtonBar;
import com.l2fprod.common.swing.plaf.ButtonBarUI;
import com.l2fprod.common.swing.plaf.blue.BlueishButtonBarUI;


public class LeftPanel extends JPanel {

	JLabel inNumLabel;
	JLabel inRowLabel;
	JLabel inColLabel;
	JTextField inNumber;
	JSpinner rowNum;
	JSpinner colNum;
	
	JButton putNumberButton;

	JTabbedPane tabs;
	JButtonBar toolbar;
	
	
	ButtonBarPanel buttonBarPanel;
	TextPanel textPanel;
	
	private ICommandListener listener;	

	public LeftPanel() {

		inNumLabel = new JLabel("Podaj liczbę: ");
		inRowLabel = new JLabel("Nr. wiersza: ");
		inColLabel = new JLabel("Nr. kolumny: ");
		
		inNumber = new JTextField();
		inNumber.setText("0");
		rowNum = new JSpinner();
		colNum = new JSpinner();
		
		tabs = new JTabbedPane();


		toolbar = new JButtonBar(JButtonBar.VERTICAL);
	    toolbar.setUI(new BlueishButtonBarUI());
	    buttonBarPanel = new ButtonBarPanel(toolbar);
		
	    inNumber.setHorizontalAlignment(JTextField.RIGHT);
		
	    putNumberButton = new JButton("Dodaj");
		Dimension buttonSize = new Dimension(70,25);
		putNumberButton.setPreferredSize(buttonSize);
		
		putNumberButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(listener != null)
				{
					listener.eventOccured(Command.ADD_VALUE);
				}
			}
		});
		

		Dimension dim = new Dimension(40, 25);

		inNumber.setPreferredSize(dim);
		rowNum.setPreferredSize(dim);
		colNum.setPreferredSize(dim);
		rowNum.setValue(1);
		colNum.setValue(1);
		
		SetUpLayout();
		
		
		
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
		dim.width = 580;
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
		gc.insets = new Insets(0, 0, 0, 0);
		add(colNum, gc);
		
		/* put number button */
		gc.gridx = 6;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(putNumberButton, gc);

		/* SECOND ROW------------------------------------------ */		
		/* JTable */
		gc.weightx = 1;
		gc.weighty = 1;
		gc.gridy = 1;
		gc.gridx = 0;
		gc.gridwidth = 7;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(12, 4, 0, 6);
		
		//add(tabs, gc);
		add(buttonBarPanel,gc);

		/* Third------------------------------------------ /		
		/* JTable /
		gc.weightx = 1;
		gc.weighty = 0.1;
		gc.gridy = 2;
		
		//gc.insets = new Insets(3,10,0,0);
		gc.insets = new Insets(0,0,0,5);

		gc.anchor = GridBagConstraints.LINE_END;
		gc.gridx = 1;
		gc.gridwidth = 1;
		gc.weightx = 1;
		
		add(calendarLabel, gc);
		
		
		gc.anchor = GridBagConstraints.LINE_START;
		gc.gridx = 2;
		gc.gridwidth = 1;

		
		add(calendarCombo,gc);
		
		*/
		


		
		
	}
}



