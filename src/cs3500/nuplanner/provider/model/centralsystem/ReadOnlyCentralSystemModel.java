package cs3500.nuplanner.provider.model.centralsystem;

import java.util.List;

import cs3500.nuplanner.provider.model.event.Day;
import cs3500.nuplanner.provider.model.event.Event;
import cs3500.nuplanner.provider.model.event.Time;
import cs3500.nuplanner.provider.model.schedule.Schedule;

/**
 * Interface for the Reading operations of a CentralSystemModel.
 */
public interface ReadOnlyCentralSystemModel {
  /**
   * Select a user to display the Schedule of.
   * @param user the user whose Schedule is displayed
   * @return the Schedule of the selected user
   * @throws IllegalArgumentException if the user does not have
   *                                  a Schedule in the system
   */
  Schedule selectUser(String user);

  /**
   * View the OurEvent occurring at a specific time.
   * for a user.
   * @param user the user to view
   * @param day the day of the time to view
   * @param time the time to view
   * @return the Events at the given time for the given user
   * @throws IllegalArgumentException if the user does not have
   *                                  a Schedule in the system
   */
  Event viewUserEvent(String user, Day day, Time time);

  /**
   * Get all schedules in the System.
   * @return returns list of all schedules;
   */
  List<Schedule> getSchedules();

  /**
   * Return a list of all the Users who have
   * Schedules in the system.
   * @return a list of all the users in the system
   */
  List<String> getUsers();
}
