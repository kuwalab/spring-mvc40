package com.example.spring.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

	@RequestMapping("/form/hidden")
	public String hidden(Model model) {
		Book book = new Book("よく分かるSpring<&>", 2000, 2500);
		model.addAttribute("book", book);
		return "form/hidden";
	}

	@RequestMapping("/form/checkbox")
	public String checkbox(Model model) {
		FormCheckbox formCheckbox = new FormCheckbox();
		formCheckbox.setCheck(true);
		model.addAttribute("formCheckbox", formCheckbox);
		return "form/checkbox";
	}

	@RequestMapping("/form/checkboxRecv")
	public String checkboxRecv(String check, Model model) {
		model.addAttribute("recvData", check);
		return "form/checkboxRecv";
	}

	@RequestMapping("/form/checkboxes")
	public String checkboxes(Model model) {
		List<Book> bookList = new ArrayList<>();

		bookList.add(new Book("123", "よく分かるSpring"));
		bookList.add(new Book("456", "よく分かるJava"));
		bookList.add(new Book("789", "よく分かるSpring MVC"));

		model.addAttribute("bookList", bookList);

		BookForm bookForm = new BookForm();
		bookForm.setSelectedIsbn(new String[] { "456" });
		model.addAttribute("bookForm", bookForm);
		return "form/checkboxes";
	}

	@RequestMapping("/form/checkboxesRecv")
	public String checkboxesRecv(
			@RequestParam(required = false) String[] selectedIsbn, Model model) {
		model.addAttribute("isbns", selectedIsbn);
		return "form/checkboxesRecv";
	}

	@RequestMapping("/form/radiobutton")
	public String radiobutton(Model model) {
		FormRadiobutton formRadiobutton = new FormRadiobutton();
		formRadiobutton.setTel("mobile");

		model.addAttribute("formRadiobutton", formRadiobutton);
		return "form/radiobutton";
	}

	@RequestMapping("/form/radiobuttonRecv")
	public String radiobuttonRecv(String check, Model model) {
		model.addAttribute("recvData", check);
		return "form/checkboxRecv";
	}

}
