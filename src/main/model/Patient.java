package model;

import org.json.JSONObject;

// The Patient class represents a patient with a name and a health status. 
// By default, the health status of a patient is set to "normal." 
// The class provides functionality to get and set the health status, and also to retrieve the patient's name.

public class Patient {

    private String name;
    private String status; // Normal or Critical

 
 // represents a patient with a name and default health status set to "Normal"

    public Patient(String name) {

        this.name = name;
        this.status = "Normal";

       
     
    }

    // Getter for the name
    public String getName() {
        return name;
    }

    // Getter for the status
    public String getStatus() {
        return status;
    }

    // Setter for the status
    public void setStatus(String status) {

        if (status.equals("Normal") || status.equals("Critical")) {
            this.status = status;

        } 
    }
     

     // Referenced from the JsonSerialization Demo
     // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
     
     // EFFECTS: Converts Patient object to a JSON object
    public JSONObject toJson() {

        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("status", status);
        
        return json; 
    }

}
        

        


   
      




