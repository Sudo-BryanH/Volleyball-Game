package model;

public class MiddleBlockers implements Players {

    // EFFECTS: constructs an Middle Blocker object with player number and starting rotation of 0 (TBD)
    public MiddleBlockers(int playerNum, int side) {

    }


    @Override
    public void moveTo(int x, int y, int speed) {

    }
    // REQUIRES: dir = 1, 2, 3
    // MODIFIES: ball object
    // EFFECTS: moves ball to middle (1) left (2) right (3)
    @Override
    public void spike(int dir) {

    }

    @Override
    public void receive() {

    }
    // REQUIRES: dir = 1, 2, 3
    // MODIFIES: ball object
    // EFFECTS: moves ball to middle (1) left (2) right (3)
    @Override
    public void serve(int dir) {

    }

    //@Override
    //public void block() {

    //}

    @Override
    public int getPosX() {
        return 0;
    }

    @Override
    public int getPosY() {
        return 0;
    }

    @Override
    public void setRotation(int pos) {

    }



    @Override
    public int getRotation() {
        return 0;
    }

    @Override
    public int getNum() {
        return 0;
    }
}
