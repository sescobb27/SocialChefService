package com.socialchef.service.features;


import cucumber.api.java.en.When;

public class SearchProduct {
    
    @When("^I search \"(.*)\"$")
    public void I_search(String query) {
	System.out.println(query);
    }
}
