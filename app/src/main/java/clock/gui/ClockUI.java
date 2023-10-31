package clock.gui;

import java.awt.*;
import java.awt.event.*;
import java.text.*;
import java.util.*;
import javax.swing.*;

public class ClockUI extends JPanel implements Runnable {
    Thread thread = null;
    JButton button;
    Calendar cal;
    Date currentTime;
    SimpleDateFormat formatter;
    boolean is24Format = false; 
    CardLayout layout;
    JPanel deck;
    JPanel analoguePanel;
    JButton switchAnalogueButton;
    JButton switchDigitalButton;

    public ClockUI() {
        layout = new CardLayout();
        deck = new JPanel();
        deck.setLayout(layout);

        analoguePanel = new JPanel();
        analoguePanel.setSize(600,400);
        analoguePanel.setLayout(null);

        this.setSize(600,400);

        deck.add(this, "digital");
        deck.add(analoguePanel, "analogue");
        
        thread = new Thread(this);
        thread.start();
        
        button = new JButton();        
        button.setBounds(325,200,150,50);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                is24Format = is24Format ? false : true;
            }
        });

        switchAnalogueButton = new JButton(">");
        switchAnalogueButton.setBounds(700,200,50,50);
        switchAnalogueButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                layout.show(deck, "analogue");               
            }
        });

        switchDigitalButton = new JButton("<");
        switchDigitalButton.setBounds(700,200,50,50);
        switchDigitalButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                layout.show(deck, "digital");                
            }
        });

        this.add(button);
        this.add(switchAnalogueButton);
        analoguePanel.add(switchDigitalButton);
        this.setLayout(null);
    }

    @Override
    public void run() {
        try {
            while (true) {

                formatter = new SimpleDateFormat("hh:mm:ss");
                cal = Calendar.getInstance();
                currentTime = cal.getTime();

                printTime(currentTime);

                Thread.sleep(1000);
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    private void printTime(Date currentTime) {
        String currentTimeString;
        
        if(is24Format){
            formatter.applyPattern("HH:mm:ss");
            currentTimeString = formatter.format(currentTime);
        } else {
            formatter.applyPattern("hh:mm:ss");
            if(cal.get(Calendar.AM_PM) == 0){
                currentTimeString = formatter.format(currentTime) + " AM";
            } else {
                currentTimeString = formatter.format(currentTime) + " PM";
            }
        }
        button.setText(currentTimeString);        
    }

    public static void main(String[] args) {
        new ClockUI();
    }

}
