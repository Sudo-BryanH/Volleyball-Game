package model;
// The ball object used in this game which has an x, y coordinate

public class Ball {

    private int currentXPos;
    private int currentYPos;
    private int moveToYPos;
    private int moveToXPos;
    private static int SCALE = 30;
    private static int Y_TRANS = 100;
    private int dy;
    private int dx;
    private boolean moveState;



    // EFFECTS: constructs a ball object at x = 0, y = 0
    public Ball() {
        this.currentXPos = 0;
        this.currentYPos = 0;

    }

    public void directX(int x) {
        this.currentXPos = x * SCALE;
    }

    public void directY(int y) {
        this.currentYPos = y * SCALE + Y_TRANS;
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
        this.moveToXPos = x  * SCALE;
        setDX();
        moveState = true;

    }

    // REQUIRES: a position Y [0, 24]
    // MODIFIES: this
    // EFFECTS: move's this ball to position Y
    public void moveToY(int y) {
        this.moveToYPos = y * SCALE + Y_TRANS;
        setDY();
        moveState = true;
    }


    public void move() {

        if (moveState) {
            if (Math.abs(currentYPos - currentYPos) < dy) {
                if (currentYPos > moveToYPos) {
                    this.currentYPos -= dy;
                } else {
                    this.currentYPos += dy;
                }
            } else {
                this.currentYPos = moveToYPos;
                this.moveState = false;
            }

            if (Math.abs(currentXPos - currentXPos) < dy) {
                if (currentXPos > moveToXPos) {
                    this.currentXPos -= dx;
                } else {
                    this.currentXPos += dx;
                }
            } else {
                this.currentXPos = moveToXPos;
                this.moveState = false;
            }
        }


    }


    public void setDY() {
        dy = Math.abs(currentYPos - moveToYPos) / 48;
    }

    public void setDX() {
        dx = Math.abs(moveToXPos - moveToXPos) / 48;
    }


}
