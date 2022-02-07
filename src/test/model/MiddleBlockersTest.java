package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MiddleBlockersTest {
    MiddleBlockers testPlayer;
    Ball mikasa;

    @BeforeEach
    public void setUp() {
        testPlayer = new MiddleBlockers(1, 1);
        mikasa = new Ball();

    }

    @Test
    public void testConstructor() {
        assertEquals(1, testPlayer.getNum());
        assertEquals(0, testPlayer.getPosX());
        assertEquals(0, testPlayer.getPosY());
        testPlayer.setRotation(5);
        assertEquals(5, testPlayer.getRotation());

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
        testPlayer.serve(1, mikasa);
        assertEquals(6,mikasa.getXPos());
        assertEquals(6,mikasa.getYPos());
        testPlayer.serve(2, mikasa);
        assertEquals(2,mikasa.getXPos());
        assertEquals(5,mikasa.getYPos());
        testPlayer.serve(2, mikasa);
        assertEquals(10,mikasa.getXPos());
        assertEquals(5,mikasa.getYPos());


    }

    @Test
    public void testReceive() {
        testPlayer.receive(mikasa);
        assertEquals(8, mikasa.getXPos());
        assertEquals(14, mikasa.getYPos());
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
}


