package model;

import java.util.ArrayList;
import java.util.List;
// EnemyTeam class represents an enemy team holding players and the chance that the
// interfaces uses to control movements of each player.

public class EnemyTeam extends Team {

    private int chance;


    // EFFECTS: Constructs a new team with 1 setter, 2 middle blockers, 2 outside hitters, and 1 opposite hitter
    public EnemyTeam(String name, Players s, Players mb1, Players mb2, Players oh1, Players oh2,
                     Players op) {
        super(name, s, mb1, mb2, oh1, oh2, op);

    }

    public EnemyTeam(List<Players> members, String name, int chance) {
        super(members, name);
        this.chance = chance;

    }

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
                p.moveToX(0);
                p.moveToY(0);

            } else if (p.getRotation() == 2) {
                p.moveToX(6);
                p.moveToY(4);

            } else if (p.getRotation() == 3) {
                p.moveToX(10);
                p.moveToY(5);

            } else if (p.getRotation() == 4) {
                p.moveToX(10);
                p.moveToY(10);

            } else if (p.getRotation() == 5) {
                p.moveToX(6);
                p.moveToY(10);

            } else if (p.getRotation() == 6) {
                p.moveToX(2);
                p.moveToY(10);
            }

        }

    }

    // MODIFIES: player positions
    // EFFECTS: moves the players into formation for receiving opponent serves
    @Override
    public void serveReceivePos() {
        for (Players p : starters) {
            if (p.getRotation() == 1) {
                p.moveToX(2);
                p.moveToY(5);

            } else if (p.getRotation() == 2) {
                p.moveToX(6);
                p.moveToY(4);

            } else if (p.getRotation() == 3) {
                p.moveToX(10);
                p.moveToY(5);

            } else if (p.getRotation() == 4) {
                p.moveToX(10);
                p.moveToY(10);

            } else if (p.getRotation() == 5) {
                p.moveToX(6);
                p.moveToY(10);

            } else if (p.getRotation() == 6) {
                p.moveToX(2);
                p.moveToY(10);
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
                p.moveToY(3);

            } else {
                p.moveToX(0);
                p.moveToY(11);
            }

            if (p.equals(setter)) {
                p.moveToX(1);
            } else if (p.equals(middle1) || p.equals(middle2)) {
                p.moveToX(6);
                if (p.getRotation() == 1 || p.getRotation() == 2 || p.getRotation() == 3) {
                    p.moveToY(4); // adjust later
                }
            } else if (p.equals(opposite)) {
                p.moveToX(2);
            } else if (p.equals(outside1) || p.equals(outside2)) {
                p.moveToX(10);
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
                p.moveToY(3);
            } else {
                p.moveToY(11);
            }
            if (p.equals(setter)) {
                p.moveToX(4);
                p.moveToY(10);
            } else if (p.equals(middle1) || p.equals(middle2)) {
                p.moveToX(6);
                if (p.getRotation() == 1 || p.getRotation() == 2 || p.getRotation() == 3) {
                    p.moveToX(3);
                }
            } else if (p.equals(opposite)) {
                p.moveToX(2);
            } else if (p.equals(outside1) || p.equals(outside2)) {
                p.moveToX(10);
                if (p.getRotation() == 1 || p.getRotation() == 2 || p.getRotation() == 3) {
                    p.moveToX(9);
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
                p.moveToY(3);
            } else {
                p.moveToY(11);
            }

            if (p.equals(setter)) {
                p.moveToX(4);
                p.moveToY(10);
            } else if (p.equals(middle1) || p.equals(middle2)) {
                p.moveToX(6);
                if (p.getRotation() == 1 || p.getRotation() == 2 || p.getRotation() == 3) {
                    p.moveToY(5);
                }
            } else if (p.equals(opposite)) {
                p.moveToX(3);
            } else if (p.equals(outside1) || p.equals(outside2)) {
                p.moveToX(11);
                if (p.getRotation() == 1 || p.getRotation() == 2 || p.getRotation() == 3) {
                    p.moveToX(10);
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
                p.moveToY(3);
            } else {
                p.moveToY(11);
            }

            if (p.equals(setter)) {
                p.moveToX(4);
                p.moveToY(10);
            } else if (p.equals(middle1) || p.equals(middle2)) {
                p.moveToX(6);
                if (p.getRotation() == 1 || p.getRotation() == 2 || p.getRotation() == 3) {
                    p.moveToX(3);
                }
            } else if (p.equals(opposite)) {
                p.moveToX(0);
            } else if (p.equals(outside1) || p.equals(outside2)) {
                p.moveToX(11);
                if (p.getRotation() == 1 || p.getRotation() == 2 || p.getRotation() == 3) {
                    p.moveToX(9);

                }
            }

        }
    }





    // REQUIRES: dir [0, 2]
    // MODIFIES: this
    // EFFECTS: sets the ball to the direction of dir
    @Override
    public void set(int dir, Ball ball) {
        setter.set(dir, ball);
    }

    // REQUIRES: dir [0, 2]
    // MODIFIES: this
    // EFFECTS: Player who attacks the ball in dir direction
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



    public int getChance() {
        return chance;
    }


    public void setChance(int c) {
        this.chance = c;
    }



    @Override
    public String createPlayer(int playerType, int playerNum) {
        return null;
    }


}
