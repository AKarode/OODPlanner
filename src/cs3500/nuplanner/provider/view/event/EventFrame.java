package cs3500.nuplanner.provider.view.event;

import cs3500.nuplanner.provider.controller.PlannerFeatures;
import cs3500.nuplanner.provider.model.event.UserEvent;

/**
 * Interface for Displaying the OurEvent Creation/Modification Interface.
 */
public interface EventFrame {

  /**
   * Display this view.
   */
  void display();

  /**
   * Add a features to the view to do command callback.
   * @param f features
   */
  void addFeatures(PlannerFeatures f);

  UserEvent buildEvent();

  UserEvent buildEvent(String host);

  UserEvent buildEventSchedule(String host);
}