package com.sudarshan.app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * @author sudarshanpradhan
 *
 */
@RestController
public class CardsController {
	
	@GetMapping("/myCards")
	public String getCardDetails(String input) {
		return "Here are the card details from the DB";
	}

}
