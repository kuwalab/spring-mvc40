package com.example.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FormController {
	@RequestMapping("/form/input")
	public String input(Model model) {
		Book book = new Book("よく分かるSpring<&>", 2000, 2500);
		model.addAttribute("book", book);
		return "form/input";
	}

	@RequestMapping("/form/password")
	public String password(Model model) {
		FormPassword formPassword = new FormPassword();
		formPassword.setPassword("password");
		model.addAttribute("formPassword", formPassword);
		return "form/password";
	}
}
