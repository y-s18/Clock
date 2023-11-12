package clock.gui;

import javax.swing.*;

public class AlarmUI extends JPanel {
    private DefaultListModel<String> hoursListModel;
    private DefaultListModel<String> minutesListModel;
    private DefaultListModel<String> ampmMarkerListModel;
    private JList<String> hoursList;
    private JList<String> minutesList;
    private JList<String> ampmMarkerList;
    private JScrollPane hoursScrollPane;
    private JScrollPane minutesScrollPane;
    private JScrollPane ampmMarkerScrollPane;    

    public AlarmUI(){
        this.setLayout(null);

        setupHoursList();
        setupMinutesList();
        setupAmPmMarkerList();

        setupHoursPane();
        setupMinutesPane();
        setupAmPmMarkerPane();

        addAlarmComponenets();       
        
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
        hoursScrollPane.setBounds(0,0,40,25);
    }
    
    private void setupMinutesPane() {
        minutesScrollPane = new JScrollPane(minutesList);
        minutesScrollPane.setBounds(40,0,40,25);
    }
    
    private void setupAmPmMarkerPane() {
        ampmMarkerScrollPane = new JScrollPane(ampmMarkerList);
        ampmMarkerScrollPane.setBounds(80,0,40,25); 
    }

    private void addAlarmComponenets() {
        this.add(hoursScrollPane);
        this.add(minutesScrollPane);
        this.add(ampmMarkerScrollPane);
    }
}