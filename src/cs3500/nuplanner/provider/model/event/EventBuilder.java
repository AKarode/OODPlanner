package cs3500.nuplanner.provider.model.event;

import java.util.ArrayList;
import java.util.List;

import cs3500.nuplanner.provider.Utils;
import cs3500.nuplanner.provider.model.event.Day;



/**
 * An OurEvent Builder is has default values, that can be set
 * and is used to create an OurEvent.
 */
public class EventBuilder {
  private String name;
  private Day startDay;
  private Time startTime;
  private Day endDay;
  private Time endTime;
  private boolean online;
  private String place;
  private String host;
  private List<String> invitees;

  /**
   * Initializes the default values for an OurEvent.
   */
  public EventBuilder() {
    this.name = "";
    this.startDay = Day.Sunday;
    this.startTime = new Time(0, 0);
    this.endDay = Day.Saturday;
    this.endTime = new Time(0, 0);
    this.online = false;
    this.place = "";
    this.host = "";
    this.invitees = new ArrayList<>();
  }

  /**
   * Takes in an OurEvent and makes a builder that has all
   * the OurEvent's attributes.
   * @param event the OurEvent to copy
   * @return a builder copy of the OurEvent
   */
  public EventBuilder copyEvent(ReadOnlyEvent event) {
    this.name = event.getName();
    this.startDay = event.getStartDay();
    this.startTime = event.getStartTime();
    this.endDay = event.getEndDay();
    this.endTime = event.getEndTime();
    this.online = event.isOnline();
    this.place = event.getLocation();
    this.host = event.getHost();
    this.invitees.addAll(event.getInvitees());
    return this;
  }

  /**
   * Sets Name Param.
   * @param name Name of OurEvent
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Sets startDay Param.
   * @param startDay starting day of event
   */
  public void setStartDay(String startDay) {
    String formattedDay = Utils.capitalize(startDay);
    switch (formattedDay) {
      case "Sunday":
        this.startDay = Day.Sunday;
        break;
      case "Monday":
        this.startDay = Day.Monday;
        break;
      case "Tuesday":
        this.startDay = Day.Tuesday;
        break;
      case "Wednesday":
        this.startDay = Day.Wednesday;
        break;
      case "Thursday":
        this.startDay = Day.Thursday;
        break;
      case "Friday":
        this.startDay = Day.Friday;
        break;
      case "Saturday":
        this.startDay = Day.Saturday;
        break;
      default:
        throw new IllegalArgumentException("Invalid Input for Day");
    }
  }

  /**
   * Sets startTime Param.
   * @param startTime Start time of the event
   * @throws IllegalArgumentException if number is invalid
   */
  public void setStartTime(Time startTime) {
    if (startTime.getHour() < 0 || startTime.getHour() > 23) {
      throw new IllegalArgumentException("Invalid hour");
    }
    if (startTime.getMinute() < 0 || startTime.getMinute() > 59) {
      throw new IllegalArgumentException("Invalid minute");
    }
    this.startTime = startTime;
  }

  /**
   * Sets endDay Param.
   * @param endDay Ending day of event
   */
  public void setEndDay(String endDay) {
    String formattedDay = Utils.capitalize(endDay);
    switch (formattedDay) {
      case "Sunday":
        this.endDay = Day.Sunday;
        break;
      case "Monday":
        this.endDay = Day.Monday;
        break;
      case "Tuesday":
        this.endDay = Day.Tuesday;
        break;
      case "Wednesday":
        this.endDay = Day.Wednesday;
        break;
      case "Thursday":
        this.endDay = Day.Thursday;
        break;
      case "Friday":
        this.endDay = Day.Friday;
        break;
      case "Saturday":
        this.endDay = Day.Saturday;
        break;
      default:
        throw new IllegalArgumentException("Invalid Input for Day");
    }
  }

  /**
   * Sets endTime Param.
   * @param endTime Ending time of OurEvent
   * @throws IllegalArgumentException if number is invalid
   */
  public void setEndTime(Time endTime) {
    if (endTime.getHour() < 0 || endTime.getHour() > 23) {
      throw new IllegalArgumentException("Invalid hour");
    }
    if (endTime.getMinute() < 0 || endTime.getMinute() > 59) {
      throw new IllegalArgumentException("Invalid minute");
    }
    this.endTime = endTime;
  }

  /**
   * Sets online Param.
   * @param online Online status of event.
   */
  public void setOnline(boolean online) {
    this.online = online;
  }

  /**
   * Sets place Param.
   * @param place Place of event
   */
  public void setPlace(String place) {
    this.place = place;
  }

  /**
   * Set the host for an OurEvent.
   * @param host the host to set for the OurEvent
   */
  public void setHost(String host) {
    this.host = host;
  }

  /**
   * Adds a user to the list of users.
   * @param user user to add
   */
  public void addUsers(String user) {
    this.invitees.add(user);
  }

  /**
   * Removes a user from the list of users.
   * @param user user to remove
   */
  public void removeUsers(String user) {
    this.invitees.remove(user);
  }

  public void setInvitees(List<String> invitees) {
    this.invitees = new ArrayList<>();
    this.invitees.addAll(invitees);
  }

  /**
   * Using the parameters given by the Builder,
   * Makes and event and sets all the values accordingly.
   * @return OurEvent made
   */
  public UserEvent build() {
    return new UserEvent(name,
            startDay, startTime, endDay, endTime,
            online, place, host, invitees);
  }
}