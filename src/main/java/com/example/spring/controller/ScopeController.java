package com.example.spring.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
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

	@RequestMapping("/sessionScope1")
	public String sessionScope1(HttpSession session, WebRequest webRequest) {
		session.setAttribute("session1", "httpSession");
		webRequest.setAttribute("session2", "webRequest",
				WebRequest.SCOPE_SESSION);

		return "scope/sessionScope1";
	}

	@RequestMapping("/sessionScope2")
	public String sessionScope2() {
		return "scope/sessionScope1";
	}

	@RequestMapping("/sessionScope3")
	public String sessionScope3(HttpSession session) {
		session.invalidate();
		return "scope/sessionScope1";
	}

	@Autowired
	private SessionBook sessionBook;

	@RequestMapping("/sessionStart")
	public String sessionStart(HttpServletRequest request) {
		request.getSession();
		return "scope/sessionStart";
	}

	@RequestMapping("/sessionScope4")
	public String sessionScope4() {
		sessionBook.setName("よくわかるHttpSession");
		sessionBook.setPrice(980);
		return "scope/sessionScope4";
	}

	@RequestMapping("/sessionScope5")
	public String sessionScope5() {
		return "scope/sessionScope4";
	}

	@RequestMapping("/sessionScope6")
	public String sessionScope6(HttpSession session) {
		session.invalidate();
		return "scope/sessionScope4";
	}
}
