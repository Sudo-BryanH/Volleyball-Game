package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

public class SettersTest {

    Setters testPlayer;
    Ball mikasa;
    Setters testEPlayer;

    @BeforeEach
    public void setUp() {
        testPlayer = new Setters(9, 1);
        testEPlayer = new Setters(10, 0);
        mikasa = new Ball();

    }

    @Test
    public void testConstructor() {
        assertEquals(9, testPlayer.getNum());
        assertEquals(300, testPlayer.getPosX());
        assertEquals(460, testPlayer.getPosY());
        assertEquals(0, testPlayer.getRotation());
        assertEquals("S", testPlayer.getShortPos());

    }

    @Test
    public void testGetNewPosX() {
        assertEquals(0, testPlayer.getNewPosX());
        testPlayer.moveToX(4);
        assertEquals(120, testPlayer.getNewPosX());
    }

    @Test
    public void testGetNewPosY() {
        assertEquals(0, testPlayer.getNewPosY());
        testPlayer.moveToY(14);
        assertEquals(520, testPlayer.getNewPosY());
    }

    @Test
    public void testGetSetDirectPosXY() {
        testPlayer.directMoveX(12);
        testPlayer.directMoveY(24);
        assertEquals(360, testPlayer.getPosX());
        assertEquals(24 * 30 + 100, testPlayer.getPosY());
    }

    @Test
    public void testSpike() {
        testPlayer.spike(1, mikasa);
        assertEquals(6 * 30,mikasa.getMoveToXPos());
        assertEquals(6 * 30 + 100,mikasa.getMoveToYPos());
        assertEquals(1, testPlayer.numDump());

        testEPlayer.spike(1, mikasa);
        assertEquals(6 * 30,mikasa.getMoveToXPos());
        assertEquals(18  * 30 + 100,mikasa.getMoveToYPos());
        assertEquals(1, testPlayer.numDump());

    }


    @Test
    public void testServe() {
        testPlayer.serve(0, mikasa);
        assertEquals(3  * 30,mikasa.getMoveToXPos());
        assertEquals(3 * 30 + 100,mikasa.getMoveToYPos());
        testPlayer.serve(1, mikasa);
        assertEquals(9 * 30,mikasa.getMoveToXPos());
        assertEquals(3  * 30 + 100,mikasa.getMoveToYPos());

        testEPlayer.serve(0, mikasa);
        assertEquals(3 * 30,mikasa.getMoveToXPos());
        assertEquals(21  * 30 + 100,mikasa.getMoveToYPos());
        testEPlayer.serve(1, mikasa);
        assertEquals(9 * 30,mikasa.getMoveToXPos());
        assertEquals(21  * 30 + 100,mikasa.getMoveToYPos());

    }

    @Test
    public void testGetRotation() {
        testPlayer.setRotation(1);
        assertEquals(1, testPlayer.getRotation());
        testPlayer.setRotation(2);
        assertEquals(2, testPlayer.getRotation());
    }

    @Test
    public void testSetRotation() {
        testPlayer.setRotation(1);
        assertEquals(1, testPlayer.getRotation());
        testPlayer.setRotation(1);
        assertEquals(1, testPlayer.getRotation());
        testPlayer.setRotation(2);
        assertEquals(2, testPlayer.getRotation());
    }

    @Test
    public void testSet() {
        testPlayer.set(2, mikasa);
        assertEquals(11 * 30,mikasa.getMoveToXPos());
        assertEquals(13 * 30 + 100,mikasa.getMoveToYPos());

        testPlayer.set(0, mikasa);
        assertEquals(0,mikasa.getMoveToXPos());
        assertEquals(13  * 30 + 100,mikasa.getMoveToYPos());

        testPlayer.set(1, mikasa);
        assertEquals(6 * 30,mikasa.getMoveToXPos());
        assertEquals(13  * 30 + 100,mikasa.getMoveToYPos());

        testEPlayer.set(2, mikasa);
        assertEquals(0,mikasa.getMoveToXPos());
        assertEquals(11  * 30 + 100,mikasa.getMoveToYPos());

        testEPlayer.set(0, mikasa);
        assertEquals(11 * 30,mikasa.getMoveToXPos());
        assertEquals(11 * 30 + 100,mikasa.getMoveToYPos());

        testEPlayer.set(1, mikasa);
        assertEquals(6 * 30,mikasa.getMoveToXPos());
        assertEquals(11  * 30 + 100,mikasa.getMoveToYPos());
    }

    @Test
    public void testNumDump() {
        assertEquals(0, testPlayer.numDump());
        testPlayer.spike(1, mikasa);
        assertEquals(1, testPlayer.numDump());
    }

    @Test
    public void testCanDump() {
        assertEquals(0, testPlayer.numDump());
        assertTrue(testPlayer.canDump());
        testPlayer.spike(1, mikasa);
        assertEquals(1, testPlayer.numDump());
        assertTrue(testPlayer.canDump());
        testPlayer.spike(2, mikasa);
        assertEquals(2, testPlayer.numDump());
        assertFalse(testPlayer.canDump());
    }

    @Test
    public void testGetNum() {
        assertEquals(9, testPlayer.getNum());
    }

    @Test
    public void testReceive() {
        testPlayer.receive(mikasa);
        assertEquals(8  * 30, mikasa.getMoveToXPos());
        assertEquals(14  * 30 + 100, mikasa.getMoveToYPos());

        testEPlayer.receive(mikasa);
        assertEquals(4  * 30, mikasa.getMoveToXPos());
        assertEquals(400, mikasa.getMoveToYPos());
    }

    @Test
    public void testGetPlayingPosition() {
        assertEquals("Setter", testPlayer.getPlayingPosition());
    }


    // Tests for phase 3
    @Test
    public void testPointSpike() {
        Point p = new Point(10, 20);

        testPlayer.spike(p, mikasa);
        assertEquals(300, mikasa.getMoveToXPos());
        assertEquals(700, mikasa.getMoveToYPos());

    }

    @Test
    public void testGetAttackPoints() {
        assertNull(testPlayer.getAttackPoints(1));
    }

    @Test
    public void testDeclareNum() {
        testPlayer.declareNum(13);

        assertEquals(13, testPlayer.getNum());
    }

    @Test
    public void testMoveBySpeed() {
        testPlayer.directMoveY(0);
        testPlayer.directMoveX(0);
        assertEquals(0, testPlayer.getPosX());
        assertEquals(100, testPlayer.getPosY());
        testPlayer.moveToX(1);
        testPlayer.moveToY(1);

        testPlayer.moveBySpeed();
        assertEquals(10, testPlayer.getPosX());
        assertEquals(110, testPlayer.getPosY());

        testPlayer.moveBySpeed();
        testPlayer.moveBySpeed();
        assertEquals(30, testPlayer.getPosX());
        assertEquals(130, testPlayer.getPosY());

        testPlayer.moveBySpeed();
        assertEquals(30, testPlayer.getPosX());
        assertEquals(130, testPlayer.getPosY());


    }


}
