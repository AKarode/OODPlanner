import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import model.*;
import model.OurEvent;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

class XMLReadWriteTests {
  private final String testFilePath = "test/xmlfiles/test_schedule.xml";

  @AfterEach
  void tearDown() {
    // Cleanup the created test file after each test
    File file = new File(testFilePath);
    if (file.exists()) {
      file.delete();
    }
  }

  @Test
  void testWriteXML() throws Exception {
    // Prepare a User with a schedule for testing
    User user = new User("Test User");
    OurEvent testOurEvent = new OurEvent(
        "Test OurEvent",
        "Test Location",
        true,
        "Monday",
        "0900",
        "Monday",
        "1000",
        user,
        Arrays.asList(new User("Invited User 1"), new User("Invited User 2")));
    user.getSchedule().addEvent(testOurEvent);

    // Write the user's schedule to XML
    XMLWriter.writeXML(user, testFilePath);

    // Read back the file and verify its content
    String content = new String(Files.readAllBytes(Paths.get(testFilePath)));
    System.out.println(content);
    // Expected content snippets to verify
    List<String> expectedSnippets = Arrays.asList(
        "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>",
        "<schedule id=\"Test User\">",
        "<name>Test OurEvent</name>",
        "<start-day>Monday</start-day>",
        "<start>0900</start>",
        "<end-day>Monday</end-day>",
        "<end>1000</end>",
        "<online>true</online>",
        "<place>Test Location</place>",
        "<uid>Invited User 1</uid>",
        "<uid>Invited User 2</uid>"
    );

    for (String snippet : expectedSnippets) {
      assertTrue(content.contains(snippet),
          "Missing expected content: " + snippet);
    }
  }

  @Test
  public void testScheduleCreationFromXML() {
    // Initialize the central system
    CentralSystem centralSystem = new CentralSystem();

    String xmlFilePath = "src/input.xml"; // Update this to your actual XML file path
    User userFromXML = XMLReader.readXML(xmlFilePath);

    // Add the user and their schedule to the central system
    centralSystem.addUser(userFromXML);

    // Assuming the XML file defines a user named
    // "Prof. Lucia" and contains specific events to verify
    User user = centralSystem.getUser("Prof. Lucia");
    assertNotNull("User 'Prof. Lucia' should be found " +
        "in the central system after reading from XML", user);
    assertFalse("User 'Prof. Lucia''s schedule should not " +
        "be empty after reading from XML", user.getSchedule().getEvents().isEmpty());

    // Example: Verify details from an ourEvent in the XML to ensure correct parsing and addition
    String expectedEventName = "CS3500 Morning Lecture"; // Adjust this based on your XML content
    // Here you may want to look for a specific ourEvent or assume the first ourEvent should match
    OurEvent ourEvent = user.getSchedule().getEvents().stream()
        .filter(e -> e.getName().equals(expectedEventName))
        .findFirst()
        .orElse(null);

    assertNotNull("The ourEvent named '" + expectedEventName
        + "' should exist in 'Prof. Lucia''s schedule", ourEvent);
    assertEquals("OurEvent name should match expected", expectedEventName, ourEvent.getName());
    // Further assertions to verify other details like time, location, invited users, etc.
  }


}
