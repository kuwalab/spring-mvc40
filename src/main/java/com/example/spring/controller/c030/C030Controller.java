package com.example.spring.controller.c030;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class C030Controller {
	@Autowired
	private C030Model c030Model;

	@RequestMapping("/c030/sessionStart")
	public String sessionStart() {
		c030Model.setName("よくわかるHttpSession");
		c030Model.setPrice(980);
		return "redirect:/c030/sessionScope";
	}

	@RequestMapping("/c030/sessionScope")
	public String sessionScope4(Model model) {
		model.addAttribute("modelC030Model", c030Model);
		return "c030/sessionScope";
	}
}
