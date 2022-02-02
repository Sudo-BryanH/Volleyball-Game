package model;

public interface Players {

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
    public void block();

    // EFFECTS: determines player's x position
    public int getPosX();

    // EFFECTS: determines player's x position
    public int getPosY();


}
