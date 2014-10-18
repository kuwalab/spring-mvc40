package com.example.spring.controller.c033;

public class C033Model {
	private String name;
	private Integer price;

	public C033Model() {
	}

	public C033Model(String name, Integer price) {
		this.name = name;
		this.price = price;
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
}
