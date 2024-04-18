package cs3500.nuplanner.provider.model.centralsystem;

import cs3500.nuplanner.provider.model.event.Event;
import cs3500.nuplanner.provider.model.event.EventBuilder;

public interface SystemStrategy {
  /**
   * Schedule an OurEvent in the given user's Schedule
   * with the given duration.
   * @param system the System to schedule an OurEvent in
   * @param event the OurEvent to add to the system
   * @param duration the duration of the OurEvent to add
   * @throws IllegalArgumentException if the OurEvent cannot be added to the system
   */
  void scheduleEvent(CentralSystemModel system, Event event, int duration);
}
