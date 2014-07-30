package com.example.spring.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
				"attachment; filename=\"test.csv\"");
		try (PrintWriter pw = response.getWriter()) {
			pw.println("山田　太郎, 33");
			pw.println("田中　花子, 29");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/csvDown2", method = RequestMethod.GET, produces = "application/octet-stream;charset=utf-8")
	public ResponseEntity<String> csvDown2() {
		HttpHeaders headers = new HttpHeaders();
		// headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		headers.add("contet-type", MimeTypeUtils.APPLICATION_OCTET_STREAM_VALUE
				+ ";utf-8");
		headers.set("Content-Disposition", "filename=\"test2.csv\"");
		String csvData = "山田　太郎,33\r\n";
		csvData = csvData + "田中　花子,29";

		return new ResponseEntity<String>(csvData, headers, HttpStatus.OK);
	}

	@RequestMapping(value = "/csvDown3", method = RequestMethod.GET, produces = MimeTypeUtils.APPLICATION_OCTET_STREAM_VALUE
			+ ";charset=utf-8")
	@ResponseBody
	public String csvDown3(HttpServletResponse response) {
		response.setHeader("Content-Disposition",
				"attachment; filename=\"test3.csv\"");

		String csvData = "山田　太郎,33\r\n";
		csvData = csvData + "田中　花子,29";
		return csvData;
	}

}