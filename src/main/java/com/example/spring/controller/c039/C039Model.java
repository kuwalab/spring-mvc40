package com.example.spring.controller.c039;

public class C039Model {
	private String isbn;
	private String name;

	public C039Model() {
	}

	public C039Model(String isbn, String name) {
		this.isbn = isbn;
		this.name = name;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
