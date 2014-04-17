package com.socialchef.service.controllers;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.util.encoders.Hex;
import org.hibernate.SessionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;

import com.socialchef.service.exceptions.InvalidDataException;
import com.socialchef.service.models.Product;
import com.socialchef.service.models.User;
import com.socialchef.service.repositories.implementation.ProductServiceRepository;
import com.socialchef.service.repositories.implementation.UserServiceRepository;

@Controller
public class UsersController {

	@Autowired
	private UserServiceRepository userRepo;
	@Autowired
	private ProductServiceRepository productRepo;
	
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
			HttpServletResponse response, HttpServletRequest request,
			HttpSession session) {
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
	
	@RequestMapping(value="/chefs/addproduct", method=RequestMethod.POST)
	@ResponseBody
	public void addProduct(HttpSession session,
			@CookieValue(value="session_id", 
				defaultValue="", required=true) String session_id,
			@RequestParam(value = "username",
				required = true, defaultValue = "") String username,
			@RequestParam(value = "productname",
				required = true, defaultValue = "") String productname,
			@RequestParam(value = "price",
				required = true, defaultValue = "0.0") String price,
			@RequestParam(value = "image") MultipartFile image) {
		
		String imagePath;
		if( image != null && !image.isEmpty() )
			imagePath = saveImage(image);
//		synchronized (session) {
//			username = (String) session.getAttribute(session_id);
//			if( username == null || username.length() == 0 ||
//					uname == null || !username.equalsIgnoreCase(uname) ) {
//				throw new SessionException("Necesitas iniciar sesion para agregar un producto");
//			}
//		}
		Product p = new Product(productname, "", Double.parseDouble(price));
		p.setUser(userRepo.findByUsername(username));
		if ( productRepo.create(p) )
			throw new InvalidDataException(p.getErrors());
	}
	
	private String saveImage(MultipartFile image) {
		MessageDigest md;
		String name;
		try {
			md = MessageDigest.getInstance("MD5");
			Timestamp now = new Timestamp(new Date().getTime());
			md.update(image.getName().getBytes("UTF-8"));
			md.update(now.toString().getBytes("UTF-8"));
			byte[] digest = md.digest();
			name =  new String(Hex.encode(digest));
			File file = new File(name+".jpg");
//			try with resource
			try ( FileOutputStream stream = new FileOutputStream(file) ) {
				stream.write(image.getBytes());
			}
			String path = file.getPath();
			String abPath = file.getAbsolutePath();
			return file.getPath();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value="/chefs/listproducts", method=RequestMethod.GET)
	public Set<Product> listProducts(@CookieValue(value="session_id",
			defaultValue="", required=true) String session_id,
			HttpSession session) {
		String username;
		synchronized (session) {
			username = (String) session.getAttribute(session_id);
			if( username == null || username.length() == 0 ) {
				throw new SessionException("Necesitas iniciar sesion para agregar un producto");
			}
		}
		return productRepo.findByUserName(username);
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
