package com.shristi.Inventory_web_app.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shristi.Inventory_web_app.exception.InsufficientStockException;
import com.shristi.Inventory_web_app.exception.ProductNotFoundException;
import com.shristi.Inventory_web_app.model.Product;
import com.shristi.Inventory_web_app.repository.ProductRepo;


@Service // object is created inside the spring container
public class ProductService {
	
	
	
	 @Autowired
	 ProductRepo repo;
	
	
//	 List<Product> products = new ArrayList<> (Arrays.asList(
//		        new Product(1, "Dell Laptop", "Laptop", 799.99, 8),
//		        new Product(2, "Macbook Air M4", "Laptop", 999.99, 5),
//		        new Product(3, "Mechanical Keyboard", "Accessories", 79.99, 15),
//		        new Product(4, "Sun Disk 15GB SSD", "Storage", 89.99, 18),
//		        new Product(5, "USB C Hub", "Accessories", 39.99, 30),
//		        new Product(6, "Sony Headphones", "Audio", 129.99, 9),
//		        new Product(7, "USB C Charger", "Accessories", 24.99, 35),
//		        new Product(8, "JB Bluetooth Speaker", "Audio", 79.99, 8)
//		    ));

	public List<Product> getProducts()
	{
		return repo.findAll();
	}

	
	// the request passes from the client to the service
	public Product getProductById(int productID) {

		return repo.findById(productID)
	            .orElseThrow(() ->
	                new ProductNotFoundException(
	                    "Product with ID " + productID + " not found"
	                )
	            );
	}
	
	
	//  the response addProduct goes from the server to the client:
	public void addProduct(Product prod)
	{
		repo.save(prod);
		
	}


	public void updateProduct(Product prod) {
		
		
		
		repo.findById(prod.getProductID())
        .orElseThrow(() ->
            new ProductNotFoundException(
                "Product with ID "
                + prod.getProductID()
                + " not found"
            )
        );
		
		repo.save(prod);
		
//		int index=0;
//		
//		repo.save(null);
//		for(int i=0; i<products.size();i++)
//		{
//			if(products.get(i).getProductID() == prod.getProductID())
//			
//				index=i;
//			
//			products.set(index, prod);
//			
//		}
		
		
	}


	public void deleteProduct(int prodID) {
		
		Product product = repo.findById(prodID)
	            .orElseThrow(() ->
	                new ProductNotFoundException(
	                    "Product with ID " + prodID + " not found"
	                )
	            );
		
		repo.deleteById(prodID);
		
//		int index=0;
//		
//		for(int i=0; i<products.size();i++)
//		{
//			if(products.get(i).getProductID() == prodID)
//			
//				index=i;
//			
//			products.remove(index);
//			
//		}	
		
	}
	
	
	
	
	// add the stock methods in here:
	public Product addStock(int productID, int quantity)
	{
	    if(quantity <=0)
	    {
	    	throw new IllegalArgumentException("Quantity must be greater than zero");
	    }
		
		// give me product whose primary key is equal to this ID.
		Product product = repo.findById(productID)
		        .orElseThrow(() ->
	            new ProductNotFoundException(
	                "Product with ID " + productID + " not found"
	            )
	        ); 
		product.setQuantity(product.getQuantity() + quantity);
		return repo.save(product); // to change the data in the postgreSQL
	}
	
	public Product removeStock(int productID, int quantity)
	{
		
		if (quantity <=0)
		{
			throw new IllegalArgumentException ("Quantity must be greater than 0");
		}
		
		Product product = repo.findById(productID).orElseThrow(() ->
        new ProductNotFoundException(
                "Product with ID " + productID + " not found"
            )
        );
		
		if(product.getQuantity() < quantity)
		{
		 throw new InsufficientStockException(
	                "Not enough stock available. Current stock: "
	                        + product.getQuantity()
	                );
		}
		
		product.setQuantity(product.getQuantity() - quantity);
		
		return repo.save(product);
	}
	
	public List<Product> getLowStockProducts(int threshold) {
	    return repo.findByQuantityLessThanEqual(threshold);
	}
	
	
	
}
