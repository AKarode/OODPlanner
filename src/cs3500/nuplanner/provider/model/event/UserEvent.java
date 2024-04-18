package cs3500.nuplanner.provider.model.event;

import java.util.ArrayList;
import java.util.List;
import cs3500.nuplanner.provider.model.event.Day;


/**
 * An event is added to the schedule of one to many users.
 */
public class UserEvent implements Event {
  private String name;
  private String location;
  private Day startDay;
  private Day endDay;
  private Time startTime;
  private Time endTime;
  private int duration;
  private boolean online;
  private final String host;
  private final List<String> invitees;

  /**
   * A constructor to initialize a new OurEvent.
   * Throws an IllegalArgumentException if the
   * start or end time is not within 24 hours.
   * Only accepts valid days as input.
   *
   * @param name      the name of the event
   * @param startDay  the first day of the event
   * @param endDay    the last day of the event
   * @param startTime the starting time of the event
   * @param endTime   the ending time of the event
   * @param location  the location of the event
   * @param host      the use hosting the event
   * @param invitees  the users invited
   * @throws IllegalArgumentException if the startTime is not a
   *                                  valid time ("0000" <= time <= "2359")
   * @throws IllegalArgumentException if the endTime is not a
   *                                  valid time ("0000" <= time <= "2359")
   */
  public UserEvent(String name, Day startDay, Time startTime, Day endDay,
                   Time endTime, boolean online, String location,
                   String host, List<String> invitees) {
    this.name = name;
    this.location = location;
    this.startDay = startDay;
    this.startTime = startTime;
    this.endDay = endDay;
    this.endTime = endTime;
    this.host = host;
    this.online = online;
    this.invitees = new ArrayList<>();
    this.invitees.addAll(invitees);
  }

  /**
   * Constructor to make a copy of an event using a prexisting one.
   * @param e OurEvent to copy
   */
  public UserEvent(Event e) {
    this.name = e.getName();
    this.location = e.getLocation();
    this.startDay = e.getStartDay();
    this.startTime = e.getStartTime();
    this.endDay = e.getEndDay();
    this.endTime = e.getEndTime();
    this.host = e.getHost();
    this.online = e.isOnline();
    this.invitees = new ArrayList<>();
    this.invitees.addAll(e.getInvitees());
  }

  /**
   * A constructor to initialize a new OurEvent.
   * Throws an IllegalArgumentException if the
   * start or end time is not within 24 hours.
   * Only accepts valid days as input.
   *
   * @param name      the name of the event
   * @param location  the location of the event
   * @param host      the use hosting the event
   * @param invitees  the users invited
   * @throws IllegalArgumentException if the startTime is not a
   *                                  valid time ("0000" <= time <= "2359")
   * @throws IllegalArgumentException if the endTime is not a
   *                                  valid time ("0000" <= time <= "2359")
   */
  public UserEvent(String name, boolean online, String location,
                   String host, List<String> invitees) {
    this.name = name;
    this.location = location;
    this.host = host;
    this.online = online;
    this.invitees = new ArrayList<>();
    this.invitees.addAll(invitees);
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public String getHost() {
    return this.host;
  }

  @Override
  public Day getStartDay() {
    return this.startDay;
  }

  @Override
  public Time getStartTime() {
    return this.startTime;
  }

  @Override
  public Day getEndDay() {
    return this.endDay;
  }

  @Override
  public Time getEndTime() {
    return this.endTime;
  }

  @Override
  public boolean isOnline() {
    return online;
  }

  @Override
  public String getLocation() {
    return this.location;
  }

  @Override
  public List<String> getInvitees() {
    return this.invitees;
  }

  @Override
  public void setName(String name) {
    this.name = name;
  }

  @Override
  public void setStartDay(Day startDay) {
    this.startDay = startDay;
  }

  @Override
  public void setStartTime(Time startTime) {
    this.startTime = startTime;
  }

  @Override
  public void setEndDay(Day endDay) {
    this.endDay = endDay;
  }

  @Override
  public void setEndTime(Time endTime) {
    this.endTime = endTime;
  }

  @Override
  public void setOnline(boolean online) {
    this.online = online;
  }

  @Override
  public void setPlace(String place) {
    this.location = place;
  }

  @Override
  public void inviteUser(String user) {
    this.invitees.add(user);
  }

  @Override
  public void uninviteUser(String user) {
    if (this.invitees.contains(user)) {
      this.invitees.remove(user);
    }
    else {
      throw new IllegalArgumentException("User is not in list of invitees");
    }
  }

  @Override
  public boolean overlapsWith(Event event) {
    if (this.startDay.getDayValue() == event.getStartDay().getDayValue()
            && this.endDay.getDayValue() == event.getEndDay().getDayValue()) {
      return this.overlapsOnSameDay(this, event)
              || this.overlapsOnSameDay(event, this);
    }
    else if (this.endDay.getDayValue() < this.startDay.getDayValue()
            && event.getEndDay().getDayValue() < event.getStartDay().getDayValue()) {
      return true;
    }
    else if (this.endDay.getDayValue() < this.startDay.getDayValue()) {
      return this.overlapsOverWeek(this, event);
    }
    else if (event.getEndDay().getDayValue() < event.getStartDay().getDayValue()) {
      return this.overlapsOverWeek(event, this);
    }
    else {
      return this.overlapsOnDifferentDays(this, event)
              || this.overlapsOnDifferentDays(event, this);
    }
  }

  @Override
  public boolean overlapsWith(Day day, Time time) {
    int timeInt = Time.timeToFourDigitNum(time);
    int startTimeInt = Time.timeToFourDigitNum(this.getStartTime());
    int endTimeInt = Time.timeToFourDigitNum(this.getEndTime());
    if (this.getStartDay().getDayValue() < day.getDayValue()
            && this.getEndDay().getDayValue() > day.getDayValue()) {
      return true;
    }
    else if (this.getEndDay().getDayValue() < this.getStartDay().getDayValue()) {
      return overlapsIntoNextWeek(day, time);
    }
    else if (this.getStartDay().getDayValue() == day.getDayValue()
            && this.getEndDay().getDayValue() == day.getDayValue()) {
      return startTimeInt < timeInt && endTimeInt > timeInt;
    }
    else if (this.getStartDay().getDayValue() == day.getDayValue()
            && startTimeInt < timeInt) {
      return true;
    }
    else {
      return this.getEndDay().getDayValue() == day.getDayValue()
              && endTimeInt > timeInt;
    }
  }

  private boolean overlapsIntoNextWeek(Day day, Time time) {
    if (this.getStartDay().getDayValue() < day.getDayValue()) {
      return true;
    }
    else {
      return this.getStartDay().getDayValue() == day.getDayValue()
              && Time.timeToFourDigitNum(this.getStartTime()) < Time.timeToFourDigitNum(time);
    }
  }

  /**
   * Compares the time period of this OurEvent to the time period of
   * other OurEvent to see if they overlap given that they are on the
   * same day.
   * @param event1 the first OurEvent to compare
   * @param event2 the second OurEvent to compare
   * @return a boolean representing whether the Events overlap
   */
  private boolean overlapsOnSameDay(Event event1, Event event2) {
    int event1StartTime = Time.timeToFourDigitNum(event1.getStartTime());
    int event1EndTime = Time.timeToFourDigitNum(event1.getEndTime());
    int event2StartTime = Time.timeToFourDigitNum(event2.getStartTime());
    int event2EndTime = Time.timeToFourDigitNum(event2.getEndTime());
    if (event1StartTime > event2StartTime
            && event1StartTime < event2EndTime) {
      return true;
    }
    if (event1StartTime > event2StartTime
            && event1EndTime < event2EndTime) {
      return true;
    }
    else if (event1StartTime == event2StartTime
            && event1EndTime < event2EndTime) {
      return true;
    }
    else if (event1StartTime < event2StartTime
            && event1EndTime == event2EndTime) {
      return true;
    }
    else {
      return event1StartTime == event2StartTime
              && event1EndTime == event2EndTime;
    }
  }

  /**
   * Compares the time period of this OurEvent to the time period of
   * other OurEvent to see if they overlap given that they are on the
   * different days and this event does not extend into next week.
   * @param event1 the first OurEvent to compare
   * @param event2 the second OurEvent to compare
   * @return a boolean representing whether the Events overlap
   */
  private boolean overlapsOnDifferentDays(Event event1, Event event2) {
    int event1StartTime = Time.timeToFourDigitNum(event1.getStartTime());
    int event1EndTime = Time.timeToFourDigitNum(event1.getEndTime());
    int event2StartTime = Time.timeToFourDigitNum(event2.getStartTime());
    int event2EndTime = Time.timeToFourDigitNum(event2.getEndTime());
    if (event1.getStartDay().getDayValue() == event2.getEndDay().getDayValue()
            && event1StartTime >= event2EndTime) {
      return false;
    }
    else if (event1.getEndDay().getDayValue() == event2.getStartDay().getDayValue()
            && event1EndTime <= event2StartTime) {
      return false;
    }
    else if (event1.getStartDay().getDayValue() > event2.getStartDay().getDayValue()
            && event1.getStartDay().getDayValue() < event2.getEndDay().getDayValue()) {
      return true;
    }
    else if (event1.getStartDay().getDayValue() == event2.getStartDay().getDayValue()
            && event1.getEndDay().getDayValue() < event2.getEndDay().getDayValue()) {
      return true;
    }
    else {
      return event1.getStartDay().getDayValue() < event2.getStartDay().getDayValue()
              && event1.getEndDay().getDayValue() == event2.getEndDay().getDayValue();
    }
  }

  /**
   * Compares the time period of this OurEvent to the time period of
   * other OurEvent to see if they overlap given that they are on the
   * different days and the given OurEvent extends into next week.
   * @param overlappingEvent the first OurEvent to compare
   * @param event2 the second OurEvent to compare
   * @return a boolean representing whether the Events overlap
   */
  private boolean overlapsOverWeek(Event overlappingEvent, Event event2) {
    int overlappingEventStartTime = Time.timeToFourDigitNum(overlappingEvent.getStartTime());
    int event2EndTime = Time.timeToFourDigitNum(event2.getEndTime());
    if (event2.getStartDay().getDayValue() > overlappingEvent.getStartDay().getDayValue()
            || event2.getEndDay().getDayValue() > overlappingEvent.getStartDay().getDayValue()) {
      return true;
    }
    else {
      return overlappingEvent.getStartDay().getDayValue() == event2.getEndDay().getDayValue()
              && overlappingEventStartTime < event2EndTime;
    }
  }

  @Override
  public boolean equalEvents(Event e) {
    if (!this.name.equals(e.getName())) {
      return false;
    }
    if (!this.location.equals(e.getLocation())) {
      return false;
    }
    if (!this.host.equals(e.getHost())) {
      return false;
    }
    if (Time.timeToFourDigitNum(this.startTime) != Time.timeToFourDigitNum(e.getStartTime())) {
      return false;
    }
    if (Time.timeToFourDigitNum(this.endTime) != Time.timeToFourDigitNum(e.getEndTime())) {
      return false;
    }
    if (this.startDay != e.getStartDay()) {
      return false;
    }
    if (this.endDay != e.getEndDay()) {
      return false;
    }
    if (e.isOnline() != this.online) {
      return false;
    }
    for (int i = 0; i < this.invitees.size(); i++) {
      if (!this.invitees.get(0).equals(e.getInvitees().get(0))) {
        return false;
      }
    }
    return true;
  }
}
