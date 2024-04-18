package adapter;

import controller.Features;
import cs3500.nuplanner.provider.view.schedule.ScheduleFrame;
import view.PlannerGrid;

public class ViewAdapter implements PlannerGrid {

  ScheduleFrame delegate;
  Features controller;

  public ViewAdapter (ScheduleFrame delegate) {
    this.delegate = delegate;
  }

  @Override
  public void render() {
    delegate.display();
  }

}
