package model;

//import view.CalendarUI;

import adapter.ControllerAdapter;
import adapter.ModelAdapter;
import controller.Features;
import controller.PlannerController;
import cs3500.nuplanner.provider.controller.PlannerFeatures;
import cs3500.nuplanner.provider.model.centralsystem.CentralSystemModel;
import cs3500.nuplanner.provider.view.schedule.ScheduleFrame;
import cs3500.nuplanner.provider.view.schedule.WeeklyPlannerGUI;
import view.CalendarUI;


/**
 * Checks to make sure the application works as expected.
 */
public class Main {

  /**
   * The entry point of the application. It demonstrates the process of initializing the system,
   * reading a user's schedule from an XML file, adding the user to the central system, and
   * displaying the schedule.
   *
   * @param args Command-line arguments (not used).
   */
  public static void main(String[] args) {

    if (args[0].equals("provider")) {
      // Our set up
      CentralSystem system = new CentralSystem();
      CalendarUI view = new CalendarUI(system);
      XMLReader reader = new XMLReader();
      User user = reader.readXML("src/model/input.xml");
      system.addUser(user);
      PlannerController controller = new PlannerController(system, view);

      // Adapt to provider code
      CentralSystemModel providerModel = new ModelAdapter(system);
      PlannerFeatures providerFeatures = new ControllerAdapter(controller);
      ScheduleFrame providerView = new WeeklyPlannerGUI(providerModel);
      providerFeatures.launch(providerModel);
      providerView.addFeatures(providerFeatures);
      providerView.display();
    }
    else {
      CentralSystem system = new CentralSystem();
      CalendarUI view = new CalendarUI(system);
      XMLReader reader = new XMLReader();
      User user = reader.readXML("src/model/input.xml");
      system.addUser(user);
      Features controller = new PlannerController(system, view);
      view.render();
    }
  }

}