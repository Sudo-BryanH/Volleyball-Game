package model;

import java.util.List;

public class Game {

    // EFFECTS: Constructs a game object with score 0, turn num 0, and two teams to play each other.
    public Game(Team myTeam, Team enemyTeam) {
        //stub
    }


    // EFFECTS: Returns the score in the form of "Your Team Score : Enemy Team Score"
    public String getScore() {
        return "___ : ___"; // stub
    }


    // EFFECTS: after attack, check if ball position = any player's positions
    public boolean checkReceive(int ballX, int ballY, List<Players> backrow) {
        return false;
    }


    // MODIFIES: this
    // EFFECTS: awards point to MyTeam, sets turn to 1
    public void weScore() {

    }

    // MODIFIES: this
    // EFFECTS: awards point to EnemyTeam, sets turn to 0
    public void enemyScore() {

    }

    // EFFECTS: determines if the game is over or not
    public boolean isGameOver() {
        return false; //stub
    }

    // (runs after attack is done)
    // EFFECTS: determines if the game is over or not by seeing if any players managed to receive the ball
    public boolean isRallyOver() {
        return false; //stub
    }

    // EFFECTS: returns the turn number
    public int getTurnNum() {
        return -1;
    }

    // MODIFIES: this
    // EFFECTS: changes the turn number from 1 to 0 or 0 to 1
    public void flipTurnNum() {

    }






}
