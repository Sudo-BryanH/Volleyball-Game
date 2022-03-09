package model;

// MiddleBlocker is a player class with an x, y position and their own type of hitting

public class MiddleBlockers implements Players {


    private int side;
    private int playerNum;
    private static String PLAYINGPOSITION = "Middle Blocker";
    private int rotationNum;
    private int posX;
    private int posY;

    // EFFECTS: constructs an Middle Blocker object with player number and starting rotation of 0 (TBD)
    public MiddleBlockers(int playerNum, int side) {
        this.playerNum = playerNum;
        this.side = side;
        this.posX = 0;
        this.posY = 0;


    }


    // REQUIRES: x [0, 12]
    // MODIFIES: this
    // EFFECTS: sends Player to position x
    @Override
    public void moveToX(int x) {
        posX = x * SCALE;

    }

    // REQUIRES: y [0, 24]
    // MODIFIES: this
    // EFFECTS: sends Player to position y
    @Override
    public void moveToY(int y) {
        posY = y * SCALE + Y_TRANS;
    }

    //
    // REQUIRES: dir = 1
    // MODIFIES: ball object
    // EFFECTS: sends the ball to (6, 6)(2, 5)(10, 5) if side 1
    // or (6, 18)(2, 19)(10, 19) if side 0
    // TODO Change to meet requirements of players
    @Override
    public void spike(int dir, Ball ball) {
        if (side == 1) {
            if (dir == 0) {
                ball.moveToX(6);
                ball.moveToY(6);
            } else if (dir == 1) {
                ball.moveToX(2);
                ball.moveToY(5);
            } else if (dir == 2) {
                ball.moveToX(10);
                ball.moveToY(5);
            }
        } else if (side == 0) {
            if (dir == 0) {
                ball.moveToX(6);
                ball.moveToY(18);
            } else if (dir == 1) {
                ball.moveToX(2);
                ball.moveToY(19);
            } else if (dir == 2) {
                ball.moveToX(10);
                ball.moveToY(19);
            }
        }
    }


    // MODIFIES: ball object
    // EFFECTS: moves ball to (8, 14) or (4, 8) depending on side
    @Override
    public void receive(Ball ball) {
        if (side == 1) {
            ball.moveToX(8);
            ball.moveToY(14);
        }
        if (side == 0) {
            ball.moveToX(4);
            ball.moveToY(8);
        }

    }

    // REQUIRES: dir = [1, 2]
    // MODIFIES: ball object
    // EFFECTS: sends the ball to either (3, 21) or (9, 21) if side 0, or (3, 3) or (9, 3) if side 1

    @Override
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

    //@Override
    //public void block() {

    //
    // EFFECTS: return player's X position
    @Override
    public int getPosX() {
        return posX;
    }

    // EFFECTS: return player's Y position
    @Override
    public int getPosY() {
        return posY;
    }

    // REQUIRES: pos [1, 6]
    // MODIFIES: this
    // EFFECTS: changes rotation based on pos
    @Override
    public void setRotation(int pos) {
        this.rotationNum = pos;

    }

    // EFFECTS: return player's rotation number
    @Override
    public int getRotation() {
        return rotationNum;
    }

    // EFFECTS: return player's number
    @Override
    public int getNum() {
        return playerNum;
    }

    @Override
    public String getPlayingPosition() {
        return PLAYINGPOSITION;
    }


    @Override
    // REQUIRES: int 0 (left) 1 (middle) 2 (right) or 3 (dump)
    // MODIFIES: ball object
    // EFFECTS: moves the ball to attack position
    // NOTE: no player but the setter should set, this is just for implementaiton sake
    public void set(int dir, Ball ball) {
        if (side == 0) {
            if (dir == 0) {
                ball.moveToX(12);
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
                ball.moveToX(12);
                ball.moveToY(13);
            }
        }
    }

    @Override
    public int getSide() {
        return side;
    }

}
