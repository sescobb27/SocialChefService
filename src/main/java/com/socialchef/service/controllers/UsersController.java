package com.socialchef.service.controllers;

import java.util.LinkedList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.socialchef.service.exceptions.InvalidDataException;
import com.socialchef.service.models.User;
import com.socialchef.service.repositories.implementation.UserServiceRepository;

@Controller
public class UsersController {

	@Autowired
	private UserServiceRepository userRepo; 
	
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
	@ResponseStatus(HttpStatus.NO_CONTENT) // 204
	public void createNewUser(@RequestBody Map<String, String> body) {
		String name = body.get("name");
		String lastName = body.get("lastName");
		String userName = body.get("userName");
		String email = body.get("email");
		String password = body.get("password");
		User user = new User(name, lastName, userName, email, password);
		user.makePasswordSalt();
		if (userRepo.create(user)) {
			return;
		}else if (user.hasErrors()) {
			throw new InvalidDataException(user.getErrors());
		}
	}
}
