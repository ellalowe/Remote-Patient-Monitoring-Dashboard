package model;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
public class TestPatient {

    private Patient john;


    @BeforeEach
    void runBefore() {

        john = new Patient("John");

        


    }

    @Test
    void patientTest() {

    assertEquals(john.getName(), "John");

    assertEquals(john.getStatus(), "Normal");

    }

    









}
