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
        addWindowListenerToNADialog();
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
                ((NewAlarmDialog) AlarmUI.this.naDialog).clearNewAlarmDialogSelections();
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
}