package clock.gui;

import java.awt.*;
import java.awt.event.*;
import java.text.*;
import java.util.*;
import javax.swing.*;

public class ClockUI extends JPanel implements Runnable {
    Thread thread = null;
    JButton timeFormatSwitcher;
    Calendar cal;
    Date currentTime;
    SimpleDateFormat formatter = new SimpleDateFormat("hh:mm:ss");
    boolean is24Format = false; 
    CardLayout cardLayout;
    JPanel digitalPanel;
    JPanel analoguePanel;
    JButton switchAnalogueButton;
    JButton switchDigitalButton;

    public ClockUI() {
        setUpAnaloguePanel();
        setUpDigitalPanel();
        setUpDeckPanel();

        thread = new Thread(this);
        thread.start();
    }

    private void setUpDeckPanel() {
        cardLayout = new CardLayout();
        this.setLayout(cardLayout);
        addDeckComponents();
    }

    private void addDeckComponents() {
        this.add(digitalPanel, "digital");
        this.add(analoguePanel, "analogue");
    }

    private void setUpDigitalPanel() {
        digitalPanel = new JPanel();
        digitalPanel.setSize(600,400);
        digitalPanel.setLayout(null);
        setUpDigitalComponents();
        addDigitalComponents();
    }

    private void setUpDigitalComponents() {
        timeFormatSwitcher = new JButton();        
        timeFormatSwitcher.setBounds(325,200,150,50);
        timeFormatSwitcher.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                is24Format = is24Format ? false : true;
            }
        });

        switchAnalogueButton = new JButton(">");
        switchAnalogueButton.setBounds(700,200,50,50);
        switchAnalogueButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                cardLayout.show(ClockUI.this, "analogue");               
            }
        });
    }

    private void addDigitalComponents() {
        digitalPanel.add(timeFormatSwitcher);
        digitalPanel.add(switchAnalogueButton);
    }
    
    private void setUpAnaloguePanel() {
        analoguePanel = new AnalogueUI();
        analoguePanel.setSize(600,400);
        analoguePanel.setLayout(null);
        setUpAnalogueComponents();
        addAnalogueComponents();
    }

    private void setUpAnalogueComponents() {
        switchDigitalButton = new JButton("<");
        switchDigitalButton.setBounds(700,200,50,50);
        switchDigitalButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                cardLayout.show(ClockUI.this, "digital");                
            }
        });
    }

    private void addAnalogueComponents() {
        analoguePanel.add(switchDigitalButton);
    }

    @Override
    public void run() {
        try {
            while (true) {
                displayTime(getCurrentTime());
                // Thread.sleep(1000);
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    private Date getCurrentTime() {
        cal = Calendar.getInstance();
        return cal.getTime();
    }

    private void displayTime(Date currentTime) {
        if(is24Format){
            formatter.applyPattern("HH:mm:ss");
        } else {
            formatter.applyPattern("hh:mm:ss a");
        }
        timeFormatSwitcher.setText(formatter.format(currentTime));        
    }

}
