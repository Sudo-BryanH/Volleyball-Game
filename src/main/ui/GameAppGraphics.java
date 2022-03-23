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
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class GameAppGraphics extends JFrame implements MouseListener, ActionListener {

    JFrame frame;
    CourtRenderer court;
    Game game;

    Team myTeam;
    EnemyTeam weakTeam;
    EnemyTeam strongTeam;
    Scanner scan = new Scanner(System.in);
    Players weakMB1;
    Players weakMB2;
    Players weakSet;
    Players weakOH1;
    Players weakOH2;
    Players weakOP;
    Players strongMB1;
    Players strongMB2;
    Players strongSet;
    Players strongOH1;
    Players strongOH2;
    Players strongOP;
    Players myMB1;
    Players myMB2;
    Players mySet;
    Players myOP;
    Players myOH1;
    Players myOH2;
    EnemyTeam enemyTeam;
    Ball ball;
    int turn;

    Timer timer;
    DeclarePlayers addPlayer;
    int setNum;
    int attackNum;
    boolean state;


    JsonWriter jsonWriter;
    JsonReader jsonReader;
    GameData gameData;
    private static final String JSON_STORE = "./data/mostRecentGameData.json";
    private static final int INTERVAL = 42;
    private int pressX;
    private int pressY;
    JButton nextButton;
    private JButton blankButton;


    public GameAppGraphics() {

        instantiateGame();
        ball = new Ball();
        game.decBall(ball);
        myGuI();
        addTimer();

    }

    private void myGuI() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        court = new CourtRenderer(game);
        frame.add(court);
        frame.pack();
        frame.addMouseListener(this);
        game.setGameState("F", "F");
        nextButton = new JButton("Next");
        nextButton.setBounds(300, 870, 50, 20);
        nextButton.setBackground(Color.white);
        nextButton.setOpaque(true);
        nextButton.addActionListener(this);
        blankButton = new JButton("Next");
        blankButton.setOpaque(false);
        frame.add(nextButton);
        frame.add(blankButton);
        frame.setVisible(true);
    }


    private void instantiateGame() {
        game = new Game();
        try {
            jsonReader = new JsonReader(JSON_STORE, game);
            gameData = jsonReader.read();
        } catch (IOException f) {
            System.out.println("Well, guess that didn't work. Let's start a new game then.");
            new SplashScreen();
            System.exit(0);
        }
        game.setEnemyScore(gameData.getEnemyScore());
        game.setMyScore(gameData.getMyScore());

        this.enemyTeam = gameData.getEnemyTeam();
        this.myTeam = gameData.getMyTeam();
        game.setEnemyTeam(enemyTeam);
        game.setMyTeam(myTeam);
        game.getMyTeam().startPosNoServe();
        game.getEnemyTeam().startPosServe();

    }

    public GameAppGraphics(List<Players> starters, List<Players> roster, String enemyTeam) {
        setUp(starters, roster, enemyTeam);
        ball = new Ball();
        game.decBall(ball);
        game.setGameState("F", "F");
        myGuI();
        addTimer();
    }

    // MODIFIES: this, MyTeam, EnemyTeam, game
    // EFFECTS: Creates enemies teams and sets up your team and players. Then, decide which team you will be playing
    public void setUp(List<Players> starters, List<Players> roster, String enemyTeam) {

        if (enemyTeam.equals("Strong Team")) {
            strongTeam = (EnemyTeam) enemyTeamConstructor("Strong team", strongMB1, strongMB2, strongSet,
                    strongOP, strongOH1, strongOH2, 2);
        } else {
            weakTeam = (EnemyTeam) enemyTeamConstructor("Weak team", weakMB1, weakMB2, weakSet,
                    weakOP, weakOH1, weakOH2, 5);
        }

        //myTeam = myTeamConstructor(input, mySet, myMB1, myMB2, myOH1, myOH2, myOP);
        myTeam = myTeamConstructor(starters, roster);


        game = new Game(myTeam, this.enemyTeam);
        game.getMyTeam().startPosNoServe();
        gameData = new GameData(game);
        jsonWriter = new JsonWriter(JSON_STORE);


    }

    public Team enemyTeamConstructor(String name, Players mb1, Players mb2,
                                     Players set, Players op, Players oh1,
                                     Players oh2, int chance) {
        set = new Setters(1, 0);
        mb1 = new MiddleBlockers(2, 0);
        oh1 = new OutsideHitter(3, 0);
        op = new OppositeHitter(4, 0);
        mb2 = new MiddleBlockers(5, 0);
        oh2 = new OutsideHitter(6, 0);

        EnemyTeam e;
        e = new EnemyTeam(name, set, mb1, mb2, oh1, oh2, op);
        e.setChance(chance);
        enemyTeam = e;

        return e; // May cause errors with subtypes

    }

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
        m.startPosNoServe();

        return m; // May cause errors with subtypes

    }

    @Override
    public void mouseClicked(MouseEvent e) {


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

    }


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

}
