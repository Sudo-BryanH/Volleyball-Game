package model;

import java.util.ArrayList;
import java.util.List;

// This is the interface representing the team.
// Teams store the default positions of players in different situations

public abstract class Team {

    protected List<Players> starters;
    protected List<Players> roster;
    protected String name;
    protected Players setter;
    protected Players middle1;
    protected Players outside1;
    protected Players opposite;
    protected Players middle2;
    protected Players outside2;

    // EFFECTS: constrcts a team
    public Team(String name, Players s, Players mb1, Players mb2, Players oh1, Players oh2,
                Players op) {
        this.name = name;
        setter = s;
        middle1 = mb1;
        outside1 = oh1;
        opposite = op;
        middle2 = mb2;
        outside2 = oh2;

        roster = new ArrayList<>();
        starters = new ArrayList<>();

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

        arrangeMbOh();

    }

    // EFFECTS: constrcts a team
    public Team(List<Players> members, String name) {

        this.name = name;
        roster = new ArrayList<>();
        starters = new ArrayList<>();

        for (Players p : members) {
            addPlayer(p);
            if (p.getRotation() != 0) {
                addStartingPlayer(p);
                if (p.getPlayingPosition().equals("Setter")) {
                    setter = p;
                } else if (p.getPlayingPosition().equals("Opposite Hitter")) {
                    opposite = p;
                } else if (p.getPlayingPosition().equals("Outside Hitter")) {
                    chooseMbOh(p);
                } else {
                    chooseMbOh(p);

                }
            }

        }
    }

    // MODIFIES: player object
    // EFFECTS: assignes initial rotation to players
    public void chooseMbOh(Players p) {
        if (p.getPlayingPosition().equals("Outside Hitter")) {
            if (Math.abs(Math.abs(6 - setter.getRotation()) - Math.abs(6 - p.getRotation())) == 1) {
                outside2 = p;
            } else {
                outside1 = p;
            }
        } else {
            if (Math.abs(Math.abs(6 - setter.getRotation()) - Math.abs(6 - p.getRotation())) == 1) {
                middle1 = p;
            } else {
                middle2 = p;

            }
        }
    }

    // MODIFIES: players
    // EFFECTS: puts players in this starting position
    public abstract void startPosServe();

    // MODIFIES: players
    // EFFECTS: puts players in this starting position
    public abstract void serveReceivePos();

    // MODIFIES: players
    // EFFECTS: puts players in this starting position
    public abstract void defendFSetter();

    // MODIFIES: players
    // EFFECTS: puts players in this starting position
    public abstract void defendBSetter();

    // MODIFIES: players
    // EFFECTS: puts players in this starting position
    public abstract void attackFSetter();

    // MODIFIES: players
    // EFFECTS: puts players in this starting position
    public abstract void attackBSetter();

    // MODIFIES: players
    // EFFECTS: changes a player's rotation
    public void changeRotation() {
        for (Players p : starters) {
            if (p.getRotation() == 6) {
                p.setRotation(1);

            } else {
                p.setRotation(p.getRotation() + 1);
            }
        }
    }

    // MODIFIES: middle blockers and outside hitters
    // EFFECTS: Arrange MB and OH to their rotations
    public void arrangeMbOh() {
        middle1.setRotation(2);
        middle2.setRotation(5);
        outside1.setRotation(3);
        outside2.setRotation(6);
    }

    public List<Players> getRoster() {
        return roster;
    }

    public List<Players> getStarters() {
        return starters;
    }

    // REQUIRES: ogNum = num of a player already in starters,
    // newNum be of the same type of player as ogNum and in roster
    // MODIFIES: this
    // EFFECTS: removes player of ogNum from starters, gives his rotation number to
    // player of newNum and adds player of newNum to starters

    public void changeStarters(int ogNum, int newNum) {
        Players sub = getPlayer(newNum);
        Players og = getStartingPlayer(ogNum);

        int rotation = og.getRotation();
        og.setRotation(0);
        sub.setRotation(rotation);
        sub.directMoveY(og.posY);
        sub.directMoveX(og.posX);
        starters.remove(og);
        addStartingPlayer(sub);
    }

    // REQUIRES: a valid player
    // MODIFIES: this
    // EFFECTS: adds a player to the roster
    public void addPlayer(Players p) {
        roster.add(p);
    }

    // REQUIRES: a player number of a player already in the starters list
    // EFFECTS: retrieves a starting player from the starters list
    public Players getStartingPlayer(int playerNum) {
        Players chosenOne = null;

        for (Players p : starters) {
            if (p.getNum() == playerNum) {
                chosenOne = p;
            }
        }

        return chosenOne;
    }

    // REQUIRES: a player number of a player already in the roster list
    // EFFECTS: retrieves a starting player from the roster list
    public Players getPlayer(int playerNum) {
        Players chosenOne = null;

        for (Players p : roster) {
            if (p.getNum() == playerNum) {
                chosenOne = p;

            }
        }
        return chosenOne;
    }


    // EFFECTS: adds a player to starter list
    public void addStartingPlayer(Players p) {
        starters.add(p);
    }

    // EFFECTS: returns whether or not the setter is in the back
    public boolean isSetterBack() {
        if (setter.getRotation() == 1 || setter.getRotation() == 2 || setter.getRotation() == 3) {
            return true;
        } else {
            return false;
        }
    }

    // REQUIRES: dir [0, 3]
    // MODIFIES: ball object
    // EFFECTS: ball moves to a specified position
    public abstract void set(int dir, Ball ball);

    // REQUIRES: dir [0, 2]
    // MODIFIES: ball object
    // EFFECTS: ball moves to a specified position
    public abstract void attack(int who, int dir, Ball ball);

    public String getName() {
        return name;
    }

    // MODIFIES: players
    // EFFECTS: moves each player by their speeds
    public void movePlayers() {
        for (Players p : starters) {
            p.moveBySpeed();
        }
    }

    // MODIFIES: this
    // EFFECTS: creates a new player as specified and adds it to roster
    public abstract String createPlayer(int playerType, int playerNum);


}
