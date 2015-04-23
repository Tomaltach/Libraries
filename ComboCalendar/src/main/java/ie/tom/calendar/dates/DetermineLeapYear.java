package ie.tom.calendar.dates;

public class DetermineLeapYear {
	
	public static boolean checkYear(int year) {		      
		//if year is divisible by 4, it is a leap year
		if((year % 400 == 0) || ((year % 4 == 0) && (year % 100 != 0))) {
			return true;
		} else {
			return false;
		}
	}
}