package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EnemyTeamTest {

    Team testTeam;
    Players testSet;
    Players testMB1;
    Players testMB2;
    Players testOH1;
    Players testOH2;
    Players testOP;
    Players testMB3;
    Players testSet2;


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

        testTeam.startPosNoServe();
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
        testTeam.startPosNoServe();
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
        assertEquals(5, testMB2.getPosY());
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
        assertEquals(5, testMB2.getPosY());
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
}
