package com.cos.blog.test;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// 사용자가 요청 -> http파일 응답
// @Controller

// 사용자가 요청 -> Data 응답
@RestController
public class HttpControllerTest {
	
	private static final String TAG = "HttpControllerTest: ";
	//인터넷 브라우저 요청은 get요청밖에 안된다.
	@GetMapping("/http/lombok")
	public String lombokTest() {
		Member m = Member.builder().username("ssal").password("11111").email("ssal@naver.com").build();
		System.out.println(TAG+"getter : " + m.getUsername());
		m.setId(5000);
		System.out.println(TAG+"getter : " + m.getUsername());
		return "lombok test 완료";
	}
	// http://localhost:8080/http/get (select)
	@GetMapping("http/get")
	public String getTest(Member m) {
		
		return "get 요청 : "+ m.getId() + ", " + m.getUsername()  + ", " + m.getPassword() + ", " + m.getEmail();
	}
	// http://localhost:8080/http/post (insert)
	@PostMapping("http/post")	//text/plain, application/json
	public String postTest(@RequestBody Member m) {	//매핑을 messageConverter가 해줌
		return "post 요청 : "+ m.getId() + ", " + m.getUsername()  + ", " + m.getPassword() + ", " + m.getEmail();
//		return  "post 요청 : " + text;
	}
	// http://localhost:8080/http/put (update)
	@PutMapping("http/put")
	public String putTest(@RequestBody Member m) {
		return "put 요청 : "+ m.getId() + ", " + m.getUsername()  + ", " + m.getPassword() + ", " + m.getEmail();

	}
	// http://localhost:8080/http/delete (delete)
	@DeleteMapping("http/delete")
	public String deleteTest() {
		return "delete 요청";
	}
}
