package com.socialchef.service.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.socialchef.service.models.Product;
import com.socialchef.service.repositories.implementation.ProductServiceRepository;

@Controller
public class ProductsController {

	@Autowired
	private ProductServiceRepository productRepo;

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    @ResponseBody
    public List<Product> getAllProducts() {
    	return null;
    }

    // /products/findby?key=category&search_value=carne
    @RequestMapping(value = "/products/findby", method = RequestMethod.GET)
    @ResponseBody
    public List<Product> findBy(
	    @RequestParam(value = "key", required = false, defaultValue = "") String key,
	    @RequestParam(value = "search_value", required = false, defaultValue = "") String search_value) {
		System.out.println("key: " + key);
		System.out.println("search: " + search_value);
		try {
			switch (key) {
			case "name":
			    return productRepo.findByName(search_value);
			case "category":
			    return productRepo.findByCategory(search_value);
			case "location":
			    return productRepo.findByLocation(search_value);
			case "price":
			    return productRepo.findByPrice(Float.parseFloat(search_value));
			default:
				return productRepo.findByName(search_value);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
    }

}
