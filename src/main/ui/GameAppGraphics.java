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
    private static final int INTERVAL = 17;
    private JButton nextButton;
    private JTextArea instructions;
    private JButton quitButton;
    private JButton changePlayerButton;
    private int servePos;


    public GameAppGraphics() {

        instantiateGame();
        ball = new Ball();
        game.decBall(ball);
        game.setGameState("N", "N");
        myGuI();
        addTimer();

    }

    public GameAppGraphics(List<Players> starters, List<Players> roster, String enemyTeam) {
        setUp(starters, roster, enemyTeam);
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
        nextButton = new JButton("Next");
        nextButton.setPreferredSize(new Dimension(360, 30));
        nextButton.setBackground(new Color(30, 144, 255));
        nextButton.setOpaque(true);
        nextButton.addActionListener(this);
        instructions = new JTextArea();
        instructions.setPreferredSize(new Dimension(350, 810));
        instructions.setBackground(Color.lightGray);
        instructions.setOpaque(true);
        printer("Welcome to the Volleyball Game. \nFollow the instructions onscreen.", Color.BLACK);
        quitButton = new JButton("Quit & Save");
        quitButton.setPreferredSize(new Dimension(180, 30));
        quitButton.setBackground(Color.RED);
        quitButton.setOpaque(true);
        quitButton.addActionListener(this);
        changePlayerButton = new JButton("Switch Players");
        changePlayerButton.setPreferredSize(new Dimension(180, 30));
        changePlayerButton.setBackground(Color.WHITE);
        changePlayerButton.setOpaque(true);
        changePlayerButton.addActionListener(this);
        add(court);
        add(instructions);
        add(nextButton);
        add(changePlayerButton);
        add(quitButton);

        //pack();
        setVisible(true);
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
        game.getMyTeam().serveReceivePos();
        game.getEnemyTeam().startPosServe();

    }


    // MODIFIES: this, MyTeam, EnemyTeam, game
    // EFFECTS: Creates enemies teams and sets up your team and players. Then, decide which team you will be playing
    public void setUp(List<Players> starters, List<Players> roster, String eteam) {

        if (eteam.equals("Strong Team")) {
            enemyTeamConstructor("Strong team", strongMB1, strongMB2, strongSet,
                    strongOP, strongOH1, strongOH2, 2);
        } else {
            enemyTeamConstructor("Weak team", weakMB1, weakMB2, weakSet,
                    weakOP, weakOH1, weakOH2, 5);
        }

        myTeam = myTeamConstructor(starters, roster);

        game = new Game(myTeam, enemyTeam);
        game.getMyTeam().serveReceivePos();
        game.getEnemyTeam().startPosServe();
        gameData = new GameData(game);
        jsonWriter = new JsonWriter(JSON_STORE);


    }

    public void enemyTeamConstructor(String name, Players mb1, Players mb2,
                                     Players set, Players op, Players oh1,
                                     Players oh2, int chance) {
        set = new Setters((int) Math.random() * 2 + 1, 0);
        mb1 = new MiddleBlockers((int) Math.random() * 2 + 3, 0);
        oh1 = new OutsideHitter((int) Math.random() * 2 + 5, 0);
        op = new OppositeHitter((int) Math.random() * 2 + 7, 0);
        mb2 = new MiddleBlockers((int) Math.random() * 2 + 9, 0);
        oh2 = new OutsideHitter((int) Math.random() * 2 + 11, 0);

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

    }

    // MODIFIES: this
    // EFFECTS: changes instruction text to message in color
    public void printer(String message, Color color) {
        instructions.setForeground(Color.black);
        instructions.setText("Game State for our team: " + game.getGameState1());
        instructions.append("\nGame State for opponent team: " + game.getGameState0());
        instructions.setForeground(color);
        instructions.append("\n" + message);
    }

    // MODIFIES: this, game
    // EFFECTS: changes the state of the game. Also decides whether to continue or end the game.
    public void changeState() {
        if (game.getGameState1().equals("N") && game.getGameState0().equals("N")) { // ONLY at the start of a game
            game.setGameState("S", "SN");
            printer("Let's begin the game", Color.BLACK);
            game.makeServe();
            printer("Ball passed to Setter", Color.BLACK);
        } else if (game.getGameState1().equals("S") && game.getGameState0().equals("SN")) {
            printer("Our serve", Color.BLACK);
            game.receive();
            game.setGameState("A", "D");
            printer("Move players to defend the court by"
                    + " clicking on them\n and then somewhere else."
                    + "\nTry to match up the players to as many red targets as\npossible. "
                    + "Note that only backrow players can receive. \nPress next when you're done.", Color.BLACK);
        } else if (game.getGameState0().equals("S") && game.getGameState1().equals("SN")) {
            printer("Opponent serving", Color.BLACK);
            game.receive();
            game.setGameState("D", "A");
            printer("Ball passed to Setter", Color.BLACK);
        } else if (game.getGameState0().equals("D") && game.getGameState1().equals("A")) {
            afterDA();
        } else if (game.getGameState1().equals("D") && game.getGameState0().equals("A")) {
            afterAD(game.enemyChooseSet());
        } else if (game.getGameState1().equals("E")) {
            game.setGameState("S", "SN");
        } else if (game.getGameState1().equals("F")) {
            game.setGameState("SN", "S");
        }

        game.adjustPos1();
        game.adjustPos0();

    }

    private void afterAD(String dir) {
        printer(game.enemyChooseAttack(dir), Color.BLACK);
        endMyDefence();
    }

    private void afterDA() {
        if (game.getAttackPlayer() != null && game.getAttackPoint() != null) {
            game.myAttack();
            game.enemyDefend();
            endMyAttack();
        }
    }


    // MODIFIES: this, game
    // EFFECTS: determines if enemy scored. If not, continue match
    private void endMyDefence() {
        Boolean scored = game.checkScore();

        if (scored) {
            printer("Your Opponent has scored.", Color.RED);
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
            printer("You have received your opponent's attack. \nMove players to defend the court by"
                    + " clicking on them\n and then somewhere else."
                    + "\nTry to match up the players to as many red targets as\n possible. "
                    + "Note that only backrow players can receive. \nPress next when you're done.", new Color(40, 71, 82));
            game.receive();
            game.setGameState("A", "D");
        }

        game.setAttackPlayer(null);
        game.setAttackPoint(null);

    }


    // EFFECTS: end game and saves data
    private void quit() {
        save();
        System.exit(0);
    }

    // EFFECTS: determines if the player wants to save. If yes, save game data
    private void save() {

        try {

            jsonWriter = new JsonWriter(JSON_STORE);
            jsonWriter.open();
            jsonWriter.write(gameData);
            jsonWriter.close();
            printer("Saved data to " + JSON_STORE, Color.RED);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);

        }

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
            quit();
        } else if (e.getSource() == nextButton) {
            changeState();
        }

    }


}
