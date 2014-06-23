// Carla de Beer
// Sparse matrix as calendar
// Date created: 03/03/2014
// Date revised: 25/06/2014

public class Appointment {

	private String description;
	private int duration;

	private int monthValue;
	private int dayValue;

	/**
	 * The next appointment (back) of this appointment on the same date
	 */
	public Appointment back;
	/**
	 * The next appointment (right) of this appointment in the same week.
	 */
	public Appointment right;
	/**
	 * The next appointment (down) of this appointment in the same month.
	 */
	public Appointment down;

	/**
	 * Default constructor
	 */
	public Appointment() {
		description = "";
		duration = 0;
		back = null;
		right = null;
		down = null;
		monthValue = 0;
		dayValue = 0;
	}

	public Appointment(int d, int m, String descr, int dur) {
		dayValue = d;
		monthValue = m;
		description = descr;
		duration = dur;
		right = null;
		down = null;
		back = null;
	}

	/**
	 * Parameterised constructor
	 * 
	 * @param desc
	 * @param h
	 * @param mm
	 * @param dd
	 */
	public Appointment(String desc, int h, int mm, int dd) {
		description = desc;
		duration = h;
		back = null;
		right = null;
		down = null;
		monthValue = mm;
		dayValue = dd;
	}

	/**
	 * Method to set the description of an appointment
	 * 
	 * @param desc
	 */
	public void setDescription(String desc) {
		description = desc;
	}

	/**
	 * Method to set the duration of an appointment
	 * 
	 * @param dur
	 */
	public void setDuration(int dur) {
		duration = dur;
	}

	/**
	 * Getter to return an appointment description
	 * 
	 * @return The string value of the appointment description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Getter to return an appointment duration
	 * 
	 * @return The integer value of an appointment duration
	 */
	public int getDuration() {
		return duration;
	}

	/**
	 * Getter to return the month value in the month array
	 * 
	 * @return Index of month array corresponding to month value
	 */
	public final int getMonthValue() {
		return monthValue;
	}

	/**
	 * Getter to return the day value in the day array
	 * 
	 * @return
	 */
	public final int getDayValue() {
		return dayValue;
	}

	@Override
	public String toString() {
		String result = "";
		result = result.concat("< ");
		result = result.concat("Day: ");
		String strI = Integer.toString(this.dayValue);
		result = result.concat(strI);
		result = result.concat(" | ");
		result = result.concat("Month: ");
		String str2 = Integer.toString(this.monthValue);
		result = result.concat(str2);
		result = result.concat(" | ");
		result = result.concat("Description: ");
		result = result.concat(this.getDescription());
		result = result.concat(" | ");
		result = result.concat("Duration: ");
		String str3 = Integer.toString(this.getDuration());
		result = result.concat(str3);
		result = result.concat(" >");
		result = result.concat("\n");

		return result;
	}

}
