package com.shristi.Inventory_web_app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shristi.Inventory_web_app.model.Supplier;
import com.shristi.Inventory_web_app.repository.SupplierRepo;


@Service
public class SupplierService {

	
	 @Autowired
	 SupplierRepo repo;
	 
	 
	 public List<Supplier> getSuppliers()
	 {
		 return repo.findAll();
	 }
	
	 public Supplier getSupplierById(int supplierID)
	 {
		 return repo.findById(supplierID).orElse(null);
	 }
	
	 
	 public Supplier addSupplier(Supplier supplier)
	 {
		 return repo.save(supplier);
	 }
	
	 public Supplier updateSupplier(
	            int supplierID,
	            Supplier supplier)
	    {
	        Supplier existingSupplier =
	                repo.findById(supplierID).orElse(null);

	        if (existingSupplier != null)
	        {
	            existingSupplier.setSupplierName(
	                    supplier.getSupplierName());

	            existingSupplier.setEmail(
	                    supplier.getEmail());

	            existingSupplier.setPhone(
	                    supplier.getPhone());

	            existingSupplier.setAddress(
	                    supplier.getAddress());

	            return repo.save(existingSupplier);
	        }

	        return null;
	    }
	 
	
	 public void deleteSupplier(int supplierID)
	 {
		 repo.deleteById(supplierID);
	 }
	
}
