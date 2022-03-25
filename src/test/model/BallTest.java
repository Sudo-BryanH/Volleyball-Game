package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BallTest {

    Ball mikasa;
    Ball molten;

    @BeforeEach
    public void setUp() {
        mikasa = new Ball();
        molten = new Ball();
    }

    @Test
    public void testConstructor() {
        assertEquals(0, mikasa.getMoveToXPos());
        assertEquals(0, mikasa.getMoveToYPos());
        assertEquals(0, molten.getMoveToXPos());
        assertEquals(0, molten.getMoveToYPos());
    }

    @Test
    public void testGetSetNewXPos() {
        assertEquals(0, mikasa.getMoveToXPos());
        mikasa.moveToX(12);
        assertTrue(mikasa.getMoveState());
        assertEquals(12 * 30, mikasa.getMoveToXPos());
        mikasa.moveToX(6);
        assertEquals(6 * 30, mikasa.getMoveToXPos());
        assertEquals(0, molten.getMoveToXPos());
        molten.moveToX(12);
        assertEquals(12 * 30, molten.getMoveToXPos());
        molten.moveToX(6);
        assertEquals(6 * 30, molten.getMoveToXPos());
    }

    @Test
    public void testGetSetNewYPos() {
        assertEquals(0, mikasa.getMoveToYPos());
        mikasa.moveToY(24);
        assertTrue(mikasa.getMoveState());
        assertEquals(24 * 30 + 100, mikasa.getMoveToYPos());
        mikasa.moveToY(16);
        assertEquals(16 * 30 + 100, mikasa.getMoveToYPos());
        assertEquals(0, molten.getMoveToYPos());
        molten.moveToY(12);
        assertEquals(12 * 30 + 100, molten.getMoveToYPos());
        molten.moveToY(6);
        assertEquals(6 * 30 + 100, molten.getMoveToYPos());
    }


    @Test
    public void testDirectXY() {
        mikasa.directY(3);
        assertEquals(190, mikasa.getCurrentYPos());
        assertFalse(mikasa.getMoveState());
        mikasa.directX(3);
        assertEquals(90, mikasa.getCurrentXPos());
        mikasa.directY(12);
        assertEquals(460, mikasa.getCurrentYPos());
        mikasa.directX(9);
        assertEquals(270, mikasa.getCurrentXPos());
    }

    @Test
    public void testMoveBySpeed() {
        molten.directY(0);
        molten.directX(0);
        assertFalse(molten.getMoveState());
        assertEquals(0, molten.getCurrentXPos());
        assertEquals(100, molten.getCurrentYPos());
        molten.moveToX(2);
        molten.moveToY(2);
        assertTrue(molten.getMoveState());

        molten.move();
        assertEquals(12, molten.getCurrentXPos());
        assertEquals(124, molten.getCurrentYPos());

        molten.move();
        molten.move();
        assertEquals(36, molten.getCurrentXPos());
        assertEquals(160, molten.getCurrentYPos());

        molten.move();
        assertEquals(48, molten.getCurrentXPos());
        assertEquals(160, molten.getCurrentYPos());

        molten.move();
        assertEquals(60, molten.getCurrentXPos());
        assertEquals(160, molten.getCurrentYPos());

        molten.move();
        assertEquals(60, molten.getCurrentXPos());
        assertEquals(160, molten.getCurrentYPos());
        assertFalse(molten.getMoveState());

    }



}
