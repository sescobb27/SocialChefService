package com.socialchef.service.models;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the payment_types database table.
 *
 */
@Entity
@Table(name="payment_types")
@NamedQuery(name="PaymentType.findAll", query="SELECT p FROM PaymentType p")
public class PaymentType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="PAYMENT_TYPES_ID_GENERATOR", sequenceName="PAYMENT_TYPES_ID_SEQUENCE")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PAYMENT_TYPES_ID_GENERATOR")
	@Column(unique=true, nullable=false)
	private Integer id;

	@Column(nullable=false, length=20, unique=true)
	private String name;

	//bi-directional many-to-one association to ProductsPaymentType
	@OneToMany(mappedBy="paymentType")
	private Set<ProductsPaymentType> productsPaymentTypes;

	public PaymentType() {
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

	public Set<ProductsPaymentType> getProductsPaymentTypes() {
		return this.productsPaymentTypes;
	}

	public void setProductsPaymentTypes(Set<ProductsPaymentType> productsPaymentTypes) {
		this.productsPaymentTypes = productsPaymentTypes;
	}

	public ProductsPaymentType addProductsPaymentType(ProductsPaymentType productsPaymentType) {
		getProductsPaymentTypes().add(productsPaymentType);
		productsPaymentType.setPaymentType(this);

		return productsPaymentType;
	}

	public ProductsPaymentType removeProductsPaymentType(ProductsPaymentType productsPaymentType) {
		getProductsPaymentTypes().remove(productsPaymentType);
		productsPaymentType.setPaymentType(null);

		return productsPaymentType;
	}

}