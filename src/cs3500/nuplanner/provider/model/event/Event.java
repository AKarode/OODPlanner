package cs3500.nuplanner.provider.model.event;
/**
 * An OurEvent is a part of a Schedule. It can be
 * as long as one week.
 */
public interface Event extends ReadOnlyEvent {

  /**
   * Sets the name of event.
   * @param name Name to change to
   */
  public void setName(String name);

  /**
   * Changes Starting day of event.
   * @param startDay Day to change to
   */
  public void setStartDay(Day startDay);

  /**
   * Changes Starting time of event.
   * @param startTime Time to change to
   * @throws IllegalArgumentException if the startTime is not a valid time
   *                                  (0000 <= time <= 2359)
   */
  public void setStartTime(Time startTime);

  /**
   * Changes Ending day of event.
   * @param endDay Day to change to
   */
  public void setEndDay(Day endDay);

  /**
   * Changes Ending time of event.
   * @param endTime Time to change to
   * @throws IllegalArgumentException if the startTime is not a valid time
   *                                  (0000 <= time <= 2359)
   */
  public void setEndTime(Time endTime);

  /**
   * Changes online status of event.
   * @param online Online Status
   */
  public void setOnline(boolean online);

  /**
   * Set location of event.
   * @param place Place event is located at.
   */
  public void setPlace(String place);

  /**
   * Adds user to user list.
   * @param user user to add
   */
  public void inviteUser(String user);

  /**
   * Removes user from invitees list.
   * @param user the user to remove from the list of invitees
   * @throws IllegalArgumentException if user is not in list of invitees
   */
  public void uninviteUser(String user);

  /**
   * Determine if this OurEvent overlaps with the
   * given OurEvent.
   * @param event the OurEvent to compare this OurEvent to
   * @return a boolean representing whether the Events overlap
   */
  public boolean overlapsWith(Event event);

  /**
   * Determine if the given time falls within this OurEvent.
   * @param day the day to check
   * @param time the time to check
   * @return whether the given time falls within the OurEvent
   */
  public boolean overlapsWith(Day day, Time time);

  /**
   * Compare this OurEvent to another OurEvent to determine if
   * they are equal.
   * @param e the OurEvent to compare this OurEvent to
   * @return a boolean representing whether the Events are equal
   */
  boolean equalEvents(Event e);
}
