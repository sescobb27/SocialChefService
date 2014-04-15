package com.socialchef.service.models;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;

import javax.persistence.*;

import org.bouncycastle.util.encoders.Hex;

import com.socialchef.service.helpers.Validator;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;


/**
 * The persistent class for the users database table.
 *
 */
@Entity
@Table(name="users")
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="USERS_ID_GENERATOR", sequenceName="USERS_ID_SEQUENCE")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="USERS_ID_GENERATOR")
	@Column(name="id", unique=true, nullable=false)
	private Integer id;

	@Column(name="created_at", nullable=false)
	private Timestamp createdAt;

	@Column(name="email", nullable=false, length=50, unique=true)
	private String email;

	@Column(name="lastname", nullable=false, length=50)
	private String lastName;

	@Column(name="name", nullable=false, length=50)
	private String name;

	@Column(name="password_hash", nullable=false, length=100)
	private String passwordHash;

	@Column(name="rate", nullable=false)
	private float rate;

	@Column(name="username", nullable=false, length=20, unique=true)
	private String username;

	//bi-directional many-to-one association to Discount
	@OneToMany(mappedBy="user")
	private List<Discount> discounts;

	//bi-directional many-to-one association to Product
	@OneToMany(mappedBy="user")
	private List<Product> products;

	//bi-directional many-to-one association to Purchase
	@OneToMany(mappedBy="chef")
	private List<Purchase> purchases;

	//bi-directional many-to-one association to UsersUserType
	@OneToMany(mappedBy="user")
	private List<UsersUserType> usersUserTypes;

	@Transient
	private LinkedList<String> errors;
	// TESTING
	public static String[] names = { "Simon", "Edgardo", "Juan", "Camilo" };
	public static String[] last_names = { "Escobar", "Sierra", "Norenia",
	"Mejia" };
	public static String[] usernames = { "sescob", "easierra", "jknore",
	"jcmejia" };
	public static String[] emails = { "sescob@gmail.com", "easierra@gmail.com",
		"jknore@gmail.com", "jcmejia@gmail.com" };
	public static String[] passwords = {"qwerty", "123456", "AeIoU!@",
		"S3CUR3P455W0RD!\"#$%&/()="};

	// END TESTING

	public User() {

	}

	public User(String name, String last_name, String username, String email, 
			String passwordHash) {
		this.name = name.trim();
		this.lastName = last_name.trim();
		this.username = username.trim().toLowerCase();
		this.email = email.trim().toLowerCase();
		this.products = new LinkedList<Product>();
		this.passwordHash = passwordHash;
		this.createdAt = new Timestamp(new Date().getTime());
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Timestamp getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLastname() {
		return this.lastName;
	}

	public void setLastname(String lastName) {
		this.lastName = lastName;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.passwordHash;
	}

	public void setPassword(String password) throws Exception {
		this.passwordHash = encryptPassword(password);
	}

	public float getRate() {
		return this.rate;
	}

	public void setRate(float rate) {
		this.rate = rate;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<Discount> getDiscounts() {
		return this.discounts;
	}

	public void setDiscounts(List<Discount> discounts) {
		this.discounts = discounts;
	}

	public Discount addDiscount(Discount discount) {
		getDiscounts().add(discount);
		discount.setUser(this);

		return discount;
	}

	public Discount removeDiscount(Discount discount) {
		getDiscounts().remove(discount);
		discount.setUser(null);

		return discount;
	}

	public List<Product> getProducts() {
		return this.products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public Product addProduct(Product product) {
		getProducts().add(product);
		product.setUser(this);

		return product;
	}

	public Product removeProduct(Product product) {
		getProducts().remove(product);
		product.setUser(null);

		return product;
	}

	public List<Purchase> getPurchases() {
		return this.purchases;
	}

	public Purchase addPurchasesChef(Purchase chef) {
		getPurchases().add(chef);
		chef.setChef(this);

		return chef;
	}

	public Purchase removePurchaseChef(Purchase chef) {
		getPurchases().remove(chef);
		chef.setChef(null);

		return chef;
	}

	public List<UsersUserType> getUsersUserTypes() {
		return this.usersUserTypes;
	}

	public void setUsersUserTypes(List<UsersUserType> usersUserTypes) {
		this.usersUserTypes = usersUserTypes;
	}

	public UsersUserType addUsersUserType(UsersUserType usersUserType) {
		getUsersUserTypes().add(usersUserType);
		usersUserType.setUser(this);

		return usersUserType;
	}

	public UsersUserType removeUsersUserType(UsersUserType usersUserType) {
		getUsersUserTypes().remove(usersUserType);
		usersUserType.setUser(null);

		return usersUserType;
	}
	
	public void makePasswordSalt() {
		this.passwordHash = encryptPassword(this.passwordHash);
	}
	
	private String encryptPassword(String pass) {
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA-256");
			md.update(pass.getBytes("UTF-8"));
			md.update(this.createdAt.toString().getBytes("UTF-8"));
			byte[] digest = md.digest();
			return new String(Hex.encode(digest));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return pass;
	}

	public static LinkedList<User> mockUsers () {
		LinkedList<User> mocks = new LinkedList<User>();

		for (int i = 0; i < User.names.length; ++i) {
			String name = User.names[i];
			String last_name = User.last_names[i];
			String username = User.usernames[i];
			String email = User.emails[i];
			String password = User.passwords[i];

			mocks.add(new User(name, last_name, username, email, password));
		}
		return mocks;
	}

	public static HashMap<String, User> mockUsersAsHash () {
		HashMap<String, User> mocks = new HashMap<String, User>();

		for (int i = 0; i < User.names.length; ++i) {
			String name = User.names[i];
			String last_name = User.last_names[i];
			String username = User.usernames[i];
			String email = User.emails[i];
			String password = User.passwords[i];
			
			mocks.put(username,
					new User(name, last_name, username, email, password));
		}
		return mocks;
	}

	public boolean validateUser () {
		errors = new LinkedList<String>();
		if ( !Validator.validateNames(this.name) )
			errors.push("Invalid Name Format");
		if ( !Validator.validateNames(this.lastName) )
			errors.push("Invalid Last Name Format");
		if ( !Validator.validateUniqueNames(this.username) )
			errors.push("Invalid Username Format");

		return errors.isEmpty();
	}

	public LinkedList<String> getErrors() {
		return this.errors;
	}
	
	public boolean hasErrors() {
		return this.errors.size() != 0;
	}
	
	public void addError(String error) {
		this.errors.push(error);
	}
}