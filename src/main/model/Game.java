package model;

// Game class. Information about the game such as score and turn are stored here.

import java.util.ArrayList;
import java.util.List;

public class Game {

    private int myScore;
    private int enemyScore;
    private int turn;
    private Team myTeam;
    private Team enemyTeam;
    protected static final int SCALE = 30;
    protected static final int Y_TRANS = 100;
    private Ball ball;
    private String gameState1; // D for defence, SN for startNoServe, A for set and attack, S for serve
    private String gameState0;
    Players selected;
    private int servePos;


    // EFFECTS: Constructs a game object with score 0, turn num 0, and two teams to play each other.
    public Game(Team myTeam, Team enemyTeam) {
        this.myScore = 0;
        this.enemyScore = 0;
        this.turn = 0;
        this.myTeam = myTeam;
        this.enemyTeam = enemyTeam;
        this.gameState1 = null;
        this.gameState0 = null;
        this.servePos = 0;
    }

    public Game() {
        this.turn = 0;
        this.gameState1 = "N";
        this.gameState0 = "N";
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

            int x = p.getPosX();
            int y = p.getPosY();
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

    public void setSelected(Players p) {
        this.selected = p;
    }

    public Players getSelected() {
        return this.selected;
    }

    public void removeSelected() {
        this.selected = null;
    }

    // MODIFIES: this
    // EFFECTS: moves players in myTeam to their positions according to gamestate
    public void adjustPos1() {
        switch (gameState1) {
            case "SN":
                myTeam.ServeReceivePos();
                break;
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
                myTeam.ServeReceivePos();
        }
    }

    public void adjustPos0() {
        switch (gameState0) {
            case "SN":
                enemyTeam.ServeReceivePos();
                break;
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
                enemyTeam.ServeReceivePos();
        }
    }

    // MODIFIES: this
    // EFFECTS: makes a serve based on the gamestate
    public void makeServe() {
        int chance = (int) (Math.random() * 2);
        if (gameState1.equals("S")) {
            for (Players p : myTeam.getStarters()) {
                if (p.getRotation() == 1) {
                    p.serve(servePos, ball);
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

    public int getServePos() {
        return servePos;
    }

    public void setServePos(int s) {
        servePos = s;
    }

    // REQUIRES: checkReceive = true
    // EFFECTS: finds the player that receives it and has the player receive it
    public void receive() {

        if (gameState0.equals("D") || gameState0.equals("SN")) {
            for (Players p : enemyTeam.getStarters()) {
                if ((p.getNewPosX() - ball.getMoveToXPos()) <= 1 * Players.SCALE &&
                        (p.getNewPosY() - ball.getMoveToYPos()) <= 1 * Players.SCALE) {
                    p.receive(ball);
                }
            }

        } else if (gameState1.equals("D") || gameState1.equals("SN")) {
            for (Players p : myTeam.getStarters()) {
                if ((p.getNewPosX() - ball.getMoveToXPos()) <= 1 * Players.SCALE &&
                        (p.getNewPosY() - ball.getMoveToYPos()) <= 1 * Players.SCALE) {
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
            ball.moveToX(0);
            ball.moveToY(0);
        } else {
            myScore();
            myTeam.changeRotation();
            ball.moveToX(12);
            ball.moveToY(24);
        }
    }

    public void chooseAttack(int x, int y) {
        if (y >= 460 && y <= 520) {
            if (x <= 90) {
                for (Players p : myTeam.getStarters()) {
                    if (p.getShortPos().equals("OH") && p.getRotation() >= 4) {
                        setSelected(p);
                    }
                }
            } else if (x >= 150 && x <= 210) {
                for (Players p : myTeam.getStarters()) {
                    if (p.getShortPos().equals("MB") && p.getRotation() >= 4) {
                        setSelected(p);
                    }
                }
            } else if (x >= 300) {
                for (Players p : myTeam.getStarters()) {
                    if (p.getShortPos().equals("OP") && p.getRotation() >= 4) {
                        setSelected(p);
                    }
                }

            }
        }
    }
}


