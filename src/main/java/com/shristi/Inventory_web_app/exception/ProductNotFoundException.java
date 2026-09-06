package com.shristi.Inventory_web_app.exception;

public class ProductNotFoundException extends RuntimeException{
	
    public ProductNotFoundException(String message) {
        super(message);
    }

}
