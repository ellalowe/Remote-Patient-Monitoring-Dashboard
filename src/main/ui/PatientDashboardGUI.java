package ui;

import javax.swing.*;
import model.Event;
import model.EventLog;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import model.Dashboard;
import model.Patient;
import persistence.JsonReader;
import persistence.JsonWriter;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;


// This is the GUI for managing the Patient Monitoring Dashboard. 
// Provides functionality to add patients, view and filter patient lists, mark patients as critical, 
// and save/load patient data to/from a JSON file.

public class PatientDashboardGUI extends JFrame implements WindowListener {

    private static final String JSON_STORE = "./data/dashboard.json";
    private Dashboard dashboard;
    private DefaultListModel<String> patientListModel;
    private JList<String> patientList;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    public PatientDashboardGUI() {
        dashboard = new Dashboard();
        patientListModel = new DefaultListModel<>();
        patientList = new JList<>(patientListModel);

        jsonWriter = new JsonWriter(JSON_STORE); 
        jsonReader = new JsonReader(JSON_STORE); 

        
        setTitle("Patient Monitoring Dashboard");
        setSize(500, 400);

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // Handle close manually
        addWindowListener(this); // Add WindowListener
        setLayout(new BorderLayout());

        
        setupGUI();
    }

    @SuppressWarnings("methodlength")

    // MODIFIES: this
    // EFFECTS: Sets up the GUI components for the Patient Monitoring Dashboard, including panels
    // for adding patients, viewing and filtering the patient list, and marking patients as critical.
    // Configures and adds colour to buttons and their associated action listeners for user interactions.

    private void setupGUI() {

        // Add Patient Panel
        JPanel addPatientPanel = new JPanel();
        JTextField patientNameField = new JTextField(10);
        JButton addPatientButton = new JButton("Add Patient");
        addPatientButton.setBackground(Color.decode("#473DFF"));
        addPatientButton.setForeground(Color.WHITE); 
        addPatientButton.setOpaque(true);
        addPatientButton.setBorderPainted(false);

        addPatientPanel.add(new JLabel("Name:"));
        addPatientPanel.add(patientNameField);
        addPatientPanel.add(addPatientButton);

        // Patient List Panel
        JPanel patientListPanel = new JPanel();
        patientListPanel.setLayout(new BorderLayout());
        patientListPanel.add(new JLabel("Patients:"), BorderLayout.NORTH);
        patientListPanel.add(new JScrollPane(patientList), BorderLayout.CENTER);

        // Actions Panel (Save, Load, Filter, Mark Critical)
        JPanel actionsPanel = new JPanel();
        JComboBox<String> statusFilter = new JComboBox<>(new String[] {"All", "Normal", "Critical"});
        JButton filterButton = new JButton("Filter");
        JButton markCriticalButton = new JButton("Mark as Critical");
        JButton saveButton = new JButton("Save");
        JButton loadButton = new JButton("Load");

        // Apply color to all buttons
        filterButton.setBackground(Color.decode("#473DFF"));
        filterButton.setForeground(Color.WHITE);
        filterButton.setOpaque(true);
        filterButton.setBorderPainted(false);

        markCriticalButton.setBackground(Color.decode("#473DFF"));
        markCriticalButton.setForeground(Color.WHITE);
        markCriticalButton.setOpaque(true);
        markCriticalButton.setBorderPainted(false);

        saveButton.setBackground(Color.decode("#473DFF"));
        saveButton.setForeground(Color.WHITE);
        saveButton.setOpaque(true);
        saveButton.setBorderPainted(false);

        loadButton.setBackground(Color.decode("#473DFF"));
        loadButton.setForeground(Color.WHITE);
        loadButton.setOpaque(true);
        loadButton.setBorderPainted(false);

        actionsPanel.add(new JLabel("Filter by Status:"));
        actionsPanel.add(statusFilter);
        actionsPanel.add(filterButton);
        actionsPanel.add(markCriticalButton);
        actionsPanel.add(saveButton);
        actionsPanel.add(loadButton);

        // Add panels to the frame
        add(addPatientPanel, BorderLayout.NORTH);
        add(patientListPanel, BorderLayout.CENTER);
        add(actionsPanel, BorderLayout.SOUTH);

        // Button Listeners
        addPatientButton.addActionListener(e -> {
            String name = patientNameField.getText().trim();
            if (!name.isEmpty()) {
                dashboard.addPatients(List.of(new Patient(name)));
                updatePatientList(dashboard.listAllPatients());
                patientNameField.setText(""); // Clear input field
            }
        });

        filterButton.addActionListener(e -> {
            String selectedStatus = (String) statusFilter.getSelectedItem();
            List<Patient> filteredPatients = dashboard.filterPatients(selectedStatus); 
            updatePatientList(filteredPatients);
        });

        markCriticalButton.addActionListener(e -> {
            String selectedPatient = patientList.getSelectedValue();
            if (selectedPatient != null) {
                String name = selectedPatient.split(" - ")[0];
                dashboard.markPatientAsCritical(name);
                updatePatientList(dashboard.listAllPatients());
            }
        });

        saveButton.addActionListener(e -> saveDashboardToFile());
        loadButton.addActionListener(e -> {
            loadDashboardFromFile();
            updatePatientList(dashboard.listAllPatients());
        });
    }
        

        
//  REQUIRES: List of patients is not null.
//  MODIFIES: This
//  EFFECTS: Clears the existing patient list and adds the names and statuses
//          of the given patients in the format "Name - Status".

    private void updatePatientList(List<Patient> patients) {
        patientListModel.clear();
        for (Patient patient : patients) {
            patientListModel.addElement(patient.getName() + " - " + patient.getStatus());
        }
    }
    
//  REQUIRES: dashboard is not null.
//  MODIFIES: The file specified by `JSON_STORE`.
//  EFFECTS: Writes the current state of the dashboard to the specified file.
//           If the file cannot be found throws file not found exception
    private void saveDashboardToFile() {
        try {
            jsonWriter.open();
            jsonWriter.write(dashboard);
            jsonWriter.close();
           
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(this, "Unable to write to file: " + JSON_STORE);
        }
    }
    
    // REQUIRES: The file specified by `JSON_STORE` is not null
    // MODIFIES: dashboard object and the patient list displayed in the GUI.
    // EFFECTS: Loads the dashboard and updates the GUI with the data.
    // If the file cannot be read throws IOException

    private void loadDashboardFromFile() {
        try {
            dashboard = jsonReader.read();
            updatePatientList(dashboard.listAllPatients());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Unable to read from file: " + JSON_STORE);
        }
    }

    @Override
    public void windowClosing(WindowEvent e) {
        // Log all events to the console when the window is closing
        EventLog eventLog = EventLog.getInstance();
        System.out.println("Event Log (on application exit):");
        for (Event event : eventLog) {
            System.out.println(event.getDate() + ": " + event.getDescription());
        }

        // Dispose the window
        dispose();
    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {
        
    }

     // EFFECTS: Launches the Patient Monitoring Dashboard application. Displays a splash screen
    // with an image for 3 seconds before initializing and displaying the main GUI.
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Create the splash screen
            JWindow splashScreen = new JWindow();
            JPanel splashPanel = new JPanel(new BorderLayout());
            splashPanel.setBackground(Color.WHITE);
    
            // Add an image to the splash screen 
            JLabel splashImage = new JLabel(new ImageIcon("data/Patient Monitoring Dashboard Splash Screen.png"));
            splashImage.setHorizontalAlignment(SwingConstants.CENTER);
            splashPanel.add(splashImage, BorderLayout.CENTER);
    
            splashScreen.add(splashPanel);
            splashScreen.setSize(400, 300); 
            splashScreen.setLocationRelativeTo(null); 
            splashScreen.setVisible(true);
    
            // Set a timer to close the splash screen and launch the GUI
            Timer timer = new Timer(3000, e -> {
                splashScreen.setVisible(false);
                splashScreen.dispose();
    
                // Launch the main application
                PatientDashboardGUI gui = new PatientDashboardGUI();
                gui.setVisible(true);
            });
            timer.setRepeats(false); 
            timer.start();
        });
    }
       
   
    
    

    

}
