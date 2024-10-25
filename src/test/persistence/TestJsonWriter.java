package persistence;

import model.Dashboard;
import model.Patient;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// Referenced from the JsonSerialization Demo
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

public class TestJsonWriter {

    @Test
    void testWriterInvalidFile() {
        try {
            
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // Pass - expected result
        }
    }


    @Test
    void testWriterEmptyDashboard() {
        try {
            Dashboard dashboard = new Dashboard();  
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyDashboard.json");
            writer.open();
            writer.write(dashboard);  
            writer.close();

            // Read the dashboard back and verify it is empty
            JsonReader reader = new JsonReader("./data/testWriterEmptyDashboard.json");
            dashboard = reader.read();
            assertEquals(0, dashboard.listAllPatients().size());  // No patients should exist
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralDashboard() {
        try {
            Dashboard dashboard = new Dashboard();
    
            
            Patient john = new Patient("John Doe");
            Patient jane = new Patient("Jane Smith");
    
            
            jane.setStatus("Critical");
    
            
            dashboard.addPatients(List.of(john, jane));
    
            
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralDashboard.json");
            writer.open();
            writer.write(dashboard);
            writer.close();
    
           
            JsonReader reader = new JsonReader("./data/testWriterGeneralDashboard.json");
            dashboard = reader.read();
            List<Patient> patients = dashboard.listAllPatients();
            assertEquals(2, patients.size());
    
            
            assertEquals("John Doe", patients.get(0).getName());
            assertEquals("Normal", patients.get(0).getStatus());
    
            
            assertEquals("Jane Smith", patients.get(1).getName());
            assertEquals("Critical", patients.get(1).getStatus());
    
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }



}
