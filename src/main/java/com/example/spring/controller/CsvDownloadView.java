package com.example.spring.controller;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.MimeTypeUtils;
import org.springframework.web.servlet.view.AbstractView;

public class CsvDownloadView extends AbstractView {

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		response.setContentType(MimeTypeUtils.APPLICATION_OCTET_STREAM_VALUE
				+ ";charset=utf-8");
		;

		response.setHeader("Content-Disposition",
				"attachment; filename=\"test.csv\"");
		try (PrintWriter pw = response.getWriter()) {
			for (Book book : (List<Book>) model.get("bookList")) {
				pw.print(book.getName());
				pw.print(",");
				pw.println(book.getPrice());
			}
		}
	}
}
