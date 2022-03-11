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
        assertEquals(0, mikasa.getMoveToXPos());
        assertEquals(0, mikasa.getMoveToYPos());
        assertEquals(0, molten.getMoveToXPos());
        assertEquals(0, molten.getMoveToYPos());
    }

    @Test
    public void testGetXPos() {
        assertEquals(0, mikasa.getMoveToXPos());
        mikasa.moveToX(12);
        assertEquals(12, mikasa.getMoveToXPos());
        mikasa.moveToX(6);
        assertEquals(6, mikasa.getMoveToXPos());
        assertEquals(0, molten.getMoveToXPos());
        molten.moveToX(12);
        assertEquals(12, molten.getMoveToXPos());
        molten.moveToX(6);
        assertEquals(6, molten.getMoveToXPos());
    }

    @Test
    public void testGetYPos() {
        assertEquals(0, mikasa.getMoveToYPos());
        mikasa.moveToY(24);
        assertEquals(24, mikasa.getMoveToYPos());
        mikasa.moveToY(16);
        assertEquals(16, mikasa.getMoveToYPos());
        assertEquals(0, molten.getMoveToXPos());
        molten.moveToX(12);
        assertEquals(12, molten.getMoveToXPos());
        molten.moveToX(6);
        assertEquals(6, molten.getMoveToXPos());
    }

    @Test
    public void testMoveToX() {
        assertEquals(0, mikasa.getMoveToXPos());
        mikasa.moveToX(12);
        assertEquals(12, mikasa.getMoveToXPos());
        mikasa.moveToX(6);
        assertEquals(6, mikasa.getMoveToXPos());
        assertEquals(0, molten.getMoveToXPos());
        molten.moveToX(12);
        assertEquals(12, molten.getMoveToXPos());
        molten.moveToX(6);
        assertEquals(6, molten.getMoveToXPos());
    }

    @Test
    public void testMoveToY() {
        assertEquals(0, mikasa.getMoveToYPos());
        mikasa.moveToY(24);
        assertEquals(24, mikasa.getMoveToYPos());
        mikasa.moveToY(16);
        assertEquals(16, mikasa.getMoveToYPos());
        assertEquals(0, molten.getMoveToXPos());
        molten.moveToX(12);
        assertEquals(12, molten.getMoveToXPos());
        molten.moveToX(6);
        assertEquals(6, molten.getMoveToXPos());
    }



}
