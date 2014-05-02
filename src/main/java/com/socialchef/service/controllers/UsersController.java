package com.socialchef.service.controllers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.bouncycastle.crypto.tls.ContentType;
import org.bouncycastle.util.encoders.Hex;
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

import com.socialchef.service.exceptions.SocialChefException;
import com.socialchef.service.helpers.Encryption;
import com.socialchef.service.helpers.Validator;
import com.socialchef.service.models.Category;
import com.socialchef.service.models.Location;
import com.socialchef.service.models.Product;
import com.socialchef.service.models.ProductsCategory;
import com.socialchef.service.models.ProductsLocation;
import com.socialchef.service.models.User;
import com.socialchef.service.repositories.implementation.ProductServiceRepository;
import com.socialchef.service.repositories.implementation.UserServiceRepository;
import com.socialchef.service.repositories.implementation.ValuesServiceRepository;

@Controller
public class UsersController {

	@Autowired
	private UserServiceRepository userRepo;
	@Autowired
	private ProductServiceRepository productRepo;
	@Autowired
	private ValuesServiceRepository valuesRepo;

	@RequestMapping(value = "/chefs", method = RequestMethod.GET)
	@ResponseBody
	public List<User> indexRoute() {
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
			session.setAttribute(session_id, userName);
			session.setMaxInactiveInterval(1200);
			return;
		}else if (user.hasErrors()) {
			throw new SocialChefException(user.getErrors());
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
			@RequestParam(value = "image") MultipartFile image,
			@RequestParam(value= "categories") List<String> categories,
			@RequestParam(value= "locations") List<String> locations ) {

		String uname = (String) session.getAttribute(session_id);
		if( username == null || username.length() == 0 ||
				uname == null || !username.equalsIgnoreCase(uname) ) {
			throw new SocialChefException(
					"Necesitas iniciar sesion para agregar un producto");
		}
//======validate image existence and content type
		String imagePath = null;
		if( image != null && !image.isEmpty() && 
				Validator.validateImageContentType(image.getContentType())) {
//======save image and return its path
			imagePath = saveImage(username, image);
		} else {
			throw new SocialChefException(
					"El producto que deseas subir necesita una imagen");
		}
		Product p = new Product(productname, "", Double.parseDouble(price),
				imagePath);
//======fecth logged user
		p.setUser(userRepo.findByUsername(username));
		
//======fetch selected categories and locations
		List<Category> p_categories =
				valuesRepo.getCategoriesByName(categories);
		List<Location> p_locations = 
				valuesRepo.getLocationsByName(locations);
		
//======populate join tables
		List<ProductsCategory> p_cat = new LinkedList<ProductsCategory>();
		for (Category c : p_categories) {
			p_cat.add(new ProductsCategory(c, p));
		}
		p.setProductsCategories(p_cat);
		
		List<ProductsLocation> p_loc = new LinkedList<ProductsLocation>();
		for (Location l : p_locations) {
			p_loc.add(new ProductsLocation(l, p));
		}
		p.setProductsLocations(p_loc);
		
//======create product
		if ( !productRepo.create(p) )
			throw new SocialChefException(p.getErrors());
	}

	private String saveImage(String username, MultipartFile image) {
		MessageDigest md;
		String name;
		try {
//			get unique name from Time.now and image content with MD5
			md = MessageDigest.getInstance("MD5");
			Timestamp now = new Timestamp(new Date().getTime());
			md.update(image.getName().getBytes("UTF-8"));
			md.update(now.toString().getBytes("UTF-8"));
			byte[] digest = md.digest();
//			name is Unique
			name =  new String(Hex.encode(digest));
			String assets_dir = "socialchef/images/";
			String file_name =
					String.format("%s_%s", username, name);
//			File name is username_UNIQUENAME.jpg
			File file = new File(
					String.format("%s%s", assets_dir, file_name));
//			try with resource
			try ( FileOutputStream stream = new FileOutputStream(file) ) {
				stream.write(image.getBytes());
			}
			String host_dir = "http://localhost:3000/";
			return host_dir + file_name;
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
	@ResponseBody
	public List<Product> listProducts(@CookieValue(value="session_id",
			defaultValue="", required=true) String session_id,
			HttpSession session) {
		String username;
		synchronized (session) {
			username = (String) session.getAttribute(session_id);
			if( username == null || username.length() == 0 ) {
				throw new SocialChefException(
						"Necesitas iniciar sesion para listar tus productos");
			}
		}
		return productRepo.findByUserName(username);
	}

	private String makeSessionId(HttpServletRequest request) {
		String IP = request.getRemoteAddr();
		Timestamp now = new Timestamp(new Date().getTime());
		String []data = {IP, now.toString()};
		try {
			return Encryption.encryptMD5(data);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "";
	}

	@RequestMapping(value="/chefs/login", method=RequestMethod.POST)
	@ResponseBody
	public Map<String,String> login(@CookieValue(value="session_id",
			defaultValue="", required=true) String session_id,
			HttpServletResponse response, HttpServletRequest request,
			HttpSession session, @RequestBody Map<String, String> body) {
		String uname = body.get("username");
		String username = (String) session.getAttribute(session_id);
		Map<String, String> result;
		if (uname == null || uname.length() == 0) {
			throw new SocialChefException("Usuario Invalido");
		} else if( username == null || username.length() == 0 ||
				!username.equalsIgnoreCase(uname) ) {
			String password = body.get("password");
			if ( userRepo.validateLogin(uname, password) ) {
				session_id = makeSessionId(request);
				Cookie cookie = new Cookie("session_id", session_id);
//				cookie.setSecure(true);
				cookie.setMaxAge(1200); // 20min
				response.addCookie(cookie);
				session.setAttribute(session_id, uname);
				session.setMaxInactiveInterval(1200);
				result = new HashMap<>();
				result.put("username", uname);
				return result;
			}
		} else if ( username.equalsIgnoreCase(uname) ) {
			result = new HashMap<>();
			result.put("username", uname);
			return result;
		}
		throw new SocialChefException("Usuario Invalido");
	}
}
