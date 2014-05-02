package com.socialchef.service.models;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the products_discounts database table.
 * 
 */
@Entity
@Table(name="products_discounts")
@NamedQuery(name="ProductsDiscount.findAll", query="SELECT p FROM ProductsDiscount p")
public class ProductsDiscount implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="PRODUCTS_DISCOUNTS_ID_GENERATOR",
		sequenceName="PRODUCTS_DISCOUNTS_ID_SEQUENCE", initialValue=1, allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PRODUCTS_DISCOUNTS_ID_GENERATOR")
	@Column(unique=true, nullable=false)
	private Integer id;

	//bi-directional many-to-one association to Discount
	@ManyToOne
	@JoinColumn(name="discount_id", nullable=false)
	private Discount discount;

	//bi-directional many-to-one association to Product
	@ManyToOne
	@JoinColumn(name="product_id", nullable=false)
	private Product product;

	public ProductsDiscount() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Discount getDiscount() {
		return this.discount;
	}

	public void setDiscount(Discount discount) {
		this.discount = discount;
	}

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}