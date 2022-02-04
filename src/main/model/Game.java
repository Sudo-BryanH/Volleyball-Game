package model;

public class Game {

    // EFFECTS: Constructs a game object with score 0 and two teams to play each other.
    public Game(Team myTeam, Team enemyTeam) {
        //stub
    }
    // MODIFIES: this
    // EFFECTS: Begins the game
    public void startRally() {

    }
    // EFFECTS: Returns the score in the form of "Your Team Score : Enemy Team Score"
    public String getScore() {
        return "___ : ___"; // stub
    }

    // MODIFIES: this
    // EFFECTS: changes which side will serve
    public void changeServeSide() {
        //stub
    }

    // MODIFIES: this
    // EFFECTS: awards point to MyTeam
    public void weScore() {

    }

    // MODIFIES: this
    // EFFECTS: awards point to EnemyTeam
    public void enemyScore() {

    }

    // EFFECTS: determines if the game is over or not
    public boolean isGameOver() {
        return false; //stub
    }

    // EFFECTS: determines if the game is over or not
    public boolean isRallyOver() {
        return false; //stub
    }






}
