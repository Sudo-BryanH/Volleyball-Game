package model;

import java.util.ArrayList;
import java.util.List;
// MyTeam represents the team of players that the user controls.

public class MyTeam implements Team {

    private String name;
    private Players setter;
    private Players middle1;
    private Players outside1;
    private Players opposite;
    private Players middle2;
    private Players outside2;

    private List<Players> roster = new ArrayList<>();
    private List<Players> starters = new ArrayList<>();


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

        arrangeMbOh();

    }

    // REQUIRES: a list with at least 6 players with required positions; players must already have their own rotations
    // EFFECTS: Constructs a new team
    public MyTeam(List<Players> members, String name) {
        this.name = name;

        for (Players p : members) {
            addPlayer(p);
            if (p.getRotation() != 0) {
                addStartingPlayer(p);
                if (p.getPlayingPosition().equals("Setter")) {
                    this.setter = p;
                } else if (p.getPlayingPosition().equals("Opposite Hitter")) {
                    this.opposite = p;
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
                this.outside2 = p;
            } else {
                this.outside1 = p;
            }
        } else {
            if (Math.abs(Math.abs(6 - setter.getRotation()) - Math.abs(6 - p.getRotation())) == 1) {
                this.middle1 = p;
            } else {
                this.middle2 = p;

            }
        }
    }







    // MODIFIES: player positions
    // EFFECTS: moves the players into formation for serving

    @Override
    public void startPosServe() {
        for (Players p : starters) {
            if (p.getRotation() == 1) {
                p.directMoveX(12);
                p.directMoveY(24);

            } else if (p.getRotation() == 2) {
                p.directMoveX(6);
                p.directMoveY(20);

            } else if (p.getRotation() == 3) {
                p.directMoveX(2);
                p.directMoveY(19);

            } else if (p.getRotation() == 4) {
                p.directMoveX(2);
                p.directMoveY(14);

            } else if (p.getRotation() == 5) {
                p.directMoveX(6);
                p.directMoveY(14);

            } else if (p.getRotation() == 6) {
                p.directMoveX(10);
                p.directMoveY(14);
            }

        }

    }
    // MODIFIES: player positions
    // EFFECTS: moves the players into formation for receiving opponent serves

    @Override
    public void startPosNoServe() {
        for (Players p : starters) {
            if (p.getRotation() == 1) {
                p.directMoveX(10);
                p.directMoveY(19);

            } else if (p.getRotation() == 2) {
                p.directMoveX(6);
                p.directMoveY(20);

            } else if (p.getRotation() == 3) {
                p.directMoveX(2);
                p.directMoveY(19);

            } else if (p.getRotation() == 4) {
                p.directMoveX(2);
                p.directMoveY(14);

            } else if (p.getRotation() == 5) {
                p.directMoveX(6);
                p.directMoveY(14);

            } else if (p.getRotation() == 6) {
                p.directMoveX(10);
                p.directMoveY(14);
            }

        }

    }
    // MODIFIES: player positions
    // EFFECTS: moves the players into formation for defending when the setter
    // is in the front row

    @Override
    public void defendFSetter() {

        for (Players p : starters) {
            if (p.getRotation() == 1 || p.getRotation() == 2 || p.getRotation() == 3) {
                p.moveToX(0);
                p.moveToY(21);

            } else {
                p.moveToX(0);
                p.moveToY(13);
            }

            if (p.equals(setter)) {
                p.moveToX(11);
            } else if (p.equals(middle1) || p.equals(middle2)) {
                p.moveToX(6);
                if (p.getRotation() == 1 || p.getRotation() == 2 || p.getRotation() == 3) {
                    p.moveToY(19);
                }
            } else if (p.equals(opposite)) {
                p.moveToX(10);
            } else if (p.equals(outside1) || p.equals(outside2)) {
                p.moveToX(2);
            }

        }

    }

    // MODIFIES: player positions
    // EFFECTS: moves the players into formation for defending when the setter
    // is in the back row
    @Override
    public void defendBSetter() {

        for (Players p : starters) {
            if (p.getRotation() == 1 || p.getRotation() == 2 || p.getRotation() == 3) {
                p.moveToY(21);
            } else {
                p.moveToY(13);
            }
            if (p.equals(setter)) {
                p.moveToX(8);
                p.moveToY(16);
            } else if (p.equals(middle1) || p.equals(middle2)) {
                p.moveToX(6);
                if (p.getRotation() == 1 || p.getRotation() == 2 || p.getRotation() == 3) {
                    p.moveToX(9);
                }
            } else if (p.equals(opposite)) {
                p.moveToX(10);
            } else if (p.equals(outside1) || p.equals(outside2)) {
                p.moveToX(2);
                if (p.getRotation() == 1 || p.getRotation() == 2 || p.getRotation() == 3) {
                    p.moveToX(3);
                }

            }

        }
    }

    // MODIFIES: player positions
    // EFFECTS: moves the players into formation for attack when the setter
    // is in the front row and opposite is in backrow
    @Override
    public void attackFSetter() {
        for (Players p : starters) {
            if (p.getRotation() == 1 || p.getRotation() == 2 || p.getRotation() == 3) {
                p.moveToY(21);
            } else {
                p.moveToY(14);
            }

            if (p.equals(setter)) {
                p.moveToX(8);
                p.moveToY(14);
            } else if (p.equals(middle1) || p.equals(middle2)) {
                p.moveToX(6);
                if (p.getRotation() == 1 || p.getRotation() == 2 || p.getRotation() == 3) {
                    p.moveToY(19);
                }
            } else if (p.equals(opposite)) {
                p.moveToX(9);
            } else if (p.equals(outside1) || p.equals(outside2)) {
                p.moveToX(0);
                if (p.getRotation() == 1 || p.getRotation() == 2 || p.getRotation() == 3) {
                    p.moveToX(2);
                }
            }

        }
    }

    // MODIFIES: player positions
    // EFFECTS: moves the players into formation for attack when the setter
    // is in the back row and opposite is in front row
    @Override
    public void attackBSetter() {
        for (Players p : starters) {
            if (p.getRotation() == 1 || p.getRotation() == 2 || p.getRotation() == 3) {
                p.moveToY(21);
            } else {
                p.moveToY(13);
            }

            if (p.equals(setter)) {
                p.moveToX(8);
                p.moveToY(14);
            } else if (p.equals(middle1) || p.equals(middle2)) {
                p.moveToX(6);
                if (p.getRotation() == 1 || p.getRotation() == 2 || p.getRotation() == 3) {
                    p.moveToX(9);
                }
            } else if (p.equals(opposite)) {
                p.moveToX(12);
            } else if (p.equals(outside1) || p.equals(outside2)) {
                p.moveToX(0);
                if (p.getRotation() == 1 || p.getRotation() == 2 || p.getRotation() == 3) {
                    p.moveToX(3);
                }
            }

        }
    }

    // MODIFIES: player rotations
    // EFFECTS: changes the rotation of each player in starters

    @Override
    public void changeRotation() {
        for (Players p : starters) {
            if (p.getRotation() == 6) {
                p.setRotation(1);

            } else {
                p.setRotation(p.getRotation() + 1);
            }
        }

    }

    // MODIFIES: players rotations
    // EFFECTS: gives middle blockers and outside hitters their starting rotation num

    @Override
    public void arrangeMbOh() {
        middle1.setRotation(2);
        middle2.setRotation(5);
        outside1.setRotation(3);
        outside2.setRotation(6);

    }

    // EFFECTS: returns the list of players in a team's roster

    @Override
    public List<Players> getRoster() {
        return roster;
    }

    // EFFECTS: returns the list of players in a team's starting list

    @Override
    public List<Players> getStarters() {
        return starters;
    }


    // REQUIRES: ogNum = num of a player already in starters,
    // newNum be of the same type of player as ogNum and in roster
    // MODIFIES: this
    // EFFECTS: removes player of ogNum from starters, gives his rotation number to
    // player of newNum and adds player of newNum to starters

    // TODO make sure that the removed starter has rotation set to 0 or will cause problems with saved file
    @Override
    public void changeStarters(int ogNum, int newNum) {
        Players sub = getPlayer(newNum);
        Players og = getStartingPlayer(ogNum);
        int rotation = og.getRotation();
        sub.setRotation(rotation);
        starters.remove(og);
        addStartingPlayer(sub);
    }

    // EFFECTS: Adds player p to players
    @Override
    public void addPlayer(Players p) {
        roster.add(p);
    }

    // EFFECTS: Adds player p to starters
    @Override
    public void addStartingPlayer(Players p) {
        starters.add(p);
    }

    // TODO write tests
    @Override
    public boolean isSetterBack() {
        if (setter.getRotation() == 1 || setter.getRotation() == 2 || setter.getRotation() == 3) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void set(int dir, Ball ball) {
        setter.set(dir, ball);
    }

    @Override
    public void attack(int who, int dir, Ball ball) {
        if (who == 0) {
            for (Players p : starters) {
                if ((p.getRotation() >= 4) && p.getPlayingPosition() == "Outside Hitter") {
                    p.spike(dir, ball);
                }
            }
        } else if (who == 1) {
            for (Players p : starters) {
                if ((p.getRotation() >= 4) && p.getPlayingPosition() == "Middle Blocker") {
                    p.spike(dir, ball);
                }
            }
        } else if (who == 2) {
            for (Players p : starters) {
                if ((p.getRotation() >= 4) && p.getPlayingPosition() == "Opposite Hitter") {
                    p.spike(dir, ball);
                }
            }
        } else if (who == 3) {
            setter.spike(1, ball);
        }
    }

    @Override
    public String getName() {
        return name;
    }

    // MODIFIES: players
    // EFFECTS: moves each player by their speeds
    @Override
    public void movePlayers() {
        while (getPlayerMovementState()) {
            for (Players p : starters) {
                if (p.getMoveState()) {
                    p.moveBySpeed();
                }
            }
        }
    }

    // EFFECTS: returns whether true (at least one player not moving), false if all are done moving
    @Override
    public boolean getPlayerMovementState() {
        return !setter.getMoveState() && !middle1.getMoveState() && !middle2.getMoveState() && !outside1.getMoveState()
                && !outside2.getMoveState() && !opposite.getMoveState();
    }


    // REQUIRES: a player number of a player already in the starters list
    // EFFECTS: retrieves a starting player from the starters list
    @Override
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
    @Override
    public Players getPlayer(int playerNum) {
        Players chosenOne = null;

        for (Players p : roster) {
            if (p.getNum() == playerNum) {
                chosenOne = p;


            }
        }

        return chosenOne;
    }
}

