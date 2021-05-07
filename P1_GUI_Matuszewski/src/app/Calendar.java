package app;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import org.freixas.jcalendar.JCalendar;
import org.freixas.jcalendar.JCalendarCombo;

public class Calendar  extends JPanel{
	
	JCalendarCombo calendarCombo;
	JCalendar calendar;
	
	public Calendar()
	{
		setLayout(new BorderLayout());
		
		calendar = new JCalendar();
		calendarCombo = new JCalendarCombo();
		
		add(calendar, BorderLayout.NORTH);
		add(calendarCombo, BorderLayout.CENTER);
	}
	
}
