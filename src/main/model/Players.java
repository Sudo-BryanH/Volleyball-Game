package model;

// Players is an interface for the classes Setters OutsideHitters OppositeHitters
// and MiddleBlockers.

import java.awt.*;
import java.util.ArrayList;

public abstract class Players {
    static final int SCALE = 30;
    static final int Y_TRANS = 100;
    static final int DY = 10;
    static final int DX = 10;
    protected int side;
    protected int playerNum;
    protected String playingPosition;
    protected String shortPos;
    protected int rotationNum;
    protected int posX;
    protected int posY;
    protected int newPosX;
    protected int newPosY;
    protected boolean moveState = false;

    public Players(int playerNum, int side, int posX, int posY, String playingPos, String shortPos) {
        this.playerNum = playerNum;
        this.side = side;
        this.shortPos = shortPos;
        this.playingPosition = playingPos;
        this.posX = posX;
        this.posY = posY;
    }


    // REQUIRES: x [0, 12]
    // MODIFIES: this
    // EFFECTS: sends Player to position x
    public void moveToX(int x) {
        //this.posX = x  * SCALE;
        this.newPosX = x * SCALE;
        this.moveState = true;

    }

    // REQUIRES: y [0, 24]
    // MODIFIES: this
    // EFFECTS: sends Player to position y
    public void moveToY(int y) {
        //this.posY = y * SCALE + Y_TRANS;
        this.newPosY = y * SCALE + Y_TRANS;
        this.moveState = true;
    }

    // MODIFIES: ball object
    // EFFECTS: Sends the ball at a given speed to position (x, y) depending on the direction
    public abstract void spike(int dir, Ball ball);

    // MODIFIES: ball object
    // EFFECTS: Sends the ball at a given speed to position (x, y) depending on the Point

    public void spike(Point point, Ball ball) {
        ball.moveToX((int) point.getX());
        ball.moveToY((int) point.getY());
    }

    // EFFECTS: moves the ball to location [8, 14] or [4, 8] for enemy
    // NOTE: this is just for implementation sake, setters should never receive

    public void receive(Ball ball) {
        if (side == 1) {
            ball.moveToX(8);
            ball.moveToY(14);
        }
        if (side == 0) {
            ball.moveToX(4);
            ball.moveToY(10);
        }

    }

    // REQUIRES: dir = [0, 1]
    // MODIFIES: ball object
    // EFFECTS: sends the ball to either (3, 21) or (9, 21) if side 0, or (3, 3) or (9, 3) if side 1
    public void serve(int dir, Ball ball) {

        if (side == 0) {
            if (dir == 0) {
                ball.moveToX(3);
                ball.moveToY(21);
            } else if (dir == 1) {
                ball.moveToX(9);
                ball.moveToY(21);
            }
        } else if (side == 1) {
            if (dir == 0) {
                ball.moveToX(3);
                ball.moveToY(3);
            } else if (dir == 1) {
                ball.moveToX(9);
                ball.moveToY(3);
            }
        }
    }

    // MODIFIES: ball object
    // EFFECTS: Ball stops
    // no blocking for now
    //public void block();

    // EFFECTS: returns player's x position
    public int getPosX() {
        return posX;
    }

    // EFFECTS: returns player's x position
    public int getPosY() {
        return posY;
    }

    // REQUIRES: a rotation number between [0, 6]
    // EFFECTS: sets the rotation #
    public void setRotation(int pos) {
        this.rotationNum = pos;
    }

    // EFFECTS: returns the rotation #
    public int getRotation() {
        return rotationNum;
    }

    // EFFECTS: returns the player num
    public int getNum() {
        return playerNum;
    }

    // EFFECTS: returns the playing position of this player
    public String getPlayingPosition() {
        return playingPosition;
    }

    // REQUIRES: int 0 (left) 1 (middle) 2 (right) or 3 (dump)
    // MODIFIES: ball object
    // EFFECTS: moves the ball to attack position
    public void set(int dir, Ball ball) {
        if (side == 0) {
            if (dir == 0) {
                ball.moveToX(11);
                ball.moveToY(11);
            } else if (dir == 1) {
                ball.moveToX(6);
                ball.moveToY(11);
            } else if (dir == 2) {
                ball.moveToX(0);
                ball.moveToY(11);
            }
        } else if (side == 1) {
            if (dir == 0) {
                ball.moveToX(0);
                ball.moveToY(13);
            } else if (dir == 1) {
                ball.moveToX(6);
                ball.moveToY(13);
            } else if (dir == 2) {
                ball.moveToX(11);
                ball.moveToY(13);
            }
        }
    }

    // EFFECTS: returns the side of this player
    public int getSide() {
        return side;
    }


    public int getNewPosX() {
        return newPosX;
    }

    public int getNewPosY() {
        return newPosY;
    }

    // MODIFIES: this
    // EFFECTS: moves the player by DY and DX upon calling unless posX = newposX or posY = newPosY
    public void moveBySpeed() {
        if (moveState) {
            if (Math.abs(posY - newPosY) > DY) { // swap ineq sign to fix
                if (posY > newPosY) {
                    this.posY -= DY;
                } else {
                    this.posY += DY;
                }
            } else if (Math.abs(posY - newPosY) <= DY) {
                this.posY = newPosY;

            }

            if (Math.abs(posX - newPosX) > DX) {
                if (posX > newPosX) {
                    this.posX -= DX;
                } else {
                    this.posX += DX;
                }
            } else if (Math.abs(posX - newPosX) <= DX) {
                this.posX = newPosX;

            }

            if (posX == newPosX && posY == newPosY) {
                moveState = true;
            }
        }
    }

    public void directMoveX(int x) {
        this.posX = x * SCALE;
    }

    public void directMoveY(int y) {
        this.posY = y * SCALE + Y_TRANS;
    }

    public String getShortPos() {
        return shortPos;
    }

    public abstract ArrayList<Point> getAttackPoints(int side);

    public void declareNum(int num) {
        playerNum = num;
    }
}


