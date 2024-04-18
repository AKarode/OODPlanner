package controller;

import model.OurEvent;
import model.User;
import java.util.List;

/**
 * Interface for defining features related to user and event management.
 */
public interface Features {

  /**
   * Adds a user and their schedule to the system from an XML file.
   *
   * @param xmlFilePath the XML file path containing the user's schedule
   */
  void addUser(String xmlFilePath);

  /**
   * Saves all schedules in the system to XML files in a specified directory.
   *
   * @param userName the name of the user whose schedule is to be saved
   * @param filepath the directory path where the schedules will be saved
   */
  void saveSchedules(String userName, String filepath);

  /**
   * Creates a new event.
   */
  void createEvent(String name, String location, boolean onlineStatus, String startDay,
                   String startTime, String endDay, String endTime,
                   User host, List<User> invitedUsers);

  /**
   * Modifies an existing event with new details.
   *
   * @param oldOurEvent  the old event to be modified
   * @param newOurEvent  the new event details to replace the old event
   */
  void modifyEvent(OurEvent oldOurEvent, OurEvent newOurEvent);

  /**
   * Removes an event from the system.
   *
   * @param ourEventId The ID of the event to remove.
   */
  void removeEvent(OurEvent ourEventId);

  /**
   * Opens the frame to create or modify an event.
   */
  void openEventFrame();

  /**
   * Switches the currently viewed user in the system.
   *
   * @param userId the ID of the user to switch to
   */
  void switchUser(String userId);

  void autoSchedule();
}
