package cs3500.nuplanner.provider.model.centralsystem;

import cs3500.nuplanner.provider.model.event.Day;
import cs3500.nuplanner.provider.model.event.Event;
import cs3500.nuplanner.provider.model.event.Time;
import cs3500.nuplanner.provider.model.schedule.Schedule;
/**
 * Interface for a central system. A central system allows a person to
 * interact with the program.
 * The central system allows a person to add, remove, or modify events,
 * which impacts the event in every user's schedule.
 * A person cannot create a time conflict in any Schedule in the system.
 * This means a person cannot add or modify an event to cause a conflict in
 * the host or an invitee's Schedule.
 * INVARIANT: Each schedule in the system must have a unique user.
 */
public interface CentralSystemModel extends ReadOnlyCentralSystemModel {
  /**
   * Add a Schedule to the CentralSystem from an
   * XML file.
   * @param file the file to upload
   * @throws IllegalArgumentException if the system contains a
   *                                  Schedule with the same user
   */
  public void uploadSchedule(String file);

  /**
   * Add a new Schedule to the Central System.
   * @param schedule the schedule to add
   * @throws IllegalArgumentException if the system contains a
   *                                  Schedule with the same user
   */
  void uploadSchedule(Schedule schedule);

  /**
   * Save a Schedule from the CentralSystem to an
   * XML file.
   * @param schedule the schedule to save to the system
   */
  void saveSchedule(Schedule schedule);

  /**
   * Remove a Schedule from the Central System by
   * the name of its user.
   * Removes the user from the list of invitees for every
   * OurEvent in the system.
   * Removes any Events that the user hosts from the system.
   * @param user the user of the Schedule to remove
   * @throws IllegalArgumentException if the user does not have
   *a Schedule in the system
   */
  void removeSchedule(String user);

  Event getMatchingEvent(String user, Event e);

  /**
   * Add an event to a user's schedule.
   * Adds the OurEvent to invitees' Schedules if
   * it does not cause a conflict in that Schedule.
   * @param event the event to add
   * @throws IllegalArgumentException if the user does not have
   *                                  a Schedule in the system
   * @throws IllegalArgumentException if the OurEvent interferes with
   *                                  the user's Schedule
   * @throws IllegalArgumentException if the OurEvent interferes with
   *                                  any of the invitees' schedules
   */
  void createEvent(String user, Event event);

  /**
   * Remove an event from a user's schedule.
   * Removes the OurEvent from invitees' Schedules if the user
   * hosts the OurEvent.
   * @param user the user of the schedule to remove the event from
   * @param event the event to remove
   * @throws IllegalArgumentException if the user does not have
   *                                  a Schedule in the system
   * @throws IllegalArgumentException if the OurEvent does not exist
   *                                  in the user's Schedule
   */
  void removeEvent(String user, Event event);

  /**
   * Modify an Event by changing its name.
   * @param user the user to access the Schedule of
   * @param event the Event to modify
   * @param newName the name to assign the OurEvent
   * @throws IllegalArgumentException if the user does not have
   *                                  a Schedule in the system
   * @throws IllegalArgumentException if the OurEvent does not exist
   *                                  in the user's Schedule
   */
  void modifyEventName(String user, Event event, String newName);

  /**
   * Modify an Event by adding a user to its list
   * of invitees.
   * Invite the new user to the OurEvent (add the OurEvent to
   * their Schedule if they are available).
   * @param user the user of the Schedule to modify
   * @param event the OurEvent in the Schedule to modify
   * @param other the user to add to the Schedule's invitees
   * @throws IllegalArgumentException if the user does not have
   *                                  a Schedule in the system
   * @throws IllegalArgumentException if the OurEvent does not exist
   *                                  in the user's Schedule
   * @throws IllegalArgumentException if the OurEvent causes a conflict
   *                                  in the invitee's Schedule
   */
  void modifyEventInviteUser(String user, Event event, String other);

  /**
   * Modify an Event by removing a user from its list
   * of invitees.
   * Revoke the other user's invite from the OurEvent (remove the
   * OurEvent from their Schedule if it is present).
   * @param user the user of the Schedule to modify
   * @param event the OurEvent in the Schedule to modify
   * @param other the user to remove from the Schedule's invitees
   * @throws IllegalArgumentException if the user does not have
   *                                  a Schedule in the system
   * @throws IllegalArgumentException if the OurEvent does not exist
   *                                  in the user's Schedule
   * @throws IllegalArgumentException if the OurEvent does not exist in
   *                                  the other user's Schedule
   */
  void modifyEventUninviteUser(String user, Event event, String other);

  /**
   * Modify an Event by changing its location.
   * @param user the user to access the Schedule of
   * @param event the OurEvent to modify
   * @param location the location to assign to the OurEvent
   * @throws IllegalArgumentException if the user does not have
   *                                  a Schedule in the system
   * @throws IllegalArgumentException if the OurEvent does not exist
   *                                  in the user's Schedule
   */
  void modifyEventLocation(String user, Event event, String location);

  /**
   * Modify an Event by changing its type (online or not online).
   * @param user the user to access the Schedule of
   * @param event the OurEvent to modify
   * @param isOnline the online status to assign the OurEvent
   * @throws IllegalArgumentException if the user does not have
   *                                  a Schedule in the system
   * @throws IllegalArgumentException if the OurEvent does not exist
   *                                  in the user's Schedule
   */
  void modifyEventOnlineStatus(String user, Event event, boolean isOnline);

  /**
   * Modify an Event by changing its start day.
   * Modifies the OurEvent's start day for all invitees.
   * If the new start day causes a conflict in an
   * invitee's Schedule, the OurEvent is removed from
   * their Schedule.
   * @param user the user to access the Schedule of
   * @param event the OurEvent to modify
   * @param startDay the start day to assign to the OurEvent
   * @throws IllegalArgumentException if the user does not have
   *                                  a Schedule in the system
   * @throws IllegalArgumentException if the OurEvent does not exist
   *                                  in the user's Schedule
   * @throws IllegalArgumentException if the new start day causes a
   *                                  conflict in the user's Schedule
   * @throws IllegalArgumentException if the new start day causes a
   *                                  conflict in an invitee's Schedule
   */
  void modifyEventStartDay(String user, Event event, Day startDay);

  /**
   * Modify an OurEvent by changing its start time.
   * Modifies the OurEvent's start time for all invitees.
   * If the new start time causes a conflict in an
   * invitee's Schedule, the OurEvent is removed from
   * their Schedule.
   * @param user the user to access the Schedule of
   * @param event the OurEvent to modify
   * @param startTime the start time to assign to the OurEvent
   * @throws IllegalArgumentException if the user does not have
   *                                  a Schedule in the system
   * @throws IllegalArgumentException if the OurEvent does not exist
   *                                  in the user's Schedule
   * @throws IllegalArgumentException if the new startTime is not
   *                                  a valid time (0000 >= time >= 2359)
   * @throws IllegalArgumentException if the new start time causes a
   *                                  conflict in the user's Schedule
   * @throws IllegalArgumentException if the new start time causes a
   *                                  conflict in an invitee's Schedule
   */
  void modifyEventStartTime(String user, Event event, Time startTime);

  /**
   * Modify an OurEvent by changing its end day.
   * Modifies the OurEvent's end day for all invitees.
   * If the new end day causes a conflict in an
   * invitee's Schedule, the OurEvent is removed from
   * their Schedule.
   * @param user the user to access the Schedule of
   * @param event the OurEvent to modify
   * @param endDay the end day to assign to the OurEvent
   * @throws IllegalArgumentException if the user does not have
   *                                  a Schedule in the system
   * @throws IllegalArgumentException if the OurEvent does not exist
   *                                  in the user's Schedule
   * @throws IllegalArgumentException if the new end day causes a
   *                                  conflict in the user's Schedule
   * @throws IllegalArgumentException if the new end day causes a
   *                                  conflict in an invitee's Schedule
   */
  void modifyEventEndDay(String user, Event event, Day endDay);

  /**
   * Modify an OurEvent by changing its end time.
   * Modifies the OurEvent's end time for all invitees.
   * If the new end time causes a conflict in an
   * invitee's Schedule, the OurEvent is removed from
   * their Schedule.
   * @param user the user to access the Schedule of
   * @param event the OurEvent to modify
   * @param endTime the location to assign to the OurEvent
   * @throws IllegalArgumentException if the user does not have
   *                                  a Schedule in the system
   * @throws IllegalArgumentException if the OurEvent does not exist
   *                                  in the user's Schedule
   * @throws IllegalArgumentException if the new endTime is not
   *                                  a valid time (0000 >= time >= 2359)
   * @throws IllegalArgumentException if the new end time causes a
   *                                  conflict in the user's Schedule
   * @throws IllegalArgumentException if the new end time causes a
   *                                  conflict in an invitee's Schedule
   */
  void modifyEventEndTime(String user, Event event, Time endTime);

  /**
   * Schedule an Event in the System if there is a window that
   * accommodates the given duration.
   * The user can schedule an Event at any time or only during
   * work hours.
   * @param event the Event to schedule
   * @param duration the duration of the Event to schedule
   * @throws IllegalArgumentException if the Event cannot be scheduled
   */
  void scheduleEvent(Event event, int duration);

  void modifyEvent(String user, Event event, Event e);
}
