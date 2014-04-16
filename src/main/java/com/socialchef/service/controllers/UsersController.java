package com.socialchef.service.controllers;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.LinkedList;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.bouncycastle.util.encoders.Hex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
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
	@Autowired
	private HttpSession session;
	
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
	public void createNewUser(@RequestBody Map<String, String> body,
			HttpServletResponse response, HttpServletRequest request) {
		String name = body.get("name");
		String lastName = body.get("lastName");
		String userName = body.get("userName");
		String email = body.get("email");
		String password = body.get("password");
		User user = new User(name, lastName, userName, email, password);
		user.makePasswordSalt();
		if (userRepo.create(user)) {
			String session_id = makeSessionId(request);
			Cookie cookie = new Cookie("session_id", session_id);
//			cookie.setSecure(true);
			cookie.setMaxAge(1200); // 20min
			response.addCookie(cookie);
			synchronized (session) {
				session.setAttribute(session_id, userName);
				session.setMaxInactiveInterval(1200);
			}
			return;
		}else if (user.hasErrors()) {
			throw new InvalidDataException(user.getErrors());
		}
	}
	
	private String makeSessionId(HttpServletRequest request) {
		MessageDigest md;
		String IP = request.getRemoteAddr();
		try {
			md = MessageDigest.getInstance("SHA-256");
			Timestamp now = new Timestamp(new Date().getTime());
			md.update(IP.getBytes("UTF-8"));
			md.update(now.toString().getBytes("UTF-8"));
			byte[] digest = md.digest();
			return new String(Hex.encode(digest));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "";
	}
}
