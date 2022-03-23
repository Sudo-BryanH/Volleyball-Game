package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SplashScreen extends JFrame implements ActionListener {

    JFrame frame = new JFrame();
    JButton loadSaved = new JButton("Load the previous saved game");
    JButton loadNew = new JButton("Start a new game");
    Boolean load;

    // EFFECTS: constructs a splash screen
    public SplashScreen() {
        renderFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    // MODIFIES: this
    // EFFECTS: creates the frame and adds panels
    private void renderFrame() {
        frame.setSize(300, 130);

        frame.setVisible(true);

        loadSaved.setBounds(10, 10, 280, 40);
        frame.add(loadSaved);
        loadSaved.addActionListener(this);
        loadNew.setBounds(10, 55, 280, 40);
        loadNew.addActionListener(this);
        frame.add(loadNew);

    }


    // MODIFIES this
    // EFFECTS: detects user input, if loadNew, move to declare players ui, else load old game
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loadNew) {
            frame.setVisible(false);
            new DeclarePlayers();
        } else if (e.getSource() == loadSaved) {
            frame.setVisible(false);
            new GameAppGUI();
        }


    }
}
