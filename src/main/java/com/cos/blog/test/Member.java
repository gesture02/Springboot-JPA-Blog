package com.cos.blog.test;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
//@AllArgsConstructor		//전체 생성자
@NoArgsConstructor		//빈 생성자
public class Member {
	// java에서 변수는 모두 private
	// 변수의 상태는 method로 바꿈
	private int id;
	private String username;
	private String password;
	private String email;
	
	@Builder
	public Member(int id, String username, String password, String email) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
	}
	
	
}
