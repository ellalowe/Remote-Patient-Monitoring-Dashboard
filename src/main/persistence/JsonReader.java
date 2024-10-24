package persistence;
import model.Dashboard;
import model.Patient;
import org.json.JSONObject;
import org.json.JSONArray;

import java.io.IOException;

// Referenced from the JsonSerialization Demo
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

// // Represents a reader that reads the Dashboard from JSON data stored in a file

public class JsonReader {

    private String source;


    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

   
    // MODIFIES: this
    // EFFECTS: reads the dashboard from the file and returns it;
    // throws IOException if an error occurs while reading data from the file
    public Dashboard read() throws IOException {
        return null; 
    }

   
    // EFFECTS: reads source file as a string and returns it
    // throws IOException if an error occurs while reading the file
    private String readFile(String source) throws IOException {
        return null; 
    }

    // MODIFIES: dashboard
    // EFFECTS: parses Dashboard from JSON object and returns it
    private Dashboard parseDashboard(JSONObject jsonObject) {
        return null; 
    }

    // MODIFIES: dashboard
    // EFFECTS: parses patients from JSON object and adds them to the dashboard
    private void addPatients(Dashboard dashboard, JSONObject jsonObject) {
        
    }

    // MODIFIES: dashboard
    // EFFECTS: parses a patient from JSON object and adds it to the dashboard
    private void addPatient(Dashboard dashboard, JSONObject jsonObject) {
        
    }



}
