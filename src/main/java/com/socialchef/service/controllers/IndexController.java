package com.socialchef.service.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.socialchef.service.models.Category;
import com.socialchef.service.models.Location;
import com.socialchef.service.repositories.implementation.ValuesServiceRepository;

@Controller
public class IndexController {

	@Autowired
	private ValuesServiceRepository valuesRepo;

	@RequestMapping(value="/")
	public String index() {
		return "resources/index.html";
	}

	@RequestMapping(value="/categories", method=RequestMethod.GET)
	@ResponseBody
	public List<Category> getCategories() {
		return valuesRepo.getCategories();
	}

	@RequestMapping(value="/locations", method=RequestMethod.GET)
	@ResponseBody
	public List<Location> getLocations() {
		return valuesRepo.getLocations();
	}
}
