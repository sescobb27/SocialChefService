package com.socialchef.service.models;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the products_payment_types database table.
 * 
 */
@Entity
@Table(name="products_payment_types")
@NamedQuery(name="ProductsPaymentType.findAll", query="SELECT p FROM ProductsPaymentType p")
public class ProductsPaymentType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="PRODUCTS_PAYMENT_TYPES_ID_GENERATOR", sequenceName="PRODUCTS_PAYMENT_TYPES_ID_SEQUENCE")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PRODUCTS_PAYMENT_TYPES_ID_GENERATOR")
	@Column(unique=true, nullable=false)
	private Integer id;

	//bi-directional many-to-one association to PaymentType
	@ManyToOne
	@JoinColumn(name="payment_type_id", nullable=false)
	private PaymentType paymentType;

	//bi-directional many-to-one association to Product
	@ManyToOne
	@JoinColumn(name="product_id", nullable=false)
	private Product product;

	public ProductsPaymentType() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public PaymentType getPaymentType() {
		return this.paymentType;
	}

	public void setPaymentType(PaymentType paymentType) {
		this.paymentType = paymentType;
	}

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}