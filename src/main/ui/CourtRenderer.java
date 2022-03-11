package ui;

import model.Ball;
import model.Game;
import model.Players;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;

public class CourtRenderer extends JPanel {


    Game game;


    static int P_TRANS = -15;
    static int B_TRANS = -25;

    public CourtRenderer(Game game) {

        this.game = game;

        //frame = new JFrame();

        setPreferredSize(new Dimension(360, 920));
        setBackground(Color.WHITE);

    }

    private void ballSetup(Graphics g) {
        JLabel label;
        Ball ball = game.getBall();

        int x = ball.getCurrentXPos();
        int y = ball.getCurrentYPos();

        Graphics2D g2d = (Graphics2D) g;

        Image molten = new ImageIcon("./data/image1.png").getImage();

        g2d.drawImage(molten, x + B_TRANS, y + B_TRANS, 50, 50, null);

        g.setColor(Color.black);
        g.drawString("(" + x + ", " + y + ")", x, y);
        g.drawString("(" + x / 30 + ", " + ((y - 100) / 30) + ")", x, (y - 20));


    }

    private void playersSetup(Graphics g) {


        for (Players p : game.getMyTeam().getStarters()) {

            int x = p.getPosX();
            int y = p.getPosY();
            int num = p.getNum();
            String pos = p.getPlayingPosition();

            if (p.getRotation() < 4 && !p.getPlayingPosition().equals("Setter")) {
                g.setColor(Color.WHITE);
                g.drawRoundRect(x + 2 * P_TRANS, y + 2 * P_TRANS, 60, 60, 15, 15);
            }

            g.setColor(new Color(46, 196,182));
            g.fillOval(x + P_TRANS, y + P_TRANS, 30, 30);
            g.drawString(pos + ": " + num, x, (y + P_TRANS));
            g.drawString("(" + x / 30 + ", " + ((y - 100) / 30) + ")", x, (y - 30));


        }

        for (Players p: game.getEnemyTeam().getStarters()) {

            int x = p.getPosX();
            int y = p.getPosY();
            int num = p.getNum();
            String pos = p.getPlayingPosition();

            if (p.getRotation() < 4 && !p.getPlayingPosition().equals("Setter")) {
                g.setColor(Color.WHITE);
                g.drawRoundRect(x + 2 * P_TRANS, y + 2 * P_TRANS, 60, 60, 15, 15);
            }

            g.setColor(new Color(184, 15, 10));
            g.fillOval(x + P_TRANS, y + P_TRANS, 30, 30);
            g.drawString(pos + ": " + num, x, (y + P_TRANS));
            g.drawString("(" + x / 30 + ", " + ((y - 100) / 30) + ")", x, (y - 30));
        }


    }

    private void orangeSetup(Graphics g) {


        g.setColor(new Color(255, 174, 73));
        g.fillRect(10, 110, 340, 260);

        g.setColor(new Color(255, 174, 73));
        g.fillRect(10, 375, 340, 170);

        g.setColor(Color.GRAY);
        g.fillRect(0, 458, 360, 5);


        g.setColor(new Color(255, 174, 73));
        g.fillRect(10, 550, 340, 260);


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
        g.fillRect(0, 820, 360, 100);

    }

    public void scoreBoardAndGrid(Graphics g) {
        g.setColor(Color.black);

        g.drawString(game.getScore(), 10, 30);

        for (int i = 0; i <= 24; i++) {
            g.setColor(Color.lightGray);
            g.fillRect(0, (i * 30) + 100, 360, 1);
            g.drawString("" + i, 0, (i * 30) + 100);

        }

        for (int i = 0; i <= 12; i++) {
            g.setColor(Color.lightGray);
            g.fillRect((i * 30), 100, 1, 720);
            g.drawString("" + i, (i * 30),  100);

        }



    }



    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        courtSetup(g);
        scoreBoardAndGrid(g);
        playersSetup(g);
        ballSetup(g);



    }


}
