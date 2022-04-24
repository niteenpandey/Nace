package com.db.nace.model;

import javax.persistence.*;

@Entity
//@Table(name="Nace")
public class Nace {
	
	@Id
	@Column(name = "orderid" , columnDefinition = "varchar(max)")
	private String orderid;
	
	@Column(name = "level" , columnDefinition = "varchar(max)")
	private String level;
	
	@Column(name = "code", columnDefinition = "varchar(max)")
	private String code;
	
	@Column(name = "parent", columnDefinition = "varchar(max)")
	private String parent;
	
	@Column(name = "discription" , columnDefinition = "varchar(max)")
	private String discription;
	
	@Column(name = "itemInclude", columnDefinition = "varchar(max)")
	private String itemInclude;
	
	@Column(name = "itemAlsoInclude", columnDefinition = "varchar(max)")
	private String itemAlsoInclude;
	
	@Column(name = "ruling", columnDefinition = "varchar(max)")
	private String ruling;
	
	@Column(name = "itemExcludes", columnDefinition = "varchar(max)")
	private String itemExcludes;
	
	@Column(name = "refrenceToISIC")
	private String refrenceToISIC;
	
		
	public Nace(String orderid, String level, String code, String parent, String discription, String itemInclude,
			String itemAlsoInclude, String ruling, String itemExcludes, String refrenceToISIC) {
		super();
		this.orderid = orderid;
		this.level = level;
		this.code = code;
		this.parent = parent;
		this.discription = discription;
		this.itemInclude = itemInclude;
		this.itemAlsoInclude = itemAlsoInclude;
		this.ruling = ruling;
		this.itemExcludes = itemExcludes;
		this.refrenceToISIC = refrenceToISIC;
	}

	public Nace() {
		
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	public String getDiscription() {
		return discription;
	}

	public void setDiscription(String discription) {
		this.discription = discription;
	}

	public String getItemInclude() {
		return itemInclude;
	}

	public void setItemInclude(String itemInclude) {
		this.itemInclude = itemInclude;
	}

	public String getItemAlsoInclude() {
		return itemAlsoInclude;
	}

	public void setItemAlsoInclude(String itemAlsoInclude) {
		this.itemAlsoInclude = itemAlsoInclude;
	}

	public String getRuling() {
		return ruling;
	}

	public void setRuling(String ruling) {
		this.ruling = ruling;
	}

	public String getItemExcludes() {
		return itemExcludes;
	}

	public void setItemExcludes(String itemExcludes) {
		this.itemExcludes = itemExcludes;
	}

	public String getRefrenceToISIC() {
		return refrenceToISIC;
	}

	public void setRefrenceToISIC(String refrenceToISIC) {
		this.refrenceToISIC = refrenceToISIC;
	}
	
	
}
