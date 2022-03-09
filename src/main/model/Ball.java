package model;
// The ball object used in this game which has an x, y coordinate

public class Ball {

    private int currentXPos;
    private int currentYPos;
    private int moveToYPos;
    private int moveToXPos;
    private static int SCALE = 30;
    private static int Y_TRANS = 100;


    // EFFECTS: constructs a ball object at x = 0, y = 0
    public Ball() {
        this.currentXPos = 0;
        this.currentYPos = 0;

    }

    // EFFECTS: returns the ball's X position
    public int getXPos() {
        return currentXPos;
    }

    // EFFECTS: returns the ball's Y position
    public int getYPos() {
        return currentYPos;
    }

    // REQUIRES: a position X [0, 12]
    // MODIFIES: this
    // EFFECTS: move's this ball to position X
    public void moveToX(int x) {
        this.currentXPos = x * SCALE;
    }

    // REQUIRES: a position Y [0, 24]
    // MODIFIES: this
    // EFFECTS: move's this ball to position Y
    public void moveToY(int y) {
        this.currentYPos = y * SCALE + Y_TRANS;
    }

    // Do move by methods later
    // REQUIRES:
    // MODIFIES: this
    // EFFECTS:
    //public void moveByX() {}

    //public void moveByY() {}


}
