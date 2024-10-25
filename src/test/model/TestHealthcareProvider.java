package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class TestHealthcareProvider {

    private HealthcareProvider rachel;
   

    @BeforeEach
    void runBefore() { 

        rachel = new HealthcareProvider("Rachel");

       
 

    }

    @Test
    void testHealthcareProvider() {

        assertEquals(rachel.getName(), "Rachel");
    }



}
