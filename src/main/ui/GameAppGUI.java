package ui;

import model.*;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;
import java.util.Scanner;

import javax.swing.*;

// This is the GameApp class where most of the user interface happens. Players can see
// information about the score, their players' positions, ball's positions and turns.
// Players can also control their players from here.

public class GameAppGUI extends JFrame implements EventListener, ActionListener, MouseListener {

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
    Game game;
    EnemyTeam enemyTeam;
    Ball ball;
    int turn;
    CourtRenderer court;
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
    private JButton nextButton;
    private JButton leftButton;
    private JButton rightButton;
    private JButton midButton;


    // EFFECTS: constructor for a game. If the player would like to load an old game, will go to load game
    public GameAppGUI() {
        // make new to start from saved game
        super("Volleyball Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        instantiateGame();
        beginGame();
    }

    public GameAppGUI(List starters, List roster, String enemyTeam) {
        super("Volleyball Game");
        setUp();
        beginGame();
    }


    private void instantiateGame() {
        game = new Game();
        try {
            jsonReader = new JsonReader(JSON_STORE, game);
            gameData = jsonReader.read();
        } catch (IOException f) {
            System.out.println("Well, guess that didn't work. Let's start a new game then.");
            setUp();
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

    // MODIFIES: this, MyTeam, EnemyTeam, game
    // EFFECTS: Creates enemies teams and sets up your team and players. Then, decide which team you will be playing
    public void setUp() {
        String input;
        boolean status = false;
        // Make enemy teams
        weakTeam = (EnemyTeam) enemyTeamConstructor("weak team", weakMB1, weakMB2, weakSet,
                weakOP, weakOH1, weakOH2, 5);
        strongTeam = (EnemyTeam) enemyTeamConstructor("strong team", strongMB1, strongMB2, strongSet,
                strongOP, strongOH1, strongOH2, 2);

        System.out.println("Welcome to this volleyball game. If you would like to quit, please type 'quit' if the "
                + "system is asking for a string or '999' if the system is asking for a number");
        input = stringInput("Let's create your team. What would you like to call your team?");
        myTeam = myTeamConstructor(input, mySet, myMB1, myMB2, myOH1, myOH2, myOP);


        checkAddPlayer();


        chooseEnemyTeam();
        game = new Game(myTeam, enemyTeam);
        game.getMyTeam().startPosNoServe();
        gameData = new GameData(game);
        jsonWriter = new JsonWriter(JSON_STORE);
        beginGame();


    }

    public void checkAddPlayer() {

        boolean status = false;
        String input;

        while (!status) {
            input = stringInput("Would you like to add another player to the roster? Type y or n");
            if (input.equals("y")) {
                addPlayerToTeam();
            } else {
                System.out.println("no more added. There are " + myTeam.getRoster().size() + "Players in roster");
                status = true;
            }
        }

    }

    // MODIFIES: game
    // EFFECTS: Runs the gameGameApp
    public void beginGame() {
        boolean gameOver = false;
        turn = game.getTurnNum();
        ball = new Ball();
        game.decBall(ball);
        court = new CourtRenderer(game);
        this.addMouseListener(this);

        this.add(court);

        pack();
        court.setVisible(true);
        this.setVisible(true);
        addTimer(); // TODO find out if this is redundant


        System.out.println("Let us begin this game. Whenever prompted, follow the onscreen instructions");


        while (gameOver == false) {

            System.out.println(game.getScore());
            beginRally(turn);

            turn = game.getTurnNum();
            if (game.isGameOver()) {
                gameOver = true;
            }
        }

        // TODO check if user wants to change starting players
        System.out.println("Game has ended");
        System.out.println("The final score for this set is " + game.getScore() + "your team to the enemy team.");
        System.out.println("Thank you for playing");


    }

    // MODIFIES: game, ball
    // EFFECTS: Runs each rally. Shows the turn and ball position after every major event. Shows scores when finished.
    public int beginRally(int turn) {
        boolean isOver = false;
        System.out.println("Turn number is " + turn);
        game.setGameState("S", "S");
        chooseTeamState(turn, "start");
        stringInput("Ready? Press any key to start");
        // TODO Design state chooser method
        serve(turn);

        while (!isOver) {
            addTimer();
            ballPos();
            game.flipTurnNum();
            turn = game.getTurnNum();
            System.out.println("Turn number changed to " + turn);
            receive(turn);
            positionsAfterServe(turn);
            chooseSet(turn);
            attackNum = chooseAttack(setNum, turn);
            chooseDefend(turn, setNum, attackNum);
            set(turn, setNum);
            attack(turn, setNum, attackNum);
            ballPos();
            isOver = !checkReceive(turn);

        }
        endRally(turn);
        return turn;
    }


    // EFFECTS: returns true if a ball has been received, false otherwise
    private boolean checkReceive(int turn) {
        List<Players> backrow = new ArrayList<>();
        boolean check = false;
        if (turn == 1) {
            for (Players p : enemyTeam.getStarters()) {
                if (p.getRotation() == 1 || p.getRotation() == 2 || p.getRotation() == 3) {
                    backrow.add(p);
                }
            }
            check = game.checkReceive(ball.getMoveToXPos(), ball.getMoveToYPos(), backrow);
        } else if (turn == 0) {
            for (Players p : myTeam.getStarters()) {
                if (p.getRotation() == 1 || p.getRotation() == 2 || p.getRotation() == 3) {
                    backrow.add(p);
                }
            }
            check = game.checkReceive(ball.getMoveToXPos(), ball.getMoveToYPos(), backrow);
        }
        if (check) {
            System.out.println("Ball has been received");
        } else {
            positionsAfterServe(turn);
        }

        return check;
    }

    private void chooseTeamState(int turn, String state) {

        if (state.equals("start")) {
            if (turn == 0) {
                enemyTeam.startPosServe();
                myTeam.startPosNoServe();
            } else if (turn == 1) {
                enemyTeam.startPosNoServe();
                myTeam.startPosServe();
            }
        }


    }

    private void set(int turn, int setNum) {

        if (turn == 0) {
            //addTimer();
            enemyTeam.set(setNum, ball);

        } else if (turn == 1) {
            //addTimer();
            myTeam.set(setNum, ball);
        }

    }

    // REQUIRES: turn[0, 1], setNum[0, 2], attackNum[0,2]
    // MODIFIES: ball, MyTeam, EnemyTeam, Players, ball
    // EFFECT: Ball goes to player then goes to opponent court
    private void attack(int turn, int setNum, int attackNum) {
        //addTimer();
        attackPositions(turn);
        if (turn == 0) {
            stringInput("Ball has been set. Press any key to finish the attack");
            if (setNum == 0) {
                System.out.println("Ball has been set to the left and is attack towards" + attackNum);
            } else if (setNum == 1) {
                System.out.println("Ball has been set to the middle and is attack towards" + attackNum);
            } else {
                System.out.println("Ball has been set to the right and is attack towards" + attackNum);
            }
            enemyTeam.attack(setNum, attackNum, ball);

        } else if (turn == 1) {
            stringInput("Ball has been set. Press any key to finish the attack");
            if (setNum == 0) {
                System.out.println("Ball has been set to the left and is attack towards" + attackNum);
            } else if (setNum == 1) {
                System.out.println("Ball has been set to the middle and is attack towards" + attackNum);
            } else {
                System.out.println("Ball has been set to the right and is attack towards" + attackNum);
            }
            myTeam.attack(setNum, attackNum, ball);
        }
        game.setGameState("F", "F");
    }

    // REQUIRES: turn [0, 1]
    // MODIFIES: MyTeam EnemyTeam Players
    // EFFECTS: moves the players into position to attack
    private void attackPositions(int turn) {
        //addTimer();
        if (turn == 0) {
            if (enemyTeam.isSetterBack()) {
                enemyTeam.attackBSetter();
            } else {
                enemyTeam.attackFSetter();
            }
        } else {
            if (myTeam.isSetterBack()) {
                myTeam.attackBSetter();
            } else {
                myTeam.attackFSetter();
            }
        }
    }

    // REQUIRES: turn [0, 1]
    // MODIFIES: ball
    // EFFECT: Ball goes to opponent court
    private void serve(int turn) {
        //addTimer();
        int chance = (int) (Math.random() * 2);
        int input;
        if (turn == 0) {
/*            ball.directX(0);
            ball.directY(0);
*//*            enemyTeam.startPosServe();
            myTeam.startPosNoServe();*/
            for (Players p : enemyTeam.getStarters()) {
                if (p.getRotation() == 1) {
                    p.serve(chance, ball);
                }
                positionsAfterServe(turn);
            }
            System.out.println("Serve has been made");
        } else if (turn == 1) {
/*            ball.directX(12);
            ball.directY(24);*/
/*            enemyTeam.startPosNoServe();
            myTeam.startPosServe();*/
            input = intInput("Do you want to serve left[0] or right [1]");
            for (Players p : myTeam.getStarters()) {
                if (p.getRotation() == 1) {
                    p.serve(input, ball);
                }
                positionsAfterServe(turn);
            }

            System.out.println("Serve has been made");
        }

    }

    private void positionsAfterServe(int turn) {
        if (turn == 0) {
            if (enemyTeam.isSetterBack()) {
                enemyTeam.defendBSetter();
            } else {
                enemyTeam.defendFSetter();
            }

            if (myTeam.isSetterBack()) {
                myTeam.attackBSetter();
            } else {
                myTeam.attackFSetter();
            }

        } else {
            if (myTeam.isSetterBack()) {
                myTeam.defendBSetter();
            } else {
                myTeam.defendFSetter();
            }

            if (enemyTeam.isSetterBack()) {
                enemyTeam.attackBSetter();
            } else {
                enemyTeam.attackFSetter();
            }


        }

    }

    // REQUIRES: turn[0, 1]
    // MODIFIES: ball
    // EFFECTS: moves the ball to setter's position
    private void receive(int turn) {
        Players receiver = null;
        //addTimer();

        if (turn == 0) {
            for (Players p : enemyTeam.getStarters()) {
                if ((p.getNewPosX() - ball.getMoveToXPos()) <= 1 * Players.SCALE &&
                        (p.getNewPosY() - ball.getMoveToYPos()) <= 1 * Players.SCALE) {
                    receiver = p;
                }
            }

        } else if (turn == 1) {
            for (Players p : myTeam.getStarters()) {
                if ((p.getNewPosX() - ball.getMoveToXPos()) <= 1 * Players.SCALE &&
                        (p.getNewPosY() - ball.getMoveToYPos()) <= 1 * Players.SCALE) {
                    receiver = p;
                }
            }

        }

        stringInput("Ball has been received. Press any button to continue");
        receiver.receive(ball);

        ballPos();
    }

    // REQUIRES: turn[0,1]
    // MODIFIES: this
    // EFFECTS: choose who to set
    private void chooseSet(int turn) {

        if (turn == 0) {
            game.setGameState("A", "D");
            chooseEnemySet(turn);
        } else {
            game.setGameState("D", "A");
            chooseMySet(turn);
        }


    }


    private void chooseEnemySet(int turn) {
        int chance2 = (int) (Math.random() * 2);
        int chance3 = (int) (Math.random() * 3);


        if (enemyTeam.isSetterBack()) {
            System.out.println("Enemy can attack from the left, middle, or right");
            setNum = chance3;
        } else {
            System.out.println("Enemy can only attack form the left or middle");
            setNum = chance2;
        }

    }

    private void chooseMySet(int turn) {
/*        String choice = null;

        if (myTeam.isSetterBack()) {

            choice = stringInput("Do you want to set the left side [l], middle [m], or right side [r] to attack?");
        } else {
            choice = stringInput("Do you want to set the left side [l], or middle [m] to attack");
        }
        System.out.println("you have picked " + choice);
        if (choice.equals("l")) {
            return 0;
        } else if (choice.equals("m")) {
            return 1;
        } else {
            return 2;
        }*/

        sleepThread(2);

        nextButton = new JButton("Next");
        nextButton.setBounds(260, 860, 90, 45);
        nextButton.setBackground(Color.WHITE);
        nextButton.setOpaque(true);
        this.add(nextButton);
        nextButton.addActionListener(new Selector());

        nextButton.addActionListener(new Selector());
        leftButton = new JButton("left");
        leftButton.setBounds(0, 12 * 30 + 100, 60, 60);
        leftButton.setBackground(Color.WHITE);
        leftButton.setOpaque(true);
        this.add(leftButton);
        leftButton.addActionListener(new Selector());

        midButton = new JButton("middle");
        midButton.setBounds(5 * 30, 12 * 30 + 100, 60, 60);
        midButton.setBackground(Color.WHITE);
        midButton.setOpaque(true);
        this.add(midButton);
        midButton.addActionListener(new Selector());

        rightButton = new JButton("right");
        rightButton.setBounds(10 * 30, 12 * 30 + 100, 60, 60);
        rightButton.setBackground(Color.WHITE);
        rightButton.setOpaque(true);
        this.add(rightButton);
        rightButton.addActionListener(new Selector());
        sleepThread(4);

    }


    // REQUIRES: turn [0, 1] attackNum[0, 2] setNum[0, 2]
    // EFFECTS: Choose how to defend before an attack
    private void chooseDefend(int turn, int setNum, int attackNum) {
        if (turn == 1) {
            enemyDefend(setNum, attackNum);
        } else {
            System.out.println("Let's choose our defence formation");
            myDefense();
        }

    }

    // REQUIRES: turn [0, 1] attackNum[0, 2] setNum[0, 2]
    // MODIFIES: EnemyTeam, this, players
    // EFFECTS: Enemy choose how to defend before an attack
    private void enemyDefend(int setNum, int attackNum) {
        int multiplier = enemyTeam.getChance();
        int chance = (int) (Math.random() * multiplier);
        if (enemyTeam.isSetterBack()) {
            enemyTeam.defendBSetter();
        } else {
            enemyTeam.defendFSetter();
        }

        if (chance == 1) {
            System.out.println("Enemy chance is in position to receive");
            if (setNum == 0) {
                enemyMoveDefence0();
            } else if (setNum == 1) {
                enemyMoveDefence1();
            } else if (setNum == 2) {
                enemyMoveDefence2();
            }

        }

    }


    // MODIFIES: EnemyTeam, this, players
    // EFFECTS: We choose how to defend before an attack by pressing it
    private void myDefense() {
        //addTimer();
        if (myTeam.isSetterBack()) {
            myTeam.defendBSetter();
        } else {
            myTeam.defendFSetter();
        }

        for (Players p : myTeam.getStarters()) {

            setPressX(p.getPosX());
            setPressY(p.getPosY());


            if (p.getRotation() >= 4) {

                game.setSelected(p);

                sleepThread(5);

                int x = getPressX();
                System.out.println("Reached here2");


                System.out.println("Reached here1");
                p.moveToX(x / 30);


            } else if (!p.getPlayingPosition().equals("Setter")) {

                game.setSelected(p);


                sleepThread(5);

                int x = getPressX();
                int y = getPressY();
                System.out.println("Reached here2");


                System.out.println("Reached here1");
                p.moveToX(x / 30);
                p.moveToY(((y - 100) / 30));


            }

        }

        game.removeSelected();
    }

    private void sleepThread(int s) {

        for (int i = 0; i < s; i++) {
            try {
                System.out.println(s - i);
                Thread.sleep(i * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    // MODIFIES: EnemyTeam, this, players
    // EFFECTS: Enemy choose how to defend before an attack if setNum ==1
    private void enemyMoveDefence1() {
        //addTimer();
        for (Players p : enemyTeam.getStarters()) {
            if (p.getRotation() == 4 || p.getRotation() == 5 || p.getRotation() == 6) {
                p.moveToX(2);
            } else if (p.getPlayingPosition().equals("Middle Blocker")) {
                p.moveToX(2);
                p.moveToY(5);
            } else if (p.getPlayingPosition().equals("Outside Hitter")) {
                p.moveToX(10);
                p.moveToY(5);
            }
        }

    }

    // MODIFIES: EnemyTeam, this, players
    // EFFECTS: Enemy choose how to defend before an attack if setNum == 0
    private void enemyMoveDefence0() {
        //addTimer();
        for (Players p : enemyTeam.getStarters()) {
            if (p.getRotation() == 4 || p.getRotation() == 5 || p.getRotation() == 6) {
                p.moveToX(6);
            } else if (p.getPlayingPosition().equals("Middle Blocker")) {
                p.moveToX(0);
                p.moveToY(3);
            } else if (p.getPlayingPosition().equals("Outside Hitter")) {
                p.moveToX(6);
                p.moveToY(2);
            }
        }
    }

    // MODIFIES: EnemyTeam, this, players
    // EFFECTS: Enemy choose how to defend before an attack if setNum == 2
    private void enemyMoveDefence2() {
        //addTimer();
        for (Players p : enemyTeam.getStarters()) {
            if (p.getRotation() == 4 || p.getRotation() == 5 || p.getRotation() == 6) {
                p.moveToX(10);
            } else if (p.getPlayingPosition().equals("Middle Blocker")) {
                p.moveToX(6);
                p.moveToY(3);
            } else if (p.getPlayingPosition().equals("Outside Hitter")) {
                p.moveToX(12);
                p.moveToY(4);
            }
        }

    }

    // REQUIRES: setNum [0, 2], turn [0, 1]
    // MODIFIES: this
    // EFFECTS: teams choose how to attack
    private int chooseAttack(int setNum, int turn) {
        int choice = 1;
        int chance2 = (int) (Math.random() * 2);
        int chance3 = (int) (Math.random() * 3);
        if (turn == 1) {

            if (setNum == 0 || setNum == 2) {
                choice = intInput("Choose whether you want to spike straight[0] or to the middle of the court[1]");
            } else if (setNum == 1) {
                choice = intInput("Choose whether you want to spike to the "
                        + "to the left[2] or to the right [1]");
            }

            return choice;
        } else if (turn == 0) {

            if (setNum == 0 || setNum == 2) {
                return chance2;
            } else if (setNum == 1) {
                return chance3;
            } else {
                return 1;
            }
        }
        System.out.println(choice);
        return -1; // Should only happen if all else fails
    }

    // TODO create buttons for each player located at their respective locations from starter list
    // EFFECTS: constructs an enemy team
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

        return e; // May cause errors with subtypes

    }

    // EFFECTS: constructs our team
    public Team myTeamConstructor(String name, Players mb1, Players mb2,
                                  Players set, Players op, Players oh1,
                                  Players oh2) {

        int n;

        n = intInput("Alright, let's give your players numbers. Let's start with the setter, type in a number:");
        set = new Setters(n, 1);
        n = intInput("Next up, your first middle blocker");
        mb1 = new MiddleBlockers(n, 1);
        n = intInput("Next up, your first outside hitter");
        oh1 = new OutsideHitter(n, 1);
        n = intInput("Next up, your first opposite hitter");
        op = new OppositeHitter(n, 1);
        n = intInput("Next up, your second middle blocker");
        mb2 = new MiddleBlockers(n, 1);
        n = intInput("Next up, your second outside hitter");
        oh2 = new OutsideHitter(n, 1);

        MyTeam m;
        m = new MyTeam(name, set, mb1, mb2, oh1, oh2, op);
        m.startPosNoServe();

        return m; // May cause errors with subtypes
    }

    // EFFECTS: creates and adds a player to our team roster
    public void addPlayerToTeam() {
        new DeclarePlayers(game);

        int num;
        String pos;

        Players p = null;
        pos = stringInput("Which player do you want to make? type 's' for setter, "
                + "'m' for middle, 'oh' for outside hitter, or 'op' for opposite");
        num = intInput("Which number would you like to assign to this player?");
        if (pos.equals("s")) {
            p = new Setters(num, 1);
            p.setRotation(0);

        } else if (pos.equals("m")) {
            p = new MiddleBlockers(num, 1);
            p.setRotation(0);

        } else if (pos.equals("oh")) {
            p = new OutsideHitter(num, 1);
            p.setRotation(0);

        } else if (pos.equals("op")) {
            p = new OppositeHitter(num, 1);
            p.setRotation(0);

        }
        myTeam.addPlayer(p);
        System.out.println(p.getPlayingPosition() + " num " + num + " has been added to the roster");
        System.out.println("there are now " + myTeam.getRoster().size() + " Players in your team's roster");

    }

    // EFFECTS: picks an enemy team to play against
    private void chooseEnemyTeam() {
        String input;
        input = stringInput("Which team would you like to choose to play against? "
                + "Your options are \n the weak team or the strong team");

        if (input.equals("strong team")) {

            enemyTeam = strongTeam;
            System.out.println("You will now be playing against the strong team");
        } else {

            enemyTeam = weakTeam;
            System.out.println("You will now be playing against the weak team");
        }

        enemyTeam.startPosNoServe();

        enemyTeam.changeRotation();
        enemyTeam.changeRotation();
        enemyTeam.changeRotation();

    }

    // EFFECTS: displays the ball's position
    private void ballPos() {
        System.out.println("Ball Position is at [" + ball.getMoveToXPos() + " , " + ball.getMoveToYPos() + "]");
    }

    // EFFECTS: displays score and other information after a rally has ended
    private void endRally(int turn) {
        //addTimer();
        System.out.println("Turn number is " + turn);
        if (turn == 0) {
            System.out.println("Rally is over, enemy team scored");
            game.enemyScore();
            enemyTeam.changeRotation();
            System.out.println("Enemy team rotated");
            stringInput("Press any key to continue");
            ball.moveToX(0);
            ball.moveToY(0);
        } else {
            System.out.println("Rally is over, our team scored");
            game.myScore();
            myTeam.changeRotation();
            System.out.println("Our team rotated");
            stringInput("Press any key to continue");
            ball.moveToX(12);
            ball.moveToY(24);
        }

    }

    // EFFECTS: asks the user for input, then accepts input. If input == 'quit', then end game.
    private String stringInput(String message) {

        String input;
        System.out.println(message);
        input = scan.next();
        scan.nextLine();
        if (input.equals("quit")) {
            quit(); // may cause problems if no return statments
        } else if (input.equals("switch")) {
            switchPlayers();
        }
        return input;

    }


    // EFFECTS: asks the user for input, then accepts input. If input == 99, then end game.
    private int intInput(String message) {
        int input;
        System.out.println(message);
        input = scan.nextInt();
        if (input == 999) {
            quit(); // may cause problems if no return statments
        }
        return input;
    }

    // EFFECTS: ends game
    private void quit() {
        save();
        System.exit(0);
    }

    // EFFECTS: determines if the player wants to save. If yes, save game data
    private void save() {
        String file = "./data/gameData" + java.time.LocalDateTime.now() + ".json";
        try {

            jsonWriter = new JsonWriter(file);
            jsonWriter.open();
            jsonWriter.write(gameData);
            jsonWriter.close();
            System.out.println("Saved data to " + file);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + file);

        }
        try {

            jsonWriter = new JsonWriter(JSON_STORE);
            jsonWriter.open();
            jsonWriter.write(gameData);
            jsonWriter.close();
            System.out.println("Saved data to " + JSON_STORE);
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
    public void actionPerformed(ActionEvent e) {
        game.update();
        court.repaint();
    }

    public void switchPlayers() {
        int ogNum = -1;
        int newNum = -1;
        boolean status = false;
        while (!status) {
            ogNum = intInput("Enter the player number you want to swap out");
            newNum = intInput("Enter the player number you want to swap in");
            status = (myTeam.getPlayer(ogNum).getPlayingPosition() == myTeam.getPlayer(newNum).getPlayingPosition());
        }


        myTeam.changeStarters(ogNum, newNum);

    }


    @Override
    public void mouseClicked(MouseEvent e) {
        // Invoked when the mouse button has been clicked (pressed and released on a compnent


/*        if (state1.equals("S")) {

        }*/
    }

    public void setPressX(int x) {
        pressX = x;
    }

    public int getPressX() {
        return pressX;
    }

    public void setPressY(int y) {
        pressY = y;
    }

    public int getPressY() {
        return pressY;
    }


    @Override
    public void mousePressed(MouseEvent e) {
        // Invoked when a mouse button has been pressed on a component
        int turn = game.getTurnNum();




        /*if (state1.equals("A")) {

        }*/


        setPressX(e.getX());
        setPressY(e.getY());

        System.out.println("You are pressing the mouse at " + e.getX() + " , " + e.getY());
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // Invoked when a mouse button has been released on a component
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // Invoked when the mouse enters a component
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // Invoked when the mouse exits a component
    }

    public class Selector implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(leftButton)) {
                setNum = 0;
                System.out.println("You've chosen to set to the left");
            } else if (e.getSource().equals(rightButton)) {
                setNum = 2;
                System.out.println("You've chosen to set to the middle");
            } else if (e.getSource().equals(midButton)) {
                setNum = 1;
                System.out.println("You've chosen to set to the right");
            }

            if (e.getSource().equals(nextButton)) {
                state = true;
            }
        }
    }


}
