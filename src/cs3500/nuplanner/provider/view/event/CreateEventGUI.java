package cs3500.nuplanner.provider.view.event;

import java.awt.*;

import cs3500.nuplanner.provider.controller.PlannerFeatures;
import cs3500.nuplanner.provider.model.centralsystem.ReadOnlyCentralSystemModel;
import cs3500.nuplanner.provider.model.event.EventBuilder;
import cs3500.nuplanner.provider.model.event.ReadOnlyEvent;
import javax.swing.*;




public class CreateEventGUI extends EventGUI {
  private final EventBuilder eventBuilder;
  private JButton createEvent;
  private ReadOnlyEvent event;
  /**
   * OurEvent GUI Constructor that takes in a model and a current user.
   * @param model CentralSystem
   * @param currentUser Current User
   */
  public CreateEventGUI(ReadOnlyCentralSystemModel model, String currentUser) {
    super(model);
    this.eventBuilder = new EventBuilder();
    this.eventBuilder.setHost(currentUser);
    this.event = this.eventBuilder.build();
    setTitle("Create OurEvent");
    this.makeMainPanel();
  }

  protected void makeMainPanel() {
    super.makeMainPanel();
    this.addCreateButtons();
  }

  protected void addCreateButtons() {
    JPanel eventOptions = new JPanel();

    createEvent = new JButton("Create OurEvent");
    //createEvent.addActionListener(evt -> createEvent());
    eventOptions.add(createEvent);
    mainPanel.add(eventOptions);
  }

  @Override
  public void addFeatures(PlannerFeatures f) {
    createEvent.addActionListener(evt -> f.createEvent(this));
  }
}