package com.shristi.Inventory_web_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

import com.shristi.Inventory_web_app.dto.ProductRequestDTO;
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
	public void addProduct(@Valid @RequestBody ProductRequestDTO dto)
	{
		service.addProduct(dto);
	}
	
	@PutMapping("/products/{productID}")
	public void updateProduct(@PathVariable("productID") int productID, @Valid @RequestBody ProductRequestDTO dto)
	{
		service.updateProduct(productID, dto);
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
	
	@PutMapping("/products/{productID}/supplier/{supplierID}")
	public Product assignSupplier( @PathVariable int productID, @PathVariable int supplierID)
	{
	    return service.assignSupplier(productID, supplierID);
	}
	
	@GetMapping("/products/search")
	public List<Product> searchProductsByName(@RequestParam String name)
	{
	    return service.searchProductsByName(name);
	}
	
	
	@GetMapping("/products/category")
	public List<Product> getProductsByCategory(@RequestParam String category)
	{
	    return service.getProductsByCategory(category);
	}
	
	
	@GetMapping("/products/page")
	public Page<Product> getProductsByPage(
	        @RequestParam(defaultValue = "0") int page,
	        @RequestParam(defaultValue = "5") int size,
	        @RequestParam(defaultValue = "productID") String sortBy,
	        @RequestParam(defaultValue = "asc") String direction)
	{
	    return service.getProductsByPage(
	            page,
	            size,
	            sortBy,
	            direction
	    );
	}
	
	
	
}
