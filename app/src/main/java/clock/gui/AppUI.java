package clock.gui;

import javax.swing.*;

public class AppUI {
    JFrame mainFrame;
    JTabbedPane tabbedPane;
    JPanel clockP;
    JPanel alarmP;
    JPanel timerP;
    JPanel stopwatchP;
    ClockUI clockUI;

    public AppUI() throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
        setUpAppComponents();
    }

    private void setUpAppComponents() throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        mainFrame = new JFrame("Main");
        tabbedPane = new JTabbedPane();
        mainFrame.add(tabbedPane);

        createTabPaneComponents();
        addTabPaneComponents();
        configureMainFrame();
    }

    private void createTabPaneComponents() {
        clockUI = new ClockUI();
        alarmP = new AlarmUI();
        timerP = new JPanel();
        stopwatchP = new JPanel();
    }

    private void addTabPaneComponents() {
        tabbedPane.add("Clock", clockUI);
        tabbedPane.add("Alarm", alarmP);
        tabbedPane.add("Timer", timerP);
        tabbedPane.add("Stopwatch", stopwatchP);
    }

    private void configureMainFrame() {
        mainFrame.setSize(800, 600);
        mainFrame.setVisible(true);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setResizable(false);
    }

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
        new AppUI();
    }

}
