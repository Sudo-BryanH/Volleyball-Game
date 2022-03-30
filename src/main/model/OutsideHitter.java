package model;


// OutsideHitter is a player class with an x, y position and their own type of hitting

import java.awt.*;
import java.util.ArrayList;

public class OutsideHitter extends Players {


    private static final Point SPIKESTRAIGHT1 = new Point(0, 3);
    private static final Point SPIKEMID1 = new Point(6, 2);
    private static final Point SPIKESTRAIGHT0 = new Point(12, 21);
    private static final Point SPIKEMID0 = new Point(6, 22);

    // EFFECTS: constructs an Outside Hitter object with player number and starting rotation of 0 (TBD)
    public OutsideHitter(int playerNum, int side) {
        super(playerNum, side, 60, 460, "Outside Hitter", "OH");

    }

    // REQUIRES: dir = 0, 1
    // MODIFIES: ball object
    // EFFECTS: sends the ball to (6, 2) (0, 3) if side 1 or (6, 22)(12 21) if side 0

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


    // EFFECTS: returns the list of points this player can attack
    @Override
    public ArrayList<Point> getAttackPoints(int side) {
        ArrayList<Point> spikes = new ArrayList<Point>();

        if (side == 0) {
            spikes.add(SPIKEMID0);
            spikes.add(SPIKESTRAIGHT0);
        } else {
            spikes.add(SPIKEMID1);
            spikes.add(SPIKESTRAIGHT1);
        }



        return spikes;
    }


}
