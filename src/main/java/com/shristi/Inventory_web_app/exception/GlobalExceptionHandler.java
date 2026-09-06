package com.shristi.Inventory_web_app.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
		
	
	@ExceptionHandler(ProductNotFoundException.class)
	public ResponseEntity<Map<String, Object>> handleProductNotFound
	(ProductNotFoundException ex)
	{
	
		Map<String, Object> error = new HashMap<>();
		
		error.put("status", HttpStatus.NOT_FOUND.value());
		error.put("error", "Not Found");
		error.put("message", ex.getMessage());
		
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
		
		
	}
	
	@ExceptionHandler(InsufficientStockException.class)
	public ResponseEntity<Map<String, Object>> handleInsufficientStock(
	        InsufficientStockException ex) {

	    Map<String, Object> error = new HashMap<>();

	    error.put("status", HttpStatus.BAD_REQUEST.value());
	    error.put("error", "Bad Request");
	    error.put("message", ex.getMessage());

	    return new ResponseEntity<>(
	            error,
	            HttpStatus.BAD_REQUEST
	    );
	}
	
	
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<Map<String, Object>> handleIllegalArgument(
	        IllegalArgumentException ex) {

	    Map<String, Object> error = new HashMap<>();

	    error.put("status", HttpStatus.BAD_REQUEST.value());
	    error.put("error", "Bad Request");
	    error.put("message", ex.getMessage());

	    return new ResponseEntity<>(
	            error,
	            HttpStatus.BAD_REQUEST
	    );
	}
}
