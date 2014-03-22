package com.socialchef.service.models;

import java.util.HashMap;
import java.util.LinkedList;

public class User {

    private String name, last_name, username, email, picture;
    private long id;
    private float rate = 0.0f;
    private HashMap<String, Product> products;

    // TESTING
    public static String[] names = { "Simon", "Edgardo", "Juan", "Camilo" };
    public static String[] last_names = { "Escobar", "Sierra", "Noreña",
	    "Mejia" };
    public static String[] usernames = { "sescob", "easierra", "jknore",
	    "jcmejia" };
    public static String[] emails = { "sescob@gmail.com", "easierra@gmail.com",
	    "jknore@gmail.com", "jcmejia@gmail.com" };

    // END TESTING

    public User(String name, String last_name, String username, String email,
	    String picture) {
	this.name = name;
	this.last_name = last_name;
	this.username = username;
	this.email = email;
	this.picture = picture;
	this.products = new HashMap<String, Product>();
    }

    public String getName() {
	return this.name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getLast_name() {
	return this.last_name;
    }

    public void setLast_name(String last_name) {
	this.last_name = last_name;
    }

    public String getUsername() {
	return this.username;
    }

    public void setUsername(String username) {
	this.username = username;
    }

    public String getEmail() {
	return this.email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    public String getPicture() {
	return this.picture;
    }

    public void setPicture(String picture) {
	this.picture = picture;
    }

    public long getId() {
	return this.id;
    }

    public void setId(long id) {
	this.id = id;
    }

    public float getRate() {
	return this.rate;
    }

    public void setRate(float rate) {
	this.rate = rate;
    }

    public void addProduct(Product p) {
	this.products.put(p.getName(), p);
    }

    public HashMap<String, Product> getAllProducts() {
	return this.products;
    }

    public boolean removeProduct(Product p) {
	return this.products.remove(p.getName()) != null;
    }

    public static LinkedList<User> mockUsers () {
	LinkedList<User> mocks = new LinkedList<User>();

	for (int i = 0; i < User.names.length; ++i) {
	    String name = User.names[i];
	    String last_name = User.last_names[i];
	    String username = User.usernames[i];
	    String email = User.emails[i];

	    mocks.add(new User(name, last_name, username, email, ""));
	}
	return mocks;
    }
    
    public static HashMap<String, User> mockUsersAsHash () {
	HashMap<String, User> mocks = new HashMap<String, User>();

	for (int i = 0; i < User.names.length; ++i) {
	    String name = User.names[i];
	    String last_name = User.last_names[i];
	    String username = User.usernames[i];
	    String email = User.emails[i];

	    mocks.put(username, new User(name, last_name, username, email, ""));
	}
	return mocks;
    }
}
