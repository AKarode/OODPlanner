package cs3500.nuplanner.provider.model.schedule;

import cs3500.nuplanner.provider.model.event.Day;
import cs3500.nuplanner.provider.model.event.Event;
import cs3500.nuplanner.provider.model.event.Time;

/**
 * A Schedule for a given user,
 * Has a list of events in which the user has within there schedule.
 */
public interface Schedule extends ReadOnlySchedule {
  /**
   * Adds an event to Users Schedule.
   * @param e event to add
   * @throws IllegalArgumentException if the event conflicts with
   *                                  an existing event in the Schedule
   */
  void addEvent(Event e);

  /**
   * Removes event from Schedule.
   * @param e the name of the OurEvent to remove
   * @throws IllegalArgumentException if the event does not exist
   *                                  in the Schedule
   */
  void removeEvent(Event e);

  /**
   * Modify an OurEvent by changing its name.
   * @param event the OurEvent to modify
   * @param newName the name to assign the OurEvent
   * @throws IllegalArgumentException if the event does not exist
   *                                  in the Schedule
   */
  void modifyEventName(Event event, String newName);

  /**
   * Modify an OurEvent by adding a user to the
   * list of invitees.
   * @param event the OurEvent to modify
   * @param user the user to invite
   * @throws IllegalArgumentException if the event does not exist
   *                                  in the Schedule
   */
  void modifyEventInviteUser(Event event, String user);

  /**
   * Modify an OurEvent by removing a user from the
   * list of invitees.
   * @param event the OurEvent to modify
   * @param user the user to be uninvited
   * @throws IllegalArgumentException if user is not in OurEvent's list of invitees
   * @throws IllegalArgumentException if the event does not exist
   *                                  in the Schedule
   * @throws IllegalArgumentException if the user is not in the event's list of
   *                                  invitees
   */
  void modifyEventUninviteUser(Event event, String user);

  /**
   * Modify an OurEvent by changing its location.
   * @param event the OurEvent to modify
   * @param location the location to assign to the OurEvent
   * @throws IllegalArgumentException if the event does not exist
   *                                  in the Schedule
   */
  void modifyEventLocation(Event event, String location);

  /**
   * Modify an OurEvent by changing its type (online or not online).
   * @param event the OurEvent to modify
   * @param isOnline the online status to assign the OurEvent
   * @throws IllegalArgumentException if the event does not exist
   *                                  in the Schedule
   */
  void modifyEventOnlineStatus(Event event, boolean isOnline);

  /**
   * Modify an OurEvent by changing its start day.
   * @param event the OurEvent to modify
   * @param startDay the start day to assign to the OurEvent
   * @throws IllegalArgumentException if the event does not exist
   *                                  in the Schedule
   * @throws IllegalArgumentException if the new start day causes a conflict
   *                                  with an existing event in the Schedule
   */
  void modifyEventStartDay(Event event, Day startDay);

  /**
   * Modify an OurEvent by changing its start time.
   * @param event the OurEvent to modify
   * @param startTime the start time to assign to the OurEvent
   * @throws IllegalArgumentException if the event does not exist
   *                                  in the Schedule
   * @throws IllegalArgumentException if the new start day causes a conflict
   *                                  with an existing event in the Schedule
   * @throws IllegalArgumentException if the new start time is not a valid time
   *                                  (0000 <= time <= 2359)
   */
  void modifyEventStartTime(Event event, Time startTime);

  /**
   * Modify an OurEvent by changing its end day.
   * @param event the OurEvent to modify
   * @param endDay the end day to assign to the OurEvent
   * @throws IllegalArgumentException if the event does not exist
   *                                  in the Schedule
   * @throws IllegalArgumentException if the new start day causes a conflict
   *                                  with an existing event in the Schedule
   */
  void modifyEventEndDay(Event event, Day endDay);

  /**
   * Modify an OurEvent by changing its end time.
   * @param event the OurEvent to modify
   * @param endTime the location to assign to the OurEvent
   * @throws IllegalArgumentException if the event does not exist
   *                                  in the Schedule
   * @throws IllegalArgumentException if the new start day causes a conflict
   *                                  with an existing event in the Schedule
   * @throws IllegalArgumentException if the new start time is not a valid time
   *                                  (0000 <= time <= 2359)
   */
  void modifyEventEndTime(Event event, Time endTime);

  /**
   * Determines if the given event conflicts with any
   * Events in this Schedule.
   * @param event the OurEvent to compare to the Schedule's events
   * @return a boolean representing whether the event causes a conflict
   */
  boolean causesConflict(Event event);
}
