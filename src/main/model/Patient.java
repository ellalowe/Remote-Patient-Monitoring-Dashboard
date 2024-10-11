package model;
import java.util.List;



public class Patient extends User {

 
 // Initializes a new Patient object with the given 
 // username, password, email, patientID, and medical history.

    public Patient (String username, String password, String email, String patientID, String medicalHistory) {

        super(username, password, email, medicalHistory);

     

}

    // REQUIRES: None
    // MODIFIES: None
    // EFFECTS: Returns the Health Metrics for this patient

    public List<HealthMetric> getHealthMetrics() {
       
        return null;

    }


    // REQUIRES: healthMetric must not be null.
    // MODIFIES: this
    // EFFECTS: Adds the given health metric to the patient's list of health metrics.
    
    public void addHealthMetric(HealthMetric healthMetric) {
    
    }


}
