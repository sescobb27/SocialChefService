package com.socialchef.service.models;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the purchases_discounts database table.
 * 
 */
@Entity
@Table(name="purchases_discounts")
@NamedQuery(name="PurchasesDiscount.findAll", query="SELECT p FROM PurchasesDiscount p")
public class PurchasesDiscount implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="PURCHASES_DISCOUNTS_ID_GENERATOR", sequenceName="PURCHASES_DISCOUNTS_ID_SEQUENCE")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PURCHASES_DISCOUNTS_ID_GENERATOR")
	@Column(unique=true, nullable=false)
	private Integer id;

	//bi-directional many-to-one association to Discount
	@ManyToOne
	@JoinColumn(name="discount_id", nullable=false)
	private Discount discount;

	//bi-directional many-to-one association to Purchase
	@ManyToOne
	@JoinColumn(name="purchase_id", nullable=false)
	private Purchase purchase;

	public PurchasesDiscount() {
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

	public Purchase getPurchase() {
		return this.purchase;
	}

	public void setPurchase(Purchase purchase) {
		this.purchase = purchase;
	}

}