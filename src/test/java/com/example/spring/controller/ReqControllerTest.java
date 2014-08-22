package com.example.spring.controller;

import static org.hamcrest.CoreMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/spring-context.xml" })
public class ReqControllerTest {
	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		mockMvc = webAppContextSetup(wac).build();
	}

	@Test
	public void pathVar_12345へのGET() throws Exception {
		mockMvc.perform(get("/pathVar/12345")).andExpect(status().isOk())
				.andExpect(view().name("req/pathVar"))
				.andExpect(model().hasNoErrors())
				.andExpect(request().attribute("var", is("12345")));
	}

	@Test
	public void pathVar2_abcdeへのGET() throws Exception {
		mockMvc.perform(get("/pathVar2/abcde")).andExpect(status().isOk())
				.andExpect(view().name("req/pathVar"))
				.andExpect(model().hasNoErrors())
				.andExpect(request().attribute("var1", is("abcde")));
	}

	@Test
	public void pathVar3_123_abcへのGET() throws Exception {
		mockMvc.perform(get("/pathVar3/123/abc")).andExpect(status().isOk())
				.andExpect(view().name("req/pathVar3"))
				.andExpect(model().hasNoErrors())
				.andExpect(request().attribute("foo", is("123")))
				.andExpect(request().attribute("bar", is("abc")));
	}

	@Test
	public void pathVar4_123_abcへのGET() throws Exception {
		mockMvc.perform(get("/pathVar4/123/abc")).andExpect(status().isOk())
				.andExpect(view().name("req/pathVar3"))
				.andExpect(model().hasNoErrors())
				.andExpect(request().attribute("bar1", is("123")))
				.andExpect(request().attribute("foo1", is("abc")));
	}

	@Test
	public void pathVar5_123_param_abcへのGET() throws Exception {
		mockMvc.perform(get("/pathVar5/123/param/abc"))
				.andExpect(status().isOk())
				.andExpect(view().name("req/pathVar3"))
				.andExpect(model().hasNoErrors())
				.andExpect(request().attribute("foo", is("123")))
				.andExpect(request().attribute("bar", is("abc")));
	}

	@Test
	public void getParam_foo$abc_bar$123のGET() throws Exception {
		mockMvc.perform(get("/getParam?foo=abc&bar=123"))
				.andExpect(status().isOk())
				.andExpect(view().name("req/getParam"))
				.andExpect(model().hasNoErrors())
				.andExpect(model().attribute("modelFoo", is("abc")))
				.andExpect(model().attribute("modelBar", is("123")))
				.andExpect(request().attribute("foo", is(nullValue())))
				.andExpect(request().attribute("bar", is(nullValue())));
	}

	@Test
	public void getParam_foo$abcのGET_パラメータ不足によるエラー() throws Exception {
		mockMvc.perform(get("/getParam?foo=abc")).andExpect(
				status().isBadRequest());
	}

	@Test
	public void getParam2_foo1$abc_bar1$123のGET() throws Exception {
		mockMvc.perform(get("/getParam2?foo1=abc&bar1=123"))
				.andExpect(status().isOk())
				.andExpect(view().name("req/getParam"))
				.andExpect(model().hasNoErrors())
				.andExpect(model().attribute("modelFoo", is("abc")))
				.andExpect(model().attribute("modelBar", is("123")))
				.andExpect(request().attribute("foo1", is(nullValue())))
				.andExpect(request().attribute("bar1", is(nullValue())));
	}

	@Test
	public void getParam3_foo$abc_bar$123のGET() throws Exception {
		mockMvc.perform(get("/getParam3?foo=abc&bar=123"))
				.andExpect(status().isOk())
				.andExpect(view().name("req/getParam"))
				.andExpect(model().hasNoErrors())
				.andExpect(model().attribute("foo", is("abc")))
				.andExpect(model().attribute("bar", is("123")));
	}

	@Test
	public void getParam3のGET() throws Exception {
		mockMvc.perform(get("/getParam3")).andExpect(status().isOk())
				.andExpect(view().name("req/getParam"))
				.andExpect(model().hasNoErrors())
				.andExpect(model().attribute("foo", is(nullValue())))
				.andExpect(model().attribute("bar", is("default")));
	}
}
