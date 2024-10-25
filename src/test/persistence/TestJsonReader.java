package persistence;

import model.Dashboard;
import model.Patient;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

// Referenced from the JsonSerialization Demo
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo


public class TestJsonReader {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass - expected result
        }
    }

    @Test
    void testReaderEmptyDashboard() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyDashboard.json");
        try {
            Dashboard dashboard = reader.read();
            assertEquals(0, dashboard.listAllPatients().size());  // Dashboard has no patients
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralDashboard() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralDashboard.json");
        try {
            Dashboard dashboard = reader.read();

            
            List<Patient> patients = dashboard.listAllPatients();
            assertEquals(2, patients.size());

            
            Patient patient1 = patients.get(0);
            assertEquals("John Doe", patient1.getName());
            assertEquals("Normal", patient1.getStatus());

            
            Patient patient2 = patients.get(1);
            assertEquals("Jane Smith", patient2.getName());
            assertEquals("Critical", patient2.getStatus());

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }





}
