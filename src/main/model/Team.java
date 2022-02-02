package model;

import java.util.ArrayList;

public interface Team {

    public void startPosServe();

    public void startPosNoServe();

    public void defendFSetter();

    public void defendBSetter();

    public void attackFSetter();

    public void attackBSetter();

    public void changeRotation();

    // MODIFIES: middle blockers and outside hitters
    // EFFECTS: Arrange MB and OH to their rotations
    public void arrangeMBOH();

    public ArrayList<String> printPositions();

    // REQUIRES: equal type of players to exchange, both players must already exist
    // MODIFIES: this
    // EFFECTS: changes a starting member
    public void changeStarters(int ogNum, int newNum) {

    }

    public void addPlayer() {

    }



}
