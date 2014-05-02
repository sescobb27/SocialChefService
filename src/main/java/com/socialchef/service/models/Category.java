package com.socialchef.service.models;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the categories database table.
 *
 */
@Entity
@Table(name="categories")
@NamedQuery(name="Category.findAll", query="SELECT c FROM Category c")
public class Category implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CATEGORIES_ID_GENERATOR",
		sequenceName="CATEGORIES_ID_SEQUENCE", initialValue=1, allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CATEGORIES_ID_GENERATOR")
	@Column(unique=true, nullable=false)
	private Integer id;

	@Column(name="description", nullable=false, length=1000)
	private String description;

	@Column(name="name", nullable=false, length=50, unique=true)
	private String name;

	//bi-directional many-to-one association to ProductsCategory
	@OneToMany(mappedBy="category")
	private List<ProductsCategory> productsCategories;

	public Category() {
	}
	
	public Category(String name, String description) {
		this.name = name.trim();
		this.description = description.trim();
	}
	
	public Category(String name) {
		this.name = name.trim();
	}
	
	public Category(Integer id, String name) {
		this.id = id;
		this.name = name.trim();
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public List<ProductsCategory> getProductsCategories() {
		return this.productsCategories;
	}

	public void setProductsCategories(List<ProductsCategory> productsCategories) {
		this.productsCategories = productsCategories;
	}

	public ProductsCategory addProductsCategory(ProductsCategory productsCategory) {
		getProductsCategories().add(productsCategory);
		productsCategory.setCategory(this);

		return productsCategory;
	}

	public ProductsCategory removeProductsCategory(ProductsCategory productsCategory) {
		getProductsCategories().remove(productsCategory);
		productsCategory.setCategory(null);

		return productsCategory;
	}

	@Override
	public boolean equals(Object obj) {
		Category c = (Category) obj;
		return c.getName().equalsIgnoreCase(this.name) ||
				c.getDescription().equalsIgnoreCase(this.description);
	}

}