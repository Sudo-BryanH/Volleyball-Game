package model;

import org.json.JSONArray;
import org.json.JSONObject;

// Creates objects for JSONWriter to write to the gameData file
public class GameData {
    Team myTeam;
    EnemyTeam enemyTeam;
    Game game;


    // EFFECTS: constructs a new game data, declaring team objects with empty arrays and other specifications
    public GameData(String name, Game game) {
        myTeam = game.getMyTeam();
        enemyTeam = (EnemyTeam) game.getEnemyTeam();
        this.game = game;
    }

    // EFFECTS: creates the whole game data
    public JSONObject prevGame() {
        JSONObject json = new JSONObject();
        json.put("myScore", game.getMyScore());
        json.put("enemyScore", game.getEnemyScore());
        json.put("myName", myTeam.getName());
        json.put("enemyName", enemyTeam.getName());
        json.put("enemyTeamChance", enemyTeam.getChance());
        json.put("myTeam", teamJson(myTeam));
        json.put("enemyTeam", teamJson(enemyTeam));

        return json;
    }



    // EFFECTS: creates JSON Array to hold teams
    public JSONArray teamJson(Team team) {
        JSONArray array = new JSONArray("");

        for (Players p : team.getRoster()) {
            JSONObject json = new JSONObject();
            json.put("side", p.getSide());
            json.put("playingPosition", p.getPlayingPosition());
            json.put("rotation", p.getRotation());
            json.put("playerNum", p.getNum());
            array.put(json);
        }

        return array;
    }




}
