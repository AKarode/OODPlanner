package cs3500.nuplanner.provider;

//import cs3500.nuplanner.provider.controller.schedule.PlannerController;
//import cs3500.nuplanner.provider.controller.schedule.PlannerFeatures;
import cs3500.nuplanner.provider.model.centralsystem.AnyTimeStrategy;
//import cs3500.nuplanner.provider.model.centralsystem.CentralSystem;
import cs3500.nuplanner.provider.model.centralsystem.WorkHoursStrategy;
import model.CentralSystem;


/**
 * A class to run the Planner program.
 * A user must pass in their desired strategy
 * as an argument to the main function.
 */
public class Planner {
  /**
   * Main method to run the program.
   * @param args the strategy to pass into the program
   * @throws IllegalArgumentException if the user does not pass in a valid strategy
   */
  public static void main(String[] args) {
    /*
    CentralSystem model;
    if (args[0].equals("workhours")) {
      model = new CentralSystem(new WorkHoursStrategy());
    }
    else if (args[0].equals("anytime")) {
      model = new CentralSystem(new AnyTimeStrategy());
    }
    else {
      throw new IllegalArgumentException("Must pick a valid strategy!");
    }

    ScheduleFrame view = new WeeklyPlannerGUI(model);
    PlannerFeatures controller = new PlannerController(view);
    controller.launch(model);

     */
  }


}

