package com.cos.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {
	
	@GetMapping({"", "/"})//아무것도 안적었을 때, /붙였을 때 홈으로 이동
	public String index() {
		return "index";
	}
}
