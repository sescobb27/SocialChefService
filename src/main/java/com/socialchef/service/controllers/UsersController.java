package com.socialchef.service.controllers;

import java.util.LinkedList;
import java.util.Map;
import java.util.logging.Logger;

import org.hibernate.usertype.LoggableUserType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.socialchef.service.exceptions.InvalidDataException;
import com.socialchef.service.models.User;

@Controller
public class UsersController {

	@RequestMapping(value = "/chefs", method = RequestMethod.GET)
	@ResponseBody
	public LinkedList<User> indexRoute() {
		return User.mockUsers();
	}

	@RequestMapping(value = "/chefs/{username}", method = RequestMethod.GET)
	@ResponseBody
	public User getUserByUsername(@PathVariable String username) {
		return User.mockUsersAsHash().get(username);
	}

	@RequestMapping(value="/chefs", method=RequestMethod.POST)
	@ResponseBody
	public User createNewUser(@RequestBody Map<String, String> body) {
		String name = body.get("name");
		String lastName = body.get("lastName");
		String userName = body.get("userName");
		String email = body.get("email");
		String password = body.get("password");
		User user = new User(name, lastName, userName, email, password);
		if (user.validateUser()) {
			return user;
		}
		throw new InvalidDataException(user.getErrors());
	}
}
