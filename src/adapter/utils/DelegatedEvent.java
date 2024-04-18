package adapter.utils;

import cs3500.nuplanner.provider.model.event.Day;
import cs3500.nuplanner.provider.model.event.Event;
import cs3500.nuplanner.provider.model.event.Time;
import model.OurEvent;
import model.User;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents an Event that delegates its functionality to an OurEvent.
 */
public class DelegatedEvent implements Event {
  private OurEvent ourEvent;

  public DelegatedEvent(OurEvent ourEvent) {
    this.ourEvent = ourEvent;
  }

  @Override
  public void setName(String name) {
    ourEvent.setName(name);
  }

  @Override
  public void setStartDay(Day startDay) {
    ourEvent.setStartDay(startDay.name());
  }

  @Override
  public void setStartTime(Time startTime) {
    ourEvent.setStartTime(startTime.toString());
  }

  @Override
  public void setEndDay(Day endDay) {
    ourEvent.setEndDay(endDay.name());
  }

  @Override
  public void setEndTime(Time endTime) {
    ourEvent.setEndTime(endTime.toString());
  }

  @Override
  public void setOnline(boolean online) {
    ourEvent.setOnlineStatus(online);
  }

  @Override
  public void setPlace(String place) {
    ourEvent.setLocation(place);
  }

  @Override
  public void inviteUser(String username) {
    User user = new User(username);
    List<User> updatedList = ourEvent.getInvitedUsers();
    updatedList.add(user);
    ourEvent.setInvitedUsers(updatedList);
  }

  @Override
  public void uninviteUser(String username) {
    List<User> updatedList = ourEvent.getInvitedUsers().stream()
            .filter(user -> !user.getName().equals(username))
            .collect(Collectors.toList());
    ourEvent.setInvitedUsers(updatedList);
  }

  @Override
  public boolean overlapsWith(Event event) {
    if (event instanceof DelegatedEvent) {
      return ourEvent.isOverLapping(((DelegatedEvent) event).ourEvent);
    }
    return false;
  }

  @Override
  public boolean overlapsWith(Day day, Time time) {
    return false;
  }

  @Override
  public String getName() {
    return ourEvent.getName();
  }

  @Override
  public Day getStartDay() {
    return Day.valueOf(ourEvent.getStartDay());
  }

  @Override
  public Time getStartTime() {
    return Time.fourDigitNumToTime(Integer.parseInt(ourEvent.getStartTime()));  // Assuming Time has a valueOf method that accepts string HHMM
  }

  @Override
  public Day getEndDay() {
    return Day.valueOf(ourEvent.getEndDay());
  }

  @Override
  public Time getEndTime() {
    return Time.fourDigitNumToTime(Integer.parseInt(ourEvent.getEndTime()));
  }

  @Override
  public boolean isOnline() {
    return ourEvent.getOnlineStatus();
  }

  @Override
  public String getLocation() {
    return ourEvent.getLocation();
  }

  @Override
  public List<String> getInvitees() {
    return ourEvent.getInvitedUsers().stream().map(User::getName).collect(Collectors.toList());
  }

  @Override
  public String getHost() {
    return ourEvent.getHost().getName();  // Assuming User has a getName method
  }

  @Override
  public boolean equalEvents(Event other) {
    if (other instanceof DelegatedEvent) {
      OurEvent otherEvent = ((DelegatedEvent) other).ourEvent;
      return ourEvent.getName().equals(otherEvent.getName()) &&
              ourEvent.getLocation().equals(otherEvent.getLocation()) &&
              ourEvent.getStartDay().equals(otherEvent.getStartDay()) &&
              ourEvent.getStartTime().equals(otherEvent.getStartTime()) &&
              ourEvent.getEndDay().equals(otherEvent.getEndDay()) &&
              ourEvent.getEndTime().equals(otherEvent.getEndTime()) &&
              ourEvent.getOnlineStatus() == otherEvent.getOnlineStatus() &&
              ourEvent.getHost().equals(otherEvent.getHost()) &&
              ourEvent.getInvitedUsers().equals(otherEvent.getInvitedUsers());
    }
    return false;
  }

}
