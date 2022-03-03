package persistence;

// JsonWriter allows game to save game data to JSON file
// TODO CITATIONS:

import model.GameData;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class JsonWriter {

    private static final int TAB = 4;
    private PrintWriter writer;
    private String destination;

    // constructs writer to write to destination file
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file cannot
    // be opened for writing
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        writer.close();
    }

    // MODIFIES: JSON file
    // EFFECTS: writes JSON file
    public void write(GameData g) {
        JSONObject json = g.makeData();
        saveToFile(json.toString(TAB));
    }

    public void saveToFile(String json) {
        writer.print(json);
    }



}