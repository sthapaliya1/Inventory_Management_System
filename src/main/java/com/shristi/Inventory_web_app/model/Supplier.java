package com.shristi.Inventory_web_app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;


@Entity
public class Supplier {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int supplierID;
	
	@NotBlank(message = "Supplier name cannot be empty")
	private String supplierName;
	
	@Email(message = "Email must be valid")
	@NotBlank(message = "Email cannot be empty")
	private String email;
	
	private String phone;
	
	private String address;

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getEmail()
	{
		return email;
	}
	
	public void setEmail(String email)
	{
		this.email = email;
	}
	
	public String getSupplierName()
	{
		 return supplierName;
	}
	
	public void setSupplierName(String supplierName)
	{
		this.supplierName = supplierName;
	}
	
	

}
