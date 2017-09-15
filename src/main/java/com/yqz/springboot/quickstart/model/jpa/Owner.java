package com.yqz.springboot.quickstart.model.jpa;

//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.Table;
//
//@Entity
//@Table(name = "owner")
//public class Owner {
//	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	@Column(name = "owner_id")
//	private Integer ownerId;
//
//	@Column(name = "owner_name")
//	private String ownerName;
//
//	private String signature;
//
//	protected Owner() {
//	}
//
//	// public Owner(Integer id, String name, String signature) {
//	// this.ownerId = id;
//	// this.ownerName = name;
//	// this.signature = signature;
//	// }
//
//	public Integer getOwnerId() {
//		return ownerId;
//	}
//
//	public void setOwnerId(Integer ownerId) {
//		this.ownerId = ownerId;
//	}
//
//	public String getOwnerName() {
//		return ownerName;
//	}
//
//	public void setOwnerName(String ownerName) {
//		this.ownerName = ownerName;
//	}
//
//	public String getSignature() {
//		return signature;
//	}
//
//	public void setSignature(String signature) {
//		this.signature = signature;
//	}
//
//	@Override
//	public String toString() {
//		return String.format("Owner[ownerId=%d, ownerName='%s', signature='%s']", ownerId, ownerId, signature);
//	}
//}
