package com.socialchef.service.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.socialchef.service.models.Bill;
import com.socialchef.service.repositories.implementation.BillServiceRepository;

@Controller
public class BillController {
	@Autowired
	private BillServiceRepository BillRepo;
	
	  @RequestMapping(value = "/bill", method = RequestMethod.GET)
	    @ResponseBody
	    public List<Bill> getAllProducts() {
	    	return BillRepo.selectAll();
	    }

}
