package com.socialchef.service.models;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the locations database table.
 *
 */
@Entity
@Table(name="locations")
@NamedQuery(name="Location.findAll", query="SELECT l FROM Location l")
public class Location implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="LOCATIONS_ID_GENERATOR", sequenceName="LOCATIONS_ID_SEQUENCE")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="LOCATIONS_ID_GENERATOR")
	@Column(unique=true, nullable=false)
	private Integer id;

	@Column(nullable=false, length=50, unique=true)
	private String name;

	//bi-directional many-to-one association to ProductsLocation
	@OneToMany(mappedBy="location")
	private List<ProductsLocation> productsLocations;

	public Location() {
	}
	
	public Location(String name) {
		this.name = name;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<ProductsLocation> getProductsLocations() {
		return this.productsLocations;
	}

	public void setProductsLocations(List<ProductsLocation> productsLocations) {
		this.productsLocations = productsLocations;
	}

	public ProductsLocation addProductsLocation(ProductsLocation productsLocation) {
		getProductsLocations().add(productsLocation);
		productsLocation.setLocation(this);

		return productsLocation;
	}

	public ProductsLocation removeProductsLocation(ProductsLocation productsLocation) {
		getProductsLocations().remove(productsLocation);
		productsLocation.setLocation(null);

		return productsLocation;
	}

	@Override
	public boolean equals(Object obj) {
		Location l = (Location) obj;
		return l.getName().equalsIgnoreCase(this.name);
	}

}