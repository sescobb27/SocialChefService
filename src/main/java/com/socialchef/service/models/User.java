package com.socialchef.service.models;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicLong;

import com.socialchef.service.helpers.Validator;

public class User {

    private String name, lastName, userName, email, picture;
    private long id;
    private float rate = 0.0f;
    private LinkedList<Product> products;
    private Date createdAt;
    private static AtomicLong incrementalId = new AtomicLong(0);
    private LinkedList<String> errors;

    // TESTING
    public static String[] names = { "Simon", "Edgardo", "Juan", "Camilo" };
    public static String[] last_names = { "Escobar", "Sierra", "Norenia",
	    "Mejia" };
    public static String[] usernames = { "sescob", "easierra", "jknore",
	    "jcmejia" };
    public static String[] emails = { "sescob@gmail.com", "easierra@gmail.com",
	    "jknore@gmail.com", "jcmejia@gmail.com" };

    // END TESTING

    public User(String name, String last_name, String username, String email,
	    String picture) {
	this.name = name.trim();
	this.lastName = last_name.trim();
	this.userName = username.trim();
	this.email = email.trim();
	this.picture = picture.trim();
	this.products = new LinkedList<Product>();
	this.id = User.incrementalId.incrementAndGet();
    }

    public String getName() {
	return this.name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getLastName() {
	return this.lastName;
    }

    public void setLastName(String last_name) {
	this.lastName = last_name;
    }

    public String getUsername() {
	return this.userName;
    }

    public void setUsername(String username) {
	this.userName = username;
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
	this.products.add(p);
    }

    public LinkedList<Product> getProducts() {
	return this.products;
    }

    public Date getCreatedAt() {
	return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
	this.createdAt = createdAt;
    }

    public boolean removeProduct(Product p) {
	return this.products.remove(p);
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


    public boolean validateUser () {
        errors = new LinkedList<String>();
        if ( !Validator.validateNames(this.name) )
            errors.push("Invalid Name Format");
        if ( !Validator.validateNames(this.lastName) )
            errors.push("Invalid Last Name Format");
        if ( !Validator.validateUniqueNames(this.userName) )
            errors.push("Invalid Username Format");
    
        return errors.isEmpty();
    }
    
    public LinkedList<String> getErrors() {
        return this.errors;
    }

    @Override
    public String toString() {
        return String.format("%s: %s %s",
        	this.name, this.lastName, this.userName);
    }
}
