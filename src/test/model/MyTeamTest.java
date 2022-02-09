package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;



public class MyTeamTest {

    Team testTeam;
    Players testSet;
    Players testMB1;
    Players testMB2;
    Players testOH1;
    Players testOH2;
    Players testOP;
    Players testMB3;
    Players testSet2;

    List<Players> roster = new ArrayList<>();
    List<Players> starters = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        testMB1 = new MiddleBlockers(10, 1);
        testMB2 = new MiddleBlockers(11, 1);
        testOH1 = new OutsideHitter(3, 1);
        testOH2 = new OutsideHitter(5, 1);
        testSet = new Setters(9, 1);
        testOP = new OppositeHitter(1, 1);
        testMB3 = new MiddleBlockers(7, 1);
        testSet2 = new Setters(2, 1);

        testTeam = new MyTeam("testTeam", testSet, testMB1, testMB2,
                testOH1, testOH2, testOP);


        testTeam.addPlayer(testSet2);
        testTeam.addPlayer(testMB3);

        testTeam.arrangeMBOH();
    }

    @Test
    public void testConstructor() {
        assertEquals(1 ,testSet.getRotation());
        assertEquals(2, testMB1.getRotation());
        assertEquals(3, testOH1.getRotation());
        assertEquals(4, testOP.getRotation());
        assertEquals(5, testMB2.getRotation());
        assertEquals(6, testOH2.getRotation());
        assertEquals(6, testTeam.getStarters().size());
        assertEquals(8, testTeam.getRoster().size());

    }

    @Test
    public void testStartPosServe(){
        testTeam.startPosServe();
        assertEquals(12, testSet.getPosX());
        assertEquals(24, testSet.getPosY());
        assertEquals(6, testMB1.getPosX());
        assertEquals(20, testMB1.getPosY());
        assertEquals(2, testOH1.getPosX());
        assertEquals(19, testOH1.getPosY());
        assertEquals(2, testOP.getPosX());
        assertEquals(14, testOP.getPosY());
        assertEquals(6, testMB2.getPosX());
        assertEquals(14, testMB2.getPosY());
        assertEquals(10, testOH2.getPosX());
        assertEquals(14, testOH2.getPosY());

        testTeam.changeRotation();
        assertEquals(1, testOH2.getRotation());
        assertEquals(2, testSet.getRotation());
        assertEquals(3, testMB1.getRotation());
        assertEquals(4, testOH1.getRotation());
        assertEquals(5, testOP.getRotation());
        assertEquals(6, testMB2.getRotation());

        testTeam.startPosServe();
        assertEquals(12, testOH2.getPosX());
        assertEquals(24, testOH2.getPosY());
        assertEquals(6, testSet.getPosX());
        assertEquals(20, testSet.getPosY());
        assertEquals(2, testMB1.getPosX());
        assertEquals(19, testMB1.getPosY());
        assertEquals(2, testOH1.getPosX());
        assertEquals(14, testOH1.getPosY());
        assertEquals(6, testOP.getPosX());
        assertEquals(14, testOP.getPosY());
        assertEquals(10, testMB2.getPosX());
        assertEquals(14, testMB2.getPosY());
    }

    @Test
    public void testStartNoServe(){
        testTeam.startPosNoServe();
        assertEquals(10, testSet.getPosX());
        assertEquals(19, testSet.getPosY());
        assertEquals(6, testMB1.getPosX());
        assertEquals(20, testMB1.getPosY());
        assertEquals(2, testOH1.getPosX());
        assertEquals(19, testOH1.getPosY());
        assertEquals(2, testOP.getPosX());
        assertEquals(14, testOP.getPosY());
        assertEquals(6, testMB2.getPosX());
        assertEquals(14, testMB2.getPosY());
        assertEquals(10, testOH2.getPosX());
        assertEquals(14, testOH2.getPosY());

        testTeam.changeRotation();
        testTeam.startPosNoServe();
        assertEquals(10, testOH2.getPosX());
        assertEquals(19, testOH2.getPosY());
        assertEquals(6, testSet.getPosX());
        assertEquals(20, testSet.getPosY());
        assertEquals(2, testMB1.getPosX());
        assertEquals(19, testMB1.getPosY());
        assertEquals(2, testOH1.getPosX());
        assertEquals(14, testOH1.getPosY());
        assertEquals(6, testOP.getPosX());
        assertEquals(14, testOP.getPosY());
        assertEquals(10, testMB2.getPosX());
        assertEquals(14, testMB2.getPosY());
    }

    @Test
    public void testDefendFSetter(){
        testTeam.changeRotation();
        testTeam.changeRotation();
        testTeam.changeRotation();
        testTeam.defendFSetter();
        assertEquals(4 ,testSet.getRotation());
        assertEquals(5, testMB1.getRotation());
        assertEquals(6, testOH1.getRotation());
        assertEquals(1, testOP.getRotation());
        assertEquals(2, testMB2.getRotation());
        assertEquals(3, testOH2.getRotation());

        assertEquals(11, testSet.getPosX());
        assertEquals(13, testSet.getPosY());
        assertEquals(6, testMB1.getPosX());
        assertEquals(13, testMB1.getPosY());
        assertEquals(2, testOH1.getPosX());
        assertEquals(13, testOH1.getPosY());
        assertEquals(10, testOP.getPosX());
        assertEquals(21, testOP.getPosY());
        assertEquals(6, testMB2.getPosX());
        assertEquals(19, testMB2.getPosY());
        assertEquals(2, testOH2.getPosX());
        assertEquals(21, testOH2.getPosY());

        testTeam.changeRotation();
        testTeam.defendFSetter();
        assertEquals(11, testSet.getPosX());
        assertEquals(13, testSet.getPosY());
        assertEquals(6, testMB1.getPosX());
        assertEquals(13, testMB1.getPosY());
        assertEquals(2, testOH1.getPosX());
        assertEquals(21, testOH1.getPosY());
        assertEquals(10, testOP.getPosX());
        assertEquals(21, testOP.getPosY());
        assertEquals(6, testMB2.getPosX());
        assertEquals(19, testMB2.getPosY());
        assertEquals(2, testOH2.getPosX());
        assertEquals(13, testOH2.getPosY());

        testTeam.changeRotation();
        testTeam.defendFSetter();
        assertEquals(11, testSet.getPosX());
        assertEquals(13, testSet.getPosY());
        assertEquals(6, testMB1.getPosX());
        assertEquals(19, testMB1.getPosY());
        assertEquals(2, testOH1.getPosX());
        assertEquals(21, testOH1.getPosY());
        assertEquals(10, testOP.getPosX());
        assertEquals(21, testOP.getPosY());
        assertEquals(6, testMB2.getPosX());
        assertEquals(13, testMB2.getPosY());
        assertEquals(2, testOH2.getPosX());
        assertEquals(13, testOH2.getPosY());
    }

    @Test
    public void testDefendBSetter(){
        testTeam.defendBSetter();
        assertEquals(8, testSet.getPosX());
        assertEquals(16, testSet.getPosY());
        assertEquals(9, testMB1.getPosX());
        assertEquals(21, testMB1.getPosY());
        assertEquals(3, testOH1.getPosX());
        assertEquals(21, testOH1.getPosY());
        assertEquals(10, testOP.getPosX());
        assertEquals(13, testOP.getPosY());
        assertEquals(6, testMB2.getPosX());
        assertEquals(13, testMB2.getPosY());
        assertEquals(2, testOH2.getPosX());
        assertEquals(13, testOH2.getPosY());

        testTeam.changeRotation();
        testTeam.defendBSetter();
        assertEquals(8, testSet.getPosX());
        assertEquals(16, testSet.getPosY());
        assertEquals(9, testMB1.getPosX());
        assertEquals(21, testMB1.getPosY());
        assertEquals(2, testOH1.getPosX());
        assertEquals(13, testOH1.getPosY());
        assertEquals(10, testOP.getPosX());
        assertEquals(13, testOP.getPosY());
        assertEquals(6, testMB2.getPosX());
        assertEquals(13, testMB2.getPosY());
        assertEquals(3, testOH2.getPosX());
        assertEquals(21, testOH2.getPosY());
    }

    @Test
    public void testAttackFSetter() {
        testTeam.changeRotation();
        testTeam.changeRotation();
        testTeam.changeRotation();
        testTeam.attackFSetter();


        assertEquals(8, testSet.getPosX());
        assertEquals(14, testSet.getPosY());
        assertEquals(6, testMB1.getPosX());
        assertEquals(13, testMB1.getPosY());
        assertEquals(0, testOH1.getPosX());
        assertEquals(13, testOH1.getPosY());
        assertEquals(9, testOP.getPosX());
        assertEquals(21, testOP.getPosY());
        assertEquals(6, testMB2.getPosX());
        assertEquals(19, testMB2.getPosY());
        assertEquals(2, testOH2.getPosX());
        assertEquals(21, testOH2.getPosY());

        testTeam.changeRotation();
        testTeam.attackFSetter();

        assertEquals(8, testSet.getPosX());
        assertEquals(14, testSet.getPosY());
        assertEquals(6, testMB1.getPosX());
        assertEquals(13, testMB1.getPosY());
        assertEquals(2, testOH1.getPosX());
        assertEquals(21, testOH1.getPosY());
        assertEquals(9, testOP.getPosX());
        assertEquals(21, testOP.getPosY());
        assertEquals(6, testMB2.getPosX());
        assertEquals(19, testMB2.getPosY());
        assertEquals(0, testOH2.getPosX());
        assertEquals(13, testOH2.getPosY());

        testTeam.changeRotation();
        testTeam.attackFSetter();

        assertEquals(8, testSet.getPosX());
        assertEquals(14, testSet.getPosY());
        assertEquals(6, testMB1.getPosX());
        assertEquals(19, testMB1.getPosY());
        assertEquals(2, testOH1.getPosX());
        assertEquals(21, testOH1.getPosY());
        assertEquals(9, testOP.getPosX());
        assertEquals(21, testOP.getPosY());
        assertEquals(6, testMB2.getPosX());
        assertEquals(13, testMB2.getPosY());
        assertEquals(0, testOH2.getPosX());
        assertEquals(13, testOH2.getPosY());

    }

    @Test
    public void testAttackBSetter(){
        testTeam.attackBSetter();
        assertEquals(8, testSet.getPosX());
        assertEquals(14, testSet.getPosY());
        assertEquals(9, testMB1.getPosX());
        assertEquals(21, testMB1.getPosY());
        assertEquals(3, testOH1.getPosX());
        assertEquals(21, testOH1.getPosY());
        assertEquals(12, testOP.getPosX());
        assertEquals(13, testOP.getPosY());
        assertEquals(6, testMB2.getPosX());
        assertEquals(13, testMB2.getPosY());
        assertEquals(0, testOH2.getPosX());
        assertEquals(13, testOH2.getPosY());

        testTeam.changeRotation();
        testTeam.attackBSetter();

        assertEquals(8, testSet.getPosX());
        assertEquals(14, testSet.getPosY());
        assertEquals(9, testMB1.getPosX());
        assertEquals(21, testMB1.getPosY());
        assertEquals(0, testOH1.getPosX());
        assertEquals(13, testOH1.getPosY());
        assertEquals(12, testOP.getPosX());
        assertEquals(13, testOP.getPosY());
        assertEquals(6, testMB2.getPosX());
        assertEquals(13, testMB2.getPosY());
        assertEquals(3, testOH2.getPosX());
        assertEquals(21, testOH2.getPosY());

    }

    @Test
    public void testChangeRotation() {
        testTeam.changeRotation();
        assertEquals(2 ,testSet.getRotation());
        assertEquals(3, testMB1.getRotation());
        assertEquals(4, testOH1.getRotation());
        assertEquals(6, testMB2.getRotation());
        assertEquals(1, testOH2.getRotation());
        assertEquals(5, testOP.getRotation());

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
        testTeam.changeRotation();
        testTeam.changeStarters(9, 2);
        assertEquals(testSet2, testTeam.getStartingPlayer(2));
        assertEquals(6, testTeam.getStarters().size() );
        assertEquals(2, testSet2.getRotation());


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



}


