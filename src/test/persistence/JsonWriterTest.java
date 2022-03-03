package persistence;

import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest extends JsonTest{

    Game game;
    GameData gd;
    JsonWriter jsonWriter;
    String JSON_STORE = "./data/sampleEmptyGameData.json";
    Team myTeam;
    EnemyTeam enemyTeam;
    Players testSet;
    Players testMB1;
    Players testMB2;
    Players testOH1;
    Players testOH2;
    Players testOP;
    Players testMB3;
    Players testSet2;
    Players testESet;
    Players testEMB1;
    Players testEMB2;
    Players testEOH1;
    Players testEOH2;
    Players testEOP;
    Game newGame;


    @BeforeEach
    public void setUp() {
        makeTeam();
        makeEnemyTeam();
        game = new Game(myTeam, enemyTeam);
        gd = new GameData(game);
        jsonWriter = new JsonWriter(JSON_STORE);
    }

    private void makeEnemyTeam() {
        testEMB1 = new MiddleBlockers(2, 0);
        testEMB2 = new MiddleBlockers(5, 0);
        testEOH1 = new OutsideHitter(3, 0);
        testEOH2 = new OutsideHitter(6, 0);
        testESet = new Setters(1, 0);
        testEOP = new OppositeHitter(4, 0);

        enemyTeam = new EnemyTeam("weak team", testESet, testEMB1, testEMB2,
                testEOH1, testEOH2, testEOP);
        enemyTeam.arrangeMbOh();
        enemyTeam.setChance(5);
        enemyTeam.changeRotation();
        enemyTeam.changeRotation();
        enemyTeam.changeRotation();


    }

    public void makeTeam() {
        testMB1 = new MiddleBlockers(2, 1);
        testMB2 = new MiddleBlockers(5, 1);
        testOH1 = new OutsideHitter(3, 1);
        testOH2 = new OutsideHitter(6, 1);
        testSet = new Setters(1, 1);
        testOP = new OppositeHitter(4, 1);
        myTeam = new MyTeam("myTeam", testSet, testMB1, testMB2,
                testOH1, testOH2, testOP);
        myTeam.arrangeMbOh();

    }

    @Test
    public void createGameData1to0Filefail() {
        newGame = new Game();
        JsonReader reader = new JsonReader(JSON_STORE, newGame);
        JsonWriter jsonWriter = new JsonWriter("./data/flub\0b");
        try {
            game.myScore();
            myTeam.changeRotation();
            jsonWriter.open();
            jsonWriter.write(gd);
            jsonWriter.close();

            gd = reader.read();
            myTeam = gd.getMyTeam();
            checkMyTeam("myTeam", myTeam);
            enemyTeam = gd.getEnemyTeam();
            checkEnemyTeam("weak team", enemyTeam, 5);
            assertEquals(1, gd.getMyScore());
            assertEquals(0, gd.getEnemyScore());

            checkPlayers(1, 2, "Setter", 1, myTeam.getRoster().get(0));
            checkPlayers(2, 3, "Middle Blocker", 1, myTeam.getRoster().get(1));
            checkPlayers(3, 4, "Outside Hitter", 1, myTeam.getRoster().get(2));
            checkPlayers(4, 5, "Opposite Hitter", 1, myTeam.getRoster().get(3));
            checkPlayers(5, 6, "Middle Blocker", 1, myTeam.getRoster().get(4));
            checkPlayers(6, 1, "Outside Hitter", 1, myTeam.getRoster().get(5));

            checkPlayers(1, 4, "Setter", 0, enemyTeam.getRoster().get(0));
            checkPlayers(2, 5, "Middle Blocker", 0, enemyTeam.getRoster().get(1));
            checkPlayers(3, 6, "Outside Hitter", 0, enemyTeam.getRoster().get(2));
            checkPlayers(4, 1, "Opposite Hitter", 0, enemyTeam.getRoster().get(3));
            checkPlayers(5, 2, "Middle Blocker", 0, enemyTeam.getRoster().get(4));
            checkPlayers(6, 3, "Outside Hitter", 0, enemyTeam.getRoster().get(5));

            fail("File was somehow found");
        } catch (FileNotFoundException e) {
            System.out.println("didn't fail");
        } catch (IOException e) {
            fail("File was found");
        }


    }

    @Test
    public void createGameData1to0IOfail() {
        newGame = new Game();
        JsonReader reader = new JsonReader("./data/whatever.json", newGame);

        try {
            game.myScore();
            myTeam.changeRotation();
            jsonWriter.open();
            jsonWriter.write(gd);
            jsonWriter.close();

            gd = reader.read();
            myTeam = gd.getMyTeam();
            checkMyTeam("myTeam", myTeam);
            enemyTeam = gd.getEnemyTeam();
            checkEnemyTeam("weak team", enemyTeam, 5);
            assertEquals(1, gd.getMyScore());
            assertEquals(0, gd.getEnemyScore());

            checkPlayers(1, 2, "Setter", 1, myTeam.getRoster().get(0));
            checkPlayers(2, 3, "Middle Blocker", 1, myTeam.getRoster().get(1));
            checkPlayers(3, 4, "Outside Hitter", 1, myTeam.getRoster().get(2));
            checkPlayers(4, 5, "Opposite Hitter", 1, myTeam.getRoster().get(3));
            checkPlayers(5, 6, "Middle Blocker", 1, myTeam.getRoster().get(4));
            checkPlayers(6, 1, "Outside Hitter", 1, myTeam.getRoster().get(5));

            checkPlayers(1, 4, "Setter", 0, enemyTeam.getRoster().get(0));
            checkPlayers(2, 5, "Middle Blocker", 0, enemyTeam.getRoster().get(1));
            checkPlayers(3, 6, "Outside Hitter", 0, enemyTeam.getRoster().get(2));
            checkPlayers(4, 1, "Opposite Hitter", 0, enemyTeam.getRoster().get(3));
            checkPlayers(5, 2, "Middle Blocker", 0, enemyTeam.getRoster().get(4));
            checkPlayers(6, 3, "Outside Hitter", 0, enemyTeam.getRoster().get(5));

            fail("File was somehow found");
        } catch (FileNotFoundException e) {
            fail("File was found");
        } catch (IOException e) {

        }


    }



    @Test
    public void createGameData1to0() {
        newGame = new Game();
        JsonReader reader = new JsonReader(JSON_STORE, newGame);

        try {
            game.myScore();
            myTeam.changeRotation();
            jsonWriter.open();
            jsonWriter.write(gd);
            jsonWriter.close();

            gd = reader.read();
            myTeam = gd.getMyTeam();
            checkMyTeam("myTeam", myTeam);
            enemyTeam = gd.getEnemyTeam();
            checkEnemyTeam("weak team", enemyTeam, 5);
            assertEquals(1, gd.getMyScore());
            assertEquals(0, gd.getEnemyScore());

            checkPlayers(1, 2, "Setter", 1, myTeam.getRoster().get(0));
            checkPlayers(2, 3, "Middle Blocker", 1, myTeam.getRoster().get(1));
            checkPlayers(3, 4, "Outside Hitter", 1, myTeam.getRoster().get(2));
            checkPlayers(4, 5, "Opposite Hitter", 1, myTeam.getRoster().get(3));
            checkPlayers(5, 6, "Middle Blocker", 1, myTeam.getRoster().get(4));
            checkPlayers(6, 1, "Outside Hitter", 1, myTeam.getRoster().get(5));

            checkPlayers(1, 4, "Setter", 0, enemyTeam.getRoster().get(0));
            checkPlayers(2, 5, "Middle Blocker", 0, enemyTeam.getRoster().get(1));
            checkPlayers(3, 6, "Outside Hitter", 0, enemyTeam.getRoster().get(2));
            checkPlayers(4, 1, "Opposite Hitter", 0, enemyTeam.getRoster().get(3));
            checkPlayers(5, 2, "Middle Blocker", 0, enemyTeam.getRoster().get(4));
            checkPlayers(6, 3, "Outside Hitter", 0, enemyTeam.getRoster().get(5));


        } catch (FileNotFoundException e) {
            fail("File was found");
        } catch (IOException e) {
            fail("File was found");
        }


    }

}
