package com.socialchef.service.models;

import java.util.Date;

public class Product {

    private String name, category, location, description, image;
    private User chef;
    private float rate = 0.0f;
    private double price;
    private Date delivery_time, disponibility_time;
// TESTING
    public static String[] names = { "Plato1", "Plato2", "Plato3" };
    public static String[] categories = { "pastas", "carne", "ensalada" };
    public static String[] locations = { "Poblado", "Laureles", "Envigado" };
    public static String[] descriptions = { "Descripcion1", "Descripcion2",
        "Descripcion3" };
    public static float[] prices = { 18500.0f, 12300.0f, 5000.0f };
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
	this.delivery_time = delivery_time;
	this.disponibility_time = disponibility_time;
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

    public Date getDelivery_time() {
	return this.delivery_time;
    }

    public void setDelivery_time(Date delivery_time) {
	this.delivery_time = delivery_time;
    }

    public Date getDisponibility_time() {
	return this.disponibility_time;
    }

    public void setDisponibility_time(Date disponibility_time) {
	this.disponibility_time = disponibility_time;
    }

}
