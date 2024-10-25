package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class TestPatient {

    private Patient john;


    @BeforeEach
    void runBefore() {

        john = new Patient("John");

        


    }

    @Test
    void patientTest() {

        assertEquals(john.getName(), "John");
        john.setStatus("Normal");
        assertEquals("Normal", john.getStatus());

    }

    @Test
    void testSetStatusCritical() {

        assertEquals(john.getName(), "John");
        john.setStatus("Critical");
        assertEquals("Critical", john.getStatus());

        john.setStatus("Normal");
        assertEquals("Normal", john.getStatus());



    }

    @Test
    void testSetStatusInvalid() {

        assertEquals(john.getName(), "John");
        john.setStatus("Random");
        assertEquals("Normal", john.getStatus());


    }
}

  
    

    










