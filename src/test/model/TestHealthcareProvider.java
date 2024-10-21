package model;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

        assertEquals(rachel.getName(), "rachel");
    }



}
