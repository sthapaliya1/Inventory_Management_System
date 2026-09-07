package com.shristi.Inventory_web_app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shristi.Inventory_web_app.model.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer>{
	
	List<Product> findByQuantityLessThanEqual(int quantity);
	
	List<Product> findBySupplier_SupplierID(int supplierID);
	
	List<Product> findByProductNameContainingIgnoreCase(String productName);
	
	List<Product> findByProductCategoryIgnoreCase(String productCategory);

}
