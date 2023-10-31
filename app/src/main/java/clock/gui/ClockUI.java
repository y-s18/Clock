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
        button.setBounds(350,200,100,50);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                is24Format = is24Format ? false : true;
            }
        });

        switchAnalogueButton = new JButton(">");
        switchAnalogueButton.setBounds(100,100,50,50);
        switchAnalogueButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                layout.show(deck, "analogue");               
            }
        });

        switchDigitalButton = new JButton("<");
        switchDigitalButton.setBounds(100,100,50,50);
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
        String currentTimeString = formatter.format(currentTime);
        int currentHourInt = Integer.parseInt(currentTimeString.substring(0,2));
        String currentHour24FormatStr = "";
        String am_pmString = "AM";
        // we get 24 format
        if(is24Format){
            currentHour24FormatStr = 
                String.valueOf(currentHourInt).concat((String) currentTimeString.subSequence(2, currentTimeString.length()));
        } else {
            if(currentHourInt>12){
                currentHourInt -= 12;
                am_pmString="PM";
            } else if (currentHourInt<12){
                if(currentHourInt == 0) currentHourInt +=12;
                am_pmString="AM";
            } else{
                am_pmString="PM";
            }
            currentHour24FormatStr = 
                String.valueOf(currentHourInt).concat((String) currentTimeString.subSequence(2, currentTimeString.length()) + " " + am_pmString);
        }
        button.setText(currentHour24FormatStr);        
    }

    public static void main(String[] args) {
        new ClockUI();
    }

}
