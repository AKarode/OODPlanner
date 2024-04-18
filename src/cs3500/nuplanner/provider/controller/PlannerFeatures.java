package cs3500.nuplanner.provider.controller;

import cs3500.nuplanner.provider.model.centralsystem.CentralSystemModel;
import cs3500.nuplanner.provider.model.event.ReadOnlyEvent;
import cs3500.nuplanner.provider.model.schedule.Schedule;
import cs3500.nuplanner.provider.view.event.EventGUI;
import cs3500.nuplanner.provider.view.event.ScheduleEventGUI;

/**
 * Controller Interface of all features
 * that can be done in programs.
 */
public interface PlannerFeatures {
  /**
   * Launches Program with given model.
   * @param model CentralSystemModel
   */
  void launch(CentralSystemModel model);

  /**
   * Handles a click on a grid cell in the planner.
   *
   * @param time        Time of click spot
   * @param day         day of click spot
   * @param currentUser selected user
   * @param gui
   */
  void handleGridClick(int time, int day, String currentUser);

  /**
   * Instances a Prompt to add a Calendar.
   */
  void addCalendarPrompt();

  /**
   * Instances a Prompt to save a Calendar.
   */
  void saveCalendarPrompt();

  /**
   * Instances a Prompt create a event.
   */
  void createEventPrompt();

  /**
   * Instances a Prompt to schedule a Calendar.
   */
  void scheduleEventPrompt();

  /**
   * Change the currently displayed Schedule to
   * that of the selected user.
   */
  Schedule selectUser(String user);

  /**
   * Change the user to view the Schedule of.
   */
  void changeActiveUser();

  /**
   * Modify an OurEvent using the fields from the
   * given EventGUI.
   * Note: If the modification creates a schedule conflict,
   *       prompts the user to input a valid EventGUI.
   * @param gui the gui that passes info to the OurEvent
   */
  void modifyEvent(EventGUI gui, ReadOnlyEvent event);

  /**
   * Create a new OurEvent using the information from the
   * given EventGUI.
   * Note: If the OurEvent cannot be created, prompts the user
   *       to input a valid EventGUI.
   * @param gui the gui that passes info to the OurEvent
   */
  void createEvent(EventGUI gui);

  /**
   * Remove the OurEvent corresponding to the given
   * EventGUI.
   * @param gui the gui of the OurEvent to remove
   */
  void removeEvent(EventGUI gui, ReadOnlyEvent readOnlyEvent);

  /**
   * Schedule a new OurEvent according to a set strategy.
   * Finds the first opportunity to create the OurEvent with
   * the given scheduleEventGUI and minutes.
   * Note: If the OurEvent cannot be scheduled, prompts the user to
   *       input a valid duration.
   * @param scheduleEventGUI the gui of the OurEvent to schedule
   * @param minutes the duration of the OurEvent to schedule
   */
  void scheduleEvent(ScheduleEventGUI scheduleEventGUI);
}