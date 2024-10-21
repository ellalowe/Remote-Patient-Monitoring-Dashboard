package model;

// The Patient class represents a patient with a name and a health status. 
// By default, the health status of a patient is set to "normal." 
// The class provides functionality to get and set the health status, and also to retrieve the patient's name.

public class Patient {

    private String name;
    private String status; // Normal or Critical

 
 // represents a patient with a name and default health status set to "Normal"

    public Patient (String name) {

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

        } else {
          
            System.out.println("Invalid status. Status must be 'Normal' or 'Critical'.");
        }
    }
      
    }



