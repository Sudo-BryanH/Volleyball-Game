package model;


// OutsideHitter is a player class with an x, y position and their own type of hitting

public class OutsideHitter implements Players {

    private int side;
    private int playerNum;
    private static String PLAYINGPOSITION = "Outside Hitter";
    private static String SHORTPOS = "OH";
    private int rotationNum;
    private int posX;
    private int posY;
    private int newPosX;
    private int newPosY;
    private boolean moveState = false;
    private int dy;
    private int dx;

    // EFFECTS: constructs an Outside Hitter object with player number and starting rotation of 0 (TBD)
    public OutsideHitter(int playerNum, int side) {
        this.playerNum = playerNum;
        this.posX = 0;
        this.posY = 0;
        this.side = side;


    }

    @Override
    public void directMoveX(int x) {
        this.posX = x * SCALE;
    }

    @Override
    public void directMoveY(int y) {
        this.posY = y * SCALE + Y_TRANS;
    }

    @Override
    public String getShortPos() {
        return SHORTPOS;
    }


    // TODO have a move directly to X method so constructor can move players quickly
    // TODO program must know when to stop moving each player
    // REQUIRES: x [0, 12]
    // MODIFIES: this
    // EFFECTS: sends Player to position x

    @Override
    public void moveToX(int x) {
        //this.posX = x  * SCALE;
        this.newPosX = x  * SCALE;
        setDX();
        this.moveState = true;

    }

    // REQUIRES: y [0, 24]
    // MODIFIES: this
    // EFFECTS: sends Player to position y
    @Override
    public void moveToY(int y) {
        //this.posY = y * SCALE + Y_TRANS;
        this.newPosY = y * SCALE + Y_TRANS;
        setDY();
        this.moveState = true;
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

    // EFFECTS: moves the ball to location [8, 14] or [4, 8] for enemy
    @Override
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

    @Override
    public void setMoveState() {

        moveState = !moveState;

    }

    @Override
    public boolean getMoveState() {
        return moveState;
    }

    @Override
    public int getNewPosX() {
        return newPosX;
    }

    @Override
    public int getNewPosY() {
        return newPosY;
    }

    @Override
    public void setDY() {
        dy = Math.abs(posY - newPosY) / SPEED;
    }

    @Override
    public void setDX() {
        dx = Math.abs(posX - newPosX) / SPEED;
    }

    @Override
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

}
