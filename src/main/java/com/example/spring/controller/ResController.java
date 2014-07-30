package com.example.spring.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ResController {
	@RequestMapping("/csvInit")
	public String csvInit() {
		return "res/csvInit";
	}

	@RequestMapping("csvDown")
	public void csvDown(HttpServletResponse response) {
		response.setContentType(MimeTypeUtils.APPLICATION_OCTET_STREAM_VALUE
				+ ";charset=utf-8");
		response.setHeader("Content-Disposition",
				"attachment; filename=test.csv");
		try (PrintWriter pw = response.getWriter()) {
			pw.println("山田　太郎, 33");
			pw.println("田中　花子, 29");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
