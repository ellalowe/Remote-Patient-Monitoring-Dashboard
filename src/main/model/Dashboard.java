package model;

import java.util.ArrayList;
import java.util.List;

public class Dashboard {

    private List<Patient> patients;

    // EFFECTS: Has a list of patients intialized to be empty

    public Dashboard() {

        patients = new ArrayList<>();

    }

    // REQUIRES: Patient must not be null and must not already be in the dashboard
    // MODIFIES: The dashboard
    // EFFECTS: Adds a list of all the new patients to the dashbaord

    public void addPatients(List<Patient> newPatients) {
 
        for (Patient patient : newPatients) {
            if (!patients.contains(patient)) {
                patients.add(patient);  // Add the patient individually
            }
        }
       
    }

    // REQUIRES: Dashbaord must not be null
    // MODIFIES: None
    // EFFECTS: Returns a list of all patients currently in the dashboard. 
    // If no patients exist, the method may return an empty list.

    public List<Patient> listAllPatients() {
        return patients;
        
    }

    // REQUIRES: Name should not be null and a patient should have a given name in the system
    // MODIFIES: The list of patients in the dashboard
    // EFFECTS: Removes the patient with the given name (case sensitive) from the dashboard. 
    // Returns true if the patient was successfully removed, otherwise returns false.

    public boolean removePatient(String name) {

        if (name == null) {
            return false;  // If the name is null, return false immediately.
        }
        
        for (Patient patient : patients) {
            if (patient.getName().equals(name)) {
                patients.remove(patient);  
                return true;  
            }
        }
    
        return false;  
    }
    


    // REQUIRES: name must not be null, and there must be a patient with the given name in the system.
    // MODIFIES: The status of the patient in the system.
    // EFFECTS: Marks patient as critical. Returns true if successful and false otherwise 
  

    public boolean markPatientAsCritical(String name) {

        if (name == null) {
            return false;  // If name is null, return false
        }
    
        for (Patient patient : patients) {
            if (patient.getName().equals(name)) {  // Case-sensitive comparison
                patient.setStatus("Critical");  // Mark the patient as critical
                return true;  // Return true when the patient is found and updated
            }
        }
    
        return false; 


    }

    
	
}
