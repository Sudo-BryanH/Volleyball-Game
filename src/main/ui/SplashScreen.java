package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SplashScreen implements ActionListener {

    JFrame frame = new JFrame();
    JButton loadSaved = new JButton("Load the previous saved game");
    JButton loadNew = new JButton("Start a new game");

    public SplashScreen() {
        renderFrame();

    }

    private void renderFrame() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 130);

        frame.setVisible(true);

        loadSaved.setBounds(10, 10, 280, 40);
        frame.add(loadSaved);
        loadSaved.addActionListener(this);
        loadNew.setBounds(10, 55, 280, 40);
        loadNew.addActionListener(this);
        frame.add(loadNew);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loadNew) {
            frame.setVisible(false);
            new GameAppGUI(false);
        } else if (e.getSource() == loadSaved) {
            frame.setVisible(false);
            new GameAppGUI(true);
        }


    }
}
