package com.example.spring.controller.c001;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/c003")
public class C003Controller {
	@RequestMapping(value = "/pathVar1/{var}", method = RequestMethod.GET)
	public String pathVar(@PathVariable String var) {
		return "c003/pathVar";
	}

	@RequestMapping(value = "/pathVar2/{var1}", method = RequestMethod.GET)
	public String pathVar2(@PathVariable("var1") String var) {
		return "c003/pathVar";
	}
}
