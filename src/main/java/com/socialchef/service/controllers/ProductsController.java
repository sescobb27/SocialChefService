package com.socialchef.service.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    	return productRepo.findByRate();
    }

    // /products/findby?key=category&search_value=carne
    @RequestMapping(value = "/products/findby", method = RequestMethod.GET)
    @ResponseBody
    public Set<Product> findBy(
    		@RequestParam(value = "search_value", required = false, defaultValue = "") 
    		String search_value) {
    	
    	Set<Product> result = new HashSet<Product>();
		result.addAll(productRepo.findByName(search_value));
	    result.addAll(productRepo.findByCategory(search_value));
	    result.addAll(productRepo.findByLocation(search_value));
	    return result;
    }

}
