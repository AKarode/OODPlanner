package cs3500.nuplanner.provider.view.event;

import java.awt.*;

import javax.swing.*;

import cs3500.nuplanner.provider.controller.PlannerFeatures;
import cs3500.nuplanner.provider.model.centralsystem.ReadOnlyCentralSystemModel;

public class ScheduleEventGUI extends EventGUI {
  private JButton scheduleButton;
  private JTextArea duration;

  /**
   * OurEvent GUI Constructor that takes in a model and a current user.
   * @param model CentralSystem
   * @param currentUser Current User
   */
  public ScheduleEventGUI(ReadOnlyCentralSystemModel model, String currentUser) {
    super(model);
    setTitle("Schedule OurEvent");
    this.makeMainPanel();
  }

  @Override
  protected void makeMainPanel() {
    mainPanel = new JPanel();
    mainPanel.setLayout(new GridLayout(0, 1));
    days = new String[] {"Sunday", "Monday", "Tuesday", "Wednesday",
            "Thursday", "Friday", "Saturday"};

    this.addLabel("OurEvent Name");
    this.addEventName();
    this.addLabel("Location");
    this.setLocation();
    this.addLabel("Duration in minutes");
    this.addDuration();
    this.addLabel("Available Users");
    this.setAvailableUsers();
    this.addScheduleButton();

    add(mainPanel);
  }

  private void addScheduleButton() {
    JPanel buttonPanel = new JPanel();
    scheduleButton = new JButton("Schedule OurEvent");
    //scheduleButton.addActionListener(evt -> createEvent());
    buttonPanel.add(scheduleButton);
    mainPanel.add(buttonPanel);
  }

  private void addDuration() {
    duration = new JTextArea(1, 1);
    duration.setLineWrap(true);
    duration.setPreferredSize(new Dimension(this.getWidth(), this.getHeight()));
    mainPanel.add(duration);
  }

  /**
   * Add a features to the view to do command callback.
   *
   * @param f features
   */
  @Override
  public void addFeatures(PlannerFeatures f) {
    scheduleButton.addActionListener(evt -> f.scheduleEvent(this));
  }

  public int getDuration() {
    int dur;
    try {
      dur = Integer.parseInt(duration.getText());
    }
    catch (NumberFormatException ex) {
      throw new RuntimeException();
    }
    return dur;
  }
}