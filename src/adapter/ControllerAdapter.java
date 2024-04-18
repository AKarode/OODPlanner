package adapter;
import controller.PlannerController;
import cs3500.nuplanner.provider.controller.PlannerFeatures;
import cs3500.nuplanner.provider.model.centralsystem.CentralSystemModel;
import cs3500.nuplanner.provider.model.event.Event;
import cs3500.nuplanner.provider.model.event.ReadOnlyEvent;
import cs3500.nuplanner.provider.model.schedule.Schedule;
import cs3500.nuplanner.provider.view.event.CreateEventGUI;
import cs3500.nuplanner.provider.view.event.EventGUI;
import cs3500.nuplanner.provider.view.event.ScheduleEventGUI;
import cs3500.nuplanner.provider.view.event.EventFrame;

import javax.swing.*;

public class ControllerAdapter implements PlannerFeatures {

    private PlannerController delegateController;
    private CentralSystemModel model;

    public ControllerAdapter(PlannerController delegateController) {
        this.delegateController = delegateController;
    }


  @Override
  public void launch(CentralSystemModel model) {
      this.model = model;
  }

  @Override
  public void handleGridClick(int time, int day, String currentUser) {
  //TODO: Implement this method

  }

  @Override
  public void addCalendarPrompt() {
  //TODO: Implement this method
  }

  @Override
  public void saveCalendarPrompt() {
//TODO: Implement this method
  }

  @Override
  public void createEventPrompt() {
      EventFrame createEventFrame = new CreateEventGUI(model, "Prof. Lucia");
      createEventFrame.display();
  }

  @Override
  public void scheduleEventPrompt() {
    EventFrame scheduleEventFrame = new ScheduleEventGUI(model, "Prof. Lucia");
    scheduleEventFrame.display();
  }

  @Override
  public Schedule selectUser(String user) {
   return model.selectUser(user);
  }

  @Override
  public void changeActiveUser() {
    delegateController.switchUser("Prof. Lucia");
  }

  @Override
  public void modifyEvent(EventGUI gui, ReadOnlyEvent event) {
    //Cannot implement as EventGUI has protected fields and no getters.
  }

  @Override
  public void createEvent(EventGUI gui) {
    //Cannot implement as EventGUI has protected fields and no getters.
  }

  @Override
  public void removeEvent(EventGUI gui, ReadOnlyEvent readOnlyEvent) {
    model.removeEvent(readOnlyEvent.getHost(),((Event) readOnlyEvent));
  }

  @Override
  public void scheduleEvent(ScheduleEventGUI scheduleEventGUI) {
    //TODO: Implement this method
  }
}
