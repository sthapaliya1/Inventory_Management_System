package com.shristi.Inventory_web_app.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public class ProductRequestDTO {
	
	@NotBlank(message = "Product name cannot be empty")
	private String productName;
	
	
	@NotBlank(message = "Product category cannot be empty" )
	private String productCategory;
	
	@Positive(message = "Price must be greater than 0")
	private double price;
	
	@PositiveOrZero(message = "Quantity cannot be negative")
	private int quantity;
	
	public String getProductName()
	{
		return productName;
	}

	public void setProductName(String productName)
	{
		this.productName = productName;
	}
	
	public String getProductCategory()
	{
		return productCategory;
	}
	
	public void setProductCategory(String productCategory)
	{
		this.productCategory = productCategory;
	}
	
	public double getPrice()
	{
		return price;
	}
	
	public void setPrice(double price)
	{
		this.price = price;
	}
	
	public int getQuantity()
	{
		return quantity;
	}
	
	public void setQuantity(int quantity)
	{
		this.quantity = quantity;
	}

	
}
