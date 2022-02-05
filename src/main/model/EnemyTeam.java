package model;

import java.util.ArrayList;

public class EnemyTeam implements Team {
    // EFFECTS: Constructs a new team with 1 setter, 2 middle blockers, 2 outside hitters, and 1 opposite hitter
    public EnemyTeam(String name, Setters s, MiddleBlockers mb1, MiddleBlockers mb2, OutsideHitter oh1, OutsideHitter oh2,
                OppositeHitter op) {
        //stub
    }

    @Override
    public void startPosServe() {

    }

    @Override
    public void startPosNoServe() {

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

    }

    @Override
    public void arrangeMBOH() {

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
