package com.socialchef.service.models;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the products_categories database table.
 * 
 */
@Entity
@Table(name="products_categories")
@NamedQuery(name="ProductsCategory.findAll", query="SELECT p FROM ProductsCategory p")
public class ProductsCategory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="PRODUCTS_CATEGORIES_ID_GENERATOR", sequenceName="PRODUCTS_CATEGORIES_ID_SEQUENCE")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PRODUCTS_CATEGORIES_ID_GENERATOR")
	@Column(unique=true, nullable=false)
	private Integer id;

	//bi-directional many-to-one association to Category
	@ManyToOne
	@JoinColumn(name="category_id", nullable=false)
	private Category category;

	//bi-directional many-to-one association to Product
	@ManyToOne
	@JoinColumn(name="product_id", nullable=false)
	private Product product;

	public ProductsCategory() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}