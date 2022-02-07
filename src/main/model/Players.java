package model;

public interface Players {

    // REQUIRES: x[0, 12],
    // MODIFIES: this
    // EFFECTS: Moves player to x
    public void moveToX(int x);

    // REQUIRES:  y[13, 24],
    // MODIFIES: this
    // EFFECTS: Moves player to x
    public void moveToY(int y);

    // MODIFIES: ball object
    // EFFECTS: Sends the ball at a given speed to position (x, y) depending on the direction
    public void spike(int dir, Ball ball);

    // MODIFIES: ball object (list non paramenter?)
    // EFFECTS: Sends the ball at a given speed to setter's position (x, y)
    public void receive(Ball ball);

    // MODIFIES: ball object
    // EFFECTS: Sends the ball at a given speed to position (x, y) depending on the direction
    public void serve(int dir, Ball ball);

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
