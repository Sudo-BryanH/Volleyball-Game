package model;

// Game class. Information about the game such as score and turn are stored here as well as different functions of the
// game such as loading, saving, attacking, defending and controlling opponent team.

import persistence.JsonReader;
import persistence.JsonWriter;
import ui.SplashScreen;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Game {

    private int myScore;
    private int enemyScore;
    private int turn;
    private Team myTeam;
    private EnemyTeam enemyTeam;
    protected static final int SCALE = 30;
    protected static final int Y_TRANS = 100;
    private Ball ball;
    private String gameState1; // D for defence, SN for startNoServe, A for set and attack, S for serve
    private String gameState0;
    private Players attackPlayer;
    private Point attackPoint;
    private Players selectDefensive;
    JsonWriter jsonWriter;
    JsonReader jsonReader;
    GameData gameData;
    private static final String JSON_STORE = "./data/mostRecentGameData.json";



    // EFFECTS: Constructs a game object with score 0, turn num 0, and two teams to play each other.
    public Game(Team myTeam, EnemyTeam enemyTeam) {
        this.myScore = 0;
        this.enemyScore = 0;
        this.turn = 0;
        this.myTeam = myTeam;
        this.enemyTeam = enemyTeam;
        this.gameState1 = null;
        this.gameState0 = null;
        ball = new Ball();
        decBall(ball);
        gameData = new GameData(this);
        jsonWriter = new JsonWriter(JSON_STORE);

    }

    public Game() {
        this.turn = 0;
        this.gameState1 = "N";
        this.gameState0 = "N";
        ball = new Ball();
        decBall(ball);
        instantiateGame();


    }

    public void instantiateGame() {
        try {
            jsonReader = new JsonReader(JSON_STORE, this);
            gameData = jsonReader.read();
            // TODO: make event here for loaded game + write tests
        } catch (IOException f) {
            System.out.println("Well, guess that didn't work. Let's start a new game then.");
            new SplashScreen();
            System.exit(0);
        }
        setEnemyScore(gameData.getEnemyScore());
        setMyScore(gameData.getMyScore());

        this.enemyTeam = gameData.getEnemyTeam();
        this.myTeam = gameData.getMyTeam();
        setEnemyTeam(enemyTeam);
        setMyTeam(myTeam);
        getMyTeam().serveReceivePos();
        getEnemyTeam().startPosServe();
        gameData = new GameData(this);
        jsonWriter = new JsonWriter(JSON_STORE);
        Event e = new Event("The previous game has loaded with a score of: " + getScore());
        EventLog.getInstance().logEvent(e);
    }


    // EFFECTS: Returns the score in the form of "Your Team Score : Enemy Team Score"
    public String getScore() {
        String us = Integer.toString(myScore);
        String them = Integer.toString(enemyScore);
        String score = myTeam.getName() + "| " + us + "| |" + them + " |" + enemyTeam.getName();
        return score;
    }

    public void decBall(Ball ball) {
        this.ball = ball;
    }

    public Ball getBall() {
        return ball;
    }


    // EFFECTS: after attack, check if ball position = any player's positions
    public boolean checkReceive(int ballX, int ballY, List<Players> backrow) {

        for (Players p : backrow) {

            int x = p.getNewPosX();
            int y = p.getNewPosY();
            if ((Math.abs(x - ballX) <= 1 * SCALE) && (Math.abs(y - ballY) <= 1 * SCALE)) {
                return true;
            }

        }
        return false;
    }


    // MODIFIES: this
    // EFFECTS: awards point to MyTeam, sets turn to 1
    public void myScore() {
        myScore++;

    }

    // MODIFIES: this
    // EFFECTS: awards point to EnemyTeam, sets turn to 0
    public void enemyScore() {
        enemyScore++;
    }

    // EFFECTS: determines if the game is over or not
    public boolean isGameOver() {
        if (myScore >= 15 || enemyScore >= 15) {
            if (Math.abs(myScore - enemyScore) >= 2) {
                return true;
            }
        }

        return false;
    }


    // EFFECTS: returns the turn number
    public int getTurnNum() {
        return turn;
    }

    // MODIFIES: this
    // EFFECTS: changes the turn number from 1 to 0 or 0 to 1
    public void flipTurnNum() {
        if (this.turn == 0) {
            this.turn = 1;
        } else {
            this.turn = 0;
        }

    }


    // REQUIRES: turn [0, 1]
    // MODIFIES: this
    // EFFECTS: sets the turn number
    public void setTurnNum(int turn) {
        this.turn = turn;
    }

    // EFFECTS: returns myTeam
    public Team getMyTeam() {
        return myTeam;
    }


    // EFFECTS: returns ememyTeam
    public Team getEnemyTeam() {
        return enemyTeam;
    }

    // EFFECTS: gets score
    public int getMyScore() {
        return myScore;
    }

    // EFFECTS: gets enemy score
    public int getEnemyScore() {
        return enemyScore;
    }

    // MODIFIES: this
    // EFFECTS: instantiates the enemyTeam
    public void setEnemyTeam(EnemyTeam enemyTeam) {
        this.enemyTeam = enemyTeam;
    }


    // MODIFIES: this
    // EFFECTS: instantiates my team
    public void setMyTeam(Team myTeam) {
        this.myTeam = myTeam;
    }

    // MODIFIES: this
    // EFFECTS: instantiates my score
    public void setMyScore(int myScore) {
        this.myScore = myScore;
    }

    // MODIFIES: this
    // EFFECTS: instnatiates enemy score
    public void setEnemyScore(int enemyScore) {
        this.enemyScore = enemyScore;
    }

    // MODIFIES: ball, players, teams
    // EFFECTS: moves players and ball
    public void update() {

        myTeam.movePlayers();
        enemyTeam.movePlayers();

        ball.move();
    }

    public String getGameState0() {
        return gameState0;
    }

    public String getGameState1() {
        return gameState1;
    }

    public void setGameState(String state0, String state1) {
        this.gameState1 = state1;
        this.gameState0 = state0;
    }

    public void setAttackPlayer(Players p) {
        this.attackPlayer = p;
    }

    public Players getAttackPlayer() {
        return this.attackPlayer;
    }


    // MODIFIES: this
    // EFFECTS: moves players in myTeam to their positions according to gamestate
    public void adjustPos1() {
        switch (gameState1) {

            case "S":
                myTeam.startPosServe();
                break;
            case "A":
                if (myTeam.isSetterBack()) {
                    myTeam.attackBSetter();
                } else {
                    myTeam.attackFSetter();
                }
                break;
            case "D":
                if (myTeam.isSetterBack()) {
                    myTeam.defendBSetter();
                } else {
                    myTeam.defendFSetter();
                }
                break;
            default:
                myTeam.serveReceivePos();
        }
    }

    public void adjustPos0() {
        switch (gameState0) {

            case "S":
                enemyTeam.startPosServe();
                break;
            case "A":
                if (enemyTeam.isSetterBack()) {
                    enemyTeam.attackBSetter();
                } else {
                    enemyTeam.attackFSetter();
                }
                break;
            case "D":
                if (enemyTeam.isSetterBack()) {
                    enemyTeam.defendBSetter();
                } else {
                    enemyTeam.defendFSetter();
                }
                break;
            default:
                enemyTeam.serveReceivePos();
        }
    }

    // MODIFIES: this
    // EFFECTS: makes a serve based on the gamestate
    public void makeServe(int chance) {
        if (gameState1.equals("S")) {
            for (Players p : myTeam.getStarters()) {
                if (p.getRotation() == 1) {
                    p.serve(chance, ball);
                }
            }
        } else if (gameState0.equals("S")) {
            for (Players p : enemyTeam.getStarters()) {
                if (p.getRotation() == 1) {
                    p.serve(chance, ball);
                }
            }
        }
    }


    // REQUIRES: checkReceive = true
    // EFFECTS: finds the player that receives it and has the player receive it
    public void receive() {

        if (gameState0.equals("D") || gameState0.equals("SN")) {
            for (Players p : enemyTeam.getStarters()) {
                if ((p.getNewPosX() - ball.getMoveToXPos()) <= 1 * Players.SCALE
                        &&  (p.getNewPosY() - ball.getMoveToYPos()) <= 1 * Players.SCALE) {
                    p.receive(ball);
                }
            }

        } else if (gameState1.equals("D") || gameState1.equals("SN")) {
            for (Players p : myTeam.getStarters()) {
                if ((p.getNewPosX() - ball.getMoveToXPos()) <= 1 * Players.SCALE
                        &&  (p.getNewPosY() - ball.getMoveToYPos()) <= 1 * Players.SCALE) {
                    p.receive(ball);
                }
            }

        }
    }

    // EFFECTS: Returns whether team on defence let the ball drop
    public boolean checkScore() {
        List<Players> backrow = new ArrayList<>();
        boolean check = false;
        if (gameState0.equals("D")) {
            for (Players p : enemyTeam.getStarters()) {
                if (p.getRotation() == 1 || p.getRotation() == 2 || p.getRotation() == 3) {
                    backrow.add(p);
                }
            }
            check = !checkReceive(ball.getMoveToXPos(), ball.getMoveToYPos(), backrow);
        } else if (gameState1.equals("D")) {
            for (Players p : myTeam.getStarters()) {
                if (p.getRotation() == 1 || p.getRotation() == 2 || p.getRotation() == 3) {
                    backrow.add(p);
                }
            }
            check = !checkReceive(ball.getMoveToXPos(), ball.getMoveToYPos(), backrow);
        }

        return check;
    }

    // MODIFIES: this
    // EFFECTS: Ends the rally and resets the ball and players
    public void endRally(int side) {
        if (side == 0) {

            enemyScore();
            enemyTeam.changeRotation();
            enemyTeam.startPosServe();
        } else {
            myScore();
            myTeam.changeRotation();
            enemyTeam.serveReceivePos();
        }
    }

    // MODIFIES: this
    // EFFECTS: Chooses an attackPlayer or attackPoint based on where the cursor was
    public String chooseAttack(int x, int y) {
        if (y > 460 && y <= 520) {
            attackPlayer = null;
            if (chooseSet(x) != null) {
                attackPoint = null;
                motionSet();
                return "You've chosen to set to the " + chooseSet(x) + ". \nClick a green target to attack.";
            } else {
                return "Click a hitter in green.";
            }
        } else if (y < 460) {
            if (attackPlayer != null) {
                if (chooseAttackPos(x, y) != null) {
                    return "Player #" + attackPlayer.getNum() + " will spike to the "
                            + chooseAttackPos(x, y) + ". \n Press next to continue. ";
                } else {
                    return "Click a proper target to choose an attack then press next.";
                }
            }

        }
        return "Please click a hitter in green and click a displayed target";

    }

    // MODIFIES: this
    // EFFECTS: Chooses an attackPoint where attackPlayer should hit
    private String chooseAttackPos(int x, int y) {
        for (Point p : attackPlayer.getAttackPoints(1)) {
            if (Math.abs(p.getX() * SCALE - x) < 40 && Math.abs((p.getY() * SCALE) + Y_TRANS - y) < 40) {
                attackPoint = p;
                return "(" + p.getX() + " , " + p.getY() + ")";
            }

        }
        return null;
    }

    // MODIFIES: this
    // EFFECTS: chooses where to set the ball
    private String chooseSet(int x) {
        String message = null;
        if (x <= 90) {
            for (Players p : myTeam.getStarters()) {
                message = declareSelected("left", p, "OH");
            }
        } else if (x >= 150 && x <= 210) {
            for (Players p : myTeam.getStarters()) {
                message = declareSelected("middle", p, "MB");
            }
        } else if (x >= 0) {
            for (Players p : myTeam.getStarters()) {
                message = declareSelected("right", p, "OP");
            }

        }

        return message;
    }

    // MODIFIES: this
    // EFFECTS: declares the attackPlayer
    private String declareSelected(String dir, Players p, String match) {
        if (p.getShortPos().equals(match) && p.getRotation() >= 4) {
            setAttackPlayer(p);
        }
        return dir;
    }


    public Point getAttackPoint() {
        return attackPoint;
    }

    public void setAttackPoint(Point point) {
        this.attackPoint = point;
    }

    // MODIFIES: this, ball
    // EFFECTS: moves the ball to the attackPlayer
    public void motionSet() {
        if (getAttackPlayer().getShortPos().equals("OH")) {
            myTeam.set(0, ball);
        } else if (getAttackPlayer().getShortPos().equals("MB")) {
            myTeam.set(1, ball);
        } else if (getAttackPlayer().getShortPos().equals("OP")) {
            myTeam.set(2, ball);
        }
    }

    // MODIFIES: this, ball
    // EFFECTS: moves the ball to the attackPoint
    public void motionAttack() {
        attackPlayer.spike(attackPoint, ball);
    }

    // MODIFIES: this
    // EFFECTS: enemy chooses where to move the players to defend
    public void enemyDefend() {
        int multiplier = enemyTeam.getChance();
        int chance = (int) (Math.random() * multiplier);
        if (enemyTeam.isSetterBack()) {
            enemyTeam.defendBSetter();
        } else {
            enemyTeam.defendFSetter();
        }

        if (chance == 1) {
            for (Players p : enemyTeam.getStarters()) {
                if (p.getRotation() < 4 && !p.getShortPos().equals("S")) {
                    p.moveToX((int) attackPoint.getX());
                    p.moveToY((int) attackPoint.getY());
                    break;
                }
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: either choose a defensiveselect or choose where defensive select goes
    public String chooseDefense(int x, int y) {
        if (selectDefensive == null) {
            return selectPlayer(x, y);
        } else {
            return moveSelected(x, y);
        }

    }

    // MODIFIES: this, ball
    // EFFECTS: Defensiveselect moves to the x, y and becomes null
    private String moveSelected(int x, int y) {
        int num = selectDefensive.getNum();
        selectDefensive.moveToX(x / SCALE);
        selectDefensive.moveToY((y - Y_TRANS) / SCALE);
        selectDefensive = null;
        return "Player #" + num + " has been moved to (" + x + ", " + y + ")";

    }

    // MODIFIES: this
    // EFFECTS: declares a DefensiveSelect
    private String selectPlayer(int x, int y) {
        for (Players p : myTeam.getRoster()) {
            if (Math.abs(p.getPosX() - x) < 40 && Math.abs(p.getPosY() - y) < 40) {
                selectDefensive = p;
                return "Player #" + p.getNum() + " selected. Click anywhere to move this player. ";
            }
        }

        return null;
    }


    public Players getSelectDefensive() {
        return selectDefensive;
    }

    // MODIFIES; this, enemyTeam
    // EFFECTS: enemy chooses where to attack and attacks
    public String enemyChooseAttack(String dir) {

        if (dir.equals("left") || dir.equals("right")) {
            int chanceOPH = ((int) Math.random() * 2);
            for (Players p : enemyTeam.getStarters()) {
                if (dir.equals("left")) {
                    enemyTeam.attack(0, chanceOPH, ball);
                } else {
                    enemyTeam.attack(2, chanceOPH, ball);
                }
            }

        } else {
            int chanceMB = (int) (Math.random() * 3);
            enemyTeam.attack(1, chanceMB, ball);
        }

        return "The ball has been set to the " + dir;
    }



    // MODIFIES: this, enemyTeam
    // EFFECTS: chooses where the enemy team should set and sets
    public String enemyChooseSet() {
        String dir = null;
        int set;
        if (enemyTeam.isSetterBack()) {
            int chanceB = (int) (Math.random() * 3);
            set = chanceB;
            enemyTeam.set(chanceB, ball);
        } else {
            int chanceF = (int) (Math.random() * 2);
            set = chanceF;
            enemyTeam.set(chanceF, ball);
        }

        if (set == 0) {
            dir = "left";
        } else if (set == 1) {
            dir = "middle";
        } else {
            dir = "right";
        }

        return dir;
    }


    // EFFECTS: end game and saves data
    public void quit() {
        printLog(EventLog.getInstance());
        System.exit(0);
    }

    private void printLog(EventLog el) {
        for (Event e : el) {
            System.out.println(e);
        }

    }

    // EFFECTS: determines if the player wants to save. If yes, save game data
    public void save() {

        try {

            jsonWriter = new JsonWriter(JSON_STORE);
            jsonWriter.open();
            jsonWriter.write(gameData);
            // TODO: put an event here

            Event e = new Event("Game saved with the score: " + getScore());
            EventLog.getInstance().logEvent(e);
            jsonWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);

        }

    }

    // EFFECTS: returns whether teh game is over or not i.e. if at least 15 points has been scored
    // and scores differ by at least 2
    public Boolean gameOver() {
        return ((myScore >= 15 || enemyScore >= 15) && Math.abs(myScore - enemyScore) >= 2);
    }
}


