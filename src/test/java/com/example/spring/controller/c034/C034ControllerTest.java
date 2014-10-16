package com.example.spring.controller.c034;

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
public class C034ControllerTest {
	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		mockMvc = webAppContextSetup(wac).build();
	}

	@Test
	public void arrayFormのGET() throws Exception {
		mockMvc.perform(get("/c034/arrayForm")).andExpect(status().isOk())
				.andExpect(view().name("c034/arrayForm"));
	}

	@Test
	public void arrayRecvのPOST_パラメータが3つ入力された場合() throws Exception {
		mockMvc.perform(post("/c034/arrayRecv").param("name", "1", "2", "3"))
				.andExpect(status().isOk())
				.andExpect(view().name("c034/arrayRecv"))
				.andExpect(model().attribute("name0", "1"))
				.andExpect(model().attribute("name1", "2"))
				.andExpect(model().attribute("name2", "3"));
	}

	@Test
	public void arrayRecvのPOST_パラメータが1つだけ入力された場合() throws Exception {
		mockMvc.perform(post("/c034/arrayRecv").param("name", "", "1", ""))
				.andExpect(status().isOk())
				.andExpect(view().name("c034/arrayRecv"))
				.andExpect(model().attribute("name0", ""))
				.andExpect(model().attribute("name1", "1"))
				.andExpect(model().attribute("name2", ""));
	}
}
