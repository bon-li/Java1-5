/*
 BonitaLi_Assign05.java is written by B. Li on Dec 10, 2021
 This is a simple code that asks user for a date, tells you the week day, asks user for a second date,
 and tells user what the week day is and calculates the number of days between the two dates.
 */
class Date {
	private int day;
	private int month;
	private int year;
	boolean inputValid = true;
	boolean leapYear;
	String weekDay;
	private int monthDays[] = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
	
	public Date() {//default constructor
		this.day = 1;
		this.month = 1;
		this.year = 1111;
	}
	
	public Date(int day, int month, int year) {
		setDate(day, month, year);
	}
	
	public int getDay() {
		return this.day;
	}
	
	public int getMonth() {
		return this.month;
	}
	
	public int getYear() {
		return this.year;
	}
	
	public boolean getInputValid() {
		return this.inputValid;
	}
	
	public boolean getLeapYear() {
		return this.leapYear;
	}
	
	public void setDay(int day) {
		this.day = day;	
	}
	
	public void setMonth(int month) {
		this.month = month;
	}
	
	public void setYear(int year) {
		this.year = year;
	}
	
	public void setInputValid(boolean inputValid) {
		this.inputValid = inputValid;
	}
	
	public void setLeapYear(boolean leapYear) {
		this.leapYear = leapYear;
	}
	
	public void setDate(int day, int month, int year) { //method checks validates inputed date
		inputValid = true;
		try {
			if (day < 1 || day > 31) {
				inputValid = false;
				throw new IllegalArgumentException();
			} else {
				this.day=day;
			} //else block end
		} catch (IllegalArgumentException e) {
			System.out.println("Please choose a day between 1 to 31 as " + day + " is outside of the range.");
		} //catch block end
		try {
			if (month < 1 || month > 12) {
				inputValid = false;
				throw new IllegalArgumentException();
			} else {
				this.month=month;
			} //else block end
		} catch (IllegalArgumentException e) {
			System.out.println("Please choose a month between 1 to 12 as " + month + " is outside of the range.");
		} //catch block end
		try {
			if (year < 1) {
				inputValid = false;
				throw new IllegalArgumentException();
			} else {
				this.year=year;
			} //else block end
		} catch (IllegalArgumentException e) {
			System.out.println("Please choose a year that is a positive integer.");
		} //catch block end
		if (day 32) {
			try {
				if (month == 4 && day > 30 || month == 6 && day > 30 || month == 9 && day > 30 || month == 11 && day > 30) {
					inputValid = false;
					throw new IllegalArgumentException();
				} // if block end
			} //try block end
			catch (IllegalArgumentException e){
				System.out.println("Please choose a day between 1 to 30 as the inputted month doesn't have a " + day + ".");
			} //catch block end
		}
		try {
			if (year % 4 == 0 && year % 100 != 0 || year %400 == 0) {//if year is a leap year 
				leapYear = true;
			} //if block end
			else {
				leapYear = false;
			} //else block end
			if (leapYear == true && month == 2 && day > 29) {
				inputValid = false;
				throw new IllegalArgumentException();	
			} //if block end
			if (leapYear == false && month == 2 && day > 28)  {
				inputValid = false;
				throw new IllegalArgumentException();	
			} //if block end
		} //try block end
		catch (IllegalArgumentException e){
			if (leapYear == true) {
				throw new IllegalArgumentException(day + " is outside the valid range as February on a leap year has 29 days.");	
			} //if block end
			if (leapYear == false) {
				throw new IllegalArgumentException(day + " is outside the valid range as February on a non-leap year has 28 days.");	
			} //if block end
		} //catch block end
	}//setDate method end
	
	public String weekDay () { //method calculates weekday
		int centuryCode, monthCode, yearCode, process;
		centuryCode = (year / 100) % 4; //century code calculation
		if (centuryCode == 0){
			centuryCode = 6;
		} else if (centuryCode == 1) {
			centuryCode = 4;
		} else if (centuryCode == 2) {
			centuryCode = 2;
		} else {
			centuryCode = 0;
		}
		if (month == 1 || month == 10) { //month code for each month
			monthCode = 0;
		} else if (month == 2 || month == 3 || month == 11) {
			monthCode = 3;
		} else if (month == 4 || month == 7) {
			monthCode = 6;
		} else if (month == 5) {
			monthCode = 1;
		} else if (month == 6) {
			 monthCode = 4;
		} else if (month == 8) {
			monthCode = 2;
		} else {
			monthCode = 5;
		}
		if (leapYear == true) {
			if (month == 1 || month == 2) {
				monthCode = monthCode - 1 ;
			}
		}
		yearCode = year % 100; //year code calculation
		yearCode = (yearCode + (yearCode / 4)) % 7;
		process = (centuryCode + yearCode + monthCode + day) % 7;
		switch (process) {
			case 0:
				weekDay = "Sunday";
				break;
			case 1:
				weekDay = "Monday";
				break;
			case 2:
				weekDay = "Tuesday";
				break;
			case 3:
				weekDay = "Wedsday";
				break;
			case 4:
				weekDay = "Thursday";
				break;
			case 5:
				weekDay = "Friday";
				break;
			default:
				weekDay = "Saturday";
		}
		return weekDay;
	}//weekDay method end	
	
	public int countLeapYears(Date date) { // check if the current year needs to be considered
        int years = date.getYear(); 
        if (date.getMonth() <= 2) { // for the count of leap years or not
            years--;
        }
        return years / 4 - years / 100 + years / 400;
    }//countLeapYears method end
	
	public int dateDiff(Date date2) { //calculates difference between dates
		Date date1 = new Date(this.getDay(), this.getMonth(), this.getYear()); // initialize date1
        if (date1.equals(date2)){ //if date1 and date2 same, return 0
        	return 0;
        } //if block ends
		int n1 = date1.getYear() * 365 + date1.getDay();
        for (int i = 0; i < date1.getMonth() - 1; i++) {
            n1 += this.monthDays[i];
        } //for loop ends
        n1 += countLeapYears(date1); // add a day for every leap year
        int n2 = date2.getYear() * 365 + date2.getDay();
        for (int i = 0; i < date2.getMonth() - 1; i++) {
            n2 += this.monthDays[i];
        } //for loop ends
        n2 += countLeapYears(date2);
        return Math.abs(n2 - n1); // return difference between two counts
	}//dateDiff method end
	
	@Override
	public String toString() {
		return String.format("Date: " + getDay() + "/" + getMonth() + "/" + getYear() + " is " + weekDay());
	}//toString method end
	
	@Override
	public boolean equals(Object ob) { //returns false if objects arent equal
		if (ob == null) return false;
		if (!(ob instanceof Date)) return false;
		Date ob2 = (Date) ob;
		return this.toString().equals(ob2.toString());
	}//equals method end
	
	@Override
	public int hashCode() { //returns has of object
		int result = 20;
		result = 31 * result + this.getDay();
		result = 31 * result + this.getMonth();
		result = 31 * result + this.getYear();
		return result;
	}//hashCode method end
}//class ends
