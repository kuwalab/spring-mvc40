package com.example.spring.controller.c004;

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
public class C004ControllerTest {
	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		mockMvc = webAppContextSetup(wac).build();
	}

	@Test
	public void pathVar3_123_abcへのGET() throws Exception {
		mockMvc.perform(get("/c004/pathVar3/123/abc"))
				.andExpect(status().isOk())
				.andExpect(view().name("c004/pathVar3"))
				.andExpect(request().attribute("foo", is("123")))
				.andExpect(request().attribute("bar", is("abc")));
	}

	@Test
	public void pathVar4_123_abcへのGET() throws Exception {
		mockMvc.perform(get("/c004/pathVar4/123/abc"))
				.andExpect(status().isOk())
				.andExpect(view().name("c004/pathVar3"))
				.andExpect(request().attribute("bar1", is("123")))
				.andExpect(request().attribute("foo1", is("abc")));
	}

	@Test
	public void pathVar5_123_param_abcへのGET() throws Exception {
		mockMvc.perform(get("/c004/pathVar5/123/param/abc"))
				.andExpect(status().isOk())
				.andExpect(view().name("c004/pathVar3"))
				.andExpect(request().attribute("foo", is("123")))
				.andExpect(request().attribute("bar", is("abc")));
	}
}
