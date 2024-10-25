package persistence;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import org.json.JSONObject;

import model.Dashboard;

// Referenced from the JsonSerialization Demo
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

// Represents a writer that writes JSON representation of dashboard to file

public class JsonWriter {

    private static final int TAB = 4;  // Indentation level for JSON formatting
    private PrintWriter writer;        // Writer to output to file
    private String destination;        // File path


    // EFFECTS: constructs writer to write to destination file
    public JsonWriter(String destination) {

        this.destination = destination;

    }
        

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if the destination file cannot be opened for writing
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

 
    // MODIFIES: this
    // EFFECTS: writes JSON representation of the dashboard to file
    public void write(Dashboard dashboard) {
        JSONObject json = dashboard.toJson();  // Convert Dashboard to a JSON object
        saveToFile(json.toString(TAB));  
    }

    // MODIFIES: this
    // EFFECTS: closes the writer
    public void close() {
        writer.close();  // Close the file writer
    }


    // MODIFIES: this
    // EFFECTS: writes the string to the file
    private void saveToFile(String json) {
        writer.print(json);  // Write the JSON string to file
    }

}
