package com.cos.blog.test;

import java.util.List;
import java.util.function.Supplier;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	@DeleteMapping("/dummy/user/{id}")
	public String delete(@PathVariable int id) {
		try{
			userRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			return "삭제에 실패하였습니다. 해당 id는 DB에 없습니다.";
		}
		//없는 번호에 대해 삭제할 경우 대비
		return "삭제되었습니다. id : " + id;
	}
	
	
	
	
	
	
	
	
	
	
	
	// save함수는 id를 전달하지 않으면 insert를 해주고
	// save함수는 id를 전달하면 해당 id에 대한 데이터가 있으면 update를 해주고
	// save함수는 id를 전달하면 해당 id에 대한 데이터가 없으면 insert를 한다.
	
	@Transactional	//함수 시작과 동시에 작동, 함수 종료시에 자동 commit
	@PutMapping("/dummy/user/{id}")
	public User updateUser(@PathVariable int id, @RequestBody User requestUser) { // json 데이터를 요청 => java object(message converter의 Jackson 라이브러리가 변환해서 받아줌) 이때 필요한 어노테이션이 requestBody이다.
		System.out.println("id : " + id);
		System.out.println("password : " + requestUser.getPassword());
		System.out.println("email : " + requestUser.getEmail());
		
		User user = userRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("수정에 실패했습니다");
		});//영속화됨
		
		//변경하는 부분*
		user.setPassword(requestUser.getPassword());
		user.setEmail(requestUser.getEmail());

		// userRepository.save(user);
		
		// 더티 체킹 : save를 안해도 업데이트가 된다?
		return user;
	}
	
	
	
	
	
	
	
	
	//http://localhost:8000/blog/dummy/user
	@GetMapping("/dummy/users")
	public List<User> list(){
		return userRepository.findAll();
	}
	
	//한 페이지당 2건의 데이터 리턴받아보자
	@GetMapping("/dummy/user")
	public Page<User> pageList(@PageableDefault(size=2, sort="id", direction = Sort.Direction.DESC) Pageable pageable){
		
		Page<User> pagingUser = userRepository.findAll(pageable);
		//.getcontent()를 붙이면 content만 반환한다.
//		if(pagingUser.isLast()) {
//			
//		}
		List<User> users = pagingUser.getContent();
		return pagingUser;
	}
	
	
	
	
	
	
	
	
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
