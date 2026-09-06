package com.shristi.Inventory_web_app.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
	
	
	@RequestMapping("/")
//	@ResponseBody
	public String greet()
	{
		// is expecting us to return the layout which is a page
		// when we are using only the @Controller
		return "Welcome to Inventory Management System!!";
	}
	
	@RequestMapping("/about")
	public String about()
	{
		return "This is where products are managed!!";
	}

}
