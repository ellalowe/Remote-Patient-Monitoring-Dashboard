package model;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.ArrayList;

public class TestDashboard {

    private Patient paul;
    private Patient sarah;
    private Patient tod;
    private Patient ella;
    private List<Patient> newPatients;
    private List<Patient> newPatientsOne;
    private Dashboard dashboard;

    @BeforeEach
    void runBefore() {

        paul = new Patient("paul");
        sarah = new Patient("sarah");
        tod = new Patient("tod");
        ella = new Patient("ella");

        newPatients = new ArrayList<>();

        newPatients.add(paul);
        newPatients.add(sarah);
        newPatients.add(tod);

        dashboard = new Dashboard();

       newPatientsOne = new ArrayList<>();

        newPatientsOne.add(paul);

    }
    

    // Tests for AddPatients:

    @Test
    void testAddPatientEmptyList() {

        List<Patient> newPatients = new ArrayList<>();
        
        dashboard.addPatients(newPatients);
        
        assertTrue(dashboard.listAllPatients().isEmpty());
    }

    @Test 
    void testAddOnePatient() {
        
       
        dashboard.addPatients(newPatientsOne);
        List<Patient> patientsInDashboard = dashboard.listAllPatients();

        assertEquals(1, patientsInDashboard.size());

        assertTrue(patientsInDashboard.contains(paul));
       


    }

    @Test 
    void testAddMultiplePatients() {

        dashboard.addPatients(newPatients);
        List<Patient> patientsInDashboard = dashboard.listAllPatients();

        assertEquals(3, patientsInDashboard.size());

        assertTrue(patientsInDashboard.contains(paul));
        assertTrue(patientsInDashboard.contains(sarah));
        assertTrue(patientsInDashboard.contains(tod));


        
    } 

    // NOTE: Duplicate patients NOT allowed

    @Test 
    void testAddDuplicatePatients() {

        
        dashboard.addPatients(newPatients);
        List<Patient> patientsInDashboard = dashboard.listAllPatients();

        assertEquals(3, patientsInDashboard.size());

        assertTrue(patientsInDashboard.contains(paul));
        assertTrue(patientsInDashboard.contains(sarah));
        assertTrue(patientsInDashboard.contains(tod));

        dashboard.addPatients(newPatients); // Adds paul again

        assertEquals(3, patientsInDashboard.size()); // Size should still be 3




    }

    // Tests for ListAllPatients:

    @Test
    void testListAllPatientsNoPatientsAdded() { 

        List<Patient> patientsInDashboard = dashboard.listAllPatients();

        assertTrue(patientsInDashboard.isEmpty());

    }

   


    // Tests for remove Patient:

    @Test
    void testRemovePatientEmptyList() {

        List<Patient> patientsInDashboard = dashboard.listAllPatients();

        assertTrue(patientsInDashboard.isEmpty());

        boolean removePaul = dashboard.removePatient("paul");

        assertFalse(removePaul);



    }

    @Test
    void testRemovePatientCaseSensitivity() {

        dashboard.addPatients(newPatients);
        List<Patient> patientsInDashboard = dashboard.listAllPatients();

        assertEquals(3, patientsInDashboard.size());

        assertTrue(patientsInDashboard.contains(paul));
        assertTrue(patientsInDashboard.contains(sarah));
        assertTrue(patientsInDashboard.contains(tod));

        boolean removePaul = dashboard.removePatient("Paul"); // "P" is capital instead of "p"

        assertFalse(removePaul);

        assertTrue(patientsInDashboard.contains(paul));
        assertTrue(patientsInDashboard.contains(sarah));
        assertTrue(patientsInDashboard.contains(tod));

        assertEquals(3, patientsInDashboard.size());


    }

    @Test
    void testRemovePatientNameNotInList() {

        dashboard.addPatients(newPatients);
        List<Patient> patientsInDashboard = dashboard.listAllPatients();

        assertEquals(3, patientsInDashboard.size());

        assertTrue(patientsInDashboard.contains(paul));
        assertTrue(patientsInDashboard.contains(sarah));
        assertTrue(patientsInDashboard.contains(tod));
        assertFalse(patientsInDashboard.contains(ella));


        boolean removeElla = dashboard.removePatient("ella");

        assertFalse(removeElla);

        assertTrue(patientsInDashboard.contains(paul));
        assertTrue(patientsInDashboard.contains(sarah));
        assertTrue(patientsInDashboard.contains(tod));
        assertFalse(patientsInDashboard.contains(ella));

        assertEquals(3, patientsInDashboard.size());






    }

    @Test
    void testRemoveOnePatient() {

        dashboard.addPatients(newPatients);
        List<Patient> patientsInDashboard = dashboard.listAllPatients();

        assertEquals(3, patientsInDashboard.size());

        assertTrue(patientsInDashboard.contains(paul));
        assertTrue(patientsInDashboard.contains(sarah));
        assertTrue(patientsInDashboard.contains(tod));

        boolean removePaul = dashboard.removePatient("paul");

        assertTrue(removePaul);

        assertFalse(patientsInDashboard.contains(paul));
        assertTrue(patientsInDashboard.contains(sarah));
        assertTrue(patientsInDashboard.contains(tod));

        assertEquals(2, patientsInDashboard.size());




    }

    @Test
    void testRemoveMultiple() {

        dashboard.addPatients(newPatients);
        List<Patient> patientsInDashboard = dashboard.listAllPatients();

        assertEquals(3, patientsInDashboard.size());

        assertTrue(patientsInDashboard.contains(paul));
        assertTrue(patientsInDashboard.contains(sarah));
        assertTrue(patientsInDashboard.contains(tod));

       boolean removePaul = dashboard.removePatient("paul");
       boolean removeTod = dashboard.removePatient("tod");

       assertTrue(removePaul);
       assertTrue(removeTod);

        assertFalse(patientsInDashboard.contains(paul));
        assertTrue(patientsInDashboard.contains(sarah));
        assertFalse(patientsInDashboard.contains(tod));

      assertEquals(1, patientsInDashboard.size());



    }

    /// Tests for MarkPatientAsCritical

    @Test
    void testMarkExistingPatientAsCritical() {
        
        dashboard.addPatients(newPatients);

       assertTrue(dashboard.markPatientAsCritical("paul"));
       
        assertEquals("Critical", paul.getStatus());
    }

    @Test
    void testMarkNonExistentPatient() {
        assertFalse(dashboard.markPatientAsCritical("Alice"));
    }

    @Test
    void testMarkPatientWithDifferentCaseName() {
        assertFalse(dashboard.markPatientAsCritical("Paul"));
    }



}
