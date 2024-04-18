package cs3500.nuplanner.provider.view.event;

import cs3500.nuplanner.provider.controller.PlannerFeatures;
import cs3500.nuplanner.provider.model.centralsystem.ReadOnlyCentralSystemModel;
import cs3500.nuplanner.provider.model.event.EventBuilder;
import cs3500.nuplanner.provider.model.event.ReadOnlyEvent;

import java.awt.*;

import javax.swing.*;


public class ModifyEventGUI extends EventGUI {
  private final ReadOnlyEvent event;
  private JButton modifyEvent;

  /**
   * OurEvent GUI Constructor that takes in a model and an OurEvent.
   * @param model CentralSystem
   * @param event OurEvent to display
   */
  public ModifyEventGUI(ReadOnlyCentralSystemModel model, ReadOnlyEvent event) {
    super(model);
    EventBuilder eventBuilder = new EventBuilder();
    eventBuilder.copyEvent(event);
    this.event = eventBuilder.build();
    setTitle("Create OurEvent");
    this.makeMainPanel();
  }

  protected void makeMainPanel() {
    super.makeMainPanel();
    this.addModifyButtons();
  }

  @Override
  protected void addEventName() {
    super.addEventName();
    eventName.setText(this.event.getName());
  }

  @Override
  protected void setLocation() {
    super.setLocation();
    if (this.event.isOnline()) {
      combobox.setSelectedItem(options[0]);
    }
    else {
      combobox.setSelectedItem(options[1]);
    }
    location.setText(this.event.getLocation());
  }

  @Override
  protected void setStartDay() {
    super.setStartDay();
    startDay.setSelectedItem(this.event.getStartDay().toString());
  }

  @Override
  protected void setStartTime() {
    super.setStartTime();
    startTimeText.setText(this.event.getStartTime().toString());
  }

  @Override
  protected void setEndDay() {
    super.setEndDay();
    endDay.setSelectedItem(this.event.getEndDay().toString());
  }

  @Override
  protected void setEndTime() {
    super.setEndTime();
    endTimeText.setText(this.event.getEndTime().toString());
  }

  @Override
  protected void setAvailableUsers() {
    super.setAvailableUsers();
    userOptions.setSelectedValue(event.getHost(), true);
    eventUsers.add(this.event.getHost());
    eventUsers.addAll(this.event.getInvitees());
    for (int i = 0; i < eventUsers.size(); i++) {
      userOptions.addSelectionInterval(i, i);
    }
  }

  private void addModifyButtons() {
    JPanel eventOptions = new JPanel();
    modifyEvent = new JButton("Modify OurEvent");
    modifyEvent = new JButton("Modify OurEvent");
    //modifyEvent.addActionListener(evt -> modifyEvent());
    eventOptions.add(modifyEvent);
    mainPanel.add(eventOptions);

    this.addRemoveButton(eventOptions);
  }

  @Override
  public void addFeatures(PlannerFeatures f) {
    modifyEvent.addActionListener(evt -> f.modifyEvent(this, event));
    removeEvent.addActionListener(evt -> f.removeEvent(this, event));
  }
}