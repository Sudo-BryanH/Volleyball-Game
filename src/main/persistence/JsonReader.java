package persistence;


import model.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

// JsonReader class which will allow for the game to read saved data
// CITATIONS: code copied and modified from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
public class JsonReader {
    private String source;
    private Game game;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source, Game game) {
        this.source = source;
        this.game = game;
    }

    // EFFECTS: reads Game Data file and returns it;
    // throws IOException if an error occurs reading data from file
    public GameData read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseGameData(jsonObject);


    }

    // EFFECTS: reads source fi8le as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses GameData from JSON object and returns it
    private GameData parseGameData(JSONObject jsonObject) {
        GameData g = new GameData(game);
        addMyScore(g, jsonObject);
        addEnemyScore(g, jsonObject);
        addMyTeam(g, jsonObject);
        addEnemyTeam(g, jsonObject);

        return g;
    }

    // MODIFIES: game
    // EFFECTS: reads the score and adds it to game
    private void addMyScore(GameData g, JSONObject jsonObject) {
        int score = jsonObject.getInt("myScore");
        g.setMyScore(score);
    }

    // MODIFIES: game
    // EFFECTS: reads the score and adds it to game
    private void addEnemyScore(GameData g, JSONObject jsonObject) {
        int score = jsonObject.getInt("enemyScore");
        g.setEnemyScore(score);
    }



    // Note: if rotation for a player is 0, do not list in starter. Player infor should be turned into a list
    // MODIFIES: game
    // EFFECTS: creates a team from jsonString and adds it to game
    private void addMyTeam(GameData g, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("myTeam");
        List<Players> members = new ArrayList<>();
        String name = jsonObject.getString("myName");

        for (Object json : jsonArray) {
            JSONObject player = (JSONObject) json;
            Players p = addPlayer(g, player);
            members.add(p);
        }

        Team myTeam = new MyTeam(members, name);
        g.setMyTeam(myTeam);

    }

    // MODIFIES: game
    // EFFECTS: creates a team from jsonString and adds it to game

    private void addEnemyTeam(GameData g, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("enemyTeam");
        String name = jsonObject.getString("enemyName");
        int chance = jsonObject.getInt("enemyTeamChance");
        List<Players> members = new ArrayList<>();
        EnemyTeam enemyTeam;

        for (Object json : jsonArray) {
            JSONObject player = (JSONObject) json;
            Players p = addPlayer(g, player);
            members.add(p);
        }

        enemyTeam = new EnemyTeam(members, name, chance);

        g.setEnemyTeam(enemyTeam);


    }
    // MODIFIES: game
    // EFFECTS: creates a playuer from jsonString and returns a p

    private Players addPlayer(GameData g, JSONObject jsonObject) {
        int side = jsonObject.getInt("side");
        String pos = jsonObject.getString("playingPosition");
        int rotation = jsonObject.getInt("rotation");
        int num = jsonObject.getInt("playerNum");
        Players p = null;

        if (pos.equals("Setter")) {
            p = new Setters(num, side);
        } else if (pos.equals("Middle Blocker")) {
            p = new MiddleBlockers(num, side);
        } else if (pos.equals("Outside Hitter")) {
            p = new OutsideHitter(num, side);
        } else if (pos.equals("Opposite Hitter")) {
            p = new OppositeHitter(num, side);
        }

        p.setRotation(rotation);

        return p;
    }


}



