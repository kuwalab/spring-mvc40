package com.example.spring.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;

@Controller
public class ScopeController {
	@RequestMapping("/requestScope1")
	public String requestScope1(HttpServletRequest request,
			WebRequest webRequest, Model model) {
		request.setAttribute("req1", "httpServletRequest");
		webRequest.setAttribute("req2", "webRequest", WebRequest.SCOPE_REQUEST);
		model.addAttribute("req3", "model");
		return "scope/requestScope1";
	}

	@RequestMapping("/requestScope2")
	public String requestScope2(RequestBook requestBook) {
		requestBook.setName("よくわかるSpring");
		requestBook.setPrice(2900);
		return "scope/requestScope2";
	}

	@RequestMapping("/requestScope3")
	public String requestScope3() {
		return "scope/requestScope3";
	}
}
