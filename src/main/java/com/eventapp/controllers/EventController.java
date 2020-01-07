package com.eventapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class EventController {

	@RequestMapping("/registerEvent")
	public String form() {
		return "./event/formEvent";
	}
}
