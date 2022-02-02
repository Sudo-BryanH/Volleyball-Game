package model;

public interface Players {

    // REQUIRES: x[0, 12], y[13, 24], speed[0,5]
    // MODIFIES: this
    // EFFECTS: Moves player to (x, y) with at a given speed
    public void moveTo(int x, int y, int speed);

    // MODIFIES: ball object
    // EFFECTS: Sends the ball at a given speed to position (x, y) depending on the direction
    public void spike(int dir);

    // MODIFIES: ball object (list non paramenter?)
    // EFFECTS: Sends the ball at a given speed to setter's position (x, y)
    public void receive();

    // MODIFIES: ball object
    // EFFECTS: Sends the ball at a given speed to position (x, y) depending on the direction
    public void serve(int dir);

    // MODIFIES: ball object
    // EFFECTS: Ball stops
    // no blocking for now
    //public void block();

    // EFFECTS: returns player's x position
    public int getPosX();

    // EFFECTS: returns player's x position
    public int getPosY();

    // REQUIRES: a rotation number between [0, 6]
    // EFFECTS: sets the rotation #
    public void setRotation(int pos);

    // EFFECTS: returns the rotation #
    public int getRotation();

    // EFFECTS: returns the player num
    public int getNum();


}
