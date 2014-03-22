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

    HashMap<String, User> mocks;

    private void fillUsers() {
	mocks = new HashMap<String, User>();

	for (int i = 0; i < User.names.length; ++i) {
	    String name = User.names[i];
	    String last_name = User.last_names[i];
	    String username = User.usernames[i];
	    String email = User.emails[i];

	    mocks.put(username, new User(name, last_name, username, email, ""));
	}
    }

    @RequestMapping(value = "/chefs", method = RequestMethod.GET)
    @ResponseBody
    public HashMap<String, User> indexRoute() {
	fillUsers();
	return mocks;
    }

    @RequestMapping(value = "/chefs/{username}", method = RequestMethod.GET)
    @ResponseBody
    public User getUserByUsername(@PathVariable String username) {
	fillUsers();
	return mocks.get(username);
    }
}
