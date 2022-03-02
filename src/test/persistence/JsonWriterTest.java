package persistence;

import model.EnemyTeam;
import model.Game;
import model.GameData;
import model.Team;
import org.junit.jupiter.api.BeforeEach;

public class JsonWriterTest extends JsonTest{

    Game game;
    GameData gameData;
    JsonWriter writer;
    String JSON_STORE = "./data/sampleEmptyGameData.json";
    Team myTeam;
    EnemyTeam enemyTeam;

    @BeforeEach
    public void setUp() {
        makeTeam();
        makeEnemyTeam();
        game = new Game(myTeam, enemyTeam);
        gameData = new GameData(game);
    }

    private void makeEnemyTeam() {
    }

    public void makeTeam() {

    }

}
