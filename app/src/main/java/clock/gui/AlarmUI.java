package clock.gui;

import java.awt.event.*;
import java.util.Calendar;
import javax.swing.*;
import java.awt.*;
import java.awt.TrayIcon.MessageType;

public class AlarmUI extends JPanel implements Runnable{
    private JButton addButton;
    private JPanel alarmsListPanel;
    private JScrollPane alarmsListScrollPane;
    private JDialog naDialog;
    private SoundUtils su;
    private int currHour;
    private int currMinute;
    private int currAmpmMarker;
    private int currDay;

    public AlarmUI(){
        this.naDialog = new NewAlarmDialog();
        this.su = new SoundUtils();

        configureAlarmUIPanel();
        setupAlarmUIButtons();
        setupAlarmsList();
        addWindowListenerToNADialog();
        addAlarmUIComponents();

        Thread thread = new Thread(this);
        thread.start();
    }
    
    private void configureAlarmUIPanel() {
        this.setLayout(null);
        this.setBackground(Color.white);
    }
    
    private void setupAlarmUIButtons() {
        addButton = new JButton("Add");
        addButton.setBounds(200, 100, 80, 50);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((NewAlarmDialog) AlarmUI.this.naDialog).clearNewAlarmDialogSelections();
                AlarmUI.this.naDialog.setVisible(true);
            }
        });
    }
    
    private void setupAlarmsList() {
        alarmsListPanel = new JPanel();
        this.alarmsListPanel.setLayout(new BoxLayout(alarmsListPanel, BoxLayout.PAGE_AXIS));
        this.alarmsListPanel.setBounds(1,1,298,298);
        alarmsListScrollPane = new JScrollPane(alarmsListPanel, 
                                    JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                                    JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        this.alarmsListScrollPane.setBounds(400, 100, 300, 300);
    }
    
    private void addWindowListenerToNADialog() {
        this.naDialog.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent windowEvent){
                AlarmUI.this.alarmsListPanel.removeAll();
                for (NewAlarm na : ((NewAlarmDialog) AlarmUI.this.naDialog).getSetAlarms()) 
                    AlarmUI.this.alarmsListPanel.add(na.getButton());
            }
        });
    }
    
    private void addAlarmUIComponents() {
        this.add(addButton);
        this.add(alarmsListScrollPane);
    }

    public void displayTray() throws AWTException {
        SystemTray tray = SystemTray.getSystemTray();
        Image image = Toolkit.getDefaultToolkit().createImage("icon.png");
        TrayIcon trayIcon = new TrayIcon(image, "Tray Demo");
        trayIcon.setImageAutoSize(true);
        trayIcon.setToolTip("System tray icon demo");
        tray.add(trayIcon);
        trayIcon.displayMessage("Wake Up!", "Close the alarm from the app!", MessageType.INFO);
    }

    @Override
    public void run() {
        while (true) {
            Calendar cal = Calendar.getInstance();
            currHour = cal.get(Calendar.HOUR);
            currMinute = cal.get(Calendar.MINUTE);
            currAmpmMarker = cal.get(Calendar.AM_PM);
            currDay = cal.get(Calendar.DAY_OF_WEEK);
            compareCurrentTimeWithAlarmTime();
        }
    }

    private void compareCurrentTimeWithAlarmTime() {
        for (int i=0; i<((NewAlarmDialog) this.naDialog).getSetAlarms().size(); i++){
            NewAlarm na = ((NewAlarmDialog) this.naDialog).getSetAlarms().get(i);
            if(isTimeToRing(na)){
                sendNotification(na);
                try { this.su.playSound(); }
                catch (Exception e) { e.printStackTrace(); }
            }else {
                na.setSentNotification(false);
            }
        }
    }
    
    private boolean isTimeToRing(NewAlarm na) {
        int setHour = na.getAlarmSettings()[11];
        int setMinute = na.getAlarmSettings()[12];
        int setAmpmMarker = na.getAlarmSettings()[10];
        
        return setHour==currHour 
        && setMinute==currMinute 
        && setAmpmMarker==currAmpmMarker 
        && na.getAlarmSettings()[currDay-1]==1;
    }
    
    private void sendNotification(NewAlarm na) {
        if(!na.isSentNotification()){
            na.setSentNotification(true);
            try { displayTray(); } 
            catch (AWTException e) { e.printStackTrace(); }
        }
    }
}