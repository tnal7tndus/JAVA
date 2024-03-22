package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("redirect:/home");
	}

	public void addViewControllers2(ViewControllerRegistry registry) {
		registry.addViewController("/orderpage").setViewName("redirect:/orderpage");
	}

	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedOrigins("http://localhost:3000") // 클라이언트의 도메인 주소
				.allowedMethods("GET", "POST", "PUT", "DELETE") // 허용할 HTTP 메서드
				.allowedHeaders("*") // 허용할 HTTP 헤더
				.allowCredentials(true); // 인증 정보 허용 여부
	}

}
