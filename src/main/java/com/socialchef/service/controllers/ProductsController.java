package com.socialchef.service.controllers;

import java.util.LinkedList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.socialchef.service.models.Product;
import com.socialchef.service.models.User;

@Controller
public class ProductsController {

    LinkedList<Product> mocks;

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    @ResponseBody
    public LinkedList<Product> getAllProducts() {
	mocks = new LinkedList<Product>();

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
	    mocks.add(p);
	}

	return mocks;
    }
}
