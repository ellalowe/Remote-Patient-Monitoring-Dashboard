package ui;

import model.Dashboard;
import model.Patient;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;


public class PatientDashboardApp {

    private Dashboard dashboard;
    private Scanner scanner;

    public PatientDashboardApp() {
        dashboard = new Dashboard();
        scanner = new Scanner(System.in);
    }

    // Main method to run the application
    public static void main(String[] args) {
        PatientDashboardApp app = new PatientDashboardApp();
        app.run();
    }

    // EFFECTS: Method to run the application
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
        System.out.println("5. Exit");
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
}