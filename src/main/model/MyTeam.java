package model;

import java.util.ArrayList;
import java.util.List;
// MyTeam represents the team of players that the user controls.

public class MyTeam extends Team {



    // EFFECTS: Constructs a new team with 1 setter, 2 middle blockers, 2 outside hitters, and 1 opposite hitter
    public MyTeam(String name, Players s, Players mb1, Players mb2, Players oh1, Players oh2,
                  Players op) {
        super(name, s, mb1, mb2, oh1, oh2, op);

    }

    // REQUIRES: a list with at least 6 players with required positions; players must already have their own rotations
    // EFFECTS: Constructs a new team
    public MyTeam(List<Players> members, String name) {
        super(members, name);
    }


    // MODIFIES: player positions
    // EFFECTS: moves the players into formation for serving

    @Override
    public void startPosServe() {
        for (Players p : starters) {
            if (p.getRotation() == 1) {
                p.moveToX(12);
                p.moveToY(24);

            } else if (p.getRotation() == 2) {
                p.moveToX(6);
                p.moveToY(20);

            } else if (p.getRotation() == 3) {
                p.moveToX(2);
                p.moveToY(19);

            } else if (p.getRotation() == 4) {
                p.moveToX(2);
                p.moveToY(14);

            } else if (p.getRotation() == 5) {
                p.moveToX(6);
                p.moveToY(14);

            } else if (p.getRotation() == 6) {
                p.moveToX(10);
                p.moveToY(14);
            }

        }

    }
    // MODIFIES: player positions
    // EFFECTS: moves the players into formation for receiving opponent serves

    @Override
    public void serveReceivePos() {
        for (Players p : starters) {
            if (p.getRotation() == 1) {
                p.moveToX(10);
                p.moveToY(19);

            } else if (p.getRotation() == 2) {
                p.moveToX(6);
                p.moveToY(20);

            } else if (p.getRotation() == 3) {
                p.moveToX(2);
                p.moveToY(19);

            } else if (p.getRotation() == 4) {
                p.moveToX(2);
                p.moveToY(14);

            } else if (p.getRotation() == 5) {
                p.moveToX(6);
                p.moveToY(14);

            } else if (p.getRotation() == 6) {
                p.moveToX(10);
                p.moveToY(14);
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
                p.moveToY(13);
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
                p.moveToX(11);
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



    // REQUIRES: ogNum = num of a player already in starters,
    // newNum be of the same type of player as ogNum and in roster
    // MODIFIES: this
    // EFFECTS: removes player of ogNum from starters, gives his rotation number to
    // player of newNum and adds player of newNum to starters


    // EFFECTS: Adds player p to starters
    @Override
    public void addStartingPlayer(Players p) {
        starters.add(p);
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

    // MODIFIES; this
    // EFFECTS: creates a player, then adds it to this
    @Override
    public String createPlayer(int playerType, int playerNum) {
        Players p = null;

        if (playerType == 0) {
            p = new Setters(playerNum, 1);
        } else if (playerType == 1) {
            p = new MiddleBlockers(playerNum, 1);
        } else if (playerType == 2) {
            p = new OutsideHitter(playerNum, 1);
        } else if (playerType == 3) {
            p = new OppositeHitter(playerNum, 1);
        }

        Event e = new Event("A new player " + p.getPlayingPosition() + " #" + p.getNum() + " was "
                + "added to your team");
        EventLog.getInstance().logEvent(e);

        addPlayer(p);
        return "\nYou have added player " + p.getPlayingPosition() + " " + playerNum
                + "\nto your team. \nYou now have " + getRoster().size() + " players on your team.";


    }
}





