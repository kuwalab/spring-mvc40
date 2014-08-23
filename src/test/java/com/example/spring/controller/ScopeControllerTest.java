package com.example.spring.controller;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import java.util.Map;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/spring-context.xml" })
public class ScopeControllerTest {
	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;
	private MockHttpSession mockHttpSession;

	@Before
	public void setup() {
		mockMvc = webAppContextSetup(wac).build();
		mockHttpSession = new MockHttpSession(wac.getServletContext(), UUID
				.randomUUID().toString());
	}

	@Test
	public void requestScope1のGET() throws Exception {
		MvcResult mvcResult = mockMvc
				.perform(get("/requestScope1"))
				.andExpect(status().isOk())
				.andExpect(view().name("scope/requestScope1"))
				.andExpect(model().hasNoErrors())
				.andExpect(
						request().attribute("req1", is("httpServletRequest")))
				.andExpect(request().attribute("req2", is("webRequest")))
				.andExpect(request().attribute("req3", is("model")))
				.andExpect(model().attributeExists("req3")).andReturn();

		Map<String, Object> model = mvcResult.getModelAndView().getModel();
		assertThat(model.get("req3"), is("model"));
	}

	@Test
	public void sessionScope1のセッションの維持のテスト() throws Exception {
		assertThat(mockHttpSession.getAttribute("session1"), is(nullValue()));
		assertThat(mockHttpSession.getAttribute("session2"), is(nullValue()));

		mockMvc.perform(get("/sessionScope1").session(mockHttpSession))
				.andExpect(view().name("scope/sessionScope1"));

		assertThat(mockHttpSession.getAttribute("session1"), is("httpSession"));
		assertThat(mockHttpSession.getAttribute("session2"), is("webRequest"));

		// セッションは維持される
		mockMvc.perform(get("/sessionScope2").session(mockHttpSession))
				.andExpect(view().name("scope/sessionScope1"));

		assertThat(mockHttpSession.getAttribute("session1"), is("httpSession"));
		assertThat(mockHttpSession.getAttribute("session2"), is("webRequest"));
	}

	@Test
	public void sessionScope1のセッションの破棄のテスト() throws Exception {
		assertThat(mockHttpSession.getAttribute("session1"), is(nullValue()));
		assertThat(mockHttpSession.getAttribute("session2"), is(nullValue()));

		mockMvc.perform(get("/sessionScope1").session(mockHttpSession))
				.andExpect(view().name("scope/sessionScope1"));

		assertThat(mockHttpSession.getAttribute("session1"), is("httpSession"));
		assertThat(mockHttpSession.getAttribute("session2"), is("webRequest"));

		// セッションは維持される
		mockMvc.perform(get("/sessionScope3").session(mockHttpSession))
				.andExpect(view().name("scope/sessionScope1"));

		assertThat(mockHttpSession.getAttribute("session1"), is(nullValue()));
		assertThat(mockHttpSession.getAttribute("session2"), is(nullValue()));
	}
}
