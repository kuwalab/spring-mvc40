package com.example.spring.controller.c041;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/c041")
public class C041Controller {
	@RequestMapping("/option")
	public String option(Model model) {
		C041Form c041Form = new C041Form();
		c041Form.setSelectedIsbn("");
		model.addAttribute("c041Form", c041Form);
		return "c041/option";
	}
}
