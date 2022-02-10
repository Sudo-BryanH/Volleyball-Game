package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GameTest {

    Team testMyTeam;
    Team testETeam;
    Players testESet;
    Players testEMB1;
    Players testEMB2;
    Players testEOH1;
    Players testEOH2;
    Players testEOP;
    Players testEMB3;
    Players testESet2;
    Players testMB1;
    Players testMB2;
    Players testOH1;
    Players testOH2;
    Players testOP;
    Players testMB3;
    Players testSet2;
    Players testSet;
    Game testGame;
    Ball mikasa;


    @BeforeEach
    public void setUp() {
        testEMB1 = new MiddleBlockers(10, 1);
        testEMB2 = new MiddleBlockers(11, 1);
        testEOH1 = new OutsideHitter(3, 1);
        testEOH2 = new OutsideHitter(5, 1);
        testESet = new Setters(9, 1);
        testEOP = new OppositeHitter(1, 1);
        testEMB3 = new MiddleBlockers(7, 1);
        testESet2 = new Setters(2, 1);

        testETeam = new EnemyTeam("testETeam", testESet, testEMB1, testEMB2,
                testEOH1, testEOH2, testEOP);

        testETeam.arrangeMbOh();

        testMB1 = new MiddleBlockers(10, 0);
        testMB2 = new MiddleBlockers(11, 0);
        testOH1 = new OutsideHitter(3, 0);
        testOH2 = new OutsideHitter(5, 0);
        testSet = new Setters(9, 0);
        testOP = new OppositeHitter(1, 0);
        testMB3 = new MiddleBlockers(7, 0);
        testSet2 = new Setters(2, 0);

        testMyTeam = new MyTeam("testTeam", testSet, testMB1, testMB2,
                testOH1, testOH2, testOP);

        testMyTeam.arrangeMbOh();

        testGame = new Game(testMyTeam, testETeam);

        mikasa = new Ball();
    }

    @Test
    public void testConstructor() {
        assertEquals("0 : 0", testGame.getScore());
        assertEquals(0, testGame.getTurnNum());
        assertEquals(6, testMyTeam.getRoster().size());
        assertEquals(6, testMyTeam.getStarters().size());
        assertEquals(6, testETeam.getRoster().size());
        assertEquals(6, testETeam.getStarters().size());
    }

    @Test
    public void testMyScore() {
        testGame.myScore();
        assertEquals("1 : 0", testGame.getScore());
        testGame.myScore();
        assertEquals("2 : 0", testGame.getScore());
    }

    @Test
    public void testEnemyScore() {
        testGame.enemyScore();
        assertEquals("0 : 1", testGame.getScore());
        testGame.enemyScore();
        assertEquals("0 : 2", testGame.getScore());
    }

    @Test
    public void testIsGameOver() {
        for (int i = 0; i <= 15; i++) {
            testGame.myScore();
        }
        for (int i = 0; i <= 13; i++) {
            testGame.enemyScore();
        }
        assertTrue(testGame.isGameOver());

        testGame.enemyScore();
        assertFalse(testGame.isGameOver());

        testGame.enemyScore();
        assertFalse(testGame.isGameOver());

        testGame.enemyScore();
        testGame.enemyScore();
        assertTrue(testGame.isGameOver());

    }

    @Test
    public void testCheckReceive() {

        testMyTeam.changeRotation();
        testMyTeam.changeRotation();
        testMyTeam.changeRotation();
        testMyTeam.defendFSetter();

        List<Players> backrow = new ArrayList<>();
        backrow.add(testOH2);
        backrow.add(testMB2);
        backrow.add(testOP);

        assertTrue(testGame.checkReceive(6, 19, backrow));
        assertTrue(testGame.checkReceive(6, 20, backrow));
        assertTrue(testGame.checkReceive(6, 18, backrow));
        assertTrue(testGame.checkReceive(5, 19, backrow));
        assertTrue(testGame.checkReceive(7, 20, backrow));
        assertFalse(testGame.checkReceive(8, 21, backrow));
        assertFalse(testGame.checkReceive(7, 22, backrow));
        assertFalse(testGame.checkReceive(8, 22, backrow));


    }


    @Test
    public void testFlipTurnNum() {

        testGame.flipTurnNum();
        assertEquals(1, testGame.getTurnNum());
        testGame.flipTurnNum();
        assertEquals(0, testGame.getTurnNum());
    }

    @Test
    public void testGetScore() {
        assertEquals("0 : 0", testGame.getScore());
        testGame.myScore();
        assertEquals("1 : 0", testGame.getScore());
    }

    @Test
    public void testGetTurnNum() {
        testGame.flipTurnNum();
        assertEquals(1, testGame.getTurnNum());
        testGame.flipTurnNum();
        assertEquals(0, testGame.getTurnNum());
    }



}