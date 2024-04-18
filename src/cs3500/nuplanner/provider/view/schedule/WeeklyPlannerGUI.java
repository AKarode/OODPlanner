package cs3500.nuplanner.provider.view.schedule;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.Objects;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import cs3500.nuplanner.provider.controller.PlannerFeatures;
import cs3500.nuplanner.provider.model.centralsystem.ReadOnlyCentralSystemModel;
import cs3500.nuplanner.provider.model.schedule.UserSchedule;

/**
 * Implementation of Planner View for Weekly Planner
 * Using the Swing Library.
 */


public class WeeklyPlannerGUI extends JFrame implements ScheduleFrame {
  private JMenuItem addCalendar;
  private JMenuItem saveCalendar;
  private ScheduleGridPanel hourGrid;
  private final JComboBox<ScheduleItem> usersSelect;
  private final JButton createEvent;
  private final JButton scheduleEvent;

  /**
   * Initializes GUI
   * adds the 24/7 Schedule Grid and UI intractable.
   */
  public WeeklyPlannerGUI(ReadOnlyCentralSystemModel model) {
    super("Weekly Planner");
    setSize(800, 796);
    setLocation(200, 200);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLayout(new BorderLayout());

    JMenuBar fileMenuBar = new JMenuBar();
    JMenu fileMenu = new JMenu("File");
    setAddCalendar(fileMenu);
    setSaveCalendar(fileMenu);
    fileMenuBar.add(fileMenu);
    this.add(fileMenuBar, BorderLayout.PAGE_START);

    hourGrid = new ScheduleGridPanel(model);
    this.add(hourGrid, BorderLayout.CENTER);

    JPanel userInteraction = new JPanel(new GridLayout(1,3));
    usersSelect = new JComboBox<>();
    usersSelect.addItem(new ScheduleItem("<none>", new UserSchedule("<none>")));
    updateComboBox(model);
    userInteraction.add(usersSelect);

    createEvent = new JButton("Create OurEvent");
    userInteraction.add(createEvent);

    scheduleEvent = new JButton("Schedule OurEvent");
    userInteraction.add(scheduleEvent);

    this.add(userInteraction, BorderLayout.PAGE_END);
  }

  @Override
  public void updateComboBox(ReadOnlyCentralSystemModel model) {
    for (String user : model.getUsers()) {
      ScheduleItem s = new ScheduleItem(user, model.selectUser(user));
      usersSelect.addItem(s);
      usersSelect.setSelectedItem(s);
      changeActiveUser();
    }
  }

  private void setAddCalendar(JMenu fm) {
    addCalendar = new JMenuItem("Add Calendar");
    fm.add(addCalendar);
  }

  private void setSaveCalendar(JMenu fm) {
    saveCalendar = new JMenuItem("Save Calendar");
    fm.add(saveCalendar);
  }

  @Override
  public String getCurrentUser() {
    return Objects.requireNonNull(usersSelect.getSelectedItem()).toString();
  }

  /**
   * Add a features to the view to do command callback.
   *
   * @param f features
   */
  @Override
  public void addFeatures(PlannerFeatures f) {
    addCalendar.addActionListener(evt -> f.addCalendarPrompt());
    saveCalendar.addActionListener(evt -> f.saveCalendarPrompt());
    createEvent.addActionListener(evt -> f.createEventPrompt());
    scheduleEvent.addActionListener(evt -> f.scheduleEventPrompt());
    usersSelect.addActionListener(evt -> f.changeActiveUser());
    hourGrid.addClickListener(f);
  }

  @Override
  public void changeActiveUser() {
    hourGrid.setCurrentUser(getCurrentUser());
    this.refresh();
  }

  /**
   * Display this view.
   */
  @Override
  public void display() {
    setVisible(true);
  }

  /**
   * Refresh the view to reflect any changes in the game state.
   */
  @Override
  public void refresh() {
    this.repaint();
  }

  /**
   * Instances a JFile Chooser to add a Schedule to the system.
   */
  public String addCalendarPrompt() {
    JFileChooser chooser = new JFileChooser();
    FileNameExtensionFilter filter = new FileNameExtensionFilter(
            "XML Schedule", "xml");
    chooser.setFileFilter(filter);
    int status = chooser.showOpenDialog(this);
    if (status == JFileChooser.APPROVE_OPTION) {
      return chooser.getSelectedFile().getAbsolutePath();
    }

    return null;
  }

  /**
   * Instances a JFile Chooser to save a Schedule to an XML File.
   */
  public String saveCalendarPrompt() {
    JFileChooser chooser = new JFileChooser();
    FileNameExtensionFilter filter = new FileNameExtensionFilter(
            "XML Schedule", "xml");
    chooser.setFileFilter(filter);
    int status = chooser.showSaveDialog(this);
    if (status == JFileChooser.APPROVE_OPTION) {
      return chooser.getSelectedFile().getAbsolutePath();
    }

    return null;
  }

  /**
   * Make a new Error prompt to display errors from Model
   *
   * @param message Message to display
   */
  @Override
  public void makeErrorPopUp(String message) {
    JOptionPane errorDisplay = new JOptionPane();
    errorDisplay.showMessageDialog(this, message);
  }
}