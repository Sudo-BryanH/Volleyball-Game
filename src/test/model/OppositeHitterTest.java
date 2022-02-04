package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class OppositeHitterTest {

        OppositeHitter testPlayer;
        Ball mikasa;

        @BeforeEach
        public void setUp() {
            testPlayer = new OppositeHitter(1);
            mikasa = new Ball();

        }

        @Test
        public void testConstructor() {
            assertEquals(1, testPlayer.getNum());
            assertEquals(0, testPlayer.getPosX());
            assertEquals(0, testPlayer.getPosY());
            assertEquals(4, testPlayer.getRotation());

        }

        @Test
        public void testGetPosX() {
            assertEquals(0, testPlayer.getPosX());
            testPlayer.moveTo(4, 14, 0);
            assertEquals(4, testPlayer.getPosX());
        }

        @Test
        public void testGetPosY() {
            assertEquals(0, testPlayer.getPosY());
            testPlayer.moveTo(4, 14, 0);
            assertEquals(14, testPlayer.getPosY());
        }

        @Test
        public void testSpike() {
            testPlayer.spike(2); // CROSS HIT
            assertEquals(6,mikasa.getXPos());
            assertEquals(3,mikasa.getYPos());
            testPlayer.spike(1);  // STRAIGHT HIT
            assertEquals(12,mikasa.getXPos());
            assertEquals(4,mikasa.getYPos());


        }

        @Test
        public void testReceive() {
            testPlayer.receive();
            assertEquals(8, mikasa.getXPos());
            assertEquals(14, mikasa.getYPos());
        }

        @Test
        public void testServe() {
            testPlayer.serve(1);
            assertEquals(12,mikasa.getXPos());
            assertEquals(4,mikasa.getYPos());
            testPlayer.serve(2);
            assertEquals(6,mikasa.getXPos());
            assertEquals(3,mikasa.getYPos());

        }

        @Test
        public void testGetRotation() {
            assertEquals(0, testPlayer.getRotation());
            testPlayer.setRotation(1);
            assertEquals(1, testPlayer.getRotation());
        }

        @Test
        public void testSetRotation() {
            assertEquals(4, testPlayer.getRotation());
            testPlayer.setRotation(5);
            assertEquals(5, testPlayer.getRotation());
            testPlayer.setRotation(6);
            assertEquals(6, testPlayer.getRotation());
        }






}
