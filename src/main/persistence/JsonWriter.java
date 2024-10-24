package persistence;

import java.io.FileNotFoundException;

import model.Dashboard;

// Referenced from the JsonSerialization Demo
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

// Represents a writer that writes JSON representation of dashboard to file

public class JsonWriter {


    // REQUIRES: destination is a valid file path
    // EFFECTS: constructs writer to write to destination file
    public JsonWriter(String destination) {

    }
        

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if the destination file cannot be opened for writing
    public void open() throws FileNotFoundException {
        // Placeholder for implementation
    }

    // REQUIRES: dashboard is not null
    // MODIFIES: this
    // EFFECTS: writes JSON representation of the dashboard to file
    public void write(Dashboard dashboard) {
        // Placeholder for implementation
    }

    // MODIFIES: this
    // EFFECTS: closes the writer
    public void close() {
        // Placeholder for implementation
    }

    // REQUIRES: json is a valid string in JSON format
    // MODIFIES: this
    // EFFECTS: writes the string to the file
    private void saveToFile(String json) {
        // Placeholder for implementation
    }

}
