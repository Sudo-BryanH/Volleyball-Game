package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GameTest {

    Team testMyTeam;
    EnemyTeam testETeam;
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
    Game testGame2;
    Ball mikasa;


    @BeforeEach
    public void setUp() {
        testEMB1 = new MiddleBlockers(10, 0);
        testEMB2 = new MiddleBlockers(11, 0);
        testEOH1 = new OutsideHitter(3, 0);
        testEOH2 = new OutsideHitter(5, 0);
        testESet = new Setters(9, 0);
        testEOP = new OppositeHitter(1, 0);
        testEMB3 = new MiddleBlockers(7, 0);
        testESet2 = new Setters(2, 0);
        testESet.setRotation(1);
        testEOP.setRotation(4);
        testEMB1.setRotation(1);
        testEMB2.setRotation(5);
        testEOH1.setRotation(3);
        testEOH2.setRotation(6);
        testESet.setRotation(1);
        testEOP.setRotation(4);

        testETeam = new EnemyTeam("testETeam", testESet, testEMB1, testEMB2,
                testEOH1, testEOH2, testEOP);

        testETeam.arrangeMbOh();

        testMB1 = new MiddleBlockers(10, 1);
        testMB2 = new MiddleBlockers(11, 2);
        testOH1 = new OutsideHitter(3, 1);
        testOH2 = new OutsideHitter(5, 1);
        testSet = new Setters(9, 1);
        testOP = new OppositeHitter(1, 1);
        testMB3 = new MiddleBlockers(7, 1);
        testSet2 = new Setters(2, 1);
        testMB1.setRotation(1);
        testMB2.setRotation(5);
        testOH1.setRotation(3);
        testOH2.setRotation(6);
        testSet.setRotation(1);
        testOP.setRotation(4);

        testMyTeam = new MyTeam("testTeam", testSet, testMB1, testMB2,
                testOH1, testOH2, testOP);

        testMyTeam.arrangeMbOh();

        testGame = new Game(testMyTeam, testETeam);

        mikasa = new Ball();
        testGame.setBall(mikasa);

        testGame2 = new Game();
    }

    @Test
    public void testConstructor() {
        assertEquals(0, testGame.getTurnNum());
        assertEquals(6, testMyTeam.getRoster().size());
        assertEquals(6, testMyTeam.getStarters().size());
        assertEquals(6, testETeam.getRoster().size());
        assertEquals(6, testETeam.getStarters().size());
        assertEquals(mikasa, testGame.getBall());
    }

    @Test
    public void testConstructor2() {
        assertEquals(0, testGame2.getTurnNum());
    }

    @Test
    public void testMyScore() {
        testGame.myScore();
        assertEquals(1, testGame.getMyScore());
        testGame.myScore();
        assertEquals(2, testGame.getMyScore());
    }

    @Test
    public void testEnemyScore() {
        testGame.enemyScore();
        assertEquals(1, testGame.getEnemyScore());
        testGame.enemyScore();
        assertEquals(2, testGame.getEnemyScore());
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

        assertTrue(testGame.checkReceive(6 * 30, 19 * 30 + 100, backrow));
        assertTrue(testGame.checkReceive(6 * 30, 20 * 30 + 100, backrow));
        assertTrue(testGame.checkReceive(6 * 30, 18 * 30 + 100, backrow));
        assertTrue(testGame.checkReceive(5 * 30, 19 * 30 + 100, backrow));
        assertTrue(testGame.checkReceive(7 * 30, 20 * 30 + 100, backrow));
        assertFalse(testGame.checkReceive(8 * 30, 21 * 30 + 100, backrow));
        assertFalse(testGame.checkReceive(7 * 30, 22 * 30 + 100, backrow));
        assertFalse(testGame.checkReceive(8 * 30, 22 * 30 + 100, backrow));


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
        assertEquals(testMyTeam.getName() + "| " + testGame.getMyScore() + "| |" + testGame.getEnemyScore()
                + " |" + testETeam.getName(), testGame.getScore());

    }

    @Test
    public void testGetTurnNum() {
        testGame.flipTurnNum();
        assertEquals(1, testGame.getTurnNum());
        testGame.flipTurnNum();
        assertEquals(0, testGame.getTurnNum());

    }

    @Test
    public void testSetTurnNum() {
        testGame.setTurnNum(1);
        assertEquals(1, testGame.getTurnNum());
    }


    @Test
    public void testGetEnemyTeam() {
        assertEquals(testETeam, testGame.getEnemyTeam());

    }

    @Test
    public void testGetMyTeam() {
        assertEquals(testMyTeam, testGame.getMyTeam());

    }

    @Test
    public void testGetMyScore() {
        assertEquals(0, testGame.getMyScore());
    }

    @Test
    public void testGetEnemyScore() {
        assertEquals(0, testGame.getEnemyScore());
    }

    @Test
    public void testSetEnemyTeam() {
        testGame2.setEnemyTeam(testETeam);
        assertEquals(testETeam, testGame2.getEnemyTeam());
    }

    @Test
    public void testSetMyTeam() {
        testGame2.setMyTeam(testMyTeam);
        assertEquals(testMyTeam, testGame2.getMyTeam());
    }

    @Test
    public void testSetMyScore() {
        testGame2.setMyScore(0);
        assertEquals(0, testGame2.getMyScore());
    }

    @Test
    public void testSetEnemyScore() {
        testGame2.setEnemyScore(0);
        assertEquals(0, testGame2.getEnemyScore());
    }


    @Test
    public void testGetSetGameState() {

        testGame.setGameState("A", "D");
        assertEquals("A", testGame.getGameState0());
        assertEquals("D", testGame.getGameState1());

        testGame.setGameState("D", "A");
        assertEquals("D", testGame.getGameState0());
        assertEquals("A", testGame.getGameState1());

    }


    @Test
    public void testGetSetAttackPlayer() {
        Players p = new OutsideHitter(3, 1);
        Players q = new MiddleBlockers(4, 1);
        assertNull(testGame.getAttackPlayer());
        testGame.setAttackPlayer(p);
        assertEquals(p, testGame.getAttackPlayer());
        testGame.setAttackPlayer(q);
        assertEquals(q, testGame.getAttackPlayer());

    }

    @Test
    public void testMakeServe() {
        testGame.setBall(mikasa);
        testGame.setGameState("S", "A");
        testGame.makeServe(0);
        assertEquals(90, mikasa.getMoveToXPos());
        assertEquals(21 * 30 + 100, mikasa.getMoveToYPos());
        testGame.makeServe(1);
        assertEquals(270, mikasa.getMoveToXPos());
        assertEquals(21 * 30 + 100, mikasa.getMoveToYPos());
        testGame.setGameState("A", "S");
        testGame.makeServe(0);
        assertEquals(90, mikasa.getMoveToXPos());
        assertEquals(3 * 30 + 100, mikasa.getMoveToYPos());
        testGame.makeServe(1);
        assertEquals(270, mikasa.getMoveToXPos());
        assertEquals(3 * 30 + 100, mikasa.getMoveToYPos());


    }

    @Test
    public void testReceive() {
        mikasa.directX(3);
        mikasa.directY(3);

        testGame.setGameState("D", "A");
        testGame.receive();
        assertEquals(120, mikasa.getMoveToXPos());
        assertEquals(400, mikasa.getMoveToYPos());

        testGame.setGameState("A", "D");
        testGame.receive();
        assertEquals(8 * 30 , mikasa.getMoveToXPos());
        assertEquals(520, mikasa.getMoveToYPos());


    }

    @Test
    public void testEndRally() {
        testGame.endRally(0);
        assertEquals(2, testESet.getRotation());
        assertEquals(1, testGame.getEnemyScore());

        testGame.endRally(1);
        assertEquals(2, testSet.getRotation());
        assertEquals(1, testGame.getMyScore());

    }

    @Test
    public void testChooseAttack() {
        assertEquals("Please click a hitter in green and click a displayed target",
                testGame.chooseAttack(1, 2));

        assertEquals(testGame.chooseAttack(92, 500), "Click a hitter in green.");

        assertEquals("You've chosen to set to the left. \nClick a green target to attack."
                , testGame.chooseAttack(40, 500));

        assertEquals("You've chosen to set to the middle. \nClick a green target to attack."
                , testGame.chooseAttack(180, 500));

        assertEquals("You've chosen to set to the right. \nClick a green target to attack."
                , testGame.chooseAttack(320, 500));


        assertEquals(testOP, testGame.getAttackPlayer());

        assertEquals("Player #1 will spike to the (6.0 , 3.0). \n Press next to continue. ",
                testGame.chooseAttack(6 * 30 , 3 * 30 + 100));
    }

    @Test
    public void testGetSetAttackPoint() {
        Point p = new Point(40, 50);
        assertEquals(null, testGame.getAttackPoint());
        testGame.setAttackPoint(p);
        assertEquals(p, testGame.getAttackPoint());
    }

    @Test
    public void testChooseDefense() {
        assertNull(testGame.getSelectDefensive());
        assertEquals(null, testGame.chooseDefense(90, 110));
        testMyTeam.defendBSetter();
        assertEquals("Player #10 selected. Click anywhere to move this player. ",
                testGame.chooseDefense(180, 460));
        assertEquals(testMB1, testGame.getSelectDefensive());
        assertEquals("Player #10 has been moved to (600, 600)", testGame.chooseDefense(600, 600));
        assertEquals(600, testMB1.getNewPosX());
        assertEquals(580, testMB1.getNewPosY());

    }

    @Test
    public void testGameOver() {
        assertFalse(testGame.gameOver());

        testGame.myScore();
        assertFalse(testGame.gameOver());
        testGame.enemyScore();
        assertFalse(testGame.gameOver());

        for (int i = 0; i < 14; i++) {
            testGame.myScore();
        }

        assertTrue(testGame.gameOver());

        for (int i = 0; i < 13; i++) {
            testGame.enemyScore();
        }
        assertFalse(testGame.gameOver());
    }

    @Test
    public void testInstantiateGame() {
        JsonReader reader = new JsonReader("./data/sampleGameData1to12.json", testGame);

        try {
            GameData gd = reader.read();
            fail("File should not have been found");
        } catch (IOException e) {

        }

        reader = new JsonReader("./data/sampleGameData1to1.json", testGame);

        try {
            GameData gd = reader.read();
            testMyTeam = gd.getMyTeam();
            checkMyTeam("nothing", testMyTeam);
            testETeam = gd.getEnemyTeam();
            checkEnemyTeam("weak team", testETeam, 5);
            assertEquals(1, gd.getMyScore());
            assertEquals(1, gd.getEnemyScore());

            checkPlayers(1, 2, "Setter", 0, testMyTeam.getRoster().get(0));
            checkPlayers(2, 3, "Middle Blocker", 0, testMyTeam.getRoster().get(1));
            checkPlayers(3, 4, "Outside Hitter", 0, testMyTeam.getRoster().get(2));
            checkPlayers(4, 5, "Opposite Hitter", 0, testMyTeam.getRoster().get(3));
            checkPlayers(5, 6, "Middle Blocker", 0, testMyTeam.getRoster().get(4));
            checkPlayers(6, 1, "Outside Hitter", 0, testMyTeam.getRoster().get(5));

            checkPlayers(1, 5, "Setter", 1, testETeam.getRoster().get(0));
            checkPlayers(2, 6, "Middle Blocker", 1, testETeam.getRoster().get(1));
            checkPlayers(3, 1, "Outside Hitter", 1, testETeam.getRoster().get(2));
            checkPlayers(4, 2, "Opposite Hitter", 1, testETeam.getRoster().get(3));
            checkPlayers(5, 3, "Middle Blocker", 1, testETeam.getRoster().get(4));
            checkPlayers(6, 4, "Outside Hitter", 1, testETeam.getRoster().get(5));


        } catch (IOException e) {
            fail("Could not read from rile");
        }
    }


    protected void checkPlayers(int num, int rotation, String position, int side, Players p){
        assertEquals(num, p.getNum());
        assertEquals(rotation, p.getRotation());
        assertEquals(position, p.getPlayingPosition());
        assertEquals(side, p.getSide());

    }

    protected void checkMyTeam(String name, Team t){
        assertEquals(name, t.getName());

    }

    protected void checkEnemyTeam(String name, EnemyTeam eTeam, int chance){
        assertEquals(name, eTeam.getName());
        assertEquals(chance, eTeam.getChance());


    }


}