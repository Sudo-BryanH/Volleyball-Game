package ui;

import model.Ball;
import model.Game;
import model.Players;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CourtRenderer extends JPanel {

    //JFrame frame;
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

        //frame = new JFrame();

        setPreferredSize(new Dimension(360, 920));
        setBackground(Color.WHITE);

    }

    private void ballSetup(Graphics g) {
        JLabel label;
        Ball ball = game.getBall();

        int x = ball.getXPos();
        int y = ball.getYPos();
    /*
        label = new JLabel();
        Icon icon = new ImageIcon("tobs.jpg");
        label.setBounds(x, y, 20, 20);
        label.setBackground(Color.yellow);
        label.setIcon(icon);
        label.setOpaque(true);
        frame.add(label);
    */

        g.setColor(Color.yellow);
        g.fillOval(x, y, 20, 20);


    }

    private void playersSetup(Graphics g) {


        for (Players p : game.getMyTeam().getStarters()) {

            int x = p.getPosX();
            int y = p.getPosY();
            int num = p.getNum();
            String pos = p.getPlayingPosition();
            g.setColor(new Color(46, 196,182));
            g.fillOval(x, y, 30, 30);
            g.drawString(pos + ": " + num, x, y);

        }

        for (Players p: game.getEnemyTeam().getStarters()) {

            int x = p.getPosX();
            int y = p.getPosY();
            int num = p.getNum();
            String pos = p.getPlayingPosition();

            g.setColor(new Color(184, 15, 10));
            g.fillOval(x, y, 30, 30);
            g.drawString(pos + ": " + num, x, y);
        }


    }

    private void orangeSetup(Graphics g) {


        g.setColor(new Color(255, 174, 73));
        g.fillRect(10, 110, 340, 250);

        g.setColor(new Color(255, 174, 73));
        g.fillRect(10, 365, 340, 80);

        g.setColor(Color.darkGray);
        g.fillRect(0, 448, 360, 5);

        g.setColor(new Color(255, 174, 73));
        g.fillRect(10, 455, 340, 80);

        g.setColor(new Color(255, 174, 73));
        g.fillRect(10, 540, 340, 250);


    }

    public void courtSetup(Graphics g) {

        g.setColor(Color.white);
        g.fillRect(0, 0, 360,  920);
        blueSetup(g);
        orangeSetup(g);
    }

    public void blueSetup(Graphics g) {

        g.setColor(new Color(0, 181, 226));
        g.fillRect(0, 0, 360, 100);
        g.setColor(new Color(0, 181, 226));
        g.fillRect(0, 800, 360, 100);

    }

    public void scoreBoard(Graphics2D g) {
        g.drawString(game.getScore(), 0, 0);




    }



    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        courtSetup(g);
        playersSetup(g);
        ballSetup(g);
        scoreBoard((Graphics2D) g);


    }


}
