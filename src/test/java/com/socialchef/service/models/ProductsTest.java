package com.socialchef.service.models;

import java.util.LinkedList;

import org.junit.Assert;
import org.junit.Test;

public class ProductsTest {

    @Test
    public void productSearchByNameTest() {

	for (String query : Product.names) {
	    LinkedList<Product> result = Product.findByName(query);
	    Assert.assertNotNull("search by name result shouldn't be null",
		    result);
	    Assert.assertTrue("search result should contains "+query,
		    result.getLast().getName().equals(query));
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
	}
    }
}
