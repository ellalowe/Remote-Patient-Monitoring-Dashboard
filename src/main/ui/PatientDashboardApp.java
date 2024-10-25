package ui;

import model.Dashboard;
import model.Patient;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

import persistence.JsonReader;
import persistence.JsonWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

// The PatientDashboardApp provides a console-based interface for managing patient data, 
// including adding, updating, and monitoring patient status in the patient monitoring dashboard system.

// Referenced from the JsonSerialization Demo
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

public class PatientDashboardApp {
    
    private static final String JSON_STORE = "./data/dashboard.json";
    private Dashboard dashboard;
    private Scanner scanner;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    public PatientDashboardApp() {
        dashboard = new Dashboard();
        scanner = new Scanner(System.in);
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }

   
    // EFFECTS: Method to run the application
    @SuppressWarnings("methodlength")
    public void run() {
        boolean running = true;
        while (running) {
            printMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); 
            switch (choice) {
                case 1:
                    addPatients();
                    break;
                case 2:
                    listPatients(); 
                    break;
                case 3:
                    removePatient();
                    break;
                case 4:
                    markPatientAsCritical();
                    break;
                case 5:
                    saveDashboard();
                    break;
                case 6:
                    loadDashboard();
                    break;
                case 7:
                    System.out.println("Exiting application...");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option, please try again.");
            }
        }
    }

    // EFFECTS: Print menu options
    private void printMenu() {
        System.out.println("\n--- Patient Monitoring Dashboard ---");
        System.out.println("1. Add Patients");
        System.out.println("2. List All Patients"); 
        System.out.println("3. Remove a Patient");
        System.out.println("4. Mark a Patient as Critical");
        System.out.println("5. Save Dashboard");
        System.out.println("6. Load Dashboard");
        System.out.println("7. Exit");
        System.out.print("Enter your choice: ");
    }

    // EFFECTS: Add patients to the dashboard
    private void addPatients() {
        System.out.print("Enter patient names (comma separated): ");
        String input = scanner.nextLine();
        String[] patientNames = input.split(",");
        List<Patient> patients = new ArrayList<>();
        for (String name : patientNames) {
            patients.add(new Patient(name.trim()));
        }
        dashboard.addPatients(patients);
        System.out.println("Patients added successfully.");
    }

    // EFFECTS: List all patients in the dashboard
    private void listPatients() {
        List<Patient> patients = dashboard.listAllPatients();
        if (patients.isEmpty()) {
            System.out.println("No patients found in the dashboard.");
        } else {
            System.out.println("\n--- List of Patients ---");
            for (Patient patient : patients) {
                System.out.println("Name: " + patient.getName() + " - Status: " + patient.getStatus());
            }
        }
    }

    // EFFECTS: Remove a patient from the dashboard
    private void removePatient() {
        System.out.print("Enter the name of the patient to remove: ");
        String name = scanner.nextLine();
        if (dashboard.removePatient(name)) {
            System.out.println("Patient removed successfully.");
        } else {
            System.out.println("Patient not found.");
        }
    }

    // EFFECTS: Mark a patient as critical
    private void markPatientAsCritical() {
        System.out.print("Enter the name of the patient to mark as critical: ");
        String name = scanner.nextLine();
        if (dashboard.markPatientAsCritical(name)) {
            System.out.println("Patient marked as critical.");
        } else {
            System.out.println("Patient not found or could not be marked.");
        }
    }

    // EFFECTS: Saves the dashboard to a file
    private void saveDashboard() {
        try {
            jsonWriter.open();
            jsonWriter.write(dashboard);
            jsonWriter.close();
            System.out.println("Dashboard saved to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: Loads the dashboard from a file
    private void loadDashboard() {
        try {
            dashboard = jsonReader.read();
            System.out.println("Dashboard loaded from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}