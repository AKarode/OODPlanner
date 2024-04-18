package cs3500.nuplanner.provider.model.event;

/**
 * This class represents a Date, which consists of a
 * Day and Time. The Date class allows a user to
 * encapsulate Day and Time in one class.
 */
public class Date {
  private final Day day;
  private final Time time;

  /**
   * Constructor to initialize the Date's
   * Day and Time.
   * @param day the day of the Date
   * @param time the time of the Date
   */
  public Date(Day day, Time time) {
    this.day = day;
    this.time = time;
  }

  /**
   * Get the Day of this Date.
   * @return the day at which the Date is scheduled
   */
  public Day getDay() {
    return this.day;
  }

  /**
   * Get the Time of this Date.
   * @return the time at which the Date is scheduled
   */
  public Time getTime() {
    return this.time;
  }

  public boolean equals(Date other) {
    return this.day.getDayValue() == other.getDay().getDayValue()
            && this.time.equalTimes(other.getTime());
  }

  /**
   * Add an amount of time (in minutes) to this Date.
   * @param minutes the number of minutes to add
   * @return the Date resulting from adding the given minutes to this Date
   */
  public Date addTime(int minutes) {
    Day curDay = this.day;

    int addedDays = minutes / (60 * 24);
    int remainingMinutes = minutes % (60 * 24);

    if (minutes >= (60 * 24)) {
      curDay = this.addDays(curDay, addedDays);
    }
    return this.addMinutes(curDay, this.time, remainingMinutes);
  }

  private Day addDays(Day day, int days) {
    int newDay = day.getDayValue() + days;
    if (newDay > 6) {
      newDay = newDay % 7;
    }
    return Day.valueToDay(newDay);
  }

  private Date addMinutes(Day day, Time time, int minutes) {
    int addedHours = minutes / 60;
    int remainingMinutes = minutes % 60;
    int curHours = time.getHour() + addedHours;
    int curMinutes = time.getMinute() + remainingMinutes;

    if (curMinutes > 59) {
      curHours += 1;
      curMinutes = curMinutes % 60;
    }

    if (curHours > 23) {
      day = this.addDays(day, 1);
      curHours = curHours % 24;
    }

    return new Date(day, new Time(curHours, curMinutes));
  }
}
