# Planner App
### By Sharyq Siddiqi and Liam McCarthy

## Overview
This project is focusing on making a system proficient in visualising the schedules of users in the planner, over the course of a week. It Highlights what times during the day are unavailable, and allows a person to create and schedule ourEvents across the week.

## Quick Start
To start the application, a person can run this code in the main method.
```
public static void main(String[] args) {
  CentralSystemModel model = new CentralSystem();
  model.uploadSchedule([INSERT SCHEDULE PATH]);
  ScheduleFrame view = new WeeklyPlannerGUI(model);
  PlannerFeatures controller = new PlannerController(view, model);
}
```

As a note: In our Main method we have it set up so all xml files within the directory the jar file/project is being run is uploaded to the system by default for ease of testing.
As an example if in Directory "Planner" you have:
- planner.jar
- prof.xml
- Liam-sched.xml
- Sharyq-sched.xml
- RandomFile.txt

All the xml files will be read in, all the rest will be ignored.

## Key Components
### The Essential Components to Running the Program are: 
- CentralSystem: An Implementation of CentralSystemModel, which organizes and handles all Schedule functions.
- WeeklyPlannerGUI: An Implementation of PlannerView, which instances a Swing JFrame interface of All data in the CentralSystem.
- PlannerController (Not Fully Implemented): An Implementation of PlannerFeatures, which Command Callbacks functions within the model and the view based on user Input
- XMLHelper: Reads and Writes XML files for purpose of being loaded in schedules and outputting them for external use or for saving.

### Subcomponents of the model are:
- Schedule: The interface that represents an individual schedule. Each schedule has one user, and no two schedules can have the same user.
- Event: The interface that represents an individual ourEvent. An ourEvent has a time, place, online status, host, and invitees. Implemented in UserEvent, and has a builder called EventBuilder for testing/XML file     writing.
- CentralSystemModel: The interface for the central system that contains schedules. The central system can add or remove schedules, and it allows a person to modify schedules. Modifications for an ourEvent will affect the host schedule and invitees' schedules. If the change causes a timing conflict in any schedules, the program will throw an exception to notify the user.
- ReadOnlyCentralSystemModel, Schedule, Model, interfaces that Schedule, Event and CentralSystemModel implement that handles all methods to do with observing, and disallows modification. It's used in classes such as views to prohibit modification to the model.
- Day: Enum representing days in week
- Time: Class Encapsulating Hours and Minutes

### Subcomponents of the view are:
- EventFrame: A interface used for the EventGUI to assert what functionality it has for displaying and modifying ourEvents in the CentralSystem.
- ScheduleFrame: A interface used for the WeeklyPlannerGUI and TUI to assert what functionality it has for displaying and modifying Schedules in the System as well as the ourEvents in them.
- ScheduleGridPanel: A JPanel used to display the weekly schedule of a user on a 24 by 7 grid, updates based on what ourEvents are in a users schedule and what user is selected
- ScheduleItem: A Object used for WeeklyPlannerGUI's JComboBox so that every schedule in its schedule has a corresponding schedule to use.

## Source Organization
- All Files that that work with the CentralSystem Model to do Operations are in `src/model`
- All Files used for displaying Model data are in `src/view`
- All controller items(Not Implemented yet) are in `src/controller`

## Changes for part 2

### Model:
- Implementation of Time Class: Instead of having a 4-digit number that could have a various amount of invalid values between 0000 and 2359, storing it in class where Hours and minutes are separate params, so we can assert limits within the constructor made a lot of comparisons easier and reduced the amount of possible invalid values that we had to check for.
- Implementation of ReadOnly Model Classes: Made so when doing work in the view, the only thing we should be doing it is viewing the values, not editing, all editing should be done in the controller, when we implement that fully, but view should not be able to do that as it could cause a lot of cases where we accidentally modify a value.
- Overlaps with Time: Implemented as there needed to be a way to get an Event for a user based on a provided day and time.

### View:
- Modified Schedule Frame and Created Event Frame Interfaces and implemented them: Main Challenge of this assignment was to make GUI, so make Interfaces and implementing the needed functions for them were needed.
- Made ScheduleGridPanel: Displaying the Events in the GUI needed to be done in a separate planner, so it can be implemented where it is needed, as well as all functionality associated with it is unobtrusive to the rest of the needed functionality of the GUI.
- Made ScheduleItem: JComboBox Requires an object input for each of its selections made out, so we could work with a combobox effectively and use it to get schedules.

### Controller:
- Started making PlannerFeatures and implementation: Certain features and interactions were liked for the purpose of testing GUI, such as switching users to see if things refreshed when needed, Event Panel got the correct ourEvent, etc. Most essential features needed for view to be working properly are implemented and are thoroughly tested as a result.
