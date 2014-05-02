package com.socialchef.service.models;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the products_locations database table.
 * 
 */
@Entity
@Table(name="products_locations")
@NamedQuery(name="ProductsLocation.findAll", query="SELECT p FROM ProductsLocation p")
public class ProductsLocation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="PRODUCTS_LOCATIONS_ID_GENERATOR",
		sequenceName="PRODUCTS_LOCATIONS_ID_SEQUENCE", initialValue=1, allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PRODUCTS_LOCATIONS_ID_GENERATOR")
	@Column(unique=true, nullable=false)
	private Integer id;

	//bi-directional many-to-one association to Location
	@ManyToOne
	@JoinColumn(name="location_id", nullable=false)
	private Location location;

	//bi-directional many-to-one association to Product
	@ManyToOne
	@JoinColumn(name="product_id", nullable=false)
	private Product product;

	public ProductsLocation() {
	}

	public ProductsLocation(Location location, Product product) {
		this.location = location;
		this.product = product;
	}
	
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Location getLocation() {
		return this.location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}