package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SettersTest {

    Setters testPlayer;
    Ball mikasa;

    @BeforeEach
    public void setUp() {
        testPlayer = new Setters(9, 1);
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

    }

    @Test
    public void testReceive() {

    }

    @Test
    public void testServe() {
        testPlayer.serve(1, mikasa);
        assertEquals(3,mikasa.getXPos());
        assertEquals(3,mikasa.getYPos());
        testPlayer.serve(2, mikasa);
        assertEquals(9,mikasa.getXPos());
        assertEquals(3,mikasa.getYPos());
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
        testPlayer.set(3, mikasa);
        assertEquals(12,mikasa.getXPos());
        assertEquals(13,mikasa.getYPos());

        testPlayer.set(1, mikasa);
        assertEquals(0,mikasa.getXPos());
        assertEquals(13,mikasa.getYPos());

        testPlayer.set(2, mikasa);
        assertEquals(6,mikasa.getXPos());
        assertEquals(13,mikasa.getYPos());
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


}
