package com.example.spring.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CheckController {
	@RequestMapping("/checkType")
	public String checkType(@ModelAttribute Book book, BindingResult errors) {
		return "check/checkType";
	}

	@RequestMapping("/bookForm")
	public String bookForm() {
		return "check/bookForm";
	}

	@RequestMapping(value = "/bookRecv", method = RequestMethod.POST)
	public String bookRecv(@Valid @ModelAttribute Book book,
			BindingResult errors) {
		if (errors.hasErrors()) {
			return "check/bookForm";
		}
		return "check/bookRecv";
	}
}
