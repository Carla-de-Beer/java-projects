// Carla de Beer
// Sparse matrix as calendar
// Date created: 03/03/2014
// Date revised: 25/06/2014

/**
 * Sparse tables can be implemented as a set of linked lists. A sparse table
 * need not be only two dimensional, they can be extended to any dimension.
 * 
 * This project uses a sparse table to store diary appointments for a year. The
 * Calendar class represents the sparse table. The sparse table allows for the
 * insertion of Appointment objects, constructed in the table with the given
 * parameters. If there already is an Appointment object at a particular date,
 * then the object is linked in to the list (by using the 'back' reference)
 * which will create a list of appointments for that particular day.
 */

public class Diary {

	public static void main(String[] args) {

		Calendar myCalendar = new Calendar();
		Calendar empty = new Calendar();

		System.out.println("-----------------------------------------");

		myCalendar.addAppointment("sep", 7, "lunch", 1);
		myCalendar.addAppointment("may", 7, "lunch", 1);
		myCalendar.addAppointment("feb", 14, "lunch", 1);
		myCalendar.addAppointment("may", 20, "dentist", 2);
		myCalendar.addAppointment("may", 17, "dinner", 1);
		myCalendar.addAppointment("jun", 17, "car", 1);
		myCalendar.addAppointment("nov", 17, "lunch", 1);
		myCalendar.addAppointment("may", 7, "lunch", 1);
		myCalendar.addAppointment("nov", 17, "accountant", 1);
		myCalendar.addAppointment("nov", 17, "lunch", 1);
		myCalendar.addAppointment("jan", 1, "dinner", 1);
		myCalendar.addAppointment("dec", 30, "party", 1);

		System.out.println("GET APPOINTMENTS (1)");
		System.out.println();

		System.out.println("Number of appointments in myCalendar: "
				+ myCalendar.getCount());

		Appointment get1 = empty.getMonthAppointment("sep");
		System.out.println("empty.getMonthAppointment(sep): " + '\n' + get1);
		System.out.println();

		Appointment get2 = empty.getDayAppointment(17);
		System.out.println("empty.getDayAppointment(17): " + '\n' + get2);
		System.out.println();

		Appointment get3 = empty.getAppointment("nov", 17);
		System.out.println("empty.getAppointment(nov, 17): " + '\n' + get3);
		System.out.println();

		Appointment get4 = myCalendar.getMonthAppointment("apr");
		System.out.println("myCalendar.getMonthAppointment(apr): " + '\n'
				+ get4);
		System.out.println();

		Appointment get5 = myCalendar.getMonthAppointment("dec");
		System.out.println("myCalendar.getMonthAppointment(dec): " + '\n'
				+ get5);

		Appointment get6 = myCalendar.getMonthAppointment("sep");
		System.out.println("myCalendar.getMonthAppointment(sep): " + '\n'
				+ get6);

		Appointment get7 = myCalendar.getDayAppointment(17);
		System.out.println("myCalendar.getDayAppointment(17): " + '\n' + get7);

		Appointment get8 = myCalendar.getAppointment("may", 20);
		System.out
				.println("myCalendar.getAppointment(may, 20): " + '\n' + get8);

		Appointment get9 = myCalendar.getAppointment("nov", 17);
		System.out
				.println("myCalendar.getAppointment(nov, 17): " + '\n' + get9);

		Appointment get10 = myCalendar.getAppointment("nov", 177);
		System.out.println("myCalendar.getAppointment(nov, 177): " + '\n'
				+ get10);

		System.out.println();
		System.out.println("Number of appointments in myCalendar: "
				+ myCalendar.getCount());

		System.out.println();

		System.out.println("-----------------------------------------");
		System.out.println("DELETE APPOINTMENTS (2)");
		System.out.println();

		System.out.println("Number of months in myCalendar: "
				+ myCalendar.getCount());
		System.out.println();

		System.out.println("Delete from empty: ");
		System.out.println("empty.getMonthAppointment(sep): " + '\n' + get9);

		System.out.println("Delete from myCalendar: ");
		myCalendar.deleteAppointment("jan", 1, "dinner");

		Appointment get11 = myCalendar.getAppointment("jan", 1);
		System.out
				.println("myCalendar.getAppointment(jan, 1): " + '\n' + get11);
		System.out.println();

		System.out.println("Delete from myCalendar: ");
		myCalendar.deleteAppointment("may", 7);

		Appointment get12 = myCalendar.getAppointment("may", 7);
		System.out
				.println("myCalendar.getAppointment(may, 7): " + '\n' + get12);

		System.out.println();
		System.out.println("Delete from myCalendar: ");
		myCalendar.deleteAppointment("may", 20);

		Appointment get13 = myCalendar.getAppointment("may", 20);
		System.out.println("myCalendar.getAppointment(may, 20): " + '\n'
				+ get13);

		myCalendar.deleteAppointment("nov", 17, "accountant");

		System.out.println("Delete from myCalendar: ");
		myCalendar.deleteAppointment("nov", 17);

		Appointment get14 = myCalendar.getAppointment("nov", 17);
		System.out.println("myCalendar.getAppointment(nov, 17): " + '\n'
				+ get14);

		System.out.println("Number of appointments in myCalendar = "
				+ myCalendar.getCount());

		System.out.println();
		System.out.println("-----------------------------------------");
		System.out.println("CHANGE APPOINTMENTS (3)");
		System.out.println();

		myCalendar.changeAppointmentDuration("sep", 7, "lunch", 3);
		myCalendar.changeAppointmentDuration("nov", 17, "lunch", 3);
		myCalendar.changeAppointmentDuration("may", 17, "dinner", 3);

		Appointment get15 = myCalendar.getMonthAppointment("sep");
		System.out.println("myCalendar.getAppointment(sep): " + '\n' + get15);

		Appointment get16 = myCalendar.getAppointment("nov", 17);
		System.out.println("myCalendar.getAppointment(nov, 17): " + '\n'
				+ get16);

		Appointment get17 = myCalendar.getAppointment("may", 17);
		System.out.println("myCalendar.getAppointment(may, 17): " + '\n'
				+ get17);

		myCalendar.changeAppointmentDescription("sep", 7, "lunch",
				"new description");
		myCalendar.changeAppointmentDescription("nov", 17, "lunch",
				"new description");
		myCalendar.changeAppointmentDescription("may", 17, "dinner",
				"new description");

		Appointment get18 = myCalendar.getMonthAppointment("sep");
		System.out.println("myCalendar.getAppointment(sep): " + '\n' + get18);

		Appointment get19 = myCalendar.getAppointment("nov", 17);
		System.out.println("myCalendar.getAppointment(nov, 17): " + '\n'
				+ get19);

		Appointment get20 = myCalendar.getAppointment("may", 17);
		System.out.println("myCalendar.getAppointment(may, 17): " + '\n'
				+ get20);

		myCalendar.changeAppointmentDate("dec", 30, "oct", 3);

		Appointment get21 = myCalendar.getAppointment("oct", 3);
		System.out
				.println("myCalendar.getAppointment(oct, 3): " + '\n' + get21);

		Appointment get22 = myCalendar.getAppointment("dec", 14);
		System.out.println("myCalendar.getAppointment(dec, 30): " + '\n'
				+ get22);

		System.out.println();
		System.out.println("-----------------------------------------");
		System.out.println("CLEAR CALENDER (4)");
		System.out.println();

		System.out.println("Clear year from an empty calendar:");
		empty.clearMyYear();

		System.out.println();

		System.out.println("Clear the first appointment for September :");

		Appointment get23 = myCalendar.getMonthAppointment("sep");
		System.out.println("myCalendar.getAppointment(sep): " + '\n' + get23);

		myCalendar.clearMyMonth("sep");
		Appointment get24 = myCalendar.getMonthAppointment("sep");
		System.out.println("myCalendar.getAppointment(sep): " + '\n' + get24);
		System.out.println();

		System.out.println("Clear the month 'May':");

		myCalendar.clearMyMonth("may");

		Appointment get25 = myCalendar.getMonthAppointment("may");
		System.out.println("myCalendar.getAppointment(may): " + '\n' + get25);

		myCalendar.clearMyDays(30);
		myCalendar.clearMyDays(17);
		myCalendar.clearMyDays(14);

		Appointment get26 = myCalendar.getMonthAppointment("dec");
		System.out.println("myCalendar.getAppointment(dec): " + '\n' + get26);

		Appointment get27 = myCalendar.getMonthAppointment("may");
		System.out.println("myCalendar.getAppointment(may): " + '\n' + get27);

		Appointment get28 = myCalendar.getMonthAppointment("feb");
		System.out.println("myCalendar.getAppointment(feb): " + '\n' + get28);
		System.out.println();

		System.out.println("Number of appointments in myCalendar = "
				+ myCalendar.getCount());

		myCalendar.clearMyYear();

		System.out.println();

		System.out.println("Number of appointments in myCalendar = "
				+ myCalendar.getCount());

	}
}
