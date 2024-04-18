package cs3500.nuplanner.provider.model.schedule;

import java.util.List;
import java.util.Map;

import cs3500.nuplanner.provider.model.event.Day;
import cs3500.nuplanner.provider.model.event.Event;
import cs3500.nuplanner.provider.model.event.Time;

/**
 * Interface for the Reading operations of a Schedule.
 */
public interface ReadOnlySchedule {
  /**
   * Gives back an observer of all the users
   * Scheduled Events.
   * @return List of Events user is attending
   */
  List<Event> getEvents();

  /**
   * Gives back the name of the user who owns the schedule.
   * @return returns name of who owns schedule
   */
  String getUser();

  /**
   * Returns the OurEvent in this Schedule occurring at the
   * given day and time. If no OurEvent is occurring, the method
   * returns null.
   * @param day the day to check
   * @param time the time to check
   * @return the OurEvent happening at the given time
   */
  Event viewEvent(Day day, Time time);
}
