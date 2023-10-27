package clock.gui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.*;

public class ClockUI implements Runnable {
    Thread thread = null;
    JFrame frame;
    JButton button;
    DateTimeFormatter dtf;
    
    
    public ClockUI() {
        frame = new JFrame("Clock");
        
        thread = new Thread(this);
        thread.start();

        button = new JButton();        
        button.setBounds(350,200,100,50);
        
        frame.add(button);
        frame.setSize(800, 600);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    @Override
    public void run() {
        try {
            while (true) {
                dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
                LocalDateTime currentTime = LocalDateTime.now();

                printTime(currentTime);

                thread.sleep(1000);
            }
            

        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    private void printTime(LocalDateTime currentTime) {
        button.setText(dtf.format(currentTime));        
    }

    public static void main(String[] args) {
        new ClockUI();
    }

}
