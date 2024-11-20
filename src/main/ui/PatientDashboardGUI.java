package ui;

import javax.swing.*;

import model.Dashboard;
import model.Patient;

import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

public class PatientDashboardGUI extends JFrame {

    private Dashboard dashboard;
    private DefaultListModel<String> patientListModel;
    private JList<String> patientList;

    @SuppressWarnings("methodlength")
    public PatientDashboardGUI() {
        dashboard = new Dashboard();
        patientListModel = new DefaultListModel<>();
        patientList = new JList<>(patientListModel);

        setTitle("Patient Monitoring Dashboard");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

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

        // Actions Panel
        JPanel actionsPanel = new JPanel();
        actionsPanel.setBackground(new Color(230, 230, 250)); 
        JComboBox<String> statusFilter = new JComboBox<>(new String[] {"All", "Normal", "Critical"});
        JButton filterButton = new JButton("Filter");
        
        JButton markCriticalButton = new JButton("Mark as Critical");

        actionsPanel.add(new JLabel("Filter by Status:"));
        actionsPanel.add(statusFilter);
        actionsPanel.add(filterButton);
        actionsPanel.add(markCriticalButton);

        // Add components to the main frame
        add(addPatientPanel, BorderLayout.NORTH);
        add(patientListPanel, BorderLayout.CENTER);
        add(actionsPanel, BorderLayout.SOUTH);

        // Button Listeners
        addPatientButton.addActionListener(e -> {
            String name = patientNameField.getText().trim();
            if (!name.isEmpty()) {
                dashboard.addPatients(List.of(new Patient(name)));
                updatePatientList(dashboard.listAllPatients());
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
                String name = selectedPatient.split(" - ")[0]; // Assuming display format is "Name - Status"
                dashboard.markPatientAsCritical(name);
                updatePatientList(dashboard.listAllPatients());
            }
        });
    }

    private void updatePatientList(List<Patient> patients) {
        patientListModel.clear();
        for (Patient patient : patients) {
            patientListModel.addElement(patient.getName() + " - " + patient.getStatus());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PatientDashboardGUI gui = new PatientDashboardGUI();
            gui.setVisible(true);
        });
    }

}
