package com.socialchef.service.models;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="bill")
@NamedQuery(name="Bill.findAll", query="SELECT p FROM Bill p")
public class Bill implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4269786076143361586L;

	@Id
	@SequenceGenerator(name="BILL_ID_GENERATOR", sequenceName="BILL_ID_SEQUENCE")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="BILL_ID_GENERATOR")
	@Column(unique=true, nullable=false)
	private Integer id;
	
	@Column(name="transaction_id", length=1000)
	private Integer transaction_id;
	
	@Column(name="chef_id", length=1000)
	private Integer chef_id;
	@Column(name="date", length=1000)
	private Timestamp 	date;
	
	@Column(name="productName", length=1000)
	private String productName;
	
	@Column(name="customerName", length=1000)
	private String customerName;
	
	@Column(name="unitaryCost", length=1000)
	private Double unitaryCost ;
	
	@Column(name="totalCost", length=1000)
	private Double totalCost
	;
	@Column(name="comission", length=1000)
	private Double comission;
	
	
	
	
	
	
	public Integer getChef_id() {
		return chef_id;
	}
	public void setChef_id(Integer chef_id) {
		this.chef_id = chef_id;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getTransaction_id() {
		return transaction_id;
	}
	public void setTransaction_id(Integer transaction_id) {
		this.transaction_id = transaction_id;
	}
	public Timestamp getDate() {
		return date;
	}
	public void setDate(Timestamp date) {
		this.date = date;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public Double getUnitaryCost() {
		return unitaryCost;
	}
	public void setUnitaryCost(Double unitaryCost) {
		this.unitaryCost = unitaryCost;
	}
	public Double getTotalCost() {
		return totalCost;
	}
	public void setTotalCost(Double totalCost) {
		this.totalCost = totalCost;
	}
	public Double getComission() {
		return comission;
	}
	public void setComission(Double comission) {
		this.comission = comission;
	}
	
	

}
