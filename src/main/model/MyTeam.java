package model;

import java.util.ArrayList;
import java.util.List;

public class MyTeam implements Team {

    private String name;
    private Players setter;
    private Players middle1;
    private Players outside1;
    private Players opposite;
    private Players middle2;
    private Players outside2;

    List<Players> roster = new ArrayList<>();
    List<Players> starters = new ArrayList<>();


    // EFFECTS: Constructs a new team with 1 setter, 2 middle blockers, 2 outside hitters, and 1 opposite hitter
    public MyTeam(String name, Players s, Players mb1, Players mb2, Players oh1, Players oh2,
                  Players op) {
        this.name = name;
        this.setter = s;
        this.middle1 = mb1;
        this.outside1 = oh1;
        this.opposite = op;
        this.middle2 = mb2;
        this.outside2 = oh2;

        roster.add(setter);
        roster.add(middle1);
        roster.add(outside1);
        roster.add(opposite);
        roster.add(middle2);
        roster.add(outside2);

        starters.add(setter);
        starters.add(middle1);
        starters.add(outside1);
        starters.add(opposite);
        starters.add(middle2);
        starters.add(outside2);

        arrangeMBOH();

    }


    @Override
    public void startPosServe() {
        for (Players p : starters) {
            if (p.getRotation() == 1) {
                p.moveTo(12, 24, 0);

            } else if (p.getRotation() == 2) {
                p.moveTo(6, 20, 0);

            } else if (p.getRotation() == 3) {
                p.moveTo(2, 19, 0);

            } else if (p.getRotation() == 4) {
                p.moveTo(2, 14, 0);

            } else if (p.getRotation() == 5) {
                p.moveTo(6, 14, 0);

            } else if (p.getRotation() == 6) {
                p.moveTo(10, 14, 0);
            }

        }

    }

    @Override
    public void startPosNoServe() {
        for (Players p : starters) {
            if (p.getRotation() == 1) {
                p.moveTo(10, 19, 0);

            } else if (p.getRotation() == 2) {
                p.moveTo(6, 20, 0);

            } else if (p.getRotation() == 3) {
                p.moveTo(2, 19, 0);

            } else if (p.getRotation() == 4) {
                p.moveTo(2, 14, 0);

            } else if (p.getRotation() == 5) {
                p.moveTo(6, 14, 0);

            } else if (p.getRotation() == 6) {
                p.moveTo(10, 14, 0);
            }

        }

    }

    @Override
    public void defendFSetter() {

    }

    @Override
    public void defendBSetter() {

    }

    @Override
    public void attackFSetter() {

    }

    @Override
    public void attackBSetter() {

    }

    @Override
    public void changeRotation() {
        for (Players p : starters) {
            if (p.getRotation() < 5) {
                p.setRotation(p.getRotation() + 1);
            } else {
                p.setRotation(1);
            }
        }

    }



    @Override
    public void arrangeMBOH() {
        middle1.setRotation(2);
        middle2.setRotation(5);
        outside1.setRotation(3);
        outside2.setRotation(6);

    }

    @Override
    public ArrayList<String> printRoster() {
        return null;
    }

    @Override
    public ArrayList<Integer> printStarters() {
        return null;
    }

    @Override
    public void changeStarters(int ogNum, int newNum) {

    }

    @Override
    public void addPlayer() {

    }

    @Override
    public Players getStarters(int playerNum) {
        return null;
    }

    @Override
    public Players getPlayer(int playerNum) {
        return null;
    }
}
