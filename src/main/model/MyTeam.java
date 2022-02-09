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

    private List<Players> roster = new ArrayList<>();
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

    @Override
    public void startPosNoServe() {
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

    @Override
    public void defendFSetter() {

        for (Players p : starters) {
            if (p.getRotation() == 1 || p.getRotation() == 2 || p.getRotation() == 3) {
                p.moveToX(0);
                p.moveToY(13);

            } else {
                p.moveToX(0);
                p.moveToY(21);
            }

            if (p.equals(setter)) {
                p.moveToX(13);
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

    @Override
    public void defendBSetter() {

        for (Players p : starters) {
            if (p.getRotation() == 1 || p.getRotation() == 2 || p.getRotation() == 3) {
                p.moveToY(13);
            } else {
                p.moveToY(21);
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

    @Override
    public void attackFSetter() {
        for (Players p : starters) {
            if (p.getRotation() == 1 || p.getRotation() == 2 || p.getRotation() == 3) {
                p.moveToY(13);
            } else {
                p.moveToY(21);
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


    @Override
    public void arrangeMBOH() {
        middle1.setRotation(2);
        middle2.setRotation(5);
        outside1.setRotation(3);
        outside2.setRotation(6);

    }

    @Override
    public List<Players> getRoster() {
        return roster;
    }

    @Override
    public List<Players> getStarters() {
        return starters;
    }

    @Override
    public void changeStarters(int ogNum, int newNum) {
        Players sub = getPlayer(newNum);
        for (Players p : starters) {
            if (p.getNum() == ogNum) {
                starters.remove(p);
                int rotation = p.getRotation();
                sub.setRotation(rotation);
            }
        }
    }

    @Override
    public void addPlayer(Players p) {
        roster.add(p);
    }

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

