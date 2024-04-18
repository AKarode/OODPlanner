package cs3500.nuplanner.provider.view.event;

import cs3500.nuplanner.provider.model.centralsystem.ReadOnlyCentralSystemModel;
import cs3500.nuplanner.provider.model.event.EventBuilder;
import cs3500.nuplanner.provider.model.event.Time;
import cs3500.nuplanner.provider.model.event.UserEvent;

import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;

/**
 * Implementation of EventFrame using JFrame to make GUI of Creating/Modifying OurEvent.
 */
public abstract class EventGUI extends JFrame implements EventFrame {

  protected final ReadOnlyCentralSystemModel model;
  protected final EventBuilder eventBuilder;
  protected JPanel mainPanel;
  protected String[] days;
  protected String[] options;
  protected JTextArea eventName;
  protected JComboBox<String> combobox;
  protected JComboBox<String> endDay;
  protected JTextArea location;
  protected JComboBox<String> startDay;
  protected JButton removeEvent;
  protected JTextArea startTimeText;
  protected JTextArea endTimeText;
  protected JList<String> userOptions;
  protected ArrayList<String> eventUsers;

  public EventGUI(ReadOnlyCentralSystemModel model) {
    this.model = model;
    eventBuilder = new EventBuilder();
    setSize(500, 700);
  }

  protected void makeMainPanel() {
    mainPanel = new JPanel();
    mainPanel.setLayout(new GridLayout(0, 1));
    days = new String[] {"Sunday", "Monday", "Tuesday", "Wednesday",
        "Thursday", "Friday", "Saturday"};

    this.addLabel("OurEvent Name");
    this.addEventName();
    this.addLabel("Location");
    this.setLocation();
    this.setStartDay();
    this.setStartTime();
    this.setEndDay();
    this.setEndTime();
    this.addLabel("Available Users");
    this.setAvailableUsers();

    add(mainPanel);
  }

  protected void addLabel(String label) {
    JPanel labelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); // Align components to the left
    JLabel labelText = new JLabel(label + ":");
    labelText.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0)); // Add padding
    labelText.setFont(new Font(labelText.getFont().getName(), Font.BOLD, 15));
    labelPanel.add(labelText);
    mainPanel.add(labelPanel);

  }

  protected void addEventName() {
    eventName = new JTextArea(1, 1);
    eventName.setLineWrap(true);
    eventName.setPreferredSize(new Dimension(this.getWidth(), this.getHeight()));
    mainPanel.add(eventName);
  }

  protected void setLocation() {
    JPanel locationPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    locationPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    options = new String[]{"Is online", "Is in person"};
    combobox = new JComboBox<>(options);
    combobox.setPreferredSize(new Dimension(this.getWidth() / 5,
            this.getHeight() / 15));
    locationPanel.add(combobox);

    location = new JTextArea(1, 1);
    location.setLineWrap(true);
    location.setPreferredSize(new Dimension((this.getWidth() / 3) * 2,
            this.getHeight() / 15));
    locationPanel.add(location);
    mainPanel.add(locationPanel);
  }

  protected void setStartDay() {
    JPanel startDayPanel = new JPanel();
    startDayPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
    startDayPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

    JLabel startDayLabel = new JLabel("Start day:");
    startDayLabel.setPreferredSize(new Dimension(this.getWidth() / 5,
            this.getHeight() / 20));
    startDayPanel.add(startDayLabel);

    startDay = new JComboBox<>(days);
    startDay.setPreferredSize(new Dimension((this.getWidth() / 3) * 2,
            this.getHeight() / 15));
    startDayPanel.add(startDay);

    mainPanel.add(startDayPanel);
  }

  protected void setStartTime() {
    JPanel startTimePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    startTimePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    JLabel startTimeLabel = new JLabel("Start time:");
    startTimeLabel.setPreferredSize(new Dimension(this.getWidth() / 5,
            this.getHeight() / 20));
    startTimePanel.add(startTimeLabel);

    startTimeText = new JTextArea(1, 1);
    startTimeText.setLineWrap(true);
    startTimeText.setPreferredSize(new Dimension((this.getWidth() / 3) * 2,
            this.getHeight() / 15));
    startTimePanel.add(startTimeText);

    mainPanel.add(startTimePanel);
  }

  protected void setEndDay() {
    JPanel endDayPanel = new JPanel();
    endDayPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    endDayPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
    mainPanel.add(endDayPanel);
    JLabel endDayLabel = new JLabel("End day:");
    endDayLabel.setPreferredSize(new Dimension(this.getWidth() / 5,
            this.getHeight() / 20));
    endDayPanel.add(endDayLabel);

    endDay = new JComboBox<>(days);

    endDay.setPreferredSize(new Dimension((this.getWidth() / 3) * 2,
            this.getHeight() / 15));
    endDayPanel.add(endDay);
  }

  protected void setEndTime() {
    JPanel endTimePanel = new JPanel();
    endTimePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    endTimePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
    mainPanel.add(endTimePanel);
    JLabel endTimeLabel = new JLabel("End time:");
    endTimeLabel.setPreferredSize(new Dimension(this.getWidth() / 5,
            this.getHeight() / 20));
    endTimePanel.add(endTimeLabel);

    endTimeText = new JTextArea(1, 1);
    endTimeText.setLineWrap(true);
    endTimeText.setPreferredSize(new Dimension((this.getWidth() / 3) * 2,
            this.getHeight() / 15));
    endTimePanel.add(endTimeText);
  }

  protected void setAvailableUsers() {
    DefaultListModel<String> listOfUsers = new DefaultListModel<>();
    for (String user : this.model.getUsers()) {
      listOfUsers.addElement(user);
    }
    userOptions = new JList<>(listOfUsers);
    userOptions.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);


    eventUsers = new ArrayList<>();
    mainPanel.add(new JScrollPane(userOptions));
  }

  protected void addRemoveButton(JPanel eventOptions) {
    removeEvent = new JButton("Remove OurEvent");
    //removeEvent.addActionListener(evt -> deleteEvent());
    eventOptions.add(removeEvent);
  }

  @Override
  public void display() {
    setVisible(true);
  }

  @Override
  public UserEvent buildEvent() {
    eventBuilder.setName(eventName.getText());
    eventBuilder.setStartDay(startDay.getSelectedItem().toString());
    eventBuilder.setStartTime(Time.fourDigitNumToTime(Integer.parseInt(startTimeText.getText().replace(":", ""))));
    eventBuilder.setEndDay(endDay.getSelectedItem().toString());
    eventBuilder.setEndTime(Time.fourDigitNumToTime(Integer.parseInt(endTimeText.getText().replace(":", ""))));
    eventBuilder.setOnline(combobox.getSelectedItem().equals("Is online"));
    eventBuilder.setPlace(location.getText());
    eventBuilder.setHost(userOptions.getSelectedValuesList().get(0));
    eventBuilder.setInvitees(userOptions.getSelectedValuesList());
    return eventBuilder.build();
  }

  @Override
  public UserEvent buildEvent(String host) {
    eventBuilder.setName(eventName.getText());
    eventBuilder.setStartDay(startDay.getSelectedItem().toString());
    eventBuilder.setStartTime(Time.fourDigitNumToTime(Integer.parseInt(startTimeText.getText().replace(":", ""))));
    eventBuilder.setEndDay(endDay.getSelectedItem().toString());
    eventBuilder.setEndTime(Time.fourDigitNumToTime(Integer.parseInt(endTimeText.getText().replace(":", ""))));
    eventBuilder.setOnline(combobox.getSelectedItem().equals("Is online"));
    eventBuilder.setPlace(location.getText());
    eventBuilder.setHost(userOptions.getSelectedValuesList().get(0));
    eventBuilder.setInvitees(userOptions.getSelectedValuesList());
    return eventBuilder.build();
  }

  @Override
  public UserEvent buildEventSchedule(String host) {
    return new UserEvent(eventName.getText(), combobox.getSelectedItem().equals("Is online"),
            location.getText(), host,
            userOptions.getSelectedValuesList());
  }
}

