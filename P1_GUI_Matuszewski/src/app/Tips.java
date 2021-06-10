package app;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

import com.l2fprod.common.swing.JTipOfTheDay;
import com.l2fprod.common.swing.tips.DefaultTip;
import com.l2fprod.common.swing.tips.DefaultTipModel;

/**
 * Okno z porada dnia
 * @author Jedrzej Matuszewski
 * @version 1.0
 */
public class Tips extends JDialog {
	/**
	 * Obiekt klasy JTipOfTheDay
	 */
	JTipOfTheDay tipOfTheDay;
	/**
	 * Model danych
	 */
	DefaultTipModel tips;

	/**
	 * Przycisk przejscia do nastepnej porady
	 */
	JButton nextBtn;
	/**
	 * Przycisk przejscia do poprzedniej porady
	 */
	JButton previousBtn;
	/**
	 * Przycisk zamykajacy okno
	 */
	JButton closeBtn;

	/**
	 * Stopka komponentu
	 */
	JPanel footer;

	/**
	 * Konstruktor
	 */
	public Tips() {
		tips = new DefaultTipModel();
		tips.add(new DefaultTip("Tip1", ""
				+ "Lorem ipsum dolor sit amet, consectetur adipiscing elit."
				+ " Nullam tempus pretium enim nec bibendum. Maecenas sodales"
				+ " eu velit in egestas. In rutrum sit amet leo vitae tristique."
				+ " Nunc tristique ipsum a tempor convallis. Nullam id porta"
				+ " elit. Aenean scelerisque nisi sed velit ultricies sodales."
				+ " Integer eu eros in felis cursus vulputate."));
		
		tips.add(new DefaultTip("Tip2", "Quisque non odio quis lorem dignissim"
				+ " laoreet. Donec pulvinar elit et aliquam interdum. Aliquam"
				+ " erat volutpat. Duis tristique at nisl ut accumsan. Sed"
				+ " quis libero aliquam, ullamcorper mauris at, ultricies"
				+ " magna. Proin dignissim felis a turpis dignissim tempus."
				+ " Duis odio tellus, eleifend luctus justo quis, iaculis"
				+ " rutrum dolor. Suspendisse potenti. Maecenas vel luctus"
				+ " urna. Praesent ac ipsum pellentesque, elementum sem et,"
				+ " consequat nisl. Fusce eleifend dictum lacinia. Nulla"
				+ " elementum volutpat scelerisque. Fusce eget sollicitudin"
				+ " diam, id porttitor ligula."));
		
		tips.add(new DefaultTip("Tip3", "Phasellus laoreet justo vitae lacus"
				+ " porttitor, id mollis lectus pharetra. Mauris viverra mauris"
				+ " mattis tempus varius. Integer quis pulvinar massa, sit"
				+ " amet bibendum nunc. Vivamus in nisi euismod, bibendum erat"
				+ " non, porta quam. Pellentesque eget tellus neque. Fusce"
				+ " dapibus purus tempor, dapibus urna eu, accumsan neque."
				+ " Quisque ut pretium enim. Nunc a diam ut lorem tempor"
				+ " ultricies. Vivamus tellus urna, egestas id magna ut,"
				+ " sodales viverra urna. Aliquam felis urna, viverra vitae"
				+ " rutrum non, consequat in metus. Suspendisse eget nisi"
				+ " pulvinar, molestie ipsum at, viverra eros. Curabitur"
				+ " consequat elementum nisi, et volutpat purus faucibus in."
				+ " Suspendisse pharetra commodo porttitor. Ut pellentesque"
				+ " nisi vitae purus dapibus molestie. Donec dictum laoreet"
				+ " tempor."));
		
		tips.add(new DefaultTip("Tip4", "Suspendisse quis feugiat ex. Duis"
				+ " aliquet auctor lobortis. Aliquam ultrices urna id urna"
				+ " elementum, dignissim fringilla orci tempus. Orci varius"
				+ " natoque penatibus et magnis dis parturient montes,"
				+ " nascetur ridiculus mus. In sed pretium augue, nec"
				+ " ullamcorper leo. Suspendisse blandit iaculis consectetur."
				+ " Etiam id sem vel nisi ullamcorper gravida. Aliquam in"
				+ " urna ut purus dignissim vulputate quis in velit. Nam"
				+ " in sem condimentum, tempus velit et, lobortis eros."
				+ " Praesent ullamcorper consectetur quam sit amet ullamcorper."
				+ " Sed ornare augue vel risus consequat vehicula. "));
		
		tipOfTheDay = new JTipOfTheDay(tips);

		Border outer = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		Border inner = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		tipOfTheDay.setBorder(BorderFactory.createCompoundBorder(outer, inner));

		footer = new JPanel();
		footer.setLayout(new FlowLayout(FlowLayout.RIGHT));

		previousBtn = new JButton("< Poprzedni");
		nextBtn = new JButton("Nastepny >");
		closeBtn = new JButton("Zamknij");

		footer.add(previousBtn);
		footer.add(nextBtn);
		footer.add(closeBtn);

		setLayout(new BorderLayout());
		setTitle("Porada dnia - P1_GUI Matuszewski");
		setSize(new Dimension(440, 265));
		add(tipOfTheDay, BorderLayout.CENTER);
		add(footer, BorderLayout.SOUTH);

		previousBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tipOfTheDay.previousTip();
			}
		});

		nextBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tipOfTheDay.nextTip();
			}
		});

		closeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});

		arrangeWindow();
	}

	/**
	 * Metoda rozmieszczajaca okno
	 */
	private void arrangeWindow() {
		Dimension dialogSize = getSize();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		if (dialogSize.height > screenSize.height)
			dialogSize.height = screenSize.height;
		if (dialogSize.width > screenSize.width)
			dialogSize.height = screenSize.width;

		setLocation((screenSize.width - dialogSize.width) / 2, (screenSize.height - dialogSize.height) / 2);
	}

}
