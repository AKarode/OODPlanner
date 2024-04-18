package model;

/**
 * The IScheduler interface defines the operations for scheduling and managing events,
 * as well as adding, removing, and retrieving users within a scheduling system. It provides
 * methods to manipulate user entries and events effectively.
 */
public interface PlannerModel extends ReadOnlyPlannerModel {
  void addUser(User u);

  void removeUser(User u);

  void removeEvent(OurEvent ourEvent);

  User getUser(String name);

  void createEvent(OurEvent e);

  void addUserFromXML(String filePath);

  void exportUserScheduleToXML(String username, String filePath);

  void updateEvent(OurEvent originalOurEvent, OurEvent updatedOurEvent);

  User getCurrentUser();

  void setCurrentUser(User newUser);

}
