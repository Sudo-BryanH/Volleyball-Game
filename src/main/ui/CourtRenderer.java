package ui;

import model.Ball;
import model.Game;
import model.Players;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CourtRenderer extends JPanel {


    Game game;


    static int P_TRANS = -15;
    static int B_TRANS = -25;

    // MODIFIES: this
    // EFFECTS: constructs a new court renderer panel
    public CourtRenderer(Game game) {

        this.game = game;


        setPreferredSize(new Dimension(360, 880));
        setBackground(Color.WHITE);
        setOpaque(true);

    }

    // MODIFIES: this
    // EFFECTS: paints the ball
    private void ballSetup(Graphics g) {

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

    // MODIFIES: this
    // EFFECTS: paints the players
    private void playersSetup(Graphics g) {

        paintMyTeam(g);
        paintEnemyTeam(g);
    }

    // MODIFIES: this
    // EFFECTS: paints the players on my team
    public void paintMyTeam(Graphics g) {

        for (Players p : game.getMyTeam().getStarters()) {
            int x = p.getPosX();
            int y = p.getPosY();
            int num = p.getNum();


            if ((game.getGameState1().equals("A") && p.getRotation() >= 4 && !p.getPlayingPosition().equals("Setter"))
                    || p == game.getAttackPlayer() || p == game.getSelectDefensive()) {
                g.setColor(Color.green);
                g.fillOval(x + P_TRANS, y + P_TRANS, 30, 30);
            } else {
                g.setColor(new Color(46, 196, 182));
                g.fillOval(x + P_TRANS, y + P_TRANS, 30, 30);
            }
            g.setColor(new Color(50, 140, 220));
            g.drawString(p.getShortPos(), x, (y + P_TRANS));
            g.drawString("(" + x / 30 + ", " + ((y - 100) / 30) + ")", x, (y - 30));
            g.setColor(Color.WHITE);
            g.setFont(new Font("Monospaced Bold", 20, 12));
            g.drawString("" + num, x - 10, y + 10);
            if (p == game.getAttackPlayer()) {
                drawPossibleAttacks(g, p, Color.GREEN);
            }
            paintHalos(g, 1, p);

        }
    }

    // MODIFIES: this
    // EFFECTS: adds a ring to select players
    private void paintHalos(Graphics g, int side, Players p) {
        int x = p.getPosX();
        int y = p.getPosY();

        if (side == 1) {
            if (game.getGameState1().equals("D") && p.getRotation() < 4 && !p.getPlayingPosition().equals("Setter")) {
                g.setColor(Color.WHITE);
                g.drawRoundRect(x + 2 * P_TRANS, y + 2 * P_TRANS, 60, 60, 15, 15);
            } else if (game.getGameState1().equals("A") && p.getRotation() >= 4
                    && !p.getPlayingPosition().equals("Setter")) {
                if (p.getRotation() >= 4 && !p.getPlayingPosition().equals("Setter")) {
                    g.setColor(Color.GREEN);
                    g.drawOval(x + 2 * P_TRANS, y + 2 * P_TRANS, 60, 60);

                }
            }
        } else if (side == 0) {
            if (game.getGameState0().equals("D") && p.getRotation() < 4 && !p.getPlayingPosition().equals("Setter")) {
                g.setColor(Color.WHITE);
                g.drawRoundRect(x + 2 * P_TRANS, y + 2 * P_TRANS, 60, 60, 15, 15);
            } else if (game.getGameState0().equals("A") && p.getRotation() >= 4
                    && !p.getPlayingPosition().equals("Setter")) {
                g.setColor(Color.RED);
                g.drawOval(x + 2 * P_TRANS, y + 2 * P_TRANS, 60, 60);

            }
        }
    }

    // MODIFIES: this
    // EFFECTS: paints the enemy Team players
    public void paintEnemyTeam(Graphics g) {
        for (Players p: game.getEnemyTeam().getStarters()) {

            JButton button; // if all else fails, use this

            int x = p.getPosX();
            int y = p.getPosY();
            int num = p.getNum();


            paintHalos(g, 0, p);

            if (game.getGameState0().equals("A") && p.getRotation() >= 4 && !p.getPlayingPosition().equals("Setter")) {
                g.setColor(Color.RED);
                g.fillOval(x + P_TRANS, y + P_TRANS, 30, 30);
                g.drawString(p.getShortPos(), x, (y + P_TRANS));
                g.drawString("(" + x / 30 + ", " + ((y - 100) / 30) + ")", x, (y - 30));
                drawPossibleAttacks(g, p, Color.RED);
            } else {
                g.setColor(Color.darkGray);
                g.fillOval(x + P_TRANS, y + P_TRANS, 30, 30);
                g.drawString(p.getShortPos(), x, (y + P_TRANS));
                g.drawString("(" + x / 30 + ", " + ((y - 100) / 30) + ")", x, (y - 30));

            }
            g.setColor(Color.WHITE);
            g.setFont(new Font("Monospaced Bold", 20, 20));
            g.drawString("" + num, x - 10, y + 10);
            g.setFont(new Font("Quicksand", 20, 12));
        }


    }

    // MODIFIES: this
    // EFFECTS: draws the possible atttack of a given player
    private void drawPossibleAttacks(Graphics g, Players p, Color color) {
        g.setColor(color);
        for (Point point : p.getAttackPoints(p.getSide())) {
            int x = (int) point.getX() * 30;
            int y = (int) point.getY() * 30 + 100;
            g.drawOval((int) x - 20, (int) (y - 20), 40, 40);
            g.fillOval((int) x - 7, (int) (y - 7), 15, 15);



        }



    }

    // MODIFIES: this
    // EFFECTS: draws the lines of the court
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

    // MODIFIES: this
    // EFFECTS: draws the court
    public void courtSetup(Graphics g) {

        g.setColor(Color.white);
        g.fillRect(0, 0, 360,  920);
        blueSetup(g);
        orangeSetup(g);
    }

    // MODIFIES: this
    // EFFECTS: draws the blue of the court
    public void blueSetup(Graphics g) {

        g.setColor(new Color(0, 181, 226));
        g.fillRect(0, 0, 360, 100);
        g.setColor(new Color(0, 181, 226));
        g.fillRect(0, 820, 360, 60);

    }

    // MODIFIES: this
    // EFFECTS: draws the gridlines and scoreboard
    public void scoreBoardAndGrid(Graphics g) {

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

        g.setColor(Color.black);
        g.setFont(new Font("Monospaced Bold", 20, 20));
        g.drawString(game.getScore(), 10, 30);

    }



    // MODIFIES: this
    // EFFECTS: paints the entire panel
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        courtSetup(g);
        scoreBoardAndGrid(g);
        g.setFont(new Font("Quicksand", 20, 12));
        playersSetup(g);
        ballSetup(g);


    }



}
