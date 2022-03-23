package ui;

import model.*;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class GameAppGraphics extends JFrame {

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




    public GameAppGraphics() {

        instantiateGame();
        ball = new Ball();
        game.decBall(ball);
        frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        court = new CourtRenderer(game);
        frame.add(court);
        frame.pack();
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


}
