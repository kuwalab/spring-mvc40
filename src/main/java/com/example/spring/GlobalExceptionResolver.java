package com.example.spring;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

public class GlobalExceptionResolver implements HandlerExceptionResolver {
	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception e) {
		System.out.println(e);
		ModelAndView mav = new ModelAndView();
		mav.addObject("errorMessage", "ファイルサイズが大きすぎます。");
		mav.setViewName("req/uploadForm");
		return mav;
	}
}