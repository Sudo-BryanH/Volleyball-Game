package ui;

import model.*;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class GameAppGraphics extends JFrame implements MouseListener, ActionListener {


    CourtRenderer court;
    Game game;

    Team myTeam;
    EnemyTeam enemyTeam;
    Ball ball;

    Timer timer;



    JsonWriter jsonWriter;
    JsonReader jsonReader;
    GameData gameData;
    private static final String JSON_STORE = "./data/mostRecentGameData.json";
    private static final int INTERVAL = 17;
    private JButton nextButton;
    private JTextArea instructions;
    private JButton quitButton;
    private JButton changePlayerButton;
    private JPanel rightSidePanel;


    public GameAppGraphics() {

        instantiateGame();
        ball = new Ball();
        game.decBall(ball);
        game.setGameState("N", "N");
        myGuI();
        addTimer();

    }

    public GameAppGraphics(Team myTeam, String enemyTeam) {
        setUp(myTeam, enemyTeam);
        ball = new Ball();
        game.decBall(ball);
        game.setGameState("N", "N");
        myGuI();
        addTimer();
    }

    private void myGuI() {

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setSize(360 * 2, 920);
        setLayout(new FlowLayout(FlowLayout.LEADING, 0, 0));
        court = new CourtRenderer(game);
        addMouseListener(this);
        nextButton();
        rightSidePanel();
        quitButton();
        changePlayerButton();
        add(court);
        add(rightSidePanel);
        add(nextButton);
        add(changePlayerButton);
        add(quitButton);
        setVisible(true);
    }

    private void nextButton() {
        nextButton = new JButton("Next");
        nextButton.setPreferredSize(new Dimension(360, 30));
        nextButton.setBackground(new Color(30, 144, 255));
        nextButton.setOpaque(true);
        nextButton.addActionListener(this);
    }

    private void changePlayerButton() {
        changePlayerButton = new JButton("Switch Players");
        changePlayerButton.setPreferredSize(new Dimension(180, 30));
        changePlayerButton.setBackground(Color.WHITE);
        changePlayerButton.setOpaque(true);
        changePlayerButton.addActionListener(this);
    }

    private void quitButton() {
        quitButton = new JButton("Quit & Save");
        quitButton.setPreferredSize(new Dimension(180, 30));
        quitButton.setBackground(Color.RED);
        quitButton.setOpaque(true);
        quitButton.addActionListener(this);
    }

    private void rightSidePanel() {
        rightSidePanel = new JPanel();
        rightSidePanel.setPreferredSize(new Dimension(359, 879));
        rightSidePanel.setBackground(Color.WHITE);
        rightSidePanel.setOpaque(true);
        instructions = new JTextArea();
        instructions.setPreferredSize(new Dimension(340, 380));
        instructions.setBackground(Color.lightGray);
        instructions.setOpaque(true);
        rightSidePanel.add(instructions);
        printer("Welcome to the Volleyball Game. \nFollow the instructions onscreen.", Color.BLACK);
    }


    private void instantiateGame() {
        game = new Game();


    }


    // MODIFIES: this, MyTeam, EnemyTeam, game
    // EFFECTS: Creates enemies teams and sets up your team and players. Then, decide which team you will be playing
    public void setUp(Team myTeam, String eteam) {

        if (eteam.equals("Strong Team")) {
            enemyTeamConstructor("Strong team", 2);
        } else {
            enemyTeamConstructor("Weak team", 5);
        }

        myTeam.serveReceivePos();

        game = new Game(myTeam, enemyTeam);
        game.getMyTeam().serveReceivePos();
        game.getEnemyTeam().startPosServe();



    }

    // MODIFIES: this
    // EFFECTS: constructs enemy team
    public void enemyTeamConstructor(String name, int chance) {
        Players set = new Setters((int) Math.random() * 2 + 1, 0);
        Players mb1 = new MiddleBlockers((int) Math.random() * 2 + 3, 0);
        Players oh1 = new OutsideHitter((int) Math.random() * 2 + 5, 0);
        Players op = new OppositeHitter((int) Math.random() * 2 + 7, 0);
        Players mb2 = new MiddleBlockers((int) Math.random() * 2 + 9, 0);
        Players oh2 = new OutsideHitter((int) Math.random() * 2 + 11, 0);


        set.setRotation(1);
        mb1.setRotation(2);
        oh1.setRotation(3);
        op.setRotation(4);
        mb2.setRotation(5);
        oh2.setRotation(6);

        EnemyTeam e;
        e = new EnemyTeam(name, set, mb1, mb2, oh1, oh2, op);
        e.setChance(chance);
        enemyTeam = e;
        e.changeRotation();
        e.changeRotation();
        e.changeRotation();
        //game.setEnemyTeam(e);
        // May cause errors with subtypes

    }

/*    // MODIFIES: this
    // EFFECTS: constructs my team
    private Team myTeamConstructor(List<Players> starters, List<Players> roster) {
        String name = "My Team";

        roster.get(0).setRotation(1);
        roster.get(1).setRotation(2);
        roster.get(2).setRotation(3);
        roster.get(3).setRotation(4);
        roster.get(4).setRotation(5);
        roster.get(5).setRotation(6);

        MyTeam m;
        m = new MyTeam(roster, name);
        m.serveReceivePos();

        return m; // May cause errors with subtypes

    }*/

    // MODIFIES: this
    // EFFECTS: changes instruction text to message in color
    public void printer(String message, Color color) {
        instructions.setForeground(Color.black);
        instructions.setFont(new Font("Quicksand", 0, 13));
        instructions.setText("INSTRUCTIONS\n");
        instructions.append("\nGame State for our team: " + game.getGameState1());
        instructions.append("\nGame State for opponent team: " + game.getGameState0());
        instructions.setForeground(color);
        instructions.append("\n" + message);
    }

    // MODIFIES: this, game
    // EFFECTS: changes the state of the game. Also decides whether to continue or end the game.
    public void changeState() {
        int chance = (int) (Math.random() * 2);
        if (game.getGameState1().equals("N") && game.getGameState0().equals("N")) { // ONLY at the start of a game
            afterNN(chance);
        } else if (game.getGameState1().equals("S") && game.getGameState0().equals("SN")) {
            afterSNS();
        } else if (game.getGameState0().equals("S") && game.getGameState1().equals("SN")) {
            afterSSN();
        } else if (game.getGameState0().equals("D") && game.getGameState1().equals("A")) {
            afterDA();
        } else if (game.getGameState1().equals("D") && game.getGameState0().equals("A")) {
            afterAD(game.enemyChooseSet());
        } else if (game.getGameState1().equals("E")) {
            afterEE(chance);

        } else if (game.getGameState1().equals("F")) {
            afterFF(chance);

        }

        game.adjustPos1();
        game.adjustPos0();

    }

    // MODIFIES: this, game
    // EFFECTS: changes the state of the game to "S" "SN" and serves the ball
    private void afterEE(int chance) {
        game.setGameState("S", "SN");
        ball.directX(1);
        ball.directY(0);
        sleepThread(2);
        game.makeServe(chance);
    }

    // MODIFIES: this, game
    // EFFECTS: changes the state of the game to "SN" "S" and serves the ball
    private void afterFF(int chance) {
        game.setGameState("SN", "S");
        ball.directX(11);
        ball.directY(24);
        sleepThread(2);
        game.makeServe(chance);
    }

    // MODIFIES: this, game
    // EFFECTS: changes the state of the game to "D" "A" and receives the ball
    private void afterSSN() {

        printer("Opponent serving", Color.BLACK);
        game.receive();
        game.setGameState("D", "A");
        printer("Click one of the green players to get them to attack", Color.BLACK);
    }

    // MODIFIES: this, game
    // EFFECTS: changes the state of the game to "A" "D" and receives the ball
    private void afterSNS() {
        printer("Our serve", Color.BLACK);
        game.receive();
        game.setGameState("A", "D");
        printer("\nMove players to defend the court by"
                + " clicking on them\n and then where you want them to go.\n"
                + "\nHint: Try to match up the players to as many \nred targets as possible. "
                + "Note that only back row players can receive.\n \nPress next when you're done.", Color.BLACK);
    }

    // MODIFIES: this, game
    // EFFECTS: changes the state of the game to "S" and "SN" then serves
    private void afterNN(int chance) {
        game.setGameState("S", "SN");
        printer("Let's begin the game", Color.BLACK);
        game.makeServe(chance);
        printer("Press Next to pass to Setter", Color.BLACK);
    }

    // MODIFIES: this, game
    // EFFECTS: enemy chooses attack, our team chooses defense and determines if a point is scored. Then changes state
    private void afterAD(String dir) {
        printer(game.enemyChooseAttack(dir), Color.BLACK);
        endMyDefence(dir);
    }

    // MODIFIES: this, game
    // EFFECTS: our team chooses attack, enemy chooses defense and determines if a point is scored. Then changes state
    private void afterDA() {
        if (game.getAttackPlayer() != null && game.getAttackPoint() != null) {
            game.motionAttack();
            game.enemyDefend();
            endMyAttack();
        }
    }


    // MODIFIES: this, game
    // EFFECTS: determines if enemy scored. If not, continue match
    private void endMyDefence(String dir) {
        Boolean scored = game.checkScore();

        if (scored) {
            printer("Your opponent set " + dir + " and scored.", Color.RED);
            game.endRally(0);
            game.setGameState("E", "E");
        } else {
            printer("You have received your opponent's attack.",
                    new Color(40, 71, 82));
            game.receive();
            game.setGameState("D", "A");
        }
    }

    // MODIFIES: this, game
    // EFFECTS: determines if we scored. If not, continue match
    private void endMyAttack() {
        Boolean scored = game.checkScore();

        if (scored) {
            printer("Your team has scored.", Color.red);
            game.endRally(1);
            game.setGameState("F", "F");
        } else {
            printer("You have received your opponent's attack.\n \nMove players to defend the court by"
                    + " clicking on them\n and then where you want them to go.\n"
                    + "\nHint: Try to match up the players to as many \nred targets as possible. "
                    + "Note that only back row players can receive.\n \nPress next when you're done.",
                    new Color(40, 71, 82));
            game.receive();
            game.setGameState("A", "D");
        }

        game.setAttackPlayer(null);
        game.setAttackPoint(null);

    }




    // MODIFIES: this, game
    // EFFECTS: after each tick, visuals get updated and objects move if needed
    private void addTimer() {
        timer = new Timer(INTERVAL, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                game.update();
                court.repaint();

            }
        });

        timer.start();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        printer("You've clicked at (" + e.getX() + " ," + e.getY() + ")", new Color(21, 71, 52));
        if (game.getGameState1().equals("A")) {
            printer(game.chooseAttack(e.getX(), e.getY()), Color.BLACK);
        } else if (game.getGameState1().equals("D")) {
            printer(game.chooseDefense(e.getX(), e.getY()), Color.BLACK);
        }
    }


    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == quitButton) {
            game.quit();
        } else if (e.getSource() == nextButton) {
            changeState();
        }

    }

    // REQUIRES: positive number s
    // MODIFIES: this
    // EFFECFTS: shuts down the thread to allow gaps in animation
    private void sleepThread(int s) {

        for (int i = 0; i < s; i++) {
            try {
                printer("Press Next when the ball stops moving", Color.BLACK);
                Thread.sleep(i * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
