package com.shristi.Inventory_web_app.exception;

public class InsufficientStockException extends RuntimeException{

	 public InsufficientStockException(String message) {
	        super(message);
	    }
}
