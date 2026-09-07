package com.shristi.Inventory_web_app.service;

import java.util.ArrayList;
import com.shristi.Inventory_web_app.model.Supplier;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.shristi.Inventory_web_app.exception.InsufficientStockException;
import com.shristi.Inventory_web_app.exception.ProductNotFoundException;
import com.shristi.Inventory_web_app.model.Product;
import com.shristi.Inventory_web_app.repository.ProductRepo;
import com.shristi.Inventory_web_app.repository.SupplierRepo;


@Service // object is created inside the spring container
public class ProductService {
	
	
	
	 @Autowired
	 ProductRepo repo;
	 
	 @Autowired
	 SupplierRepo supplierRepo;
	

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
		
		
	}


	public void deleteProduct(int prodID) {
		
		Product product = repo.findById(prodID)
	            .orElseThrow(() ->
	                new ProductNotFoundException(
	                    "Product with ID " + prodID + " not found"
	                )
	            );
		
		repo.deleteById(prodID);
		
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
	
	
	public Product assignSupplier(int productID, int supplierID)
	{
	    Product product = repo.findById(productID)
	            .orElseThrow(() ->
	                new ProductNotFoundException(
	                    "Product with ID " + productID + " not found"
	                )
	            );

	    Supplier supplier = supplierRepo.findById(supplierID).orElseThrow(() -> new RuntimeException("Supplier with ID " + supplierID + " not found"));

	    product.setSupplier(supplier);

	    return repo.save(product);
	}
	
	// search products by name
	public List<Product> searchProductsByName(String name)
	{
	    return repo.findByProductNameContainingIgnoreCase(name);
	}
	
	// Filter products by category
	public List<Product> getProductsByCategory(String category)
	{
	    return repo.findByProductCategoryIgnoreCase(category);
	}
	
	public Page<Product> getProductsByPage(int page, int size)
	{
	    Pageable pageable = PageRequest.of(page, size);

	    return repo.findAll(pageable);
	}
	
	
	public Page<Product> getProductsByPage(
	        int page,
	        int size,
	        String sortBy,
	        String direction)
	{
	    Sort sort;

	    if(direction.equalsIgnoreCase("desc"))
	    {
	        sort = Sort.by(sortBy).descending();
	    }
	    else
	    {
	        sort = Sort.by(sortBy).ascending();
	    }

	    Pageable pageable = PageRequest.of(page, size, sort);

	    return repo.findAll(pageable);
	}
	
	
}
