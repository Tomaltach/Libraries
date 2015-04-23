package ie.tom.calendar.test;

import java.awt.Dimension;
import java.awt.GridLayout;

import ie.tom.calendar.Calendar;
import ie.tom.calendar.ComboCalendar;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class TestCalendar {

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Calendar cal;
		JPanel panel = new JPanel(new GridLayout(0,1));

		cal = new ComboCalendar();
		panel.add(cal.getCalendar());

		cal = new ComboCalendar(new Dimension(50,50));
		panel.add(cal.getCalendar());

		cal = new ComboCalendar(new Dimension(50,30), 20);
		panel.add(cal.getCalendar());

		String[] y = {"2015", "2016"};
		cal = new ComboCalendar(new Dimension(50,30), y);
		panel.add(cal.getCalendar());

		cal = new ComboCalendar(new Dimension(50,30), 30, y);
		panel.add(cal.getCalendar());
		
		cal = new ComboCalendar();
		cal.setYears(y);
		frame.repaint();
		panel.add(cal.getCalendar());
		
		System.out.println(cal.getDate());
		
		frame.add(panel);
		
		frame.pack();
		frame.setSize(400, 600);
		frame.setVisible(true);
	}
}
