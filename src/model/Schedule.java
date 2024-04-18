package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents a schedule associated with a user. This class
 * allows for the management of ourEvents within
 * the user's schedule, including adding and removing ourEvents. It ensures that no
 * ourEvents overlap with
 * each other.
 */
public class Schedule {
  private User owner;
  private List<OurEvent> ourEvents;

  //Deep Copy Constructor
  public Schedule(Schedule other) {
    this.owner = other.owner;
    this.ourEvents = new ArrayList<>(other.ourEvents);
  }

  public Schedule(User owner) {
    this.owner = owner;
    this.ourEvents = new ArrayList<>();
  }

  public User getOwner() {
    return owner;
  }

  public void setOwner(User owner) {
    this.owner = owner;
  }

  /**
   * Provides a read-only view of the ourEvents in the schedule.
   * This ensures that external modifications to the list
   * can't directly affect the internal list of ourEvents.
   *
   * @return A read-only List of ourEvents.
   */
  public List<OurEvent> getEvents() {
    return Collections.unmodifiableList(ourEvents);
  }

  /**
   * Adds an event to the schedule, ensuring no overlap with existing ourEvents.
   *
   * @param e the event to add
   * @throws IllegalArgumentException if the
   *                                  event overlaps with any existing event
   */
  public void addEvent(OurEvent e) {
    for (OurEvent ourEvent : this.ourEvents) {
      if (ourEvent.isOverLapping(e)) {
        throw new IllegalArgumentException("OurEvent overlaps with another ourEvent");
      }
    }
    this.ourEvents.add(e);
  }

  public void removeEvent(OurEvent e) {
    this.ourEvents.remove(e);
  }
}
