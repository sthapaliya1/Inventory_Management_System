package com.shristi.Inventory_web_app.model;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Inventory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int inventoryID;
	
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;
	
	@ManyToOne
	@JoinColumn(name = "warehouse_id")
	private Warehouse warehouse;
	
	private int quantity;
	
	public Inventory() {
	    }
	   
	
	public Inventory(Product product, Warehouse warehouse, int quantity)
	{
	    this.product = product;
	    this.warehouse = warehouse;
	    this.quantity = quantity;
	}
	
	
	public int getInventoryID()
	{
		return inventoryID;
	}
	
	public void setInventoryID(int inventoryID)
	{
		this.inventoryID = inventoryID;
	}
	
	public Product getProduct()
	{
		return product;
	}
	
	public void setProduct(Product product)
	{
		this.product = product;
	}
	
	public Warehouse getWarehouse()
	{
		return warehouse;
	}
	
	public void setWarehouse(Warehouse warehouse)
	{
		this.warehouse = warehouse;
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
