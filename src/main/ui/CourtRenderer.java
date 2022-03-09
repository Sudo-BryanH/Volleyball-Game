package ui;

import javax.swing.*;
import java.awt.*;

public class CourtRenderer extends JFrame {

    JFrame frame;

    public CourtRenderer() {

        JFrame frame = new JFrame();
        courtSetup();

    }

    public void courtSetup() {
        frame.setTitle("Volleyball Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(360, 920);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.getContentPane().setBackground(new Color(255, 174, 73));
    }

}
