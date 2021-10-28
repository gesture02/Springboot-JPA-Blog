package com.cos.blog.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.cos.blog.config.auth.PrincipalDetail;

@Controller
public class BoardController {

	@GetMapping({"", "/"})//아무것도 안적었을 때, /붙였을 때 홈으로 이동
	public String index(@AuthenticationPrincipal PrincipalDetail principal) {	//컨트롤러에서 세션에 접근하는법
		System.out.println("login username : " + principal.getUsername());
		return "index";
	}
}
