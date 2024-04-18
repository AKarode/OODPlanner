package cs3500.nuplanner.provider.view.schedule;

import cs3500.nuplanner.provider.Utils;
import cs3500.nuplanner.provider.controller.PlannerFeatures;
import cs3500.nuplanner.provider.model.centralsystem.ReadOnlyCentralSystemModel;
import cs3500.nuplanner.provider.model.event.Event;
import cs3500.nuplanner.provider.model.schedule.Schedule;

import java.io.IOException;
import java.util.List;


/**
 * The text-user interface converts a Schedule into a text representation.
 */
public class WeeklyPlannerTUI implements ScheduleFrame {
  private ReadOnlyCentralSystemModel model;
  private final Appendable output;

  public WeeklyPlannerTUI(ReadOnlyCentralSystemModel model, Appendable output) {
    this.model = model;
    this.output = output;
  }

  public WeeklyPlannerTUI(ReadOnlyCentralSystemModel model) {
    this.model = model;
    this.output = System.out;
  }

  /**
   * Add a features to the view to do command callback.
   *
   * @param f features
   */
  @Override
  public void addFeatures(PlannerFeatures f) {
    // Implement if needed. Not Feature Desired
  }

  @Override
  public void changeActiveUser() {
    // Implement if needed.
  }

  @Override
  public String toString() {
    String str = "";
    for (int schIdx = 0; schIdx < model.getSchedules().size(); schIdx++) {
      str = str + representSchedule(model.getSchedules().get(schIdx));
    }
    return str;
  }

  private String representSchedule(Schedule user) {
    List<Event> events = user.getEvents();
    String[] days =
        { "Sunday:", "Monday:", "Tuesday:", "Wednesday:", "Thursday:", "Friday:", "Saturday:" };
    String str = "";
    str = str + "User: " + user.getUser() + "\n";

    for (Event event : events) {
      int dayVal = event.getStartDay().getDayValue();
      days[dayVal] = days[dayVal] + "\n" + formatEvent(event);
    }

    for (String day : days) {
      str = str + day + "\n";
    }
    return str;
  }

  private String formatEvent(Event event) {
    String value = "";
    value = value + "\t\tname: " + event.getName() + "\n";
    value = value + "\t\ttime: "
            + event.getStartDay() + ": " + Utils.timeToString(event.getStartTime())
            + " -> " + event.getStartDay() + ": " + Utils.timeToString(event.getEndTime()) + "\n";
    value = value + "\t\tlocation: " + event.getLocation() + "\n";
    value = value + "\t\tonline: " + event.isOnline() + "\n";

    for (int userIdx = 0; userIdx < event.getInvitees().size(); userIdx++) {
      if (userIdx != 0) {
        value = value + "\t\t";
      } else {
        value = value + "\t\tinvitees: ";
      }
      value = value + event.getInvitees().get(userIdx);
      if (userIdx != event.getInvitees().size() - 1) {
        value = value + "\n";
      }
    }

    return value;
  }

  /**
   * Display this view.
   */
  @Override
  public void display() {
    try {
      output.append(toString());
    } catch (IOException e) {
      throw new RuntimeException("Cant add to Output!");
    }
  }

  @Override
  public void refresh() {
    System.out.println("Refreshed");
  }

  @Override
  public String addCalendarPrompt() {
    //TODO: Filler;
    return null;
  }

  @Override
  public void updateComboBox(ReadOnlyCentralSystemModel model) {
    // TODO: Implement if needed
  }

  @Override
  public String getCurrentUser() {
    //TODO filler
    return "";
  }

  @Override
  public String saveCalendarPrompt() {
    //TODO: Filler;
    return null;
  }

  /**
   * Make a new Error prompt to display errors from Model
   *
   * @param message Message to display
   */
  @Override
  public void makeErrorPopUp(String message) {
    //TODO: Implement if needed
  }
}
