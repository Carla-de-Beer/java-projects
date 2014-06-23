// Carla de Beer
// Sparse matrix as calendar
// Date created: 03/03/2014
// Date revised: 25/06/2014

public class Calendar {

	/**
	 * The NUM_MONTHS array allows for 13 indices so that the index value
	 * directly corresponds to the month value.
	 */
	private static final int NUM_MONTHS = 13;
	/**
	 * The NUM_DAYS array allows for 31 indices so that the index value directly
	 * corresponds to the day value. For this project it is assumed that each
	 * month can hold a maximum of 31 days and there are no check in place to
	 * validate month day inputs.
	 */
	private static final int NUM_DAYS = 31;

	private Appointment[] months;
	private Appointment[] days;

	private Appointment head;
	private Appointment tail;
	private int count;

	public Calendar() {
		months = new Appointment[NUM_MONTHS];
		days = new Appointment[NUM_DAYS];

		for (int i = 0; i < months.length; ++i) {
			months[i] = null;
		}
		for (int i = 0; i < days.length; ++i) {
			days[i] = null;
		}
		count = 0;
	}

	/**
	 * Insert an appointment at the given month and day combination. Duplicate
	 * appointments are allowed.
	 * 
	 * @param month
	 * @param day
	 * @param description
	 * @param duration
	 */
	public void addAppointment(String month, int day, String description,
			int duration) {

		int index = stringToIndex(month);

		if (day < 1 && day > 30)
			return;

		if (index == -1)
			return;

		Appointment newAppointment = new Appointment(day, index, description,
				duration);
		boolean existingAppointments = false;
		boolean appointmentAdded = false;

		// check whether month has no scheduled appointments
		if (months[index] == null) {
			months[index] = newAppointment;
			count++;
		} else {

			Appointment temp = months[index];

			// there exists at least one appointment
			if (temp.getDayValue() == day) {
				// find the newest appointment
				existingAppointments = true;
				while (temp.back != null) {
					temp = temp.back;
				}
				// add the new appointment to the end
				temp.back = newAppointment;
				appointmentAdded = true;
				count++;
			}

			temp = months[index];
			if (appointmentAdded == false) {
				while (temp.down != null) {
					temp = temp.down;
					// there exists at least one appointment
					if (temp.getDayValue() == day) {
						// find the newest appointment
						existingAppointments = true;
						while (temp.back != null) {
							temp = temp.back;
						}
						// add the new appointment to the end
						temp.back = newAppointment;
						appointmentAdded = true;
						count++;
						break;
					}

				}
				if (appointmentAdded == false)
					temp.down = newAppointment;
			}
		}

		if (days[day] == null) {
			days[day] = newAppointment;
		} else {
			if (existingAppointments == false) {
				Appointment temp = days[day];

				// find the end of the list
				while (temp.right != null) {
					temp = temp.right;
				}
				temp.right = newAppointment;
				count++;
			}
		}

	}

	/**
	 * Delete the first appointment at the given month and day combination and
	 * return the deleted appointment.
	 * 
	 * @param month
	 * @param day
	 * @return Deleted appointment, or null, if no such appointment exists
	 */
	public Appointment deleteAppointment(String month, int day) {

		Appointment currentMonth = months[stringToIndex(month)];
		Appointment currentDay = days[day];
		boolean foundMonthMatch = false;
		boolean foundDayMatch = false;

		// System.out.println("XX");

		// Deletion only if lists are not empty
		if (currentMonth != null && currentDay != null) {
			// System.out.println("AA");
			// Match occurring at head in month list
			if (currentMonth.getDayValue() == day) {
				// adjust the list accordingly if there are more
				// than one appointment on the given date
				if (currentMonth.back == null)
					months[stringToIndex(month)] = currentMonth.down;
				else {
					months[stringToIndex(month)] = currentMonth.back;
					months[stringToIndex(month)].down = currentMonth.down;
				}
				count--;
				foundMonthMatch = true;
			}
			// Match occurring at head in day list
			if (currentDay.getMonthValue() == stringToIndex(month)) {
				// System.out.println("BB");
				// adjust the list accordingly if there are more
				// than one appointment on the given date
				if (currentDay.back == null)
					days[day] = currentDay.right;
				else {
					days[day] = currentDay.back;
					days[day].right = currentDay.right;
				}
				count--;
				foundDayMatch = true;
			}

			// iterate to find the match in case not head in month list
			if (foundMonthMatch == false) {
				// System.out.println("CC");
				Appointment prev = currentMonth;
				currentMonth = currentMonth.down;
				while (currentMonth != null) {
					if (currentMonth.getDayValue() == day) {
						// adjust the list accordingly if there are more
						// than one appointment on the given date
						if (currentMonth.back == null)
							prev.down = currentMonth.down;
						else {
							currentMonth.back.down = currentMonth.down;
							prev.down = currentMonth.back;
						}

						break;
					}
					prev = currentMonth;
					currentMonth = currentMonth.down;
				}
			}

			// iterate to find the match in case not head in day list
			if (foundDayMatch == false) {
				// System.out.println("DD");
				Appointment prev = currentDay;
				currentDay = currentDay.right;
				while (currentDay != null) {
					if (currentDay.getMonthValue() == stringToIndex(month)) {
						// adjust the list accordingly if there are more
						// than one appointment on the given date
						if (currentDay.back == null)
							prev.right = currentDay.right;
						else {
							currentDay.back.right = currentDay.right;
							prev.right = currentDay.back;
						}
						break;
					}
					prev = currentDay;
					currentDay = currentDay.right;
				}
			}
			System.out.println(currentMonth);
			return currentMonth;

		}

		return null;
	}

	/**
	 * Delete the first appointment at the given month and day combination with
	 * the description and return the deleted appointment.
	 * 
	 * @param month
	 * @param day
	 * @param description
	 * @return Deleted appointment, or null, if no such appointment exists
	 */
	public Appointment deleteAppointment(String month, int day,
			String description) {

		Appointment currentMonth = months[stringToIndex(month)];
		Appointment currentDay = days[day];
		Appointment currentBack;
		Appointment prev;
		Appointment prevBack;
		boolean foundMonthMatch = false;
		boolean foundDayMatch = false;
		boolean deletedFromBackList = false;

		// Deletion only if lists are not empty
		if (currentMonth != null && currentDay != null) {

			// Match occurring at head in month list
			if (currentMonth.getDayValue() == day) {
				// System.out.println("01");
				if (currentMonth.getDescription().equals(description)) {
					// adjust the list accordingly if there are more
					// than one appointment on the given date
					if (currentMonth.back == null)
						months[stringToIndex(month)] = currentMonth.down;
					else {
						months[stringToIndex(month)] = currentMonth.back;
						months[stringToIndex(month)].down = currentMonth.down;
					}
					count--;
					foundMonthMatch = true;
				} else {
					// System.out.println("02");
					// if no match at head, check the appointments at the back
					// for possible matches
					prevBack = currentMonth;
					currentBack = currentMonth.back;
					while (currentBack != null) {
						if (currentBack.getDescription().equals(description)) {
							currentMonth = currentBack;
							prevBack.back = currentBack.back;
							deletedFromBackList = true;
							foundMonthMatch = true;
							break;
						}
						prevBack = currentBack;
						currentBack = currentBack.back;
					}
				}
			}

			// Match occurring at head in day list
			if (deletedFromBackList == false
					&& currentDay.getMonthValue() == stringToIndex(month)) {
				// System.out.println("03");
				if (currentDay.getDescription().equals(description)) {
					// adjust the list accordingly if there are more
					// than one appointment on the given date
					if (currentDay.back == null)
						days[day] = currentDay.right;
					else {
						days[day] = currentDay.back;
						days[day].right = currentDay.right;
					}
					count--;
					foundDayMatch = true;
				} else {
					// System.out.println("04");
					// if no match at head, check the appointments at the back
					// for possible matches
					prevBack = currentDay;
					currentBack = currentDay.back;
					while (currentBack != null) {
						if (currentBack.getDescription().equals(description)) {
							currentMonth = currentBack;
							prevBack.back = currentBack.back;
							deletedFromBackList = true;
							foundDayMatch = true;
							break;
						}
						prevBack = currentBack;
						currentBack = currentBack.back;
					}
				}
			}

			// iterate to find the match in case not head in month list
			if (deletedFromBackList == false && foundMonthMatch == false) {
				// System.out.println("05");
				prev = currentMonth;
				currentMonth = currentMonth.down;
				while (currentMonth != null) {
					if (currentMonth.getDayValue() == day) {
						if (currentMonth.getDescription().equals(description)) {
							// adjust the list accordingly if there are more
							// than one appointment on the given date
							if (currentMonth.back == null)
								prev.down = currentMonth.down;
							else {
								currentMonth.back.down = currentMonth.down;
								prev.down = currentMonth.back;
							}
							break;
						} else {
							// System.out.println("06");
							// if first appointment of day is a mismatch
							// check the rest appointments for possible matches
							prevBack = currentMonth;
							currentBack = currentMonth.back;
							while (currentBack != null) {
								if (currentBack.getDescription().equals(
										description)) {
									currentMonth = currentBack;
									prevBack.back = currentBack.back;
									count--;
									break;
								}
								prevBack = currentBack;
								currentBack = currentBack.back;
							}
						}
						prev = currentMonth;
						currentMonth = currentMonth.down;
					}
				}
			}

			// iterate to find the match in case not head in day list
			if (deletedFromBackList == false && foundDayMatch == false) {
				// System.out.println("07");
				prev = currentDay;
				currentDay = currentDay.right;
				while (currentDay != null) {
					if (currentDay.getMonthValue() == stringToIndex(month)) {
						if (currentDay.getDescription().equals(description)) {
							// adjust the list accordingly if there are more
							// than one appointment on the given date
							if (currentDay.back == null) {
								prev.right = currentDay.right;

							} else {
								currentDay.back.right = currentDay.right;
								prev.right = currentDay.back;
							}
							break;
						}

					}
					prev = currentDay;
					currentDay = currentDay.right;
				}
			}
			System.out.println(currentMonth);
			return currentMonth;

		}
		return null;
	}

	/**
	 * All appointments for the given month should be deleted. If the month has
	 * no appointments, simply do nothing.
	 * 
	 * @param month
	 */
	public void clearMyMonth(String month) {

		int value = stringToIndex(month);

		if (months[value] != null) {

			Appointment current;
			Appointment trailCurrent;
			months[value] = null;

			for (int i = 0; i < days.length; ++i) {

				current = days[i];

				if (current != null) {

					if (current.getMonthValue() == value) {

						days[i] = current.right;
					} else {
						trailCurrent = current;
						current = current.right;

						while (current != null) {

							if (current.getMonthValue() == value) {

								trailCurrent.right = current.right;
								return;
							}
							trailCurrent = current;
							current = current.right;
						}
					}
				}
			}
		}

	}

	/**
	 * All appointments for the given day should be deleted. If the day has no
	 * appointments, simply do nothing
	 * 
	 * @param day
	 */
	public void clearMyDays(int day) {

		if (days[day] != null) {

			Appointment current;
			Appointment trailCurrent;
			days[day] = null;

			for (int i = 0; i < months.length; ++i) {

				current = months[i];

				if (current != null) {

					if (current.getDayValue() == day) {
						months[i] = current.down;
					} else {
						trailCurrent = current;
						current = current.down;
						while (current != null) {

							if (current.getDayValue() == day) {
								trailCurrent.down = current.down;
								return;
							}

							trailCurrent = current;
							current = current.down;
						}
					}
				}
			}
		}
	}

	/** Delete all appointments from the calendar. */
	public void clearMyYear() {

		for (int i = 0; i < days.length; ++i) {
			days[i] = null;
		}

		for (int i = 0; i < months.length; ++i) {
			months[i] = null;
		}

		count = 0;
	}

	/**
	 * Return the head appointment of the month and day combination. If no such
	 * appointment exists, return null
	 * 
	 * @param month
	 * @param day
	 * @return
	 */
	public Appointment getAppointment(String month, int day) {

		Appointment current;

		for (int i = 0; i < days.length; ++i) {
			current = days[i];
			while (current != null)
				if (current.getMonthValue() == stringToIndex(month)
						&& current.getDayValue() == day) {

					return current;
				} else {
					current = current.right;
				}
		}
		return null;
	}

	/**
	 * Return the head appointment for the month passed as a parameter. If no
	 * such appointment exists, return null
	 * 
	 * @param month
	 * @return
	 */
	public Appointment getMonthAppointment(String month) {

		Appointment current;

		for (int i = 0; i < days.length; ++i) {
			current = days[i];
			while (current != null)
				if (current.getMonthValue() == stringToIndex(month)) {

					return current;
				} else {
					current = current.right;
				}
		}
		return null;
	}

	/**
	 * Return the head appointment for the day passed as a parameter. If no such
	 * appointment exists, return null
	 * 
	 * @param day
	 * @return
	 */
	public Appointment getDayAppointment(int day) {

		Appointment current;

		for (int i = 0; i < months.length; ++i) {
			current = months[i];
			while (current != null)
				if (current.getDayValue() == day) {
					return current;
				} else {
					current = current.down;
				}
		}
		return null;
	}

	/**
	 * Find the first appointment at the month and day combination with the
	 * description "oldDescription", and change the description to
	 * "newDescription"
	 * 
	 * @param month
	 * @param day
	 * @param oldDescription
	 * @param newDescription
	 */
	public void changeAppointmentDescription(String month, int day,
			String oldDescription, String newDescription) {

		Appointment current;
		Appointment newAppointment;
		int value = stringToIndex(month);

		for (int i = 0; i < days.length; ++i) {
			current = days[i];
			while (current != null)
				if (current.getMonthValue() == value
						&& current.getDayValue() == day
						&& current.getDescription() == oldDescription) {
					newAppointment = new Appointment(newDescription,
							current.getDuration(), value, day);
					current = newAppointment;
					days[i] = newAppointment;
					return;

				} else {
					current = current.right;
				}
		}
	}

	/**
	 * Find the first appointment at the month and day combination with the
	 * description "oldDescription", and change the duration to "newDuration"
	 * 
	 * @param month
	 * @param day
	 * @param oldDescription
	 * @param newDuration
	 */
	public void changeAppointmentDuration(String month, int day,
			String oldDescription, int newDuration) {

		Appointment current;
		Appointment newAppointment;
		int value = stringToIndex(month);

		for (int i = 0; i < days.length; ++i) {
			current = days[i];
			while (current != null)
				if (current.getMonthValue() == value
						&& current.getDayValue() == day
						&& current.getDescription() == oldDescription) {
					newAppointment = new Appointment(oldDescription,
							newDuration, value, day);
					current = newAppointment;
					days[i] = newAppointment;
					return;

				} else {
					current = current.right;
				}
		}
	}

	/**
	 * Find the appointment located at the "fromMonth"/"fromDate" combination
	 * and change the appointment to the "toMonth/toDate" location
	 * 
	 * @param fromMonth
	 * @param fromDay
	 * @param toMonth
	 * @param toDay
	 */
	public void changeAppointmentDate(String fromMonth, int fromDay,
			String toMonth, int toDay) {

		if (getMonthAppointment(fromMonth) != null
				&& getDayAppointment(fromDay) != null) {
			Appointment subject = this.deleteAppointment(fromMonth, fromDay);
			this.addAppointment(toMonth, toDay, subject.getDescription(),
					subject.getDuration());
		}

	}

	public final int getCount() {
		return count;
	}

	/**
	 * Converts the month string value to an in integer for the month array
	 * index
	 * 
	 * @param month
	 * @return The index of the month
	 */
	private int stringToIndex(String month) {
		if (month.equalsIgnoreCase("Jan"))
			return 1;
		if (month.equalsIgnoreCase("Feb"))
			return 2;
		if (month.equalsIgnoreCase("Mar"))
			return 3;
		if (month.equalsIgnoreCase("Apr"))
			return 4;
		if (month.equalsIgnoreCase("May"))
			return 5;
		if (month.equalsIgnoreCase("Jun"))
			return 6;
		if (month.equalsIgnoreCase("Jul"))
			return 7;
		if (month.equalsIgnoreCase("Aug"))
			return 8;
		if (month.equalsIgnoreCase("Sep"))
			return 9;
		if (month.equalsIgnoreCase("Oct"))
			return 10;
		if (month.equalsIgnoreCase("Nov"))
			return 11;
		if (month.equalsIgnoreCase("Dec"))
			return 12;
		return -1;
	}

}
