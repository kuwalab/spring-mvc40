package com.example.spring.controller;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Book {
	@NotNull
	@Size(min = 10, max = 10)
	private String name;
	@NotNull
	private Integer price;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}
}
