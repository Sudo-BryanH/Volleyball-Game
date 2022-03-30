package model;

// Setters is a type of player. Unlike other player types, they should not receive but can set
// Note: they can receive but the program dictates that they won't

import java.awt.*;
import java.util.ArrayList;

public class Setters extends Players {

    protected int dumps;

    // EFFECTS: constructs an Setter object with player number and starting rotation of 1
    //          and, dumps = 0, position (0, 0)
    public Setters(int playerNum, int side) {
        super(playerNum, side, 300, 460, "Setter", "S");
        this.dumps = 0;

    }


    // REQUIRES: dir = 1
    // MODIFIES: ball object
    // EFFECTS: sends the ball to (6, 6) if side 1 or (6, 18) if side 0
    @Override
    public void spike(int dir, Ball ball) {
        ball.moveToX(6);
        if (side == 1) {
            ball.moveToY(6);
        } else if (side == 0) {
            ball.moveToY(18);
        }

        dumps++;

    }



    // EFFECTS: returns true if dump limit of 2 has exceeded
    public boolean canDump() {
        return dumps < 2;
    }


    // EFFECTS: returns the number of dumps
    public int numDump() {
        return dumps;
    }



    // EFFECTS: returns the list of points this player can attack
    @Override
    public ArrayList<Point> getAttackPoints(int side) {
        return null;
    }


}
