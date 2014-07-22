package com.example.spring.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.WebRequest;

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

	@RequestMapping(value = "/urlparam/{foo}/{bar}", method = RequestMethod.GET)
	public String urlparam3(@PathVariable String foo, @PathVariable String bar,
			Model model) {
		model.addAttribute("foo", foo);
		model.addAttribute("bar", bar);
		return "hello/multiparam";
	}

	@RequestMapping(value = "/urlparam2/{foo}/{bar}", method = RequestMethod.GET)
	public String urlparam4(@PathVariable("bar") String var1,
			@PathVariable("foo") String var2, Model model) {
		model.addAttribute("foo", var1);
		model.addAttribute("bar", var2);
		return "hello/multiparam";
	}

	@RequestMapping(value = "/urlparam2/{foo}/param/{bar}", method = RequestMethod.GET)
	public String urlparam5(@PathVariable String foo, @PathVariable String bar,
			Model model) {
		model.addAttribute("foo", foo);
		model.addAttribute("bar", bar);
		return "hello/multiparam";
	}

	@RequestMapping(value = "/getparam")
	public String getparam(@RequestParam String foo, @RequestParam String bar,
			Model model) {
		model.addAttribute("foo", foo);
		model.addAttribute("bar", bar);
		return "hello/getparam";
	}

	@RequestMapping(value = "/getparam2")
	public String getparam2(@RequestParam("var1") String foo,
			@RequestParam("var2") String bar, Model model) {
		model.addAttribute("foo", foo);
		model.addAttribute("bar", bar);
		return "hello/getparam";
	}

	@RequestMapping(value = "initbody", method = RequestMethod.GET)
	public String initbody() {
		return "hello/initbody";
	}

	@RequestMapping(value = "bodyparam", method = RequestMethod.POST)
	public String bodyparam(@RequestBody String body, Model model) {
		model.addAttribute("body", body);
		return "hello/bodyparam";
	}

	@RequestMapping("normal")
	public String normal(HttpServletRequest request, Model model) {
		model.addAttribute("var", request.getParameter("var"));
		return "hello/urlparam";
	}

	@RequestMapping("webrequest")
	public String webrequest(WebRequest webRequest, Model model) {
		model.addAttribute("var", webRequest.getParameter("var"));
		return "hello/urlparam";
	}

	@RequestMapping("nativeweb")
	public String nativeweb(NativeWebRequest nativeWebRequest, Model model) {
		model.addAttribute("var", nativeWebRequest.getParameter("var"));
		return "hello/urlparam";
	}
}
