package com.shristi.Inventory_web_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shristi.Inventory_web_app.model.Product;
import com.shristi.Inventory_web_app.service.ProductService;

import jakarta.validation.Valid;

import java.util.List;

@RestController
public class ProductController {
	
	@Autowired
	ProductService service;
	
	@GetMapping("/products")
	public List<Product>getProduct()
	{
		return service.getProducts();
	}
	
	
	@GetMapping("/products/{productID}")
	public Product getProductById(@PathVariable int productID)
	{
		return service.getProductById(productID);
	}
	
	@GetMapping("/products/low-stock")
	public List<Product> getLowStockProducts(
	        @RequestParam(defaultValue = "5") int threshold) {

	    return service.getLowStockProducts(threshold);
	}
	
	
	@PostMapping("/products")
	public void addProduct(@Valid @RequestBody Product prod)
	{
		service.addProduct(prod);
	}
	
	@PutMapping("/products")
	public void updateProduct(@Valid @RequestBody Product prod)
	{
		service.updateProduct(prod);
	}
	
	@DeleteMapping("/products/{prodID}")
	public void deleteProduct(@PathVariable int prodID)
	{
		 service.deleteProduct(prodID);
	} 
	
	@PatchMapping("/products/{productID}/stock/add")
	public Product addStock(@PathVariable int productID, @RequestParam int quantity)
	{
		return service.addStock(productID, quantity);
	}
	
	@PatchMapping("/products/{productID}/stock/remove")
	public Product removeStock (@PathVariable int productID, @RequestParam int quantity)
	{
		return service.removeStock(productID, quantity);
	}
	
	
}
