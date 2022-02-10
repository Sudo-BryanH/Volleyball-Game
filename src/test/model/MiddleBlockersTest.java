package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MiddleBlockersTest {
    Players testPlayer;
    Ball mikasa;
    Players testEPlayer;

    @BeforeEach
    public void setUp() {
        testPlayer = new MiddleBlockers(1, 1);
        mikasa = new Ball();
        testEPlayer = new MiddleBlockers(12, 0);

    }

    @Test
    public void testConstructor() {
        assertEquals(1, testPlayer.getNum());
        assertEquals(0, testPlayer.getPosX());
        assertEquals(0, testPlayer.getPosY());
        testPlayer.setRotation(5);
        assertEquals(5, testPlayer.getRotation());
        assertEquals(0, mikasa.getXPos());
        assertEquals(0, mikasa.getYPos());
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
        assertEquals(6,mikasa.getXPos());
        assertEquals(6,mikasa.getYPos());
        testPlayer.spike(2, mikasa);
        assertEquals(2,mikasa.getXPos());
        assertEquals(5,mikasa.getYPos());
        testPlayer.spike(3, mikasa);
        assertEquals(10,mikasa.getXPos());
        assertEquals(5,mikasa.getYPos());

        testEPlayer.spike(1, mikasa);
        assertEquals(6,mikasa.getXPos());
        assertEquals(18,mikasa.getYPos());
        testEPlayer.spike(2, mikasa);
        assertEquals(2,mikasa.getXPos());
        assertEquals(19,mikasa.getYPos());
        testEPlayer.spike(3, mikasa);
        assertEquals(10,mikasa.getXPos());
        assertEquals(19,mikasa.getYPos());

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
    public void testServe() {
        testPlayer.serve(1, mikasa);
        assertEquals(3,mikasa.getXPos());
        assertEquals(3,mikasa.getYPos());
        testPlayer.serve(2, mikasa);
        assertEquals(9,mikasa.getXPos());
        assertEquals(3,mikasa.getYPos());

        testEPlayer.serve(1, mikasa);
        assertEquals(3,mikasa.getXPos());
        assertEquals(21,mikasa.getYPos());
        testEPlayer.serve(2, mikasa);
        assertEquals(9,mikasa.getXPos());
        assertEquals(21,mikasa.getYPos());



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
}


