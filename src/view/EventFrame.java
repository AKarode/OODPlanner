package view;

import controller.Features;
import model.OurEvent;
import model.ReadOnlyPlannerModel;
import model.User;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


/**
 * EventFrame provides a user interface for creating,
 * modifying, and viewing details of an ourEvent.
 * It extends JFrame and implements the PopUpFrame interface,
 * allowing for operations on OurEvent objects
 * within a graphical interface. Users can input ourEvent details,
 * invite other users, and indicate whether
 * the ourEvent is online or in-person.
 */
public class EventFrame extends JFrame implements PopUpFrame {

  private CalendarUI view;
  private Features features;
  private OurEvent ourEvent;
  private ReadOnlyPlannerModel model;

  private JTextField eventNameField;
  private JTextField locationField;
  private JCheckBox isOnlineCheckbox;
  private JComboBox<String> startingDayCombo;
  private JTextField startingTimeField;
  private JComboBox<String> endingDayCombo;
  private JTextField endingTimeField;

  /**
   * Constructs an EventFrame window either with
   * a pre-existing OurEvent object for editing
   * or without one for creating a new ourEvent.
   *
   * @param ourEvent The ourEvent to edit, or null if creating a new ourEvent.
   */
  public EventFrame(OurEvent ourEvent, ReadOnlyPlannerModel model, Features features, CalendarUI view) {
    this.ourEvent = ourEvent;
    this.model = model;
    this.features = features;
    this.view = view;
    createAndShowGUI();
  }

  private JScrollPane selectedUser() {
    JList<String> usersList = createUserList();
    usersList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
    return new JScrollPane(usersList);
  }

  private void createAndShowGUI() {
    setTitle("OurEvent Details");
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
    createFields();

    JScrollPane usersScrollPane = selectedUser();

    JButton createEventButton = new JButton("Create ourEvent");
    JButton modifyEventButton = new JButton("Modify ourEvent");
    JButton removeEventButton = new JButton("Remove ourEvent");
    createEventButton.addActionListener(e -> {
      if (validateInput()) {
        JList<String> usersList = (JList<String>) usersScrollPane.getViewport().getView();
        List<User> selectedUsers = Arrays.stream(usersList
                .getSelectedValuesList().toArray(new String[0]))
            .map(name -> model.getUser(name))
            .collect(Collectors.toList());

        features.createEvent(
            eventNameField.getText(),
            locationField.getText(),
            isOnlineCheckbox.isSelected(),
            (String) startingDayCombo.getSelectedItem(),
            startingTimeField.getText(),
            (String) endingDayCombo.getSelectedItem(),
            endingTimeField.getText(),
            model.getCurrentUser(),
            selectedUsers
        );
        JOptionPane.showMessageDialog(this, "OurEvent created successfully.");
      } else {
        JOptionPane.showMessageDialog(this, "Please fill in all ourEvent details before creating.");
      }
      view.updateView();

    });

    modifyEventButton.addActionListener(e -> {
      if (ourEvent != null && validateInput()) {
        OurEvent newOurEvent = new OurEvent(
            eventNameField.getText(),
            locationField.getText(),
            isOnlineCheckbox.isSelected(),
            (String) startingDayCombo.getSelectedItem(),
            startingTimeField.getText(),
            (String) endingDayCombo.getSelectedItem(),
            endingTimeField.getText(),
            model.getCurrentUser(),
            Arrays.asList() // Similar user list retrieval as in createEvent
        );
        features.modifyEvent(ourEvent, newOurEvent);
        JOptionPane.showMessageDialog(this, "OurEvent modified successfully.");
      } else {
        JOptionPane.showMessageDialog(this, "Please fill in all ourEvent details before modifying.");
      }
      view.updateView();
    });


    removeEventButton.addActionListener(e -> {
      if (ourEvent != null) {
        features.removeEvent(ourEvent);
        JOptionPane.showMessageDialog(this, "OurEvent removed successfully.");
      } else {
        JOptionPane.showMessageDialog(this, "No ourEvent to remove.");
      }
      view.updateView();

    });

    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    add(createLabeledField("OurEvent name:", eventNameField));
    add(createLabeledField("Location:", locationField));
    add(isOnlineCheckbox);
    add(createLabeledField("Starting Day:", startingDayCombo));
    add(createLabeledField("Starting time:", startingTimeField));
    add(createLabeledField("Ending Day:", endingDayCombo));
    add(createLabeledField("Ending time:", endingTimeField));
    add(new JLabel("Available users"));
    add(usersScrollPane);
    add(createEventButton);
    add(modifyEventButton);
    add(removeEventButton);
    pack();
  }

  private void createFields() {
    eventNameField = new JTextField(ourEvent != null ? ourEvent.getName() : "");
    locationField = new JTextField(ourEvent != null ? ourEvent.getLocation() : "");
    isOnlineCheckbox = new JCheckBox("Is online", ourEvent != null && ourEvent.getOnlineStatus());
    startingDayCombo = new JComboBox<>(new String[]
        {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"});
    startingDayCombo.setSelectedItem(ourEvent != null ? ourEvent.getStartDay() : "Sunday");
    startingTimeField = new JTextField(ourEvent != null ? ourEvent.getStartTime() : "");
    endingDayCombo = new JComboBox<>(new String[]
        {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"});
    endingDayCombo.setSelectedItem(ourEvent != null ? ourEvent.getEndDay() : "Sunday");
    endingTimeField = new JTextField(ourEvent != null ? ourEvent.getEndTime() : "");
  }

  private JList<String> createUserList() {
    String[] userListArray = ourEvent != null ? ourEvent.getInvitedUsers().stream()
        .map(User::getName).toArray(String[]::new) : new String[]{};
    JList<String> usersList = new JList<>(userListArray);
    return usersList;
  }

  private boolean validateInput() {
    // Validation logic remains the same
    return !eventNameField.getText().trim().isEmpty()
        && !locationField.getText().trim().isEmpty()
        && !startingTimeField.getText().trim().isEmpty()
        && !endingTimeField.getText().trim().isEmpty();
  }

  private JPanel createLabeledField(String labelText, JComponent field) {
    // This method remains the same
    JPanel panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    JLabel label = new JLabel(labelText);
    label.setAlignmentX(Component.LEFT_ALIGNMENT);
    field.setAlignmentX(Component.LEFT_ALIGNMENT);
    panel.add(label);
    panel.add(field);
    return panel;
  }

  /**
   * Renders the EventFrame GUI, making it visible to the user.
   * This method is intended
   * to ensure that the GUI is displayed on the OurEvent
   * Dispatch Thread (EDT) for thread safety.
   */
  public void render() {
    SwingUtilities.invokeLater(() -> {
      new EventFrame(ourEvent, model, features, view).setVisible(true);
    });
  }

}
