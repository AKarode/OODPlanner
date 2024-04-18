import model.OurEvent;
import model.User;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.ArrayList;
import java.util.List;

class EventTest {

  // Helper method to create a mock user
  private User createUser(String name) {
    return new User(name);
  }

  @Test
  void testEventsOverlap() {
    User host = createUser("Host");
    List<User> invitedUsers = new ArrayList<>();
    invitedUsers.add(createUser("User1"));
    invitedUsers.add(createUser("User2"));

    // OurEvent 1: Monday 10:00 - 11:00
    OurEvent ourEvent1 = new OurEvent("Morning Meeting", "Office", false, "Monday",
        "1000", "Monday", "1100", host, invitedUsers);

    // OurEvent 2: Overlaps with OurEvent 1, same day, 10:30 - 12:00
    OurEvent ourEvent2 = new OurEvent("Extended Meeting", "Office", false, "Monday",
        "1030", "Monday", "1200", host, invitedUsers);

    assertTrue(ourEvent1.isOverLapping(ourEvent2), "Event1 should overlap with Event2");
  }

  @Test
  void testEventsDoNotOverlap() {
    User host = createUser("Host");
    List<User> invitedUsers = new ArrayList<>();
    invitedUsers.add(createUser("User1"));
    invitedUsers.add(createUser("User2"));

    // OurEvent 1: Monday 10:00 - 11:00
    OurEvent ourEvent1 = new OurEvent("Morning Meeting", "Office", false, "Monday",
        "1000", "Monday", "1100", host, invitedUsers);

    // OurEvent 3: Does not overlap with OurEvent 1, same day, 11:00 - 12:00
    OurEvent ourEvent3 = new OurEvent("Afternoon Meeting", "Office", false, "Monday",
        "1100", "Monday", "1200", host, invitedUsers);

    assertFalse(ourEvent1.isOverLapping(ourEvent3), "Event1 should not overlap with Event3");
  }

  @Test
  void testEventOverlapAcrossDays() {
    User host = createUser("Host");
    List<User> invitedUsers = new ArrayList<>();
    invitedUsers.add(createUser("User1"));

    // OurEvent 4: Sunday 23:00 - Monday 01:00
    OurEvent ourEvent4 = new OurEvent("Late Night Meeting", "Online", true,
        "Sunday", "2300", "Monday", "0100", host, invitedUsers);

    // OurEvent 5: Starts right after OurEvent 4 ends, no overlap, Monday 01:00 - 02:00
    OurEvent ourEvent5 = new OurEvent("Early Morning Meeting", "Online", true,
        "Monday", "0100", "Monday", "0200", host, invitedUsers);

    assertFalse(ourEvent4.isOverLapping(ourEvent5), "Event4 should not overlap with Event5");
  }
}
