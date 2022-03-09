package ui;

import model.Game;

import javax.swing.*;
import java.awt.*;

public class CourtRenderer extends JFrame {

    JFrame frame;
    JPanel blueTop;
    JPanel blueBottom;
    JPanel orange1;
    JPanel orange2;
    JPanel orange3;
    JPanel orange4;
    String score;
    Game game;
    JLabel scoreBoard;
    JPanel net;

    public CourtRenderer(Game game) {

        this.game = game;

        frame = new JFrame();
        courtSetup();
        blueSetup();
        orangeSetup();

    }

    private void orangeSetup() {
        orange1 = new JPanel();
        orange2 = new JPanel();
        orange3 = new JPanel();
        orange4 = new JPanel();
        net = new JPanel();

        orange1.setBackground(new Color(255, 174, 73));
        orange1.setBounds(10, 110, 340, 250);
        frame.add(orange1);

        orange2.setBackground(new Color(255, 174, 73));
        orange2.setBounds(10, 365, 340, 80);
        frame.add(orange2);

        net.setBackground(Color.DARK_GRAY);
        net.setBounds(0, 448, 360, 5);
        frame.add(net);

        orange3.setBackground(new Color(255, 174, 73));
        orange3.setBounds(10, 455, 340, 80);
        frame.add(orange3);

        orange4.setBackground(new Color(255, 174, 73));
        orange4.setBounds(10, 540, 340, 250);
        frame.add(orange4);


    }

    public void courtSetup() {
        frame.setTitle("Volleyball Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(360, 920);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.getContentPane().setBackground(new Color(255, 255, 255));
    }

    public void blueSetup() {
        blueTop = new JPanel();
        blueBottom = new JPanel();

        blueTop.setBackground(new Color(0, 181, 226));
        blueTop.setBounds(0, 0, 360, 100);
        frame.add(blueTop);

        blueBottom.setBackground(new Color(0, 181, 226));
        blueBottom.setBounds(0, 800, 360, 100);
        frame.add(blueBottom);

    }

    public void scoreBoard() {
        scoreBoard = new JLabel();


    }

}
