package model;

// MiddleBlocker is a player class with an x, y position and their own type of hitting

import java.awt.*;
import java.util.ArrayList;

public class MiddleBlockers extends Players {




    private static final Point SPIKELEFT1 = new Point(10, 5);
    private static final Point SPIKERIGHT1 = new Point(2, 5);
    private static final Point SPIKEMID1 = new Point(6, 6);
    private static final Point SPIKELEFT0 = new Point(2, 19);
    private static final Point SPIKERIGHT0 = new Point(10, 19);
    private static final Point SPIKEMID0 = new Point(6, 18);

    // EFFECTS: constructs an Middle Blocker object with player number and starting rotation of 0 (TBD)
    public MiddleBlockers(int playerNum, int side) {
        super(playerNum, side, 180, 460, "Middle Blocker", "MB");



    }


    @Override
    public ArrayList<Point> getAttackPoints(int side) {
        ArrayList<Point> spikes = new ArrayList<Point>();

        if (side == 0) {
            spikes.add(SPIKELEFT0);
            spikes.add(SPIKERIGHT0);
            spikes.add(SPIKEMID0);
        } else {
            spikes.add(SPIKELEFT1);
            spikes.add(SPIKERIGHT1);
            spikes.add(SPIKEMID1);
        }


        return spikes;
    }


    //
    // REQUIRES: dir = 1
    // MODIFIES: ball object
    // EFFECTS: sends the ball to (6, 6)(2, 5)(10, 5) if side 1
    // or (6, 18)(2, 19)(10, 19) if side 0
    // TODO Change to meet requirements of players
    @Override
    public void spike(int dir, Ball ball) {
        if (side == 1) {
            if (dir == 0) {
                ball.moveToX(6);
                ball.moveToY(6);
            } else if (dir == 1) {
                ball.moveToX(2);
                ball.moveToY(5);
            } else if (dir == 2) {
                ball.moveToX(10);
                ball.moveToY(5);
            }
        } else if (side == 0) {
            if (dir == 0) {
                ball.moveToX(6);
                ball.moveToY(18);
            } else if (dir == 1) {
                ball.moveToX(2);
                ball.moveToY(19);
            } else if (dir == 2) {
                ball.moveToX(10);
                ball.moveToY(19);
            }
        }
    }




}
