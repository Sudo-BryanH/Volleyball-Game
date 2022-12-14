package model;
// The ball object used in this game which has an x, y coordinate

public class Ball {

    private int currentXPos;
    private int currentYPos;
    private int moveToYPos;
    private int moveToXPos;
    private static int SCALE = 30;
    private static int Y_TRANS = 100;
    private int dy = 24;
    private int dx = 12;
    private boolean moveState;



    // EFFECTS: constructs a ball object at x = 0, y = 0
    public Ball() {
        this.currentXPos = 0;
        this.currentYPos = 100;

    }

    public void directX(int x) {
        this.currentXPos = x * SCALE;
    }

    public void directY(int y) {
        this.currentYPos = y * SCALE + Y_TRANS;
    }

    // EFFECTS: returns the ball's moveToX position
    public int getMoveToXPos() {
        return moveToXPos;
    }

    // EFFECTS: returns the ball's moveToY position
    public int getMoveToYPos() {
        return moveToYPos;
    }

    // EFFECTS: returns the ball's X position
    public int getCurrentXPos() {
        return currentXPos;
    }

    // EFFECTS: returns the ball's Y position
    public int getCurrentYPos() {
        return currentYPos;
    }

    // REQUIRES: a position X [0, 12]
    // MODIFIES: this
    // EFFECTS: move's this ball to position X
    public void moveToX(int x) {
        this.moveToXPos = x  * SCALE;
        moveState = true;

    }

    // REQUIRES: a position Y [0, 24]
    // MODIFIES: this
    // EFFECTS: move's this ball to position Y
    public void moveToY(int y) {
        this.moveToYPos = y * SCALE + Y_TRANS;
        //setDX();
        moveState = true;
    }

    // MODIFIES: this
    // EFFECTS: moves by speed if mvoe state is true
    public void move() {

        if (moveState) {
            if (Math.abs(currentYPos - moveToYPos) > dy) {
                if (currentYPos > moveToYPos) {
                    this.currentYPos -= dy;
                } else {
                    this.currentYPos += dy;
                }
            } else if (Math.abs(currentYPos - moveToYPos) <= dy) {
                this.currentYPos = moveToYPos;

            }

            if (Math.abs(currentXPos - moveToXPos) > dx) {
                if (currentXPos > moveToXPos) {
                    this.currentXPos -= dx;
                } else {
                    this.currentXPos += dx;
                }
            } else if (Math.abs(currentXPos - moveToXPos) <= dx) {
                this.currentXPos = moveToXPos;

            }

            if (currentXPos == moveToXPos && currentYPos == moveToYPos) {
                moveState = false;
            }
        }


    }



    // EFFECTS: returns moveState of the ball
    public boolean getMoveState() {
        return moveState;
    }





}
