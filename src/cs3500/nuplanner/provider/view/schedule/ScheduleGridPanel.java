package cs3500.nuplanner.provider.view.schedule;

import java.awt.GridLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import cs3500.nuplanner.provider.controller.PlannerFeatures;
import cs3500.nuplanner.provider.model.centralsystem.ReadOnlyCentralSystemModel;
import cs3500.nuplanner.provider.model.event.ReadOnlyEvent;
import cs3500.nuplanner.provider.model.event.Time;
import cs3500.nuplanner.provider.model.schedule.Schedule;
import cs3500.nuplanner.provider.model.schedule.UserSchedule;

/**
 * JPanel that displays a 24/7 Grid of every event in a week of given selected user.
 */
public class ScheduleGridPanel extends JPanel {
  private final ReadOnlyCentralSystemModel model;
  private String currentUser;

  /**
   * Constructs a ScheduleGridPanel and declares a currentUser.
   * @param model CentralSystem
   */
  public ScheduleGridPanel(ReadOnlyCentralSystemModel model) {
    this.model = model;
    this.setLayout(new GridLayout(24,7));
    this.currentUser = "<none>";
  }

  @Override
  protected void paintComponent(Graphics g) {
    Graphics2D g2d = (Graphics2D) g;

    if (!model.getUsers().isEmpty()) {
      makeScheduleGrid(g2d, currentUser);
    }
    for (int day = 1; day <= 7; day++) {
      g2d.drawLine(day * this.getWidth() / 7, 0,
              day * this.getWidth() / 7, this.getHeight());
    }

    for (int hour = 0; hour <= 23; hour++) {
      g2d.setStroke(new BasicStroke(1));
      if (hour % 4 == 0) {
        g2d.setStroke(new BasicStroke(4));
      }
      g2d.drawLine(0, hour * this.getHeight() / 24,
              this.getWidth(), hour * this.getHeight() / 24);
    }
  }

  public void setCurrentUser(String user) {
    this.currentUser = user;
    this.repaint();
  }

  private void makeScheduleGrid(Graphics2D g2d, String user) {
    Schedule schedule = new UserSchedule("None");
    if (model.getUsers().contains(user)) {
      schedule = model.selectUser(user);
    }
    else {
      schedule = new UserSchedule("None");
    }
    for (ReadOnlyEvent e : schedule.getEvents()) {
      drawEvent(g2d, e);
    }
  }


  private void drawEvent(Graphics2D g2d, ReadOnlyEvent e) {
    int startCol = e.getStartDay().getDayValue();
    int endCol = e.getEndDay().getDayValue();
    int startRow = timeToY(Time.timeToFourDigitNum(e.getStartTime()), this.getHeight());
    int endRow = timeToY(Time.timeToFourDigitNum(e.getEndTime()), this.getHeight());
    int x = startCol * this.getWidth() / 7;
    int y = startRow;
    int width = this.getWidth() / 7;
    int height = endRow - startRow;

    g2d.setColor(Color.RED);
    if (startCol == endCol) {
      g2d.fillRect(x, y, width, height);
    } else {
      if (startCol > endCol) {
        endCol = 7;
      }
      for (int col = startCol; col < endCol; col++) {
        g2d.fillRect(col * this.getWidth() / 7, y,
                width, this.getHeight());
        y = 0;
      }
      g2d.fillRect(endCol * this.getWidth() / 7, y,
              width, endRow);
    }
    g2d.setColor(Color.BLACK);
  }

  private int timeToY(int time, int panelSize) {
    int totalMinutes = 24 * 60;
    double minutesPerPixel = (double) totalMinutes / panelSize;

    int hours = time / 100;
    int minutes = time % 100;

    int totalMinutesFromTime = hours * 60 + minutes;

    return (int) (totalMinutesFromTime / minutesPerPixel);
  }

  /**
   * Listens to clicks on the panel and responds based on input.
   * @param features PlannerController to use
   */
  public void addClickListener(PlannerFeatures features) {
    this.addMouseListener(new MouseListener() {
      @Override
      public void mouseClicked(MouseEvent e) {
        ScheduleGridPanel panel = ScheduleGridPanel.this;
        int time = yToTime(e.getY(), panel.getHeight());
        int day  = e.getX() / (panel.getWidth() / 7) + 1;
        features.handleGridClick(time, day - 1, panel.currentUser);
      }

      @Override
      public void mousePressed(MouseEvent e) {
        // Implement if needed. Not Feature Desired
      }

      @Override
      public void mouseReleased(MouseEvent e) {
        // Implement if needed. Not Feature Desired
      }

      @Override
      public void mouseEntered(MouseEvent e) {
        // Implement if needed. Not Feature Desired
      }

      @Override
      public void mouseExited(MouseEvent e) {
        // Implement if needed. Not Feature Desired
      }

      private int yToTime(int num, int panelSize) {
        int totalMinutes = 24 * 60;
        double minutesPerPixel = (double) totalMinutes / panelSize;
        int minutesFromY = (int) (num * minutesPerPixel);

        int hours = minutesFromY / 60;
        int minutes = minutesFromY % 60;

        return (hours * 100) + minutes;
      }
    });
  }
}
