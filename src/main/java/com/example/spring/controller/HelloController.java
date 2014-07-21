package com.example.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HelloController {
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index() {
		return "hello/index";
	}

	@RequestMapping(value = "/urlparam/{var}", method = RequestMethod.GET)
	public String urlparam(@PathVariable String var, Model model) {
		model.addAttribute("var", var);
		return "hello/urlparam";
	}

	@RequestMapping(value = "/urlparam2/{var}", method = RequestMethod.GET)
	public String urlparam2(@PathVariable("var") String var1, Model model) {
		model.addAttribute("var", var1);
		return "hello/urlparam";
	}
}
