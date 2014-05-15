package com.socialchef.service.controllers;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.socialchef.service.exceptions.SocialChefException;
import com.socialchef.service.models.Product;
import com.socialchef.service.models.User;
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
    
    @RequestMapping(value = "/purchase", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,String> purchase(@RequestBody Map<String, String> body) {
    	int id = Integer.parseInt(body.get("id"));
    	String name = body.get("name");
    	double price = Double.parseDouble(body.get("price"));
    	Product p = new Product(name, "", price, "");
    	if (!p.validateProduct()) {
    		throw new SocialChefException(p.getErrors());
    	}else if (id <= 0 || id > Long.MAX_VALUE) {
    		throw new SocialChefException("Producto Invalido");
    	}
    	
    	p = productRepo.findById(id);
    	User unknown = 
    			new User(
    					"Unknown", "Unknown", "Unknown",
    					"Unknown@Unknown.com", "");
    	productRepo.purchase(p, unknown);
    	Map<String, String> response = new HashMap<String, String>();
    	response.put("msg", "Compra Satisfactoria");
    	return response;
    }

}
