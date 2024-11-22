package ui;

import javax.swing.*;
import model.Dashboard;
import model.Patient;
import persistence.JsonReader;
import persistence.JsonWriter;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class PatientDashboardGUI extends JFrame {

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

        // Set up the frame
        setTitle("Patient Monitoring Dashboard");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Add GUI components
        setupGUI();
    }

    @SuppressWarnings("methodlength")

    private void setupGUI() {
        // Add Patient Panel
        JPanel addPatientPanel = new JPanel();
        JTextField patientNameField = new JTextField(10);
        JButton addPatientButton = new JButton("Add Patient");
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
            List<Patient> filteredPatients = dashboard.listAllPatients().stream()
                    .filter(p -> "All".equals(selectedStatus) || p.getStatus().equals(selectedStatus))
                    .collect(Collectors.toList());
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

    private void updatePatientList(List<Patient> patients) {
        patientListModel.clear();
        for (Patient patient : patients) {
            patientListModel.addElement(patient.getName() + " - " + patient.getStatus());
        }
    }

    private void saveDashboardToFile() {
        try {
            jsonWriter.open();
            jsonWriter.write(dashboard);
            jsonWriter.close();
           
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(this, "Unable to write to file: " + JSON_STORE);
        }
    }

    private void loadDashboardFromFile() {
        try {
            dashboard = jsonReader.read();
            updatePatientList(dashboard.listAllPatients());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Unable to read from file: " + JSON_STORE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Create the splash screen
            JWindow splashScreen = new JWindow();
            JPanel splashPanel = new JPanel(new BorderLayout());
            splashPanel.setBackground(Color.WHITE);
    
            // Add an image to the splash screen (accessing from the data package)
            JLabel splashImage = new JLabel(new ImageIcon("data/Patient Monitoring Dashboard Splash Screen.png"));
            splashImage.setHorizontalAlignment(SwingConstants.CENTER);
            splashPanel.add(splashImage, BorderLayout.CENTER);
    
            splashScreen.add(splashPanel);
            splashScreen.setSize(400, 300); // Adjust size as needed
            splashScreen.setLocationRelativeTo(null); // Center on screen
            splashScreen.setVisible(true);
    
            // Set a timer to close the splash screen and launch the GUI
            Timer timer = new Timer(3000, e -> { // 3 seconds
                splashScreen.setVisible(false);
                splashScreen.dispose();
    
                // Launch the main application
                PatientDashboardGUI gui = new PatientDashboardGUI();
                gui.setVisible(true);
            });
            timer.setRepeats(false); // Run the timer only once
            timer.start();
        });
    }
       
   
    
    

    

}
