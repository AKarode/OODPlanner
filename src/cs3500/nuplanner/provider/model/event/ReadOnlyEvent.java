package cs3500.nuplanner.provider.model.event;

import java.util.List;

/**
 * Interface for the Reading operations of a OurEvent.
 */
public interface ReadOnlyEvent {
  /**
   * Gives a copy of start day to user.
   * @return Start Day
   */
  Day getStartDay();

  /**
   * Gives the int value of the end time.
   *
   * @return Start time
   */
  Time getStartTime();

  /**
   * Gives a copy of end day to user.
   * @return End Day
   */
  Day getEndDay();

  /**
   * Gives the int value of the end time.
   *
   * @return End Time
   */
  Time getEndTime();

  /**
   * Gives back if the location is online.
   * @return Online Status
   */
  boolean isOnline();

  /**
   * Gives a copy of the place where the event is.
   * @return OurEvent Place
   */
  String getLocation();

  /**
   * Gives a copy of all users going to event.
   * @return List of Users.
   */
  List<String> getInvitees();

  /**
   * Gives a copy of the event's name.
   * @return name of event
   */
  String getName();

  /**
   * Returns the name of the User hosting this OurEvent.
   * @return the name of the host
   */
  String getHost();
}
