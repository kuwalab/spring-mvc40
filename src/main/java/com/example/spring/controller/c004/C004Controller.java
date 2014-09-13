package com.example.spring.controller.c004;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/c004")
public class C004Controller {
	@RequestMapping(value = "/pathVar3/{foo}/{bar}", method = RequestMethod.GET)
	public String pathVar3(@PathVariable String foo, @PathVariable String bar) {
		return "c004/pathVar3";
	}

	@RequestMapping(value = "/pathVar4/{bar1}/{foo1}", method = RequestMethod.GET)
	public String pathVar4(@PathVariable("bar1") String bar,
			@PathVariable("foo1") String foo) {
		return "c004/pathVar3";
	}

	@RequestMapping(value = "/pathVar5/{foo}/param/{bar}", method = RequestMethod.GET)
	public String pathVar5(@PathVariable String foo, @PathVariable String bar) {
		return "c004/pathVar3";
	}
}
