package cs3500.nuplanner.provider.model.centralsystem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import cs3500.nuplanner.provider.model.event.Date;
import cs3500.nuplanner.provider.model.event.Day;
import cs3500.nuplanner.provider.model.event.Event;
import cs3500.nuplanner.provider.model.event.EventBuilder;
import cs3500.nuplanner.provider.model.event.Time;
import cs3500.nuplanner.provider.model.event.UserEvent;

public class AnyTimeStrategy implements SystemStrategy {
  @Override
  public void scheduleEvent(CentralSystemModel system, Event event, int duration) {
    Event openTime = this.findHours(system, event, duration);

    if (openTime == null) {
      throw new IllegalArgumentException("OurEvent cannot be scheduled!");
    }
    else {
      system.createEvent(event.getHost(), openTime);
    }
  }

  protected Event findHours(CentralSystemModel system, Event event, int duration) {
    Date currentDate;
    Event currentEvent;
    for (int day = 0; day <= 6; day++) {
      for (int hour = 0; hour <= 23; hour++) {
        for (int minute = 0; minute <= 59; minute++) {
          currentDate =  new Date(Day.valueToDay(day), new Time(hour, minute));
          currentEvent = this.makeEvent(event, currentDate, duration);
//          System.out.print(currentEvent.getStartDay() + "," + currentEvent.getStartTime());
//          System.out.println("->" + currentEvent.getEndDay() + "," + currentEvent.getEndTime());
          if (findAvailability(system, currentEvent)) {
            return currentEvent;
          }
        }
      }
    }
    return null;
  }

  protected Boolean findAvailability(CentralSystemModel system, Event currentEvent) {
    List<String> users = new ArrayList<>(Collections.singleton(currentEvent.getHost()));
    users.addAll(currentEvent.getInvitees());
    boolean openSlot;
    openSlot = true;
    for (String user : users) {
      for (Event e : system.selectUser(user).getEvents()) {
        if (e.overlapsWith(currentEvent)) {
          openSlot = false;
        }
      }
    }
    return openSlot;
  }

  protected Event makeEvent(Event event, Date date, int duration) {
    Date endDate = date.addTime(duration);
    Day scheduledStartDay = date.getDay();
    Time scheduledStartTime = date.getTime();
    Day scheduledEndDay = endDate.getDay();
    Time scheduledEndTime = endDate.getTime();

    EventBuilder scheduledEvent = new EventBuilder();
    scheduledEvent.setName(event.getName());
    scheduledEvent.setHost(event.getHost());
    scheduledEvent.setPlace(event.getLocation());
    scheduledEvent.setOnline(event.isOnline());
    scheduledEvent.setInvitees(event.getInvitees());
    scheduledEvent.setStartDay(scheduledStartDay.toString());
    scheduledEvent.setStartTime(scheduledStartTime);
    scheduledEvent.setEndDay(scheduledEndDay.toString());
    scheduledEvent.setEndTime(scheduledEndTime);

    return scheduledEvent.build();
  }
}
