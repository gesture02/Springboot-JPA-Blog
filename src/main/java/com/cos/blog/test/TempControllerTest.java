package com.cos.blog.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TempControllerTest {
	
	@GetMapping("/temp/home")
	public String tempHome() {
		System.out.println("tempHome()");
		// 파일 리턴 시 기본 경로 : src/main/resources/static
		// 리턴 명 : /home.html로 해야 리턴가능
		// static에 jsp를 놓으면 안됨 : 브라우저가 인식할 수 있는 파일만 넣어야함(정적파일)
		return "/home.html";
	}
	@GetMapping("/temp/img")
	public String tempImg() {
		return "/a.png";
	}
	// jsp는 정적인 파일이 아니라( 컴파일 필요: 동적) 오류뜸
	// 경로를 바꿔줘야한다.(yaml)
	@GetMapping("/temp/jsp")
	public String tempJsp() {
		// prefix: /WEB-INF/views/
	    // suffix: .jsp
		// 결과 : /WEB-INF/views/test.jsp
		return "test";
	}
}
