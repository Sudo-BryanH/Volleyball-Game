package model;

import org.json.JSONArray;
import org.json.JSONObject;

// Creates objects for JSONWriter to write to the gameData file
public class GameData {
    Team myTeam;
    Team enemyTeam;

    // EFFECTS: constructs a new game data, declaring team objects with empty arrays and other specifications
    public GameData(String name, Game game) {

    }


    // EFFECTS: creates JSON Object for strings
    public JSONObject stringJson(String category, String string) {
        JSONObject json = new JSONObject();
        json.put(category, string);
        return json;
    }

    // EFFECTS: creates JSON Object for numbers
    public JSONObject intJson(String category, int num) {
        JSONObject json = new JSONObject();
        json.put(category, num);
        return json;
    }

    // EFFECTS: creates JSON Array to hold teams
    public JSONArray teamJson(String side, Players s, Players mb1, Players oh1, Players op, Players mb2, Players ph2) {
        return null;
    }




}
