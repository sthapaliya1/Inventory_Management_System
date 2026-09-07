package com.shristi.Inventory_web_app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor //lombock automatically generates the constructor for Product
@Entity
public class Product {
	
	@Id
	// lets the database automatically generate the value of the productID
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int productID;
	
	@ManyToOne
	@JoinColumn(name="supplier_id")
	private Supplier supplier;
	
	@NotBlank(message = "Product name cannot be empty")
	private String productName;
	
	@NotBlank(message = "Product category cannot be empty")
	private String productCategory;
	
	@Positive(message = "Price must be greater than zero")
	private double price;
	
	@PositiveOrZero(message = "Quantity cannot be negative")
	private int quantity;
	
	public Supplier getSupplier()
	{
		return supplier;
	}
	
	public void setSupplier(Supplier supplier)
	{
		this.supplier = supplier;
	}

}
