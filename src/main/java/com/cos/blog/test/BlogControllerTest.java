package com.cos.blog.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
// Spring이 com.cos.blog 패키지 이하를 스캔하여 모든 파일을 메모리에 new하는게 아니라
// 특정 어노테이션이 붙어있는 클래스 파일들을 new 하여 (IOC) 스프링 컨테이너에 관리해줌
public class BlogControllerTest {
	@GetMapping("/test/hello")
	public String hello() {
		return "<h1>hello Spring Boot</h1>";
	}
}
