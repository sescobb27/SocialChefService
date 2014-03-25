package com.socialchef.service.controllers;

import java.util.LinkedList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.socialchef.service.models.Product;

@Controller
public class ProductsController {

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    @ResponseBody
    public LinkedList<Product> getAllProducts() {
	LinkedList<Product> mocks = Product.mockProducts();
	return mocks;
    }
    
    // /products/findby?key=category&search_value=carne
    @RequestMapping(value = "/products/findby", method = RequestMethod.GET)
    @ResponseBody
    public LinkedList<Product> findBy(
	    @RequestParam(value = "key", required = false, defaultValue = "match") String key,
	    @RequestParam(value = "search_value", required = false, defaultValue = "") String search_value) {
	System.out.println("key: " + key);
	System.out.println("search: " + search_value);
	switch (key) {
	case "name":
	    return Product.findByName(search_value);
	case "category":
	    return Product.findByCategory(search_value);
	case "location":
	    return Product.findByLocation(search_value);
	case "price":
	    return Product.findByPrice(search_value);
	default:
	    return Product.findByRegex(search_value);
	}
    }

}
