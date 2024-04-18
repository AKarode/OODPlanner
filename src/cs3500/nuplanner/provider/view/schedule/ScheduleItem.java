package cs3500.nuplanner.provider.view.schedule;

import cs3500.nuplanner.provider.model.schedule.Schedule;

/**
 * An Item used to display a schedule in a ComboBox in the GUI.
 */
public class ScheduleItem {
  private String key;
  private Schedule value;

  public ScheduleItem(String key, Schedule value) {
    this.key = key;
    this.value = value;
  }

  @Override
  public String toString() {
    return key;
  }

  /**
   * Gets the Key associated with a Schedule.
   * @return Key
   */
  public String getKey() {
    return key;
  }

  /**
   * Gets the Schedule associated with a Key.
   * @return Schedule
   */
  public Schedule getValue() {
    return value;
  }
}
