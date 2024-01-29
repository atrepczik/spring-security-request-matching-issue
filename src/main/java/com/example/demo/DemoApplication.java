package com.example.demo;

import java.io.IOException;
import java.util.Set;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.GenericServlet;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

@SpringBootApplication
@RestController
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@GetMapping("/")
	public String index() {
		return "Hello";
	}

	@Bean
	public ServletRegistrationBean<TestServlet> servletRegistrationBean() {
		final var servletRegistrationBean = new ServletRegistrationBean<TestServlet>();
		servletRegistrationBean.setServlet(new TestServlet());
		servletRegistrationBean.setUrlMappings(Set.of("/test"));
		return servletRegistrationBean;
	}

	public static class TestServlet extends GenericServlet {

		@Override
		public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws IOException {
			servletResponse.getOutputStream().write("Hello Servlet".getBytes());
		}
	}
}
