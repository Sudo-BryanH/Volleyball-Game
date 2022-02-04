package model;

public class OppositeHitter implements Players {

    // EFFECTS: constructs an Opposite Hitter object with player number and starting rotation of 4
    public OppositeHitter(int playerNum) {

    }


    @Override
    public void moveTo(int x, int y, int speed) {

    }
    // REQUIRES: Dir [1, 2]
    // Modifies: ball object
    // EFFECTS: Sends the ball to (6, 3) or (12, 4) depending on dir
    @Override
    public void spike(int dir) {

    }

    // MODIFIES: ball object
    // EFFECTS: Sends the ball to setter position
    @Override
    public void receive() {

    }
    // REQUIRES: Dir [1, 2]
    // Modifies: ball object
    // EFFECTS: Sends the ball to (6, 3) or (12, 4) depending on dir
    @Override
    public void serve(int dir) {

    }

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

    // EFFECTS: returns the rotation number of the player
    @Override
    public int getRotation() {
        return 0;
    }

    @Override
    public int getNum() {
        return 0;
    }
}


