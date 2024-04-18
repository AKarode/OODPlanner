package cs3500.nuplanner.provider.model.event;

/**
 * Enumeration of valid days of the week.
 */
public enum Day {
  Sunday(0),
  Monday(1),
  Tuesday(2),
  Wednesday(3),
  Thursday(4),
  Friday(5),
  Saturday(6);

  private final int dayValue;

  Day(int dayValue) {
    this.dayValue = dayValue;
  }

  /**
   * Gets the day value of a day.
   * @return value of day
   */
  public int getDayValue() {
    return this.dayValue;
  }

  @Override
  public String toString() {
    String value = "";
    switch (this) {
      case Sunday:
        value = "Sunday";
        break;
      case Monday:
        value = "Monday";
        break;
      case Tuesday:
        value = "Tuesday";
        break;
      case Wednesday:
        value = "Wednesday";
        break;
      case Thursday:
        value = "Thursday";
        break;
      case Friday:
        value = "Friday";
        break;
      case Saturday:
        value = "Saturday";
        break;
      default:
        break;
    }
    return value;
  }

  /**
   * Returns Day based on value.
   * @param day value of day
   * @return The Day
   */
  public static Day valueToDay(int day) {
    switch (day) {
      case 1:
        return Monday;
      case 2:
        return Tuesday;
      case 3:
        return Wednesday;
      case 4:
        return Thursday;
      case 5:
        return Friday;
      case 6:
        return Saturday;
      default:
        return Sunday;
    }
  }
}
