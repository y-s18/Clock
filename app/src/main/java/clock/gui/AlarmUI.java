package clock.gui;

import java.awt.event.*;
import javax.swing.*;

public class AlarmUI extends JPanel {
    private JButton addButton;
    private JPanel alarmsListPanel;
    private JScrollPane alarmsListScrollPane;
    private JDialog naDialog;
    
    public AlarmUI(){
        naDialog = new NewAlarmDialog();

        configureAlarmUIPanel();
        setupAlarmUIButtons();
        setupAlarmsList();
        configureNewAlarmDialogButtons();
        addAlarmUIComponents();
    }
    
    private void configureAlarmUIPanel() {
        this.setLayout(null);
    }

    private void setupAlarmUIButtons() {
        addButton = new JButton("Add");
        addButton.setBounds(100, 100, 80, 50);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AlarmUI.this.naDialog.setVisible(true);
            }
        });
    }
    
    private void setupAlarmsList() {
        alarmsListPanel = new JPanel();
        this.alarmsListPanel.setLayout(new BoxLayout(alarmsListPanel, BoxLayout.PAGE_AXIS));
        this.alarmsListPanel.setBounds(1,1,98,98);
        alarmsListScrollPane = new JScrollPane(alarmsListPanel, 
                    JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                    JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        this.alarmsListScrollPane.setBounds(500, 100, 100, 100);
    }

    private void configureNewAlarmDialogButtons() {
        ((NewAlarmDialog) this.naDialog).getSetButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addNewAlarmButton();
                clearNewAlarmDialogSelections();
                AlarmUI.this.naDialog.dispose();
            }
        });
        
        ((NewAlarmDialog) this.naDialog).getResetButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearNewAlarmDialogSelections();
            }
        });
    }

    protected void addNewAlarmButton() {
        JButton newAlarm = new JButton("new Alarm");
        newAlarm.setSize(75, 26);
        AlarmUI.this.alarmsListPanel.add(newAlarm);
    }
    
    protected void clearNewAlarmDialogSelections() {
        ((NewAlarmDialog) this.naDialog).getMonTButton().setSelected(false);
        ((NewAlarmDialog) this.naDialog).getTueTButton().setSelected(false);
        ((NewAlarmDialog) this.naDialog).getWedTButton().setSelected(false);
        ((NewAlarmDialog) this.naDialog).getThursTButton().setSelected(false);
        ((NewAlarmDialog) this.naDialog).getFriTButton().setSelected(false);
        ((NewAlarmDialog) this.naDialog).getSatTButton().setSelected(false);
        ((NewAlarmDialog) this.naDialog).getSunTButton().setSelected(false);
        ((NewAlarmDialog) this.naDialog).getSnoozeRButton().setSelected(false);

        ((NewAlarmDialog) this.naDialog).getHoursList().clearSelection();
        ((NewAlarmDialog) this.naDialog).getMinutesList().clearSelection();
        ((NewAlarmDialog) this.naDialog).getAmpmMarkerList().clearSelection();
    }

    private void addAlarmUIComponents() {
        this.add(addButton);
        this.add(alarmsListScrollPane);
    }
}