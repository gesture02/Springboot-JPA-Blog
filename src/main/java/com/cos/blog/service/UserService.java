package com.cos.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

// 스프링이 컴포넌트 스캔을 통해 Bean에 등록해줌 (IOC를 해준다: 메모리에 띄워줌)
@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Transactional
	public void 회원가입(User user) {
		userRepository.save(user);
	}
	
	@Transactional(readOnly = true) // select 할때 트랜잭션 시작, 서비스 종료시에 트랜잭션 종료 동안에 정합성 보장
	public User 로그인(User user) {
		return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
	}

}
