package com.shristi.Inventory_web_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shristi.Inventory_web_app.model.Supplier;


public interface SupplierRepo extends JpaRepository<Supplier, Integer>
{
	
}