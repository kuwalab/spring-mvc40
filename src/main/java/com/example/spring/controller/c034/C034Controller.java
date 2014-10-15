package com.example.spring.controller.c034;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/c034")
public class C034Controller {
	@RequestMapping("/arrayForm")
	public String entityForm() {
		return "c034/arrayForm";
	}

	@RequestMapping(value = "/arrayRecv", method = RequestMethod.POST)
	public String entityRecv(@RequestParam String[] name) {
		return "c034/arrayForm";
	}
}
