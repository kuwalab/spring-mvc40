package com.example.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ReqController {
	@RequestMapping(value = "/pathVar/{var}", method = RequestMethod.GET)
	public String pathVar(@PathVariable String var) {
		return "req/pathVar";
	}

	@RequestMapping(value = "/pathVar2/{var1}", method = RequestMethod.GET)
	public String pathVar2(@PathVariable("var1") String var) {
		return "req/pathVar";
	}

	@RequestMapping(value = "/pathVar3/{foo}/{bar}", method = RequestMethod.GET)
	public String pathVar3(@PathVariable String foo, @PathVariable String bar) {
		return "req/pathVar3";
	}

	@RequestMapping(value = "/pathVar4/{bar1}/{foo1}", method = RequestMethod.GET)
	public String pathVar4(@PathVariable("bar1") String bar,
			@PathVariable("foo1") String foo) {
		return "req/pathVar3";
	}

	@RequestMapping(value = "/pathVar5/{foo}/param/{bar}", method = RequestMethod.GET)
	public String pathVar5(@PathVariable String foo, @PathVariable String bar) {
		return "req/pathVar3";
	}

	@RequestMapping(value = "/getParam")
	public String getParam(@RequestParam String foo, @RequestParam String bar,
			Model model) {
		model.addAttribute("modelFoo", foo);
		model.addAttribute("modelBar", bar);
		return "req/getParam";
	}

	@RequestMapping(value = "/getParam2")
	public String getparam2(@RequestParam("foo1") String foo,
			@RequestParam("bar1") String bar, Model model) {
		model.addAttribute("modelFoo", foo);
		model.addAttribute("modelBar", bar);
		return "req/getParam";
	}

	@RequestMapping("/bodyForm")
	public String bodyForm() {
		return "req/bodyForm";
	}

	@RequestMapping(value = "/bodyRecv", method = RequestMethod.POST)
	public String bodyRecv(@RequestBody String body, Model model) {
		model.addAttribute("body", body);
		return "req/bodyRecv";
	}
}
