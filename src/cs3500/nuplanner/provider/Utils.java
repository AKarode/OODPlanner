package cs3500.nuplanner.provider;

import cs3500.nuplanner.provider.model.event.Time;

/**
 * Helper Functions that can be used across the code.
 */
public class Utils {
  /**
   * Formats inputted 4 digit time as a proper string for usage.
   * @param time Time of Event
   * @return Formatted Time
   */
  public static String timeToString(Time time) {
    String timeString = String.valueOf(Time.timeToFourDigitNum(time));
    while (timeString.length() < 4) {
      timeString = "0" + timeString;
    }
    return timeString;
  }

  /**
   * Properly capitalizes the first character of a string.
   * @param str String to capitalize
   * @return Formatted String
   */
  public static String capitalize(String str) {
    return str.substring(0, 1).toUpperCase()
            + str.substring(1);
  }
}
