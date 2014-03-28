package com.socialchef.service.models;

import java.util.LinkedList;

import org.junit.Assert;
import org.junit.Test;

public class ProductsTest {


    @Test
    public void mockProductsTest() {
	for(Product p : Product.mockProducts()) {
	    Assert.assertNotNull("mock Product shouldn't be null",
		    p);
	    Assert.assertTrue("mock Product name should be valid",
		    Product.validateProduct(p));
	}
    }
    @Test
    public void productSearchByNameTest() {

	for (String query : Product.names) {
	    LinkedList<Product> result = Product.findByName(query);
	    Assert.assertNotNull("search by name result shouldn't be null",
		    result);
	    Assert.assertTrue("search result should contains "+query,
		    result.getLast().getName().equals(query));
	    Assert.assertTrue("search result should contains 1 element",
		    result.size() == 1);
	}
    }

    @Test
    public void productSearchByCategoryTest() {
	for (String query : Product.categories) {
	    LinkedList<Product> result = Product.findByCategory(query);
	    Assert.assertNotNull("search by category result shouldn't be null",
		    result);
	    Assert.assertTrue("search result should contains "+query,
		    result.getLast().getCategory().equals(query));
	    Assert.assertTrue("search result should contains 1 element",
		    result.size() == 1);
	}
    }

    @Test
    public void productSearchByLocationTest() {
	for (String query : Product.locations) {
	    LinkedList<Product> result = Product.findByLocation(query);
	    Assert.assertNotNull("search by category result shouldn't be null",
		    result);
	    Assert.assertTrue("search result should contains "+query,
		    result.getLast().getLocation().equals(query));
	    Assert.assertTrue("search result should contains 1 elements",
		    result.size() == 1);
	}
    }
    
    @Test
    public void productSearchByRegexTest() {
	LinkedList<Product> result = Product.findByRegex("ado");
	Assert.assertNotNull("search by regex result shouldn't be null",
		result);
	Assert.assertTrue("search result should contains 2 elements",
		result.size() == 2);
    }
}
