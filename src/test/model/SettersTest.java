package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        assertEquals(0, testPlayer.getPosX());
        assertEquals(0, testPlayer.getPosY());
        assertEquals(1, testPlayer.getRotation());

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
        assertEquals(6,mikasa.getXPos());
        assertEquals(6,mikasa.getYPos());
        assertEquals(1, testPlayer.numDump());

        testEPlayer.spike(1, mikasa);
        assertEquals(6,mikasa.getXPos());
        assertEquals(18,mikasa.getYPos());
        assertEquals(1, testPlayer.numDump());

    }


    @Test
    public void testServe() {
        testPlayer.serve(0, mikasa);
        assertEquals(3,mikasa.getXPos());
        assertEquals(3,mikasa.getYPos());
        testPlayer.serve(1, mikasa);
        assertEquals(9,mikasa.getXPos());
        assertEquals(3,mikasa.getYPos());

        testEPlayer.serve(0, mikasa);
        assertEquals(3,mikasa.getXPos());
        assertEquals(21,mikasa.getYPos());
        testEPlayer.serve(1, mikasa);
        assertEquals(9,mikasa.getXPos());
        assertEquals(21,mikasa.getYPos());

    }

    @Test
    public void testGetRotation() {
        assertEquals(1, testPlayer.getRotation());
        testPlayer.setRotation(2);
        assertEquals(2, testPlayer.getRotation());
    }

    @Test
    public void testSetRotation() {
        assertEquals(1, testPlayer.getRotation());
        testPlayer.setRotation(1);
        assertEquals(1, testPlayer.getRotation());
        testPlayer.setRotation(2);
        assertEquals(2, testPlayer.getRotation());
    }

    @Test
    public void testSet() {
        testPlayer.set(2, mikasa);
        assertEquals(12,mikasa.getXPos());
        assertEquals(13,mikasa.getYPos());

        testPlayer.set(0, mikasa);
        assertEquals(0,mikasa.getXPos());
        assertEquals(13,mikasa.getYPos());

        testPlayer.set(1, mikasa);
        assertEquals(6,mikasa.getXPos());
        assertEquals(13,mikasa.getYPos());

        testEPlayer.set(2, mikasa);
        assertEquals(0,mikasa.getXPos());
        assertEquals(11,mikasa.getYPos());

        testEPlayer.set(0, mikasa);
        assertEquals(12,mikasa.getXPos());
        assertEquals(11,mikasa.getYPos());

        testEPlayer.set(1, mikasa);
        assertEquals(6,mikasa.getXPos());
        assertEquals(11,mikasa.getYPos());
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
        assertEquals(8, mikasa.getXPos());
        assertEquals(14, mikasa.getYPos());

        testEPlayer.receive(mikasa);
        assertEquals(4, mikasa.getXPos());
        assertEquals(8, mikasa.getYPos());
    }

    @Test
    public void testGetPlayingPosition() {
        assertEquals("Setter", testPlayer.getPlayingPosition());
    }

}
