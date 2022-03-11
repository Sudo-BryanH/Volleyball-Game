package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        assertEquals(0, testPlayer.getPosX());
        assertEquals(0, testPlayer.getPosY());
        testPlayer.setRotation(5);
        assertEquals(5, testPlayer.getRotation());
        assertEquals(0, mikasa.getMoveToXPos());
        assertEquals(0, mikasa.getMoveToYPos());

        assertEquals(0, testEPlayer.getPosX());
        assertEquals(0, testEPlayer.getPosY());
        testEPlayer.setRotation(5);
        assertEquals(5, testEPlayer.getRotation());


    }

    @Test
    public void testGetPosX() {
        assertEquals(0, testPlayer.getPosX());
        testPlayer.moveToX(4);
        assertEquals(4, testPlayer.getPosX());
    }

    @Test
    public void testGetPosY() {
        assertEquals(0, testPlayer.getPosY());
        testPlayer.moveToY(14);
        assertEquals(14, testPlayer.getPosY());
    }

    @Test
    public void testSpike() {

        testPlayer.spike(1, mikasa);
        assertEquals(6,mikasa.getMoveToXPos());
        assertEquals(2,mikasa.getMoveToYPos());

        testPlayer.spike(0, mikasa);
        assertEquals(0,mikasa.getMoveToXPos());
        assertEquals(3,mikasa.getMoveToYPos());

        testEPlayer.spike(1, mikasa);
        assertEquals(6,mikasa.getMoveToXPos());
        assertEquals(22,mikasa.getMoveToYPos());

        testEPlayer.spike(0, mikasa);
        assertEquals(12,mikasa.getMoveToXPos());
        assertEquals(21,mikasa.getMoveToYPos());


    }

    @Test
    public void testReceive() {
        testPlayer.receive(mikasa);
        assertEquals(8, mikasa.getMoveToXPos());
        assertEquals(14, mikasa.getMoveToYPos());

        testEPlayer.receive(mikasa);
        assertEquals(4, mikasa.getMoveToXPos());
        assertEquals(8, mikasa.getMoveToYPos());
    }

    @Test
    public void testServe() {
        testPlayer.serve(0, mikasa);
        assertEquals(3,mikasa.getMoveToXPos());
        assertEquals(3,mikasa.getMoveToYPos());
        testPlayer.serve(1, mikasa);
        assertEquals(9,mikasa.getMoveToXPos());
        assertEquals(3,mikasa.getMoveToYPos());

        testEPlayer.serve(0, mikasa);
        assertEquals(3,mikasa.getMoveToXPos());
        assertEquals(21,mikasa.getMoveToYPos());
        testEPlayer.serve(1, mikasa);
        assertEquals(9,mikasa.getMoveToXPos());
        assertEquals(21,mikasa.getMoveToYPos());

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
        assertEquals(12,mikasa.getMoveToXPos());
        assertEquals(13,mikasa.getMoveToYPos());

        testPlayer.set(0, mikasa);
        assertEquals(0,mikasa.getMoveToXPos());
        assertEquals(13,mikasa.getMoveToYPos());

        testPlayer.set(1, mikasa);
        assertEquals(6,mikasa.getMoveToXPos());
        assertEquals(13,mikasa.getMoveToYPos());

        testEPlayer.set(2, mikasa);
        assertEquals(0,mikasa.getMoveToXPos());
        assertEquals(11,mikasa.getMoveToYPos());

        testEPlayer.set(0, mikasa);
        assertEquals(12,mikasa.getMoveToXPos());
        assertEquals(11,mikasa.getMoveToYPos());

        testEPlayer.set(1, mikasa);
        assertEquals(6,mikasa.getMoveToXPos());
        assertEquals(11,mikasa.getMoveToYPos());
    }
}
