package adapter.utils;

import cs3500.nuplanner.provider.model.event.Day;
import cs3500.nuplanner.provider.model.event.Event;
import cs3500.nuplanner.provider.model.event.Time;
import model.OurEvent;
import model.Schedule;
import model.User;

import java.util.List;
import java.util.stream.Collectors;


public class DelegatedSchedule implements cs3500.nuplanner.provider.model.schedule.Schedule {
  private Schedule schedule;

  public DelegatedSchedule(Schedule schedule) {
    this.schedule = schedule;
  }

  @Override
  public void addEvent(Event e) {
    User hostUser = new User(e.getHost());
    List<User> invitedUsers = e.getInvitees().stream()
            .map(User::new)
            .collect(Collectors.toList());

    this.schedule.addEvent(new OurEvent(e.getName(), e.getLocation(), e.isOnline(), e.getStartDay().toString(), e.getStartTime().toString(), e.getEndDay().toString(), e.getEndTime().toString(), hostUser, invitedUsers));
  }

  @Override
  public void removeEvent(Event e) {
    User hostUser = new User(e.getHost());
    List<User> invitedUsers = e.getInvitees().stream()
            .map(User::new)
            .collect(Collectors.toList());

    this.schedule.removeEvent(new OurEvent(e.getName(), e.getLocation(), e.isOnline(), e.getStartDay().toString(), e.getStartTime().toString(), e.getEndDay().toString(), e.getEndTime().toString(), hostUser, invitedUsers));
  ;}

  @Override
  public void modifyEventName(Event event, String newName){
    this.schedule.getEvents().stream().filter(e -> e.equals(event)).forEach(e -> e.setName(newName));

  }

  @Override
  public void modifyEventInviteUser(Event event, String user) {
    event.inviteUser(user);
  }

  @Override
  public void modifyEventUninviteUser(Event event, String user) {
    event.uninviteUser(user);
  }

  @Override
  public void modifyEventLocation(Event event, String location) {

  }

  @Override
  public void modifyEventOnlineStatus(Event event, boolean isOnline) {
    event.setOnline(isOnline);
  }

  @Override
  public void modifyEventStartDay(Event event, Day startDay) {
    event.setStartDay(startDay);
  }

  @Override
  public void modifyEventStartTime(Event event, Time startTime) {
    event.setStartTime(startTime);
  }

  @Override
  public void modifyEventEndDay(Event event, Day endDay) {
    event.setEndDay(endDay);
  }

  @Override
  public void modifyEventEndTime(Event event, Time endTime) {
    event.setEndTime(endTime);
  }

  @Override
  public boolean causesConflict(Event event) {
    return schedule.getEvents().stream().anyMatch(e -> e.isOverLapping(new OurEvent(event.getName(),
            event.getLocation()
            , event.isOnline(), event.getStartDay().toString(), event.getStartTime().toString(),
            event.getEndDay().toString(),
            event.getEndTime().toString(),
            new User(event.getHost()),
            event.getInvitees().stream().map(u -> new User(u)).collect(Collectors.toList()))));
  }

  @Override
  public List<Event> getEvents() {
    List<Event> delegatedEvents = schedule.getEvents().stream()
            .map(DelegatedEvent::new)
            .collect(Collectors.toList());

    return delegatedEvents;
  }

  @Override
  public String getUser() {
    return schedule.getOwner().getName();
  }

  @Override
  public Event viewEvent(Day day, Time time) {
    return null;
  }
}
