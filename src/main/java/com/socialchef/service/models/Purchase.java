package com.socialchef.service.models;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the purchases database table.
 *
 */
@Entity
@Table(name="purchases")
@NamedQuery(name="Purchase.findAll", query="SELECT p FROM Purchase p")
public class Purchase implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="PURCHASES_ID_GENERATOR", sequenceName="PURCHASES_ID_SEQUENCE")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PURCHASES_ID_GENERATOR")
	@Column(unique=true, nullable=false)
	private Integer id;

	@Column(name="total_price", nullable=false)
	private double totalPrice;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="chef_id", nullable=false)
	private User chef;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="purchaser_id", nullable=false)
	private User purchaser;

	//bi-directional many-to-one association to PurchasesDiscount
	@OneToMany(mappedBy="purchase")
	private List<PurchasesDiscount> purchasesDiscounts;

	//bi-directional many-to-one association to PurchasesProduct
	@OneToMany(mappedBy="purchase")
	private List<PurchasesProduct> purchasesProducts;

	public Purchase() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public double getTotalPrice() {
		return this.totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public User getChef() {
		return this.chef;
	}

	public void setChef(User chef) {
		this.chef = chef;
	}

	public User getPurchaser() {
		return this.purchaser;
	}

	public void setPurchaser(User purchaser) {
		this.purchaser = purchaser;
	}

	public List<PurchasesDiscount> getPurchasesDiscounts() {
		return this.purchasesDiscounts;
	}

	public void setPurchasesDiscounts(List<PurchasesDiscount> purchasesDiscounts) {
		this.purchasesDiscounts = purchasesDiscounts;
	}

	public PurchasesDiscount addPurchasesDiscount(PurchasesDiscount purchasesDiscount) {
		getPurchasesDiscounts().add(purchasesDiscount);
		purchasesDiscount.setPurchase(this);

		return purchasesDiscount;
	}

	public PurchasesDiscount removePurchasesDiscount(PurchasesDiscount purchasesDiscount) {
		getPurchasesDiscounts().remove(purchasesDiscount);
		purchasesDiscount.setPurchase(null);

		return purchasesDiscount;
	}

	public List<PurchasesProduct> getPurchasesProducts() {
		return this.purchasesProducts;
	}

	public void setPurchasesProducts(List<PurchasesProduct> purchasesProducts) {
		this.purchasesProducts = purchasesProducts;
	}

	public PurchasesProduct addPurchasesProduct(PurchasesProduct purchasesProduct) {
		getPurchasesProducts().add(purchasesProduct);
		purchasesProduct.setPurchase(this);

		return purchasesProduct;
	}

	public PurchasesProduct removePurchasesProduct(PurchasesProduct purchasesProduct) {
		getPurchasesProducts().remove(purchasesProduct);
		purchasesProduct.setPurchase(null);

		return purchasesProduct;
	}

}