package persistence;

import model.EnemyTeam;
import model.Game;
import model.Players;
import model.Team;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {

    protected void checkPlayers(int num, int rotation, String position, int side, Players p){
        assertEquals(num, p.getNum());
        assertEquals(rotation, p.getRotation());
        assertEquals(position, p.getPlayingPosition());
        assertEquals(side, p.getSide());

    }

    protected void checkMyTeam(String name, Team t){
        assertEquals(name, t.getName());

    }

    protected void checkEnemyTeam(String name, EnemyTeam eTeam, int chance){
        assertEquals(name, eTeam.getName());
        assertEquals(chance, eTeam.getChance());


    }

    protected void checkGame(Game g, int myScore, int enemyScore) {
        assertEquals(myScore, g.getMyScore());
        assertEquals(enemyScore, g.getEnemyScore());
    }


}
