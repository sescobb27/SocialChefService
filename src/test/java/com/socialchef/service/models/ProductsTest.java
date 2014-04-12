package com.socialchef.service.models;

import java.util.HashMap;
import java.util.LinkedList;

import org.junit.Assert;
import org.junit.Test;

public class ProductsTest {

	// TESTING
	public static String[] names = { "Plato1", "Plato2", "Plato3" };
	public static String[] categories = { "pastas", "carne", "ensalada" };
	public static String[] locations = { "Poblado", "Laureles", "Envigado" };
	public static String[] descriptions = { "Descripcion1", "Descripcion2",
	"Descripcion3" };
	public static float[] prices = { 18500.0f, 12300.0f, 5000.0f };
	public static String[] images = { "images/default.png",
		"images/default.png", "images/default.png" };

	// END TESTING

//	public static LinkedList<Product> mockProducts() {
//		LinkedList<Product> mocks = new LinkedList<Product>();
//		for (int i = 0; i < Product.names.length; ++i) {
//			String u_name = User.names[i];
//			String u_last_name = User.last_names[i];
//			String u_username = User.usernames[i];
//			String u_email = User.emails[i];
//
//			User u = new User(u_name, u_last_name, u_username, u_email);
//			String p_name = Product.names[i];
//			String p_category = Product.categories[i];
//			String p_location = Product.locations[i];
//			String p_description = Product.descriptions[i];
//			float p_price = Product.prices[i];
//			String p_image = Product.images[i];
//
//			Product p = new Product(p_name, p_category, p_location,
//					p_description, p_image, u, p_price, null, null);
//			mocks.add(p);
//		}
//		return mocks;
//	}
//
//	public static HashMap<String, Product> mockProductsAsHash() {
//		HashMap<String, Product> mocks = new HashMap<String, Product>();
//		for (int i = 0; i < Product.names.length; ++i) {
//			String u_name = User.names[i];
//			String u_last_name = User.last_names[i];
//			String u_username = User.usernames[i];
//			String u_email = User.emails[i];
//
//			User u = new User(u_name, u_last_name, u_username, u_email);
//			String p_name = Product.names[i];
//			String p_category = Product.categories[i];
//			String p_location = Product.locations[i];
//			String p_description = Product.descriptions[i];
//			float p_price = Product.prices[i];
//
//			Product p = new Product(p_name, p_category, p_location,
//					p_description, "", u, p_price, null, null);
//			mocks.put(p.name, p);
//		}
//		return mocks;
//	}
//
//	private static LinkedList<Product> mockSearch(String query) {
//		LinkedList<Product> mocks = Product.mockProducts();
//		LinkedList<Product> results = new LinkedList<Product>();
//		for (Product p : mocks) {
//			if (p.name.contains(query)
//					|| p.category.contains(query)
//					|| p.chef.getUsername().contains(query)
//					|| p.location.contains(query)) {
//				results.add(p);
//			}
//		}
//		return results;
//	}
	
	@Test
	public void mockProductsTest() {
//		for(Product p : Product.mockProducts()) {
//			Assert.assertNotNull("mock Product shouldn't be null",
//					p);
//			Assert.assertTrue("mock Product name should be valid",
//					Product.validateProduct(p));
//		}
	}
	@Test
	public void productSearchByNameTest() {

//		for (String query : names) {
//			LinkedList<Product> result = Product.findByName(query);
//			Assert.assertNotNull("search by name result shouldn't be null",
//					result);
//			Assert.assertTrue("search result should contains "+query,
//					result.getLast().getName().equals(query));
//			Assert.assertTrue("search result should contains 1 element",
//					result.size() == 1);
//		}
	}

	@Test
	public void productSearchByCategoryTest() {
//		for (String query : categories) {
//			LinkedList<Product> result = Product.findByCategory(query);
//			Assert.assertNotNull("search by category result shouldn't be null",
//					result);
//			Assert.assertTrue("search result should contains "+query,
//					result.getLast().getCategory().equals(query));
//			Assert.assertTrue("search result should contains 1 element",
//					result.size() == 1);
//		}
	}

	@Test
	public void productSearchByLocationTest() {
//		for (String query : locations) {
//			LinkedList<Product> result = Product.findByLocation(query);
//			Assert.assertNotNull("search by category result shouldn't be null",
//					result);
//			Assert.assertTrue("search result should contains "+query,
//					result.getLast().getLocation().equals(query));
//			Assert.assertTrue("search result should contains 1 elements",
//					result.size() == 1);
//		}
	}

	@Test
	public void productSearchByRegexTest() {
//		LinkedList<Product> result = Product.findByRegex("ado");
//		Assert.assertNotNull("search by regex result shouldn't be null",
//				result);
//		Assert.assertTrue("search result should contains 2 elements",
//				result.size() == 2);
	}
}
