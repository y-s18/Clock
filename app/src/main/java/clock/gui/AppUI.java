package clock.gui;

import javax.swing.*;

public class AppUI {
    JFrame frame;
    JTabbedPane tp;
    JPanel clockP;
    JPanel alarmP;
    JPanel timerP;
    JPanel stopwatchP;
    ClockUI clockUI;

    public AppUI() {
        frame = new JFrame("Main");
        tp = new JTabbedPane();
        clockP = new JPanel();
        alarmP = new JPanel();
        timerP = new JPanel();
        stopwatchP = new JPanel();
        clockUI = new ClockUI();
        
        tp.add("Clock", clockUI.deck);
        tp.add("Alarm", alarmP);
        tp.add("Timer", timerP);
        tp.add("Stopwatch", stopwatchP);

        frame.add(tp);

        frame.setSize(800, 600);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
    }

    public static void main(String[] args) {
        new AppUI();
    }

}
