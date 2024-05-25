package TankGame;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Scanner;

public class MyFrame extends JFrame {
    MyPanel myPanel;
    public static void main(String[] args) {
        new MyFrame();
    }
    public MyFrame() {
        System.out.println("Please choose to start the game or load the record: 1 for start, 2 for load record.");
        Scanner scanner = new Scanner(System.in);
        String key = scanner.next();
        myPanel = new MyPanel(key);
        // start the panel thread: for continously repainting...
        new Thread(myPanel).start();
        this.add(myPanel);
        this.setSize(1000, 750);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Add the key listener to the frame and let the panel implement the key listener
        this.addKeyListener(myPanel);
        // respond to close the window by storing the record in the txt file
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Recorder.keepRecord();
                System.exit(0);
            }
        });
    }
}
