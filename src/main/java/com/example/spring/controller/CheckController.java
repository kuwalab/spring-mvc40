package com.example.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CheckController {
	@RequestMapping("/checkType")
	public String checkType(@ModelAttribute Book book, BindingResult errors) {
		return "check/checkType";
	}
}
