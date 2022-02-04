package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;



public class MyTeamTest {

    MyTeam testTeam;
    Setters testSet;
    MiddleBlockers testMB1;
    MiddleBlockers testMB2;
    OutsideHitter testOH1;
    OutsideHitter testOH2;
    OppositeHitter testOP;
    MiddleBlockers testMB3;
    Setters testSet2;

    @BeforeEach
    public void setUp() {
        testMB1 = new MiddleBlockers(10);
        testMB2 = new MiddleBlockers(11);
        testOH1 = new OutsideHitter(3);
        testOH2 = new OutsideHitter(5);
        testSet = new Setters(9);
        testOP = new OppositeHitter(1);
        testMB3 = new MiddleBlockers(7);
        testSet2 = new Setters(2);

        testTeam = new MyTeam("testTeam", testSet, testMB1, testMB2,
                testOH1, testOH2, testOP);

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

        assertEquals(8, testTeam.printRoster().size());
        assertEquals(6, testTeam.printStarters().size());
    }

    @Test
    public void testStartPosServe(){
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

        assertEquals(8, testSet.getPosX());
        assertEquals(16, testSet.getPosY());
        assertEquals(9, testMB1.getPosX());
        assertEquals(21, testMB1.getPosY());
        assertEquals(3, testOH1.getPosX());
        assertEquals(13, testOH1.getPosY());
        assertEquals(10, testOP.getPosX());
        assertEquals(13, testOP.getPosY());
        assertEquals(6, testMB2.getPosX());
        assertEquals(13, testMB2.getPosY());
        assertEquals(2, testOH2.getPosX());
        assertEquals(21, testOH2.getPosY());
    }

    @Test
    public void testAttackFSetter() {
        testTeam.changeRotation();
        testTeam.changeRotation();
        testTeam.changeRotation();


        assertEquals(8, testSet.getPosX());
        assertEquals(14, testSet.getPosY());
        assertEquals(6, testMB1.getPosX());
        assertEquals(13, testMB1.getPosY());
        assertEquals(0, testOH1.getPosX());
        assertEquals(13, testOH1.getPosY());
        assertEquals(10, testOP.getPosX());
        assertEquals(21, testOP.getPosY());
        assertEquals(6, testMB2.getPosX());
        assertEquals(19, testMB2.getPosY());
        assertEquals(2, testOH2.getPosX());
        assertEquals(21, testOH2.getPosY());

        testTeam.changeRotation();

        assertEquals(8, testSet.getPosX());
        assertEquals(14, testSet.getPosY());
        assertEquals(6, testMB1.getPosX());
        assertEquals(13, testMB1.getPosY());
        assertEquals(2, testOH1.getPosX());
        assertEquals(21, testOH1.getPosY());
        assertEquals(10, testOP.getPosX());
        assertEquals(21, testOP.getPosY());
        assertEquals(6, testMB2.getPosX());
        assertEquals(19, testMB2.getPosY());
        assertEquals(0, testOH2.getPosX());
        assertEquals(13, testOH2.getPosY());

        testTeam.changeRotation();

        assertEquals(8, testSet.getPosX());
        assertEquals(14, testSet.getPosY());
        assertEquals(6, testMB1.getPosX());
        assertEquals(19, testMB1.getPosY());
        assertEquals(2, testOH1.getPosX());
        assertEquals(21, testOH1.getPosY());
        assertEquals(10, testOP.getPosX());
        assertEquals(21, testOP.getPosY());
        assertEquals(6, testMB2.getPosX());
        assertEquals(13, testMB2.getPosY());
        assertEquals(0, testOH2.getPosX());
        assertEquals(13, testOH2.getPosY());

    }

    @Test
    public void testAttackBSetter(){
        assertEquals(8, testSet.getPosX());
        assertEquals(14, testSet.getPosY());
        assertEquals(3, testMB1.getPosX());
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

        assertEquals(8, testSet.getPosX());
        assertEquals(14, testSet.getPosY());
        assertEquals(3, testMB1.getPosX());
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
        assertEquals(testSet2, testTeam.getStarters(2));

    }
}

