package ie.tom.calendar;

import ie.tom.calendar.dates.DetermineLeapYear;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import org.joda.time.DateTime;

import net.java.dev.designgridlayout.DesignGridLayout;

public class ComboCalendar implements Calendar {
	private JComboBox<?> day;
	private JComboBox<?> month;
	private JComboBox<?> year;
	private Dimension size = new Dimension(20,25);
	private float font = 12;
	private static final String[] DAYS = {
		"01", "02", "03", "04", "05", "06", "07", "08", "09", "10",
		"11", "12", "13", "14", "15", "16", "17", "18", "19", "20",
		"21", "22", "23", "24", "25", "26", "27", "28", "29", "30",
		"30"
	};
	private static final String[] MONTHS = {
		"January", "Feburary", "March", "April",
		"May", "June", "July", "August",
		"September", "October", "November", "December"
	};
	private String[] years = {
		"2013", "2014", "2015", "2016"
	};
	private boolean checkYears = false;
	
	public ComboCalendar() {
		dayCombo();
		monthCombo();
		yearCombo();
		setDate();
	}
	public ComboCalendar(Dimension size) {
		this.size = size;
		
		dayCombo();
		monthCombo();
		yearCombo();
		setDate();
	}
	public ComboCalendar(float font) {
		this.font = font;
		
		dayCombo();
		monthCombo();
		yearCombo();
		setDate();
	}
	public ComboCalendar(String[] years) {
		this.years = years;
		checkYears = true;
		
		dayCombo();
		monthCombo();
		yearCombo();
		setDate();
	}
	public ComboCalendar(Dimension size, float font) {
		this.size = size;
		this.font = font;
		
		dayCombo();
		monthCombo();
		yearCombo();
		setDate();
	}
	public ComboCalendar(Dimension size, String[] years) {
		this.size = size;
		this.years = years;
		checkYears = true;
		
		dayCombo();
		monthCombo();
		yearCombo();
		setDate();
	}	
	public ComboCalendar(float font, String[] years) {
		this.font = font;
		this.years = years;
		checkYears = true;
		
		dayCombo();
		monthCombo();
		yearCombo();
		setDate();
	}
	public ComboCalendar(Dimension size, float font, String[] years) {
		this.size = size;
		this.font = font;
		this.years = years;
		checkYears = true;
		
		dayCombo();
		monthCombo();
		yearCombo();
		setDate();
	}
	
	public JPanel getCalendar() {
		JPanel panel = new JPanel();
		DesignGridLayout layout = new DesignGridLayout(panel);
		layout.row().grid().add(day).grid().add(month).grid().add(year);
		
		return panel;
	}
	
	public JComboBox<?> getDay() {
		return day;
	}
	public JComboBox<?> getMonth() {
		return month;
	}
	public JComboBox<?> getYear() {
		return year;
	}
	
	public void setSize(Dimension size) {
		this.size = size;
	}
	public Dimension getSize() {
		return size;
	}
	
	public float getFont() {
		return font;
	}
	
	public void setYears(String[] years) {
		this.years = years;
		checkYears = true;
		setDate();
	}

	public String getDate() {
		return day.getSelectedItem().toString() + " " +
			month.getSelectedItem().toString() + " " +
			year.getSelectedItem().toString();
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void dayCombo() {
		day = new JComboBox<Object>();
		day.setPreferredSize(size);
		day.setFont(day.getFont().deriveFont(font));
		day.setModel(new DefaultComboBoxModel(DAYS));
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void monthCombo() {
		month = new JComboBox<Object>();
		if(font <= 25) {
			month.setMaximumRowCount(12);
		}
		month.setPreferredSize(size);
		month.setFont(month.getFont().deriveFont(font));
		month.setModel(new DefaultComboBoxModel(MONTHS));
		month.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				validate();
			}			
		});
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void yearCombo() {
		year = new JComboBox<Object>();
		year.setPreferredSize(size);
		year.setFont(year.getFont().deriveFont(font));
		year.setModel(new DefaultComboBoxModel(years));
		year.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				validate();
			}			
		});
	}
	private void validate() {
		int cy = 2015;
		try {
			cy = Integer.parseInt(year.getSelectedItem().toString());
		} catch(NumberFormatException e) {
			e.printStackTrace();
		}
		String cm = month.getSelectedItem().toString();
		if(DetermineLeapYear.checkYear(cy) == true) {			
			if(cm.equals("Feburary")) {
				fillDays(29);
			} else if(cm.equals("April") || cm.equals("June") || cm.equals("September") || cm.equals("November")) {
				fillDays(30);
			} else {
				fillDays(31);
			}
		} else {
			if(cm.equals("Feburary")) {
				fillDays(28);
			} else if(cm.equals("April") || cm.equals("June") || cm.equals("September") || cm.equals("November")) {
				fillDays(30);
			} else {
				fillDays(31);
			}
		}
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void fillDays(int max) {
		String[] temp = new String[max];
		for(int i=0; i<max; i++) {
			temp[i] = DAYS[i];
		}
		day.setModel(new DefaultComboBoxModel(temp));
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void setDate() {
		DateTime date = new DateTime();
		
		int d = date.getDayOfMonth();
		int m = date.getMonthOfYear();
		int y = date.getYear();
		
		if(checkYears == false) {
			String[] temp = new String[4];
			temp[0] = "" + (y-1);
			temp[1] = "" + (y);
			temp[2] = "" + (y+1);
			temp[3] = "" + (y+2);

			year.setModel(new DefaultComboBoxModel(temp));
			year.setSelectedIndex(1);
		} else {
			year.setModel(new DefaultComboBoxModel(years));
			String sy = "" + y;
			int i = 0;
			while(!year.getItemAt(i).equals(sy)) {
				i++;
			}
			year.setSelectedIndex(i);
		}
		month.setSelectedIndex(m-1);
		day.setSelectedIndex(d-1);
	}
}