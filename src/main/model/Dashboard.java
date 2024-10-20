package model;

import java.util.List;

public class Dashboard {

    // EFFECTS: Has a list of patients intialized to be empty

    public Dashboard () {

    }

    // REQUIRES: Patient must not be null and must not already be in the dashboard
    // MODIFIES: The dashboard
    // EFFECTS: Adds a list of all the new patients to the dashbaord

    public void addPatients(List<Patient> newPatients) {
       
    }

    // REQUIRES: Dashbaord must not be null
    // MODIFIES: None
    // EFFECTS: Returns a list of all patients currently in the dashboard. 
    // If no patients exist, the method may return an empty list.

    public List<Patient> listAllPatients() {
        return null;
        
    }

    // REQUIRES: Name should not be null and a patient should have a given name in the system
    // MODIFIES: The list of patients in the dashboard
    // EFFECTS: Removes the patient with the given name (case sensitive) from the dashboard. 
    // Returns true if the patient was successfully removed, otherwise returns false.

    public boolean removePatient(String name) {
        
        return false;
    }


    // REQUIRES: name must not be null, and there must be a patient with the given name in the system.
    // MODIFIES: The status of the patient in the system.
    // EFFECTS: Marks patient as critical. Returns true if successful and false otherwise 
  

    public boolean markPatientAsCritical(String name) {

        return false;


    }

    public void add(List<Patient> newPatients) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'add'");
    }

	
}
