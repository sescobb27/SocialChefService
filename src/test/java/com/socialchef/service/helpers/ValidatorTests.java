package com.socialchef.service.helpers;

import org.junit.Assert;
import org.junit.Test;

public class ValidatorTests {

    @Test
    public void validNamesTest() {
	String[] names = {"Simon", "Kiro", "Edgardo", "JuAn", "CARLOS",
		"Luz", "VeryLongName", "veryLONGLONGname"};
	String[] names_LastNames = {"Simon Escobar Benitez",
		"Edgardo Andres Sierra", "Juan Carlos Noreña",
		"Very Long LongName",
		"pepitoperezperez pepitoperezperez pepitoperezperez",
		"Luz Marina", "MAYUS MAYUS MAYUS", "CaMeL LoNg NaMe"};
	
	for (int i = 0; i < names.length; ++i) {
	    String name = names[i];
	    String name_LastName = names_LastNames[i];
	    
	    Assert.assertTrue("Name should be a valid name",
		    Validator.validateNames(name));
	    Assert.assertTrue("Name and Last Name should be both valid",
		    Validator.validateNames(name_LastName));
	}
    }
    
    @Test
    public void invalidNames() {
	String[] iNames = {"", "   ", "Simon1", "1Simon", "_Simon",
		"Simon_", "_Simon_", "Si", "S", "12345", "@simon",
		"51m0N", "simon..."};
	String[] iNames_LastNames = {"pepitoperezperez pepitoperezperez pepitoperezperez pepitoperezperez",
		"pepitoperezperez 1234","!\"#$&","pepitoperezperez pepitoperezperez pepitoperezperezperez",
		"pepitoperezperez p","pepitoperezperez pe","pepitoperezperez pepitoperezperez p",
		"pepitoperezperez pepitoperezperez pe", "p pepitoperezperez",
		"pe pepitoperezperez",
		"<script> alert('fuckyou'); </script>"};
	for (String name : iNames) {
	    Assert.assertFalse("Name should be invalid",
		    Validator.validateNames(name));
	}
	for (String iname_lastname : iNames_LastNames) {
	    Assert.assertFalse("Name with Last Name should be both invalids "+iname_lastname,
		    Validator.validateNames(iname_lastname));
	}
    }
}
