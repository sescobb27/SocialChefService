package com.socialchef.service.models;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the discounts database table.
 * 
 */
@Entity
@Table(name="discounts")
@NamedQuery(name="Discount.findAll", query="SELECT d FROM Discount d")
public class Discount implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="DISCOUNTS_ID_GENERATOR", sequenceName="DISCOUNTS_ID_SEQUENCE")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="DISCOUNTS_ID_GENERATOR")
	@Column(unique=true, nullable=false)
	private Integer id;

	@Temporal(TemporalType.DATE)
	@Column(name="created_at", nullable=false)
	private Date createdAt;

	@Column(nullable=false, length=2147483647)
	private String description;

	@Column(name="discount_percent")
	private float discountPercent;

	@Temporal(TemporalType.DATE)
	@Column(name="finish_at", nullable=false)
	private Date finishAt;

	@Column(nullable=false)
	private Boolean finished;

	@Column(length=100)
	private String title;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="created_by", nullable=false)
	private User user;

	//bi-directional many-to-one association to ProductsDiscount
	@OneToMany(mappedBy="discount")
	private List<ProductsDiscount> productsDiscounts;

	//bi-directional many-to-one association to PurchasesDiscount
	@OneToMany(mappedBy="discount")
	private List<PurchasesDiscount> purchasesDiscounts;

	public Discount() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public float getDiscountPercent() {
		return this.discountPercent;
	}

	public void setDiscountPercent(float discountPercent) {
		this.discountPercent = discountPercent;
	}

	public Date getFinishAt() {
		return this.finishAt;
	}

	public void setFinishAt(Date finishAt) {
		this.finishAt = finishAt;
	}

	public Boolean getFinished() {
		return this.finished;
	}

	public void setFinished(Boolean finished) {
		this.finished = finished;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<ProductsDiscount> getProductsDiscounts() {
		return this.productsDiscounts;
	}

	public void setProductsDiscounts(List<ProductsDiscount> productsDiscounts) {
		this.productsDiscounts = productsDiscounts;
	}

	public ProductsDiscount addProductsDiscount(ProductsDiscount productsDiscount) {
		getProductsDiscounts().add(productsDiscount);
		productsDiscount.setDiscount(this);

		return productsDiscount;
	}

	public ProductsDiscount removeProductsDiscount(ProductsDiscount productsDiscount) {
		getProductsDiscounts().remove(productsDiscount);
		productsDiscount.setDiscount(null);

		return productsDiscount;
	}

	public List<PurchasesDiscount> getPurchasesDiscounts() {
		return this.purchasesDiscounts;
	}

	public void setPurchasesDiscounts(List<PurchasesDiscount> purchasesDiscounts) {
		this.purchasesDiscounts = purchasesDiscounts;
	}

	public PurchasesDiscount addPurchasesDiscount(PurchasesDiscount purchasesDiscount) {
		getPurchasesDiscounts().add(purchasesDiscount);
		purchasesDiscount.setDiscount(this);

		return purchasesDiscount;
	}

	public PurchasesDiscount removePurchasesDiscount(PurchasesDiscount purchasesDiscount) {
		getPurchasesDiscounts().remove(purchasesDiscount);
		purchasesDiscount.setDiscount(null);

		return purchasesDiscount;
	}

}