package com.example.spring.controller;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.WebRequest;

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

	@RequestMapping("/req")
	public String req(HttpServletRequest request, Model model) {
		model.addAttribute("foo", request.getParameter("foo"));
		return "req/req";
	}

	@RequestMapping("/req2")
	public String req2(WebRequest request, Model model) {
		model.addAttribute("foo", request.getParameter("foo"));
		return "req/req";
	}

	@RequestMapping("/req3")
	public String req3(NativeWebRequest request, Model model) {
		model.addAttribute("foo", request.getParameter("foo"));
		return "req/req";
	}

	@RequestMapping("/readerForm")
	public String readerForm() {
		return "req/readerForm";
	}

	@RequestMapping(value = "/readerRecv", method = RequestMethod.POST)
	public String readerRecv(BufferedReader reader, Model model)
			throws IOException {
		model.addAttribute("body", reader.readLine());
		return "req/readerRecv";
	}

	@RequestMapping("/entityForm")
	public String entityForm() {
		return "req/entityForm";
	}

	@RequestMapping(value = "/entityRecv", method = RequestMethod.POST)
	public String entityRecv(HttpEntity<String> httpEntity, Model model) {
		model.addAttribute("body", httpEntity.getBody());
		return "req/entityRecv";
	}

	@RequestMapping("/modelForm")
	public String modelForm() {
		return "req/modelForm";
	}

	@RequestMapping(value = "/modelRecv", method = RequestMethod.POST)
	public String modelRecv(@ModelAttribute Customer customer) {
		return "req/modelRecv";
	}
}
