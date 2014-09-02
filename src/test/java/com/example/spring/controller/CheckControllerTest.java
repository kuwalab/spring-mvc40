package com.example.spring.controller;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/spring-context.xml" })
public class CheckControllerTest {
	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		mockMvc = webAppContextSetup(wac).build();
	}

	@Test
	public void checkTypeへのGET_priceが1000() throws Exception {
		MvcResult mvcResult = mockMvc
				.perform(get("/checkType").param("price", "1000"))
				.andExpect(status().isOk())
				.andExpect(view().name("check/checkType"))
				.andExpect(model().hasNoErrors())
				.andExpect(model().attributeExists("book")).andReturn();

		Map<String, Object> model = mvcResult.getModelAndView().getModel();
		Object bookObject = model.get("book");
		assertThat(bookObject, is(notNullValue()));
		assertThat(bookObject, is(instanceOf(Book.class)));
		Book book = (Book) bookObject;
		assertThat(book.getPrice(), is(1000));
	}

	@Test
	public void checkTypeへのGET_priceがabc() throws Exception {
		MvcResult mvcResult = mockMvc
				.perform(get("/checkType").param("price", "abc"))
				.andExpect(status().isOk())
				.andExpect(view().name("check/checkType"))
				.andExpect(model().hasErrors())
				.andExpect(model().errorCount(1))
				.andExpect(model().attributeHasFieldErrors("book", "price"))
				.andExpect(model().attributeExists("book")).andReturn();

		// パラメータのチェック
		ModelAndView mav = mvcResult.getModelAndView();
		Map<String, Object> model = mav.getModel();
		Object bookObject = model.get("book");
		assertThat(bookObject, is(notNullValue()));
		assertThat(bookObject, is(instanceOf(Book.class)));
		Book book = (Book) bookObject;
		assertThat(book.getPrice(), is(nullValue()));

		// エラーメッセージのチェック
		Object object = mav.getModel().get(
				"org.springframework.validation.BindingResult.book");
		assertThat(object, is(not(nullValue())));
		assertThat(object, is(instanceOf(BindingResult.class)));
		BindingResult bindingResult = (BindingResult) object;

		// エラーのあるフィールドの取得
		List<FieldError> list = bindingResult.getFieldErrors("price");
		assertThat(list, is(not(nullValue())));
		assertThat(list.size(), is(1));

		// 詳細なエラーチェック
		FieldError fieldError = list.get(0);
		assertThat(fieldError.getCode(), is("typeMismatch"));

		// エラーメッセージのパラメータ
		Object[] args = fieldError.getArguments();
		assertThat(args.length, is(1));
		assertThat(args[0],
				is(instanceOf(DefaultMessageSourceResolvable.class)));
		DefaultMessageSourceResolvable dmr = (DefaultMessageSourceResolvable) args[0];
		assertThat(dmr.getCode(), is("price"));
	}

	@Test
	public void bookFormへのGET() throws Exception {
		mockMvc.perform(get("/bookForm")).andExpect(status().isOk())
				.andExpect(view().name("check/bookForm"))
				.andExpect(model().hasNoErrors());
	}

	@Test
	public void bookRecvへのPOST_NOT_NULL() throws Exception {
		mockMvc.perform(
				post("/bookRecv").param("name", "よくわかるSpring").param("price",
						"1000")).andExpect(status().isOk())
				.andExpect(view().name("check/bookRecv"))
				.andExpect(model().hasNoErrors());
	}

	@Test
	public void bookRecvへのPOST_nameがnull() throws Exception {
		MvcResult mvcResult = mockMvc
				.perform(post("/bookRecv").param("price", "1000"))
				.andExpect(status().isOk())
				.andExpect(view().name("check/bookForm"))
				.andExpect(model().hasErrors())
				.andExpect(model().errorCount(1))
				.andExpect(model().attributeHasFieldErrors("book", "name"))
				.andExpect(model().attributeExists("book")).andReturn();

		// パラメータのチェック
		ModelAndView mav = mvcResult.getModelAndView();
		Map<String, Object> model = mav.getModel();
		Object bookObject = model.get("book");
		assertThat(bookObject, is(notNullValue()));
		assertThat(bookObject, is(instanceOf(Book.class)));
		Book book = (Book) bookObject;
		assertThat(book.getName(), is(nullValue()));

		// エラーメッセージのチェック
		Object object = mav.getModel().get(
				"org.springframework.validation.BindingResult.book");
		assertThat(object, is(not(nullValue())));
		assertThat(object, is(instanceOf(BindingResult.class)));
		BindingResult bindingResult = (BindingResult) object;

		checkField(bindingResult, "name", "NotNull");
	}

	@Test
	public void bookRecvへのPOST_nameとpriceがnull() throws Exception {
		MvcResult mvcResult = mockMvc.perform(post("/bookRecv"))
				.andExpect(status().isOk())
				.andExpect(view().name("check/bookForm"))
				.andExpect(model().hasErrors())
				.andExpect(model().errorCount(2))
				.andExpect(model().attributeHasFieldErrors("book", "name"))
				.andExpect(model().attributeHasFieldErrors("book", "price"))
				.andExpect(model().attributeExists("book")).andReturn();

		// パラメータのチェック
		ModelAndView mav = mvcResult.getModelAndView();
		Map<String, Object> model = mav.getModel();
		Object bookObject = model.get("book");
		assertThat(bookObject, is(notNullValue()));
		assertThat(bookObject, is(instanceOf(Book.class)));
		Book book = (Book) bookObject;
		assertThat(book.getName(), is(nullValue()));

		// エラーメッセージのチェック
		Object object = mav.getModel().get(
				"org.springframework.validation.BindingResult.book");
		assertThat(object, is(not(nullValue())));
		assertThat(object, is(instanceOf(BindingResult.class)));
		BindingResult bindingResult = (BindingResult) object;

		checkField(bindingResult, "name", "NotNull");
		checkField(bindingResult, "price", "NotNull");
	}

	private void checkField(BindingResult bindingResult, String fieldName,
			String errorCode) {
		// エラーのあるフィールドの取得
		List<FieldError> list = bindingResult.getFieldErrors(fieldName);
		assertThat(list, is(not(nullValue())));
		assertThat(list.size(), is(1));

		// 詳細なエラーチェック
		FieldError fieldError = list.get(0);
		assertThat(fieldError.getCode(), is(errorCode));

		// エラーメッセージのパラメータ
		Object[] args = fieldError.getArguments();
		assertThat(args.length, is(1));
		assertThat(args[0],
				is(instanceOf(DefaultMessageSourceResolvable.class)));
		DefaultMessageSourceResolvable dmr = (DefaultMessageSourceResolvable) args[0];
		assertThat(dmr.getCode(), is(fieldName));
	}

	@Test
	public void bookRecvへのPOST_priceが1() throws Exception {
		MvcResult mvcResult = mockMvc
				.perform(
						post("/bookRecv").param("name", "よく分かるSpring").param(
								"price", "1")).andExpect(status().isOk())
				.andExpect(view().name("check/bookForm"))
				.andExpect(model().hasErrors())
				.andExpect(model().errorCount(0))
				.andExpect(model().attributeExists("book")).andReturn();

		// パラメータのチェック
		ModelAndView mav = mvcResult.getModelAndView();
		Map<String, Object> model = mav.getModel();
		Object bookObject = model.get("book");
		assertThat(bookObject, is(notNullValue()));
		assertThat(bookObject, is(instanceOf(Book.class)));
		Book book = (Book) bookObject;
		assertThat(book.getName(), is("よく分かるSpring"));
		assertThat(book.getPrice(), is(1));
	}

	@Test
	public void bookRecvへのPOST_priceが0() throws Exception {
		MvcResult mvcResult = mockMvc
				.perform(
						post("/bookRecv").param("name", "よく分かるSpring").param(
								"price", "0")).andExpect(status().isOk())
				.andExpect(view().name("check/bookForm"))
				.andExpect(model().hasErrors())
				.andExpect(model().errorCount(1))
				.andExpect(model().attributeHasFieldErrors("book", "price"))
				.andExpect(model().attributeExists("book")).andReturn();

		// パラメータのチェック
		ModelAndView mav = mvcResult.getModelAndView();
		Map<String, Object> model = mav.getModel();
		Object bookObject = model.get("book");
		assertThat(bookObject, is(notNullValue()));
		assertThat(bookObject, is(instanceOf(Book.class)));
		Book book = (Book) bookObject;
		assertThat(book.getName(), is("よく分かるSpring"));
		assertThat(book.getPrice(), is(0));

		// エラーメッセージのチェック
		Object object = mav.getModel().get(
				"org.springframework.validation.BindingResult.book");
		assertThat(object, is(not(nullValue())));
		assertThat(object, is(instanceOf(BindingResult.class)));
		BindingResult bindingResult = (BindingResult) object;

		checkDecimalField(bindingResult, "price", "DecimalMin", true, "1");
	}

	@Test
	public void bookRecvへのPOST_priceが100001() throws Exception {
		MvcResult mvcResult = mockMvc
				.perform(
						post("/bookRecv").param("name", "よく分かるSpring").param(
								"price", "100001")).andExpect(status().isOk())
				.andExpect(view().name("check/bookForm"))
				.andExpect(model().hasErrors())
				.andExpect(model().errorCount(1))
				.andExpect(model().attributeHasFieldErrors("book", "price"))
				.andExpect(model().attributeExists("book")).andReturn();

		// パラメータのチェック
		ModelAndView mav = mvcResult.getModelAndView();
		Map<String, Object> model = mav.getModel();
		Object bookObject = model.get("book");
		assertThat(bookObject, is(notNullValue()));
		assertThat(bookObject, is(instanceOf(Book.class)));
		Book book = (Book) bookObject;
		assertThat(book.getName(), is("よく分かるSpring"));
		assertThat(book.getPrice(), is(100001));

		// エラーメッセージのチェック
		Object object = mav.getModel().get(
				"org.springframework.validation.BindingResult.book");
		assertThat(object, is(not(nullValue())));
		assertThat(object, is(instanceOf(BindingResult.class)));
		BindingResult bindingResult = (BindingResult) object;

		checkDecimalField(bindingResult, "price", "DecimalMax", true, "1");
	}

	private void checkDecimalField(BindingResult bindingResult,
			String fieldName, String errorCode, boolean inclusive, String value) {
		// エラーのあるフィールドの取得
		List<FieldError> list = bindingResult.getFieldErrors(fieldName);
		assertThat(list, is(not(nullValue())));
		assertThat(list.size(), is(1));

		// 詳細なエラーチェック
		FieldError fieldError = list.get(0);
		assertThat(fieldError.getCode(), is(errorCode));

		// エラーメッセージのパラメータ
		Object[] args = fieldError.getArguments();
		assertThat(args.length, is(3));
		assertThat(args[0],
				is(instanceOf(DefaultMessageSourceResolvable.class)));
		DefaultMessageSourceResolvable dmr = (DefaultMessageSourceResolvable) args[0];
		assertThat(dmr.getCode(), is(fieldName));
		// inclusive
		assertThat(args[1], is(instanceOf(Boolean.class)));
		assertThat(args[1], is(inclusive));
		// value
		assertThat(args[2], is(instanceOf(String.class)));
		assertThat(args[2], is(value));
	}

	@Test
	public void bookRecvへのPOST_priceが1_MinMax() throws Exception {
		MvcResult mvcResult = mockMvc
				.perform(
						post("/bookRecv").param("name", "よく分かるSpring").param(
								"price", "1")).andExpect(status().isOk())
				.andExpect(view().name("check/bookRecv"))
				.andExpect(model().hasNoErrors())
				.andExpect(model().errorCount(0))
				.andExpect(model().attributeExists("book")).andReturn();

		// パラメータのチェック
		ModelAndView mav = mvcResult.getModelAndView();
		Map<String, Object> model = mav.getModel();
		Object bookObject = model.get("book");
		assertThat(bookObject, is(notNullValue()));
		assertThat(bookObject, is(instanceOf(Book.class)));
		Book book = (Book) bookObject;
		assertThat(book.getName(), is("よく分かるSpring"));
		assertThat(book.getPrice(), is(1));
	}

	@Test
	public void bookRecvへのPOST_priceが0_MinMax() throws Exception {
		MvcResult mvcResult = mockMvc
				.perform(
						post("/bookRecv").param("name", "よく分かるSpring").param(
								"price", "0")).andExpect(status().isOk())
				.andExpect(view().name("check/bookForm"))
				.andExpect(model().hasErrors())
				.andExpect(model().errorCount(1))
				.andExpect(model().attributeHasFieldErrors("book", "price"))
				.andExpect(model().attributeExists("book")).andReturn();

		// パラメータのチェック
		ModelAndView mav = mvcResult.getModelAndView();
		Map<String, Object> model = mav.getModel();
		Object bookObject = model.get("book");
		assertThat(bookObject, is(notNullValue()));
		assertThat(bookObject, is(instanceOf(Book.class)));
		Book book = (Book) bookObject;
		assertThat(book.getName(), is("よく分かるSpring"));
		assertThat(book.getPrice(), is(0));

		// エラーメッセージのチェック
		Object object = mav.getModel().get(
				"org.springframework.validation.BindingResult.book");
		assertThat(object, is(not(nullValue())));
		assertThat(object, is(instanceOf(BindingResult.class)));
		BindingResult bindingResult = (BindingResult) object;

		checkMinMaxField(bindingResult, "price", "Min", 1L);
	}

	@Test
	public void bookRecvへのPOST_priceが1000001_MinMax() throws Exception {
		MvcResult mvcResult = mockMvc
				.perform(
						post("/bookRecv").param("name", "よく分かるSpring").param(
								"price", "100001")).andExpect(status().isOk())
				.andExpect(view().name("check/bookForm"))
				.andExpect(model().hasErrors())
				.andExpect(model().errorCount(1))
				.andExpect(model().attributeHasFieldErrors("book", "price"))
				.andExpect(model().attributeExists("book")).andReturn();

		// パラメータのチェック
		ModelAndView mav = mvcResult.getModelAndView();
		Map<String, Object> model = mav.getModel();
		Object bookObject = model.get("book");
		assertThat(bookObject, is(notNullValue()));
		assertThat(bookObject, is(instanceOf(Book.class)));
		Book book = (Book) bookObject;
		assertThat(book.getName(), is("よく分かるSpring"));
		assertThat(book.getPrice(), is(100001));

		// エラーメッセージのチェック
		Object object = mav.getModel().get(
				"org.springframework.validation.BindingResult.book");
		assertThat(object, is(not(nullValue())));
		assertThat(object, is(instanceOf(BindingResult.class)));
		BindingResult bindingResult = (BindingResult) object;

		checkMinMaxField(bindingResult, "price", "Max", 100000L);
	}

	private void checkMinMaxField(BindingResult bindingResult,
			String fieldName, String errorCode, Long value) {
		// エラーのあるフィールドの取得
		List<FieldError> list = bindingResult.getFieldErrors(fieldName);
		assertThat(list, is(not(nullValue())));
		assertThat(list.size(), is(1));

		// 詳細なエラーチェック
		FieldError fieldError = list.get(0);
		assertThat(fieldError.getCode(), is(errorCode));

		// エラーメッセージのパラメータ
		Object[] args = fieldError.getArguments();
		assertThat(args.length, is(2));
		assertThat(args[0],
				is(instanceOf(DefaultMessageSourceResolvable.class)));
		DefaultMessageSourceResolvable dmr = (DefaultMessageSourceResolvable) args[0];
		assertThat(dmr.getCode(), is(fieldName));
		// value
		assertThat(args[1], is(instanceOf(Long.class)));
		assertThat(args[1], is(value));
	}
}
