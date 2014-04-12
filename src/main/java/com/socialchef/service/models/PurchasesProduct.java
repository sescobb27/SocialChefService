package com.socialchef.service.models;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the purchases_products database table.
 * 
 */
@Entity
@Table(name="purchases_products")
@NamedQuery(name="PurchasesProduct.findAll", query="SELECT p FROM PurchasesProduct p")
public class PurchasesProduct implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="PURCHASES_PRODUCTS_ID_GENERATOR", sequenceName="PURCHASES_PRODUCTS_ID_SEQUENCE")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PURCHASES_PRODUCTS_ID_GENERATOR")
	@Column(unique=true, nullable=false)
	private Integer id;

	//bi-directional many-to-one association to Product
	@ManyToOne
	@JoinColumn(name="product_id", nullable=false)
	private Product product;

	//bi-directional many-to-one association to Purchase
	@ManyToOne
	@JoinColumn(name="purchase_id", nullable=false)
	private Purchase purchase;

	public PurchasesProduct() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Purchase getPurchas() {
		return this.purchase;
	}

	public void setPurchas(Purchase purchase) {
		this.purchase = purchase;
	}

}