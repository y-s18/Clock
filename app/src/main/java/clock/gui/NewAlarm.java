package clock.gui;

import javax.swing.JButton;

public class NewAlarm {
    private JButton button;    
    private int[] alarmSettings;
    
    public NewAlarm(JButton button, int[] alarmSettings) {
        this.button = button;
        this.alarmSettings = alarmSettings;
    }
    
    public JButton getButton() {
        return button;
    }

    public int[] getAlarmSettings() {
        return this.alarmSettings;
    }
}
