package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class OutsideHitterTest {
    Players testPlayer;
    Ball mikasa;
    Players testEPlayer;

    @BeforeEach
    public void setUp() {
        testPlayer = new OutsideHitter(1, 1);
        mikasa = new Ball();
        testEPlayer = new OutsideHitter(3, 0);

    }

    @Test
    public void testConstructor() {
        assertEquals(1, testPlayer.getNum());
        assertEquals(60, testPlayer.getPosX());
        assertEquals(460, testPlayer.getPosY());
        testPlayer.setRotation(5);
        assertEquals(5, testPlayer.getRotation());
        assertEquals(0, mikasa.getMoveToXPos());
        assertEquals(0, mikasa.getMoveToYPos());

        assertEquals(60, testEPlayer.getPosX());
        assertEquals(460, testEPlayer.getPosY());
        testEPlayer.setRotation(5);
        assertEquals(5, testEPlayer.getRotation());


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
        assertEquals(2 * 30 + 100,mikasa.getMoveToYPos());

        testPlayer.spike(0, mikasa);
        assertEquals(0 * 30,mikasa.getMoveToXPos());
        assertEquals(3 * 30 + 100,mikasa.getMoveToYPos());

        testEPlayer.spike(1, mikasa);
        assertEquals(6 * 30,mikasa.getMoveToXPos());
        assertEquals(22 * 30 + 100,mikasa.getMoveToYPos());

        testEPlayer.spike(0, mikasa);
        assertEquals(12 * 30,mikasa.getMoveToXPos());
        assertEquals(21 * 30 + 100,mikasa.getMoveToYPos());


    }

    @Test
    public void testReceive() {
        testPlayer.receive(mikasa);
        assertEquals(8  * 30, mikasa.getMoveToXPos());
        assertEquals(14 * 30 + 100, mikasa.getMoveToYPos());

        testEPlayer.receive(mikasa);
        assertEquals(4 * 30, mikasa.getMoveToXPos());
        assertEquals(400, mikasa.getMoveToYPos());
    }

    @Test
    public void testServe() {
        testPlayer.serve(0, mikasa);
        assertEquals(3 * 30,mikasa.getMoveToXPos());
        assertEquals(3 * 30 + 100,mikasa.getMoveToYPos());
        testPlayer.serve(1, mikasa);
        assertEquals(9 * 30,mikasa.getMoveToXPos());
        assertEquals(3 * 30 + 100,mikasa.getMoveToYPos());

        testEPlayer.serve(0, mikasa);
        assertEquals(3 * 30,mikasa.getMoveToXPos());
        assertEquals(21 * 30 + 100,mikasa.getMoveToYPos());
        testEPlayer.serve(1, mikasa);
        assertEquals(9 * 30,mikasa.getMoveToXPos());
        assertEquals(21 * 30 + 100,mikasa.getMoveToYPos());

    }

    @Test
    public void testGetRotation() {
        testPlayer.setRotation(0);
        assertEquals(0, testPlayer.getRotation());
        testPlayer.setRotation(1);
        assertEquals(1, testPlayer.getRotation());
    }

    @Test
    public void testSetRotation() {

        testPlayer.setRotation(5);
        assertEquals(5, testPlayer.getRotation());
        testPlayer.setRotation(6);
        assertEquals(6, testPlayer.getRotation());
    }
    @Test
    public void testGetNum() {
        assertEquals(1, testPlayer.getNum());
    }

    @Test
    public void testGetPlayingPosition() {
        assertEquals("Outside Hitter", testPlayer.getPlayingPosition());
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
        assertEquals(11  * 30 + 100,mikasa.getMoveToYPos());

        testEPlayer.set(1, mikasa);
        assertEquals(6 * 30,mikasa.getMoveToXPos());
        assertEquals(11  * 30 + 100,mikasa.getMoveToYPos());
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
        assertEquals(2, testPlayer.getAttackPoints(1).size());
        assertEquals(2, testPlayer.getAttackPoints(0).size());
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
