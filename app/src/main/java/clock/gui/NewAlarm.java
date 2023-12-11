package clock.gui;

import javax.swing.JButton;

public class NewAlarm {
    private JButton button;    
    private int[] alarmSettings;
    private String alarmTime;
    private boolean sentNotification;
    
    public NewAlarm(JButton button, int[] alarmSettings) {
        this.button = button;
        this.alarmSettings = alarmSettings;
        this.sentNotification = false;
    }
    
    public JButton getButton() {
        return button;
    }

    public int[] getAlarmSettings() {
        return this.alarmSettings;
    }
    
    public String getAlarmTime() {
        return alarmTime;
    }
    
    public void setAlarmTime(String alarmTime) {
        if (this.alarmTime == null)
            this.alarmTime = alarmTime;
    }
    
    public boolean isSentNotification() {
        return sentNotification;
    }
    
    void setSentNotification(boolean sentNotification) {
        this.sentNotification = sentNotification;
    }
}
