package com.shristi.Inventory_web_app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shristi.Inventory_web_app.dto.SupplierRequestDTO;
import com.shristi.Inventory_web_app.model.Product;
import com.shristi.Inventory_web_app.model.Supplier;
import com.shristi.Inventory_web_app.service.SupplierService;

import jakarta.validation.Valid;



@RestController
@RequestMapping("/suppliers")
public class SupplierController {
	
	@Autowired
	SupplierService service;
	
	@GetMapping
	public List<Supplier> getSuppliers()
	{
		return service.getSuppliers();
		
	}
	
	@GetMapping("/{supplierID}/products")
	public List<Product> getProductsBySupplier(
	        @PathVariable int supplierID)
	{
	    return service.getProductsBySupplier(supplierID);
	}
	
	
	@GetMapping("/{supplierID}")
	public Supplier getSupplierById(@PathVariable int supplierID )
	{
		return service.getSupplierById(supplierID);
	}
	
	
	@PostMapping
	public Supplier addSupplier(@Valid @RequestBody SupplierRequestDTO dto)
	{
		return service.addSupplier(dto);
	}
	
	@PutMapping("/{supplierID}")
	public Supplier updateSupplier (@PathVariable("supplierID") int supplierID, @Valid @RequestBody SupplierRequestDTO dto)
	{
		return service.updateSupplier(supplierID, dto);
	}
	
	@DeleteMapping("/{supplierID}")
	public void deleteSupplier(@PathVariable int supplierID)
	{
		service.deleteSupplier(supplierID);
	}
	
	
	
}
