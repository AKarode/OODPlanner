package view;

import model.ReadOnlyPlannerModel;
import model.User;

import javax.swing.*;
import java.awt.*;


/**
 * The AutoEventFrame class provides a graphical
 * user interface for automatically scheduling events.
 * It allows users to input event details such as name,
 * location, duration, and whether the event is online.
 * It also displays a
 * list of available users to include in the event.
 */
public class AutoEventFrame extends JPanel {

  private ReadOnlyPlannerModel model;
  private User currentUser;
  private JTextField eventNameField;
  private JTextField locationField;
  private JCheckBox onlineCheckBox;
  private JTextField durationField;
  private JList<String> usersList;
  private JButton scheduleButton;
  /**
   * Constructs an AutoEventFrame using a model.
   * Initializes the UI components based on the model's state,
   * particularly the first user if any.
   *
   * @param model the read-only planner model to use for getting initial data
   */
  public AutoEventFrame(ReadOnlyPlannerModel model) {
    this.model = model;
    System.out.println(model.users);

    if (!model.getUsers().isEmpty()) {
      this.currentUser = model.getUsers().get(0);
    }
  }

  /**
   * Constructs an AutoEventFrame without a model.
   * Initializes the UI components for event
   * creation including fields for event name, location,
   * online status, duration, a list of users,
   * and a button to schedule the event.
   */
  public AutoEventFrame() {
    User currentUser;
    JTextField eventNameField;
    JTextField locationField;
    JCheckBox onlineCheckBox;
    JTextField durationField;
    JList<String> usersList;
    JButton scheduleButton;

    setLayout(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();

    // OurEvent Name
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    add(new JLabel("OurEvent name:"), gbc);

    eventNameField = new JTextField(20);
    gbc.gridx = 1;
    gbc.gridy = 0;
    add(eventNameField, gbc);

    // Location
    gbc.gridx = 0;
    gbc.gridy = 1;
    add(new JLabel("Location:"), gbc);

    locationField = new JTextField(20);
    gbc.gridx = 1;
    gbc.gridy = 1;
    add(locationField, gbc);

    // Online Check Box
    onlineCheckBox = new JCheckBox("Not online");
    gbc.gridx = 1;
    gbc.gridy = 2;
    add(onlineCheckBox, gbc);

    // Duration
    gbc.gridx = 0;
    gbc.gridy = 3;
    add(new JLabel("Duration in minutes:"), gbc);

    durationField = new JTextField(20);
    gbc.gridx = 1;
    gbc.gridy = 3;
    add(durationField, gbc);

    // Users List
    gbc.gridx = 0;
    gbc.gridy = 4;
    gbc.gridwidth = 2;
    add(new JLabel("Available users"), gbc);

//    List<User> users = model.getUsers();
    String[] users = {"Prof. Lucia", "Chat", "Student Anon"};
    usersList = new JList<>(users);
    usersList.setVisibleRowCount(3);
    JScrollPane scrollPane = new JScrollPane(usersList);
    gbc.gridy = 5;
    add(scrollPane, gbc);

    // Schedule OurEvent Button
    scheduleButton = new JButton("Schedule event");
    gbc.gridy = 6;
    add(scheduleButton, gbc);
  }

  /**
   * Creates and displays the GUI in the event-dispatching thread.
   * This static method sets up the frame containing an instance of AutoEventFrame.
   */
  public static void createAndShowGUI() {
    JFrame frame = new JFrame("OurEvent Scheduler");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.add(new AutoEventFrame()); // Changed from EventSchedulerPanel to AutoEventFrame
    frame.pack();
    frame.setVisible(true);
  }

  public void render() {
    SwingUtilities.invokeLater(AutoEventFrame
        ::createAndShowGUI); // Changed from EventSchedulerPanel to AutoEventFrame
  }

}
