package adapter;

import adapter.utils.DelegatedEvent;
import adapter.utils.DelegatedSchedule;
import cs3500.nuplanner.provider.model.schedule.Schedule;
import model.CentralSystem;
import model.User;
import model.OurEvent;
import cs3500.nuplanner.provider.model.centralsystem.CentralSystemModel;
import cs3500.nuplanner.provider.model.event.Day;
import cs3500.nuplanner.provider.model.event.Event;
import cs3500.nuplanner.provider.model.event.Time;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ModelAdapter implements CentralSystemModel {
  private final CentralSystem delegate;

  public ModelAdapter(CentralSystem delegate) {
    this.delegate = delegate;
  }

  /**
   * Converts an OurEvent to an Event.
   * @param event
   * @return
   */
  public OurEvent convertEvent(Event event){
      DelegatedEvent delegatedEvent = (DelegatedEvent) event;
      List<User> invitees = delegatedEvent.getInvitees().stream()
              .map(delegate::getUser)
              .collect(Collectors.toList());
      return new OurEvent(delegatedEvent.getName(),delegatedEvent.getLocation(), delegatedEvent.isOnline(), delegatedEvent.getStartDay().toString() , delegatedEvent.getStartTime().toString(), delegatedEvent.getEndDay().toString(), delegatedEvent.getEndTime().toString(), delegate.getUser(delegatedEvent.getHost()), invitees);
  }


  @Override
  public void uploadSchedule(String file) {
    delegate.addUserFromXML(file);
  }

  @Override
  public void uploadSchedule(Schedule schedule) {

  }

  @Override
  public void saveSchedule(Schedule schedule) {
    delegate.exportUserScheduleToXML(schedule.getUser().toString(),"src/" + schedule.getUser() + ".xml");
  }

  @Override
  public void removeSchedule(String user) {
    delegate.removeUser(delegate.getUser(user));
  }

  @Override
  public Event getMatchingEvent(String user, Event e) {
    return null;
  }

  @Override
  public void createEvent(String user, Event event) {
    delegate.createEvent(convertEvent(event));
  }

  @Override
  public void removeEvent(String user, Event event) {
    delegate.removeEvent(convertEvent(event));
  }

  @Override
  public void modifyEventName(String user, Event event, String newName) {
    delegate.changeEventName(event.getName(), newName);
  }

  @Override
  public void modifyEventInviteUser(String user, Event event, String other) {
    List<User> invitedUser = Collections.singletonList(delegate.getUser(other));
    delegate.changeEventInvitedUsers(event.getName(), invitedUser);
  }

  @Override
  public void modifyEventUninviteUser(String user, Event event, String other) {
    List<User> invitedUser = Collections.singletonList(delegate.getUser(other));
    delegate.changeEventInvitedUsers(event.getName(), invitedUser);
  }

  @Override
  public void modifyEventLocation(String user, Event event, String location) {
    delegate.changeEventLocation(event.getName(), location, event.isOnline());

  }

  @Override
  public void modifyEventOnlineStatus(String user, Event event, boolean isOnline) {
    OurEvent newEvent = this.convertEvent(event);
    newEvent.setOnlineStatus(isOnline);

    delegate.updateEvent(this.convertEvent(event), newEvent);
  }

  @Override
  public void modifyEventStartDay(String user, Event event, Day startDay) {
    OurEvent newEvent = this.convertEvent(event);
    newEvent.setOnlineStatus(true);

    delegate.updateEvent(this.convertEvent(event), newEvent);
  }

  @Override
  public void modifyEventStartTime(String user, Event event, Time startTime) {
    delegate.changeEventTiming(event.getName(), event.getStartDay().toString(), startTime.toString(), event.getEndDay().toString(), event.getEndTime().toString());

  }

  @Override
  public void modifyEventEndDay(String user, Event event, Day endDay) {
    delegate.changeEventTiming(event.getName(), event.getStartDay().toString(), event.getStartTime().toString(), endDay.toString(), event.getEndTime().toString());
  }

  @Override
  public void modifyEventEndTime(String user, Event event, Time endTime) {
    delegate.changeEventTiming(event.getName(), event.getStartDay().toString(), event.getStartTime().toString(), event.getEndDay().toString(), endTime.toString());
  }

  @Override
  public void scheduleEvent(Event event, int duration) {
    delegate.createEvent(convertEvent(event));
  }

  @Override
  public void modifyEvent(String user, Event event, Event e) {
    delegate.updateEvent(convertEvent(event), convertEvent(e));
  }

  @Override
  public Schedule selectUser(String user) {
    DelegatedSchedule schedule = new DelegatedSchedule(delegate.getUser(user).getSchedule());
    return schedule;
  }

  @Override
  public Event viewUserEvent(String user, Day day, Time time) {
    DelegatedSchedule schedule = new DelegatedSchedule(delegate.getUser(user).getSchedule());
    List<Event> events = schedule.getEvents();

    for (Event e: events){
      if (e.getStartDay() == day && e.getStartTime() == time){
        return e;
      }
    }



    return null;
  }

  @Override
  public List<Schedule> getSchedules() {
//TODO
    List<User> users = delegate.getUsers();
    List<Schedule> userSchedules = new ArrayList<>();

    for (User u: users) {
//      userSchedules.add(u.getSchedule());
    }

    return userSchedules;
  }

  @Override
  public List<String> getUsers() {
    List<User> users = delegate.getUsers();
    List<String> userStrings = new ArrayList<>();

    for (User user: users) {
      userStrings.add(user.toString());
    }

    return userStrings;
  }
}
