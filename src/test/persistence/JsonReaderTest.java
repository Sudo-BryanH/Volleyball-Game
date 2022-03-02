package persistence;

import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest extends JsonTest {

    Game game;
    Team team;
    EnemyTeam eteam;

    @BeforeEach
    public void setUp() {
        game = new Game();
    }

    @Test
    void testReader1to12fail() {
        JsonReader reader = new JsonReader("./data/sampleGameData1to12.json", game);

        try {
            GameData gd = reader.read();
            fail("File should not have been found");
        } catch (IOException e) {

        }
    }

    @Test
    void testReader1to1() {
        JsonReader reader = new JsonReader("./data/sampleGameData1to1.json", game);

        try {
            GameData gd = reader.read();
            team = gd.getMyTeam();
            checkMyTeam("nothing", team);
            eteam = gd.getEnemyTeam();
            checkEnemyTeam("weak team", eteam, 5);
            assertEquals(1, gd.getMyScore());
            assertEquals(1, gd.getEnemyScore());

            checkPlayers(1, 2, "Setter", 0, team.getRoster().get(0));
            checkPlayers(2, 3, "Middle Blocker", 0, team.getRoster().get(1));
            checkPlayers(3, 4, "Outside Hitter", 0, team.getRoster().get(2));
            checkPlayers(4, 5, "Opposite Hitter", 0, team.getRoster().get(3));
            checkPlayers(5, 6, "Middle Blocker", 0, team.getRoster().get(4));
            checkPlayers(6, 1, "Outside Hitter", 0, team.getRoster().get(5));

            checkPlayers(1, 5, "Setter", 1, eteam.getRoster().get(0));
            checkPlayers(2, 6, "Middle Blocker", 1, eteam.getRoster().get(1));
            checkPlayers(3, 1, "Outside Hitter", 1, eteam.getRoster().get(2));
            checkPlayers(4, 2, "Opposite Hitter", 1, eteam.getRoster().get(3));
            checkPlayers(5, 3, "Middle Blocker", 1, eteam.getRoster().get(4));
            checkPlayers(6, 4, "Outside Hitter", 1, eteam.getRoster().get(5));


        } catch (IOException e) {
            fail("Could not read from rile");
        }
    }

    @Test
    void testReader14to12() {
        JsonReader reader = new JsonReader("./data/sampleGameData14to12.json", game);

        try {
            GameData gd = reader.read();
            team = gd.getMyTeam();
            checkMyTeam("nothing", team);
            eteam = gd.getEnemyTeam();
            checkEnemyTeam("strong team", eteam, 2);
            assertEquals(14, gd.getMyScore());
            assertEquals(12, gd.getEnemyScore());

            checkPlayers(1, 2, "Setter", 0, team.getRoster().get(0));
            checkPlayers(2, 3, "Middle Blocker", 0, team.getRoster().get(1));
            checkPlayers(3, 4, "Outside Hitter", 0, team.getRoster().get(2));
            checkPlayers(4, 5, "Opposite Hitter", 0, team.getRoster().get(3));
            checkPlayers(5, 6, "Middle Blocker", 0, team.getRoster().get(4));
            checkPlayers(6, 1, "Outside Hitter", 0, team.getRoster().get(5));

            checkPlayers(1, 3, "Setter", 1, eteam.getRoster().get(0));
            checkPlayers(2, 4, "Middle Blocker", 1, eteam.getRoster().get(1));
            checkPlayers(3, 5, "Outside Hitter", 1, eteam.getRoster().get(2));
            checkPlayers(4, 6, "Opposite Hitter", 1, eteam.getRoster().get(3));
            checkPlayers(5, 1, "Middle Blocker", 1, eteam.getRoster().get(4));
            checkPlayers(6, 2, "Outside Hitter", 1, eteam.getRoster().get(5));


        } catch (IOException e) {
            fail("Could not read from rile");
        }
    }


}
