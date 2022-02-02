package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        assertEquals(0, mikasa.getXPos());
        assertEquals(0, mikasa.getYPos());
        assertEquals(0, molten.getXPos());
        assertEquals(0, molten.getYPos());
    }

    @Test
    public void testGetXPos() {
        assertEquals(0, mikasa.getXPos());
        mikasa.moveToX(12);
        assertEquals(12, mikasa.getXPos());
        mikasa.moveToX(6);
        assertEquals(6, mikasa.getXPos());
        assertEquals(0, molten.getXPos());
        molten.moveToX(12);
        assertEquals(12, molten.getXPos());
        molten.moveToX(6);
        assertEquals(6, molten.getXPos());
    }

    @Test
    public void testGetYPos() {
        assertEquals(0, mikasa.getYPos());
        mikasa.moveToY(24);
        assertEquals(24, mikasa.getYPos());
        mikasa.moveToY(16);
        assertEquals(16, mikasa.getYPos());
        assertEquals(0, molten.getXPos());
        molten.moveToX(12);
        assertEquals(12, molten.getXPos());
        molten.moveToX(6);
        assertEquals(6, molten.getXPos());
    }



}
