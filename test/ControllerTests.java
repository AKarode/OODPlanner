package test;

import model.PlannerModelStub;
import view.CalendarUIStub;

public class ControllerTests.java {
  public static void main(String[] args) {
    PlannerModelStub model = new PlannerModelStub();
    CalendarUIStub view = new CalendarUIStub();
    PlannerController controller = new PlannerController(model, view);

    // Test adding a user
    controller.addUser("path/to/user.xml");
    System.out.println("Add User Test: " + (view.viewUpdated ? "Passed" : "Failed"));

    // Reset view update flag
    view.viewUpdated = false;

    // Test creating an event
    User host = new User("Host");
    controller.createEvent("Meeting", "Conference Room", true, "Monday",
        "0900", "Monday", "1000", host, Arrays.asList(host));
    System.out.println("Create Event Test: " + (!model.events.isEmpty() ? "Passed" : "Failed"));

    // Test modifying an event
    Event oldEvent = model.events.get(0);
    Event newEvent = new Event("Updated Meeting", "Online", true, "Monday",
        "0900", "Monday", "1100", host, Arrays.asList(host));
    controller.modifyEvent(oldEvent, newEvent);
    System.out.println("Modify Event Test: " + (model.events.contains(newEvent) && !model.events.contains(oldEvent) ?
        "Passed" : "Failed"));

    // Test removing an event
    controller.removeEvent(newEvent);
    System.out.println("Remove Event Test: " + (model.events.isEmpty() ? "Passed" : "Failed"));

    // Test switching user
    controller.switchUser("testUser");
    System.out.println("Switch User Test: " + ("testUser".equals(model.getUser("testUser").getName()) ?
        "Passed" : "Failed"));
  }
}
