package com.example.spring.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Scope("request")
@Component
public class RequestBook {
	private String name;
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