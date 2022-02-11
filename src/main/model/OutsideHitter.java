package model;

public class OutsideHitter implements Players {

    private int side;
    private int playerNum;
    private static String PLAYINGPOSITION = "Outside Hitter";
    private int rotationNum;
    private int posX;
    private int posY;

    // EFFECTS: constructs an Outside Hitter object with player number and starting rotation of 0 (TBD)
    public OutsideHitter(int playerNum, int side) {
        this.playerNum = playerNum;
        this.posX = 0;
        this.posY = 0;
        this.side = side;


    }

    // REQUIRES: x [0, 12]
    // MODIFIES: this
    // EFFECTS: sends Player to position x
    @Override
    public void moveToX(int x) {
        this.posX = x;

    }

    // REQUIRES: y [0, 24]
    // MODIFIES: this
    // EFFECTS: sends Player to position y
    @Override
    public void moveToY(int y) {
        this.posY = y;
    }

    //
    // REQUIRES: dir = 0, 1
    // MODIFIES: ball object
    // EFFECTS: sends the ball to (6, 2) (0, 3) if side 1 or (6, 22)(12 21) if side 0
    // TODO Change to meet requirements of players
    @Override
    public void spike(int dir, Ball ball) {
        if (side == 1) {
            if (dir == 0) {
                ball.moveToX(0);
                ball.moveToY(3);
            } else if (dir == 1) {
                ball.moveToX(6);
                ball.moveToY(2);
            }
        } else if (side == 0) {
            if (dir == 0) {
                ball.moveToX(12);
                ball.moveToY(21);
            } else if (dir == 1) {
                ball.moveToX(6);
                ball.moveToY(22);
            }
        }


    }

    // EFFECTS: moves the ball to location [8, 14]
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

    // REQUIRES: dir = [0, 1]
    // MODIFIES: ball object
    // EFFECTS: sends the ball to either (3, 21) or (9, 21) if side 0, or (3, 3) or (9, 3) if side 1
    // TODO Change to meet requirements of players
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
        return this.posX;
    }

    // EFFECTS: return player's Y position
    @Override
    public int getPosY() {
        return this.posY;
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
    public void set(int d, Ball ball) {

    }

}
