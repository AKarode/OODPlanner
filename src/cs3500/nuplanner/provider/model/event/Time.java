package cs3500.nuplanner.provider.model.event;

/**
 * Class to represent Time. A time consists of hours and minutes.
 * Time starts at 00:00 and ends at 23:59.
 */
public class Time {
  private final int hour;
  private final int minute;

  /**
   * Constructor to create a new Time object.
   * @param hour the hour of the time
   * @param minute the minute of the time
   * @throws IllegalArgumentException if the hour is invalid
   *                                  (valid hours: 0 < hour < 23)
   * @throws IllegalArgumentException if the minute is invalid
   *                                  (valid minutes: 0 < minute < 59)
   */
  public Time(int hour, int minute) {
    if (hour < 0 || hour > 23) {
      throw new IllegalArgumentException("Invalid hour input!");
    }
    if (minute < 0 || minute > 59) {
      throw new IllegalArgumentException("Invalid minute input!");
    }
    this.hour = hour;
    this.minute = minute;
  }

  /**
   * Get the hour of the time.
   * @return hour value of the Time
   */
  public int getHour() {
    return hour;
  }

  /**
   * Get the minute of the time.
   * @return minute value of the Time
   */
  public int getMinute() {
    return minute;
  }

  /**
   * Given a 4-digit number, will convert it to a Time.
   * @param time four-digit number
   * @return Time Object
   */
  public static Time fourDigitNumToTime(int time) {
    int hours = time / 100;
    int minutes = time % 100;
    if (hours > 23 || hours < 0) {
      throw new IllegalArgumentException("Invalid Time");
    }
    if (minutes > 59 || minutes < 0) {
      throw new IllegalArgumentException("Invalid Time");
    }
    return new Time(hours, minutes);
  }

  /**
   * Given a Time, will convert to a four-digit number for usage.
   * @param t Time
   * @return 4 digit number representation
   */
  public static int timeToFourDigitNum(Time t) {
    return (t.hour * 100) + t.minute;
  }


  /**
   * Equals operator for comparing times.
   * @param other other time
   * @return equality of the times
   */
  public boolean equalTimes(Time other) {
    return other.getHour() == this.hour && other.getMinute() == this.minute;
  }

  /**
   * Convert the Time to String format (00:00).
   * @return the formatted Time
   */
  public String toString() {
    String hourString;
    String minuteString;

    if (hour < 10) {
      hourString = "0" + String.valueOf(hour);
    }
    else {
      hourString = String.valueOf(hour);
    }

    if (minute < 10) {
      minuteString = "0" + String.valueOf(minute);
    }
    else {
      minuteString = String.valueOf(minute);
    }

    return hourString + ":" + minuteString;
  }

  /**
   * Add a given number of minutes to this Time.
   * Note: The minutes will always be less than 60 * 24.
   *       Trying to add a day's worth of minutes should be handled
   *       in the Date class.
   * @param minutes the number of minutes to add
   * @return the new Time with the added minutes
   */
  public Time addMinutes(int minutes) {
    int addedHours = minutes / 60;
    int remainingMinutes = minutes % 60;

    return new Time(this.hour + addedHours, this.minute + remainingMinutes);
  }
}
