package model;

// OppositeHitter is a player class with an x, y position and their own type of hitting

import java.awt.*;
import java.util.ArrayList;

public class OppositeHitter extends Players {


    private static final Point SPIKESTRAIGHT1 = new Point(12, 4);
    private static final Point SPIKEMID1 = new Point(6, 3);
    private static final Point SPIKESTRAIGHT0 = new Point(0, 21);
    private static final Point SPIKEMID0 = new Point(6, 21);


    // EFFECTS: constructs an Opposite Hitter object with player number and starting rotation of 4
    public OppositeHitter(int playerNum, int side) {
        super(playerNum, side, 300, 460, "Opposite Hitter", "OP");


    }


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


    @Override
    public void spike(int dir, Ball ball) {

        if (side == 1) {
            if (dir == 0) {
                ball.moveToX(12);
                ball.moveToY(4);
            } else if (dir == 1) {
                ball.moveToX(6);
                ball.moveToY(3);
            }
        } else if (side == 0) {
            if (dir == 0) {
                ball.moveToX(0);
                ball.moveToY(20);
            } else if (dir == 1) {
                ball.moveToX(6);
                ball.moveToY(21);
            }
        }


    }


}


