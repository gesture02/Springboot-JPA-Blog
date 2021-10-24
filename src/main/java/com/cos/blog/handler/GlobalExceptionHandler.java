package com.cos.blog.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice		//exception 발생 시 이 클래스로 오게함
@RestController
public class GlobalExceptionHandler {
	
	@ExceptionHandler(value = IllegalArgumentException.class) // 이 exception이 발생하면 스프링이 이 함수로 그 메시지를 전달해준다.
	public String handleArgumentException(IllegalArgumentException e) {
		return "<h1>" + e.getMessage() + "</h1>";
	}
	//모든 exception을 받고싶으면 Exception으로 하면된다.
}
