package model;

import java.util.List;

public class EnemyTeam implements Team {
    // EFFECTS: Constructs a new team with 1 setter, 2 middle blockers, 2 outside hitters, and 1 opposite hitter
    public EnemyTeam(String name, Players s, Players mb1, Players mb2, Players oh1, Players oh2,
                     Players op) {
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
    public List<Players> getRoster() {
        return null;
    }

    @Override
    public List<Players> getStarters() {
        return null;
    }

    @Override
    public void changeStarters(int ogNum, int newNum) {

    }

    @Override
    public void addPlayer(Players p) {

    }

    @Override
    public Players getStartingPlayer(int playerNum) {
        return null;
    }

    @Override
    public Players getPlayer(int playerNum) {
        return null;
    }
}
