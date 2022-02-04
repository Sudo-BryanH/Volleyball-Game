package model;

import java.util.ArrayList;

public interface Team {

    // MODIFIES: players
    // EFFECTS: puts players in this starting position
    public void startPosServe();

    // MODIFIES: players
    // EFFECTS: puts players in this starting position
    public void startPosNoServe();

    // MODIFIES: players
    // EFFECTS: puts players in this starting position
    public void defendFSetter();

    // MODIFIES: players
    // EFFECTS: puts players in this starting position
    public void defendBSetter();

    // MODIFIES: players
    // EFFECTS: puts players in this starting position
    public void attackFSetter();

    // MODIFIES: players
    // EFFECTS: puts players in this starting position
    public void attackBSetter();

    // MODIFIES: players
    // EFFECTS: changes a player's rotation
    public void changeRotation();

    // MODIFIES: middle blockers and outside hitters
    // EFFECTS: Arrange MB and OH to their rotations
    public void arrangeMBOH();

    public ArrayList<String> printRoster();

    public ArrayList<Integer> printStarters();

    // REQUIRES: equal type of players to exchange, both players must already exist
    // MODIFIES: this
    // EFFECTS: changes a starting member, assigns new memeber the roation number of old player
    public void changeStarters(int ogNum, int newNum);

    public void addPlayer();

    // EFFECTS: gets a player from the starter list
    public Players getStarters(int playerNum);

    // EFFECTS: gets a player from the roster list
    public Players getPlayer(int playerNum);



}
