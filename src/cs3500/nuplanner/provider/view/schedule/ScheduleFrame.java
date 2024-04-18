package cs3500.nuplanner.provider.view.schedule;

import cs3500.nuplanner.provider.controller.PlannerFeatures;
import cs3500.nuplanner.provider.model.centralsystem.ReadOnlyCentralSystemModel;

//TODO: implement this interface.
/**
 * Interface for Developping Views for Planner App.
 */
public interface ScheduleFrame {
  /**
   * Add a features to the view to do command callback.
   * @param f features
   */
  void addFeatures(PlannerFeatures f);

  void changeActiveUser();

  /**
   * Display this view.
   */
  void display();
  
  /**
   * Refreshes Drawings on Screen.
   */
  void refresh();

  /**
   * Make a JFileChooser to add a calendar.
   * @return Path
   */
  String addCalendarPrompt();

  void updateComboBox(ReadOnlyCentralSystemModel model);

  /**
   * Get the currently selected user.
   * @return the String representing the selected user
   */
  String getCurrentUser();

  /**
   * Make a JFileChooser to save a calendar.
   * @return Path
   */
  String saveCalendarPrompt();

  /**
   * Make a new Error prompt to display errors from Model
   * @param message Message to display
   */
  void makeErrorPopUp(String message);
}
