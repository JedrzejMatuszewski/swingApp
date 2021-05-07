package app;

import com.l2fprod.common.swing.JButtonBar;
import com.l2fprod.common.swing.plaf.blue.BlueishButtonBarUI;
import com.l2fprod.common.swing.plaf.misc.IconPackagerButtonBarUI;
import com.l2fprod.common.util.ResourceManager;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.*;

import com.l2fprod.common.demo.ButtonBarMain;

public class ButtonBarPanel extends JPanel {

	DataTable dataTable;
	Chart chart;
	
	private Component currentComponent;

	public ButtonBarPanel(JButtonBar toolbar) {
		setLayout(new BorderLayout());

		add("West", toolbar);

		ButtonGroup group = new ButtonGroup();
		
		dataTable = new DataTable();
		chart = new Chart();
		

		addButton("Tabela", "table.png",
				dataTable,toolbar, group);

		addButton("Wykres", "chart.png",
				chart, toolbar, group);

	}

	private JPanel makePanel(String title) {
		JPanel panel = new JPanel(new BorderLayout());
		JLabel top = new JLabel(title);
		top.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
		top.setFont(top.getFont().deriveFont(Font.BOLD));
		top.setOpaque(true);
		top.setBackground(panel.getBackground().brighter());
		panel.add("North", top);
		panel.setPreferredSize(new Dimension(40, 40));
		panel.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
		return panel;
	}

	private void show(Component component) {
		if (currentComponent != null) {
			remove(currentComponent);
		}
		add("Center", currentComponent = component);
		revalidate();
		repaint();
	}

	private void addButton(String title, String iconUrl, final Component component, JButtonBar bar, ButtonGroup group) {
		String iconSource = getSrc(iconUrl);
		Action action = new AbstractAction(title, new ImageIcon(iconSource)) {

			public void actionPerformed(ActionEvent e) {
				show(component);
			}
		};

		JToggleButton button = new JToggleButton(action);
		bar.add(button);

		group.add(button);

		if (group.getSelection() == null) {
			button.setSelected(true);
			show(component);
		}
	}
	
	private String getSrc(String file)
	{
		String path = System.getProperty("user.dir");
		path = path + "\\icons\\" + file;
		return path;
	}

}
