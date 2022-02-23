package model;

// Game class. Information about the game such as score and turn are stored here.

import java.util.List;

public class Game {

    private int myScore;
    private int enemyScore;
    private int turn;
    private Team myTeam;
    private Team enemyTeam;

    // EFFECTS: Constructs a game object with score 0, turn num 0, and two teams to play each other.
    public Game(Team myTeam, Team enemyTeam) {
        this.myScore = 0;
        this.enemyScore = 0;
        this.turn = 0;
        this.myTeam = myTeam;
        this.enemyTeam = enemyTeam;
    }


    // EFFECTS: Returns the score in the form of "Your Team Score : Enemy Team Score"
    public String getScore() {
        String us = Integer.toString(myScore);
        String them = Integer.toString(enemyScore);
        return us + " : " + them;
    }


    // EFFECTS: after attack, check if ball position = any player's positions
    public boolean checkReceive(int ballX, int ballY, List<Players> backrow) {

        for (Players p : backrow) {

            int x = p.getPosX();
            int y = p.getPosY();
            if ((Math.abs(x - ballX) <= 1) && (Math.abs(y - ballY) <= 1)) {
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

    // TODO: write tests
    // EFFECTS: returns myTeam
    public Team getMyTeam() {
        return myTeam;
    }

    // TODO: write tests
    // EFFECTS: returns ememyTeam
    public Team getEnemyTeam() {
        return enemyTeam;
    }

    public int getMyScore() {
        return myScore;
    }

    public int getEnemyScore() {
        return enemyScore;
    }




}
