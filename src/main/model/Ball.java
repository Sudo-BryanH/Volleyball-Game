package model;

public class Ball {

    private int ballXPos;
    private int ballYPos;

    // EFFECTS: constructs a ball object at x = 0, y = 0
    public Ball() {
        this.ballXPos = 0;
        this.ballYPos = 0;

    }

    // EFFECTS: returns the ball's X position
    public int getXPos() {
        return ballXPos;
    }

    // EFFECTS: returns the ball's Y position
    public int getYPos() {
        return ballYPos;
    }

    // REQUIRES: a position X [0, 12]
    // MODIFIES: this
    // EFFECTS: move's this ball to position X
    public void moveToX(int x) {
        this.ballXPos = x;
    }

    // REQUIRES: a position Y [0, 24]
    // MODIFIES: this
    // EFFECTS: move's this ball to position Y
    public void moveToY(int y) {
        this.ballYPos = y;
    }

    // Do move by methods later
    // REQUIRES:
    // MODIFIES: this
    // EFFECTS:
    //public void moveByX() {}

    //public void moveByY() {}


}
