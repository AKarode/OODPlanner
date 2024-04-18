package cs3500.nuplanner.provider.model.centralsystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cs3500.nuplanner.provider.model.event.Day;
import cs3500.nuplanner.provider.model.event.Date;
import cs3500.nuplanner.provider.model.event.Event;
import cs3500.nuplanner.provider.model.event.EventBuilder;
import cs3500.nuplanner.provider.model.event.Time;

public class WorkHoursStrategy extends AnyTimeStrategy implements SystemStrategy {
  @Override
  protected Event findHours(CentralSystemModel system, Event event, int duration) {
    if (duration > 480) {
      throw new IllegalArgumentException("OurEvent extends past work hours!");
    }

    Date currentDate;
    Event currentEvent;
    Event openSlot;
    for (int day = 1; day <= 5; day++) {
      for (int hour = 9; hour <= 17; hour++) {
        for (int minute = 0; minute <= 59; minute++) {
          currentDate =  new Date(Day.valueToDay(day), new Time(hour, minute));
          currentEvent = this.makeEvent(event, currentDate, duration);
//          System.out.print(currentEvent.getStartDay() + "," + currentEvent.getStartTime());
//          System.out.println("->" + currentEvent.getEndDay() + "," + currentEvent.getEndTime());
          if (findAvailability(system, currentEvent)
                  && currentEvent.getEndTime().getHour() < 17) {
            return currentEvent;
          }
        }
      }
    }

    return null;
  }
}
