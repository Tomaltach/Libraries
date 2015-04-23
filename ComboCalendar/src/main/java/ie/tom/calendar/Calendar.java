package ie.tom.calendar;

import java.awt.Dimension;

import javax.swing.JComboBox;
import javax.swing.JPanel;

public interface Calendar {
	JPanel getCalendar();
	JComboBox<?> getDay();
	JComboBox<?> getMonth();
	JComboBox<?> getYear();
	void setSize(Dimension size);
	Dimension getSize();
	float getFont();
	void setYears(String[] years);
	String getDate();
}