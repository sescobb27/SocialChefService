package com.socialchef.service.models;

import java.io.Serializable;

import javax.persistence.*;

import com.socialchef.service.helpers.Validator;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;
import java.util.LinkedList;

/**
 * The persistent class for the products database table.
 *
 */
@Entity
@Table(name="products")
@NamedQuery(name="Product.findAll", query="SELECT p FROM Product p")
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final float MAX_RATE = 5.0f;

	@Id
	@SequenceGenerator(name="PRODUCTS_ID_GENERATOR", sequenceName="PRODUCTS_ID_SEQUENCE")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PRODUCTS_ID_GENERATOR")
	@Column(unique=true, nullable=false)
	private Integer id;

	@Column(name="created_at", nullable=false)
	private Timestamp createdAt;

	@Column(name="description", length=1000)
	private String description;

	@Column(name="name", nullable=false, length=50)
	private String name;

	@Column(name="price", nullable=false)
	private double price;

	@Column(name="rate", nullable=false)
	private float rate;
	
	@Column(name="image", nullable=false)
	private String image = "images/default.png";

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="chef_id", nullable=false)
	private User user;

	//bi-directional many-to-one association to ProductsCategory
	@OneToMany(mappedBy="product")
	private Set<ProductsCategory> categories;

	//bi-directional many-to-one association to ProductsDiscount
	@OneToMany(mappedBy="product")
	private Set<ProductsDiscount> discounts;

	//bi-directional many-to-one association to ProductsLocation
	@OneToMany(mappedBy="product")
	private Set<ProductsLocation> locations;

	//bi-directional many-to-one association to ProductsPaymentType
	@OneToMany(mappedBy="product")
	private Set<ProductsPaymentType> paymentTypes;

	//bi-directional many-to-one association to PurchasesProduct
	@OneToMany(mappedBy="product")
	private Set<PurchasesProduct> purchasesProducts;

	@Transient
	private LinkedList<String> errors;

	public Product() {
	}

	public Product(String name, String description, double price,
			String image) {
		this.name = name;
		this.description = description;
		this.price = price;
		this.createdAt = new Timestamp(new Date().getTime());
		this.rate = 0.0f;
		this.errors = new LinkedList<String>();
		if (image != null && image.length() != 0)
			this.image = image; 
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

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return this.price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public float getRate() {
		return this.rate;
	}

	public void setRate(float rate) {
		this.rate = rate;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Set<ProductsCategory> getProductsCategories() {
		return this.categories;
	}

	public void setProductsCategories(Set<ProductsCategory> categories) {
		this.categories = categories;
	}

	public ProductsCategory addProductsCategory(ProductsCategory productsCategory) {
		getProductsCategories().add(productsCategory);
		productsCategory.setProduct(this);

		return productsCategory;
	}

	public ProductsCategory removeProductsCategory(ProductsCategory productsCategory) {
		getProductsCategories().remove(productsCategory);
		productsCategory.setProduct(null);

		return productsCategory;
	}

	public Set<ProductsDiscount> getProductsDiscounts() {
		return this.discounts;
	}

	public void setProductsDiscounts(Set<ProductsDiscount> discounts) {
		this.discounts = discounts;
	}

	public ProductsDiscount addProductsDiscount(ProductsDiscount productsDiscount) {
		getProductsDiscounts().add(productsDiscount);
		productsDiscount.setProduct(this);

		return productsDiscount;
	}

	public ProductsDiscount removeProductsDiscount(ProductsDiscount productsDiscount) {
		getProductsDiscounts().remove(productsDiscount);
		productsDiscount.setProduct(null);

		return productsDiscount;
	}

	public Set<ProductsLocation> getProductsLocations() {
		return this.locations;
	}

	public void setProductsLocations(Set<ProductsLocation> locations) {
		this.locations = locations;
	}

	public ProductsLocation addProductsLocation(ProductsLocation productsLocation) {
		getProductsLocations().add(productsLocation);
		productsLocation.setProduct(this);

		return productsLocation;
	}

	public ProductsLocation removeProductsLocation(ProductsLocation productsLocation) {
		getProductsLocations().remove(productsLocation);
		productsLocation.setProduct(null);

		return productsLocation;
	}

	public Set<ProductsPaymentType> getProductsPaymentTypes() {
		return this.paymentTypes;
	}

	public void setProductsPaymentTypes(Set<ProductsPaymentType> paymentTypes) {
		this.paymentTypes = paymentTypes;
	}

	public ProductsPaymentType addProductsPaymentType(ProductsPaymentType productsPaymentType) {
		getProductsPaymentTypes().add(productsPaymentType);
		productsPaymentType.setProduct(this);

		return productsPaymentType;
	}

	public ProductsPaymentType removeProductsPaymentType(ProductsPaymentType productsPaymentType) {
		getProductsPaymentTypes().remove(productsPaymentType);
		productsPaymentType.setProduct(null);

		return productsPaymentType;
	}

	public Set<PurchasesProduct> getPurchasesProducts() {
		return this.purchasesProducts;
	}

	public void setPurchasesProducts(Set<PurchasesProduct> purchasesProducts) {
		this.purchasesProducts = purchasesProducts;
	}

	public PurchasesProduct addPurchasesProduct(PurchasesProduct purchasesProduct) {
		getPurchasesProducts().add(purchasesProduct);
		purchasesProduct.setProduct(this);

		return purchasesProduct;
	}

	public PurchasesProduct removePurchasesProduct(PurchasesProduct purchasesProduct) {
		getPurchasesProducts().remove(purchasesProduct);
		purchasesProduct.setProduct(null);

		return purchasesProduct;
	}
	
	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public boolean validateProduct () {
		if ( !Validator.validateUniqueNames(this.name) )
			addError("Invalid Product Name Format");
		if ( this.price < 0.0 )
			addError("Price Should be greater than 0");
		if ( this.rate < 0.0f && this.rate > MAX_RATE )
			addError("Rate should be between 0.0 and 5.0");
		return hasErrors();
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

	@Override
	public boolean equals(Object obj) {
		Product p = (Product) obj;
		return p.getUser().equals(this.user) &&
				p.getName().equalsIgnoreCase(this.name);
	}
}