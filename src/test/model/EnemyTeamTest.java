package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class EnemyTeamTest {

    EnemyTeam testTeam;
    EnemyTeam testTeam2;
    Players testSet;
    Players testMB1;
    Players testMB2;
    Players testOH1;
    Players testOH2;
    Players testOP;
    Players testMB3;
    Players testSet2;
    Ball mikasa;
    List<Players> members;



    @BeforeEach
    public void setUp() {
        testMB1 = new MiddleBlockers(10, 0);
        testMB2 = new MiddleBlockers(11, 0);
        testOH1 = new OutsideHitter(3, 0);
        testOH2 = new OutsideHitter(5, 0);
        testSet = new Setters(9, 0);
        testOP = new OppositeHitter(1, 0);
        testMB3 = new MiddleBlockers(7, 0);
        testSet2 = new Setters(2, 0);

        testTeam = new EnemyTeam("testTeam", testSet, testMB1, testMB2,
                testOH1, testOH2, testOP);

        testTeam.addPlayer(testMB3);
        testTeam.addPlayer(testSet2);
        testTeam.arrangeMbOh();
        mikasa = new Ball();
        testTeam.setChance(10);
        members = new ArrayList<>();

        arrayListAdder();
        testTeam2 = new EnemyTeam(members, "testTeam2", 10);
    }

    public void arrayListAdder() {
        testMB3.setRotation(0);
        testSet2.setRotation(0);
        members.add(testSet);
        members.add(testMB1);
        members.add(testMB2);
        members.add(testOH1);
        members.add(testOH2);
        members.add(testOP);
        members.add(testMB3);
        members.add(testSet2);

    }

    @Test
    public void testConstructor() {
        assertEquals(1 ,testSet.getRotation());
        assertEquals(2, testMB1.getRotation());
        assertEquals(3, testOH1.getRotation());
        assertEquals(4, testOP.getRotation());
        assertEquals(5, testMB2.getRotation());
        assertEquals(6, testOH2.getRotation());

        assertEquals(8, testTeam.getRoster().size());
        assertEquals(6, testTeam.getStarters().size());
    }

    @Test
    public void testConstructor2() {
        assertEquals(1, testSet.getRotation());
        assertEquals(2, testMB1.getRotation());
        assertEquals(3, testOH1.getRotation());
        assertEquals(4, testOP.getRotation());
        assertEquals(5, testMB2.getRotation());
        assertEquals(6, testOH2.getRotation());
        assertEquals(6, testTeam2.getStarters().size());
        assertEquals(8, testTeam2.getRoster().size());
        assertEquals("Setter", testSet.getPlayingPosition());
        assertEquals("Middle Blocker", testMB1.getPlayingPosition());
        assertEquals("Outside Hitter", testOH1.getPlayingPosition());
        assertEquals("Opposite Hitter", testOP.getPlayingPosition());
        assertEquals("Middle Blocker", testMB2.getPlayingPosition());
        assertEquals("Outside Hitter", testOH2.getPlayingPosition());
        assertEquals(10, testTeam2.getChance());
    }

    @Test
    public void testStartPosServe(){
        testTeam.startPosServe();
        assertEquals(0, testSet.getPosX());
        assertEquals(0, testSet.getPosY());
        assertEquals(6, testMB1.getPosX());
        assertEquals(4, testMB1.getPosY());
        assertEquals(10, testOH1.getPosX());
        assertEquals(5, testOH1.getPosY());
        assertEquals(10, testOP.getPosX());
        assertEquals(10, testOP.getPosY());
        assertEquals(6, testMB2.getPosX());
        assertEquals(10, testMB2.getPosY());
        assertEquals(2, testOH2.getPosX());
        assertEquals(10, testOH2.getPosY());

        testTeam.changeRotation();
        testTeam.startPosServe();
        assertEquals(0, testOH2.getPosX());
        assertEquals(0, testOH2.getPosY());
        assertEquals(6, testSet.getPosX());
        assertEquals(4, testSet.getPosY());
        assertEquals(10, testMB1.getPosX());
        assertEquals(5, testMB1.getPosY());
        assertEquals(10, testOH1.getPosX());
        assertEquals(10, testOH1.getPosY());
        assertEquals(6, testOP.getPosX());
        assertEquals(10, testOP.getPosY());
        assertEquals(2, testMB2.getPosX());
        assertEquals(10, testMB2.getPosY());
    }

    @Test
    public void testStartNoServe(){

        testTeam.ServeReceivePos();
        assertEquals(2, testSet.getPosX());
        assertEquals(5, testSet.getPosY());
        assertEquals(6, testMB1.getPosX());
        assertEquals(4, testMB1.getPosY());
        assertEquals(10, testOH1.getPosX());
        assertEquals(5, testOH1.getPosY());
        assertEquals(10, testOP.getPosX());
        assertEquals(10, testOP.getPosY());
        assertEquals(6, testMB2.getPosX());
        assertEquals(10, testMB2.getPosY());
        assertEquals(2, testOH2.getPosX());
        assertEquals(10, testOH2.getPosY());

        testTeam.changeRotation();
        testTeam.ServeReceivePos();
        assertEquals(2, testOH2.getPosX());
        assertEquals(5, testOH2.getPosY());
        assertEquals(6, testSet.getPosX());
        assertEquals(4, testSet.getPosY());
        assertEquals(10, testMB1.getPosX());
        assertEquals(5, testMB1.getPosY());
        assertEquals(10, testOH1.getPosX());
        assertEquals(10, testOH1.getPosY());
        assertEquals(6, testOP.getPosX());
        assertEquals(10, testOP.getPosY());
        assertEquals(2, testMB2.getPosX());
        assertEquals(10, testMB2.getPosY());
    }

    @Test
    public void testDefendFSetter(){
        testTeam.changeRotation();
        testTeam.changeRotation();
        testTeam.changeRotation();

        testTeam.defendFSetter();
        assertEquals(1, testSet.getPosX());
        assertEquals(11, testSet.getPosY());
        assertEquals(6, testMB1.getPosX());
        assertEquals(11, testMB1.getPosY());
        assertEquals(10, testOH1.getPosX());
        assertEquals(11, testOH1.getPosY());
        assertEquals(2, testOP.getPosX());
        assertEquals(3, testOP.getPosY());
        assertEquals(6, testMB2.getPosX());
        assertEquals(4, testMB2.getPosY());
        assertEquals(10, testOH2.getPosX());
        assertEquals(3, testOH2.getPosY());

        testTeam.changeRotation();
        testTeam.defendFSetter();

        assertEquals(1, testSet.getPosX());
        assertEquals(11, testSet.getPosY());
        assertEquals(6, testMB1.getPosX());
        assertEquals(11, testMB1.getPosY());
        assertEquals(10, testOH1.getPosX());
        assertEquals(3, testOH1.getPosY());
        assertEquals(2, testOP.getPosX());
        assertEquals(3, testOP.getPosY());
        assertEquals(6, testMB2.getPosX());
        assertEquals(4, testMB2.getPosY());
        assertEquals(10, testOH2.getPosX());
        assertEquals(11, testOH2.getPosY());


    }

    @Test
    public void testDefendBSetter(){

        testTeam.defendBSetter();
        assertEquals(8, testSet.getPosX());
        assertEquals(8, testSet.getPosY());
        assertEquals(3, testMB1.getPosX());
        assertEquals(3, testMB1.getPosY());
        assertEquals(9, testOH1.getPosX());
        assertEquals(3, testOH1.getPosY());
        assertEquals(2, testOP.getPosX());
        assertEquals(11, testOP.getPosY());
        assertEquals(6, testMB2.getPosX());
        assertEquals(11, testMB2.getPosY());
        assertEquals(10, testOH2.getPosX());
        assertEquals(11, testOH2.getPosY());

        testTeam.changeRotation();
        testTeam.defendBSetter();
        assertEquals(8, testSet.getPosX());
        assertEquals(8, testSet.getPosY());
        assertEquals(3, testMB1.getPosX());
        assertEquals(3, testMB1.getPosY());
        assertEquals(10, testOH1.getPosX());
        assertEquals(11, testOH1.getPosY());
        assertEquals(2, testOP.getPosX());
        assertEquals(11, testOP.getPosY());
        assertEquals(6, testMB2.getPosX());
        assertEquals(11, testMB2.getPosY());
        assertEquals(9, testOH2.getPosX());
        assertEquals(3, testOH2.getPosY());
    }

    @Test
    public void testAttackFSetter() {
        testTeam.changeRotation();
        testTeam.changeRotation();
        testTeam.changeRotation();

        testTeam.attackFSetter();
        assertEquals(8, testSet.getPosX());
        assertEquals(10, testSet.getPosY());
        assertEquals(6, testMB1.getPosX());
        assertEquals(11, testMB1.getPosY());
        assertEquals(12, testOH1.getPosX());
        assertEquals(11, testOH1.getPosY());
        assertEquals(3, testOP.getPosX());
        assertEquals(3, testOP.getPosY());
        assertEquals(6, testMB2.getPosX());
        assertEquals(5, testMB2.getPosY());
        assertEquals(10, testOH2.getPosX());
        assertEquals(3, testOH2.getPosY());

        testTeam.changeRotation();
        testTeam.attackFSetter();
        assertEquals(8, testSet.getPosX());
        assertEquals(10, testSet.getPosY());
        assertEquals(6, testMB1.getPosX());
        assertEquals(11, testMB1.getPosY());
        assertEquals(10, testOH1.getPosX());
        assertEquals(3, testOH1.getPosY());
        assertEquals(3, testOP.getPosX());
        assertEquals(3, testOP.getPosY());
        assertEquals(6, testMB2.getPosX());
        assertEquals(5, testMB2.getPosY());
        assertEquals(12, testOH2.getPosX());
        assertEquals(11, testOH2.getPosY());



    }

    @Test
    public void testAttackBSetter(){

        testTeam.attackBSetter();
        assertEquals(4, testSet.getPosX());
        assertEquals(8, testSet.getPosY());
        assertEquals(3, testMB1.getPosX());
        assertEquals(3, testMB1.getPosY());
        assertEquals(9, testOH1.getPosX());
        assertEquals(3, testOH1.getPosY());
        assertEquals(0, testOP.getPosX());
        assertEquals(11, testOP.getPosY());
        assertEquals(6, testMB2.getPosX());
        assertEquals(11, testMB2.getPosY());
        assertEquals(12, testOH2.getPosX());
        assertEquals(11, testOH2.getPosY());

        testTeam.changeRotation();
        testTeam.attackBSetter();
        assertEquals(4, testSet.getPosX());
        assertEquals(8, testSet.getPosY());
        assertEquals(3, testMB1.getPosX());
        assertEquals(3, testMB1.getPosY());
        assertEquals(12, testOH1.getPosX());
        assertEquals(11, testOH1.getPosY());
        assertEquals(0, testOP.getPosX());
        assertEquals(11, testOP.getPosY());
        assertEquals(6, testMB2.getPosX());
        assertEquals(11, testMB2.getPosY());
        assertEquals(9, testOH2.getPosX());
        assertEquals(3, testOH2.getPosY());

    }

    @Test
    public void testChangeRotation() {
        testTeam.changeRotation();
        assertEquals(2 ,testSet.getRotation());
        assertEquals(3, testMB1.getRotation());
        assertEquals(4, testOH1.getRotation());
        assertEquals(5, testOP.getRotation());
        assertEquals(6, testMB2.getRotation());
        assertEquals(1, testOH2.getRotation());
        testTeam.changeRotation();
        assertEquals(3 ,testSet.getRotation());
        assertEquals(4, testMB1.getRotation());
        assertEquals(5, testOH1.getRotation());
        assertEquals(6, testOP.getRotation());
        assertEquals(1, testMB2.getRotation());
        assertEquals(2, testOH2.getRotation());
    }

    @Test
    public void testArrangeMBOH() {

        assertEquals(2, testMB1.getRotation());
        assertEquals(3, testOH1.getRotation());

        assertEquals(5, testMB2.getRotation());
        assertEquals(6, testOH2.getRotation());
    }

    @Test
    public void testChangeStarter() {
        testTeam.changeStarters(9, 2);
        assertEquals(testSet2, testTeam.getStartingPlayer(2));

    }

    @Test
    public void testAddPlayer() {
        testTeam.addPlayer(testSet2);
        assertEquals(9, testTeam.getRoster().size() );
    }

    @Test
    public void testAddStartingPlayer() {
        testTeam.addStartingPlayer(testSet2);
        assertEquals(7, testTeam.getStarters().size() );
    }

    @Test
    public void testGetRoster() {
        assertEquals(8, testTeam.getRoster().size() );
        testTeam.addPlayer(testSet2);
        assertEquals(9, testTeam.getRoster().size() );
    }

    @Test
    public void testGetStarters() {
        assertEquals(6, testTeam.getStarters().size() );
        testTeam.addStartingPlayer(testSet2);
        assertEquals(7, testTeam.getStarters().size() );
    }

    @Test
    public void testGetStartingPlayer() {
        assertEquals(testSet, testTeam.getStartingPlayer(9));
        assertEquals(testOP, testTeam.getStartingPlayer(1));
    }

    @Test
    public void testGetPlayer() {
        assertEquals(testSet, testTeam.getPlayer(9));
        assertEquals(testSet2, testTeam.getPlayer(2));
        assertEquals(testMB3, testTeam.getPlayer(7));
    }

    @Test
    public void testIsSetterBack() {
        assertTrue(testTeam.isSetterBack());
        testTeam.changeRotation();
        testTeam.changeRotation();
        testTeam.changeRotation();
        assertFalse(testTeam.isSetterBack());
    }

    @Test
    public void testSet() {
        testTeam.set(0, mikasa);
        assertEquals(12, mikasa.getMoveToXPos());
        assertEquals(11, mikasa.getMoveToYPos());

        testTeam.set(1, mikasa);
        assertEquals(6, mikasa.getMoveToXPos());
        assertEquals(11, mikasa.getMoveToYPos());

        testTeam.set(2, mikasa);
        assertEquals(0, mikasa.getMoveToXPos());
        assertEquals(11, mikasa.getMoveToYPos());
    }

    @Test
    public void testAttack() {
        testTeam.attack(0, 0, mikasa);
        assertEquals(12, mikasa.getMoveToXPos());
        assertEquals(21, mikasa.getMoveToYPos());

        testTeam.attack(0, 1, mikasa);
        assertEquals(6, mikasa.getMoveToXPos());
        assertEquals(22, mikasa.getMoveToYPos());

        testTeam.attack(1, 0, mikasa);
        assertEquals(6, mikasa.getMoveToXPos());
        assertEquals(18, mikasa.getMoveToYPos());

        testTeam.attack(1, 1, mikasa);
        assertEquals(2, mikasa.getMoveToXPos());
        assertEquals(19, mikasa.getMoveToYPos());

        testTeam.attack(1, 2, mikasa);
        assertEquals(10, mikasa.getMoveToXPos());
        assertEquals(19, mikasa.getMoveToYPos());

        testTeam.attack(2, 0, mikasa);
        assertEquals(0, mikasa.getMoveToXPos());
        assertEquals(20, mikasa.getMoveToYPos());

        testTeam.attack(2, 1, mikasa);
        assertEquals(6, mikasa.getMoveToXPos());
        assertEquals(21, mikasa.getMoveToYPos());

        testTeam.attack(3, 1, mikasa);
        assertEquals(6, mikasa.getMoveToXPos());
        assertEquals(18, mikasa.getMoveToYPos());

    }

    @Test
    public void testGetChance() {
        assertEquals(10, testTeam.getChance());
        testTeam.setChance(3);
        assertEquals(3, testTeam.getChance());
    }

    @Test
    public void testSetChance() {
        assertEquals(10, testTeam.getChance());
        testTeam.setChance(3);
        assertEquals(3, testTeam.getChance());
    }
}
