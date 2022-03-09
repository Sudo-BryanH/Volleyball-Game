package model;

// Players is an interface for the classes Setters OutsideHitters OppositeHitters
// and MiddleBlockers.

public interface Players {
    static final int SCALE = 30;
    static final int Y_TRANS = 100;

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

    // EFFECTS: returns the playing position of this player
    public String getPlayingPosition();

    // REQUIRES: int 0 (left) 1 (middle) 2 (right) or 3 (dump)
    // MODIFIES: ball object
    // EFFECTS: moves the ball to attack position
    public void set(int dir, Ball ball);

    // EFFECTS: returns the side of this player
    public int getSide();

}
