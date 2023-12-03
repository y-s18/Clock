package clock.gui;

import java.awt.*;
import javax.swing.*;

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
    private JToggleButton monTButton;
    private JToggleButton tueTButton;
    private JToggleButton wedTButton;
    private JToggleButton thursTButton;
    private JToggleButton friTButton;
    private JToggleButton satTButton;
    private JToggleButton sunTButton;
    private JRadioButton snoozeRButton;
    private JButton setButton;
    private JButton resetButton;

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
        this.setTitle("Add New Alarm");
        this.setResizable(false);
        this.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
    }
    
    private void setupHoursList() {
        hoursListModel = new DefaultListModel<>();
        for(int i=0; i<13;i++)
        hoursListModel.addElement(" " + i);        
        hoursList = new JList<>(hoursListModel);
    }
    
    private void setupMinutesList() {
        minutesListModel = new DefaultListModel<>();
        for(int i=0; i<60;i++)
        minutesListModel.addElement(" " + i);            
        minutesList = new JList<>(minutesListModel);
    }
    
    private void setupAmPmMarkerList() {
        ampmMarkerListModel = new DefaultListModel<>();
        ampmMarkerList = new JList<>(ampmMarkerListModel);
        ampmMarkerListModel.addElement("am");
        ampmMarkerListModel.addElement("pm");
    }
    
    private void setupHoursPane() {
        hoursScrollPane = new JScrollPane(hoursList);
        hoursScrollPane.setBounds(300,250,40,25);
    }
    
    private void setupMinutesPane() {
        minutesScrollPane = new JScrollPane(minutesList);
        minutesScrollPane.setBounds(340,250,40,25);
    }
    
    private void setupAmPmMarkerPane() {
        ampmMarkerScrollPane = new JScrollPane(ampmMarkerList);
        ampmMarkerScrollPane.setBounds(380,250,40,25);
    }
    
    private void createDayTButtons() {
        monTButton = new JToggleButton("Mo");
        tueTButton = new JToggleButton("Tu");
        wedTButton = new JToggleButton("We");
        thursTButton = new JToggleButton("Th");
        friTButton = new JToggleButton("Fr");
        satTButton = new JToggleButton("Sa");
        sunTButton = new JToggleButton("Su");
    }
    
    private void configureDayTButtons() {
        monTButton.setBounds(0,0,45,25);
        monTButton.setFont(new Font("Arial", Font.PLAIN, 8));
        
        tueTButton.setBounds(45,0,45,25);
        tueTButton.setFont(new Font("Arial", Font.PLAIN, 8));
        
        wedTButton.setBounds(90,0,45,25);
        wedTButton.setFont(new Font("Arial", Font.PLAIN, 8));
        
        thursTButton.setBounds(135,0,45,25);
        thursTButton.setFont(new Font("Arial", Font.PLAIN, 8));
        
        friTButton.setBounds(180,0,45,25);
        friTButton.setFont(new Font("Arial", Font.PLAIN, 8));
        
        satTButton.setBounds(225,0,45,25);
        satTButton.setFont(new Font("Arial", Font.PLAIN, 8));
        
        sunTButton.setBounds(270,0,45,25);
        sunTButton.setFont(new Font("Arial", Font.PLAIN, 8));
    }
    
    private void setupAlarmButtons() {
        snoozeRButton = new JRadioButton("Snooze");
        setButton = new JButton("Set");
        resetButton = new JButton("Reset");
        
        snoozeRButton.setBounds(300,100,80,50);
        setButton.setBounds(100, 100, 80, 50);
        resetButton.setBounds(200, 100, 80, 50);
        
    }
    
    private void addAlarmComponents() {
        this.add(hoursScrollPane);
        this.add(minutesScrollPane);
        this.add(ampmMarkerScrollPane);
        this.add(monTButton);
        this.add(tueTButton);
        this.add(wedTButton);
        this.add(thursTButton);
        this.add(friTButton);
        this.add(satTButton);
        this.add(sunTButton);
        this.add(snoozeRButton);
        this.add(setButton);
        this.add(resetButton);
    }
    
    public JButton getSetButton() {
        return setButton;
    }
    
    public JList<String> getHoursList() {
        return hoursList;
    }
    
    public JList<String> getMinutesList() {
        return minutesList;
    }
    
    public JList<String> getAmpmMarkerList() {
        return ampmMarkerList;
    }
    
    public JToggleButton getMonTButton() {
        return monTButton;
    }
    
    public JToggleButton getTueTButton() {
        return tueTButton;
    }
    
    public JToggleButton getWedTButton() {
        return wedTButton;
    }
    
    public JToggleButton getThursTButton() {
        return thursTButton;
    }
    
    public JToggleButton getFriTButton() {
        return friTButton;
    }
    
    public JToggleButton getSatTButton() {
        return satTButton;
    }
    
    public JToggleButton getSunTButton() {
        return sunTButton;
    }
    
    public JRadioButton getSnoozeRButton() {
        return snoozeRButton;
    }

    public JButton getResetButton() {
        return resetButton;
    }
}
