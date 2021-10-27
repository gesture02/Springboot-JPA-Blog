package com.cos.blog.controller.api;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.dto.ResponseDto;
import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.service.UserService;

@RestController	//data만 주니까
public class UserApiController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private HttpSession session;
	
	@PostMapping("/api/user")
	public ResponseDto<Integer> save(@RequestBody User user) { // username, password, email
		System.out.println("save");
		// 실제로 DB에 insert 하고 아래에 return 되면됨
		user.setRole(RoleType.USER);
		userService.회원가입(user);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}
	
	//전통적인 로그인방식
	@PostMapping("/api/user/login")
	public ResponseDto<Integer> login(@RequestBody User user) {
		System.out.println("login");
		User principal = userService.로그인(user); // principal (접근 주체)
		
		if (principal != null) {
			// 세션만들기 -> 세션은 매개변수로도 받을 수 있음
			session.setAttribute("principal", principal);
		}
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}
}
