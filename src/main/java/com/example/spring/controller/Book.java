package com.example.spring.controller;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

public class Book {
	@NotBlank
	private String name;
	@NotNull
	private Integer price;
	@NotNull
	private Integer listPrice;

	public Book() {
	}

	public Book(String name, Integer price, Integer listPrice) {
		super();
		this.name = name;
		this.price = price;
		this.listPrice = listPrice;
	}

	@AssertTrue(message = "{valid.price}")
	public boolean isValidPrice() {
		if (price == null || listPrice == null) {
			return true;
		}
		return listPrice >= price;
	}

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

	public Integer getListPrice() {
		return listPrice;
	}

	public void setListPrice(Integer listPrice) {
		this.listPrice = listPrice;
	}
}
