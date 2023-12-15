package clock.gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.LinkedList;

public class NewAlarmDialog extends JDialog{
    private DefaultListModel<String> hoursListModel;
    private DefaultListModel<String> minutesListModel;
    private DefaultListModel<String> ampmMarkerListModel;
    private JList<String> hoursList;
    private JList<String> minutesList;
    private JList<String> ampmMarkerList;
    private JScrollPane hoursScrollPane;
    private JScrollPane minutesScrollPane;
    private JScrollPane ampmMarkerScrollPane;
    private JRadioButton snoozeRButton;
    private JButton setButton;
    private JButton resetButton;
    private JButton saveButton;
    private JButton deleteButton;
    private LinkedList<NewAlarm> setAlarms = new LinkedList<NewAlarm>();
    private JToggleButton[] weekDays = new JToggleButton[7];
    private NewAlarm changeAlarmBuffer;

    public NewAlarmDialog(){
        configureDialog();
        
        setupHoursList();
        setupMinutesList();
        setupAmPmMarkerList();
        setupHoursPane();
        setupMinutesPane();
        setupAmPmMarkerPane();
        
        createDayTButtons();
        configureDayTButtons();
        setupAlarmButtons();
        
        addAlarmComponents();
    }

    private void configureDialog() {
        this.setLayout(null);
        this.setSize(600,400);
        this.setTitle("");
        this.setResizable(false);
        this.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
    
    private void setupHoursList() {
        hoursListModel = new DefaultListModel<>();
        for(int i=1; i<13;i++)
            hoursListModel.addElement(i<10 ? "0"+i : ""+i);        
        hoursList = new JList<>(hoursListModel);
        hoursList.setFont(new Font("Arial", Font.PLAIN, 35));
    }
    
    private void setupMinutesList() {
        minutesListModel = new DefaultListModel<>();
        for(int i=0; i<60;i++)
            minutesListModel.addElement(i<10 ? "0"+i : ""+i);            
        minutesList = new JList<>(minutesListModel);
        minutesList.setFont(new Font("Arial", Font.PLAIN, 35));
    }
    
    private void setupAmPmMarkerList() {
        ampmMarkerListModel = new DefaultListModel<>();
        ampmMarkerList = new JList<>(ampmMarkerListModel);
        ampmMarkerListModel.addElement("AM");
        ampmMarkerListModel.addElement("PM");
        ampmMarkerList.setFont(new Font("Arial", Font.PLAIN, 35));
    }
    
    private void setupHoursPane() {
        hoursScrollPane = new JScrollPane(hoursList);
        hoursScrollPane.setBounds(175,100,75,50);
    }
    
    private void setupMinutesPane() {
        minutesScrollPane = new JScrollPane(minutesList);
        minutesScrollPane.setBounds(250,100,75,50);
    }
    
    private void setupAmPmMarkerPane() {
        ampmMarkerScrollPane = new JScrollPane(ampmMarkerList);
        ampmMarkerScrollPane.setBounds(325,100,75,50);
    }
    
    private void createDayTButtons() {
        this.weekDays[0]= new JToggleButton("Su");
        this.weekDays[1]= new JToggleButton("Mo");
        this.weekDays[2]= new JToggleButton("Tu");
        this.weekDays[3]= new JToggleButton("We");
        this.weekDays[4]= new JToggleButton("Th");
        this.weekDays[5]= new JToggleButton("Fr");
        this.weekDays[6]= new JToggleButton("Sa");
    }
    
    private void configureDayTButtons() {
        int x = 130;
        for (JToggleButton tb : this.weekDays) {
            tb.setBounds(x,150,45,25);
            tb.setFont(new Font("Arial", Font.PLAIN, 8));
            x += 45;    
        }
    }
    
    private void setupAlarmButtons() {
        snoozeRButton = new JRadioButton("Snooze");
        setButton = new JButton("Set");
        resetButton = new JButton("Reset");
        saveButton = new JButton("Save");
        deleteButton = new JButton("Delete");

        snoozeRButton.setBounds(300,100,80,50);
        setButton.setBounds(162, 200, 80, 50);
        resetButton.setBounds(247, 200, 80, 50);
        saveButton.setBounds(162, 200, 80, 50);
        deleteButton.setBounds(332, 200, 80, 50);
        snoozeRButton.setVisible(false);
        saveButton.setVisible(false);
        deleteButton.setEnabled(false);

        String message = "Set a day and time";
        this.setButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isTimeSelected() && isDaySelected()){
                    instantiateNewAlarm(createNewAlarmButton(), getSelectedSettings());
                    NewAlarmDialog.this.clearNewAlarmDialogSelections();
                    NewAlarmDialog.this.dispose();
                } else {
                    JOptionPane.showMessageDialog(new Frame(), message, "Select time", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        this.resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NewAlarmDialog.this.clearNewAlarmDialogSelections();
            }
        });

        this.saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isTimeSelected() && isDaySelected()){
                    int[] newAlarmSettings = NewAlarmDialog.this.getSelectedSettings();
                    changeAlarmBuffer.setAlarmSettings(newAlarmSettings);
                    NewAlarmDialog.this.dispose();
                    NewAlarmDialog.this.setButton.setVisible(true);
                    NewAlarmDialog.this.saveButton.setVisible(false);
                    changeAlarmBuffer.setAlarmTime(formatAlarmTime(newAlarmSettings[11], newAlarmSettings[12], newAlarmSettings[10]));
                    changeAlarmBuffer.getButton().setText(changeAlarmBuffer.getAlarmTime());
                    changeAlarmBuffer = null;
                } else {
                    JOptionPane.showMessageDialog(new Frame(), message, "Select time", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        this.deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                NewAlarmDialog.this.deleteButton.setEnabled(false);
                NewAlarmDialog.this.setAlarms.remove(changeAlarmBuffer);
                changeAlarmBuffer = null;
                NewAlarmDialog.this.dispose();
            }
        });
    }

    boolean isDaySelected() {
        for (JToggleButton day : weekDays){
            if (day.isSelected()){
                return true;
            }
        }
        return false;
    }
    
    boolean isTimeSelected() {
        return !this.hoursList.isSelectionEmpty()
            && !this.minutesList.isSelectionEmpty()
            && !this.ampmMarkerList.isSelectionEmpty();
    }
    
    JButton createNewAlarmButton() {
        JButton newAlarmButton = new JButton();
        newAlarmButton.setMinimumSize(new Dimension(299, 30));
        newAlarmButton.setMaximumSize(new Dimension(299,30));
        newAlarmButton.setFont(new Font("Arial", Font.PLAIN, 16));
        return newAlarmButton;
    }

    int[] getSelectedSettings() {
        // 0-6 WEEK DAYS, 7 SNOOZE, 8-9 Hours Minutes, 10 am pm, 11,12 Selected Time
        int[] selectedSettings = new int[13]; 

        for (int i = 0; i < selectedSettings.length-6; i++) 
            selectedSettings[i] = this.weekDays[i].isSelected() ? 1 : 0;
        selectedSettings[7] = this.snoozeRButton.isSelected() ? 1 : 0;
        selectedSettings[8] = this.hoursList.getSelectedIndex();
        selectedSettings[9] = this.minutesList.getSelectedIndex();
        selectedSettings[10] = this.ampmMarkerList.getSelectedIndex();
        selectedSettings[11] = Integer.parseInt(this.hoursList.getSelectedValue());
        selectedSettings[12] = Integer.parseInt(this.minutesList.getSelectedValue());
        return selectedSettings;
    }

    void instantiateNewAlarm(JButton newAlarmButton, int[] alarmSettings) {
        NewAlarm newAlarm = new NewAlarm(newAlarmButton, alarmSettings);
        newAlarm.setAlarmTime(formatAlarmTime(alarmSettings[11], alarmSettings[12], alarmSettings[10]));
        newAlarm.getButton().setText(newAlarm.getAlarmTime());
        this.setAlarms.add(newAlarm);
        newAlarmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NewAlarmDialog.this.setTitle("Edit Alarm");
                changeAlarmBuffer = newAlarm;
                NewAlarmDialog.this.setButton.setVisible(false);
                NewAlarmDialog.this.saveButton.setVisible(true);
                NewAlarmDialog.this.displaySetAlarmSettings(newAlarm.getAlarmSettings());
                NewAlarmDialog.this.deleteButton.setEnabled(true);
                NewAlarmDialog.this.setVisible(true);
            }
        });
    }
    
    private String formatAlarmTime(int hour, int minute, int ampmMarker) {
        String formattedTime = hour<10 ? "0"+hour : ""+hour;
        formattedTime += minute<10 ? ":0"+minute : ":"+minute;
        formattedTime += ampmMarker==1 ? " PM" : " AM";
        return formattedTime;
    }

    void displaySetAlarmSettings(int[] alarmSettings) {
        for (int i = 0; i < alarmSettings.length-6; i++) {
            this.weekDays[i].setSelected((alarmSettings[i] == 1) ? true : false);
        }
        this.snoozeRButton.setSelected((alarmSettings[7] == 1) ? true : false);

        this.hoursList.setSelectedIndex(alarmSettings[8]);
        this.hoursList.ensureIndexIsVisible(hoursList.getSelectedIndex());
        this.minutesList.setSelectedIndex(alarmSettings[9]);
        this.minutesList.ensureIndexIsVisible(minutesList.getSelectedIndex());
        this.ampmMarkerList.setSelectedIndex(alarmSettings[10]);
        this.ampmMarkerList.ensureIndexIsVisible(ampmMarkerList.getSelectedIndex());
    }

    void clearNewAlarmDialogSelections() {
        for (JToggleButton tb: this.weekDays)
            tb.setSelected(false);
        this.snoozeRButton.setSelected(false);
        this.hoursList.clearSelection();
        this.minutesList.clearSelection();
        this.ampmMarkerList.clearSelection();
    }

    private void addAlarmComponents() {
        this.add(hoursScrollPane);
        this.add(minutesScrollPane);
        this.add(ampmMarkerScrollPane);

        for (JToggleButton tb : this.weekDays)
            this.add(tb);
        
        this.add(snoozeRButton);
        this.add(setButton);
        this.add(resetButton);
        this.add(saveButton);
        this.add(deleteButton);
    }

    public LinkedList<NewAlarm> getSetAlarms() {
        return setAlarms;
    }

    public JButton getSetButton() {
        return setButton;
    }

    public JButton getSaveButton() {
        return saveButton;
    }
    
    public JButton getDeleteButton() {
        return deleteButton;
    }
}
