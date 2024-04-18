package cs3500.nuplanner.provider.model.schedule;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cs3500.nuplanner.provider.model.event.Day;
import cs3500.nuplanner.provider.model.event.Event;
import cs3500.nuplanner.provider.model.event.Time;

/**
 * A UserSchedule is the weekly planner for
 * one individual user.
 */
public class UserSchedule implements Schedule {
  private final List<Event> events;
  private final String user;

  /**
   * A single Schedule for a user.
   * Can be created with a list of Events, which
   * is used for testing.
   * @param user User the schedule belongs to
   * @param events all events in users schedule
   */
  public UserSchedule(String user, List<Event> events) {
    this.events = new ArrayList<>();
    this.events.addAll(events);
    this.user = user;
  }

  /**
   * A single Schedule for a user.
   * The Schedule has no Events by default, which is how
   * a Schedule should be created.
   * @param user User the schedule belongs to
   */
  public UserSchedule(String user) {
    this.events = new ArrayList<>();
    this.user = user;
  }

  @Override
  public void addEvent(Event e) {
    for (Event event : this.events) {
      if (event.overlapsWith(e)) {
        throw new IllegalArgumentException("OurEvent causes schedule conflict!");
      }
    }
    this.events.add(e);
  }

  @Override
  public void removeEvent(Event e) {
    if (this.events.contains(e)) {
      this.events.remove(e);
    }
    /*
    else {
      throw new IllegalArgumentException("OurEvent not in schedule");
    }*/
    for (Event event : events) {
      if (event.equals(e)) {
        this.events.remove(e);
      }
    }
  }

  /**
   * Gives back an observer of all the users
   * Scheduled Events.
   * @return List of Events user is attending
   */
  public List<Event> getEvents() {
    return new ArrayList<>(this.events);
  }

  @Override
  public boolean causesConflict(Event event) {
    for (Event e : this.events) {
      if (e.overlapsWith(event) && !e.equals(event)) {
        return true;
      }
    }
    return false;
  }

  @Override
  public Event viewEvent(Day day, Time time) {
    for (Event event : this.getEvents()) {
      if (event.overlapsWith(day, time)) {
        return event;
      }
    }
    return null;
  }

  /**
   * Searches the Schedule to find the given OurEvent.
   * Returns the schedule's copy of the OurEvent if
   * the Schedule contains the OurEvent.
   * Throws an IllegalArgumentException if this Schedule
   * does not contain the given OurEvent.
   * @param event the OurEvent to search the Schedule for
   * @return the Schedule's copy of the given OurEvent
   * @throws IllegalArgumentException if event not in this Schedule
   */
  private Event findEvent(Event event) {
    for (Event e : this.events) {
      if (e.equals(event)) {
        return e;
      }
    }
    throw new IllegalArgumentException("OurEvent not in schedule!");
  }

  @Override
  public void modifyEventName(Event event, String newName) {
    this.findEvent(event).setName(newName);
  }

  @Override
  public void modifyEventInviteUser(Event event, String user) {
    this.findEvent(event).inviteUser(user);
  }

  @Override
  public void modifyEventUninviteUser(Event event, String user) {
    this.findEvent(event).uninviteUser(user);
  }

  @Override
  public void modifyEventLocation(Event event, String location) {
    this.findEvent(event).setPlace(location);
  }

  @Override
  public void modifyEventOnlineStatus(Event event, boolean isOnline) {
    this.findEvent(event).setOnline(isOnline);
  }

  @Override
  public void modifyEventStartDay(Event event, Day startDay) {
    this.findEvent(event).setStartDay(startDay);
    if (this.causesConflict(event)) {
      throw new IllegalArgumentException("Invalid start day. Causes conflict!");
    }
  }

  @Override
  public void modifyEventStartTime(Event event, Time startTime) {
    this.findEvent(event).setStartTime(startTime);
    if (this.causesConflict(event)) {
      throw new IllegalArgumentException("Invalid start time. Causes conflict!");
    }
  }

  @Override
  public void modifyEventEndDay(Event event, Day endDay) {
    this.findEvent(event).setEndDay(endDay);
    if (this.causesConflict(event)) {
      throw new IllegalArgumentException("Invalid end day. Causes conflict!");
    }
  }

  @Override
  public void modifyEventEndTime(Event event, Time endTime) {
    this.findEvent(event).setEndTime(endTime);
    if (this.causesConflict(event)) {
      throw new IllegalArgumentException("Invalid end time. Causes conflict!");
    }
  }

  /**
   * Gives back the name of the user who owns the schedule.
   * @return returns name of who owns schedule
   */
  public String getUser() {
    return this.user;
  }
}
