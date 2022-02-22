package model;

import java.util.ArrayList;
import java.util.List;

// This is the interface representing the team.
// Teams store the default positions of players in different situations

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
    public void arrangeMbOh();

    public List<Players> getRoster();

    public List<Players> getStarters();

    // REQUIRES: equal type of players to exchange, both players must already exist
    // MODIFIES: this
    // EFFECTS: changes a starting member, assigns new memeber the roation number of old player
    public void changeStarters(int ogNum, int newNum);

    // REQUIRES: a valid player
    // MODIFIES: this
    // EFFECTS: adds a player to the roster
    public void addPlayer(Players p);

    // EFFECTS: gets a player from the starter list
    public Players getStartingPlayer(int playerNum);

    // EFFECTS: gets a player from the roster list
    public Players getPlayer(int playerNum);

    // EFFECTS: adds a player to starter list
    public void addStartingPlayer(Players p);

    // EFFECTS: returns whether or not the setter is in the back
    public boolean isSetterBack();

    // REQUIRES: dir [0, 3]
    // MODIFIES: ball object
    // EFFECTS: ball moves to a specified position
    public void set(int dir, Ball ball);

    // REQUIRES: dir [0, 2]
    // MODIFIES: ball object
    // EFFECTS: ball moves to a specified position
    public void attack(int who, int dir, Ball ball);


}
