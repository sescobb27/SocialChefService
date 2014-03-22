package com.socialchef.service.controllers;

import java.util.HashMap;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.socialchef.service.models.User;

@Controller
public class UsersController {

    @RequestMapping(value = "/chefs", method = RequestMethod.GET)
    @ResponseBody
    public HashMap<String, User> indexRoute() {
	return User.mockUsers();
    }

    @RequestMapping(value = "/chefs/{username}", method = RequestMethod.GET)
    @ResponseBody
    public User getUserByUsername(@PathVariable String username) {
	return User.mockUsers().get(username);
    }
}
