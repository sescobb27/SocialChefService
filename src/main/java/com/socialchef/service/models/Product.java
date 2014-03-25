package com.socialchef.service.models;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicLong;

public class Product {

    private String name, category, location, description, image;
    private User chef;
    private float rate = 0.0f;
    private double price;
    private Date deliveryTime, disponibilityTime, createdAt;
    private static AtomicLong incrementalId = new AtomicLong(0);
    private long id;
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
    public Product(String name, String category, String location,
	    String description, String image, User chef, double price,
	    Date delivery_time, Date disponibility_time) {
	this.name = name;
	this.category = category;
	this.location = location;
	this.description = description;
	this.image = image;
	this.chef = chef;
	this.price = price;
	this.deliveryTime = delivery_time;
	this.disponibilityTime = disponibility_time;
	this.id = Product.incrementalId.incrementAndGet();
    }

    public String getName() {
	return this.name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getCategory() {
	return this.category;
    }

    public void setCategory(String category) {
	this.category = category;
    }

    public String getLocation() {
	return this.location;
    }

    public void setLocation(String location) {
	this.location = location;
    }

    public String getDescription() {
	return this.description;
    }

    public void setDescription(String description) {
	this.description = description;
    }

    public String getImage() {
	return this.image;
    }

    public void setImage(String image) {
	this.image = image;
    }

    public User getChef() {
	return this.chef;
    }

    public void setChef(User chef) {
	this.chef = chef;
    }

    public float getRate() {
	return this.rate;
    }

    public void setRate(float rate) {
	this.rate = rate;
    }

    public double getPrice() {
	return this.price;
    }

    public void setPrice(double price) {
	this.price = price;
    }

    public Date getDeliveryTime() {
	return this.deliveryTime;
    }

    public void setDeliveryTime(Date delivery_time) {
	this.deliveryTime = delivery_time;
    }

    public Date getDisponibilityTime() {
	return this.disponibilityTime;
    }

    public void setDisponibilityTime(Date disponibility_time) {
	this.disponibilityTime = disponibility_time;
    }

    public Date getCreatedAt() {
	return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
	this.createdAt = createdAt;
    }

    public long getId() {
	return id;
    }

    public void setId(long id) {
	this.id = id;
    }

    public static LinkedList<Product> mockProducts() {
	LinkedList<Product> mocks = new LinkedList<Product>();
	for (int i = 0; i < Product.names.length; ++i) {
	    String u_name = User.names[i];
	    String u_last_name = User.last_names[i];
	    String u_username = User.usernames[i];
	    String u_email = User.emails[i];

	    User u = new User(u_name, u_last_name, u_username, u_email, "");
	    String p_name = Product.names[i];
	    String p_category = Product.categories[i];
	    String p_location = Product.locations[i];
	    String p_description = Product.descriptions[i];
	    float p_price = Product.prices[i];
	    String p_image = Product.images[i];

	    Product p = new Product(p_name, p_category, p_location,
		    p_description, p_image, u, p_price, null, null);
	    mocks.add(p);
	}
	return mocks;
    }
    
    public static HashMap<String, Product> mockProductsAsHash() {
	HashMap<String, Product> mocks = new HashMap<String, Product>();
	for (int i = 0; i < Product.names.length; ++i) {
	    String u_name = User.names[i];
	    String u_last_name = User.last_names[i];
	    String u_username = User.usernames[i];
	    String u_email = User.emails[i];

	    User u = new User(u_name, u_last_name, u_username, u_email, "");
	    String p_name = Product.names[i];
	    String p_category = Product.categories[i];
	    String p_location = Product.locations[i];
	    String p_description = Product.descriptions[i];
	    float p_price = Product.prices[i];

	    Product p = new Product(p_name, p_category, p_location,
		    p_description, "", u, p_price, null, null);
	    mocks.put(p.name, p);
	}
	return mocks;
    }

    private static LinkedList<Product> mockSearch(String query) {
	LinkedList<Product> mocks = Product.mockProducts();
	LinkedList<Product> results = new LinkedList<Product>();
	for (Product p : mocks) {
	    if (p.name.contains(query) 
		    || p.category.contains(query)
		    || p.chef.getUsername().contains(query)
		    || p.location.contains(query)) {
		results.add(p);
	    }
	}
	return results;
    }

    public static LinkedList<Product> findByName(String p_name) {
	// TODO
	// SEARCH QUERY
	return mockSearch(p_name);
    }

    public static LinkedList<Product> findByCategory(String p_category) {
	// TODO
	// SEARCH QUERY
	return mockSearch(p_category);
    }

    public static LinkedList<Product> findByPrice(String p_price) {
	// TODO
	// SEARCH QUERY
	return mockSearch(p_price);
    }

    public static LinkedList<Product> findByLocation(String p_location) {
	// TODO
	// SEARCH QUERY
	return mockSearch(p_location);
    }

    public static LinkedList<Product> findByRegex(String query) {
	return Product.mockSearch(query);
    }
}
