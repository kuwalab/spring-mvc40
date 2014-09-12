package com.example.spring;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

public class MyWebApplicationInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext context) throws ServletException {
		context.addListener(new ContextLoaderListener());
		context.setInitParameter("contextConfigLocation",
				"/WEB-INF/spring/spring-context.xml");

		ServletRegistration.Dynamic dispatcherServlet = context.addServlet(
				"dispatcher", new DispatcherServlet());
		dispatcherServlet.setInitParameter("contextConfigLocation", "");
		dispatcherServlet.setLoadOnStartup(1);
		dispatcherServlet.addMapping("/");

		FilterRegistration.Dynamic characterEncodingFilter = context.addFilter(
				"CharacterEncodingFilter", new CharacterEncodingFilter());
		characterEncodingFilter.setInitParameter("encoding", "utf-8");
		characterEncodingFilter.setInitParameter("forceEncoding", "true");
		characterEncodingFilter.addMappingForUrlPatterns(null, true, "/*");

	}
}
