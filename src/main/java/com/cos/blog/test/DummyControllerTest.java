package com.cos.blog.test;

import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

// html이 아니라 data를 리턴해주는 controller
@RestController
public class DummyControllerTest {
	
	@Autowired// 의존성 주입(DI)
	//DummyControllerTest가 메모리에 뜰 때 같이뜨게해줌
	//UserRepository 타입으로 Spring이 관리하고있는 객체가 있다면 변수에 넣어줌
	//그런데 스프링이 컴포넌트 스캔할때 UserRepository를 메모리에띄워줌 -> 그냥쓰면댐
	private UserRepository userRepository;
	
	//{id} 주소로 파라미터를 전달받을 수 있음
	//http://localhost:8000/blog/dummy/user/3
	@GetMapping("/dummy/user/{id}")
	public User detail(@PathVariable int id) {
		// user/4를 찾으면 없다 -> DB에서 못찾으면 user가 null
		// 그러면 return이 null -> 프로그램에 문제 될 수 도 있음
		// 그래서 Optional로 user 객체를 감싸서 가져올테니 null인지 아닌지 판단하여 return 해라
		User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
			@Override
			public IllegalArgumentException get() {
				return new IllegalArgumentException("해당 유저는 없습니다. id : " + id);
			}
		});
//		//람다식
//		User user = userRepository.findById(id).orElseThrow(()->{
//			return new IllegalArgumentException("해당 유저는 없습니다. id : " + id);
//		});
		// .get() -> null일리 없음
		/* .orElseGet(new Supplier<User>() {
				@Override
				public User get() {
					return new User();
				}
			}); -> 없으면 객체 만들어서 넣어라*/
		
		
		// 요청 = 웹브라우저
		// user 객체 = 자바 오브젝트
		// 자바 오브젝트를 json으로 변환해야함
		// 스프링부트 = MessageConverter라는 애가 응답시에 자동 작동
		// 만약에 자바 오브젝트를 리턴하게 되면 MC가 Jackson이라는 라이브러리를 호출해서
		// user 오브젝트를 json으로 변환해서 브라우저에게 줌
		return user;
	}
	//http://localhost:8000/blog/dummy/join
	@PostMapping("/dummy/join")
	public String join(User user) {
		System.out.println("id: " + user.getId());
		System.out.println("username: " + user.getUsername());
		System.out.println("password: " + user.getPassword());
		System.out.println("email: " + user.getEmail());
		System.out.println("role: " + user.getRole());
		System.out.println("createDate: " + user.getCreateDate());
		
		user.setRole(RoleType.USER);
		userRepository.save(user);
		return "회원가입이 완료되었습니다.";
	}
}
