package persistence;

import model.Dashboard;
import model.Patient;
import org.json.JSONObject;
import org.json.JSONArray;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;



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
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseDashboard(jsonObject);
    }

   
    // EFFECTS: reads source file as a string and returns it
    // throws IOException if an error occurs while reading the file
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(contentBuilder::append);
        }

        return contentBuilder.toString();
    }

    // MODIFIES: dashboard
    // EFFECTS: parses Dashboard from JSON object and returns it
    private Dashboard parseDashboard(JSONObject jsonObject) {
        Dashboard dashboard = new Dashboard();
        addPatients(dashboard, jsonObject);  // Add patients from the JSON object to the dashboard
        return dashboard;
    }

    // MODIFIES: dashboard
    // EFFECTS: parses patients from JSON object and adds them to the dashboard
    private void addPatients(Dashboard dashboard, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("patients");  // Assuming your JSON object has a "patients" key
        List<Patient> patientList = new ArrayList<>();
        
        for (Object json : jsonArray) {
            JSONObject nextPatient = (JSONObject) json;
            String name = nextPatient.getString("name");
            String status = nextPatient.getString("status");  // Get the status from JSON
    
            Patient patient = new Patient(name);  // Create the patient with the name
            patient.setStatus(status);  // Set the status for the patient
            
            patientList.add(patient);  // Add the patient to the list
        }
        
        dashboard.addPatients(patientList);  // Add the list of patients to the dashboard
    }
        
    

    



}
