package com.eventapp.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.eventapp.models.Event;
import com.eventapp.models.Guest;
import com.eventapp.repository.EventRepository;
import com.eventapp.repository.GuestRepository;

@Controller
public class EventController {

	@Autowired
	private EventRepository eventRep;
	
	@Autowired
	private GuestRepository guestRep;
	
	@GetMapping("/registerEvent")
	public String form() {
		return "./event/formEvent";
	}
	
	@PostMapping("/registerEvent")
	public String form(@Valid Event event, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			attributes.addFlashAttribute("message", "Check the fields");
			return "redirect:/registerEvent";
		}
		
		eventRep.save(event);
		return "redirect:/registerEvent";
	}
	
	@GetMapping("/events")
	public ModelAndView eventList() {
		ModelAndView mv = new ModelAndView("index");
		Iterable<Event> events = eventRep.findAll();
		mv.addObject("events", events);
		return mv;
	}
	
	@GetMapping("/{id}")
	public ModelAndView eventDetails(@PathVariable("id") long id) {
		Event event = eventRep.findById(id);
		ModelAndView mv = new ModelAndView("event/eventDetails");
		mv.addObject("event", event);
		
		Iterable<Guest> guests = guestRep.findByEvent(event);
		mv.addObject("guests", guests);
		return mv;
	}
	
	@PostMapping("/{id}")
	public String eventDetailsPost(@PathVariable("id") long id, @Valid Guest guest, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			attributes.addFlashAttribute("message", "Check the fields");
			return "redirect:/{id}";
		}
		
		Event event = eventRep.findById(id);
		guest.setEvent(event);
		guestRep.save(guest);
//		attributes.addFlashAttribute("message", "Guest added successfully");
		return "redirect:/{id}";
	}
	
	
	
}
