package model;

public class Setters implements Players {
    // EFFECTS: constructs an Setter object with player number and starting rotation of 1
    //          and, dumps = 0, position (0, 0)
    public Setters(int playerNum, int side) {

    }


    @Override
    public void moveTo(int x, int y, int speed) {

    }
    // REQUIRES: dir = 1
    // MODIFIES: ball object
    // EFFECTS: sends the ball to (6, 6)
    @Override
    public void spike(int dir) {

    }

    // EFFECTS: none. Setters should not be able to receive
    @Override
    public void receive() {

    }

    // REQUIRES: dir = 1
    // MODIFIES: ball object
    // EFFECTS: sends the ball to (6, 6)
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

    // REQUIRES: int 1 (left) 2 (middle) or 3 (right)
    // MODIFIES: ball object
    // EFFECTS: moves the ball to attack position
    public void set(int dir) {

    }

    // EFFECTS: returns true if dump limit of 2 has exceeded
    public boolean canDump() {
        return false;
    }

    public int numDump() {
        return -1;
    }
}
